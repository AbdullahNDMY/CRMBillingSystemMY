/*
 * @(#)M_CSTR02Input.java
 * $Id$
 */
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * parameterClass: id=SELECT.M_CST.SQL003
 * 
 * @author Duyen.Vo
 */
public class M_CSTR02Input implements Serializable {

	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = -2059850015859532263L;

	/**
	 * <div>id_cust</div>
	 */
	private String id_cust = null;
	
	/**
	 * <div>mode</div> 
	 */
	private String mode = null;
	
	BillingSystemUserValueObject uvoObject = null;
	
	private String previous;
	
	private String fromPopup;

	public BillingSystemUserValueObject getUvoObject() {
		return uvoObject;
	}

	public void setUvoObject(BillingSystemUserValueObject uvoObject) {
		this.uvoObject = uvoObject;
	}

	public String getId_cust() {
		return this.id_cust;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public void setFromPopup(String fromPopup) {
		this.fromPopup = fromPopup;
	}

	public String getFromPopup() {
		return fromPopup;
	}

}