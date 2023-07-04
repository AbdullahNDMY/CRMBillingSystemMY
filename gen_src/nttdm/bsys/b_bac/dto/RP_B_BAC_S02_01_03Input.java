/*
 * @(#)RP_B_BAC_S02_01_03Input.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.bean.T_BAC_H;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_01_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4326601926755685252L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private String[] idKeys;

	/**
     * 
     */
	private String cboCustomerName;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;
	
	/**
     * 
     */
    private Boolean isDisplayGiro;

    /**
     * 
     */
    private Boolean isDisplayCC;

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
}