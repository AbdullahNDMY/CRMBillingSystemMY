/*
 * @(#)BIFS03Form.java
 *
 *
 */
package nttdm.bsys.bif.form;
import org.apache.struts.upload.FormFile;

/**
 * アクションフォームクラス。
 * 
 * @author duongnld
 */
public class BIFS03Form extends BIF_Type_Form {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3055703726094492246L;

	/**
	 * rdbInstructionf
	 */
	private String rdbInstructionf;

	/**
	 * rdbCreditNoteType
	 */
	private String rdbCreditNoteType;

	/**
	 * txtOthers
	 */
	private String txtOthers;

	/**
	 * txtRemarks
	 */
	private String txtRemarks;

	/**
	 * listFileTFBif
	 */
	private FormFile[] listFileTFBif;

	/**
	 * listFileMRBif
	 */
	private FormFile[] listFileMRBif;

	/**
	 * listFileCRBif
	 */
	private FormFile[] listFileCRBif;

	/**
	 * listFileTLBif
	 */
	private FormFile[] listFileTLBif;

	/**
	 * listFilePOBif
	 */
	private FormFile[] listFilePOBif;

	/**
	 * listFileOTBif
	 */
	private FormFile[] listFileOTBif;

	/**
	 * listFileIVBif
	 */
	private FormFile[] listFileIVBif;

	/**
	 * listFileAGBif
	 */
	private FormFile[] listFileAGBif;
	
	private String hidIs_ObtainA;
	private String hidIs_ObtainV;
	
	
	private String hidDeleteListFileTFBif;
	private String hidDeleteListFileMRBif;
	private String hidDeleteListFileCRBif;
	private String hidDeleteListFileTLBif;
	private String hidDeleteListFilePOBif;
	private String hidDeleteListFileOTBif;
	private String hidDeleteListFileIVBif;
	private String hidDeleteListFileAGBif;
	
	//section properties
	private String cboBillingOpera;
	private String tbxBIFReceivedD;
	private String tbxInvoiceRefer;
	private String tbxPrintDate;
	private String tbxSignDate;
	
	private String hidIsRevised;
	private String hidSectionNo;
	private String hidSectionGroup;
	private String hidIdWfApproval;
	private String hidIsSave;
	
	private String tbxApprComments;
	private String hidApprStatus;
	
	/**
	 * rdbInstructionf を取得する
	 * 
	 * @return rdbInstructionf
	 */
	public String getRdbInstructionf() {
		return rdbInstructionf;
	}

	/**
	 * rdbInstructionf を設定する
	 * 
	 * @param rdbInstructionf
	 *            rdbInstructionf
	 */
	public void setRdbInstructionf(String rdbInstructionf) {
		this.rdbInstructionf = rdbInstructionf;
	}

	/**
	 * rdbCreditNoteType を取得する
	 * 
	 * @return rdbCreditNoteType
	 */
	public String getRdbCreditNoteType() {
		return rdbCreditNoteType;
	}

	/**
	 * rdbCreditNoteType を設定する
	 * 
	 * @param rdbCreditNoteType
	 *            rdbCreditNoteType
	 */
	public void setRdbCreditNoteType(String rdbCreditNoteType) {
		this.rdbCreditNoteType = rdbCreditNoteType;
	}

	/**
	 * txtOthers を取得する
	 * 
	 * @return txtOthers
	 */
	public String getTxtOthers() {
		return txtOthers;
	}

	/**
	 * txtOthers を設定する
	 * 
	 * @param txtOthers
	 *            txtOthers
	 */
	public void setTxtOthers(String txtOthers) {
		this.txtOthers = txtOthers;
	}

	/**
	 * txtRemarks を取得する
	 * 
	 * @return txtRemarks
	 */
	public String getTxtRemarks() {
		return txtRemarks;
	}

	/**
	 * txtRemarks を設定する
	 * 
	 * @param txtRemarks
	 *            txtRemarks
	 */
	public void setTxtRemarks(String txtRemarks) {
		this.txtRemarks = txtRemarks;
	}

