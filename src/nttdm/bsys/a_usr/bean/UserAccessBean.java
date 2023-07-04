package nttdm.bsys.a_usr.bean;

import java.util.Date;

/**
 * <div>
 *  Describe user access information 
 * </div>
 * @author Phu.Nguyen
 */
public class UserAccessBean {
	/**
	 * <div>id_module</div>
	 */
	private String id_module;
	/**
	 * <div>mod_name</div>
	 */
	private String mod_name;
	/**
	 * <div>id_sub_module</div>
	 */
	private String id_sub_module;
	/**
	 * <div>sub_mod_name</div>
	 */
	private String sub_mod_name;
	/**
	 * <div>access_type</div>
	 */
	private String access_type;
	/**
	 * <div>id_login</div> 
	 */
	private String id_login;
	/**
	 * <div>id_user</div>
	 */
	private String id_user = null;
	/**
	 * <div>date_updated</div>
	 */
	private Date date_updated;

	public Date getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
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

	public String getAccess_type() {
		return access_type;
	}

	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	public String getId_module() {
		return id_module;
	}

	public void setId_module(String id_module) {
		this.id_module = id_module;
	}

	public String getMod_name() {
		return mod_name;
	}

	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}

	public String getId_sub_module() {
		return id_sub_module;
	}

	public void setId_sub_module(String id_sub_module) {
		this.id_sub_module = id_sub_module;
	}

	public String getSub_mod_name() {
		return sub_mod_name;
	}

	public void setSub_mod_name(String sub_mod_name) {
		this.sub_mod_name = sub_mod_name;
	}
	
	
}
