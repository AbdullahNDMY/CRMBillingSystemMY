/*
 * @(#)B_BIFS03LoadInput.java
 *
 *
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.bif.bean.CustomerPlanBean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * 入力DTOクラス。
 * 
 * @author duongnld
 */
public class B_BIFS03LoadOutput extends B_BIF_Output implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7576543938462057363L;
	String exportCurrency = null;
	String rateCurrency = null;
	
	String taxType = null;
	String taxStr = null;
	
	Map<String, Object> cusInfo = null;
	
	BillingSystemUserValueObject userInfo = null;
	
	Map<String, Object> totalPlan = null;
	
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
	
	private Map<String, Object> bifObject = null;
	
	private String action = null;
	
	private CustomerPlanBean customerPlan;
	
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
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param bifObject the bifObject to set
	 */
	public void setBifObject(Map<String, Object> bifObject) {
		this.bifObject = bifObject;
	}

	/**
	 * @return the bifObject
	 */
	public Map<String, Object> getBifObject() {
		return bifObject;
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
	 * @return the taxType
	 */
	public String getTaxType() {
		return taxType;
	}

	/**
	 * @param taxType the taxType to set
	 */
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	/**
	 * @return the taxStr
	 */
	public String getTaxStr() {
		return taxStr;
	}

	/**
	 * @param taxStr the taxStr to set
	 */
	public void setTaxStr(String taxStr) {
		this.taxStr = taxStr;
	}
}