	/**
	 * listFileTFBif を取得する
	 * 
	 * @return listFileTFBif
	 */
	public FormFile[] getListFileTFBif() {
		return listFileTFBif;
	}

	/**
	 * listFileTFBif を設定する
	 * 
	 * @param listFileTFBif
	 *            listFileTFBif
	 */
	public void setListFileTFBif(FormFile[] listFileTFBif) {
		this.listFileTFBif = listFileTFBif;
	}

	/**
	 * listFileMRBif を取得する
	 * 
	 * @return listFileMRBif
	 */
	public FormFile[] getListFileMRBif() {
		return listFileMRBif;
	}

	/**
	 * listFileMRBif を設定する
	 * 
	 * @param listFileMRBif
	 *            listFileMRBif
	 */
	public void setListFileMRBif(FormFile[] listFileMRBif) {
		this.listFileMRBif = listFileMRBif;
	}

	/**
	 * listFileCRBif を取得する
	 * 
	 * @return listFileCRBif
	 */
	public FormFile[] getListFileCRBif() {
		return listFileCRBif;
	}

	/**
	 * listFileCRBif を設定する
	 * 
	 * @param listFileCRBif
	 *            listFileCRBif
	 */
	public void setListFileCRBif(FormFile[] listFileCRBif) {
		this.listFileCRBif = listFileCRBif;
	}

	/**
	 * listFileTLBif を取得する
	 * 
	 * @return listFileTLBif
	 */
	public FormFile[] getListFileTLBif() {
		return listFileTLBif;
	}

	/**
	 * listFileTLBif を設定する
	 * 
	 * @param listFileTLBif
	 *            listFileTLBif
	 */
	public void setListFileTLBif(FormFile[] listFileTLBif) {
		this.listFileTLBif = listFileTLBif;
	}

	/**
	 * listFilePOBif を取得する
	 * 
	 * @return listFilePOBif
	 */
	public FormFile[] getListFilePOBif() {
		return listFilePOBif;
	}

	/**
	 * listFilePOBif を設定する
	 * 
	 * @param listFilePOBif
	 *            listFilePOBif
	 */
	public void setListFilePOBif(FormFile[] listFilePOBif) {
		this.listFilePOBif = listFilePOBif;
	}

	/**
	 * listFileOTBif を取得する
	 * 
	 * @return listFileOTBif
	 */
	public FormFile[] getListFileOTBif() {
		return listFileOTBif;
	}

	/**
	 * listFileOTBif を設定する
	 * 
	 * @param listFileOTBif
	 *            listFileOTBif
	 */
	public void setListFileOTBif(FormFile[] listFileOTBif) {
		this.listFileOTBif = listFileOTBif;
	}

	/**
	 * listFileIVBif を取得する
	 * 
	 * @return listFileIVBif
	 */
	public FormFile[] getListFileIVBif() {
		return listFileIVBif;
	}

	/**
	 * listFileIVBif を設定する
	 * 
	 * @param listFileIVBif
	 *            listFileIVBif
	 */
	public void setListFileIVBif(FormFile[] listFileIVBif) {
		this.listFileIVBif = listFileIVBif;
	}

	/**
	 * listFileAGBif を取得する
	 * 
	 * @return listFileAGBif
	 */
	public FormFile[] getListFileAGBif() {
		return listFileAGBif;
	}

	/**
	 * listFileAGBif を設定する
	 * 
	 * @param listFileAGBif
	 *            listFileAGBif
	 */
	public void setListFileAGBif(FormFile[] listFileAGBif) {
		this.listFileAGBif = listFileAGBif;
	}

	/**
	 * @return the hidIs_ObtainA
	 */
	public String getHidIs_ObtainA() {
		return hidIs_ObtainA;
	}

	/**
	 * @param hidIs_ObtainA the hidIs_ObtainA to set
	 */
	public void setHidIs_ObtainA(String hidIs_ObtainA) {
		this.hidIs_ObtainA = hidIs_ObtainA;
	}

