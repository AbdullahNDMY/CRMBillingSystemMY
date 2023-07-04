/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : ServiceSubscriptionMappingBean
 * FILE NAME      : M_SSMBean.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.bean;

import java.util.Date;

/**
 * M_SSMBean<br>
 * <ul>
 * <li>A Bean to Express a setting of Service and Subscription mapping
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_SSMBean {
	
	private int svc_grp;
	private int id_service;
	private int info_id;
	private String entry;
	private String report;
	
	private String is_deleted;
	private Date date_created;
	private Date date_updated;
	private String id_login;
	
	public int getSvc_grp() {
		return svc_grp;
	}
	
	public void setSvc_grp(int svc_grp) {
		this.svc_grp = svc_grp;
	}
	
	public int getId_service() {
		return id_service;
	}
	
	public void setId_service(int id_service) {
		this.id_service = id_service;
	}
	
	public int getInfo_id() {
		return info_id;
	}
	
	public void setInfo_id(int info_id) {
		this.info_id = info_id;
	}
	
	public String getEntry() {
		return entry;
	}
	
	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	public String getReport() {
		return report;
	}
	
	public void setReport(String report) {
		this.report = report;
	}
	
	public String getIs_deleted() {
		return is_deleted;
	}
	
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	public Date getDate_created() {
		return date_created;
	}
	
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	public Date getDate_updated() {
		return date_updated;
	}
	
	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}
	
	public String getId_login() {
		return id_login;
	}
	
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}

}
