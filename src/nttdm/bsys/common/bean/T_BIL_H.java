package nttdm.bsys.common.bean;



@SuppressWarnings("serial")
public class T_BIL_H implements java.io.Serializable {
	// Fields
	private String idRef;
	private String billType;
	private String isManual;
	private String billAcc;
	private String dateReq;
	private String payMethod;
	private String idCust;
	private String adrType;
	private String contactType;
	private String idBif;
	private String idQcs;
	private String quoRef;
	private String custPo;
	private String idConsult;
	private String jobNo;
	private String term;
	private String billCurrency;
	private String gstPercent;
	private String gstAmount;
	private String billAmount;
	private String paidAmount;
	private String lastPaymentDate;
	private String outstandingBal;
	private String isSettled;
	private String isExport;
	private String isDisplay;
	private String exportCur;
	private String curRate;
	private String exportAmount;
	private String fixedForex;
	private String noPrinted;
	private String datePrinted;
	private String userPrinted;
	private String isClosed;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String contactName;
	private String contactEmail;
	private String contactEmailCC;
	private String isDeleted;
	private String preparedBy;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	// extend fields
	private String telNo;
	private String faxNo;
	private String changeTypeFlag;
	private String zipCode;
	private String country;
	private String subTotalAmt;
	// Constructors
	private String termDays;
	private String dueDate;
	private String deliverymail;
	private String delivery;
	private String emailToAdd;
    private String emailCcAdd;
	
	/**
	 * @return the termDays
	 */
	public String getTermDays() {
		return termDays;
	}

	/**
	 * @param termDays the termDays to set
	 */
	public void setTermDays(String termDays) {
		this.termDays = termDays;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}


	/** default constructor */
	public T_BIL_H() {
	}

	/** minimal constructor */
	public T_BIL_H(String idRef, String billType, String isManual,
			String billAcc, String dateReq, String payMethod, String idCust,
			String adrType, String contactType, String billCurrency,
			String gstPercent, String gstAmount, String billAmount,
			String paidAmount, String lastPaymentDate, String outstandingBal,
			String isSettled, String isExport, String isDisplay,
			String exportCur, String curRate, String exportAmount,
			String fixedForex, String noPrinted, String isClosed,
			String isDeleted, String preparedBy, String dateCreated,
			String dateUpdated, String idLogin,String deliverymail,String delivery) {
		this.idRef = idRef;
		this.billType = billType;
		this.isManual = isManual;
		this.billAcc = billAcc;
		this.dateReq = dateReq;
		this.payMethod = payMethod;
		this.idCust = idCust;
		this.adrType = adrType;
		this.contactType = contactType;
		this.billCurrency = billCurrency;
		this.gstPercent = gstPercent;
		this.gstAmount = gstAmount;
		this.billAmount = billAmount;
		this.paidAmount = paidAmount;
		this.lastPaymentDate = lastPaymentDate;
		this.outstandingBal = outstandingBal;
		this.isSettled = isSettled;
		this.isExport = isExport;
		this.isDisplay = isDisplay;
		this.exportCur = exportCur;
		this.curRate = curRate;
		this.exportAmount = exportAmount;
		this.fixedForex = fixedForex;
		this.noPrinted = noPrinted;
		this.isClosed = isClosed;
		this.isDeleted = isDeleted;
		this.preparedBy = preparedBy;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.idLogin = idLogin;
		this.delivery=delivery;
		this.deliverymail=deliverymail;
	}

	/** full constructor */
	public T_BIL_H(String idRef, String billType, String isManual,
			String billAcc, String dateReq, String payMethod, String idCust,
			String adrType, String contactType, String idBif, String idQcs,
			String quoRef, String custPo, String idConsult, String jobNo,
			String term, String billCurrency, String gstPercent,
			String gstAmount, String billAmount, String paidAmount,
			String lastPaymentDate, String outstandingBal, String isSettled,
			String isExport, String isDisplay, String exportCur,
			String curRate, String exportAmount, String fixedForex,
			String noPrinted, String datePrinted, String userPrinted,
			String isClosed, String address1, String address2, String address3,
			String address4, String contactName, String contactEmail,String contactEmailCC,
			String isDeleted, String preparedBy, String dateCreated,
			String dateUpdated, String idLogin,String deliverymail,String delivery,String dueDate,String termDays) {
		this.idRef = idRef;
		this.billType = billType;
		this.isManual = isManual;
		this.billAcc = billAcc;
		this.dateReq = dateReq;
		this.payMethod = payMethod;
		this.idCust = idCust;
		this.adrType = adrType;
		this.contactType = contactType;
		this.idBif = idBif;
		this.idQcs = idQcs;
		this.quoRef = quoRef;
		this.custPo = custPo;
		this.idConsult = idConsult;
		this.jobNo = jobNo;
		this.term = term;
		this.billCurrency = billCurrency;
		this.gstPercent = gstPercent;
		this.gstAmount = gstAmount;
		this.billAmount = billAmount;
		this.paidAmount = paidAmount;
		this.lastPaymentDate = lastPaymentDate;
		this.outstandingBal = outstandingBal;
		this.isSettled = isSettled;
		this.isExport = isExport;
		this.isDisplay = isDisplay;
		this.exportCur = exportCur;
		this.curRate = curRate;
		this.exportAmount = exportAmount;
		this.fixedForex = fixedForex;
		this.noPrinted = noPrinted;
		this.datePrinted = datePrinted;
		this.userPrinted = userPrinted;
		this.isClosed = isClosed;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.address4 = address4;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactEmailCC = contactEmailCC;
		this.isDeleted = isDeleted;
		this.preparedBy = preparedBy;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.idLogin = idLogin;
		this.delivery=delivery;
        this.deliverymail=deliverymail;
        this.dueDate = dueDate;
        this.termDays = termDays;
	}
	
