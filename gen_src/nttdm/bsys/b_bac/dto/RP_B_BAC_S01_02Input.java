/*
 * @(#)RP_B_BAC_S01_02Input.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S01_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8386330502262397277L;

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
     * 
     */
	private String cboCustomerName;

	/**
     * 
     */
	private String tbxBillingAccount;

	/**
     * 
     */
	private String tbxPlanName;
	
	/**
     * 
     */
    private String tbxSubscription;

	/**
     * 
     */
	private String[] idKeys;

    private String tbxCustomerName ;
    
    private String tbxCustomerId;
     
    private String cboPaymentMethod;
     
    private String cboBillingCurrency;
    /* #263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
    private String cboCustomerType;
    /* #263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
    private String tbxBillingAccountNo;
     
    private String cboPaymentCurrency;
     
    private String[] chkBySingPost;
     
    private String[] chkStatus;
     
    private String[] chkTotalAmountDue;
    
    private String[] chkEmail;
    
    private String[] chkDeliveries;
    
	public String getTbxCustomerName() {
        return tbxCustomerName;
    }

    public void setTbxCustomerName(String tbxCustomerName) {
        this.tbxCustomerName = tbxCustomerName;
    }

    public String getTbxCustomerId() {
        return tbxCustomerId;
    }

    public void setTbxCustomerId(String tbxCustomerId) {
        this.tbxCustomerId = tbxCustomerId;
    }

    public String getCboPaymentMethod() {
        return cboPaymentMethod;
    }

    public void setCboPaymentMethod(String cboPaymentMethod) {
        this.cboPaymentMethod = cboPaymentMethod;
    }

    public String getCboBillingCurrency() {
        return cboBillingCurrency;
    }
    /* #263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
    public void setCboBillingCurrency(String cboBillingCurrency) {
        this.cboBillingCurrency = cboBillingCurrency;
    }

    public String getCboCustomerType() {
		return cboCustomerType;
	}
    /* #263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	public void setCboCustomerType(String cboCustomerType) {
		this.cboCustomerType = cboCustomerType;
	}

	public String getTbxBillingAccountNo() {
        return tbxBillingAccountNo;
    }

    public void setTbxBillingAccountNo(String tbxBillingAccountNo) {
        this.tbxBillingAccountNo = tbxBillingAccountNo;
    }

    public String getCboPaymentCurrency() {
        return cboPaymentCurrency;
    }

    public void setCboPaymentCurrency(String cboPaymentCurrency) {
        this.cboPaymentCurrency = cboPaymentCurrency;
    }

    public String[] getChkBySingPost() {
        return chkBySingPost;
    }

    public void setChkBySingPost(String[] chkBySingPost) {
        this.chkBySingPost = chkBySingPost;
    }

    public String[] getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String[] chkStatus) {
        this.chkStatus = chkStatus;
    }

    public String[] getChkTotalAmountDue() {
        return chkTotalAmountDue;
    }

    public void setChkTotalAmountDue(String[] chkTotalAmountDue) {
        this.chkTotalAmountDue = chkTotalAmountDue;
    }

    /**
	 * uvo を取得する
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo を設定する
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
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
	 * cboCustomerName を取得する
	 * 
	 * @return cboCustomerName
	 */
	public String getCboCustomerName() {
		return cboCustomerName;
	}

	/**
	 * cboCustomerName を設定する
	 * 
	 * @param cboCustomerName
	 */
	public void setCboCustomerName(String cboCustomerName) {
		this.cboCustomerName = cboCustomerName;
	}

	/**
	 * tbxBillingAccount を取得する
	 * 
	 * @return tbxBillingAccount
	 */
	public String getTbxBillingAccount() {
		return tbxBillingAccount;
	}

	/**
	 * tbxBillingAccount を設定する
	 * 
	 * @param tbxBillingAccount
	 */
	public void setTbxBillingAccount(String tbxBillingAccount) {
		this.tbxBillingAccount = tbxBillingAccount;
	}

	/**
	 * tbxPlanName を取得する
	 * 
	 * @return tbxPlanName
	 */
	public String getTbxPlanName() {
		return tbxPlanName;
	}

	/**
	 * tbxPlanName を設定する
	 * 
	 * @param tbxPlanName
	 */
	public void setTbxPlanName(String tbxPlanName) {
		this.tbxPlanName = tbxPlanName;
	}
	
	/**
     * tbxSubscription getting
     * 
     * @return tbxSubscription
     */
    public String getTbxSubscription() {
        return tbxSubscription;
    }

    /**
     * tbxSubscription setting
     * 
     * @param tbxSubscription
     */
    public void setTbxSubscription(String tbxSubscription) {
        this.tbxSubscription = tbxSubscription;
    }

	/**
	 * idKeys を取得する
	 * 
	 * @return idKeys
	 */
	public String[] getIdKeys() {
		return idKeys;
	}

	/**
	 * idKeys を設定する
	 * 
	 * @param idKeys
	 */
	public void setIdKeys(String[] idKeys) {
		this.idKeys = idKeys;
	}
	
	/**
     * chkMail を取得する
     * 
     * @return chkMail
     */
	public String[] getChkEmail() {
        return chkEmail;
    }

    /**
     * chkMail を設定する
     * 
     * @param chkMail
     */
	public void setChkEmail(String[] chkEmail) {
        this.chkEmail = chkEmail;
    }

    /**
     * deliveries を取得する
     * 
     * @return deliveries
     */
    public String[] getChkDeliveries() {
        return chkDeliveries;
    }

    /**
     * deliveries を設定する
     * 
     * @param deliveries
     */
    public void setChkDeliveries(String[] chkDeliveries) {
        this.chkDeliveries = chkDeliveries;
    }

}