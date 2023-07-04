/*
 * @(#)RP_B_BAC_S02_03_02Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;

import nttdm.bsys.common.bean.T_BAC_H;

/**
 * OutputDTO class.
 * 
 * @author gplai
 */
public class RP_B_BAC_S02_03_02Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6379573885085697529L;
	
	/**
     * 
     */
    private String idBillAccount;
    
    /**
     * 
     */
    private String idCustPlan;
    
    private T_BAC_H inputHeaderInfo;

    /**
     * @return the idBillAccount
     */
    public String getIdBillAccount() {
        return idBillAccount;
    }

    /**
     * @param idBillAccount the idBillAccount to set
     */
    public void setIdBillAccount(String idBillAccount) {
        this.idBillAccount = idBillAccount;
    }

    /**
     * @return the idCustPlan
     */
    public String getIdCustPlan() {
        return idCustPlan;
    }

    /**
     * @param idCustPlan the idCustPlan to set
     */
    public void setIdCustPlan(String idCustPlan) {
        this.idCustPlan = idCustPlan;
    }

    /**
     * @return the inputHeaderInfo
     */
    public T_BAC_H getInputHeaderInfo() {
        return inputHeaderInfo;
    }

    /**
     * @param inputHeaderInfo the inputHeaderInfo to set
     */
    public void setInputHeaderInfo(T_BAC_H inputHeaderInfo) {
        this.inputHeaderInfo = inputHeaderInfo;
    }
}