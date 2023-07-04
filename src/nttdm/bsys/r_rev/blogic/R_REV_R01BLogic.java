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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_rev.dto.R_REV_R01Input;
import nttdm.bsys.r_rev.dto.R_REV_R01Output;

/**
 * BusinessLogic class.
 * 
 * @author p-minzh
 */
public class R_REV_R01BLogic extends AbstractR_REV_R01BLogic {
	
	/**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_REV_R01Input param) {
        BLogicResult result = new BLogicResult();
        R_REV_R01Output outputDTO = new R_REV_R01Output();
        
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUser", param.getUvo().getId_user());
        //m.put("currentPage", param.getCurrentPage());
        HashMap<String, Object> userAccess =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "R_REV.getAccessType", m);
        if (!CommonUtils.isEmpty(userAccess)) {
            outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        }
        
        Map<String, Object> dt = new HashMap<String, Object>();
        dt = queryDAO.executeForMap("R_REV.GET_INIT_DATE", null);
        
        outputDTO.setFinancialStart(dt.get("FINANCIALSTART").toString());
        outputDTO.setFinancialEnd(dt.get("FINANCIALEND").toString());
        outputDTO.setClosingMonth(dt.get("CLOSINGMONTH").toString());
        outputDTO.setReportMonth1(dt.get("REPORTMONTH1").toString());
        outputDTO.setReportYear1(dt.get("REPORTYEAR1").toString());
        outputDTO.setReportMonth2(dt.get("REPORTMONTH2").toString());
        outputDTO.setReportYear2(dt.get("REPORTYEAR2").toString());
        outputDTO.setReportMonth3(dt.get("REPORTMONTH2").toString());
        outputDTO.setReportYear3(dt.get("REPORTYEAR2").toString());
        
        if(CommonUtils.isEmpty(param.getCurrentPage()))
        {
        	param.setCurrentPage("2");
        	outputDTO.setCurrentPage("2");
        }
        else
        	outputDTO.setCurrentPage(param.getCurrentPage());
        
        // All Service Status check boxes are selected by default
        //outputDTO.setTbxServiceStatus(new String[]{"PS1", "PS3", "PS6", "PS7"});
        
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}