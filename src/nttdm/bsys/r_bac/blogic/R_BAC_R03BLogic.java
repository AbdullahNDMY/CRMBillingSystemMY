/**
 * @(#)R_BAC_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.blogic;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_RPT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
import nttdm.bsys.r_bac.dto.R_BAC_R02Input;
import nttdm.bsys.r_bac.dto.R_BAC_R03Input;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * R_BAC_R03BLogic class.
 * 
 * @author NTT DATA
 */
public class R_BAC_R03BLogic extends AbstractR_BAC_R03BLogic{
	
	/**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
	public BLogicResult execute(R_BAC_R03Input input) {
		BLogicResult result = new BLogicResult();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		List<Map<String, Object>> searchResults = new ArrayList<Map<String, Object>>();
		R_BAC_R02Input paramInput = new R_BAC_R02Input();
		try {
		        BeanUtils.copyProperties(paramInput, input);
		    } catch (IllegalAccessException e) {
		        e.printStackTrace();
		    } catch (InvocationTargetException e) {
		        e.printStackTrace();
		    }
		searchResults = queryDAO.executeForMapList("SELECT.R_BAC.SQL001",paramInput);
		
		HashMap<String, Object> item = new HashMap<String, Object>();
//		String id_bill_account = "";
//		BigDecimal amountA = BigDecimal.ZERO;
//        BigDecimal amountC = BigDecimal.ZERO;
//        BigDecimal amountD = BigDecimal.ZERO;
//        BigDecimal totalAmountDue=BigDecimal.ZERO;
    	DecimalFormat formater = new DecimalFormat("0.00");
    	formater.setRoundingMode(RoundingMode.HALF_UP);
        
		for (int i = 0; i < searchResults.size(); i++) {
			item = (HashMap<String, Object>)searchResults.get(i);
			item.put("DATA_REQ", i+1);
//			id_bill_account = item.get("ID_BILL_ACCOUNT").toString();
//			amountA = queryDAO.executeForObject("SELECT.R_BAC.SQL003", id_bill_account, BigDecimal.class);
//			amountC = queryDAO.executeForObject("SELECT.R_BAC.SQL004", id_bill_account, BigDecimal.class);
//			amountD = queryDAO.executeForObject("SELECT.R_BAC.SQL005", id_bill_account, BigDecimal.class);
//			BigDecimal amountB = amountA.subtract(amountC).subtract(amountD);
//			item.put("ACTUAL_AMOUNT", formater.format(amountB));
//			totalAmountDue = new BigDecimal(CommonUtils.toString(item.get("TOTAL_AMT_DUE")));
//			item.put("TOTAL_AMT_DUE", formater.format(totalAmountDue));
//			item.put("VARIANCE_AMOUNT", formater.format(totalAmountDue.subtract(amountB)));

			item.put("TOTAL_AMT_DUE", formater.format(item.get("TOTAL_AMT_DUE")));
			item.put("ACTUAL_AMOUNT", formater.format(item.get("ACTUAL_AMOUNT")));
			item.put("VARIANCE_AMOUNT", formater.format(item.get("VARIANCE_AMOUNT")));
			item.put("PAYMENT_METHOD", CommonUtils.getCodeMapListNameByValue("LIST_PAYMENT_METHOD",CommonUtils.toString(item.get("PAYMENT_METHOD"))));
			item.put("BILL_CURRENCY", item.get("BILL_CURRENCY").toString());
			//item.put("BILL_CURRENCY", CommonUtils.getCodeMapListNameByValue("LIST_CURRENCY",item.get("BILL_CURRENCY").toString()));
			resultList.add(item);
		}
        // Call G_RPT_P01
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        // get G_RPT_P01 object
        G_RPT_P01 gRptP01 = (G_RPT_P01) context.getBean("G_RPT_P01");
        G_RPT_P01_Output gRptP01Output = new G_RPT_P01_Output();
        G_RPT_P01_Input gRptP01Input = new G_RPT_P01_Input();
        gRptP01Input.setListAgingReport(resultList);
        gRptP01Input.setReportType("TTL");
        // The method execute is called G_RPT_P01
        GlobalProcessResult gRptResult =
            gRptP01.execute(gRptP01Input, gRptP01Output);
        // error info
        result.setErrors(gRptResult.getErrors());
        // message info
        result.setMessages(gRptResult.getMessages());
        // Set download file
        DownloadFile file = new DownloadFile(gRptResult.getFile());
        result.setResultObject(file);
        return result;
	}
}
