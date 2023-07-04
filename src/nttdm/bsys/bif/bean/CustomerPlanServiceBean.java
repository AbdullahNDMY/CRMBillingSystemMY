/**
 * @(#)CustomerPlanServiceBean.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created 2013/03/20
 * 
 * Copyright (c) 2013 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.bif.bean;

import java.math.BigDecimal;
import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * @author gplai
 *
 */
public class CustomerPlanServiceBean {

    private List<CustomerPlanSubPlanBean> subPlanList;
    
    private String itemNo;
    
    private String itemQty;
    
    private String itemUprice;
    
    private String itemAmt;
    
    private String itemDiscAmt; 
    
    private String itemGst;
    
    private String svcStart;
    
    private String svcEnd;
    
    private String minSvcStart;
    
    private String minSvcEnd;
    
    private String contractTerm;
    
    private String contractTermNo;
    
    /**
     * 0: Itemised Amount,1: Lump Sum Amount
     */
    private String isLumpSum;
    
    /**
     * 0: No - unselected,1: Yes - selected
     */
    private String isDisplayMinSvc;
    
    private String itemDesc;
    
    private String billInstruction;
    
    private String billCurrency;
    
    private String exportCurrency;
    
    private String fixedForex;
    
    /**
     * 0: NO,1: YES
     */
    private String autoRenew;
    
    private String idCustPlanGrp;
    
    private String idCustPlan;
    
    private String discLumpSum;
    
    private BigDecimal taxAmount;
    
    public CustomerPlanServiceBean() {
        this.subPlanList = new AutoScaleList<CustomerPlanSubPlanBean>(new CustomerPlanSubPlanBean());
    }

    /**
     * @return the subPlanList
     */
    public List<CustomerPlanSubPlanBean> getSubPlanList() {
        return subPlanList;
    }

    /**
     * @param subPlanList the subPlanList to set
     */
    public void setSubPlanList(List<CustomerPlanSubPlanBean> subPlanList) {
        this.subPlanList = subPlanList;
    }

    /**
     * @return the itemNo
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * @param itemNo the itemNo to set
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * @return the itemQty
     */
    public String getItemQty() {
        return itemQty;
    }

    /**
     * @param itemQty the itemQty to set
     */
    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    /**
     * @return the itemUprice
     */
    public String getItemUprice() {
        return itemUprice;
    }

    /**
     * @param itemUprice the itemUprice to set
     */
    public void setItemUprice(String itemUprice) {
        this.itemUprice = itemUprice;
    }

    /**
     * @return the itemAmt
     */
    public String getItemAmt() {
        return itemAmt;
    }

    /**
     * @param itemAmt the itemAmt to set
     */
    public void setItemAmt(String itemAmt) {
        this.itemAmt = itemAmt;
    }
    
	public String getItemDiscAmt() {
		return itemDiscAmt;
	}

	public void setItemDiscAmt(String itemDiscAmt) {
		this.itemDiscAmt = itemDiscAmt;
	}

	/**
     * @return the svcStart
     */
    public String getSvcStart() {
        return svcStart;
    }

    /**
     * @param svcStart the svcStart to set
     */
    public void setSvcStart(String svcStart) {
        this.svcStart = svcStart;
    }

    /**
     * @return the svcEnd
     */
    public String getSvcEnd() {
        return svcEnd;
    }

    /**
     * @param svcEnd the svcEnd to set
     */
    public void setSvcEnd(String svcEnd) {
        this.svcEnd = svcEnd;
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
     * @return the isDisplayMinSvc
     */
    public String getIsDisplayMinSvc() {
        return isDisplayMinSvc;
    }

    /**
     * @param isDisplayMinSvc the isDisplayMinSvc to set
     */
    public void setIsDisplayMinSvc(String isDisplayMinSvc) {
        this.isDisplayMinSvc = isDisplayMinSvc;
    }

    /**
     * @return the billInstruction
     */
    public String getBillInstruction() {
        return billInstruction;
    }

    /**
     * @param billInstruction the billInstruction to set
     */
    public void setBillInstruction(String billInstruction) {
        this.billInstruction = billInstruction;
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
     * @return the exportCurrency
     */
    public String getExportCurrency() {
        return exportCurrency;
    }

    /**
     * @param exportCurrency the exportCurrency to set
     */
    public void setExportCurrency(String exportCurrency) {
        this.exportCurrency = exportCurrency;
    }

    /**
     * @return the fixedForex
     */
    public String getFixedForex() {
        return fixedForex;
    }

    /**
     * @param fixedForex the fixedForex to set
     */
    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
    }

    /**
     * @return the autoRenew
     */
    public String getAutoRenew() {
        return autoRenew;
    }

    /**
     * @param autoRenew the autoRenew to set
     */
    public void setAutoRenew(String autoRenew) {
        this.autoRenew = autoRenew;
    }

    /**
     * @return the itemDesc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * @param itemDesc the itemDesc to set
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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
     * @return the minSvcStart
     */
    public String getMinSvcStart() {
        return minSvcStart;
    }

    /**
     * @param minSvcStart the minSvcStart to set
     */
    public void setMinSvcStart(String minSvcStart) {
        this.minSvcStart = minSvcStart;
    }

    /**
     * @return the minSvcEnd
     */
    public String getMinSvcEnd() {
        return minSvcEnd;
    }

    /**
     * @param minSvcEnd the minSvcEnd to set
     */
    public void setMinSvcEnd(String minSvcEnd) {
        this.minSvcEnd = minSvcEnd;
    }

    /**
     * @return the contractTerm
     */
    public String getContractTerm() {
        return contractTerm;
    }

    /**
     * @param contractTerm the contractTerm to set
     */
    public void setContractTerm(String contractTerm) {
        this.contractTerm = contractTerm;
    }

    /**
     * @return the contractTermNo
     */
    public String getContractTermNo() {
        return contractTermNo;
    }

    /**
     * @param contractTermNo the contractTermNo to set
     */
    public void setContractTermNo(String contractTermNo) {
        this.contractTermNo = contractTermNo;
    }

	public String getDiscLumpSum() {
		return discLumpSum;
	}

	public void setDiscLumpSum(String discLumpSum) {
		this.discLumpSum = discLumpSum;
	}

	public String getItemGst() {
		return itemGst;
	}

	public void setItemGst(String itemGst) {
		this.itemGst = itemGst;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
    
    
}
