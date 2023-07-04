/*
 * @(#)M_COMS01BLogicOutput.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * OutputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01BLogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1466536814431135611L;

	/**
     * 
     */
	private String mode;

	/**
     * 
     */
	private Map<java.lang.String, java.lang.Object> gen_info;

	/**
     * 
     */
	private Map<java.lang.String, java.lang.Object> sale_contact;

	/**
     * 
     */
	private Map<java.lang.String, java.lang.Object> tech_contact;

	/**
     * 
     */
	private Map<java.lang.String, java.lang.Object> other_contact;

	/**
     * 
     */
	private Map<java.lang.String, java.lang.Object> reg_address;

	/**
     * 
     */
	private Map<java.lang.String, java.lang.Object> corr_address;

	/**
     * 
     */
	private String cboAddressCountryCA;

	/**
     * 
     */
	private String cboAddressCountryRA;

	/**
     * 
     */
	private String id_com;

	/**
     * 
     */
	private String enable_bank;
	
	private String addrLine3Disp;

	/**
	 * mode を取得する
	 * 
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * mode を設定する
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * gen_info を取得する
	 * 
	 * @return gen_info
	 */
	public Map<java.lang.String, java.lang.Object> getGen_info() {
		return gen_info;
	}

	/**
	 * gen_info を設定する
	 * 
	 * @param gen_info
	 */
	public void setGen_info(Map<java.lang.String, java.lang.Object> gen_info) {
		this.gen_info = gen_info;
	}

	/**
	 * sale_contact を取得する
	 * 
	 * @return sale_contact
	 */
	public Map<java.lang.String, java.lang.Object> getSale_contact() {
		return sale_contact;
	}

	/**
	 * sale_contact を設定する
	 * 
	 * @param sale_contact
	 */
	public void setSale_contact(
			Map<java.lang.String, java.lang.Object> sale_contact) {
		this.sale_contact = sale_contact;
	}

	/**
	 * tech_contact を取得する
	 * 
	 * @return tech_contact
	 */
	public Map<java.lang.String, java.lang.Object> getTech_contact() {
		return tech_contact;
	}

	/**
	 * tech_contact を設定する
	 * 
	 * @param tech_contact
	 */
	public void setTech_contact(
			Map<java.lang.String, java.lang.Object> tech_contact) {
		this.tech_contact = tech_contact;
	}

	/**
	 * other_contact を取得する
	 * 
	 * @return other_contact
	 */
	public Map<java.lang.String, java.lang.Object> getOther_contact() {
		return other_contact;
	}

	/**
	 * other_contact を設定する
	 * 
	 * @param other_contact
	 */
	public void setOther_contact(
			Map<java.lang.String, java.lang.Object> other_contact) {
		this.other_contact = other_contact;
	}

	/**
	 * reg_address を取得する
	 * 
	 * @return reg_address
	 */
	public Map<java.lang.String, java.lang.Object> getReg_address() {
		return reg_address;
	}

	/**
	 * reg_address を設定する
	 * 
	 * @param reg_address
	 */
	public void setReg_address(
			Map<java.lang.String, java.lang.Object> reg_address) {
		this.reg_address = reg_address;
	}

	/**
	 * corr_address を取得する
	 * 
	 * @return corr_address
	 */
	public Map<java.lang.String, java.lang.Object> getCorr_address() {
		return corr_address;
	}

	/**
	 * corr_address を設定する
	 * 
	 * @param corr_address
	 */
	public void setCorr_address(
			Map<java.lang.String, java.lang.Object> corr_address) {
		this.corr_address = corr_address;
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
	 * enable_bank を取得する
	 * 
	 * @return enable_bank
	 */
	public String getEnable_bank() {
		return enable_bank;
	}

	/**
	 * enable_bank を設定する
	 * 
	 * @param enable_bank
	 */
	public void setEnable_bank(String enable_bank) {
		this.enable_bank = enable_bank;
	}

    /**
     * @return the addrLine3Disp
     */
    public String getAddrLine3Disp() {
        return addrLine3Disp;
    }

    /**
     * @param addrLine3Disp the addrLine3Disp to set
     */
    public void setAddrLine3Disp(String addrLine3Disp) {
        this.addrLine3Disp = addrLine3Disp;
    }
}