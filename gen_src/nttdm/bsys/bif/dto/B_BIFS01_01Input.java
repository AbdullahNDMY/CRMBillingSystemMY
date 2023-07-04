/*
 * @(#)B_BIFS01_01Input.java
 *
 *
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * 入力DTOクラス。
 * 
 * @author duongnld
 */
public class B_BIFS01_01Input implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7672790483199831465L;
	
	private String idRef;
	private BillingSystemUserValueObject uvo;

	/**
	 * @param idRef the idRef to set
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	/**
	 * @return the idRef
	 */
	public String getIdRef() {
		return idRef;
	}

	/**
	 * @param uvo the uvo to set
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * @return the uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

}