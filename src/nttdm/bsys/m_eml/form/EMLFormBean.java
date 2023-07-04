package nttdm.bsys.m_eml.form;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.m_eml.bean.MailModule;
import nttdm.bsys.m_eml.bean.MailTemplate;

public class EMLFormBean extends ValidatorActionFormEx {

	private static final long serialVersionUID = 5471385394462378902L;
	private String serverName;
	private String userName;
	private String portNumber;
	//#252 Batch Email Billing Document: generate PDF / email CT 11052017 ST
	private String ssltls;
	private String testEmail;
	//#252 Batch Email Billing Document: generate PDF / email CT 11052017 EN
	private String password;
	private List<MailModule> mailModule = new ArrayList<MailModule>();
	private List<MailTemplate> mailTemplate = new ArrayList<MailTemplate>();
	private String message;
	private String id_com;
	private int attachmentFileSize;

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName
	 *            the serverName to set
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
	 * @param userName
	 *            the userName to set
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
	 * @param portNumber
	 *            the portNumber to set
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
	 * @param password
	 *            the password to set
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
	 * @param mailModule
	 *            the mailModule to set
	 */
	public void setMailModule(List<MailModule> mailModule) {
		this.mailModule = mailModule;
	}

	/**
	 * @return the mailTemplate
	 */
	public List<MailTemplate> getMailTemplate() {
		return mailTemplate;
	}

	/**
	 * @param mailTemplate
	 *            the mailTemplate to set
	 */
	public void setMailTemplate(List<MailTemplate> mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public MailModule getMailmod(int index) {
		while (index >= mailModule.size())
			mailModule.add(new MailModule());
		return (MailModule) mailModule.get(index);
	}
	
	public MailTemplate getMailTem(int index) {
		while (index >= mailTemplate.size())
			mailTemplate.add(new MailTemplate());
		return (MailTemplate) mailTemplate.get(index);
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
