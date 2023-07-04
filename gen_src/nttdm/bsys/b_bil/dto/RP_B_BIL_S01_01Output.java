/*
 * @(#)RP_B_BIL_S01_01Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S01_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7041589992428617037L;

	/**
     * 
     */
	private String accessType;
	
	private String fullySettled;
    
    private String partiallySettled;
    
    private String outstanding;
    
    private String cboDeletedStatus;
    
    private String issueTypeSingpost;
    
    private String issueTypeAuto;
    
    private String issueTypeManual;
    
    private String manualBillButtonShowFlg;

    private String nonTaxInvoiceShowFlg;
    
    private String[] deliveryEmail;
    private String deliveryEmail1;
    private String deliveryEmail2;
    
    private String[] delivery;
    private String delivery1;
    private String delivery2;
    private String delivery4;
    
    //add for #143 Start
	private String tbxBillingReference;
	
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	private String tbxCustomerId;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	private String tbxCustomerName;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	private String cboCustomerType;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	
	private String tbxBillingAccountNo;
	private String jobNo;
	private String cboTransactionType;
	private String cboBillingCurrency;
	private String tbxBillingDateFrom;
	private String tbxBillingDateTo;
	private String cboDocumentStatus;
	private Integer totalRow;
	private Integer row;
	private Integer startIndex;
	private List<HashMap<String, Object>> listReport;
    //add for #143 End
    
	// Add #156 Start
    private String billCnAmtNegative;
	// Add #156 End
    
    // #191 Start
    private String issueTypeSingpostValue;
    private String issueTypeAutoValue;
    private String issueTypeManualValue;
    // #191 End
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
    private String cboCategory;
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	
	/**
	 * accessType を�?�得�?�る
	 * 
	 * @return accessType
	 */
	public String getAccessType() {
		return accessType;
	}

	/**
	 * accessType を設定�?�る
	 * 
	 * @param accessType
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

    /**
     * @return the fullySettled
     */
    public String getFullySettled() {
        return fullySettled;
    }

    /**
     * @param fullySettled the fullySettled to set
     */
    public void setFullySettled(String fullySettled) {
        this.fullySettled = fullySettled;
    }

    /**
     * @return the partiallySettled
     */
    public String getPartiallySettled() {
        return partiallySettled;
    }

    /**
     * @param partiallySettled the partiallySettled to set
     */
    public void setPartiallySettled(String partiallySettled) {
        this.partiallySettled = partiallySettled;
    }

    /**
     * @return the outstanding
     */
    public String getOutstanding() {
        return outstanding;
    }

    /**
     * @param outstanding the outstanding to set
     */
    public void setOutstanding(String outstanding) {
        this.outstanding = outstanding;
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

    public String getManualBillButtonShowFlg() {
        return manualBillButtonShowFlg;
    }

    public void setManualBillButtonShowFlg(String manualBillButtonShowFlg) {
        this.manualBillButtonShowFlg = manualBillButtonShowFlg;
    }
    
    /**
     * nonTaxInvoiceShowFlg を取得する
     * 
     * @return nonTaxInvoiceShowFlg
     */
    public String getNonTaxInvoiceShowFlg() {
        return nonTaxInvoiceShowFlg;
    }

    /**
     * nonTaxInvoiceShowFlg を設定する
     * 
     * @param nonTaxInvoiceShowFlg
     */
    public void setNonTaxInvoiceShowFlg(String nonTaxInvoiceShowFlg) {
        this.nonTaxInvoiceShowFlg = nonTaxInvoiceShowFlg;
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

	/**
	 * @return the tbxBillingReference
	 */
	public String getTbxBillingReference() {
		return tbxBillingReference;
	}

	/**
	 * @param tbxBillingReference the tbxBillingReference to set
	 */
	public void setTbxBillingReference(String tbxBillingReference) {
		this.tbxBillingReference = tbxBillingReference;
	}
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
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
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	/**
	 * @return the tbxCustomerName
	 */
	public String getTbxCustomerName() {
		return tbxCustomerName;
	}

	/**
	 * @param tbxCustomerName the tbxCustomerName to set
	 */
	public void setTbxCustomerName(String tbxCustomerName) {
		this.tbxCustomerName = tbxCustomerName;
	}

	/**
	 * @return the tbxBillingAccountNo
	 */
	public String getTbxBillingAccountNo() {
		return tbxBillingAccountNo;
	}

	/**
	 * @param tbxBillingAccountNo the tbxBillingAccountNo to set
	 */
	public void setTbxBillingAccountNo(String tbxBillingAccountNo) {
		this.tbxBillingAccountNo = tbxBillingAccountNo;
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
	 * @return the cboTransactionType
	 */
	public String getCboTransactionType() {
		return cboTransactionType;
	}

	/**
	 * @param cboTransactionType the cboTransactionType to set
	 */
	public void setCboTransactionType(String cboTransactionType) {
		this.cboTransactionType = cboTransactionType;
	}

	/**
	 * @return the cboBillingCurrency
	 */
	public String getCboBillingCurrency() {
		return cboBillingCurrency;
	}

	/**
	 * @param cboBillingCurrency the cboBillingCurrency to set
	 */
	public void setCboBillingCurrency(String cboBillingCurrency) {
		this.cboBillingCurrency = cboBillingCurrency;
	}

	/**
	 * @return the tbxBillingDateFrom
	 */
	public String getTbxBillingDateFrom() {
		return tbxBillingDateFrom;
	}

	/**
	 * @param tbxBillingDateFrom the tbxBillingDateFrom to set
	 */
	public void setTbxBillingDateFrom(String tbxBillingDateFrom) {
		this.tbxBillingDateFrom = tbxBillingDateFrom;
	}

	/**
	 * @return the tbxBillingDateTo
	 */
	public String getTbxBillingDateTo() {
		return tbxBillingDateTo;
	}

	/**
	 * @param tbxBillingDateTo the tbxBillingDateTo to set
	 */
	public void setTbxBillingDateTo(String tbxBillingDateTo) {
		this.tbxBillingDateTo = tbxBillingDateTo;
	}

	/**
	 * @return the cboDocumentStatus
	 */
	public String getCboDocumentStatus() {
		return cboDocumentStatus;
	}

	/**
	 * @param cboDocumentStatus the cboDocumentStatus to set
	 */
	public void setCboDocumentStatus(String cboDocumentStatus) {
		this.cboDocumentStatus = cboDocumentStatus;
	}

	/**
	 * @return the totalRow
	 */
	public Integer getTotalRow() {
		return totalRow;
	}

	/**
	 * @param totalRow the totalRow to set
	 */
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	/**
	 * @return the row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * @param row the row to set
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
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the listReport
	 */
	public List<HashMap<String, Object>> getListReport() {
		return listReport;
	}

	/**
	 * @param listReport the listReport to set
	 */
	public void setListReport(List<HashMap<String, Object>> listReport) {
		this.listReport = listReport;
	}

	/**
	 * @return the deliveryEmail1
	 */
	public String getDeliveryEmail1() {
		return deliveryEmail1;
	}

	/**
	 * @param deliveryEmail1 the deliveryEmail1 to set
	 */
	public void setDeliveryEmail1(String deliveryEmail1) {
		this.deliveryEmail1 = deliveryEmail1;
	}

	/**
	 * @return the deliveryEmail2
	 */
	public String getDeliveryEmail2() {
		return deliveryEmail2;
	}

	/**
	 * @param deliveryEmail2 the deliveryEmail2 to set
	 */
	public void setDeliveryEmail2(String deliveryEmail2) {
		this.deliveryEmail2 = deliveryEmail2;
	}

	/**
	 * @return the delivery1
	 */
	public String getDelivery1() {
		return delivery1;
	}

	/**
	 * @param delivery1 the delivery1 to set
	 */
	public void setDelivery1(String delivery1) {
		this.delivery1 = delivery1;
	}

	/**
	 * @return the delivery2
	 */
	public String getDelivery2() {
		return delivery2;
	}

	/**
	 * @param delivery2 the delivery2 to set
	 */
	public void setDelivery2(String delivery2) {
		this.delivery2 = delivery2;
	}

	/**
	 * @return the delivery4
	 */
	public String getDelivery4() {
		return delivery4;
	}

	/**
	 * @param delivery4 the delivery4 to set
	 */
	public void setDelivery4(String delivery4) {
		this.delivery4 = delivery4;
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

	/**
	 * @return the issueTypeSingpostValue
	 */
	public String getIssueTypeSingpostValue() {
		return issueTypeSingpostValue;
	}

	/**
	 * @param issueTypeSingpostValue the issueTypeSingpostValue to set
	 */
	public void setIssueTypeSingpostValue(String issueTypeSingpostValue) {
		this.issueTypeSingpostValue = issueTypeSingpostValue;
	}

	/**
	 * @return the issueTypeAutoValue
	 */
	public String getIssueTypeAutoValue() {
		return issueTypeAutoValue;
	}

	/**
	 * @param issueTypeAutoValue the issueTypeAutoValue to set
	 */
	public void setIssueTypeAutoValue(String issueTypeAutoValue) {
		this.issueTypeAutoValue = issueTypeAutoValue;
	}

	/**
	 * @return the issueTypeManualValue
	 */
	public String getIssueTypeManualValue() {
		return issueTypeManualValue;
	}

	/**
	 * @param issueTypeManualValue the issueTypeManualValue to set
	 */
	public void setIssueTypeManualValue(String issueTypeManualValue) {
		this.issueTypeManualValue = issueTypeManualValue;
	}

	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	public String getCboCategory() {
		return cboCategory;
	}

	public void setCboCategory(String cboCategory) {
		this.cboCategory = cboCategory;
	}
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	
}