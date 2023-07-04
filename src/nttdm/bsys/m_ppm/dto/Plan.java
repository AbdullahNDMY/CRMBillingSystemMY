/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM
 * FUNCTION : Form Bean
 * FILE NAME : Plan.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * Plan<br/>
 * Bean contain Plan information<br/>
 * Use for make <b>New, Edit, View</b><br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class Plan {
	private String txtPlanName;
	
	private String txtPlanDescription;
	
	private String rdbCustomerType;
	
	private String cboBillCurrenc;
	
	private List<PlanService> services;
	
	private Integer inUsed;
	
	//for save database
	private String idLogin;
	
	private Integer idAudit;
	
	private Integer idPlan;
	
	private String GSTApplyAllChk;
	
	private String GSTApplyAllCbo;
	
	private String categoryApplyAllChk;
	
	private String categoryApplyAllCbo;
	
	private String serviceApplyAllCbo;
	
	private Integer isSaveFlg;
	
	/**
	 * new,edit,edit2
	 */
	private String modelFlg;
	
	/**
	 * 0:same,1:not same
	 */
	private String allGstSameFlg;
	
	/**
	 * 0:same,1:not same
	 */
	private String allCategorySameFlg;
	
	private String ppmOptionSvc;
	
	public Plan() {
		services = new AutoScaleList<PlanService>(new PlanService());
	}

	public String getTxtPlanName() {
		return txtPlanName;
	}

	public void setTxtPlanName(String txtPlanName) {
		this.txtPlanName = txtPlanName;
	}

	public String getTxtPlanDescription() {
		return txtPlanDescription;
	}

	public void setTxtPlanDescription(String txtPlanDescription) {
		this.txtPlanDescription = txtPlanDescription;
	}

	public String getRdbCustomerType() {
		return rdbCustomerType;
	}

	public void setRdbCustomerType(String rdbCustomerType) {
		this.rdbCustomerType = rdbCustomerType;
	}

	public String getCboBillCurrenc() {
		return cboBillCurrenc;
	}

	public void setCboBillCurrenc(String cboBillCurrenc) {
		this.cboBillCurrenc = cboBillCurrenc;
	}

	public List<PlanService> getServices() {
		return services;
	}

	public void setServices(List<PlanService> services) {
		this.services = services;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public Integer getInUsed() {
		return inUsed;
	}

	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
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
     * @return the modelFlg
     */
    public String getModelFlg() {
        return modelFlg;
    }

    /**
     * @param modelFlg the modelFlg to set
     */
    public void setModelFlg(String modelFlg) {
        this.modelFlg = modelFlg;
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
     * @return the ppmOptionSvc
     */
    public String getPpmOptionSvc() {
        return ppmOptionSvc;
    }

    /**
     * @param ppmOptionSvc the ppmOptionSvc to set
     */
    public void setPpmOptionSvc(String ppmOptionSvc) {
        this.ppmOptionSvc = ppmOptionSvc;
    }
    
    /**
     * @return the isSaveFlg
     */
    public Integer getIsSaveFlg() {
        return isSaveFlg;
    }

    /**
     * @param isSaveFlg the isSaveFlg to set
     */
    public void setIsSaveFlg(Integer isSaveFlg) {
        this.isSaveFlg = isSaveFlg;
    }

}
