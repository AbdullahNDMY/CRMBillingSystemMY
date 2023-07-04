/**
 * @(#)R_SOA_S01SearchInput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/02
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class R_SOA_S01SearchInput implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7317012704770706767L;

    private BillingSystemUserValueObject uvo;

    private Integer row;

    private Integer startIndex;
    
    private String accessType;
    
    private String cboStatementMonth;
    
    private String tbxStatementYear;
    
    private String tbxStatementNoFrom;
    
    private String tbxStatementNoTo;
    
    private String tbxCustomerName;
    
    private String tbxCustomerId;
    
    private String tbxBillingAccount;
    
    private String cboCustomerType;
    
    private String cboPrintStatement;
    
    private String cboBillingCurrency;
   
    private String cboPaymentCurrency;
   
    private String[] cboPaymentCurrencyArray;
        
	/**
	 * @return the cboPaymentCurrencyArray
	 */
	public String[] getCboPaymentCurrencyArray() {
		return cboPaymentCurrencyArray;
	}

	/**
	 * @param cboPaymentCurrencyArray the cboPaymentCurrencyArray to set
	 */
	public void setCboPaymentCurrencyArray(String[] cboPaymentCurrencyArray) {
		this.cboPaymentCurrencyArray = cboPaymentCurrencyArray;
	}

	/**
	 * @return the cboBillingCurrency
	 */
	public String getCboBillingCurrency() {		
		return cboBillingCurrency;
	}

	/**
	 * @param cboBillingCurrency the cboBillingCurrency to set
	 */
	public void setCboBillingCurrency(String cboBillingCurrency) {
		this.cboBillingCurrency = cboBillingCurrency;
	}
	
	/**
	 * @return the cboPaymentCurrency
	 */
	public String getCboPaymentCurrency() {
		return cboPaymentCurrency;
	}

	/**
	 * @param cboPaymentCurrency the cboPaymentCurrency to set
	 */
	public void setCboPaymentCurrency(String cboPaymentCurrency) {
		this.cboPaymentCurrency = cboPaymentCurrency;
	}
	//

	private String chkStatementTotal;
    
    private String[] selectedStatementNo;
    
    private String clickBtnTypeFlg;
    
    private String hidStatementMonth;
    
    private String hidStatementYear;
    
    private String hidStatementNoFrom;
    
    private String hidStatementNoTo;
    
    private String hidCustomerName;
    
    private String hidCustomerId;
    
    private String hidBillingAccount;
    
    private String hidCustomerType;
    
    private String hidPrintStatement;
    
	private String hidStatementTotal;

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

    /**
     * @return the cboStatementMonth
     */
    public String getCboStatementMonth() {
        return cboStatementMonth;
    }

    /**
     * @param cboStatementMonth the cboStatementMonth to set
     */
    public void setCboStatementMonth(String cboStatementMonth) {
        this.cboStatementMonth = cboStatementMonth;
    }

    /**
     * @return the tbxStatementYear
     */
    public String getTbxStatementYear() {
        return tbxStatementYear;
    }

    /**
     * @param tbxStatementYear the tbxStatementYear to set
     */
    public void setTbxStatementYear(String tbxStatementYear) {
        this.tbxStatementYear = tbxStatementYear;
    }

    /**
     * @return the tbxStatementNoFrom
     */
    public String getTbxStatementNoFrom() {
        return tbxStatementNoFrom;
    }

    /**
     * @param tbxStatementNoFrom the tbxStatementNoFrom to set
     */
    public void setTbxStatementNoFrom(String tbxStatementNoFrom) {
        this.tbxStatementNoFrom = tbxStatementNoFrom;
    }

    /**
     * @return the tbxStatementNoTo
     */
    public String getTbxStatementNoTo() {
        return tbxStatementNoTo;
    }

    /**
     * @param tbxStatementNoTo the tbxStatementNoTo to set
     */
    public void setTbxStatementNoTo(String tbxStatementNoTo) {
        this.tbxStatementNoTo = tbxStatementNoTo;
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
     * @return the tbxCustomerId
     */
    public String getTbxCustomerId() {
        return tbxCustomerId;
    }

    /**
     * @param tbxCustomerId the tbxCustomerId to set
     */
    public void setTbxCustomerId(String tbxCustomerId) {
        this.tbxCustomerId = tbxCustomerId;
    }

    /**
     * @return the tbxBillingAccount
     */
    public String getTbxBillingAccount() {
        return tbxBillingAccount;
    }

    /**
     * @param tbxBillingAccount the tbxBillingAccount to set
     */
    public void setTbxBillingAccount(String tbxBillingAccount) {
        this.tbxBillingAccount = tbxBillingAccount;
    }

    /**
     * @return the cboCustomerType
     */
    public String getCboCustomerType() {
        return cboCustomerType;
    }

    /**
     * @param cboCustomerType the cboCustomerType to set
     */
    public void setCboCustomerType(String cboCustomerType) {
        this.cboCustomerType = cboCustomerType;
    }

    /**
     * @return the cboPrintStatement
     */
    public String getCboPrintStatement() {
        return cboPrintStatement;
    }

    /**
     * @param cboPrintStatement the cboPrintStatement to set
     */
    public void setCboPrintStatement(String cboPrintStatement) {
        this.cboPrintStatement = cboPrintStatement;
    }

    /**
     * @return the chkStatementTotal
     */
    public String getChkStatementTotal() {
        return chkStatementTotal;
    }

    /**
     * @param chkStatementTotal the chkStatementTotal to set
     */
    public void setChkStatementTotal(String chkStatementTotal) {
        this.chkStatementTotal = chkStatementTotal;
    }

    /**
     * @return the selectedStatementNo
     */
    public String[] getSelectedStatementNo() {
        return selectedStatementNo;
    }

    /**
     * @param selectedStatementNo the selectedStatementNo to set
     */
    public void setSelectedStatementNo(String[] selectedStatementNo) {
        this.selectedStatementNo = selectedStatementNo;
    }

    /**
     * @return the clickBtnTypeFlg
     */
    public String getClickBtnTypeFlg() {
        return clickBtnTypeFlg;
    }

    /**
     * @param clickBtnTypeFlg the clickBtnTypeFlg to set
     */
    public void setClickBtnTypeFlg(String clickBtnTypeFlg) {
        this.clickBtnTypeFlg = clickBtnTypeFlg;
    }

    /**
     * @return the hidStatementMonth
     */
    public String getHidStatementMonth() {
        return hidStatementMonth;
    }

    /**
     * @param hidStatementMonth the hidStatementMonth to set
     */
    public void setHidStatementMonth(String hidStatementMonth) {
        this.hidStatementMonth = hidStatementMonth;
    }

    /**
     * @return the hidStatementYear
     */
    public String getHidStatementYear() {
        return hidStatementYear;
    }

    /**
     * @param hidStatementYear the hidStatementYear to set
     */
    public void setHidStatementYear(String hidStatementYear) {
        this.hidStatementYear = hidStatementYear;
    }

    /**
     * @return the hidStatementNoFrom
     */
    public String getHidStatementNoFrom() {
        return hidStatementNoFrom;
    }

    /**
     * @param hidStatementNoFrom the hidStatementNoFrom to set
     */
    public void setHidStatementNoFrom(String hidStatementNoFrom) {
        this.hidStatementNoFrom = hidStatementNoFrom;
    }

    /**
     * @return the hidStatementNoTo
     */
    public String getHidStatementNoTo() {
        return hidStatementNoTo;
    }

    /**
     * @param hidStatementNoTo the hidStatementNoTo to set
     */
    public void setHidStatementNoTo(String hidStatementNoTo) {
        this.hidStatementNoTo = hidStatementNoTo;
    }

    /**
     * @return the hidCustomerName
     */
    public String getHidCustomerName() {
        return hidCustomerName;
    }

    /**
     * @param hidCustomerName the hidCustomerName to set
     */
    public void setHidCustomerName(String hidCustomerName) {
        this.hidCustomerName = hidCustomerName;
    }

    /**
     * @return the hidCustomerId
     */
    public String getHidCustomerId() {
        return hidCustomerId;
    }

    /**
     * @param hidCustomerId the hidCustomerId to set
     */
    public void setHidCustomerId(String hidCustomerId) {
        this.hidCustomerId = hidCustomerId;
    }

    /**
     * @return the hidBillingAccount
     */
    public String getHidBillingAccount() {
        return hidBillingAccount;
    }

    /**
     * @param hidBillingAccount the hidBillingAccount to set
     */
    public void setHidBillingAccount(String hidBillingAccount) {
        this.hidBillingAccount = hidBillingAccount;
    }

    /**
     * @return the hidCustomerType
     */
    public String getHidCustomerType() {
        return hidCustomerType;
    }

    /**
     * @param hidCustomerType the hidCustomerType to set
     */
    public void setHidCustomerType(String hidCustomerType) {
        this.hidCustomerType = hidCustomerType;
    }

    /**
     * @return the hidPrintStatement
     */
    public String getHidPrintStatement() {
        return hidPrintStatement;
    }

    /**
     * @param hidPrintStatement the hidPrintStatement to set
     */
    public void setHidPrintStatement(String hidPrintStatement) {
        this.hidPrintStatement = hidPrintStatement;
    }

    /**
     * @return the hidStatementTotal
     */
    public String getHidStatementTotal() {
        return hidStatementTotal;
    }

    /**
     * @param hidStatementTotal the hidStatementTotal to set
     */
    public void setHidStatementTotal(String hidStatementTotal) {
        this.hidStatementTotal = hidStatementTotal;
    }
}
