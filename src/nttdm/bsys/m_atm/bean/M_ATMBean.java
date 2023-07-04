package nttdm.bsys.m_atm.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class M_ATMBean  extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
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
	private String id_role;
	private String bean_id;
	private String tfr_name;
	private Integer auditID;
	
	public String getTfr_name() {
		return tfr_name;
	}
	public void setTfr_name(String tfr_name) {
		this.tfr_name = tfr_name;
	}
	
	public String getBean_id() {
		return bean_id;
	}
	public void setBean_id(String bean_id) {
		this.bean_id = bean_id;
	}
	public String getId_role() {
		return id_role;
	}
	public void setId_role(String id_role) {
		this.id_role = id_role;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
