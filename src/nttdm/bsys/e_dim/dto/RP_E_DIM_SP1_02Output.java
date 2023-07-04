/*
 * @(#)RP_E_DIM_SP1_02Output.java
 *
 *
 */
package nttdm.bsys.e_dim.dto;

import java.io.Serializable;
import org.apache.struts.upload.FormFile;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_DIM_SP1_02Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5267543028695037460L;

	/**
     * 
     */
	private FormFile formFile;

	/**
     * 
     */
	private String month;

	/**
     * 
     */
	private String year;

	/**
	 * formFile を取得する
	 * 
	 * @return formFile
	 */
	public FormFile getFormFile() {
		return formFile;
	}

	/**
	 * formFile を設定する
	 * 
	 * @param formFile
	 */
	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
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