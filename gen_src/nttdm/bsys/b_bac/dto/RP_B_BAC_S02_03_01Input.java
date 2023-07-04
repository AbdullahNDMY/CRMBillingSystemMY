/*
 * @(#)RP_B_BAC_S02_03_01Input.java
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
 * @author dunglq
 */
public class RP_B_BAC_S02_03_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4526942090427908990L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private String idBillAccount;

	/**
     * 
     */
	private String idCustPlan;

	/**
     * 
     */
	private T_BAC_H inputHeaderInfo;
	
	/**
     * 
     */
    private String editFlg;
    
    private String fromPage;
    
    private String idRef;

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
	 * idBillAccount を取得する
	 * 
	 * @return idBillAccount
	 */
	public String getIdBillAccount() {
		return idBillAccount;
	}

	/**
	 * idBillAccount を設定する
	 * 
	 * @param idBillAccount
	 */
	public void setIdBillAccount(String idBillAccount) {
		this.idBillAccount = idBillAccount;
	}

	/**
	 * idCustPlan を取得する
	 * 
	 * @return idCustPlan
	 */
	public String getIdCustPlan() {
		return idCustPlan;
	}

	/**
	 * idCustPlan を設定する
	 * 
	 * @param idCustPlan
	 */
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
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
     * @return the editFlg
     */
    public String getEditFlg() {
        return editFlg;
    }

    /**
     * @param editFlg the editFlg to set
     */
    public void setEditFlg(String editFlg) {
        this.editFlg = editFlg;
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

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}
    
    
}