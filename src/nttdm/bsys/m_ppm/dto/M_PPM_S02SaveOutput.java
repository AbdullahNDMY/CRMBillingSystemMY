/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S02SaveOutput.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.Vendor;

/**
 * M_PPM_S02SaveOutput<br/>
 * Output information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02SaveOutput {
    private Plan plan = null;
    
    private Integer idPlan;

 private List<Service> cboCategoryList;
    
    private List<Vendor> cboVendorList;
    
    private String allGstSameFlg;
    
    private String allCategorySameFlg;
    
    /**
     * @return the cboCategoryList
     */
    public List<Service> getCboCategoryList() {
        return cboCategoryList;
    }

    /**
     * @param cboCategoryList the cboCategoryList to set
     */
    public void setCboCategoryList(List<Service> cboCategoryList) {
        this.cboCategoryList = cboCategoryList;
    }

    /**
     * @return the cboVendorList
     */
    public List<Vendor> getCboVendorList() {
        return cboVendorList;
    }

    /**
     * @param cboVendorList the cboVendorList to set
     */
    public void setCboVendorList(List<Vendor> cboVendorList) {
        this.cboVendorList = cboVendorList;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    /**
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
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
}
