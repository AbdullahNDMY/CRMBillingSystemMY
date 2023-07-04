/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM
 * FUNCTION : Java Bean
 * FILE NAME : ServiceGroup.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.bean;

/**
 * ServiceGroup<br/>
 * Bean contain Service Group information of plan<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class ServiceGroup {
	
	private Integer svcGrp;
	
	private String svcGrpName;
	
	private String accCode;
	
	private String originCode;
	
	private String productCode;
	
	private String remark;

	public Integer getSvcGrp() {
		return svcGrp;
	}

	public void setSvcGrp(Integer svcGrp) {
		this.svcGrp = svcGrp;
	}

	public String getSvcGrpName() {
		return svcGrpName;
	}

	public void setSvcGrpName(String svcGrpName) {
		this.svcGrpName = svcGrpName;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
