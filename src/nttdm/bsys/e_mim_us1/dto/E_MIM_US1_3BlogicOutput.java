/*
 * @(#)E_MIM_US1_3BlogicOutput.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author ss054
 */
public class E_MIM_US1_3BlogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6331810366918088520L;

	/**
     * 
     */
	private String tempt;

	/**
	 * tempt を取得する
	 * 
	 * @return tempt
	 */
	public String getTempt() {
		return tempt;
	}

	/**
	 * tempt を設定する
	 * 
	 * @param tempt
	 */
	public void setTempt(String tempt) {
		this.tempt = tempt;
	}

}