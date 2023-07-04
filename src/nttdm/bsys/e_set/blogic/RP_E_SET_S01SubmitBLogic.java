/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : E_SET
 * SERVICE NAME   : E_SET_S01
 * FUNCTION       : Updating BLogic
 * FILE NAME      : RP_E_SET_S01BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.e_set.blogic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.bean.G_BIL_P01_Input;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.E_EXP_F01;
import nttdm.bsys.common.util.E_EXP_F02;
import nttdm.bsys.common.util.G_ALT_P04;
import nttdm.bsys.common.util.G_ALT_P05;
import nttdm.bsys.common.util.G_BAC_P01;
import nttdm.bsys.common.util.G_BIL_P01;
import nttdm.bsys.common.util.G_CSB_P01;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.dto.G_BAC_P01_Input;
import nttdm.bsys.common.util.dto.G_BAC_P01_Output;
import nttdm.bsys.e_set.bean.E_SET_S01_CheckInput;
import nttdm.bsys.e_set.bean.E_SET_S01_CheckOutput;
import nttdm.bsys.e_set.bean.E_SET_S03Bean;
import nttdm.bsys.e_set.dto.RP_E_SET_S01Input;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * RP_E_SET_S01SubmitBLogic<br>
 * <ul>
 * <li>A BLogic class to process updating Batch maintenance Settings
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class RP_E_SET_S01SubmitBLogic extends AbstractRP_E_SET_S01SubmitBLogic {
	private static final String CC = "CC";
	private static final String SD = "SD";
	private static final String GB = "GB";
	private static final String SA = "SA";
//	private static final String SS = "SS";
	private static final String CB = "CB";
	private static final String AR = "AR";
    private static final String BA = "BA";
	
	private String alertMode;
	private String frequencyMode = "Monthly";
	private String noOfMonth;
	private String dayOfMonth;
	private String timeHour;
	private String timeMinute;
	private String statementMonth;
	private String[] alertUsers;
	private Map<String, Object> parameter = new HashMap<String, Object>();
	
	private BLogicResult result = new BLogicResult();
	private BLogicMessages errors = new BLogicMessages();
	private BLogicMessages messages = new BLogicMessages();
	private Integer idAudit = null;

	public BLogicResult execute(final RP_E_SET_S01Input input) {
		result = new BLogicResult();
		errors = new BLogicMessages();
		messages = new BLogicMessages();
		parameter.put("idLogin", input.getUvo().getId_user());
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
		if (CommonUtils.notEmpty(input.getForward_save())) {
			try {
				//CC
			    List<Map<String, Object>> allmaintenance = queryDAO.executeForObjectList("E_SET.getAllBatchMaintenance", null);
				String ccreference="";
				String sdreference="";
				String gbreference="";
				String sareference="";
				String cbreference="";
				String arreference="";
				String bareference="";
				for (int k=0;k<allmaintenance.size();k++){
				    if(allmaintenance.get(k).get("BATCH_ID").toString().equals("CC")){
				        ccreference="CC:"+allmaintenance.get(k).get("BATCH_DESC").toString();
				    }
				    if(allmaintenance.get(k).get("BATCH_ID").toString().equals("SD")){
				        sdreference="SD:"+allmaintenance.get(k).get("BATCH_DESC").toString();
                    }
				    if(allmaintenance.get(k).get("BATCH_ID").toString().equals("GB")){
				        gbreference="GB:"+allmaintenance.get(k).get("BATCH_DESC").toString();
                    }
				    if(allmaintenance.get(k).get("BATCH_ID").toString().equals("SA")){
				        sareference="SA:"+allmaintenance.get(k).get("BATCH_DESC").toString();
                    }
				    if(allmaintenance.get(k).get("BATCH_ID").toString().equals("CB")){
				        cbreference="CB:"+allmaintenance.get(k).get("BATCH_DESC").toString();
                    }
				    if(allmaintenance.get(k).get("BATCH_ID").toString().equals("AR")){
				        arreference="AR:"+allmaintenance.get(k).get("BATCH_DESC").toString();
                    }
				    if(allmaintenance.get(k).get("BATCH_ID").toString().equals("BA")){
				        bareference="BA:"+allmaintenance.get(k).get("BATCH_DESC").toString();
                    }
				}
				
			    parameter.put("batchId", CC);
				idAudit = this.writeAuditTrail(input.getUvo().getId_user(), ccreference, BillingSystemConstants.AUDIT_TRAIL_EDITED);
				this.processCC(input);
				if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
					this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED,ccreference);
					this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
				}
				this.saveUser(alertUsers, CC);
				CommonUtils.auditTrailEnd(idAudit, true);//End CC Audit Trail
				
				//SD
				parameter.put("batchId", SD);
				idAudit = this.writeAuditTrail(input.getUvo().getId_user(), sdreference, BillingSystemConstants.AUDIT_TRAIL_EDITED);
				this.processSD(input);
				if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
					this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED,sdreference);
					this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
				}
				this.saveUser(alertUsers, SD);
				CommonUtils.auditTrailEnd(idAudit, true);//End SD Audit Trail
				
				//GB
				parameter.put("batchId", GB);
				idAudit = this.writeAuditTrail(input.getUvo().getId_user(), gbreference, BillingSystemConstants.AUDIT_TRAIL_EDITED);
				this.processGB(input);
				if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
					this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED,gbreference);
					this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
				}
				this.saveUser(alertUsers, GB);
				CommonUtils.auditTrailEnd(idAudit, true);//End GB Audit Trail
				
				//SA
				parameter.put("batchId", SA);
				idAudit = this.writeAuditTrail(input.getUvo().getId_user(), sareference, BillingSystemConstants.AUDIT_TRAIL_EDITED);
				this.processSA(input);
				if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
					this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED,sareference);
					this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
				}
				this.saveUser(alertUsers, SA);
				CommonUtils.auditTrailEnd(idAudit, true);//End SA Audit Trail
				
