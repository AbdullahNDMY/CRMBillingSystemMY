package nttdm.bsys.bif.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class B_BIFS02_01Input implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idCust = null;
	private String idCustPlan = null;
	private String bifType = null;

	private BillingSystemUserValueObject uvo;

	public String getIdCust() {
		return idCust;
	}

	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	public String getIdCustPlan() {
		return idCustPlan;
	}

	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}

	public String getBifType() {
		return bifType;
	}

	public void setBifType(String bifType) {
		this.bifType = bifType;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

}
