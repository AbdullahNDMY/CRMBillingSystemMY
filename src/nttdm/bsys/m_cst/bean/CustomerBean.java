/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST
 * FUNCTION       : Define bean Custom 
 * FILE NAME      : CustomerBean.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 * 2011/09/13 [Hoang Duong] Update
**********************************************************/
package nttdm.bsys.m_cst.bean;

import java.io.Serializable;

import nttdm.bsys.m_cst.dto.M_CSTR01Input;
/**
 * 	CustomerBean.class<br>
 * <ul>
 * <li>Retrieve searching result to fill into CustomerBean </li>
 * </ul>
 * <br>
 *  * @author NTT Data Vietnam	
 *   @version 1.1
 */
public class CustomerBean  implements Serializable{

	/**
	 * <div>serial id</div>
	 */
	private static final long serialVersionUID = 7625552019269168865L;
	
	/**
	 * <div>index</div>
	 */
	private Integer index = null;
	
	/**
	 * <div>id_cust</div>
	 */
	private String id_cust = null;

	/**
	 * <div>cust_acc_no</div>
	 */
	private String cust_acc_no = null;

	/**
	 * <div>cust_name</div>
	 */
	private String cust_name = null;

	/**
	 * <div>co_regno</div>
	 */
	private String co_regno = null;
	
	/**
	 * <div>country</div>
	 */
	
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
	private String[] chkCheckExport = null;
	private String[] chkCheckExport1 = null;
	private boolean checked = false;
	private String acc_mgr_prim ="";
	private String acc_mgr_sec = "";

	public String getgbcAccountManager(){
		if(acc_mgr_prim==null||acc_mgr_prim.trim().equals("")||acc_mgr_prim.equals("-")){
			return acc_mgr_sec;
		}else{
			return acc_mgr_prim;
		}
	}
	
	
	public String getAcc_mgr_prim() {
		return acc_mgr_prim;
	}

	public void setAcc_mgr_prim(String acc_mgr_prim) {
		this.acc_mgr_prim = acc_mgr_prim;
	}

	public String getAcc_mgr_sec() {
		return acc_mgr_sec;
	}

	public void setAcc_mgr_sec(String acc_mgr_sec) {
		this.acc_mgr_sec = acc_mgr_sec;
	}

	public String[] getChkCheckExport1() {
		return chkCheckExport1;
	}

	public void setChkCheckExport1(String[] chkCheckExport1) {
		this.chkCheckExport1 = chkCheckExport1;
	}
	private String customerType = null;
	
	
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String[] getChkCheckExport() {
		return chkCheckExport;
	}

	public void setChkCheckExport(String[] chkCheckExport) {
		this.chkCheckExport = chkCheckExport;
	}

	public void setCriticalForSearch(M_CSTR01Input input){
		if (input.getCustomerAccountNo() != null && !input.getCustomerAccountNo().equals(""))
			this.customerAccountNo = "%" + input.getCustomerAccountNo() + "%";
		if (input.getCoRegNo() != null && !input.getCoRegNo().equals(""))
			this.coRegNo = "%" + input.getCoRegNo() + "%";
		if (input.getCustomerName() != null && !input.getCustomerName().equals(""))
			this.customerName = "%" + input.getCustomerName() + "%";
		if (input.getOtherReferenceNo() != null && !input.getOtherReferenceNo().equals(""))
			this.otherReferenceNo = "%" + input.getOtherReferenceNo() + "%";
		if (input.getId_cust() != null && !input.getId_cust().equals(""))
			this.id_cust = "%" + input.getId_cust() + "%";
		if (input.getAddress() != null && !input.getAddress().equals(""))
			this.address = "%" + input.getAddress() + "%";
		if (input.getEmailAddress() != null && !input.getEmailAddress().equals(""))
			this.emailAddress = "%" + input.getEmailAddress()+ "%";
		if (input.getZipCode() != null && !input.getZipCode().equals(""))
			this.zipCode = "%" + input.getZipCode() + "%";
		if (input.getContactNo() != null && !input.getContactNo().equals(""))
			this.contactNo = "%" + input.getContactNo()+ "%";
		if (input.getBillingCountry() != null && !input.getBillingCountry().equals(""))
			this.billingCountry = "%" + input.getBillingCountry() + "%";
		if (input.getContactPerson() != null && !input.getContactPerson().equals(""))
			this.contactPerson = "%" + input.getContactPerson()+ "%";
		if (input.getAccountManager() != null && !input.getAccountManager().equals(""))
            this.accountManager = "%" + input.getAccountManager()+ "%";
	}
	// End Handn for M_CTS_r11
	private String country = null;

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
		this.customerAccountNo = customerAccountNo;
	}

	public String getCoRegNo() {
		return coRegNo;
	}

	public void setCoRegNo(String coRegNo) {
		this.coRegNo = coRegNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOtherReferenceNo() {
		return otherReferenceNo;
	}

	public void setOtherReferenceNo(String otherReferenceNo) {
		this.otherReferenceNo = otherReferenceNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getId_cust() {
		return this.id_cust;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}

	public String getCust_acc_no() {
		return this.cust_acc_no;
	}

	public void setCust_acc_no(String cust_acc_no) {
		this.cust_acc_no = cust_acc_no;
	}

	public String getCust_name() {
		return this.cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCo_regno() {
		return this.co_regno;
	}

	public void setCo_regno(String co_regno) {
		this.co_regno = co_regno;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}

	public void setChecked(boolean isChecked) {
		this.checked = isChecked;
	}

	public boolean isChecked() {
		return checked;
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
        this.accountManager = accountManager;
    }
}
