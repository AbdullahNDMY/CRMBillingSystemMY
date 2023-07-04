/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management Inquiry
 * SERVICE NAME   : B_CPM_S01
 * FUNCTION       : Search data from database
 * FILE NAME      : B_CPM_S01SearchBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.CodeListLoader;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.InputSearchPlan;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionMessages;

/**
 * B_CPM_S01SearchBLogic.class<br>
 * <ul>
 * <li>search customer plan info inquiry</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S01SearchBLogic extends AbstractB_CPM_S01SearchBLogic implements B_CPM_CONSTANT {

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

		InputSearchPlan inputSearch = (InputSearchPlan)param.get("inputSearchPlan");
		String jobNo = inputSearch.getJobNo();
		inputSearch.setJobNo(CommonUtils.escapeSQL(jobNo));
		inputSearch = this.processInputSeach(inputSearch);
		//get search result
		List<Map<String, Object>> searchResult = null;
		if (param.get("action") != null && param.get("action").equals("generateBIF")) {
			inputSearch.setIdScreen(param.get("idScreen"));
			searchResult = queryDAO.executeForMapList(NAMESPACE + "SEARCH_CUST_PLAN_H", inputSearch);
		} else {
			searchResult = queryDAO.executeForMapList(NAMESPACE + "SEARCH_CUST_PLAN_H", inputSearch, inputSearch.getStartIndex(), inputSearch.getRow());
		}
		String totalCount = queryDAO.executeForObject(NAMESPACE + "SEARCH_CUST_PLAN_H_COUNT", inputSearch, String.class);
		inputSearch.setTotalCount(totalCount);
		
		if("0".equals(totalCount)){
		    // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
		}
		
		// Process Bill Inst
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

		//get detail customer plan
		for (Map<String, Object> plan : searchResult) {
			List<Map<String, Object>> subPlanList = queryDAO.executeForMapList(NAMESPACE + "SEARCH_CUST_PLAN_D", plan);
			plan = this.processCustomerPlanH(plan);
			
			plan.put("SUB_PLAN", subPlanList);
			//get link customer plan
			double total = 0.00;
			for (Map<String, Object> subPlan : subPlanList) {
				Integer length = CommonUtils.getSystemLength(queryDAO);
				subPlan.put("BILL_DESC", CommonUtils.formatString(subPlan.get("BILL_DESC"), length));
				List<Map<String, Object>> subPlanLinkList = queryDAO.executeForMapList(NAMESPACE + "SEARCH_CUST_PLAN_LINK", subPlan);
				subPlan.put("SUB_PLAN_LINK", subPlanLinkList);
				double sub_total = 0.00;	
				for (Map<String, Object> subPlanLink : subPlanLinkList) {
					subPlanLink.put("ITEM_DESC", CommonUtils.formatString(subPlanLink.get("ITEM_DESC"), length));
					sub_total += Double.parseDouble(subPlanLink.get("TOTAL_AMOUNT").toString());
				}
				subPlan.put("TOTAL_AMOUNT", sub_total);
				total += sub_total;
			}
			plan.put("TOTAL_AMOUNT", total);
		}
		
		inputSearch.setJobNo(jobNo);
		Map<String, Object> resultObject = new HashMap<String, Object>();
		resultObject.put("inputSearchPlan", inputSearch);
		resultObject.put("searchResult", searchResult);
		result.setResultObject(resultObject);
		result.setResultString("success");
		return result;
	}
	
	/**
	 * 
	 * @param inputSearch
	 * @return
	 */
	private InputSearchPlan processInputSeach(InputSearchPlan inputSearch) {
		//Customer Information
		if (inputSearch != null) {
            inputSearch.setSearchCustomerId(Trim(inputSearch.getSearchCustomerId()));
            inputSearch.setSearchCustomerName(Trim(inputSearch.getSearchCustomerName()));

			//Customer Plan Information
			inputSearch.setSubscriptionId(Trim(inputSearch.getSubscriptionId()));
			inputSearch.setBillingAccount(Trim(inputSearch.getBillingAccount()));
		}
		return inputSearch;
	}
	
	private String Trim(String value) {
		if (value != null) {
			value = value.trim();
		}
		return value;
	}
	
	/**
	 * 
	 * @param plan
	 * @return
	 */
	private Map<String, Object> processCustomerPlanH(Map<String, Object> plan) {
		Object bacNo = plan.get("ID_BILL_ACCOUNT");
		if (bacNo == null || bacNo.equals("") || bacNo.toString().trim().equals("NEW")) {
			plan.put("ID_BILL_ACCOUNT", "-");
		}
		return plan;
	}
}