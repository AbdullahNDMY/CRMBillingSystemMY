/*
 * @(#)RP_E_MIM_US3_04Input.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US3_04Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5259435004821198608L;

	/**
     * 
     */
	private String example;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private String month;

	/**
     * 
     */
	private String year;

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

	/**
	 * uvo を取得する
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo を設定する
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * month を取得する
	 * 
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * month を設定する
	 * 
	 * @param month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * year を取得する
	 * 
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * year を設定する
	 * 
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}

}