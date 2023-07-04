/**
 * @(#)B_CPM_S02NewUtil.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_cpm.common;

import java.text.SimpleDateFormat;
import java.util.*;

import jp.terasoluna.fw.dao.*;
import nttdm.bsys.b_cpm.dto.*;
import nttdm.bsys.common.util.BillingSystemSettings;

/**
 * Common methods for B_CPM_S02
 * 
 * @author feawindh
 */
public class B_CPM_S02NewUtil {
	/**
	 * <div>QueryDAO</div>
	 */
	QueryDAO query;

	/**
	 * <div>UpdateDAO</div>
	 */
	UpdateDAO update;

	/**
	 * <div>Constructor</div>
	 * 
	 * @param query
	 *            QueryDAO
	 */
	public B_CPM_S02NewUtil(QueryDAO query) {
		this.query = query;
		this.update = null;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param update
	 *            UpdateDAO
	 */
	public B_CPM_S02NewUtil(UpdateDAO update) {
		this.update = update;
		this.query = null;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param query
	 *            QueryDAO
	 * @param update
	 *            UpdateDAO
	 * 
	 */
	public B_CPM_S02NewUtil(QueryDAO query, UpdateDAO update) {
		this.query = query;
		this.update = update;
	}

	/**
	 * <div>Get bill account id by customer plan id</div>
	 * 
	 * @param idCustPlan
	 *            customer plan id
	 * 
	 * @return String bill acount id
	 */
	public String getIdBillAcc(String idCustPlan) {
		return query.executeForObject("SELECT.B_CPM.GET_ID_BILL_ACC",
				idCustPlan, String.class);
	}

	/**
	 * <div>Get service info by plan group id and customer plan id</div>
	 * 
	 * @param idPlanGroup
	 *            plan group id
	 * @param idCustPlan
	 *            customer plan id
	 * 
	 * @return Service found service info
	 */
	public Service getServiceInfo(String idPlanGroup, String idCustPlan) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("idPlanGroup", idPlanGroup);
		map.put("idCustPlan", idCustPlan);
		Service service = query.executeForObject(
				"SELECT.B_CPM.GET_SERVICE_INFO", map, Service.class);
		if (service != null) {
			List<PlanService> details = getDetails(service.getIdPlanGroup(),
					service.getRateType(), service.getServiceGroup());
			List<PlanService> listServiceType = getListPlanServices(
					service.getServiceGroup(), PlanService.TYPE);
			List<PlanService> listServiceItem = getListPlanServices(
					service.getServiceGroup(), PlanService.ITEM);

			service.setDetails(details);
			service.setListServiceItem(listServiceItem);
			service.setListServiceType(listServiceType);
		}
		return service;
	}

	/**
	 * <div>check if the service is existed by plan group id and customer plan
	 * id</div>
	 * 
	 * @param idPlanGroup
	 *            plan group id
	 * @param idCustPlan
	 *            customer plan id
	 * 
	 * @return Boolean true:existed false:not existed
	 */
	public Boolean isServiceExisted(String idPlanGroup, String idCustPlan) {
		Boolean isExisted = false;
		if (getServiceInfo(idPlanGroup, idCustPlan) != null) {
			isExisted = true;
		}
		return isExisted;
	}

	/**
	 * <div>Get plan service info by plan service id</div>
	 * 
	 * @param idPlanService
	 *            plan service id
	 * 
	 * @return PlanService found plan service info
	 */
	public PlanService getPlanServiceInfo(String idPlanService) {
		return query.executeForObject("SELECT.B_CPM.GET_PLANSERVICE_INFO",
				idPlanService, PlanService.class);
	}

	/**
	 * <div>check if the plan service is existed by plan service id</div>
	 * 
	 * @param idPlanService
	 *            plan service id
	 * 
	 * @return Boolean true:existed false:not existed
	 */
	public Boolean isPlanServiceExisted(String idPlanService) {
		Boolean isExisted = false;
		if (getPlanServiceInfo(idPlanService) != null) {
			isExisted = true;
		}
		return isExisted;
	}

	/**
	 * <div>Get plan info by plan id</div>
	 * 
	 * @param idPlan
	 *            plan id
	 * 
	 * @return Plan found plan info
	 */
	public Plan getPlanInfo(String idPlan) {
		// Get plan info from M_PLAN_H
		Plan plan = query.executeForObject("SELECT.B_CPM.M_PLAN_H", idPlan,
				Plan.class);
		return plan;
	}

	/**
	 * <div>Get customer plan info by customer plan id</div>
	 * 
	 * @param idCustPlan
	 *            customer plan id
	 * 
	 * @return Plan found customer plan info
	 */
	public Plan getCustomerPlanInfo(String idCustPlan) {
		// Get customerplan info from T_CUST_PLAN_D
		Plan plan = query.executeForObject("SELECT.B_CPM.T_CUST_PLAN_D",
				idCustPlan, Plan.class);
		String initialBac = plan.getInitialBac();
		if (initialBac != null) {
			if (initialBac.equals("NEW")) {
				plan.setNewAcc("NEW");
			} else {
				plan.setNewAcc("0");
			}
		}
		// Get list subplans
		List<Service> subPlans = getListServiceByIdCustPlan(idCustPlan,
				Service.SUBPLAN);
		// Get list option services
		List<Service> optionServices = getListServiceByIdCustPlan(idCustPlan,
				Service.OPTIONSERVICE);
		combineOptionService(optionServices);
		plan.setSubPlans(subPlans);
		plan.setOptionServices(optionServices);

		return plan;
	}

	/**
	 * <div>Get service list info by plan id and item type</div>
	 * 
	 * @param idPlan
	 *            plan id
	 * @param itemType
	 *            item type
	 * 
	 * @return List<Service> found service info list
	 */
	public List<Service> getListServiceByIdPlan(String idPlan, String itemType) {
		HashMap<String, String> map = new HashMap<String, String>();
		// Get list subplans
		map.put("idPlan", idPlan);
		map.put("itemType", itemType);
		List<Service> services = query.executeForObjectList(
				"SELECT.B_CPM.S02_GET_LIST_SERIVCE", map);
		for (int i = 0; i < services.size(); i++) {
			Service service = services.get(i);
			List<PlanService> details = getDetails(service.getIdPlanGroup(),
					service.getRateType(), service.getServiceGroup());
			List<PlanService> listServiceType = getListPlanServices(
					service.getServiceGroup(), PlanService.TYPE);
			List<PlanService> listServiceItem = getListPlanServices(
					service.getServiceGroup(), PlanService.ITEM);

			service.setDetails(details);
			service.setListServiceItem(listServiceItem);
			service.setListServiceType(listServiceType);
		}
		return services;
	}

	/**
	 * <div>Get service list info by customer plan id and item type</div>
	 * 
	 * @param idCustPlan
	 *            customer plan id
	 * @param itemType
	 *            item type
	 * 
	 * @return List<Service> found service info list
	 */
	public List<Service> getListServiceByIdCustPlan(String idCustPlan,
			String itemType) {
		HashMap<String, String> map = new HashMap<String, String>();
		// Get list subplans
		map.put("idCustPlan", idCustPlan);
		map.put("itemType", itemType);
		List<Service> services = query.executeForObjectList(
				"SELECT.B_CPM.S02_GET_LIST_SUBPLAN_OR_OPTIONSERVICE", map);
		for (int i = 0; i < services.size(); i++) {
			Service service = services.get(i);
			List<PlanService> details = getDetails(service.getIdPlanGroup(),
					service.getRateType(), service.getServiceGroup());
			List<PlanService> listServiceType = getListPlanServices(
					service.getServiceGroup(), PlanService.TYPE);
			List<PlanService> listServiceItem = getListPlanServices(
					service.getServiceGroup(), PlanService.ITEM);

			service.setDetails(details);
			service.setListServiceItem(listServiceItem);
			service.setListServiceType(listServiceType);
		}
		return services;
	}

	/**
	 * <div>Get details list info by plan group id, rate type and service group
	 * id</div>
	 * 
	 * @param idPlanGroup
	 *            plan group id
	 * @param rateType
	 *            rate type
	 * @param serviceGroup
	 *            service group id
	 * 
	 * @return List<Service> found plan service info list
	 */
	public List<PlanService> getDetails(String idPlanGroup, String rateType,
			String serviceGroup) {
		List<PlanService> details = query.executeForObjectList(
				"SELECT.B_CPM.S02_GET_LIST_SUBPLAN_DETAILS", idPlanGroup);
		for (int i = 0; i < details.size(); i++) {
			details.get(i).setRateType(rateType);
			details.get(i).setSvc_grp(serviceGroup);
		}
		return details;
	}

	/**
	 * <div>Get plan service list info by service group id and plan service
	 * type</div>
	 * 
	 * @param serviceGroup
	 *            service group id
	 * @param planServiceType
	 *            plan service type
	 * 
	 * @return List<Service> found plan service info list
	 */
	public List<PlanService> getListPlanServices(String serviceGroup,
			String planServiceType) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", planServiceType);
		map.put("svc_grp", serviceGroup);
		return query.executeForObjectList(
				"SELECT.B_CPM.LOAD_SERVICE_TYPE_OR_ITEM", map);

	}

