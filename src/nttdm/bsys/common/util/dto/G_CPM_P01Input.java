package nttdm.bsys.common.util.dto;

import jp.terasoluna.fw.service.thin.BLogicMessages;

public class G_CPM_P01Input {
	private String id_cust_plan;
	private String id_cust_plan_grp;
	private String is_first_bill;
	private String id_qcs;
	private String id_quo;
	private String cust_po;
	private String ac_manager;
	private String job_no;
	private String term;
	private String termDay;
	private String bill_from;
	private String bill_to;
	private String is_recurring;
	private String id_bif;
	private String payment_method;
	private String bill_currency;
	private String is_display_exp_amt;
	private String multi_bill_period;

	private String export_currency;
	private String fixed_forex;
	private String cust_adr_type;

	private String contact_type;
	private String approve_type;	
	private String id_login;
	private String id_bill_account;
	private String id_cust;
	private String id_audit;
	
	private boolean upadteT_BAC_H;
	private boolean isRadius;
	private String accessAccount;
	private String accessPw;
	private String delicery;
    private String deliceryEmail;
	
	private BLogicMessages errorMessages;
	

	public String getId_cust() {
		return id_cust;
	}
	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}
	public String getId_bill_account() {
		return id_bill_account;
	}
	public void setId_bill_account(String id_bill_account) {
		this.id_bill_account = id_bill_account;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getId_cust_plan() {
		return id_cust_plan;
	}
	public void setId_cust_plan(String id_cust_plan) {
		this.id_cust_plan = id_cust_plan;
	}
	public String getId_qcs() {
		return id_qcs;
	}
	public void setId_qcs(String id_qcs) {
		this.id_qcs = id_qcs;
	}
	public String getId_quo() {
		return id_quo;
	}
	public void setId_quo(String id_quo) {
		this.id_quo = id_quo;
	}
	public String getCust_po() {
		return cust_po;
	}
	public void setCust_po(String cust_po) {
		this.cust_po = cust_po;
	}
	public String getAc_manager() {
		return ac_manager;
	}
	public void setAc_manager(String ac_manager) {
		this.ac_manager = ac_manager;
	}
	public String getJob_no() {
		return job_no;
	}
	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getTermDay() {
        return termDay;
    }
    public void setTermDay(String termDay) {
        this.termDay = termDay;
    }
    public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getBill_currency() {
		return bill_currency;
	}
	public void setBill_currency(String bill_currency) {
		this.bill_currency = bill_currency;
	}
	public String getExport_currency() {
		return export_currency;
	}
	public void setExport_currency(String export_currency) {
		this.export_currency = export_currency;
	}
	public String getFixed_forex() {
		return fixed_forex;
	}
	public void setFixed_forex(String fixed_forex) {
		this.fixed_forex = fixed_forex;
	}

	public String getContact_type() {
		return contact_type;
	}
	public void setContact_type(String contact_type) {
		this.contact_type = contact_type;
	}
	public String getApprove_type() {
		return approve_type;
	}
	public void setApprove_type(String approve_type) {
		this.approve_type = approve_type;
	}
	public String getCust_adr_type() {
		return cust_adr_type;
	}
	public void setCust_adr_type(String cust_adr_type) {
		this.cust_adr_type = cust_adr_type;
	}
	public String getIs_display_exp_amt() {
		return is_display_exp_amt;
	}
	public void setIs_display_exp_amt(String is_display_exp_amt) {
		this.is_display_exp_amt = is_display_exp_amt;
	}
	public String getIs_first_bill() {
		return is_first_bill;
	}
	public void setIs_first_bill(String is_first_bill) {
		this.is_first_bill = is_first_bill;
	}
	public String getBill_from() {
		return bill_from;
	}
	public void setBill_from(String bill_from) {
		this.bill_from = bill_from;
	}
	public String getBill_to() {
		return bill_to;
	}
	public void setBill_to(String bill_to) {
		this.bill_to = bill_to;
	}
	public String getIs_recurring() {
		return is_recurring;
	}
	public void setIs_recurring(String is_recurring) {
		this.is_recurring = is_recurring;
	}
	public String getId_bif() {
		return id_bif;
	}
	public void setId_bif(String id_bif) {
		this.id_bif = id_bif;
	}
    public void setId_cust_plan_grp(String id_cust_plan_grp) {
        this.id_cust_plan_grp = id_cust_plan_grp;
    }
    public String getId_cust_plan_grp() {
        return id_cust_plan_grp;
    }
    
    /**
     * @return the errorMessages
     */
    public BLogicMessages getErrorMessages() {
        return errorMessages;
    }
    /**
     * @param errorMessages the errorMessages to set
     */
    public void setErrorMessages(BLogicMessages errorMessages) {
        this.errorMessages = errorMessages;
    }	
    /**
     * @return the id_audit
     */
    public String getId_audit() {
        return id_audit;
    }
    /**
     * @param id_audit the id_audit to set
     */
    public void setId_audit(String id_audit) {
        this.id_audit = id_audit;
    }	
    /**
     * @return the isRadius
     */
    public boolean isRadius() {
        return isRadius;
    }
    /**
     * @param isRadius the isRadius to set
     */
    public void setRadius(boolean isRadius) {
        this.isRadius = isRadius;
    }
    /**
     * @return the accessPw
     */
    public String getAccessPw() {
        return accessPw;
    }
    /**
     * @param accessPw the accessPw to set
     */
    public void setAccessPw(String accessPw) {
        this.accessPw = accessPw;
    }
    /**
     * @return the accessAccount
     */
    public String getAccessAccount() {
        return accessAccount;
    }
    /**
     * @param accessAccount the accessAccount to set
     */
    public void setAccessAccount(String accessAccount) {
        this.accessAccount = accessAccount;
    }
    /**
     * @return the upadteT_BAC_H
     */
    public boolean isUpadteT_BAC_H() {
        return upadteT_BAC_H;
    }
    /**
     * @param upadteT_BAC_H the upadteT_BAC_H to set
     */
    public void setUpadteT_BAC_H(boolean upadteT_BAC_H) {
        this.upadteT_BAC_H = upadteT_BAC_H;
    }
    /**
     * @return the multi_bill_period
     */
    public String getMulti_bill_period() {
        return multi_bill_period;
    }
    /**
     * @param multi_bill_period the multi_bill_period to set
     */
    public void setMulti_bill_period(String multi_bill_period) {
        this.multi_bill_period = multi_bill_period;
    }
	/**
	 * @return the delicery
	 */
	public String getDelicery() {
		return delicery;
	}
	/**
	 * @param delicery the delicery to set
	 */
	public void setDelicery(String delicery) {
		this.delicery = delicery;
	}
	/**
	 * @return the deliceryEmail
	 */
	public String getDeliceryEmail() {
		return deliceryEmail;
	}
	/**
	 * @param deliceryEmail the deliceryEmail to set
	 */
	public void setDeliceryEmail(String deliceryEmail) {
		this.deliceryEmail = deliceryEmail;
	}	
}
