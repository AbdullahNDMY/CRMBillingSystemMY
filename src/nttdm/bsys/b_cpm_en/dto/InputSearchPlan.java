/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management Inquiry
 * SERVICE NAME   : B_CPM_S01
 * FUNCTION       : object using for Customer Plan Management Inquiry 
 * FILE NAME      : InputSearchPlan.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/09/23 [Duong Nguyen] fixed #2655
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

/**
 * InputSearchPlan.class<br>
 * <ul>
 * <li>define input serach plan object</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class InputSearchPlan {

	private String customerId;
	private String customerName;
	private String customerType;
	
	private String category;
	private String service;
	private String plan;
	private String planDetail;
	
	private String subscriptionId;
	private String billingAccount;
	private String[] serviceStatus;
	private String jobNo;
	
	private String[] transType;
	private String[] billingInstructions;
	private String[] billingStatus;
	private String billingInstruction;
	private String serviceDateStartFrom;
	private String serviceDateStartTo;
	private String serviceDateEndFrom;
	private String serviceDateEndTo;
	private String contractStartFrom;
	private String contractStartTo;
	private String contractEndFrom;
	private String contractEndTo;
	private String applicationDateFrom;
    private String applicationDateTo;
	
	private String totalCount;
	private String searchBy;
	private int startIndex;
	private int row;
	
	private String tranType;
	private String billCurrency;
	
	private Object idScreen;
	
	private String[] idCustPlans;
	
	private String searchCustomerId;
    private String searchCustomerName;
    // #185 Start
    private String billingDesc;
    // #185 End
    
    // #189 Start
    private String[] billingOption;
    // #189 End
	
	public InputSearchPlan() {
		this.serviceStatus = new String[0];
		this.transType = new String[0];
		this.billingInstructions=new String[0];
		this.billingStatus=new String[0];
		this.totalCount = "";
		this.startIndex = 1;
		this.billingOption = new String[0];
	}
	/**
	 * @return the jobNo
	 */
	public String getJobNo() {
		return jobNo;
	}

	/**
	 * @param jobNo the jobNo to set
	 */
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	/**
	 * @return the tranType
	 */
	public String getTranType() {
		return tranType;
	}

	/**
	 * @param tranType the tranType to set
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	/**
	 * @return the billCurrency
	 */
	public String getBillCurrency() {
		return billCurrency;
	}

	/**
	 * @param billCurrency the billCurrency to set
	 */
	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the customerType
	 */
	public String getCustomerType() {
		return customerType;
	}
	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * @return the plan
	 */
	public String getPlan() {
		return plan;
	}
	/**
	 * @param plan the plan to set
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}
	/**
	 * @return the planDetail
	 */
	public String getPlanDetail() {
		return planDetail;
	}
	/**
	 * @param planDetail the planDetail to set
	 */
	public void setPlanDetail(String planDetail) {
		this.planDetail = planDetail;
	}
	/**
	 * @return the subscriptionId
	 */
	public String getSubscriptionId() {
		return subscriptionId;
	}
	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	/**
	 * @return the billingAccount
	 */
	public String getBillingAccount() {
		return billingAccount;
	}
	/**
	 * @param billingAccount the billingAccount to set
	 */
	public void setBillingAccount(String billingAccount) {
		this.billingAccount = billingAccount;
	}
	/**
	 * @return the serviceStatus
	 */
	public String[] getServiceStatus() {
		return serviceStatus;
	}
	/**
	 * @param serviceStatus the serviceStatus to set
	 */
	public void setServiceStatus(String[] serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	/**
	 * @return the billingInstruction
	 */
	public String getBillingInstruction() {
		return billingInstruction;
	}
	/**
	 * @param billingInstruction the billingInstruction to set
	 */
	public void setBillingInstruction(String billingInstruction) {
		this.billingInstruction = billingInstruction;
	}
	
	/**
     * @return the billingInstructions
     */
    public String[] getBillingInstructions() {
        return billingInstructions;
    }
    /**
     * @param billingInstructions the billingInstructions to set
     */
    public void setBillingInstructions(String[] billingInstructions) {
        this.billingInstructions = billingInstructions;
    }
	
    /**
     * @return the billingStatus
     */
	public String[] getBillingStatus() {
        return billingStatus;
    }
	
	/**
     * @param billingStatus the billingStatus to set
     */
    public void setBillingStatus(String[] billingStatus) {
        this.billingStatus = billingStatus;
    }
    
    /**
	 * @return the serviceDateStartFrom
	 */
	public String getServiceDateStartFrom() {
		return serviceDateStartFrom;
	}
	/**
	 * @param serviceDateStartFrom the serviceDateStartFrom to set
	 */
	public void setServiceDateStartFrom(String serviceDateStartFrom) {
		this.serviceDateStartFrom = serviceDateStartFrom;
	}
	/**
	 * @return the serviceDateStartTo
	 */
	public String getServiceDateStartTo() {
		return serviceDateStartTo;
	}
	/**
	 * @param serviceDateStartTo the serviceDateStartTo to set
	 */
	public void setServiceDateStartTo(String serviceDateStartTo) {
		this.serviceDateStartTo = serviceDateStartTo;
	}
	/**
	 * @return the serviceDateEndFrom
	 */
	public String getServiceDateEndFrom() {
		return serviceDateEndFrom;
	}
	/**
	 * @param serviceDateEndFrom the serviceDateEndFrom to set
	 */
	public void setServiceDateEndFrom(String serviceDateEndFrom) {
		this.serviceDateEndFrom = serviceDateEndFrom;
	}
	/**
	 * @return the serviceDateEndTo
	 */
	public String getServiceDateEndTo() {
		return serviceDateEndTo;
	}
	/**
	 * @param serviceDateEndTo the serviceDateEndTo to set
	 */
	public void setServiceDateEndTo(String serviceDateEndTo) {
		this.serviceDateEndTo = serviceDateEndTo;
	}
	
	/**
	 * @return the transType
	 */
	public String[] getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String[] transType) {
		this.transType = transType;
	}

	/**
	 * @return the totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the searchBy
	 */
	public String getSearchBy() {
		return searchBy;
	}

	/**
	 * @param searchBy the searchBy to set
	 */
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @param idScreen the idScreen to set
	 */
	public void setIdScreen(Object idScreen) {
		this.idScreen = idScreen;
	}

	/**
	 * @return the idScreen
	 */
	public Object getIdScreen() {
		return idScreen;
	}

    /**
     * @return the contractStartFrom
     */
    public String getContractStartFrom() {
        return contractStartFrom;
    }

    /**
     * @param contractStartFrom the contractStartFrom to set
     */
    public void setContractStartFrom(String contractStartFrom) {
        this.contractStartFrom = contractStartFrom;
    }

    /**
     * @return the contractStartTo
     */
    public String getContractStartTo() {
        return contractStartTo;
    }

    /**
     * @param contractStartTo the contractStartTo to set
     */
    public void setContractStartTo(String contractStartTo) {
        this.contractStartTo = contractStartTo;
    }

    /**
     * @return the contractEndFrom
     */
    public String getContractEndFrom() {
        return contractEndFrom;
    }

    /**
     * @param contractEndFrom the contractEndFrom to set
     */
    public void setContractEndFrom(String contractEndFrom) {
        this.contractEndFrom = contractEndFrom;
    }

    /**
     * @return the contractEndTo
     */
    public String getContractEndTo() {
        return contractEndTo;
    }

    /**
     * @param contractEndTo the contractEndTo to set
     */
    public void setContractEndTo(String contractEndTo) {
        this.contractEndTo = contractEndTo;
    }

    /**
     * @return the applicationDateFrom
     */
    public String getApplicationDateFrom() {
        return applicationDateFrom;
    }

    /**
     * @param applicationDateFrom the applicationDateFrom to set
     */
    public void setApplicationDateFrom(String applicationDateFrom) {
        this.applicationDateFrom = applicationDateFrom;
    }

    /**
     * @return the applicationDateTo
     */
    public String getApplicationDateTo() {
        return applicationDateTo;
    }

    /**
     * @param applicationDateTo the applicationDateTo to set
     */
    public void setApplicationDateTo(String applicationDateTo) {
        this.applicationDateTo = applicationDateTo;
    }

    /**
     * @return the idCustPlans
     */
    public String[] getIdCustPlans() {
        return idCustPlans;
    }

    /**
     * @param idCustPlans the idCustPlans to set
     */
    public void setIdCustPlans(String[] idCustPlans) {
        this.idCustPlans = idCustPlans;
    }

    /**
     * @return the searchCustomerId
     */
    public String getSearchCustomerId() {
        return searchCustomerId;
    }

    /**
     * @param searchCustomerId the searchCustomerId to set
     */
    public void setSearchCustomerId(String searchCustomerId) {
        this.searchCustomerId = searchCustomerId;
    }

    /**
     * @return the searchCustomerName
     */
    public String getSearchCustomerName() {
        return searchCustomerName;
    }

    /**
     * @param searchCustomerName the searchCustomerName to set
     */
    public void setSearchCustomerName(String searchCustomerName) {
        this.searchCustomerName = searchCustomerName;
    }
	/**
	 * @return the billingDesc
	 */
	public String getBillingDesc() {
		return billingDesc;
	}
	/**
	 * @param billingDesc the billingDesc to set
	 */
	public void setBillingDesc(String billingDesc) {
		this.billingDesc = billingDesc;
	}
	/**
	 * @return the billingOption
	 */
	public String[] getBillingOption() {
		return billingOption;
	}
	/**
	 * @param billingOption the billingOption to set
	 */
	public void setBillingOption(String[] billingOption) {
		this.billingOption = billingOption;
	}
}
