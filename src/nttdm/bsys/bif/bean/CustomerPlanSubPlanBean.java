/**
 * @(#)CustomerPlanSubPlanBean.java
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

/**
 * @author gplai
 *
 */
public class CustomerPlanSubPlanBean {

    private String itemNo;
    
    private String itemQty;
    
    private String itemUprice;
    
    private String itemAmt;
    
    private String itemGst;
    
    /**
     * 0: Itemised Amount,1: Lump Sum Amount
     */
    private String isLumpSum;
    
    private String itemDesc;
    
    private String jobNo;
    
    private String isDisplayJobNo;
    /**
     * 0 - No,1 - Yes
     */
    private String applyGst;
    
    private String idCustPlanLink;
    
    private String discLumpSum;
    
    private String discAmount;
    
    private String taxRate;
    
    private BigDecimal taxAmount;
    
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
    
    

    public String getItemGst() {
		return itemGst;
	}

	public void setItemGst(String itemGst) {
		this.itemGst = itemGst;
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
     * @return the applyGst
     */
    public String getApplyGst() {
        return applyGst;
    }

    /**
     * @param applyGst the applyGst to set
     */
    public void setApplyGst(String applyGst) {
        this.applyGst = applyGst;
    }

    /**
     * @return the idCustPlanLink
     */
    public String getIdCustPlanLink() {
        return idCustPlanLink;
    }

    /**
     * @param idCustPlanLink the idCustPlanLink to set
     */
    public void setIdCustPlanLink(String idCustPlanLink) {
        this.idCustPlanLink = idCustPlanLink;
    }

    /**
     * @return the isDisplayJobNo
     */
    public String getIsDisplayJobNo() {
        return isDisplayJobNo;
    }

    /**
     * @param isDisplayJobNo the isDisplayJobNo to set
     */
    public void setIsDisplayJobNo(String isDisplayJobNo) {
        this.isDisplayJobNo = isDisplayJobNo;
    }

	public String getDiscLumpSum() {
		return discLumpSum;
	}

	public void setDiscLumpSum(String discLumpSum) {
		this.discLumpSum = discLumpSum;
	}

	public String getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(String discAmount) {
		this.discAmount = discAmount;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	
}
