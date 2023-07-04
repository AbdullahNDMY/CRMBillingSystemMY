/**
 * @(#)G_RPT_P01_Output.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util.dto;

/**
 * G_RPT_P01 output parameter class.
 */
public class G_RPT_P01_Output {
    
    /**
     * Error Message
     */
    private String errorMessage;

    /**
     * Get Error Message.
     * 
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set Error Message.
     * 
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
