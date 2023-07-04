/*
 * @(#)A_ADTFormBean.java
 *
 *
 */
package nttdm.bsys.a_adt.form;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.a_adt.bean.AuditDetail;
import nttdm.bsys.a_adt.bean.AuditHeader;

/**
 * アクションフォームクラス、E
 * 
 * @author ss042
 */
public class A_ADTFormBean extends ValidatorActionFormEx {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1760689326600944119L;
	
	/**
	 * total audit header record
	 */
	private Integer totalAuditHeader;

	/**
	 * rows for audit per page
	 */
	private Integer rowsPerPage = 10;
	
	/**
	 * start index
	 */
	private Integer startIndex = 0;
	
	/**
	 * dateFrom
	 */
	private String dateFrom;

	/**
	 * dateTo
	 */
	private String dateTo;

	/**
	 * reference
	 */
	private String reference;

	/**
	 * subModuleID
	 */
	private String subModuleID;

	/**
	 * action
	 */
	private String actionCbo;
	
	/**
	 * user_name
	 */
	private String userName;	
	
	/**
	 * auditHeaderList
	 */
	private List<AuditHeader> auditHeaderList = new ArrayList<AuditHeader>();	
	
	/**
	 * Selected Audit
	 */
	private String selectedAuditID;
	
	/**
	 * Selected Audit Header
	 */
	private AuditHeader selectedAuditHeader;
	
	/**
	 * Selected Audit Detail List
	 */
	private List<AuditDetail> selectedAuditDetailList;
	
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
	 * Gets the selectedAuditID
	 * @return selectedAuditID
	 */
	public String getSelectedAuditID() {
		return selectedAuditID;
	}

	/**
	 * Sets the selectedAuditID
	 * @param selectedAuditID
	 */
	public void setSelectedAuditID(String selectedAuditID) {
		this.selectedAuditID = selectedAuditID;
	}	

	/**
	 * Gets the rowsPerPage
	 * @return rowsPerPage
	 */
	public Integer getRowsPerPage() {
		return rowsPerPage;
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
	 * Sets the rowsPerPage
	 * @param rowsPerPage
	 */
	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}	

	/**
	 * Gets the startIndex
	 * @return startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * Sets the startIndex
	 * @param startIndex
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Gets the dateFrom
	 * @return dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}

	/**
	 * Sets the dateFrom
	 * @param dateFrom
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * Gets the dateTo
	 * @return dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}

	/**
	 * Sets the dateTo
	 * @param dateTo
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * Gets the reference
	 * @return reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference
	 * @param reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the subModuleID
	 * @return subModuleID
	 */
	public String getSubModuleID() {
		return subModuleID;
	}

	/**
	 * Sets the subModuleID
	 * @param subModuleID
	 */
	public void setSubModuleID(String subModuleID) {
		this.subModuleID = subModuleID;
	}

	/**
     * @return the actionCbo
	 */
    public String getActionCbo() {
        return actionCbo;
	}

	/**
     * @param actionCbo the actionCbo to set
	 */
    public void setActionCbo(String actionCbo) {
        this.actionCbo = actionCbo;
	}

	/**
	 * Gets the userName
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the userName
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the selectedAuditHeader
	 * @return selectedAuditHeader
	 */
	public AuditHeader getSelectedAuditHeader() {
		return selectedAuditHeader;
	}

	/**
	 * Sets the selectedAuditHeader
	 * @param selectedAuditHeader
	 */
	public void setSelectedAuditHeader(AuditHeader selectedAuditHeader) {
		this.selectedAuditHeader = selectedAuditHeader;
	}

	/**
	 * Gets the selectedAuditDetailList
	 * @return selectedAuditDetailList
	 */
	public List<AuditDetail> getSelectedAuditDetailList() {
		return selectedAuditDetailList;
	}

	/**
	 * Sets the selectedAuditDetailList
	 * @param selectedAuditDetailList
	 */
	public void setSelectedAuditDetailList(List<AuditDetail> selectedAuditDetailList) {
		this.selectedAuditDetailList = selectedAuditDetailList;
	}		
}