/**
 * @(#)E_SET_S01_CheckOutput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/02/26
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_set.bean;

/**
 * @author gplai
 *
 */
public class E_SET_S01_CheckOutput {

    private String messageContext;
    
    private boolean resultFlg;
    
    /**
     * @return the messageContext
     */
    public String getMessageContext() {
        return messageContext;
    }

    /**
     * @param messageContext the messageContext to set
     */
    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    /**
     * @return the resultFlg
     */
    public boolean isResultFlg() {
        return resultFlg;
    }

    /**
     * @param resultFlg the resultFlg to set
     */
    public void setResultFlg(boolean resultFlg) {
        this.resultFlg = resultFlg;
    }
}
