
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS02_01
 * FUNCTION       : Get data BLogic
 * FILE NAME      : M_CSTR08BLogicInput.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

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

public class M_CSTR08BLogicInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5142796618844531073L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvoObject;

	/**
	 * uvoObject ã‚’å�–å¾—ã�™ã‚‹
	 * 
	 * @return uvoObject
	 */
	public BillingSystemUserValueObject getUvoObject() {
		return uvoObject;
	}

	/**
	 * uvoObject ã‚’è¨­å®šã�™ã‚‹
	 * 
	 * @param uvoObject
	 */
	public void setUvoObject(BillingSystemUserValueObject uvoObject) {
		this.uvoObject = uvoObject;
	}
	
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
	private String idCust;
	private String uvo;
	private String expiredDate;
	private String id_cust;
	private String swiftCode;
	private String refNum;
	private String expiredDateDay;
	private String bankFullName;
    private String popupClickYesFlg;
	
	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	
	private  String bankCode;
	private String branchCode;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

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

	private Integer idAudit = null;
	

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getId_cust() {
		return id_cust;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
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
		if (this.expiredDateMonth != null && !this.expiredDateMonth.equals("")  && this.expiredDateYear != null && !this.expiredDateYear.equals(""))
			return this.expiredDateDay + "/" + this.expiredDateMonth + "/" + this.expiredDateYear ;
		else
			return null;
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

    /**
     * @return the popupClickYesFlg
     */
    public String getPopupClickYesFlg() {
        return popupClickYesFlg;
    }

    /**
     * @param popupClickYesFlg the popupClickYesFlg to set
     */
    public void setPopupClickYesFlg(String popupClickYesFlg) {
        this.popupClickYesFlg = popupClickYesFlg;
    }

    public BankInfoDto getBankInfo(){
		BankInfoDto bankInfo = new BankInfoDto();
		bankInfo.setAcctName(this.getAcctName());
		bankInfo.setAcctNumber(acctNumber);
		bankInfo.setAcctNumberCredit(acctNumberCredit);
		bankInfo.setBank(bank);
		bankInfo.setBankName(bankName);
		bankInfo.setBranchName(branchName);
		bankInfo.setCardType(cardType);
		bankInfo.setCreditCardNumber(creditCardNumber);
		bankInfo.setExpiredDateMonth(expiredDateMonth);
		bankInfo.setExpiredDateYear(expiredDateYear);
		bankInfo.setExpiredDateDay(expiredDateDay);
		bankInfo.setExpiredDate(getExpiredDate());
		bankInfo.setHolderName(holderName);
		bankInfo.setIdCust(idCust);
		bankInfo.setMode(mode);
		bankInfo.setUvo(uvoObject.getId_user());
		bankInfo.setSecurityNo(securityNo);	
		bankInfo.setIdCust(id_cust);
		bankInfo.setSwiftCode(swiftCode);
		bankInfo.setRefNum(this.getRefNum());
		bankInfo.setCbBankCode(cbBankCode);
		bankInfo.setCbBranchCode(cbBranchCode);
		
		return bankInfo;
	}
}