/**
 * @(#)M_CSTRBankInfoCheckErrorInput.java
 * 
 * NTTS System
 * 
 * Version 1.00
 * 
 * Created 2012/12/11
 * 
 * Copyright (c) 2012 NTTS Malaysia. All rights reserved.
 */
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class M_CSTRBankInfoCheckErrorInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2591911793550772858L;

    private BillingSystemUserValueObject uvo;
    
    private String mode;
    private String bank;
    private String id_cust;
    private String acctNumber;
    private String bankName;
    private String acctName;
    private String refNum;
    private String branchName;
    private String cardType;
    private String holderName;
    private String acctNumberCredit;
    private String expiredDateMonth;
    private String creditCardNumber;
    private String securityNo;
    private String expiredDateYear;
    private List bankList;
    private String bankFullName;
    private String bankBicCode;
    private String cbBankCode;
    private String cbBranchCode;
    private String swiftCode;
    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }
    /**
     * @param uvo the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }
    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }
    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
    /**
     * @return the bank
     */
    public String getBank() {
        return bank;
    }
    /**
     * @param bank the bank to set
     */
    public void setBank(String bank) {
        this.bank = bank;
    }
    /**
     * @return the acctNumber
     */
    public String getAcctNumber() {
        return acctNumber;
    }
    /**
     * @param acctNumber the acctNumber to set
     */
    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }
    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }
    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    /**
     * @return the acctName
     */
    public String getAcctName() {
        return acctName;
    }
    /**
     * @param acctName the acctName to set
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }
    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }
    /**
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    /**
     * @return the holderName
     */
    public String getHolderName() {
        return holderName;
    }
    /**
     * @param holderName the holderName to set
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    /**
     * @return the acctNumberCredit
     */
    public String getAcctNumberCredit() {
        return acctNumberCredit;
    }
    /**
     * @param acctNumberCredit the acctNumberCredit to set
     */
    public void setAcctNumberCredit(String acctNumberCredit) {
        this.acctNumberCredit = acctNumberCredit;
    }
    /**
     * @return the expiredDateMonth
     */
    public String getExpiredDateMonth() {
        return expiredDateMonth;
    }
    /**
     * @param expiredDateMonth the expiredDateMonth to set
     */
    public void setExpiredDateMonth(String expiredDateMonth) {
        this.expiredDateMonth = expiredDateMonth;
    }
    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    /**
     * @return the securityNo
     */
    public String getSecurityNo() {
        return securityNo;
    }
    /**
     * @param securityNo the securityNo to set
     */
    public void setSecurityNo(String securityNo) {
        this.securityNo = securityNo;
    }
    /**
     * @return the expiredDateYear
     */
    public String getExpiredDateYear() {
        return expiredDateYear;
    }
    /**
     * @param expiredDateYear the expiredDateYear to set
     */
    public void setExpiredDateYear(String expiredDateYear) {
        this.expiredDateYear = expiredDateYear;
    }
    /**
     * @return the id_cust
     */
    public String getId_cust() {
        return id_cust;
    }
    /**
     * @param id_cust the id_cust to set
     */
    public void setId_cust(String id_cust) {
        this.id_cust = id_cust;
    }
    /**
     * @return the bankList
     */
    public List getBankList() {
        return bankList;
    }
    /**
     * @param bankList the bankList to set
     */
    public void setBankList(List bankList) {
        this.bankList = bankList;
    }
    /**
     * @return the refNum
     */
    public String getRefNum() {
        return refNum;
    }
    /**
     * @param refNum the refNum to set
     */
    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }
    /**
     * @return the cbBankCode
     */
    public String getCbBankCode() {
        return cbBankCode;
    }
    /**
     * @param cbBankCode the cbBankCode to set
     */
    public void setCbBankCode(String cbBankCode) {
        this.cbBankCode = cbBankCode;
    }
    /**
     * @return the cbBranchCode
     */
    public String getCbBranchCode() {
        return cbBranchCode;
    }
    /**
     * @param cbBranchCode the cbBranchCode to set
     */
    public void setCbBranchCode(String cbBranchCode) {
        this.cbBranchCode = cbBranchCode;
    }
    /**
     * @return the bankFullName
     */
    public String getBankFullName() {
        return bankFullName;
    }
    /**
     * @param bankFullName the bankFullName to set
     */
    public void setBankFullName(String bankFullName) {
        this.bankFullName = bankFullName;
    }
    
    /**
     * @return the bankBicCode
     */
    public String getBankBicCode() {
        return bankBicCode;
    }
    
    /**
     * @param bankBicCode the bankBicCode to set
     */
    public void setBankBicCode(String bankBicCode) {
        this.bankBicCode = bankBicCode;
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
}
