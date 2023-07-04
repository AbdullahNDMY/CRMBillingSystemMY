/**
 * @(#)PR_E_EXP_S01ExecuteCheckErrorInput.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created 2013/08/04
 * 
 * Copyright (c) 2013 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.e_exp.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class PR_E_EXP_S01ExecuteCheckErrorInput {

    private BillingSystemUserValueObject uvo;
    
    private String closingMonth;
    
    private String closingYear;

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
     * @return the closingMonth
     */
    public String getClosingMonth() {
        return closingMonth;
    }

    /**
     * @param closingMonth the closingMonth to set
     */
    public void setClosingMonth(String closingMonth) {
        this.closingMonth = closingMonth;
    }

    /**
     * @return the closingYear
     */
    public String getClosingYear() {
        return closingYear;
    }

    /**
     * @param closingYear the closingYear to set
     */
    public void setClosingYear(String closingYear) {
        this.closingYear = closingYear;
    }
}
