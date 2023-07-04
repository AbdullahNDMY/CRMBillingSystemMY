/*
 * @(#)RP_B_BAC_S02_04_02Input.java
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
public class RP_B_BAC_S02_04_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5249175385334233725L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;

	/**
     * 
     */
	private String[] idKeys;

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

}