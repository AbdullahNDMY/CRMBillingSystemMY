/**
 * @(#)BillingSystemUserValueObject.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.fw;

import java.util.List;

import nttdm.bsys.c_cmn001.bean.UserAccess;
import jp.terasoluna.fw.web.UserValueObject;

/**
 * the class of UserAccessInfo
 * 
 * @author p-chengh
 */
public class BillingSystemUserValueObject extends UserValueObject {

	private static final long serialVersionUID = -8378462307217597209L;

	/**
	 * id_user
	 */
	private String id_user = null;
	/**
	 * user_name
	 */
	private String user_name = null;
	/**
	 * password
	 */
	private String password = null;
	/**
	 * is_deleted
	 */
	private String is_deleted = null;
	/**
	 * user_status
	 */
	private String user_status = null;
	/**
	 * last_pwd_change
	 */
	private String last_pwd_change = null;
	/**
	 * is_need_change_password
	 */
	private String is_need_change_password = null;
	/**
	 * user_access
	 */
	private List<UserAccess> user_access = null;

	/**
	 * author: hieunguyen get audit_seq for G_ADT_P01
	 */
	private int audit_seq = 0;
	/**
	 * author: hieunguyen global_running_number for G_ADT_P01
	 */
	private static String global_running_number = null;

	private String SESSION_DIRECTORY = " ";
	
	public String getSESSION_DIRECTORY() {
		return SESSION_DIRECTORY;
	}

	public void setSESSION_DIRECTORY(String sESSION_DIRECTORY) {
		SESSION_DIRECTORY = sESSION_DIRECTORY;
	}

	/**
	 * get audit_seq
	 * 
	 * @return audit_seq
	 */
	public String getAudit_Seq() {
		audit_seq++;
		return Integer.toString(audit_seq);
	}

	/**
	 * get global_running_number
	 * 
	 * @return global_running_number
	 */
	public String getGlobal_running_number() {
		return global_running_number;
	}

	/**
	 * set global_running_number
	 * 
	 * @param global_running_number
	 */
	public void setGlobal_running_number(String global_running_number) {
		BillingSystemUserValueObject.global_running_number = global_running_number;
	}

	/**
	 * get is_deleted
	 * 
	 * @return is_deleted
	 */
	public String getIs_deleted() {
		return is_deleted;
	}

	/**
	 * set is_deleted
	 * 
	 * @param is_deleted
	 */
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	/**
	 * get user_status
	 * 
	 * @return user_status
	 */
	public String getUser_status() {
		return user_status;
	}

	/**
	 * set user_status
	 * 
	 * @param user_status
	 */
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	/**
	 * get id_user
	 * 
	 * @return id_user
	 */
	public String getId_user() {
		return id_user;
	}

	/**
	 * set id_user
	 * 
	 * @param id_user
	 */
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	/**
	 * set password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * get password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set user_name
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * get user_name
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * set is_need_change_password
	 * 
	 * @param is_need_change_password
	 */
	public void setIs_need_change_password(String is_need_change_password) {
		this.is_need_change_password = is_need_change_password;
	}

	/**
	 * get is_need_change_password
	 * 
	 * @return is_need_change_password
	 */
	public String getIs_need_change_password() {
		return is_need_change_password;
	}

	/**
	 * set last_pwd_change
	 * 
	 * @param last_pwd_change
	 */
	public void setLast_pwd_change(String last_pwd_change) {
		this.last_pwd_change = last_pwd_change;
	}

	/**
	 * get last_pwd_change
	 * 
	 * @return last_pwd_change
	 */
	public String getLast_pwd_change() {
		return last_pwd_change;
	}

	/**
	 * get userAccess info
	 * 
	 * @return List<UserAccess>
	 */
	public List<UserAccess> getUser_access() {
		return user_access;
	}

	/**
	 * set userAccess info
	 * 
	 * @param List
	 *            <UserAccess>
	 */
	public void setUser_access(List<UserAccess> user_access) {
		this.user_access = user_access;
	}

	/**
	 * get the User Access Info by id_module and id_sub_module
	 * 
	 * @param String
	 *            id_module
	 * @param String
	 *            id_sub_module
	 * 
	 * @return UserAccess
	 */
	public UserAccess getUserAccessInfo(String id_module, String id_sub_module) {
		if (user_access == null || user_access.size() == 0) {
			return null;
		}
		for (int i = 0; i < user_access.size(); i++) {
			UserAccess obj = user_access.get(i);
			if (obj.getId_module().equals(id_module)
					&& obj.getId_sub_module().equals(id_sub_module)) {
				return user_access.get(i);
			}
		}
		return null;
	}
}
