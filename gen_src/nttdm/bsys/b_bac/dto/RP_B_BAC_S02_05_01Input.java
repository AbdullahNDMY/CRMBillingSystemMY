/*
 * @(#)RP_B_BAC_S02_05_01Input.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_05_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4356874119906814794L;

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
	private String idCust;

	/**
     * 
     */
	private String idCustPlan;

	/**
     * 
     */
	private String idBillAccount;
	
	private String clickBtnTypeFlg;
	
	private String popupClickYesFlg;

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
	 * idCust を取得する
	 * 
	 * @return idCust
	 */
	public String getIdCust() {
		return idCust;
	}

	/**
	 * idCust を設定する
	 * 
	 * @param idCust
	 */
	public void setIdCust(String idCust) {
		this.idCust = idCust;
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
     * @return the clickBtnTypeFlg
     */
    public String getClickBtnTypeFlg() {
        return clickBtnTypeFlg;
    }

    /**
     * @param clickBtnTypeFlg the clickBtnTypeFlg to set
     */
    public void setClickBtnTypeFlg(String clickBtnTypeFlg) {
        this.clickBtnTypeFlg = clickBtnTypeFlg;
    }

    /**
     * @return the popupClickYesFlg
     */
    public String getPopupClickYesFlg() {
        return popupClickYesFlg;
    }

    /**
     * @param popupClickYesFlg the popupClickYesFlg to set
     */
    public void setPopupClickYesFlg(String popupClickYesFlg) {
        this.popupClickYesFlg = popupClickYesFlg;
    }
}