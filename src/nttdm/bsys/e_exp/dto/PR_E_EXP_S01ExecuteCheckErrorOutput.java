/**
 * @(#)PR_E_EXP_S01ExecuteCheckErrorOutput.java
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


/**
 * @author gplai
 *
 */
public class PR_E_EXP_S01ExecuteCheckErrorOutput {
    
    private String closingMonth;
    
    private String closingYear;
    
    private String typeFlg;

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

    /**
     * @return the typeFlg
     */
    public String getTypeFlg() {
        return typeFlg;
    }

    /**
     * @param typeFlg the typeFlg to set
     */
    public void setTypeFlg(String typeFlg) {
        this.typeFlg = typeFlg;
    }
}
