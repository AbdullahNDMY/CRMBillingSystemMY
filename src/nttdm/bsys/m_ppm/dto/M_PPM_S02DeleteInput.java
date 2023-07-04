/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S02DeleteInput.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * M_PPM_S02DeleteInput<br/>
 * Input information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02DeleteInput {

	private Integer idPlan;
	
	private BillingSystemUserValueObject uvo;

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}
}