package nttdm.bsys.e_eml.form;

import java.util.List;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.e_eml.bean.E_EML02Bean;

public class E_EML02FromBean extends ValidatorActionFormEx{
	
	private static final long serialVersionUID = 5471385394462378902L;
	private String emailId;
	private String batchId;
	private String from;
	private String to;
	private String cc;
	private String subject;
	private String sent;
	private String password;
	private String attactment;
	private String billDocuNo;
	private String noEmail;
	private String sent2;
	private Integer totalCount=-1;
	private Integer startIndex=0;
	private Integer row=0;
	private List<E_EML02Bean> emailInfo;
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
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the attactment
	 */
	public String getAttactment() {
		return attactment;
	}
	/**
	 * @param attactment the attactment to set
	 */
	public void setAttactment(String attactment) {
		this.attactment = attactment;
	}
	/**
	 * @return the billdocuNo
	 */
	public String getBillDocuNo() {
		return billDocuNo;
	}
	/**
	 * @param billdocuNo the billdocuNo to set
	 */
	public void setBillDocuNo(String billDocuNo) {
		this.billDocuNo = billDocuNo;
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
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @return the row
	 */
	public Integer getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(Integer row) {
		this.row = row;
	}
	/**
	 * @return the noEmail
	 */
	public String getNoEmail() {
		return noEmail;
	}
	/**
	 * @param noEmail the noEmail to set
	 */
	public void setNoEmail(String noEmail) {
		this.noEmail = noEmail;
	}
	/**
	 * @return the emailInfo
	 */
	public List<E_EML02Bean> getEmailInfo() {
		return emailInfo;
	}
	/**
	 * @param emailInfo the emailInfo to set
	 */
	public void setEmailInfo(List<E_EML02Bean> emailInfo) {
		this.emailInfo = emailInfo;
	}

}
