/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (load plan)
 * SERVICE NAME   : B_CPM_S01
 * FUNCTION       : Get Customer Plan Object
 * FILE NAME      : B_CPM_S02_LoadPlanBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/08/02 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import nttdm.bsys.b_cpm.blogic.AbstractB_CPM_S02_LoadPlanBLogic;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.B_CPM_Common;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlanDetail;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_S02_LoadPlanBLogic.class<br>
 * <ul>
 * <li>load customer plan information</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02_LoadPlanBLogic extends AbstractB_CPM_S02_LoadPlanBLogic {

    private static final String ADD_STDPLN = "ADD_STDPLN";
    private static final String ADD_NON_STDPLN = "ADD_NON_STDPLN";
    private static final String ADD_STDPLN_MULTI = "ADD_STDPLN_MULTI";
    private static final String ENABLED_FLAG = "1";
    
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
		CustomerPlan customerPlan;
		Object paramPlan = param.get("customerPlan");
		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO);
		if (paramPlan != null) {
			customerPlan = (CustomerPlan)paramPlan;
			if (customerPlan.getIdCustPlan() == null || customerPlan.getIdCustPlan().equals("")) {
				String idCust = customerPlan.getIdCust();
				String planType = customerPlan.getPlanType();
				String idPlan = customerPlan.getIdPlanParam();
				String planName = customerPlan.getPlanNameParam();
				String multiPln = customerPlan.getMultiPln();
				customerPlan = new CustomerPlan();
				customerPlan.setIdCust(idCust);
				customerPlan.setPlanType(planType);
				customerPlan.setIdPlanParam(idPlan);
				customerPlan.setPlanNameParam(planName);
				customerPlan.setMultiPln(multiPln);
				String taxType = CommonUtils
						.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_TYPE_DEFAULT", null, String.class));
				customerPlan.setTaxType(taxType);
			} else {
				//load customer plan
				customerPlan = util.getCustomerPlan(customerPlan);
				//load service
				List<CustomerPlanService> services = util.getCustomerPlanService(customerPlan);
				customerPlan.setServices(services);
				for (CustomerPlanService service : services) {
					// #177 Start
					List<CustomerSubPlan> subPlanS = new ArrayList<CustomerSubPlan>();
					List<CustomerSubPlan> subPlanO = new ArrayList<CustomerSubPlan>();
					// #177 End
					//load sub plan
					List<CustomerSubPlan> subPlans = util.getCustomerSubPlan(service);
					String idPlan = "";
					service.setSubPlans(subPlans);
					service.setMasterLocationFlg("1");
					for (CustomerSubPlan subPlan : subPlans) {
						if (StringUtils.isEmpty(subPlan.getCurrency())) {
						    subPlan.setCurrency(customerPlan.getBillCurrency());
						}
					    idPlan = subPlan.getIdPlan();
						//load sub plan detail
						List<CustomerSubPlanDetail> detail = util.getCustomerSubPlanDetail(subPlan);
						subPlan.setSubPlanDetails(detail);
						// #177 Start
						if ("S".equals(subPlan.getItemType())) {
							subPlanS.add(subPlan);
						}
						if ("O".equals(subPlan.getItemType())) {
							subPlanO.add(subPlan);
						}
						// #177 End
						
						// #200, #201 wcbeh@20160922 - Set Master Location Flag uncheck if subLocation set
						if (!StringUtils.isEmpty(subPlan.getSubLocation())) {
							service.setMasterLocationFlg("0");
						}
					}
					// #177 Start
					subPlans.clear();
					subPlans.addAll(subPlanS);
					subPlans.addAll(subPlanO);
					// #177 End
					service.setServiceIdPlan(idPlan);
					//Service in BAC billing data
					service.setServiceBacCount(util.getBAC_D_CountByServiceID(service.getIdCustPlanGrp()));
				}
				//get first service's sub plan
				List<CustomerSubPlan> firstServiceSubPlanList = services.get(0).getSubPlans();
				if(firstServiceSubPlanList!=null && 0<firstServiceSubPlanList.size()) {
                    customerPlan.setIdPlanParam(firstServiceSubPlanList.get(0).getIdPlan());
                    customerPlan.setMultiPln(services.get(0).getServiceMultiPln());
                    
					// #200, #201 wcbeh@20160922 - Set Master Location Flag uncheck if subLocation set
                    services.get(0).setMasterLocationFlg("1");
                    if (!StringUtils.isEmpty(firstServiceSubPlanList.get(0).getSubLocation())) {
                    	services.get(0).setMasterLocationFlg("0");
					}
                    
                }
			}
		} else {
			customerPlan = new CustomerPlan();
		}
		customerPlan = B_CPM_Common.restructureCustomerPlan(customerPlan, false);
		
		String planType = CommonUtils.toString(customerPlan.getPlanType());
		if (B_CPM_S02Util.STANDARD_PLAN_TYPE.equals(planType)) {
			// used for Button btnAddStandard,btnAddStandardMul,btnAddNonStandard
	        List<Map<String, Object>> listBtnDisplayFlg = util.getStdAdStdMulAdNonStd();
	        if(listBtnDisplayFlg!=null && 0<listBtnDisplayFlg.size()) {
	            for(Map<String, Object> map : listBtnDisplayFlg) {
	                String idSet = CommonUtils.toString(map.get("ID_SET"));
	                String setValue = CommonUtils.toString(map.get("SET_VALUE"));
	                if (ADD_STDPLN.equals(idSet)) {
	                    if (ENABLED_FLAG.equals(setValue)) {
	                        customerPlan.setAddStdPln("1");
	                    }
	                } else if (ADD_NON_STDPLN.equals(idSet)){
	                    if (ENABLED_FLAG.equals(setValue)) {
	                        customerPlan.setAddNonStdPln("1");
	                    }
	                } else if (ADD_STDPLN_MULTI.equals(idSet)){
	                    if (ENABLED_FLAG.equals(setValue)) {
	                        customerPlan.setAddStdPlnMul("1");
	                    }
	                }
	            }
	        }
		}
		
		Map<String, Object> mapTransactionTypeFlg = util.getTransactionTypeFlg();
        if (mapTransactionTypeFlg!=null) {
            customerPlan.setTransactionTypeFlg("0");
        }
		
		//non standard plan get GST and Category same flag
		util.setApplyAllFlag(customerPlan);
		
		//M_JNM Display flag
        customerPlan.setM_jnmDisplayFlg(util.getIsJNMModulesDisplayFlg());
        customerPlan.setB_ssmIsUsed(util.getIsB_SSMModulesDisplayFlg());
        customerPlan.setFixedForexFlg(util.getFixedForexFlg());
        customerPlan.setNewAccCheckFlg(util.getNewAccCheckFlg());
        
        //#200, #201 wcbeh@20160926 - Get Master Location Display condition
        customerPlan.setMasterLocationDisplayFlg(util.getIsMasterLocationDisplayFlg());
        
		// #189 Start
		String disBillingOption = queryDAO.executeForObject("B_CPM.GET_disBillingOption", null, String.class);
		customerPlan.setDisBillingOption(disBillingOption);
		// #189 End
		
		String taxTypeDisplay = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_TYPE_DISPLAY", null, String.class));
		customerPlan.setTaxTypeDisplay(taxTypeDisplay);
		String taxTypeDefault = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_TYPE_DEFAULT", null, String.class));
		customerPlan.setTaxTypeDefault(taxTypeDefault);
		String taxIdDefault = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_ID_DEFAULT", null, String.class));
		customerPlan.setTaxIdDefault(taxIdDefault);
		String taxWord = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_WORD", null, String.class));
		customerPlan.setTaxWord(taxWord);
		
		
		
		Map<String, Object> resultObject = new HashMap<String, Object>();
		resultObject.put("customerPlan", customerPlan);
		resultObject.put("action", param.get("action"));
		result.setResultObject(resultObject);
		result.setResultString("success");
		return result;
	}
}