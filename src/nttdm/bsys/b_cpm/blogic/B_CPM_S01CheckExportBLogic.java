/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (Export)
 * SERVICE NAME   : B_CPM_E01
 * FUNCTION       : check export data isn't empty 
 * FILE NAME      : B_CPM_S01CheckExportBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.CodeListLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.b_cpm.blogic.AbstractB_CPM_S01CheckExportBLogic;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.InputSearchPlan;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.util.ApplicationContextProvider;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_CPM_S01CheckExportBLogic extends
		AbstractB_CPM_S01CheckExportBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		InputSearchPlan inputSearch = (InputSearchPlan)param.get("inputSearchPlan");
		//get search result
		//List<Map<String, Object>> searchResult = queryDAO.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_EXPORT_CUSTOMER_PLAN", inputSearch, inputSearch.getStartIndex(), inputSearch.getRow());
		List<Map<String, Object>> searchResult = queryDAO.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_EXPORT_CUSTOMER_PLAN", inputSearch);
				
		CodeListLoader codeList= (CodeListLoader) ApplicationContextProvider.getApplicationContext().getBean("BILL_INSTRUCTION_LIST");
		CodeBean[] results = codeList != null ? codeList.getCodeBeans() : null;
		
		for (Map<String, Object> record : searchResult) {
			for(CodeBean cBean : results){
				if(record.get("BILL_INSTRUCT").equals(cBean.getId())){
					String fm = cBean.getName();
					fm = fm.substring(fm.indexOf('(')+1, fm.indexOf(')'));
					record.put("BILL_INSTRUCT",fm);
					break;
				}	
			}
		}

		Map<String, Object> resultObject = new HashMap<String, Object>();
		resultObject.put("inputSearchPlan", inputSearch);
		resultObject.put("searchResult", searchResult);
		result.setResultObject(resultObject);
		if (searchResult.size() == 0) {
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		} else {
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		}
		return result;
	}
}