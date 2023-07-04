/**
 * @(#)G_BIL_P01Bean.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.bean;

import java.util.Date;

/**
 * Bean class used for G_BIL_P01 calculation
 */
public class G_BIL_P01Bean {

    private String idBillingAccount;
    private String billType;
    private String payMethod;
    private String idCust;
    private String adrType;
    private String contactType;
    private String billCurrency;
    private Object expCur;
    private Object fixedForex;
    private String isDisplay;
    private String displayDisc;
    private String isDisplayExpAMT;
    private String idPlan;
    private String idCustPlan;
    private String idCustPlanGrp;
    private String idCustPlanLink;
    
    private String jobNo;
    private String isDisplayJobNo;
    private String custPo2;
    private String svcLevel1;
    private String svcLevel2;

    private Object zipCode;
    private Object country;
    private Object address1;
    private Object address2;
    private Object address3;
    private Object address4;
    private Object telNo;
    private Object faxNo;
    private Object contractName;
    private Object email;
    private Object emailCc;

    private String isRecurring;
    private String isSingpost;
    private String billingStatus;
    private String serviceStatus;
    private String planStatus;
    private Date lastBillFrom;
    private Date lastBillTo;

    private Date billFrom;
    private Date billTo;

    private Date svcStart;
    private Date svcEnd;
    private String BI;

    private String itemDesc;
    private String billDesc;
    private int updateStatus;
    private String batchId;

    private String postingMonth;
    private String cal;
    private String refId;

    private String proRateBase;
    private long proRateBaseNo;
    private String isLumpSum;
    private String isLumpSumDisc;
    private int applyGST;

    private double itemHeaderAmt;
    private double itemHeaderDisc;
    private String itemTaxCode;
    private double itemTaxAmt;
    private double itemHeaderSubtotal;
    private double upPrice;
    private double amount;
    private double expAmount;
    private double expGst;
    private long quantity;
    private double discAmt;
    private String discCal;
    private double subtotal;
    private String taxCode;
    private int taxRate;
    
    private int itemLevel;
    private String idPlanGrp;

    private Date minSvcStart;
    private Date minSvcEnd;
    private String isDisplayMinSvc;
    private String itemCat;
    
    private String custName;
    private String salutation;
    private String delicery;
    private String deliceryEmail;
    // #192 Add
    private String billOption;
    
    // #203 Start
    private String location;
    // #203 End
    
    private String taxType;
    
    /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 20190603*/
    private double taxExemptedAmout;
    private double totalTaxExemptedAmout;
    /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 20190603*/
    
    /**
     * @return the minSvcStart
     */
    public Date getMinSvcStart() {
        return minSvcStart;
    }

    /**
     * @param minSvcStart the minSvcStart to set
     */
    public void setMinSvcStart(Object minSvcStart) {
        if (minSvcStart != null) {
            this.minSvcStart = (Date) minSvcStart;
        } else {
            this.minSvcStart = null;
        }
    }

    /**
     * @return the minSvcEnd
     */
    public Date getMinSvcEnd() {
        return minSvcEnd;
    }

    /**
     * @param minSvcEnd the minSvcEnd to set
     */
    public void setMinSvcEnd(Object minSvcEnd) {
        if (minSvcEnd != null) {
            this.minSvcEnd = (Date) minSvcEnd;
        } else {
            this.minSvcEnd = null;
        }
    }

    /**
     * @return the isDisplayMinSvc
     */
    public String getIsDisplayMinSvc() {
        return isDisplayMinSvc;
    }

    /**
     * @param isDisplayMinSvc the isDisplayMinSvc to set
     */
    public void setIsDisplayMinSvc(String isDisplayMinSvc) {
        this.isDisplayMinSvc = isDisplayMinSvc;
    }

    /**
     * @return the itemCat
     */
    public String getItemCat() {
        return itemCat;
    }

    /**
     * @param itemCat the itemCat to set
     */
    public void setItemCat(String itemCat) {
        this.itemCat = itemCat;
    }

