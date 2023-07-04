/**
 * @(#)RP_B_BAC_S03SearchInput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/10
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class RP_B_BAC_S03SearchInput implements Serializable {

    private static final long serialVersionUID = -6846146215709578312L;
    
    private BillingSystemUserValueObject uvo;
    
    private String tbxCustomerName;
    
    private String tbxCustomerId;
    
    private String tbxBillAcc;
    
    private String pageSearch;
    
    private String doSearch;
    
    private Integer startIndex;

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * @param uvo the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    /**
     * @return the tbxCustomerName
     */
    public String getTbxCustomerName() {
        return tbxCustomerName;
    }

    /**
     * @param tbxCustomerName the tbxCustomerName to set
     */
    public void setTbxCustomerName(String tbxCustomerName) {
        this.tbxCustomerName = tbxCustomerName;
    }

    /**
     * @return the tbxCustomerId
     */
    public String getTbxCustomerId() {
        return tbxCustomerId;
    }

    /**
     * @param tbxCustomerId the tbxCustomerId to set
     */
    public void setTbxCustomerId(String tbxCustomerId) {
        this.tbxCustomerId = tbxCustomerId;
    }

    /**
     * @return the tbxBillAcc
     */
    public String getTbxBillAcc() {
        return tbxBillAcc;
    }

    /**
     * @param tbxBillAcc the tbxBillAcc to set
     */
    public void setTbxBillAcc(String tbxBillAcc) {
        this.tbxBillAcc = tbxBillAcc;
    }

    /**
     * @return the pageSearch
     */
    public String getPageSearch() {
        return pageSearch;
    }

    /**
     * @param pageSearch the pageSearch to set
     */
    public void setPageSearch(String pageSearch) {
        this.pageSearch = pageSearch;
    }

    /**
     * @return the doSearch
     */
    public String getDoSearch() {
        return doSearch;
    }

    /**
     * @param doSearch the doSearch to set
     */
    public void setDoSearch(String doSearch) {
        this.doSearch = doSearch;
    }

    /**
     * @return the startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
