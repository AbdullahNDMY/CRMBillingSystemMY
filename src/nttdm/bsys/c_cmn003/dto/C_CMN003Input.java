/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : C_CMN
 * SERVICE NAME   : C_CMN003
 * FUNCTION       : Get id_user and id_module
 * FILE NAME      : C_CMN003Input.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.c_cmn003.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
/**
 * @author NTT Data Vietnam	
 * Action processes business 
 * @param <P>
 */
public class C_CMN003Input implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;	
	private String id_user;
	private BillingSystemUserValueObject uvo;
	private String id_module;
	
	public String getId_module() {
		return id_module;
	}
	public void setId_module(String id_module) {
		this.id_module = id_module;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	
}
