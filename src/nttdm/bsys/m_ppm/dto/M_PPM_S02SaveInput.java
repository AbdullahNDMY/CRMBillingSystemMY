/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S02SaveInput.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * M_PPM_S02SaveInput<br/>
 * Input information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02SaveInput {
	
	private BillingSystemUserValueObject uvo;

	private Plan plan = null;

	private String isHaveCheckFlg;

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

    public String getIsHaveCheckFlg() {
        return isHaveCheckFlg;
    }

    public void setIsHaveCheckFlg(String isHaveCheckFlg) {
        this.isHaveCheckFlg = isHaveCheckFlg;
    }
}
