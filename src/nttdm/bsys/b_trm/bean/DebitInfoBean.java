package nttdm.bsys.b_trm.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class DebitInfoBean extends ValidatorActionFormEx{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String  gdcDateDebitRef;
	private String  gdcDebitReference;
	private String  gdcOriginalAmountCR;
	private String  gdcAmountDue;
	
	//edit & new mode
	private String  chkAppliedTo;
	private String  gdcCurrency;
	private String  tbxPayment;
	private String  tbxPaymentPrev;
	//view mode
	private String  gdcAppliedTo;
	private Double  gdcPayment;
	private Double  gdcOriginalAmountDR;
	private Double  paidAmount;
	private String  gdcCurrencyDebitRef;
	private String  isDeleted;
	private String matchID;
	private String billAccount;
	
	public String getTbxPaymentPrev() {
		return tbxPaymentPrev;
	}
	public void setTbxPaymentPrev(String tbxPaymentPrev) {
		this.tbxPaymentPrev = tbxPaymentPrev;
	}
	
	public String getMatchID() {
		return matchID;
	}
	public void setMatchID(String matchID) {
		this.matchID = matchID;
	}
	public void setGdcDateDebitRef(String gdcDateDebitRef) {
		this.gdcDateDebitRef = gdcDateDebitRef;
	}
	public String getGdcDateDebitRef() {
		return gdcDateDebitRef.substring(0,10);
	}
	public void setGdcOriginalAmountCR(String gdcOriginalAmountCR) {
		this.gdcOriginalAmountCR = gdcOriginalAmountCR;
	}
	public String getGdcOriginalAmountCR() {
		return gdcOriginalAmountCR;
	}
	public void setGdcDebitReference(String gdcDebitReference) {
		this.gdcDebitReference = gdcDebitReference;
	}
	public String getGdcDebitReference() {
		return gdcDebitReference;
	}
	public void setChkAppliedTo(String chkAppliedTo) {
		this.chkAppliedTo = chkAppliedTo;
	}
	public String getChkAppliedTo() {
		return chkAppliedTo;
	}
	public void setGdcCurrency(String gdcCurrency) {
		this.gdcCurrency = gdcCurrency;
	}
	public String getGdcCurrency() {
		return gdcCurrency;
	}
	public void setTbxPayment(String tbxPayment) {
		this.tbxPayment = tbxPayment;
	}
	public String getTbxPayment() {
		return tbxPayment;
	}
	public void setGdcAmountDue(String gdcAmountDue) {
		this.gdcAmountDue = gdcAmountDue;
	}
	public String getGdcAmountDue() {
		return gdcAmountDue;
	}
	public void setGdcAppliedTo(String gdcAppliedTo) {
		this.gdcAppliedTo = gdcAppliedTo;
	}
	public String getGdcAppliedTo() {
		return gdcAppliedTo;
	}
	public void setGdcOriginalAmountDR(Double gdcOriginalAmountDR) {
		this.gdcOriginalAmountDR = gdcOriginalAmountDR;
	}
	public Double getGdcOriginalAmountDR() {
		return gdcOriginalAmountDR;
	}
	public void setGdcPayment(Double gdcPayment) {
		this.gdcPayment = gdcPayment;
	}
	public Double getGdcPayment() {
		return gdcPayment;
	}
	public void setGdcCurrencyDebitRef(String gdcCurrencyDebitRef) {
		this.gdcCurrencyDebitRef = gdcCurrencyDebitRef;
	}
	public String getGdcCurrencyDebitRef() {
		return gdcCurrencyDebitRef;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public String getBillAccount() {
		return billAccount;
	}
	public void setBillAccount(String billAccount) {
		this.billAccount = billAccount;
	}
}
