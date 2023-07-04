//no need
package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.b_cpm.common.B_CPM_S02UtilP1;

public class B_CPMSqlDto implements Serializable {
	B_CPM_S02UtilP1 util = new B_CPM_S02UtilP1();
	private String index;
	private String subId;
	private String custName;
	private String planName;
	private String desc;
	private String transType;
	private String billServiceGroup;
	private String billCur;
	private String serviceDateFrom;
	private String serviceDateTo;
	private String status;
	private String planId;
	private List<Service> subList; 
	// B_CPM_S01(Sub. Info tab)
	private String accessAccount;
	private String routerNo;
	private String routerType;
	private String modemNo;
	private String adslNo;
	private String domainName;
	private String[] mailId;
	private String[] rackId;
	private String idLogin;
	private String idCustPlan;
	private String billInstruction;
	private String[] gdcCheckbox;
	private String exportCurrency;
	private String fixedFox;
	private String statusName;
	private String idCustomer;

	
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getStatusName() {
		return util.getStatus(this.status);
	}
	public void setStatusName(String statusName) {
		this.statusName = util.getStatus(this.status);
	}
	public String getFixedFox() {
		return fixedFox;
	}
	public void setFixedFox(String fixedFox) {
		this.fixedFox = fixedFox;
	}
	public String getExportCurrency() {
		return exportCurrency;
	}
	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}
	public String[] getGdcCheckbox() {
		return gdcCheckbox;
	}
	public void setGdcCheckbox(String[] gdcCheckbox) {
		this.gdcCheckbox = gdcCheckbox;
	}
	public String getBillInstruction() {
		return billInstruction;
	}
	public void setBillInstruction(String billInstruction) {
		this.billInstruction = billInstruction;
	}
	public String[] getMailId() {
		return mailId;
	}
	public void setMailId(String[] mailId) {
		this.mailId = mailId;
	}
	public String[] getRackId() {
		return rackId;
	}
	public void setRackId(String[] rackId) {
		this.rackId = rackId;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getIdCustPlan() {
		return idCustPlan;
	}
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}


	public List<Service> getSubList() {
		return subList;
	}
	public void setSubList(List<Service> subList) {
		this.subList = subList;
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
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getBillCur() {
		return billCur;
	}
	public void setBillCur(String billCur) {
		this.billCur = billCur;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
