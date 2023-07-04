/*
 * @(#)RP_B_BTH_P01_02Input.java
 *
 *
 */
package nttdm.bsys.b_bth.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BTH_P01_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8788572987771890860L;

	/**
     * 
     */
	private String customerName;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	private String tbxCustomerId;
	private String cboCustomerType;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	/**
     * 
     */
	private String documentNoFrom;

	/**
     * 
     */
	private String documentNoTo;
	
	private String[] deliveryEmail;
    
    private String[] delivery;

	/**
     * 
     */
	private String billingType;

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
	private String billingAccount;

	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;
	
	private String[] idRefs;
	
	/**
     * 
     */
    private String cboDeletedStatus;
    
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

    private String documentNo;
    
    private String cboBillingCurrency;

    // Add #156 Start
    private String billCnAmtNegative;
    // Add #156 End

	public String getCboBillingCurrency() {
        return cboBillingCurrency;
    }

    public void setCboBillingCurrency(String cboBillingCurrency) {
        this.cboBillingCurrency = cboBillingCurrency;
    }

    public String[] getIdRefs() {
		return idRefs;
	}

	public void setIdRefs(String[] idRefs) {
		this.idRefs = idRefs;
	}

	/**
	 * customerName を取得する
	 * 
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * customerName を設定する
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * documentNoFrom を取得する
	 * 
	 * @return documentNoFrom
	 */
	public String getDocumentNoFrom() {
		return documentNoFrom;
	}

	/**
	 * documentNoFrom を設定する
	 * 
	 * @param documentNoFrom
	 */
	public void setDocumentNoFrom(String documentNoFrom) {
		this.documentNoFrom = documentNoFrom;
	}

	/**
	 * documentNoTo を取得する
	 * 
	 * @return documentNoTo
	 */
	public String getDocumentNoTo() {
		return documentNoTo;
	}

	/**
	 * documentNoTo を設定する
	 * 
	 * @param documentNoTo
	 */
	public void setDocumentNoTo(String documentNoTo) {
		this.documentNoTo = documentNoTo;
	}

	/**
	 * billingType を取得する
	 * 
	 * @return billingType
	 */
	public String getBillingType() {
		return billingType;
	}

	/**
	 * billingType を設定する
	 * 
	 * @param billingType
	 */
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	/**
	 * fromDate を取得する
	 * 
	 * @return fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * fromDate を設定する
	 * 
	 * @param fromDate
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * toDate を取得する
	 * 
	 * @return toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * toDate を設定する
	 * 
	 * @param toDate
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * billingAccount を取得する
	 * 
	 * @return billingAccount
	 */
	public String getBillingAccount() {
		return billingAccount;
	}

	/**
	 * billingAccount を設定する
	 * 
	 * @param billingAccount
	 */
	public void setBillingAccount(String billingAccount) {
		this.billingAccount = billingAccount;
	}

	/**
	 * row を取得する
	 * 
	 * @return row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * row を設定する
	 * 
	 * @param row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * startIndex を取得する
	 * 
	 * @return startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * startIndex を設定する
	 * 
	 * @param startIndex
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

    /**
     * @return the cboDeletedStatus
     */
    public String getCboDeletedStatus() {
        return cboDeletedStatus;
    }

    /**
     * @param cboDeletedStatus the cboDeletedStatus to set
     */
    public void setCboDeletedStatus(String cboDeletedStatus) {
        this.cboDeletedStatus = cboDeletedStatus;
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
    
    // Add #156 Start
	/**
	 * @return the billCnAmtNegative
	 */
	public String getBillCnAmtNegative() {
		return billCnAmtNegative;
	}

	/**
	 * @param billCnAmtNegative the billCnAmtNegative to set
	 */
	public void setBillCnAmtNegative(String billCnAmtNegative) {
		this.billCnAmtNegative = billCnAmtNegative;
	}
	// Add #156 End

	/*#263 [T11] Add customer type at inquiry screen and export result CT 08062017 ST*/
	
	public String getTbxCustomerId() {
		return tbxCustomerId;
	}

	public void setTbxCustomerId(String tbxCustomerId) {
		this.tbxCustomerId = tbxCustomerId;
	}

	public String getCboCustomerType() {
		return cboCustomerType;
	}

	public void setCboCustomerType(String cboCustomerType) {
		this.cboCustomerType = cboCustomerType;
	}
	
	/*#263 [T11] Add customer type at inquiry screen and export result CT 08062017 EN*/
}