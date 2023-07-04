package nttdm.bsys.b_csb.dto;
import java.io.Serializable;
public class Debit_info_bean implements Serializable {
	private static final long serialVersionUID = -746640436857442359L;
	
	private String DATE_REQ;
	private String BILL_CURRENCY;
	private String BILL_AMOUNT;
	private String ID_REF;
	private String AMT_PAID;
	private String AMT_PAID_OLD;
	private String AMT_DUE;
	private String FOREX_LOSS;
	private String FOREX_GAIN;
	private Boolean CHECK = false;
	
	private String RECEIPT_NO;
	private String id_login;
	private String tbxtransday;
	private String AMTDUE;
	private String PAID_AMOUNT;
	private String APPLIED;
	private String BILL_TYPE;

	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bILL_TYPE) {
		BILL_TYPE = bILL_TYPE;
	}
	public String getAMT_PAID_OLD() {
		return AMT_PAID_OLD;
	}
	public void setAMT_PAID_OLD(String amt_paid_old) {
		AMT_PAID_OLD = amt_paid_old;
	}
	
	public String getAPPLIED() {
		return APPLIED;
	}
	public void setAPPLIED(String applied) {
		APPLIED = applied;
	}
	public Boolean getCHECK()
	{
		return CHECK;
		
	}
	public void setCHECK(Boolean check) {
		CHECK = check;
	}

	
	public String getPAID_AMOUNT() {
		return PAID_AMOUNT;
	}
	public void setPAID_AMOUNT(String paid_amount) {
		PAID_AMOUNT = paid_amount;
	}	
	public String getAMTDUE() {
		return AMTDUE;
	}
	public void setAMTDUE(String amtdue) {
		AMTDUE = amtdue;
	}
	public String getDATE_REQ() {
		return DATE_REQ;
	}
	public void setDATE_REQ(String date_req) {
		DATE_REQ = date_req;
	}
	public String getBILL_CURRENCY() {
		return BILL_CURRENCY;
	}
	public void setBILL_CURRENCY(String bill_currency) {
		BILL_CURRENCY = bill_currency;
	}
	public String getBILL_AMOUNT() {
		return BILL_AMOUNT;
	}
	public void setBILL_AMOUNT(String bill_amount) {
		BILL_AMOUNT = bill_amount;
	}
	public String getID_REF() {
		return ID_REF;
	}
	public void setID_REF(String id_ref) {
		ID_REF = id_ref;
	}
	public String getAMT_PAID() {
		return AMT_PAID;
	}
	public void setAMT_PAID(String amt_paid) {
		AMT_PAID = amt_paid;
	}
	public String getAMT_DUE() {
		return AMT_DUE;
	}
	public void setAMT_DUE(String amt_due) {
		AMT_DUE = amt_due;
	}
	public String getFOREX_LOSS() {
		return FOREX_LOSS;
	}
	public void setFOREX_LOSS(String forex_loss) {
		FOREX_LOSS = forex_loss;
	}
	
	public String getRECEIPT_NO() {
		return RECEIPT_NO;
	}
	public void setRECEIPT_NO(String receipt_no) {
		RECEIPT_NO = receipt_no;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getFOREX_GAIN() {
		return FOREX_GAIN;
	}
	public void setFOREX_GAIN(String forex_gain) {
		FOREX_GAIN = forex_gain;
	}
	public String getTbxtransday() {
		return tbxtransday;
	}
	public void setTbxtransday(String tbxtransday) {
		this.tbxtransday = tbxtransday;
	}
	
}
