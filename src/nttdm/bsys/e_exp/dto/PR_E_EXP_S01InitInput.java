/**
 * @(#)PR_E_EXP_S01InitInput.java
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
public class PR_E_EXP_S01InitInput {

    private BillingSystemUserValueObject uvo;
    
    private Integer row;
    
    private Integer startIndex;
    
    private String closingMonth;
    
    private String closingYear;
    
    private String typeFlg;

    // Add #146 Start
    private String customerTypeDispFlg;

    private String customerType;
    // Add #146 End

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
     * @return the row
     */
    public Integer getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * @return the startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
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

    // Add #146 Start
    /**
     * @return the customerTypeDispFlg
     */
    public String getCustomerTypeDispFlg() {
        return customerTypeDispFlg;
    }

    /**
     * @param customerTypeDispFlg the customerTypeDispFlg to set
     */
    public void setCustomerTypeDispFlg(String customerTypeDispFlg) {
        this.customerTypeDispFlg = customerTypeDispFlg;
    }

    /**
     * @return the customerType
     */
    public String getCustomerType() {
         return customerType;
    }

    /**
     * @param customerType the customerType to set
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    // Add #146 End
}