//				//SS
//				parameter.put("batchId", SS);
//				idAudit = this.writeAuditTrail(input.getUvo().getId_user(), SS, BillingSystemConstants.AUDIT_TRAIL_EDITED);
//				this.processSS(input);
//				if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
//					this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED);
//					this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
//				}
//				this.saveUser(alertUsers, SS);
//				CommonUtils.auditTrailEnd(idAudit, true);//End SS Audit Trail
				
				//CB
				parameter.put("batchId", CB);
				idAudit = this.writeAuditTrail(input.getUvo().getId_user(), cbreference, BillingSystemConstants.AUDIT_TRAIL_EDITED);
				this.processCB(input);
				if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
					this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED,cbreference);
					this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
				}
				this.saveUser(alertUsers, CB);
				CommonUtils.auditTrailEnd(idAudit, true);//End CB Audit Trail
				
				//AR
				if (!"SG".equals(input.getSystemBase()))
				{
					parameter.put("batchId", AR);
					idAudit = this.writeAuditTrail(input.getUvo().getId_user(), arreference, BillingSystemConstants.AUDIT_TRAIL_EDITED);
					this.processAR(input);
					if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
						this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED,arreference);
						this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
					}
					this.saveUser(alertUsers, AR);
					CommonUtils.auditTrailEnd(idAudit, true);//End AR Audit Trail
				}
				
				// BA
				parameter.put("batchId", BA);
				idAudit = this.writeAuditTrail(input.getUvo().getId_user(), bareference, BillingSystemConstants.AUDIT_TRAIL_EDITED);
				this.processBA(input);
				if (this.updateDAO.execute("E_SET.updateBatchMaintenance", parameter) == 0) {
				    this.updateLog(idAudit, BillingSystemConstants.AUDIT_TRAIL_CREATED,bareference);
				    this.updateDAO.execute("E_SET.addBatchMaintenance", parameter);
				}
				this.saveUser(alertUsers, BA);
				CommonUtils.auditTrailEnd(idAudit, true);//End BA Audit Trail
				
				//save success
				messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC003"));
			} catch(Exception e) {
				//save fail
				errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC004"));
			}
		} 
		else {
		    // Check batch RUN_STATUS before start new thread to run.
            Date receiveRunDate = CommonUtils.toDate(input.getEsetRunDateValue(), new SimpleDateFormat("dd/MM/yyyy"), new Date());
            final Date runDate = receiveRunDate;
            
            E_SET_S03Common e_set_s03Common = new E_SET_S03Common(queryDAO);
            // CC. Credit Card Alert Generation (on Expiry Date)
            if (CommonUtils.notEmpty(input.getForward_ccRun())) {
                E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(CC);
                String retStatus = e_set_s03Bean.getRetStatus();
                if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
                    String retMsg = e_set_s03Bean.getRetMsg();
                    String batchId = e_set_s03Bean.getBatchId();
                    if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] { batchId }));
                    } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
                    }
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
            } else if (CommonUtils.notEmpty(input.getForward_sdRun())) {
                // SD. Service Date Alert Generation (on Due Date)
                E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(SD);
                String retStatus = e_set_s03Bean.getRetStatus();
                if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
                    String retMsg = e_set_s03Bean.getRetMsg();
                    String batchId = e_set_s03Bean.getBatchId();
                    if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] { batchId }));
                    } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
                    }
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
            } else if (CommonUtils.notEmpty(input.getForward_gbRun())) {
                // GB. Generate Billing
                G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
                G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
                gCsbP01CheckInput.setMessageParam1("execute");
                gCsbP01CheckInput.setMessageParam2("Generate Billing");
                gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
                G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
                boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
                
                if (!resultFlg) {
                    String errorMsg = gCsbP01CheckOutput.getMessageContext();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
                
                E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(GB);
                String retStatus = e_set_s03Bean.getRetStatus();
                if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
                    String retMsg = e_set_s03Bean.getRetMsg();
                    String batchId = e_set_s03Bean.getBatchId();
                    if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] { batchId }));
                    } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
                    }
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
            } else if (CommonUtils.notEmpty(input.getForward_arRun())) {
                // AR. PeopleSoft AR Export
                E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(AR);
                String retStatus = e_set_s03Bean.getRetStatus();
                if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
                    String retMsg = e_set_s03Bean.getRetMsg();
                    String batchId = e_set_s03Bean.getBatchId();
                    if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] { batchId }));
                    } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
                    }
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
            } else if (CommonUtils.notEmpty(input.getForward_cbRun())) {
                // CB. Auto Offset Cash Book Against Invoice/ Debit Note
                //CITI_CHECKING
                E_SET_S01_Check eSetS01Check = new E_SET_S01_Check(queryDAO);
                E_SET_S01_CheckInput eSetS01CheckInput = new E_SET_S01_CheckInput();
                eSetS01CheckInput.setIdBatchType(E_SET_S01_Check.ID_BATCH_TYPE_G_CIT_P01);
                eSetS01CheckInput.setMessageParam1("execute");
                eSetS01CheckInput.setMessageParam2("Batch Auto Offset Cash Book");
                eSetS01CheckInput.setMessageParam3("Batch Export Citibank");
                E_SET_S01_CheckOutput eSetS01CheckOutput = eSetS01Check.execute(eSetS01CheckInput);
                boolean resultFlg = eSetS01CheckOutput.isResultFlg();
                
                if (!resultFlg) {
                    String errorMsg = eSetS01CheckOutput.getMessageContext();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
                //GIRO_CHECKING
                eSetS01CheckInput = new E_SET_S01_CheckInput();
                eSetS01CheckInput.setIdBatchType(E_SET_S01_Check.ID_BATCH_TYPE_G_GIR_P01);
                eSetS01CheckInput.setMessageParam1("execute");
                eSetS01CheckInput.setMessageParam2("Batch Auto Offset Cash Book");
                eSetS01CheckInput.setMessageParam3("Batch Export SMBC Giro");
                eSetS01CheckOutput = eSetS01Check.execute(eSetS01CheckInput);
                resultFlg = eSetS01CheckOutput.isResultFlg();
                
                if (!resultFlg) {
                    String errorMsg = eSetS01CheckOutput.getMessageContext();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
                //IMPORT_SINGPOST_CHECKING
                eSetS01CheckInput = new E_SET_S01_CheckInput();
                eSetS01CheckInput.setIdBatchType(E_SET_S01_Check.ID_BATCH_TYPE_G_SGP_P02);
                eSetS01CheckInput.setMessageParam1("execute");
                eSetS01CheckInput.setMessageParam2("Batch Auto Offset Cash Book");
                eSetS01CheckInput.setMessageParam3("Batch Import SingPost");
                eSetS01CheckOutput = eSetS01Check.execute(eSetS01CheckInput);
                resultFlg = eSetS01CheckOutput.isResultFlg();
                
                if (!resultFlg) {
                    String errorMsg = eSetS01CheckOutput.getMessageContext();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
                
                E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(CB);
                String retStatus = e_set_s03Bean.getRetStatus();
                
                if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
                    String retMsg = e_set_s03Bean.getRetMsg();
                    String batchId = e_set_s03Bean.getBatchId();
                    if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] { batchId }));
                    } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
                    }
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
            } else if (CommonUtils.notEmpty(input.getForward_baRun())) {
                // BA. Update Billing Account Total Amount Due
                E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(BA);
                String retStatus = e_set_s03Bean.getRetStatus();
                if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
                    String retMsg = e_set_s03Bean.getRetMsg();
                    String batchId = e_set_s03Bean.getBatchId();
                    if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] { batchId }));
                    } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
                    }
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
            } else if (CommonUtils.notEmpty(input.getForward_saRun())) {
                //SA. Statement of Accounts
                G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
                G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
                gCsbP01CheckInput.setMessageParam1("execute");
                gCsbP01CheckInput.setMessageParam2("Statement of Accounts");
                gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
                G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
                boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
                
                if (!resultFlg) {
                    String errorMsg = gCsbP01CheckOutput.getMessageContext();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
                
                E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(SA);
                String retStatus = e_set_s03Bean.getRetStatus();
                if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
                    String retMsg = e_set_s03Bean.getRetMsg();
                    String batchId = e_set_s03Bean.getBatchId();
                    if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] {batchId}));
                    } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
                    }
                    result.setResultObject(input);
                    result.setErrors(errors);
                    result.setMessages(messages);
                    result.setResultString("success");
                    return result;
                }
                
                if (CommonUtils.isEmpty(input.getSApopupClickYesFlg())){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(runDate);
                    String stmtMonth = CommonUtils.toString(input.getSAStatementMonth()).trim();
                    if ("0".equals(stmtMonth)) {
                        calendar.add(Calendar.MONTH, -1);
                    }
                    Date excuteRunDate = calendar.getTime();
                    
                    String DATE_FORMAT_YYYY = "yyyy";
                    String DATE_FORMAT_MM = "MM";
                    SimpleDateFormat sdfYYYY = new SimpleDateFormat(DATE_FORMAT_YYYY);
                    SimpleDateFormat sdfMM = new SimpleDateFormat(DATE_FORMAT_MM);
                    String stmt_date_yyyy = sdfYYYY.format(excuteRunDate);
                    String stmt_date_mm = sdfMM.format(excuteRunDate);

                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("YEARMONTH", stmt_date_yyyy + stmt_date_mm);
                    Integer totalRow = this.getQueryDAO().executeForObject("E_SET.getStatementCount", param, Integer.class);
                    
                    if (CommonUtils.toInteger(totalRow).intValue() > 0) {
                        String enMM = CommonUtils.getShortMonthName(Integer.parseInt(stmt_date_mm));
                        StringBuffer popupInfo = new StringBuffer();
                        popupInfo.append("Warning<br/>")
                                    .append("Statement was").append(" already generated for month of ")
                                    .append(enMM).append(" ").append(stmt_date_yyyy).append("<br/>")
                                    .append("Do you want to proceed?<br/>")
                                    .append("Click Yes to proceed, No to cancel.");
                        
                        input.setSAPopupInfo(popupInfo.toString());

                        result.setResultObject(input);
                        result.setErrors(errors);
                        result.setMessages(messages);
                        result.setResultString("success");
                        return result;
                    }
                } else {
                    input.setSARunDateEntry("");
                    input.setSAPopupInfo("");
                    input.setSApopupClickYesFlg("");
                }
            }
			
            final String custType = this.parseCustomerType(input.getCBCustomerType());
			Thread t = new Thread() {
                public void run() {
                    
                    if (CommonUtils.notEmpty(input.getForward_ccRun())) {
                        // CC
                        G_ALT_P04 gAltP04 = new G_ALT_P04(queryDAO, updateDAO);
                        gAltP04.execute(input.getUvo());
                        //recode who run
                        Integer idbatch=gAltP04.getIdBatch();
                        recodeWhoRun(input.getUvo().getId_user(),"CC",idbatch);
                    } else if (CommonUtils.notEmpty(input.getForward_sdRun())) {
                        // SD
                        G_ALT_P05 gAltP05 = new G_ALT_P05(queryDAO, updateDAO);
                        gAltP05.execute(input.getUvo());
                        //recode who run
                        Integer idbatch=gAltP05.getIdBatch();
                        recodeWhoRun(input.getUvo().getId_user(),"SD",idbatch);
                    } else if (CommonUtils.notEmpty(input.getForward_gbRun())) {
                        // GB
                        G_BIL_P01_Input gbInput = new G_BIL_P01_Input();
                        gbInput.setModuleId("E");
                        gbInput.setUserId(input.getUvo().getId_user());
                        gbInput.setRunning_date(runDate);
                        //set who run the action
                        gbInput.setRunFrom("BatchScreen");
                        // #192 Start
                        gbInput.setBillingOption(input.getBillingOption());
                        // #192 End
                        G_BIL_P01 gBilP01 = new G_BIL_P01(queryDAO, updateDAO);
                        gBilP01.execute(gbInput);
                        //recode who run
                        Integer idbatch=gBilP01.getIdBatch();
                        recodeWhoRun(input.getUvo().getId_user(),"GB",idbatch);
                    } else if (CommonUtils.notEmpty(input.getForward_saRun())) {
                        // SA
                        E_EXP_F02 eExpF02 = new E_EXP_F02();
                        eExpF02.setQueryDAO(queryDAO);
                        eExpF02.setUpdateDAO(updateDAO);
                        eExpF02.setEset_rundate(runDate);
                        eExpF02.setCurStmtMonth(CommonUtils.toString(input.getSAStatementMonth()));
                        eExpF02.setBatch("E");
                        eExpF02.excute(null);
                        //recode who run
                        Integer idbatch=eExpF02.getIdBatch();
                        recodeWhoRun(input.getUvo().getId_user(),"SA",idbatch);
                        
                    } else if (CommonUtils.notEmpty(input.getForward_ssRun())) {
                        //in enhancement
                    } else if (CommonUtils.notEmpty(input.getForward_cbRun())) {
                        //CB. in enhancement
                        G_CSB_P01 gCsbP01 = new G_CSB_P01();
                        gCsbP01.setQueryDAO(queryDAO);
                        gCsbP01.setUpdateDAO(updateDAO);
                        gCsbP01.setUvo(input.getUvo());
                        gCsbP01.setCustomerType(custType);
                        gCsbP01.execute();
                        //recode who run
                        Integer idbatch=gCsbP01.getIdBatch();
                        recodeWhoRun(input.getUvo().getId_user(),"CB",idbatch);
                    } else if (CommonUtils.notEmpty(input.getForward_arRun())) {
                        // AR
                        E_EXP_F01 eExpF01 = new E_EXP_F01();
                        eExpF01.setQueryDAO(queryDAO);
                        eExpF01.setUpdateDAO(updateDAO);
                        eExpF01.setUvo(input.getUvo());
                        eExpF01.setEsetRundate(runDate);
                        eExpF01.e_exp_execute();
                        //recode who run
                        Integer idbatch=eExpF01.getIdBatch();
                        recodeWhoRun(input.getUvo().getId_user(),"AR",idbatch);
                    } else if (CommonUtils.notEmpty(input.getForward_baRun())) {
                        // BA
                        //ApplicationContext context = ApplicationContextProvider.getApplicationContext();
                        //G_BAC_P01 gBacP01 = (G_BAC_P01)context.getBean("G_BAC_P01");
                        G_BAC_P01 gBacP01 = new G_BAC_P01();
                        gBacP01.setQueryDAO(queryDAO);
                        gBacP01.setUpdateDAO(updateDAO);
                        G_BAC_P01_Input param = new G_BAC_P01_Input();
                        G_BAC_P01_Output outputDTO = new G_BAC_P01_Output();
                        param.setType("E");
                        gBacP01.execute(param, outputDTO);
                        //recode who run
                        Integer idbatch=gBacP01.getIdBatch();
                        recodeWhoRun(input.getUvo().getId_user(),"BA",idbatch);
                    }
                }
            };
            t.start();
            runMsg();
		}

        input.setSARunDateEntry("");
        input.setSAPopupInfo("");
        input.setSApopupClickYesFlg("");
        result.setResultObject(input);
		result.setErrors(errors);
		result.setMessages(messages);
		result.setResultString("success");
		return result;
	}

	private void processCC(RP_E_SET_S01Input input) {
		alertMode = input.getCCAlertMode();
		frequencyMode = null;
		noOfMonth = input.getCCMonths();
		dayOfMonth = input.getCCDay();
		timeHour = input.getCCHour();
		timeMinute = input.getCCMinute();
		alertUsers = input.getCCUsers();
        statementMonth = "0";
		putParameter();
	}
	
	private void processSD(RP_E_SET_S01Input input) {
		alertMode = input.getSDAlertMode();
		frequencyMode = input.getSDMode();
		noOfMonth = input.getSDMonths();
		dayOfMonth = input.getSDDay();
		timeHour = input.getSDHour();
		timeMinute = input.getSDMinute();
		alertUsers = input.getSDUsers();
        statementMonth = "0";
		putParameter();
	}
	
	private void processGB(RP_E_SET_S01Input input) {
		alertMode = null;
		frequencyMode = null;
		noOfMonth = input.getGBMonths();
		dayOfMonth = input.getGBDay();
		timeHour = input.getGBHour();
		timeMinute = input.getGBMinute();
		alertUsers = input.getGBUsers();
        statementMonth = "0";
		putParameter();
	}
	
	private void processSA(RP_E_SET_S01Input input) {
		alertMode = null;
		frequencyMode = null;
		noOfMonth = input.getSAMonths();
		dayOfMonth = input.getSADay();
		timeHour = input.getSAHour();
		timeMinute = input.getSAMinute();
		alertUsers = input.getSAUsers();
		statementMonth = input.getSAStatementMonth();
		putParameter();
	}
	
