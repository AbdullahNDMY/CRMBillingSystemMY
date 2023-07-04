package nttdm.bsys.bif.blogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIFS02Output;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_ALT_P03;
import nttdm.bsys.common.util.G_OBT_P01;
import nttdm.bsys.common.util.G_OBT_P02;
import nttdm.bsys.common.util.MessageUtil;

public class B_BIFS02_05BLogic extends AbstractB_BIFS02_05BLogic{
	
	private static final String UPDATE_BIF_H = "UPDATE.B_BIF.S02_05.SQL001";
	private static final String DELETE_BIF = "DELETE.B_BIF.S03.SQL001";
	private static final String DELETE_DOC = "DELETE.B_BIF.S03.SQL002";
	private static final String DELETE_PLAN_CUST = "DELETE.B_BIF.S03.SQL003";
	private static final String DELETE_PLAN_CUST_H = "DELETE.B_BIF.S03.SQL004";
	public static final String DELETE_T_WF_DOC = "DELETE.B_BIF.DOC.SQL001";
	private static final String SELECT_BIF_INFO = "SELECT.B_BIF.S03_01.SQL001";
	private static final String REJECT_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL002";
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	private static final String SELECT_BILL_ACC_BY_ID_REF = "SELECT.B_BIF.SQL_BILL_ACC_BY_ID_REF";
	
	private int maxFileSize = 0;
	
	public BLogicResult execute(B_BIFS02Input input) {
		// TODO Auto-generated method stub
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		
		BLogicMessages messages = new BLogicMessages();
		
		String textOthers = input.getTxtOthers();
		String textOtherDays = input.getTxtOthersTady();
		if(!textOtherDays.equals("0")&&(textOthers.equals("")||textOthers == null)){
			input.setTxtOthers(textOtherDays+" Days");
		}else if(textOtherDays.equals("0")&&(textOthers.equals("")||textOthers == null)){
			input.setTxtOthers("");
		}
		
		try{ 
			B_BIFS02Output out = new B_BIFS02Output();
			
			Map<String , Object> screenObject = new HashMap<String, Object>();
			
			if (input.getAction().equals(B_BIFS02Input.EDIT_ACTION) || input.getIsSave().equals("1")) {
				/**
				 * Audit Trail
				 */
				Integer idAudit = null;
				if (! input.getAction().equals(B_BIFS02Input.DELETE_ACTION)) {
					idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), 
						BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
						input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()),
						BillingSystemConstants.AUDIT_TRAIL_EDITED);
				}
				// update BIF information
				this.updateBIF(input, idAudit);
				//update upload file
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
				this.updateFileUpload(input, idAudit);
				//update IS_DISPLAY_EXP_AMT(T_CUST_PLAN_D)
				this.updateIsExpAmt(input, idAudit);
				CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC003"));
				out.setMessage("info.ERR2SC003");
				out.setAction(B_BIFS02Input.VIEW_ACTION);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
			
