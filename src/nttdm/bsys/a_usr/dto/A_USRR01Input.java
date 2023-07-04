/*
 * @(#)A_USRR01Input.java
 * $Id$
 */
package nttdm.bsys.a_usr.dto;

import java.io.Serializable;
/**
 * <div>
 * Input class
 * </div>
 * @author Phu.Nguyen
 *
 */
public class A_USRR01Input implements Serializable {



	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * <div>uvo</div>
	 */
	private nttdm.bsys.common.fw.BillingSystemUserValueObject uvo = null;


	/**
	 * <div>serial id</div>
	 */
	private static final long serialVersionUID = 8187248950881400374L;

	/**
	 * <div>id_user</div>
	 */
	private String id_user = null;

	/**
	 * <div>user_name</div>
	 */
	private String user_name = null;
	/**
	 * <div>id_div</div>
	 */
	private String id_div = null;

	/**
	 * <div>id_dept</div>
	 */
	private String id_dept = null;
	/**
	 * <div>row</div>
	 */
	private String row = null;
	/**
	 * <div>startIndex</div>
	 */
	private String startIndex = null;
	/**
	 * <div>id_user_search</div>
	 */
	private String id_user_search = null;

	/**
	 * <div>user_name_search</div>
	 */
	private String user_name_search = null;

	/**
	 * <div>id_div_search</div>
	 */
	private String id_div_search = null;

	/**
	 * <div>id_dept_search</div>
	 */
	private String id_dept_search = null;
	/**
	 * <div>clickEvent</div>
	 */
	private String clickEvent = null;
	
	public String getClickEvent() {
		return clickEvent;
	}
	
	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}
	
	public String getId_user_search() {
		return id_user_search;
	}
	
	public void setId_user_search(String id_user_search) {
		this.id_user_search = id_user_search;
	}
	
	public String getUser_name_search() {
		return user_name_search;
	}
	
	public void setUser_name_search(String user_name_search) {
		this.user_name_search = user_name_search;
	}
	
	public String getId_div_search() {
		return id_div_search;
	}
	
	public void setId_div_search(String id_div_search) {
		this.id_div_search = id_div_search;
	}
	
	public String getId_dept_search() {
		return id_dept_search;
	}
	
	public void setId_dept_search(String id_dept_search) {
		this.id_dept_search = id_dept_search;
	}
	
	public String getId_user() {
		return this.id_user;
	}

	
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getUser_name() {
		return this.user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getId_div() {
		return this.id_div;
	}

	public void setId_div(String id_div) {
		this.id_div = id_div;
	}

	public String getId_dept() {
		return this.id_dept;
	}

	public void setId_dept(String id_dept) {
		this.id_dept = id_dept;
	}

	public String getRow() {
		return row;
	}
	
	public void setRow(String row) {
		this.row = row;
	}
	
	public String getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public nttdm.bsys.common.fw.BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(nttdm.bsys.common.fw.BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
}