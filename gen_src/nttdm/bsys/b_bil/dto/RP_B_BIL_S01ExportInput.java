/*
 * @(#)RP_B_BIL_S01ExportInput.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S01ExportInput implements Serializable {
    
	/**
     * 
     */
    private static final long serialVersionUID = 2259940837661038905L;

    /**
     * 
     */
	private String tbxBillingReference;

	/**
     * 
     */
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	private String tbxCustomerId;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	private String tbxCustomerName;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	private String cboCustomerType;
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/

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

	/**
     * 
     */
	private String cboTransactionType;

	/**
     * 
     */
	private String tbxBillingAccountNo;

	/**
     * 
     */
	private String tbxBillingDateFrom;

	/**
     * 
     */
	private String tbxBillingDateTo;

	/**
     * 
     */
	private String cboDocumentStatus;

	/**
     * 
     */
	private String fullySettled;
	
	private String partiallySettled;
	
	private String outstanding;
	
	private String cboBillingCurrency;

	private String[] deliveryEmail;
	//Search delivery by email no filter in export CT 15 Aug 2017
	private String deliveryEmail1;
    private String deliveryEmail2;
  //Search delivery by email no filter in export CT 15 Aug 2017
    
    private String[] delivery;
    private String delivery1;
    private String delivery2;
    private String delivery4;
    
	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;
	
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
    private String cboCategory;
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	
	/**
	 * tbxBillingReference を�?�得�?�る
	 * 
	 * @return tbxBillingReference
	 */
	public String getTbxBillingReference() {
		return tbxBillingReference;
	}

	/**
	 * tbxBillingReference を設定�?�る
	 * 
	 * @param tbxBillingReference
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
	 * tbxCustomerName を�?�得�?�る
	 * 
	 * @return tbxCustomerName
	 */
	public String getTbxCustomerName() {
		return tbxCustomerName;
	}

	/**
	 * tbxCustomerName を設定�?�る
	 * 
	 * @param tbxCustomerName
	 */
	public void setTbxCustomerName(String tbxCustomerName) {
		this.tbxCustomerName = tbxCustomerName;
	}

	/**
	 * cboTransactionType を�?�得�?�る
	 * 
	 * @return cboTransactionType
	 */
	public String getCboTransactionType() {
		return cboTransactionType;
	}

	/**
	 * cboTransactionType を設定�?�る
	 * 
	 * @param cboTransactionType
	 */
	public void setCboTransactionType(String cboTransactionType) {
		this.cboTransactionType = cboTransactionType;
	}

	/**
	 * tbxBillingAccountNo を�?�得�?�る
	 * 
	 * @return tbxBillingAccountNo
	 */
	public String getTbxBillingAccountNo() {
		return tbxBillingAccountNo;
	}

	/**
	 * tbxBillingAccountNo を設定�?�る
	 * 
	 * @param tbxBillingAccountNo
	 */
	public void setTbxBillingAccountNo(String tbxBillingAccountNo) {
		this.tbxBillingAccountNo = tbxBillingAccountNo;
	}

	/**
	 * tbxBillingDateFrom を�?�得�?�る
	 * 
	 * @return tbxBillingDateFrom
	 */
	public String getTbxBillingDateFrom() {
		return tbxBillingDateFrom;
	}

	/**
	 * tbxBillingDateFrom を設定�?�る
	 * 
	 * @param tbxBillingDateFrom
	 */
	public void setTbxBillingDateFrom(String tbxBillingDateFrom) {
		this.tbxBillingDateFrom = tbxBillingDateFrom;
	}

	/**
	 * tbxBillingDateTo を�?�得�?�る
	 * 
	 * @return tbxBillingDateTo
	 */
	public String getTbxBillingDateTo() {
		return tbxBillingDateTo;
	}

	/**
	 * tbxBillingDateTo を設定�?�る
	 * 
	 * @param tbxBillingDateTo
	 */
	public void setTbxBillingDateTo(String tbxBillingDateTo) {
		this.tbxBillingDateTo = tbxBillingDateTo;
	}

	/**
	 * cboDocumentStatus を�?�得�?�る
	 * 
	 * @return cboDocumentStatus
	 */
	public String getCboDocumentStatus() {
		return cboDocumentStatus;
	}

	/**
	 * cboDocumentStatus を設定�?�る
	 * 
	 * @param cboDocumentStatus
	 */
	public void setCboDocumentStatus(String cboDocumentStatus) {
		this.cboDocumentStatus = cboDocumentStatus;
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
	 * row を�?�得�?�る
	 * 
	 * @return row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * row を設定�?�る
	 * 
	 * @param row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * startIndex を�?�得�?�る
	 * 
	 * @return startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * startIndex を設定�?�る
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
    
    // #251 B-BIL-S01 Export Result Not filter by Job No. CT 08052017 ST
    private String jobNo;
    
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
    // #251 B-BIL-S01 Export Result Not filter by Job No. CT 08052017 EN
	
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	public String getCboCategory() {
		return cboCategory;
	}

	public void setCboCategory(String cboCategory) {
		this.cboCategory = cboCategory;
	}
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	//Search delivery by email no filter in export CT 15 Aug 2017

	public String getDeliveryEmail1() {
		return deliveryEmail1;
	}

	public void setDeliveryEmail1(String deliveryEmail1) {
		this.deliveryEmail1 = deliveryEmail1;
	}

	public String getDeliveryEmail2() {
		return deliveryEmail2;
	}

	public void setDeliveryEmail2(String deliveryEmail2) {
		this.deliveryEmail2 = deliveryEmail2;
	}

	public String getDelivery1() {
		return delivery1;
	}

	public void setDelivery1(String delivery1) {
		this.delivery1 = delivery1;
	}

	public String getDelivery2() {
		return delivery2;
	}

	public void setDelivery2(String delivery2) {
		this.delivery2 = delivery2;
	}

	public String getDelivery4() {
		return delivery4;
	}

	public void setDelivery4(String delivery4) {
		this.delivery4 = delivery4;
	}
	
	//Search delivery by email no filter in export CT 15 Aug 2017
}