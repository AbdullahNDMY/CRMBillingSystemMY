package nttdm.bsys.m_bnk.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class M_BNKS02deInput implements Serializable {
	
	private static final long serialVersionUID = -7466401111347442359L;
	
	private BillingSystemUserValueObject uvo;
	
	private String lblidbank;
	private String lblidlogin;
	private Integer idAudit = null;
	
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	
	public String getLblidlogin() {
		return lblidlogin;
	}
	public void setLblidlogin(String lblidlogin) {
		this.lblidlogin = lblidlogin;
	}
	public String getLblidbank() {
		return lblidbank;
	}
	public void setLblidbank(String lblidbank) {
		this.lblidbank = lblidbank;
	}
	
}
