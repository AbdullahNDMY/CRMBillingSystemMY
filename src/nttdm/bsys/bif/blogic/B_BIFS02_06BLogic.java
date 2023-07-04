package nttdm.bsys.bif.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIF_Output;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_OBT_P02;

import org.apache.struts.Globals;
import org.apache.struts.upload.FormFile;

public class B_BIFS02_06BLogic extends AbstractB_BIFS02_06BLogic{
	
	private static final String DELETE_BIF = "DELETE.B_BIF.S03.SQL001";
	private static final String DELETE_DOC = "DELETE.B_BIF.S03.SQL002";
	private static final String DELETE_PLAN_CUST = "DELETE.B_BIF.S03.SQL003";
	private static final String DELETE_PLAN_CUST_H = "DELETE.B_BIF.S03.SQL004";
	private static final String REJECT_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL002";
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	private int maxFileSize = 0;
	
	public BLogicResult execute(B_BIFS02Input input) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		try{ 
			B_BIF_Output out = new B_BIF_Output();
			Map<String , Object> screenObject = new HashMap<String, Object>();
			if (B_BIFS02Input.EDIT2_ACTION.equals(input.getAction())) {
				BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
				this.maxFileSize = systemSetting.getFileSizeUpload();
				String OTfileName = checkSize(input.getListFileOTBif());
				String QPfileName = checkSize(input.getListFileQPBif());
				String SCfileName = checkSize(input.getListFileSCBif());
				if (OTfileName != null && !"".equals(OTfileName)
						|| QPfileName != null && !"".equals(QPfileName)
						|| SCfileName != null && !"".equals(SCfileName)) {
					String errorFile = "";
					if (SCfileName != null && !"".equals(SCfileName)) {
						errorFile += SCfileName;
					}
					if (QPfileName != null && !"".equals(QPfileName)) {
						errorFile += QPfileName;
					}
					if (OTfileName != null && !"".equals(OTfileName)) {
						errorFile += OTfileName;
					}
					int maxSize = (int)Math.rint(this.maxFileSize/1024/1024);
					errorFile = errorFile.replaceAll(".$", "");
					out.setErrorFlg("1");
					out.setFileName(errorFile);
					out.setMaxFileSize(String.valueOf(maxSize));
				}
				this.saveBIF(input);
				out.setMessage("info.ERR2SC003");
				out.setAction(B_BIFS02Input.VIEW_ACTION);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
			
			if (B_BIFS02Input.DELETE_ACTION.equals(input.getAction())) {
				this.deleteBIF(input);
				out.setMessage("info.ERR2SC005");
				result.setResultString("list");
			}
			
			if (B_BIFS02Input.OBTAIN_APPROVAL_ACTION.equals(input.getAction())) {
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
					bean.setId_screen(B_BIFS02Input.ID_SCREEN);
					bean.setId_user(input.getUvo().getId_user());
					bean.setId_login3(input.getUvo().getId_user());
					bean.setResponse_expire(map.get("RESPONSE_EXPIRE").toString());
					gObtP02.exucute(bean);
				}
				screenObject.put("is_ObtainA", "1");
				out.setAction(B_BIFS02Input.EDIT2_ACTION);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
			
			out.setIdCust(input.getIdCust());
			out.setIdCustPlan(input.getIdCustPlan());
			out.setBifType(input.getBifType());
			//return object
			result.setResultObject(out);
					
			return result;  
		}
		catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
	
	private boolean deleteBIF(B_BIFS02Input input) {
		/**
		 * Audit Trail
		 */
		Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
				input.getIdRef(), "DS5", BillingSystemConstants.AUDIT_TRAIL_DELETED);
		
		Map<String, Object> params = new HashMap<String, Object>();
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
		CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		return true;
	}
	
	private boolean saveBIF(B_BIFS02Input input) {
		/**
		 * Audit Trail
		 */
		Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
				input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_EDITED);
		
		BIFAttachmentBLogic att = new BIFAttachmentBLogic();
		att.setDeletedAttachmentOT(input.getDeletedAttachmentOT());
		att.setDeletedAttachmentQP(input.getDeletedAttachmentQP());
		att.setDeletedAttachmentSC(input.getDeletedAttachmentSC());
		
		att.setQueryDAO(queryDAO);
		att.setUpdateDAO(updateDAO);
		att.setUpdateDAONuked(updateDAONuked);
		att.setIdAudit(idAudit);
		
		att.deleteAttachment(input.getIdRef());
		
		att.setListFileOTBif(input.getListFileOTBif());
		att.setListFileQPBif(input.getListFileQPBif());
		att.setListFileSCBif(input.getListFileSCBif());
		att.insertAttachment(input.getUvo().getId_user(), input.getIdRef());

		String isExpAmt = CommonUtils.toString(input.getChkExportAmount()).trim();
		if (CommonUtils.isEmpty(isExpAmt)) {
		    isExpAmt = "0";
		}
		
		String mpb=CommonUtils.toString(input.getCheckMultipleInOneInvoice()).trim();
		if (CommonUtils.isEmpty(mpb)) {
            mpb = "0";
        }
		String deliverymail=CommonUtils.toString(input.getCheckDeliveryEmail()).trim();
        if (CommonUtils.isEmpty(deliverymail)) {
            deliverymail = "0";
        }
		
		//updateIsExpAmt
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idRef", input.getIdRef());
		param.put("isExpAmt", isExpAmt);
		param.put("idAudit", idAudit);
		param.put("checkMultipleInOneInvoice", mpb);
		param.put("checkDeliveryEmail", deliverymail);
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
		updateDAO.execute("UPDATE.B_BIF.IS_DISPLAY_EXP_AMT", param);
		updateDAO.execute("UPDATE.B_BIF.MULTI_BILL_PERIOD",param);
		//updateDAO.execute("UPDATE.B_BIF.T_BAC_IS_DISPLAY_EXP_AMT", param);
		CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		
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