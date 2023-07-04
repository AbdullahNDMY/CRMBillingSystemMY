/**
 * @(#)R_SOA_S01PrintInput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/03
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class R_SOA_S01PrintInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -659359984096724339L;

    private BillingSystemUserValueObject uvo;
    
    private String[] selectedStatementNo;
    
    private String printType;
    
    private String fullStmtNo;

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * @param uvo the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    /**
     * @return the selectedStatementNo
     */
    public String[] getSelectedStatementNo() {
        return selectedStatementNo;
    }

    /**
     * @param selectedStatementNo the selectedStatementNo to set
     */
    public void setSelectedStatementNo(String[] selectedStatementNo) {
        this.selectedStatementNo = selectedStatementNo;
    }

    /**
     * @return the printType
     */
    public String getPrintType() {
        return printType;
    }

    /**
     * @param printType the printType to set
     */
    public void setPrintType(String printType) {
        this.printType = printType;
    }

    /**
     * @return the fullStmtNo
     */
    public String getFullStmtNo() {
        return fullStmtNo;
    }

    /**
     * @param fullStmtNo the fullStmtNo to set
     */
    public void setFullStmtNo(String fullStmtNo) {
        this.fullStmtNo = fullStmtNo;
    }
}
