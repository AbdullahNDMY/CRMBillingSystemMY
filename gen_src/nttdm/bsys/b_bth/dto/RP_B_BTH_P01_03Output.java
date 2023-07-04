/*
 * @(#)RP_B_BTH_P01_03Output.java
 *
 *
 */
package nttdm.bsys.b_bth.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BTH_P01_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9059641224603217702L;

	/**
     * 
     */
	private String[] idRefs;

	/**
	 * idRefs を取得する
	 * 
	 * @return idRefs
	 */
	public String[] getIdRefs() {
		return idRefs;
	}

	/**
	 * idRefs を設定する
	 * 
	 * @param idRefs
	 */
	public void setIdRefs(String[] idRefs) {
		this.idRefs = idRefs;
	}

}