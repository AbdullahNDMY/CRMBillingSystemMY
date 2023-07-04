
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS03
 * FUNCTION       : Output data BLogic
 * FILE NAME      : M_CSTR07BLogicOutput.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class<br>
 * <br>
 * <ul>
 * <li>
 * <li>OutputDTO class
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1
 */

public class M_CSTR07BLogicOutput implements Serializable {

	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = -907141641797697681L;

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
	
	/**
	 * <div>ca_country_id</div>
	 */
	private String ca_country_id = null;
	
	private String bi_sub_module = null;
	private String ac_sub_module = null;
	private String accessType = null;

	private String salutation = null;
	private String cust_id_no = null;
	private String cust_birth_date = null;
	private String home_tel_no = null;
	private String home_fax_no = null;
	private String sales_force_acc_id = null;
	private String gboc_acc_no = null;
	private String others_ref_no = null;
	private String ra_zip_code;
	private String print_statement;
	private String ba_zip_code;
	private String mobile_no=null;
	// add by Jing Start
	private String company_sub_category = null;
	private String company_category = null; 
	private String company_bank_info = null;
	private List<LabelValueBean> companyBankInfoList = new ArrayList<LabelValueBean>();
	private String end_user = null;
	private String category_enableFlg = null;
	private String subCategory_enableFlg = null;
	private String bankInfo_enableFlg = null;
	private String company_type = null;
	private String company_enableFlg = null;
	// add by Jing End
	private String mode;

	public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCompany_sub_category() {
		return company_sub_category;
	}

	public void setCompany_sub_category(String company_sub_category) {
		this.company_sub_category = company_sub_category;
	}

	public String getCompany_category() {
		return company_category;
	}

	public void setCompany_category(String company_category) {
		this.company_category = company_category;
	}

	public String getCompany_bank_info() {
		return company_bank_info;
	}

	public void setCompany_bank_info(String company_bank_info) {
		this.company_bank_info = company_bank_info;
	}

	public List<LabelValueBean> getCompanyBankInfoList() {
		return companyBankInfoList;
	}

	public void setCompanyBankInfoList(List<LabelValueBean> companyBankInfoList) {
		this.companyBankInfoList = companyBankInfoList;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getRa_zip_code() {
		return ra_zip_code;
	}

	public void setRa_zip_code(String ra_zip_code) {
		this.ra_zip_code = ra_zip_code;
	}



	public String getPrint_statement() {
		return print_statement;
	}

	public void setPrint_statement(String print_statement) {
		this.print_statement = print_statement;
	}

	public String getBa_zip_code() {
		return ba_zip_code;
	}

	public void setBa_zip_code(String ba_zip_code) {
		this.ba_zip_code = ba_zip_code;
	}

	public String getOthers_ref_no() {
		return others_ref_no;
	}

	public void setOthers_ref_no(String others_ref_no) {
		this.others_ref_no = others_ref_no;
	}

	public String getGboc_acc_no() {
		return gboc_acc_no;
	}

	public void setGboc_acc_no(String gboc_acc_no) {
		this.gboc_acc_no = gboc_acc_no;
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