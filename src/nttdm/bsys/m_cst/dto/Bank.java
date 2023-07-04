package nttdm.bsys.m_cst.dto;

import java.io.Serializable;

public class Bank implements Serializable {
	private String bankFullName;
	private String idBank;
	private String bankCode;
	private String branchCode;
	private String bankBicCode;
	
	public String getBankFullName() {
		return bankFullName;
	}
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	public String getIdBank() {
		return idBank;
	}
	public void setIdBank(String idBank) {
		this.idBank = idBank;
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
	public String getBankBicCode() {
        return bankBicCode;
    }
    public void setBankBicCode(String bankBicCode) {
        this.bankBicCode = bankBicCode;
    }

	
}
