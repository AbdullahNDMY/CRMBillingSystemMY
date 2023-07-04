package nttdm.bsys.r_rev.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class RP_R_REV_S02RegenerateIO {
	
    private BillingSystemUserValueObject uvo;
	private String monthlyReportMonth;
	private String monthlyReportYear;
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
