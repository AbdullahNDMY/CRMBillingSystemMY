package nttdm.bsys.b_cpm.dto;

public class InputSearchPlan {

	private String customerName;
	private String customerType;
	private String planName;
	private String[] planStatus;
	private String transactionType;
	private String billServiceGrp;
	private String serviceDateFrom;
	private String serviceDateTo;
	private String subscriptionId;
	private String searchBy;

	private String row;
	private String totalCount;
	private String startIndex;
	
	/*#263 [T11] Add customer type at inquiry screen and export result CT 13062017*/
	private String billingStatus;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 13062017*/
	
	public InputSearchPlan(){
		planStatus = new String[0];
		startIndex = "0";
		totalCount = "";
		row = "0";
	}
	
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String[] getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String[] planStatus) {
		this.planStatus = planStatus;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getBillServiceGrp() {
		return billServiceGrp;
	}
	public void setBillServiceGrp(String billServiceGrp) {
		this.billServiceGrp = billServiceGrp;
	}
	public String getServiceDateFrom() {
		return serviceDateFrom;
	}
	public void setServiceDateFrom(String serviceDateFrom) {
		this.serviceDateFrom = serviceDateFrom;
	}
	public String getServiceDateTo() {
		return serviceDateTo;
	}
	public void setServiceDateTo(String serviceDateTo) {
		this.serviceDateTo = serviceDateTo;
	}
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	/*#263 [T11] Add customer type at inquiry screen and export result CT 13062017*/
	public String getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}
	/*#263 [T11] Add customer type at inquiry screen and export result CT 13062017*/
}
