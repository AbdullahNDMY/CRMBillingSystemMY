/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM
 * FUNCTION : Form Bean
 * FILE NAME : PlanServiceDetail.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

/**
 * PlanServiceDetail<br/>
 * Bean contain PlanServiceDetail information<br/>
 * Use for make <b>New, Edit, View</b><br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class PlanServiceDetail {
	
	private String cboPlan;

	private String cboPlanDetail;

	private String cboVendor;
	
	//for save database
	private Integer idPlanSvc;
	
	private Integer idPlanGrp;
	
	private String idLogin;
	
	private Integer idAudit;
	
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

	public String getCboVendor() {
		return cboVendor;
	}

	public void setCboVendor(String cboVendor) {
		this.cboVendor = cboVendor;
	}

	public Integer getIdPlanGrp() {
		return idPlanGrp;
	}

	public void setIdPlanGrp(Integer idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
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

	public Integer getIdPlanSvc() {
		return idPlanSvc;
	}

	public void setIdPlanSvc(Integer idPlanSvc) {
		this.idPlanSvc = idPlanSvc;
	}
}
