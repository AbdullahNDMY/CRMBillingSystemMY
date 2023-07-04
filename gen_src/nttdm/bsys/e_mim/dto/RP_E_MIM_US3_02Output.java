/*
 * @(#)RP_E_MIM_US3_02Output.java
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
public class RP_E_MIM_US3_02Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 464702047710399048L;

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