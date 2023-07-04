/**
 * @(#)R_ACR_R02Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_acr.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * R_ACR_R02Output class.
 * 
 * @author xycao
 */
public class R_ACR_R02Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7112562778671150991L;

    /**
     * access Type
     */
    private String accessType;
    
    /**
     * 
     */
    private Integer row;

    /**
     * 
     */
    private Integer startIndex;

    /**
     * 
     */
    private Integer totalRow;

    /**
     * accrualReport
     */
    private List<HashMap<String, Object>> listAccrualReport = null;

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
     * @return the listAccrualReport
     */
    public List<HashMap<String, Object>> getListAccrualReport() {
        return listAccrualReport;
    }

    /**
     * @param listAccrualReport the listAccrualReport to set
     */
    public void setListAccrualReport(
        List<HashMap<String, Object>> listAccrualReport) {
        this.listAccrualReport = listAccrualReport;
    }
    /**
     * @return the accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * @param accessType the accessType to set
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}