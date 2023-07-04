/*
 * @(#)B_BIF_S03SaveInput.java
 *
 *
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

/**
 * 入力DTOクラス。
 * 
 * @author duongnld
 */
public class B_BIFS03SaveInput extends B_BIF_Input implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3549105069125723437L;
	
	private FormFile[] listFileTFBif;
	private FormFile[] listFileMRBif;
	private FormFile[] listFileCRBif;
	private FormFile[] listFileTLBif;
	private FormFile[] listFilePOBif;
	private FormFile[] listFileOTBif;
	private FormFile[] listFileIVBif;
	private FormFile[] listFileAGBif;
	
	private String checkMultipleInOneInvoice = null;
	
	private String emailToAdd;
    private String emailCcAdd;
	/**
	 * @return the listFileTFBif
	 */
	public FormFile[] getListFileTFBif() {
		return listFileTFBif;
	}
	/**
	 * @param listFileTFBif the listFileTFBif to set
	 */
	public void setListFileTFBif(FormFile[] listFileTFBif) {
		this.listFileTFBif = listFileTFBif;
	}
	/**
	 * @return the listFileMRBif
	 */
	public FormFile[] getListFileMRBif() {
		return listFileMRBif;
	}
	/**
	 * @param listFileMRBif the listFileMRBif to set
	 */
	public void setListFileMRBif(FormFile[] listFileMRBif) {
		this.listFileMRBif = listFileMRBif;
	}
	/**
	 * @return the listFileCRBif
	 */
	public FormFile[] getListFileCRBif() {
		return listFileCRBif;
	}
	/**
	 * @param listFileCRBif the listFileCRBif to set
	 */
	public void setListFileCRBif(FormFile[] listFileCRBif) {
		this.listFileCRBif = listFileCRBif;
	}
	/**
	 * @return the listFileTLBif
	 */
	public FormFile[] getListFileTLBif() {
		return listFileTLBif;
	}
	/**
	 * @param listFileTLBif the listFileTLBif to set
	 */
	public void setListFileTLBif(FormFile[] listFileTLBif) {
		this.listFileTLBif = listFileTLBif;
	}
	/**
	 * @return the listFilePOBif
	 */
	public FormFile[] getListFilePOBif() {
		return listFilePOBif;
	}
	/**
	 * @param listFilePOBif the listFilePOBif to set
	 */
	public void setListFilePOBif(FormFile[] listFilePOBif) {
		this.listFilePOBif = listFilePOBif;
	}
	/**
	 * @return the listFileOTBif
	 */
	public FormFile[] getListFileOTBif() {
		return listFileOTBif;
	}
	/**
	 * @param listFileOTBif the listFileOTBif to set
	 */
	public void setListFileOTBif(FormFile[] listFileOTBif) {
		this.listFileOTBif = listFileOTBif;
	}
	/**
	 * @return the listFileIVBif
	 */
	public FormFile[] getListFileIVBif() {
		return listFileIVBif;
	}
	/**
	 * @param listFileIVBif the listFileIVBif to set
	 */
	public void setListFileIVBif(FormFile[] listFileIVBif) {
		this.listFileIVBif = listFileIVBif;
	}
	/**
	 * @return the listFileAGBif
	 */
	public FormFile[] getListFileAGBif() {
		return listFileAGBif;
	}
	/**
	 * @param listFileAGBif the listFileAGBif to set
	 */
	public void setListFileAGBif(FormFile[] listFileAGBif) {
		this.listFileAGBif = listFileAGBif;
	}
	public void setCheckMultipleInOneInvoice(String checkMultipleInOneInvoice) {
		this.checkMultipleInOneInvoice = checkMultipleInOneInvoice;
	}
	public String getCheckMultipleInOneInvoice() {
		return checkMultipleInOneInvoice;
	}
	/**
	 * @return the emailToAdd
	 */
	public String getEmailToAdd() {
		return emailToAdd;
	}
	/**
	 * @param emailToAdd the emailToAdd to set
	 */
	public void setEmailToAdd(String emailToAdd) {
		this.emailToAdd = emailToAdd;
	}
	/**
	 * @return the emailCcAdd
	 */
	public String getEmailCcAdd() {
		return emailCcAdd;
	}
	/**
	 * @param emailCcAdd the emailCcAdd to set
	 */
	public void setEmailCcAdd(String emailCcAdd) {
		this.emailCcAdd = emailCcAdd;
	}
}