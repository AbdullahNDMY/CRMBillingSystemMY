/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (new/edit)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : object using for Customer SubPlan
 * FILE NAME      : CustomerSubPlan.java
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
 * CustomerSubPlan.class<br>
 * <ul>
 * <li>define customer sub plan object</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class CustomerSubPlan {

	private String idCustPlanLink;
	private String idCustPlanGrp;
	private String planDesc;
	private String quantity;
	private String unitPrice;
	private String amount;
	private String jobNo;
	private String itemDesc;
	private String idPlan;
	private String idPlanGrp;
	private String planName;
	private String itemGrpName;
	private String svcLevel1;
	private String svcLevel2;
	private String rateType;
	private String rateType2;
	private String rateMode;
	private String rate;
	private String applyGst;
	private String itemType;
	private String currency;
	private String isDiscountOneTime;
	private String discamount;
	private String custPo;
	//Use for 'LineDesc' ,when the 'SubPlanDetail' is empty,then show svcLevel2Name in Screen
    private String planLineDesc;
	public String getIsDiscountOneTime() {
        return isDiscountOneTime;
    }

    public void setIsDiscountOneTime(String isDiscountOneTime) {
        this.isDiscountOneTime = isDiscountOneTime;
    }

	public String getDiscamount() {
        return discamount;
    }

    public void setDiscamount(String discamount) {
        this.discamount = discamount;
    }

	private List<CustomerSubPlanDetail> subPlanDetails;
	private String idLogin;
	private String idAudit;
	
	private String svcLevel1Name;
	private String svcLevel2Name;
	private String rateTypeName;
	private String rateType2Name;
	private String rateModeName;
	
	private String isDisplayJobNo;
	
	// #200, #201 wcbeh@20160921 - Master Location
	private String subLocation;

	public String getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

	public CustomerSubPlan() {
		this.subPlanDetails = new AutoScaleList<CustomerSubPlanDetail>(new CustomerSubPlanDetail());
	}
	
	/**
	 * @param subPlanDetails the subPlanDetails to set
	 */
	public void setSubPlanDetails(List<CustomerSubPlanDetail> subPlanDetails) {
		this.subPlanDetails = subPlanDetails;
	}

	/**
	 * @return the subPlanDetails
	 */
	public List<CustomerSubPlanDetail> getSubPlanDetails() {
		return subPlanDetails;
	}

	/**
	 * @return the planDesc
	 */
	public String getPlanDesc() {
		return planDesc;
	}

	/**
	 * @param planDesc the planDesc to set
	 */
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unitPrice
	 */
	public String getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
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
	 * @return the idPlan
	 */
	public String getIdPlan() {
		return idPlan;
	}

	/**
	 * @param idPlan the idPlan to set
	 */
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	/**
	 * @return the idPlanGrp
	 */
	public String getIdPlanGrp() {
		return idPlanGrp;
	}

	/**
	 * @param idPlanGrp the idPlanGrp to set
	 */
	public void setIdPlanGrp(String idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
	}

	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * @return the rateType
	 */
	public String getRateType() {
		return rateType;
	}

	/**
	 * @param rateType the rateType to set
	 */
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	/**
     * @return the rateType2
     */
	public String getRateType2() {
        return rateType2;
    }

	/**
     * @param rateType2 the rateType2 to set
     */
    public void setRateType2(String rateType2) {
        this.rateType2 = rateType2;
    }

    /**
	 * @return the rateMode
	 */
	public String getRateMode() {
		return rateMode;
	}

	/**
	 * @param rateMode the rateMode to set
	 */
	public void setRateMode(String rateMode) {
		this.rateMode = rateMode;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
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
	 * @param itemGrpName the itemGrpName to set
	 */
	public void setItemGrpName(String itemGrpName) {
		this.itemGrpName = itemGrpName;
	}

	/**
	 * @return the itemGrpName
	 */
	public String getItemGrpName() {
		return itemGrpName;
	}

	/**
	 * @return the svcLevel1Name
	 */
	public String getSvcLevel1Name() {
		return svcLevel1Name;
	}

	/**
	 * @param svcLevel1Name the svcLevel1Name to set
	 */
	public void setSvcLevel1Name(String svcLevel1Name) {
		this.svcLevel1Name = svcLevel1Name;
	}

	/**
	 * @return the svcLevel2Name
	 */
	public String getSvcLevel2Name() {
		return svcLevel2Name;
	}

	/**
	 * @param svcLevel2Name the svcLevel2Name to set
	 */
	public void setSvcLevel2Name(String svcLevel2Name) {
		this.svcLevel2Name = svcLevel2Name;
	}

	/**
	 * @return the rateTypeName
	 */
	public String getRateTypeName() {
		return rateTypeName;
	}

	/**
	 * @param rateTypeName the rateTypeName to set
	 */
	public void setRateTypeName(String rateTypeName) {
		this.rateTypeName = rateTypeName;
	}

	public String getRateType2Name() {
        return rateType2Name;
    }

    public void setRateType2Name(String rateType2Name) {
        this.rateType2Name = rateType2Name;
    }

    /**
	 * @return the rateModeName
	 */
	public String getRateModeName() {
		return rateModeName;
	}

	/**
	 * @param rateModeName the rateModeName to set
	 */
	public void setRateModeName(String rateModeName) {
		this.rateModeName = rateModeName;
	}
	
	/**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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

    /**
     * @return the planLineDesc
     */
    public String getPlanLineDesc() {
        return planLineDesc;
    }

    /**
     * @param planLineDesc the planLineDesc to set
     */
    public void setPlanLineDesc(String planLineDesc) {
        this.planLineDesc = planLineDesc;
    }
    
    /**
	 * 
	 * @param propertyName
	 * @return
	 */
	public Object getPropertyByName(String propertyName) {
		if (propertyName.equals("idCustPlanLink")) {
			return this.idCustPlanLink;
		} else if (propertyName.equals("idCustPlanGrp")) {
			return this.idCustPlanGrp;
		} else if (propertyName.equals("planDesc")) {
			return this.planDesc;
		} else if (propertyName.equals("quantity")) {
			return this.quantity;
		} else if (propertyName.equals("unitPrice")) {
			return this.unitPrice;
		} else if (propertyName.equals("amount")) {
			return this.amount;
		} else if (propertyName.equals("jobNo")) {
			return this.jobNo;
		} else if (propertyName.equals("itemDesc")) {
			return this.itemDesc;
		} else if (propertyName.equals("idPlan")) {
			return this.idPlan;
		} else if (propertyName.equals("idPlanGrp")) {
			return this.idPlanGrp;
		} else if (propertyName.equals("planName")) {
			return this.planName;
		} else if (propertyName.equals("itemGrpName")) {
			return this.itemGrpName;
		} else if (propertyName.equals("svcLevel1")) {
			return this.svcLevel1;
		} else if (propertyName.equals("svcLevel2")) {
			return this.svcLevel2;
		} else if (propertyName.equals("rateType")) {
			return this.rateType;
		} else if (propertyName.equals("rateType2")) {
            return this.rateType2;
		} else if (propertyName.equals("rateMode")) {
			return this.rateMode;
		} else if (propertyName.equals("rate")) {
			return this.rate;
		} else if (propertyName.equals("applyGst")) {
			return this.applyGst;
		} else if (propertyName.equals("itemType")) {
			return this.itemType;
		} else if (propertyName.equals("discamount")) {
            return this.discamount;
        }else if (propertyName.equals("isDiscountOneTime")) {
            return this.isDiscountOneTime;
        }
		return null;
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
}
