package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class BillAccNo implements Serializable {
	
	private String billAccNo;

	private String planName;
	
	private String curCode;
	private String idBillAccount;
	private String idCust;
	private String paymentMethod;
	private String billCurrency;
	private String isDisplayExpAmt;
	private String exportCurrency;
	private String fixedForex;
	private String custAdrType;
	private String contactType;
	private String isDeleted;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	
	
	public String getIdCust() {
		return idCust;
	}

	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getBillCurrency() {
		return billCurrency;
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	public String getIsDisplayExpAmt() {
		return isDisplayExpAmt;
	}

	public void setIsDisplayExpAmt(String isDisplayExpAmt) {
		this.isDisplayExpAmt = isDisplayExpAmt;
	}

	public String getExportCurrency() {
		return exportCurrency;
	}

	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}

	public String getFixedForex() {
		return fixedForex;
	}

	public void setFixedForex(String fixedForex) {
		this.fixedForex = fixedForex;
	}

	public String getCustAdrType() {
		return custAdrType;
	}

	public void setCustAdrType(String custAdrType) {
		this.custAdrType = custAdrType;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}



	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

	public String getIdBillAccount() {
		return idBillAccount;
	}

	public void setIdBillAccount(String idBillAccount) {
		this.idBillAccount = idBillAccount;
	}

	public String getCurCode() {
		return curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getBillAccNo() {
		return billAccNo;
	}

	public void setBillAccNo(String billAccNo) {
		this.billAccNo = billAccNo;
	}
	
}
