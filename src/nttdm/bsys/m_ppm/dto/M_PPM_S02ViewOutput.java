/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S02ViewOutput.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.Vendor;

/**
 * M_PPM_S02ViewOutput<br/>
 * Output information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02ViewOutput {
	private Plan plan;

	private List<Service> cboCategoryList;

	private List<Vendor> cboVendorList;
	
	//#436: [B2-2][REQ003]NewTaxCode Start
	private String taxWord;
	
	private String taxDefaultId;
	//#436: [B2-2][REQ003]NewTaxCode End

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<Service> getCboCategoryList() {
		return cboCategoryList;
	}

	public void setCboCategoryList(List<Service> cboCategoryList) {
		this.cboCategoryList = cboCategoryList;
	}

	public List<Vendor> getCboVendorList() {
		return cboVendorList;
	}

	public void setCboVendorList(List<Vendor> cboVendorList) {
		this.cboVendorList = cboVendorList;
	}

	public String getTaxWord() {
		return taxWord;
	}

	public void setTaxWord(String taxWord) {
		this.taxWord = taxWord;
	}

	public String getTaxDefaultId() {
		return taxDefaultId;
	}

	public void setTaxDefaultId(String taxDefaultId) {
		this.taxDefaultId = taxDefaultId;
	}
	
}
