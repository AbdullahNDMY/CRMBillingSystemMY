/**
 * @(#)M_PPM_S02CheckError2Output.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/28
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.Vendor;

/**
 * @author gplai
 *
 */
public class M_PPM_S02CheckError2Output {

    private List<Service> cboCategoryList;
    
    private List<Vendor> cboVendorList;
    
    private Plan plan;

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
