/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Quotation Control Sheet
 * SERVICE NAME   : Quotation Control Sheet
 * FUNCTION       : Quotation Control BLogic
 * FILE NAME      : Q_QCSR04BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 Tuyen Fixed for error of checking style
 * 
**********************************************************/
package nttdm.bsys.q_qcs.blogic;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.Globals;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.T_WF_APPROVAL;
import nttdm.bsys.common.bean.T_WF_SECTIONBean;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_alt.bean.FileInfo;
import nttdm.bsys.q_qcs.dto.Q_QCSR04Input;
import nttdm.bsys.q_qcs.dto.Q_QCSR04Output;
import nttdm.bsys.q_qcs.bean.QCSDetail;
import nttdm.bsys.q_qcs.bean.QCSHeader;
import nttdm.bsys.q_qcs.bean.QCSSection;
import nttdm.bsys.q_qcs.bean.Qcs_Approval;
import nttdm.bsys.q_qcs.bean.WF_ACTION;
import nttdm.bsys.q_qcs.bean.WF_ACTION_SECTION;
import nttdm.bsys.q_qcs.bean.WF_DOC;
import nttdm.bsys.q_qcs.blogic.AbstractQ_QCSR04BLogic;

/**
 * QCS Screen S004 BLogic
 * <ul>
 * <li>QCS Screen S004 BLogic
 * </ul>
 * @author  NTTData Vietnam
 * @version 1.1 
 */
public class Q_QCSR04BLogic extends AbstractQ_QCSR04BLogic {
	
	
	private static final String BLOGIC_RESULT_STR_SAVE_EDIT = "save_edit";
	private static final String BLOGIC_RESULT_STR_DELETE_EXIT = "delete_exit";
	private static final String SAVE_SUCCESSFUL_MSG = "info.ERR2SC003";
	private static final String DELETE_SUCCESSFUL_MSG = "info.ERR2SC005";
	
	private static final String DS0Class = "DS0Class";
	private static final String DS1Class = "DS1Class";
	private static final String DS2Class = "DS2Class";
	private static final String DS3Class = "DS3Class";
	private static final String DS4Class = "DS4Class";
	private static final String DS5Class = "DS5Class";
	private static final String AS0Class = "AS0Class";
	private static final String AS1Class = "AS1Class";
	private static final String AS2Class = "AS2Class";
	private static final String AS3Class = "AS3Class";
	private static final String AS4Class = "AS4Class";
	private static final String AS5Class = "AS5Class";
	private static final String AS6Class = "AS6Class";
	private static final String AS7Class = "AS7Class";
	
