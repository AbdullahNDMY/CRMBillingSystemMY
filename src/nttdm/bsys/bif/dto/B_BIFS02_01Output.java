package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.bif.bean.CustomerPlanBean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class B_BIFS02_01Output implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String idCust = null;
	String idCustPlan = null;
	String bifType = null;
	String idRef = null;
	String exportCurrency = null;
	String rateCurrency = null;
	String taxType = null;
	String taxStr = null;
	
	Map cusInfo = null;
	
	BillingSystemUserValueObject userInfo = null;
	
	Map totalPlan = null;
	
	List cusAdr = null;

	List qscRefers = null;

	List quoRefers = null;

	List colsNames = null;
	
	List jobNos = null;
	
	List attns = null;

	List currencies = null;

	List suppliers = null;
	
	List plans = null;
	
	List planSs = null;
	
	List planOs = null;
	
	private String bacDelivery;
	
	private String bacDeliveryEmail;
	
	private CustomerPlanBean customerPlan;
	
	private Map<String, Object> bifInfo = null;
	

	public List getColsNames() {
		return colsNames;
	}

	public void setColsNames(List colsNames) {
		this.colsNames = colsNames;
	}

	public List getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List currencies) {
		this.currencies = currencies;
	}


	public List getQscRefers() {
		return qscRefers;
	}

	public void setQscRefers(List qscRefers) {
		this.qscRefers = qscRefers;
	}

	public List getQuoRefers() {
		return quoRefers;
	}

	public void setQuoRefers(List quoRefers) {
		this.quoRefers = quoRefers;
	}

	public List getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List suppliers) {
		this.suppliers = suppliers;
	}

	public Map getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(Map cusInfo) {
		this.cusInfo = cusInfo;
	}

	public List getCusAdr() {
		return cusAdr;
	}

	public void setCusAdr(List cusAdr) {
		this.cusAdr = cusAdr;
	}

	public List getJobNos() {
		return jobNos;
	}

	public void setJobNos(List jobNos) {
		this.jobNos = jobNos;
	}

	public List getAttns() {
		return attns;
	}

	public void setAttns(List attns) {
		this.attns = attns;
	}

	public List getPlans() {
		return plans;
	}

	public void setPlans(List plans) {
		this.plans = plans;
	}

	public List getPlanSs() {
		return planSs;
	}

	public void setPlanSs(List planSs) {
		this.planSs = planSs;
	}

	public List getPlanOs() {
		return planOs;
	}

	public void setPlanOs(List planOs) {
		this.planOs = planOs;
	}

	public Map getTotalPlan() {
		return totalPlan;
	}

	public void setTotalPlan(Map totalPlan) {
		this.totalPlan = totalPlan;
	}

	public BillingSystemUserValueObject getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(BillingSystemUserValueObject userInfo) {
		this.userInfo = userInfo;
	}

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

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

	public String getBifType() {
		return bifType;
	}

	public void setBifType(String bifType) {
		this.bifType = bifType;
	}

	public String getExportCurrency() {
		return exportCurrency;
	}

	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}

	public String getRateCurrency() {
		return rateCurrency;
	}

	public void setRateCurrency(String rateCurrency) {
		this.rateCurrency = rateCurrency;
	}

    /**
     * @return the customerPlan
     */
    public CustomerPlanBean getCustomerPlan() {
        return customerPlan;
    }

    /**
     * @param customerPlan the customerPlan to set
     */
    public void setCustomerPlan(CustomerPlanBean customerPlan) {
        this.customerPlan = customerPlan;
    }

    /**
     * @return the bifInfo
     */
    public Map<String, Object> getBifInfo() {
        return bifInfo;
    }

    /**
     * @param bifInfo the bifInfo to set
     */
    public void setBifInfo(Map<String, Object> bifInfo) {
        this.bifInfo = bifInfo;
    }

	/**
	 * @return the bacDelivery
	 */
	public String getBacDelivery() {
		return bacDelivery;
	}

	/**
	 * @param bacDelivery the bacDelivery to set
	 */
	public void setBacDelivery(String bacDelivery) {
		this.bacDelivery = bacDelivery;
	}

	/**
	 * @return the bacDeliveryEmail
	 */
	public String getBacDeliveryEmail() {
		return bacDeliveryEmail;
	}

	/**
	 * @param bacDeliveryEmail the bacDeliveryEmail to set
	 */
	public void setBacDeliveryEmail(String bacDeliveryEmail) {
		this.bacDeliveryEmail = bacDeliveryEmail;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getTaxStr() {
		return taxStr;
	}

	public void setTaxStr(String taxStr) {
		this.taxStr = taxStr;
	}
	
	
}
