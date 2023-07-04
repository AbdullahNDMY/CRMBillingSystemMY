/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : ServiceObjectBean
 * FILE NAME      : ServiceObjectBean.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.bean;

/**
 * ServiceObjectBean<br>
 * <ul>
 * <li>A Bean to Express a Service of Service and Plan Mapping
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class ServiceObjectBean {
	
	private String serviceGroup;
	private String serviceId;
	private String serviceDesc;
	
	public String getServiceGroup() {
		return serviceGroup;
	}
	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
}
