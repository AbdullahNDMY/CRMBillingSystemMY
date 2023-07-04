package nttdm.bsys.b_ssm.s03.b_rpt.data;

public class B_RPT_SB03_Input {
	private String subscriptionID;
	private String moduleID;
	private String customerID;
	private String customerPlanID;
	private String[] customerPlanGrps;
	private String reportPath;
	private String addType;
	private String sessionDirectory;
	private String loginId;
	
	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getSubscriptionID() {
		return subscriptionID;
	}
	
	public String getModuleID() {
		return moduleID;
	}

	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String getCustomerPlanID() { 
		return customerPlanID;
	}

	public void setCustomerPlanID(String customerPlanID) {
		this.customerPlanID = customerPlanID;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public String getReportPath() {
		return reportPath;
	}
	
    public String[] getCustomerPlanGrps() {
        return customerPlanGrps;
    }

    public void setCustomerPlanGrps(String[] customerPlanGrps) {
        this.customerPlanGrps = customerPlanGrps;
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

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
