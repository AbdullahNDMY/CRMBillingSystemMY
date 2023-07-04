package nttdm.bsys.bif.blogic;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIFS02Output;
import nttdm.bsys.bif.dto.B_BIFS02_02Input;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;

import org.apache.struts.Globals;
import org.apache.struts.upload.FormFile;

public class B_BIFS02_02BLogic extends AbstractB_BIFS02_02BLogic{
	
	//private static final String SELECT_BIF_ID_REF = "SELECT.B_BIF.S02_02.SQL001";
	
	private static final String INSERT_BIF_BIF_H = "INSERT.B_BIF.S02_02.SQL001";
	private static final String INSERT_BIF_BIF_D = "INSERT.B_BIF.S02_02.SQL002";
	private static final String INSERT_BIF_T_SECTION_BIF = "INSERT.B_BIF.S02_02.SQL003";
	private static final String INSERT_BIF_T_SECTION_BOF = "INSERT.B_BIF.S02_02.SQL004";
	private static final String UPDATE_BIF_CUST_PLAN_D = "UPDATE.B_BIF.S02_02.SQL005";
	private static final String UPDATE_BIF_CUST_PLAN_H = "UPDATE.B_BIF.S02_02.SQL006";
	//private static final String UPDATE_BIF_BAC_H = "UPDATE.B_BIF.S02_02.SQL007";
	
	public static final String INSERT_T_DOC = "INSERT.B_BIF.S02_02.SQL006";
	public static final String SQL_GET_MAX_ID_DOC = "SELECT.B_BIF.SQL007";
	public static final String INSERT_T_WF_DOC = "INSERT.B_BIF.S02_02.SQL008";
	public static final String SELECT_BILL_ACC = "SELECT.B_BIF.SQL_BILL_ACC";
	
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	private int maxFileSize = 0;
	
	public BLogicResult execute(B_BIFS02_02Input input) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		
		String textOthers = input.getTxtOthers();
		String textOtherDays = input.getTxtOthersTady();
		if(!textOtherDays.equals("0")&&(textOthers.equals("")||textOthers == null)){
			input.setTxtOthers(textOtherDays+" Days");
		}else if(textOtherDays.equals("0")&&(textOthers.equals("")||textOthers == null)){
			input.setTxtOthers("");
		}
		
