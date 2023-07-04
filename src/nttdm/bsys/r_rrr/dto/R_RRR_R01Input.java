/**
 * @(#)R_RRR_R01Input.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_RRR_R01Input class.
 * 
 * @author xycao
 */
public class R_RRR_R01Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9145210015647412918L;

    /**
     * 
     */
    private BillingSystemUserValueObject uvo;

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * @param uvo the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }
}