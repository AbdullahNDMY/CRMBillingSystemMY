/*
 * @(#)RP_B_BAC_S02_03_01Input.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.bean.T_BAC_D;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.util.LabelValueBean;


/**
 * InputDTO class.
 * 
 * @author dunglq
 */


public class RP_B_BAC_S02_06_02Input implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5558034075782347335L;
    
    /**
     * inputBillReferenceInfo
     */
    private T_BAC_D inputBillReferenceInfo;
    
    /**
     * BillAccount
     */
    private String idBillAccount;
    
    /**
     *AC_Manager
     */
    private List<LabelValueBean> billRefACManagerList;

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
    
    /**
     * uvo
     */
    private BillingSystemUserValueObject uvo;
    
    /**
     * inputBillReferenceInfo を取得する
     * 
     * @return inputBillReferenceInfo
     */
    public T_BAC_D getInputBillReferenceInfo() {
        return inputBillReferenceInfo;
    }

    /**
     * inputBillReferenceInfo を設定する
     * 
     * @param inputBillReferenceInfo
     */
    public void setInputBillReferenceInfo(T_BAC_D inputBillReferenceInfo) {
        this.inputBillReferenceInfo = inputBillReferenceInfo;
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
     * billRefACManagerList を取得する
     * 
     * @return billRefACManagerList
     */
    public List<LabelValueBean> getBillRefACManagerList() {
        return billRefACManagerList;
    }

    /**
     * billRefACManagerList を設定する
     * 
     * @param billRefACManagerList
     */
    public void setBillRefACManagerList(List<LabelValueBean> billRefACManagerList) {
        this.billRefACManagerList = billRefACManagerList;
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
}