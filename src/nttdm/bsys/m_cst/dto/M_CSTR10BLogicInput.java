
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS02_01
 * FUNCTION       : Get Input data BLogic
 * FILE NAME      : M_CSTR10BLogicInput.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
/**
 * InputDTO class<br>
 * <br>
 * <ul>
 * <li>
 * <li>InputDTO class
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1 
 */

public class M_CSTR10BLogicInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8523204680257083947L;

	/**
     * 
     */
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
	private String id_cust;
	private String idCust;
	private String uvo;
	private String expiredDate;
	/**
     * Method description
     * @param refNum description of reference Number
     * @param cbBankCode description of cbBankCode
     * @param cbBranchCode description of cbBranchCode
     */
	private String refNum;
	private String cbBankCode;
	private String cbBranchCode;
	private String bankCode;

	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	private String branchCode;
	

	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
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
	public String getId_cust() {
		return id_cust;
	}
	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
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

	
}