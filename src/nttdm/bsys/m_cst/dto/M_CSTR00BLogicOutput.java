/*
 * @(#)M_CSTR00BLogicOutput.java
 *
 *
 */
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.m_cst.bean.CustomerBean;

/**
 * OutputDTO class.
 * 
 * @author ss054
 */
public class M_CSTR00BLogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6231954895857069947L;

	/**
     * display Consumer Customer button
     */
	private String displayConsumerCust;

	/**
     * display Export button
     */
	private String displayExport;

	/**
     * access Type
     */
	private String accessType;

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

    private List<CustomerBean> cusBeans = null;
    private String cust_name = null;
    private String cust_acc_no = null;
    private String cust_id=null;
    private String cust_type=null;
    private String co_regno = null;
    private String country = null;
    private Integer totalCount=0;
    private Integer row;
    private Integer startIndex=0;
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
    private String id_cust = null;
    private String accountManager = null;
    private String chkCheckExport1 = null;
    private String chkCheckExport = null;
    private String chkCheckExportSaved = null;
    private String clickSearchButton = null;
    
	/**
	 * displayConsumerCust を取得する
	 * 
	 * @return displayConsumerCust
	 */
	public String getDisplayConsumerCust() {
		return displayConsumerCust;
	}

	/**
	 * displayConsumerCust を設定する
	 * 
	 * @param displayConsumerCust
	 */
	public void setDisplayConsumerCust(String displayConsumerCust) {
		this.displayConsumerCust = displayConsumerCust;
	}

	/**
	 * displayExport を取得する
	 * 
	 * @return displayExport
	 */
	public String getDisplayExport() {
		return displayExport;
	}

	/**
	 * displayExport を設定する
	 * 
	 * @param displayExport
	 */
	public void setDisplayExport(String displayExport) {
		this.displayExport = displayExport;
	}

	/**
	 * accessType を設定する
	 * 
	 * @return accessType
	 */
	public String getAccessType() {
		return accessType;
	}

	/**
	 * AccessType を設定する
	 * 
	 * @param accessType
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

    /**
     * @param accountManagerList the accountManagerList to set
     */
    public void setAccountManagerList(List<Map<String, Object>> accountManagerList) {
        this.accountManagerList = accountManagerList;
    }

    /**
     * @return the accountManagerList
     */
    public List<Map<String, Object>> getAccountManagerList() {
        return accountManagerList;
    }

    /**
     * @return the cusBeans
     */
    public List<CustomerBean> getCusBeans() {
        return cusBeans;
    }

    /**
     * @param cusBeans the cusBeans to set
     */
    public void setCusBeans(List<CustomerBean> cusBeans) {
        this.cusBeans = cusBeans;
    }

    /**
     * @return the cust_acc_no
     */
    public String getCust_acc_no() {
        return cust_acc_no;
    }

    /**
     * @param cust_acc_no the cust_acc_no to set
     */
    public void setCust_acc_no(String cust_acc_no) {
        this.cust_acc_no = cust_acc_no;
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
     * @return the co_regno
     */
    public String getCo_regno() {
        return co_regno;
    }

    /**
     * @param co_regno the co_regno to set
     */
    public void setCo_regno(String co_regno) {
        this.co_regno = co_regno;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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
     * @return the cust_name
     */
    public String getCust_name() {
        return cust_name;
    }

    /**
     * @param cust_name the cust_name to set
     */
    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    /**
     * @return the searchType
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * @param searchType the searchType to set
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     * @return the customerAccountNo
     */
    public String getCustomerAccountNo() {
        return customerAccountNo;
    }

    /**
     * @param customerAccountNo the customerAccountNo to set
     */
    public void setCustomerAccountNo(String customerAccountNo) {
        this.customerAccountNo = customerAccountNo;
    }

    /**
     * @return the coRegNo
     */
    public String getCoRegNo() {
        return coRegNo;
    }

    /**
     * @param coRegNo the coRegNo to set
     */
    public void setCoRegNo(String coRegNo) {
        this.coRegNo = coRegNo;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the otherReferenceNo
     */
    public String getOtherReferenceNo() {
        return otherReferenceNo;
    }

    /**
     * @param otherReferenceNo the otherReferenceNo to set
     */
    public void setOtherReferenceNo(String otherReferenceNo) {
        this.otherReferenceNo = otherReferenceNo;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the billingCountry
     */
    public String getBillingCountry() {
        return billingCountry;
    }

    /**
     * @param billingCountry the billingCountry to set
     */
    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    /**
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @param contactPerson the contactPerson to set
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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
     * @return the chkCheckExport1
     */
    public String getChkCheckExport1() {
        return chkCheckExport1;
    }

    /**
     * @param chkCheckExport1 the chkCheckExport1 to set
     */
    public void setChkCheckExport1(String chkCheckExport1) {
        this.chkCheckExport1 = chkCheckExport1;
    }

    /**
     * @return the chkCheckExport
     */
    public String getChkCheckExport() {
        return chkCheckExport;
    }

    /**
     * @param chkCheckExport the chkCheckExport to set
     */
    public void setChkCheckExport(String chkCheckExport) {
        this.chkCheckExport = chkCheckExport;
    }

    /**
     * @return the chkCheckExportSaved
     */
    public String getChkCheckExportSaved() {
        return chkCheckExportSaved;
    }

    /**
     * @param chkCheckExportSaved the chkCheckExportSaved to set
     */
    public void setChkCheckExportSaved(String chkCheckExportSaved) {
        this.chkCheckExportSaved = chkCheckExportSaved;
    }

    /**
     * @return the clickSearchButton
     */
    public String getClickSearchButton() {
        return clickSearchButton;
    }

    /**
     * @param clickSearchButton the clickSearchButton to set
     */
    public void setClickSearchButton(String clickSearchButton) {
        this.clickSearchButton = clickSearchButton;
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
