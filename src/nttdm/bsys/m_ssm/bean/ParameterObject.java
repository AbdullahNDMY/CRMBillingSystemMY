/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : ParameterObject
 * FILE NAME      : ParameterObject.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.bean;

/**
 * ParameterObject<br>
 * <ul>
 * <li>An object to be input for SQL execution
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class ParameterObject {
	
	private String svcGrp;
	private String idService;
	private String infoId;
	private String entry;
	private String report;
	private String idLogin;
	private Integer idAudit;

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public String getSvcGrp() {
		return svcGrp;
	}

	public void setSvcGrp(String svcGrp) {
		this.svcGrp = svcGrp;
	}

	public String getIdService() {
		return idService;
	}

	public void setIdService(String idService) {
		this.idService = idService;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
}
