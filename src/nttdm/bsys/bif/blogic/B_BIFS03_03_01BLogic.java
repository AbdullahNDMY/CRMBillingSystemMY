/*
 * @(#)B_BIFS03_03_01BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import nttdm.bsys.common.util.G_OBT_P02;

import org.apache.struts.upload.FormFile;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS03_03_01BLogic extends AbstractB_BIFS03_03_01BLogic {

	private static final String DELETE_BIF = "DELETE.B_BIF.S03.SQL001";
	private static final String DELETE_DOC = "DELETE.B_BIF.S03.SQL002";
	private static final String DELETE_PLAN_CUST = "DELETE.B_BIF.S03.SQL003";
	private static final String DELETE_PLAN_CUST_H = "DELETE.B_BIF.S03.SQL004";
	private static final String REJECT_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL002";
	
	private int maxFileSize = 0;

	public BLogicResult execute(B_BIFS03Input input) {
		BLogicResult result = new BLogicResult();

		B_BIFS03Output out = new B_BIFS03Output();
		Map<String , Object> screenObject = new HashMap<String, Object>();
		Map<String, Object> bifObject = new HashMap<String,Object>();
		
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		try {
			out.setIdRef(input.getIdRef());
			
			if (B_BIFS03Input.EDIT2_ACTION.equals(input.getAction()) || "0".equals(input.getIsSave())) {
				/**
				 * Audit Trail
				 */
				Integer idAudit = null;
				if(! B_BIFS03Input.DELETE_ACTION.equals(input.getAction())) {
					idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
						input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_EDITED);
				}

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
				Map<String, Object> param = new HashMap<String,Object>();
				String mbp=input.getCheckMultipleInOneInvoice();
				if(CommonUtils.isEmpty(mbp)){
					mbp="0";
				}
				
				String isExpAmt = CommonUtils.toString(input.getExportAmount()).trim();
				if (CommonUtils.isEmpty(isExpAmt)) {
				    isExpAmt = "0";
				}
				String deliveryemail= CommonUtils.toString(input.getCheckDeliveryEmail()).trim();
	            if(CommonUtils.isEmpty(deliveryemail)){
	                deliveryemail="0";
	            }
	            param.put("checkDeliveryEmail", deliveryemail);
				param.put("isExpAmt", isExpAmt);
				param.put("checkMultipleInOneInvoice", mbp);
				param.put("idRef", input.getIdRef());
				param.put("idAudit", idAudit);
				param.put("idLogin", input.getUvo().getId_user());
				param.put("custName", input.getCustName());
				param.put("addr1", CommonUtils.toString(input.getAddr1()).trim());
				param.put("addr2", CommonUtils.toString(input.getAddr2()).trim());
				param.put("addr3", CommonUtils.toString(input.getAddr3()).trim());
				param.put("addr4", ("".equals(CommonUtils.toString(input.getZipCode()).trim()) ? "" : input.getZipCode() + ", ") + CommonUtils.toString(input.getCountry()).trim());
				param.put("tel", CommonUtils.toString(input.getTel()).trim());
				param.put("fax", CommonUtils.toString(input.getFax()).trim());
				param.put("contactName", input.getCboAttn() == null ? null : input.getCboAttn().trim());
				param.put("zipCode", CommonUtils.toString(input.getZipCode()).trim());
		        param.put("countryCd", CommonUtils.toString(input.getCountryCd()).trim());
		        param.put("emailToAdd", CommonUtils.toString(input.getEmailToAdd()).trim());
		        param.put("emailCcAdd", CommonUtils.toString(input.getEmailCcAdd()).trim());
				this.updateDAO.execute("UPDATE.B_BIF.MULTI_BILL_PERIOD", param);
				//this.updateDAO.execute("UPDATE.B_BIF.IS_DISPLAY_EXP_AMT", param);
				CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC003"));
				out.setMessage("info.ERR2SC003");
				out.setAction(B_BIFS03Input.VIEW_ACTION);
				bifObject.put("checkMultipleInOneInvoice", mbp);
				out.setBifObject(bifObject);
				result.setResultString("success");
			}
			
			if (B_BIFS03Input.DELETE_ACTION.equals(input.getAction())) {
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
			
			if (B_BIFS03Input.OBTAIN_APPROVAL_ACTION.equals(input.getAction())) {
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
				
				screenObject.put("is_ObtainA", "1");
				out.setAction(B_BIFS03Input.EDIT2_ACTION);
				result.setResultString(B_BIFS03Input.EDIT2_ACTION);
			}
			out.setScreenObject(screenObject);
			result.setResultObject(out);
		} catch (Exception e) {
			// TODO: handle exception
			messages.add(null, new BLogicMessage(e.getMessage()));
			result.setErrors(messages);
		}
		return result;
	}
	
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
	
	private boolean updateUploadFile(B_BIFS03Input input, Integer idAudit) throws FileNotFoundException, IOException {
		
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