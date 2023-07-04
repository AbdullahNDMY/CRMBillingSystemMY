/**
 * @(#)RP_B_BIL_S02_04Input.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author NTTDM
 */
public class RP_B_BIL_S02_04Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6789589575211402343L;

    /**
     * Billing Document Id
     */
    private String idRef;

    /**
     * Mode
     */
    private String mode;

    /**
     * fromPageFlag
     */
    private String fromPageFlag;

    /**
     * message
     */
    private String lastMsg;

    /**
     * @return the idRef
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * @param idRef
     *            the idRef to set
     */
    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode
     *            the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the fromPageFlag
     */
    public String getFromPageFlag() {
        return fromPageFlag;
    }

    /**
     * @param fromPageFlag
     *            the fromPageFlag to set
     */
    public void setFromPageFlag(String fromPageFlag) {
        this.fromPageFlag = fromPageFlag;
    }

    /**
     * @return the lastMsg
     */
    public String getLastMsg() {
        return lastMsg;
    }

    /**
     * @param lastMsg
     *            the lastMsg to set
     */
    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }
}