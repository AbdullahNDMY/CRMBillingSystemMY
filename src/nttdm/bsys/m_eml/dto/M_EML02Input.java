package nttdm.bsys.m_eml.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class M_EML02Input implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private String code;
	private String description;
	private String display_name;
	private String subject;
	private String attachpass;
	private String content;
	private String attactmen1;
	private String attactmen2;
	private String attactmen3;
	private String attactmen4;
	private String alwaysCc;
	private String id_login;
	private Integer id_audit;
	private String isSaveFlg;
	private String modelFlg;
	private BillingSystemUserValueObject uvo;
	private String zipFilename;
	private String remark;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the attachpass
	 */
	public String getAttachpass() {
		return attachpass;
	}
	/**
	 * @param attachpass the attachpass to set
	 */
	public void setAttachpass(String attachpass) {
		this.attachpass = attachpass;
	}
	/**
	 * @return the conten
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param conten the conten to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the id_login
	 */
	public String getId_login() {
		return id_login;
	}
	/**
	 * @param id_login the id_login to set
	 */
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	/**
	 * @return the id_audit
	 */
	public Integer getId_audit() {
		return id_audit;
	}
	/**
	 * @param id_audit the id_audit to set
	 */
	public void setId_audit(Integer id_audit) {
		this.id_audit = id_audit;
	}
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
	 * @return the isSaveFlg
	 */
	public String getIsSaveFlg() {
		return isSaveFlg;
	}
	/**
	 * @param isSaveFlg the isSaveFlg to set
	 */
	public void setIsSaveFlg(String isSaveFlg) {
		this.isSaveFlg = isSaveFlg;
	}
	/**
	 * @return the modelFlg
	 */
	public String getModelFlg() {
		return modelFlg;
	}
	/**
	 * @param modelFlg the modelFlg to set
	 */
	public void setModelFlg(String modelFlg) {
		this.modelFlg = modelFlg;
	}
	/**
	 * @return the attactmen1
	 */
	public String getAttactmen1() {
		return attactmen1;
	}
	/**
	 * @param attactmen1 the attactmen1 to set
	 */
	public void setAttactmen1(String attactmen1) {
		this.attactmen1 = attactmen1;
	}
	/**
	 * @return the attactmen2
	 */
	public String getAttactmen2() {
		return attactmen2;
	}
	/**
	 * @param attactmen2 the attactmen2 to set
	 */
	public void setAttactmen2(String attactmen2) {
		this.attactmen2 = attactmen2;
	}
	/**
	 * @return the attactmen3
	 */
	public String getAttactmen3() {
		return attactmen3;
	}
	/**
	 * @param attactmen3 the attactmen3 to set
	 */
	public void setAttactmen3(String attactmen3) {
		this.attactmen3 = attactmen3;
	}
	/**
	 * @return the attactmen4
	 */
	public String getAttactmen4() {
		return attactmen4;
	}
	/**
	 * @param attactmen4 the attactmen4 to set
	 */
	public void setAttactmen4(String attactmen4) {
		this.attactmen4 = attactmen4;
	}
	/**
	 * @return the alwaysCc
	 */
	public String getAlwaysCc() {
		return alwaysCc;
	}
	/**
	 * @param alwaysCc the alwaysCc to set
	 */
	public void setAlwaysCc(String alwaysCc) {
		this.alwaysCc = alwaysCc;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @return the zipFilename
	 */
	public String getZipFilename() {
		return zipFilename;
	}
	/**
	 * @param zipFilename the zipFilename to set
	 */
	public void setZipFilename(String zipFilename) {
		this.zipFilename = zipFilename;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
