/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S01
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S01_02Input.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;


/**
 * M_PPM_S01_02Input<br/>
 * Input information<br/>
 * 
 * @author NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S01_02Input {
    private String txtPlanName;

    private String cboCustomerType;

    private String txtPlanDescription;

    private String cboCategory;

    private String cboBillCurrency;

    private String startIndex;

    private String row;

    private String cboService;

    private String cboPlan;

    private String cboPlanDetail;

    private String txtSubPlanOptionName;

    public String getTxtSubPlanOptionName() {
        return txtSubPlanOptionName == null ? txtSubPlanOptionName : txtSubPlanOptionName.trim();
    }

    public void setTxtSubPlanOptionName(String txtSubPlanOptionName) {
        this.txtSubPlanOptionName = txtSubPlanOptionName;
    }

    public String getCboService() {
        return cboService;
    }

    public void setCboService(String cboService) {
        this.cboService = cboService;
    }

    public String getCboPlan() {
        return cboPlan;
    }

    public void setCboPlan(String cboPlan) {
        this.cboPlan = cboPlan;
    }

    public String getCboPlanDetail() {
        return cboPlanDetail;
    }

    public void setCboPlanDetail(String cboPlanDetail) {
        this.cboPlanDetail = cboPlanDetail;
    }

    public String getTxtPlanName() {
        return txtPlanName == null ? txtPlanName : txtPlanName.trim();
    }

    public void setTxtPlanName(String txtPlanName) {
        this.txtPlanName = txtPlanName;
    }

    public String getCboCustomerType() {
        return cboCustomerType;
    }

    public void setCboCustomerType(String cboCustomerType) {
        this.cboCustomerType = cboCustomerType;
    }

    public String getTxtPlanDescription() {
        return txtPlanDescription == null ? txtPlanDescription : txtPlanDescription.trim();
    }

    public void setTxtPlanDescription(String txtPlanDescription) {
        this.txtPlanDescription = txtPlanDescription;
    }

    public String getCboCategory() {
        return cboCategory;
    }

    public void setCboCategory(String cboCategory) {
        this.cboCategory = cboCategory;
    }

    public String getCboBillCurrency() {
        return cboBillCurrency;
    }

    public void setCboBillCurrency(String cboBillCurrency) {
        this.cboBillCurrency = cboBillCurrency;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
}
