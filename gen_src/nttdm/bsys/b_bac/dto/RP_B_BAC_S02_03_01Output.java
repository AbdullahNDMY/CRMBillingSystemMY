/*
 * @(#)RP_B_BAC_S02_03_01Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.BillingSystemSettings;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_03_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2031114487026833880L;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;

	/**
     * 
     */
	private List<LabelValueBean> listContact;

	/**
     * 
     */
	private HashMap<String, Object> custInfo;

	/**
     * 
     */
	private HashMap<String, Object> headerInfo;

	/**
     * 
     */
	private HashMap<String, Object> addressInfo;

	/**
     * 
     */
	private Boolean isDisplayGiro;

	/**
     * 
     */
	private Boolean isDisplayCC;
	
	private String fixedForex;
	
    /**
     * 
     */
    private BillingSystemSettings bss;
    
    private String fromPage;
    
    private String idRef;
    
    private List<LabelValueBean> listGrandTotalCur;
    
    private String taxTypeDisplay;

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

	/**
	 * listContact を取得する
	 * 
	 * @return listContact
	 */
	public List<LabelValueBean> getListContact() {
		return listContact;
	}

	/**
	 * listContact を設定する
	 * 
	 * @param listContact
	 */
	public void setListContact(List<LabelValueBean> listContact) {
		this.listContact = listContact;
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
	 * isDisplayGiro を取得する
	 * 
	 * @return isDisplayGiro
	 */
	public Boolean getIsDisplayGiro() {
		return isDisplayGiro;
	}

	/**
	 * isDisplayGiro を設定する
	 * 
	 * @param isDisplayGiro
	 */
	public void setIsDisplayGiro(Boolean isDisplayGiro) {
		this.isDisplayGiro = isDisplayGiro;
	}

	/**
	 * isDisplayCC を取得する
	 * 
	 * @return isDisplayCC
	 */
	public Boolean getIsDisplayCC() {
		return isDisplayCC;
	}

	/**
	 * isDisplayCC を設定する
	 * 
	 * @param isDisplayCC
	 */
	public void setIsDisplayCC(Boolean isDisplayCC) {
		this.isDisplayCC = isDisplayCC;
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
     * @return the fromPage
     */
    public String getFromPage() {
        return fromPage;
    }

    /**
     * @param fromPage the fromPage to set
     */
    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    /**
     * @return the listGrandTotalCur
     */
    public List<LabelValueBean> getListGrandTotalCur() {
        return listGrandTotalCur;
    }

    /**
     * @param listGrandTotalCur the listGrandTotalCur to set
     */
    public void setListGrandTotalCur(List<LabelValueBean> listGrandTotalCur) {
        this.listGrandTotalCur = listGrandTotalCur;
    }

    public String getFixedForex() {
        return fixedForex;
    }

    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
    }

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
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