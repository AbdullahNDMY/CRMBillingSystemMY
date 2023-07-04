/**
 * @(#)RP_B_BAC_S03SearchOutput.java
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
import java.util.HashMap;
import java.util.List;

/**
 * @author gplai
 *
 */
public class RP_B_BAC_S03SearchOutput implements Serializable {

    private static final long serialVersionUID = 1290452746959477038L;

    private String tbxCustomerName;
    
    private String tbxCustomerId;
    
    private String tbxBillAcc;
    
    private List<HashMap<String, Object>> customerBeans;
    
    private Integer startIndex;
    
    private Integer row;
    
    private Integer totalCount;

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
     * @return the customerBeans
     */
    public List<HashMap<String, Object>> getCustomerBeans() {
        return customerBeans;
    }

    /**
     * @param customerBeans the customerBeans to set
     */
    public void setCustomerBeans(List<HashMap<String, Object>> customerBeans) {
        this.customerBeans = customerBeans;
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

    /**
     * @return the row
     */
    public Integer getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * @return the totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
