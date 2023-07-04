/*
 * @(#)RP_B_BAC_S02_01_03Output.java
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
 * @author khungl0ng
 */
public class RP_B_BAC_S02_01_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4826124418508404805L;

	/**
     * 
     */
	private HashMap<String, Object> custInfo;

	/**
     * 
     */
	private List<LabelValueBean> listContact;

	/**
     * 
     */
	private List<HashMap<String, Object>> listPlanInfo;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;

	/**
     * 
     */
	private String cboCustomerName;

	/**
     * 
     */
	private HashMap<String, Object> addressInfo;

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
    private Boolean isDisplayGiro;

    /**
     * 
     */
    private Boolean isDisplayCC;
    
    /**
     * 
     */
    private String fixedForex;
    
    private String taxTypeDisplay;

	public String getFixedForex() {
        return fixedForex;
    }

    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
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