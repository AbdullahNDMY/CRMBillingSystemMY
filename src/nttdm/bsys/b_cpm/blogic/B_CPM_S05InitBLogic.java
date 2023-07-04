/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management B-CPM-S02v
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Display B-CPM-S02v
 * FILE NAME      : B_CPM_S05BLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/10/03 [Duong Nguyen] Fix bug #2688
 * 2011/10/18 [Duong Nguyen] Update DD change #2761
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.B_CPM_Common;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlanDetail;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_S02ViewBLogic.class<br>
 * <ul>
 * <li>process to display B-CPM-S02</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S05InitBLogic extends AbstractB_CPM_S02ViewBLogic {
	
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();

		CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
		String fromScreen = customerPlan.getFromScreen();
		String billType = customerPlan.getBillType();
		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO);
		
		String custPlanMPlanCurDifFlg = CommonUtils.toString(customerPlan.getCustPlanMPlanCurDifFlg());
		
		customerPlan = this.loadCustomerPlan(customerPlan, util);
		
		customerPlan.setFromScreen(fromScreen);
		customerPlan.setBillType(billType);
		//M_JNM Display flag
		customerPlan.setM_jnmDisplayFlg(util.getIsJNMModulesDisplayFlg());
		
		//#227 wcbeh@20170213 - Get Master Location Display condition
        customerPlan.setMasterLocationDisplayFlg(util.getIsMasterLocationDisplayFlg());
        		
		customerPlan.setB_ssmIsUsed(util.getIsB_SSMModulesDisplayFlg());
		
		customerPlan.setCustPlanMPlanCurDifFlg(custPlanMPlanCurDifFlg);
		
		customerPlan.setFixedForexFlg(util.getFixedForexFlg());
		
		Map<String, Object> loadObject = new HashMap<String, Object>();
		loadObject.put("CUSTOMER_INFO", util.getCustomerInfo(customerPlan.getIdCust()));
		loadObject.put("GRAND_TOTAL", util.getGrandTotal());
		loadObject.put("REFERENCE_PLAN", util.getListReferencePlan(customerPlan.getIdCust()));
		loadObject.put("SVC_LEVEL1", util.getSVCLevel1());
		loadObject.put("SVC_LEVEL2", util.getSVCLevel2());
		loadObject.put("SVC_LEVEL3", util.getSVCLevel3());
		loadObject.put("SVC_LEVEL4", util.getSVCLevel4());
		loadObject.put("VENDOR", util.getVendor());
		loadObject.put("PAYMENT_METHOD", util.getPaymentMethod());
		loadObject.put("JOB_NO", util.getJobNo(customerPlan.getIdCust()));

		//Radio button NonTaxInvoice Show Flg
        String nontaxinvoiceFlg=util.getNonTaxInvoiceShowFlg();
        loadObject.put("NonTaxInvoiceShowFlg", nontaxinvoiceFlg);
        
        // custPo display
        String custPoDis = util.getCustPoDisplay();
        loadObject.put("CustPoDisplay", custPoDis);
        
		// #189 Start
		String disBillingOption = queryDAO.executeForObject("B_CPM.GET_disBillingOption", null, String.class);
		customerPlan.setDisBillingOption(disBillingOption);
		// #189 End
		
		String taxTypeDisplay = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_TYPE_DISPLAY", null, String.class));
		customerPlan.setTaxTypeDisplay(taxTypeDisplay);
		String taxWord = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_WORD", null, String.class));
		customerPlan.setTaxWord(taxWord);
		
		customerPlan = B_CPM_Common.restructureCustomerPlan(customerPlan, true);
		Map<String, Object> resultObject = new HashMap<String, Object>();
		resultObject.put("customerPlan", customerPlan);
		resultObject.put("LOAD_OBJECT", loadObject);
		
		result.setResultObject(resultObject);
		result.setResultString("success");
		return result;
	}
	
	/**
	 * 
	 * @param customerPlan
	 * @param util
	 * @return
	 */
	private CustomerPlan loadCustomerPlan(CustomerPlan customerPlan, B_CPM_S02Util util) {
		//load customer plan
		customerPlan = util.getCustomerPlan(customerPlan);
		
		//have customer plan
		if (customerPlan != null) {
			//load service
			List<CustomerPlanService> services = util.getCustomerPlanService(customerPlan);
			customerPlan.setServices(services);
			for (CustomerPlanService service : services) {
				
				//load sub plan
				List<CustomerSubPlan> subPlans = util.getCustomerSubPlan(service);
				service.setSubPlans(subPlans);
				String idPlan = "";
				for (CustomerSubPlan subPlan : subPlans) {
					idPlan = subPlan.getIdPlan();
					//load sub plan detail
					List<CustomerSubPlanDetail> detail = util.getCustomerSubPlanDetail(subPlan);
					subPlan.setSubPlanDetails(detail);
				}
				service.setServiceIdPlan(idPlan);
			}
		} else {
			customerPlan = CustomerPlan.EmptyCustomerPlan();
		}
		return customerPlan;
	}
}