/**
 * @(#)R_BAC_R01Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_bac.dto;

/**
 * R_BAC_R01Output class.
 * 
 * @author NTTD DATA
 */
public class R_BAC_R01Output {
	
	/**
     * access Type
     */
    private String accessType;
    
    /**
     * totalamount
     */
    private String totalamount;
    
    public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	/**
     * @return the accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * @param accessType the accessType to set
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
