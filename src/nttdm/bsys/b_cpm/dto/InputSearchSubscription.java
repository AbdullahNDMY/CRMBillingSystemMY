package nttdm.bsys.b_cpm.dto;

public class InputSearchSubscription {
	private String customerName;
	private String subscriptionId;
	private String accessAccount;
	private String routerNo;
	private String routerType;
	private String modemNo;
	private String adslNo;
	private String circuitId;
	private String carrier;
	private String domainName;
	private String operationAddress;
	private String mailAccount;
	private String memoRemark;
	


	private String searchBy;

	private String row;
	private String totalCount;
	private String startIndex;
	
	public InputSearchSubscription(){
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
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getAccessAccount() {
		return accessAccount;
	}
	public void setAccessAccount(String accessAccount) {
		this.accessAccount = accessAccount;
	}
	public String getRouterNo() {
		return routerNo;
	}
	public void setRouterNo(String routerNo) {
		this.routerNo = routerNo;
	}
	public String getRouterType() {
		return routerType;
	}
	public void setRouterType(String routerType) {
		this.routerType = routerType;
	}
	public String getModemNo() {
		return modemNo;
	}
	public void setModemNo(String modemNo) {
		this.modemNo = modemNo;
	}
	public String getAdslNo() {
		return adslNo;
	}
	public void setAdslNo(String adslNo) {
		this.adslNo = adslNo;
	}
	public String getCircuitId() {
		return circuitId;
	}
	public void setCurcuitId(String circuitId) {
		this.circuitId = circuitId;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getOperationAddress() {
		return operationAddress;
	}
	public void setOperationAddress(String operationAddress) {
		this.operationAddress = operationAddress;
	}
	public String getMailAccount() {
		return mailAccount;
	}
	public void setMailAccount(String mailAccount) {
		this.mailAccount = mailAccount;
	}
	public String getMemoRemark() {
		return memoRemark;
	}
	public void setMemoRemark(String memoRemark) {
		this.memoRemark = memoRemark;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

}
