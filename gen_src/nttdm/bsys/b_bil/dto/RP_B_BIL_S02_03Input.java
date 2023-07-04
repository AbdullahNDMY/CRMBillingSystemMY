/*
 * @(#)RP_B_BIL_S02_03Input.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9134245938124004680L;

	/**
     * 
     */
	private String idRef;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
	 * idRef ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return idRef
	 */
	public String getIdRef() {
		return idRef;
	}

	/**
	 * idRef ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param idRef
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	/**
	 * uvo ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

}