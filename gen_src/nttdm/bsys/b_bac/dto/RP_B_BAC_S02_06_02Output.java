/*
 * @(#)RP_B_BAC_S02_06_02Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.bean.T_BAC_D;

import org.apache.struts.util.LabelValueBean;


/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_06_02Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8923849361341612523L;

    /**
     * inputBillReferenceInfo
     */
    private T_BAC_D inputBillReferenceInfo;
    
    /**
     * BillAccount
     */
    private String idBillAccount;
    
    /**
     *AC_Manager DropdownList
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
}