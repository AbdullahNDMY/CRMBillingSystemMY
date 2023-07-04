/*
 * @(#)B_BIF_S03SaveInput.java
 *
 *
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts.upload.FormFile;
/**
 * 入力DTOクラス。
 * 
 * @author duongnld
 */
public class B_BIFS03Input extends B_BIF_Input implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3549105069125723437L;
	
	public static final String ID_SCREEN = "BIFCN";
	
	private String is_ObtainA;
	private String is_ObtainV;
	
	private String deleteListFileTFBif;
	private String deleteListFileMRBif;
	private String deleteListFileCRBif;
	private String deleteListFileTLBif;
	private String deleteListFilePOBif;
	private String deleteListFileOTBif;
	private String deleteListFileIVBif;
	private String deleteListFileAGBif;
	
	private FormFile[] listFileTFBif;
	private FormFile[] listFileMRBif;
	private FormFile[] listFileCRBif;
	private FormFile[] listFileTLBif;
	private FormFile[] listFilePOBif;
	private FormFile[] listFileOTBif;
	private FormFile[] listFileIVBif;
	private FormFile[] listFileAGBif;
	
	//approval comment information
	private String isRevised;
	private String apprComment;
	private String apprStatus;
	
	//customer relation information
	private String idWfApproval;
	private String isSave;
	private String sectionNo;
	private String sectionGroup;
	private String bilOpBy;
	private String dateBifRcv;
	private String bilRefNo;
	private String datePrinted;
	private String dateSigned;
	private String customerAccount;
	
	private String actionR;
	private String idRefR;
	private Map<String, Object> pagAction;
	private String cboAttn;
	private String pictureId;
	private String emailToAdd;
    private String emailCcAdd;
    
	/**
	 * @return the pictureId
	 */
	public String getPictureId() {
		return pictureId;
	}
	/**
	 * @param pictureId the pictureId to set
	 */
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	
	/**
	 * @return the is_ObtainA
	 */
	public String getIs_ObtainA() {
		return is_ObtainA;
	}
	/**
	 * @param is_ObtainA the is_ObtainA to set
	 */
	public void setIs_ObtainA(String is_ObtainA) {
		this.is_ObtainA = is_ObtainA;
	}
	/**
	 * @return the is_ObtainV
	 */
	public String getIs_ObtainV() {
		return is_ObtainV;
	}
	/**
	 * @param is_ObtainV the is_ObtainV to set
	 */
	public void setIs_ObtainV(String is_ObtainV) {
		this.is_ObtainV = is_ObtainV;
	}
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
	/**
	 * @return the apprComment
	 */
	public String getApprComment() {
		return apprComment;
	}
	/**
	 * @param apprComment the apprComment to set
	 */
	public void setApprComment(String apprComment) {
		this.apprComment = apprComment;
	}
	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return apprStatus;
	}
	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	/**
	 * @return the bilOpBy
	 */
	public String getBilOpBy() {
		return bilOpBy;
	}
	/**
	 * @param bilOpBy the bilOpBy to set
	 */
	public void setBilOpBy(String bilOpBy) {
		this.bilOpBy = bilOpBy;
	}
	/**
	 * @return the dateBifRcv
	 */
	public String getDateBifRcv() {
		return dateBifRcv;
	}
	/**
	 * @param dateBifRcv the dateBifRcv to set
	 */
	public void setDateBifRcv(String dateBifRcv) {
		this.dateBifRcv = dateBifRcv;
	}
	/**
	 * @return the datePrinted
	 */
	public String getDatePrinted() {
		return datePrinted;
	}
	/**
	 * @param datePrinted the datePrinted to set
	 */
	public void setDatePrinted(String datePrinted) {
		this.datePrinted = datePrinted;
	}
	/**
	 * @return the dateSigned
	 */
	public String getDateSigned() {
		return dateSigned;
	}
	/**
	 * @param dateSigned the dateSigned to set
	 */
	public void setDateSigned(String dateSigned) {
		this.dateSigned = dateSigned;
	}
	/**
	 * @return the customerAccount
	 */
	public String getCustomerAccount() {
		return customerAccount;
	}
	/**
	 * @param customerAccount the customerAccount to set
	 */
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	/**
	 * @param bilRefNo the bilRefNo to set
	 */
	public void setBilRefNo(String bilRefNo) {
		this.bilRefNo = bilRefNo;
	}
	/**
	 * @return the bilRefNo
	 */
	public String getBilRefNo() {
		return bilRefNo;
	}
	/**
	 * @return the deleteListFileTFBif
	 */
	public String getDeleteListFileTFBif() {
		return deleteListFileTFBif;
	}
	/**
	 * @param deleteListFileTFBif the deleteListFileTFBif to set
	 */
	public void setDeleteListFileTFBif(String deleteListFileTFBif) {
		this.deleteListFileTFBif = deleteListFileTFBif;
	}
	/**
	 * @return the deleteListFileMRBif
	 */
	public String getDeleteListFileMRBif() {
		return deleteListFileMRBif;
	}
	/**
	 * @param deleteListFileMRBif the deleteListFileMRBif to set
	 */
	public void setDeleteListFileMRBif(String deleteListFileMRBif) {
		this.deleteListFileMRBif = deleteListFileMRBif;
	}
	/**
	 * @return the deleteListFileCRBif
	 */
	public String getDeleteListFileCRBif() {
		return deleteListFileCRBif;
	}
	/**
	 * @param deleteListFileCRBif the deleteListFileCRBif to set
	 */
	public void setDeleteListFileCRBif(String deleteListFileCRBif) {
		this.deleteListFileCRBif = deleteListFileCRBif;
	}
	/**
	 * @return the deleteListFileTLBif
	 */
	public String getDeleteListFileTLBif() {
		return deleteListFileTLBif;
	}
	/**
	 * @param deleteListFileTLBif the deleteListFileTLBif to set
	 */
	public void setDeleteListFileTLBif(String deleteListFileTLBif) {
		this.deleteListFileTLBif = deleteListFileTLBif;
	}
	/**
	 * @return the deleteListFilePOBif
	 */
	public String getDeleteListFilePOBif() {
		return deleteListFilePOBif;
	}
	/**
	 * @param deleteListFilePOBif the deleteListFilePOBif to set
	 */
	public void setDeleteListFilePOBif(String deleteListFilePOBif) {
		this.deleteListFilePOBif = deleteListFilePOBif;
	}
	/**
	 * @return the deleteListFileOTBif
	 */
	public String getDeleteListFileOTBif() {
		return deleteListFileOTBif;
	}
	/**
	 * @param deleteListFileOTBif the deleteListFileOTBif to set
	 */
	public void setDeleteListFileOTBif(String deleteListFileOTBif) {
		this.deleteListFileOTBif = deleteListFileOTBif;
	}
	/**
	 * @return the deleteListFileIVBif
	 */
	public String getDeleteListFileIVBif() {
		return deleteListFileIVBif;
	}
	/**
	 * @param deleteListFileIVBif the deleteListFileIVBif to set
	 */
	public void setDeleteListFileIVBif(String deleteListFileIVBif) {
		this.deleteListFileIVBif = deleteListFileIVBif;
	}
	/**
	 * @return the deleteListFileAGBif
	 */
	public String getDeleteListFileAGBif() {
		return deleteListFileAGBif;
	}
	/**
	 * @param deleteListFileAGBif the deleteListFileAGBif to set
	 */
	public void setDeleteListFileAGBif(String deleteListFileAGBif) {
		this.deleteListFileAGBif = deleteListFileAGBif;
	}
	/**
	 * @return the actionR
	 */
	public String getActionR() {
		return actionR;
	}
	/**
	 * @param actionR the actionR to set
	 */
	public void setActionR(String actionR) {
		this.actionR = actionR;
	}
	/**
	 * @return the idRefR
	 */
	public String getIdRefR() {
		return idRefR;
	}
	/**
	 * @param idRefR the idRefR to set
	 */
	public void setIdRefR(String idRefR) {
		this.idRefR = idRefR;
	}
	/**
	 * @return the isRevised
	 */
	public String getIsRevised() {
		return isRevised;
	}
	/**
	 * @param isRevised the isRevised to set
	 */
	public void setIsRevised(String isRevised) {
		this.isRevised = isRevised;
	}
	/**
	 * @return the idWfApproval
	 */
	public String getIdWfApproval() {
		return idWfApproval;
	}
	/**
	 * @param idWfApproval the idWfApproval to set
	 */
	public void setIdWfApproval(String idWfApproval) {
		this.idWfApproval = idWfApproval;
	}
	/**
	 * @return the sectionNo
	 */
	public String getSectionNo() {
		return sectionNo;
	}
	/**
	 * @param sectionNo the sectionNo to set
	 */
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	/**
	 * @return the sectionGroup
	 */
	public String getSectionGroup() {
		return sectionGroup;
	}
	/**
	 * @param sectionGroup the sectionGroup to set
	 */
	public void setSectionGroup(String sectionGroup) {
		this.sectionGroup = sectionGroup;
	}
	/**
	 * @param isSave the isSave to set
	 */
	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}
	/**
	 * @return the isSave
	 */
	public String getIsSave() {
		return isSave;
	}
	public Map<String, Object> getPagAction() {
		return pagAction;
	}
	public void setPagAction(Map<String, Object> pagAction) {
		this.pagAction = pagAction;
	}
	public void setCboAttn(String cboAttn) {
		this.cboAttn = cboAttn;
	}
	public String getCboAttn() {
		return cboAttn;
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