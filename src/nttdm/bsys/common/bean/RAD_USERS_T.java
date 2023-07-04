/**
 * @(#)RAD_USERS_T.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/10/21
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.bean;

/**
 * @author gplai
 * RAD_USERS_T table Bean
 */
public class RAD_USERS_T implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * USERNAME
     */
    private String userName;
    
    /**
     * PASSWORD
     */
    private String password;
    
    /**
     * EXPIRE_DATE
     */
    private String expireDate; 
    
    /**
     * CREDIT_TIME
     */
    private String creditTime; 
    
    /**
     * STATUS
     */
    private String status; 
    
    /**
     * PLAN_ID
     */
    private String planId; 
    
    /**
     * AUTH_METHOD
     */
    private String authMethod; 
    
    /**
     * ATTRBUTE_RULE
     */
    private String attrbuteRule; 
    
    /**
     * CREATE_DATE
     */
    private String createDate;
    
    /**
     * MODIFY_DATE
     */
    private String modifyDate;
    
    /**
     * MODIFY_COUNTER
     */
    private String modifyCounter;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the expireDate
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the creditTime
     */
    public String getCreditTime() {
        return creditTime;
    }

    /**
     * @param creditTime the creditTime to set
     */
    public void setCreditTime(String creditTime) {
        this.creditTime = creditTime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the planId
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * @param planId the planId to set
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * @return the authMethod
     */
    public String getAuthMethod() {
        return authMethod;
    }

    /**
     * @param authMethod the authMethod to set
     */
    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

    /**
     * @return the attrbuteRule
     */
    public String getAttrbuteRule() {
        return attrbuteRule;
    }

    /**
     * @param attrbuteRule the attrbuteRule to set
     */
    public void setAttrbuteRule(String attrbuteRule) {
        this.attrbuteRule = attrbuteRule;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the modifyDate
     */
    public String getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate the modifyDate to set
     */
    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @return the modifyCounter
     */
    public String getModifyCounter() {
        return modifyCounter;
    }

    /**
     * @param modifyCounter the modifyCounter to set
     */
    public void setModifyCounter(String modifyCounter) {
        this.modifyCounter = modifyCounter;
    }
}
