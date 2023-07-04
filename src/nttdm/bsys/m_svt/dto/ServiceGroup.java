/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVT
 * SERVICE NAME   : M_SVT_S01
 * FUNCTION       : ServiceGroup
 * FILE NAME      : ServiceGroup.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svt.dto;

/**
 * ServiceGroup<br>
 * <ul>
 * <li>An Object to Express a brief group of services
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class ServiceGroup {
	
	private String serviceGroup;
	private String serviceGroupName;
	
	public String getServiceGroup() {
		return serviceGroup;
	}
	
	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}
	
	public String getServiceGroupName() {
		return serviceGroupName;
	}
	
	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName;
	}
	
}
