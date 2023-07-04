/**
 * @(#)R_REV_R02BLogic.java
 * Billing System
 * Version 3.03
 * Created 2016/09/30
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
public class R_REV_R02Input implements Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2791181926683415000L;
	
    private BillingSystemUserValueObject uvo;
    private String generateReportYear;
	private String generateReportMonth;
    
    
    public String getGenerateReportYear() {
		return generateReportYear;
	}

	public void setGenerateReportYear(String generateReportYear) {
		this.generateReportYear = generateReportYear;
	}

	public String getGenerateReportMonth() {
		return generateReportMonth;
	}

	public void setGenerateReportMonth(String generateReportMonth) {
		this.generateReportMonth = generateReportMonth;
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