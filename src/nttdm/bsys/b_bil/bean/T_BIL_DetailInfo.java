/**
 * @(#)T_BIL_DetailInfo.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/26
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.bean;

import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;


/**
 * @author gplai
 *
 */
public class T_BIL_DetailInfo {
    
    private List<T_BIL_DBean> subPlanBean;
    
    private String idRef;
    
    private String itemId;
    
    private String itemCat;
    
    private String itemLevel;
    
    private String itemNo;

    private String itemDesc;
    
    private String itemQty;
    
    private String itemUprice;
    
    private String itemAmt;
    
    private String itemGst;
    
    private String itemExportAmt;
    
    private String applyGst;
    
    private String isDisplay;
    
    private String idCustPlan;
    
    private String idCustPlanGrp;
    
    private String idCustPlanLink;
    
    private String itemType;
    
    private String svcLevel1;
    
    private String svcLevel2;
    
    private String billFrom;
    
    private String billTo;
    
    private String jobNo;
    
    private String isDisplayMinSvc;
    
    private String minSvcFrom;
    
    private String minSvcTo;
    
    private String isDeleted;
    
    private String dateCreated;
    
    private String dateUpdated;
    
    private String idLogin;
    
    private String idAudit;
    
    private String billFromDisplay;
    
    private String billToDisplay;
    
    private String minSvcFromDisplay;
    
    private String minSvcToDisplay;
    
    //add for #154 Start 
    private String itemDisc;
    
    private String itemSubTotal;
    
    private String taxCode;
    
    private String taxRate;
    
    private String itemExportGST;
    
    private String poNo;
    
    private String displayDiscount;
    //add for #154 End 

    /**
     * @return the idRef
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * @param idRef the idRef to set
     */
    public void setIdRef(String idRef) {
        this.idRef = idRef;
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
     * @return the itemGst
     */
    public String getItemGst() {
        return itemGst;
    }

    /**
     * @param itemGst the itemGst to set
     */
    public void setItemGst(String itemGst) {
        this.itemGst = itemGst;
    }

    /**
     * @return the itemExportAmt
     */
    public String getItemExportAmt() {
        return itemExportAmt;
    }

    /**
     * @param itemExportAmt the itemExportAmt to set
     */
    public void setItemExportAmt(String itemExportAmt) {
        this.itemExportAmt = itemExportAmt;
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
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the svcLevel1
     */
    public String getSvcLevel1() {
        return svcLevel1;
    }

    /**
     * @param svcLevel1 the svcLevel1 to set
     */
    public void setSvcLevel1(String svcLevel1) {
        this.svcLevel1 = svcLevel1;
    }

    /**
     * @return the svcLevel2
     */
    public String getSvcLevel2() {
        return svcLevel2;
    }

    /**
     * @param svcLevel2 the svcLevel2 to set
     */
    public void setSvcLevel2(String svcLevel2) {
        this.svcLevel2 = svcLevel2;
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
     * @return the isDeleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return the dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the dateUpdated
     */
    public String getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated the dateUpdated to set
     */
    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
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

    public T_BIL_DetailInfo(){
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
	 * @return the itemSubTotal
	 */
	public String getItemSubTotal() {
		return itemSubTotal;
	}

	/**
	 * @param itemSubTotal the itemSubTotal to set
	 */
	public void setItemSubTotal(String itemSubTotal) {
		this.itemSubTotal = itemSubTotal;
	}

	/**
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}

	/**
	 * @param taxCode the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	/**
	 * @return the taxRate
	 */
	public String getTaxRate() {
		return taxRate;
	}

	/**
	 * @param taxRate the taxRate to set
	 */
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	/**
	 * @return the itemExportGST
	 */
	public String getItemExportGST() {
		return itemExportGST;
	}

	/**
	 * @param itemExportGST the itemExportGST to set
	 */
	public void setItemExportGST(String itemExportGST) {
		this.itemExportGST = itemExportGST;
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
}
