/*
 * @(#)A_USR_S04IO.java
 *
 */
package nttdm.bsys.a_usr.dto;

import java.io.Serializable;
import java.util.*;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
/**
 * DTO class.
 * @author i-yang
 */
public class A_USR_S04IO implements Serializable {
	private static final long serialVersionUID = 1L;
	//input
	private String mode;
	private String pageEvent;	
	private BillingSystemUserValueObject uvo;
	private String idUser;
	//output
	List<Role> listRole;
	private List<SubModule> listSubModule;
	private List<Module> listModule;
	private String accessType;
	
	//input & output
	String choosed;
	List<RoleAccess> listRoleAccess;
	
	
	public String getChoosed() {
		return choosed;
	}
	public void setChoosed(String choosed) {
		this.choosed = choosed;
	}
	
	public String getPageEvent() {
		return pageEvent;
	}
	public void setPageEvent(String pageEvent) {
		this.pageEvent = pageEvent;
	}	
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getIdLogin() {
		String idLogin ="";
		if(uvo != null){
			idLogin = uvo.getId_user();
		}
		return idLogin;
	}
	public String getAccessType(String module, String subModule){
		String accessType = "";
		if(uvo != null){
			accessType = uvo.getUserAccessInfo(module, subModule).getAccess_type();
		}
		return accessType;
	}
	
	/**
	 * @return the listRoleAccess
	 */
	public List<RoleAccess> getListRoleAccess() {
		return listRoleAccess;
	}
	/**
	 * @param listRoleAccess the listRoleAccess to set
	 */
	public void setListRoleAccess(List<RoleAccess> listRoleAccess) {
		this.listRoleAccess = listRoleAccess;
	}
	/**
	 * @return the listRole
	 */
	public List<Role> getListRole() {
		return listRole;
	}
	/**
	 * @param listRole the listRole to set
	 */
	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**
	 * @return the listSubModule
	 */
	public List<SubModule> getListSubModule() {
		return listSubModule;
	}
	/**
	 * @param listSubModule the listSubModule to set
	 */
	public void setListSubModule(List<SubModule> listSubModule) {
		this.listSubModule = listSubModule;
	}
	/**
	 * @return the listModule
	 */
	public List<Module> getListModule() {
		return listModule;
	}
	/**
	 * @param listModule the listModule to set
	 */
	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}
	/**
	 * @return the accessType
	 */
	public String getAccessType() {
		return accessType;
	}
	/**
	 * @param accessType the accessType to set
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}	
}