/*
 * @(#)M_JNMFormBean.java
 *
 *
 */
package nttdm.bsys.m_jnm.dto;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class T_JOB_LIST extends ValidatorActionFormEx {

	private static final long serialVersionUID = -7466401838917442359L;
	
	private String id_cust;
	private String name_cust;
	private String job_no;
	private String id_jobno;	
	private String is_deleted;
	private String id_login;
	private String date_created;
	private String date_updated;
	private String hid_flag;
	private Integer id_audit;
	private Integer rowsPerPage;	
	private Integer startIndex;	

	/**
	 * get id_cust
	 * 
	 * @return id_cust
	 */
	public String getId_cust() {
		return id_cust;
	}
	
	/**
	 * setting id_cust
	 * 
	 * @param id_cust
	 */
	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}
	
	/**
	 * get name_cust
	 * 
	 * @return name_cust
	 */
	public String getName_cust() {
		return name_cust;
	}
	
	/**
	 * setting name_cust
	 * 
	 * @param name_cust
	 */
	public void setName_cust(String name_cust) {
		this.name_cust = name_cust;
	}

	/**
	 * get job_no
	 * 
	 * @return job_no
	 */
	public String getjob_no() {
		return job_no;
	}
	
	/**
	 * setting job_no
	 * 
	 * @param job_no
	 */
	public void setjob_no(String job_no) {
		this.job_no = job_no;
	}
	
	/**
	 * get id_jobno
	 * 
	 * @return id_jobno
	 */
	public String getId_jobno() {
		return id_jobno;
	}
	
	/**
	 * setting id_jobno
	 * 
	 * @param id_jobno
	 */
	public void setId_jobno(String id_jobno) {
		this.id_jobno = id_jobno;
	}

	/**
	 * get is_deleted
	 * 
	 * @return is_deleted
	 */
	public String getIs_deleted() {
		return is_deleted;
	}
	
	/**
	 * setting is_deleted
	 * 
	 * @param is_deleted
	 */
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	/**
	 * get id_login
	 * 
	 * @return id_login
	 */
	public String getId_login() {
		return id_login;
	}
	
	/**
	 * setting id_login
	 * 
	 * @param id_login
	 */
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	
	/**
	 * get date_created
	 * 
	 * @return date_created
	 */
	public String getDate_created() {
		return date_created;
	}
	
	/**
	 * setting date_created
	 * 
	 * @param date_created
	 */
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	
	/**
	 * get date_updated
	 * 
	 * @return date_updated
	 */
	public String getDate_updated() {
		return date_updated;
	}
	
	/**
	 * setting date_updated
	 * 
	 * @param date_updated
	 */
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	
	/**
	 * get hid_flag
	 * 
	 * @return hid_flag
	 */
	public String getHid_flag() {
		return hid_flag;
	}
	
	/**
	 * setting hid_flag
	 * 
	 * @param hid_flag
	 */
	public void setHid_flag(String hid_flag) {
		this.hid_flag = hid_flag;
	}
		

	/**
	 * get Id_audit
	 * 
	 * @return Id_audit
	 */
	public Integer getId_audit() {
		return id_audit;
	}


	/**
	 * setting Id_audit
	 * 
	 * @param Id_audit
	 */
	public void setId_audit(Integer id_audit) {
		this.id_audit = id_audit;
	}

	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}


}