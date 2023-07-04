/**
 * @(#)M_PPM_S02CheckError2Input.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/28
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_ppm.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class M_PPM_S02CheckError2Input {
    
    private BillingSystemUserValueObject uvo;

    private Plan plan = null;


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
}
