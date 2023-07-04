/*
 * @(#)M_CSTR07BLogicInput.java
 *
 *
 */
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author ss054
 */
public class M_CSTR07BLogicInput implements Serializable {

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

}