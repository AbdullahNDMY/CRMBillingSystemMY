/*
 *Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST
 * FUNCTION       : Define Form Customer Bear 
 * FILE NAME      : M_CSTFormBean.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 *
 */
package nttdm.bsys.m_cst.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * <div>
 * Formbean is extended from ValidatorActionFormEx
 * </div> * 
 * @author NTT Data Vietnam	
 * @see jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx
 */
public class M_CSTFormBean extends ValidatorActionFormEx {

	/**
	 * <div>serial id</div>
	 */
	private static final long serialVersionUID = 7377059038048167846L;
	private String previous;
	private String fromPopup;
	private String clickSearchButton = null;
	/**
	 * <div>keep the mode of page</div>
	 */
	public static final String READONLY = "READONLY";
	
	/**
	 * <div>keep the mode of page</div>
	 */
	public static final String NEWMODE = "NEWMODE";
	
	/**
	 * <div>keep the mode of page</div>
	 */
	public static final String EDITMODE = "EDITMODE";
	
	/**
	 * <div>class name in css file</div>
	 */
	private static final String HIDDEN = "hidden";
	
	/**
	 * <div>class name in css file</div>
	 */
	private static final String UNHIDE = "unhide";
	
	/**
	 * <div>keep the label displaying in view mode</div>
	 */
	private String classViewMode="";
	
	/**
	 * <div>keep the controls displaying in modifying mode</div>
	 */
	private String classNewMode="";
	
	/**
	 * <div>uvo</div>
	 */
	private String uvo = null;
	
	/**
	 * <div>mode : remember mode from m_csts01 (readonly or default)</div>
	 */
	private String mode=null;
	
	/**
	 * <div>event : remember event click</div> 
	 */
	private String event=null;
	
	/**
	 * <div>id_cust</div> 
	 */
	private String id_cust=null;
	
	/**
	 * <div>cust_acc_no is searching key</div>
	 */
	private String cust_acc_no=null;

	/**
	 * <div>cust_name is searching key</div>
	 */
	private String cust_name=null;
	
	/**
	 * <div>Customer ID for Basic Search</div>
	 */
	private String cust_id=null;

	/**
	 * <div>Customer Type for Basic Search</div>
	 */
    private String cust_type=null;
    
    
	/**
	 * <div>co_regno is searching key</div>
	 */
	private String co_regno=null;
	
	/**
	 * <div>country is searching key</div>
	 */
	private String country=null;
	
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
     * PeopleSoft Acc. No. duplicated PopupInfo
     */
    private String peopleSoftPopupInfo = null;

	/**
	 * <div>cusBeans is searching result</div>
	 */
	private List<CustomerBean> cusBeans=null;
	
	/**
	 * <div>row is number of displayed items on searching result</div>
	 */
	private Integer row=0;
	
	/**
	 * <div>startIndex is start index of paging</div>
	 */
	private Integer startIndex=0;
	
	/**
	 * <div>totalCount is total items of searching result</div>
	 */
	private Integer totalCount=-1;
	
	/**
	 * <div>co_website 。</div>
	 */
	private String co_website = null;

	/**
	 * <div>co_email 。</div>
	 */
	private String co_email = null;

	/**
	 * <div>co_tel_no 。</div>
	 */
	private String co_tel_no = null;

	/**
	 * <div>co_fax_no 。</div>
	 */
	private String co_fax_no = null;

	/**
	 * <div>inter_comp 。</div>
	 */
	private String inter_comp;

	/**
	 * <div>bill_type 。</div>
	 */
	private String bill_type = null;

	/**
	 * <div>isp 。</div>
	 */
	private String isp;

	/**
	 * <div>gin 。</div>
	 */
	private String gin;

	/**
	 * <div>idc 。</div>
	 */
	private String idc;

	/**
	 * <div>sim 。</div>
	 */
	private String sim;

	/**
	 * <div>ra_adr_line1 。</div>
	 */
	private String ra_adr_line1 = null;

	/**
	 * <div>ra_adr_line2 。</div>
	 */
	private String ra_adr_line2 = null;

	/**
	 * <div>ra_adr_line3 。</div>
	 */
	private String ra_adr_line3 = null;

	/**
	 * <div>ra_country 。</div>
	 */
	private String ra_country = null;

	/**
	 * <div>ba_adr_line1 。</div>
	 */
	private String ba_adr_line1 = null;

	/**
	 * <div>ba_adr_line2 。</div>
	 */
	private String ba_adr_line2 = null;

	/**
	 * <div>ba_adr_line3 。</div>
	 */
	private String ba_adr_line3 = null;

	/**
	 * <div>ba_country 。</div>
	 */
	private String ba_country = null;

	/**
	 * <div>ca_adr_line1 。</div>
	 */
	private String ca_adr_line1 = null;

	/**
	 * <div>ca_adr_line2 。</div>
	 */
	private String ca_adr_line2 = null;

	/**
	 * <div>ca_adr_line3 。</div>
	 */
	private String ca_adr_line3 = null;

	/**
	 * <div>ca_country 。</div>
	 */
	private String ca_country = null;

	/**
	 * <div>pc_contact_name 。</div>
	 */
	private String pc_contact_name = null;

	/**
	 * <div>pc_designation 。</div>
	 */
	private String pc_designation = null;

	/**
	 * <div>pc_email 。</div>
	 */
	private String pc_email = null;

	/**
     * <div>pc_email_cc 。</div>
     */
    private String pc_email_cc = null;
	
	/**
	 * <div>pc_tel_no 。</div>
	 */
	private String pc_tel_no = null;

	/**
	 * <div>pc_did_no 。</div>
	 */
	private String pc_did_no = null;

	/**
	 * <div>pc_fax_no 。</div>
	 */
	private String pc_fax_no = null;

	/**
	 * <div>pc_mobile_no 。</div>
	 */
	private String pc_mobile_no = null;

	/**
	 * <div>bc_contact_name 。</div>
	 */
	private String bc_contact_name = null;

	/**
	 * <div>bc_designation 。</div>
	 */
	private String bc_designation = null;

	/**
	 * <div>bc_email 。</div>
	 */
	private String bc_email = null;
	
	/**
     * <div>bc_email_cc 。</div>
     */
    private String bc_email_cc = null;

	/**
	 * <div>bc_tel_no 。</div>
	 */
	private String bc_tel_no = null;

	/**
	 * <div>bc_did_no 。</div>
	 */
	private String bc_did_no = null;

	/**
	 * <div>bc_fax_no 。</div>
	 */
	private String bc_fax_no = null;

	/**
	 * <div>bc_mobile_no 。</div>
	 */
	private String bc_mobile_no = null;

