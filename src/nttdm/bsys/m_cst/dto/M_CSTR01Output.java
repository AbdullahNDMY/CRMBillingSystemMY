/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST01
 * FUNCTION       : Set output data
 * FILE NAME      : M_CSTR01Output.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 *  History
 * 2011/09/13 [Hoang Duong] Update
**********************************************************/
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.m_cst.bean.CustomerBean;

/**
 * M_CSTR01BLogic.class<br>
 * <ul>
 * <li>Output dto for customer searching blogic</li>
 * </ul>
 * <br>
 *  * @author NTT Data Vietnam	
 *   @version 1.1
 *
 */
public class M_CSTR01Output implements Serializable {

	/**
	 * <div>serial id</div>
	 */
	private static final long serialVersionUID = -6645624067442494744L;
	
	/**
	 * <div>cusBeans</div>
	 */
	private List<CustomerBean> cusBeans = null;

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
	 * <div>totalCount</div>
	 */
	private Integer totalCount=0;

    /**
     * Account Manager List
     */
    private List<Map<String, Object>> accountManagerList =null;
    private String accountManagerString = null;
	/**
	 * @return the accountManagerString
	 */
	public String getAccountManagerString() {
		return accountManagerString;
	}

	/**
	 * @param accountManagerString the accountManagerString to set
	 */
	public void setAccountManagerString(String accountManagerString) {
		this.accountManagerString = accountManagerString;
	}

	/**
	 * <div>row</div>
	 */
    private Integer row;

    private Integer startIndex=0;
    /**
     * <div>chkCheckExport1</div>
     */
	private String[] chkCheckExport1;
	private String[] chkCheckExport;
	private String[] chkCheckExportSaved;
	private String clickSearchButton = null;
	
	// for M_CTS_r11
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
	private String displayConsumerCust = null;
	private String accessType = null;
	private String displayExport = null;
	private String sub_module = "";
	private String id_cust = null;
	private String accountManager = null;

	public String getSub_module() {
		return sub_module;
	}

	public void setSub_module(String sub_module) {
		this.sub_module = sub_module;
	}

	public String getDisplayConsumerCust() {
		return displayConsumerCust;
	}

	public void setDisplayConsumerCust(String displayConsumerCust) {
		this.displayConsumerCust = displayConsumerCust;
	}



	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getDisplayExport() {
		return displayExport;
	}

	public void setDisplayExport(String displayExport) {
		this.displayExport = displayExport;
	}

	public String getCust_acc_no() {
		return cust_acc_no;
	}

	public void setCust_acc_no(String cust_acc_no) {
		this.cust_acc_no = cust_acc_no;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCo_regno() {
		return co_regno;
	}

	public void setCo_regno(String co_regno) {
		this.co_regno = co_regno;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCusBeans(List<CustomerBean> cusBeans) {
		this.cusBeans = cusBeans;
	}

	public List<CustomerBean> getCusBeans() {
		return cusBeans;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getRow() {
		return row;
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

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
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

	public void setChkCheckExportSaved(String[] chkCheckExportSaved) {
		this.chkCheckExportSaved = chkCheckExportSaved;
	}

	public String[] getChkCheckExportSaved() {
		return chkCheckExportSaved;
	}

	public void setChkCheckExport(String[] chkCheckExport) {
		this.chkCheckExport = chkCheckExport;
	}

	public String[] getChkCheckExport() {
		return chkCheckExport;
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
        this.cust_id = cust_id;
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
        this.accountManager = accountManager;
    }

    /**
     * @return the accountManagerList
     */
    public List<Map<String, Object>> getAccountManagerList() {
        return accountManagerList;
    }

    /**
     * @param accountManagerList the accountManagerList to set
     */
    public void setAccountManagerList(List<Map<String, Object>> accountManagerList) {
        this.accountManagerList = accountManagerList;
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

}