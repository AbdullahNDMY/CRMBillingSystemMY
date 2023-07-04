/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S02EditInput.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

/**
 * M_PPM_S02EditInput<br/>
 * Input information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02EditInput {
	
	private Integer idPlan;

	private String cboSvcLevel1;
	
	private String cboSvcLevel2;
	
	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
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
   
}
