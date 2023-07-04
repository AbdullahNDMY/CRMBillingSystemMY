/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_GBS
 * SERVICE NAME   : M_GBS_S01
 * FUNCTION       : SettingBean
 * FILE NAME      : GSettingBean.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_gbs.bean;

/**
 * GSettingBean<br>
 * <ul>
 * <li>A Bean to Express a global setting
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class GSettingBean {
	
	private String idSet;
	private int setSeq;
	private String setValue;
	private String setDesc;
	private boolean isDeleted;
	private String idLogin;
	private String setApply;

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

	public String getSetApply() {
		return setApply;
	}

	public void setSetApply(String setApply) {
		this.setApply = setApply;
	}		
}