    /**
     * T_CUST_PLAN_LINK.ITEM_TYPE
     */
    private String itemType;
    
    /**
     * Sub service Rate Mode
     */
    private String rateMode;

    /**
     * Calculate month number
     */
    private int nMonths;
    
    /**
     * Calculate day number
     */
    private long nDays;
    
    /**
     * Calculate base day number per month
     */
    private long p;

    /**
     *  nImport
     */
    private int nImport;

    /**
     * Login user id
     */
    private String idLogin;

    /**
     * audit trail
     */
    private Integer idAudit = null;

    /**
     * @return the billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType
     *            the billType to set
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * @return the payMethod
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * @param payMethod
     *            the payMethod to set
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * @return the idCust
     */
    public String getIdCust() {
        return idCust;
    }

    /**
     * @param idCust
     *            the idCust to set
     */
    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    /**
     * @return the adrType
     */
    public String getAdrType() {
        return adrType;
    }

    /**
     * @param adrType
     *            the adrType to set
     */
    public void setAdrType(String adrType) {
        this.adrType = adrType;
    }

    /**
     * @return the billCurrency
     */
    public String getBillCurrency() {
        return billCurrency;
    }

    /**
     * @param billCurrency
     *            the billCurrency to set
     */
    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    /**
     * @return the expCur
     */
    public Object getExpCur() {
        return expCur;
    }

    /**
     * @param expCur
     *            the expCur to set
     */
    public void setExpCur(Object expCur) {
        this.expCur = expCur;
    }

    /**
     * @return the fixedForex
     */
    public Object getFixedForex() {
        return fixedForex;
    }

    /**
     * @param fixedForex
     *            the fixedForex to set
     */
    public void setFixedForex(Object fixedForex) {
        this.fixedForex = fixedForex;
    }

    /**
     * @return the isDisplay
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * @param isDisplay
     *            the isDisplay to set
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * @return the displayDisc
     */
    public String getDisplayDisc() {
        return displayDisc;
    }

    /**
     * @param displayDisc
     *            the displayDisc to set
     */
    public void setDisplayDisc(String displayDisc) {
        this.displayDisc = displayDisc;
    }

    /**
     * @param idPlan
     *            the idPlan to set
     */
    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    /**
     * @return the idPlan
     */
    public String getIdPlan() {
        return idPlan;
    }

    /**
     * @return the idCustPlan
     */
    public String getIdCustPlan() {
        return idCustPlan;
    }

    /**
     * @param idCustPlan
     *            the idCustPlan to set
     */
    public void setIdCustPlan(String idCustPlan) {
        this.idCustPlan = idCustPlan;
    }

    /**
     * @return the idCustPlanLink
     */
    public String getIdCustPlanLink() {
        return idCustPlanLink;
    }

