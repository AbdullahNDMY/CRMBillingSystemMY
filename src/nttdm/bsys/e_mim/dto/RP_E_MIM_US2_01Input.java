/*
 * @(#)RP_E_MIM_US2_01Input.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US2_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6971303330869998556L;

	/**
     * 
     */
	private String example;

	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;

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
	 * row を取得する
	 * 
	 * @return row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * row を設定する
	 * 
	 * @param row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * startIndex を取得する
	 * 
	 * @return startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * startIndex を設定する
	 * 
	 * @param startIndex
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
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