			if (input.getAction().equals(B_BIFS02Input.DELETE_ACTION)) {
				/**
				 * Audit Trail
				 */
				Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo()
						.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()),
						BillingSystemConstants.AUDIT_TRAIL_DELETED);
				
				this.deleteBIF(input, idAudit);
				CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC005"));
				out.setMessage("info.ERR2SC005");
				result.setResultString("list");
			}
			
			if (input.getAction().equals(B_BIFS02Input.OBTAIN_APPROVAL_ACTION)) {
				
				Map<String, String> param = new HashMap<String, String>();
				param.put("ID_REF", input.getIdRef());
				Map<String, Object> bifObject = queryDAO.executeForMap(SELECT_BIF_INFO, param);
				
				if (bifObject.get("ID_STATUS").equals("DS0")) {
					G_OBT_P01 gObtP01 = new G_OBT_P01(this.queryDAO, this.updateDAO);
					try {
						gObtP01.exucute(input.getIdRef(), "BIF-IDN", "B000", B_BIFS02Input.ID_SCREEN, input.getUvo().getId_user());
						messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("POPALT_BIF", input.getIdRef()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw e;
					}
				} else {
					try {
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
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						throw e;
					}
				}
				
				
				screenObject.put("is_ObtainV", input.getIs_ObtainV());
				screenObject.put("is_ObtainA", "1");
				out.setAction(B_BIFS02Input.EDIT_ACTION);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}	
			
			if (input.getAction().equals(B_BIFS02Input.OBTAIN_VERIFICATION_ACTION)) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("ID_REF", input.getIdRef());
				Map<String, Object> bifObject = queryDAO.executeForMap(SELECT_BIF_INFO, param);
				
				G_ALT_P03 gAltP03 = new G_ALT_P03(this.queryDAO, this.updateDAO);
				WF_TABLEBean wf = new WF_TABLEBean();
				wf.setId_ref(input.getIdRef());
				wf.setAction_user(bifObject.get("ID_CONSLT").toString());
				wf.setType(bifObject.get("BIF_TYPE").toString());
				wf.setId_screen("BIF");
				wf.setSubject(MessageUtil.get("info.ERR2SC026", input.getIdRef()));
				wf.setMsg(MessageUtil.get("info.ERR2SC027", input.getIdRef()));
				wf.setId_login3(input.getUvo().getId_user());
				gAltP03.execute(wf);
				screenObject.put("is_ObtainV", "1");
				screenObject.put("is_ObtainA", input.getIs_ObtainA());
				out.setAction(B_BIFS02Input.EDIT_ACTION);
				out.setMessage(null);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
			
			out.setScreenObject(screenObject);
			out.setIdRef(input.getIdRef());
			//return object
			result.setResultObject(out);
			result.setMessages(messages);

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
	
	private boolean deleteBIF(B_BIFS02Input input, Integer idAudit) {
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
	
	private boolean updateBIF(B_BIFS02Input input, Integer idAudit) throws ParseException {
		Map<String, Object> param = new HashMap<String, Object>();
		String  cboConsultantName = CommonUtils.toString(input.getCboConsultantName()).trim();
		String  preCboConsultantName = CommonUtils.toString(input.getPreCboConsultantName()).trim();
		if(!preCboConsultantName.equals(cboConsultantName)) {
		    param.put("changeIdConsultant", "0");
		} else {
		    param.put("changeIdConsultant", "");
		}
		param.put("idRef", input.getIdRef());
		param.put("idCust", input.getIdCust().trim());
		param.put("idConslt", input.getCboConsultantName().trim());
		param.put("idQcs", input.getCboQCSReference().trim());
		param.put("idQuo", input.getCboQuoReference().trim());
		param.put("bifType", input.getBifType().trim());
		param.put("adrType", input.getCboBillingAddress().trim());
		param.put("addr1", CommonUtils.toString(input.getAddr1()).trim());
		param.put("addr2", CommonUtils.toString(input.getAddr2()).trim());
		param.put("addr3", CommonUtils.toString(input.getAddr3()).trim());
		param.put("addr4", ("".equals(CommonUtils.toString(input.getZipCode()).trim()) ? "" : input.getZipCode() + ", ") + CommonUtils.toString(input.getCountry()).trim());
		param.put("tel", CommonUtils.toString(input.getTel()).trim());
		param.put("fax", CommonUtils.toString(input.getFax()).trim());
		param.put("contactName", input.getCboAttn() == null ? null : input.getCboAttn().trim());
		param.put("custPo", input.getTxtCustomerPO().trim());
		param.put("jobNo", "");
		if(input.getTxtRequestDate() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
			param.put("dateReq", sdf.parse(input.getTxtRequestDate()));
		}
		if(input.getChkNewCustomer() == null){
			param.put("mode", "0");
		}else {
			param.put("mode", input.getChkNewCustomer().trim());
		}
		String ctterm = input.getRdbCreditTermAp().trim();
		String ctterm3_day = null;
		String term_day = null;
		param.put("ctterm", ctterm);
		List<Map<String, Object>> billAccInfoList = queryDAO.executeForMapList(SELECT_BILL_ACC_BY_ID_REF, input.getIdRef());

		if (billAccInfoList==null || billAccInfoList.size()<=0) {
				if("1".equals(ctterm)){
					term_day = "0";
					ctterm3_day = "COD";
				}
				else if(ctterm.equals("2")){
					term_day = "30";
					ctterm3_day = "30 Days";
				}
				else{
					ctterm3_day = input.getTxtOthers().trim();
					term_day = input.getTxtOthersTady().trim();
				}
			}else{
				ctterm3_day = (String) billAccInfoList.get(0).get("TERM");
				term_day = billAccInfoList.get(0).get("TERM_DAY").toString();
			}
		
		param.put("term_day", term_day);
		param.put("ctterm3_day", ctterm3_day);
		param.put("delivery", input.getRdbInstructionf().trim());
		
		param.put("remarks", input.getTxtRemarks());
		param.put("exportCur", input.getExportCurrency().trim());
		param.put("billCur", input.getBillingCurrency().trim());
		param.put("convRate", input.getRateCurrency().trim());
		param.put("reqUser", input.getUvo().getId_user().trim());			
		param.put("userComment", input.getTbxComments()[0]);
		param.put("idLogin", input.getUvo().getId_user());
		param.put("idAudit", idAudit);
		param.put("custName", CommonUtils.toString(input.getCustName()).trim());
		param.put("emailTo", CommonUtils.toString(input.getEmailToAdd().toString()));
		param.put("emailCc", CommonUtils.toString(input.getEmailCcAdd().toString()));
		String isExpAmt=CommonUtils.toString(input.getChkExportAmount()).trim();
		if (CommonUtils.isEmpty(isExpAmt)) {
		    isExpAmt = "0";
		}
		param.put("isExpAmt", isExpAmt);
		
		String mpb= input.getCheckMultipleInOneInvoice();
		if (CommonUtils.isEmpty(mpb)) {
            mpb = "0";
        }
		
		param.put("checkMultipleInOneInvoice", mpb);
		 String deliverymail=input.getCheckDeliveryEmail();
	        if(CommonUtils.isEmpty(deliverymail)){
	            deliverymail="0";
	        }
	    param.put("checkDeliveryEmail", deliverymail);
		String cboAttn = CommonUtils.toString(input.getCboAttn_temp()).trim();
        if(CommonUtils.isEmpty(cboAttn)) {
            param.put("contactType", "  ");
        } else {
            param.put("contactType", cboAttn);
        }
        param.put("zipCode", CommonUtils.toString(input.getZipCode()).trim());
        param.put("countryCd", CommonUtils.toString(input.getCountryCd()).trim());
		updateDAO.execute(UPDATE_BIF_H, param);
		return true;
	}
	
	private boolean updateFileUpload(B_BIFS02Input input, Integer idAudit) {
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
		return true;
	}
	
	private void updateIsExpAmt(B_BIFS02Input input, Integer idAudit) {
		Map<String, Object> param = new HashMap<String, Object>();
		String isExpAmt = CommonUtils.toString(input.getChkExportAmount()).trim();
        if (CommonUtils.isEmpty(isExpAmt)) {
            isExpAmt = "0";
        }
		param.put("idRef", input.getIdRef());
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
