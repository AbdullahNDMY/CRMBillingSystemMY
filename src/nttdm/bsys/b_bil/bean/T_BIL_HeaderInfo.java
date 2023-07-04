/**
 * @(#)T_BIL_Detail.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/26
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.bean;

import java.util.List;
import java.util.Map;

import nttdm.bsys.common.util.dto.AutoScaleList;

import org.apache.struts.util.LabelValueBean;

/**
 * @author gplai
 *
 */
public class T_BIL_HeaderInfo {

    private List<T_BIL_DetailInfo> bilDetail;
    
    private String idRef;
    private String mode;
    /**
     * IN:Invoice,DN:Debit Note,CN:Credit Note
     */
    private String billType;
    private String idCust;
    private String adrType;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String telNo;
    private String faxNo;
    private String contactType;
    private String contactName;
    private String contactEmail;
    private String contactEmailCC;
    private String payMethod;
    private String isManual;
    private String billAcc;
    private String dateReq;
    private String idBif;
    private String idQcs;
    private String quoRef;
    private String custPo;
    private String idConsult;
    private String jobNo;
    private String term;
    private String termDays;
    private String billCurrency;
    private String contactDueDate;
    private String contactDelivery;
    private String contactDeliveryEmail;
    /**
     * 0:disabled,1:enabled
     */
    private String currencyEnabledFlg;
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
    private String isDeleted;
    private String preparedBy;
    private String preparedByName;
    private String dateCreated;
    private String dateUpdated;
    private String idLogin;
    private String changeTypeFlag;
    private String custName;
    private String salutation;
    private String subTotalAmt;
    private String invoiceDate;
    // #194 Add Start
    private String isDuplicate;
    public String getIsDuplicate() {
		return isDuplicate;
	}

	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	// #194 Add End
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
     * 0:not display,1:display
     */
    private String jobModulesDisplayFlg;
    
    private String bacBillingCurrency;
    
    private List<LabelValueBean> listCust;
    private List<LabelValueBean> listContact;
    private List<LabelValueBean> listAcMan;
    private List<LabelValueBean> listBillingAccountNo;
    private List<Map<String,Object>> footerInfo;
    private List<Map<String, Object>> bankFooterInfo;
    
    private String customerTypeFlag;
    
    private String defCurrency;
    private String currencyStd;
    
    // #174 Start
    private String billingPeriod;
    // #174 Emd
    
    public T_BIL_HeaderInfo(){
        this.bilDetail = new AutoScaleList<T_BIL_DetailInfo>(new T_BIL_DetailInfo());
    }

    /**
     * @return the bilDetail
     */
    public List<T_BIL_DetailInfo> getBilDetail() {
        return bilDetail;
    }

    /**
     * @param bilDetail the bilDetail to set
     */
    public void setBilDetail(List<T_BIL_DetailInfo> bilDetail) {
        this.bilDetail = bilDetail;
    }

    /**
     * @return the idRef
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * @param idRef the idRef to set
     */
    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType the billType to set
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * @return the idCust
     */
    public String getIdCust() {
        return idCust;
    }

    /**
     * @param idCust the idCust to set
     */
    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    /**
     * @return the listCust
     */
    public List<LabelValueBean> getListCust() {
        return listCust;
    }

    /**
     * @param listCust the listCust to set
     */
    public void setListCust(List<LabelValueBean> listCust) {
        this.listCust = listCust;
    }

    /**
     * @return the listContact
     */
    public List<LabelValueBean> getListContact() {
        return listContact;
    }

    /**
     * @param listContact the listContact to set
     */
    public void setListContact(List<LabelValueBean> listContact) {
        this.listContact = listContact;
    }

    /**
     * @return the adrType
     */
    public String getAdrType() {
        return adrType;
    }

