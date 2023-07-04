/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (new/edit)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : object using for define Customer Plan
 * FILE NAME      : CustomerPlan.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * CustomerPlan.class<br>
 * <ul>
 * <li>define customer plan object</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class CustomerPlan {

	private String screen;
	
	private String idCustPlan;
	private String idSubInfo;
	private String idCust;
	private String planType;
	private String approveType;
	private String transactionType;
	private String billAccNo;
	private String newAcc;
	private String billAccAll;
	private String svcLevel1;
	private String svcLevel2;
	private String referencePlan;
	private String applicationDate;
	private String planStatus;
	
	private String billingInstruction;
	private String billCurrency;
	private String paymentMethod;
	private String expGrandTotal;
	private String fixedForex;
	
	private List<CustomerPlanService> services;
	private String idLogin;
	private String idAudit;
	
	private String svcLevel1Name;
	private String svcLevel2Name;
	private String billingInstructionName;
	private String paymentMethodName;
	private String billCurrencyName;
	
	private String idPlanParam;
	private String planNameParam;
	/**
	 * 0:StandardPlan plan ,1:StandardPlan-Multi
	 */
	private String multiPln;
	/**
	 * 0:not enabled, other value enabled
	 */
	private String transactionTypeFlg;
	
	private String addStdPln;
	private String addNonStdPln;
	private String addStdPlnMul;
	/**
	 * 0:not display,1:display
	 */
	private String m_jnmDisplayFlg;
	
	/**
	 * not null:click save button,null:not click save button
	 */
	private String clickSaveFlg;
	
	/**
	 * customer plan currency and M_PLAN_H currency is Different flag,null:not different,not null different
	 */
	private String custPlanMPlanCurDifFlg;
	
	/**
     * customer plan currency fifferent M_PLAN_H currency click yes button flag.
     */
    private String clickDifCurrencyYesFlg;
    
    private String GSTApplyAllChk;
    
    private String GSTApplyAllCbo;
    
    private String categoryApplyAllChk;
    
    private String categoryApplyAllCbo;
    
    private String serviceApplyAllCbo;
    
    /**
     * 0:same,1:not same
     */
    private String allGstSameFlg;
    
    /**
     * 0:same,1:not same
     */
    private String allCategorySameFlg;
    
    private String b_ssmIsUsed;

    private String fixedForexFlg;
    
    private String fromScreen;
    
    private String billType;
    
    private String addRadiusFlg;
    
    private String clickAddRadiusYesFlg;
    
    private String removeRadiusFlg;
    
    private String clickRemoveRadiusYesFlg;
    
    private String newAccCheckFlg;
    
	// #189 Start
	private String disBillingOption;
	// #189 En
	
	//#200, #201 wcbeh@20160926 - Master Location Display Condition
	private String masterLocationDisplayFlg;
	private String masterLocation;
	
	// B2_REQ002_AddTaxType
	private String taxTypeDisplay;
	private String taxType;
	private String taxTypeDefault;
	private String taxIdDefault;
	private String taxWord;
	
	
	public String getMasterLocation() {
		return masterLocation;
	}

	public void setMasterLocation(String masterLocation) {
		this.masterLocation = masterLocation;
	}

	public String getMasterLocationDisplayFlg() {
		return masterLocationDisplayFlg;
	}

	public void setMasterLocationDisplayFlg(String masterLocationDisplayFlg) {
		this.masterLocationDisplayFlg = masterLocationDisplayFlg;
	}

	public static CustomerPlan EmptyCustomerPlan() {
		CustomerPlan customerPlan = new CustomerPlan();
		customerPlan.transactionType = "";
		customerPlan.services = new AutoScaleList<CustomerPlanService>(new CustomerPlanService());
		customerPlan.billAccAll = "";
		return customerPlan;
	}
	
	public CustomerPlan() {
		// TODO Auto-generated constructor stub
		this.planStatus = "PS1";
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		this.applicationDate = fmt.format(new Date());
		this.transactionType = "IN";
		this.services = new AutoScaleList<CustomerPlanService>(new CustomerPlanService());
		this.billAccAll = "0";
	}
	
	/**
	 * @return the idSubInfo
	 */
	public String getIdSubInfo() {
		return idSubInfo;
	}

	/**
	 * @param idSubInfo the idSubInfo to set
	 */
	public void setIdSubInfo(String idSubInfo) {
		this.idSubInfo = idSubInfo;
	}
	
	/**
	 * @return the idCust
	 */
	public String getIdCust() {
		return idCust;
	}

	/**
	 * @param idCust the idCust to set
	 */
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @return the billAccNo
	 */
	public String getBillAccNo() {
		return billAccNo;
	}

	/**
	 * @param billAccNo the billAccNo to set
	 */
	public void setBillAccNo(String billAccNo) {
		this.billAccNo = billAccNo;
	}

	/**
	 * @return the newAcc
	 */
	public String getNewAcc() {
		return newAcc;
	}

	/**
	 * @param newAcc the newAcc to set
	 */
	public void setNewAcc(String newAcc) {
		this.newAcc = newAcc;
	}

	/**
	 * @return the billAccAll
	 */
	public String getBillAccAll() {
		return billAccAll;
	}

	/**
	 * @param billAccAll the billAccAll to set
	 */
	public void setBillAccAll(String billAccAll) {
		this.billAccAll = billAccAll;
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
	 * @param services the services to set
	 */
	public void setServices(List<CustomerPlanService> services) {
		this.services = services;
	}

	/**
	 * @return the services
	 */
	public List<CustomerPlanService> getServices() {
		return services;
	}

	/**
	 * @return the referencePlan
	 */
	public String getReferencePlan() {
		return referencePlan;
	}

	/**
	 * @param referencePlan the referencePlan to set
	 */
	public void setReferencePlan(String referencePlan) {
		this.referencePlan = referencePlan;
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
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the expGrandTotal
	 */
	public String getExpGrandTotal() {
		return expGrandTotal;
	}

	/**
	 * @param expGrandTotal the expGrandTotal to set
	 */
	public void setExpGrandTotal(String expGrandTotal) {
		this.expGrandTotal = expGrandTotal;
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
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return applicationDate;
	}

	/**
	 * @param planStatus the planStatus to set
	 */
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	/**
	 * @return the planStatus
	 */
	public String getPlanStatus() {
		return planStatus;
	}

	/**
	 * @param idCustPlan the idCustPlan to set
	 */
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}

	/**
	 * @return the idCustPlan
	 */
	public String getIdCustPlan() {
		return idCustPlan;
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
	 * @return the planType
	 */
	public String getPlanType() {
		return planType;
	}

	/**
	 * @param planType the planType to set
	 */
	public void setPlanType(String planType) {
		this.planType = planType;
	}

	/**
	 * @return the approveType
	 */
	public String getApproveType() {
		return approveType;
	}

	/**
	 * @param approveType the approveType to set
	 */
	public void setApproveType(String approveType) {
		this.approveType = approveType;
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
	 * @return the billingInstructionName
	 */
	public String getBillingInstructionName() {
		return billingInstructionName;
	}

	/**
	 * @param billingInstructionName the billingInstructionName to set
	 */
	public void setBillingInstructionName(String billingInstructionName) {
		this.billingInstructionName = billingInstructionName;
	}

	/**
	 * @return the paymentMethodName
	 */
	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	/**
	 * @param paymentMethodName the paymentMethodName to set
	 */
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	public void setBillCurrencyName(String billCurrencyName) {
		this.billCurrencyName = billCurrencyName;
	}

	public String getBillCurrencyName() {
		return billCurrencyName;
	}

	/**
	 * @param screen the screen to set
	 */
	public void setScreen(String screen) {
		this.screen = screen;
	}

	/**
	 * @return the screen
	 */
	public String getScreen() {
		return screen;
	}

    /**
     * @return the idPlanParam
     */
    public String getIdPlanParam() {
        return idPlanParam;
    }

    /**
     * @param idPlanParam the idPlanParam to set
     */
    public void setIdPlanParam(String idPlanParam) {
        this.idPlanParam = idPlanParam;
    }

    /**
     * @return the planNameParam
     */
    public String getPlanNameParam() {
        return planNameParam;
    }

    /**
     * @param planNameParam the planNameParam to set
     */
    public void setPlanNameParam(String planNameParam) {
        this.planNameParam = planNameParam;
    }

    /**
     * @return the multiPln
     */
    public String getMultiPln() {
        return multiPln;
    }

    /**
     * @param multiPln the multiPln to set
     */
    public void setMultiPln(String multiPln) {
        this.multiPln = multiPln;
    }

	public String getTransactionTypeFlg() {
		return transactionTypeFlg;
	}

	public void setTransactionTypeFlg(String transactionTypeFlg) {
		this.transactionTypeFlg = transactionTypeFlg;
	}

    /**
     * @return the addStdPln
     */
    public String getAddStdPln() {
        return addStdPln;
    }

    /**
     * @param addStdPln the addStdPln to set
     */
    public void setAddStdPln(String addStdPln) {
        this.addStdPln = addStdPln;
    }

    /**
     * @return the addNonStdPln
     */
    public String getAddNonStdPln() {
        return addNonStdPln;
    }

    /**
     * @param addNonStdPln the addNonStdPln to set
     */
    public void setAddNonStdPln(String addNonStdPln) {
        this.addNonStdPln = addNonStdPln;
    }

    /**
     * @return the addStdPlnMul
     */
    public String getAddStdPlnMul() {
        return addStdPlnMul;
    }

    /**
     * @param addStdPlnMul the addStdPlnMul to set
     */
    public void setAddStdPlnMul(String addStdPlnMul) {
        this.addStdPlnMul = addStdPlnMul;
    }

    /**
     * @return the clickSaveFlg
     */
    public String getClickSaveFlg() {
        return clickSaveFlg;
    }

    /**
     * @param clickSaveFlg the clickSaveFlg to set
     */
    public void setClickSaveFlg(String clickSaveFlg) {
        this.clickSaveFlg = clickSaveFlg;
    }

    /**
     * @return the m_jnmDisplayFlg
     */
    public String getM_jnmDisplayFlg() {
        return m_jnmDisplayFlg;
    }

    /**
     * @param m_jnmDisplayFlg the m_jnmDisplayFlg to set
     */
    public void setM_jnmDisplayFlg(String m_jnmDisplayFlg) {
        this.m_jnmDisplayFlg = m_jnmDisplayFlg;
    }

    /**
     * @return the custPlanMPlanCurDifFlg
     */
    public String getCustPlanMPlanCurDifFlg() {
        return custPlanMPlanCurDifFlg;
    }

    /**
     * @param custPlanMPlanCurDifFlg the custPlanMPlanCurDifFlg to set
     */
    public void setCustPlanMPlanCurDifFlg(String custPlanMPlanCurDifFlg) {
        this.custPlanMPlanCurDifFlg = custPlanMPlanCurDifFlg;
    }

    /**
     * @return the clickDifCurrencyYesFlg
     */
    public String getClickDifCurrencyYesFlg() {
        return clickDifCurrencyYesFlg;
    }

    /**
     * @param clickDifCurrencyYesFlg the clickDifCurrencyYesFlg to set
     */
    public void setClickDifCurrencyYesFlg(String clickDifCurrencyYesFlg) {
        this.clickDifCurrencyYesFlg = clickDifCurrencyYesFlg;
    }

    /**
     * @return the gSTApplyAllChk
     */
    public String getGSTApplyAllChk() {
        return GSTApplyAllChk;
    }

    /**
     * @param gSTApplyAllChk the gSTApplyAllChk to set
     */
    public void setGSTApplyAllChk(String gSTApplyAllChk) {
        GSTApplyAllChk = gSTApplyAllChk;
    }

    /**
     * @return the gSTApplyAllCbo
     */
    public String getGSTApplyAllCbo() {
        if(GSTApplyAllCbo==null){
            GSTApplyAllCbo="1";
        }
        return GSTApplyAllCbo;
    }

    /**
     * @param gSTApplyAllCbo the gSTApplyAllCbo to set
     */
    public void setGSTApplyAllCbo(String gSTApplyAllCbo) {
        GSTApplyAllCbo = gSTApplyAllCbo;
    }

    /**
     * @return the categoryApplyAllChk
     */
    public String getCategoryApplyAllChk() {
        return categoryApplyAllChk;
    }

    /**
     * @param categoryApplyAllChk the categoryApplyAllChk to set
     */
    public void setCategoryApplyAllChk(String categoryApplyAllChk) {
        this.categoryApplyAllChk = categoryApplyAllChk;
    }

    /**
     * @return the categoryApplyAllCbo
     */
    public String getCategoryApplyAllCbo() {
        return categoryApplyAllCbo;
    }

    /**
     * @param categoryApplyAllCbo the categoryApplyAllCbo to set
     */
    public void setCategoryApplyAllCbo(String categoryApplyAllCbo) {
        this.categoryApplyAllCbo = categoryApplyAllCbo;
    }

    /**
     * @return the serviceApplyAllCbo
     */
    public String getServiceApplyAllCbo() {
        return serviceApplyAllCbo;
    }

    /**
     * @param serviceApplyAllCbo the serviceApplyAllCbo to set
     */
    public void setServiceApplyAllCbo(String serviceApplyAllCbo) {
        this.serviceApplyAllCbo = serviceApplyAllCbo;
    }

    /**
     * @return the allGstSameFlg
     */
    public String getAllGstSameFlg() {
        return allGstSameFlg;
    }

    /**
     * @param allGstSameFlg the allGstSameFlg to set
     */
    public void setAllGstSameFlg(String allGstSameFlg) {
        this.allGstSameFlg = allGstSameFlg;
    }

    /**
     * @return the allCategorySameFlg
     */
    public String getAllCategorySameFlg() {
        return allCategorySameFlg;
    }

    /**
     * @param allCategorySameFlg the allCategorySameFlg to set
     */
    public void setAllCategorySameFlg(String allCategorySameFlg) {
        this.allCategorySameFlg = allCategorySameFlg;
    }

    /**
     * @return the b_ssmIsUsed
     */
    public String getB_ssmIsUsed() {
        return b_ssmIsUsed;
    }

    /**
     * @param b_ssmIsUsed the b_ssmIsUsed to set
     */
    public void setB_ssmIsUsed(String b_ssmIsUsed) {
        this.b_ssmIsUsed = b_ssmIsUsed;
    }

    /**
     * @return the fixedForexFlg
     */
    public String getFixedForexFlg() {
        return fixedForexFlg;
    }

    /**
     * @param fixedForexFlg the fixedForexFlg to set
     */
    public void setFixedForexFlg(String fixedForexFlg) {
        this.fixedForexFlg = fixedForexFlg;
    }

    /**
     * @return the fromScreen
     */
    public String getFromScreen() {
        return fromScreen;
    }

    /**
     * @param fromScreen the fromScreen to set
     */
    public void setFromScreen(String fromScreen) {
        this.fromScreen = fromScreen;
    }

    /**
     * @return the billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType the billType to set
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * @return the addRadiusFlg
     */
    public String getAddRadiusFlg() {
        return addRadiusFlg;
    }

    /**
     * @param addRadiusFlg the addRadiusFlg to set
     */
    public void setAddRadiusFlg(String addRadiusFlg) {
        this.addRadiusFlg = addRadiusFlg;
    }

    /**
     * @return the clickAddRadiusYesFlg
     */
    public String getClickAddRadiusYesFlg() {
        return clickAddRadiusYesFlg;
    }

    /**
     * @param clickAddRadiusYesFlg the clickAddRadiusYesFlg to set
     */
    public void setClickAddRadiusYesFlg(String clickAddRadiusYesFlg) {
        this.clickAddRadiusYesFlg = clickAddRadiusYesFlg;
    }

    /**
     * @return the removeRadiusFlg
     */
    public String getRemoveRadiusFlg() {
        return removeRadiusFlg;
    }

    /**
     * @param removeRadiusFlg the removeRadiusFlg to set
     */
    public void setRemoveRadiusFlg(String removeRadiusFlg) {
        this.removeRadiusFlg = removeRadiusFlg;
    }

    /**
     * @return the clickRemoveRadiusYesFlg
     */
    public String getClickRemoveRadiusYesFlg() {
        return clickRemoveRadiusYesFlg;
    }

    /**
     * @param clickRemoveRadiusYesFlg the clickRemoveRadiusYesFlg to set
     */
    public void setClickRemoveRadiusYesFlg(String clickRemoveRadiusYesFlg) {
        this.clickRemoveRadiusYesFlg = clickRemoveRadiusYesFlg;
    }

    /**
     * @return the newAccCheckFlg
     */
    public String getNewAccCheckFlg() {
        return newAccCheckFlg;
    }

    /**
     * @param newAccCheckFlg the newAccCheckFlg to set
     */
    public void setNewAccCheckFlg(String newAccCheckFlg) {
        this.newAccCheckFlg = newAccCheckFlg;
    }

	/**
	 * @return the disBillingOption
	 */
	public String getDisBillingOption() {
		return disBillingOption;
	}

	/**
	 * @param disBillingOption the disBillingOption to set
	 */
	public void setDisBillingOption(String disBillingOption) {
		this.disBillingOption = disBillingOption;
	}

	/**
	 * @return the taxTypeDisplay
	 */
	public String getTaxTypeDisplay() {
		return taxTypeDisplay;
	}

	/**
	 * @param taxTypeDisplay the taxTypeDisplay to set
	 */
	public void setTaxTypeDisplay(String taxTypeDisplay) {
		this.taxTypeDisplay = taxTypeDisplay;
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
	 * @return the taxTypeDefault
	 */
	public String getTaxTypeDefault() {
		return taxTypeDefault;
	}

	/**
	 * @param taxTypeDefault the taxTypeDefault to set
	 */
	public void setTaxTypeDefault(String taxTypeDefault) {
		this.taxTypeDefault = taxTypeDefault;
	}

	/**
	 * @return the taxIdDefault
	 */
	public String getTaxIdDefault() {
		return taxIdDefault;
	}

	/**
	 * @param taxIdDefault the taxIdDefault to set
	 */
	public void setTaxIdDefault(String taxIdDefault) {
		this.taxIdDefault = taxIdDefault;
	}

	/**
	 * @return the taxWord
	 */
	public String getTaxWord() {
		return taxWord;
	}

	/**
	 * @param taxWord the taxWord to set
	 */
	public void setTaxWord(String taxWord) {
		this.taxWord = taxWord;
	}


}
