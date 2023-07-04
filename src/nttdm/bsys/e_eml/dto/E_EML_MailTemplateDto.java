package nttdm.bsys.e_eml.dto;

import java.io.Serializable;

public class E_EML_MailTemplateDto implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	
	private String idCode;
	private String description;
	private String subject;
	private String attactmentPass;
	private String content;
	private String attactment1;
	private String attactment2;
	private String attactment3;
	private String attactment4;
	private String alwaysCc;
	private String fromDisplayName;
	private String zipFlieName;
	
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAttactmentPass() {
		return attactmentPass;
	}
	public void setAttactmentPass(String attactmentPass) {
		this.attactmentPass = attactmentPass;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttactment1() {
		return attactment1;
	}
	public void setAttactment1(String attactment1) {
		this.attactment1 = attactment1;
	}
	public String getAttactment2() {
		return attactment2;
	}
	public void setAttactment2(String attactment2) {
		this.attactment2 = attactment2;
	}
	public String getAttactment3() {
		return attactment3;
	}
	public void setAttactment3(String attactment3) {
		this.attactment3 = attactment3;
	}
	public String getAttactment4() {
		return attactment4;
	}
	public void setAttactment4(String attactment4) {
		this.attactment4 = attactment4;
	}
	public String getAlwaysCc() {
		return alwaysCc;
	}
	public void setAlwaysCc(String alwaysCc) {
		this.alwaysCc = alwaysCc;
	}
	/**
	 * @return the fromDisplayName
	 */
	public String getFromDisplayName() {
		return fromDisplayName;
	}
	/**
	 * @param fromDisplayName the fromDisplayName to set
	 */
	public void setFromDisplayName(String fromDisplayName) {
		this.fromDisplayName = fromDisplayName;
	}
	/**
	 * @return the zipFlieName
	 */
	public String getZipFlieName() {
		return zipFlieName;
	}
	/**
	 * @param zipFlieName the zipFlieName to set
	 */
	public void setZipFlieName(String zipFlieName) {
		this.zipFlieName = zipFlieName;
	}

}