    /**
     * @param adrType the adrType to set
     */
    public void setAdrType(String adrType) {
        this.adrType = adrType;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * @return the address4
     */
    public String getAddress4() {
        return address4;
    }

    /**
     * @param address4 the address4 to set
     */
    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    /**
     * @return the telNo
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * @param telNo the telNo to set
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * @return the faxNo
     */
    public String getFaxNo() {
        return faxNo;
    }

    /**
     * @param faxNo the faxNo to set
     */
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    /**
     * @return the contactType
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @param contactType the contactType to set
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail the contactEmail to set
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * @return the payMethod
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * @param payMethod the payMethod to set
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * @return the isManual
     */
    public String getIsManual() {
        return isManual;
    }

    /**
     * @param isManual the isManual to set
     */
    public void setIsManual(String isManual) {
        this.isManual = isManual;
    }

    /**
     * @return the billAcc
     */
    public String getBillAcc() {
        return billAcc;
    }

    /**
     * @param billAcc the billAcc to set
     */
    public void setBillAcc(String billAcc) {
        this.billAcc = billAcc;
    }

    /**
     * @return the dateReq
     */
    public String getDateReq() {
        return dateReq;
    }

    /**
     * @param dateReq the dateReq to set
     */
    public void setDateReq(String dateReq) {
        this.dateReq = dateReq;
    }

    /**
     * @return the idBif
     */
    public String getIdBif() {
        return idBif;
    }

    /**
     * @param idBif the idBif to set
     */
    public void setIdBif(String idBif) {
        this.idBif = idBif;
    }

    /**
     * @return the idQcs
     */
    public String getIdQcs() {
        return idQcs;
    }

    /**
     * @param idQcs the idQcs to set
     */
    public void setIdQcs(String idQcs) {
        this.idQcs = idQcs;
    }

    /**
     * @return the quoRef
     */
    public String getQuoRef() {
        return quoRef;
    }

    /**
     * @param quoRef the quoRef to set
     */
    public void setQuoRef(String quoRef) {
        this.quoRef = quoRef;
    }

    /**
     * @return the custPo
     */
    public String getCustPo() {
        return custPo;
    }

    /**
     * @param custPo the custPo to set
     */
    public void setCustPo(String custPo) {
        this.custPo = custPo;
    }

    /**
     * @return the idConsult
     */
    public String getIdConsult() {
        return idConsult;
    }

    /**
     * @param idConsult the idConsult to set
     */
    public void setIdConsult(String idConsult) {
        this.idConsult = idConsult;
    }

    /**
     * @return the jobNo
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * @param jobNo the jobNo to set
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the billCurrency
     */
    public String getBillCurrency() {
        return billCurrency;
    }

    /**
     * @param billCurrency the billCurrency to set
     */
    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    /**
     * @return the currencyEnabledFlg
     */
    public String getCurrencyEnabledFlg() {
        return currencyEnabledFlg;
    }

    /**
     * @param currencyEnabledFlg the currencyEnabledFlg to set
     */
    public void setCurrencyEnabledFlg(String currencyEnabledFlg) {
        this.currencyEnabledFlg = currencyEnabledFlg;
    }

    /**
     * @return the gstPercent
     */
    public String getGstPercent() {
        return gstPercent;
    }

    /**
     * @param gstPercent the gstPercent to set
     */
    public void setGstPercent(String gstPercent) {
        this.gstPercent = gstPercent;
    }

    /**
     * @return the gstAmount
     */
    public String getGstAmount() {
        return gstAmount;
    }

    /**
     * @param gstAmount the gstAmount to set
     */
    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }

    /**
     * @return the billAmount
     */
    public String getBillAmount() {
        return billAmount;
    }

    /**
     * @param billAmount the billAmount to set
     */
    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    /**
     * @return the paidAmount
     */
    public String getPaidAmount() {
        return paidAmount;
    }

    /**
     * @param paidAmount the paidAmount to set
     */
    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     * @return the lastPaymentDate
     */
    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * @param lastPaymentDate the lastPaymentDate to set
     */
    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * @return the outstandingBal
     */
    public String getOutstandingBal() {
        return outstandingBal;
    }

