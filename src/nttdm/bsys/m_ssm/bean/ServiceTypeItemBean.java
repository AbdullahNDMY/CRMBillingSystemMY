/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : ServiceTypeItemBean
 * FILE NAME      : ServiceTypeItemBean.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.bean;

/**
 * ServiceTypeItemBean<br>
 * <ul>
 * <li>A Bean to Express a Service and Subscription Mapping
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class ServiceTypeItemBean {
	
	private String idService;
	private String infoId;
	private String entryValue;
	private String reportValue;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String idInfo) {
		this.infoId = idInfo;
	}

	public String getIdService() {
		return idService;
	}

	public void setIdService(String idService) {
		this.idService = idService;
	}

	public String getEntryValue() {
		return entryValue;
	}

	public void setEntryValue(String entryValue) {
		this.entryValue = entryValue;
	}
	
	public String getReportValue() {
		return reportValue;
	}

	public void setReportValue(String reportValue) {
		this.reportValue = reportValue;
	}

	@Override
	public boolean equals(Object aThat) {
		if (!(aThat instanceof ServiceTypeItemBean)) {
			return false;
		}

		ServiceTypeItemBean object = (ServiceTypeItemBean) aThat;

		String oldIdService = this.getIdService().trim();
		String newIdService = object.getIdService();
		
		return oldIdService.equalsIgnoreCase(newIdService);
	}
}