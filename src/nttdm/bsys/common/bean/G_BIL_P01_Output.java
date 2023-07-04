/**
 * @(#)G_BIL_P01_Output.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * G_BIL_P01 output parameter class.
 */
public class G_BIL_P01_Output {

    /**
     * Batch status
     */
    private String batchStatus;
    
    /**
     * Screen Id
     */
    private String screenId;
    
    /**
     * Error Message
     */
    private String errorMessage;
    
    /**
     * Billing Id list
     */
    private List<String> idRefList;

    /**
     * Constructor.
     */
    public G_BIL_P01_Output() {
        this.screenId = "";
        this.errorMessage = "";
        this.idRefList = new ArrayList<String>();
    }

    /**
     * Get Screen Id.
     * 
     * @return the screenId
     */
    public String getScreenId() {
        return screenId;
    }

    /**
     * Set Screen Id.
     * 
     * @param screenId
     *            the screenId to set
     */
    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

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

    /**
     * Get billing id list.
     * 
     * @return the idRefList
     */
    public List<String> getIdRefList() {
        return idRefList;
    }

    /**
     * Set billing id list.
     * 
     * @param idRefList
     *            the idRefList to set
     */
    public void setIdRefList(List<String> idRefList) {
        this.idRefList = idRefList;
    }

    /**
     * Set Batch Status.
     * 
     * @param batchStatus
     *            the batchStatus to set
     */
    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    /**
     * Get Batch Status.
     * 
     * @return the batchStatus
     */
    public String getBatchStatus() {
        return batchStatus;
    }
}
