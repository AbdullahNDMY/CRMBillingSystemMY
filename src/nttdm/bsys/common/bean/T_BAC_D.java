package nttdm.bsys.common.bean;


/**
 * TBacD entity. @author MyEclipse Persistence Tools
 */

public class T_BAC_D implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idCustPlan;
	private String idBillAccount;
	private String isFirstBill;
	private String idQcs;
	private String idQuo;
	private String custPo;
	private String acManager;
	private String jobNo;
	private String term;
	private String billFrom;
	private String billTo;
	private String isRecurring;
	private String isDeleted;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	private String idBif;
	private String termDay;
	private String custPoDisp;
	
	// Constructors

	/**
	 * @return the termDay
	 */
	public String getTermDay() {
		return termDay;
	}

	/**
	 * @param termDay the termDay to set
	 */
	public void setTermDay(String termDay) {
		this.termDay = termDay;
	}

	/** default constructor */
	public T_BAC_D() {
	}
	
	// Property accessors
	
	public String getIsFirstBill() {
		return this.isFirstBill;
	}

	public String getIdCustPlan() {
		return idCustPlan;
	}

	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}

	public String getIdBillAccount() {
		return idBillAccount;
	}

	public void setIdBillAccount(String idBillAccount) {
		this.idBillAccount = idBillAccount;
	}

	public void setIsFirstBill(String isFirstBill) {
		this.isFirstBill = isFirstBill;
	}

	public String getIdQcs() {
		return this.idQcs;
	}

	public void setIdQcs(String idQcs) {
		this.idQcs = idQcs;
	}

	public String getIdQuo() {
		return this.idQuo;
	}

	public void setIdQuo(String idQuo) {
		this.idQuo = idQuo;
	}

	public String getCustPo() {
		return this.custPo;
	}

	public void setCustPo(String custPo) {
		this.custPo = custPo;
	}

	public String getAcManager() {
		return this.acManager;
	}

	public void setAcManager(String acManager) {
		this.acManager = acManager;
	}

	public String getJobNo() {
		return this.jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getBillFrom() {
		return this.billFrom;
	}

	public void setBillFrom(String billFrom) {
		this.billFrom = billFrom;
	}

	public String getBillTo() {
		return this.billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getIsRecurring() {
		return this.isRecurring;
	}

	public void setIsRecurring(String isRecurring) {
		this.isRecurring = isRecurring;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getIdLogin() {
		return this.idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

    public String getIdBif() {
        return idBif;
    }

    public void setIdBif(String idBif) {
        this.idBif = idBif;
    }

	/**
	 * @return the custPoDisp
	 */
	public String getCustPoDisp() {
		return custPoDisp;
	}

	/**
	 * @param custPoDisp the custPoDisp to set
	 */
	public void setCustPoDisp(String custPoDisp) {
		this.custPoDisp = custPoDisp;
	}

}