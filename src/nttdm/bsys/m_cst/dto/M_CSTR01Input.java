/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST01
 * FUNCTION       : Get input data search
 * FILE NAME      : M_CSTR01Input.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 *  History
 * 2011/09/13 [Hoang Duong] Update
**********************************************************/
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * M_CSTR01BLogic.class<br>
 * <ul>
 * <li>Input dto for customer searching blogic</li>
 * </ul>
 * <br>
 *  * @author NTT Data Vietnam	
 *   @version 1.1
 *
 */
public class M_CSTR01Input implements Serializable {

	/**
	 * <div>serial id</div>
	 */
	private static final long serialVersionUID = -2591844779497538634L;

	
	/**
	 * <div>cust_acc_no</div>
	 */
	private String cust_acc_no = null;

	/**
	 * <div>cust_name</div>
	 */
	private String cust_name = null;
	
    /**
     * <div>Customer ID for Basic Search</div>
     */
    private String cust_id=null;

    /**
     * <div>Customer Type for Basic Search</div>
     */
    private String cust_type=null;
    
	/**
	 * <div>co_regno</div>
	 */
	private String co_regno = null;
	
	/**
	 * <div>country</div>
	 */
	private String country = null;
	
	/**
	 * <div>temp_cust_acc_no</div>
	 */
	private String temp_cust_acc_no = null;

	/**
	 * <div>temp_cust_name</div>
	 */
	private String temp_cust_name = null;

	/**
	 * <div>temp_co_regno</div>
	 */
	private String temp_co_regno = null;
	
	/**
	 * <div>temp_country</div>
	 */
	private String temp_country = null;
	
	/**
	 * <div>event</div>
	 */
	private String event = null;
	
	/**
	 * <div>row</div>
	 */
	private Integer row=0;
	
	/**
	 * <div>startIndex</div>
	 */
	private String[] chkCheckExport1;
	private String[] chkCheckExport;
	private String[] chkCheckExportSaved;
	private String clickSearchButton = null;
	
	/**
	 * <div>chkCheckExport1</div>
	 */
	private String id_cust = null;
	
	private Integer startIndex=0;
	
	// Handn for M_CTS_r11
	private String searchType = null;	
	private String customerAccountNo = null;
	private String coRegNo = null;
	private String customerName = null;
	private String otherReferenceNo = null;
	private String address = null;
	private String emailAddress = null;
	private String zipCode = null;
	private String contactNo = null;
	private String billingCountry = null;
	private String contactPerson = null;
	private String accountManager = null;
	private BillingSystemUserValueObject uvo = null;
	private String sub_module = "";
	
	
	// End Handn for M_CTS_r11
	
	
	public String getTemp_cust_acc_no() {
		return temp_cust_acc_no;
	}

	public String getSub_module() {
		return sub_module;
	}

	public void setSub_module(String sub_module) {
		this.sub_module = sub_module != null ? sub_module.trim() : sub_module;
	}

	public void setTemp_cust_acc_no(String temp_cust_acc_no) {
		if(temp_cust_acc_no!=null){
		this.temp_cust_acc_no = temp_cust_acc_no.trim();
		}
	}

	public String getTemp_cust_name() {
		return temp_cust_name;
	}

	public void setTemp_cust_name(String temp_cust_name) {
		this.temp_cust_name =  temp_cust_name != null ? temp_cust_name.trim() :temp_cust_name;		
	}

	public String getTemp_co_regno() {
		return temp_co_regno;
	}

	public void setTemp_co_regno(String temp_co_regno) {
		this.temp_co_regno = temp_co_regno != null ? temp_co_regno.trim() :temp_co_regno;
	}

	public String getTemp_country() {
		return temp_country;
	}

	public void setTemp_country(String temp_country) {
		this.temp_country = temp_country != null ? temp_country.trim() :temp_country;
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

	public String getCust_acc_no() {
		return this.cust_acc_no;
	}

	public void setCust_acc_no(String cust_acc_no) {
		this.cust_acc_no = cust_acc_no != null ? cust_acc_no.trim() :cust_acc_no;
	}

	public String getCust_name() {
		return this.cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name != null ? cust_name.trim() :cust_name;
	}

	public String getCo_regno() {
		return this.co_regno;
	}

	public void setCo_regno(String co_regno) {
		this.co_regno = co_regno != null ? co_regno.trim() :co_regno;
	}

	public void setCountry(String country) {
		this.country = country != null ? country.trim() :country;
	}

	public String getCountry() {
		return country;
	}

	public void setEvent(String event) {
		this.event = event != null ? event.trim() :event;
	}

	public String getEvent() {
		return event;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getCustomerAccountNo() {
		return customerAccountNo;
	}

	public void setCustomerAccountNo(String customerAccountNo) {
		this.customerAccountNo = customerAccountNo != null ? customerAccountNo.trim() : customerAccountNo;
	}

	public String getCoRegNo() {
		return coRegNo;
	}

	public void setCoRegNo(String coRegNo) {
		this.coRegNo = coRegNo != null ? coRegNo.trim() : coRegNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName != null ? customerName.trim() : customerName;
	}

	public String getOtherReferenceNo() {
		return otherReferenceNo;
	}

	public void setOtherReferenceNo(String otherReferenceNo) {
		this.otherReferenceNo = otherReferenceNo != null ? otherReferenceNo.trim() : otherReferenceNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address != null ? address.trim() : address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress != null ? emailAddress.trim() : emailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode != null ? zipCode.trim() : zipCode;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo != null ? contactNo.trim() : contactNo;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry != null ? billingCountry.trim() : billingCountry;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson != null ? contactPerson.trim() : contactPerson;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust != null ? id_cust.trim() : id_cust;
	}

	public String getId_cust() {
		return id_cust;
	}

	public void setChkCheckExport1(String[] chkCheckExport1) {
		this.chkCheckExport1 = chkCheckExport1;
	}

	public String[] getChkCheckExport1() {
		return chkCheckExport1;
	}

	public void setChkCheckExport(String[] chkCheckExport) {
		this.chkCheckExport = chkCheckExport;
	}

	public String[] getChkCheckExport() {
		return chkCheckExport;
	}

	public void setChkCheckExportSaved(String[] chkCheckExportSaved) {
		this.chkCheckExportSaved = chkCheckExportSaved;
	}

	public String[] getChkCheckExportSaved() {
		return chkCheckExportSaved;
	}

	public void setClickSearchButton(String clickSearchButton) {
		this.clickSearchButton = clickSearchButton;
	}

	public String getClickSearchButton() {
		return clickSearchButton;
	}

    /**
     * @return the cust_id
     */
    public String getCust_id() {
        return cust_id;
    }

    /**
     * @param cust_id the cust_id to set
     */
    public void setCust_id(String cust_id) {
        this.cust_id = cust_id==null?"":cust_id.trim();
    }

    /**
     * @return the cust_type
     */
    public String getCust_type() {
        return cust_type;
    }

    /**
     * @param cust_type the cust_type to set
     */
    public void setCust_type(String cust_type) {
        this.cust_type = cust_type;
    }

    /**
     * @return the accountManager
     */
    public String getAccountManager() {
        return accountManager;
    }

    /**
     * @param accountManager the accountManager to set
     */
    public void setAccountManager(String accountManager) {
    	this.accountManager = accountManager != null ? accountManager.trim() : accountManager;
    }
	
}