    /**
     * @param outstandingBal the outstandingBal to set
     */
    public void setOutstandingBal(String outstandingBal) {
        this.outstandingBal = outstandingBal;
    }

    /**
     * @return the isSettled
     */
    public String getIsSettled() {
        return isSettled;
    }

    /**
     * @param isSettled the isSettled to set
     */
    public void setIsSettled(String isSettled) {
        this.isSettled = isSettled;
    }

    /**
     * @return the isExport
     */
    public String getIsExport() {
        return isExport;
    }

    /**
     * @param isExport the isExport to set
     */
    public void setIsExport(String isExport) {
        this.isExport = isExport;
    }

    /**
     * @return the isDisplay
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * @param isDisplay the isDisplay to set
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * @return the exportCur
     */
    public String getExportCur() {
        return exportCur;
    }

    /**
     * @param exportCur the exportCur to set
     */
    public void setExportCur(String exportCur) {
        this.exportCur = exportCur;
    }

    /**
     * @return the curRate
     */
    public String getCurRate() {
        return curRate;
    }

    /**
     * @param curRate the curRate to set
     */
    public void setCurRate(String curRate) {
        this.curRate = curRate;
    }

    /**
     * @return the exportAmount
     */
    public String getExportAmount() {
        return exportAmount;
    }

    /**
     * @param exportAmount the exportAmount to set
     */
    public void setExportAmount(String exportAmount) {
        this.exportAmount = exportAmount;
    }

    /**
     * @return the fixedForex
     */
    public String getFixedForex() {
        return fixedForex;
    }

