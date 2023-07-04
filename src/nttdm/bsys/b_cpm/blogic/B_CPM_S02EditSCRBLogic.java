/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (new/edit customer plan)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : load data for edit/new plan screen
 * FILE NAME      : B_CPM_S02EditSCRBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.TabController;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_S02EditSCRBLogic.class<br>
 * <ul>
 * <li>process display edit screen</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02EditSCRBLogic extends AbstractB_CPM_S02EditSCRBLogic {
	
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

		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO);
		CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
		String planType = CommonUtils.toString(customerPlan.getPlanType());
		Map<String, Object> loadObject = new HashMap<String, Object>();
		loadObject.put("CUSTOMER_INFO", util.getCustomerInfo(customerPlan.getIdCust()));
		loadObject.put("GRAND_TOTAL", util.getGrandTotal());
		//not standard plan used
		if (B_CPM_S02Util.NON_STANDARD_PLAN_TYPE.equals(planType)) {
			loadObject.put("REFERENCE_PLAN", util.getListReferencePlan(customerPlan.getIdCust()));
		} else {
			//standard plan Deleted
			loadObject.put("REFERENCE_PLAN", new ArrayList<Object>());
		}
		Map<String, List<LabelValueBean>> resutlMapSvc2=setSvcLevelToMap(util.getSVCLevel2());
        Map<String, List<LabelValueBean>> resutlMapSvc3=setSvcLevelToMap(util.getSVCLevel3());
        Map<String, List<LabelValueBean>> resutlMapSvc4=setSvcLevelToMap(util.getSVCLevel4());
		loadObject.put("SVC_LEVEL1", util.getSVCLevel1());
		loadObject.put("SVC_LEVEL2", util.getSVCLevel2());
		loadObject.put("SVC_LEVEL3", util.getSVCLevel3());
		loadObject.put("SVC_LEVEL4", util.getSVCLevel4());
		loadObject.put("MLABELVALUEBEAN2", resutlMapSvc2);
		loadObject.put("MLABELVALUEBEAN3", resutlMapSvc3);
		loadObject.put("MLABELVALUEBEAN4", resutlMapSvc4);
		loadObject.put("VENDOR", util.getVendor());
		List<Map<String, Object>> billingAccount = util.getBillingAccount(customerPlan); 
		loadObject.put("BILLING_ACCOUNT", billingAccount);
		//In-approval
		if ("PS2".equals(customerPlan.getPlanStatus())) {
		    loadObject.put("CHECK_BILLING_ACCOUNT", "1");
		} else {
		    loadObject.put("CHECK_BILLING_ACCOUNT", util.checkBillingAccount(customerPlan));
		}
		loadObject.put("JOB_NO", util.getJobNo(customerPlan.getIdCust()));
		
		//Radio button NonTaxInvoice Show Flg
		String nontaxinvoiceFlg=util.getNonTaxInvoiceShowFlg();
		loadObject.put("NonTaxInvoiceShowFlg", nontaxinvoiceFlg);
		
		//#75->Edit Active Plan - no allow to edit amount like edit plan in approval status by global setting
		String editactiveplanflg=CommonUtils.toString(util.getEditActivePlanAMTFlg());
        loadObject.put("EditActivePlanAMTFlg", editactiveplanflg);
		
        // custPo display
        String custPoDis = util.getCustPoDisplay();
        loadObject.put("CustPoDisplay", custPoDis);
        
        // rateType2
        String rateType2Flg = util.getRateType();
        loadObject.put("RateType2Flg", rateType2Flg);
        
        // #141 Add Existing Sub-Plan/Option Service button disable and hide button
        String addExistingPlanflg=CommonUtils.toString(util.getAddExistingPlanFlg());
        loadObject.put("AddExistingPlanflg", addExistingPlanflg);
		/* 
		 * this code for bug 2934
		 * from 75 -> 120 line, added by longhb
		 */
		List<Map<String, Object>> payment = util.getPaymentMethod();
		Map<String, Object> giro_credit =  util.getGiroCredit(customerPlan.getIdCust());

		if(giro_credit==null){ // idCust is not in m_cust_bankinfo
			//remove GIRO_ACCT_NO && CCARD_NO from payment
			for (int i=0; i<payment.size(); i++) {
				if(payment.get(i).get("RESOURCE_ID").equals("GR") || payment.get(i).get("RESOURCE_ID").equals("CC")){					
					payment.remove(i);
					i--;
				}
			}
			loadObject.put("PAYMENT_METHOD",payment);
		} else { // idCust is in m_cust_bankinfo
			if(giro_credit.get("GIRO_ACCT_NO")==null && giro_credit.get("CCARD_NO")==null){
				// remove GIRO_ACCT_NO && CCARD_NO
				for (int i=0; i<payment.size(); i++) {
					if(payment.get(i).get("RESOURCE_ID").equals("GR") || payment.get(i).get("RESOURCE_ID").equals("CC")){					
						payment.remove(i);
						i--;
					}
				}
				loadObject.put("PAYMENT_METHOD",payment);
			} else if(giro_credit.get("GIRO_ACCT_NO")!=null && giro_credit.get("CCARD_NO")==null){
				// remove CCARD_NO
				for (int i=0; i<payment.size(); i++) {
					if(payment.get(i).get("RESOURCE_ID").equals("CC")){					
						payment.remove(i);
						i--;
					}
				}
				loadObject.put("PAYMENT_METHOD",payment);
			} else if (giro_credit.get("GIRO_ACCT_NO")==null && giro_credit.get("CCARD_NO")!=null){
				// remove GIRO_ACCT_NO
				for (int i=0; i<payment.size(); i++) {
					if(payment.get(i).get("RESOURCE_ID").equals("GR")){					
						payment.remove(i);
						i--;
					}
				}
				loadObject.put("PAYMENT_METHOD",payment);
			} else { //giro_credit.get("GIRO_ACCT_NO")!=null && giro_credit.get("CCARD_NO")!=null
				loadObject.put("PAYMENT_METHOD",payment);
			}
		}// end bug 2934
		
		Map<String, Object> returnObject = new HashMap<String, Object>();
		returnObject.put("LOAD_OBJECT", loadObject);
		
		//generate tab control
		boolean isBifUsedFlg = util.getIsBifModulesUsedFlg();
		TabController tabController = TabController.getEmptyTab(isBifUsedFlg);
		tabController.addTabByStatus(customerPlan.getPlanStatus());
		
		returnObject.put("customerPlan", customerPlan);
		returnObject.put("tabController", tabController);
		if(param.get("action") == null || param.get("action").equals("")){
			returnObject.put("action","new");
		} else {
			returnObject.put("action", param.get("action"));
		}  
	
		result.setResultObject(returnObject);
		result.setResultString("success");
		return result;
	}
	
	
	private Map<String, List<LabelValueBean>> setSvcLevelToMap(List<Map<String, Object>> paramSvcLevel){
	    Map<String, List<LabelValueBean>> resultMap=new  HashMap<String, List<LabelValueBean>>();
	    
	    List<LabelValueBean> resultList;
	    for (int j = 0; j < paramSvcLevel.size(); j++) {
            Map<String, Object> paramsvcmap=paramSvcLevel.get(j);
            String paramkey=CommonUtils.toString(paramsvcmap.get("SVC_GRP"));
            if (resultMap.containsKey(paramkey)) {
                String label=CommonUtils.toString(paramsvcmap.get("SVC_NAME"));
                String value=CommonUtils.toString(paramsvcmap.get("ID_SERVICE"));
                resultList=resultMap.get(paramkey);
                resultList.add(new org.apache.struts.util.LabelValueBean(label,value));
            }else{
                resultList=new ArrayList<LabelValueBean>();
                String label=CommonUtils.toString(paramsvcmap.get("SVC_NAME"));
                String value=CommonUtils.toString(paramsvcmap.get("ID_SERVICE"));
                resultList.add(new org.apache.struts.util.LabelValueBean(label,value));
                resultMap.put(paramkey, resultList);
            }
        }
	    return resultMap;
	}
}