
 /**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Get data save BLogic
 * FILE NAME      : M_COMS01getSaveDataBLogicOutput.java
 * 
* Copyright © 2011 NTTDATA Corporation
* 
* 
**********************************************************/

package nttdm.bsys.m_com.dto;

import java.io.Serializable;

/**
 * OutputDTO class<br>
 * <br>
 * <ul>
 * <li>
 * <li>
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1
 **/

public class M_COMS01getSaveDataBLogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1863399056222788042L;

	/**
     * 
     */
	private String tbxCompanyName;

	/**
     * 
     */
	private String tbxCompanyRegNo;
	
	/**
     * 
     */
    private String tbxCompanyGstRegNo;

	/**
     * 
     */
	private String tbxCompanyWebsite;

	/**
     * 
     */
	private String tbxTelephoneNo;

	/**
     * 
     */
	private String tbxFaxNo;

	/**
     * 
     */
	private String tbxAffiliateCode;

	/**
     * 
     */
	private String tbxAddressLine1RA;

	/**
     * 
     */
	private String tbxAddressLine2RA;

	/**
     * 
     */
	private String tbxAddressLine3RA;

	/**
     * 
     */
	private String tbxZipCodeRA;

	/**
     * 
     */
	private String cboAddressCountryRA;

	/**
     * 
     */
	private String tbxAddressLine1CA;

	/**
     * 
     */
	private String tbxAddressLine2CA;

	/**
     * 
     */
	private String tbxAddressLine3CA;

	/**
     * 
     */
	private String tbxZipCodeCA;

	/**
     * 
     */
	private String cboAddressCountryCA;

	/**
     * 
     */
	private String tbxEmailSC;

	/**
     * 
     */
	private String tbxEmailTC;

	/**
     * 
     */
	private String tbxEmailOC;

	/**
     * 
     */
	private String tbxTelephoneNoSC;

	/**
     * 
     */
	private String tbxTelephoneNoTC;

	/**
     * 
     */
	private String tbxTelephoneNoOC;

	/**
     * 
     */
	private String tbxFaxNoSC;

	/**
     * 
     */
	private String tbxFaxNoTC;

	/**
     * 
     */
	private String tbxFaxNoOC;

	/**
     * 
     */
	private String tbxProxyServerName;

	/**
     * 
     */
	private String tbxPortNumber;

	/**
     * 
     */
	private String tbxPrimaryDomainNameServer;

	/**
     * 
     */
	private String tbxSecondaryDomainNameServer;

	/**
     * 
     */
	private String id_com;

	/**
     * 
     */
	private byte[] photo;

	/**
     * 
     */
	private String tbxDefaultDialupTelNo;

	/**
     * 
     */
	private String tbxDefaultPassword;
	
	private String tbxDomainName;
    
    private String tbxWebmailURL;
    
    private String tbxSMTPServerName;
    
    private String tbxPopServerName;

	/**
	 * tbxCompanyName を取得する
	 * 
	 * @return tbxCompanyName
	 */
	public String getTbxCompanyName() {
		return tbxCompanyName;
	}

	/**
	 * tbxCompanyName を設定する
	 * 
	 * @param tbxCompanyName
	 */
	public void setTbxCompanyName(String tbxCompanyName) {
		this.tbxCompanyName = tbxCompanyName;
	}

	/**
	 * tbxCompanyRegNo を取得する
	 * 
	 * @return tbxCompanyRegNo
	 */
	public String getTbxCompanyRegNo() {
		return tbxCompanyRegNo;
	}

	/**
	 * tbxCompanyRegNo を設定する
	 * 
	 * @param tbxCompanyRegNo
	 */
	public void setTbxCompanyRegNo(String tbxCompanyRegNo) {
		this.tbxCompanyRegNo = tbxCompanyRegNo;
	}

	/**
	 * tbxCompanyWebsite を取得する
	 * 
	 * @return tbxCompanyWebsite
	 */
	public String getTbxCompanyWebsite() {
		return tbxCompanyWebsite;
	}

	/**
	 * tbxCompanyWebsite を設定する
	 * 
	 * @param tbxCompanyWebsite
	 */
	public void setTbxCompanyWebsite(String tbxCompanyWebsite) {
		this.tbxCompanyWebsite = tbxCompanyWebsite;
	}

	/**
	 * tbxTelephoneNo を取得する
	 * 
	 * @return tbxTelephoneNo
	 */
	public String getTbxTelephoneNo() {
		return tbxTelephoneNo;
	}

	/**
	 * tbxTelephoneNo を設定する
	 * 
	 * @param tbxTelephoneNo
	 */
	public void setTbxTelephoneNo(String tbxTelephoneNo) {
		this.tbxTelephoneNo = tbxTelephoneNo;
	}

	/**
	 * tbxFaxNo を取得する
	 * 
	 * @return tbxFaxNo
	 */
	public String getTbxFaxNo() {
		return tbxFaxNo;
	}

	/**
	 * tbxFaxNo を設定する
	 * 
	 * @param tbxFaxNo
	 */
	public void setTbxFaxNo(String tbxFaxNo) {
		this.tbxFaxNo = tbxFaxNo;
	}

	/**
	 * tbxAffiliateCode を取得する
	 * 
	 * @return tbxAffiliateCode
	 */
	public String getTbxAffiliateCode() {
		return tbxAffiliateCode;
	}

	/**
	 * tbxAffiliateCode を設定する
	 * 
	 * @param tbxAffiliateCode
	 */
	public void setTbxAffiliateCode(String tbxAffiliateCode) {
		this.tbxAffiliateCode = tbxAffiliateCode;
	}

	/**
	 * tbxAddressLine1RA を取得する
	 * 
	 * @return tbxAddressLine1RA
	 */
	public String getTbxAddressLine1RA() {
		return tbxAddressLine1RA;
	}

	/**
	 * tbxAddressLine1RA を設定する
	 * 
	 * @param tbxAddressLine1RA
	 */
	public void setTbxAddressLine1RA(String tbxAddressLine1RA) {
		this.tbxAddressLine1RA = tbxAddressLine1RA;
	}

	/**
	 * tbxAddressLine2RA を取得する
	 * 
	 * @return tbxAddressLine2RA
	 */
	public String getTbxAddressLine2RA() {
		return tbxAddressLine2RA;
	}

	/**
	 * tbxAddressLine2RA を設定する
	 * 
	 * @param tbxAddressLine2RA
	 */
	public void setTbxAddressLine2RA(String tbxAddressLine2RA) {
		this.tbxAddressLine2RA = tbxAddressLine2RA;
	}

	/**
	 * tbxAddressLine3RA を取得する
	 * 
	 * @return tbxAddressLine3RA
	 */
	public String getTbxAddressLine3RA() {
		return tbxAddressLine3RA;
	}

	/**
	 * tbxAddressLine3RA を設定する
	 * 
	 * @param tbxAddressLine3RA
	 */
	public void setTbxAddressLine3RA(String tbxAddressLine3RA) {
		this.tbxAddressLine3RA = tbxAddressLine3RA;
	}

	/**
	 * tbxZipCodeRA を取得する
	 * 
	 * @return tbxZipCodeRA
	 */
	public String getTbxZipCodeRA() {
		return tbxZipCodeRA;
	}

	/**
	 * tbxZipCodeRA を設定する
	 * 
	 * @param tbxZipCodeRA
	 */
	public void setTbxZipCodeRA(String tbxZipCodeRA) {
		this.tbxZipCodeRA = tbxZipCodeRA;
	}

	/**
	 * cboAddressCountryRA を取得する
	 * 
	 * @return cboAddressCountryRA
	 */
	public String getCboAddressCountryRA() {
		return cboAddressCountryRA;
	}

	/**
	 * cboAddressCountryRA を設定する
	 * 
	 * @param cboAddressCountryRA
	 */
	public void setCboAddressCountryRA(String cboAddressCountryRA) {
		this.cboAddressCountryRA = cboAddressCountryRA;
	}

	/**
	 * tbxAddressLine1CA を取得する
	 * 
	 * @return tbxAddressLine1CA
	 */
	public String getTbxAddressLine1CA() {
		return tbxAddressLine1CA;
	}

	/**
	 * tbxAddressLine1CA を設定する
	 * 
	 * @param tbxAddressLine1CA
	 */
	public void setTbxAddressLine1CA(String tbxAddressLine1CA) {
		this.tbxAddressLine1CA = tbxAddressLine1CA;
	}

	/**
	 * tbxAddressLine2CA を取得する
	 * 
	 * @return tbxAddressLine2CA
	 */
	public String getTbxAddressLine2CA() {
		return tbxAddressLine2CA;
	}

	/**
	 * tbxAddressLine2CA を設定する
	 * 
	 * @param tbxAddressLine2CA
	 */
	public void setTbxAddressLine2CA(String tbxAddressLine2CA) {
		this.tbxAddressLine2CA = tbxAddressLine2CA;
	}

	/**
	 * tbxAddressLine3CA を取得する
	 * 
	 * @return tbxAddressLine3CA
	 */
	public String getTbxAddressLine3CA() {
		return tbxAddressLine3CA;
	}

	/**
	 * tbxAddressLine3CA を設定する
	 * 
	 * @param tbxAddressLine3CA
	 */
	public void setTbxAddressLine3CA(String tbxAddressLine3CA) {
		this.tbxAddressLine3CA = tbxAddressLine3CA;
	}

	/**
	 * tbxZipCodeCA を取得する
	 * 
	 * @return tbxZipCodeCA
	 */
	public String getTbxZipCodeCA() {
		return tbxZipCodeCA;
	}

	/**
	 * tbxZipCodeCA を設定する
	 * 
	 * @param tbxZipCodeCA
	 */
	public void setTbxZipCodeCA(String tbxZipCodeCA) {
		this.tbxZipCodeCA = tbxZipCodeCA;
	}

	/**
	 * cboAddressCountryCA を取得する
	 * 
	 * @return cboAddressCountryCA
	 */
	public String getCboAddressCountryCA() {
		return cboAddressCountryCA;
	}

	/**
	 * cboAddressCountryCA を設定する
	 * 
	 * @param cboAddressCountryCA
	 */
	public void setCboAddressCountryCA(String cboAddressCountryCA) {
		this.cboAddressCountryCA = cboAddressCountryCA;
	}

	/**
	 * tbxEmailSC を取得する
	 * 
	 * @return tbxEmailSC
	 */
	public String getTbxEmailSC() {
		return tbxEmailSC;
	}

	/**
	 * tbxEmailSC を設定する
	 * 
	 * @param tbxEmailSC
	 */
	public void setTbxEmailSC(String tbxEmailSC) {
		this.tbxEmailSC = tbxEmailSC;
	}

	/**
	 * tbxEmailTC を取得する
	 * 
	 * @return tbxEmailTC
	 */
	public String getTbxEmailTC() {
		return tbxEmailTC;
	}

	/**
	 * tbxEmailTC を設定する
	 * 
	 * @param tbxEmailTC
	 */
	public void setTbxEmailTC(String tbxEmailTC) {
		this.tbxEmailTC = tbxEmailTC;
	}

	/**
	 * tbxEmailOC を取得する
	 * 
	 * @return tbxEmailOC
	 */
	public String getTbxEmailOC() {
		return tbxEmailOC;
	}

	/**
	 * tbxEmailOC を設定する
	 * 
	 * @param tbxEmailOC
	 */
	public void setTbxEmailOC(String tbxEmailOC) {
		this.tbxEmailOC = tbxEmailOC;
	}

	/**
	 * tbxTelephoneNoSC を取得する
	 * 
	 * @return tbxTelephoneNoSC
	 */
	public String getTbxTelephoneNoSC() {
		return tbxTelephoneNoSC;
	}

	/**
	 * tbxTelephoneNoSC を設定する
	 * 
	 * @param tbxTelephoneNoSC
	 */
	public void setTbxTelephoneNoSC(String tbxTelephoneNoSC) {
		this.tbxTelephoneNoSC = tbxTelephoneNoSC;
	}

	/**
	 * tbxTelephoneNoTC を取得する
	 * 
	 * @return tbxTelephoneNoTC
	 */
	public String getTbxTelephoneNoTC() {
		return tbxTelephoneNoTC;
	}

	/**
	 * tbxTelephoneNoTC を設定する
	 * 
	 * @param tbxTelephoneNoTC
	 */
	public void setTbxTelephoneNoTC(String tbxTelephoneNoTC) {
		this.tbxTelephoneNoTC = tbxTelephoneNoTC;
	}

	/**
	 * tbxTelephoneNoOC を取得する
	 * 
	 * @return tbxTelephoneNoOC
	 */
	public String getTbxTelephoneNoOC() {
		return tbxTelephoneNoOC;
	}

	/**
	 * tbxTelephoneNoOC を設定する
	 * 
	 * @param tbxTelephoneNoOC
	 */
	public void setTbxTelephoneNoOC(String tbxTelephoneNoOC) {
		this.tbxTelephoneNoOC = tbxTelephoneNoOC;
	}

	/**
	 * tbxFaxNoSC を取得する
	 * 
	 * @return tbxFaxNoSC
	 */
	public String getTbxFaxNoSC() {
		return tbxFaxNoSC;
	}

	/**
	 * tbxFaxNoSC を設定する
	 * 
	 * @param tbxFaxNoSC
	 */
	public void setTbxFaxNoSC(String tbxFaxNoSC) {
		this.tbxFaxNoSC = tbxFaxNoSC;
	}

	/**
	 * tbxFaxNoTC を取得する
	 * 
	 * @return tbxFaxNoTC
	 */
	public String getTbxFaxNoTC() {
		return tbxFaxNoTC;
	}

	/**
	 * tbxFaxNoTC を設定する
	 * 
	 * @param tbxFaxNoTC
	 */
	public void setTbxFaxNoTC(String tbxFaxNoTC) {
		this.tbxFaxNoTC = tbxFaxNoTC;
	}

	/**
	 * tbxFaxNoOC を取得する
	 * 
	 * @return tbxFaxNoOC
	 */
	public String getTbxFaxNoOC() {
		return tbxFaxNoOC;
	}

	/**
	 * tbxFaxNoOC を設定する
	 * 
	 * @param tbxFaxNoOC
	 */
	public void setTbxFaxNoOC(String tbxFaxNoOC) {
		this.tbxFaxNoOC = tbxFaxNoOC;
	}

	/**
	 * tbxProxyServerName を取得する
	 * 
	 * @return tbxProxyServerName
	 */
	public String getTbxProxyServerName() {
		return tbxProxyServerName;
	}

	/**
	 * tbxProxyServerName を設定する
	 * 
	 * @param tbxProxyServerName
	 */
	public void setTbxProxyServerName(String tbxProxyServerName) {
		this.tbxProxyServerName = tbxProxyServerName;
	}

	/**
	 * tbxPortNumber を取得する
	 * 
	 * @return tbxPortNumber
	 */
	public String getTbxPortNumber() {
		return tbxPortNumber;
	}

	/**
	 * tbxPortNumber を設定する
	 * 
	 * @param tbxPortNumber
	 */
	public void setTbxPortNumber(String tbxPortNumber) {
		this.tbxPortNumber = tbxPortNumber;
	}

	/**
	 * tbxPrimaryDomainNameServer を取得する
	 * 
	 * @return tbxPrimaryDomainNameServer
	 */
	public String getTbxPrimaryDomainNameServer() {
		return tbxPrimaryDomainNameServer;
	}

	/**
	 * tbxPrimaryDomainNameServer を設定する
	 * 
	 * @param tbxPrimaryDomainNameServer
	 */
	public void setTbxPrimaryDomainNameServer(String tbxPrimaryDomainNameServer) {
		this.tbxPrimaryDomainNameServer = tbxPrimaryDomainNameServer;
	}

	/**
	 * tbxSecondaryDomainNameServer を取得する
	 * 
	 * @return tbxSecondaryDomainNameServer
	 */
	public String getTbxSecondaryDomainNameServer() {
		return tbxSecondaryDomainNameServer;
	}

	/**
	 * tbxSecondaryDomainNameServer を設定する
	 * 
	 * @param tbxSecondaryDomainNameServer
	 */
	public void setTbxSecondaryDomainNameServer(
			String tbxSecondaryDomainNameServer) {
		this.tbxSecondaryDomainNameServer = tbxSecondaryDomainNameServer;
	}

	/**
	 * id_com を取得する
	 * 
	 * @return id_com
	 */
	public String getId_com() {
		return id_com;
	}

	/**
	 * id_com を設定する
	 * 
	 * @param id_com
	 */
	public void setId_com(String id_com) {
		this.id_com = id_com;
	}

	/**
	 * photo を取得する
	 * 
	 * @return photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * photo を設定する
	 * 
	 * @param photo
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * tbxDefaultDialupTelNo を取得する
	 * 
	 * @return tbxDefaultDialupTelNo
	 */
	public String getTbxDefaultDialupTelNo() {
		return tbxDefaultDialupTelNo;
	}

	/**
	 * tbxDefaultDialupTelNo を設定する
	 * 
	 * @param tbxDefaultDialupTelNo
	 */
	public void setTbxDefaultDialupTelNo(String tbxDefaultDialupTelNo) {
		this.tbxDefaultDialupTelNo = tbxDefaultDialupTelNo;
	}

	/**
	 * tbxDefaultPassword を取得する
	 * 
	 * @return tbxDefaultPassword
	 */
	public String getTbxDefaultPassword() {
		return tbxDefaultPassword;
	}

	/**
	 * tbxDefaultPassword を設定する
	 * 
	 * @param tbxDefaultPassword
	 */
	public void setTbxDefaultPassword(String tbxDefaultPassword) {
		this.tbxDefaultPassword = tbxDefaultPassword;
	}

    /**
     * @return the tbxCompanyGstRegNo
     */
    public String getTbxCompanyGstRegNo() {
        return tbxCompanyGstRegNo;
    }

    /**
     * @param tbxCompanyGstRegNo the tbxCompanyGstRegNo to set
     */
    public void setTbxCompanyGstRegNo(String tbxCompanyGstRegNo) {
        this.tbxCompanyGstRegNo = tbxCompanyGstRegNo;
    }
    
    public void setTbxDomainName(String tbxDomainName) {
        this.tbxDomainName = tbxDomainName;
    }

    public String getTbxDomainName() {
        return tbxDomainName;
    }

    public void setTbxWebmailURL(String tbxWebmailURL) {
        this.tbxWebmailURL = tbxWebmailURL;
    }

    public String getTbxWebmailURL() {
        return tbxWebmailURL;
    }

    public void setTbxSMTPServerName(String tbxSMTPServerName) {
        this.tbxSMTPServerName = tbxSMTPServerName;
    }

    public String getTbxSMTPServerName() {
        return tbxSMTPServerName;
    }

    public void setTbxPopServerName(String tbxPopServerName) {
        this.tbxPopServerName = tbxPopServerName;
    }

    public String getTbxPopServerName() {
        return tbxPopServerName;
    }
}