/**
 * @(#)R_SOA_S01InitInput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/02
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class R_SOA_S01InitInput implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3855237350435393102L;
    
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
