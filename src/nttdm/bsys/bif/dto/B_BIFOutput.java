package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.bif.bean.B_BIFBean;

public class B_BIFOutput implements Serializable{
	private static final long serialVersionUID = -5538432094237818425L;
	private List<B_BIFBean> arr_bif;
	private Integer totalCount=0;
	private Integer row;
	private Integer startIndex=0;
	private String id_ref;
	private String cust_name;
	private String user_name;
	private String id_qcs;
	private String start_date;
	private String end_date;
	private String row_num;
	private String bif_type;
	private String status;
	private String nonTaxInvoiceShowFlg;
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

	public String getRow_num() {
		return row_num;
	}
	public void setRow_num(String row_num) {
		this.row_num = row_num;
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
	public List<B_BIFBean> getArr_bif() {
		return arr_bif;
	}
	public void setArr_bif(List<B_BIFBean> arr_bif) {
		this.arr_bif = arr_bif;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
	
	/**
	 * define 'NonTaxInvoice' is to Show or not show
	 * @return nonTaxInvoiceShowFlg
	 */
    public String getNonTaxInvoiceShowFlg() {
        return nonTaxInvoiceShowFlg;
    }
    
    /**
     * define 'NonTaxInvoice' is to Show or not show
     * @param nonTaxInvoiceShowFlg
     */
    public void setNonTaxInvoiceShowFlg(String nonTaxInvoiceShowFlg) {
        this.nonTaxInvoiceShowFlg = nonTaxInvoiceShowFlg;
    }	
}
