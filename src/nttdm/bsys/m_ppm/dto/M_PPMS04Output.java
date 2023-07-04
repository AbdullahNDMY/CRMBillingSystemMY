/**
 * Billing System
 * 
 * SUBSYSTEM NAME : called by B-CPM-S02n screen
 * SERVICE NAME : M_PPM_04
 * FUNCTION : Output Bean
 * FILE NAME : M_PPMS04Output.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;

import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.ServiceGroup;
import nttdm.bsys.m_ppm.bean.ServiceSearchResult;

/**
 * M_PPMS04Output<br/>
 * Output data<br/>
 * 
 * @author NTTData
 * @version 1.1
 */
public class M_PPMS04Output {

	private String tbxServiceName = "";
	private String tbxServiceDescr = "";
	private String cboService = "";
	private String cboCategory = "";
	private String lblCustomerType = "";
	private String row = "";
	private String startIndex = "";
	private String totalCount = "";
	private List<ServiceGroup> cboCategoryList;
	private List<Service> cboServiceList;
	private List<ServiceSearchResult> searchResult;
	private String lblCustomerTypeShow;

	public String getLblCustomerTypeShow() {
		return lblCustomerTypeShow;
	}

	public void setLblCustomerTypeShow(String lblCustomerTypeShow) {
		this.lblCustomerTypeShow = lblCustomerTypeShow;
	}

	public List<ServiceSearchResult> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<ServiceSearchResult> searchResult) {
		this.searchResult = searchResult;
	}

	public String getTbxServiceName() {
		return tbxServiceName;
	}

	public List<ServiceGroup> getCboCategoryList() {
		return cboCategoryList;
	}

	public void setCboCategoryList(List<ServiceGroup> cboCategoryList) {
		this.cboCategoryList = cboCategoryList;
	}

	public List<Service> getCboServiceList() {
		return cboServiceList;
	}

	public void setCboServiceList(List<Service> cboServiceList) {
		this.cboServiceList = cboServiceList;
	}

	public void setTbxServiceName(String tbxServiceName) {
		this.tbxServiceName = tbxServiceName;
	}

	public String getTbxServiceDescr() {
		return tbxServiceDescr;
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

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
}
