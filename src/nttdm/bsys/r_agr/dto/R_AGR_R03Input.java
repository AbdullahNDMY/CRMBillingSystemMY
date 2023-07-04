/**
 * @(#)R_AGR_R03Input.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_agr.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_AGR_R03Input class.
 * 
 * @author xycao
 */
public class R_AGR_R03Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9145210015647412918L;

    /**
     * 
     */
    private BillingSystemUserValueObject uvo;

    /**
     * 
     */
    private Integer row;

    /**
     * 
     */
    private Integer startIndex;

    /**
     * billMonth
     */
    private String cboBillMonth;

    /**
     * billYear
     */
    private String tbxBillYear;

    /**
     * customerName
     */
    private String tbxCustomerName;

    /**
     * affiliateCode
     */
    private String tbxAffiliateCod;

    /**
     * paymentMethod
     */
    private String cboPaymentMetho;

    /**
     * currency
     */
    private String cboCurrency;
    
    /**
     * month to
     */
    private String cboBillMonthTo;
    
    /**
     * year to
     */
    private String tbxBillYearTo;

    /**
     * Billing Account
     */
    private String tbxBillingAccout;

    /**
     * Billing Document No
     */
    private String tbxBillDocumentNo;

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
     * @return the cboBillMonth
     */
    public String getCboBillMonth() {
        return cboBillMonth;
    }

    /**
     * @param cboBillMonth the cboBillMonth to set
     */
    public void setCboBillMonth(String cboBillMonth) {
        this.cboBillMonth = cboBillMonth;
    }

    /**
     * @return the tbxBillYear
     */
    public String getTbxBillYear() {
        return tbxBillYear;
    }

    /**
     * @param tbxBillYear the tbxBillYear to set
     */
    public void setTbxBillYear(String tbxBillYear) {
        this.tbxBillYear = tbxBillYear;
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
     * @return the cboBillMonthTo
     */
    public String getCboBillMonthTo() {
        return cboBillMonthTo;
    }

    /**
     * @param cboBillMonthTo the cboBillMonthTo to set
     */
    public void setCboBillMonthTo(String cboBillMonthTo) {
        this.cboBillMonthTo = cboBillMonthTo;
    }

    /**
     * @return the tbxBillYearTo
     */
    public String getTbxBillYearTo() {
        return tbxBillYearTo;
    }

    /**
     * @param tbxBillYearTo the tbxBillYearTo to set
     */
    public void setTbxBillYearTo(String tbxBillYearTo) {
        this.tbxBillYearTo = tbxBillYearTo;
    }

    /**
     * @return the tbxBillDocumentNo
     */
    public String getTbxBillDocumentNo() {
        return tbxBillDocumentNo;
    }

    /**
     * @param tbxBillDocumentNo the tbxBillDocumentNo to set
     */
    public void setTbxBillDocumentNo(String tbxBillDocumentNo) {
        this.tbxBillDocumentNo = tbxBillDocumentNo;
    }

	public String getTbxBillingAccout() {
		return tbxBillingAccout;
	}

	public void setTbxBillingAccout(String tbxBillingAccout) {
		this.tbxBillingAccout = tbxBillingAccout;
	}

}