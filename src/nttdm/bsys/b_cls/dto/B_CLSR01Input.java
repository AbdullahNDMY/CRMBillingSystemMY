/*
 * @(#)M_JNMR01Input.java
 *
 *
 */
package nttdm.bsys.b_cls.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;


public class B_CLSR01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6016366325152242755L;

	private BillingSystemUserValueObject uvo;
	private String moduleId;
	private String month;
	private String year;
	private String status;
	private String hidModuleId;
	private String hidMonth;
	private String hidYear;
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHidModuleId() {
		return hidModuleId;
	}
	public void setHidModuleId(String hidModuleId) {
		this.hidModuleId = hidModuleId;
	}
	public String getHidMonth() {
		return hidMonth;
	}
	public void setHidMonth(String hidMonth) {
		this.hidMonth = hidMonth;
	}
	public String getHidYear() {
		return hidYear;
	}
	public void setHidYear(String hidYear) {
		this.hidYear = hidYear;
	}
	public String getHidStatus() {
		return hidStatus;
	}
	public void setHidStatus(String hidStatus) {
		this.hidStatus = hidStatus;
	}
	public String getClickEvent() {
		return clickEvent;
	}
	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	private String hidStatus;
	private String clickEvent;
	private String row;
	private String startIndex;
	
}