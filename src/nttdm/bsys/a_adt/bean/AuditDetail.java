package nttdm.bsys.a_adt.bean;

import java.io.Serializable;

public class AuditDetail implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8756718304234451256L;	
	
	/**
	 * id_audit
	 */
	private Long auditID;
	
	/**
	 * audit_seq
	 */
	private Long auditSeq;
	
	/**
	 * table_name
	 */
	private String tableName;
	
	/**
	 * at_field
	 */
	private String atField;
	
	/**
	 * old_data
	 */
	private String oldData;
	
	/**
	 * new_data
	 */
	private String newData;	

	/**
	 * Gets the auditID
	 * @return auditID
	 */
	public Long getAuditID() {
		return auditID;
	}

	/**
	 * Sets the auditID
	 * @param auditID
	 */
	public void setAuditID(Long auditID) {
		this.auditID = auditID;
	}

	/**
	 * Gets the auditSeq
	 * @return auditSeq
	 */
	public Long getAuditSeq() {
		return auditSeq;
	}

	/**
	 * Sets the auditSeq
	 * @param auditSeq
	 */
	public void setAuditSeq(Long auditSeq) {
		this.auditSeq = auditSeq;
	}

	/**
	 * Gets the tableName
	 * @return tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the tableName
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the atField
	 * @return atField
	 */
	public String getAtField() {
		return atField;
	}

	/**
	 * Sets the atField
	 * @param atField
	 */
	public void setAtField(String atField) {
		this.atField = atField;
	}

	/**
	 * Gets the oldData
	 * @return oldData
	 */
	public String getOldData() {
		return oldData;
	}

	/**
	 * Sets the oldData
	 * @param oldData
	 */
	public void setOldData(String oldData) {
		this.oldData = oldData;
	}

	/**
	 * Gets the newData
	 * @return newData
	 */
	public String getNewData() {
		return newData;
	}

	/**
	 * Sets the newData
	 * @param newData
	 */
	public void setNewData(String newData) {
		this.newData = newData;
	}
}
