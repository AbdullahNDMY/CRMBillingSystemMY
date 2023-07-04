package nttdm.bsys.q_qcs.bean;

public class QCSHeader {
	private String id_ref;
	private String id_cust;
	private String id_conslt;
	private String cust_name;
	private String user_name;
	private String date_req;
	private String id_quo;
	private String id_status;
	private String row_num = null;
	private String is_closed;
	private String deposit;
	private String cust_mode;
	private String ctterm3_day;
	private String remarks;
	private String ctterm;
	private String req_user;
	private String id_login;
	
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getCtterm3_day() {
		return ctterm3_day;
	}
	public void setCtterm3_day(String ctterm3_day) {
		this.ctterm3_day = ctterm3_day;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReq_user() {
		return req_user;
	}
	public void setReq_user(String req_user) {
		this.req_user = req_user;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getCust_mode() {
		return cust_mode;
	}
	public void setCust_mode(String cust_mode) {
		this.cust_mode = cust_mode;
	}
	public String getCtterm() {
		return ctterm;
	}
	public void setCtterm(String ctterm) {
		this.ctterm = ctterm;
	}
	public String getIs_closed() {
		return is_closed;
	}
	public void setIs_closed(String is_closed) {
		this.is_closed = is_closed;
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
	public String getDate_req() {
		return date_req;
	}
	public void setDate_req(String date_req) {
		this.date_req = date_req;
	}
	public String getId_quo() {
		return id_quo;
	}
	public void setId_quo(String id_quo) {
		this.id_quo = id_quo;
	}
	public String getId_status() {
		return id_status;
	}
	public void setId_status(String id_status) {
		this.id_status = id_status;
	}
	
}
