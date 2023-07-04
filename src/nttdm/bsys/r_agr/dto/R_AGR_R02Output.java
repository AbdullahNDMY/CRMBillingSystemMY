/**
 * @(#)R_AGRR02Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_agr.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * R_AGRR02Output class.
 * 
 * @author xycao
 */
public class R_AGR_R02Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7112562778671150991L;

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
     * 
     */

    /**
     * agingReport
     */
    private List<HashMap<String, Object>> listAgingReport = null;

    /**
     * permission
     */
    private String permission;

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
     * @return the listAgingReport
     */
    public List<HashMap<String, Object>> getListAgingReport() {
        return listAgingReport;
    }

    /**
     * @param listAgingReport the listAgingReport to set
     */
    public void setListAgingReport(
        List<HashMap<String, Object>> listAgingReport) {
        this.listAgingReport = listAgingReport;
    }

    /**
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission the permission to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

}