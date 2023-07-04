/*
 * @(#)RP_B_BAC_S02_01_01Input.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.bean.T_BAC_H;
import java.util.HashMap;

/**
 * InputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_01_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -13530521444872238L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private String cboCustomerName;

	/**
     * 
     */
	private String[] idKeys;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;

	/**
     * 
     */
	private HashMap<String, Object> addressInfo;
	
    /**
     * 
     */
    private String newFlg;

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
}