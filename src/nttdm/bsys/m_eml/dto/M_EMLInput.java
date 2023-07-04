package nttdm.bsys.m_eml.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.m_eml.bean.MailModule;
import nttdm.bsys.m_eml.form.EMLFormBean;

public class M_EMLInput extends EMLFormBean implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private String serverName;
	private String userName;
	private String portNumber;
	//#252 Batch Email Billing Document: generate PDF / email CT 11052017 ST
	private String ssltls;
	private String testEmail;
	//#252 Batch Email Billing Document: generate PDF / email CT 11052017 EN
	private String password;
	private List<MailModule> mailModule;
	private BillingSystemUserValueObject uvo;
	private Integer id_audit;
	private String id_login;
	private String id_com;
	private int attachmentFileSize;
	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}
	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the portNumber
	 */
	public String getPortNumber() {
		return portNumber;
	}
	/**
	 * @param portNumber the portNumber to set
	 */
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
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
	 * @return the mailModule
	 */
	public List<MailModule> getMailModule() {
		return mailModule;
	}
	/**
	 * @param mailModule the mailModule to set
	 */
	public void setMailModule(List<MailModule> mailModule) {
		this.mailModule = mailModule;
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
	 * @return the id_com
	 */
	public String getId_com() {
		return id_com;
	}
	/**
	 * @param id_com the id_com to set
	 */
	public void setId_com(String id_com) {
		this.id_com = id_com;
	}
	/**
	 * @return the attachmentFileSize
	 */
	public int getAttachmentFileSize() {
		return attachmentFileSize;
	}
	/**
	 * @param attachmentFileSize the attachmentFileSize to set
	 */
	public void setAttachmentFileSize(int attachmentFileSize) {
		this.attachmentFileSize = attachmentFileSize;
	}
	// #252 Batch Email Billing Document: generate PDF / email CT 11052017 ST
	public String getSsltls() {
		return ssltls;
	}
	public void setSsltls(String ssltls) {
		this.ssltls = ssltls;
	}
	public String getTestEmail() {
		return testEmail;
	}
	public void setTestEmail(String testEmail) {
		this.testEmail = testEmail;
	}
	
	// #252 Batch Email Billing Document: generate PDF / email CT 11052017 EN
}
