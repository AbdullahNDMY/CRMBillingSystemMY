/*
 * @(#)RP_B_BAC_S02_04_01Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;

import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.BillingSystemSettings;

/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_04_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4080939243375464510L;

	/**
     * 
     */
	private List<HashMap<String, Object>> listPlanInfo;

	/**
     * 
     */
	private List<HashMap<String, Object>> billRefInfo;

	/**
     * 
     */
	private HashMap<String, Object> addressInfo;

	/**
     * 
     */
	private HashMap<String, Object> headerInfo;

	/**
     * 
     */
	private HashMap<String, Object> custInfo;

	/**
     * 
     */
	private List<org.apache.struts.util.LabelValueBean> listContact;

	/**
     * 
     */
	private HashMap<String, Object> contactInfo;

	/**
     * 
     */
	private List<org.apache.struts.util.LabelValueBean> listBillAccount;

	/**
     * 
     */
	private String billRefDispCond;
	
    /**
     * 
     */
    private BillingSystemSettings bss;
    
    private String fixedForex;
    
    /**
     * 
     */
    private T_BAC_H inputHeaderInfo;
    
    private String taxTypeDisplay;

	/**
	 * listPlanInfo を取得する
	 * 
	 * @return listPlanInfo
	 */
	public List<HashMap<String, Object>> getListPlanInfo() {
		return listPlanInfo;
	}

	/**
	 * listPlanInfo を設定する
	 * 
	 * @param listPlanInfo
	 */
	public void setListPlanInfo(List<HashMap<String, Object>> listPlanInfo) {
		this.listPlanInfo = listPlanInfo;
	}

	/**
	 * billRefInfo を取得する
	 * 
	 * @return billRefInfo
	 */
	public List<HashMap<String, Object>> getBillRefInfo() {
		return billRefInfo;
	}

	/**
	 * billRefInfo を設定する
	 * 
	 * @param billRefInfo
	 */
	public void setBillRefInfo(List<HashMap<String, Object>> billRefInfo) {
		this.billRefInfo = billRefInfo;
	}

	/**
	 * addressInfo を取得する
	 * 
	 * @return addressInfo
	 */
	public HashMap<String, Object> getAddressInfo() {
		return addressInfo;
	}

	/**
	 * addressInfo を設定する
	 * 
	 * @param addressInfo
	 */
	public void setAddressInfo(HashMap<String, Object> addressInfo) {
		this.addressInfo = addressInfo;
	}

	/**
	 * headerInfo を取得する
	 * 
	 * @return headerInfo
	 */
	public HashMap<String, Object> getHeaderInfo() {
		return headerInfo;
	}

	/**
	 * headerInfo を設定する
	 * 
	 * @param headerInfo
	 */
	public void setHeaderInfo(HashMap<String, Object> headerInfo) {
		this.headerInfo = headerInfo;
	}

	/**
	 * custInfo を取得する
	 * 
	 * @return custInfo
	 */
	public HashMap<String, Object> getCustInfo() {
		return custInfo;
	}

	/**
	 * custInfo を設定する
	 * 
	 * @param custInfo
	 */
	public void setCustInfo(HashMap<String, Object> custInfo) {
		this.custInfo = custInfo;
	}

	/**
	 * listContact を取得する
	 * 
	 * @return listContact
	 */
	public List<org.apache.struts.util.LabelValueBean> getListContact() {
		return listContact;
	}

	/**
	 * listContact を設定する
	 * 
	 * @param listContact
	 */
	public void setListContact(
			List<org.apache.struts.util.LabelValueBean> listContact) {
		this.listContact = listContact;
	}

	/**
	 * contactInfo を取得する
	 * 
	 * @return contactInfo
	 */
	public HashMap<String, Object> getContactInfo() {
		return contactInfo;
	}

	/**
	 * contactInfo を設定する
	 * 
	 * @param contactInfo
	 */
	public void setContactInfo(HashMap<String, Object> contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * listBillAccount を取得する
	 * 
	 * @return listBillAccount
	 */
	public List<org.apache.struts.util.LabelValueBean> getListBillAccount() {
		return listBillAccount;
	}

	/**
	 * listBillAccount を設定する
	 * 
	 * @param listBillAccount
	 */
	public void setListBillAccount(
			List<org.apache.struts.util.LabelValueBean> listBillAccount) {
		this.listBillAccount = listBillAccount;
	}

	/**
	 * billRefDispCond を取得する
	 * 
	 * @return billRefDispCond
	 */
	public String getBillRefDispCond() {
		return billRefDispCond;
	}

	/**
	 * billRefDispCond を設定する
	 * 
	 * @param billRefDispCond
	 */
	public void setBillRefDispCond(String billRefDispCond) {
		this.billRefDispCond = billRefDispCond;
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
     * inputHeaderInfo を取得する
     * 
     * @return inputHeaderInfo
     */
    public T_BAC_H getInputHeaderInfo() {
        return inputHeaderInfo;
    }

    /**
     * inputHeaderInfo を設定する
     * 
     * @param inputHeaderInfo
     */
    public void setInputHeaderInfo(T_BAC_H inputHeaderInfo) {
        this.inputHeaderInfo = inputHeaderInfo;
    }

    public String getFixedForex() {
        return fixedForex;
    }

    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
    }

	/**
	 * @return the taxTypeDisplay
	 */
	public String getTaxTypeDisplay() {
		return taxTypeDisplay;
	}

	/**
	 * @param taxTypeDisplay the taxTypeDisplay to set
	 */
	public void setTaxTypeDisplay(String taxTypeDisplay) {
		this.taxTypeDisplay = taxTypeDisplay;
	}

}