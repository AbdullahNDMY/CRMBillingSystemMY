/**
 * @(#)RP_B_BIL_S02_04Input.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author NTTDM
 */
public class RP_B_BIL_S02_04Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9134245938124004680L;

    /**
     * Billing Document Id
     */
    private String idRef;

    /**
     * User Value Object
     */
    private BillingSystemUserValueObject uvo;

    /**
     * idRef を取得する
     * 
     * @return idRef
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * idRef を設定する
     * 
     * @param idRef
     */
    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    /**
     * uvo を取得する
     * 
     * @return uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * uvo を設定する
     * 
     * @param uvo
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

}