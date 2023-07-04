/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : M_SVCBean
 * FILE NAME      : M_SVCBean.java
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
 * M_SVCBean<br>
 * <ul>
 * <li>A Bean to Express a Service
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_SVCBean {

	private int id_service;
	private int svc_grp;
	private String svc_category;
	private String svc_desc;
	private String acc_code;
	private int uqty;
	private String gst;
	private String is_deleted;
	private Date date_created;
	private Date date_updated;
	private String id_login;

	public int getId_service() {
		return id_service;
	}

	public void setId_service(int id_service) {
		this.id_service = id_service;
	}

	public int getSvc_grp() {
		return svc_grp;
	}

	public void setSvc_grp(int svc_grp) {
		this.svc_grp = svc_grp;
	}

	public String getSvc_category() {
		return svc_category;
	}

	public void setSvc_category(String svc_category) {
		this.svc_category = svc_category;
	}

	public String getSvc_desc() {
		return svc_desc;
	}

	public void setSvc_desc(String svc_desc) {
		this.svc_desc = svc_desc;
	}

	public String getAcc_code() {
		return acc_code;
	}

	public void setAcc_code(String acc_code) {
		this.acc_code = acc_code;
	}

	public int getUqty() {
		return uqty;
	}

	public void setUqty(int uqty) {
		this.uqty = uqty;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
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
