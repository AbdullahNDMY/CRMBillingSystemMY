/**
 * @(#)R_REV_R03BLogic.java
 * Billing System
 * Version 3.03
 * Created 2016/10/25
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rev.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_REV_R02Input class.
 * 
 * @author binz
 */
public class R_REV_R03Input implements Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2791181926683415000L;
	
    private BillingSystemUserValueObject uvo;
    private String monthlyReportMonth;
	private String monthlyReportYear;
	private String currentPage;
    
	
    public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getMonthlyReportMonth() {
		return monthlyReportMonth;
	}

	public void setMonthlyReportMonth(String monthlyReportMonth) {
		this.monthlyReportMonth = monthlyReportMonth;
	}

	public String getMonthlyReportYear() {
		return monthlyReportYear;
	}

	public void setMonthlyReportYear(String monthlyReportYear) {
		this.monthlyReportYear = monthlyReportYear;
	}

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
}