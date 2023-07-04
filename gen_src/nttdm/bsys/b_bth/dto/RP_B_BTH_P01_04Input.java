/*
 * @(#)RP_B_BTH_P01_04Input.java
 *
 *
 */
package nttdm.bsys.b_bth.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BTH_P01_04Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -674991055245806123L;

	/**
     * 
     */
	private String allIdRefs;
	
	/**
	 * 
	 */
	private String fullIdRefs;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
	 * getAllDate item Map
	 */
	private String customerName;
	/**
     * 
     */
	private String documentNoFrom;

	/**
     * 
     */
	private String documentNoTo;

	/**
     * 
     */
	private String billingType;

	/**
     * 
     */
	private String billingAccount;

	/**
     * 
     */
	private String fromDate;

	/**
     * 
     */
	private String toDate;
	
	/**
     * 
     */
    private String cboDeletedStatus;
    
    /**
     * 
     */
    private String cboBillingCurrency;
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
     * AutSign
     */
    private String autSign="";
    
    /**
     * 
     */
    private String documentNo;
    
    private String[] deliveryEmail;
    
    private String[] delivery;
    /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
    private int printOption;
    /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	/**
     * 
     */
	/**
	 * allIdRefs を取得する
	 * 
	 * @return allIdRefs
	 */
	public String getAllIdRefs() {
		return allIdRefs;
	}

	/**
	 * allIdRefs を設定する
	 * 
	 * @param allIdRefs
	 */
	public void setAllIdRefs(String allIdRefs) {
		this.allIdRefs = allIdRefs;
	}

	/**
	 * uvo を取得する
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	/*
	 * add get set
	 */
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDocumentNoFrom() {
		return documentNoFrom;
	}

	public void setDocumentNoFrom(String documentNoFrom) {
		this.documentNoFrom = documentNoFrom;
	}

	public String getDocumentNoTo() {
		return documentNoTo;
	}

	public void setDocumentNoTo(String documentNoTo) {
		this.documentNoTo = documentNoTo;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public String getBillingAccount() {
		return billingAccount;
	}

	public void setBillingAccount(String billingAccount) {
		this.billingAccount = billingAccount;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getCboDeletedStatus() {
		return cboDeletedStatus;
	}

	public void setCboDeletedStatus(String cboDeletedStatus) {
		this.cboDeletedStatus = cboDeletedStatus;
	}

	public String getCboBillingCurrency() {
		return cboBillingCurrency;
	}

	public void setCboBillingCurrency(String cboBillingCurrency) {
		this.cboBillingCurrency = cboBillingCurrency;
	}

	public String getIssueTypeSingpost() {
		return issueTypeSingpost;
	}

	public void setIssueTypeSingpost(String issueTypeSingpost) {
		this.issueTypeSingpost = issueTypeSingpost;
	}

	public String getIssueTypeAuto() {
		return issueTypeAuto;
	}

	public void setIssueTypeAuto(String issueTypeAuto) {
		this.issueTypeAuto = issueTypeAuto;
	}

	public String getIssueTypeManual() {
		return issueTypeManual;
	}

	public void setIssueTypeManual(String issueTypeManual) {
		this.issueTypeManual = issueTypeManual;
	}

	/**
	 * uvo を設定する
	 * 
	 * @param uvo
	 */
	
	public String getFullIdRefs() {
		return fullIdRefs;
	}

	public void setFullIdRefs(String fullIdRefs) {
		this.fullIdRefs = fullIdRefs;
	}

    public String getAutSign() {
        return autSign;
    }

    public void setAutSign(String autSign) {
        this.autSign = autSign;
    }
    
    /**
     * @return the documentNo
     */
    public String getDocumentNo() {
        return documentNo;
    }

    /**
     * @param documentNo the documentNo to set
     */
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
    
    /**
     * deliveryEmail を取得する
     * 
     * @return deliveryEmail
     */
    public String[] getDeliveryEmail() {
        return deliveryEmail;
    }

    /**
     * deliveryEmail を設定する
     * 
     * @param deliveryEmail
     */
    public void setDeliveryEmail(String[] deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    /**
     * delivery を取得する
     * 
     * @return delivery
     */
    public String[] getDelivery() {
        return delivery;
    }

    /**
     * delivery を設定する
     * 
     * @param delivery
     */
    public void setDelivery(String[] delivery) {
        this.delivery = delivery;
    }
    /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	public int getPrintOption() {
		return printOption;
	}

	public void setPrintOption(int printOption) {
		this.printOption = printOption;
	}
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
}