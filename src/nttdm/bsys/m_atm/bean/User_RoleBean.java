package nttdm.bsys.m_atm.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class User_RoleBean  extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private String role_name;
	private String id_user;
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	
}
