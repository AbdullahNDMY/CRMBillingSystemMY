package nttdm.bsys.c_cmn002.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class C_CMN002Input implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3825291939065997528L;
	
	private String id_user;
	private BillingSystemUserValueObject uvo;
	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	} 
	

}
