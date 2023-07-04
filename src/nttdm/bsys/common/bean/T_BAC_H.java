package nttdm.bsys.common.bean;

public class T_BAC_H implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idBillAccount;
	private String idCust;
	private String paymentMethod;
	private String billCurrency;
	private String isDisplayExpAmt;
	private String exportCurrency;
	private String fixedForex;
	private String custAdrType;
	private String contactType;
	private String isDeleted;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	private String exportSingPost ;
	private String idCustPlan;
	private String bacDCount;
	private String defCurrency;
	private String multiBillPeriod;
	private String delivery;
	private String deliveryEmail;
	
	// #191 Start
	private String singPostValue;
	// #191 End
	
	private String taxType;

	// Constructors

	/**
	 * @return the multiBillPeriod
	 */
	public String getMultiBillPeriod() {
		return multiBillPeriod;
	}

	/**
	 * @param multiBillPeriod the multiBillPeriod to set
	 */
	public void setMultiBillPeriod(String multiBillPeriod) {
		this.multiBillPeriod = multiBillPeriod;
	}

	/** default constructor */
	public T_BAC_H() {
	}

	/** full constructor */
	public T_BAC_H(String idBillAccount, String idCust, String paymentMethod,
			String billCurrency, String isDisplayExpAmt, String exportCurrency,
			String fixedForex, String custAdrType, String contactType,
			String isDeleted, String dateCreated, String dateUpdated, String idLogin, String exportSingPost,String idCustPlan
			, String taxType) {
		this.idBillAccount = idBillAccount;
		this.idCust = idCust;
		this.paymentMethod = paymentMethod;
		this.billCurrency = billCurrency;
		this.isDisplayExpAmt = isDisplayExpAmt;
		this.exportCurrency = exportCurrency;
		this.fixedForex = fixedForex;
		this.custAdrType = custAdrType;
		this.contactType = contactType;
		this.isDeleted = isDeleted;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.idLogin = idLogin;
	    this.exportSingPost = exportSingPost;
	    this.idCustPlan = idCustPlan;
	    this.taxType = taxType;
	}

	// Property accessors

	public String getIdBillAccount() {
		return this.idBillAccount;
	}

	public void setIdBillAccount(String idBillAccount) {
		this.idBillAccount = idBillAccount;
	}

	public String getIdCust() {
		return this.idCust;
	}

	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getBillCurrency() {
		return this.billCurrency;
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	public String getIsDisplayExpAmt() {
		return this.isDisplayExpAmt;
	}

	public void setIsDisplayExpAmt(String isDisplayExpAmt) {
		this.isDisplayExpAmt = isDisplayExpAmt;
	}

	public String getExportCurrency() {
		return this.exportCurrency;
	}

	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}

	public String getFixedForex() {
		return this.fixedForex;
	}

	public void setFixedForex(String fixedForex) {
		this.fixedForex = fixedForex;
	}

	public String getCustAdrType() {
		return this.custAdrType;
	}

	public void setCustAdrType(String custAdrType) {
		this.custAdrType = custAdrType;
	}

	public String getContactType() {
		return this.contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
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
	
    /**
     * @return exportSingPost
     */
    public String getExportSingPost() {
        return this.exportSingPost;
    }

    /**
     * @param exportSingPost String
     */
    public void setExportSingPost(String exportSingPost) {
        this.exportSingPost = exportSingPost;
    }

    /**
     * @return the idCustPlan
     */
    public String getIdCustPlan() {
        return idCustPlan;
    }

    /**
     * @param idCustPlan the idCustPlan to set
     */
    public void setIdCustPlan(String idCustPlan) {
        this.idCustPlan = idCustPlan;
    }

    /**
     * @return the bacDCount
     */
    public String getBacDCount() {
        return bacDCount;
    }

    /**
     * @param bacDCount the bacDCount to set
     */
    public void setBacDCount(String bacDCount) {
        this.bacDCount = bacDCount;
    }

    /**
     * @return the defCurrency
     */
    public String getDefCurrency() {
        return defCurrency;
    }

    /**
     * @param defCurrency the defCurrency to set
     */
    public void setDefCurrency(String defCurrency) {
        this.defCurrency = defCurrency;
    }

    /**
     * @return the delivery
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * @return the deliveryEmail
     */
    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    /**
     * @param deliveryEmail the deliveryEmail to set
     */
    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

	/**
	 * @return the singPostValue
	 */
	public String getSingPostValue() {
		return singPostValue;
	}

	/**
	 * @param singPostValue the singPostValue to set
	 */
	public void setSingPostValue(String singPostValue) {
		this.singPostValue = singPostValue;
	}

	/**
	 * @return the taxType
	 */
	public String getTaxType() {
		return taxType;
	}

	/**
	 * @param taxType the taxType to set
	 */
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
}