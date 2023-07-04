/*
 * @(#)M_JNMFormBean.java
 *
 *
 */
package nttdm.bsys.m_jnm.bean;

import java.util.List;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.m_jnm.dto.T_JOB_LIST;
import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class M_JNMFormBean extends ValidatorActionFormEx {

	private static final long serialVersionUID = -7466401838917442359L;

	private Integer rowsPerPage = 10;
	private Integer startIndex = 0;
	private String id_cust;
	private String name_cust;
	private String hid_id_cust;
	private String job_no;
	private String id_jobno;
	private String clickEvent;
	private String totalCount;
	private List<T_JOB_LIST> jnmInfos;
	private String hid_id_jobno;
	private List<LabelValueBean> listCustomer;
	private String refreshFlg;

	public List<LabelValueBean> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<LabelValueBean> listCustomer) {
		this.listCustomer = listCustomer;
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
	 * get hid_id_cust
	 * 
	 * @return hid_id_cust
	 */
	public String getHid_id_cust() {
		return hid_id_cust;
	}

	/**
	 * setting hid_id_cust
	 * 
	 * @param hid_id_cust
	 */
	public void setHid_id_cust(String hid_id_cust) {
		this.hid_id_cust = hid_id_cust;
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
	 * get id_job_no
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
	 * get clickEvent
	 * 
	 * @return clickEvent
	 */
	public String getClickEvent() {
		return clickEvent;
	}

	/**
	 * setting clickEvent
	 * 
	 * @param clickEvent
	 */
	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}

	/**
	 * get totalCount
	 * 
	 * @return totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}

	/**
	 * setting totalCount
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * get jnmInfos
	 * 
	 * @return jnmInfos
	 */
	public List<T_JOB_LIST> getJnmInfos() {
		return jnmInfos;
	}

	/**
	 * setting jnmInfos
	 * 
	 * @param jnmInfos
	 */
	public void setJnmInfos(List<T_JOB_LIST> jnmInfos) {
		this.jnmInfos = jnmInfos;
	}

	/**
	 * get hid_id_jobno
	 * 
	 * @return hid_id_jobno
	 */
	public String getHid_id_jobno() {
		return hid_id_jobno;
	}

	/**
	 * setting hid_id_jobno
	 * 
	 * @param hid_id_jobno
	 */
	public void setHid_id_jobno(String hid_id_jobno) {
		this.hid_id_jobno = hid_id_jobno;
	}

	private String access_type;

	/**
	 * get access_type
	 * 
	 * @return access_type
	 */
	public String getAccess_type() {
		return access_type;
	}

	/**
	 * setting access_type
	 * 
	 * @param access_type
	 */
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	public String getRefreshFlg() {
		return refreshFlg;
	}

	public void setRefreshFlg(String refreshFlg) {
		this.refreshFlg = refreshFlg;
	}

}