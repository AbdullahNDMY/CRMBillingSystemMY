/*
 * @(#)RP_B_BAC_S02_03_01Input.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * InputDTO class.
 * 
 * @author dunglq
 */


/**
 * BusinessLogic class of B-BAC Edit init.
 * 
 */
public class RP_B_BAC_S02_06_01Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2467681813403131874L;

    /**
     * BillAccount
     */
    private String idBillAccount;

    /**
     * CustPlan
     */
    private String idCustPlan;
    
    /**
     * editFlg
     */
    private String editFlg;
    
    /**
     * fromPage
     */
    private String fromPage;
    
    private List<HashMap<String, Object>> billRefInfo;
    
    private String cboPaymentMethod;
    
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
     * editFlg を取得する
     */
    public String getEditFlg() {
        return editFlg;
    }

    /**
     * editFlgを設定する
     */
    public void setEditFlg(String editFlg) {
        this.editFlg = editFlg;
    }

    /**
     * fromPage を取得する
     */
    public String getFromPage() {
        return fromPage;
    }

    /**
     * fromPageを設定する
     */
    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    /**
     * billRefInfo を取得する
     */
    public List<HashMap<String, Object>> getBillRefInfo() {
        return billRefInfo;
    }

    /**
     * billRefInfoを設定する
     */
    public void setBillRefInfo(List<HashMap<String, Object>> billRefInfo) {
        this.billRefInfo = billRefInfo;
    }

	/**
	 * @return the cboPaymentMethod
	 */
	public String getCboPaymentMethod() {
		return cboPaymentMethod;
	}

	/**
	 * @param cboPaymentMethod the cboPaymentMethod to set
	 */
	public void setCboPaymentMethod(String cboPaymentMethod) {
		this.cboPaymentMethod = cboPaymentMethod;
	}
}