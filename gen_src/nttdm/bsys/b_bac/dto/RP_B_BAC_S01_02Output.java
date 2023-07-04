/*
 * @(#)RP_B_BAC_S01_02Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.util.BillingSystemSettings;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S01_02Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4243647567471185363L;

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
	private List<HashMap<String, Object>> listBillingAccount;

	/**
     * 
     */
	private String[] idKeys;

	/**
     * 
     */
	private BillingSystemSettings bss;
	
    /**
     * 
     */
    private String newFlg;

    private String accessType;

    private String tbxCustomerName ;
    
    private String tbxCustomerId;
     
    private String cboPaymentMethod;
     
    private String cboBillingCurrency;
     
    private String tbxBillingAccountNo;
     
    private String cboPaymentCurrency;
     
    private String[] chkBySingPost;
     
    private String[] chkStatus;
     
    private String[] chkTotalAmountDue;
    
    private List<LabelValueBean> cboPaymentCurrencyList;
    
    private List<Map<String,Object>> searchResultList;
    
    private String cbAutoOffset;
    
    private String fixedForex;
    
    private String noRecord;
    
    private String[] chkEmail;
    
    private String[] chkDeliveries;
    
    // #191 Start
    private String singPostValue;
    // #191 End
    
    public String getNoRecord() {
        return noRecord;
    }

    public void setNoRecord(String noRecord) {
        this.noRecord = noRecord;
    }

    public String getFixedForex() {
        return fixedForex;
    }

    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
    }

    public String getCbAutoOffset() {
        return cbAutoOffset;
    }

    public void setCbAutoOffset(String cbAutoOffset) {
        this.cbAutoOffset = cbAutoOffset;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
    
	public List<Map<String, Object>> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<Map<String, Object>> searchResultList) {
        this.searchResultList = searchResultList;
    }

    public List<LabelValueBean> getCboPaymentCurrencyList() {
        return cboPaymentCurrencyList;
    }

    public void setCboPaymentCurrencyList(List<LabelValueBean> cboPaymentCurrencyList) {
        this.cboPaymentCurrencyList = cboPaymentCurrencyList;
    }

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

    public void setCboBillingCurrency(String cboBillingCurrency) {
        this.cboBillingCurrency = cboBillingCurrency;
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
	 * listBillingAccount を取得する
	 * 
	 * @return listBillingAccount
	 */
	public List<HashMap<String, Object>> getListBillingAccount() {
		return listBillingAccount;
	}

	/**
	 * listBillingAccount を設定する
	 * 
	 * @param listBillingAccount
	 */
	public void setListBillingAccount(
			List<HashMap<String, Object>> listBillingAccount) {
		this.listBillingAccount = listBillingAccount;
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
	 * bss を取得する
	 * 
	 * @return bss
	 */
	public BillingSystemSettings getBss() {
		return bss;
	}

	/**
	 * bss を設定する
	 * 
	 * @param bss
	 */
	public void setBss(BillingSystemSettings bss) {
		this.bss = bss;
	}

    /**
     * @return the newFlg
     */
    public String getNewFlg() {
        return newFlg;
    }

    /**
     * @param newFlg the newFlg to set
     */
    public void setNewFlg(String newFlg) {
        this.newFlg = newFlg;
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

	/**
	 * @return the singPostValue
	 */
	public String getSingPostValue() {
		return singPostValue;
	}

	/**
	 * @param singPostValue the singPostValue to set
	 */
	public void setSingPostValue(String singPostValue) {
		this.singPostValue = singPostValue;
	}
    
}