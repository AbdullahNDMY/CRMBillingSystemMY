/*
 * @(#)M_CSTR09BLogicOutput.java
 *
 *
 */
package nttdm.bsys.m_cst.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author ss054
 */
public class M_CSTR09BLogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1373326809235416433L;

	/**
     * 
     */
	private String t;

	/**
	 * t を取得する
	 * 
	 * @return t
	 */
	public String getT() {
		return t;
	}

	/**
	 * t を設定する
	 * 
	 * @param t
	 */
	public void setT(String t) {
		this.t = t;
	}

}