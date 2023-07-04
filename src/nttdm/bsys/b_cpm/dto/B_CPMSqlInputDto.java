//no need
package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class B_CPMSqlInputDto implements Serializable {
	private String custType = null;
	private String subId = null;
	private String custName = null;
	private String planName = null;
	private String transType = null;
	private String billServiceGroup = null;
	private String serviceDateFrom = null;
	private String serviceDateTo = null;
	private String[] status = null;
	// for B_CPM_S01(Sub. Info tab)
	private String accessAcount;
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
	// FOR B_CPM_S04
	private String idCust;
	private String billCurrency;
	// FOR B_CPM_S02
	private String idPlan;
	private String codeValue;
	private String idCustPlan;
	private String[] idPlanSvcs;
	
	
	public String[] getIdPlanSvcs() {
		return idPlanSvcs;
	}
	public void setIdPlanSvcs(String[] idPlanSvcs) {
		this.idPlanSvcs = idPlanSvcs;
	}
	public String getIdCustPlan() {
		return idCustPlan;
	}
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}
	public String getBillCurrency() {
		return billCurrency;
	}
	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}
	public String getIdCust() {
		return idCust;
	}
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	private String itemType;
	private String[] idPlanGroups;
	
	
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public String[] getIdPlanGroups() {
		return idPlanGroups;
	}
	public void setIdPlanGroups(String[] idPlanGroups) {
		this.idPlanGroups = idPlanGroups;
	}
	public String getAccessAcount() {
		return accessAcount;
	}
	public void setAccessAcount(String accessAcount) {
		this.accessAcount = accessAcount;
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
	public void setCircuitId(String circuitId) {
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
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getBillServiceGroup() {
		return billServiceGroup;
	}
	public void setBillServiceGroup(String billServiceGroup) {
		this.billServiceGroup = billServiceGroup;
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

	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	public void setSearchPlanInfo(String custName, String transType, String custType, String billServiceGroup, String planName, String fromDate, String toDate, String[] status, String subId ){
		if (!custName.equals("")) this.custName = "%" + custName + "%";
		if (!transType.equals("")) this.transType = transType;
		if (!custType.equals("")) this.custType = custType;
		if (!subId.equals("")) this.subId = subId;
		if (!planName.equals("")) this.planName = planName;
		if (!fromDate.equals("")) this.serviceDateFrom = fromDate;
		if (!toDate.equals("")) this.serviceDateTo = toDate;
		if (!billServiceGroup.equals("")) this.billServiceGroup = billServiceGroup;
		this.status = status;
		
		
	}
	public void setSearchSubInfo(String custName, String subId, String accessAcount, String routerNo, String routerType, String modemNo, String adslNo, String circuitId, String carrier, String domainName, String operationAddress, String mailAccount, String memoRemark){
		if (!custName.equals("")) this.custName = "%" + custName + "%";
		if (!subId.equals("")) this.subId = "%" + subId + "%";
		if (!accessAcount.equals("")) this.accessAcount = "%" + accessAcount + "%";
		if (!routerNo.equals("")) this.routerNo = "%" + routerNo + "%";
		if (!routerType.equals("")) this.routerType = "%" + routerType + "%";
		if (!modemNo.equals("")) this.modemNo = "%" + modemNo + "%";
		if (!adslNo.equals("")) this.adslNo = "%" + adslNo + "%";
		if (!circuitId.equals("")) this.circuitId = "%" + circuitId + "%";
		if (!carrier.equals("")) this.carrier = carrier ;
		if (!domainName.equals("")) this.domainName = "%" + domainName + "%";
		if (!operationAddress.equals("")) this.operationAddress = "%" + operationAddress + "%";
		if (!mailAccount.equals("")) this.mailAccount = "%" + mailAccount + "%";
		if (!memoRemark.equals("")) this.memoRemark = "%" + memoRemark + "%";
		
		
	}	
	
	
}
