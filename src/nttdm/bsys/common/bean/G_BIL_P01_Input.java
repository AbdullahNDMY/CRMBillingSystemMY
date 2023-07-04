/**
 * @(#)G_BIL_P01_Input.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.bean;

import java.util.Date;

/**
 * G_BIL_P01 input parameter class.
 */
public class G_BIL_P01_Input {

    /**
     * Module ID
     */
    private String moduleId;

    /**
     * User ID
     */
    private String userId;

    /**
     * generate billing date
     */
    private Date running_date = null;

    /**
     * billing Account ID
     */
    private String billAccountId = null;

    /**
     * who run the action
     */
    private String runFrom="";
    
    // #192 Start
    private String billingOption;
    // #192 End
    
    /**
     * Get module id.
     * 
     * @return the moduleId
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Set module id.
     * 
     * @param moduleId
     *            the moduleId to set
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Get user id.
     * 
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set current user id.
     * 
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get running date.<br>
     * The default value is current system date.
     * 
     * @return running date.
     */
    public Date getRunning_date() {
        return running_date;
    }

    /**
     * Set billing generated running date.
     * 
     * @param runningDate
     *            billing date
     */
    public void setRunning_date(Date runningDate) {
        this.running_date = runningDate;
    }

    /**
     * Get billing account ID.
     * 
     * @return the billAccountId
     */
    public String getBillAccountId() {
        return billAccountId;
    }

    /**
     * Set billing account ID.
     * 
     * @param billAccountId
     *            the billAccountId to set
     */
    public void setBillAccountId(String billAccountId) {
        this.billAccountId = billAccountId;
    }
    
    /**
     * Get runFrom.
     * 
     * @return the runFrom
     */
    public String getRunFrom() {
        return runFrom;
    }

    /**
     * Set runFrom.
     * 
     * @param runFrom
     *            the runFrom to set
     */
    public void setRunFrom(String runFrom) {
        this.runFrom = runFrom;
    }

    /**
     * Constructor.
     */
    public G_BIL_P01_Input() {
    }

	/**
	 * @return the billingOption
	 */
	public String getBillingOption() {
		return billingOption;
	}

	/**
	 * @param billingOption the billingOption to set
	 */
	public void setBillingOption(String billingOption) {
		this.billingOption = billingOption;
	}


}
