/*
 * @(#)A_ADTR02Output.java
 *
 *
 */
package nttdm.bsys.a_adt.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.a_adt.bean.AuditHeader;
import nttdm.bsys.a_adt.bean.AuditDetail;

/**
 * 出力DTOクラス。
 * 
 * @author ss042
 */
public class A_ADTR02Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3198609086705038735L;

	/**
     * Audit header infor
     */
	private AuditHeader auditHeader;

	/**
	 * Audit detail list
	 */
	private List<AuditDetail> auditDetailList;

	/**
	 * Gets the auditHeader
	 * @return auditHeader
	 */
	public AuditHeader getAuditHeader() {
		return auditHeader;
	}

	/**
	 * Sets the auditHeader
	 * @param auditHeader
	 */
	public void setAuditHeader(AuditHeader auditHeader) {
		this.auditHeader = auditHeader;
	}

	/**
	 * Gets the auditDetailList
	 * @return auditDetailList
	 */
	public List<AuditDetail> getAuditDetailList() {
		return auditDetailList;
	}

	/**
	 * Sets the auditDetailList
	 * @param auditDetailList
	 */
	public void setAuditDetailList(List<AuditDetail> auditDetailList) {
		this.auditDetailList = auditDetailList;
	}	

}