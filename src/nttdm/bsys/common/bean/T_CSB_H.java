package nttdm.bsys.common.bean;

public class T_CSB_H {
	private String id_ref;
	private String id_cust;
	private String cust_name;
	private String pmt_method;
	private String date_trans;
	private String receipt_no;
	private String cur_code;
	private String amt_received;
	private String remark;
	private String pmt_status;
	private String balance_amt;
	private String reference1;
	private String reference2;
	private String bank_charge;
	private String is_closed;
	private String id_login;
	private String ref_no;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private String row_num;

    /** id_bill_account */
    private String id_bill_account;
	
	public String getRow_num() {
		return row_num;
	}
	public void setRow_num(String row_num) {
		this.row_num = row_num;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
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
	public String getPmt_method() {
		return pmt_method;
	}
	public void setPmt_method(String pmt_method) {
		this.pmt_method = pmt_method;
	}
	public String getDate_trans() {
		return date_trans;
	}
	public void setDate_trans(String date_trans) {
		this.date_trans = date_trans;
	}
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getCur_code() {
		return cur_code;
	}
	public void setCur_code(String cur_code) {
		this.cur_code = cur_code;
	}
	public String getAmt_received() {
		return amt_received;
	}
	public void setAmt_received(String amt_received) {
		this.amt_received = amt_received;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPmt_status() {
		return pmt_status;
	}
	public void setPmt_status(String pmt_status) {
		this.pmt_status = pmt_status;
	}
	public String getBalance_amt() {
		return balance_amt;
	}
	public void setBalance_amt(String balance_amt) {
		this.balance_amt = balance_amt;
	}
	public String getReference1() {
		return reference1;
	}
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}
	public String getReference2() {
		return reference2;
	}
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}
	public String getBank_charge() {
		return bank_charge;
	}
	public void setBank_charge(String bank_charge) {
		this.bank_charge = bank_charge;
	}
	public String getIs_closed() {
		return is_closed;
	}
	public void setIs_closed(String is_closed) {
		this.is_closed = is_closed;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getRef_no() {
		return ref_no;
	}
	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
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
    /**
     * @param id_bill_account the id_bill_account to set
     */
    public void setId_bill_account(String id_bill_account) {

        this.id_bill_account = id_bill_account;
    }
    /**
     * @return the id_bill_account
     */
    public String getId_bill_account() {

        return id_bill_account;
    }
	
}
