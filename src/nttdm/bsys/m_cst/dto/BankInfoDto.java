/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : BankInf
 * SERVICE NAME   : M_CSTS02_01
 * FUNCTION       : Save data BLogic
 * FILE NAME      : M_CSTR08BLogicBLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_cst.dto;

public class BankInfoDto {
	private String mode;
	private String bank;
	private String acctNumber;
	private String bankName;
	private String acctName;
	private String branchName;
	private String cardType;
	private String holderName;
	private String acctNumberCredit;
	private String expiredDateMonth;
	private String creditCardNumber;
	private String securityNo;
	private String expiredDateYear;
	private String expiredDate;
	private String expiredDateDay;
	private String uvo;
	private String idCust;
	private String dateCreated;
	private String dateUpated;
	private String idGiroBank;
	
//	Modified by @author, 2011/07/28-Start
	/**
     * Method description
     * @param swiftCode description of swiftCode
     * @param refNum description of refNum
     * @param cbBankCode description of cbBankCode
     * @param cbBranchCode description of cbBranchCode
     * 
     * 
     */

	
	private String swiftCode;
	private String refNum;
	private String cbBankCode;
	private String cbBranchCode;
	public String getCbBankCode() {
		return cbBankCode;
	}
	public void setCbBankCode(String cbBankCode) {
		this.cbBankCode = cbBankCode;
	}
	public String getCbBranchCode() {
		return cbBranchCode;
	}
	public void setCbBranchCode(String cbBranchCode) {
		this.cbBranchCode = cbBranchCode;
	}
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
// Modified by @author, yyyy/mm/dd-End
	
	private Integer idAudit = null;
	
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	
	public String getIdGiroBank() {
		return idGiroBank;
	}
	public void setIdGiroBank(String idGiroBank) {
		this.idGiroBank = idGiroBank;
	}
	public String getIdCust() {
		return idCust;
	}
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}
	public String getUvo() {
		return uvo;
	}
	public void setUvo(String uvo) {
		this.uvo = uvo;
	}

	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAcctNumber() {
		return acctNumber;
	}
	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
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
	public String getAcctNumberCredit() {
		return acctNumberCredit;
	}
	public void setAcctNumberCredit(String acctNumberCredit) {
		this.acctNumberCredit = acctNumberCredit;
	}
	public String getExpiredDateMonth() {
		return expiredDateMonth;
	}
	public void setExpiredDateMonth(String expiredDateMonth) {
		this.expiredDateMonth = expiredDateMonth;
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
	public String getExpiredDateYear() {
		return expiredDateYear;
	}
	public void setExpiredDateYear(String expiredDateYear) {
		this.expiredDateYear = expiredDateYear;
	}	
	public String getExpiredDateDay() {
		return expiredDateDay;
	}
	public void setExpiredDateDay(String expiredDateDay) {
		this.expiredDateDay = expiredDateDay;
	}	
}
