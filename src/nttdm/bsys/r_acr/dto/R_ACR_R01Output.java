/**
 * @(#)R_ACR_R01Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_acr.dto;

import java.io.Serializable;

/**
 * R_ACR_R01Output class.
 * 
 * @author xycao
 */
public class R_ACR_R01Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7112562778671150991L;

    /**
     * access Type
     */
    private String accessType;

    /**
     * Service Status
     */
    private String[] tbxServiceStatus;
    
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
    /**
     * @return the tbxServiceStatus
     */
    public String[] getTbxServiceStatus() {
        return tbxServiceStatus;
    }

    /**
     * @param tbxServiceStatus the tbxServiceStatus to set
     */
    public void setTbxServiceStatus(String[] tbxServiceStatus) {
        this.tbxServiceStatus = tbxServiceStatus;
    }
}