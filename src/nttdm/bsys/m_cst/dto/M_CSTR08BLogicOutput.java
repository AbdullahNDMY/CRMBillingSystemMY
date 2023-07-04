
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS02_01
 * FUNCTION       : Set data BLogic
 * FILE NAME      : M_CSTR08BLogicOutput.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import java.util.List;
/**
 * OutputDTO class<br>
 * <br>
 * <ul>
 * <li>
 * <li>OutputDTO class
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1 
 */

public class M_CSTR08BLogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1784042245034327894L;

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
	private String expiredDate;
	private String idCust;
	private String uvo;
	private List bankList;
	private String refNum;
	private String cbBankCode;
	private String cbBranchCode;
	private String bankFullName;
	private String bankCode;
	private String branchCode;
	private String swiftCode;
	private String acctNumberMsg;
	private String creditCardNumberMsg;
	private String popupClickYesFlg;
	
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
	public String getBankFullName() {
		return bankFullName;
	}
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
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
	public List getBankList() {
		return bankList;
	}
	public void setBankList(List bankList) {
		this.bankList = bankList;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
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
	
	/**
     * @return the swiftCode
     */
    public String getSwiftCode() {
        return swiftCode;
    }
    /**
     * @param swiftCode the swiftCode to set
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
    /**
     * @return the acctNumberMsg
     */
    public String getAcctNumberMsg() {
        return acctNumberMsg;
    }
    /**
     * @param acctNumberMsg the acctNumberMsg to set
     */
    public void setAcctNumberMsg(String acctNumberMsg) {
        this.acctNumberMsg = acctNumberMsg;
    }
    /**
     * @return the creditCardNumberMsg
     */
    public String getCreditCardNumberMsg() {
        return creditCardNumberMsg;
    }
    /**
     * @param creditCardNumberMsg the creditCardNumberMsg to set
     */
    public void setCreditCardNumberMsg(String creditCardNumberMsg) {
        this.creditCardNumberMsg = creditCardNumberMsg;
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
    public void setOutput(M_CSTR08BLogicInput param){

		
		this.setAcctName(param.getAcctName());
		this.setAcctNumber(param.getAcctNumber());
		this.setAcctNumberCredit(param.getAcctNumberCredit());
		this.setBank(param.getBank());
		this.setBankName(param.getBankName());
		this.setBranchName(param.getBranchName());
		this.setCardType(param.getCardType());
		this.setCreditCardNumber(param.getCreditCardNumber());
		this.setExpiredDateMonth(param.getExpiredDateMonth());
		this.setExpiredDateYear(param.getExpiredDateYear());
		this.setHolderName(param.getHolderName());
		this.setIdCust(param.getIdCust());
		this.setId_cust(param.getId_cust());
		this.setMode(param.getMode());
		this.setUvo(param.getUvo());
		this.setSecurityNo(param.getSecurityNo());	
		this.setExpiredDate(param.getExpiredDate());
		this.setRefNum(param.getRefNum());
		this.setCbBankCode(param.getCbBankCode());
		this.setCbBranchCode(param.getCbBranchCode());
		this.setSwiftCode(param.getSwiftCode());
//		this.setAcctNumberMsg(param.getAcctNumberMsg());
//		this.setCreditCardNumberMsg(param.getCreditCardNumberMsg());
//		this.setPopupClickYesFlg(param.getPopupClickYesFlg());
	}
	
	

}