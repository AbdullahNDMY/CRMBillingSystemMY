/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (new/edit)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : object using for define service
 * FILE NAME      : CustomerPlanService.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * CustomerPlanService.class<br>
 * <ul>
 * <li>define customer plan service object</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class CustomerPlanService {

	private String idCustPlanGrp;
	private String idCustPlan;
	
	private String serviceDateStart;
	private String serviceDateEnd;
	private String autoRenewal;
	private String serviceStatus;
	
	private String minimumService;
	private String minimumServiceFrom;
	private String minimumServiceTo;
	private String contactTermNo;
	private String contactTerm;
	private String proRateBase;
	private String proRateBaseNo;
	private String isLumpSum;
	private String isDiscountLumpSum;
	private String billDesc;
	
	private String serviceMultiPln;
	private String serviceIdPlan;
	
	private String billingStatus;
	
	private String completionDate;
	private List<CustomerSubPlan> subPlans;
	private String idLogin;
	private String idAudit;
	
	private String serviceBacCount;
	
	private String jobNoAllChk;
	    
	private String jobNoAllJob;
	// #141 service
	private String custPo;
	
	// #189 Start
	private String billingOption;
	// #189 End
	
	// #200, #201 wcbeh@20160921 - Master Location
	private String masterLocationFlg;	
	private String masterLocation;
	
	public String getMasterLocationFlg() {
		return masterLocationFlg;
	}

	public void setMasterLocationFlg(String masterLocationFlg) {
		this.masterLocationFlg = masterLocationFlg;
	}

	public String getMasterLocation() {
		return masterLocation;
	}

	public void setMasterLocation(String masterLocation) {
		this.masterLocation = masterLocation;
	}

	public CustomerPlanService() {
		this.subPlans = new AutoScaleList<CustomerSubPlan>(new CustomerSubPlan());
	}
	
	/**
	 * @param subPlans the subPlans to set
	 */
	public void setSubPlans(List<CustomerSubPlan> subPlans) {
		this.subPlans = subPlans;
	}

	/**
	 * @return the subPlans
	 */
	public List<CustomerSubPlan> getSubPlans() {
		return subPlans;
	}

	/**
	 * @return the serviceDateStart
	 */
	public String getServiceDateStart() {
		return serviceDateStart;
	}

	/**
	 * @param serviceDateStart the serviceDateStart to set
	 */
	public void setServiceDateStart(String serviceDateStart) {
		this.serviceDateStart = serviceDateStart;
	}

	/**
	 * @return the serviceDateEnd
	 */
	public String getServiceDateEnd() {
		return serviceDateEnd;
	}

	/**
	 * @param serviceDateEnd the serviceDateEnd to set
	 */
	public void setServiceDateEnd(String serviceDateEnd) {
		this.serviceDateEnd = serviceDateEnd;
	}

	/**
	 * @return the autoRenewal
	 */
	public String getAutoRenewal() {
		return autoRenewal;
	}

	/**
	 * @param autoRenewal the autoRenewal to set
	 */
	public void setAutoRenewal(String autoRenewal) {
		this.autoRenewal = autoRenewal;
	}

	/**
	 * @return the serviceStatus
	 */
	public String getServiceStatus() {
		return serviceStatus;
	}

	/**
	 * @param serviceStatus the serviceStatus to set
	 */
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	/**
	 * @return the minimumService
	 */
	public String getMinimumService() {
		return minimumService;
	}

	/**
	 * @param minimumService the minimumService to set
	 */
	public void setMinimumService(String minimumService) {
		this.minimumService = minimumService;
	}

	/**
	 * @return the minimumServiceFrom
	 */
	public String getMinimumServiceFrom() {
		return minimumServiceFrom;
	}

	/**
	 * @param minimumServiceFrom the minimumServiceFrom to set
	 */
	public void setMinimumServiceFrom(String minimumServiceFrom) {
		this.minimumServiceFrom = minimumServiceFrom;
	}

	/**
	 * @return the minimumServiceTo
	 */
	public String getMinimumServiceTo() {
		return minimumServiceTo;
	}

	/**
	 * @param minimumServiceTo the minimumServiceTo to set
	 */
	public void setMinimumServiceTo(String minimumServiceTo) {
		this.minimumServiceTo = minimumServiceTo;
	}

	/**
	 * @return the contactTermNo
	 */
	public String getContactTermNo() {
		return contactTermNo;
	}

	/**
	 * @param contactTermNo the contactTermNo to set
	 */
	public void setContactTermNo(String contactTermNo) {
		this.contactTermNo = contactTermNo;
	}

	/**
	 * @return the contactTerm
	 */
	public String getContactTerm() {
		return contactTerm;
	}

	/**
	 * @param contactTerm the contactTerm to set
	 */
	public void setContactTerm(String contactTerm) {
		this.contactTerm = contactTerm;
	}

	/**
	 * @return the proRateBase
	 */
	public String getProRateBase() {
		return proRateBase;
	}

	/**
	 * @param proRateBase the proRateBase to set
	 */
	public void setProRateBase(String proRateBase) {
		this.proRateBase = proRateBase;
	}

	/**
	 * @return the proRateBaseNo
	 */
	public String getProRateBaseNo() {
		return proRateBaseNo;
	}

	/**
	 * @param proRateBaseNo the proRateBaseNo to set
	 */
	public void setProRateBaseNo(String proRateBaseNo) {
		this.proRateBaseNo = proRateBaseNo;
	}

	/**
	 * @return the isLumpSum
	 */
	public String getIsLumpSum() {
		return isLumpSum;
	}

	/**
	 * @param isLumpSum the isLumpSum to set
	 */
	public void setIsLumpSum(String isLumpSum) {
		this.isLumpSum = isLumpSum;
	}
	
	/**
     * @return the isDiscountLumpSum
     */
    public String getIsDiscountLumpSum() {
        return isDiscountLumpSum;
    }
    
    /**
     * @param isDiscountLumpSum the isDiscountLumpSum to set
     */
    public void setIsDiscountLumpSum(String isDiscountLumpSum) {
        this.isDiscountLumpSum = isDiscountLumpSum;
    }

	/**
	 * @return the billDesc
	 */
	public String getBillDesc() {
		return billDesc;
	}

	/**
	 * @param billDesc the billDesc to set
	 */
	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}

	/**
	 * @return the idLogin
	 */
	public String getIdLogin() {
		return idLogin;
	}

	/**
	 * @param idLogin the idLogin to set
	 */
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

	/**
	 * @return the idAudit
	 */
	public String getIdAudit() {
		return idAudit;
	}

	/**
	 * @param idAudit the idAudit to set
	 */
	public void setIdAudit(String idAudit) {
		this.idAudit = idAudit;
	}

	/**
	 * @return the idCustPlanGrp
	 */
	public String getIdCustPlanGrp() {
		return idCustPlanGrp;
	}

	/**
	 * @param idCustPlanGrp the idCustPlanGrp to set
	 */
	public void setIdCustPlanGrp(String idCustPlanGrp) {
		this.idCustPlanGrp = idCustPlanGrp;
	}

	/**
	 * @return the idCustPlan
	 */
	public String getIdCustPlan() {
		return idCustPlan;
	}

	/**
	 * @param idCustPlan the idCustPlan to set
	 */
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	
	/**
	 * 
	 * @param propertyName
	 * @return
	 */
	public Object getPropertyByName(String propertyName) {
		if (propertyName.equals("")) {
			
		}
		return null;
	}

    /**
     * @return the serviceMultiPln
     */
    public String getServiceMultiPln() {
        return serviceMultiPln;
    }

    /**
     * @param serviceMultiPln the serviceMultiPln to set
     */
    public void setServiceMultiPln(String serviceMultiPln) {
        this.serviceMultiPln = serviceMultiPln;
    }

    /**
     * @return the serviceIdPlan
     */
    public String getServiceIdPlan() {
        return serviceIdPlan;
    }

    /**
     * @param serviceIdPlan the serviceIdPlan to set
     */
    public void setServiceIdPlan(String serviceIdPlan) {
        this.serviceIdPlan = serviceIdPlan;
    }

    /**
     * @return the serviceBacCount
     */
    public String getServiceBacCount() {
        return serviceBacCount;
    }

    /**
     * @param serviceBacCount the serviceBacCount to set
     */
    public void setServiceBacCount(String serviceBacCount) {
        this.serviceBacCount = serviceBacCount;
    }

    /**
     * @return the billingStatus
     */
    public String getBillingStatus() {
        return billingStatus;
    }

    /**
     * @param billingStatus the billingStatus to set
     */
    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }
    
    /**
     * @return the completionDate
     */
    
    public String getCompletionDate() {
        return completionDate;
    }

    /**
     * @param completiondate the completionDate to set
     */
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }
    
    /**
     * @return the jobNoAllChk
     */
    public String getJobNoAllChk() {
        return jobNoAllChk;
    }

    /**
     * @param jobNoAllChk the jobNoAllChk to set
     */
    public void setJobNoAllChk(String jobNoAllChk) {
        this.jobNoAllChk = jobNoAllChk;
    }

    /**
     * @return the jobNoAllJob
     */
    public String getJobNoAllJob() {
        return jobNoAllJob;
    }

    /**
     * @param jobNoAllJob the jobNoAllJob to set
     */
    public void setJobNoAllJob(String jobNoAllJob) {
        this.jobNoAllJob = jobNoAllJob;
    }

	/**
	 * @return the custPo
	 */
	public String getCustPo() {
		return custPo;
	}

	/**
	 * @param custPo the custPo to set
	 */
	public void setCustPo(String custPo) {
		this.custPo = custPo;
	}

	/**
	 * @return the billingOption
	 */
	public String getBillingOption() {
		return billingOption;
	}

	/**
	 * @param billingOption the billingOption to set
	 */
	public void setBillingOption(String billingOption) {
		this.billingOption = billingOption;
	}
}
