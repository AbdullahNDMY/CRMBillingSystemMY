/*
 * @(#)RP_B_BTH_P01_01Output.java
 *
 *
 */
package nttdm.bsys.b_bth.dto;

import java.io.Serializable;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BTH_P01_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2737879668229057328L;

	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;

	/**
     * 
     */
	private Integer totalRow;

	/**
     * 
     */
	private List<org.apache.struts.util.LabelValueBean> listBillingType;

    private String cboDeletedStatus;
    
    private String issueTypeSingpost;
    
    private String issueTypeAuto;
    
    private String issueTypeManual;
    
    private String nonTaxInvoiceShowFlg;
    
    private String[] deliveryEmail;
    
    private String[] delivery;
    
	// Add #156 Start
    private String billCnAmtNegative;
	// Add #156 End
    
    // #191 Start
    private String issueTypeSingpostValue;
    private String issueTypeAutoValue;
    private String issueTypeManualValue;
    // #191 End

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
	 * totalRow を取得する
	 * 
	 * @return totalRow
	 */
	public Integer getTotalRow() {
		return totalRow;
	}

	/**
	 * totalRow を設定する
	 * 
	 * @param totalRow
	 */
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	/**
	 * listBillingType を取得する
	 * 
	 * @return listBillingType
	 */
	public List<org.apache.struts.util.LabelValueBean> getListBillingType() {
		return listBillingType;
	}

	/**
	 * listBillingType を設定する
	 * 
	 * @param listBillingType
	 */
	public void setListBillingType(
			List<org.apache.struts.util.LabelValueBean> listBillingType) {
		this.listBillingType = listBillingType;
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
}