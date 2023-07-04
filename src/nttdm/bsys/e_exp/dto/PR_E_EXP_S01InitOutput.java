/**
 * @(#)PR_E_EXP_S01InitOutput.java
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

import java.util.List;
import java.util.Map;

/**
 * @author gplai
 *
 */
public class PR_E_EXP_S01InitOutput {

    private Integer row;

    private Integer startIndex;

    private Integer totalRow;
    
    private List<Map<String, Object>> listHistories;
    
    private String closingMonth;
    
    private String closingYear;

    // Add #146 Start
    private String customerTypeDispFlg;

    private String customerType;
    // Add #146 End

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
     * @return the totalRow
     */
    public Integer getTotalRow() {
        return totalRow;
    }

    /**
     * @param totalRow the totalRow to set
     */
    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    /**
     * @return the listHistories
     */
    public List<Map<String, Object>> getListHistories() {
        return listHistories;
    }

    /**
     * @param listHistories the listHistories to set
     */
    public void setListHistories(List<Map<String, Object>> listHistories) {
        this.listHistories = listHistories;
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
