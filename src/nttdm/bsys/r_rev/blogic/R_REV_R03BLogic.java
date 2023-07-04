/**
 * @(#)R_REV_R01BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-8-29

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rev.blogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.r_rev.dto.EXCEL_LIST;
import nttdm.bsys.r_rev.dto.R_REV_R03Input;
import nttdm.bsys.r_rev.dto.R_REV_R03Output;

/**
 * BusinessLogic class.
 * 
 * @author p-minzh
 */
public class R_REV_R03BLogic extends AbstractR_REV_R03BLogic {

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_REV_R03Input input) {
        BLogicResult result = new BLogicResult();
        
        R_REV_R03Output outputDTO = new R_REV_R03Output();
        
        String reportMonth = this.getReportMonth(input.getMonthlyReportYear(),
				input.getMonthlyReportMonth());
               
        List<EXCEL_LIST> resultList = new ArrayList<EXCEL_LIST>();
        List<EXCEL_LIST> resultList2 = new ArrayList<EXCEL_LIST>();
        
        if(input.getCurrentPage().equals("2")){
        	List<String> reportBy = new ArrayList<String>();
        	reportBy.add("CAT");
        	reportBy.add("CSL");
        	reportBy.add("DTL");
        	
        	Map<String, Object> param = new HashMap<String, Object>();
            param.put("reportMonth", reportMonth);
            param.put("reportBy", reportBy);       	
        	
        	resultList = this.queryDAO.executeForObjectList("R_REV.GET_EXCEL_FILE_LIST", param);
        	
        	 if(resultList == null || resultList.size() == 0){        	
     		    // info.ERR2SC002
                 BLogicMessages msgs = new BLogicMessages();
                 BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                 msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                 result.setMessages(msgs);
     		} 
        	
        	String hvMonthClosed = this.queryDAO.executeForObject("R_REV.CHECK_RPT_MTH_CLOSED", reportMonth, String.class);
        	outputDTO.setReGenerateFlag(resultList.size() == 0 && !hvMonthClosed.equals("0") ? "Y" : "N"); 
        }else if(input.getCurrentPage().equals("3")){
        	List<String> reportBy = new ArrayList<String>();
        	reportBy.add("ADV1");
        	
        	Map<String, Object> param = new HashMap<String, Object>();        	
            param.put("reportMonth", reportMonth);   
        	param.put("reportBy", reportBy);
        	
        	resultList = this.queryDAO.executeForObjectList("R_REV.GET_EXCEL_FILE_LIST", param);
        	
        	reportBy.clear();
        	reportBy.add("ADV2");
        	Map<String, Object> param2 = new HashMap<String, Object>();
            param2.put("reportMonth", reportMonth);   
        	param2.put("reportBy", reportBy);        	
        	
        	resultList2 = this.queryDAO.executeForObjectList("R_REV.GET_EXCEL_FILE_LIST", param2);        	
        	
        	if((resultList == null || resultList.size() == 0) && (resultList2 == null || resultList2.size() == 0)){        	
    		    // info.ERR2SC002
                BLogicMessages msgs = new BLogicMessages();
                BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                result.setMessages(msgs);
    		} 
        }             
               
        outputDTO.setResultList(resultList);
        outputDTO.setResultList2(resultList2);
        
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
    
    private String getReportMonth(String year, String month) {
		String rslt = "";
		SimpleDateFormat inSDF = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyyMM");
		try {
			rslt = outSDF.format(inSDF.parse(year + month));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rslt;
	}
}