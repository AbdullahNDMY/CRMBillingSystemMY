
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS01
 * FUNCTION       : Get data BLogic
 * FILE NAME      : M_CSTR06BLogicInput.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
/**
 * InputDTO class<br>
 * <br>
 * <ul>
 * <li>
 * <li>InputDTO class
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1 
 */
public class M_CSTR06BLogicInput implements Serializable {

    private static final long serialVersionUID = -8028621674973616598L;

    private String cust_name = null;
    private String cust_acc_no = null;
    private String cust_id=null;
    private String cust_type=null;
    private String co_regno = null;
    private String country = null;

    private String customerName = null;
    private String coRegNo = null;
    private String customerAccountNo = null;
    private String id_cust = null;
    private String address = null;
    private String emailAddress = null;
    private String zipCode = null;
    private String contactNo = null;
    private String billingCountry = null;
    private String contactPerson = null;
    private String accountManager = null;
    /**
	 * <div>cust_acc_no</div>
	 */
	private String[] cust_acc_nos = null;

	/**
	 * <div>cust_names</div>
	 */
	private String[] cust_names = null;

	/**
	 * <div>co_regno</div>
	 */
	private String[] co_regnos = null;
	
	/**
	 * <div>country</div>
	 */
	private String[] countrys = null;
	
	private String[] chkCheckExport = null;
	
	private String[] id_custs = null;

	private String[] chkCheckExport1 = null;
	
	private String searchType = null;
	
	public String[] getChkCheckExport1() {
		return chkCheckExport1;
	}

	public void setChkCheckExport1(String[] chkCheckExport1) {
		this.chkCheckExport1 = chkCheckExport1;
	}

	public String[] getCust_acc_nos() {
		return cust_acc_nos;
	}

	public void setCust_acc_nos(String[] cust_acc_nos) {
		this.cust_acc_nos = cust_acc_nos;
	}

	public String[] getCust_names() {
		return cust_names;
	}

	public void setCust_names(String[] cust_names) {
		this.cust_names = cust_names;
	}

	public String[] getCo_regnos() {
		return co_regnos;
	}

	public void setCo_regnos(String[] co_regnos) {
		this.co_regnos = co_regnos;
	}

	public String[] getCountrys() {
		return countrys;
	}

	public void setCountrys(String[] countrys) {
		this.countrys = countrys;
	}

	public String[] getChkCheckExport() {
		return chkCheckExport;
	}

	public void setChkCheckExport(String[] chkCheckExport) {
		this.chkCheckExport = chkCheckExport;
	}

	public String[] getId_custs() {
		return id_custs;
	}

	public void setId_custs(String[] id_custs) {
		this.id_custs = id_custs;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
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