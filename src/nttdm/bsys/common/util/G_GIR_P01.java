package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.common.bean.G_CSB_P02_Input;
import nttdm.bsys.common.bean.G_GIR_P01_GIRO_Bean;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitOutput;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

public class G_GIR_P01 extends AbstractGlobalProcess<RP_E_MEX_GR1SubmitInput, RP_E_MEX_GR1SubmitOutput> {

	private static final String STATUS_SUCCESS = "0";
	private static final String STATUS_FAILED = "1";
	private static final String STATUS_INPROCESS = "2";
	private static final String ID_BATCH_TYPE = "G_GIR_P01";
	private static final String PAY_METHOD = "GR";
	
	private static final String INSERT_BatchRecordErr = "INSERT.G_GIR_P01.001";
	private static final String INSERT_ErrorLog = "INSERT.G_GIR_P01.002";
	private static final String INSERT_BatchHeader = "INSERT.G_GIR_P01.003";
	private static final String INSERT_CashBook = "INSERT.G_GIR_P01.004";

	private static final String GET_ProcessStatus = "SELECT.G_GIR_P01.001";
	private static final String GET_FileName = "SELECT.G_GIR_P01.002";
	private static final String GET_BillAcc = "SELECT.G_GIR_P01.003";
	private static final String GET_GiroDetails = "SELECT.G_GIR.P01.004";
	private static final String GET_ComAccNo = "SELECT.G_GIR.P01.005";
	private static final String GET_IdRef = "SELECT.G_GIR.P01.006";
	
	private static final String UPDATE_ProcessStatus = "UPDATE.G_GIR_P01.001";
	private static final String UPDATE_FileName = "UPDATE.G_GIR_P01.002";
	private static final String UPDATE_TotalAmtDue = "UPDATE.G_GIR_P01.003";
	
	// getting parameter passed by caller method
	private boolean SCR;
	private String bankAcc;
	private String GIRO_DEDUCT_DAY;
	private String idLogin;

	private BLogicMessages messages = null;
	private BLogicMessages errors = null;
	private GlobalProcessResult result = null;
	private Map<String, Object> parameter = null;
	private Map<String, Object> latestProcess = null;
	private BillingSystemUserValueObject uvo = null;
	private String DATE_TRANS = null;
	//TODO
	// Audit Trail
	private String auditIdModule;
	private String auditIdSubModule;
	private String auditReference;
	private String auditStatus;

	public G_GIR_P01(QueryDAO queryDAO, UpdateDAO updateDAO, UpdateDAOiBatisNuked updateDAONuked){
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
		this.updateDAONuked = updateDAONuked;
	}
	public G_GIR_P01(){}

	public GlobalProcessResult execute(RP_E_MEX_GR1SubmitInput param, RP_E_MEX_GR1SubmitOutput outputDTO) {

		this.uvo = param.getUvo();		
		this.idLogin = uvo.getId_user();
		this.messages = new BLogicMessages();
		this.errors = new BLogicMessages();
		this.result = new GlobalProcessResult();
		this.parameter = new HashMap<String, Object>();
		this.SCR = param.isScr();
		this.bankAcc = param.getBankAcc();
		this.GIRO_DEDUCT_DAY = param.getDeductionDate();
		boolean inProcess = false;
		boolean toCheckExportPath = false;
		boolean createBatchHeader = false;	
		Integer idGirExpBatch = null;
		
		//setAuditIdModule(BillingSystemConstants.MODULE_B);
		//setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_B_BAC);
		//TODO
		result.setParameter(this.parameter);
		setAuditIdModule(BillingSystemConstants.MODULE_E);
		setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_GR1);
		
		Integer auditId = CommonUtils.auditTrailBegin(this.idLogin,
				this.auditIdModule, this.auditIdSubModule,
				this.auditReference, this.auditStatus,
				BillingSystemConstants.AUDIT_TRAIL_CREATED);
		
