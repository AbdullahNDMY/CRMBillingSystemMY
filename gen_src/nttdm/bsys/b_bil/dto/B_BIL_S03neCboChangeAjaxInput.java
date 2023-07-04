/**
 * @(#)B_BIL_S03neCboChangeAjaxInput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/30
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class B_BIL_S03neCboChangeAjaxInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5767676997790394929L;

    private T_BIL_HeaderInfo bilHeaderInfo;

    private BillingSystemUserValueObject uvo;

    /**
     * @return the bilHeaderInfo
     */
    public T_BIL_HeaderInfo getBilHeaderInfo() {
        return bilHeaderInfo;
    }

    /**
     * @param bilHeaderInfo the bilHeaderInfo to set
     */
    public void setBilHeaderInfo(T_BIL_HeaderInfo bilHeaderInfo) {
        this.bilHeaderInfo = bilHeaderInfo;
    }

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
