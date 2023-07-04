/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S01
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S01_01Output.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.m_ppm.bean.ServiceGroup;

/**
 * M_PPM_S01_01Output<br/>
 * Output information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S01_01Output {
	
	private List<ServiceGroup> cboCategoryList;
	
	public List<ServiceGroup> getCboCategoryList() {
		return cboCategoryList;
	}

	public void setCboCategoryList(List<ServiceGroup> cboCategoryList) {
		this.cboCategoryList = cboCategoryList;
	}
}