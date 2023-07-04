/*
 * @(#)Q_QCSR02Input.java
 *
 *
 */
package nttdm.bsys.q_qcs.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author ss051
 */
public class Q_QCSR02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3254951494469570209L;


	private String id_ref_s01;
	private BillingSystemUserValueObject uvo;
	
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public String getId_ref_s01() {
		return id_ref_s01;
	}

	public void setId_ref_s01(String id_ref_s01) {
		this.id_ref_s01 = id_ref_s01;
	}

}