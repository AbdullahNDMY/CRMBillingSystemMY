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
	 * idRef を�?�得�?�る
	 * 
	 * @return idRef
	 */
	public String getIdRef() {
		return idRef;
	}

	/**
	 * idRef を設定�?�る
	 * 
	 * @param idRef
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	/**
	 * uvo を�?�得�?�る
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo を設定�?�る
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

}