	/**
	 * <div>tc_contact_name 。</div>
	 */
	private String tc_contact_name = null;

	/**
	 * <div>tc_designation 。</div>
	 */
	private String tc_designation = null;

	/**
	 * <div>tc_email 。</div>
	 */
	private String tc_email = null;

	/**
     * <div>tc_email_cc 。</div>
     */
    private String tc_email_cc = null;
	
	/**
	 * <div>tc_tel_no 。</div>
	 */
	private String tc_tel_no = null;

	/**
	 * <div>tc_did_no 。</div>
	 */
	private String tc_did_no = null;

	/**
	 * <div>tc_fax_no 。</div>
	 */
	private String tc_fax_no = null;

	/**
	 * <div>tc_mobile_no 。</div>
	 */
	private String tc_mobile_no = null;
	
	/**
	 * <div>oc_contact_name 。</div>
	 */
	private String oc_contact_name = null;

	/**
	 * <div>oc_designation 。</div>
	 */
	private String oc_designation = null;

	/**
	 * <div>oc_email 。</div>
	 */
	private String oc_email = null;

	/**
     * <div>oc_email_cc 。</div>
     */
    private String oc_email_cc = null;
	
	/**
	 * <div>oc_tel_no 。</div>
	 */
	private String oc_tel_no = null;

	/**
	 * <div>oc_did_no 。</div>
	 */
	private String oc_did_no = null;

	/**
	 * <div>oc_fax_no 。</div>
	 */
	private String oc_fax_no = null;

	/**
	 * <div>oc_mobile_no 。</div>
	 */
	private String oc_mobile_no = null;
	
	/**
	 * <div>ba_country_id</div>
	 */
	private String ba_country_id = null;
	
	/**
	 * <div>ra_country_id</div>
	 */
	private String ra_country_id = null;
	
	/**
	 * <div>ca_country_id</div>
	 */
	private String ca_country_id = null;
	
	/**
	 * <div>idAudit</div>
	 */
	private String allIdRefs = null;
	
	/**
	 * <div>allIdRefs</div>
	 */
	private Integer idAudit = null;
	
	private String tbxCustomerName = null;
	
	private String tbxCustomerId = null;
	
	private String doSearch = null;
	
	private String pageSearch = null;
	
	private  List<Map<String,Object>> customerBeans=null;
	
	//billing address 2
	private String ba_adr2_line1=null;
    
    private String ba_adr2_line2=null;
    
    private String ba_adr2_line3=null;
    
    private String ba_zip_code2=null;
    
    private String ba_country2=null;
    
    private String ba_country2_id=null;
	
    //billing address 3
    private String ba_adr3_line1=null;
    
    private String ba_adr3_line2=null;
    
    private String ba_adr3_line3=null;
    
    private String ba_zip_code3=null;
    
    private String ba_country3=null;
    
    private String ba_country3_id=null;
    
    //billing address 4
    private String ba_adr4_line1=null;
    
    private String ba_adr4_line2=null;
    
    private String ba_adr4_line3=null;
    
    private String ba_zip_code4=null;
    
    private String ba_country4=null;
    
    private String ba_country4_id=null;
    
  //Billing Contact 2
    /**
     * <div>bc2_contact_name</div>
     */
    private String bc2_contact_name = null;
    
    /**
     * <div>bc2_designation</div>
     */
    private String bc2_designation = null;
    
    /**
     * <div>bc2_email_to</div>
     */
    private String bc2_email_to= null;
    
    /**
     * <div>bc2_email_cc</div>
     */
    private String bc2_email_cc = null;
    
    /**
     * <div>bc2_tel_no</div>
     */
    private String bc2_tel_no = null;
    
    /**
     * <div>bc2_did_no</div>
     */
    private String bc2_did_no = null;
    
    /**
     * <div>bc2_fax_no</div>
     */
    private String bc2_fax_no = null;
    
    /**
     * <div>bc2_mobile_no</div>
     */
    private String bc2_mobile_no = null;
    
    //Billing Contact 3 
    /**
     * <div>bc3_contact_name</div>
     */
    private String bc3_contact_name = null;
    
    /**
     * <div>bc3_designation</div>
     */
    private String bc3_designation = null;
    
    /**
     * <div>bc3_email_to</div>
     */
    private String bc3_email_to= null;
    
    /**
     * <div>bc3_email_cc</div>
     */
    private String bc3_email_cc = null;
    
    /**
     * <div>bc3_tel_no</div>
     */
    private String bc3_tel_no = null;
    
    /**
     * <div>bc3_did_no</div>
     */
    private String bc3_did_no = null;
    
    /**
     * <div>bc3_fax_no</div>
     */
    private String bc3_fax_no = null;
    
    /**
     * <div>bc3_mobile_no</div>
     */
    private String bc3_mobile_no = null;
    
    //Billing Contact 4 
    /**
     * <div>bc4_contact_name</div>
     */
    private String bc4_contact_name = null;
    
    /**
     * <div>bc4_designation</div>
     */
    private String bc4_designation = null;
    
    /**
     * <div>bc4_email_to</div>
     */
    private String bc4_email_to= null;
    
    /**
     * <div>bc4_email_cc</div>
     */
    private String bc4_email_cc = null;
    
    /**
     * <div>bc4_tel_no</div>
     */
    private String bc4_tel_no = null;
    
    /**
     * <div>bc4_did_no</div>
     */
    private String bc4_did_no = null;
    
    /**
     * <div>bc4_fax_no</div>
     */
    private String bc4_fax_no = null;
    
    /**
     * <div>bc4_mobile_no</div>
     */
    private String bc4_mobile_no = null;
    
	public String getPageSearch() {
		return pageSearch;
	}

	public void setPageSearch(String pageSearch) {
		this.pageSearch = pageSearch;
	}

	public String getTbxCustomerName() {
		if (tbxCustomerName == null) {
			return tbxCustomerName;
		} else {
			return tbxCustomerName.trim();
		}
	}

	public void setTbxCustomerName(String tbxCustomerName) {
		this.tbxCustomerName = tbxCustomerName;
	}

	public String getTbxCustomerId() {
		if (tbxCustomerId == null) {
			return tbxCustomerId;
		} else {
			return tbxCustomerId.trim();
		}
	}

	public void setTbxCustomerId(String tbxCustomerId) {
		this.tbxCustomerId = tbxCustomerId;
	}

	public String getDoSearch() {
		return doSearch;
	}

	public void setDoSearch(String doSearch) {
		this.doSearch = doSearch;
	}

	public List<Map<String, Object>> getCustomerBeans() {
		return customerBeans;
	}

	public void setCustomerBeans(List<Map<String, Object>> customerBeans) {
		this.customerBeans = customerBeans;
	}

