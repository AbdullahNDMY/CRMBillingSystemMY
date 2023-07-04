package nttdm.bsys.e_eml.bean;

import java.io.Serializable;
import java.util.List;

public class E_EMLSendMailBean implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private String errorLog;
	private String filePathList; 
	private String pdfPass;
	private String id_doc;
	private String emailCc;
	private String errorLogTotal;
	private int emailFailCount;
	private boolean breakfor;
	/**
	 * @return the errorLog
	 */
	public String getErrorLog() {
		return errorLog;
	}
	/**
	 * @param errorLog the errorLog to set
	 */
	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
	/**
	 * @return the filePathList
	 */
	public String getFilePathList() {
		return filePathList;
	}
	/**
	 * @param filePathList the filePathList to set
	 */
	public void setFilePathList(String filePathList) {
		this.filePathList = filePathList;
	}
	/**
	 * @return the pdfPass
	 */
	public String getPdfPass() {
		return pdfPass;
	}
	/**
	 * @param pdfPass the pdfPass to set
	 */
	public void setPdfPass(String pdfPass) {
		this.pdfPass = pdfPass;
	}
	/**
	 * @return the id_doc
	 */
	public String getId_doc() {
		return id_doc;
	}
	/**
	 * @param id_doc the id_doc to set
	 */
	public void setId_doc(String id_doc) {
		this.id_doc = id_doc;
	}
	/**
	 * @return the emailCc
	 */
	public String getEmailCc() {
		return emailCc;
	}
	/**
	 * @param emailCc the emailCc to set
	 */
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}
	/**
	 * @return the errorLogTotal
	 */
	public String getErrorLogTotal() {
		return errorLogTotal;
	}
	/**
	 * @param errorLogTotal the errorLogTotal to set
	 */
	public void setErrorLogTotal(String errorLogTotal) {
		this.errorLogTotal = errorLogTotal;
	}
	/**
	 * @return the breakfor
	 */
	public boolean isBreakfor() {
		return breakfor;
	}
	/**
	 * @param breakfor the breakfor to set
	 */
	public void setBreakfor(boolean breakfor) {
		this.breakfor = breakfor;
	}
	/**
	 * @return the emailFailCount
	 */
	public int getEmailFailCount() {
		return emailFailCount;
	}
	/**
	 * @param emailFailCount the emailFailCount to set
	 */
	public void setEmailFailCount(int emailFailCount) {
		this.emailFailCount = emailFailCount;
	}
	
	

}
