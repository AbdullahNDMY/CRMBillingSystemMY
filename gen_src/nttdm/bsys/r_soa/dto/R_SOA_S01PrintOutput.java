/**
 * @(#)R_SOA_S01PrintOutput.java
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

/**
 * @author gplai
 *
 */
public class R_SOA_S01PrintOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5517069521742899297L;

    private String[] selectedStatementNo;

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
}
