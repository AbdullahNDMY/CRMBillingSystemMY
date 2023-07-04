/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM
 * FUNCTION : Java Bean
 * FILE NAME : Service.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.bean;

/**
 * Service<br/>
 * Bean contain Service information of plan<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class Service {
	private Integer idService;
	private Integer svcGrp;
	private String svcDesc;
	private String svcCategory;
	private String accCode;
	private String gst;
	private Double uqty;

	public Integer getIdService() {
		return idService;
	}

	public void setIdService(Integer idService) {
		this.idService = idService;
	}

	public Integer getSvcGrp() {
		return svcGrp;
	}

	public void setSvcGrp(Integer svcGrp) {
		this.svcGrp = svcGrp;
	}

	public String getSvcDesc() {
		return svcDesc;
	}

	public void setSvcDesc(String svcDesc) {
		this.svcDesc = svcDesc;
	}

	public String getSvcCategory() {
		return svcCategory;
	}

	public void setSvcCategory(String svcCategory) {
		this.svcCategory = svcCategory;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public Double getUqty() {
		return uqty;
	}

	public void setUqty(Double uqty) {
		this.uqty = uqty;
	}
}