	/**
     * @return the ba_adr2_line1
     */
    public String getBa_adr2_line1() {
        return ba_adr2_line1;
    }

    /**
     * @param ba_adr2_line1 the ba_adr2_line1 to set
     */
    public void setBa_adr2_line1(String ba_adr2_line1) {
        this.ba_adr2_line1 = ba_adr2_line1;
    }

    /**
     * @return the ba_adr2_line2
     */
    public String getBa_adr2_line2() {
        return ba_adr2_line2;
    }

    /**
     * @param ba_adr2_line2 the ba_adr2_line2 to set
     */
    public void setBa_adr2_line2(String ba_adr2_line2) {
        this.ba_adr2_line2 = ba_adr2_line2;
    }

    /**
     * @return the ba_adr2_line3
     */
    public String getBa_adr2_line3() {
        return ba_adr2_line3;
    }

    /**
     * @param ba_adr2_line3 the ba_adr2_line3 to set
     */
    public void setBa_adr2_line3(String ba_adr2_line3) {
        this.ba_adr2_line3 = ba_adr2_line3;
    }

    /**
     * @return the ba_zip_code2
     */
    public String getBa_zip_code2() {
        return ba_zip_code2;
    }

    /**
     * @param ba_zip_code2 the ba_zip_code2 to set
     */
    public void setBa_zip_code2(String ba_zip_code2) {
        this.ba_zip_code2 = ba_zip_code2;
    }

    /**
     * @return the ba_country2
     */
    public String getBa_country2() {
        return ba_country2;
    }

    /**
     * @param ba_country2 the ba_country2 to set
     */
    public void setBa_country2(String ba_country2) {
        this.ba_country2 = ba_country2;
    }

    /**
     * @return the ba_country2_id
     */
    public String getBa_country2_id() {
        return ba_country2_id;
    }

    /**
     * @param ba_country2_id the ba_country2_id to set
     */
    public void setBa_country2_id(String ba_country2_id) {
        this.ba_country2_id = ba_country2_id;
    }

    /**
     * @return the ba_adr3_line1
     */
    public String getBa_adr3_line1() {
        return ba_adr3_line1;
    }

    /**
     * @param ba_adr3_line1 the ba_adr3_line1 to set
     */
    public void setBa_adr3_line1(String ba_adr3_line1) {
        this.ba_adr3_line1 = ba_adr3_line1;
    }

    /**
     * @return the ba_adr3_line2
     */
    public String getBa_adr3_line2() {
        return ba_adr3_line2;
    }

    /**
     * @param ba_adr3_line2 the ba_adr3_line2 to set
     */
    public void setBa_adr3_line2(String ba_adr3_line2) {
        this.ba_adr3_line2 = ba_adr3_line2;
    }

    /**
     * @return the ba_adr3_line3
     */
    public String getBa_adr3_line3() {
        return ba_adr3_line3;
    }

    /**
     * @param ba_adr3_line3 the ba_adr3_line3 to set
     */
    public void setBa_adr3_line3(String ba_adr3_line3) {
        this.ba_adr3_line3 = ba_adr3_line3;
    }

    /**
     * @return the ba_zip_code3
     */
    public String getBa_zip_code3() {
        return ba_zip_code3;
    }

    /**
     * @param ba_zip_code3 the ba_zip_code3 to set
     */
    public void setBa_zip_code3(String ba_zip_code3) {
        this.ba_zip_code3 = ba_zip_code3;
    }

    /**
     * @return the ba_country3
     */
    public String getBa_country3() {
        return ba_country3;
    }

    /**
     * @param ba_country3 the ba_country3 to set
     */
    public void setBa_country3(String ba_country3) {
        this.ba_country3 = ba_country3;
    }

    /**
     * @return the ba_country3_id
     */
    public String getBa_country3_id() {
        return ba_country3_id;
    }

    /**
     * @param ba_country3_id the ba_country3_id to set
     */
    public void setBa_country3_id(String ba_country3_id) {
        this.ba_country3_id = ba_country3_id;
    }

    /**
     * @return the ba_adr4_line1
     */
    public String getBa_adr4_line1() {
        return ba_adr4_line1;
    }

    /**
     * @param ba_adr4_line1 the ba_adr4_line1 to set
     */
    public void setBa_adr4_line1(String ba_adr4_line1) {
        this.ba_adr4_line1 = ba_adr4_line1;
    }

    /**
     * @return the ba_adr4_line2
     */
    public String getBa_adr4_line2() {
        return ba_adr4_line2;
    }

    /**
     * @param ba_adr4_line2 the ba_adr4_line2 to set
     */
    public void setBa_adr4_line2(String ba_adr4_line2) {
        this.ba_adr4_line2 = ba_adr4_line2;
    }

    /**
     * @return the ba_adr4_line3
     */
    public String getBa_adr4_line3() {
        return ba_adr4_line3;
    }

    /**
     * @param ba_adr4_line3 the ba_adr4_line3 to set
     */
    public void setBa_adr4_line3(String ba_adr4_line3) {
        this.ba_adr4_line3 = ba_adr4_line3;
    }

    /**
     * @return the ba_zip_code4
     */
    public String getBa_zip_code4() {
        return ba_zip_code4;
    }

    /**
     * @param ba_zip_code4 the ba_zip_code4 to set
     */
    public void setBa_zip_code4(String ba_zip_code4) {
        this.ba_zip_code4 = ba_zip_code4;
    }

    /**
     * @return the ba_country4
     */
    public String getBa_country4() {
        return ba_country4;
    }

    /**
     * @param ba_country4 the ba_country4 to set
     */
    public void setBa_country4(String ba_country4) {
        this.ba_country4 = ba_country4;
    }

    /**
     * @return the ba_country4_id
     */
    public String getBa_country4_id() {
        return ba_country4_id;
    }

    /**
     * @param ba_country4_id the ba_country4_id to set
     */
    public void setBa_country4_id(String ba_country4_id) {
        this.ba_country4_id = ba_country4_id;
    }

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
	private String accountManager = null;
    private List<Map<String, Object>> accountManagerList = null;
    private String accountManagerString = null;	
	private String displayConsumerCust = null;
	private String accessType = null;
	private String displayExport = null;	
	private String[] cust_acc_nos = null;
	
	private String print_statement;	
	private String ta_adr_line1 = null;
	private String ta_adr_line2 = null;
	private String ta_adr_line3 = null;
	private String ta_country = null;
	private String ta_country_id = null;	
	private String gboc_acc_no = null;	
	private String ra_zip_code= null;
	private String ta_zip_code = null;
	private String ba_zip_code = null;
	private String ca_zip_code = null;
	private String copy_to_bill_addr = null;
	private String others_ref_no = null;
	private String sub_module = "";
	private String copy_to_cor_tech = null;
	private String ac_sub_module = null;
	private String bi_sub_module = null;
	
