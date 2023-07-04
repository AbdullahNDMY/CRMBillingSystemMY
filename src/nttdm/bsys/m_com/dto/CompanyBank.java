/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COM
 * FUNCTION       : Define bean Company Bank
 * FILE NAME      : CompanyBank.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 **********************************************************/
package nttdm.bsys.m_com.dto;

import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * BusinessLogic class.
 * 
 * @author loamanma
 */
public class CompanyBank {

    private String displayOrder;

    private String bankFullName;

    private String bankCode;

    private String branchCode;

    private String accountName;

    private String swiftCode;

    private String idComBank;

    private String idbank;

    private String idCom;

    private List<CompanyBankAccount> listCompanyBankAccount = new AutoScaleList<CompanyBankAccount>(new CompanyBankAccount());

    public String getIdbank() {
        return idbank;
    }

    public void setIdbank(String idbank) {
        this.idbank = idbank;
    }

    public String getIdCom() {
        return idCom;
    }

    public void setIdCom(String idCom) {
        this.idCom = idCom;
    }

    public String getIdComBank() {
        return idComBank;
    }

    public void setIdComBank(String idComBank) {
        this.idComBank = idComBank;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getBankFullName() {
        return bankFullName;
    }

    public void setBankFullName(String bankFullName) {
        this.bankFullName = bankFullName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public List<CompanyBankAccount> getListCompanyBankAccount() {
        return listCompanyBankAccount;
    }

    public void setListCompanyBankAccount(List<CompanyBankAccount> listCompanyBankAccount) {
        this.listCompanyBankAccount = listCompanyBankAccount;
    }

}
