/*
 * @(#)M_CSTR02Output.java
 * $Id$
 */
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * resultClass: id=SELECT.M_CST.SQL003
 * 
 * @author Duyen.Vo
 */
public class M_CSTR02Output implements Serializable {

	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = -907141641797697681L;
	private String previous;
	private String fromPopup;
	/**
	 * <div>id_cust</div>
	 */
	private String id_cust = null;

	/**
	 * <div>cust_name</div>
	 */
	private String cust_name = null;

	/**
	 * <div>co_regno</div>
	 */
	private String co_regno = null;

	/**
	 * <div>co_website</div>
	 */
	private String co_website = null;

	/**
	 * <div>co_email</div>
	 */
	private String co_email = null;

	/**
	 * <div>co_tel_no</div>
	 */
	private String co_tel_no = null;

	/**
	 * <div>co_fax_no</div>
	 */
	private String co_fax_no = null;

	/**
	 * <div>inter_comp</div>
	 */
	private String inter_comp;

	/**
	 * <div>print_statement</div>
	 */
	private String print_statement;
	
	/**
	 * <div>cust_acc_no</div>
	 */
	private String cust_acc_no = null;

	/**
	 * <div>bill_type</div>
	 */
	private String bill_type = null;

	/**
	 * <div>isp</div>
	 */
	private String isp;

	/**
	 * <div>gin</div>
	 */
	private String gin;

	/**
	 * <div>idc</div>
	 */
	private String idc;

	/**
	 * <div>sim</div>
	 */
	private String sim;

	/**
	 * <div>ra_adr_line1</div>
	 */
	private String ra_adr_line1 = null;

	/**
	 * <div>ra_adr_line2</div>
	 */
	private String ra_adr_line2 = null;

	/**
	 * <div>ra_adr_line3</div>
	 */
	private String ra_adr_line3 = null;

	/**
	 * <div>ra_country</div>
	 */
	private String ra_country = null;

	/**
	 * <div>ba_adr_line1</div>
	 */
	private String ba_adr_line1 = null;

	/**
	 * <div>ba_adr_line2</div>
	 */
	private String ba_adr_line2 = null;

	/**
	 * <div>ba_adr_line3</div>
	 */
	private String ba_adr_line3 = null;

	/**
	 * <div>ba_country</div>
	 */
	private String ba_country = null;

	/**
	 * <div>ca_adr_line1</div>
	 */
	private String ca_adr_line1 = null;

	/**
	 * <div>ca_adr_line2</div>
	 */
	private String ca_adr_line2 = null;

	/**
	 * <div>ca_adr_line3</div>
	 */
	private String ca_adr_line3 = null;

	/**
	 * <div>ca_country</div>
	 */
	private String ca_country = null;
	
	private String ta_adr_line1 = null;

	/**
	 * <div>ca_adr_line2</div>
	 */
	private String ta_adr_line2 = null;

	/**
	 * <div>ca_adr_line3</div>
	 */
	private String ta_adr_line3 = null;

	/**
	 * <div>ca_country</div>
	 */
	private String ta_country = null;	

	/**
	 * <div>pc_contact_name</div>
	 */
	private String pc_contact_name = null;

	/**
	 * <div>pc_designation</div>
	 */
	private String pc_designation = null;

	/**
	 * <div>pc_email</div>
	 */
	private String pc_email = null;
	
	/**
     * <div>pc_email_cc</div>
     */
    private String pc_email_cc = null;

	/**
	 * <div>pc_tel_no</div>
	 */
	private String pc_tel_no = null;

	/**
	 * <div>pc_did_no</div>
	 */
	private String pc_did_no = null;

	/**
	 * <div>pc_fax_no</div>
	 */
	private String pc_fax_no = null;

	/**
	 * <div>pc_mobile_no</div>
	 */
	private String pc_mobile_no = null;

	/**
	 * <div>bc_contact_name</div>
	 */
	private String bc_contact_name = null;

	/**
	 * <div>bc_designation</div>
	 */
	private String bc_designation = null;

	/**
	 * <div>bc_email</div>
	 */
	private String bc_email = null;

	/**
     * <div>bc_email_cc</div>
     */
    private String bc_email_cc = null;
    
	/**
	 * <div>bc_tel_no</div>
	 */
	private String bc_tel_no = null;

	/**
	 * <div>bc_did_no</div>
	 */
	private String bc_did_no = null;

	/**
	 * <div>bc_fax_no</div>
	 */
	private String bc_fax_no = null;

	/**
	 * <div>bc_mobile_no</div>
	 */
	private String bc_mobile_no = null;

	/**
	 * <div>tc_contact_name</div>
	 */
	private String tc_contact_name = null;

	/**
	 * <div>tc_designation</div>
	 */
	private String tc_designation = null;

	/**
	 * <div>tc_email</div>
	 */
	private String tc_email = null;
	
	/**
     * <div>tc_email_cc</div>
     */
    private String tc_email_cc = null;

	/**
	 * <div>tc_tel_no</div>
	 */
	private String tc_tel_no = null;

	/**
	 * <div>tc_did_no</div>
	 */
	private String tc_did_no = null;

	/**
	 * <div>tc_fax_no</div>
	 */
	private String tc_fax_no = null;