	private String salutation = null;
	private String cust_id_no = null;
	private String cust_birth_date = null;
	private String home_tel_no = null;
	private String home_fax_no = null;
	private String sales_force_acc_id = null;
	private String affiliate_ntt ;
	private String mobile_no;
	private String company_sub_category = null;
	private String company_category = null;
	private String company_bank_info = null;
	private List<LabelValueBean> companyBankInfoList = new ArrayList<LabelValueBean>() ;
	private String end_user = null;
	private String category_enableFlg = null;
	private String subCategory_enableFlg = null;
	private String bankInfo_enableFlg = null;
	private String company_type = null;
	private String company_enableFlg = null;
	/**
	 * @return the company_sub_category
	 */
	public String getCompany_sub_category() {
		return company_sub_category;
	}

	/**
	 * @param company_sub_category the company_sub_category to set
	 */
	public void setCompany_sub_category(String company_sub_category) {
		this.company_sub_category = company_sub_category;
	}

	/**
	 * @return the company_category
	 */
	public String getCompany_category() {
		return company_category;
	}

	/**
	 * @param company_category the company_category to set
	 */
	public void setCompany_category(String company_category) {
		this.company_category = company_category;
	}
	
	/**
     * @return the company_bank_info
     */
	public String getCompany_bank_info() {
        return company_bank_info;
    }

	/**
     * @param company_bank_info the company_bank_info to set
     */
    public void setCompany_bank_info(String company_bank_info) {
        this.company_bank_info = company_bank_info;
    }

    /**
     * @return the companyBankInfoList
     */
    public List<LabelValueBean> getCompanyBankInfoList() {
        return companyBankInfoList;
    }

    /**
     * @param companyBankInfoList the companyBankInfoList to set
     */
    public void setCompanyBankInfoList(List<LabelValueBean> companyBankInfoList) {
        this.companyBankInfoList = companyBankInfoList;
    }   
    
    public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
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



	private String bank;
	private String acctNumber;
	private String bankName;
	private String acctName;
	private String branchName;
	private String cardType;
	private String holderName;
	private String acctNumberCredit;
	private String expiredDateMonth;
	private String creditCardNumber;
	private String securityNo;
	private String currentYear;
	private String expiredDateYear;
	private String expiredDate;
	private String tmpBirthDate;
	private List bankList;
	private String bankFullName;
	private String bankBicCode;
	private String bankCode;
	private String branchCode;
	private String swiftCode;
	private boolean hasBankInfo;
	private String event1=null;	
	private String refNum;
	
	private String acctNumberMsg;
    private String creditCardNumberMsg;
    private String popupClickYesFlg;
	
	private String cbBankCode;
	private String cbBranchCode;
	// Account Manager (Primary)
	private String managerPrimary = "";
	// Account Manager (Secondary)
	private String managerSecondary="";
	
	private String listUser = "";
	
	public String getListUser() {
		return listUser;
	}

	public void setListUser(String listUser) {
		this.listUser = listUser;
	}

	public String getManagerPrimary() {
		return managerPrimary;
	}

	public void setManagerPrimary(String managerPrimary) {
		this.managerPrimary = managerPrimary;
	}

	public String getManagerSecondary() {
		return managerSecondary;
	}

	public void setManagerSecondary(String managerSecondary) {
		this.managerSecondary = managerSecondary;
	}
	public String getCbBankCode() {
		return cbBankCode;
	}

	public void setCbBankCode(String cbBankCode) {
		this.cbBankCode = cbBankCode;
	}

	public String getCbBranchCode() {
		return cbBranchCode;
	}