//	private void processSS(RP_E_SET_S01Input input) {
//		noOfMonth = input.getSSMonths();
//		dayOfMonth = input.getSSDay();
//		timeHour = input.getSSHour();
//		timeMinute = input.getSSMinute();
//		alertUsers = input.getSSUsers();
//		putParameter();
//	}
	
	private void processCB(RP_E_SET_S01Input input) {
        alertMode = this.parseCustomerType(input.getCBCustomerType());
		frequencyMode = null;
		noOfMonth = input.getCBMonths();
		dayOfMonth = input.getCBDay();
		timeHour = input.getCBHour();
		timeMinute = input.getCBMinute();
		alertUsers = input.getCBUsers();
        statementMonth = "0";
		putParameter();
	}
	
	private void processAR(RP_E_SET_S01Input input) {
		alertMode = null;
		frequencyMode = null;
		noOfMonth = input.getARMonths();
		dayOfMonth = input.getARDay();
		timeHour = input.getARHour();
		timeMinute = input.getARMinute();
		alertUsers = input.getARUsers();
        statementMonth = "0";
		putParameter();
	}
	
    private void processBA(RP_E_SET_S01Input input) {
        alertMode = null;
        frequencyMode = null;
        noOfMonth = "";
        dayOfMonth = "";
        timeHour = "";
        timeMinute = "";
        alertUsers = input.getBAUsers();
        statementMonth = "0";
        putParameter();
    }	
	private void putParameter() {
		parameter.put("alertMode", alertMode);
		parameter.put("frequencyMode", frequencyMode);
		parameter.put("noOfMonth", noOfMonth);
		parameter.put("dayOfMonth", dayOfMonth);
		parameter.put("timeHour", timeHour);
		parameter.put("timeMinute", timeMinute);
		parameter.put("idAudit", idAudit);
        parameter.put("statementMonth", statementMonth);
	}
	
	private void saveUser(String[] users, String batchID) {
		Map<String, Object>[] userAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserAlert", parameter);
		int i = 0;
		for (String user : users) {
			if (CommonUtils.notEmpty(user)) {
				//String alertUser = CommonUtils.replace(user, "^[a-zA-Z]+_", "");
				//String userType = CommonUtils.replace(user, "_[a-zA-Z0-9]+$", "");
							
				String userType = user.substring(0,user.indexOf("_"));
				String alertUser = user.substring(user.indexOf("_")+1);				
				
				parameter.put("alertUser", alertUser);
				parameter.put("userType", userType);
				if(userAlerts.length <= i) {
					this.updateDAONuked.execute("E_SET.addBatchUserAlert", parameter);
				}
				else {
					parameter.put("batchUserId", userAlerts[i].get("BATCH_USER_ID"));
					if (this.updateDAO.execute("E_SET.updateBatchUserAlert", parameter) == 0)
						this.updateDAONuked.execute("E_SET.addBatchUserAlert", parameter);
				}				
			} else if(i < userAlerts.length){
				// Delete alert user
				parameter.put("batchUserId", userAlerts[i].get("BATCH_USER_ID"));
				this.updateDAO.execute("E_SET.deleteBatchUserAlert", parameter);
			}
			i++;
		}
	}
	
	private void runMsg() {
		messages.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC041"));
	}
	
	/**
	 * Audit Trail
	 */
	private Integer writeAuditTrail(String idUser, String reference, String action) {
		return CommonUtils.auditTrailBegin(idUser, BillingSystemConstants.MODULE_E, 
				BillingSystemConstants.SUB_MODULE_E_SET, reference, null, action);
	}
	
	private void updateLog(Integer idAudit, String action,String reference) {
		AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
		auditHeader.setIdAudit(idAudit);
		auditHeader.setAction(action);
		auditHeader.setReference(reference);
		CommonUtils.auditTrailUpdate(auditHeader);
	}
	
	/**
	 * Parse customer type to CHAR(1) mode, CONS:0 / CORP:1.
	 * 
	 * @param customerType CONS/CORP
	 * @return
	 */
	private String parseCustomerType(String customerType) {
	    
	    String custType = "";
	    
	    if ("CONS".equalsIgnoreCase(customerType)) {
	        custType = "0";
        } else if ("CORP".equalsIgnoreCase(customerType)) {
            custType = "1";
        }
	    
	    return custType;
	}
	
	/**
	 * Audit Trail
	 *     @param userid 
	 *     @param batchtype 
	 *     @param idbatch 
	 */
	private void recodeWhoRun(String userid,String batchtype,Integer idbatch) {
	    
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("batchId", batchtype);
        Map<String, Object> batchNo = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
        parameter.put("idBatch", idbatch);
        Map<String, Object> batchStatus = queryDAO.executeForMap("E_SET.getBatchStatus", parameter);
        //Reference
        String reference="";
        String status="";
        if(batchNo != null) {
            reference=batchtype+":"+batchNo.get("BATCH_DESC").toString();
        }
        //Status
        if(batchStatus!=null){
            String statusid=batchStatus.get("STATUS").toString();
            if(statusid.equals("1")){
                status="In-Progress";
            }
            if(statusid.equals("2")){
                status="Success";
            }
            if(statusid.equals("3")){
                status="Fail";
            }
        }
        //write log
        Integer idAudit = CommonUtils.auditTrailBegin(userid, 
                BillingSystemConstants.MODULE_E, 
                BillingSystemConstants.SUB_MODULE_E_SET,
                reference, status, BillingSystemConstants.AUDIT_TRAIL_CREATED);
    }
}