package nttdm.bsys.bif.dto;

import java.io.Serializable;

public class B_BIFInput implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private String idRef;
	private String id_ref;
	private String cust_name;
	private String user_name;
	private String id_qcs;
	private String start_date;
	private String end_date;
	private Integer row;
	private Integer startIndex=0;
	private String bif_type;
	private String status;
	private String job_no;
	private String subscription_id;
	private String billing_account;
	private String prepared_by;
	
	/**
	 * @return the billing_account
	 */
	public String getBilling_account() {
		return billing_account;
	}
	/**
	 * @param billing_account the billing_account to set
	 */
	public void setBilling_account(String billing_account) {
		this.billing_account = billing_account;
	}
	/**
	 * @return the prepared_by
	 */
	public String getPrepared_by() {
		return prepared_by;
	}
	/**
	 * @param prepared_by the prepared_by to set
	 */
	public void setPrepared_by(String prepared_by) {
		this.prepared_by = prepared_by;
	}
	
	/**
	 * @return the job_no
	 */
	public String getJob_no() {
		return job_no;
	}
	/**
	 * @param job_no the job_no to set
	 */
	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}
	/**
	 * @return the subscription_id
	 */
	public String getSubscription_id() {
		return subscription_id;
	}
	/**
	 * @param subscription_id the subscription_id to set
	 */
	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getId_qcs() {
		return id_qcs;
	}
	public void setId_qcs(String id_qcs) {
		this.id_qcs = id_qcs;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @param idRef the idRef to set
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}
	/**
	 * @return the idRef
	 */
	public String getIdRef() {
		return idRef;
	}
	public String getBif_type() {
		return bif_type;
	}
	public void setBif_type(String bif_type) {
		this.bif_type = bif_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
