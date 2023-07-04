/*
 * @(#)RP_B_BAC_S02_04_02Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author gplai
 */
public class RP_B_BAC_S02_04_02Output implements Serializable {

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
}