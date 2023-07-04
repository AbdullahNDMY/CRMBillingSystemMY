/*
 * @(#)M_CSTR00BLogicInput.java
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
public class M_CSTR00BLogicInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7432627582800130416L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvoObject;

	/**
	 * uvoObjec を取得する
	 * 
	 * @return uvoObjec
	 */
	public BillingSystemUserValueObject getUvoObject() {
		return uvoObject;
	}

	/**
	 * uvoObjec を設定する
	 * 
	 * @param uvoObjec
	 */
	public void setUvoObject(BillingSystemUserValueObject uvoObjec) {
		this.uvoObject = uvoObjec;
	}

}