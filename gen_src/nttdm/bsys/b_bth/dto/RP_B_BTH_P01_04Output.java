/*
 * @(#)RP_B_BTH_P01_04Output.java
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
public class RP_B_BTH_P01_04Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6527562690657036303L;

	/**
     * 
     */
	private String allIdRefs;

	/**
	 * allIdRefs を取得する
	 * 
	 * @return allIdRefs
	 */
	public String getAllIdRefs() {
		return allIdRefs;
	}

	/**
	 * allIdRefs を設定する
	 * 
	 * @param allIdRefs
	 */
	public void setAllIdRefs(String allIdRefs) {
		this.allIdRefs = allIdRefs;
	}

}