/*
 * @(#)A_ADTR02Input.java
 *
 *
 */
package nttdm.bsys.a_adt.dto;

import java.io.Serializable;

/**
 * 入力DTOクラス。
 * 
 * @author ss042
 */
public class A_ADTR02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3810491990648978490L;

	/**
	 * Selected Audit ID
	 */
	private String selectedAuditID;

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
}