	public BLogicResult execute(Q_QCSR04Input param) {
		BLogicResult result = new BLogicResult();
		Q_QCSR04Output output = new Q_QCSR04Output();
		BLogicMessages messages = new BLogicMessages();
		int clickEvent = 0;
		String idStatus = "";
		if(param.getClickEvent() != null){
			clickEvent = Integer.parseInt(param.getClickEvent());
		}
		String id_ref = param.getId_ref();
		if(id_ref != null){
			id_ref = id_ref.trim();
		}
		String id_login = param.getUvo().getId_user();
		
		String enableRejectApprove = "0";
		output.setEnablePMR("1");
		output.setEnableBZR("1");
		output.setEnableCTC("1");
		output.setEnablePRI("1");
		output.setEnableMRG("1");
		output.setEnableCRC("1");
		output.setEnableFRX("1");
		output.setEnableCOV("1");
		
		//Enable or Disable Obtain Approval of section group
		output.setEnableObtainApprovalPMR("0");
		output.setEnableObtainApprovalBZR("0");
		output.setEnableObtainApprovalCTC("0");
		output.setEnableObtainApprovalPRI("0");
		output.setEnableObtainApprovalMRG("0");
		output.setEnableObtainApprovalCRC("0");
		output.setEnableObtainApprovalFRX("0");
		output.setEnableObtainApprovalCOV("0");
		
		//Get information
		if(clickEvent == 3 || clickEvent == 5 || 
				clickEvent == 6 || clickEvent == 7
				|| clickEvent == 8 || clickEvent == 9|| clickEvent == 10
				|| clickEvent == 11 || clickEvent == 12 || clickEvent == 13
				|| clickEvent == 14){
			//Get qcs header
			QCSHeader header = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_SELECT_QCS_HEADER,id_ref , QCSHeader.class);
			if(header != null){
				idStatus = header.getId_status();
				output.setId_ref(header.getId_ref().trim());
				output.setId_quo(header.getId_quo());
				output.setId_cust(header.getId_cust());
				output.setReq_user(header.getReq_user());
				output.setDate_req(header.getDate_req());
				output.setId_conslt(header.getId_conslt());
				output.setCtterm(header.getCtterm());
				if(header.getCtterm().equals("3")){
					output.setCtterm3_day(header.getCtterm3_day());
				}
				output.setDeposit(header.getDeposit());				
			}
			//Get qcs detail
			List<QCSDetail> qcsDetail = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_QCS_DETAIL, id_ref);
			output.setQcsDetail(qcsDetail);
			//Get qcs section
			List<QCSSection> qcsSection = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_QCS_SECTION, id_ref);
			for(QCSSection section : qcsSection){
				if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_PMR_VALUE)){
					output.setSection_no_pmr(section.getSection_no());
					output.setRemarks_pmr(section.getRemarks());
				}else if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_BZR_VALUE)){
					output.setSection_no_bzr(section.getSection_no());
					output.setRemarks_bzr(section.getRemarks());
				}else if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_CTC_VALUE)){
					output.setSection_no_ctc(section.getSection_no());
					output.setRemarks_ctc(section.getRemarks());
				}else if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_PRI_VALUE)){
					output.setSection_no_pri(section.getSection_no());
					output.setRemarks_pri(section.getRemarks());
				}else if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_MRG_VALUE)){
					output.setSection_no_mrg(section.getSection_no());
					output.setRemarks_mrg(section.getRemarks());
				}else if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_CRC_VALUE)){
					output.setSection_no_crc(section.getSection_no());
					output.setRemarks_crc(section.getRemarks());
				}else if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_FRX_VALUE)){
					output.setSection_no_frx(section.getSection_no());
					output.setRemarks_frx(section.getRemarks());
					if(section.getSection_no().equals(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE)){
						output.setValue_frx(BillingSystemConstants.FRX_USD_VALUE);
					}else{
						output.setValue_frx(section.getValue());
					}
				}else if(section.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_COV_VALUE)){
					output.setSection_no_cov(section.getSection_no());
					output.setRemarks_cov(section.getRemarks());
				}
			}
			//Get attachment QCS
			WF_DOC wf_doc = new WF_DOC();
			wf_doc.setDoc_type(BillingSystemConstants.DOCTYPE_QUOTATION);
			wf_doc.setId_ref(id_ref);
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_QCS_VALUE);
			List<FileInfo> attachmentQCS = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentQCS = attachmentQCS != null ? attachmentQCS : new ArrayList<FileInfo>();
			//Get attachment PMR
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_PMR_VALUE);
			List<FileInfo> attachmentPMR = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentPMR = attachmentPMR != null ? attachmentPMR : new ArrayList<FileInfo>();
			//Get attachment BZR
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_BZR_VALUE);
			List<FileInfo> attachmentBZR = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentBZR = attachmentBZR != null ? attachmentBZR : new ArrayList<FileInfo>();
			//Get attachment CTC
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_CTC_VALUE);
			List<FileInfo> attachmentCTC = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentCTC = attachmentCTC != null ? attachmentCTC : new ArrayList<FileInfo>();
			//Get attachment PRI
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_PRI_VALUE);
			List<FileInfo> attachmentPRI = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentPRI = attachmentPRI != null ? attachmentPRI : new ArrayList<FileInfo>();
			//Get attachment MRG
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_MRG_VALUE);
			List<FileInfo> attachmentMRG = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentMRG = attachmentMRG != null ? attachmentMRG : new ArrayList<FileInfo>();
			//Get attachment CRC
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_CRC_VALUE);
			List<FileInfo> attachmentCRC = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentCRC = attachmentCRC != null ? attachmentCRC : new ArrayList<FileInfo>();
			//Get attachment FRX
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_FRX_VALUE);
			List<FileInfo> attachmentFRX = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentFRX = attachmentFRX != null ? attachmentFRX : new ArrayList<FileInfo>();
			//Get attachment COV
			wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_COV_VALUE);
			List<FileInfo> attachmentCOV = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentCOV = attachmentCOV != null ? attachmentCOV : new ArrayList<FileInfo>();
			
			output.setAttachmentQCS(attachmentQCS);
			output.setAttachmentPMR(attachmentPMR);
			output.setAttachmentBZR(attachmentBZR);
			output.setAttachmentCTC(attachmentCTC);
			output.setAttachmentPRI(attachmentPRI);
			output.setAttachmentMRG(attachmentMRG);
			output.setAttachmentCRC(attachmentCRC);
			output.setAttachmentFRX(attachmentFRX);
			output.setAttachmentCOV(attachmentCOV);
			
			//Enable or Disable Obtain Approval button
			if(header != null){
				if(header.getId_status().equals(BillingSystemConstants.DOCUMENT_STATUS_DRAFTED_VALUE)){
					output.setEnableObtainApproval("1");
				}else{
					output.setEnableObtainApproval("0");
				}	
			}else{
				output.setEnableObtainApproval("0");
			}
		}
		//Enable radio box which is section_no
		output.setDisableSectionNo("false");
		
		//Set status of Approve and Reject button
		output.setEnableApproveQCS("0");
		
		//Get Approval status
		List<WF_ACTION> listApprQCS =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprPMR1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprPMR2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprBZR1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprBZR2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprCTC1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprCTC2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprPRI1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprPRI2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprMRG1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprMRG2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprMRG3 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprCRC1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprCRC2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprFRX1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprFRX2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprFRX3 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprCOV1 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprCOV2 =new ArrayList<WF_ACTION>();
		List<WF_ACTION> listApprCOV3 =new ArrayList<WF_ACTION>();
		
		WF_ACTION_SECTION wf_action_section = new WF_ACTION_SECTION();
		wf_action_section.setId_ref(id_ref);
		wf_action_section.setAction_type(BillingSystemConstants.ACTION_TYPE_AA);
		wf_action_section.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
		//wf_action_section.setSection_group(BillingSystemConstants.SECTION_GROUP_QUO_VALUE);
		//wf_action_section.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);		
		List<WF_ACTION> listAppr = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_NAME_APPROVER, wf_action_section);				
		for(WF_ACTION wf_action : listAppr){
			String section_no = wf_action.getSection_no();
			String[] status = this.determineApprStatusCssClass(wf_action.getAppr_status());
			wf_action.setAppr_status_css(status[0]);
			wf_action.setAppr_status(status[1]);
			if(section_no.equals(BillingSystemConstants.SECTION_NO_QCS_VALUE)){				
				listApprQCS.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PMR_STANDARD_VALUE)){
				listApprPMR1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PMR_NON_STANDARD_VALUE)){
				listApprPMR2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_BZR_STANDARD_VALUE)){
				listApprBZR1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_BZR_NON_STANDARD_VALUE)){
				listApprBZR2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CTC_STANDARD_VALUE)){
				listApprCTC1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CTC_NON_STANDARD_VALUE)){
				listApprCTC2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PRI_STANDARD_VALUE)){
				listApprPRI1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PRI_NON_STANDARD_VALUE)){
				listApprPRI2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_MRG_STANDARD_VALUE)){
				listApprMRG1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_MRG_NON_STANDARD_Q052_VALUE)){
				listApprMRG2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_MRG_NON_STANDARD_Q053_VALUE)){
				listApprMRG3.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CRC_STANDARD_VALUE)){
				listApprCRC1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CRC_NON_STANDARD_VALUE)){
				listApprCRC2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE)){
				listApprFRX1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_FRX_NON_STANDARD_Q072_VALUE)){
				listApprFRX2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_FRX_NON_STANDARD_Q073_VALUE)){
				listApprFRX3.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_COV_STANDARD_VALUE)){
				listApprCOV1.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_COV_NON_STANDARD_Q082_VALUE)){
				listApprCOV2.add(wf_action);
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_COV_NON_STANDARD_Q083_VALUE)){
				listApprCOV3.add(wf_action);
			}
		}
		//Get sequence of displaying section groups
		List<T_WF_SECTIONBean> sequenceGroups = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_SELECT_SEQUENCE_SECT_GROUP, BillingSystemConstants.ID_SCREEN_QCS);
		if(sequenceGroups == null){
			sequenceGroups = new ArrayList<T_WF_SECTIONBean>();
		}
		output.setSequenceGroups(sequenceGroups);
		
		switch(clickEvent){
		case 1:	
			output.setCtterm(BillingSystemConstants.CREDIT_TERM_COD_VALUE);
			output.setDeposit(BillingSystemConstants.DEPOSIT_2MONTHS_VALUE);
			output.setSection_no_pmr(BillingSystemConstants.SECTION_NO_PMR_STANDARD_VALUE);
			output.setSection_no_bzr(BillingSystemConstants.SECTION_NO_BZR_STANDARD_VALUE);
			output.setSection_no_ctc(BillingSystemConstants.SECTION_NO_CTC_STANDARD_VALUE);
			output.setSection_no_pri(BillingSystemConstants.SECTION_NO_PRI_STANDARD_VALUE);
			output.setSection_no_mrg(BillingSystemConstants.SECTION_NO_MRG_STANDARD_VALUE);
			output.setSection_no_crc(BillingSystemConstants.SECTION_NO_CRC_STANDARD_VALUE);
			output.setSection_no_frx(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE);
			output.setSection_no_cov(BillingSystemConstants.SECTION_NO_COV_STANDARD_VALUE);
			output.setValue_frx(BillingSystemConstants.FRX_USD_VALUE);
			output.setClickEvent(param.getClickEvent());	
			
			output.setAttachmentQCS(new ArrayList<FileInfo>());
			output.setAttachmentPMR(new ArrayList<FileInfo>());
			output.setAttachmentBZR(new ArrayList<FileInfo>());
			output.setAttachmentCTC(new ArrayList<FileInfo>());
			output.setAttachmentPRI(new ArrayList<FileInfo>());
			output.setAttachmentMRG(new ArrayList<FileInfo>());
			output.setAttachmentCRC(new ArrayList<FileInfo>());
			output.setAttachmentFRX(new ArrayList<FileInfo>());
			output.setAttachmentCOV(new ArrayList<FileInfo>());
			
			output.setEnableObtainApproval("0");
			output.setEnableDelete("0");
			output.setEnableSave("1");
			output.setEnableEdit("0");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			param.setShowPopalt("");
			break;
		case 2://After updating
			result.setResultString(BLOGIC_RESULT_STR_SAVE_EDIT);
			messages.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_SUCCESSFUL_MSG));
			break;
		case 3://After user click Obtain approval in QCS section group			
			output.setClickEvent("7");	
			output.setEnableSave("1");
			output.setEnableEdit("0");
			output.setEnableDelete("1");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 4://After deleting QCS
			messages.add(Globals.MESSAGE_KEY,new BLogicMessage(DELETE_SUCCESSFUL_MSG));
			result.setResultString(BLOGIC_RESULT_STR_DELETE_EXIT);
			break;
		case 5://After user click Obtain approval in other section groups		
			output.setEnableDelete("1");
			output.setEnableSave("1");
			output.setEnableEdit("0");
			output.setClickEvent("5");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 6://View mode
			output.setEnablePMR("0");
			output.setEnableBZR("0");
			output.setEnableCTC("0");
			output.setEnablePRI("0");
			output.setEnableMRG("0");
			output.setEnableCRC("0");
			output.setEnableFRX("0");
			output.setEnableCOV("0");
			output.setEnableSave("0");			
			output.setEnableDelete("0");
			
			if (idStatus != null && (idStatus.equals(BillingSystemConstants.DOCUMENT_STATUS_DRAFTED_VALUE) || 
					idStatus.equals(BillingSystemConstants.DOCUMENT_STATUS_OPEN_VALUE) || 
					idStatus.equals(BillingSystemConstants.DOCUMENT_STATUS_APPROVAL_IN_PROGRESS_VALUE))){
				output.setEnableEdit("1");
			}else{
				output.setEnableEdit("0");
			}
			output.setClickEvent("6");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 7://user Click Edit button
			output.setEnableSave("1");
			output.setEnableEdit("0");
			output.setEnableDelete("1");
			output.setClickEvent("7");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 8://User click link from Q_QCSS01 or user click Save button from Approval screen
			output.setEnableApproveQCS("1");
			output.setEnableSave("1");
			output.setEnableEdit("0");
			output.setEnableDelete("1");
			output.setClickEvent("8");
			String section_no = param.getSection_no();						
			WF_TABLEBean wf_table_bean = new WF_TABLEBean();
			wf_table_bean.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
			wf_table_bean.setSection_no(section_no);
			wf_table_bean.setId_tfr_user(id_login);
			List<WF_ACTION> wf_temp = null;
			
			if(section_no.equals(BillingSystemConstants.SECTION_NO_QCS_VALUE)){	
				wf_temp = listApprQCS;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PMR_STANDARD_VALUE)){
				wf_temp = listApprPMR1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PMR_NON_STANDARD_VALUE)){
				wf_temp = listApprPMR2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_BZR_STANDARD_VALUE)){
				wf_temp = listApprBZR1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_BZR_NON_STANDARD_VALUE)){
				wf_temp = listApprBZR2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CTC_STANDARD_VALUE)){
				wf_temp = listApprCTC1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CTC_NON_STANDARD_VALUE)){
				wf_temp = listApprCTC2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PRI_STANDARD_VALUE)){
				wf_temp = listApprPRI1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_PRI_NON_STANDARD_VALUE)){
				wf_temp = listApprPRI2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_MRG_STANDARD_VALUE)){
				wf_temp = listApprMRG1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_MRG_NON_STANDARD_Q052_VALUE)){
				wf_temp = listApprMRG2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_MRG_NON_STANDARD_Q053_VALUE)){
				wf_temp = listApprMRG3;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CRC_STANDARD_VALUE)){
				wf_temp = listApprCRC1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_CRC_NON_STANDARD_VALUE)){
				wf_temp = listApprCRC2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE)){
				wf_temp = listApprFRX1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_FRX_NON_STANDARD_Q072_VALUE)){
				wf_temp = listApprFRX2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_FRX_NON_STANDARD_Q073_VALUE)){
				wf_temp = listApprFRX3;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_COV_STANDARD_VALUE)){
				wf_temp = listApprCOV1;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_COV_NON_STANDARD_Q082_VALUE)){
				wf_temp = listApprCOV2;
			}else if(section_no.equals(BillingSystemConstants.SECTION_NO_COV_NON_STANDARD_Q083_VALUE)){
				wf_temp = listApprCOV3;
			}
			for (WF_ACTION wf_action : wf_temp){
				if(wf_action.getPic().equals(id_login)){
					break;
				}else{						
					wf_table_bean.setId_user(wf_action.getPic());
					Integer countTFR = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_COUNT_TRANSFER_USER, wf_table_bean, Integer.class);
					if(countTFR != null && countTFR.intValue() > 0){
						wf_action.setPic(id_login);
						break;
					}
				}
			}	
			//Get approve status to enable or disable
			T_WF_APPROVAL t_wf_approval = new T_WF_APPROVAL();
			t_wf_approval.setId_ref(id_ref);
			t_wf_approval.setSection_group(param.getSection_group());
			t_wf_approval.setSection_no(param.getSection_no());
			t_wf_approval.setId_approver(id_login);
			String status = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_SELECT_STATUS_APPROVER, t_wf_approval, String.class);
			if(status !=  null && !status.equals(BillingSystemConstants.APPROVAL_STATUS_PENDING_VALUE)){
				output.setEnablePMR("0");
				output.setEnableBZR("0");
				output.setEnableCTC("0");
				output.setEnablePRI("0");
				output.setEnableMRG("0");
				output.setEnableCRC("0");
				output.setEnableFRX("0");
				output.setEnableCOV("0");
				output.setEnableSave("0");			
				output.setEnableDelete("0");
			}
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 9 ://Approve QCS group
			output.setEnableApproveQCS("0");
			output.setEnableSave("0");
			output.setEnableEdit("0");
			output.setEnableDelete("0");
			output.setClickEvent("9");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 10 ://Reject QCS group
			output.setEnableApproveQCS("0");
			output.setEnableSave("0");
			output.setEnableEdit("0");
			output.setEnableDelete("0");
			output.setClickEvent("10");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 11://Click Save button from Section approval mode
			output.setEnableSave("1");
			output.setEnableEdit("2");//Invisible
			output.setEnableDelete("2");//Invisible
			output.setClickEvent("11");
			
			String section_no_2 = param.getSection_no();						
			WF_TABLEBean wf_table_bean_2 = new WF_TABLEBean();
			wf_table_bean_2.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
			wf_table_bean_2.setSection_no(section_no_2);
			wf_table_bean_2.setId_tfr_user(id_login);
			List<WF_ACTION> wf_temp_2 = null;
			
			if(section_no_2.equals(BillingSystemConstants.SECTION_NO_QCS_VALUE)){	
				wf_temp_2 = listApprQCS;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_PMR_STANDARD_VALUE)){
				wf_temp_2 = listApprPMR1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_PMR_NON_STANDARD_VALUE)){
				wf_temp_2 = listApprPMR2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_BZR_STANDARD_VALUE)){
				wf_temp_2 = listApprBZR1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_BZR_NON_STANDARD_VALUE)){
				wf_temp_2 = listApprBZR2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_CTC_STANDARD_VALUE)){
				wf_temp_2 = listApprCTC1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_CTC_NON_STANDARD_VALUE)){
				wf_temp_2 = listApprCTC2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_PRI_STANDARD_VALUE)){
				wf_temp_2 = listApprPRI1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_PRI_NON_STANDARD_VALUE)){
				wf_temp_2 = listApprPRI2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_MRG_STANDARD_VALUE)){
				wf_temp_2 = listApprMRG1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_MRG_NON_STANDARD_Q052_VALUE)){
				wf_temp_2 = listApprMRG2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_MRG_NON_STANDARD_Q053_VALUE)){
				wf_temp_2 = listApprMRG3;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_CRC_STANDARD_VALUE)){
				wf_temp_2 = listApprCRC1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_CRC_NON_STANDARD_VALUE)){
				wf_temp_2 = listApprCRC2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE)){
				wf_temp_2 = listApprFRX1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_FRX_NON_STANDARD_Q072_VALUE)){
				wf_temp_2 = listApprFRX2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_FRX_NON_STANDARD_Q073_VALUE)){
				wf_temp_2 = listApprFRX3;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_COV_STANDARD_VALUE)){
				wf_temp_2 = listApprCOV1;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_COV_NON_STANDARD_Q082_VALUE)){
				wf_temp_2 = listApprCOV2;
			}else if(section_no_2.equals(BillingSystemConstants.SECTION_NO_COV_NON_STANDARD_Q083_VALUE)){
				wf_temp_2 = listApprCOV3;
			}
			for (WF_ACTION wf_action : wf_temp_2){
				if(wf_action.getPic().equals(id_login)){
					break;
				}else{						
					wf_table_bean_2.setId_user(wf_action.getPic());
					Integer countTFR = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_COUNT_TRANSFER_USER, wf_table_bean_2, Integer.class);
					if(countTFR != null && countTFR > 0){
						wf_action.setPic(id_login);
						break;
					}
				}
			}	
			enableRejectApprove ="1";
			this.determineSectionGroupToDisplay(sequenceGroups,id_ref,param.getSection_no(),id_login,param.getSection_group(),output);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 12://Approve section group
			output.setEnableSave("0");
			output.setEnableEdit("2");//Invisible
			output.setEnableDelete("2");//Invisible	
			output.setClickEvent("12");
			this.determineSectionGroupToDisplayAfterApprove(sequenceGroups,param.getSection_group(),output);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 13://Reject section group
			output.setEnableSave("0");
			output.setEnableEdit("2");//Invisible
			output.setEnableDelete("2");//Invisible	
			output.setClickEvent("13");
			this.determineSectionGroupToDisplayAfterApprove(sequenceGroups,param.getSection_group(),output);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
		case 14://X mode
			output.setEnablePMR("0");
			output.setEnableBZR("0");
			output.setEnableCTC("0");
			output.setEnablePRI("0");
			output.setEnableMRG("0");
			output.setEnableCRC("0");
			output.setEnableFRX("0");
			output.setEnableCOV("0");
			output.setEnableSave("0");			
			output.setEnableDelete("0");
			output.setEnableEdit("0");
			output.setClickEvent("15");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			break;
			default:
				break;
		}
		output.setListApprQCS(listApprQCS);
		output.setListApprPMR1(listApprPMR1);
		output.setListApprPMR2(listApprPMR2);
		output.setListApprBZR1(listApprBZR1);
		output.setListApprBZR2(listApprBZR2);
		output.setListApprCTC1(listApprCTC1);
		output.setListApprCTC2(listApprCTC2);
		output.setListApprPRI1(listApprPRI1);
		output.setListApprPRI2(listApprPRI2);
		output.setListApprMRG1(listApprMRG1);
		output.setListApprMRG2(listApprMRG2);
		output.setListApprMRG3(listApprMRG3);
		output.setListApprCRC1(listApprCRC1);
		output.setListApprCRC2(listApprCRC2);
		output.setListApprFRX1(listApprFRX1);
		output.setListApprFRX2(listApprFRX2);
		output.setListApprFRX3(listApprFRX3);
		output.setListApprCOV1(listApprCOV1);
		output.setListApprCOV2(listApprCOV2);
		output.setListApprCOV3(listApprCOV3);	
		
		//Get Document status
		QCSHeader header = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_GET_STATUS, param.getId_ref(), QCSHeader.class);
		String id_status = "";
		if(header != null){
			id_status = header.getId_status();
		}
		if(id_status.equals("") || id_status.equals(BillingSystemConstants.DOCUMENT_STATUS_DRAFTED_VALUE)){
			output.setDocStatusCSS(DS0Class);
			output.setDocStatus(BillingSystemConstants.DOCUMENT_STATUS_DRAFTED);
		}else if(id_status.equals(BillingSystemConstants.DOCUMENT_STATUS_OPEN_VALUE)){
			output.setDocStatusCSS(DS1Class);
			output.setDocStatus(BillingSystemConstants.DOCUMENT_STATUS_OPEN);
		}else if(id_status.equals(BillingSystemConstants.DOCUMENT_STATUS_APPROVAL_IN_PROGRESS_VALUE)){
			output.setDocStatusCSS(DS2Class);
			output.setDocStatus(BillingSystemConstants.DOCUMENT_STATUS_APPROVAL_IN_PROGRESS);
		}else if(id_status.equals(BillingSystemConstants.DOCUMENT_STATUS_COMPLETED_VALUE)){
			output.setDocStatusCSS(DS3Class);
			output.setDocStatus(BillingSystemConstants.DOCUMENT_STATUS_COMPLETED);
		}else if(id_status.equals(BillingSystemConstants.DOCUMENT_STATUS_REJECTED_VALUE)){
			output.setDocStatusCSS(DS4Class);
			output.setDocStatus(BillingSystemConstants.DOCUMENT_STATUS_REJECTED);
		}else if(id_status.equals(BillingSystemConstants.DOCUMENT_STATUS_CANCELLED_VALUE)){
			output.setDocStatusCSS(DS5Class);
			output.setDocStatus(BillingSystemConstants.DOCUMENT_STATUS_CANCELLED);
		}
		
		
		//Check condition APPR_STATUS=AS3 and ID_STATUS=DS2
		Qcs_Approval qcs_approval = new Qcs_Approval();
		qcs_approval.setId_ref(param.getId_ref());
		qcs_approval.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE);
		qcs_approval.setId_status(BillingSystemConstants.DOCUMENT_STATUS_APPROVAL_IN_PROGRESS_VALUE);
		T_WF_APPROVAL wl_approval = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_CHECK_APPR_STATUS, qcs_approval, T_WF_APPROVAL.class);
		if(wl_approval != null){
			String sect_group = wl_approval.getSection_group();
			if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_PMR_VALUE)){
				output.setEnableObtainApprovalPMR("1");
			}else if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_BZR_VALUE)){
				output.setEnableObtainApprovalBZR("1");
			}else if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_CTC_VALUE)){
				output.setEnableObtainApprovalCTC("1");
			}else if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_PRI_VALUE)){
				output.setEnableObtainApprovalPRI("1");
			}else if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_MRG_VALUE)){
				output.setEnableObtainApprovalMRG("1");
			}else if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_CRC_VALUE)){
				output.setEnableObtainApprovalCRC("1");
			}else if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_FRX_VALUE)){
				output.setEnableObtainApprovalFRX("1");
			}else if(sect_group.equals(BillingSystemConstants.SECTION_GROUP_COV_VALUE)){
				output.setEnableObtainApprovalCOV("1");
			}
		}
		//Check to enable or disable QCS portion
		T_WF_APPROVAL qcs_appr = new T_WF_APPROVAL();
		qcs_appr.setId_ref(id_ref);
		qcs_appr.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_APPROVED_VALUE);
		qcs_appr.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);
		Integer countQcs = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_COUNT_QCS_APPR, qcs_appr, Integer.class);
		if(countQcs != null && countQcs.intValue() == 0){
			output.setEnableQCS("1");
		}else{
			output.setEnableQCS("0");
		}
		if(clickEvent == 6){
			output.setEnableQCS("0");
		}
		
		
		output.setSection_group(param.getSection_group());
		output.setSection_no(param.getSection_no());
		output.setId_wf_approval(param.getId_wf_approval());
		output.setEnableRejectApprove(enableRejectApprove);
		output.setShowPopalt(param.getShowPopalt());
		result.setResultObject(output);
		result.setMessages(messages);
		return result;
	}
	
	private void setEnableSectionGroup(T_WF_SECTIONBean bean,Q_QCSR04Output output,String value){
		if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_PMR_VALUE)){
			output.setEnablePMR(value);
		}else if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_BZR_VALUE)){
			output.setEnableBZR(value);
		}else if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_CTC_VALUE)){
			output.setEnableCTC(value);
		}else if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_PRI_VALUE)){
			output.setEnablePRI(value);
		}else if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_MRG_VALUE)){
			output.setEnableMRG(value);
		}else if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_CRC_VALUE)){
			output.setEnableCRC(value);
		}else if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_FRX_VALUE)){
			output.setEnableFRX(value);
		}else if(bean.getSection_group().equals(BillingSystemConstants.SECTION_GROUP_COV_VALUE)){
			output.setEnableCOV(value);
		}
	}
	private void determineSectionGroupToDisplay(List<T_WF_SECTIONBean> sequenceGroups,String id_ref,String section_no,String id_approver,String sectionGroup,Q_QCSR04Output output){
				
		//Determine section group need to be display
		if(sequenceGroups != null){
			for (int i=0;i < sequenceGroups.size();i++){
				T_WF_SECTIONBean bean = sequenceGroups.get(i);
				if(bean.getSection_group().equals(sectionGroup)){
					//Get approve status to enable or disable
					T_WF_APPROVAL t_wf_approval = new T_WF_APPROVAL();
					t_wf_approval.setId_ref(id_ref);
					t_wf_approval.setSection_group(sectionGroup);
					t_wf_approval.setSection_no(section_no);
					t_wf_approval.setId_approver(id_approver);
					String status = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_SELECT_STATUS_APPROVER, t_wf_approval, String.class);
					if(status !=  null && (status.equals(BillingSystemConstants.APPROVAL_STATUS_PENDING_VALUE))){
						setEnableSectionGroup(bean,output,"1");//Enable
					}else{
						output.setEnableSave("0");
						setEnableSectionGroup(bean,output,"0");//Disable
					}
					
					for(int j = i+1;j < sequenceGroups.size(); j++){
						bean = sequenceGroups.get(j);
						setEnableSectionGroup(bean,output,"2");//Invisible
					}
					break;
				}else{
					setEnableSectionGroup(bean,output,"0");//Disable
				}
			}
		}
		output.setDisableSectionNo("true");
	}
	private void determineSectionGroupToDisplayAfterApprove(List<T_WF_SECTIONBean> sequenceGroups,String sectionGroup,Q_QCSR04Output output){
		
		//Determine section group need to be display
		if(sequenceGroups != null){
			for (int i=0;i < sequenceGroups.size();i++){
				T_WF_SECTIONBean bean = sequenceGroups.get(i);
				if(bean.getSection_group().equals(sectionGroup)){
					setEnableSectionGroup(bean,output,"0");
					for(int j = i+1;j < sequenceGroups.size(); j++){
						bean = sequenceGroups.get(j);
						setEnableSectionGroup(bean,output,"2");//Invisible
					}
					break;
				}else{
					setEnableSectionGroup(bean,output,"0");//Disable
				}
			}
		}
		output.setDisableSectionNo("true");
		
	}
	/**
	 * Determine CSS class for approval status of section group
	 * @param appr_status
	 * @return
	 */
	private String[] determineApprStatusCssClass(String appr_status){
		String result[] = new String[]{AS0Class,""};
		
		if(appr_status == null ){
			return result;
		}
		if(appr_status.equals(BillingSystemConstants.APPROVAL_STATUS_PENDING_VALUE)){
			result[0] = AS1Class ;
			result[1] = BillingSystemConstants.APPROVAL_STATUS_PENDING;
		}else if(appr_status.equals(BillingSystemConstants.APPROVAL_STATUS_APPROVED_VALUE)){
			result[0] = AS2Class ;
			result[1] = BillingSystemConstants.APPROVAL_STATUS_APPROVED;
		}else if(appr_status.equals(BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE)){
			result[0] = AS3Class ;
			result[1] = BillingSystemConstants.APPROVAL_STATUS_REJECTED;
		}else if(appr_status.equals(BillingSystemConstants.APPROVAL_STATUS_CANCELLED_VALUE)){
			result[0] = AS4Class ;
			result[1] = BillingSystemConstants.APPROVAL_STATUS_CANCELLED;
		}else if(appr_status.equals(BillingSystemConstants.APPROVAL_STATUS_NOT_REQUIRE_VALUE)){
			result[0] = AS5Class ;
			result[1] = BillingSystemConstants.APPROVAL_STATUS_NOT_REQUIRE;
		}else if(appr_status.equals(BillingSystemConstants.APPROVAL_STATUS_FORWARDED_VALUE)){
			result[0] = AS6Class ;
			result[1] = BillingSystemConstants.APPROVAL_STATUS_FORWARDED;
		}else if(appr_status.equals(BillingSystemConstants.APPROVAL_STATUS_ESCALATION_VALUE)){
			result[0] = AS7Class ;
			result[1] = BillingSystemConstants.APPROVAL_STATUS_ESCALATION;
		}
		return result;
	}
	
}