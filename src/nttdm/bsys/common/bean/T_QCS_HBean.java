package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class T_QCS_HBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private String id_ref;
	private String id_cust;
	private String id_conslt;
	private String id_quo;
	private String req_user;
	private String date_req;
	private String mode;
	private String ctterm;
	private String ctterm3_day;
	private String deposit;
	private String remarks;
	private String id_status;
	private String is_closed;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private String id_login;
	private String id_approver;
	public String getId_approver() {
		return id_approver;
	}
	public void setId_approver(String id_approver) {
		this.id_approver = id_approver;
	}
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getId_cust() {
		return id_cust;
	}
	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}
	public String getId_conslt() {
		return id_conslt;
	}
	public void setId_conslt(String id_conslt) {
		this.id_conslt = id_conslt;
	}
	public String getId_quo() {
		return id_quo;
	}
	public void setId_quo(String id_quo) {
		this.id_quo = id_quo;
	}
	public String getReq_user() {
		return req_user;
	}
	public void setReq_user(String req_user) {
		this.req_user = req_user;
	}
	public String getDate_req() {
		return date_req;
	}
	public void setDate_req(String date_req) {
		this.date_req = date_req;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCtterm() {
		return ctterm;
	}
	public void setCtterm(String ctterm) {
		this.ctterm = ctterm;
	}
	public String getCtterm3_day() {
		return ctterm3_day;
	}
	public void setCtterm3_day(String ctterm3_day) {
		this.ctterm3_day = ctterm3_day;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getId_status() {
		return id_status;
	}
	public void setId_status(String id_status) {
		this.id_status = id_status;
	}
	public String getIs_closed() {
		return is_closed;
	}
	public void setIs_closed(String is_closed) {
		this.is_closed = is_closed;
	}
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}

}
