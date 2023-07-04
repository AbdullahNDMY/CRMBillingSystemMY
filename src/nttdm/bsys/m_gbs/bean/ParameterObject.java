/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_GBS
 * SERVICE NAME   : M_GBS_S01
 * FUNCTION       : ParameterObject
 * FILE NAME      : ParameterObject.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_gbs.bean;

/**
 * ParameterObject<br>
 * <ul>
 * <li>Parameter for interacting with SQL execution
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class ParameterObject {
	String idSet;
	int setSeq;
	String setValue;
	String setDesc;
	boolean isDeleted;
	String idLogin;
	Integer idAudit;

	public String getIdSet() {
		return idSet;
	}

	public void setIdSet(String idSet) {
		this.idSet = idSet;
	}

	public int getSetSeq() {
		return setSeq;
	}

	public void setSetSeq(int setSeq) {
		this.setSeq = setSeq;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}

	public String getSetDesc() {
		return setDesc;
	}

	public void setSetDesc(String setDesc) {
		this.setDesc = setDesc;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
}
