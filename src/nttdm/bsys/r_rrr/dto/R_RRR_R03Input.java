/**
 * @(#)R_RRR_R03Input.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_RRR_R03Input class.
 * 
 * @author xycao
 */
public class R_RRR_R03Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9145210015647412918L;

    /**
     * User Value Object
     */
    private BillingSystemUserValueObject uvo;

    /**
     * Search records count
     */
    private Integer row;

    /**
     * Search records start index
     */
    private Integer startIndex;

    /**
     * Paid Date From
     */
    private String tbxPaidDateFrom;
    
    /**
     * Paid Date To
     */
    private String tbxPaidDateTo;
    
    /**
     * customerName
     */
    private String tbxCustomerName;

    /**
     * affiliateCode
     */
    private String tbxAffiliateCod;
    
    /**
     * tbxBillDocument
     */
    private String tbxBillDocument;
    
    /**
     * tbxChequeNo
     */
    private String tbxChequeNo;
    
    /**
     * tbxReceiptNo
     */
    private String tbxReceiptNo;

    /**
     * paymentMethod
     */
    private String cboPaymentMetho;

    /**
     * currency
     */
    private String cboCurrency;

    /**
     * bankInName
     */
    private String tbxBankInName;

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
     * @return the row
     */
    public Integer getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * @return the startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return the tbxPaidDateFrom
     */
    public String getTbxPaidDateFrom() {
        return tbxPaidDateFrom;
    }

    /**
     * @param tbxPaidDateFrom the tbxPaidDateFrom to set
     */
    public void setTbxPaidDateFrom(String tbxPaidDateFrom) {
        this.tbxPaidDateFrom = tbxPaidDateFrom;
    }

    /**
     * @return the tbxPaidDateTo
     */
    public String getTbxPaidDateTo() {
        return tbxPaidDateTo;
    }

    /**
     * @param tbxPaidDateTo the tbxPaidDateTo to set
     */
    public void setTbxPaidDateTo(String tbxPaidDateTo) {
        this.tbxPaidDateTo = tbxPaidDateTo;
    }
   
    /**
     * @return the tbxCustomerName
     */
    public String getTbxCustomerName() {
        return tbxCustomerName;
    }

    /**
     * @param tbxCustomerName the tbxCustomerName to set
     */
    public void setTbxCustomerName(String tbxCustomerName) {
        this.tbxCustomerName = tbxCustomerName;
    }

    /**
     * @return the tbxAffiliateCod
     */
    public String getTbxAffiliateCod() {
        return tbxAffiliateCod;
    }

    /**
     * @param tbxAffiliateCod the tbxAffiliateCod to set
     */
    public void setTbxAffiliateCod(String tbxAffiliateCod) {
        this.tbxAffiliateCod = tbxAffiliateCod;
    }

    /**
     * @return the cboPaymentMetho
     */
    public String getCboPaymentMetho() {
        return cboPaymentMetho;
    }

    /**
     * @param cboPaymentMetho the cboPaymentMetho to set
     */
    public void setCboPaymentMetho(String cboPaymentMetho) {
        this.cboPaymentMetho = cboPaymentMetho;
    }

    /**
     * @return the cboCurrency
     */
    public String getCboCurrency() {
        return cboCurrency;
    }

    /**
     * @param cboCurrency the cboCurrency to set
     */
    public void setCboCurrency(String cboCurrency) {
        this.cboCurrency = cboCurrency;
    }

    /**
     * @return the tbxBankInName
     */
    public String getTbxBankInName() {
        return tbxBankInName;
    }

    /**
     * @param tbxBankInName the tbxBankInName to set
     */
    public void setTbxBankInName(String tbxBankInName) {
        this.tbxBankInName = tbxBankInName;
    }

    /**
     * @return the tbxBillDocument
     */
    public String getTbxBillDocument() {
        return tbxBillDocument;
    }

    /**
     * @param tbxBillDocument the tbxBillDocument to set
     */
    public void setTbxBillDocument(String tbxBillDocument) {
        this.tbxBillDocument = tbxBillDocument;
    }

    /**
     * @return the tbxChequeNo
     */
    public String getTbxChequeNo() {
        return tbxChequeNo;
    }

    /**
     * @param tbxChequeNo the tbxChequeNo to set
     */
    public void setTbxChequeNo(String tbxChequeNo) {
        this.tbxChequeNo = tbxChequeNo;
    }

    /**
     * @return the tbxReceiptNo
     */
    public String getTbxReceiptNo() {
        return tbxReceiptNo;
    }

    /**
     * @param tbxReceiptNo the tbxReceiptNo to set
     */
    public void setTbxReceiptNo(String tbxReceiptNo) {
        this.tbxReceiptNo = tbxReceiptNo;
    }
}