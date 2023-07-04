/**
 * @(#)E_SET_S03Bean.java
 * 
 * BillingSystem
 * 
 * Version 1.00
 * 
 * Created 2012/12/13
 * 
 * Copyright (c) 2012 BillingSystem Malaysia. All rights reserved.
 */
package nttdm.bsys.e_set.bean;

/**
 * Batch RUN_STATUS check.
 * 
 * @author NTTDM
 *
 */
public class E_SET_S03Bean {

    private String batchId;
    
    private String retStatus;
    
    private String retMsg;

    /**
     * @return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId the batchId to set
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * @return the retStatus
     */
    public String getRetStatus() {
        return retStatus;
    }

    /**
     * @param retStatus the retStatus to set
     */
    public void setRetStatus(String retStatus) {
        this.retStatus = retStatus;
    }

    /**
     * @return the retMsg
     */
    public String getRetMsg() {
        return retMsg;
    }

    /**
     * @param retMsg the retMsg to set
     */
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