    /**
     * @param idCustPlanLink the idCustPlanLink to set
     */
    public void setIdCustPlanLink(String idCustPlanLink) {
        this.idCustPlanLink = idCustPlanLink;
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
     * @return the svcLevel1
     */
    public String getSvcLevel1() {
        return svcLevel1;
    }

    /**
     * @param svcLevel1 the svcLevel1 to set
     */
    public void setSvcLevel1(String svcLevel1) {
        this.svcLevel1 = svcLevel1;
    }

    /**
     * @return the svcLevel2
     */
    public String getSvcLevel2() {
        return svcLevel2;
    }

    /**
     * @param svcLevel2 the svcLevel2 to set
     */
    public void setSvcLevel2(String svcLevel2) {
        this.svcLevel2 = svcLevel2;
    }

    /**
     * @return the idCustPlanGrp
     */
    public String getIdCustPlanGrp() {
        return idCustPlanGrp;
    }

    /**
     * @param idCustPlanGrp the idCustPlanGrp to set
     */
    public void setIdCustPlanGrp(String idCustPlanGrp) {
        this.idCustPlanGrp = idCustPlanGrp;
    }
    
    /**
     * @return the zipCode
     */
    public Object getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(Object zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the address1
     */
    public Object getAddress1() {
        return address1;
    }

    /**
     * @param address1
     *            the address1 to set
     */
    public void setAddress1(Object address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public Object getAddress2() {
        return address2;
    }

    /**
     * @param address2
     *            the address2 to set
     */
    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public Object getAddress3() {
        return address3;
    }

    /**
     * @param address3
     *            the address3 to set
     */
    public void setAddress3(Object address3) {
        this.address3 = address3;
    }

    /**
     * @return the address4
     */
    public Object getAddress4() {
        return address4;
    }

    /**
     * @param address4
     *            the address4 to set
     */
    public void setAddress4(Object address4) {
        this.address4 = address4;
    }

    /**
     * @return the telNo
     */
    public Object getTelNo() {
        return telNo;
    }

    /**
     * @param telNo
     *            the telNo to set
     */
    public void setTelNo(Object telNo) {
        this.telNo = telNo;
    }

    /**
     * @return the faxNo
     */
    public Object getFaxNo() {
        return faxNo;
    }

    /**
     * @param faxNo
     *            the faxNo to set
     */
    public void setFaxNo(Object faxNo) {
        this.faxNo = faxNo;
    }

    /**
     * @return the contractName
     */
    public Object getContractName() {
        return contractName;
    }

    /**
     * @param contractName
     *            the contractName to set
     */
    public void setContractName(Object contractName) {
        this.contractName = contractName;
    }

    /**
     * @return the email
     */
    public Object getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(Object email) {
        this.email = email;
    }

    /**
     * @return the isRecurring
     */
    public String getIsRecurring() {
        return isRecurring;
    }

    /**
     * @param isRecurring
     *            the isRecurring to set
     */
    public void setIsRecurring(String isRecurring) {
        this.isRecurring = isRecurring;
    }

    /**
     * @return the isSingpost
     */
    public String getIsSingpost() {
        return isSingpost;
    }

    /**
     * @param isSingpost
     *            the isSingpost to set
     */
    public void setIsSingpost(String isSingpost) {
        this.isSingpost = isSingpost;
    }

    /**
     * @return the serviceStatus
     */
    public String getServiceStatus() {
        return serviceStatus;
    }

    /**
     * @param serviceStatus
     *            the serviceStatus to set
     */
    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    /**
     * @return the planStatus
     */
    public String getPlanStatus() {
        return planStatus;
    }

    /**
     * @param planStatus
     *            the planStatus to set
     */
    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    /**
     * @return the lastBillFrom
     */
    public Date getLastBillFrom() {
        return lastBillFrom;
    }

    /**
     * @param lastBillFrom
     *            the lastBillFrom to set
     */
    public void setLastBillFrom(Object lastBillFrom) {
        if (lastBillFrom != null) {
            this.lastBillFrom = (Date)lastBillFrom;
        } else {
            this.lastBillFrom = null;
        }
    }

    /**
     * @return the lastBillTo
     */
    public Date getLastBillTo() {
        return lastBillTo;
    }

    /**
     * @param lastBillTo
     *            the lastBillTo to set
     */
    public void setLastBillTo(Object lastBillTo) {
        if (lastBillTo != null) {
            this.lastBillTo = (Date) lastBillTo;
        } else {
            this.lastBillTo = null;
        }
    }

    /**
     * @return the svcStart
     */
    public Date getSvcStart() {
        return svcStart;
    }

    /**
     * @param svcStart
     *            the svcStart to set
     */
    public void setSvcStart(Object svcStart) {
        if (svcStart != null) {
            this.svcStart = (Date) svcStart;
        } else {
            this.svcStart = null;
        }
    }

    /**
     * @return the svcEnd
     */
    public Date getSvcEnd() {
        return svcEnd;
    }

    /**
     * @param svcEnd
     *            the svcEnd to set
     */
    public void setSvcEnd(Object svcEnd) {
        if (svcEnd != null) {
            this.svcEnd = (Date) svcEnd;
        } else {
            this.svcEnd = null;
        }
    }

    /**
     * @return the bI
     */
    public String getBI() {
        return BI;
    }

    /**
     * @param bi
     *            the bI to set
     */
    public void setBI(String bi) {
        BI = bi;
    }

    /**
     * @return the itemDesc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * @param itemDesc
     *            the itemDesc to set
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * 
     * @return the billDesc
     */
    public String getBillDesc() {
        return billDesc;
    }

    /**
     * 
     * @param billDesc
     *            the billDesc to set
     */
    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    /**
     * @return the updateStatus
     */
    public int getUpdateStatus() {
        return updateStatus;
    }

    /**
     * @param updateStatus
     *            the updateStatus to set
     */
    public void setUpdateStatus(int updateStatus) {
        this.updateStatus = updateStatus;
    }

    /**
     * @return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId
     *            the batchId to set
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * @param contactType
     *            the contactType to set
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * @return the contactType
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @return the billFrom
     */
    public Date getBillFrom() {
        return billFrom;
    }

    /**
     * @param billFrom
     *            the billFrom to set
     */
    public void setBillFrom(Date billFrom) {
        this.billFrom = billFrom;
    }

    /**
     * @return the billTo
     */
    public Date getBillTo() {
        return billTo;
    }

    /**
     * @param billTo
     *            the billTo to set
     */
    public void setBillTo(Date billTo) {
        this.billTo = billTo;
    }

    /**
     * @param postingMonth
     *            the postingMonth to set
     */
    public void setPostingMonth(String postingMonth) {
        this.postingMonth = postingMonth;
    }

    /**
     * @return the postingMonth
     */
    public String getPostingMonth() {
        return postingMonth;
    }

    /**
     * @param cal
     *            the cal to set
     */
    public void setCal(String cal) {
        this.cal = cal;
    }

    /**
     * @return the cal
     */
    public String getCal() {
        return cal;
    }

    /**
     * @param refId
     *            the refId to set
     */
    public void setRefId(String refId) {
        this.refId = refId;
    }

    /**
     * @return the refId
     */
    public String getRefId() {
        return refId;
    }

    /**
     * @return the proRateBase
     */
    public String getProRateBase() {
        return proRateBase;
    }

    /**
     * @param proRateBase
     *            the proRateBase to set
     */
    public void setProRateBase(String proRateBase) {
        this.proRateBase = proRateBase;
    }

    /**
     * @param proRateBase
     *            the proRateBase to set
     */
    public void setProRateBaseNo(Object proRateBase) {
        if (proRateBase != null && !"".equals(proRateBase)){
            this.proRateBaseNo = Long.parseLong(proRateBase.toString());
        }
    }

    /**
     * @return the proRateBase
     */
    public long getProRateBaseNo() {
        return proRateBaseNo;
    }

    /**
     * @return the isLumpSum
     */
    public String getIsLumpSum() {
        return isLumpSum;
    }

    /**
     * @param isLumpSum
     *            the isLumpSum to set
     */
    public void setIsLumpSum(String isLumpSum) {
        this.isLumpSum = isLumpSum;
    }

    /**
     * @return the isLumpSumDisc
     */
    public String getIsLumpSumDisc() {
        return isLumpSumDisc;
    }

    /**
     * @param isLumpSumDisc
     *            the isLumpSumDisc to set
     */
    public void setIsLumpSumDisc(String isLumpSumDisc) {
        this.isLumpSumDisc = isLumpSumDisc;
    }

    /**
     * @param isDisplayDesc
     *            the isDisplayDesc to set
     */
    public void setIsDisplayExpAMT(String isDisplayDesc) {
        this.isDisplayExpAMT = isDisplayDesc;
    }

    /**
     * @return the isDisplayDesc
     */
    public String getIsDisplayExpAMT() {
        return isDisplayExpAMT;
    }

    /**
     * @param applyGST
     *            the applyGST to set
     */
    public void setApplyGST(int applyGST) {
        this.applyGST = applyGST;
    }

    /**
     * @return the applyGST
     */
    public int getApplyGST() {
        return applyGST;
    }

    /**
     * @return the itemHeaderAmt
     */
    public double getItemHeaderAmt() {
        return itemHeaderAmt;
    }

    /**
     * @param itemHeaderAmt the itemHeaderAmt to set
     */
    public void setItemHeaderAmt(double itemHeaderAmt) {
        this.itemHeaderAmt = itemHeaderAmt;
    }
    
    /**
     * @return the itemHeaderDisc
     */
    public double getItemHeaderDisc() {
        return itemHeaderDisc;
    }

    /**
     * @param itemHeaderDisc the itemHeaderDisc to set
     */
    public void setItemHeaderDisc(double itemHeaderDisc) {
        this.itemHeaderDisc = itemHeaderDisc;
    }

    /**
     * @return the itemHeaderTaxCode
     */
    public String getItemTaxCode() {
        return itemTaxCode;
    }

    /**
     * @param itemHeaderTaxCode the itemHeaderTaxCode to set
     */
    public void setItemTaxCode(String itemTaxCode) {
        this.itemTaxCode = itemTaxCode;
    }

    /**
     * @return the itemTaxAmt
     */
    public double getItemTaxAmt() {
        return itemTaxAmt;
    }

    /**
     * @param itemTaxAmt the itemTaxAmt to set
     */
    public void setItemTaxAmt(double itemTaxAmt) {
        this.itemTaxAmt = itemTaxAmt;
    }

    /**
     * @return the itemHeaderSubtotal
     */
    public double getItemHeaderSubtotal() {
        return itemHeaderSubtotal;
    }

    /**
     * @param itemHeaderSubtotal the itemHeaderSubtotal to set
     */
    public void setItemHeaderSubtotal(double itemHeaderSubtotal) {
        this.itemHeaderSubtotal = itemHeaderSubtotal;
    }

    /**
     * @return the upPrice
     */
    public double getUpPrice() {
        return upPrice;
    }

    /**
     * @param upPrice
     *            the upPrice to set
     */
    public void setUpPrice(Object upPrice) {
        if (upPrice != null) {
            this.upPrice = Double.parseDouble(upPrice.toString());
        } else {
            this.upPrice = 0D;
        }
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(Object amount) {
        if (amount != null) {
            this.amount = Double.parseDouble(amount.toString());
        } else {
            this.amount = 0D;
        }
    }

    /**
     * @return the expAmount
     */
    public double getExpAmount() {
        return expAmount;
    }

    /**
     * @param expAmount the expAmount to set
     */
    public void setExpAmount(double expAmount) {
        this.expAmount = expAmount;
    }

    /**
     * @return the expGst
     */
    public double getExpGst() {
        return expGst;
    }

    /**
     * @param expGst the expGst to set
     */
    public void setExpGst(double expGst) {
        this.expGst = expGst;
    }

    /**
     * @return the quantity
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(Object quantity) {
        if (quantity != null) {
            this.quantity = Long.parseLong(quantity.toString());
        } else {
            this.quantity = 0L;
        }
    }

    /**
     * @return the discAmt
     */
    public double getDiscAmt() {
        return discAmt;
    }

    /**
     * @param discAmt
     *            the discAmt to set
     */
    public void setDiscAmt(double discAmt) {
        this.discAmt=discAmt;
    }

    /**
     * @return the discCal
     */
    public String getDiscCal() {
        return discCal;
    }

    /**
     * @param discCal
     *            the discCal to set
     */
    public void setDiscCal(String discCal) {
        this.discCal = discCal;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal
     *            the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the taxCode
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * @param taxCode
     *            the taxCode to set
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    /**
     * @return the taxRate
     */
    public int getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate
     *            the taxRate to set
     */
    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the itemLevel
     */
    public int getItemLevel() {
        return itemLevel;
    }

    /**
     * @param itemLevel
     *            the itemLevel to set
     */
    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    /**
     * @return the idPlanGrp
     */
    public String getIdPlanGrp() {
        return idPlanGrp;
    }

    /**
     * @param idPlanGrp
     *            the idPlanGrp to set
     */
    public void setIdPlanGrp(String idPlanGrp) {
        this.idPlanGrp = idPlanGrp;
    }

    /**
     * @param idBillingAccount
     *            the idBillingAccount to set
     */
    public void setIdBillingAccount(String idBillingAccount) {
        this.idBillingAccount = idBillingAccount;
    }

    /**
     * @return the idBillingAccount
     */
    public String getIdBillingAccount() {
        return idBillingAccount;
    }

    /**
     * @param rateMode
     *            the rateMode to set
     */
    public void setRateMode(String rateMode) {
        this.rateMode = rateMode;
    }

    /**
     * @return the rateMode
     */
    public String getRateMode() {
        return rateMode;
    }

    /**
     * @return the nMonths
     */
    public int getNMonths() {
        return nMonths;
    }

    /**
     * @param months
     *            the nMonths to set
     */
    public void setNMonths(int months) {
        nMonths = months;
    }

    /**
     * @return the nDays
     */
    public long getNDays() {
        return nDays;
    }

    /**
     * @param days
     *            the nDays to set
     */
    public void setNDays(long days) {
        nDays = days;
    }

    /**
     * @param p
     *            the p to set
     */
    public void setP(long p) {
        this.p = p;
    }

    /**
     * @return the p
     */
    public long getP() {
        return p;
    }

    /**
     * @param nImport
     *            the nImport to set
     */
    public void setNImport(int nImport) {
        this.nImport = nImport;
    }

    /**
     * @return the nImport
     */
    public int getNImport() {
        return nImport;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(Object country) {
        this.country = country;
    }

    /**
     * @return the country
     */
    public Object getCountry() {
        return country;
    }

    /**
     * @param idLogin
     *            the idLogin to set
     */
    public void setIdLogin(String idLogin) {
        this.idLogin = idLogin;
    }

    /**
     * @return the idLogin
     */
    public String getIdLogin() {
        return idLogin;
    }

    /**
     * @return the idAudit
     */
    public Integer getIdAudit() {
        return idAudit;
    }

    /**
     * @param idAudit
     *            the idAudit to set
     */
    public void setIdAudit(Integer idAudit) {
        this.idAudit = idAudit;
    }

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
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
     * @return the isDisplayJobNo
     */
    public String getIsDisplayJobNo() {
        return isDisplayJobNo;
    }

    /**
     * @param isDisplayJobNo the isDisplayJobNo to set
     */
    public void setIsDisplayJobNo(String isDisplayJobNo) {
        this.isDisplayJobNo = isDisplayJobNo;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
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

	/**
	 * @return the emailCc
	 */
	public Object getEmailCc() {
		return emailCc;
	}

	/**
	 * @param emailCc the emailCc to set
	 */
	public void setEmailCc(Object emailCc) {
		this.emailCc = emailCc;
	}

	/**
	 * @return the custPo2
	 */
	public String getCustPo2() {
		return custPo2;
	}

	/**
	 * @param custPo2 the custPo2 to set
	 */
	public void setCustPo2(String custPo2) {
		this.custPo2 = custPo2;
	}

	/**
	 * @return the billOption
	 */
	public String getBillOption() {
		return billOption;
	}

	/**
	 * @param billOption the billOption to set
	 */
	public void setBillOption(String billOption) {
		this.billOption = billOption;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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

	//#594: [B1] Customer Maintenance CT 20190604
	public double getTaxExemptedAmout() {
		return taxExemptedAmout;
	}

	public void setTaxExemptedAmout(double taxExemptedAmout) {
		this.taxExemptedAmout = taxExemptedAmout;
	}

	public double getTotalTaxExemptedAmout() {
		return totalTaxExemptedAmout;
	}

	public void setTotalTaxExemptedAmout(double totalTaxExemptedAmout) {
		this.totalTaxExemptedAmout = totalTaxExemptedAmout;
	}
	//#594: [B1] Customer Maintenance CT 20190604
}
