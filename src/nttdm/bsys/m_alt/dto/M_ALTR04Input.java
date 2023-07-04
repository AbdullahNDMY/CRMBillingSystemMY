/*
 * @(#)M_ALTR04Input.java
 *
 *
 */
package nttdm.bsys.m_alt.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author ss051
 */
public class M_ALTR04Input implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id_msg;
	private String id_user;
	private BillingSystemUserValueObject uvo;
	
	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getId_msg() {
		return id_msg;
	}

	public void setId_msg(String id_msg) {
		this.id_msg = id_msg;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	
}