/*
 * @(#)RP_B_BAC_S02_03_03Input.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_03_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5145366411995110850L;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;
	
	/**
     * 
     */
    private Boolean isDisplayGiro;

    /**
     * 
     */
    private Boolean isDisplayCC;

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