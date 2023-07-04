package nttdm.bsys.bif.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import java.util.ArrayList;
import java.util.List;

public class B_BIFBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = 5471385394462378902L;
	private String date_req;
	private String id_ref;
	private String cust_name;
	private String user_name;
	private String id_qcs;
	private Integer totalCount=0;
	private Integer startIndex=0;
	private Integer row=0;
	private String start_date;
	private String end_date;
	private String row_num;
	private String id_status;
	private String bif_type;
	private String id_approver_name;
	private String job_no;
	private String subscription_id;
	private List<String> billing_account;
	/**
	 * @return the billing_account
	 */
	public List<String> getBilling_account() {
		if(this.billing_account==null){
			this.billing_account = new ArrayList<String>();
		}
		return this.billing_account;
	}
	/**
	 * @param billing_account the billing_account to set
	 */
	public void setBilling_account(List<String> billing_account) {
		this.billing_account = billing_account;
	}
	private String prepared_by;
	
	
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
	
	public String getRow_num() {
		return row_num;
	}
	public void setRow_num(String row_num) {
		this.row_num = row_num;
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
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public String getDate_req() {
		return date_req;
	}
	public void setDate_req(String date_req) {
		this.date_req = date_req;
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
	/**
	 * @param id_status the id_status to set
	 */
	public void setId_status(String id_status) {
		this.id_status = id_status;
	}
	/**
	 * @return the id_status
	 */
	public String getId_status() {
		return id_status;
	}
	public String getBif_type() {
		return bif_type;
	}
	public void setBif_type(String bif_type) {
		this.bif_type = bif_type;
	}
    /**
     * @return the id_approver_name
     */
    public String getId_approver_name() {
        return id_approver_name;
    }
    /**
     * @param id_approver_name the id_approver_name to set
     */
    public void setId_approver_name(String id_approver_name) {
        this.id_approver_name = id_approver_name;
    }
}
