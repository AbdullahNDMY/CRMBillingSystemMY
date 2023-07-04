/**
 * @(#)B_BIL_S02neAddBillingItemOutput.java
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

/**
 * @author gplai
 *
 */
public class B_BIL_S03neAddBillingItemOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2090403656261218255L;
    
    private T_BIL_HeaderInfo bilHeaderInfo;
    
	private String gstCheck;

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
	 * @return the gstCheck
	 */
	public String getGstCheck() {
		return gstCheck;
	}

	/**
	 * @param gstCheck the gstCheck to set
	 */
	public void setGstCheck(String gstCheck) {
		this.gstCheck = gstCheck;
	}
}
