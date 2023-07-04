/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COM
 * FUNCTION       : Define bean Bank
 * FILE NAME      : Bank.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_com.dto;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 */
public class Bank {
	private String idBank;
	private String bankFullName;
	private String bankCode;
	private String bankName;
	private String branchCode;
	private String branchName;
	private String bankTelephoneNo;
	private String bankFaxNo;
	public String getIdBank() {
		return idBank;
	}
	public void setIdBank(String idBank) {
		this.idBank = idBank;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBankTelephoneNo() {
		return bankTelephoneNo;
	}
	public void setBankTelephoneNo(String bankTelephoneNo) {
		this.bankTelephoneNo = bankTelephoneNo;
	}
	public String getBankFaxNo() {
		return bankFaxNo;
	}
	public void setBankFaxNo(String bankFaxNo) {
		this.bankFaxNo = bankFaxNo;
	}
	
}
