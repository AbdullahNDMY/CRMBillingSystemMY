/*
 * @(#)Q_QCSR01Input.java
 *
 *
 */
package nttdm.bsys.q_qcs.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.q_qcs.bean.QCSHeader;

/**
 * InputDTO class.
 * 
 * @author ss051
 */
public class Q_QCSR01Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8570053932379117881L;
	private String row;
	private String totalCount;
	private List<QCSHeader> qcsInfos=null;
	private String permission;
	
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public List<QCSHeader> getQcsInfos() {
		return qcsInfos;
	}
	public void setQcsInfos(List<QCSHeader> qcsInfos) {
		this.qcsInfos = qcsInfos;
	}
	

}