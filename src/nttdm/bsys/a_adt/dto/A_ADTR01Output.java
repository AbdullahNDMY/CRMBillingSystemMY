/*
 * @(#)A_ADTR01Output.java
 *
 *
 */
package nttdm.bsys.a_adt.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.a_adt.bean.AuditHeader;

/**
 * Audit Header DTO
 * 
 * @author locnt
 */
public class A_ADTR01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7204451347926472754L;

	/**
     * Audit header list
     */
	private List<AuditHeader> auditHeaderList=new ArrayList<AuditHeader>();
	
	/**
	 * Total of audit header
	 */
	private Integer totalAuditHeader;
	
	private Integer rowsPerPage;

	/**
	 * Gets the auditHeaderList
	 * @return auditHeaderList
	 */
	public List<AuditHeader> getAuditHeaderList() {
		return auditHeaderList;
	}

	/**
	 * Sets the auditHeaderList
	 * @param auditHeaderList
	 */
	public void setAuditHeaderList(List<AuditHeader> auditHeaderList) {
		this.auditHeaderList = auditHeaderList;
	}

	/**
	 * Gets the totalAuditHeader
	 * @return totalAuditHeader
	 */
	public Integer getTotalAuditHeader() {
		return totalAuditHeader;
	}

	/**
	 * Sets the totalAuditHeader
	 * @param totalAuditHeader
	 */
	public void setTotalAuditHeader(Integer totalAuditHeader) {
		this.totalAuditHeader = totalAuditHeader;
	}

	/**
	 * Gets the rowsPerPage
	 * @return rowsPerPage
	 */
	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	/**
	 * Sets the rowsPerPage
	 * @param rowsPerPage
	 */
	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}	

}