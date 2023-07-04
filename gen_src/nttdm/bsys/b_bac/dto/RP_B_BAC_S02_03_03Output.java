/*
 * @(#)RP_B_BAC_S02_03_03Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.BillingSystemSettings;

import java.util.List;
import java.util.HashMap;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_03_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5449924123479720060L;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;

	/**
     * 
     */
	private List<org.apache.struts.util.LabelValueBean> listContact;

	/**
     * 
     */
	private HashMap<String, Object> addressInfo;

	/**
     * 
     */
	private HashMap<String, Object> custInfo;
	
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
    
    private List<org.apache.struts.util.LabelValueBean> listGrandTotalCur;

    private String fixedForex;
    
    public String getFixedForex() {
        return fixedForex;
    }

    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
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
     * @return the listGrandTotalCur
     */
    public List<org.apache.struts.util.LabelValueBean> getListGrandTotalCur() {
        return listGrandTotalCur;
    }

    /**
     * @param listGrandTotalCur the listGrandTotalCur to set
     */
    public void setListGrandTotalCur(
            List<org.apache.struts.util.LabelValueBean> listGrandTotalCur) {
        this.listGrandTotalCur = listGrandTotalCur;
    }
}