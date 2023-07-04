/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S02AjaxOutput.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.m_ppm.bean.Service;

/**
 * M_PPM_S02AjaxOutput<br/>
 * Output information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02AjaxOutput {
	
	private List<Service> cboSvcLevel2List;

	private List<Service> cboPlanList;

	private List<Service> cboPlanDetailList;
	
	private String ppmOptionSvc;

	public List<Service> getCboSvcLevel2List() {
		return cboSvcLevel2List;
	}

	public void setCboSvcLevel2List(List<Service> cboSvcLevel2List) {
		this.cboSvcLevel2List = cboSvcLevel2List;
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
}
