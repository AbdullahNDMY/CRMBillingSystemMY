/*
 * @(#)RP_E_MIM_US2_02Input.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;
import org.apache.struts.upload.FormFile;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US2_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8327696511323346523L;

	/**
     * 
     */
	private String example;

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
     * 
     */
	private BillingSystemUserValueObject uvo;

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

}