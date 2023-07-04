/*
 * @(#)B_BIFS03LoadInput.java
 *
 *
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * 入力DTOクラス。
 * 
 * @author duongnld
 */
public class B_BIFS03LoadInput implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7576543938462057363L;

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