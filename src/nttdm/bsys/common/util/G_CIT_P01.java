package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
import nttdm.bsys.common.bean.G_CIT_P01_CITI_Bean;
import nttdm.bsys.common.bean.G_CSB_P02_Input;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1SubmitOutput;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

public class G_CIT_P01 extends AbstractGlobalProcess<RP_E_MEX_CT1SubmitInput, RP_E_MEX_CT1SubmitOutput> {

	private static final String STATUS_SUCCESS = "0";
	private static final String STATUS_FAILED = "1";
	private static final String STATUS_INPROCESS = "2";
	private static final String ID_BATCH_TYPE = "G_CIT_P01";
	private static final String PAY_METHOD = "CC";

	private static final String INSERT_BatchRecordErr = "INSERT.G_CIT_P01.001";
	private static final String INSERT_ErrorLog = "INSERT.G_CIT_P01.002";
	private static final String INSERT_BatchHeader = "INSERT.G_CIT_P01.003";
	private static final String INSERT_CashBook = "INSERT.G_CIT_P01.004";

	private static final String GET_ProcessStatus = "SELECT.G_CIT_P01.001";
	private static final String GET_FileName = "SELECT.G_CIT_P01.002";
	private static final String GET_CCDetails = "SELECT.G_CIT_P01.003";
	private static final String GET_IdRef = "SELECT.G_CIT_P01.005";

	private static final String UPDATE_ProcessStatus = "UPDATE.G_CIT_P01.001";
	private static final String UPDATE_FileName = "UPDATE.G_CIT_P01.002";
	private static final String UPDATE_TotalAmtDue = "UPDATE.G_CIT_P01.003";

	// getting parameter passed by caller method
	private boolean SCR;
	private String bankAcc;
	private String idLogin;
	private BLogicMessages messages = null;
	private BLogicMessages errors = null;
	private GlobalProcessResult result = null;
	private Map<String, Object> parameter = null;
	private Map<String, Object> latestProcess = null;
	private BillingSystemUserValueObject uvo = null;

	// Audit Trail
	private String auditIdModule;
	private String auditIdSubModule;
	private String auditReference;
	private String auditStatus;

	public G_CIT_P01(QueryDAO queryDAO, UpdateDAO updateDAO,
			UpdateDAOiBatisNuked updateDAONuked) {
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
		this.updateDAONuked = updateDAONuked;
	}

	public G_CIT_P01() {}

	public GlobalProcessResult execute(RP_E_MEX_CT1SubmitInput param, RP_E_MEX_CT1SubmitOutput outputDTO) {

		this.uvo = param.getUvo();
		this.idLogin = uvo.getId_user();
		this.messages = new BLogicMessages();
		this.errors = new BLogicMessages();
		this.result = new GlobalProcessResult();
		this.parameter = new HashMap<String, Object>();
		this.SCR = param.isScr();
		this.bankAcc = param.getBankAcc();
		boolean inProcess = false;
		boolean toCheckExportPath = false;
		boolean createBatchHeader = false;
		Integer idCitExpBatch = null;
			
//		setAuditIdModule(BillingSystemConstants.MODULE_B);
//		setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_B_BAC);
		//TODO
		result.setParameter(parameter);
		setAuditIdModule(BillingSystemConstants.MODULE_E);
		setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_CT1);

		Integer auditId = CommonUtils.auditTrailBegin(this.idLogin,
				this.auditIdModule, this.auditIdSubModule, this.auditReference,
				this.auditStatus, BillingSystemConstants.AUDIT_TRAIL_CREATED);
		
		DateFormat exMonth = new SimpleDateFormat("MM");
		DateFormat exYearLong = new SimpleDateFormat("yyyy");
		DateFormat exYear = new SimpleDateFormat("yy");
		Date executeMonth = null;
		Date executeYear = null;
		try {
			executeMonth = exMonth.parse(param.getClosingMonth());
			executeYear = exYearLong.parse(param.getClosingYear());	
		} catch (ParseException e) {
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		}
		String MM = exMonth.format(executeMonth);
		String yyyy = exYearLong.format(executeYear);
		String yy = exYear.format(executeYear);
		parameter.put("closeMonthyear", MM + "/" + yyyy);
		parameter.put("executeMonth", MM + yyyy);
		parameter.put("executeYearMonth", yyyy + MM);
		parameter.put("idLogin", idLogin);
		
