/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : ServiceGroupBean
 * FILE NAME      : ServiceGroupBean.java
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
 * ServiceGroupBean<br>
 * <ul>
 * <li>A Bean to Express a Service group
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class ServiceGroupBean {

  	private int svc_grp; 
  	private String svc_grp_name; 
  	private String acc_code;
  	private String origin_code; 
  	private String product_code;
  	private String remark;
	private Date date_created;
	private Date date_updated;
	private String id_login ;
	
	public int getSvc_grp() {
		return svc_grp;
	}
	
	public void setSvc_grp(int svc_grp) {
		this.svc_grp = svc_grp;
	}
	
	public String getSvc_grp_name() {
		return svc_grp_name;
	}
	
	public void setSvc_grp_name(String svc_grp_name) {
		this.svc_grp_name = svc_grp_name;
	}
	
	public String getAcc_code() {
		return acc_code;
	}
	
	public void setAcc_code(String acc_code) {
		this.acc_code = acc_code;
	}
	
	public String getOrigin_code() {
		return origin_code;
	}
	
	public void setOrigin_code(String origin_code) {
		this.origin_code = origin_code;
	}
	
	public String getProduct_code() {
		return product_code;
	}
	
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
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
