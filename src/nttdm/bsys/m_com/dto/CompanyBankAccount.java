/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COM
 * FUNCTION       : Define bean Company Bank Account
 * FILE NAME      : CompanyBankAccount.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 **********************************************************/
package nttdm.bsys.m_com.dto;

/**
 * BusinessLogic class.
 * 
 * @author loamanma
 */
public class CompanyBankAccount {

    private String idComBank;

    private String accountNo;

    private String currency;

    private String isDefault;

    private String active;

    public String getIdComBank() {
        return idComBank;
    }

    public void setIdComBank(String idComBank) {
        this.idComBank = idComBank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