	/**
	 * @return the hidIs_ObtainB
	 */
	public String getHidIs_ObtainV() {
		return hidIs_ObtainV;
	}

	/**
	 * @param hidIs_ObtainB the hidIs_ObtainB to set
	 */
	public void setHidIs_ObtainV(String hidIs_ObtainV) {
		this.hidIs_ObtainV = hidIs_ObtainV;
	}

	/**
	 * @return the hidDeleteListFileTFBif
	 */
	public String getHidDeleteListFileTFBif() {
		return hidDeleteListFileTFBif;
	}

	/**
	 * @param hidDeleteListFileTFBif the hidDeleteListFileTFBif to set
	 */
	public void setHidDeleteListFileTFBif(String hidDeleteListFileTFBif) {
		this.hidDeleteListFileTFBif = hidDeleteListFileTFBif;
	}

	/**
	 * @return the hidDeleteListFileMRBif
	 */
	public String getHidDeleteListFileMRBif() {
		return hidDeleteListFileMRBif;
	}

	/**
	 * @param hidDeleteListFileMRBif the hidDeleteListFileMRBif to set
	 */
	public void setHidDeleteListFileMRBif(String hidDeleteListFileMRBif) {
		this.hidDeleteListFileMRBif = hidDeleteListFileMRBif;
	}

	/**
	 * @return the hidDeleteListFileCRBif
	 */
	public String getHidDeleteListFileCRBif() {
		return hidDeleteListFileCRBif;
	}

	/**
	 * @param hidDeleteListFileCRBif the hidDeleteListFileCRBif to set
	 */
	public void setHidDeleteListFileCRBif(String hidDeleteListFileCRBif) {
		this.hidDeleteListFileCRBif = hidDeleteListFileCRBif;
	}

	/**
	 * @return the hidDeleteListFileTLBif
	 */
	public String getHidDeleteListFileTLBif() {
		return hidDeleteListFileTLBif;
	}

	/**
	 * @param hidDeleteListFileTLBif the hidDeleteListFileTLBif to set
	 */
	public void setHidDeleteListFileTLBif(String hidDeleteListFileTLBif) {
		this.hidDeleteListFileTLBif = hidDeleteListFileTLBif;
	}

	/**
	 * @return the hidDeleteListFilePOBif
	 */
	public String getHidDeleteListFilePOBif() {
		return hidDeleteListFilePOBif;
	}

	/**
	 * @param hidDeleteListFilePOBif the hidDeleteListFilePOBif to set
	 */
	public void setHidDeleteListFilePOBif(String hidDeleteListFilePOBif) {
		this.hidDeleteListFilePOBif = hidDeleteListFilePOBif;
	}

	/**
	 * @return the hidDeleteListFileOTBif
	 */
	public String getHidDeleteListFileOTBif() {
		return hidDeleteListFileOTBif;
	}

	/**
	 * @param hidDeleteListFileOTBif the hidDeleteListFileOTBif to set
	 */
	public void setHidDeleteListFileOTBif(String hidDeleteListFileOTBif) {
		this.hidDeleteListFileOTBif = hidDeleteListFileOTBif;
	}

	/**
	 * @return the hidDeleteListFileIVBif
	 */
	public String getHidDeleteListFileIVBif() {
		return hidDeleteListFileIVBif;
	}

	/**
	 * @param hidDeleteListFileIVBif the hidDeleteListFileIVBif to set
	 */
	public void setHidDeleteListFileIVBif(String hidDeleteListFileIVBif) {
		this.hidDeleteListFileIVBif = hidDeleteListFileIVBif;
	}

	/**
	 * @return the hidDeleteListFileAGBif
	 */
	public String getHidDeleteListFileAGBif() {
		return hidDeleteListFileAGBif;
	}

	/**
	 * @param hidDeleteListFileAGBif the hidDeleteListFileAGBif to set
	 */
	public void setHidDeleteListFileAGBif(String hidDeleteListFileAGBif) {
		this.hidDeleteListFileAGBif = hidDeleteListFileAGBif;
	}

	/**
	 * @return the cboBillingOpera
	 */
	public String getCboBillingOpera() {
		return cboBillingOpera;
	}

