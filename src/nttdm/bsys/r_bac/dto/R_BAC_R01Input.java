/**
 * @(#)R_BAC_R01Input.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA  All Rights Reserved.
 */
package nttdm.bsys.r_bac.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_BAC_R01 Input parameter class
 * @author NTT DATA
 *
 */
public class R_BAC_R01Input {

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
