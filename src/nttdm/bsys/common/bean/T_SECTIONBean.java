package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class T_SECTIONBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private String sectgrp;
	private String id_ref;
	private String section_no;
	private String value;
	private String appr_by;
	private String date_appr;
	private String appr_status;
	private String is_revised;
	private String remarks;
	private String date_created;
	private String date_updated;
	private String id_login;
	public String getSectgrp() {
		return sectgrp;
	}
	public void setSectgrp(String sectgrp) {
		this.sectgrp = sectgrp;
	}
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getAppr_by() {
		return appr_by;
	}
	public void setAppr_by(String appr_by) {
		this.appr_by = appr_by;
	}
	public String getDate_appr() {
		return date_appr;
	}
	public void setDate_appr(String date_appr) {
		this.date_appr = date_appr;
	}
	public String getAppr_status() {
		return appr_status;
	}
	public void setAppr_status(String appr_status) {
		this.appr_status = appr_status;
	}
	public String getIs_revised() {
		return is_revised;
	}
	public void setIs_revised(String is_revised) {
		this.is_revised = is_revised;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}

}
