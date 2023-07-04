/*
 * @(#)B_CLSR01Output.java
 *
 *
 */
package nttdm.bsys.b_cls.dto;

import java.io.Serializable;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author ss051
 */
public class B_CLSR01Output implements Serializable {

	private static final long serialVersionUID = -77363358361876312L;
	
	private String hidModuleId;
	private String hidMonth;
	private String hidYear;
	private String hidStatus;
	private List<T_CLOSING> clsInfos;
	private String totalCount;
	private String row;
	private String accessType;
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
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}