/*
 * @(#)RP_E_MIM_US3_04Output.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US3_04Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8089088227997315726L;

	/**
     * 
     */
	private String example;

	/**
	 * example を取得する
	 * 
	 * @return example
	 */
	public String getExample() {
		return example;
	}

	/**
	 * example を設定する
	 * 
	 * @param example
	 */
	public void setExample(String example) {
		this.example = example;
	}

}