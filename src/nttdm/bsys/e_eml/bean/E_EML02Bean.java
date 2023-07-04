package nttdm.bsys.e_eml.bean;

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class E_EML02Bean extends ValidatorActionFormEx{
	private static final long serialVersionUID = 5471385394462378902L;
	private String batchId;
	private String emailId;
	private String emailfrom;
	private String emailto;
	private String emailcc;
	private String subject;
	private String sent;
	private String sent2;
	private String attachment;
	private List<Map<String, Object>> id_doc;
	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the sent
	 */
	public String getSent() {
		return sent;
	}
	/**
	 * @param sent the sent to set
	 */
	public void setSent(String sent) {
		this.sent = sent;
	}
	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}
	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	/**
	 * @return the sent2
	 */
	public String getSent2() {
		return sent2;
	}
	/**
	 * @param sent2 the sent2 to set
	 */
	public void setSent2(String sent2) {
		this.sent2 = sent2;
	}
	/**
	 * @return the emailfrom
	 */
	public String getEmailfrom() {
		return emailfrom;
	}
	/**
	 * @param emailfrom the emailfrom to set
	 */
	public void setEmailfrom(String emailfrom) {
		this.emailfrom = emailfrom;
	}
	/**
	 * @return the emailto
	 */
	public String getEmailto() {
		return emailto;
	}
	/**
	 * @param emailto the emailto to set
	 */
	public void setEmailto(String emailto) {
		this.emailto = emailto;
	}
	/**
	 * @return the emailcc
	 */
	public String getEmailcc() {
		return emailcc;
	}
	/**
	 * @param emailcc the emailcc to set
	 */
	public void setEmailcc(String emailcc) {
		this.emailcc = emailcc;
	}
	/**
	 * @return the id_doc
	 */
	public List<Map<String, Object>> getId_doc() {
		return id_doc;
	}
	/**
	 * @param id_doc the id_doc to set
	 */
	public void setId_doc(List<Map<String, Object>> id_doc) {
		this.id_doc = id_doc;
	}


}
