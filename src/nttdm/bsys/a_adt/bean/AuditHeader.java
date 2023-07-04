package nttdm.bsys.a_adt.bean;

import java.io.Serializable;

/**
 * Audit Trail Header Bean
 * 
 * @author locnt
 */
public class AuditHeader implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6668860653138624325L;
	
	/**
	 * index
	 */
	private Long index;
	
	/**
	 * id_audit
	 */
	private Long auditID;
	
	/**
	 * id_user
	 */
	private String userID;
	
	/**
	 * user_name
	 */
	private String userName;
	
	/**
	 * id_module
	 */
	private String moduleID;
	
	/**
	 * mod_name
	 */
	private String moduleName;
	
	/**
	 * id_sub_module
	 */
	private String subModuleID;
	
	/**
	 * sub_mod_name
	 */
	private String subModuleName;
	
	/**
	 * reference
	 */
	private String reference;
	
	/**
	 * status
	 */
	private String status;
	
	/**
	 * action
	 */
	private String action;
	
	/**
	 * date_created
	 */
	private String createdDate;

	/**
	 * Gets the index
	 * @return index
	 */
	public Long getIndex() {
		return index;
	}

	/**
	 * Sets the index
	 * @param index
	 */
	public void setIndex(Long index) {
		this.index = index;
	}
	
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
	 * Gets the userID
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the userID
	 * @param userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
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
	 * Gets the moduleID
	 * @return moduleID
	 */
	public String getModuleID() {
		return moduleID;
	}

	/**
	 * Sets the moduleID
	 * @param moduleID
	 */
	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}

	/**
	 * Gets the moduleName
	 * @return moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Sets the moduleName
	 * @param moduleName
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
	 * Gets the subModuleName
	 * @return subModuleName
	 */
	public String getSubModuleName() {
		return subModuleName;
	}

	/**
	 * Sets the subModuleName
	 * @param subModuleName
	 */
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
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
	 * Gets the status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the action
	 * @return action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the createdDate
	 * @return createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the createdDate
	 * @param createdDate
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}			
}