	/**
	 * <div>tc_mobile_no</div>
	 */
	private String tc_mobile_no = null;
	
	/**
	 * <div>oc_contact_name</div>
	 */
	private String oc_contact_name = null;

	/**
	 * <div>oc_designation</div>
	 */
	private String oc_designation = null;

	/**
	 * <div>oc_email</div>
	 */
	private String oc_email = null;
	
	/**
     * <div>oc_email_cc</div>
     */
    private String oc_email_cc = null;

	/**
	 * <div>oc_tel_no</div>
	 */
	private String oc_tel_no = null;

	/**
	 * <div>oc_did_no</div>
	 */
	private String oc_did_no = null;

	/**
	 * <div>oc_fax_no</div>
	 */
	private String oc_fax_no = null;

	/**
	 * <div>oc_mobile_no</div>
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
	
	/**
	 * <div>ca_country_id</div>
	 */
	private String ca_country_id = null;
	private String ta_country_id = null;
	private String bi_sub_module = null;
	private String ac_sub_module = null;
	private String ca_zip_code = null;
	private String ta_zip_code = null;
	private String ra_zip_code = null;
	private String ba_zip_code = null;
	private String accessType = null;
	private String affiliate_ntt = null;
	private String sales_force_acc_id = null;
	private String gboc_acc_no = null;
	private String others_ref_no = null;
	private String company_sub_category = null;
	private String company_category = null; 
	private String company_bank_info = null;
	private List<LabelValueBean> companyBankInfoList = new ArrayList<LabelValueBean>();
	private String end_user = null;
	private String category_enableFlg = null;
	private String subCategory_enableFlg = null;
	private String bankInfo_enableFlg = null;
	//Add by MiffyAn Start
	private String company_type = null; 
	private String company_enableFlg = null; 
	//Add by MiffyAn End
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
	
	// Account Manager (Primary)
	private String managerPrimary = "";
	// Account Manager (Secondary)
	private String managerSecondary="";
	
	private String listUser="";	
	
	private String mode;
	
	public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

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
	
	public String getGboc_acc_no() {
		return gboc_acc_no;
	}

	public void setGboc_acc_no(String gboc_acc_no) {
		this.gboc_acc_no = gboc_acc_no;
	}

	public String getOthers_ref_no() {
		return others_ref_no;
	}

	public void setOthers_ref_no(String others_ref_no) {
		this.others_ref_no = others_ref_no;
	}

	public String getAffiliate_ntt() {
		return affiliate_ntt;
	}

	public void setAffiliate_ntt(String affiliate_ntt) {
		this.affiliate_ntt = affiliate_ntt;
	}

	public String getSales_force_acc_id() {
		return sales_force_acc_id;
	}

	public void setSales_force_acc_id(String sales_force_acc_id) {
		this.sales_force_acc_id = sales_force_acc_id;
	}

	public String getCa_zip_code() {
		return ca_zip_code;
	}

	public void setCa_zip_code(String ca_zip_code) {
		this.ca_zip_code = ca_zip_code;
	}

	public String getTa_zip_code() {
		return ta_zip_code;
	}

	public void setTa_zip_code(String ta_zip_code) {
		this.ta_zip_code = ta_zip_code;
	}

	public String getRa_zip_code() {
		return ra_zip_code;
	}

	public void setRa_zip_code(String ra_zip_code) {
		this.ra_zip_code = ra_zip_code;
	}

	public String getBa_zip_code() {
		return ba_zip_code;
	}

	public void setBa_zip_code(String ba_zip_code) {
		this.ba_zip_code = ba_zip_code;
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

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}


	public String getBi_sub_module() {
		return bi_sub_module;
	}

	public void setBi_sub_module(String bi_sub_module) {
		this.bi_sub_module = bi_sub_module;
	}

	public String getAc_sub_module() {
		return ac_sub_module;
	}

	public void setAc_sub_module(String ac_sub_module) {
		this.ac_sub_module = ac_sub_module;
	}

	public String getBa_country_id() {
		return ba_country_id;
	}

	public void setBa_country_id(String ba_country_id) {
		this.ba_country_id = ba_country_id;
	}

	public String getRa_country_id() {
		return ra_country_id;
	}

	public void setRa_country_id(String ra_country_id) {
		this.ra_country_id = ra_country_id;
	}

	public String getCa_country_id() {
		return ca_country_id;
	}

	public void setCa_country_id(String ca_country_id) {
		this.ca_country_id = ca_country_id;
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

	public String getId_cust() {
		return id_cust;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
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

	public String getCo_website() {
		return co_website;
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

	public String getCust_acc_no() {
		return cust_acc_no;
	}

	public void setCust_acc_no(String cust_acc_no) {
		this.cust_acc_no = cust_acc_no;
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

	public String getInter_comp() {
		return inter_comp;
	}

	public void setInter_comp(String inter_comp) {
		this.inter_comp = inter_comp;
	}

	public String getPrint_statement() {
		return print_statement;
	}

	public void setPrint_statement(String print_statement) {
		this.print_statement = print_statement;
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

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public void setFromPopup(String fromPopup) {
		this.fromPopup = fromPopup;
	}

	public String getFromPopup() {
		return fromPopup;
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