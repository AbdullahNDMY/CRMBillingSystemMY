/**
 * @(#)G_RPT_P01_Input.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util.dto;
import java.util.HashMap;
import java.util.List;

/**
 * G_RPT_P01 input parameter class.
 */
public class G_RPT_P01_Input {

    /**
     * reportType
     */
    private String reportType;

    /**
     * User ID
     */
    private String userId;
    
    /**
     * listAgingReport
     */
    private List<HashMap<String, Object>> listAgingReport = null;


    /**
     * @return the reportType
     */
    public String getReportType() {
        return reportType;
    }


    /**
     * @param reportType the reportType to set
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }


    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }


    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
    public void setListAgingReport(List<HashMap<String, Object>> listAgingReport) {
        this.listAgingReport = listAgingReport;
    }

    /**
     * Constructor.
     */
    public G_RPT_P01_Input() {
    }
}
