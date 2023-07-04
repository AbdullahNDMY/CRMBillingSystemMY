/*
 * @(#)B_BIF_S03SaveBLogic.java
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
import nttdm.bsys.bif.dto.B_BIFS03SaveInput;
import nttdm.bsys.bif.dto.B_BIF_Output;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;

import org.apache.struts.Globals;
import org.apache.struts.upload.FormFile;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS03SaveBLogic extends AbstractB_BIF_S03SaveBLogic {

	private static final String INSERT_BIF_H = "INSERT.B_BIF.S03.SQL001";
	private static final String INSERT_BIF_D = "INSERT.B_BIF.S03.SQL002";
	private static final String INSERT_SECTION = "INSERT.B_BIF.S03.SQL003";
	private static final String UPDATE_CUST_PLAN = "UPDATE.B_BIF.S03.SQL001";
	private static final String UPDATE_CUST_PLAN_H = "UPDATE.B_BIF.S03.SQL002";
	private static final String UPDATE_BAC_H = "UPDATE.B_BIF.S03.SQL003";
	private static final String SELECT_BILL_ACC = "SELECT.B_BIF.SQL_BILL_ACC";
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	private String[][] sec_group = new String[][]{{"BIF-CN","C000"},{"HCD-CN","C012"},{"BOP-CN","C011"}};
	
	private int maxFileSize = 0;

	public BLogicResult execute(B_BIFS03SaveInput input) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();

		B_BIF_Output out = new B_BIF_Output();
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		try	{
			//insert to BIF_H
			Map<String, Object> param = new HashMap<String, Object>();
			G_CDM_P01 cmd_p01 = new G_CDM_P01(queryDAO,getUpdateDAO(),input.getUvo().getId_user());
			String idRef = "";
			idRef = cmd_p01.getGenerateCode("BIFCN", input.getUvo().getId_user());
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
					idRef, "DS0", BillingSystemConstants.AUDIT_TRAIL_CREATED);
			
			input.setIdRef(idRef);
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
			param.put("REQ_USER", input.getUvo().getId_user());
			param.put("DATE_REQ", input.getRequestDate());
			param.put("USER_COMMENT", input.getUserComment());
			param.put("ID_STATUS", "DS0");
			param.put("ID_LOGIN", input.getUvo().getId_user());
			param.put("ID_AUDIT", idAudit);
			param.put("CUST_NAME", CommonUtils.toString(input.getCustName()).trim());
			String cboAttn = CommonUtils.toString(input.getCboAttn_temp()).trim();
			String isExpAmt = CommonUtils.toString(input.getExportAmount()).trim();
            if (CommonUtils.isEmpty(isExpAmt)) {
                isExpAmt = "0";
            }
	        if(CommonUtils.isEmpty(cboAttn)) {
	            param.put("CONTACT_TYPE", "  ");
	        } else {
	            param.put("CONTACT_TYPE", cboAttn);
	        }
            param.put("ZIP_CODE", CommonUtils.toString(input.getZipCode()).trim());
            param.put("COUNTRY_CD", CommonUtils.toString(input.getCountryCd()).trim());
            String mpb=input.getCheckMultipleInOneInvoice();
            if(CommonUtils.isEmpty(mpb)){
            	mpb="0";
            }
            param.put("checkMultipleInOneInvoice", mpb);
            String deliveryemail=input.getCheckDeliveryEmail();
            if(CommonUtils.isEmpty(deliveryemail)){
                deliveryemail="0";
            }
            param.put("checkDeliveryEmail", deliveryemail);
            param.put("isExpAmt", isExpAmt);
            String idCustPlan = input.getIdCustPlan();
            HashMap<String, Object> m = new HashMap<String, Object>();
			List<Map<String, Object>> billAccInfoList = queryDAO.executeForMapList(SELECT_BILL_ACC, idCustPlan);
			if (billAccInfoList!=null && 0<billAccInfoList.size()) {
				long bacDCount = CommonUtils.toLong(billAccInfoList.get(0).get("BAC_D_COUNT"));
				if (0==bacDCount) {
					param.put("term_day", "30");
					param.put("ctterm3_day", "30 Days");
				}else{
					 m.put("idBillAccount", billAccInfoList.get(0).get("ID_BILL_ACCOUNT"));
			         List<HashMap<String, Object>> billInfo = this.queryDAO.executeForObjectList("B_BAC.getDistBillRef", m);
			         param.put("term_day", billInfo.get(0).get("TERM_DAY"));
					 param.put("ctterm3_day", billInfo.get(0).get("TERM"));
						
				}
			} else {
			    param.put("term_day", "30");
                param.put("ctterm3_day", "30 Days");
			}
			param.put("emailTo", CommonUtils.toString(input.getEmailToAdd().toString()));
			param.put("emailCc", CommonUtils.toString(input.getEmailCcAdd().toString()));
			this.updateDAO.execute(INSERT_BIF_H, param);
			
			//insert to BIF_D
			String[] custPlanList = input.getIdCustPlan().split(":");
			param = new HashMap<String, Object>();
			param.put("ID_REF", input.getIdRef());
			param.put("ID_LOGIN", input.getUvo().getId_user());
			param.put("ID_AUDIT", idAudit);
			for (String custPlan : custPlanList) {
				if (!"".equals(custPlan)) {
					param.put("ID_CUST_PLAN", custPlan);
					this.updateDAO.execute(INSERT_BIF_D, param);
				}
			}
			
			//insert to T_SECTION_H
			param = new HashMap<String, Object>();
			param.put("ID_REF", input.getIdRef());
			param.put("ID_LOGIN", input.getUvo().getId_user());
			param.put("ID_AUDIT", idAudit);
			for (int i = 0; i < sec_group.length; i++) {
				String section_group = sec_group[i][0];
				String section_no = sec_group[i][1];
				param.put("SECTION_GROUP", section_group);
				param.put("SECTION_NO", section_no);
				this.updateDAO.execute(INSERT_SECTION, param);
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
			BIFAttachmentProccessBLogic att = new BIFAttachmentProccessBLogic(this.queryDAO, this.updateDAO, this.updateDAONuked);
			att.setIdAudit(idAudit);
			//upload file for <Termination Form>
			FormFile[] listFile = input.getListFileTFBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DTMF");
			
			//upload file for <Termination Letter>
			listFile = input.getListFileTLBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DTML");
			
			//upload file for <Invoice>
			listFile = input.getListFileIVBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DINV");
			
			//upload file for <MRTG>
			listFile = input.getListFileMRBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DMRT");
			
			//upload file for <Purchase Order>
			listFile = input.getListFilePOBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DPCO");
			
			//upload file for <Agreement>
			listFile = input.getListFileAGBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DAGM");
			
			//upload file for <Change Request Form>
			listFile = input.getListFileCRBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DCRF");
			
			//upload file for <Others>
			listFile = input.getListFileOTBif();
			att.setListFile(listFile);
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef(), "DOTH");
			
			
			//insert to CUST_PLAN
			
			param = new HashMap<String, Object>();
			param.put("PLAN_STATUS", "PS2");
			param.put("ID_LOGIN", input.getUvo().getId_user());
			param.put("isExpAmt", isExpAmt);
			param.put("ID_AUDIT", idAudit);
			for (String custPlan : custPlanList) {
			    if (!"".equals(custPlan)) {
					param.put("ID_CUST_PLAN", custPlan);
					this.updateDAO.execute(UPDATE_CUST_PLAN, param);
					this.updateDAO.execute(UPDATE_CUST_PLAN_H, param);
					//this.updateDAO.execute(UPDATE_BAC_H, param);
				}
			}
//			this.updateIsExpAmt(input, idAudit);
			Map<String, Object> screenObject = new HashMap<String, Object>();
			//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC003"));
			out.setMessage("info.ERR2SC003");
			out.setIdRef(paddingRightSpace(input.getIdRef(), 20));
			out.setScreenObject(screenObject);
			out.setAction(B_BIFS03SaveInput.VIEW_ACTION);
			result.setResultObject(out);
			result.setResultString(B_BIFS03SaveInput.VIEW_ACTION);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		} catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
		return result;
	}
	
//	private void updateIsExpAmt(B_BIFS03SaveInput input, Integer idAudit) {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("idRef", input.getIdRef());
//		String isExpAmt = "0";
//		if(input.getExportAmount() == null || input.getExportAmount().equals("0"))
//			isExpAmt = "0";
//		else
//			isExpAmt = input.getExportAmount();
//		param.put("isExpAmt", isExpAmt);
//		param.put("idAudit", idAudit);
//		updateDAO.execute("UPDATE.B_BIF.IS_DISPLAY_EXP_AMT", param);
//	}
	
	/**
     * padding right space
     * @param str
     * @param len
     * @return
     */
    private static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for(int i=str.length();i<len;i++) {
            sb.append(" ");
        }
        return sb.toString();
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