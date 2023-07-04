package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import nttdm.bsys.common.util.dto.AutoScaleList;

public class Plan implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int STANDARD = 1;
	public static final int NON_STANDARD = 2;
	private Customer customer = new Customer();
	// M_Plan_H
	private String planId;
	private String planName;
	private String planDesc;
	private String planStatus;
	private String customerType;
	private String billSrvGrp;
	private String billSrvGrpName;
	private String billCurrency;
	private String billServiceId;

	// T_CUST_PLAN
	private String transactionType;
	private String subcriptionId;
	private String billAccNo;
	private String newAcc;
	private String applicationDate;	
	private String subcriptionInfo;
	private String serviceStartFrom;
	private String serviceStartTo;
	private String autoRenewal;
	private String billingInstruction;
	private String proRateBaseOn;
	private String paymentMethod;
	private String exportSingPost;
	private String expGrandTotal;
	private String fixedForex;
	private String referencePlan;
	private String idCustPlan;
	private String billDesc;
	private String isDisplayDesc;
	private String quantity;
	private String unitPrice;
	private String totalAmount;
	private String idLogin;
	private String idCust;
	private String initialBac;
	private String isDeleted;
	private String planStatusName;
	private String approveType;
	private String isDisplayExpAmt;
	private String dateCreated;
	private String dateUpdated;
	private String referencePlanName;
	private List<Service> subPlans;
	private List<Service> optionServices;
	private Integer idAudit = null;
	
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	//Subscription
	private int noOfSubscription;
	
	/**
	 * @return the noOfSubscription
	 */
	public int getNoOfSubscription() {
		return noOfSubscription;
	}
	/**
	 * @param noOfSubscription the noOfSubscription to set
	 */
	public void setNoOfSubscription(int noOfSubscription) {
		this.noOfSubscription = noOfSubscription;
	}
	// for B_CPM_S05
	private String[] idCustPlanList = null;
	private String planType;
	
	
	public Plan(){
		subPlans = new AutoScaleList<Service>(new Service());
		optionServices = new AutoScaleList<Service>(new Service());
	}
	public String getBillSrvGrpName() {
		return billSrvGrpName;
	}
	public void setBillSrvGrpName(String billSrvGrpName) {
		this.billSrvGrpName = billSrvGrpName;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String[] getIdCustPlanList() {
		return idCustPlanList;
	}
	public void setIdCustPlanList(String[] idCustPlanList) {
		this.idCustPlanList = idCustPlanList;
	}
	public String getReferencePlanName() {
		return referencePlanName;
	}
	public void setReferencePlanName(String referencePlanName) {
		this.referencePlanName = referencePlanName;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getApproveType() {
		return approveType;
	}
	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}
	public String getIsDisplayExpAmt() {
		return isDisplayExpAmt;
	}
	public void setIsDisplayExpAmt(String isDisplayExpAmt) {
		this.isDisplayExpAmt = isDisplayExpAmt;
	}
	public String getPlanStatusName() {
		return planStatusName;
	}
	public void setPlanStatusName(String planStatusName) {
		this.planStatusName = planStatusName;
	}

	
	

	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<Service> getSubPlans() {
		return subPlans;
	}


	public void setSubPlans(List<Service> subPlans) {
		this.subPlans = subPlans;
	}
	public List<Service> getOptionServices() {
		return optionServices;
	}
	public void setOptionServices(List<Service> optionServices) {
		this.optionServices = optionServices;
	}
	public String getInitialBac() {
		return initialBac;
	}
	public void setInitialBac(String initialBac) {
		this.initialBac = initialBac;
	}
	public String getIdCust() {
		return idCust;
	}
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getBillDesc() {
		return billDesc;
	}
	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}
	public String getIsDisplayDesc() {
		return this.isDisplayDesc;
	}
	public void setIsDisplayDesc(String isDisplayDesc) {
		this.isDisplayDesc = isDisplayDesc;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getIdCustPlan() {
		return idCustPlan;
	}
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getSubcriptionId() {
		return subcriptionId;
	}
	public void setSubcriptionId(String subcriptionId) {
		this.subcriptionId = subcriptionId;
	}
	public String getBillAccNo() {
		return billAccNo;
	}
	public void setBillAccNo(String billAccNo) {
		this.billAccNo = billAccNo;
	}
	public String getNewAcc() {
		return newAcc;
	}
	public void setNewAcc(String newAcc) {
		this.newAcc = newAcc;
	}
	public String getApplicationDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getSubcriptionInfo() {
		return subcriptionInfo;
	}
	public void setSubcriptionInfo(String subcriptionInfo) {
		this.subcriptionInfo = subcriptionInfo;
	}
	public String getServiceStartFrom() {
		return serviceStartFrom;
	}
	public void setServiceStartFrom(String serviceStartFrom) {
		this.serviceStartFrom = serviceStartFrom;
	}
	public String getServiceStartTo() {
		return serviceStartTo;
	}
	public void setServiceStartTo(String serviceStartTo) {
		this.serviceStartTo = serviceStartTo;
	}
	public String getAutoRenewal() {
		return this.autoRenewal;
	}
	public void setAutoRenewal(String autoRenewal) {
		this.autoRenewal = autoRenewal;
	}
	public String getBillingInstruction() {
		return billingInstruction;
	}
	public void setBillingInstruction(String billingInstruction) {
		this.billingInstruction = billingInstruction;
	}
	public String getProRateBaseOn() {
		return proRateBaseOn;
	}
	public void setProRateBaseOn(String proRateBaseOn) {
		this.proRateBaseOn = proRateBaseOn;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getExportSingPost() {
		return exportSingPost;
	}
	public void setExportSingPost(String exportSingPost) {
		this.exportSingPost = exportSingPost;
	}
	public String getExpGrandTotal() {
		return expGrandTotal;
	}
	public void setExpGrandTotal(String expGrandTotal) {
		this.expGrandTotal = expGrandTotal;
	}
	public String getFixedForex() {
		return fixedForex;
	}
	public void setFixedForex(String fixedForex) {
		try {
			//convert .123 to 0.123
			this.fixedForex = String.valueOf(Double.parseDouble(fixedForex));
		} catch(Exception e) {
			this.fixedForex = fixedForex;
		}
	}
	public String getReferencePlan() {
		return referencePlan;
	}
	public void setReferencePlan(String referencePlan) {
		this.referencePlan = referencePlan;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getBillSrvGrp() {
		return billSrvGrp;
	}
	public void setBillSrvGrp(String billSrvGrp) {
		this.billSrvGrp = billSrvGrp;
	}
	public String getBillCurrency() {
		return billCurrency;
	}
	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}
	public String getBillServiceId() {
		return billServiceId;
	}
	public void setBillServiceId(String billServiceId) {
		this.billServiceId = billServiceId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
