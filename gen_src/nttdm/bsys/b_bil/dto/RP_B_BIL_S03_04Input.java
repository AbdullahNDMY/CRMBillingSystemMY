/**
 * @(#)RP_B_BIL_S03_04Input.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/18
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * nputDTO class.
 * 
 * @author gplai
 * 
 */
public class RP_B_BIL_S03_04Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1870494861673407487L;

    /**
     * idRef
     */
    private String idRef;

    /**
     * AutSign
     */
    private String autSign="";
    
    /**
     * uvo
     */
    private BillingSystemUserValueObject uvo;

    /**
     * @return the idRef
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * @param idRef
     *            the idRef to set
     */
    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    /**
     * @return the autSign
     */
    public String getAutSign() {
        return autSign;
    }

    /**
     * @param autSign
     *            the autSign to set
     */
    public void setAutSign(String autSign) {
        this.autSign = autSign;
    }

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * @param uvo
     *            the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }
}
