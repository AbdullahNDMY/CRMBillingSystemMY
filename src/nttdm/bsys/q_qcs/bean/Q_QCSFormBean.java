/*
 * @(#)Q_QCSFormBean.java
 *
 *
 */
package nttdm.bsys.q_qcs.bean;

import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.common.bean.T_WF_SECTIONBean;
import nttdm.bsys.m_alt.bean.FileInfo;

import org.apache.struts.upload.FormFile;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * ActionForm class.
 * 
 * @author ss051
 */

public class Q_QCSFormBean extends ValidatorActionFormEx {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id_ref;
	private String id_cust;
	private String id_conslt;
	private String id_quo;
	private String req_user;
	private String date_req;
	private String cust_mode;
	private String ctterm;
	private String ctterm3_day;
	private String deposit;
	private String remarks;
	private String id_status;
	private String is_closed;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private String id_login;
	private String cust_name;
	private String user_name;
	private String start_date;
	private String end_date;
	private String startIndex ="0";
	private String totalCount = null;
	private String row = "0";
	private List<QCSHeader> qcsInfos;
	private String permission;
	private String id_ref_s01;
	private String [] svc_grp;
	private String [] capacity ;
	private String [] nrc ;
	private String [] mrc ;
	private String [] term ;
	private String [] tariff ;
	private String [] disc ;
	private String [] total;
	private String section_no_pmr;
	private String remarks_pmr;
	private String section_no_bzr;
	private String remarks_bzr;
	private String section_no_ctc;
	private String remarks_ctc;
	private String section_no_pri;
	private String remarks_pri;
	private String section_no_mrg;
	private String remarks_mrg;
	private String section_no_crc;
	private String remarks_crc;
	private String section_no_frx;
	private String remarks_frx;
	private String value_frx;
	private String section_no_cov;
	private String remarks_cov;
	private String clickEvent;	
	private String enableObtainApproval;
	private List<QCSDetail> qcsDetail;
	private String enableDelete;
	private String enableSave;
	private String enableEdit;
	/**
	 * 'yes' if user want to save any changes
	 * 'no' if not
	 */
	private String obt_appr_save;
	private String showPopalt;
	private String docStatusCSS;
	private String docStatus;	
	private String enableObtainApprovalPMR;
	private String enableObtainApprovalBZR;
	private String enableObtainApprovalCTC;
	private String enableObtainApprovalPRI;
	private String enableObtainApprovalMRG;
	private String enableObtainApprovalCRC;
	private String enableObtainApprovalFRX;
	private String enableObtainApprovalCOV;
	private String section_group;
	private String enableRejectApprove;
	private String enableQCS;
	private String enablePMR;
	private String enableBZR;
	private String enableCTC;
	private String enablePRI;
	private String enableMRG;
	private String enableCRC;
	private String enableFRX;
	private String enableCOV;
	private FormFile[] listFileQCS = new FormFile[10];
	private FormFile[] listFilePMR = new FormFile[10];
	private FormFile[] listFileBZR = new FormFile[10];
	private FormFile[] listFileCTC = new FormFile[10];
	private FormFile[] listFilePRI = new FormFile[10];
	private FormFile[] listFileMRG = new FormFile[10];
	private FormFile[] listFileCRC = new FormFile[10];
	private FormFile[] listFileFRX = new FormFile[10];
	private FormFile[] listFileCOV = new FormFile[10];	
	private List<FileInfo> attachmentQCS;
	private List<FileInfo> attachmentPMR;
	private List<FileInfo> attachmentBZR;
	private List<FileInfo> attachmentCTC;
	private List<FileInfo> attachmentPRI;
	private List<FileInfo> attachmentMRG;
	private List<FileInfo> attachmentCRC;
	private List<FileInfo> attachmentFRX;
	private List<FileInfo> attachmentCOV;
	private String file_name;	
	private String deletedAttachmentQCS;
	private String deletedAttachmentPMR;
	private String deletedAttachmentBZR;
	private String deletedAttachmentCTC;
	private String deletedAttachmentPRI;
	private String deletedAttachmentMRG;
	private String deletedAttachmentCRC;
	private String deletedAttachmentFRX;
	private String deletedAttachmentCOV;
	private String enableApproveQCS;
	private String enableApprovePMR;
	private String enableApproveBZR;
	private String enableApproveCTC;
	private String enableApprovePRI;
	private String enableApproveMRG;
	private String enableApproveCRC;
	private String enableApproveFRX;
	private String enableApproveCOV;
	private List<WF_ACTION> listApprQCS;
	private List<WF_ACTION> listApprPMR1;
	private List<WF_ACTION> listApprPMR2;
	private List<WF_ACTION> listApprBZR1;
	private List<WF_ACTION> listApprBZR2;
	private List<WF_ACTION> listApprPRI1;
	private List<WF_ACTION> listApprPRI2;
	private List<WF_ACTION> listApprCTC1;
	private List<WF_ACTION> listApprCTC2;
	private List<WF_ACTION> listApprMRG1;
	private List<WF_ACTION> listApprMRG2;
	private List<WF_ACTION> listApprMRG3;
	private List<WF_ACTION> listApprCRC1;
	private List<WF_ACTION> listApprCRC2;
	private List<WF_ACTION> listApprFRX1;
	private List<WF_ACTION> listApprFRX2;
	private List<WF_ACTION> listApprFRX3;
	private List<WF_ACTION> listApprCOV1;
	private List<WF_ACTION> listApprCOV2;
	private List<WF_ACTION> listApprCOV3;
	private String disableSectionNo;
	private String sectionGroupActive;
	private String sectionNoActive;
	private String id_wf_approval;
	private String section_no;
	private List<T_WF_SECTIONBean> sequenceGroups;
	
	
	public List<T_WF_SECTIONBean> getSequenceGroups() {
		return sequenceGroups;
	}
	public void setSequenceGroups(List<T_WF_SECTIONBean> sequenceGroups) {
		this.sequenceGroups = sequenceGroups;
	}
	public String getId_wf_approval() {
		return id_wf_approval;
	}
	public void setId_wf_approval(String id_wf_approval) {
		this.id_wf_approval = id_wf_approval;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getSectionNoActive() {
		return sectionNoActive;
	}
	public void setSectionNoActive(String sectionNoActive) {
		this.sectionNoActive = sectionNoActive;
	}
	public String getSectionGroupActive() {
		return sectionGroupActive;
	}
	public void setSectionGroupActive(String sectionGroupActive) {
		this.sectionGroupActive = sectionGroupActive;
	}
	public String getDisableSectionNo() {
		return disableSectionNo;
	}
	public void setDisableSectionNo(String disableSectionNo) {
		this.disableSectionNo = disableSectionNo;
	}
	public List<WF_ACTION> getListApprCTC1() {
		return listApprCTC1;
	}
	public void setListApprCTC1(List<WF_ACTION> listApprCTC1) {
		this.listApprCTC1 = listApprCTC1;
	}
	public List<WF_ACTION> getListApprCTC2() {
		return listApprCTC2;
	}
	public void setListApprCTC2(List<WF_ACTION> listApprCTC2) {
		this.listApprCTC2 = listApprCTC2;
	}
	public List<WF_ACTION> getListApprBZR1() {
		return listApprBZR1;
	}
	public void setListApprBZR1(List<WF_ACTION> listApprBZR1) {
		this.listApprBZR1 = listApprBZR1;
	}
	public List<WF_ACTION> getListApprBZR2() {
		return listApprBZR2;
	}
	public void setListApprBZR2(List<WF_ACTION> listApprBZR2) {
		this.listApprBZR2 = listApprBZR2;
	}
	public List<WF_ACTION> getListApprPRI1() {
		return listApprPRI1;
	}
	public void setListApprPRI1(List<WF_ACTION> listApprPRI1) {
		this.listApprPRI1 = listApprPRI1;
	}
	public List<WF_ACTION> getListApprPRI2() {
		return listApprPRI2;
	}
	public void setListApprPRI2(List<WF_ACTION> listApprPRI2) {
		this.listApprPRI2 = listApprPRI2;
	}
	public List<WF_ACTION> getListApprMRG1() {
		return listApprMRG1;
	}
	public void setListApprMRG1(List<WF_ACTION> listApprMRG1) {
		this.listApprMRG1 = listApprMRG1;
	}
	public List<WF_ACTION> getListApprMRG2() {
		return listApprMRG2;
	}
	public void setListApprMRG2(List<WF_ACTION> listApprMRG2) {
		this.listApprMRG2 = listApprMRG2;
	}
	public List<WF_ACTION> getListApprMRG3() {
		return listApprMRG3;
	}
	public void setListApprMRG3(List<WF_ACTION> listApprMRG3) {
		this.listApprMRG3 = listApprMRG3;
	}
	public List<WF_ACTION> getListApprCRC1() {
		return listApprCRC1;
	}
	public void setListApprCRC1(List<WF_ACTION> listApprCRC1) {
		this.listApprCRC1 = listApprCRC1;
	}
	public List<WF_ACTION> getListApprCRC2() {
		return listApprCRC2;
	}
	public void setListApprCRC2(List<WF_ACTION> listApprCRC2) {
		this.listApprCRC2 = listApprCRC2;
	}
	public List<WF_ACTION> getListApprFRX1() {
		return listApprFRX1;
	}
	public void setListApprFRX1(List<WF_ACTION> listApprFRX1) {
		this.listApprFRX1 = listApprFRX1;
	}
	public List<WF_ACTION> getListApprFRX2() {
		return listApprFRX2;
	}
	public void setListApprFRX2(List<WF_ACTION> listApprFRX2) {
		this.listApprFRX2 = listApprFRX2;
	}
	public List<WF_ACTION> getListApprFRX3() {
		return listApprFRX3;
	}
	public void setListApprFRX3(List<WF_ACTION> listApprFRX3) {
		this.listApprFRX3 = listApprFRX3;
	}
	public List<WF_ACTION> getListApprCOV1() {
		return listApprCOV1;
	}
	public void setListApprCOV1(List<WF_ACTION> listApprCOV1) {
		this.listApprCOV1 = listApprCOV1;
	}
	public List<WF_ACTION> getListApprCOV2() {
		return listApprCOV2;
	}
	public void setListApprCOV2(List<WF_ACTION> listApprCOV2) {
		this.listApprCOV2 = listApprCOV2;
	}
	public List<WF_ACTION> getListApprCOV3() {
		return listApprCOV3;
	}
	public void setListApprCOV3(List<WF_ACTION> listApprCOV3) {
		this.listApprCOV3 = listApprCOV3;
	}
	public List<WF_ACTION> getListApprPMR2() {
		return listApprPMR2;
	}
	public void setListApprPMR2(List<WF_ACTION> listApprPMR2) {
		this.listApprPMR2 = listApprPMR2;
	}
	public List<WF_ACTION> getListApprPMR1() {
		return listApprPMR1;
	}
	public void setListApprPMR1(List<WF_ACTION> listApprPMR1) {
		this.listApprPMR1 = listApprPMR1;
	}
	public List<WF_ACTION> getListApprQCS() {
		return listApprQCS;
	}
	public void setListApprQCS(List<WF_ACTION> listApprQCS) {
		this.listApprQCS = listApprQCS;
	}
	public String getEnableApproveQCS() {
		return enableApproveQCS;
	}
	public void setEnableApproveQCS(String enableApproveQCS) {
		this.enableApproveQCS = enableApproveQCS;
	}
	public String getEnableApprovePMR() {
		return enableApprovePMR;
	}
	public void setEnableApprovePMR(String enableApprovePMR) {
		this.enableApprovePMR = enableApprovePMR;
	}
	public String getEnableApproveBZR() {
		return enableApproveBZR;
	}
	public void setEnableApproveBZR(String enableApproveBZR) {
		this.enableApproveBZR = enableApproveBZR;
	}
	public String getEnableApproveCTC() {
		return enableApproveCTC;
	}
	public void setEnableApproveCTC(String enableApproveCTC) {
		this.enableApproveCTC = enableApproveCTC;
	}
	public String getEnableApprovePRI() {
		return enableApprovePRI;
	}
	public void setEnableApprovePRI(String enableApprovePRI) {
		this.enableApprovePRI = enableApprovePRI;
	}
	public String getEnableApproveMRG() {
		return enableApproveMRG;
	}
	public void setEnableApproveMRG(String enableApproveMRG) {
		this.enableApproveMRG = enableApproveMRG;
	}
	public String getEnableApproveCRC() {
		return enableApproveCRC;
	}
	public void setEnableApproveCRC(String enableApproveCRC) {
		this.enableApproveCRC = enableApproveCRC;
	}
	public String getEnableApproveFRX() {
		return enableApproveFRX;
	}
	public void setEnableApproveFRX(String enableApproveFRX) {
		this.enableApproveFRX = enableApproveFRX;
	}
	public String getEnableApproveCOV() {
		return enableApproveCOV;
	}
	public void setEnableApproveCOV(String enableApproveCOV) {
		this.enableApproveCOV = enableApproveCOV;
	}
	public String getDocStatusCSS() {
		return docStatusCSS;
	}
	public void setDocStatusCSS(String docStatusCSS) {
		this.docStatusCSS = docStatusCSS;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getDeletedAttachmentQCS() {
		return deletedAttachmentQCS;
	}
	public void setDeletedAttachmentQCS(String deletedAttachmentQCS) {
		this.deletedAttachmentQCS = deletedAttachmentQCS;
	}
	public String getDeletedAttachmentPMR() {
		return deletedAttachmentPMR;
	}
	public void setDeletedAttachmentPMR(String deletedAttachmentPMR) {
		this.deletedAttachmentPMR = deletedAttachmentPMR;
	}
	public String getDeletedAttachmentBZR() {
		return deletedAttachmentBZR;
	}
	public void setDeletedAttachmentBZR(String deletedAttachmentBZR) {
		this.deletedAttachmentBZR = deletedAttachmentBZR;
	}
	public String getDeletedAttachmentCTC() {
		return deletedAttachmentCTC;
	}
	public void setDeletedAttachmentCTC(String deletedAttachmentCTC) {
		this.deletedAttachmentCTC = deletedAttachmentCTC;
	}
	public String getDeletedAttachmentPRI() {
		return deletedAttachmentPRI;
	}
	public void setDeletedAttachmentPRI(String deletedAttachmentPRI) {
		this.deletedAttachmentPRI = deletedAttachmentPRI;
	}
	public String getDeletedAttachmentMRG() {
		return deletedAttachmentMRG;
	}
	public void setDeletedAttachmentMRG(String deletedAttachmentMRG) {
		this.deletedAttachmentMRG = deletedAttachmentMRG;
	}
	public String getDeletedAttachmentCRC() {
		return deletedAttachmentCRC;
	}
	public void setDeletedAttachmentCRC(String deletedAttachmentCRC) {
		this.deletedAttachmentCRC = deletedAttachmentCRC;
	}
	public String getDeletedAttachmentFRX() {
		return deletedAttachmentFRX;
	}
	public void setDeletedAttachmentFRX(String deletedAttachmentFRX) {
		this.deletedAttachmentFRX = deletedAttachmentFRX;
	}
	public String getDeletedAttachmentCOV() {
		return deletedAttachmentCOV;
	}
	public void setDeletedAttachmentCOV(String deletedAttachmentCOV) {
		this.deletedAttachmentCOV = deletedAttachmentCOV;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public List<FileInfo> getAttachmentQCS() {
		return attachmentQCS;
	}
	public void setAttachmentQCS(List<FileInfo> attachmentQCS) {
		this.attachmentQCS = attachmentQCS;
	}
	public List<FileInfo> getAttachmentPMR() {
		return attachmentPMR;
	}
	public void setAttachmentPMR(List<FileInfo> attachmentPMR) {
		this.attachmentPMR = attachmentPMR;
	}
	public List<FileInfo> getAttachmentBZR() {
		return attachmentBZR;
	}
	public void setAttachmentBZR(List<FileInfo> attachmentBZR) {
		this.attachmentBZR = attachmentBZR;
	}
	public List<FileInfo> getAttachmentCTC() {
		return attachmentCTC;
	}
	public void setAttachmentCTC(List<FileInfo> attachmentCTC) {
		this.attachmentCTC = attachmentCTC;
	}
	public List<FileInfo> getAttachmentPRI() {
		return attachmentPRI;
	}
	public void setAttachmentPRI(List<FileInfo> attachmentPRI) {
		this.attachmentPRI = attachmentPRI;
	}
	public List<FileInfo> getAttachmentMRG() {
		return attachmentMRG;
	}
	public void setAttachmentMRG(List<FileInfo> attachmentMRG) {
		this.attachmentMRG = attachmentMRG;
	}
	public List<FileInfo> getAttachmentCRC() {
		return attachmentCRC;
	}
	public void setAttachmentCRC(List<FileInfo> attachmentCRC) {
		this.attachmentCRC = attachmentCRC;
	}
	public List<FileInfo> getAttachmentFRX() {
		return attachmentFRX;
	}
	public void setAttachmentFRX(List<FileInfo> attachmentFRX) {
		this.attachmentFRX = attachmentFRX;
	}
	public List<FileInfo> getAttachmentCOV() {
		return attachmentCOV;
	}
	public void setAttachmentCOV(List<FileInfo> attachmentCOV) {
		this.attachmentCOV = attachmentCOV;
	}
	public FormFile[] getListFilePMR() {
		return listFilePMR;
	}
	public void setListFilePMR(FormFile[] listFilePMR) {
		this.listFilePMR = listFilePMR;
	}
	public FormFile[] getListFileBZR() {
		return listFileBZR;
	}
	public void setListFileBZR(FormFile[] listFileBZR) {
		this.listFileBZR = listFileBZR;
	}
	public FormFile[] getListFileCTC() {
		return listFileCTC;
	}
	public void setListFileCTC(FormFile[] listFileCTC) {
		this.listFileCTC = listFileCTC;
	}
	public FormFile[] getListFilePRI() {
		return listFilePRI;
	}
	public void setListFilePRI(FormFile[] listFilePRI) {
		this.listFilePRI = listFilePRI;
	}
	public FormFile[] getListFileMRG() {
		return listFileMRG;
	}
	public void setListFileMRG(FormFile[] listFileMRG) {
		this.listFileMRG = listFileMRG;
	}
	public FormFile[] getListFileCRC() {
		return listFileCRC;
	}
	public void setListFileCRC(FormFile[] listFileCRC) {
		this.listFileCRC = listFileCRC;
	}
	public FormFile[] getListFileFRX() {
		return listFileFRX;
	}
	public void setListFileFRX(FormFile[] listFileFRX) {
		this.listFileFRX = listFileFRX;
	}
	public FormFile[] getListFileCOV() {
		return listFileCOV;
	}
	public void setListFileCOV(FormFile[] listFileCOV) {
		this.listFileCOV = listFileCOV;
	}
	public FormFile[] getListFileQCS() {
		return listFileQCS;
	}
	public void setListFileQCS(FormFile[] listFileQCS) {
		this.listFileQCS = listFileQCS;
	}
	public String getEnableSave() {
		return enableSave;
	}
	public void setEnableSave(String enableSave) {
		this.enableSave = enableSave;
	}
	public String getEnableEdit() {
		return enableEdit;
	}
	public void setEnableEdit(String enableEdit) {
		this.enableEdit = enableEdit;
	}
	public String getEnablePMR() {
		return enablePMR;
	}
	public void setEnablePMR(String enablePMR) {
		this.enablePMR = enablePMR;
	}
	public String getEnableBZR() {
		return enableBZR;
	}
	public void setEnableBZR(String enableBZR) {
		this.enableBZR = enableBZR;
	}
	public String getEnableCTC() {
		return enableCTC;
	}
	public void setEnableCTC(String enableCTC) {
		this.enableCTC = enableCTC;
	}
	public String getEnablePRI() {
		return enablePRI;
	}
	public void setEnablePRI(String enablePRI) {
		this.enablePRI = enablePRI;
	}
	public String getEnableMRG() {
		return enableMRG;
	}
	public void setEnableMRG(String enableMRG) {
		this.enableMRG = enableMRG;
	}
	public String getEnableCRC() {
		return enableCRC;
	}
	public void setEnableCRC(String enableCRC) {
		this.enableCRC = enableCRC;
	}
	public String getEnableFRX() {
		return enableFRX;
	}
	public void setEnableFRX(String enableFRX) {
		this.enableFRX = enableFRX;
	}
	public String getEnableCOV() {
		return enableCOV;
	}
	public void setEnableCOV(String enableCOV) {
		this.enableCOV = enableCOV;
	}
	public String getEnableQCS() {
		return enableQCS;
	}
	public void setEnableQCS(String enableQCS) {
		this.enableQCS = enableQCS;
	}
	public String getEnableRejectApprove() {
		return enableRejectApprove;
	}
	public void setEnableRejectApprove(String enableRejectApprove) {
		this.enableRejectApprove = enableRejectApprove;
	}
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getEnableObtainApprovalPMR() {
		return enableObtainApprovalPMR;
	}
	public void setEnableObtainApprovalPMR(String enableObtainApprovalPMR) {
		this.enableObtainApprovalPMR = enableObtainApprovalPMR;
	}
	public String getEnableObtainApprovalBZR() {
		return enableObtainApprovalBZR;
	}
	public void setEnableObtainApprovalBZR(String enableObtainApprovalBZR) {
		this.enableObtainApprovalBZR = enableObtainApprovalBZR;
	}
	public String getEnableObtainApprovalCTC() {
		return enableObtainApprovalCTC;
	}
	public void setEnableObtainApprovalCTC(String enableObtainApprovalCTC) {
		this.enableObtainApprovalCTC = enableObtainApprovalCTC;
	}
	public String getEnableObtainApprovalPRI() {
		return enableObtainApprovalPRI;
	}
	public void setEnableObtainApprovalPRI(String enableObtainApprovalPRI) {
		this.enableObtainApprovalPRI = enableObtainApprovalPRI;
	}
	public String getEnableObtainApprovalMRG() {
		return enableObtainApprovalMRG;
	}
	public void setEnableObtainApprovalMRG(String enableObtainApprovalMRG) {
		this.enableObtainApprovalMRG = enableObtainApprovalMRG;
	}
	public String getEnableObtainApprovalCRC() {
		return enableObtainApprovalCRC;
	}
	public void setEnableObtainApprovalCRC(String enableObtainApprovalCRC) {
		this.enableObtainApprovalCRC = enableObtainApprovalCRC;
	}
	public String getEnableObtainApprovalFRX() {
		return enableObtainApprovalFRX;
	}
	public void setEnableObtainApprovalFRX(String enableObtainApprovalFRX) {
		this.enableObtainApprovalFRX = enableObtainApprovalFRX;
	}
	public String getEnableObtainApprovalCOV() {
		return enableObtainApprovalCOV;
	}
	public void setEnableObtainApprovalCOV(String enableObtainApprovalCOV) {
		this.enableObtainApprovalCOV = enableObtainApprovalCOV;
	}
	public String getShowPopalt() {
		return showPopalt;
	}
	public void setShowPopalt(String showPopalt) {
		this.showPopalt = showPopalt;
	}
	public String getEnableDelete() {
		return enableDelete;
	}
	public void setEnableDelete(String enableDelete) {
		this.enableDelete = enableDelete;
	}
	public String getObt_appr_save() {
		return obt_appr_save;
	}
	public void setObt_appr_save(String obt_appr_save) {
		this.obt_appr_save = obt_appr_save;
	}
	public List<QCSDetail> getQcsDetail() {
		if(qcsDetail == null){
			return new ArrayList<QCSDetail>();
		}
		return qcsDetail;
	}
	public void setQcsDetail(List<QCSDetail> qcsDetail) {
		this.qcsDetail = qcsDetail;
	}
	
	public String getEnableObtainApproval() {
		return enableObtainApproval;
	}
	public void setEnableObtainApproval(String enableObtainApproval) {
		this.enableObtainApproval = enableObtainApproval;
	}
	
	public String getClickEvent() {
		return clickEvent;
	}
	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}
	public String getCust_mode() {
		return cust_mode;
	}
	public void setCust_mode(String cust_mode) {
		this.cust_mode = cust_mode;
	}
	public String[] getTotal() {
		return total;
	}
	public void setTotal(String[] total) {
		this.total = total;
	}
	public String[] getCapacity() {
		return capacity;
	}
	public void setCapacity(String[] capacity) {
		this.capacity = capacity;
	}
			
	public String[] getNrc() {
		return nrc;
	}
	public void setNrc(String[] nrc) {
		this.nrc = nrc;
	}
	public String[] getMrc() {
		return mrc;
	}
	public void setMrc(String[] mrc) {
		this.mrc = mrc;
	}
	public String[] getTerm() {
		return term;
	}
	public void setTerm(String[] term) {
		this.term = term;
	}
	public String[] getTariff() {
		return tariff;
	}
	public void setTariff(String[] tariff) {
		this.tariff = tariff;
	}
	public String[] getDisc() {
		return disc;
	}
	public void setDisc(String[] disc) {
		this.disc = disc;
	}
	public String getSection_no_cov() {
		return section_no_cov;
	}
	public void setSection_no_cov(String section_no_cov) {
		this.section_no_cov = section_no_cov;
	}
	public String getRemarks_cov() {
		return remarks_cov;
	}
	public void setRemarks_cov(String remarks_cov) {
		this.remarks_cov = remarks_cov;
	}
	public String getSection_no_frx() {
		return section_no_frx;
	}
	public void setSection_no_frx(String section_no_frx) {
		this.section_no_frx = section_no_frx;
	}
	public String getRemarks_frx() {
		return remarks_frx;
	}
	public void setRemarks_frx(String remarks_frx) {
		this.remarks_frx = remarks_frx;
	}
	public String getValue_frx() {
		return value_frx;
	}
	public void setValue_frx(String value_frx) {
		this.value_frx = value_frx;
	}
	public String getSection_no_crc() {
		return section_no_crc;
	}
	public void setSection_no_crc(String section_no_crc) {
		this.section_no_crc = section_no_crc;
	}
	public String getRemarks_crc() {
		return remarks_crc;
	}
	public void setRemarks_crc(String remarks_crc) {
		this.remarks_crc = remarks_crc;
	}
	public String getSection_no_mrg() {
		return section_no_mrg;
	}
	public void setSection_no_mrg(String section_no_mrg) {
		this.section_no_mrg = section_no_mrg;
	}
	public String getRemarks_mrg() {
		return remarks_mrg;
	}
	public void setRemarks_mrg(String remarks_mrg) {
		this.remarks_mrg = remarks_mrg;
	}
	public String getSection_no_pri() {
		return section_no_pri;
	}
	public void setSection_no_pri(String section_no_pri) {
		this.section_no_pri = section_no_pri;
	}
	public String getRemarks_pri() {
		return remarks_pri;
	}
	public void setRemarks_pri(String remarks_pri) {
		this.remarks_pri = remarks_pri;
	}
	public String getSection_no_ctc() {
		return section_no_ctc;
	}
	public void setSection_no_ctc(String section_no_ctc) {
		this.section_no_ctc = section_no_ctc;
	}
	public String getRemarks_ctc() {
		return remarks_ctc;
	}
	public void setRemarks_ctc(String remarks_ctc) {
		this.remarks_ctc = remarks_ctc;
	}
	public String getRemarks_bzr() {
		return remarks_bzr;
	}
	public void setRemarks_bzr(String remarks_bzr) {
		this.remarks_bzr = remarks_bzr;
	}
	public String getSection_no_bzr() {
		return section_no_bzr;
	}
	public void setSection_no_bzr(String section_no_bzr) {
		this.section_no_bzr = section_no_bzr;
	}
	public String getRemarks_pmr() {
		return remarks_pmr;
	}
	public void setRemarks_pmr(String remarks_pmr) {
		this.remarks_pmr = remarks_pmr;
	}
	public String getSection_no_pmr() {
		return section_no_pmr;
	}
	public void setSection_no_pmr(String section_no_pmr) {
		this.section_no_pmr = section_no_pmr;
	}
	public String getId_ref_s01() {
		return id_ref_s01;
	}
	public void setId_ref_s01(String id_ref_s01) {
		this.id_ref_s01 = id_ref_s01;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public List<QCSHeader> getQcsInfos() {
		if(qcsInfos == null){
			return new ArrayList<QCSHeader>();
		}
		return qcsInfos;
	}
	public void setQcsInfos(List<QCSHeader> qcsInfos) {
		this.qcsInfos = qcsInfos;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String [] getSvc_grp() {
		return svc_grp;
	}
	public void setSvc_grp(String [] svc_grp) {
		this.svc_grp = svc_grp;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getId_cust() {
		return id_cust;
	}
	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}
	public String getId_conslt() {
		return id_conslt;
	}
	public void setId_conslt(String id_conslt) {
		this.id_conslt = id_conslt;
	}
	public String getId_quo() {
		return id_quo;
	}
	public void setId_quo(String id_quo) {
		this.id_quo = id_quo;
	}
	public String getReq_user() {
		return req_user;
	}
	public void setReq_user(String req_user) {
		this.req_user = req_user;
	}
	public String getDate_req() {
		return date_req;
	}
	public void setDate_req(String date_req) {
		this.date_req = date_req;
	}
	public String getCtterm() {
		return ctterm;
	}
	public void setCtterm(String ctterm) {
		this.ctterm = ctterm;
	}
	public String getCtterm3_day() {
		return ctterm3_day;
	}
	public void setCtterm3_day(String ctterm3_day) {
		this.ctterm3_day = ctterm3_day;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getId_status() {
		return id_status;
	}
	public void setId_status(String id_status) {
		this.id_status = id_status;
	}
	public String getIs_closed() {
		return is_closed;
	}
	public void setIs_closed(String is_closed) {
		this.is_closed = is_closed;
	}
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}

}