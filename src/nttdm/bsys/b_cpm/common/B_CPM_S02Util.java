/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management
 * SERVICE NAME   : B-CPM-S02 Utility
 * FUNCTION       : Get data from database
 * FILE NAME      : B_CPM_S02Util.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/

package nttdm.bsys.b_cpm.common;

import java.util.*;

import jp.terasoluna.fw.dao.*;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.B_CPM_Tab;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlanDetail;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_S02Util.class<br>
 * <ul>
 * <li>define database access to get and update data for B-CPM</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02Util {
	
	/**
	 * STANDARD_PLAN_TYPE
	 */
	public static final String STANDARD_PLAN_TYPE = "SP";
	
	/**
	 * NON_STANDARD_PLAN_TYPE
	 */
	public static final String NON_STANDARD_PLAN_TYPE = "NP";
	
	QueryDAO query;
	UpdateDAO update;
	public B_CPM_S02Util(QueryDAO query){
		this.query = query;
		this.update = null;
	}
	public B_CPM_S02Util(UpdateDAO update){
		this.update = update;
		this.query = null;
	}
	public B_CPM_S02Util(QueryDAO query, UpdateDAO update){
		this.query = query;
		this.update = update;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public List<Object> getListReferencePlan(String idCust){
		List<Object> referencePlans = query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_REFERENCE_PLAN", idCust);
		return referencePlans;
	}
	/**
	 * 
	 * @param idCustomer
	 * @return
	 */
	public Map<String, Object> getCustomerInfo(String idCustomer){
		return query.executeForMap(B_CPM_CONSTANT.NAMESPACE + "GET_CUSTOMER_INFO", idCustomer);
	}
	
	/**
	 * 
	 * @param customerPlan
	 * @return
	 */
	public Object checkBillingAccount(CustomerPlan customerPlan) {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "CHECK_BILLING_ACCOUNT", customerPlan, String.class);
	}
	
	/**
	 * 
	 * @param customerPlan
	 * @return
	 */
	public List<Map<String, Object>> getBillingAccount(CustomerPlan customerPlan) {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_BILLING_ACCOUNT", customerPlan);
	}
	
	/**
	 * 
	 * @param customerPlan
	 * @return
	 */
	public Map<String, Object> getSelectedBillingAccount(CustomerPlan customerPlan) {
		return query.executeForMap(B_CPM_CONSTANT.NAMESPACE + "GET_SELECTED_BILLING_ACCOUNT", customerPlan);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getGrandTotal() {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_GRAND_TOTAL", null);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getSVCLevel1() {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_SVC_LEVEL1", null);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getSVCLevel2() {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_SVC_LEVEL2", null);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getSVCLevel3() {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_SVC_LEVEL3", null);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getSVCLevel4() {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_SVC_LEVEL4", null);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getPaymentMethod() {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_PAYMENT_METHOD", null);
	}
	
	/**
	 * 
	 * @param idCust
	 * @return
	 */
	public List<Map<String, Object>> getJobNo(String idCust) {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_JOB_NO", idCust);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getVendor() {
		return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_VENDOR", null);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Object> getGiroCredit(String idCust){
		return query.executeForMap(B_CPM_CONSTANT.NAMESPACE + "GET_GIRO_CREDIT", idCust);
	}
	/**
	 * 
	 * @return
	 */
	public String getNewIdCustPlanH() {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_NEW_ID_T_CUST_PLAN_H", "", String.class);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNewIdCustPlanD() {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_NEW_ID_T_CUST_PLAN_D", "", String.class);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNewIdCustPlanLink() {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_NEW_ID_T_CUST_PLAN_LINK", "", String.class);
	}
	
	public List<Map<String, Object>> getPlanBatchMapping(Map<String, Object> param) {
		return this.query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "PLAN_AND_BATCH_MAPPING", param);
	}
	
	public int insertCustomerPlanH(CustomerPlan customerPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_T_CUST_PLAN_H", customerPlan);
	}
	
	public int insertSubscriptionInfo(CustomerPlan customerPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_T_SUBSCRIPTION_INFO", customerPlan);
	}
	
	public int updateCustomerPlanH(CustomerPlan customerPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_T_CUST_PLAN_H", customerPlan);
	}
	
	public int deleteCustomerPlanH(CustomerPlan customerPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_H", customerPlan);
	}
	
	public int deleteCustomerPlanHD(CustomerPlan customerPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_H_D", customerPlan);
	}
	
	public int deleteCustomerPlanHLink(CustomerPlan customerPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_H_LINK", customerPlan);
	}
	
	public int deleteCustomerPlanHSVC(CustomerPlan customerPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_H_SVC", customerPlan);
	}
	
	public int insertCustomerPlanD(CustomerPlanService customerPlanService) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_T_CUST_PLAN_D", customerPlanService);
	}
	
	public int updateCustomerPlanD(CustomerPlanService customerPlanService) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_T_CUST_PLAN_D", customerPlanService);
	}
	
	public int deleteCustomerPlanD(List<String> existIdList ,String idAudit, String idTable) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("existIdList", existIdList.toArray());
		param.put("idTable", idTable);
		param.put("idAudit", idAudit);
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_D", param);
	}
	
	public int deleteCustomerPlanDLink(List<String> existIdList ,String idAudit, String idTable) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("existIdList", existIdList.toArray());
		param.put("idTable", idTable);
		param.put("idAudit", idAudit);
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_D_LINK", param);
	}
	
	public int deleteCustomerPlanDSVC(List<String> existIdList ,String idAudit, String idTable) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("existIdList", existIdList.toArray());
		param.put("idTable", idTable);
		param.put("idAudit", idAudit);
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_D_SVC", param);
	}
	
	public int deleteT_BAC_D(List<String> existIdList ,String idAudit, String idTable) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("existIdList", existIdList.toArray());
        param.put("idTable", idTable);
        param.put("idAudit", idAudit);
        return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_BAC_D", param);
	}
	
	public int insertCustomerPlanLink(CustomerSubPlan customerSubPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_T_CUST_PLAN_LINK", customerSubPlan);
	}
	
	public int updateCustomerPlanLink(CustomerSubPlan customerSubPlan) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_T_CUST_PLAN_LINK", customerSubPlan);
	}
	
	public int deleteCustomerPlanLink(List<String> existIdList ,String idAudit, String idTable) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("existIdList", existIdList.toArray());
		param.put("idTable", idTable);
		param.put("idAudit", idAudit);
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_LINK", param);
	}
	
	public int deleteCustomerPlanLinkSVC(List<String> existIdList ,String idAudit, String idTable) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("existIdList", existIdList.toArray());
		param.put("idTable", idTable);
		param.put("idAudit", idAudit);
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_LINK_SVC", param);
	}
	
	/**
	 * 
	 * @param customerSubPlanDetail
	 * @return
	 */
	public int insertCustomerPlanSVC(CustomerSubPlanDetail customerSubPlanDetail) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_T_CUST_PLAN_SVC", customerSubPlanDetail);
	}
	
	/**
	 * 
	 * @param customerSubPlanDetail
	 * @return
	 */
	public int updateCustomerPlanSVC(CustomerSubPlanDetail customerSubPlanDetail) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_T_CUST_PLAN_SVC", customerSubPlanDetail);
	}
	
	/**
	 * 
	 * @param existIdList
	 * @return
	 */
	public int deleteCustomerPlanSVC(List<String> existIdList ,String idAudit, String idTable) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("existIdList", existIdList.toArray());
		param.put("idTable", idTable);
		param.put("idAudit", idAudit);
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_CUST_PLAN_SVC", param);
	}
	
	public CustomerPlan getCustomerPlan(CustomerPlan customerPlan) {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_CUSTOMER_PLAN_H", customerPlan, CustomerPlan.class);
	}
	
	public List<CustomerPlanService> getCustomerPlanService(CustomerPlan customerPlan) {
		return query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_CUSTOMER_PLAN_D", customerPlan);
	}	
	
	public List<CustomerSubPlan> getCustomerSubPlan(CustomerPlanService customerPlanService) {
		return query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_CUSTOMER_PLAN_LINK", customerPlanService);
	}
	
	public List<CustomerSubPlanDetail> getCustomerSubPlanDetail(CustomerSubPlan customerSubPlan) {
		return query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_CUSTOMER_PLAN_SVC", customerSubPlan);
	}
	
	public List<CustomerPlan> getCustomerPlanListByStatus(B_CPM_Tab tab) {
		return query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_CUST_PLAN_LIST_BY_STATUS", tab);
		
	}
	
	public List<CustomerSubPlan> getSubPlanByIdList(String[] idPlanGrpList) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idPlanGrpList", idPlanGrpList);
		return query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_SUB_PLAN_BY_ID_LIST", param);
	}

	public List<CustomerSubPlan> getExistSubPlanByIdList(String[] idCustPlanLinkList) {
	    Map<String, Object> param = new HashMap<String, Object>();
        param.put("idCustPlanLink", idCustPlanLinkList);
        return query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_EXIST_SUB_PLAN_BY_ID_LIST", param);
    }
	
	public List<CustomerSubPlanDetail> getSubPlanDetailByPlan(CustomerSubPlan customerSubPlan) {
		return query.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_SUB_PLAN_DETAIL_BY_PLAN", customerSubPlan);
	}
	
	public int updateServiceStatus(Map<String, Object> param) {
		return update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_SERVICE_STATUS", param);
	}
	
	public int updateServiceStatusUnsuspend(Map<String, Object> param) {
        return update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_SERVICE_STATUS_UNSUSPEND", param);
    }
	
	public String getIdBillAcc(String idCustPlan){
		return query.executeForObject("SELECT.B_CPM.GET_ID_BILL_ACC", idCustPlan, String.class);
	}
    public  Map<String, Object> getAccessAccount(String idCustPlan) {
        return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE
            + "SELECT_ACCESS_ACCOUNT", idCustPlan).get(0);
    }
    public boolean isExistPlanGrp(String idCustPlan) {
        String strCount = query.executeForObject(B_CPM_CONSTANT.NAMESPACE
            + "SELECT_IDCUSTPLAN_COUNT", idCustPlan,java.lang.String.class);
        long count = Long.parseLong(strCount);
        if (count>0) {
            return  true;
        }
        else{
            return false;
        }
    }
    public int deleteSubInfo(CustomerPlan customerPlan) {
        return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "DELETE_T_SUBSCRIPTION_INFO", customerPlan);
       
    }
    
    /**
     * get Transaction Type Enabled Flag
     * @return
     */
	public Map<String, Object> getTransactionTypeFlg(){
		return query.executeForMap(B_CPM_CONSTANT.NAMESPACE + "SELECT_CPM_TRANS_TYPE", null);
	}
	
	/**
     * get Transaction Type Enabled Flag
     * @return
     */
    public String getNonTaxInvoiceShowFlg(){
        return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "getNonTaxInvoiceShowFlg",null, String.class);
    }
	
    /**
     * get EditActivePlan Enabled Flag
     * @return 
     */
    public String getEditActivePlanAMTFlg(){
        return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "getEditActivePlanAMTFlg",null, String.class);
    }
    
	/**
	 * used for Button btnAddStandard,btnAddStandardMul,btnAddNonStandard
	 * @param customerSubPlan
	 * @return
	 */
	public List<Map<String, Object>> getStdAdStdMulAdNonStd() {
	    return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "SELECT_BUTTON_ISENABLED", null);
    }
	
	/**
	 * get B-BIF is used flag
	 * @return true:is used,false,not used
	 */
	public boolean getIsBifModulesUsedFlg(){
	    String isBIFUsed = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_BIF_STATUS", null, String.class);
	    if("1".equals(isBIFUsed)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	/**
     * get M-JNM is used flag
     * @return 0:not display,1:display
     */
    public String getIsJNMModulesDisplayFlg(){
        String isJNMDisplayFlg = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_M_JNM_STATUS", null, String.class);
        isJNMDisplayFlg = CommonUtils.toString(isJNMDisplayFlg);
        if(CommonUtils.isEmpty(isJNMDisplayFlg)) {
            return "0";
        } else {
            return isJNMDisplayFlg;
        }
    }
    
    /**
     * get M-JNM is used flag
     * @return 0:not display,1:display
     */
    public String getIsMasterLocationDisplayFlg(){
    	// #200, #201 wcbeh@20160926 - Set Master Location to display or not
        String isMLocationDisplayFlg = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_M_LOCATION_STATUS", null, String.class);
        isMLocationDisplayFlg = CommonUtils.toString(isMLocationDisplayFlg);
        if(CommonUtils.isEmpty(isMLocationDisplayFlg)) {
            return "0";
        } else {
            return isMLocationDisplayFlg;
        }
    }
    
    /**
     * get standard plan currency
     * @param idPlan
     * @return
     */
    public String getStdPlanCurrency(String idPlan){
        String currency = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_STD_PLAN_CURRENCY", idPlan, String.class);
        return CommonUtils.toString(currency);
    }
    
    public List<Map<String, Object>> getCustAtcInfo(String idCust) {
        List<Map<String, Object>> custAtcInfo = query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "SELECT_CUST_ATC_INFO", idCust);
        return custAtcInfo;
    }
    
    public String getCustomerType(String idCust) {
        String customerType = CommonUtils.toString(query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "SELECT_CUST_TYPE", idCust, String.class)).trim();
        return customerType;
    }
    
    public void insertBAC_H(Map<String, Object> map){
        update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_T_BAC_H", map);
    }
    
    public void updateBAC_H(Map<String, Object> map){
        update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_T_BAC_H", map);
    }
    
    public int getBAC_D_CountByIdBacNo(String id_bill_account) {
        String count = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "SELECT_BAC_D_COUNT", id_bill_account, String.class);
        return Integer.parseInt(count);
    }
    
    public String getBAC_D_CountByServiceID(String idCustPlanGrp) {
        String count = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_SERVICE_BAC_COUNT", idCustPlanGrp, String.class);
        return count;
    }
    
    /**
     * non standard plan get GST and Category same flag
     * @param customerPlan
     */
    public void setApplyAllFlag(CustomerPlan customerPlan) {
        String planType = CommonUtils.toString(customerPlan.getPlanType());
        //non standard plan get GST and Category same flag
        if (B_CPM_S02Util.NON_STANDARD_PLAN_TYPE.equals(planType)) {
            String allGstSameFlg = "0";
            String allCategorySameFlg = "0";
            List<CustomerPlanService> serviceList = customerPlan.getServices();
            for(int i=0;i<serviceList.size();i++) {
                CustomerPlanService service1 = serviceList.get(i);
                for (CustomerSubPlan subPlan1 : service1.getSubPlans()) {
                    String gst1 = CommonUtils.toString(subPlan1.getApplyGst());
                    for(int j=i;j<serviceList.size();j++) {
                        CustomerPlanService service2 = serviceList.get(j);
                        for (CustomerSubPlan subPlan2 : service2.getSubPlans()) {
                            String gst2 = CommonUtils.toString(subPlan2.getApplyGst());
                            if(!gst1.equals(gst2)) {
                                allGstSameFlg = "1";
                                break;
                            }
                        }
                        if("1".equals(allGstSameFlg)) {
                            break;
                        }
                    }
                    if("1".equals(allGstSameFlg)) {
                        break;
                    }
                }
                if("1".equals(allGstSameFlg)) {
                    break;
                }
            }
            
            for(int i=0;i<serviceList.size();i++) {
                CustomerPlanService service1 = serviceList.get(i);
                for (CustomerSubPlan subPlan1 : service1.getSubPlans()) {
                    String svcLevel11 = CommonUtils.toString(subPlan1.getSvcLevel1());
                    String svcLevel21 = CommonUtils.toString(subPlan1.getSvcLevel2());
                    for(int j=i;j<serviceList.size();j++) {
                        CustomerPlanService service2 = serviceList.get(j);
                        for (CustomerSubPlan subPlan2 : service2.getSubPlans()) {
                            String svcLevel12 = CommonUtils.toString(subPlan2.getSvcLevel1());
                            String svcLevel22 = CommonUtils.toString(subPlan2.getSvcLevel2());
                            if(!svcLevel11.equals(svcLevel12)) {
                                allCategorySameFlg = "1";
                                break;
                            }
                            if(!svcLevel21.equals(svcLevel22)) {
                                allCategorySameFlg = "1";
                                break;
                            }
                        }
                        if("1".equals(allCategorySameFlg)) {
                            break;
                        }
                    }
                    if("1".equals(allCategorySameFlg)) {
                        break;
                    }
                }
                if("1".equals(allCategorySameFlg)) {
                    break;
                }
            }
            
            customerPlan.setAllGstSameFlg(allGstSameFlg);
            customerPlan.setAllCategorySameFlg(allCategorySameFlg);
        }
    }
    
    public String getIsCheckMulCharFlg() {
        String isCheckMulCharFlg = CommonUtils.toString(query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_IS_CHECK_MUL_CHAR", null, String.class)).trim();
        
        return isCheckMulCharFlg;
    }
    
    public String getIsB_SSMModulesDisplayFlg(){
        String isB_SSMDisplayFlg = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_B_SSM_STATUS", null, String.class);
        isB_SSMDisplayFlg = CommonUtils.toString(isB_SSMDisplayFlg);
        if(CommonUtils.isEmpty(isB_SSMDisplayFlg)) {
            return "0";
        } else {
            return isB_SSMDisplayFlg;
        }
    }
    
    public String getFixedForexFlg() {
        String fixedForexFlg = CommonUtils.toString(query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_FIXED_FOREX_FLAG", null, String.class)).trim();
        
        return fixedForexFlg;
    }
    
    public String getNewAccCheckFlg() {
        String newAccCheckFlg = CommonUtils.toString(query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_NEW_ACC_CHECK_FLAG", null, String.class)).trim();
        
        return newAccCheckFlg;
    }
    
    public int updateT_CUST_PLAN_D_ServiceStatus(Map<String, Object> param) {
        return update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_T_CUST_PLAN_D_SERVICESTATUS", param);
    }
    
    public int updateT_CUST_PLAN_H_PlanStatus(Map<String, Object> param) {
        return update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_T_CUST_PLAN_H_PLANSTATUS", param);
    }
    
    public List<Map<String, Object>> getPlanBatchMappingByIdCustPlan(String idCustPlan) {
        return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_BATCH_MAPPING_BY_ID_CUST_PLAN", idCustPlan);
    }
    
    public List<Map<String, Object>> getPlanBatchMappingByIdCustPlanActiveService(String idCustPlan) {
        return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_BATCH_MAPPING_BY_ID_CUST_PLAN_AND_SERVICE_STATUS", idCustPlan);
    }
    
    public Map<String, Object> getSubScriptionInfoByIdCustPlan(String idCustPlan) {
        return query.executeForMap(B_CPM_CONSTANT.NAMESPACE + "GET_SUBSCIPTION_BY_ID_CUST_PLAN", idCustPlan);
    }
    public List<Map<String, Object>> getActiveServicePlanBatchMappingByIdCustPlan(Map<String, Object> sqlParam) {
        return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_ACTIVE_SERVICE_BATCH_MAPPING_BY_ID_CUST_PLAN", sqlParam);
    }
    public List<Map<String, Object>> getPlanBatchMappingByIdPlanGrp(List<String> idPlanGrpList) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idPlanGrpList", idPlanGrpList);
        return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_BATCH_MAPPING_BY_ID_PLAN_GRP", sqlParam);
    }
    public List<Map<String, Object>> getPlanBatchMappingByExistIdPlanGrp(List<String> idPlanGrpList, List<String> idCustPlanLinkList, String idCustPlan) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idPlanGrpList", idPlanGrpList);
        sqlParam.put("idCustPlanLinkList", idCustPlanLinkList);
        sqlParam.put("idCustPlan", idCustPlan);
        return query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_BATCH_MAPPING_BY_EXIST_ID_PLAN_GRP", sqlParam);
    }
    public boolean isSubPlanMailHosting(List<String> subPlan_idPlanGrp) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idPlanGrpList", subPlan_idPlanGrp);
        int count = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_SUB_PLAN_MAIL_HOSTING_BY_ID_PLAN_GRP", sqlParam, Integer.class);
        if (0<count) {
            return true;
        } else {
            return false;
        }
    }
    public List<Map<String, Object>> getOptionServiceMailHosting(String idPlan) {
        List<Map<String, Object>> list = query.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_OPTION_SERVICE_MAIL_HOSTING_BY_ID_PLAN_GRP", idPlan);
        return list;
    }
    public void updateMAuditTrailH(Map<String, Object> sqlParam) {
        update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_M_AUDIT_TRAIL_H", sqlParam);
    }
    
    public void updateMAuditTrailD(Map<String, Object> sqlParam) {
        update.execute(B_CPM_CONSTANT.NAMESPACE + "UPDATE_M_AUDIT_TRAIL_D", sqlParam);
    }
    
    public void insertMAuditTrailD(Map<String, Object> sqlParam) {
        update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_M_AUDIT_TRAIL_D", sqlParam);
    }
    
    public boolean hasIdCustPlanLink(CustomerSubPlan subPlan) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idCustPlanLink", subPlan.getIdCustPlanLink());
        sqlParam.put("idCustPlanGrp", subPlan.getIdCustPlanGrp());
        int count = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_ID_CUST_PLAN_LINK", sqlParam, Integer.class);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
    }
    
    public boolean hasIdCustPlanSvc(String idCustPlanLink) {
        int count = query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_ID_CUST_PLAN_SVC", idCustPlanLink, Integer.class);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
    }
    
	public String getNewIdCustPlanSvc() {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_NEW_ID_T_CUST_PLAN_SVC", "", String.class);
	}
	
	public int insertCustomerPlanSVC2(CustomerSubPlanDetail customerSubPlanDetail) {
		return this.update.execute(B_CPM_CONSTANT.NAMESPACE + "INSERT_T_CUST_PLAN_SVC2", customerSubPlanDetail);
	}
	
	public String getCustPoDisplay() {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_IS_CUST_PO_DISPLAY", null, String.class);
	}
	
	public String getRateType() {
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_IS_RATE_TYPE2", null, String.class);
	}
	
	public String getRateTypeVal(Map<String, String> param){
		return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_IS_RATE_TYPE2_CHANGE", param, String.class);
	}
	//#141 Add Existing Sub-Plan/Option Service button disable and hide button
	public String getAddExistingPlanFlg(){
        return query.executeForObject(B_CPM_CONSTANT.NAMESPACE + "getAddExistingPlanFlg",null, String.class);
    }
}
