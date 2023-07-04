/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : C_CMN
 * SERVICE NAME   : C_CMN003
 * FUNCTION       : Get data for sub module
 * FILE NAME      : SubModule.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.c_cmn003.bean;
/**
 * @author NTT Data Vietnam	
 * Action processes business from C_CMN003 form
 * @param <P>
 */
public class SubModule {
	private static final long serialVersionUID = -5538432094237818425L;
	private String id_sub_module;
	private String id_module;
	private String sub_mod_name;
	private String sub_mod_desc;
	private String date_created;
	private String date_updated;
	private String id_login;
	private String order_seq;
	private String is_display;
	private String path_module;
	private String access_type;
	
	public String getPath_module() {
		return path_module;
	}
	public void setPath_module(String path_module) {
		this.path_module = path_module;
	}
	public String getId_sub_module() {
		return id_sub_module;
	}
	public void setId_sub_module(String id_sub_module) {
		this.id_sub_module = id_sub_module;
	}
	public String getId_module() {
		return id_module;
	}
	public void setId_module(String id_module) {
		this.id_module = id_module;
	}
	public String getSub_mod_name() {
		return sub_mod_name;
	}
	public void setSub_mod_name(String sub_mod_name) {
		this.sub_mod_name = sub_mod_name;
	}
	public String getSub_mod_desc() {
		return sub_mod_desc;
	}
	public void setSub_mod_desc(String sub_mod_desc) {
		this.sub_mod_desc = sub_mod_desc;
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
	public String getOrder_seq() {
		return order_seq;
	}
	public void setOrder_seq(String order_seq) {
		this.order_seq = order_seq;
	}
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	/**
	 * @param access_type the access_type to set
	 */
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	/**
	 * @return the access_type
	 */
	public String getAccess_type() {
		return access_type;
	}

}
