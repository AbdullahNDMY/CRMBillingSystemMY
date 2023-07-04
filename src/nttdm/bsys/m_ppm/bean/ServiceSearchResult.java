/**
 * Billing System
 * 
 * SUBSYSTEM NAME : called by B-CPM-S02n screen
 * SERVICE NAME : M_PPM_04
 * FUNCTION : Java Bean
 * FILE NAME : ServiceSearchResult.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */

package nttdm.bsys.m_ppm.bean;

/**
 * ServiceSearchResult<br/>
 * Bean contain Service Group information of plan<br/>
 * 
 * @author NTTData
 * @version 1.1
 */
public class ServiceSearchResult {

	private String idPlan;
	private String gdcServiceName;
	private String gdcServiceDescr;
	private String gdcCategory;
	private String gdcSvclevel2;
	private String billCurrency;

	public String getBillCurrency() {
		return billCurrency;
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	public String getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	public String getGdcServiceName() {
		return gdcServiceName;
	}

	public void setGdcServiceName(String gdcServiceName) {
		this.gdcServiceName = gdcServiceName;
	}

	public String getGdcServiceDescr() {
		return gdcServiceDescr;
	}

	public void setGdcServiceDescr(String gdcServiceDescr) {
		this.gdcServiceDescr = gdcServiceDescr;
	}

	public String getGdcCategory() {
		return gdcCategory;
	}

	public void setGdcCategory(String gdcCategory) {
		this.gdcCategory = gdcCategory;
	}

	public String getGdcSvclevel2() {
		return gdcSvclevel2;
	}

	public void setGdcSvclevel2(String gdcSvclevel2) {
		this.gdcSvclevel2 = gdcSvclevel2;
	}

}
