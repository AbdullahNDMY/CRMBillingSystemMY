/*
 * @(#)E_MIM_US1_2BlogicInput.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.upload.FormFile;

/**
 * InputDTO class.
 * 
 * @author ss054
 */
public class E_MIM_US1_2BlogicInput implements Serializable {
	private FormFile fileName;
	private BillingSystemUserValueObject uvo;
	private Integer row = 0;
	private Integer startIndex = 0;
	private String closingMonth;
	private String closingYear;
	
	public String getClosingMonth() {
		return closingMonth;
	}
	public void setClosingMonth(String closingMonth) {
		this.closingMonth = closingMonth;
	}
	public String getClosingYear() {
		return closingYear;
	}
	public void setClosingYear(String closingYear) {
		this.closingYear = closingYear;
	}
	public FormFile getFileName() {
		return fileName;
	}
	public void setFileName(FormFile fileName) {
		this.fileName = fileName;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
	
	

}