/*
 * @(#)A_USR_S02IO.java
 *
 *
 */
package nttdm.bsys.a_usr.dto;

import java.io.Serializable;
import java.util.*;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
/**
 * InputDTO class.
 * 
 * @author hungtm
 */
public class A_USR_S02IO implements Serializable {
	private static final long serialVersionUID = 1L;
	//input
	private String mode;
	private String pageEvent;
	private String isSaveSuccess;
	private BillingSystemUserValueObject uvo;
	private String idUser;
	//output
	private List<Department> listDepartment;
	private List<Division> listDivision;
	private List<Role> listRole;
	private List<SubModule> listSubModule;
	private List<Module> listModule;
	private String accessType;
	//input & output
	private User user;
	private List<UserAccess> listUserAccess;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPageEvent() {
		return pageEvent;
	}
	public void setPageEvent(String pageEvent) {
		this.pageEvent = pageEvent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserAccess> getListUserAccess() {
		return listUserAccess;
	}
	public void setListUserAccess(List<UserAccess> listUserAccess) {
		this.listUserAccess = listUserAccess;
	}
	public List<Department> getListDepartment() {
		return listDepartment;
	}
	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}
	public List<Division> getListDivision() {
		return listDivision;
	}
	public void setListDivision(List<Division> listDivision) {
		this.listDivision = listDivision;
	}
	public List<Role> getListRole() {
		return listRole;
	}
	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
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
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public List<SubModule> getListSubModule() {
		return listSubModule;
	}
	public void setListSubModule(List<SubModule> listSubModule) {
		this.listSubModule = listSubModule;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public List<Module> getListModule() {
		return listModule;
	}
	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}
	public String getIsSaveSuccess() {
		return isSaveSuccess;
	}
	public void setIsSaveSuccess(String isSaveSuccess) {
		this.isSaveSuccess = isSaveSuccess;
	}

	
	
}