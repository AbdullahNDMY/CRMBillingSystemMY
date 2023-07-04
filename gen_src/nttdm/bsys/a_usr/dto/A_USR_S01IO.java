/*
 * @(#)A_USR_S01IO.java
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
public class A_USR_S01IO implements Serializable {

	private static final long serialVersionUID = 1L;
	//input
	private InputSearch inputSearch;
	private String searchBy;
	private BillingSystemUserValueObject uvo;
	//output
	private List<Department> listDepartment;
	private List<Division> listDivision;
	private String accessType;
	private List<User> listUser;
	private String row;
	private String totalCount;
	//input & output
	private InputSearch firstInputSearch;
	private String startIndex;
	
	private List<Role> listRole;
	private List<String> listdispEmail;

    public List<Role> getListRole() {
        return listRole;
    }
    public void setListRole(List<Role> listRole) {
        this.listRole = listRole;
    }
    public InputSearch getInputSearch() {
		return inputSearch;
	}
	public void setInputSearch(InputSearch inputSearch) {
		this.inputSearch = inputSearch;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
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
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public List<User> getListUser() {
		return listUser;
	}
	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
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
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public InputSearch getFirstInputSearch() {
		return firstInputSearch;
	}
	public void setFirstInputSearch(InputSearch firstInputSearch) {
		this.firstInputSearch = firstInputSearch;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public void setListdispEmail(List<String> listdispEmail) {
		this.listdispEmail = listdispEmail;
	}
	public List<String> getListdispEmail() {
		return listdispEmail;
	}
	
}