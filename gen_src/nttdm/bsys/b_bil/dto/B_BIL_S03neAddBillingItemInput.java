/**
 * @(#)B_BIL_S02neAddBillingItemInput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/30
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

/**
 * @author gplai
 *
 */
public class B_BIL_S03neAddBillingItemInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4600964074434566975L;

    private String idCustPlanGrps;
    
    private String idCustPlanLinks;

    private String billType;
    /**
     * @return the idCustPlanGrps
     */
    public String getIdCustPlanGrps() {
        return idCustPlanGrps;
    }

    /**
     * @param idCustPlanGrps the idCustPlanGrps to set
     */
    public void setIdCustPlanGrps(String idCustPlanGrps) {
        this.idCustPlanGrps = idCustPlanGrps;
    }

    /**
     * @return the idCustPlanLinks
     */
    public String getIdCustPlanLinks() {
        return idCustPlanLinks;
    }

    /**
     * @param idCustPlanLinks the idCustPlanLinks to set
     */
    public void setIdCustPlanLinks(String idCustPlanLinks) {
        this.idCustPlanLinks = idCustPlanLinks;
    }

    /**
     * @return the billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType the billType to set
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }
}
