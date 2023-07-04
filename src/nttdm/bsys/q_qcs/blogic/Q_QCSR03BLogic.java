/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Quotation Control Sheet
 * SERVICE NAME   : Quotation Control Sheet
 * FUNCTION       : Quotation Control BLogic
 * FILE NAME      : Q_QCSR03BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 Tuyen Fixed for error of checking style
 * 
**********************************************************/
package nttdm.bsys.q_qcs.blogic;

import java.text.MessageFormat;
import org.apache.struts.Globals;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.util.PropertyUtil;
import nttdm.bsys.common.bean.T_WF_SECTIONBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.G_WFM_P01;
import nttdm.bsys.q_qcs.dto.Q_QCSR03Input;
import nttdm.bsys.q_qcs.dto.Q_QCSR03Output;
import nttdm.bsys.q_qcs.blogic.AbstractQ_QCSR03BLogic;

/**
 * QCS Screen S003 BLogic
 * <ul>
 * <li>QCS Screen S003 BLogic
 * </ul>
 * @author  NTTData Vietnam
 * @version 1.1 
 */
public class Q_QCSR03BLogic extends AbstractQ_QCSR03BLogic {

	public static final String SQL_INSERT_T_QCS_H = "INSERT.Q_QCS.SQL004";
	public static final String SQL_INSERT_T_QCS_D = "INSERT.Q_QCS.SQL005";
	public static final String SQL_INSERT_T_SECTION = "INSERT.Q_QCS.SQL006";
	public static final String SQL_SELECT_MAX_ID_SVC = "SELECT.Q_QCS.SQL010";
	public static final String SQL_UPDATE_ID_STATUS = "UPDATE.Q_QCS.SQL011";
	public static final String SQL_UPDATE_T_QCS_H = "UPDATE.Q_QCS.SQL012";
	public static final String SQL_DELETE_T_QCS_D = "DELETE.Q_QCS.SQL013";
	public static final String SQL_UPDATE_T_SECTION = "UPDATE.Q_QCS.SQL014";
	public static final String SQL_DELETE_T_QCS_H = "UPDATE.Q_QCS.SQL015";
	public static final String SQL_DELETE_T_WF_DOC = "UPDATE.Q_QCS.SQL016";
	public static final String SQL_INSERT_T_WF_APPROVAL = "INSERT.Q_QCS.SQL018";
	public static final String SQL_GET_STATUS = "SELECT.Q_QCS.SQL003";
	public static final String SQL_SELECT_QCS_HEADER = "SELECT.Q_QCS.SQL007";
	public static final String SQL_SELECT_QCS_DETAIL = "SELECT.Q_QCS.SQL008";
	public static final String SQL_SELECT_QCS_SECTION = "SELECT.Q_QCS.SQL009";
	public static final String SQL_CHECK_APPR_STATUS = "SELECT.Q_QCS.SQL017";
	public static final String SQL_SELECT_APPR_STATUS = "SELECT.Q_QCS.SQL019";
	public static final String SQL_COUNT_QCS_APPR = "SELECT.Q_QCS.SQL020";
	public static final String SQL_UPDATE_APPR_STATUS = "UPDATE.Q_QCS.SQL021";
	public static final String SQL_INSERT_T_DOC = "INSERT.Q_QCS.SQL022";
	public static final String SQL_GET_MAX_ID_DOC = "SELECT.Q_QCS.SQL023";
	public static final String SQL_INSERT_T_WF_DOC = "INSERT.Q_QCS.SQL024";
	public static final String SQL_SELECT_T_WF_DOC = "SELECT.Q_QCS.SQL025";
	public static final String SQL_DELETE_T_WF_DOC_2 = "UPDATE.Q_QCS.SQL026";
	public static final String SQL_SELECT_NAME_APPROVER = "SELECT.Q_QCS.SQL027";
	public static final String SQL_UPDATE_APPROVAL = "UPDATE.Q_QCS.SQL028";
	public static final String SQL_SECTION_GROUP_TO_APPROVE = "SELECT.Q_QCS.SQL029";
	public static final String SQL_UPDATE_SECTION_GROUP_TO_REJECT = "UPDATE.Q_QCS.SQL030";
	public static final String SQL_GET_SEQUENCE_NO = "SELECT.Q_QCS.SQL031";
	public static final String SQL_COUNT_TRANSFER_USER = "SELECT.Q_QCS.SQL032";
	public static final String SQL_SELECT_SEQUENCE_SECT_GROUP = "SELECT.Q_QCS.SQL033";
	public static final String SQL_SELECT_STATUS_APPROVER = "SELECT.Q_QCS.SQL034";
	public static final String SQL_UPDATE_T_SECTION_REJECT = "UPDATE.Q_QCS.SQL035";
	public static final String SQL_GET_RESPONSE_EXPIRE = "SELECT.Q_QCS.SQL036";
	public static final String SQL_GET_AUTHORITY_USER = "SELECT.Q_QCS.SQL037";
	public static final String SQL_GET_PIC = "SELECT.Q_QCS.SQL038";
	
