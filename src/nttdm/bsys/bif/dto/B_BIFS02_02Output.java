package nttdm.bsys.bif.dto;

import java.io.Serializable;

public class B_BIFS02_02Output implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idCust = null;
	private String idCustPlan = null;
	private String bifType = null;
	private String idRef = null;

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

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}
	
	

}