		// error checking for auditID
		if (auditId == null) {	
			parameter = this.batchRecordError(parameter);
			doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC074", "errors.ERR1SC074", "");
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		} else {
			parameter.put("ID_AUDIT", auditId);
			parameter.put("idBatchType", ID_BATCH_TYPE);

		    parameter.put("RUN_STATUS", "Y");
		    updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
			
			BillingSystemSettings setting = new BillingSystemSettings(queryDAO);

			// 1.0 Check any "In Process" status
			List<Map<String, Object>> processStatus = this.queryDAO.executeForMapList(GET_ProcessStatus, null);
				if (!processStatus.isEmpty()) {
					latestProcess = processStatus.get(0);
					parameter.put("idBatch", latestProcess.get("ID_CIT_EXP_BATCH"));
					parameter.put("idBatchRefNo", latestProcess.get("ID_CIT_EXP_BATCH"));
					inProcess = true;
				} else {
					toCheckExportPath = true;
				}
			
			if (inProcess == true) {
				// 2.0 Check if process has exceed BATCH_TIME_INTERVAL
				if (CommonUtils.isTimeInterval(latestProcess.get("DATE_UPDATED_CHAR").toString(), setting.getBatchTimeInterval()* 1000)) {
					// 3.0 and 4.0
					doLogging(STATUS_FAILED, this.SCR, "", "errors.ERR1BT016", "");
					toCheckExportPath = true;			
				} else {
					parameter = this.batchRecordError(parameter);
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC059", "errors.ERR1BT020", setting.getBatchTimeInterval()/60);
					// exit current method
			        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_CIT_P01.lastBatch", parameter);
			        String lastBatchId = lastBatch.get("ID_CIT_EXP_BATCH").toString();
			        String currentBatchId = CommonUtils.toString(parameter.get("idBatch"));
			        if(lastBatchId.equals(currentBatchId)||currentBatchId.equals("")){
			            parameter.put("RUN_STATUS", "N");
			            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
			        }
					result.setErrors(errors);
					result.setMessages(messages);
					return result;			
				}
			}
			boolean FileExportPath = false;
			String exportPath = "";
			if (toCheckExportPath == true) {
				if (setting.getBatchGCitP01() == null || setting.getBatchGCitP01().trim().equals("")) {					
					parameter = this.batchRecordError(parameter);
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC075", "errors.ERR1SC075", "");
			        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_CIT_P01.lastBatch", parameter);
			        String lastBatchId = lastBatch.get("ID_CIT_EXP_BATCH").toString();
			        String currentBatchId = CommonUtils.toString(parameter.get("idBatch"));
			        if(lastBatchId.equals(currentBatchId)||currentBatchId.equals("")){
			            parameter.put("RUN_STATUS", "N");
			            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
			        }
					result.setErrors(errors);
					result.setMessages(messages);
					return result;					
				} else {
					// get exportPath from M_GSET_D.BATCH_G_CIT_P01
					exportPath = setting.getBatchGCitP01();
					FileExportPath = true;
				}
			}

			// 5.0 check if exportPath is accessible
			if (FileExportPath == true) {

				File folder = new File(exportPath);
				if (!folder.exists()) {
					parameter = this.batchRecordError(parameter);	
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC076", "errors.ERR1SC076", "");
					// exit current method
			        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_CIT_P01.lastBatch", parameter);
			        String lastBatchId = lastBatch.get("ID_CIT_EXP_BATCH").toString();
			        String currentBatchId = CommonUtils.toString(parameter.get("idBatch"));
			        if(lastBatchId.equals(currentBatchId)||currentBatchId.equals("")){
			            parameter.put("RUN_STATUS", "N");
			            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
			        }
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}

				if (folder.isDirectory() && folder.canRead() && folder.canWrite()) {
					
					String fileNameCsv = "CITIdata_" + MM + yy + ".xls";
					File file = new File(exportPath + fileNameCsv);
					Integer idExpBatch;
					String newFileCsv;
					parameter.put("fileName", fileNameCsv);
					idExpBatch = this.queryDAO.executeForObject(GET_FileName, parameter, Integer.class);

					if (idExpBatch == null) {
						// 7.0 check if filename CITIdata_MMyy.txt exist
						// check physical file
						if(file.exists()) {
							parameter = this.batchRecordError(parameter);	
							doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC077", "errors.ERR1SC077", "");
							result.setErrors(errors);
							result.setMessages(messages);
					        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_CIT_P01.lastBatch", parameter);
					        String lastBatchId = lastBatch.get("ID_CIT_EXP_BATCH").toString();
					        String currentBatchId = CommonUtils.toString(parameter.get("idBatch"));
					        if(lastBatchId.equals(currentBatchId)||currentBatchId.equals("")){
					            parameter.put("RUN_STATUS", "N");
					            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
					        }
							return result;
						} else {
							createBatchHeader = true;
						}
					} else {
						// meaning the name is exist in DB and return id_batch
						newFileCsv = "CITIdata_" + MM + yy + "_" + idExpBatch + ".xls";
						// 8.0 Update FILENAME as CITIdata_MMYY_<ID_CIT_EXP_BATCH>.csv
						parameter.put("BATCH_ID", idExpBatch);
						parameter.put("FILENAME_NEW", newFileCsv);
						this.updateDAO.execute(UPDATE_FileName, parameter);
						createBatchHeader = true;
						// check whether physical file exist
						if (file.exists()) {
							// 9.0 rename physical file
							file.renameTo(new File(exportPath + newFileCsv));
						}
					}
					// 10.0 Create batch header
					if (createBatchHeader == true) {
						parameter.put("status", STATUS_INPROCESS);
						idCitExpBatch = this.updateDAONuked.insert(INSERT_BatchHeader, parameter);
						parameter.put("idBatch", idCitExpBatch);
						parameter.put("idBatchRefNo", idCitExpBatch);							
					} 
					// goto 12.0 retrieve Billing Account
					result = this.retrieveBillAcc(exportPath + fileNameCsv, param.getUvo().getId_user());
					
				} else {
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC076", "errors.ERR1SC076", "");
					// exit current method
					result.setErrors(errors);
					result.setMessages(messages);
			        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_CIT_P01.lastBatch", parameter);
			        String lastBatchId = lastBatch.get("ID_CIT_EXP_BATCH").toString();
			        String currentBatchId = CommonUtils.toString(parameter.get("idBatch"));
			        if(lastBatchId.equals(currentBatchId)||currentBatchId.equals("")){
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
		        Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_CIT_P01.lastBatch", parameter);
		        String lastBatchId = lastBatch.get("ID_CIT_EXP_BATCH").toString();
		        String currentBatchId = CommonUtils.toString(parameter.get("idBatch"));
		        if(lastBatchId.equals(currentBatchId)||currentBatchId.equals("")){
		            parameter.put("RUN_STATUS", "N");
		            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
		        }
				return result;
			}
		}
		// end of execute method
		Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.G_CIT_P01.lastBatch", parameter);
		String lastBatchId = lastBatch.get("ID_CIT_EXP_BATCH").toString();
		String currentBatchId = CommonUtils.toString(parameter.get("idBatch"));
		if(lastBatchId.equals(currentBatchId)||currentBatchId.equals("")){
		    parameter.put("RUN_STATUS", "N");
		    updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
		}
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}

	private GlobalProcessResult retrieveBillAcc(String fileNameCsv, String idUser){

		ArrayList<Object> detailList = new ArrayList<Object>();
					
		try {
			// Process 12.0 & 13.0 Retrieve customers' billing accounts & ccDetails
			// record the previous bill account
			String preID_BILL_ACCOUNT = "";
			String curID_BILL_ACCOUNT = "";
			G_CIT_P01_CITI_Bean[] ccDetails = new G_CIT_P01_CITI_Bean[]{};
			ccDetails = this.queryDAO.executeForObjectArray(GET_CCDetails, parameter, G_CIT_P01_CITI_Bean.class);	
			for (int i = 0; i < ccDetails.length; i++){

				ccDetails[i].setID_LOGIN(idLogin);	
				parameter.put("ID_BILL_ACCOUNT", ccDetails[i].getID_BILL_ACCOUNT());
				// record the current bill account
				curID_BILL_ACCOUNT = ccDetails[i].getID_BILL_ACCOUNT();
				ccDetails[i].setCCARD_NO(SecurityUtil.aesDecrypt(ccDetails[i].getCCARD_NO()));
				
				String screenObj = ccDetails[i].getID_BILL_ACCOUNT();
				String[] dbObj = new String[]{screenObj, "Credit Card Details", "M_CUST_BANKINFO, T_BAC_H"};
				
				String idCust = ccDetails[i].getID_CUST();
				if(idCust == null || idCust.trim().equals("")){
					dbObj = new String[] {screenObj, "Customer ID", "T_BAC_H"};
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}else{
					ccDetails[i].setID_CUST(idCust);
				}
				
				java.math.BigDecimal totalAmtDue = ccDetails[i].getTOTAL_AMT_DUE();
				if(totalAmtDue == null){
					dbObj = new String[] {screenObj, "Total Amount Due", "T_BAC_H"};
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}else{
					ccDetails[i].setTOTAL_AMT_DUE(totalAmtDue);
				}
				
				if(preID_BILL_ACCOUNT.equals(curID_BILL_ACCOUNT)){
					continue;
				}else{
					// set current bill acc
					preID_BILL_ACCOUNT = curID_BILL_ACCOUNT;
					
					G_CIT_P01_CITI_Bean recentBill = this.queryDAO.executeForObject("SELECT.G_CIT_P01.RetrieveBillAccGrouping", parameter, G_CIT_P01_CITI_Bean.class);
					ccDetails[i].setID_REF(recentBill.getID_REF());
					ccDetails[i].setBILL_CURRENCY(recentBill.getBILL_CURRENCY());
					ccDetails[i].setDATE_REQ(recentBill.getDATE_REQ());
					parameter.put("dateReqParam", recentBill.getDATE_REQ());

					// Credit card expired check
					String CCARD_EXPIRED_DATE  = ccDetails[i].getCCARD_EXPIRED_DATE();
					if(CCARD_EXPIRED_DATE.compareTo(parameter.get("executeYearMonth").toString()) < 0){
					    String message = this.getCCardExpiredMessage(ccDetails[i]);
					    
						parameter.put("errorMsg", message);
						this.updateDAO.execute(INSERT_ErrorLog, parameter);
						continue;
					}

					G_CIT_P01_CITI_Bean collectionAmtObj = this.queryDAO.executeForObject("SELECT.G_CIT_P01.CollectionAmtChecking", parameter, G_CIT_P01_CITI_Bean.class);
					BigDecimal TOTAL_AMT_DUE = recentBill.getTOTAL_AMT_DUE();
					
					BigDecimal SUM_BILL_AMOUNT = CommonUtils.isEmpty(collectionAmtObj)? BigDecimal.ZERO:collectionAmtObj.getTOTAL_AMT_DUE();
					
					BigDecimal collectionAmount = TOTAL_AMT_DUE.subtract(SUM_BILL_AMOUNT);
					
					if(collectionAmount.compareTo(BigDecimal.ZERO)<=0){
						continue;
					}
					
					ccDetails[i].setCollectionAmount(collectionAmount);
				}
				// get running Receipt No
				G_CDM_P01 cdm_p01 = new G_CDM_P01(this.queryDAO, getUpdateDAO(), idUser);
											
				String rcpNo = cdm_p01.getGenerateCode("RCPNO", idUser);
				if(rcpNo == null || rcpNo.trim().equals("")){
					dbObj = new String[] {screenObj, "Receipt No", "M_CODE"};
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC071", "errors.ERR1BT018", screenObj, dbObj);
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}else{
					ccDetails[i].setRECEIPT_NO(rcpNo);
				}	
																	
				// Process 14.0 Get ID_Ref
//				Map<String, Object> idRef = this.queryDAO.executeForMap(GET_IdRef, parameter);
//				
//				String idReference = idRef.get("ID_REF").toString();
//				if(idReference == null || idReference.trim().equals("")){
//					String [] scrObj = new String[]{"ID Reference", "T_BIL_H"};
//					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC073", scrObj);
//					result.setErrors(errors);
//					result.setMessages(messages);
//					return result;
//				}else {
//					ccDetails[i].setID_REF(idReference);
//					}
//
//				String billCurr = idRef.get("BILL_CURRENCY").toString();
//				if(billCurr == null || billCurr.trim().equals("")){
//					dbObj = new String[] {screenObj, idReference, "Bill Currency", "T_BIL_H"};
//					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC072", "errors.ERR1BT019", new String[] {screenObj, idReference}, dbObj);
//					result.setErrors(errors);
//					result.setMessages(messages);
//					return result;
//				}else{
//					ccDetails[i].setBILL_CURRENCY(billCurr);
//					}
				detailList.add(ccDetails[i]);
				
			}
		}catch(Exception e){
			doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC064", "errors.ERR1SC064", "");
			// exit current method
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		}
		
		if (detailList.size() > 0) {	
					OutputStream os = null;
					try {
						HashMap<String, Object> blankFieldList = mandCheckBlank();
						FileWriter fw = new FileWriter(fileNameCsv);
						
						Workbook wb = new HSSFWorkbook();  
						//CreationHelper helper = wb.getCreationHelper();
						Sheet sheet1 = wb.createSheet("citibank_export"); 

						writeCsvHeader(sheet1);
						
					    writeCsvContent(detailList, blankFieldList, sheet1);
						
					    for(int i = 0;i<11;i++){
					    	sheet1.autoSizeColumn(i);
					    }

					    os = new FileOutputStream(new File(fileNameCsv));  
					    wb.write(os);  
					    
						result = this.updateCollectionData(detailList);
						
					} catch (IOException e) {
						doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC065", "errors.ERR1BT014", fileNameCsv);
						// exit current method
						result.setErrors(errors);
						result.setMessages(messages);
						return result;
					}finally{
						if(os!=null){
							try {
								os.close();
							} catch (IOException e) {
								e.printStackTrace();
							}  
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
	
	private HashMap<String, Object> mandCheckBlank(){
			
		HashMap<String, Object> blankFieldList = new HashMap<String, Object>();
		String value = "";		
		String refNo = reportHeader("reports.GCITP01.REFNOCONTENT", value) == null ? reportHeader("reports.GCITP01.REFNOCONTENT", value) : "";
		String traceNo = reportHeader("reports.GCITP01.TRACENOCONTENT", value) == null ? reportHeader("reports.GCITP01.TRACENOCONTENT", value) : "";	
		String invNo = reportHeader("reports.GCITP01.INVNOCONTENT", value) == null ? reportHeader("reports.GCITP01.INVNOCONTENT", value) : "";
		String approved = reportHeader("reports.GCITP01.APPROVEDCONTENT", value) == null ? reportHeader("reports.GCITP01.APPROVEDCONTENT", value) : "";
		String approvalCode = reportHeader("reports.GCITP01.APPROVALCODECONTENT", value) == null ? reportHeader("reports.GCITP01.APPROVALCODECONTENT", value) : "";
		String dateTime = reportHeader("reports.GCITP01.DATETIMECONTENT", value) == null ? reportHeader("reports.GCITP01.DATETIMECONTENT", value) : "";
		String remark = reportHeader("reports.GCITP01.REMARKCONTENT", value) == null ? reportHeader("reports.GCITP01.REMARKCONTENT", value) : "";
		blankFieldList.put("refNo", refNo);
		blankFieldList.put("traceNo", traceNo);
		blankFieldList.put("invNo", invNo);
		blankFieldList.put("approved", approved);
		blankFieldList.put("approvalCode", approvalCode);
		blankFieldList.put("dateTime", dateTime);
		blankFieldList.put("remark", remark);
		return blankFieldList;	
	}

	//write Csv Header
	private void writeCsvHeader(Sheet sheet)throws IOException {

		String value = "";
		Row row = sheet.createRow(0); 
       
		Cell cell1 = row.createCell(0); 
		cell1.setCellValue(reportHeader("reports.GCITP01.ORDER", value));
	    
		Cell cell2 = row.createCell(1);
		cell2.setCellValue(reportHeader("reports.GCITP01.CARDNO", value));
		
		Cell cell3 = row.createCell(2);
		cell3.setCellValue(reportHeader("reports.GCITP01.EXPIRYDATE", value));

		Cell cell4 = row.createCell(3);
		cell4.setCellValue(reportHeader("reports.GCITP01.AMOUNT", value));

		Cell cell5 = row.createCell(4);
		cell5.setCellValue(reportHeader("reports.GCITP01.REFNO", value));
		
		Cell cell6 = row.createCell(5);
		cell6.setCellValue(reportHeader("reports.GCITP01.TRACENO", value));

		Cell cell7 = row.createCell(6);
		cell7.setCellValue(reportHeader("reports.GCITP01.INVNO", value));
		
		Cell cell8 = row.createCell(7);
		cell8.setCellValue(reportHeader("reports.GCITP01.APPROVED", value));

		Cell cell9 = row.createCell(8);
		cell9.setCellValue(reportHeader("reports.GCITP01.APPROVALCODE", value));
		
		Cell cell10 = row.createCell(9);
		cell10.setCellValue(reportHeader("reports.GCITP01.DATETIME", value));

		Cell cell11 = row.createCell(10);
		cell11.setCellValue(reportHeader("reports.GCITP01.REMARK", value));
		
	}

	//write Csv Content
	private void writeCsvContent(ArrayList<Object> detailList, HashMap<String, Object> blankFieldList, Sheet sheet) throws IOException {
		Row row = null; 
		for (int i = 0; i < detailList.size(); i++){
			
			row = sheet.createRow(i+1); 			
			G_CIT_P01_CITI_Bean detailObj = (G_CIT_P01_CITI_Bean) detailList.get(i);
			
			Cell cell1 = row.createCell(0); 
			cell1.setCellValue(detailObj.getID_REF().replace("-", ""));
		    
			Cell cell2 = row.createCell(1);
			cell2.setCellValue(detailObj.getCCARD_NO());
			
			Cell cell3 = row.createCell(2);
			cell3.setCellValue(detailObj.getCCARD_EXPIRED_STRING());

			Cell cell4 = row.createCell(3);
			cell4.setCellValue(detailObj.getCollectionAmount().toString());

			Cell cell5 = row.createCell(4);
			cell5.setCellValue(blankFieldList.get("refNo").toString());
			
			Cell cell6 = row.createCell(5);
			cell6.setCellValue(blankFieldList.get("traceNo").toString());

			Cell cell7 = row.createCell(6);
			cell7.setCellValue(blankFieldList.get("invNo").toString());
			
			Cell cell8 = row.createCell(7);
			cell8.setCellValue(blankFieldList.get("approved").toString());

			Cell cell9 = row.createCell(8);
			cell9.setCellValue(blankFieldList.get("approvalCode").toString());
			
			Cell cell10 = row.createCell(9);
			cell10.setCellValue(blankFieldList.get("dateTime").toString());

			Cell cell11 = row.createCell(10);
			cell11.setCellValue(blankFieldList.get("remark").toString());
		}
		
	}

	private GlobalProcessResult updateCollectionData(ArrayList<Object> detailList) {

		try {
			for (int i = 0; i < detailList.size(); i++) {
				
				G_CIT_P01_CITI_Bean ccDetails = (G_CIT_P01_CITI_Bean) detailList.get(i);
				//18.1 insert into T_CSB_H
				String value = "";
				parameter.put("RECEIPT_NO", ccDetails.getRECEIPT_NO());
				parameter.put("ID_CUST", ccDetails.getID_CUST());
				parameter.put("ID_COM_BANK", Integer.parseInt(bankAcc));
				parameter.put("PMT_METHOD", PAY_METHOD);
				parameter.put("CUR_CODE", ccDetails.getBILL_CURRENCY());
				parameter.put("PMT_STATUS",	reportHeader("reports.GCITP01.PMT_STATUS", value));
				parameter.put("REFERENCE1", ccDetails.getID_REF());
				parameter.put("IS_CLOSED", reportHeader("reports.GCITP01.IS_CLOSED", value));
				parameter.put("IS_DELETED",	reportHeader("reports.GCITP01.IS_DELETED", value));
				parameter.put("ID_LOGIN", ccDetails.getID_LOGIN());
				parameter.put("AMT_RECEIVED", ccDetails.getCollectionAmount());
				parameter.put("BANK_CHARGE", reportHeader("reports.GCITP01.BANK_CHARGE", value));
				parameter.put("BALANCE_AMT", ccDetails.getCollectionAmount());
				parameter.put("ID_BILL_ACCOUNT", ccDetails.getID_BILL_ACCOUNT());
				parameter.put("IS_EXPORT", reportHeader("reports.GCITP01.IS_EXPORT", value));
				parameter.put("PAID_PRE_INVOICE", reportHeader("reports.GCITP01.PAID_PRE_INVOICE", value));
				parameter.put("OVER_PAID", reportHeader("reports.GCITP01.OVER_PAID", value));

				int insertResult = this.updateDAO.execute(INSERT_CashBook, parameter);
				if (insertResult == 0) {
					doLogging(STATUS_FAILED, this.SCR, "errors.ERR1SC078", "errors.ERR1SC078", "T_CSB_H");
					// exit current method
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}
				
				// 18.2 Update TOTAL_AMT_DUE
				parameter.put("ID_BILL_ACCOUNT", ccDetails.getID_BILL_ACCOUNT());
				BigDecimal bdAmount = ccDetails.getTOTAL_AMT_DUE().subtract(ccDetails.getCollectionAmount());
				bdAmount = bdAmount.setScale(2, RoundingMode.HALF_UP);
				parameter.put("TOTAL_AMT_DUE", bdAmount);
				this.updateDAO.execute(UPDATE_TotalAmtDue, parameter);
			}
			// moved for 368
//			for (int i = 0; i < detailList.size(); i++) {
//
//				G_CIT_P01_CITI_Bean ccDetails = (G_CIT_P01_CITI_Bean) detailList.get(i);
//				// 18.2 Update TOTAL_AMT_DUE
//				parameter.put("ID_BILL_ACCOUNT", ccDetails.getID_BILL_ACCOUNT());
//				BigDecimal bdAmount = ((java.math.BigDecimal) parameter.get("BALANCE_AMT")).subtract((java.math.BigDecimal) parameter.get("AMT_RECEIVED"));
//				bdAmount = bdAmount.setScale(2, RoundingMode.HALF_UP);
//				parameter.put("TOTAL_AMT_DUE", bdAmount);
//				this.updateDAO.execute(UPDATE_TotalAmtDue, parameter);
//			}
			for (int i = 0; i < detailList.size(); i++) {

				G_CIT_P01_CITI_Bean ccDetails = (G_CIT_P01_CITI_Bean) detailList.get(i);
				// Passing ID_BILL_ACCOUNT to G_CSB_P02
				G_CSB_P02 gCSBP02 = new G_CSB_P02(queryDAO, updateDAO, this.uvo);
				G_CSB_P02_Input input = new G_CSB_P02_Input();
				input.setReceiptNo(ccDetails.getRECEIPT_NO());
				gCSBP02.execute(input);
			}
			// Process 20.0 Update Status Success
			doLogging(STATUS_SUCCESS, this.SCR, "info.ERR2SC029", "", "");
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
		//TODO
		int idCitExpBatch = this.updateDAONuked.insert(INSERT_BatchRecordErr, parameter);	
		parameter.put("idBatch", idCitExpBatch);
		parameter.put("idBatchRefNo", idCitExpBatch);
		return parameter; 
	}
	
	private static void writeCsv(FileWriter fw, String content)
			throws IOException {
		if (content.contains(",")) {
			fw.append("\"" + content.toString().trim() + "\"");
		} else {
			fw.append(content.toString().trim());
		}
	}

	private String reportHeader(String key, Object args) {
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

	private void addError(String msgID, Object... args) {
		errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID, args));
	}

	private void addMsg(String msgID, Object... args) {
		messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(msgID,
				args));
	}

	private void recordLog(String msgID, Object... args) {
		parameter.put("errorMsg", MessageUtil.get(msgID, args));
		int a = this.updateDAO.execute(INSERT_ErrorLog, parameter);
	}

	private void updateStatus(String status) {
		// 6.0 Update the previous process STATUS
		parameter.put("status", status);
		this.updateDAO.execute(UPDATE_ProcessStatus, parameter);
	}

	/**
	 * Generate Credit Card Expired Message.
	 * 
	 * @param bean G_CIT_P01_CITI_Bean
	 * @return
	 */
	private String getCCardExpiredMessage(G_CIT_P01_CITI_Bean bean) {
	    String idBillAccount = bean.getID_BILL_ACCOUNT().trim();
        String idRef = bean.getID_REF().trim();
        String custId = bean.getID_CUST();
        String custName = bean.getCUST_NAME();
        String expiredDate = bean.getCCARD_EXPIRED_DATE1();
        String cCardNo = bean.getCCARD_NO();

        if (cCardNo.length() > 4) {
            String last4Digit = cCardNo.substring(cCardNo.length() - 4);
            String firstDigit = cCardNo.substring(0, cCardNo.length() - 4).replaceAll("\\w", "*");
            cCardNo = firstDigit + last4Digit;
        }

        String message = idBillAccount + "'s credit card (" + cCardNo + ") is already expired (" + expiredDate + "). " +
            "The cash book for "+ idRef + " (" + custId + " - " + custName+ ") was not created.";

        return message;
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