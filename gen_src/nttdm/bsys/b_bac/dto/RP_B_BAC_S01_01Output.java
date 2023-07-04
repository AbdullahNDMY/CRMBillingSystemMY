/*
 * @(#)RP_B_BAC_S01_01Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S01_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3865866758715982804L;

	/**
     * 
     */
	private String accessType;

	/**
     * 
     */
	private List<org.apache.struts.util.LabelValueBean> listCust;

	/**
     * 
     */
	private String[] idKeys;

    /**
	 * 
	 */
    private List<LabelValueBean> cboPaymentCurrencyList;

    private String[] chkBySingPost;

    private String[] chkStatus;

    private String[] chkTotalAmountDue;

    private String cbAutoOffset;

    private String[] chkEmail;
    
    private String[] chkDeliveries;
    
    // #191 Start
    private String singPostValue;
    // #191 End
    
    public String getCbAutoOffset() {
        return cbAutoOffset;
    }

    public void setCbAutoOffset(String cbAutoOffset) {
        this.cbAutoOffset = cbAutoOffset;
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

    public List<LabelValueBean> getCboPaymentCurrencyList() {
        return cboPaymentCurrencyList;
    }

    public void setCboPaymentCurrencyList(List<LabelValueBean> cboPaymentCurrencyList) {
        this.cboPaymentCurrencyList = cboPaymentCurrencyList;
    }

    /**
     * accessType を取得する
     * 
     * @return accessType
     */
    public String getAccessType() {
        return accessType;
    }

	/**
	 * accessType を設定する
	 * 
	 * @param accessType
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	/**
	 * listCust を取得する
	 * 
	 * @return listCust
	 */
	public List<org.apache.struts.util.LabelValueBean> getListCust() {
		return listCust;
	}

	/**
	 * listCust を設定する
	 * 
	 * @param listCust
	 */
	public void setListCust(List<org.apache.struts.util.LabelValueBean> listCust) {
		this.listCust = listCust;
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
	public void setChkEMail(String[] chkEmail) {
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