	/**
	 * <div>Get customer info by bustomer id</div>
	 * 
	 * @param idCustomer
	 *            customer id
	 * 
	 * @return Customer found customer info
	 */
	public Customer getCustomerInfo(String idCustomer) {
		return query.executeForObject("SELECT.B_CPM.CUSTOMER_INFO", idCustomer,
				Customer.class);
	}

	/**
	 * <div>check if the set value of 'SYSTEMBASE' is 'SG' </div>
	 * 
	 * @return int 1:display 0:non_display
	 */
	public int getDisplayExportSingPort() {
		String setValue = query.executeForObject("SELECT.B_CPM.GET_SYSTEMBASE",
				null, String.class);
		if (setValue.equals("SG")) {
			return Item.DISPLAY;
		} else {
			return Item.NON_DISPLAY;
		}
	}

	/**
	 * <div>check if the plan status is 'PS2' </div>
	 * 
	 * @return int 2:ENABLE 3:DISABLE
	 */
	public int getDisplayTransactionType(String planStatus) {
		if (planStatus.equals("PS2")) {
			return Item.DISABLE;
		}
		return Item.ENABLE;
	}

	/**
	 * <div>check if the transaction type is 'EN' or 'CN' </div>
	 * 
	 * @param transactionType
	 *            transaction type
	 * 
	 * @return int 1:display 0:non_display
	 */
	public int getDisplayReferencePlan(String transactionType) {
		if (transactionType.equals("EN") || transactionType.equals("CN")) {
			return Item.DISPLAY;
		}
		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the plan status is 'PS3' and the access type is '2' </div>
	 * 
	 * @param planStatus
	 *            plan status
	 * @param accessType
	 *            access type
	 * 
	 * @return int 1:display 0:non_display
	 */
	public int getDisplaySuspend(String planStatus, String accessType) {
		if (planStatus.equals("PS3") && accessType.equals("2")) {
			return Item.DISPLAY;
		}
		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the plan status is 'PS6' and the access type is '2' </div>
	 * 
	 * @param planStatus
	 *            plan status
	 * @param accessType
	 *            access type
	 * 
	 * @return int 1:display 0:non_display
	 */
	public int getDisplayUnsuspend(String planStatus, String accessType) {
		if (planStatus.equals("PS6") && accessType.equals("2")) {
			return Item.DISPLAY;
		}
		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the plan status is 'PS3' or 'PS6' and the access type is
	 * '2' </div>
	 * 
	 * @param planStatus
	 *            plan status
	 * @param accessType
	 *            access type
	 * 
	 * @return int 1:display 0:non_display
	 */
	public int getDisplayTerminate(String planStatus, String accessType) {
		if ((planStatus.equals("PS3") || planStatus.equals("PS6"))
				&& accessType.equals("2")) {
			return Item.DISPLAY;
		}
		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the 'SYSTEMBASE' is 'MY' and plan status is 'PS1' and the
	 * access type is '2' </div>
	 * 
	 * @param planStatus
	 *            plan status
	 * @param isDeleted
	 *            delete flag
	 * @param accessType
	 *            access type
	 * 
	 * @return int 2:enable 3:disable 0:non_display
	 */
	public int getDisplayGernerateBIF(String planStatus, String isDeleted,
			String accessType) {
		String setValue = query.executeForObject("SELECT.B_CPM.GET_SYSTEMBASE",
				null, String.class);
		if (setValue.equals("MY")) {
			if (planStatus.equals("PS1") && isDeleted.equals("0")
					&& accessType.equals("2")) {
				return Item.ENABLE;
			}
			return Item.DISABLE;
		}

		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the 'SYSTEMBASE' is 'SG' and plan status is 'PS1' and the
	 * access type is '2' </div>
	 * 
	 * @param planStatus
	 *            plan status
	 * @param isDeleted
	 *            delete flag
	 * @param accessType
	 *            access type
	 * 
	 * @return int 2:enable 3:disable 0:non_display
	 */
	public int getDisplayApprovePlan(String planStatus, String isDeleted,
			String accessType) {
		String setValue = query.executeForObject("SELECT.B_CPM.GET_SYSTEMBASE",
				null, String.class);
		if (setValue.equals("SG")) {
			if (planStatus.equals("PS1") && isDeleted.equals("0")
					&& accessType.equals("2")) {
				return Item.ENABLE;
			}
			return Item.DISABLE;
		}

		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the plan status is 'PS1' or 'PS2' and the access type is
	 * '2' </div>
	 * 
	 * @param planStatus
	 *            plan status
	 * @param accessType
	 *            access type
	 * 
	 * @return int 1:display 0:non_display
	 */
	public int getDisplayEdit(String planStatus, String accessType) {
		if ((planStatus.equals("PS1") || planStatus.equals("PS2"))
				&& accessType.equals("2")) {
			return Item.DISPLAY;
		}
		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the plan status is 'PS1' and the access type is '2' </div>
	 * 
	 * @param planStatus
	 *            plan status
	 * @param accessType
	 *            access type
	 * 
	 * @return int 1:display 0:non_display
	 */
	public int getDisplayDelete(String planStatus, String accessType) {
		if (planStatus.equals("PS1") && accessType.equals("2")) {
			return Item.DISPLAY;
		}
		return Item.NON_DISPLAY;
	}

	/**
	 * <div>check if the customer plan is exist in T_BAC_D '2' </div>
	 * 
	 * @param idCustPlan
	 *            customer plan id
	 * 
	 * @return boolean true:exist false:not exist
	 */
	public Boolean IsBillAccountExisted(String idCustPlan) {
		Boolean result = false;
		Integer count = query
				.executeForObject("SELECT.B_CPM.CHECK_BILLACC_EXISTED",
						idCustPlan, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * <div>get the list of bill account no</div>
	 * 
	 * @param idCustomer
	 *            customer id
	 * @param billCurrency
	 *            bill currency
	 * @param planType
	 *            plan type
	 * @param plan
	 *            plan bean
	 * @param displayItems
	 *            display items
	 * 
	 * @return List<BillAccNo> list of bill account no
	 */
	public List<BillAccNo> processFlowBillingAccountNoInEditMode(
			String idCustomer, String billCurrency, int planType, Plan plan,
			Integer[] displayItems) {
		List<BillAccNo> billAccList = new ArrayList<BillAccNo>();
		if (IsBillAccountExisted(plan.getIdCustPlan())) {
			displayItems[Item.BILLING_ACC_NO] = Item.LABEL;
			displayItems[Item.NEW_ACC] = Item.NON_DISPLAY;
		} else {
			billAccList = this.processFlowBillingAccountNoInNewMode(idCustomer,
					billCurrency, planType, new Plan(), displayItems);
		}
		return billAccList;
	}

	/**
	 * <div>get the list of bill account no</div>
	 * 
	 * @param idCustomer
	 *            customer id
	 * @param billCurrency
	 *            bill currency
	 * @param planType
	 *            plan type
	 * @param plan
	 *            plan bean
	 * @param displayItems
	 *            display items
	 * 
	 * @return List<BillAccNo> the list of bill account no
	 */
	public List<BillAccNo> processFlowBillingAccountNoInNewMode(
			String idCustomer, String billCurrency, int planType, Plan plan,
			Integer[] displayItems) {

		// load Billing No List
		BillingSystemSettings sysSettings = new BillingSystemSettings(query);
		List<BillAccNo> billAccList = new ArrayList<BillAccNo>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("idCust", idCustomer);
		if (planType == Plan.STANDARD) {
			// billing currency is used only in Standard, process flow
			// Non-standard not need
			map.put("billCurrency", billCurrency);
			billAccList = query.executeForObjectList(
					"SELECT.B_CPM.BILL_ACC_NO_STANDARD", map);
		} else if (planType == Plan.NON_STANDARD) {
			billAccList = query.executeForObjectList(
					"SELECT.B_CPM.BILL_ACC_NO_NONSTANDARD", map);
		}

		if (billAccList.size() == 0) {
			displayItems[Item.BILLING_ACC_NO] = Item.DISABLE;
			plan.setNewAcc("NEW");
			plan.setInitialBac(plan.getNewAcc());
			plan.setBillAccNo(null);
			if (planType == Plan.NON_STANDARD) {
				plan.setBillCurrency(sysSettings.getDefCurrency());
			}
			// In case no data in combo box Billing Acc No
			// default value for payment method, billing currency, exp grand
			// total are set in JSP
		} else {

			if (plan.getNewAcc() == null || plan.getNewAcc().equals("0")) {
				// get 1st BillingAccNo
				BillAccNo billAccNo = billAccList.get(0);

				// update PaymentMethod = O.Payment_method, export currency,
				// fixed forxed, bill currency, bill acc no
				plan.setBillAccNo(billAccNo.getIdBillAccount());
				plan.setInitialBac(plan.getBillAccNo());
				plan.setPaymentMethod(billAccNo.getPaymentMethod());
				plan.setExpGrandTotal(billAccNo.getExportCurrency());
				plan.setFixedForex(billAccNo.getFixedForex());
				if (planType == Plan.NON_STANDARD) {
					plan.setBillCurrency(billAccNo.getBillCurrency());
				}
			}

			displayItems[Item.PAYMENT_METHOD] = Item.DISABLE;
			displayItems[Item.EXP_GRAND_TOTAL] = Item.DISABLE;
			displayItems[Item.FIXED_FORED] = Item.DISABLE;
		}
		return billAccList;
	}

	/**
	 * <div>get the date formated by 'dd/MM/yyyy'</div>
	 * 
	 * @return String the date formated by 'dd/MM/yyyy'
	 */
	public String getToday() {
		Calendar car = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String today = format.format(car.getTime());
		return today;
	}

	/**
	 * <div>get the list of plan from M_PLAN_H</div>
	 * 
	 * @return List<Plan> the list of plan from M_PLAN_H
	 */
	public List<Plan> getListReferencePlan() {
		List<Plan> referencePlans = query.executeForObjectList(
				"SELECT.B_CPM.REFERENCE_PLAN", null);
		return referencePlans;
	}

	/**
	 * <div>get the list of bill service group</div>
	 * 
	 * @return List<ServiceGroup> the list of bill service group
	 */
	public List<ServiceGroup> getListBillServiceGrp() {
		return query.executeForObjectList(
				"SELECT.B_CPM.GET_LIST_BILLSERVICEGRP", null);
	}

	/**
	 * <div>get the list of bill service id</div>
	 * 
	 * @param svc_grp
	 *            : value from combo box Bill Service Grp
	 * @return List<PlanService> the list of bill service id
	 */
	public List<PlanService> getListBillServiceId(String svc_grp) {
		return query.executeForObjectList(
				"SELECT.B_CPM.GET_LIST_BILLSERVICEID", svc_grp);
	}

	/**
	 * <div>get the list of bill account no</div>
	 * 
	 * @param billCurrency
	 *            bill currency
	 * 
	 * @return List<BillAccNo> the list of bill account no
	 */
	public List<BillAccNo> processFlowExpGrandTotal(String billCurrency) {
		List<BillAccNo> listExpGrandTotal = new ArrayList<BillAccNo>();
		// get default currency from Database
		String defCurrency = query.executeForObject(
				"SELECT.B_CPM.DEF_CURRENCY", null, String.class);
		if (defCurrency != null && defCurrency.equals(billCurrency)) {
			listExpGrandTotal = query.executeForObjectList(
					"SELECT.B_CPM.EXP_GRAND_TOTAL", defCurrency);
		} else {
			BillAccNo accNo = new BillAccNo();
			accNo.setCurCode(defCurrency);
			listExpGrandTotal.add(accNo);
		}
		return listExpGrandTotal;
	}

	/**
	 * <div>get the list of Supplier</div>
	 * 
	 * @param billCurrency
	 *            bill currency
	 * 
	 * @return List<Supplier> the list of Supplier
	 */
	public List<Supplier> getListSupplier() {
		return query.executeForObjectList("SELECT.B_CPM.CARRIER_LIST", null);
	}

	/**
	 * <div>split option service info</div>
	 * 
	 * @param optionServices
	 *            list of service
	 */
	public void splitOptionService(List<Service> optionServices) {
		for (int i = 0; i < optionServices.size(); i++) {
			Service optionService = optionServices.get(i);
			List<PlanService> details = new ArrayList<PlanService>();
			PlanService detail = new PlanService();
			detail.setIdPlanService(optionService.getIdPlanSvc());
			detail.setServiceType(optionService.getServiceType());
			detail.setServiceItem(optionService.getServiceItem());
			detail.setVendor(optionService.getVendor());
			details.add(detail);
			optionService.setDetails(details);
		}
	}

	/**
	 * <div>combine option service info</div>
	 * 
	 * @param optionServices
	 *            list of service
	 */
	public void combineOptionService(List<Service> optionServices) {
		for (int i = 0; i < optionServices.size(); i++) {
			Service optionService = optionServices.get(i);
			PlanService detail = null;
			if (optionService.getDetails().size() > 0) {
				detail = optionService.getDetails().get(0);
			}
			if (detail != null) {
				optionService.setIdPlanSvc(detail.getIdPlanService());
				optionService.setServiceType(detail.getServiceType());
				optionService.setServiceItem(detail.getServiceItem());
				optionService.setVendor(detail.getVendor());
			}
		}
	}

}
