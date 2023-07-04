/**
 * @(#)R_SAL_R02Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * R_SAL_R02Output class.
 * 
 * @author xycao
 */
public class R_SAL_R02Output implements Serializable {

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
     * salesReport
     */
    private List<HashMap<String, Object>> listSalesReport = null;
    
    private String initFlag;
    
    /**
     * 
     */
    private String issueTypeSingpost;
    
    /**
     * 
     */
    private String issueTypeAuto;
    
    /**
     * 
     */
    private String issueTypeManual;
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
     * @return the listSalesReport
     */
    public List<HashMap<String, Object>> getListSalesReport() {
        return listSalesReport;
    }

    /**
     * @param listSalesReport the listSalesReport to set
     */
    public void setListSalesReport(List<HashMap<String, Object>> listSalesReport) {
        this.listSalesReport = listSalesReport;
    }

    /**
     * @return the initFlag
     */
    public String getInitFlag() {
        return initFlag;
    }

    /**
     * @param initFlag the initFlag to set
     */
    public void setInitFlag(String initFlag) {
        this.initFlag = initFlag;
    }

    /**
     * @return the issueTypeSingpost
     */
    public String getIssueTypeSingpost() {
        return issueTypeSingpost;
    }

    /**
     * @param issueTypeSingpost the issueTypeSingpost to set
     */
    public void setIssueTypeSingpost(String issueTypeSingpost) {
        this.issueTypeSingpost = issueTypeSingpost;
    }

    /**
     * @return the issueTypeAuto
     */
    public String getIssueTypeAuto() {
        return issueTypeAuto;
    }

    /**
     * @param issueTypeAuto the issueTypeAuto to set
     */
    public void setIssueTypeAuto(String issueTypeAuto) {
        this.issueTypeAuto = issueTypeAuto;
    }

    /**
     * @return the issueTypeManual
     */
    public String getIssueTypeManual() {
        return issueTypeManual;
    }

    /**
     * @param issueTypeManual the issueTypeManual to set
     */
    public void setIssueTypeManual(String issueTypeManual) {
        this.issueTypeManual = issueTypeManual;
    }
}