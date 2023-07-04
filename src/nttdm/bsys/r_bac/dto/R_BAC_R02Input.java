/**
 * @(#)R_BAC_R02Input.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_BAC_R02Input class.
 * 
 * @author NTTD DATA
 */
public class R_BAC_R02Input {
    
	/**
     * BillingSystemUserValueObject
     */
    private BillingSystemUserValueObject uvo;
	/**
	 * customer ID
	 */
	private String custId = "";
	/**
	 * access Type
	 */
	private String accessType;

	/**
	 * page row number
	 */
	private Integer row;

	/**
	 * startIndex
	 */
	private Integer startIndex;

	/**
	 * payment method
	 */
	private String paymentMethod = "";

	/**
	 * customer name
	 */
	private String custName = "";

	/**
	 * billing currency
	 */
	private String billCurrency = "";

	/**
	 * billing account NO.
	 */
	private String billAcc = "";

	/**
	 * is Total Amount Due <> 0
	 */
	private String totalamount = "";

	/**
	 * is search button clicked
	 */
	private String doSearch = "";

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
    
	public String getDoSearch() {
		return doSearch;
	}

	public void setDoSearch(String doSearch) {
		this.doSearch = doSearch;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId==null?"":custId.trim();
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName==null?"":custName.trim();
	}

	public String getBillCurrency() {
		return billCurrency;
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	public String getBillAcc() {
		return billAcc;
	}

	public void setBillAcc(String billAcc) {
		this.billAcc = billAcc==null?"":billAcc.trim();
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

}
