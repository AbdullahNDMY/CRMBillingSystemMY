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

import org.apache.commons.lang.StringUtils;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.B_CPM_Common;
import nttdm.bsys.b_cpm_en.dto.B_CPM_Tab;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlanDetail;
import nttdm.bsys.b_cpm_en.dto.TabController;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
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
public class B_CPM_S02ViewBLogic extends AbstractB_CPM_S02ViewBLogic {

	String viewScreen;
	
	/**
	 * 
	 * @param param
	 * @return ビジネスロジチE��の実行結果、BLogicResultインスタンス、E
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();

		// TODO
		// QueryDAO 記述侁E
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述侁E
		// updateDAO.execute("namespace.sqlID", params);
		//
		CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
		String accessSubmit = (String)param.get("accessSubmit");
		TabController tabController = (TabController)param.get("tabController");
		Object action = param.get("action");
		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO);
		
		this.viewScreen = customerPlan.getScreen();
		String custPlanMPlanCurDifFlg = CommonUtils.toString(customerPlan.getCustPlanMPlanCurDifFlg());
		
		//initialize tab info
		if (tabController == null || tabController.getActiveTab() == null
                || (action!= null && (action.equals("terminate") || action.equals("approve")
                        || action.equals("cancel") || action.equals("draft")
                        || action.equals("suspend") || action.equals("unsuspend")))) {
		    boolean isBifUsedFlg = util.getIsBifModulesUsedFlg();
		    tabController = new TabController(isBifUsedFlg);
			
			customerPlan = this.loadCustomerPlan(customerPlan, util);
						
			tabController.setActiveTab(TabController.getActiveTab(customerPlan.getPlanStatus()));
			
			//load tab info
			this.loadCustomerPlanList4EachPlanStaus(tabController, util, customerPlan.getIdCust());
			tabController.seletedTab().setCurrentCustomerPlanId(customerPlan.getIdCustPlan());
			tabController.synchronizeTabinfo();
			
		} else {
			// #169 Start
			/*//load tab info
			this.loadCustomerPlanList4EachPlanStaus(tabController, util, customerPlan.getIdCust());
						
			//process for tab control
			String idCustPlan = tabController.seletedTab().getCurrentCustomerPlanId();
			tabController.synchronizeTabinfo();
			
			customerPlan.setIdCustPlan(idCustPlan);
			customerPlan = this.loadCustomerPlan(customerPlan, util);*/
			
			String activeTabString = tabController.getActiveTab();
			//load tab info
			this.loadCustomerPlanList4EachPlanStaus(tabController, util, customerPlan.getIdCust());
			String idCustPlan = tabController.seletedTab().getCurrentCustomerPlanId();
			
			tabController = new TabController(util.getIsBifModulesUsedFlg());
			customerPlan.setIdCustPlan(idCustPlan);
			customerPlan = this.loadCustomerPlan(customerPlan, util);
			tabController.setActiveTab(activeTabString);
			
			this.loadCustomerPlanList4EachPlanStaus(tabController, util, customerPlan.getIdCust());
			tabController.seletedTab().setCurrentCustomerPlanId(idCustPlan);
			tabController.synchronizeTabinfo();
			// #169 End
		}
		
		if (this.viewScreen != null && !this.viewScreen.equals("")) {
			customerPlan.setScreen(this.viewScreen);
		}
		//M_JNM Display flag
		customerPlan.setM_jnmDisplayFlg(util.getIsJNMModulesDisplayFlg());
		
		customerPlan.setB_ssmIsUsed(util.getIsB_SSMModulesDisplayFlg());
		
		customerPlan.setCustPlanMPlanCurDifFlg(custPlanMPlanCurDifFlg);
		
		customerPlan.setFixedForexFlg(util.getFixedForexFlg());
		
		String taxTypeDisplay = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_TYPE_DISPLAY", null, String.class));
		customerPlan.setTaxTypeDisplay(taxTypeDisplay);

		
		//#200, #201 wcbeh@2010926 - Master Location Display Condition
		customerPlan.setMasterLocationDisplayFlg(util.getIsMasterLocationDisplayFlg());
		
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
		
		customerPlan = B_CPM_Common.restructureCustomerPlan(customerPlan, true);
		//non standard plan get GST and Category same flag
		util.setApplyAllFlag(customerPlan);
		
		Map<String, Object> mapTransactionTypeFlg = util.getTransactionTypeFlg();
        if (mapTransactionTypeFlg!=null) {
            customerPlan.setTransactionTypeFlg("0");
        }
		// custPo display
        String custPoDis = util.getCustPoDisplay();
        loadObject.put("CustPoDisplay", custPoDis);
        
        // #187 Start
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)param.get("uvo");
        String idUser = uvo.getId_user();
        Map<String,Object> inputMap = new HashMap<String, Object>();
        inputMap.put("idUser", idUser);
        String accessType = queryDAO.executeForObject("B_CPM.GET_ACCESS_TYPE", inputMap, String.class);
        String approveButton = queryDAO.executeForObject("B_CPM.GET_APPROVE_BUTTON", null, String.class);
        loadObject.put("ACCESS_TYPE", accessType);
        loadObject.put("APPROVE_BUTTON", approveButton);
		loadObject.put("TAX_WORD",
				CommonUtils.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_WORD", null, String.class)));
        // #187 End
		// #189 Start
		String disBillingOption = queryDAO.executeForObject("B_CPM.GET_disBillingOption", null, String.class);
		customerPlan.setDisBillingOption(disBillingOption);
		// #189 End
        
		Map<String, Object> resultObject = new HashMap<String, Object>();
		resultObject.put("customerPlan", customerPlan);
		resultObject.put("accessSubmit", accessSubmit);
		resultObject.put("tabController", tabController);
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
		
		String planType = CommonUtils.toString(customerPlan.getPlanType());
		//setting screen
		if (this.viewScreen == null || this.viewScreen.equals("")) {
			customerPlan.setScreen(B_CPM_CONSTANT.SCR_B_CPM_02);
		} 
		
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
					if (B_CPM_S02Util.NON_STANDARD_PLAN_TYPE.equals(planType)) {
					    subPlan.setCurrency(customerPlan.getBillCurrency());
					}
					//set Exist Plan Currency
					if (StringUtils.isEmpty(subPlan.getCurrency()) && B_CPM_S02Util.STANDARD_PLAN_TYPE.equals(planType)) {
					    String idCustPlanLink=CommonUtils.toString(subPlan.getIdCustPlanLink());
					    Map<String, Object> m = new HashMap<String, Object>();
					    m.put("idCustPlanLink", idCustPlanLink);
					    String currency=queryDAO.executeForObject("B_CPM.GET_EXIST_SUB_PLAN_CURRENCY", m, String.class);
					    subPlan.setCurrency(currency);
					}
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
	
	/**
	 * 
	 * @param tabController
	 * @param util
	 */
	private void loadCustomerPlanList4EachPlanStaus(TabController tabController, B_CPM_S02Util util, String idCust) {
		for (B_CPM_Tab tab : tabController.getTabs()) {
			tab.setIdCust(idCust);
			tab.setStatus(TabController.getStatusByTabId(tab.getId()));
			tab.setCustomerPlanList(util.getCustomerPlanListByStatus(tab));
		}
	}
}