	/**
	 * @param cboBillingOpera the cboBillingOpera to set
	 */
	public void setCboBillingOpera(String cboBillingOpera) {
		this.cboBillingOpera = cboBillingOpera;
	}

	/**
	 * @return the tbxBIFReceivedD
	 */
	public String getTbxBIFReceivedD() {
		return tbxBIFReceivedD;
	}

	/**
	 * @param tbxBIFReceivedD the tbxBIFReceivedD to set
	 */
	public void setTbxBIFReceivedD(String tbxBIFReceivedD) {
		this.tbxBIFReceivedD = tbxBIFReceivedD;
	}

	/**
	 * @return the tbxInvoiceRefer
	 */
	public String getTbxInvoiceRefer() {
		return tbxInvoiceRefer;
	}

	/**
	 * @param tbxInvoiceRefer the tbxInvoiceRefer to set
	 */
	public void setTbxInvoiceRefer(String tbxInvoiceRefer) {
		this.tbxInvoiceRefer = tbxInvoiceRefer;
	}

	/**
	 * @return the tbxPrintDate
	 */
	public String getTbxPrintDate() {
		return tbxPrintDate;
	}

	/**
	 * @param tbxPrintDate the tbxPrintDate to set
	 */
	public void setTbxPrintDate(String tbxPrintDate) {
		this.tbxPrintDate = tbxPrintDate;
	}

	/**
	 * @return the tbxSignDate
	 */
	public String getTbxSignDate() {
		return tbxSignDate;
	}

	/**
	 * @param tbxSignDate the tbxSignDate to set
	 */
	public void setTbxSignDate(String tbxSignDate) {
		this.tbxSignDate = tbxSignDate;
	}

	/**
	 * @return the hidIsRevised
	 */
	public String getHidIsRevised() {
		return hidIsRevised;
	}

	/**
	 * @param hidIsRevised the hidIsRevised to set
	 */
	public void setHidIsRevised(String hidIsRevised) {
		this.hidIsRevised = hidIsRevised;
	}

	/**
	 * @return the hidSectionNo
	 */
	public String getHidSectionNo() {
		return hidSectionNo;
	}

	/**
	 * @param hidSectionNo the hidSectionNo to set
	 */
	public void setHidSectionNo(String hidSectionNo) {
		this.hidSectionNo = hidSectionNo;
	}

	/**
	 * @return the hidSectionGroup
	 */
	public String getHidSectionGroup() {
		return hidSectionGroup;
	}

	/**
	 * @param hidSectionGroup the hidSectionGroup to set
	 */
	public void setHidSectionGroup(String hidSectionGroup) {
		this.hidSectionGroup = hidSectionGroup;
	}

	/**
	 * @return the hidIdWfApproval
	 */
	public String getHidIdWfApproval() {
		return hidIdWfApproval;
	}

	/**
	 * @param hidIdWfApproval the hidIdWfApproval to set
	 */
	public void setHidIdWfApproval(String hidIdWfApproval) {
		this.hidIdWfApproval = hidIdWfApproval;
	}

	/**
	 * @param hidIsSave the hidIsSave to set
	 */
	public void setHidIsSave(String hidIsSave) {
		this.hidIsSave = hidIsSave;
	}

	/**
	 * @return the hidIsSave
	 */
	public String getHidIsSave() {
		return hidIsSave;
	}

	/**
	 * @param tbxApprComments the tbxApprComments to set
	 */
	public void setTbxApprComments(String tbxApprComments) {
		this.tbxApprComments = tbxApprComments;
	}

	/**
	 * @return the tbxApprComments
	 */
	public String getTbxApprComments() {
		return tbxApprComments;
	}

	/**
	 * @param hidApprStatus the hidApprStatus to set
	 */
	public void setHidApprStatus(String hidApprStatus) {
		this.hidApprStatus = hidApprStatus;
	}

	/**
	 * @return the hidApprStatus
	 */
	public String getHidApprStatus() {
		return hidApprStatus;
	}
}