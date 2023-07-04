/**
 * Billing System
 * 
 * SUBSYSTEM NAME : B-CPM-S02n pop up
 * SERVICE NAME : M_PPM_S04
 * FUNCTION : Input Bean
 * FILE NAME : M_PPMS04Input.java
 * 
 * Copyright (C) 2012 NTTDATA Corporation
 */

package nttdm.bsys.m_ppm.dto;

/**
 * M_PPMS04Input<br/>
 * Input information<br/>
 * 
 * @author NTTData
 * @version 1.1
 */
public class M_PPMS04Input {

	private String tbxServiceName = "";
	private String tbxServiceDescr = "";
	private String cboService = "";
	private String cboCategory = "";
	private String lblCustomerType = "";
	private String row = "";
	private String startIndex = "";
	private String doSearch = "";
	private String doGetService = "";

	public String getTbxServiceName() {
		return tbxServiceName.trim();
	}

	public void setTbxServiceName(String tbxServiceName) {
		this.tbxServiceName = tbxServiceName;
	}

	public String getTbxServiceDescr() {
		return tbxServiceDescr.trim();
	}

	public void setTbxServiceDescr(String tbxServiceDescr) {
		this.tbxServiceDescr = tbxServiceDescr;
	}

	public String getCboService() {
		return cboService;
	}

	public void setCboService(String cboService) {
		this.cboService = cboService;
	}

	public String getCboCategory() {
		return cboCategory;
	}

	public void setCboCategory(String cboCategory) {
		this.cboCategory = cboCategory;
	}

	public String getLblCustomerType() {
		return lblCustomerType;
	}

	public void setLblCustomerType(String lblCustomerType) {
		this.lblCustomerType = lblCustomerType;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getDoSearch() {
		return doSearch;
	}

	public void setDoSearch(String doSearch) {
		this.doSearch = doSearch;
	}

	public String getDoGetService() {
		return doGetService;
	}

	public void setDoGetService(String doGetService) {
		this.doGetService = doGetService;
	}

}
