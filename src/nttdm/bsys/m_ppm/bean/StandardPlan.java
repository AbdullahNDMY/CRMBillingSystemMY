/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM
 * FUNCTION : Java Bean
 * FILE NAME : StandardPlan.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.bean;

/**
 * StandardPlan<br/>
 * Bean contain StandardPlan information of plan when <b>Search</b><br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class StandardPlan {
	private String idPlan;
	
	private String idPlanGrp;
	
	private String no;
	
	private String cat;
	
	private String bill;
	
	private String planName;
	
	private String customerType;
	
	private String type;
	
	private String serviceName;
	
	private String rateType;
	
	private String rateMode;
	
	private String rate;
	
	private String accountCode;
	
	private String description;
	
	private String descriptionLimit;
	
	private String currency;
	
	private String cboCategory;

	public String getCboCategory() {
        return cboCategory == null ? "" : cboCategory;
    }

    public void setCboCategory(String cboCategory) {
        this.cboCategory = cboCategory;
    }

    private boolean selected = false;

	public String getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	public String getIdPlanGrp() {
		return idPlanGrp;
	}

	public void setIdPlanGrp(String idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getRateMode() {
		return rateMode;
	}

	public void setRateMode(String rateMode) {
		this.rateMode = rateMode;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionLimit() {
		return descriptionLimit;
	}

	public void setDescriptionLimit(String descriptionLimit) {
		this.descriptionLimit = descriptionLimit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
