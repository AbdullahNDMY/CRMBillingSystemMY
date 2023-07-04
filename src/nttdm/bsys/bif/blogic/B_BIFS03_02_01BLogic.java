/*
 * @(#)B_BIFS03_02_01BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS03Input;
import nttdm.bsys.bif.dto.B_BIFS03Output;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_ALT_P03;
import nttdm.bsys.common.util.G_OBT_P01;
import nttdm.bsys.common.util.G_OBT_P02;
import nttdm.bsys.common.util.MessageUtil;

import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS03_02_01BLogic extends AbstractB_BIFS03_02_01BLogic {

	private static final String UPDATE_BIF_H = "UPDATE.B_BIF.S03_02.SQL001";
	private static final String DELETE_BIF = "DELETE.B_BIF.S03.SQL001";
	private static final String DELETE_DOC = "DELETE.B_BIF.S03.SQL002";
	private static final String DELETE_PLAN_CUST = "DELETE.B_BIF.S03.SQL003";
	private static final String DELETE_PLAN_CUST_H = "DELETE.B_BIF.S03.SQL004";
	private static final String SELECT_BIF_INFO = "SELECT.B_BIF.S03_01.SQL001";
	private static final String REJECT_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL002";
	
	private int maxFileSize = 0;
	
	public BLogicResult execute(B_BIFS03Input input) {
		BLogicResult result = new BLogicResult();
		B_BIFS03Output out = new B_BIFS03Output();
		Map<String , Object> screenObject = new HashMap<String, Object>();
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		
		try {
			out.setIdRef(input.getIdRef());
			
			if (input.getAction().equals(B_BIFS03Input.EDIT_ACTION) || input.getIsSave().equals("0")) {
				/**
				 * Audit Trail
				 */
				Integer idAudit = null;
				if(! input.getAction().equals(B_BIFS03Input.DELETE_ACTION)) {
					idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
						input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_EDITED);
				}
				this.updateBIF(input, idAudit);
				//update IS_DISPLAY_EXP_AMT(T_CUST_PLAN_D)
				this.updateIsExpAmt(input, idAudit);
				BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
				this.maxFileSize = systemSetting.getFileSizeUpload();
				String AGfileName = checkSize(input.getListFileAGBif());
				String CRfileName = checkSize(input.getListFileCRBif());
				String IVfileName = checkSize(input.getListFileIVBif());
				String MRfileName = checkSize(input.getListFileMRBif());
				String OTfileName = checkSize(input.getListFileOTBif());
				String POfileName = checkSize(input.getListFilePOBif());
				String TFfileName = checkSize(input.getListFileTFBif());
				String TLfileName = checkSize(input.getListFileTLBif());
				if (AGfileName != null && !"".equals(AGfileName)
						|| CRfileName != null && !"".equals(CRfileName)
						|| IVfileName != null && !"".equals(IVfileName)
						|| MRfileName != null && !"".equals(MRfileName)
						|| OTfileName != null && !"".equals(OTfileName)
						|| POfileName != null && !"".equals(POfileName)
						|| TFfileName != null && !"".equals(TFfileName)
						|| TLfileName != null && !"".equals(TLfileName)) {
					String errorFile = "";
					if (TFfileName != null && !"".equals(TFfileName)) {
						errorFile += TFfileName;
					}
					if (MRfileName != null && !"".equals(MRfileName)) {
						errorFile += MRfileName;
					}
					if (CRfileName != null && !"".equals(CRfileName)) {
						errorFile += CRfileName;
					}
					if (TLfileName != null && !"".equals(TLfileName)) {
						errorFile += TLfileName;
					}
					if (POfileName != null && !"".equals(POfileName)) {
						errorFile += POfileName;
					}
					if (OTfileName != null && !"".equals(OTfileName)) {
						errorFile += OTfileName;
					}
					if (IVfileName != null && !"".equals(IVfileName)) {
						errorFile += IVfileName;
					}
					if (AGfileName != null && !"".equals(AGfileName)) {
						errorFile += AGfileName;
					}
					int maxSize = (int)Math.rint(this.maxFileSize/1024/1024);
					errorFile = errorFile.replaceAll(".$", "");
					out.setErrorFlg("1");
					out.setFileName(errorFile);
					out.setMaxFileSize(String.valueOf(maxSize));
				}
				this.updateUploadFile(input, idAudit);
				CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				out.setAction(B_BIFS03Input.VIEW_ACTION);
				//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC003"));
				out.setMessage("info.ERR2SC003");
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
			
			if (input.getAction().equals(B_BIFS03Input.DELETE_ACTION)) {
				/**
				 * Audit Trail
				 */
				Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
						input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_DELETED);
				
				this.deleteBIF(input, idAudit);
				CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC005"));
				out.setMessage("info.ERR2SC005");
				result.setResultString("list");
			}
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("ID_REF", input.getIdRef());
			Map<String, Object> bifObject = queryDAO.executeForMap(SELECT_BIF_INFO, param);
			
			if (input.getAction().equals(B_BIFS03Input.OBTAIN_APPROVAL_ACTION)) {
				if (bifObject.get("ID_STATUS").equals("DS0")) {
					G_OBT_P01 gObtP01 = new G_OBT_P01(this.queryDAO, this.updateDAO);
					gObtP01.exucute(input.getIdRef(), "BIF-CN", "C000", B_BIFS03Input.ID_SCREEN, input.getUvo().getId_user());
				} else {
					Map<String, String> params = new HashMap<String, String>();
					params.put("ID_REF", input.getIdRef());
					params.put("APPR_STATUS", "AS3");
					List<Map<String, Object>> rejectList = queryDAO.executeForMapList(REJECT_STATUS_INFO, params);
					//get reject workflow information
					for (Map<String, Object> map : rejectList) {
						String action_user = map.get("ID_APPROVER").toString();
						String section_no = map.get("SECTION_NO").toString();
						G_OBT_P02 gObtP02 = new G_OBT_P02(this.queryDAO, this.updateDAO);
						WF_TABLEBean bean = new WF_TABLEBean();
						bean.setId_ref(input.getIdRef());
						bean.setAction_user(action_user);
						bean.setSection_no(section_no);
						bean.setId_screen(B_BIFS03Input.ID_SCREEN);
						bean.setId_user(input.getUvo().getId_user());
						bean.setId_login3(input.getUvo().getId_user());
						bean.setResponse_expire(map.get("RESPONSE_EXPIRE").toString());
						gObtP02.exucute(bean);
					}
				}
				
				screenObject.put("is_ObtainV", input.getIs_ObtainV());
				screenObject.put("is_ObtainA", "1");
				//alert popup
				out.setMessage(null);
				messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("POPALT_BIF", input.getIdRef()));
				//
				out.setAction(B_BIFS03Input.EDIT_ACTION);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}	
			
			if (input.getAction().equals(B_BIFS03Input.OBTAIN_VERIFICATION_ACTION)) {
				
				G_ALT_P03 gAltP03 = new G_ALT_P03(this.queryDAO, this.updateDAO);
				WF_TABLEBean wf = new WF_TABLEBean();
				wf.setId_ref(input.getIdRef());
				wf.setAction_user(bifObject.get("ID_CONSLT").toString());
				wf.setType(bifObject.get("BIF_TYPE").toString());
				wf.setId_screen(B_BIFS03Input.ID_SCREEN);
				String message = MessageUtil.get("info.ERR2SC026", input.getIdRef());
				wf.setSubject(message);
				message = MessageUtil.get("info.ERR2SC027", input.getIdRef());
				wf.setMsg(message);
				wf.setId_login3(input.getUvo().getId_user());
				gAltP03.execute(wf);
				out.setMessage(null);
				screenObject.put("is_ObtainV", "1");
				screenObject.put("is_ObtainA", input.getIs_ObtainA());
				out.setAction(B_BIFS03Input.EDIT_ACTION);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
			out.setScreenObject(screenObject);
			result.setResultObject(out);
		} catch (Exception e) {
			messages.add(null, new BLogicMessage(e.getMessage()));
			result.setErrors(messages);
		}
		return result;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private boolean deleteBIF(B_BIFS03Input input, Integer idAudit) {
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("ID_REF", input.getIdRef());
		params.put("IS_DELETED", "1");
		params.put("ID_STATUS", "DS5");
		params.put("ID_LOGIN", input.getUvo().getId_user());
		params.put("PLAN_STATUS", "PS1");
		params.put("ID_AUDIT", idAudit);
		updateDAO.execute(DELETE_BIF, params);
		updateDAO.execute(DELETE_DOC, params);
	    updateDAO.execute(DELETE_PLAN_CUST, params);
	    updateDAO.execute(DELETE_PLAN_CUST_H, params);
		return true;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private boolean updateBIF(B_BIFS03Input input, Integer idAudit) {
		//insert to BIF_H
		Map<String, Object> param = new HashMap<String, Object>();
		String  cboConsultantName = CommonUtils.toString(input.getConsultantName()).trim();
        String  preCboConsultantName = CommonUtils.toString(input.getPreCboConsultantName()).trim();
        if(!preCboConsultantName.equals(cboConsultantName)) {
            param.put("changeIdConsultant", "0");
        } else {
            param.put("changeIdConsultant", "");
        }
		param.put("ID_REF", input.getIdRef());
		param.put("ID_CUST", input.getIdCust());
		param.put("ID_CONSLT", input.getConsultantName());
		param.put("ID_QCS", input.getQCSReference());
		param.put("ID_QUO", input.getQuoReference());
		param.put("BIF_TYPE", input.getBifType());
		param.put("ADR_TYPE", input.getBillingAddress());
		param.put("ADDRESS1", CommonUtils.toString(input.getAddr1()).trim());
		param.put("ADDRESS2", CommonUtils.toString(input.getAddr2()).trim());
		param.put("ADDRESS3", CommonUtils.toString(input.getAddr3()).trim());
		param.put("ADDRESS4", ("".equals(CommonUtils.toString(input.getZipCode()).trim()) ? "" : input.getZipCode() + ", ") + CommonUtils.toString(input.getCountry()).trim());
		param.put("FAX_NO", CommonUtils.toString(input.getFax()).trim());
		param.put("TEL_NO", CommonUtils.toString(input.getTel()).trim());
		param.put("CONTACT_NAME", input.getAttn());
		param.put("CUST_PO", input.getCustomer());
		param.put("JOB_NO", "");
		param.put("CN_TYPE", input.getCreditNoteType());
		param.put("CN_TYPE6", input.getOthers());
		param.put("DELIVERY", input.getInstruction());
		param.put("REMARKS", input.getRemarks());
		param.put("BILL_CUR", input.getBillingCurrency());
		param.put("EXPORT_CUR", input.getExportCurrency());
		param.put("CONV_RATE", input.getExportAmount());
//		param.put("REQ_USER", input.getUvo().getId_user());
//		param.put("DATE_REQ", input.getRequestDate());
		param.put("USER_COMMENT", input.getUserComment());
		param.put("ID_LOGIN", input.getUvo().getId_user());
		param.put("ID_AUDIT", idAudit);
		param.put("CUST_NAME", CommonUtils.toString(input.getCustName()).trim());
		String cboAttn = CommonUtils.toString(input.getCboAttn_temp()).trim();
        if(CommonUtils.isEmpty(cboAttn)) {
            param.put("CONTACT_TYPE", "  ");
        } else {
            param.put("CONTACT_TYPE", cboAttn);
        }
        param.put("ZIP_CODE", CommonUtils.toString(input.getZipCode()).trim());
        param.put("COUNTRY_CD", CommonUtils.toString(input.getCountryCd()).trim());
        String isExpAmt = CommonUtils.toString(input.getExportAmount());
		if (CommonUtils.isEmpty(isExpAmt)) {
			isExpAmt = "0";
		}
        param.put("isExpAmt", isExpAmt);
        String mpb=input.getCheckMultipleInOneInvoice();
        if(CommonUtils.isEmpty(mpb)){
        	mpb="0";
        }
        param.put("checkMultipleInOneInvoice", mpb);
        String deliverymail=input.getCheckDeliveryEmail();
        if(CommonUtils.isEmpty(deliverymail)){
            deliverymail="0";
        }
        param.put("checkDeliveryEmail", deliverymail);
        param.put("emailTo", CommonUtils.toString(input.getEmailToAdd().toString()));
		param.put("emailCc", CommonUtils.toString(input.getEmailCcAdd().toString()));
		this.updateDAO.execute(UPDATE_BIF_H, param);
		return true;
	}
	
	private boolean updateUploadFile(B_BIFS03Input input, Integer idAudit) {
		
		BIFAttachmentProccessBLogic att = new BIFAttachmentProccessBLogic(this.queryDAO, this.updateDAO, this.updateDAONuked);
		att.setIdAudit(idAudit);
		//upload file for <Termination Form>
		FormFile[] listFile = input.getListFileTFBif();
		att.setDeletedList(input.getDeleteListFileTFBif());
		att.deleteAttachment(input.getIdRef(), "DTMF");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DTMF");
		
		//upload file for <Termination Letter>
		listFile = input.getListFileTLBif();
		att.setDeletedList(input.getDeleteListFileTLBif());
		att.deleteAttachment(input.getIdRef(), "DTML");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DTML");
		
		//upload file for <Invoice>
		listFile = input.getListFileIVBif();
		att.setDeletedList(input.getDeleteListFileIVBif());
		att.deleteAttachment(input.getIdRef(), "DINV");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DINV");
		
		//upload file for <MRTG>
		listFile = input.getListFileMRBif();
		att.setDeletedList(input.getDeleteListFileMRBif());
		att.deleteAttachment(input.getIdRef(), "DMRT");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DMRT");
		
		//upload file for <Purchase Order>
		listFile = input.getListFilePOBif();
		att.setDeletedList(input.getDeleteListFilePOBif());
		att.deleteAttachment(input.getIdRef(), "DPCO");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DPCO");
		
		//upload file for <Agreement>
		listFile = input.getListFileAGBif();
		att.setDeletedList(input.getDeleteListFileAGBif());
		att.deleteAttachment(input.getIdRef(), "DAGM");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DAGM");
		
		//upload file for <Change Request Form>
		listFile = input.getListFileCRBif();
		att.setDeletedList(input.getDeleteListFileCRBif());
		att.deleteAttachment(input.getIdRef(), "DCRF");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DCRF");
		
		//upload file for <Others>
		listFile = input.getListFileOTBif();
		att.setDeletedList(input.getDeleteListFileOTBif());
		att.deleteAttachment(input.getIdRef(), "DOTH");
		att.setListFile(listFile);
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DOTH");
		
		return true;
	}
	
	private void updateIsExpAmt(B_BIFS03Input input, Integer idAudit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idRef", input.getIdRef());
		String isExpAmt = "0";
		if (CommonUtils.isEmpty(CommonUtils.toString(input.getExportAmount()))) {
			isExpAmt = "0";
		} else {
		    isExpAmt = input.getExportAmount();
		}
		param.put("isExpAmt", isExpAmt);
		param.put("idAudit", idAudit);
		updateDAO.execute("UPDATE.B_BIF.IS_DISPLAY_EXP_AMT", param);
		//updateDAO.execute("UPDATE.B_BIF.T_BAC_IS_DISPLAY_EXP_AMT", param);
	}
	private String checkSize(FormFile [] listFile){
		StringBuilder fileName = new StringBuilder();
		for(FormFile file : listFile) {
			if(file != null && file.getFileSize() > this.maxFileSize) {
				fileName.append(file.getFileName());
				fileName.append(",");
			}
		}
		return fileName.toString();
	}
}