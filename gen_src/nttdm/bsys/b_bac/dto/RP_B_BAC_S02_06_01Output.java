/*
 * @(#)RP_B_BAC_S02_06_01Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.common.bean.T_BAC_D;


/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_06_01Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8658755089983217540L;
    
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
     * fromPage
     */
    private String fromPage;
    
    /**
     * idBillAccount を取得する
     */
    public String getIdBillAccount() {
        return idBillAccount;
    }
    
    /**
     * idBillAccountを設定する
     */
    public void setIdBillAccount(String idBillAccount) {
        this.idBillAccount = idBillAccount;
    }
    
    /**
     * inputBillReferenceInfo を取得する
     */
    public T_BAC_D getInputBillReferenceInfo() {
        return inputBillReferenceInfo;
    }
    
    /**
     * inputBillReferenceInfoを設定する
     */
    public void setInputBillReferenceInfo(T_BAC_D inputBillReferenceInfo) {
        this.inputBillReferenceInfo = inputBillReferenceInfo;
    }

    /**
     * billRefACManagerList を取得する
     */
    public List<LabelValueBean> getBillRefACManagerList() {
        return billRefACManagerList;
    }

    /**
     * billRefACManagerListを設定する
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
}