/**
 * @(#)B_BIL_S06Bean.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/06/02
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.bean;

import java.util.List;
import java.util.Map;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * @author gplai
 *
 */
public class B_BIL_S06Bean {
    
    private List<T_BIL_DBean> subPlanBean;
    
    private String itemId;
    
    private String itemCat;
    
    private String itemLevel;
    
    private String itemDesc;
    
    private String itemQty;
    
    private String itemUprice;
    
    private String itemAmt;
    
    private String isDisplay;
    
    private String billFrom;
    
    private String billTo;
    
    private String jobNo;
    
    private String isDisplayMinSvc;
    
    private String minSvcFrom;
    
    private String minSvcTo;
    
    private String billFromDisplay;
    
    private String billToDisplay;
    
    private String minSvcFromDisplay;
    
    private String minSvcToDisplay;
    
    /**
     * 0:not display,1:display
     */
    private String jobModulesDisplayFlg;
    
    private String subPlanLength;
    
    private String gstPercent;
    
    // #154 Start
	private String gstCheck;

    private String displayDiscount;
    private String itemDisc;
    private String poNo;
    private String custPoDisplay;
    private List<Map<String, Object>> applyGstList;
    // #154 End

    public B_BIL_S06Bean(){
        this.subPlanBean = new AutoScaleList<T_BIL_DBean>(new T_BIL_DBean());
    }
    
    /**
     * @return the subPlanBean
     */
    public List<T_BIL_DBean> getSubPlanBean() {
        return subPlanBean;
    }

    /**
     * @param subPlanBean the subPlanBean to set
     */
    public void setSubPlanBean(List<T_BIL_DBean> subPlanBean) {
        this.subPlanBean = subPlanBean;
    }

    /**
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemCat
     */
    public String getItemCat() {
        return itemCat;
    }

    /**
     * @param itemCat the itemCat to set
     */
    public void setItemCat(String itemCat) {
        this.itemCat = itemCat;
    }

    /**
     * @return the itemLevel
     */
    public String getItemLevel() {
        return itemLevel;
    }

    /**
     * @param itemLevel the itemLevel to set
     */
    public void setItemLevel(String itemLevel) {
        this.itemLevel = itemLevel;
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

    /**
     * @return the isDisplay
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * @param isDisplay the isDisplay to set
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * @return the billFrom
     */
    public String getBillFrom() {
        return billFrom;
    }

    /**
     * @param billFrom the billFrom to set
     */
    public void setBillFrom(String billFrom) {
        this.billFrom = billFrom;
    }

    /**
     * @return the billTo
     */
    public String getBillTo() {
        return billTo;
    }

    /**
     * @param billTo the billTo to set
     */
    public void setBillTo(String billTo) {
        this.billTo = billTo;
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
     * @return the minSvcFrom
     */
    public String getMinSvcFrom() {
        return minSvcFrom;
    }

    /**
     * @param minSvcFrom the minSvcFrom to set
     */
    public void setMinSvcFrom(String minSvcFrom) {
        this.minSvcFrom = minSvcFrom;
    }

    /**
     * @return the minSvcTo
     */
    public String getMinSvcTo() {
        return minSvcTo;
    }

    /**
     * @param minSvcTo the minSvcTo to set
     */
    public void setMinSvcTo(String minSvcTo) {
        this.minSvcTo = minSvcTo;
    }

    /**
     * @return the billFromDisplay
     */
    public String getBillFromDisplay() {
        return billFromDisplay;
    }

    /**
     * @param billFromDisplay the billFromDisplay to set
     */
    public void setBillFromDisplay(String billFromDisplay) {
        this.billFromDisplay = billFromDisplay;
    }

    /**
     * @return the billToDisplay
     */
    public String getBillToDisplay() {
        return billToDisplay;
    }

    /**
     * @param billToDisplay the billToDisplay to set
     */
    public void setBillToDisplay(String billToDisplay) {
        this.billToDisplay = billToDisplay;
    }

    /**
     * @return the minSvcFromDisplay
     */
    public String getMinSvcFromDisplay() {
        return minSvcFromDisplay;
    }

    /**
     * @param minSvcFromDisplay the minSvcFromDisplay to set
     */
    public void setMinSvcFromDisplay(String minSvcFromDisplay) {
        this.minSvcFromDisplay = minSvcFromDisplay;
    }

    /**
     * @return the minSvcToDisplay
     */
    public String getMinSvcToDisplay() {
        return minSvcToDisplay;
    }

    /**
     * @param minSvcToDisplay the minSvcToDisplay to set
     */
    public void setMinSvcToDisplay(String minSvcToDisplay) {
        this.minSvcToDisplay = minSvcToDisplay;
    }

    /**
     * @return the jobModulesDisplayFlg
     */
    public String getJobModulesDisplayFlg() {
        return jobModulesDisplayFlg;
    }

    /**
     * @param jobModulesDisplayFlg the jobModulesDisplayFlg to set
     */
    public void setJobModulesDisplayFlg(String jobModulesDisplayFlg) {
        this.jobModulesDisplayFlg = jobModulesDisplayFlg;
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
     * @return the subPlanLength
     */
    public String getSubPlanLength() {
        return subPlanLength;
    }

    /**
     * @param subPlanLength the subPlanLength to set
     */
    public void setSubPlanLength(String subPlanLength) {
        this.subPlanLength = subPlanLength;
    }

    /**
     * @return the gstPercent
     */
    public String getGstPercent() {
        return gstPercent;
    }

    /**
     * @param gstPercent the gstPercent to set
     */
    public void setGstPercent(String gstPercent) {
        this.gstPercent = gstPercent;
    }

	/**
	 * @return the gstCheck
	 */
	public String getGstCheck() {
		return gstCheck;
	}

	/**
	 * @param gstCheck the gstCheck to set
	 */
	public void setGstCheck(String gstCheck) {
		this.gstCheck = gstCheck;
	}

	/**
	 * @return the displayDiscount
	 */
	public String getDisplayDiscount() {
		return displayDiscount;
	}

	/**
	 * @param displayDiscount the displayDiscount to set
	 */
	public void setDisplayDiscount(String displayDiscount) {
		this.displayDiscount = displayDiscount;
	}

	/**
	 * @return the itemDisc
	 */
	public String getItemDisc() {
		return itemDisc;
	}

	/**
	 * @param itemDisc the itemDisc to set
	 */
	public void setItemDisc(String itemDisc) {
		this.itemDisc = itemDisc;
	}

	/**
	 * @return the poNo
	 */
	public String getPoNo() {
		return poNo;
	}

	/**
	 * @param poNo the poNo to set
	 */
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	/**
	 * @return the custPoDisplay
	 */
	public String getCustPoDisplay() {
		return custPoDisplay;
	}

	/**
	 * @param custPoDisplay the custPoDisplay to set
	 */
	public void setCustPoDisplay(String custPoDisplay) {
		this.custPoDisplay = custPoDisplay;
	}

	/**
	 * @return the applyGstList
	 */
	public List<Map<String, Object>> getApplyGstList() {
		return applyGstList;
	}

	/**
	 * @param applyGstList the applyGstList to set
	 */
	public void setApplyGstList(List<Map<String, Object>> applyGstList) {
		this.applyGstList = applyGstList;
	}
}
