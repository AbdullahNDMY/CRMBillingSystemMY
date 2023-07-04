/**
 * @(#)R_ACR_R02Input.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_acr.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_ACR_R02Input class.
 * 
 * @author xycao
 */
public class R_ACR_R02Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9145210015647412918L;

    /**
     * 
     */
    private BillingSystemUserValueObject uvo;

    /**
     * access Type
     */
    private String accessType;
    
    /**
     * 
     */
    private Integer row;

    /**
     * 
     */
    private Integer startIndex;

    /**
     * cboUsageMonth
     */
    private String cboUsageMonth;

    /**
     * tbxUsageYear
     */
    private String tbxUsageYear;

    /**
     * customerName
     */
    private String tbxCustomerName;

    /**
     * tbxServiceName
     */
    private String tbxServiceName;

    /**
     * currency
     */
    private String cboCurrency;

    /**
     * Service Start From
     */
    private String tbxServiceStartFrom;
    
    /**
     * Service Start To
     */
    private String tbxServiceStartTo;
    
    /**
     * Service Status
     */
    private String[] tbxServiceStatus;
    
    /**
     * Sub ID
     */
    private String tbxSubID;
    
    /**
     * ImportedAmount
     */
    private String tbxImportedAmount;
    
    /**
     * tbx Unbill Amount
     */
    private String tbxUnbillAmount;
    
    public String getTbxUnbillAmount() {
		return tbxUnbillAmount;
	}

	public void setTbxUnbillAmount(String tbxUnbillAmount) {
		this.tbxUnbillAmount = tbxUnbillAmount;
	}

	/**
     * tbx Customer Id
     */
    private String tbxCustomerId;
    
    public String getTbxCustomerId() {
		return tbxCustomerId;
	}

	public void setTbxCustomerId(String tbxCustomerId) {
		this.tbxCustomerId = tbxCustomerId;
	}

	/**
     * @return the tbxServiceStartFrom
     */
    public String getTbxServiceStartFrom() {
        return tbxServiceStartFrom;
    }

    /**
     * @param tbxServiceStartFrom the tbxServiceStartFrom to set
     */
    public void setTbxServiceStartFrom(String tbxServiceStartFrom) {
        this.tbxServiceStartFrom = tbxServiceStartFrom;
    }

    /**
     * @return the tbxServiceStartTo
     */
    public String getTbxServiceStartTo() {
        return tbxServiceStartTo;
    }

    /**
     * @param tbxServiceStartTo the tbxServiceStartTo to set
     */
    public void setTbxServiceStartTo(String tbxServiceStartTo) {
        this.tbxServiceStartTo = tbxServiceStartTo;
    }

    /**
     * @return the tbxServiceStatus
     */
    public String[] getTbxServiceStatus() {
        return tbxServiceStatus;
    }

    /**
     * @param tbxServiceStatus the tbxServiceStatus to set
     */
    public void setTbxServiceStatus(String[] tbxServiceStatus) {
        this.tbxServiceStatus = tbxServiceStatus;
    }

    /**
     * @return the tbxSubID
     */
    public String getTbxSubID() {
        return tbxSubID;
    }

    /**
     * @param tbxSubID the tbxSubID to set
     */
    public void setTbxSubID(String tbxSubID) {
        this.tbxSubID = tbxSubID;
    }

    /**
     * @return the tbxImportedAmount
     */
    public String getTbxImportedAmount() {
        return tbxImportedAmount;
    }

    /**
     * @param tbxImportedAmount the tbxImportedAmount to set
     */
    public void setTbxImportedAmount(String tbxImportedAmount) {
        this.tbxImportedAmount = tbxImportedAmount;
    }

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
     * @return the cboUsageMonth
     */
    public String getCboUsageMonth() {
        return cboUsageMonth;
    }

    /**
     * @param cboUsageMonth the cboUsageMonth to set
     */
    public void setCboUsageMonth(String cboUsageMonth) {
        this.cboUsageMonth = cboUsageMonth;
    }

    /**
     * @return the tbxUsageYear
     */
    public String getTbxUsageYear() {
        return tbxUsageYear;
    }

    /**
     * @param tbxUsageYear the tbxUsageYear to set
     */
    public void setTbxUsageYear(String tbxUsageYear) {
        this.tbxUsageYear = tbxUsageYear;
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
     * @return the tbxServiceName
     */
    public String getTbxServiceName() {
        return tbxServiceName;
    }

    /**
     * @param tbxServiceName the tbxServiceName to set
     */
    public void setTbxServiceName(String tbxServiceName) {
        this.tbxServiceName = tbxServiceName;
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
     * @return the accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * @param accessType the accessType to set
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}