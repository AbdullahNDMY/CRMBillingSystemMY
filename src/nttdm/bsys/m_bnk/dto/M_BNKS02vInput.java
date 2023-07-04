package nttdm.bsys.m_bnk.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class M_BNKS02vInput implements Serializable{
	
	private static final long serialVersionUID = -7466401834447442359L;

	private String hypBankReference;
	private BillingSystemUserValueObject uvo;
	private String lblidlogin;

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public String getHypBankReference() {
		return hypBankReference;
	}

	public void setHypBankReference(String hypBankReference) {
		this.hypBankReference = hypBankReference;
	}
	public String getLblidlogin() {
		return lblidlogin;
	}
	public void setLblidlogin(String lblidlogin) {
		this.lblidlogin = lblidlogin;
	}
}
