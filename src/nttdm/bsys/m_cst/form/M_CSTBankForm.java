/*
 * @(#)M_CSTBankForm.java
 *
 *
 */
package nttdm.bsys.m_cst.form;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * ActionForm class.
 * 
 * @author ss054
 */
public class M_CSTBankForm extends ValidatorActionFormEx {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -881130320205985002L;

	/**
	 * salutation
	 */
	private String salutation;

	/**
	 * salutation を取得する
	 * 
	 * @return salutation
	 */
	
	private String bank;
	private String accNumber;
	private String bankName;
	private String accName;
	private String branchName;
	private String cardType;
	private String holderName;
	private String acctNumber;
	private String expiredDate;
	private String creditCardNumber;
	private String securityNo;
	private String classViewMode;
	private String classNewMode;
	
	
	public String getClassViewMode() {
		return classViewMode;
	}

	public void setClassViewMode(String classViewMode) {
		this.classViewMode = classViewMode;
	}

	public String getClassNewMode() {
		return classNewMode;
	}

	public void setClassNewMode(String classNewMode) {
		this.classNewMode = classNewMode;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getSecurityNo() {
		return securityNo;
	}

	public void setSecurityNo(String securityNo) {
		this.securityNo = securityNo;
	}

	public String getSalutation() {
		return salutation;
	}

	/**
	 * salutation を設定する
	 * 
	 * @param salutation
	 *            salutation
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

}