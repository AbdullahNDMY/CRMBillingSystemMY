/*
 * @(#)B_CLSFormBean.java
 *
 *
 */
package nttdm.bsys.b_cls.bean;

import java.util.List;
import nttdm.bsys.b_cls.dto.T_CLOSING;
import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class B_CLSFormBean extends ValidatorActionFormEx {

	private static final long serialVersionUID = -7466401838917442359L;
	private String moduleId;
	private String month;
	private String year;
	private String status;
	private String hidModuleId;
	private String hidMonth;
	private String hidYear;
	private String hidStatus;
	private String clickEvent;
	private List<T_CLOSING> clsInfos;
	private String totalCount;
	private String row;
	private String startIndex;
	private String accessType;
	
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
	public List<T_CLOSING> getClsInfos() {
		return clsInfos;
	}
	public void setClsInfos(List<T_CLOSING> clsInfos) {
		this.clsInfos = clsInfos;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
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
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
}