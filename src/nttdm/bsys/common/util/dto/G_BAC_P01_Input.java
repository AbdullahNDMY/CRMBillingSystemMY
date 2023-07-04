/**
 * @(#)G_BAC_P01_Input.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util.dto;

/**
 * Input Object for Update Total Amount Due
 * 
 * @author bench
 */
public class G_BAC_P01_Input {
	
	private String type = "";
	
	private String id_login = "";
	
	private String id_bill_account = "";
	
	private String auditIdModule = "";
	
	private String auditIdSubModule = "";
	
	private String auditReference = "";
	
	private String auditStatus = "";
	
	/**
	 * @return the auditReference
	 */
	public String getAuditReference() {
		return auditReference;
	}
	
	/**
	 * @param auditReference
	 *            the auditReference to set
	 */
	public void setAuditReference(String auditReference) {
		this.auditReference = auditReference;
	}
	
	public String getAuditIdModule() {
		return auditIdModule;
	}
	
	public void setAuditIdModule(String auditIdModule) {
		this.auditIdModule = auditIdModule;
	}
	
	public String getAuditIdSubModule() {
		return auditIdSubModule;
	}
	
	public void setAuditIdSubModule(String auditIdSubModule) {
		this.auditIdSubModule = auditIdSubModule;
	}
	
	public String getAuditStatus() {
		return auditStatus;
	}
	
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public String getId_bill_account() {
		return id_bill_account;
	}
	
	public void setId_bill_account(String id_bill_account) {
		this.id_bill_account = id_bill_account;
	}
	
	public String getId_login() {
		return id_login;
	}
	
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
