/*
 * @(#)A_ADTR01Input.java
 *
 *
 */
package nttdm.bsys.a_adt.dto;

import java.io.Serializable;

/**
 * 入力DTOクラス、E
 * 
 * @author ss042
 */
public class A_ADTR01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7881998416629808838L;

	/**
     * 
     */
	private String dateFrom;

	/**
     * 
     */
	private String dateTo;

	/**
     * 
     */
	private String reference;

	/**
     * 
     */
	private String subModuleID;

	/**
     * 
     */
	private String userName;
	
	/**
	 * 
	 */
	private String actionCbo;
	
	/**
	 * 
	 */
	private Integer startIndex;
	
	private String subModuleIDLen;
	
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
	 * 
	 */
	private Integer rowsPerPage;
	
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
	 * dateFrom を取得すめE
	 * 
	 * @return dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}

	/**
	 * dateFrom を設定すめE
	 * 
	 * @param dateFrom
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * dateTo を取得すめE
	 * 
	 * @return dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}

	/**
	 * dateTo を設定すめE
	 * 
	 * @param dateTo
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * reference を取得すめE
	 * 
	 * @return reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * reference を設定すめE
	 * 
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
     * @return the subModuleIDLen
     */
    public String getSubModuleIDLen() {
        return subModuleIDLen;
    }

    /**
     * @param subModuleIDLen the subModuleIDLen to set
     */
    public void setSubModuleIDLen(String subModuleIDLen) {
        this.subModuleIDLen = subModuleIDLen;
    }

}