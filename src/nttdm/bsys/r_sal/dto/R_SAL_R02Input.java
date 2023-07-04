/**
 * @(#)R_SAL_R02Input.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * R_SAL_R02Input class.
 * 
 * @author xycao
 */
public class R_SAL_R02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9145210015647412918L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;

	/**
	 * billYear
	 */
	private String tbxBillYearMonth;

	/**
	 * billYearTo
	 */
	private String tbxBillYearMonthTo;

	/**
	 * customerName
	 */
	private String tbxCustomerName;

	/**
	 * affiliateCode
	 */
	private String tbxAffiliateCod;

	/**
	 * invoiceNo
	 */
	private String tbxInvoiceNo;

	/**
	 * subscription
	 */
	private String tbxSubscription;

	/**
	 * serviceName
	 */
	private String tbxServiceName;

	/**
	 * currency
	 */
	private String cboCurrency;

	/**
	 * paymentMethod
	 */
	private String cboPaymentMetho;

	/**
     * 
     */
    private String issueTypeSingpost;
    
    /**
     * 
     */
    private String issueTypeAuto;
    
    /**
     * 
     */
    private String issueTypeManual;

	/**
	 * Revenue Code
	 */
	private String tbxRevenueCode = "";

	/**
	 * Category
	 */
	private String cboCategory = "";
	
	/**
     * CustomerType
     */
    private String tbxCustomerType = "";
    /**
     * Rate Mode
     */
    private String cboRateMode;

    /**
     * Service Date Start (From)
     */
    private String serviceDateStartFrom;

    /**
     * Service Date Start (To)
     */
    private String serviceDateStartTo;

    /**
     * Service Date End (From)
     */
    private String serviceDateEndFrom;

    /**
     * Service Date End (To)
     */
    private String serviceDateEndTo;

	/**
	 * @return the Revenue Code
	 */
	public String getTbxRevenueCode() {
		return tbxRevenueCode;
	}

	/**
	 * @param tbxRevenueCode
	 *            the Revenue Code to set
	 */
	public void setTbxRevenueCode(String tbxRevenueCode) {
		this.tbxRevenueCode = tbxRevenueCode;
	}
	
	/**
	 * @return the Category
	 */
	public String getCboCategory() {
		return cboCategory;
	}
	
	/**
	 * @param cboCategory
	 *            the Category to set
	 */
	public void setCboCategory(String cboCategory) {
		this.cboCategory = cboCategory;
	}

	/**
	 * @return the uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * @param uvo
	 *            the uvo to set
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * @return the row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex
	 *            the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the tbxCustomerName
	 */
	public String getTbxCustomerName() {
		return tbxCustomerName;
	}

	/**
	 * @param tbxCustomerName
	 *            the tbxCustomerName to set
	 */
	public void setTbxCustomerName(String tbxCustomerName) {
		this.tbxCustomerName = tbxCustomerName;
	}

	/**
	 * @return the tbxAffiliateCod
	 */
	public String getTbxAffiliateCod() {
		return tbxAffiliateCod;
	}

	/**
	 * @param tbxAffiliateCod
	 *            the tbxAffiliateCod to set
	 */
	public void setTbxAffiliateCod(String tbxAffiliateCod) {
		this.tbxAffiliateCod = tbxAffiliateCod;
	}

	/**
	 * @return the tbxInvoiceNo
	 */
	public String getTbxInvoiceNo() {
		return tbxInvoiceNo;
	}

	/**
	 * @param tbxInvoiceNo
	 *            the tbxInvoiceNo to set
	 */
	public void setTbxInvoiceNo(String tbxInvoiceNo) {
		this.tbxInvoiceNo = tbxInvoiceNo;
	}

	/**
	 * @return the tbxSubscription
	 */
	public String getTbxSubscription() {
		return tbxSubscription;
	}

	/**
	 * @param tbxSubscription
	 *            the tbxSubscription to set
	 */
	public void setTbxSubscription(String tbxSubscription) {
		this.tbxSubscription = tbxSubscription;
	}

	/**
	 * @return the tbxServiceName
	 */
	public String getTbxServiceName() {
		return tbxServiceName;
	}

	/**
	 * @param tbxServiceName
	 *            the tbxServiceName to set
	 */
	public void setTbxServiceName(String tbxServiceName) {
		this.tbxServiceName = tbxServiceName;
	}

	/**
	 * @return the cboCurrency
	 */
	public String getCboCurrency() {
		return cboCurrency;
	}

	/**
	 * @param cboCurrency
	 *            the cboCurrency to set
	 */
	public void setCboCurrency(String cboCurrency) {
		this.cboCurrency = cboCurrency;
	}

	/**
	 * @return the cboPaymentMetho
	 */
	public String getCboPaymentMetho() {
		return cboPaymentMetho;
	}

	/**
	 * @param cboPaymentMetho
	 *            the cboPaymentMetho to set
	 */
	public void setCboPaymentMetho(String cboPaymentMetho) {
		this.cboPaymentMetho = cboPaymentMetho;
	}

    /**
     * @return the tbxBillYearMonth
     */
    public String getTbxBillYearMonth() {
        return tbxBillYearMonth;
    }

    /**
     * @param tbxBillYearMonth the tbxBillYearMonth to set
     */
    public void setTbxBillYearMonth(String tbxBillYearMonth) {
        this.tbxBillYearMonth = tbxBillYearMonth;
    }

    /**
     * @return the tbxBillYearMonthTo
     */
    public String getTbxBillYearMonthTo() {
        return tbxBillYearMonthTo;
    }

    /**
     * @param tbxBillYearMonthTo the tbxBillYearMonthTo to set
     */
    public void setTbxBillYearMonthTo(String tbxBillYearMonthTo) {
        this.tbxBillYearMonthTo = tbxBillYearMonthTo;
    }

    /**
     * @return the issueTypeSingpost
     */
    public String getIssueTypeSingpost() {
        return issueTypeSingpost;
    }

    /**
     * @param issueTypeSingpost the issueTypeSingpost to set
     */
    public void setIssueTypeSingpost(String issueTypeSingpost) {
        this.issueTypeSingpost = issueTypeSingpost;
    }

    /**
     * @return the issueTypeAuto
     */
    public String getIssueTypeAuto() {
        return issueTypeAuto;
    }

    /**
     * @param issueTypeAuto the issueTypeAuto to set
     */
    public void setIssueTypeAuto(String issueTypeAuto) {
        this.issueTypeAuto = issueTypeAuto;
    }

    /**
     * @return the issueTypeManual
     */
    public String getIssueTypeManual() {
        return issueTypeManual;
    }

    /**
     * @param issueTypeManual the issueTypeManual to set
     */
    public void setIssueTypeManual(String issueTypeManual) {
        this.issueTypeManual = issueTypeManual;
    }

    /**
     * @return the tbxCustomerType
     */
    public String getTbxCustomerType() {
        return tbxCustomerType;
    }

    /**
     * @param tbxCustomerType the tbxCustomerType to set
     */
    public void setTbxCustomerType(String tbxCustomerType) {
        this.tbxCustomerType = tbxCustomerType;
    }

    public String getCboRateMode() {
        return cboRateMode;
    }

    public void setCboRateMode(String cboRateMode) {
        this.cboRateMode = cboRateMode;
    }

    public String getServiceDateStartFrom() {
        return serviceDateStartFrom;
    }

    public void setServiceDateStartFrom(String serviceDateStartFrom) {
        this.serviceDateStartFrom = serviceDateStartFrom;
    }

    public String getServiceDateStartTo() {
        return serviceDateStartTo;
    }

    public void setServiceDateStartTo(String serviceDateStartTo) {
        this.serviceDateStartTo = serviceDateStartTo;
    }

    public String getServiceDateEndFrom() {
        return serviceDateEndFrom;
    }

    public void setServiceDateEndFrom(String serviceDateEndFrom) {
        this.serviceDateEndFrom = serviceDateEndFrom;
    }

    public String getServiceDateEndTo() {
        return serviceDateEndTo;
    }

    public void setServiceDateEndTo(String serviceDateEndTo) {
        this.serviceDateEndTo = serviceDateEndTo;
    }
}