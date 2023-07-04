/*
 * @(#)M_JNMR01Output.java
 *
 *
 */
package nttdm.bsys.m_jnm.dto;

import java.io.Serializable;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author ss051
 */
public class M_JNMR01Output implements Serializable {

	private static final long serialVersionUID = -77363358361876312L;
	
	private Integer rowsPerPage;	
	private Integer startIndex;	
	private String id_jobno;
	private String id_cust;
	private String name_cust;
	private String hid_id_cust;
	private String job_no;
	private String totalCount;
	private String clickEvent;
	
	private List<T_JOB_LIST> jnmInfos;
	private List<org.apache.struts.util.LabelValueBean> listCustomer;
		
	

	public List<org.apache.struts.util.LabelValueBean> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(
			List<org.apache.struts.util.LabelValueBean> listCustomer) {
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
	public String getJob_no() {
		return job_no;
	}
	
	/**
	 * setting job_no
	 * 
	 * @param job_no
	 */
	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}
	

	/**
	 * get TotalCount
	 * 
	 * @return TotalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}

	/**
	 * setting TotalCount
	 * 
	 * @param TotalCount
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * get ClickEvent
	 * 
	 * @return ClickEvent
	 */
	public String getClickEvent() {
		return clickEvent;
	}

	/**
	 * setting ClickEvent
	 * 
	 * @param ClickEvent
	 */
	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
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
}