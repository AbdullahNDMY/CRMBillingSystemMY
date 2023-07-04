package nttdm.bsys.bif.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class B_BIFS02_03Input implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idRef = null;

	private BillingSystemUserValueObject uvo;

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}


}
