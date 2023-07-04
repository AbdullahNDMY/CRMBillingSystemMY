/*
 * @(#)M_JNMR01Input.java
 *
 *
 */
package nttdm.bsys.m_jnm.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;


public class M_JNMR01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6016366325152242755L;

	private BillingSystemUserValueObject uvo;
	private String name_cust;
	private String id_cust;
	private String job_no;
	private String hid_id_cust;
	private String id_jobno;
	private String clickEvent;
	private String hid_id_jobno;
	private List<org.apache.struts.util.LabelValueBean> listCustomer;
	private Integer rowsPerPage;
	private String refreshFlg;
	
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

	private Integer startIndex;
		
	public List<org.apache.struts.util.LabelValueBean> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(
			List<org.apache.struts.util.LabelValueBean> listCustomer) {
		this.listCustomer = listCustomer;
	}

	/**
	 * get Name_cust
	 * 
	 * @return Name_cust
	 */
	public String getName_cust() {
		return name_cust;
	}

	/**
	 * setting Name_cust
	 * 
	 * @param Name_cust
	 */
	public void setName_cust(String name_cust) {
		this.name_cust = name_cust != null ? name_cust.trim() :name_cust;
	}

	/**
	 * get uvo
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	
	/**
	 * setting uvo
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
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
		this.id_cust = id_cust != null ? id_cust.trim() :id_cust;
	}

	/**
	 * get Job_no
	 * 
	 * @return Job_no
	 */
	public String getJob_no() {
		return job_no;
	}

	/**
	 * setting Job_no
	 * 
	 * @param Job_no
	 */
	public void setJob_no(String job_no) {
		this.job_no = job_no != null ? job_no.trim() :job_no;
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

	public String getRefreshFlg() {
		return refreshFlg;
	}

	public void setRefreshFlg(String refreshFlg) {
		this.refreshFlg = refreshFlg;
	}

}