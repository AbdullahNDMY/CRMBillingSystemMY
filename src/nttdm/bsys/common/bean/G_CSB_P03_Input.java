/**
 * @(#)G_CSB_P03_Input.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/06/06
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.bean;

/**
 * @author gplai
 *
 */
public class G_CSB_P03_Input {

    /**
     * idBillAccount
     */
    private String idBillAccount;
    
    private String idRef;
    
    private String receiptNo;
    
    private String AmtReceived;

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
     * @return the idRef
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * @param idRef the idRef to set
     */
    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    /**
     * @return the receiptNo
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * @param receiptNo the receiptNo to set
     */
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * @return the amtReceived
     */
    public String getAmtReceived() {
        return AmtReceived;
    }

    /**
     * @param amtReceived the amtReceived to set
     */
    public void setAmtReceived(String amtReceived) {
        AmtReceived = amtReceived;
    }
    
}
