/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST
 * FUNCTION       : Define bean Custom 
 * FILE NAME      : CustomBean.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.bean;

import java.io.Serializable;
/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 */
public class CustomBean   implements Serializable{
	
	private String set_value;
	private String set_seq;
	private String id_set;
	private String access_type;
	
	public String getSet_value() {
		return set_value;
	}
	public void setSet_value(String set_value) {
		this.set_value = set_value;
	}
	public String getSet_seq() {
		return set_seq;
	}
	public void setSet_seq(String set_seq) {
		this.set_seq = set_seq;
	}
	public String getId_set() {
		return id_set;
	}
	public void setId_set(String id_set) {
		this.id_set = id_set;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	
}
