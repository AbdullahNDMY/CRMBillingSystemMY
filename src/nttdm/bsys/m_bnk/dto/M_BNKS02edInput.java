/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_BNKS02
 * SERVICE NAME   : M_BNKS02
 * FUNCTION       : Data input to edit
 * FILE NAME      : M_BNKS02edInput.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/09/20 [Hoang Duong] Update
 * 
**********************************************************/
package nttdm.bsys.m_bnk.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import nttdm.bsys.m_bnk.bean.M_BNK_AdressRA;
import nttdm.bsys.m_bnk.bean.M_BNKContactInfo;

/**
 * M_BNKS02edInput.class<br>
 * <ul>
 * <li>Data input to edit</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_BNKS02edInput implements Serializable {
	
	private static final long serialVersionUID = -7466401812347442359L;
	
	private BillingSystemUserValueObject uvo;
	
	private String lblBankFullName;
	private String tbxBankCode;
	private String tbxBankName;	
	private String lblidbank;	
	private String tbxBankID;
	private String tbxBranchCode;
	private String tbxBranchName;
	private String tbxBankBICCode;
    private String tbxTelNo;
	private String tbxFaxNo;
	private String tbxAddressLine1RA;
	private String tbxAddressLine2RA;
	private String tbxAddressLine3RA;
	private String tbxAddressLine1CA;
	private String tbxAddressLine2CA;
	private String tbxAddressLine3CA;
	private String tbxZipCodeRA;
	private String tbxZipCodeCA;
	private String cboAddressCountryRA;
	private String cboAddressCountryCA;	
	
	private String tbxContactNamePC;
	private String tbxDesignationPC;
	private String tbxEmailPC;
	private String tbxTelephoneNoPC;
	private String tbxDIDNoPC;
	private String tbxFaxNoPC;
	private String tbxMobileNoPc;
	
	private String tbxContactNameBC;
	private String tbxDesignationBC;
	private String tbxEmailBC;
	private String tbxTelephoneNoBC;
	private String tbxDIDNoBC;
	private String tbxFaxNoBC;
	private String tbxMobileNoBC;
	
	private String tbxContactNameOC;
	private String tbxDesignationOC;
	private String tbxEmailOC;
	private String tbxTelephoneNoOC;
	private String tbxDIDNoOC;
	private String tbxFaxNoOC;
	private String tbxMobileNoOC;
	private String lblidlogin;
	private String id_maxbank;
	
	private M_BNK_bankbean bankbeaninfo;
	private M_BNK_AdressRA addressRA;
	private M_BNK_AdressRA addressCA;
	private M_BNKContactInfo contactPC;
	private M_BNKContactInfo contactBC;
	private M_BNKContactInfo contactOC;

	private Integer idAudit = null;

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	
	public M_BNK_bankbean getBankbeaninfo() {
		return bankbeaninfo;
	}
	public void setBankbeaninfo(M_BNK_bankbean bankbeaninfo) {
		this.bankbeaninfo = bankbeaninfo;
	}
	public M_BNK_AdressRA getAddressRA() {
		return addressRA;
	}
	public void setAddressRA(M_BNK_AdressRA addressRA) {
		this.addressRA = addressRA;
	}
	public M_BNK_AdressRA getAddressCA() {
		return addressCA;
	}
	public void setAddressCA(M_BNK_AdressRA addressCA) {
		this.addressCA = addressCA;
	}
	public M_BNKContactInfo getContactPC() {
		return contactPC;
	}
	public void setContactPC(M_BNKContactInfo contactPC) {
		this.contactPC = contactPC;
	}
	public M_BNKContactInfo getContactBC() {
		return contactBC;
	}
	public void setContactBC(M_BNKContactInfo contactBC) {
		this.contactBC = contactBC;
	}
	public M_BNKContactInfo getContactOC() {
		return contactOC;
	}
	public void setContactOC(M_BNKContactInfo contactOC) {
		this.contactOC = contactOC;
	}
	
	
	public String getId_maxbank() {
		return id_maxbank;
	}
	public void setId_maxbank(String id_maxbank) {
		this.id_maxbank = id_maxbank;
	}
	public String getLblidlogin() {
		return lblidlogin;
	}
	public void setLblidlogin(String lblidlogin) {
		this.lblidlogin = lblidlogin;
	}
	public String getLblidbank() {
		return lblidbank;
	}
	public void setLblidbank(String lblidbank) {
		this.lblidbank = lblidbank;
	}
	
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getLblBankFullName() {
		return lblBankFullName;
	}
	public void setLblBankFullName(String lblBankFullName) {
		this.lblBankFullName = lblBankFullName;
	}
	public String getTbxBankID() {
		return tbxBankID;
	}
	public void setTbxBankID(String tbxBankID) {
		this.tbxBankID = tbxBankID;
	}
	public String getTbxBankCode() {
		return tbxBankCode;
	}
	public void setTbxBankCode(String tbxBankCode) {
		this.tbxBankCode = tbxBankCode;
	}
	public String getTbxBankName() {
		return tbxBankName;
	}
	public void setTbxBankName(String tbxBankName) {
		this.tbxBankName = tbxBankName;
	}
	public String getTbxBranchCode() {
		return tbxBranchCode;
	}
	public void setTbxBranchCode(String tbxBranchCode) {
		this.tbxBranchCode = tbxBranchCode;
	}
	public String getTbxBranchName() {
		return tbxBranchName;
	}
	public void setTbxBranchName(String tbxBranchName) {
		this.tbxBranchName = tbxBranchName;
	}
	public String getTbxBankBICCode() {
        return tbxBankBICCode;
    }

    public void setTbxBankBICCode(String tbxBankBICCode) {
        this.tbxBankBICCode = tbxBankBICCode;
    }
	public String getTbxTelNo() {
		return tbxTelNo;
	}
	public void setTbxTelNo(String tbxTelNo) {
		this.tbxTelNo = tbxTelNo;
	}
	public String getTbxFaxNo() {
		return tbxFaxNo;
	}
	public void setTbxFaxNo(String tbxFaxNo) {
		this.tbxFaxNo = tbxFaxNo;
	}
	public String getTbxAddressLine1RA() {
		return tbxAddressLine1RA;
	}
	public void setTbxAddressLine1RA(String tbxAddressLine1RA) {
		this.tbxAddressLine1RA = tbxAddressLine1RA;
	}
	public String getTbxAddressLine2RA() {
		return tbxAddressLine2RA;
	}
	public void setTbxAddressLine2RA(String tbxAddressLine2RA) {
		this.tbxAddressLine2RA = tbxAddressLine2RA;
	}
	public String getTbxAddressLine3RA() {
		return tbxAddressLine3RA;
	}
	public void setTbxAddressLine3RA(String tbxAddressLine3RA) {
		this.tbxAddressLine3RA = tbxAddressLine3RA;
	}
	public String getTbxAddressLine1CA() {
		return tbxAddressLine1CA;
	}
	public void setTbxAddressLine1CA(String tbxAddressLine1CA) {
		this.tbxAddressLine1CA = tbxAddressLine1CA;
	}
	public String getTbxAddressLine2CA() {
		return tbxAddressLine2CA;
	}
	public void setTbxAddressLine2CA(String tbxAddressLine2CA) {
		this.tbxAddressLine2CA = tbxAddressLine2CA;
	}
	public String getTbxAddressLine3CA() {
		return tbxAddressLine3CA;
	}
	public void setTbxAddressLine3CA(String tbxAddressLine3CA) {
		this.tbxAddressLine3CA = tbxAddressLine3CA;
	}
	public String getTbxZipCodeRA() {
		return tbxZipCodeRA;
	}
	public void setTbxZipCodeRA(String tbxZipCodeRA) {
		if(tbxZipCodeRA.equalsIgnoreCase("ZipCode"))
			this.tbxZipCodeRA = "";
		else this.tbxZipCodeRA = tbxZipCodeRA;
	}
	public String getTbxZipCodeCA() {
		return tbxZipCodeCA;
	}
	public void setTbxZipCodeCA(String tbxZipCodeCA) {
		if(tbxZipCodeCA.equalsIgnoreCase("ZipCode"))
			this.tbxZipCodeCA = "";
		else this.tbxZipCodeCA = tbxZipCodeCA;
	}
	public String getCboAddressCountryRA() {
		return cboAddressCountryRA;
	}
	public void setCboAddressCountryRA(String cboAddressCountryRA) {
		this.cboAddressCountryRA = cboAddressCountryRA;
	}
	public String getCboAddressCountryCA() {
		return cboAddressCountryCA;
	}
	public void setCboAddressCountryCA(String cboAddressCountryCA) {
		this.cboAddressCountryCA = cboAddressCountryCA;
	}
	public String getTbxContactNamePC() {
		return tbxContactNamePC;
	}
	public void setTbxContactNamePC(String tbxContactNamePC) {
		this.tbxContactNamePC = tbxContactNamePC;
	}
	public String getTbxDesignationPC() {
		return tbxDesignationPC;
	}
	public void setTbxDesignationPC(String tbxDesignationPC) {
		this.tbxDesignationPC = tbxDesignationPC;
	}
	public String getTbxEmailPC() {
		return tbxEmailPC;
	}
	public void setTbxEmailPC(String tbxEmailPC) {
		this.tbxEmailPC = tbxEmailPC;
	}
	public String getTbxTelephoneNoPC() {
		return tbxTelephoneNoPC;
	}
	public void setTbxTelephoneNoPC(String tbxTelephoneNoPC) {
		this.tbxTelephoneNoPC = tbxTelephoneNoPC;
	}
	public String getTbxDIDNoPC() {
		return tbxDIDNoPC;
	}
	public void setTbxDIDNoPC(String tbxDIDNoPC) {
		this.tbxDIDNoPC = tbxDIDNoPC;
	}
	public String getTbxFaxNoPC() {
		return tbxFaxNoPC;
	}
	public void setTbxFaxNoPC(String tbxFaxNoPC) {
		this.tbxFaxNoPC = tbxFaxNoPC;
	}
	public String getTbxMobileNoPc() {
		return tbxMobileNoPc;
	}
	public void setTbxMobileNoPc(String tbxMobileNoPc) {
		this.tbxMobileNoPc = tbxMobileNoPc;
	}
	public String getTbxContactNameBC() {
		return tbxContactNameBC;
	}
	public void setTbxContactNameBC(String tbxContactNameBC) {
		this.tbxContactNameBC = tbxContactNameBC;
	}
	public String getTbxDesignationBC() {
		return tbxDesignationBC;
	}
	public void setTbxDesignationBC(String tbxDesignationBC) {
		this.tbxDesignationBC = tbxDesignationBC;
	}
	public String getTbxEmailBC() {
		return tbxEmailBC;
	}
	public void setTbxEmailBC(String tbxEmailBC) {
		this.tbxEmailBC = tbxEmailBC;
	}
	public String getTbxTelephoneNoBC() {
		return tbxTelephoneNoBC;
	}
	public void setTbxTelephoneNoBC(String tbxTelephoneNoBC) {
		this.tbxTelephoneNoBC = tbxTelephoneNoBC;
	}
	public String getTbxDIDNoBC() {
		return tbxDIDNoBC;
	}
	public void setTbxDIDNoBC(String tbxDIDNoBC) {
		this.tbxDIDNoBC = tbxDIDNoBC;
	}
	public String getTbxFaxNoBC() {
		return tbxFaxNoBC;
	}
	public void setTbxFaxNoBC(String tbxFaxNoBC) {
		this.tbxFaxNoBC = tbxFaxNoBC;
	}
	public String getTbxMobileNoBC() {
		return tbxMobileNoBC;
	}
	public void setTbxMobileNoBC(String tbxMobileNoBC) {
		this.tbxMobileNoBC = tbxMobileNoBC;
	}
	public String getTbxContactNameOC() {
		return tbxContactNameOC;
	}
	public void setTbxContactNameOC(String tbxContactNameOC) {
		this.tbxContactNameOC = tbxContactNameOC;
	}
	public String getTbxDesignationOC() {
		return tbxDesignationOC;
	}
	public void setTbxDesignationOC(String tbxDesignationOC) {
		this.tbxDesignationOC = tbxDesignationOC;
	}
	public String getTbxEmailOC() {
		return tbxEmailOC;
	}
	public void setTbxEmailOC(String tbxEmailOC) {
		this.tbxEmailOC = tbxEmailOC;
	}
	public String getTbxTelephoneNoOC() {
		return tbxTelephoneNoOC;
	}
	public void setTbxTelephoneNoOC(String tbxTelephoneNoOC) {
		this.tbxTelephoneNoOC = tbxTelephoneNoOC;
	}
	public String getTbxDIDNoOC() {
		return tbxDIDNoOC;
	}
	public void setTbxDIDNoOC(String tbxDIDNoOC) {
		this.tbxDIDNoOC = tbxDIDNoOC;
	}
	public String getTbxFaxNoOC() {
		return tbxFaxNoOC;
	}
	public void setTbxFaxNoOC(String tbxFaxNoOC) {
		this.tbxFaxNoOC = tbxFaxNoOC;
	}
	public String getTbxMobileNoOC() {
		return tbxMobileNoOC;
	}
	public void setTbxMobileNoOC(String tbxMobileNoOC) {
		this.tbxMobileNoOC = tbxMobileNoOC;
	}

}
