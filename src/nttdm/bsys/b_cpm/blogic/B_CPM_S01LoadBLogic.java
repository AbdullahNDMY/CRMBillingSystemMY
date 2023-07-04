/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management Inquiry
 * SERVICE NAME   : B_CPM_S01
 * FUNCTION       : Get data from database
 * FILE NAME      : B_CPM_S01LoadBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/09/27 [Duong Nguyen] Fix search after back from Edit and View screen
 * 2011/09/28 [Duong Nguyen] Fix DD change (B_CPM_S01_P1-27_r3_20110913.xlsx) include #2654 and condition to display BAC
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.InputSearchPlan;

import nttdm.bsys.b_cpm.blogic.AbstractB_CPM_S01LoadBLogic;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_S01LoadBLogic.class<br>
 * <ul>
 * <li>load search screen inquiry</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S01LoadBLogic extends AbstractB_CPM_S01LoadBLogic implements B_CPM_CONSTANT {
	
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(Map<String, Object> param) {
		BLogicResult result = new BLogicResult();
		
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		
		Map<String, Object> resultObject = new HashMap<String, Object>();
		
		List<Map<String, Object>> category = queryDAO.executeForMapList(NAMESPACE + "GET_SVC_LEVEL1", null);
		List<Map<String, Object>> service = queryDAO.executeForMapList(NAMESPACE + "GET_SVC_LEVEL2", null);
		List<Map<String, Object>> plan = queryDAO.executeForMapList(NAMESPACE + "GET_SVC_LEVEL3", null);
		List<Map<String, Object>> planDetail = queryDAO.executeForMapList(NAMESPACE + "GET_SVC_LEVEL4", null);
		
		List<Map<String, Object>> custList = queryDAO.executeForMapList(NAMESPACE + "GET_CUST_LIST", null);
		
		String isBIFUsed = queryDAO.executeForObject(NAMESPACE + "GET_BIF_STATUS", null, String.class);
		Map<String, Object> loadObject = new HashMap<String, Object>();
		
		String jobNoDisplayCount = queryDAO.executeForObject(NAMESPACE + "GET_JOB_NO_STATUS", null, String.class);
		
		loadObject.put("CATEGORY", category);
		loadObject.put("SERVICE", service);
		loadObject.put("PLAN", plan);
		loadObject.put("PLAN_DETAIL", planDetail);
		loadObject.put("CUST_LIST", custList);
		loadObject.put("isBIFUsed", isBIFUsed);
		loadObject.put("jobNoDisplayCount", jobNoDisplayCount);
		
		//Radio button NonTaxInvoice Show Flg
		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO);
        String nontaxinvoiceFlg=util.getNonTaxInvoiceShowFlg();
        loadObject.put("NonTaxInvoiceShowFlg", nontaxinvoiceFlg);
        
		InputSearchPlan inputSearchPlan;
		if (param.get("inputSearchPlan") == null || ((InputSearchPlan)param.get("inputSearchPlan")).getSearchCustomerId() == null) {	
			//setting default search start
			inputSearchPlan = new InputSearchPlan();
			inputSearchPlan.setStartIndex(0);
		} else {
			inputSearchPlan = (InputSearchPlan)param.get("inputSearchPlan");
		}
		
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
		String row = String.valueOf(systemSetting.getResultRow());
		inputSearchPlan.setRow(Integer.parseInt(row));
		resultObject.put("inputSearchPlan", inputSearchPlan);
		
		if (param.get("searchResult") == null) {
			resultObject.put("searchResult", new ArrayList<Map<String, Object>>());
		} else {
			resultObject.put("searchResult", param.get("searchResult"));
		}
		resultObject.put("customerPlan", new CustomerPlan());
		resultObject.put("LOAD_OBJECT", loadObject);
		
		Map<String, Object> cpmTransType = this.queryDAO.executeForMap(NAMESPACE + "GET_CPM_TRANS_TYPE", null);
		resultObject.put("cpmTransType", CommonUtils.toString(cpmTransType.get("SET_VALUE")));
		
		String b_ssmIsUsed = CommonUtils.toString(queryDAO.executeForObject(NAMESPACE + "GET_B_SSM_STATUS", null, String.class));
        if(CommonUtils.isEmpty(b_ssmIsUsed)) {
            b_ssmIsUsed = "0";
        }
        resultObject.put("b_ssmIsUsed", b_ssmIsUsed);
        
        // #208 Start
        String disBillingOption = queryDAO.executeForObject("B_CPM.GET_disBillingOption", null, String.class);
        loadObject.put("disBillingOption", disBillingOption);
        // #208 End
		
		result.setResultObject(resultObject);
		if (param.get("action") != null && param.get("action").equals("generateBIF")) {
			result.setResultString("BIFsuccess");
		} else {
			result.setResultString("success");
		}
		return result;
	}
}