	public static final String ERR2SC003 = "info.ERR2SC003";
	public static final String ERR2SC016 = "info.ERR2SC016";
	public static final String ERR2SC017 = "info.ERR2SC017";
	public static final String QCSNO_ID_CODE = "QCSNO";
	public static final String POP_ALT = "POPALT";
	private static final String ERR2SC007 = "info.ERR2SC007";
	private static final String approved = "info.ERR2SC007.Approved";
	private static final String rejected = "info.ERR2SC007.Rejected";
	public BLogicResult execute(Q_QCSR03Input param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages messages = new BLogicMessages();
		Q_QCSR03Output output = new Q_QCSR03Output();
		try{
			String showPopalt = "";
			//id_user
			String id_login = param.getUvo().getId_user();
			param.setId_login(id_login);
			String id_ref = param.getId_ref();
			//Get click event
			int clickEvent = 0;
			if(param.getClickEvent() != null && !param.getClickEvent().equals("")){
				clickEvent = Integer.parseInt(param.getClickEvent());
			}
			Q_QCSR01 q_qcsr01 = new Q_QCSR01(param,queryDAO,updateDAO);
			//Check click event
			switch (clickEvent){
			case 1://New QCS
				q_qcsr01.executeNewQCS();
				id_ref = param.getId_ref();
				output.setClickEvent("3");//Obtain approval
				break;
			case 2:
				//Update data				
				q_qcsr01.executeUpdateQCS();
				output.setClickEvent("2");
				
				break;
			case 3://Case user click Obtain approval in QCS section group
				output.setClickEvent("3");
				showPopalt = MessageFormat.format(PropertyUtil.getProperty(POP_ALT), param.getId_ref());

				q_qcsr01.executeObtainApprovalQCS();
				break;
			case 4://Delete
				output.setClickEvent("4");
				q_qcsr01.executeDeleteQCS();
				break;
			case 5://Case user click Obtain Approval in other section groups
				output.setClickEvent("5");
				showPopalt = MessageFormat.format(PropertyUtil.getProperty(POP_ALT), param.getId_ref());
				//Check whether user want to save any changes or not
				
				q_qcsr01.executeObtainApprovalSectionGroup();
				break;
			case 6://View mode
				output.setClickEvent("6");
				break;
			case 7://Edit mode
				//Update data				
				q_qcsr01.executeUpdateQCS();
				output.setClickEvent("7");
				messages.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC003));
				break;
			case 8://Click Save button from Approval mode
				q_qcsr01.executeUpdateQCS();
				output.setClickEvent("8");
				break;
			case 9://Approve QCS group
				//Update T_WF_APPROVAL table
				//q_qcsr01.executeApproveSectionGroupQCS();
				//Call G_WFM_P01 process
				G_WFM_P01 wfm_p01_1 = new G_WFM_P01(updateDAO, queryDAO, id_ref, BillingSystemConstants.ID_SCREEN_QCS, 
						BillingSystemConstants.SECTION_NO_QCS_VALUE,"1",BillingSystemConstants.APPROVAL_STATUS_APPROVED_VALUE, param.getId_wf_approval(), id_login);
				wfm_p01_1.execute();
				
				output.setClickEvent("9");
				messages.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC007,PropertyUtil.getProperty(approved)));
				break;
			case 10://Reject QCS group
				//Update QCS-
				if(param.getObt_appr_save().equals("y")){
					q_qcsr01.executeUpdateQCS();
				}
				//Update T_WF_APPROVAL table
				//q_qcsr01.executeRejectSectionGroupQCS();				
				//Call G_WFM_P01 process
				G_WFM_P01 wfm_p01_2 = new G_WFM_P01(updateDAO, queryDAO, id_ref, BillingSystemConstants.ID_SCREEN_QCS, 
						BillingSystemConstants.SECTION_NO_QCS_VALUE,"1",BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE, param.getId_wf_approval(), id_login);
				wfm_p01_2.execute();
				output.setClickEvent("10");
				messages.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC007,PropertyUtil.getProperty(rejected)));
				break;
			case 11://Click Save button from Section approval mode
				q_qcsr01.executeUpdateSectionGroupToReject();
				output.setClickEvent("11");
				break;
			case 12://Approve section group
				//q_qcsr01.executeApproveSectionGroup();
				//---Call G_WFM_P01 process
				//Get sequence no 
				String seq_no = this.getSequenceNo(id_ref, param.getSection_group(),param.getSection_no());
				G_WFM_P01 wfm_p01_3 = new G_WFM_P01(updateDAO, queryDAO, id_ref, BillingSystemConstants.ID_SCREEN_QCS, 
						param.getSection_no(),seq_no,BillingSystemConstants.APPROVAL_STATUS_APPROVED_VALUE, param.getId_wf_approval(), id_login);
				wfm_p01_3.execute();
				output.setClickEvent("12");
				break;
			case 13://Reject section group
				//Update section group
				if(param.getObt_appr_save().equals("y")){
					q_qcsr01.executeUpdateSectionGroupToReject();
				}
				//Update T_WF_APPROVAL table
				//q_qcsr01.executeRejectSectionGroup();
				//---Call G_WFM_P01 process
				//Get sequence no 
				String seqe_no = this.getSequenceNo(id_ref, param.getSection_group(), param.getSection_no());
				G_WFM_P01 g_wfm_p01_4 = new G_WFM_P01(updateDAO, queryDAO, id_ref, BillingSystemConstants.ID_SCREEN_QCS, 
						param.getSection_no(),seqe_no,BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE, param.getId_wf_approval(), id_login);
				g_wfm_p01_4.execute();
				output.setClickEvent("13");
				messages.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC007,PropertyUtil.getProperty(rejected)));
				break;
				default:
					break;
			}
			output.setId_wf_approval(param.getId_wf_approval());
			output.setSection_group(param.getSection_group());
			output.setSection_no(param.getSection_no());
			output.setId_ref(id_ref);
			output.setShowPopalt(showPopalt);
			result.setMessages(messages);	
			result.setResultObject(output);		
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			return result;
		}catch(Exception ex){
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;		
		}
	}
	private String getSequenceNo(String id_ref,String section_group,String section_no){
		T_WF_SECTIONBean wf_ection = new T_WF_SECTIONBean();
		wf_ection.setSection_group(section_group);
		wf_ection.setSection_no(section_no);
		wf_ection.setId_ref(id_ref);
		wf_ection.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
		String seq_no = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_GET_SEQUENCE_NO, wf_ection, String.class);
		return seq_no;
	}
}