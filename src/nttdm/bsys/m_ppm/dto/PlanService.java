/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM
 * FUNCTION : Form Bean
 * FILE NAME : PlanService.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 * 2011/09/27 [Duoc Nguyen] fix bug #2674 on portal
 * 2011/10/21 [Duoc Nguyen] update to M_PPM_P1-11_r4_20111014.xlsx
 */
package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;
import nttdm.bsys.m_ppm.bean.Service;

/**
 * PlanService<br/>
 * Bean contain PlanService information<br/>
 * Use for make <b>New, Edit, View</b><br/>
 * @author NTTData Vietnam
 * @version 1.1
 */
public class PlanService {
	private String type;

	private String tbxServiceName;

	private String cboSvcLevel1;

	private String cboSvcLevel2;

	private String cboRateType;

	private String cboRateMode;

	private String tbxRate;

	private String tbxGST;

	private String taxRateDescription;

	private List<Service> cboSvcLevel2List;

	private List<Service> cboPlanList;

	private List<Service> cboPlanDetailList;

	private List<PlanServiceDetail> details;

	private Integer inUsed;

	private Integer inMapping;

	// for save database
	private Integer idPlan;

	private String idLogin;

	private Integer idAudit;

	private Integer idPlanGrp;
	
	private String cboRateType2;
	private String cboRateType2Value;

	/**
	 * @return the cboRateType2Value
	 */
	public String getCboRateType2Value() {
		return cboRateType2Value;
	}

	/**
	 * @param cboRateType2Value the cboRateType2Value to set
	 */
	public void setCboRateType2Value(String cboRateType2Value) {
		this.cboRateType2Value = cboRateType2Value;
	}

	/**
	 * @return the cboRateType2
	 */
	public String getCboRateType2() {
		return cboRateType2;
	}

	/**
	 * @param cboRateType2 the cboRateType2 to set
	 */
	public void setCboRateType2(String cboRateType2) {
		this.cboRateType2 = cboRateType2;
	}

	public PlanService() {
		cboPlanList = new AutoScaleList<Service>(new Service());
		cboPlanDetailList = new AutoScaleList<Service>(new Service());
		details = new AutoScaleList<PlanServiceDetail>(new PlanServiceDetail());
	}

	public String getTaxRateDescription() {
		return taxRateDescription;
	}

	public void setTaxRateDescription(String taxRateDescription) {
		this.taxRateDescription = taxRateDescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public List<PlanServiceDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PlanServiceDetail> details) {
		this.details = details;
	}

	public String getTbxServiceName() {
		return tbxServiceName;
	}

	public void setTbxServiceName(String tbxServiceName) {
		this.tbxServiceName = tbxServiceName;
	}

	public String getCboSvcLevel1() {
		return cboSvcLevel1;
	}

	public void setCboSvcLevel1(String cboSvcLevel1) {
		this.cboSvcLevel1 = cboSvcLevel1;
	}

	public String getCboSvcLevel2() {
		return cboSvcLevel2;
	}

	public void setCboSvcLevel2(String cboSvcLevel2) {
		this.cboSvcLevel2 = cboSvcLevel2;
	}

	public String getCboRateType() {
		return cboRateType;
	}

	public void setCboRateType(String cboRateType) {
		this.cboRateType = cboRateType;
	}

	public String getCboRateMode() {
		return cboRateMode;
	}

	public void setCboRateMode(String cboRateMode) {
		this.cboRateMode = cboRateMode;
	}

	public String getTbxRate() {
		return tbxRate;
	}

	public void setTbxRate(String tbxRate) {
		try {
			// fix for case 0.0 < tbxRate < 1.0
			this.tbxRate = String.valueOf(Double.parseDouble(tbxRate));
		} catch (Exception e) {
			this.tbxRate = tbxRate;
		}
	}

	public String getTbxGST() {
	    if(tbxGST==null){
            tbxGST="1";
        }
		return tbxGST;
	}

	public void setTbxGST(String tbxGST) {
		this.tbxGST = tbxGST;
	}

	public List<Service> getCboPlanList() {
		return cboPlanList;
	}

	public void setCboPlanList(List<Service> cboPlanList) {
		this.cboPlanList = cboPlanList;
	}

	public List<Service> getCboPlanDetailList() {
		return cboPlanDetailList;
	}

	public void setCboPlanDetailList(List<Service> cboPlanDetailList) {
		this.cboPlanDetailList = cboPlanDetailList;
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

	public Integer getIdPlanGrp() {
		return idPlanGrp;
	}

	public void setIdPlanGrp(Integer idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
	}

	public List<Service> getCboSvcLevel2List() {
		return cboSvcLevel2List;
	}

	public void setCboSvcLevel2List(List<Service> cboSvcLevel2List) {
		this.cboSvcLevel2List = cboSvcLevel2List;
	}

	public Integer getInUsed() {
		return inUsed;
	}

	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
	}

	public Integer getInMapping() {
		return inMapping;
	}

	public void setInMapping(Integer inMapping) {
		this.inMapping = inMapping;
	}

}