	// Property accessors
	
	public String getIdRef() {
		return idRef;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getIsManual() {
		return isManual;
	}

	public void setIsManual(String isManual) {
		this.isManual = isManual;
	}

	public String getBillAcc() {
		return billAcc;
	}

	public void setBillAcc(String billAcc) {
		this.billAcc = billAcc;
	}

	public String getDateReq() {
		return dateReq;
	}

	public void setDateReq(String dateReq) {
		this.dateReq = dateReq;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getIdCust() {
		return idCust;
	}

	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	public String getAdrType() {
		return adrType;
	}

	public void setAdrType(String adrType) {
		this.adrType = adrType;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getIdBif() {
		return idBif;
	}

	public void setIdBif(String idBif) {
		this.idBif = idBif;
	}

	public String getIdQcs() {
		return idQcs;
	}

	public void setIdQcs(String idQcs) {
		this.idQcs = idQcs;
	}

	public String getQuoRef() {
		return quoRef;
	}

	public void setQuoRef(String quoRef) {
		this.quoRef = quoRef;
	}

	public String getCustPo() {
		return custPo;
	}

	public void setCustPo(String custPo) {
		this.custPo = custPo;
	}

	public String getIdConsult() {
		return idConsult;
	}

	public void setIdConsult(String idConsult) {
		this.idConsult = idConsult;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getBillCurrency() {
		return billCurrency;
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	public String getGstPercent() {
		return gstPercent;
	}

	public void setGstPercent(String gstPercent) {
		this.gstPercent = gstPercent;
	}

	public String getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}

	public String getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public String getOutstandingBal() {
		return outstandingBal;
	}

	public void setOutstandingBal(String outstandingBal) {
		this.outstandingBal = outstandingBal;
	}

	public String getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getExportCur() {
		return exportCur;
	}

	public void setExportCur(String exportCur) {
		this.exportCur = exportCur;
	}

	public String getCurRate() {
		return curRate;
	}

	public void setCurRate(String curRate) {
		this.curRate = curRate;
	}

	public String getExportAmount() {
		return exportAmount;
	}

	public void setExportAmount(String exportAmount) {
		this.exportAmount = exportAmount;
	}

	public String getFixedForex() {
		return fixedForex;
	}

	public void setFixedForex(String fixedForex) {
		this.fixedForex = fixedForex;
	}

	public String getNoPrinted() {
		return noPrinted;
	}

	public void setNoPrinted(String noPrinted) {
		this.noPrinted = noPrinted;
	}

	public String getDatePrinted() {
		return datePrinted;
	}

	public void setDatePrinted(String datePrinted) {
		this.datePrinted = datePrinted;
	}

	public String getUserPrinted() {
		return userPrinted;
	}

	public void setUserPrinted(String userPrinted) {
		this.userPrinted = userPrinted;
	}

	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

    /**
     * @return the changeTypeFlag
     */
    public String getChangeTypeFlag() {
        return changeTypeFlag;
    }

    /**
     * @param changeTypeFlag the changeTypeFlag to set
     */
    public void setChangeTypeFlag(String changeTypeFlag) {
        this.changeTypeFlag = changeTypeFlag;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

	public void setSubTotalAmt(String subTotalAmt) {
		this.subTotalAmt = subTotalAmt;
	}

	public String getSubTotalAmt() {
		return subTotalAmt;
	}

    public String getDeliverymail() {
        return deliverymail;
    }

    public void setDeliverymail(String deliverymail) {
        this.deliverymail = deliverymail;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

	/**
	 * @return the emailToAdd
	 */
	public String getEmailToAdd() {
		return emailToAdd;
	}

	/**
	 * @param emailToAdd the emailToAdd to set
	 */
	public void setEmailToAdd(String emailToAdd) {
		this.emailToAdd = emailToAdd;
	}

	/**
	 * @return the emailCcAdd
	 */
	public String getEmailCcAdd() {
		return emailCcAdd;
	}

	/**
	 * @param emailCcAdd the emailCcAdd to set
	 */
	public void setEmailCcAdd(String emailCcAdd) {
		this.emailCcAdd = emailCcAdd;
	}

	/**
	 * @return the contactEmailCC
	 */
	public String getContactEmailCC() {
		return contactEmailCC;
	}

	/**
	 * @param contactEmailCC the contactEmailCC to set
	 */
	public void setContactEmailCC(String contactEmailCC) {
		this.contactEmailCC = contactEmailCC;
	}
}