    /**
     * @param fixedForex the fixedForex to set
     */
    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
    }

    /**
     * @return the noPrinted
     */
    public String getNoPrinted() {
        return noPrinted;
    }

    /**
     * @param noPrinted the noPrinted to set
     */
    public void setNoPrinted(String noPrinted) {
        this.noPrinted = noPrinted;
    }

    /**
     * @return the datePrinted
     */
    public String getDatePrinted() {
        return datePrinted;
    }

    /**
     * @param datePrinted the datePrinted to set
     */
    public void setDatePrinted(String datePrinted) {
        this.datePrinted = datePrinted;
    }

    /**
     * @return the userPrinted
     */
    public String getUserPrinted() {
        return userPrinted;
    }

    /**
     * @param userPrinted the userPrinted to set
     */
    public void setUserPrinted(String userPrinted) {
        this.userPrinted = userPrinted;
    }

    /**
     * @return the isClosed
     */
    public String getIsClosed() {
        return isClosed;
    }

    /**
     * @param isClosed the isClosed to set
     */
    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    /**
     * @return the isDeleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return the preparedBy
     */
    public String getPreparedBy() {
        return preparedBy;
    }

    /**
     * @param preparedBy the preparedBy to set
     */
    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    /**
     * @return the dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the dateUpdated
     */
    public String getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated the dateUpdated to set
     */
    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * @return the idLogin
     */
    public String getIdLogin() {
        return idLogin;
    }

    /**
     * @param idLogin the idLogin to set
     */
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
     * @return the listAcMan
     */
    public List<LabelValueBean> getListAcMan() {
        return listAcMan;
    }

    /**
     * @param listAcMan the listAcMan to set
     */
    public void setListAcMan(List<LabelValueBean> listAcMan) {
        this.listAcMan = listAcMan;
    }

    /**
     * @return the listBillingAccountNo
     */
    public List<LabelValueBean> getListBillingAccountNo() {
        return listBillingAccountNo;
    }

    /**
     * @param listBillingAccountNo the listBillingAccountNo to set
     */
    public void setListBillingAccountNo(List<LabelValueBean> listBillingAccountNo) {
        this.listBillingAccountNo = listBillingAccountNo;
    }

    /**
     * @return the footerInfo
     */
    public List<Map<String, Object>> getFooterInfo() {
        return footerInfo;
    }

    /**
     * @param footerInfo the footerInfo to set
     */
    public void setFooterInfo(List<Map<String, Object>> footerInfo) {
        this.footerInfo = footerInfo;
    }

    /**
     * @return the preparedByName
     */
    public String getPreparedByName() {
        return preparedByName;
    }

    /**
     * @param preparedByName the preparedByName to set
     */
    public void setPreparedByName(String preparedByName) {
        this.preparedByName = preparedByName;
    }

    /**
     * @return the bankFooterInfo
     */
    public List<Map<String, Object>> getBankFooterInfo() {
        return bankFooterInfo;
    }

    /**
     * @param bankFooterInfo the bankFooterInfo to set
     */
    public void setBankFooterInfo(List<Map<String, Object>> bankFooterInfo) {
        this.bankFooterInfo = bankFooterInfo;
    }
    
    /**
     * @return the jobModulesDisplayFlg
     */
    public String getJobModulesDisplayFlg() {
        return jobModulesDisplayFlg;
    }

    /**
     * @param jobModulesDisplayFlg the jobModulesDisplayFlg to set
     */
    public void setJobModulesDisplayFlg(String jobModulesDisplayFlg) {
        this.jobModulesDisplayFlg = jobModulesDisplayFlg;
    }

    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the salutation
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the salutation to set
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    /**
     * @return the customerTypeFlag
     */
    public String getCustomerTypeFlag() {
        return customerTypeFlag;
    }

    /**
     * @param customerTypeFlag the customerTypeFlag to set
     */
    public void setCustomerTypeFlag(String customerTypeFlag) {
        this.customerTypeFlag = customerTypeFlag;
    }

    /**
     * @return the bacBillingCurrency
     */
    public String getBacBillingCurrency() {
        return bacBillingCurrency;
    }

    /**
     * @param bacBillingCurrency the bacBillingCurrency to set
     */
    public void setBacBillingCurrency(String bacBillingCurrency) {
        this.bacBillingCurrency = bacBillingCurrency;
    }

    /**
     * @return the subTotalAmt
     */
    public String getSubTotalAmt() {
        return subTotalAmt;
    }

    /**
     * @param subTotalAmt the subTotalAmt to set
     */
    public void setSubTotalAmt(String subTotalAmt) {
        this.subTotalAmt = subTotalAmt;
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
     * @return the currencyStd
     */
    public String getCurrencyStd() {
        return currencyStd;
    }

    /**
     * @param currencyStd the currencyStd to set
     */
    public void setCurrencyStd(String currencyStd) {
        this.currencyStd = currencyStd;
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

	/**
	 * @return the contactDueDate
	 */
	public String getContactDueDate() {
		return contactDueDate;
	}

	/**
	 * @param contactDueDate the contactDueDate to set
	 */
	public void setContactDueDate(String contactDueDate) {
		this.contactDueDate = contactDueDate;
	}

	/**
	 * @return the contactDelivery
	 */
	public String getContactDelivery() {
		return contactDelivery;
	}

	/**
	 * @param contactDelivery the contactDelivery to set
	 */
	public void setContactDelivery(String contactDelivery) {
		this.contactDelivery = contactDelivery;
	}

	/**
	 * @return the contactDeliveryEmail
	 */
	public String getContactDeliveryEmail() {
		return contactDeliveryEmail;
	}

	/**
	 * @param contactDeliveryEmail the contactDeliveryEmail to set
	 */
	public void setContactDeliveryEmail(String contactDeliveryEmail) {
		this.contactDeliveryEmail = contactDeliveryEmail;
	}

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
	 * @return the billingPeriod
	 */
	public String getBillingPeriod() {
		return billingPeriod;
	}

	/**
	 * @param billingPeriod the billingPeriod to set
	 */
	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

}
