package nttdm.bsys.m_atm.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class M_ATMInput implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private BillingSystemUserValueObject uvo;
	private String id_tfr_user;
	private String id_screen;
	private String section_no;
	private String section_desc;
	private String role_name;
	private String eff_date_from;
	private String eff_date_to;
	private String id_user;
	private String id_login;
	private String user_name;
	private String update_mode;
	private String detail_inf;
	private Integer auditID;
	
	public String getDetail_inf() {
		return detail_inf;
	}
	public void setDetail_inf(String detail_inf) {
		this.detail_inf = detail_inf;
	}
	public String getUpdate_mode() {
		return update_mode;
	}
	public void setUpdate_mode(String update_mode) {
		this.update_mode = update_mode;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getId_tfr_user() {
		return id_tfr_user;
	}
	public void setId_tfr_user(String id_tfr_user) {
		this.id_tfr_user = id_tfr_user;
	}
	public String getId_screen() {
		return id_screen;
	}
	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getSection_desc() {
		return section_desc;
	}
	public void setSection_desc(String section_desc) {
		this.section_desc = section_desc;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getEff_date_from() {
		return eff_date_from;
	}
	public void setEff_date_from(String eff_date_from) {
		this.eff_date_from = eff_date_from;
	}
	public String getEff_date_to() {
		return eff_date_to;
	}
	public void setEff_date_to(String eff_date_to) {
		this.eff_date_to = eff_date_to;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * Gets the auditID
	 * @return auditID
	 */
	public Integer getAuditID() {
		return auditID;
	}
	/**
	 * Sets the auditID
	 * @param auditID
	 */
	public void setAuditID(Integer auditID) {
		this.auditID = auditID;
	}
	
}