		try{ 
			B_BIFS02Output out = new B_BIFS02Output();
			Map<String, Object> param = new HashMap<String, Object>();
			G_CDM_P01 cmd_p01 = new G_CDM_P01(queryDAO,updateDAO,input.getUvo().getId_user());
			String idRef = "";
			if ("DN".equals(input.getBifType())) {
			    idRef = cmd_p01.getGenerateCode("BIFDN", input.getUvo().getId_user());
			} else {
			    idRef = cmd_p01.getGenerateCode("BIFNO", input.getUvo().getId_user());
			}
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo()
					.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
					idRef, "DS0", BillingSystemConstants.AUDIT_TRAIL_CREATED);
			input.setIdRef(idRef);
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
			param.put("addr4", ( "".equals(CommonUtils.toString(input.getZipCode()).trim())? "" : input.getZipCode() + ", ") + CommonUtils.toString(input.getCountry()).trim());
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
			HashMap<String, Object> m = new HashMap<String, Object>();
			String idCustPlan = input.getIdCustPlan();
			List<Map<String, Object>> billAccInfoList = queryDAO.executeForMapList(SELECT_BILL_ACC, idCustPlan);
			if (billAccInfoList!=null && 0<billAccInfoList.size()) {
				long bacDCount = CommonUtils.toLong(billAccInfoList.get(0).get("BAC_D_COUNT"));
				if (0==bacDCount) {
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
					 m.put("idBillAccount", billAccInfoList.get(0).get("ID_BILL_ACCOUNT"));
			         List<HashMap<String, Object>> billInfo = this.queryDAO.executeForObjectList("B_BAC.getDistBillRef", m);
		        	 ctterm3_day = (String) billInfo.get(0).get("TERM");
					 term_day = billInfo.get(0).get("TERM_DAY").toString();
				}
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
			String cboAttn = CommonUtils.toString(input.getCboAttn_temp()).trim();
			
			String isExpAmt = CommonUtils.toString(input.getChkExportAmount()).trim();
			if (CommonUtils.isEmpty(isExpAmt)) {
			    isExpAmt = "0";
			}
			
			if(CommonUtils.isEmpty(cboAttn)) {
			    param.put("contactType", "  ");
			} else {
			    param.put("contactType", cboAttn);
			}
			param.put("zipCode", CommonUtils.toString(input.getZipCode()).trim());
			param.put("countryCd", CommonUtils.toString(input.getCountryCd()).trim());
			param.put("isExpAmt", isExpAmt);
			
			String mbp=input.getCheckMultipleInOneInvoice();
			if(CommonUtils.isEmpty(mbp)){
				mbp="0";
			}
			param.put("checkMultipleInOneInvoice", mbp);
			String deliverymail=input.getCheckDeliveryEmail();
            if(CommonUtils.isEmpty(deliverymail)){
                deliverymail="0";
            }
            param.put("checkDeliveryEmail", deliverymail);
            param.put("emailTo", CommonUtils.toString(input.getEmailToAdd().toString()));
    		param.put("emailCc", CommonUtils.toString(input.getEmailCcAdd().toString()));
			updateDAO.execute(INSERT_BIF_BIF_H, param);
			
			
			
			String[] custPlanList = input.getIdCustPlan().split(":");
			Map<String, Object> paramD = new HashMap<String, Object>();
			paramD.put("idRef", input.getIdRef());
			paramD.put("idLogin", input.getUvo().getId_user());
			paramD.put("idAudit", idAudit);
			for (String custPlan : custPlanList) {
				if (!"".equals(custPlan)) {
					paramD.put("idCustPlan", custPlan);
					updateDAO.execute(INSERT_BIF_BIF_D, paramD);
				}
			}
			
			Map<String, Object> paramS = new HashMap<String, Object>();
			paramS.put("idRef", input.getIdRef());
			paramS.put("idLogin", input.getUvo().getId_user());
			paramS.put("idAudit", idAudit);
			updateDAO.execute(INSERT_BIF_T_SECTION_BIF, paramS);
			updateDAO.execute(INSERT_BIF_T_SECTION_BOF, paramS);
			
			
			
			Map<String, Object> paramPD = new HashMap<String, Object>();
			paramPD.put("idLogin", input.getUvo().getId_user());
			paramPD.put("idAudit", idAudit);
			paramPD.put("isExpAmt", isExpAmt);
			for (String custPlan : custPlanList) {
				if (!"".equals(custPlan)) {
					paramPD.put("idCustPlan", custPlan);
					updateDAO.execute(UPDATE_BIF_CUST_PLAN_D, paramPD);
					updateDAO.execute(UPDATE_BIF_CUST_PLAN_H, paramPD);
					//updateDAO.execute(UPDATE_BIF_BAC_H, paramPD);
				}
			}
			
//			Map<String, Object> param2 = new HashMap<String, Object>();
//			param2.put("idRef", input.getIdRef());
//			param2.put("idAudit", idAudit);
//			if(input.getChkExportAmount() == null || input.getChkExportAmount().equals(""))
//				param2.put("isExpAmt", "0");
//			else
//				param2.put("isExpAmt", input.getChkExportAmount());
//			updateDAO.execute("UPDATE.B_BIF.IS_DISPLAY_EXP_AMT", param2);
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
			BIFAttachmentBLogic att = new BIFAttachmentBLogic();
			att.setListFileOTBif(input.getListFileOTBif());
			att.setListFileQPBif(input.getListFileQPBif());
			att.setListFileSCBif(input.getListFileSCBif());
			att.setQueryDAO(queryDAO);
			att.setUpdateDAO(updateDAO);
			att.setUpdateDAONuked(updateDAONuked);
			att.setIdAudit(idAudit);
			
			att.insertAttachment(input.getUvo().getId_user(), input.getIdRef());
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			
			out.setIdRef(paddingRightSpace(input.getIdRef(), 20));
			//out.setIdCust(input.getIdCust());
			//out.setIdCustPlan(input.getIdCustPlan());
			//out.setBifType(input.getBifType());
			out.setAction(B_BIFS02Input.VIEW_ACTION);
			
			//return object
			result.setResultObject(out);
			
			//return message
			//result.setResultString("success");
			
			//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC003"));
			out.setMessage("info.ERR2SC003");
			
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);			
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
