
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS02_01
 * FUNCTION       : Set output data BLogic
 * FILE NAME      : M_CSTR10BLogicOutput.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class M_CSTR10BLogicOutput implements Serializable {

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
	private List bankList = new ArrayList();
	private boolean hasBankInfo;
	/**
     * Method description
     * @param bankCode description of bankCode
     * @param branchCode description of branchCode
     * @param swiftCode description of swiftCode
     * @param bankFullName description of bankFullName
     * @param refNum description of refNum
     * @param cbBankCode description of cbBankCode
     * @param CbBranchCode description of CbBranchCode
     */
	private String bankCode;
	private String branchCode;
	private String swiftCode;
	private String bankFullName;
	private String bankBicCode;
	

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
	public boolean isHasBankInfo() {
		return hasBankInfo;
	}
	public void setHasBankInfo(boolean hasBankInfo) {
		this.hasBankInfo = hasBankInfo;
	}
	public String getBankFullName() {
		return bankFullName;
	}
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	
	public String getBankBicCode() {
        return bankBicCode;
    }
    public void setBankBicCode(String bankBicCode) {
        this.bankBicCode = bankBicCode;
    }
	
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
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
	public List getBankList() {
		return bankList;
	}
	public void setBankList(List bankList) {
		this.bankList = bankList;
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
	public void setOutput(M_CSTR10BLogicInput param){

		
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
		
	}
	public void setBlankOutput(M_CSTR10BLogicInput param){
		this.setAcctName("");
		this.setAcctNumber("");
		this.setAcctNumberCredit("");
		this.setBank("0");
		this.setBankName("");
		this.setBranchName("");
		this.setCardType("");
		this.setCreditCardNumber("");
		this.setExpiredDateMonth("");
		this.setExpiredDateYear("");
		this.setHolderName("");
		this.setIdCust(param.getIdCust());
		this.setId_cust(param.getId_cust());
		this.setMode(param.getMode());
		this.setUvo(param.getUvo());
		this.setSecurityNo("");	
		this.setExpiredDate("");
		this.setSwiftCode("");
	}
	
	public void setOutput(BankInfoDto param){
		if (param.getAcctName()!= null) this.setAcctName(param.getAcctName());
		if (param.getAcctNumber()!= null) this.setAcctNumber(param.getAcctNumber());
		if (param.getAcctNumberCredit()!= null) this.setAcctNumberCredit(param.getAcctNumberCredit());
		if (param.getBank()!= null) this.setBank(param.getBank());
		if (param.getBankName()!= null) this.setBankName(param.getBankName());
		if (param.getBranchName()!= null) this.setBranchName(param.getBranchName());
		if (param.getCardType()!= null) this.setCardType(param.getCardType());
		if (param.getCreditCardNumber()!= null) this.setCreditCardNumber(param.getCreditCardNumber());
		if (param.getHolderName()!= null) this.setHolderName(param.getHolderName());
		if (param.getIdCust()!= null) this.setIdCust(param.getIdCust());
		if (param.getIdCust()!= null) this.setId_cust(param.getIdCust());
		if (param.getMode()!= null) this.setMode(param.getMode());
		if (param.getUvo()!= null) this.setUvo(param.getUvo());
		if (param.getSecurityNo()!= null) this.setSecurityNo(param.getSecurityNo());
		if (param.getSwiftCode()!= null) this.setSwiftCode(param.getSwiftCode());
		if (param.getRefNum()!=null) this.setRefNum(param.getRefNum());
		if (param.getCbBankCode()!=null) this.setCbBankCode(param.getCbBankCode());
		if (param.getCbBranchCode()!=null) this.setCbBranchCode(param.getCbBranchCode());
		// split Month and Year of Expire date
		if (param.getExpiredDate()!= null) {
			this.setExpiredDate(param.getExpiredDate());
			String[] split = param.getExpiredDate().split("/");
			if(split[0].startsWith("0")){
				this.setExpiredDateMonth(split[0].replace("0", ""));
				}
			this.setExpiredDateYear(split[1]);
		}
	}
	
	
}