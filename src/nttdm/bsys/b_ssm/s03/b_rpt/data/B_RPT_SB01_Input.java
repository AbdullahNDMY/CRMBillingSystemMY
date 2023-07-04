/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Subscription Information
 * SERVICE NAME : B_SSM_S03
 * FUNCTION : processing reports from B_SSM_S03
 * FILE NAME : B_RPT_SB01_Input.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 */
package nttdm.bsys.b_ssm.s03.b_rpt.data;

/**
 * Java input bean of B_RPT_SB01<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_RPT_SB01_Input {
	private String idCust;
	private String idCustPlan;
	private String[] idCustPlanGrp;
	private String idSubInfo;
	private String reportPath;
	private String addType;
	private String sessionDirectory;
	
	private String[] serviceDescs;
	private String[] selectedServiceIds;
	
    public String[] getServiceDescs() {
        return serviceDescs;
    }

    public void setServiceDescs(String[] serviceDescs) {
        this.serviceDescs = serviceDescs;
    }

    public String[] getSelectedServiceIds() {
        return selectedServiceIds;
    }

    public void setSelectedServiceIds(String[] selectedServiceIds) {
        this.selectedServiceIds = selectedServiceIds;
    }

    /**
     * N:next day <br>
     * C:current day <br>
     * NM:next month
     */
    private String noticeMode;

    public String getNoticeMode() {
        return noticeMode;
    }

    public void setNoticeMode(String noticeMode) {
        this.noticeMode = noticeMode;
    }

    /**
	 * example 
	 * BillingSystemConstants.SUB_MODULE_B_SSM
	 * BillingSystemConstants.SUB_MODULE_B_CPM
	 */
	private String moduleId;

	public String getIdCust() {
		return idCust;
	}

	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	public String getIdCustPlan() { 
		return idCustPlan;
	}

	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	
    public String[] getIdCustPlanGrp() {
        return idCustPlanGrp;
    }

    public void setIdCustPlanGrp(String[] idCustPlanGrp) {
        this.idCustPlanGrp = idCustPlanGrp;
    }

    public String getIdSubInfo() {
		return idSubInfo;
	}

	public void setIdSubInfo(String idSubInfo) {
		this.idSubInfo = idSubInfo;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
	public String getReportPath() {
		return reportPath;
	}

    /**
     * @return the addType
     */
    public String getAddType() {
        return addType;
    }

    /**
     * @param addType the addType to set
     */
    public void setAddType(String addType) {
        this.addType = addType;
    }

    /**
     * @return the sessionDirectory
     */
    public String getSessionDirectory() {
        return sessionDirectory;
    }

    /**
     * @param sessionDirectory the sessionDirectory to set
     */
    public void setSessionDirectory(String sessionDirectory) {
        this.sessionDirectory = sessionDirectory;
    }
}