		DateFormat exMonth = new SimpleDateFormat("MM");
		DateFormat exYearLong = new SimpleDateFormat("yyyy");
		DateFormat exYear = new SimpleDateFormat("yy");
		DateFormat grDate = new SimpleDateFormat("DD");
		Date executeMonth = null;
		Date executeYear = null;
		Date executeDate = null;
		try {
			executeMonth = exMonth.parse(param.getClosingMonth());
			executeYear = exYearLong.parse(param.getClosingYear());	
			executeDate = grDate.parse(GIRO_DEDUCT_DAY);
		} catch (ParseException e) {
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		}
		String MM = exMonth.format(executeMonth);
		String yyyy = exYearLong.format(executeYear);
		String yy = exYear.format(executeYear);
		String giroDate = yyyy+MM+grDate.format(executeDate);
		parameter.put("closeMonthyear", MM + "/" + yyyy);
		parameter.put("executeMonth", MM + yyyy);
		DATE_TRANS = yyyy+"/"+MM+"/"+ grDate.format(executeDate);
		// error checking for auditID
		if(auditId == null){
			parameter = this.batchRecordError(parameter);
			doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC074", "errors.ERR1SC074", "");
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		} else{
			parameter.put("ID_AUDIT", auditId);
			parameter.put("idLogin", idLogin);
			parameter.put("idBatchType", ID_BATCH_TYPE);	
			
		    parameter.put("RUN_STATUS", "Y");
		    updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
		        
			BillingSystemSettings setting = new BillingSystemSettings(queryDAO);

			// 1.0 Check any "In Process" status
			List<Map<String, Object>> processStatus = this.queryDAO.executeForMapList(GET_ProcessStatus, null);
			if(!processStatus.isEmpty()) {
				latestProcess = processStatus.get(0);
				parameter.put("idBatch", latestProcess.get("ID_GIR_EXP_BATCH"));
				parameter.put("idBatchRefNo", latestProcess.get("ID_GIR_EXP_BATCH"));
				inProcess = true;
			}else{		
				toCheckExportPath = true;
			}
			//TODO
			if(inProcess == true) {
				// 2.0 Check if process has exceed BATCH_TIME_INTERVAL
				if(CommonUtils.isTimeInterval(latestProcess.get("DATE_UPDATED_CHAR").toString(), setting.getBatchTimeInterval()*1000)) {
					// 3.0 and 4.0				
					doLogging(STATUS_FAILED, this.SCR, "", "errors.ERR1BT016", "");
					toCheckExportPath = true;
				} else {
					parameter = this.batchRecordError(parameter);
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC059", "errors.ERR1BT020", setting.getBatchTimeInterval()/60);
					// exit current method
					result.setErrors(errors);
					result.setMessages(messages);
			        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
			        String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
			        String currentId = CommonUtils.toString(parameter.get("idBatch"));
			        if(lastBatchId.equals(currentId)||currentId.equals("")){
			              parameter.put("RUN_STATUS", "N");
			              updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
			        }
					return result;
				}
			} 			
			
			boolean FileExportPath = false;	
			String exportPath = "";
			if(toCheckExportPath == true){
				if (setting.getBatchGGirP01() == null || setting.getBatchGGirP01().trim().equals("")) {			
					parameter = this.batchRecordError(parameter);
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC075", "errors.ERR1SC075", "");
					result.setErrors(errors);
					result.setMessages(messages);
			        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
			        String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
			        String currentId = CommonUtils.toString(parameter.get("idBatch"));
			        if(lastBatchId.equals(currentId)||currentId.equals("")){
			              parameter.put("RUN_STATUS", "N");
			              updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
			        }
			        
					return result;		
				} else{
					//get exportPath from M_GSET_D.BATCH_G_GIR_P01
					exportPath = setting.getBatchGGirP01();	
					FileExportPath = true;
				}
			}
			
			// 5.0 check if exportPath is accessible
			if(FileExportPath == true){	
				File folder = new File(exportPath);
				if(!folder.exists()){
					
					//TODO
					parameter = this.batchRecordError(parameter);
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC076", "errors.ERR1SC076", "");
					// exit current method
					result.setErrors(errors);
					result.setMessages(messages);
			        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
			        String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
			        String currentId = CommonUtils.toString(parameter.get("idBatch"));
			        if(lastBatchId.equals(currentId)||currentId.equals("")){
			              parameter.put("RUN_STATUS", "N");
			              updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
			        }
					return result;
			    } 		
				if(folder.isDirectory() && folder.canRead() && folder.canWrite()) {		
					
						String fileNameTxt = "GIROdata_" + MM + yy + ".txt";						
						File file = new File(exportPath+fileNameTxt);
						Integer idExpBatch;
						String newFileTxt;
						parameter.put("fileName",fileNameTxt);
						
						idExpBatch = this.queryDAO.executeForObject(GET_FileName, parameter, Integer.class);
							
						if(idExpBatch == null){
							// 7.0 check if filename GIROdata_MMyy.txt exist
							if(file.exists()) {	
								parameter = this.batchRecordError(parameter);
								doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC077", "errors.ERR1SC077", "");
								result.setErrors(errors);
								result.setMessages(messages);
						        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
						        String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
						        String currentId = CommonUtils.toString(parameter.get("idBatch"));
						        if(lastBatchId.equals(currentId)||currentId.equals("")){
						              parameter.put("RUN_STATUS", "N");
						              updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
						        }
								return result;
							} else {
								createBatchHeader = true;
							}
						} else {
							newFileTxt = "GIROdata_"  + MM + yy + "_" + idExpBatch + ".txt";	
							//8.0 Update FILENAME as GIROdata_MMYY_<ID_GIR_EXP_BATCH>.txt
							parameter.put("BATCH_ID", idExpBatch);
							parameter.put("FILENAME_NEW", newFileTxt);
							this.updateDAO.execute(UPDATE_FileName, parameter);
							createBatchHeader = true;
							
							if(file.exists()) {						
								//9.0 rename physical file
								file.renameTo(new File(exportPath+newFileTxt));									
							} 					
					}					
					// 10.0 Create batch header
					if(createBatchHeader == true) {
						parameter.put("status", STATUS_INPROCESS);
						parameter.put("closeMonthyear",  MM + "/" + yyyy );
						try {
							//TODO
							idGirExpBatch = this.updateDAONuked.insert(INSERT_BatchHeader, parameter);
							parameter.put("idBatch", idGirExpBatch);
							parameter.put("idBatchRefNo", idGirExpBatch);
						} catch (Exception e) {
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC060", "errors.ERR1BT017","T_GIR_EXP_HD");
							// exit current method
							result.setErrors(errors);
							result.setMessages(messages);
					        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
					        String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
					        String currentId = CommonUtils.toString(parameter.get("idBatch"));
					        if(lastBatchId.equals(currentId)||currentId.equals("")){
					              parameter.put("RUN_STATUS", "N");
					              updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
					        }
							return result;
						}
					} 
					// goto 12.0 retrieve Billing Account 
					result = this.retrieveBillAcc(exportPath+fileNameTxt, giroDate, param.getUvo().getId_user());	
				
				} else {
				doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC076", "errors.ERR1SC076", "");
				// exit current method
				result.setErrors(errors);
				result.setMessages(messages);
		        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
		        String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
		        String currentId = CommonUtils.toString(parameter.get("idBatch"));
		        if(lastBatchId.equals(currentId)||currentId.equals("")){
		              parameter.put("RUN_STATUS", "N");
		              updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
		        }
				return result;
			}	
		} else {
			doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC075", "errors.ERR1SC075", "");
			// exit current method
			result.setErrors(errors);
			result.setMessages(messages);
	        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
	        String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
	        String currentId = CommonUtils.toString(parameter.get("idBatch"));
	        if(lastBatchId.equals(currentId)||currentId.equals("")){
	              parameter.put("RUN_STATUS", "N");
	              updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
	        }
			return result;
		}
		}
		// end of execute method
		Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_GIR_P01.lastBatch", parameter);
		String lastBatchId = lastBatch.get("ID_GIR_EXP_BATCH").toString();
		String currentId = CommonUtils.toString(parameter.get("idBatch"));
		if(lastBatchId.equals(currentId)||currentId.equals("")){
		      parameter.put("RUN_STATUS", "N");
		      updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
		}
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	private GlobalProcessResult retrieveBillAcc(String fileNameTxt, String giroDate, String idUser){
		
		ArrayList<Object> detailList = new ArrayList<Object>();
		List<Map<String, Object>> billAccount = this.queryDAO.executeForMapList(GET_BillAcc, parameter);
		if (billAccount.size() == 0){
			doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC064", "errors.ERR1SC064", "");
			// exit current method
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		}else{
			// record the previous bill account
			String preID_BILL_ACCOUNT = "";
			String curID_BILL_ACCOUNT = "";
			int a = 0;
			for (a = 0; a< billAccount.size(); a++){
				
		    	Map<String, Object> billAcc = billAccount.get(a);				
				
		    	String idBillAcc = billAcc.get("ID_BILL_ACCOUNT").toString();
				if(idBillAcc == null || idBillAcc.trim().equals("")){
					String[] scrObj = new String[]{"ID Bill Account", "T_BAC_H"};
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC073", scrObj);
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}
				curID_BILL_ACCOUNT = billAcc.get("ID_BILL_ACCOUNT").toString();
				parameter.put("ID_CUST", billAcc.get("ID_CUST"));
				parameter.put("ID_BILL_ACCOUNT", billAcc.get("ID_BILL_ACCOUNT"));
				
				String screenObj = billAcc.get("ID_BILL_ACCOUNT").toString();
				String[] dbObj = new String[]{screenObj, "Giro Details", "M_CUST_BANKINFO, M_BANK"};
				Map<String, Object> billAccGrouping;
				BigDecimal collectionAmount;
				if(preID_BILL_ACCOUNT.equalsIgnoreCase(curID_BILL_ACCOUNT)){
					continue;
				}else{
					// set current bill acc
					preID_BILL_ACCOUNT = curID_BILL_ACCOUNT;
				    billAccGrouping = this.queryDAO.executeForMap("SELECT.G_GIR_P01.RetrieveBillAccGrouping", parameter);		
					
					parameter.put("dateReqParam", CommonUtils.toString(billAccGrouping.get("DATE_REQ")));
					
					G_GIR_P01_GIRO_Bean collectionAmtChecking = this.queryDAO.executeForObject("SELECT.G_GIR_P01.CollectionAmtChecking", parameter,G_GIR_P01_GIRO_Bean.class);	
				
					BigDecimal TOTAL_AMT_DUE = new BigDecimal(CommonUtils.toString(billAccGrouping.get("TOTAL_AMT_DUE")));
					
					BigDecimal SUM_BILL_AMOUNT = CommonUtils.isEmpty(collectionAmtChecking)? BigDecimal.ZERO:collectionAmtChecking.getTOTAL_AMT_DUE();
					
				    collectionAmount = TOTAL_AMT_DUE.subtract(SUM_BILL_AMOUNT);
					
					if(collectionAmount.compareTo(BigDecimal.ZERO)<=0){
						continue;
					}
					
				}
				
				try{	
					// Process 13.0 Retrieve customers' giroDetails
					G_GIR_P01_GIRO_Bean giroDetails = this.queryDAO.executeForObject(GET_GiroDetails, parameter, Object.class);								
					
					if(giroDetails == null){
						doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
						result.setErrors(errors);
						result.setMessages(messages);
						return result;
					} else{
						
						giroDetails.setID_REF(CommonUtils.toString(billAccGrouping.get("ID_REF")));
						giroDetails.setBILL_CURRENCY(CommonUtils.toString(billAccGrouping.get("BILL_CURRENCY")));
						giroDetails.setCollectionAmount(collectionAmount);
						
						giroDetails.setID_LOGIN(idLogin);
						giroDetails.setID_BILL_ACCOUNT(idBillAcc);	
						
						String idCust = billAcc.get("ID_CUST").toString();
						if(idCust == null || idCust.trim().equals("")){
							dbObj = new String[] {screenObj, "Customer ID", "T_BAC_H"};
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
							result.setErrors(errors);
							result.setMessages(messages);
						}else{
							giroDetails.setID_CUST(idCust);
							}
						
						java.math.BigDecimal totalAmtDue = (java.math.BigDecimal)billAcc.get("TOTAL_AMT_DUE");
						if(totalAmtDue == null || totalAmtDue.equals("")){
							dbObj = new String[] {screenObj, "Total Amount Due", "T_BAC_H"};
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
							result.setErrors(errors);
							result.setMessages(messages);
						}else{
							giroDetails.setTOTAL_AMT_DUE(totalAmtDue);
							}
						
						String giroAccNo = giroDetails.getGIRO_ACCT_NO();
						if (giroAccNo == null || giroAccNo.trim().equals("")) {
							dbObj = new String[] {screenObj, "Giro Account No", "M_CUST_BANKINFO"};
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
							result.setErrors(errors);
							result.setMessages(messages);
						}
						
						String noRef = giroDetails.getNO_REFERENCE();
						if (noRef == null || noRef.trim().equals("")) {
							dbObj = new String[] {screenObj, "No Reference", "M_CUST_BANKINFO"};
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
							result.setErrors(errors);
							result.setMessages(messages);
						}

						String bankCode=giroDetails.getBIC_CODE();
						giroDetails.setBANK_CODE(bankCode);
						if (bankCode == null || bankCode.trim().equals("")) {
							dbObj = new String[] {screenObj, "Bank Code", "M_BANK"};
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
							result.setErrors(errors);
							result.setMessages(messages);
						}
						
						String girAccName = giroDetails.getGIRO_ACCT_NAME();
						if(girAccName == null || girAccName.trim().equals("")){
							dbObj = new String[] {screenObj, "Customer Name", "M_CUST"};
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
							result.setErrors(errors);
							result.setMessages(messages);
						}else{
							giroDetails.setGIRO_ACCT_NAME(girAccName);
							}
						
						// get running Receipt No
						G_CDM_P01 cdm_p01 = new G_CDM_P01(this.queryDAO,getUpdateDAO(),idUser);
						
						String rcpNo = cdm_p01.getGenerateCode("RCPNO", idUser);
						if(rcpNo == null || rcpNo.trim().equals("")){
							dbObj = new String[] {screenObj, "Receipt No", "M_CODE"};
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
							result.setErrors(errors);
							result.setMessages(messages);
						}else{
							giroDetails.setRECEIPT_NO(rcpNo);
							}	
						
//						// Process 15.0 Get ID_Ref
//						Map<String, Object> idRef = this.queryDAO.executeForMap(GET_IdRef, parameter);
//						
//						String idReference = idRef.get("ID_REF").toString();
//						if(idReference == null || idReference.trim().equals("")){
//							String [] scrObj = new String[]{"ID Reference", "T_BIL_H"};
//							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC073", scrObj);
//							result.setErrors(errors);
//							result.setMessages(messages);
//							return result;
//						}else {
//							giroDetails.setID_REF(idReference);
//							}
//						
//						String billCurr = idRef.get("BILL_CURRENCY").toString();
//						if(billCurr == null || billCurr.trim().equals("")){
//							dbObj = new String[] {screenObj, idReference, "Bill Currency", "T_BIL_H"};
//							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC072", "errors.ERR1BT019", new String[] {screenObj, idReference}, dbObj);
//							result.setErrors(errors);
//							result.setMessages(messages);
//						}else{
//							giroDetails.setBILL_CURRENCY(billCurr);
//							}
						
						detailList.add(giroDetails);		
					}
				}catch(Exception e){
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC064", "errors.ERR1SC064", "");
					// exit current method
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}
			}		
		    }
		    if(detailList.size() > 0){
		    	
		    	this.result = mandCheckHeader(giroDate);
		    	if(this.result.getMessages() != null)		    		
		    		return this.result;
		    	else{
		    		try {
			    		HashMap<String, Object> blankFieldList = mandCheckBlank();
						FileWriter fw = new FileWriter(fileNameTxt);
						fw = writeTxtHeader(fw, fileNameTxt, giroDate, blankFieldList);
						fw = writeTxtContent(fw, detailList, blankFieldList, fileNameTxt);	
						fw.flush();
						fw.close();
						result = this.updateCollectionData(detailList);
					} catch (IOException e) {							
						doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC065", "errors.ERR1BT014", fileNameTxt);
						// exit current method
						result.setErrors(errors);
						result.setMessages(messages);
						return result;
				}		    			
		    	}
			} else {
				doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC064", "errors.ERR1SC064", "");
				// exit current method
				result.setErrors(errors);
				result.setMessages(messages);
				return result;
			}

		// end of retrieveBillAcc method
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	
	private GlobalProcessResult mandCheckHeader(String giroDate){
	
		parameter.put("ID_COM_BANK", bankAcc);
		String comAccNo = this.queryDAO.executeForObject(GET_ComAccNo, parameter, String.class);
		String[] screenObj;
		
		if(comAccNo == null || comAccNo.trim().equals("")){
			screenObj = new String[]{"Company Account No", "Company Maintenance"};
			doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC073", screenObj);
			result.setErrors(errors);
			result.setMessages(messages);
		}
		if(giroDate == null || giroDate.trim().equals("")){
			screenObj = new String[]{"GIRO deduct day", "Batch Maintenance"};
			doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC073", screenObj);	
			result.setErrors(errors);
			result.setMessages(messages);
		}
		return result;
	}
	
	private HashMap<String, Object> mandCheckBlank(){
			
		HashMap<String, Object> blankFieldList = new HashMap<String, Object>();
		String value = "";		
		
		String DefaultID = reportHeader("reports.GGIRP01.DEFAULTID", value) == null ? reportHeader("reports.GGIRP01.DEFAULTID", value) : "";
		String SM = reportHeader("reports.GGIRP01.SM", value) == null ? reportHeader("reports.GGIRP01.SM", value) : "";	
		String ServiceCD = reportHeader("reports.GGIRP01.SERVICECD", value) == null ? reportHeader("reports.GGIRP01.SERVICECD", value) : "";
		String DebitSSNo = reportHeader("reports.GGIRP01.DEBITSSNO", value) == null ? reportHeader("reports.GGIRP01.DEBITSSNO", value) : "";
		String DebitSWIFTAddress = reportHeader("reports.GGIRP01.DEBITSWIFTADDRESS", value) == null ? reportHeader("reports.GGIRP01.DEBITSWIFTADDRESS", value) : "";
		String DebitSubNo = reportHeader("reports.GGIRP01.DEBITSUBNO", value) == null ? reportHeader("reports.GGIRP01.DEBITSUBNO", value) : "";
		String ChargeSubNo = reportHeader("reports.GGIRP01.CHARGESUBNO", value) == null ? reportHeader("reports.GGIRP01.CHARGESUBNO", value) : "";
		String CustRef = reportHeader("reports.GGIRP01.SM", value) == null ? reportHeader("reports.GGIRP01.SM", value) : "";	
		String WHTCertificationFlg = reportHeader("reports.GGIRP01.SM", value) == null ? reportHeader("reports.GGIRP01.SM", value) : "";	
		String EquivCurrency = reportHeader("reports.GGIRP01.SM", value) == null ? reportHeader("reports.GGIRP01.SM", value) : "";	
		String ApplicatName = reportHeader("reports.GGIRP01.SM", value) == null ? reportHeader("reports.GGIRP01.SM", value) : "";	
		String ApplicatPhoneNo = reportHeader("reports.GGIRP01.SM", value) == null ? reportHeader("reports.GGIRP01.SM", value) : "";	
		String ApplicatAddress = reportHeader("reports.GGIRP01.SM", value) == null ? reportHeader("reports.GGIRP01.SM", value) : "";	
		
		String PayerNo = reportHeader("reports.GGIRP01.PAYERNO", value) == null ? reportHeader("reports.GGIRP01.PAYERNO", value) : "";	
		String PayerNameLocal = reportHeader("reports.GGIRP01.PAYERNAMELOCAL", value) == null ? reportHeader("reports.GGIRP01.PAYERNAMELOCAL", value) : "";	
		String Address = reportHeader("reports.GGIRP01.ADDRESS", value) == null ? reportHeader("reports.GGIRP01.ADDRESS", value) : "";	
		String AddressLocal = reportHeader("reports.GGIRP01.ADDRESSLOCAL", value) == null ? reportHeader("reports.GGIRP01.ADDRESSLOCAL", value) : "";	
		String Attention = reportHeader("reports.GGIRP01.ATTENTION", value) == null ? reportHeader("reports.GGIRP01.ATTENTION", value) : "";	
		String CountryName = reportHeader("reports.GGIRP01.COUNTRYNAME", value) == null ? reportHeader("reports.GGIRP01.COUNTRYNAME", value) : "";	
		String ZipCode = reportHeader("reports.GGIRP01.ZIPCODE", value) == null ? reportHeader("reports.GGIRP01.ZIPCODE", value) : "";	
		String PhoneNo = reportHeader("reports.GGIRP01.PHONENO", value) == null ? reportHeader("reports.GGIRP01.PHONENO", value) : "";	
		String FaxNo = reportHeader("reports.GGIRP01.FAXNO", value) == null ? reportHeader("reports.GGIRP01.FAXNO", value) : "";	
		String EmailAddress = reportHeader("reports.GGIRP01.EMAILADDRESS", value) == null ? reportHeader("reports.GGIRP01.EMAILADDRESS", value) : "";	
		String WHTID = reportHeader("reports.GGIRP01.WHTID", value) == null ? reportHeader("reports.GGIRP01.WHTID", value) : "";	
		String WHTFormID = reportHeader("reports.GGIRP01.WHTFORMID", value) == null ? reportHeader("reports.GGIRP01.WHTFORMID", value) : "";	
		String WHTRef = reportHeader("reports.GGIRP01.WHTREF", value) == null ? reportHeader("reports.GGIRP01.WHTREF", value) : "";	
		String BankName = reportHeader("reports.GGIRP01.BANKNAME", value) == null ? reportHeader("reports.GGIRP01.BANKNAME", value) : "";	
		String BankNameLocal = reportHeader("reports.GGIRP01.BANKNAMELOCAL", value) == null ? reportHeader("reports.GGIRP01.BANKNAMELOCAL", value) : "";	
		String BranchName = reportHeader("reports.GGIRP01.BRANCHNAME", value) == null ? reportHeader("reports.GGIRP01.BRANCHNAME", value) : "";	
		String BranchNameLocal = reportHeader("reports.GGIRP01.BRANCHNAMELOCAL", value) == null ? reportHeader("reports.GGIRP01.BRANCHNAMELOCAL", value) : "";	
		String IntermedBankName = reportHeader("reports.GGIRP01.INTERMEDBANKNAME", value) == null ? reportHeader("reports.GGIRP01.INTERMEDBANKNAME", value) : "";	
		String IntermedBankCode = reportHeader("reports.GGIRP01.INTERMEDBANKCODE", value) == null ? reportHeader("reports.GGIRP01.INTERMEDBANKCODE", value) : "";	
		String IntermedBranchName = reportHeader("reports.GGIRP01.INTERMEDBRANCHNAME", value) == null ? reportHeader("reports.GGIRP01.INTERMEDBRANCHNAME", value) : "";	
		String IntermedBankCountryName = reportHeader("reports.GGIRP01.INTERMEDBANKCOUNTRYNAME", value) == null ? reportHeader("reports.GGIRP01.INTERMEDBANKCOUNTRYNAME", value) : "";	
		String InvoiceAmount = reportHeader("reports.GGIRP01.INVOICEAMOUNT", value) == null ? reportHeader("reports.GGIRP01.INVOICEAMOUNT", value) : "";	
		String VatRate = reportHeader("reports.GGIRP01.VATRATE", value) == null ? reportHeader("reports.GGIRP01.VATRATE", value) : "";	
		String VatAmount = reportHeader("reports.GGIRP01.VATAMOUNT", value) == null ? reportHeader("reports.GGIRP01.VATAMOUNT", value) : "";	
		String DiscountAmount = reportHeader("reports.GGIRP01.DISCOUNTAMOUNT", value) == null ? reportHeader("reports.GGIRP01.DISCOUNTAMOUNT", value) : "";	
		String WHTAmount = reportHeader("reports.GGIRP01.WHTAMOUNT", value) == null ? reportHeader("reports.GGIRP01.WHTAMOUNT", value) : "";	
		String CalcModeID = reportHeader("reports.GGIRP01.CALCMODEID", value) == null ? reportHeader("reports.GGIRP01.CALCMODEID", value) : "";	
		String OurCharge = reportHeader("reports.GGIRP01.OURCHARGE", value) == null ? reportHeader("reports.GGIRP01.OURCHARGE", value) : "";	
		String BeneCharge = reportHeader("reports.GGIRP01.BENECHARGE", value) == null ? reportHeader("reports.GGIRP01.BENECHARGE", value) : "";	
		
		String Particulars = reportHeader("reports.GGIRP01.PARTICULARS", value) == null ? reportHeader("reports.GGIRP01.PARTICULARS", value) : "";	
		String MsgToPayee = reportHeader("reports.GGIRP01.MsgToPayee", value) == null ? reportHeader("reports.GGIRP01.MsgToPayee", value) : "";	
		String DocSendMethodID = reportHeader("reports.GGIRP01.DOCSENDMETHODID", value) == null ? reportHeader("reports.GGIRP01.DOCSENDMETHODID", value) : "";	
		String InstructionReferenceNo = reportHeader("reports.GGIRP01.INSTRUCTIONREFERENCENO", value) == null ? reportHeader("reports.GGIRP01.INSTRUCTIONREFERENCENO", value) : "";	
		String Merchandise = reportHeader("reports.GGIRP01.MERCHANDISE", value) == null ? reportHeader("reports.GGIRP01.MERCHANDISE", value) : "";	
		String Origin = reportHeader("reports.GGIRP01.ORIGIN", value) == null ? reportHeader("reports.GGIRP01.ORIGIN", value) : "";	
		String FaxNotification = reportHeader("reports.GGIRP01.FAXNOTIFICATION", value) == null ? reportHeader("reports.GGIRP01.FAXNOTIFICATION", value) : "";	
		String DebitDepositType = reportHeader("reports.GGIRP01.DEBITDEPOSITTYPE", value) == null ? reportHeader("reports.GGIRP01.DEBITDEPOSITTYPE", value) : "";	
		String DebitDepositACNo = reportHeader("reports.GGIRP01.DEBITDEPOSITACNO", value) == null ? reportHeader("reports.GGIRP01.DEBITDEPOSITACNO", value) : "";	
		String DebitRefNo = reportHeader("reports.GGIRP01.DEBITREFNO", value) == null ? reportHeader("reports.GGIRP01.DEBITREFNO", value) : "";	
		String CreditDepositType = reportHeader("reports.GGIRP01.CREDITDEPOSITTYPE", value) == null ? reportHeader("reports.GGIRP01.CREDITDEPOSITTYPE", value) : "";	
		String CreditGLCD = reportHeader("reports.GGIRP01.CREDITGLCD", value) == null ? reportHeader("reports.GGIRP01.CREDITGLCD", value) : "";	
		String CreditGLSubCD = reportHeader("reports.GGIRP01.CREDITGLSUBCD", value) == null ? reportHeader("reports.GGIRP01.CREDITGLSUBCD", value) : "";	
		String CreditAccountNo = reportHeader("reports.GGIRP01.CREDITACCOUNTNO", value) == null ? reportHeader("reports.GGIRP01.CREDITACCOUNTNO", value) : "";	
		String CreditSubNo = reportHeader("reports.GGIRP01.CREDITSUBNO", value) == null ? reportHeader("reports.GGIRP01.CREDITSUBNO", value) : "";	
		String CreditCCY = reportHeader("reports.GGIRP01.CREDITCCY", value) == null ? reportHeader("reports.GGIRP01.CREDITCCY", value) : "";	
		String CreditInterestGLCD = reportHeader("reports.GGIRP01.CREDITINTERESTGLCD", value) == null ? reportHeader("reports.GGIRP01.CREDITINTERESTGLCD", value) : "";	
		String CreditInterestGLSubCD = reportHeader("reports.GGIRP01.CREDITINTERESTGLSUBCD", value) == null ? reportHeader("reports.GGIRP01.CREDITINTERESTGLSUBCD", value) : "";	
		String CreditInterestAccountNo = reportHeader("reports.GGIRP01.CREDITINTERESTACCOUNTNO", value) == null ? reportHeader("reports.GGIRP01.CREDITINTERESTACCOUNTNO", value) : "";	
		String CreditInterestSubNo = reportHeader("reports.GGIRP01.CREDITINTERESTSUBNO", value) == null ? reportHeader("reports.GGIRP01.CREDITINTERESTSUBNO", value) : "";	
		String CreditInterestCCY = reportHeader("reports.GGIRP01.CREDITINTERESTCCY", value) == null ? reportHeader("reports.GGIRP01.CREDITINTERESTCCY", value) : "";	
		String MaturityDate = reportHeader("reports.GGIRP01.MATURITYDATE", value) == null ? reportHeader("reports.GGIRP01.MATURITYDATE", value) : "";	
		String InterestRate = reportHeader("reports.GGIRP01.INTERESTRATE", value) == null ? reportHeader("reports.GGIRP01.INTERESTRATE", value) : "";	
		String ContractNo = reportHeader("reports.GGIRP01.CONTRACTNO", value) == null ? reportHeader("reports.GGIRP01.CONTRACTNO", value) : "";	
		String ContactPerson = reportHeader("reports.GGIRP01.CONTACTPERSON", value) == null ? reportHeader("reports.GGIRP01.CONTACTPERSON", value) : "";	
		String PickUpID = reportHeader("reports.GGIRP01.PICKUPID", value) == null ? reportHeader("reports.GGIRP01.PICKUPID", value) : "";	
		String Remarks = reportHeader("reports.GGIRP01.REMARKS", value) == null ? reportHeader("reports.GGIRP01.REMARKS", value) : "";	
		
		blankFieldList.put("DefaultID", DefaultID);
		blankFieldList.put("SM", SM);
		blankFieldList.put("ServiceCD", ServiceCD);
		blankFieldList.put("DebitSSNo", DebitSSNo);
		blankFieldList.put("DebitSWIFTAddress", DebitSWIFTAddress);
		blankFieldList.put("DebitSubNo", DebitSubNo);
		blankFieldList.put("ChargeSubNo",ChargeSubNo);
		blankFieldList.put("CustRef",CustRef);
		blankFieldList.put("WHTCertificationFlg",WHTCertificationFlg);
		blankFieldList.put("EquivCurrency",EquivCurrency);
		blankFieldList.put("ApplicatName",ApplicatName);
		blankFieldList.put("ApplicatPhoneNo",ApplicatPhoneNo);
		blankFieldList.put("ApplicatAddress",ApplicatAddress);
		
		blankFieldList.put("PayerNo",PayerNo);
		blankFieldList.put("PayerNameLocal",PayerNameLocal);
		blankFieldList.put("Address",Address);
		blankFieldList.put("AddressLocal",AddressLocal);
		blankFieldList.put("Attention",Attention);
		blankFieldList.put("CountryName",CountryName);
		blankFieldList.put("ZipCode",ZipCode);
		blankFieldList.put("PhoneNo",PhoneNo);
		blankFieldList.put("FaxNo",FaxNo);
		blankFieldList.put("EmailAddress",EmailAddress);
		blankFieldList.put("WHTID",WHTID);
		blankFieldList.put("WHTFormID",WHTFormID);
		blankFieldList.put("WHTRef",WHTRef);
		blankFieldList.put("BankName",BankName);
		blankFieldList.put("BankNameLocal",BankNameLocal);
		blankFieldList.put("BranchName",BranchName);
		blankFieldList.put("BranchNameLocal",BranchNameLocal);
		blankFieldList.put("IntermedBankName",IntermedBankName);
		blankFieldList.put("IntermedBankCode",IntermedBankCode);
		blankFieldList.put("IntermedBranchName",IntermedBranchName);
		blankFieldList.put("IntermedBankCountryName",IntermedBankCountryName);
		blankFieldList.put("InvoiceAmount",InvoiceAmount);
		blankFieldList.put("VatRate",VatRate);
		blankFieldList.put("VatAmount",VatAmount);
		blankFieldList.put("DiscountAmount",DiscountAmount);
		blankFieldList.put("WHTAmount",WHTAmount);
		blankFieldList.put("CalcModeID",CalcModeID);
		blankFieldList.put("OurCharge",OurCharge);
		blankFieldList.put("BeneCharge",BeneCharge);
		
		blankFieldList.put("Particulars",Particulars);
		blankFieldList.put("MsgToPayee",MsgToPayee);
		blankFieldList.put("DocSendMethodID",DocSendMethodID);
		blankFieldList.put("InstructionReferenceNo",InstructionReferenceNo);
		blankFieldList.put("Merchandise",Merchandise);
		blankFieldList.put("Origin",Origin);
		blankFieldList.put("FaxNotification",FaxNotification);
		blankFieldList.put("DebitDepositType",DebitDepositType);
		blankFieldList.put("DebitDepositACNo",DebitDepositACNo);
		blankFieldList.put("DebitRefNo",DebitRefNo);
		blankFieldList.put("CreditDepositType",CreditDepositType);
		blankFieldList.put("CreditGLCD",CreditGLCD);
		blankFieldList.put("CreditGLSubCD",CreditGLSubCD);
		blankFieldList.put("CreditAccountNo",CreditAccountNo);
		blankFieldList.put("CreditSubNo",CreditSubNo);
		blankFieldList.put("CreditCCY",CreditCCY);
		blankFieldList.put("CreditInterestGLCD",CreditInterestGLCD);
		blankFieldList.put("CreditInterestGLSubCD",CreditInterestGLSubCD);
		blankFieldList.put("CreditInterestAccountNo",CreditInterestAccountNo);
		blankFieldList.put("CreditInterestSubNo",CreditInterestSubNo);
		blankFieldList.put("CreditInterestCCY",CreditInterestCCY);
		blankFieldList.put("MaturityDate",MaturityDate);
		blankFieldList.put("InterestRate",InterestRate);
		blankFieldList.put("ContractNo",ContractNo);
		blankFieldList.put("ContactPerson",ContactPerson);
		blankFieldList.put("PickUpID",PickUpID);
		blankFieldList.put("Remarks",Remarks);
		
		return blankFieldList;	
	}
		
	// write Txt Header
	private FileWriter writeTxtHeader(FileWriter fw, String newFileTxt, String giroDate, HashMap<String, Object> blankFieldList) throws IOException {
		
		parameter.put("ID_COM_BANK", bankAcc);
		String comAccNo = this.queryDAO.executeForObject(GET_ComAccNo, parameter, String.class);
		String value = "";
		
		writeTxt(fw,reportHeader("reports.GGIRP01.ENV", value));
		fw.append(',');
		writeTxt(fw, blankFieldList.get("DefaultID").toString());
		fw.append(',');
		writeTxt(fw, blankFieldList.get("SM").toString());
		fw.append(',');
		writeTxt(fw, blankFieldList.get("ServiceCD").toString());
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.TRANSFERTYPE", value));
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.TRANSFERSUBTYPE", value));
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.DEBITBRANCHCD", value));
		fw.append(',');
		writeTxt(fw, blankFieldList.get("DebitSSNo").toString());
		fw.append(',');
		writeTxt(fw, blankFieldList.get("DebitSWIFTAddress").toString());
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.DEBITGLCD", value));
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.DEBITGLSUBCD", value));
		fw.append(',');
		writeTxt(fw, comAccNo);
		fw.append(',');
		writeTxt(fw, blankFieldList.get("DebitSubNo").toString());
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.DEBITCCY", value));
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.CHARGEGLCD", value));
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.DEBITGLSUBCD", value));
		fw.append(',');
		writeTxt(fw, comAccNo);
		fw.append(',');
		writeTxt(fw, blankFieldList.get("ChargeSubNo").toString());
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.CHARGECCY", value));
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.DEBITTYPE", value));
		fw.append(',');
		writeTxt(fw, blankFieldList.get("CustRef").toString());
		fw.append(',');
		writeTxt(fw,giroDate);
		fw.append(',');
		writeTxt(fw, blankFieldList.get("WHTCertificationFlg").toString());
		fw.append(',');
		writeTxt(fw,reportHeader("reports.GGIRP01.REMITTCURRENCY", value));
		fw.append(',');
		writeTxt(fw, blankFieldList.get("EquivCurrency").toString());
		fw.append(',');
		writeTxt(fw, blankFieldList.get("ApplicatName").toString());
		fw.append(',');
		writeTxt(fw, blankFieldList.get("ApplicatPhoneNo").toString());
		fw.append(',');
		writeTxt(fw, blankFieldList.get("ApplicatAddress").toString());
		fw.append("\r\n");
		return fw;
	}
	
	// write Txt Content
	private FileWriter writeTxtContent(FileWriter fw, ArrayList<Object> detailList, HashMap<String, Object> blankFieldList, String newFileTxt) throws IOException {
		//write content
		for(int i = 0; i < detailList.size(); i++) {
			G_GIR_P01_GIRO_Bean detailObj = (G_GIR_P01_GIRO_Bean) detailList.get(i);
				String value = "";
				writeTxt(fw, reportHeader("reports.GGIRP01.INS", value));
				fw.append(',');
				writeTxt(fw, blankFieldList.get("DefaultID").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("PayerNo").toString());
				fw.append(',');
				writeTxt(fw, detailObj.getGIRO_ACCT_NAME());		
				fw.append(',');
				writeTxt(fw, blankFieldList.get("PayerNameLocal").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("Address").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("AddressLocal").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("Attention").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CountryName").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("ZipCode").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("PhoneNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("FaxNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("EmailAddress").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("WHTID").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("WHTFormID").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("WHTRef").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("BankName").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("BankNameLocal").toString());
				fw.append(',');
				writeTxt(fw, detailObj.getBANK_CODE());		
				fw.append(',');
				writeTxt(fw, blankFieldList.get("BranchName").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("BranchNameLocal").toString());
				fw.append(',');
				writeTxt(fw, reportHeader("reports.GGIRP01.BANKCOUNTRYNAME", value));
				fw.append(',');
				writeTxt(fw, detailObj.getGIRO_ACCT_NO());			
				fw.append(',');
				writeTxt(fw, blankFieldList.get("IntermedBankName").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("IntermedBankCode").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("IntermedBranchName").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("IntermedBankCountryName").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("InvoiceAmount").toString());
				fw.append(',');
				writeTxt(fw, detailObj.getCollectionAmount().toString());			
				fw.append(',');
				writeTxt(fw, blankFieldList.get("VatRate").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("VatAmount").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("DiscountAmount").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("WHTAmount").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CalcModeID").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("OurCharge").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("BeneCharge").toString());
				fw.append(',');
				writeTxt(fw, reportHeader("reports.GGIRP01.TRANSACTIONCD", value));
				fw.append(',');
				writeTxt(fw, reportHeader("reports.GGIRP01.MsgToPayee", value));
				fw.append(',');
				writeTxt(fw, blankFieldList.get("Particulars").toString());
				fw.append(',');
				writeTxt(fw, detailObj.getNO_REFERENCE());				
				fw.append(',');
				writeTxt(fw, blankFieldList.get("DocSendMethodID").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("InstructionReferenceNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("Merchandise").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("Origin").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("FaxNotification").toString());
				fw.append(',');
				writeTxt(fw, reportHeader("reports.GGIRP01.EMAILNOTIFICATION", value));
				fw.append(',');
				writeTxt(fw, blankFieldList.get("DebitDepositType").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("DebitDepositACNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("DebitRefNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditDepositType").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditGLCD").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditGLSubCD").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditAccountNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditSubNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditCCY").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditInterestGLCD").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditInterestGLSubCD").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditInterestAccountNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditInterestSubNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("CreditInterestCCY").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("MaturityDate").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("InterestRate").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("ContractNo").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("ContactPerson").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("PickUpID").toString());
				fw.append(',');
				writeTxt(fw, blankFieldList.get("Remarks").toString());
				fw.append("\r\n");
		}
		return fw;
	}
	
	private GlobalProcessResult updateCollectionData(ArrayList<Object> detailList){	

		try {
			for (int i = 0; i < detailList.size(); i++){

				G_GIR_P01_GIRO_Bean giroDetails = (G_GIR_P01_GIRO_Bean) detailList.get(i);							
				// 16.1 Insert into T_CSB_H
				String value = "";
				parameter.put("RECEIPT_NO", giroDetails.getRECEIPT_NO());
				parameter.put("ID_CUST", giroDetails.getID_CUST());
				parameter.put("ID_COM_BANK", Integer.parseInt(bankAcc));
				parameter.put("PMT_METHOD", PAY_METHOD);			
				parameter.put("CUR_CODE", giroDetails.getBILL_CURRENCY());
				parameter.put("PMT_STATUS", reportHeader("reports.GGIRP01.PMT_STATUS", value));				
				parameter.put("IS_CLOSED", reportHeader("reports.GGIRP01.IS_CLOSED", value));
				parameter.put("IS_DELETED", reportHeader("reports.GGIRP01.IS_DELETED", value));
				parameter.put("ID_LOGIN", giroDetails.getID_LOGIN());
				parameter.put("AMT_RECEIVED", giroDetails.getCollectionAmount());
				parameter.put("BANK_CHARGE", reportHeader("reports.GGIRP01.BANK_CHARGE", value));
				parameter.put("BALANCE_AMT",giroDetails.getCollectionAmount());	
				parameter.put("REFERENCE1", giroDetails.getID_REF());
				parameter.put("ID_BILL_ACCOUNT", giroDetails.getID_BILL_ACCOUNT());	
				parameter.put("IS_EXPORT", reportHeader("reports.GGIRP01.IS_EXPORT", value));
				parameter.put("PAID_PRE_INVOICE", reportHeader("reports.GGIRP01.PAID_PRE_INVOICE", value));
				parameter.put("OVER_PAID", reportHeader("reports.GGIRP01.OVER_PAID", value));		
				parameter.put("DATE_TRANS", DATE_TRANS);		

				int insertResult = this.updateDAO.execute(INSERT_CashBook, parameter);
				if (insertResult == 0){
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC078", "errors.ERR1SC078", "T_CSB_H");
					// exit current method
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}
				
				// 16.2 Update TOTAL_AMT_DUE
				parameter.put("ID_BILL_ACCOUNT", giroDetails.getID_BILL_ACCOUNT());	
				BigDecimal bdAmount = giroDetails.getTOTAL_AMT_DUE().subtract(giroDetails.getCollectionAmount());							
				bdAmount = bdAmount.setScale(2, RoundingMode.HALF_UP);
				parameter.put("TOTAL_AMT_DUE", bdAmount);
				this.updateDAO.execute(UPDATE_TotalAmtDue, parameter);	
			}
			// moved for 369
//			for (int i = 0; i < detailList.size(); i++){
//				
//				G_GIR_P01_GIRO_Bean giroDetails = (G_GIR_P01_GIRO_Bean) detailList.get(i);
//			
//				// 16.2 Update TOTAL_AMT_DUE
//				parameter.put("ID_BILL_ACCOUNT", giroDetails.getID_BILL_ACCOUNT());	
//				BigDecimal bdAmount = ((java.math.BigDecimal)parameter.get("BALANCE_AMT")).subtract((java.math.BigDecimal)parameter.get("AMT_RECEIVED"));							
//				bdAmount = bdAmount.setScale(2, RoundingMode.HALF_UP);
//				parameter.put("TOTAL_AMT_DUE", bdAmount);
//				this.updateDAO.execute(UPDATE_TotalAmtDue, parameter);				
//			}
			for (int i = 0; i < detailList.size(); i++){
				
				G_GIR_P01_GIRO_Bean giroDetails = (G_GIR_P01_GIRO_Bean) detailList.get(i);				
				// Passing ID_BILL_ACCOUNT to G_CSB_P02
				G_CSB_P02 gCSBP02 = new G_CSB_P02(queryDAO, updateDAO, this.uvo);
				G_CSB_P02_Input input = new G_CSB_P02_Input();
				input.setReceiptNo(giroDetails.getRECEIPT_NO());
				gCSBP02.execute(input);	
			}
			// Process 20.0 Update Status Success
			doLogging(STATUS_SUCCESS, this.SCR, "info.ERR2SC029","","");		
			result.setMessages(messages);
			return result;
		} catch (Exception e) {
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		}						
	}
	
	private Map<String, Object> batchRecordError(Map<String, Object> parameter){
		
		parameter.put("fileName", "NOT AVAILABLE");
		parameter.put("status", STATUS_FAILED);
		int idGirExpBatch = this.updateDAONuked.insert(INSERT_BatchRecordErr, parameter);	
		parameter.put("idBatch", idGirExpBatch);
		parameter.put("idBatchRefNo", idGirExpBatch);
		return parameter;
	}
	
	private static void writeTxt(FileWriter fw, String content) throws IOException {
		if(content.contains(",")) {
			fw.append("\""+content.toString()+"\"");
		} else {
			fw.append(content.toString());
		}
	}

	private String reportHeader(String key, Object args){
		return MessageUtil.get(key, args);
	}
	
	private void doLogging(String status, boolean SCR, String screenMsg, String dbMsg, Object screenObj, Object[] dbObj) {
		this.updateStatus(status);
		// check status && check if process is called from scheduler or screen
		if (status == "0" && SCR == true) {
			this.addMsg(screenMsg, screenObj);
		} else if (status == "1" && SCR == true) {
			this.recordLog(dbMsg, dbObj);
			this.addError(screenMsg, screenObj);
		} else if (status == "1" && SCR == false) {
			this.recordLog(dbMsg, dbObj);
		}
	}

	private void doLogging(String status, boolean SCR, String screenMsg, String dbMsg, Object object) {
		this.updateStatus(status);
		// check status && check if process is called from scheduler or screen
		if (status == "0" && SCR == true) {
			this.addMsg(screenMsg, object);
		} else if (status == "1" && SCR == true) {
			this.recordLog(dbMsg, object);
			this.addError(screenMsg, object);
		} else if (status == "1" && SCR == false) {
			this.recordLog(dbMsg, object);
		}
	}
	
	private void doLogging(String status, boolean SCR, String Msg, Object[] Obj){
		this.updateStatus(status);
		// check status && check if process is called from scheduler or screen	
		if(status == "0" && SCR == true){
			this.addMsg(Msg, Obj);
		} else if (status == "1" && SCR == true){
			this.recordLog(Msg, Obj);	
			this.addError(Msg, Obj);
		} else if (status == "1" && SCR == false) {
			this.recordLog(Msg, Obj);	
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
	private void addError(String msgID, Object... args) {
		errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID, args));
	}

	private void addMsg(String msgID, Object... args) {
		messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(msgID,
				args));
	}

	private void recordLog(String msgID, Object... args) {
		parameter.put("errorMsg", MessageUtil.get(msgID, args));
		this.updateDAO.execute(INSERT_ErrorLog, parameter);
	}

	private void updateStatus(String status){
		//6.0 Update the previous process STATUS
		parameter.put("status", status);
		this.updateDAO.execute(UPDATE_ProcessStatus, parameter);	
	}
	
	public void setAuditIdModule(String auditIdModule) {
		this.auditIdModule = auditIdModule;
	}

	public void setAuditIdSubModule(String auditIdSubModule) {
		this.auditIdSubModule = auditIdSubModule;
	}

	public void setAuditReference(String auditReference) {
		this.auditReference = auditReference;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
}