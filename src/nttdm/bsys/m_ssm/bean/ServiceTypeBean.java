/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : ServiceTypeBean
 * FILE NAME      : ServiceTypeBean.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.bean;

/**
 * ServiceTypeBean<br>
 * <ul>
 * <li>A Bean to Express a Service type
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class ServiceTypeBean {
	
	private String typeName;
	private String idService;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIdService() {
		return idService;
	}

	public void setIdService(String idService) {
		this.idService = idService;
	}

	@Override
	public boolean equals(Object aThat) {
		if (!(aThat instanceof ServiceTypeBean)) {
			return false;
		}

		if (this == aThat)
	           return true;
		
		if (aThat == null)
	           return false;
		
		ServiceTypeBean object = (ServiceTypeBean) aThat;

		return object.getIdService().equalsIgnoreCase(this.getIdService());
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(idService);
	}
}