	public void setCbBranchCode(String cbBranchCode) {
		this.cbBranchCode = cbBranchCode;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getEvent1() {
		return event1;
	}

	public void setEvent1(String event1) {
		this.event1 = event1;
	}
	
	public boolean isHasBankInfo() {
		return hasBankInfo;
	}

	public void setHasBankInfo(boolean hasBankInfo) {
		this.hasBankInfo = hasBankInfo;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}

	public String getBankBicCode() {
        return bankBicCode;
    }

    public void setBankBicCode(String bankBicCode) {
        this.bankBicCode = bankBicCode;
    }
	
	public List getBankList() {
		return bankList;
	}

	public void setBankList(List bankList) {
		this.bankList = bankList;
	}

	public String getAffiliate_ntt() {
		if (affiliate_ntt == null) return "";
		return affiliate_ntt;
	}

	public void setAffiliate_ntt(String affiliate_ntt) {
		this.affiliate_ntt = affiliate_ntt;
	}

	public String getTmpBirthDate() {
		return tmpBirthDate;
	}

	public void setTmpBirthDate(String tmpBirthDate) {
		this.tmpBirthDate = tmpBirthDate;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		this.currentYear = String.valueOf(year);
		//System.out.println("heelo=" + this.currentYear);
		return this.currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public String getAcctNumberCredit() {
		return acctNumberCredit;
	}

	public void setAcctNumberCredit(String acctNumberCredit) {
		this.acctNumberCredit = acctNumberCredit;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}



	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}


	public String getExpiredDateMonth() {
		return expiredDateMonth;
	}

	public void setExpiredDateMonth(String expiredDateMonth) {
		this.expiredDateMonth = expiredDateMonth;
	}

	public String getExpiredDateYear() {
		return expiredDateYear;
	}

	public void setExpiredDateYear(String expiredDateYear) {
		this.expiredDateYear = expiredDateYear;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getSecurityNo() {
		return securityNo;
	}

	public void setSecurityNo(String securityNo) {
		this.securityNo = securityNo;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getCust_id_no() {
		return cust_id_no;
	}

	public void setCust_id_no(String cust_id_no) {
		this.cust_id_no = cust_id_no;
	}

	public String getCust_birth_date() {
		return cust_birth_date;
	}

	public void setCust_birth_date(String cust_birth_date) {
		this.cust_birth_date = cust_birth_date;
	}

	public String getHome_tel_no() {
		return home_tel_no;
	}

	public void setHome_tel_no(String home_tel_no) {
		this.home_tel_no = home_tel_no;
	}

	public String getHome_fax_no() {
		return home_fax_no;
	}

	public void setHome_fax_no(String home_fax_no) {
		this.home_fax_no = home_fax_no;
	}

	public String getSales_force_acc_id() {
		return sales_force_acc_id;
	}

	public void setSales_force_acc_id(String sales_force_acc_id) {
		this.sales_force_acc_id = sales_force_acc_id;
	}

	public String getAc_sub_module() {
		return ac_sub_module;
	}

	public void setAc_sub_module(String ac_sub_module) {
		this.ac_sub_module = ac_sub_module;
	}

	public String getBi_sub_module() {
		return bi_sub_module;
	}

	public void setBi_sub_module(String bi_sub_module) {
		this.bi_sub_module = bi_sub_module;
	}

	public String getCopy_to_cor_tech() {
		return copy_to_cor_tech;
	}

	public void setCopy_to_cor_tech(String copy_to_cor_tech) {
		this.copy_to_cor_tech = copy_to_cor_tech;
	}

	public String getSub_module() {
		return sub_module;
	}

	public void setSub_module(String sub_module) {
		this.sub_module = sub_module;
	}

	public String getOthers_ref_no() {
		return others_ref_no;
	}

	public void setOthers_ref_no(String others_ref_no) {
		this.others_ref_no = others_ref_no;
	}

	public String getCopy_to_bill_addr() {
		return copy_to_bill_addr;
	}

	public void setCopy_to_bill_addr(String copy_to_bill_addr) {
		this.copy_to_bill_addr = copy_to_bill_addr;
	}

	public String getRa_zip_code() {
		return ra_zip_code;
	}

	public void setRa_zip_code(String ra_zip_code) {
		this.ra_zip_code = ra_zip_code;
	}

	public String getTa_zip_code() {
		return ta_zip_code;
	}

	public void setTa_zip_code(String ta_zip_code) {
		this.ta_zip_code = ta_zip_code;
	}

	public String getBa_zip_code() {
		return ba_zip_code;
	}

	public void setBa_zip_code(String ba_zip_code) {
		this.ba_zip_code = ba_zip_code;
	}

	public String getCa_zip_code() {
		return ca_zip_code;
	}

	public void setCa_zip_code(String ca_zip_code) {
		this.ca_zip_code = ca_zip_code;
	}




	public String getGboc_acc_no() {
		return gboc_acc_no;
	}

	public void setGboc_acc_no(String gboc_acc_no) {
		this.gboc_acc_no = gboc_acc_no;
	}

	public String getTa_adr_line1() {
		return ta_adr_line1;
	}

	public void setTa_adr_line1(String ta_adr_line1) {
		this.ta_adr_line1 = ta_adr_line1;
	}

	public String getTa_adr_line2() {
		return ta_adr_line2;
	}

	public void setTa_adr_line2(String ta_adr_line2) {
		this.ta_adr_line2 = ta_adr_line2;
	}

	public String getTa_adr_line3() {
		return ta_adr_line3;
	}

	public void setTa_adr_line3(String ta_adr_line3) {
		this.ta_adr_line3 = ta_adr_line3;
	}

	public String getTa_country() {
		return ta_country;
	}

	public void setTa_country(String ta_country) {
		this.ta_country = ta_country;
	}

	public String getTa_country_id() {
		return ta_country_id;
	}

	public void setTa_country_id(String ta_country_id) {
		this.ta_country_id = ta_country_id;
	}



	public String getPrint_statement() {
		return print_statement;
	}

	public void setPrint_statement(String print_statement) {
		this.print_statement = print_statement;
	}



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
	private String[] chkCheckExportSaved = null;
	
	private String[] id_custs = null;
	
	private String[] chkCheckExport1 = null;

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

	public String[] getId_custs() {
		return id_custs;
	}

	public void setId_custs(String[] id_custs) {
		this.id_custs = id_custs;
	}

	public String[] getChkCheckExport() {
		return chkCheckExport;
	}

	public void setChkCheckExport(String[] chkCheckExport) {
		this.chkCheckExport = chkCheckExport;
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

	/**
	 * <div>reset default value for checkboxes when submit form</div>
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.inter_comp="";
		this.print_statement="";
		this.isp = "";
		this.gin = "";
		this.idc = "";
		this.sim = "";
		this.previous = "M_CST";
	}

	public String getCo_website() {
		return co_website;
	}

	public String getOc_contact_name() {
		return oc_contact_name;
	}

	public void setOc_contact_name(String oc_contact_name) {
		this.oc_contact_name = oc_contact_name;
	}

	public String getOc_designation() {
		return oc_designation;
	}

	public void setOc_designation(String oc_designation) {
		this.oc_designation = oc_designation;
	}

	public String getOc_email() {
		return oc_email;
	}

	public void setOc_email(String oc_email) {
		this.oc_email = oc_email;
	}

	public String getOc_email_cc() {
        return oc_email_cc;
    }

    public void setOc_email_cc(String oc_email_cc) {
        this.oc_email_cc = oc_email_cc;
    }

    public String getOc_tel_no() {
		return oc_tel_no;
	}

	public void setOc_tel_no(String oc_tel_no) {
		this.oc_tel_no = oc_tel_no;
	}

	public String getOc_did_no() {
		return oc_did_no;
	}

	public void setOc_did_no(String oc_did_no) {
		this.oc_did_no = oc_did_no;
	}

	public String getOc_fax_no() {
		return oc_fax_no;
	}

	public void setOc_fax_no(String oc_fax_no) {
		this.oc_fax_no = oc_fax_no;
	}

	public String getOc_mobile_no() {
		return oc_mobile_no;
	}

	public void setOc_mobile_no(String oc_mobile_no) {
		this.oc_mobile_no = oc_mobile_no;
	}

	public void setCo_website(String co_website) {
		this.co_website = co_website;
	}

	public String getCo_email() {
		return co_email;
	}

	public void setCo_email(String co_email) {
		this.co_email = co_email;
	}

	public String getCo_tel_no() {
		return co_tel_no;
	}

	public void setCo_tel_no(String co_tel_no) {
		this.co_tel_no = co_tel_no;
	}

	public String getCo_fax_no() {
		return co_fax_no;
	}

	public void setCo_fax_no(String co_fax_no) {
		this.co_fax_no = co_fax_no;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public String getRa_adr_line1() {
		return ra_adr_line1;
	}

	public void setRa_adr_line1(String ra_adr_line1) {
		this.ra_adr_line1 = ra_adr_line1;
	}

	public String getRa_adr_line2() {
		return ra_adr_line2;
	}

	public void setRa_adr_line2(String ra_adr_line2) {
		this.ra_adr_line2 = ra_adr_line2;
	}

	public String getRa_adr_line3() {
		return ra_adr_line3;
	}

	public void setRa_adr_line3(String ra_adr_line3) {
		this.ra_adr_line3 = ra_adr_line3;
	}

	public String getRa_country() {
		return ra_country;
	}

	public void setRa_country(String ra_country) {
		this.ra_country = ra_country;
	}

	public String getBa_adr_line1() {
		return ba_adr_line1;
	}

	public void setBa_adr_line1(String ba_adr_line1) {
		this.ba_adr_line1 = ba_adr_line1;
	}

	public String getBa_adr_line2() {
		return ba_adr_line2;
	}

	public void setBa_adr_line2(String ba_adr_line2) {
		this.ba_adr_line2 = ba_adr_line2;
	}

	public String getBa_adr_line3() {
		return ba_adr_line3;
	}

	public void setBa_adr_line3(String ba_adr_line3) {
		this.ba_adr_line3 = ba_adr_line3;
	}

	public String getBa_country() {
		return ba_country;
	}

	public void setBa_country(String ba_country) {
		this.ba_country = ba_country;
	}

	public String getCa_adr_line1() {
		return ca_adr_line1;
	}

	public void setCa_adr_line1(String ca_adr_line1) {
		this.ca_adr_line1 = ca_adr_line1;
	}

	public String getCa_adr_line2() {
		return ca_adr_line2;
	}

	public void setCa_adr_line2(String ca_adr_line2) {
		this.ca_adr_line2 = ca_adr_line2;
	}

	public String getCa_adr_line3() {
		return ca_adr_line3;
	}

	public void setCa_adr_line3(String ca_adr_line3) {
		this.ca_adr_line3 = ca_adr_line3;
	}

	public String getCa_country() {
		return ca_country;
	}

	public void setCa_country(String ca_country) {
		this.ca_country = ca_country;
	}

	public String getPc_contact_name() {
		return pc_contact_name;
	}

	public void setPc_contact_name(String pc_contact_name) {
		this.pc_contact_name = pc_contact_name;
	}

	public String getPc_designation() {
		return pc_designation;
	}

	public void setPc_designation(String pc_designation) {
		this.pc_designation = pc_designation;
	}

	public String getPc_email() {
		return pc_email;
	}

	public void setPc_email(String pc_email) {
		this.pc_email = pc_email;
	}

	public String getPc_email_cc() {
        return pc_email_cc;
    }

    public void setPc_email_cc(String pc_email_cc) {
        this.pc_email_cc = pc_email_cc;
    }

    public String getPc_tel_no() {
		return pc_tel_no;
	}

	public void setPc_tel_no(String pc_tel_no) {
		this.pc_tel_no = pc_tel_no;
	}

	public String getPc_did_no() {
		return pc_did_no;
	}

	public void setPc_did_no(String pc_did_no) {
		this.pc_did_no = pc_did_no;
	}

	public String getPc_fax_no() {
		return pc_fax_no;
	}

	public void setPc_fax_no(String pc_fax_no) {
		this.pc_fax_no = pc_fax_no;
	}

	public String getPc_mobile_no() {
		return pc_mobile_no;
	}

	public void setPc_mobile_no(String pc_mobile_no) {
		this.pc_mobile_no = pc_mobile_no;
	}

	public String getBc_contact_name() {
		return bc_contact_name;
	}

	public void setBc_contact_name(String bc_contact_name) {
		this.bc_contact_name = bc_contact_name;
	}

	public String getBc_designation() {
		return bc_designation;
	}

	public void setBc_designation(String bc_designation) {
		this.bc_designation = bc_designation;
	}

	public String getBc_email() {
		return bc_email;
	}

	public void setBc_email(String bc_email) {
		this.bc_email = bc_email;
	}

	public String getBc_email_cc() {
        return bc_email_cc;
    }

    public void setBc_email_cc(String bc_email_cc) {
        this.bc_email_cc = bc_email_cc;
    }

    public String getBc_tel_no() {
		return bc_tel_no;
	}

	public void setBc_tel_no(String bc_tel_no) {
		this.bc_tel_no = bc_tel_no;
	}

	public String getBc_did_no() {
		return bc_did_no;
	}

	public void setBc_did_no(String bc_did_no) {
		this.bc_did_no = bc_did_no;
	}

	public String getBc_fax_no() {
		return bc_fax_no;
	}

	public void setBc_fax_no(String bc_fax_no) {
		this.bc_fax_no = bc_fax_no;
	}

	public String getBc_mobile_no() {
		return bc_mobile_no;
	}

	public void setBc_mobile_no(String bc_mobile_no) {
		this.bc_mobile_no = bc_mobile_no;
	}

	public String getTc_contact_name() {
		return tc_contact_name;
	}

	public void setTc_contact_name(String tc_contact_name) {
		this.tc_contact_name = tc_contact_name;
	}

	public String getTc_designation() {
		return tc_designation;
	}

	public void setTc_designation(String tc_designation) {
		this.tc_designation = tc_designation;
	}

	public String getTc_email() {
		return tc_email;
	}

	public void setTc_email(String tc_email) {
		this.tc_email = tc_email;
	}

	public String getTc_email_cc() {
        return tc_email_cc;
    }

    public void setTc_email_cc(String tc_email_cc) {
        this.tc_email_cc = tc_email_cc;
    }

    public String getTc_tel_no() {
		return tc_tel_no;
	}

	public void setTc_tel_no(String tc_tel_no) {
		this.tc_tel_no = tc_tel_no;
	}

	public String getTc_did_no() {
		return tc_did_no;
	}

	public void setTc_did_no(String tc_did_no) {
		this.tc_did_no = tc_did_no;
	}

	public String getTc_fax_no() {
		return tc_fax_no;
	}

	public void setTc_fax_no(String tc_fax_no) {
		this.tc_fax_no = tc_fax_no;
	}

	public String getTc_mobile_no() {
		return tc_mobile_no;
	}

	public void setTc_mobile_no(String tc_mobile_no) {
		this.tc_mobile_no = tc_mobile_no;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
		if(cusBeans==null){
			return new ArrayList<CustomerBean>();
		}
		return cusBeans;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}

	public String getId_cust() {
		return id_cust;
	}

	public void setBa_country_id(String ba_country_id) {
		this.ba_country_id = ba_country_id;
	}

	public String getBa_country_id() {
		return ba_country_id;
	}

	public void setRa_country_id(String ra_country_id) {
		this.ra_country_id = ra_country_id;
	}

	public String getRa_country_id() {
		return ra_country_id;
	}

	public void setCa_country_id(String ca_country_id) {
		this.ca_country_id = ca_country_id;
	}

	public String getCa_country_id() {
		return ca_country_id;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public void setClassViewMode(String classViewMode) {
		this.classViewMode = classViewMode;
	}

	public String getClassViewMode() {
		//check the mode of page
		if(this.getMode()!=null && String.valueOf(this.getMode()).equals(M_CSTFormBean.READONLY)){
			//when mode is readonly
			//visible labels
			classViewMode = M_CSTFormBean.UNHIDE;
		}
		else{
			//otherwise mode isn't readonly
			//hidden labels
			classViewMode = M_CSTFormBean.HIDDEN;
		}
		return classViewMode;
	}

	public void setClassNewMode(String classNewMode) {
		this.classNewMode = classNewMode;
	}

	public String getClassNewMode() {
		//check the mode of page
		if(this.getMode()!=null && String.valueOf(this.getMode()).equals(M_CSTFormBean.READONLY)){
			//when mode is readonly
			//hidden controls
			classNewMode = M_CSTFormBean.HIDDEN;
		}
		else{
			//otherwise mode is new
			//visible controls
			classNewMode = M_CSTFormBean.UNHIDE;
		}
		return classNewMode;
	}

	public String getTemp_cust_acc_no() {
		return temp_cust_acc_no;
	}

	public void setTemp_cust_acc_no(String temp_cust_acc_no) {
		this.temp_cust_acc_no = temp_cust_acc_no;
	}

	public String getTemp_cust_name() {
		return temp_cust_name;
	}

	public void setTemp_cust_name(String temp_cust_name) {
		this.temp_cust_name = temp_cust_name;
	}

	public String getTemp_co_regno() {
		return temp_co_regno;
	}

	public void setTemp_co_regno(String temp_co_regno) {
		this.temp_co_regno = temp_co_regno;
	}

	public String getTemp_country() {
		return temp_country;
	}

	public void setTemp_country(String temp_country) {
		this.temp_country = temp_country;
	}

	public void setUvo(String uvo) {
		this.uvo = uvo;
	}

	public String getUvo() {
		return uvo;
	}

	public String getInter_comp() {
		return inter_comp;
	}

	public void setInter_comp(String inter_comp) {
		this.inter_comp = inter_comp;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getGin() {
		return gin;
	}

	public void setGin(String gin) {
		this.gin = gin;
	}

	public String getIdc() {
		return idc;
	}

	public void setIdc(String idc) {
		this.idc = idc;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
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

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}
	
	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public void setFromPopup(String fromPopup) {
		this.fromPopup = fromPopup;
	}

	public String getFromPopup() {
		return fromPopup;
	}

	public void setAllIdRefs(String allIdRefs) {
		this.allIdRefs = allIdRefs;
	}

	public String getAllIdRefs() {
		return allIdRefs;
	}

	public String[] getChkCheckExportSaved() {
		return chkCheckExportSaved;
	}

	public void setChkCheckExportSaved(String[] chkCheckExportSaved) {
		this.chkCheckExportSaved = chkCheckExportSaved;
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
     * @return the acctNumberMsg
     */
    public String getAcctNumberMsg() {
        return acctNumberMsg;
    }

    /**
     * @param acctNumberMsg the acctNumberMsg to set
     */
    public void setAcctNumberMsg(String acctNumberMsg) {
        this.acctNumberMsg = acctNumberMsg;
    }

    /**
     * @return the creditCardNumberMsg
     */
    public String getCreditCardNumberMsg() {
        return creditCardNumberMsg;
    }

    /**
     * @param creditCardNumberMsg the creditCardNumberMsg to set
     */
    public void setCreditCardNumberMsg(String creditCardNumberMsg) {
        this.creditCardNumberMsg = creditCardNumberMsg;
    }

    /**
     * @return the popupClickYesFlg
     */
    public String getPopupClickYesFlg() {
        return popupClickYesFlg;
    }

    /**
     * @param popupClickYesFlg the popupClickYesFlg to set
     */
    public void setPopupClickYesFlg(String popupClickYesFlg) {
        this.popupClickYesFlg = popupClickYesFlg;
    }

    public String getPeopleSoftPopupInfo() {
        return peopleSoftPopupInfo;
    }

    public void setPeopleSoftPopupInfo(String peopleSoftPopupInfo) {
        this.peopleSoftPopupInfo = peopleSoftPopupInfo;
    }
    
    /**
     * @return the bc2_contact_name
     */
    public String getBc2_contact_name() {
        return bc2_contact_name;
    }

    /**
     * @param bc2_contact_name the bc2_contact_name to set
     */
    public void setBc2_contact_name(String bc2_contact_name) {
        this.bc2_contact_name = bc2_contact_name;
    }

    /**
     * @return the bc2_designation
     */
    public String getBc2_designation() {
        return bc2_designation;
    }

    /**
     * @param bc2_designation the bc2_designation to set
     */
    public void setBc2_designation(String bc2_designation) {
        this.bc2_designation = bc2_designation;
    }

    /**
     * @return the bc2_email_to
     */
    public String getBc2_email_to() {
        return bc2_email_to;
    }

    /**
     * @param bc2_email_to the bc2_email_to to set
     */
    public void setBc2_email_to(String bc2_email_to) {
        this.bc2_email_to = bc2_email_to;
    }

    /**
     * @return the bc2_email_cc
     */
    public String getBc2_email_cc() {
        return bc2_email_cc;
    }

    /**
     * @param bc2_email_cc the bc2_email_cc to set
     */
    public void setBc2_email_cc(String bc2_email_cc) {
        this.bc2_email_cc = bc2_email_cc;
    }

    /**
     * @return the bc2_tel_no
     */
    public String getBc2_tel_no() {
        return bc2_tel_no;
    }

    /**
     * @param bc2_tel_no the bc2_tel_no to set
     */
    public void setBc2_tel_no(String bc2_tel_no) {
        this.bc2_tel_no = bc2_tel_no;
    }

    /**
     * @return the bc2_did_no
     */
    public String getBc2_did_no() {
        return bc2_did_no;
    }

    /**
     * @param bc2_did_no the bc2_did_no to set
     */
    public void setBc2_did_no(String bc2_did_no) {
        this.bc2_did_no = bc2_did_no;
    }

    /**
     * @return the bc2_fax_no
     */
    public String getBc2_fax_no() {
        return bc2_fax_no;
    }

    /**
     * @param bc2_fax_no the bc2_fax_no to set
     */
    public void setBc2_fax_no(String bc2_fax_no) {
        this.bc2_fax_no = bc2_fax_no;
    }

    /**
     * @return the bc2_mobile_no
     */
    public String getBc2_mobile_no() {
        return bc2_mobile_no;
    }

    /**
     * @param bc2_mobile_no the bc2_mobile_no to set
     */
    public void setBc2_mobile_no(String bc2_mobile_no) {
        this.bc2_mobile_no = bc2_mobile_no;
    }

    /**
     * @return the bc3_contact_name
     */
    public String getBc3_contact_name() {
        return bc3_contact_name;
    }

    /**
     * @param bc3_contact_name the bc3_contact_name to set
     */
    public void setBc3_contact_name(String bc3_contact_name) {
        this.bc3_contact_name = bc3_contact_name;
    }

    /**
     * @return the bc3_designation
     */
    public String getBc3_designation() {
        return bc3_designation;
    }

    /**
     * @param bc3_designation the bc3_designation to set
     */
    public void setBc3_designation(String bc3_designation) {
        this.bc3_designation = bc3_designation;
    }

    /**
     * @return the bc3_email_to
     */
    public String getBc3_email_to() {
        return bc3_email_to;
    }

    /**
     * @param bc3_email_to the bc3_email_to to set
     */
    public void setBc3_email_to(String bc3_email_to) {
        this.bc3_email_to = bc3_email_to;
    }

    /**
     * @return the bc3_email_cc
     */
    public String getBc3_email_cc() {
        return bc3_email_cc;
    }

    /**
     * @param bc3_email_cc the bc3_email_cc to set
     */
    public void setBc3_email_cc(String bc3_email_cc) {
        this.bc3_email_cc = bc3_email_cc;
    }

    /**
     * @return the bc3_tel_no
     */
    public String getBc3_tel_no() {
        return bc3_tel_no;
    }

    /**
     * @param bc3_tel_no the bc3_tel_no to set
     */
    public void setBc3_tel_no(String bc3_tel_no) {
        this.bc3_tel_no = bc3_tel_no;
    }

    /**
     * @return the bc3_did_no
     */
    public String getBc3_did_no() {
        return bc3_did_no;
    }

    /**
     * @param bc3_did_no the bc3_did_no to set
     */
    public void setBc3_did_no(String bc3_did_no) {
        this.bc3_did_no = bc3_did_no;
    }

    /**
     * @return the bc3_fax_no
     */
    public String getBc3_fax_no() {
        return bc3_fax_no;
    }

    /**
     * @param bc3_fax_no the bc3_fax_no to set
     */
    public void setBc3_fax_no(String bc3_fax_no) {
        this.bc3_fax_no = bc3_fax_no;
    }

    /**
     * @return the bc3_mobile_no
     */
    public String getBc3_mobile_no() {
        return bc3_mobile_no;
    }

    /**
     * @param bc3_mobile_no the bc3_mobile_no to set
     */
    public void setBc3_mobile_no(String bc3_mobile_no) {
        this.bc3_mobile_no = bc3_mobile_no;
    }

    /**
     * @return the bc4_contact_name
     */
    public String getBc4_contact_name() {
        return bc4_contact_name;
    }

    /**
     * @param bc4_contact_name the bc4_contact_name to set
     */
    public void setBc4_contact_name(String bc4_contact_name) {
        this.bc4_contact_name = bc4_contact_name;
    }

    /**
     * @return the bc4_designation
     */
    public String getBc4_designation() {
        return bc4_designation;
    }

    /**
     * @param bc4_designation the bc4_designation to set
     */
    public void setBc4_designation(String bc4_designation) {
        this.bc4_designation = bc4_designation;
    }

    /**
     * @return the bc4_email_to
     */
    public String getBc4_email_to() {
        return bc4_email_to;
    }

    /**
     * @param bc4_email_to the bc4_email_to to set
     */
    public void setBc4_email_to(String bc4_email_to) {
        this.bc4_email_to = bc4_email_to;
    }

    /**
     * @return the bc4_email_cc
     */
    public String getBc4_email_cc() {
        return bc4_email_cc;
    }

    /**
     * @param bc4_email_cc the bc4_email_cc to set
     */
    public void setBc4_email_cc(String bc4_email_cc) {
        this.bc4_email_cc = bc4_email_cc;
    }

    /**
     * @return the bc4_tel_no
     */
    public String getBc4_tel_no() {
        return bc4_tel_no;
    }

    /**
     * @param bc4_tel_no the bc4_tel_no to set
     */
    public void setBc4_tel_no(String bc4_tel_no) {
        this.bc4_tel_no = bc4_tel_no;
    }

    /**
     * @return the bc4_did_no
     */
    public String getBc4_did_no() {
        return bc4_did_no;
    }

    /**
     * @param bc4_did_no the bc4_did_no to set
     */
    public void setBc4_did_no(String bc4_did_no) {
        this.bc4_did_no = bc4_did_no;
    }

    /**
     * @return the bc4_fax_no
     */
    public String getBc4_fax_no() {
        return bc4_fax_no;
    }

    /**
     * @param bc4_fax_no the bc4_fax_no to set
     */
    public void setBc4_fax_no(String bc4_fax_no) {
        this.bc4_fax_no = bc4_fax_no;
    }

    /**
     * @return the bc4_mobile_no
     */
    public String getBc4_mobile_no() {
        return bc4_mobile_no;
    }

    /**
     * @param bc4_mobile_no the bc4_mobile_no to set
     */
    public void setBc4_mobile_no(String bc4_mobile_no) {
        this.bc4_mobile_no = bc4_mobile_no;
    }

	/**
	 * @return the end_user
	 */
	public String getEnd_user() {
		return end_user;
	}

	/**
	 * @param end_user the end_user to set
	 */
	public void setEnd_user(String end_user) {
		this.end_user = end_user;
	}

	/**
	 * @return the category_enableFlg
	 */
	public String getCategory_enableFlg() {
		return category_enableFlg;
	}

	/**
	 * @param category_enableFlg the category_enableFlg to set
	 */
	public void setCategory_enableFlg(String category_enableFlg) {
		this.category_enableFlg = category_enableFlg;
	}

	/**
	 * @return the subCategory_enableFlg
	 */
	public String getSubCategory_enableFlg() {
		return subCategory_enableFlg;
	}

	/**
	 * @param subCategory_enableFlg the subCategory_enableFlg to set
	 */
	public void setSubCategory_enableFlg(String subCategory_enableFlg) {
		this.subCategory_enableFlg = subCategory_enableFlg;
	}

	/**
	 * @return the bankInfo_enableFlg
	 */
	public String getBankInfo_enableFlg() {
		return bankInfo_enableFlg;
	}

	/**
	 * @param bankInfo_enableFlg the bankInfo_enableFlg to set
	 */
	public void setBankInfo_enableFlg(String bankInfo_enableFlg) {
		this.bankInfo_enableFlg = bankInfo_enableFlg;
	}

	/**
	 * @return the company_type
	 */
	public String getCompany_type() {
		return company_type;
	}

	/**
	 * @param company_type the company_type to set
	 */
	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	/**
	 * @return the company_enableFlg
	 */
	public String getCompany_enableFlg() {
		return company_enableFlg;
	}

	/**
	 * @param company_enableFlg the company_enableFlg to set
	 */
	public void setCompany_enableFlg(String company_enableFlg) {
		this.company_enableFlg = company_enableFlg;
	}

	

}