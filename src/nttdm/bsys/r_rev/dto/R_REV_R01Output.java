/**
 * @(#)R_REV_R01Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rev.dto;

import java.io.Serializable;

/**
 * R_ACR_R01Output class.
 * 
 * @author xycao
 */
public class R_REV_R01Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7112562778671150991L;
    	
	/**
     * access Type
     */
    private String accessType;
    
    private String reportMonth1;
    private String reportYear1;
    private String reportMonth2;
    private String reportYear2;
    private String reportMonth3;
    private String reportYear3;
    private String financialStart;
    private String financialEnd;
	private String closingMonth;
	private String currentPage;
	
    public String getReportMonth3() {
		return reportMonth3;
	}

	public void setReportMonth3(String reportMonth3) {
		this.reportMonth3 = reportMonth3;
	}

	public String getReportYear3() {
		return reportYear3;
	}

	public void setReportYear3(String reportYear3) {
		this.reportYear3 = reportYear3;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getFinancialStart() {
		return financialStart;
	}

	public void setFinancialStart(String financialStart) {
		this.financialStart = financialStart;
	}

	public String getFinancialEnd() {
		return financialEnd;
	}

	public void setFinancialEnd(String financialEnd) {
		this.financialEnd = financialEnd;
	}

	public String getClosingMonth() {
		return closingMonth;
	}

	public void setClosingMonth(String closingMonth) {
		this.closingMonth = closingMonth;
	}
    
    public String getReportMonth1() {
		return reportMonth1;
	}

	public void setReportMonth1(String reportMonth1) {
		this.reportMonth1 = reportMonth1;
	}

	public String getReportYear1() {
		return reportYear1;
	}

	public void setReportYear1(String reportYear1) {
		this.reportYear1 = reportYear1;
	}

	public String getReportMonth2() {
		return reportMonth2;
	}

	public void setReportMonth2(String reportMonth2) {
		this.reportMonth2 = reportMonth2;
	}

	public String getReportYear2() {
		return reportYear2;
	}

	public void setReportYear2(String reportYear2) {
		this.reportYear2 = reportYear2;
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