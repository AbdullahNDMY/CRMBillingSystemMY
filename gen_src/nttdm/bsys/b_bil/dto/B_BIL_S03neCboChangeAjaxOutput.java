/**
 * @(#)B_BIL_S03neCboChangeAjaxOutput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/06/14
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;

/**
 * @author gplai
 *
 */
public class B_BIL_S03neCboChangeAjaxOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2193644495893311312L;

    private T_BIL_HeaderInfo bilHeaderInfo;

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
}
