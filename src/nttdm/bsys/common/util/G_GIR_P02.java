package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicInput;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicOutput;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

public class G_GIR_P02 extends AbstractGlobalProcess<E_DIM_GR1_2BlogicInput, E_DIM_GR1_2BlogicOutput> {

	private BatchUtil util;

	private static final String ID_BATCH_TYPE = "G_GIR_P02";
	private static final String STATUS_SUCCESS = "0";
	private static final String STATUS_FAILED = "1";
	private static final String STATUS_INPROCESS = "2";
	private static final String ENV = "ENV";
	private static final String INS = "INS";
	private static final String FILE_EXTENSION = ".csv";
	
	private static final String SELECT_G_GIR_P02_001 = "SELECT.G_GIR_P02.001";
	private static final String SELECT_G_GIR_P02_009 = "SELECT.G_GIR_P02.009";
	private static final String SELECT_G_GIR_P02_010 = "SELECT.G_GIR_P02.010";
	private static final String SELECT_G_GIR_P02_011 = "SELECT.G_GIR_P02.011";
	
	private static final String UPDATE_G_GIR_P02_001 = "UPDATE.G_GIR_P02.003";	
	private static final String UPDATE_G_GIR_P02_017 = "UPDATE.G_GIR_P02.017";
	//private static final String UPDATE_G_GIR_P02_019 = "UPDATE.G_GIR_P02.019";
	
	private static final String INSERT_G_GIR_P02_004 = "INSERT.G_GIR_P02.004";
	private static final String INSERT_G_GIR_P02_005 = "INSERT.G_GIR_P02.005";
	private static final String INSERT_G_GIR_P02_015 = "INSERT.G_GIR_P02.015";
	private static final String INSERT_G_GIR_P02_016 = "INSERT.G_GIR_P02.016";
	
	private static final String GET_SEQ_ID_GIR_IMP_BATCH = "GET.G_GIR_P02.001";
	
	private static final int POSITION_ENV_ENV = 0;
	private static final int POSITION_ENV_VALUE_DATE = 21;
	private static final int POSITION_ENV_REMIT_CUR = 23;
	private static final int POSITION_INS_INS = 0;
	private static final int POSITION_INS_BANK_CODE = 18;
	private static final int POSITION_INS_ACC_NO = 22;
	private static final int POSITION_INS_COLL_AMOUNT = 28;
	private static final int POSITION_INS_REFERENCE = 37;
	
	public static final Integer REFERENCE_LENGTH = 20;
	
	private String[][] envHeader = null;
	private String[][] insHeader = null;
	private int rowImported = 0;
	private String fileNameImported = null;
	
	private BLogicMessages errors = null;
	private BLogicMessages messages = null;
	private GlobalProcessResult result = null;
	private Map<String, Object> parameter = null;
	private Map<String, Object> latestProcess = null;
	private String[] custIDLoaded = null;
	private Map<Integer, Map<String, Object>> bilHLoaded = null;
	
	/**
	 * version 8
	 */
	@Override
	public GlobalProcessResult execute(E_DIM_GR1_2BlogicInput param, E_DIM_GR1_2BlogicOutput outputDTO) {
		this.errors = new BLogicMessages();
		this.messages = new BLogicMessages();
		this.result = new GlobalProcessResult();
		this.parameter = new HashMap<String, Object>();
		this.rowImported = 0;
		this.fileNameImported = "";
		
		parameter.put("idLogin", param.getUvo().getId_user());
		this.util = new BatchUtil(this.queryDAO, this.updateDAO, param.getUvo());

		BillingSystemSettings setting = new BillingSystemSettings(this.queryDAO);
		boolean inProcess = false;
		boolean createBatchHeader = false;
		// 1.0 Check latest process status 
		List<Map<String, Object>> processStatus = this.queryDAO.executeForMapList(SELECT_G_GIR_P02_001, null);
		if(! processStatus.isEmpty()) {
			latestProcess = processStatus.get(0);
			parameter.put("idGirImpBatch", latestProcess.get("ID_GIR_IMP_BATCH"));
			parameter.put("idBatchRefNo", latestProcess.get("ID_GIR_IMP_BATCH"));
			if(((String) latestProcess.get("STATUS")).equals("2")) {
				inProcess = true;
			}
		}
		if(inProcess) {
			//2.0
			// BATCH_TIME_INTERVAL
			if(CommonUtils.isTimeInterval(latestProcess.get("DATE_UPDATED_CHAR").toString(), setting.getBatchTimeInterval() * 1000)) {
				// 3.0 Update the previous process STATUS=Failed
				parameter.put("status", STATUS_FAILED);
				this.updateDAO.execute(UPDATE_G_GIR_P02_001, parameter);
				// Call G_ADT to insert Audit Trail
				this.insertADT(param.getUvo());
				createBatchHeader = true; // go to 5.0
			}
			else {
				// 2.1 prompt error message, end
				this.addError("errors.ERR1SC059");
				result.setErrors(errors);
				return result;
			}
		}
		else {
			createBatchHeader = true; // go to 5.0
		}
		if(createBatchHeader) {
			// 5.0 Create batch header
			String idGirImpBatch = this.queryDAO.executeForObject(GET_SEQ_ID_GIR_IMP_BATCH, null, String.class);
			String fileName = idGirImpBatch + ".csv";
			parameter.put("idGirImpBatch", idGirImpBatch);
			parameter.put("idBatch", idGirImpBatch);
			parameter.put("idBatchRefNo", idGirImpBatch);
			parameter.put("fileName", fileName);
			parameter.put("status", STATUS_INPROCESS);
			this.updateDAO.execute(INSERT_G_GIR_P02_005, parameter);
			// 6.0 Check ImportPath is accessible.
			String importPath = setting.getBatchGGirP02() + "/";
			File folder = new File(importPath);
			boolean isAccessible = false;
			if(folder.canWrite()) {
				isAccessible = true;
			}
			else {
				this.addError("errors.ERR1SC056", importPath);
				this.recordLog("errors.ERR1BT012", importPath);
			}
			// 8.0 Verify import file format
			boolean rightFormat = false;
			boolean allMatch = true;
			boolean isDuplicate = false;
			boolean mathBilH = true;
			boolean sameCurrency = false;
			if(isAccessible) {
				try {
					List<String[]> dataLines = verifyImportFormat(param.getFileName());
					this.custIDLoaded = new String[dataLines.size()];
					rightFormat = true;
					// 9.0 Retrieve CUST_ID
					for(int i = 1; i < dataLines.size(); i++) {
						parameter.put("bankCode", dataLines.get(i)[POSITION_INS_BANK_CODE].substring(0, 4));
						parameter.put("branchCode", dataLines.get(i)[POSITION_INS_BANK_CODE].substring(4));
						parameter.put("accNo", dataLines.get(i)[POSITION_INS_ACC_NO]);
						String custID = this.queryDAO.executeForObject(SELECT_G_GIR_P02_009, parameter, String.class);
						if(custID == null) {
							allMatch = false;
							this.addError("errors.ERR1SC048", i);
							this.recordLog("errors.ERR1BT009", i);
						}
						else {
							this.custIDLoaded[i] = custID;
						}
					}
					// 10.0 Check has duplicate
					if(allMatch) {
						parameter.put("valueDate", dataLines.get(0)[POSITION_ENV_VALUE_DATE]);
						List<Map<String, Object>> rs = null;
						for(int i = 1; i < dataLines.size(); i++) {
							parameter.put("reference", dataLines.get(i)[POSITION_INS_REFERENCE]);
							rs = this.queryDAO.executeForMapList(SELECT_G_GIR_P02_010, parameter);
							if(!rs.isEmpty()) {
								isDuplicate = true;
								this.addError("errors.ERR1SC050", i);
								this.recordLog("errors.ERR1BT010", i);
								break;
							}
						}
					}
					// 11.0 Check is all import invoice number match to unclose record in T_BIL_H
					if(!isDuplicate) {
						this.bilHLoaded = new HashMap<Integer, Map<String,Object>>();
						for(int i = 1; i < dataLines.size(); i++) {
							parameter.put("reference", dataLines.get(i)[POSITION_INS_REFERENCE]);
							List<Map<String, Object>> rs = this.queryDAO.executeForMapList(SELECT_G_GIR_P02_011, parameter);
							if(rs.isEmpty()) {
								mathBilH = false;
								this.addError("errors.ERR1SC048", i);
								this.recordLog("errors.ERR1BT009", i);
							}
							else {
								this.bilHLoaded.put(i, rs.get(0));
							}
						}
					}
					// 12.0 Get system currency 
					if(mathBilH) {
						String defCur = setting.getDefCurrency();
						// 13.0 Check import currency
						sameCurrency = defCur.equals(dataLines.get(0)[POSITION_ENV_REMIT_CUR]);
//						sameCurrency = true; //tmp code
						if (sameCurrency) {
							// 14.0 Copy import file
							File fileToCreate = new File(importPath, fileName);
							if (!fileToCreate.exists()) {
								FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
								fileOutStream.write(param.getFileName().getFileData());
								fileOutStream.flush();
								fileOutStream.close();
							}
							// 15.0 import data to database 
							this.importData(dataLines, param);
							
							// 18.0 Prompt success message
							this.addMsg("info.ERR2SC028");
							
							// 19.0 Update T_GIR_IMP_HD.STATUS = SUCCESSFUL 
							parameter.put("status", STATUS_SUCCESS);
							this.updateDAO.execute(UPDATE_G_GIR_P02_001, parameter);
						}
						else {
							this.addError("errors.ERR1SC049", 0);
							this.recordLog("errors.ERR1BT006", 0, this.fileNameImported);
						}
					}
				} catch(Exception e) {
					rightFormat = false;
					this.addError("errors.ERR1SC049", this.rowImported);
					this.recordLog("errors.ERR1BT007", this.fileNameImported);
				}
			}
			
			// 7.0 Display and record error
			if(!isAccessible || !rightFormat || !allMatch || isDuplicate || ! mathBilH || !sameCurrency) {
				// 7.1 Update T_GIRO_IMP_HD and STATUS = Failed
				parameter.put("status", STATUS_FAILED);
				this.updateDAO.execute(UPDATE_G_GIR_P02_001, parameter);
				
				result.setErrors(errors);
				result.setMessages(messages);
				return result;
			}
		}
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	
	private void addError(String msgID) {
		errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID));
	}
	
	private void addError(String msgID, Object args) {
		errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID, args));
	}
	
	private void addMsg(String msgID) {
		messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(msgID));
	}
	
	private void recordLog(String msgID, Object... args) {
		parameter.put("idBatchType", ID_BATCH_TYPE);
		parameter.put("errorMsg", MessageUtil.get(msgID, args));
		this.updateDAO.execute(INSERT_G_GIR_P02_004, parameter);
	}
	
	private String[][] getFormatENV() {
		if(this.envHeader == null) {
			this.envHeader = new String[][] { 
				{ "ENV", "M", "3" },
				{ "DefaultID", "B", "20" }, 
				{ "SM", "B", "1" },
				{ "ServiceCD", "B", "2" }, 
				{ "TransferType", "M", "2" },
				{ "TransferSubType", "M", "2" }, 
				{ "DebitBranchCD", "M", "4" },
				{ "DebitSSNo", "B", "40" }, 
				{ "DebitSWIFTAddress", "B", "40" },
				{ "DebitGLCD", "M", "8" }, 
				{ "DebitGLSubCD", "M", "8" },
				{ "DebitAccountNo", "M", "40" }, 
				{ "DebitSubNo", "B", "8" },
				{ "DebitCCY", "M", "3" }, 
				{ "ChargeGLCD", "M", "8" },
				{ "ChargeGLSubCD", "M", "8" },
				{ "ChargeAccountNo", "M", "40" }, 
				{ "ChargeSubNo", "B", "8" },
				{ "ChargeCCY", "M", "3" }, 
				{ "DebitType", "M", "2" },
				{ "CustRef", "B", "20" }, 
				{ "ValueDate", "M", "8" },
				{ "WHTCertificationFlg", "B", "1" },
				{ "RemittCurrency", "M", "3" }, 
				{ "EquivCurrency", "B", "3" },
				{ "ApplicatName", "B", "70" },
				{ "ApplicatPhoneNo", "B", "15" },
				{ "ApplicatAddress", "B", "105" } 
			};
		}
		return this.envHeader;
	}
	
	private String[][] getFormatINS() {
		if(this.insHeader == null) {
			this.insHeader = new String[][] {
				{ "INS", "M", "3" },
				{ "DefaultID", "B", "20" },
				{ "PayerNo", "B", "20" },
				{ "PayerName", "M", "20" },
				{ "PayerNameLocal", "B", "140" },
				{ "Address", "B", "105" },
				{ "AddressLocal", "B", "140" },
				{ "Attention", "B", "50" },
				{ "CountryName", "B", "50" },
				{ "ZipCode", "B", "10" },
				{ "PhoneNo", "B", "15" },
				{ "FaxNo", "B", "15" },
				{ "EmailAddress", "B", "70" },
				{ "WHTID", "B", "10" },
				{ "WHTFormID", "B", "2" },
				{ "WHTRef", "B", "6" },
				{ "BankName", "B", "70" },
				{ "BankNameLocal", "B", "70" },
				{ "BankCode", "M", "7" },
				{ "BranchName", "B", "70" },
				{ "BranchNameLocal", "B", "70" },
				{ "BankCountryName", "M", "50" },
				{ "AccountNo", "M", "11" },
				{ "IntermedBankName", "B", "70" },
				{ "IntermedBankCode", "B", "12" },
				{ "IntermedBranchName", "B", "70" },
				{ "IntermedBankCountryName", "B", "50" },
				{ "InvoiceAmount", "B", "20" },
				{ "CollectionAmoun", "M", "9" },
				{ "VatRate", "B", "13" },
				{ "VatAmount", "B", "20" },
				{ "DiscountAmount", "B", "20" },
				{ "WHTAmount", "B", "20" },
				{ "CalcModeID", "B", "2" },
				{ "OurCharge", "B", "2" },
				{ "BeneCharge", "B", "2" },
				{ "TransactionCD", "M", "2" },
				{ "Reference", "M", "12" },
				{ "Particulars", "O", "12" },
				{ "Purpose", "B", "35" },
				{ "DocSendMethodID", "B", "2" },
				{ "InstructionReferenceNo", "B", "20" },
				{ "Merchandise", "B", "20" },
				{ "Origin", "B", "20" },
				{ "FaxNotification", "B", "1" },
				{ "EmailNotification", "M", "1" },
				{ "DebitDepositType", "B", "2" },
				{ "DebitDepositACNo", "B", "40" },
				{ "DebitRefNo", "B", "10" },
				{ "CreditDepositType", "B", "2" },
				{ "CreditGLCD", "B", "8" },
				{ "CreditGLSubCD", "B", "8" },
				{ "CreditAccountNo", "B", "40" },
				{ "CreditSubNo", "B", "8" },
				{ "CreditCCY", "B", "3" },
				{ "CreditInterestGLCD", "B", "8" },
				{ "CreditInterestGLSubCD", "B", "8" },
				{ "CreditInterestAccountNo", "B", "40" },
				{ "CreditInterestSubNo", "B", "8" },
				{ "CreditInterestCCY", "B", "3" },
				{ "MaturityDate", "B", "8" },
				{ "InterestRate", "B", "13" },
				{ "ContractNo", "B", "20" },
				{ "ContactPerson", "B", "30" },
				{ "PickUpID", "B", "30" },
				{ "Remarks", "B", "20" }
			};
		}
		return this.insHeader;
	}

	private List<String[]> verifyImportFormat(FormFile uploadFile)
			throws FileImportExportException {
		// check .csv extension
		this.fileNameImported = uploadFile.getFileName();
		String extension = this.fileNameImported.substring(this.fileNameImported.length() - 4, this.fileNameImported.length());
		List<String[]> lineDatas = null;
		boolean error = false;
		if (extension.toLowerCase().equals(FILE_EXTENSION.toLowerCase())) {
			// read upload file by line
			lineDatas = util.readFile(uploadFile);
			// Envelpe Common Information Record (first line)
			if(!lineDatas.isEmpty()) {
				String[] line = lineDatas.get(0);
				if(line[POSITION_ENV_ENV].trim().equals(ENV)) {
					if(line.length < this.getFormatENV().length) {
						error = true;
					}
					String valueDate = line[POSITION_ENV_VALUE_DATE];// ValueDate YYYYMMDD
					Integer.parseInt(valueDate);
					if(valueDate.length() != 8) {
						error = true;
					}
					if(! line[POSITION_ENV_REMIT_CUR].trim().equals("SGD")) {
						error = true;
					}
				}
			}
			if(error) {
				throw new FileImportExportException("Wrong import format");
			}
			// Instruction Data Record (second line to end)
			for (this.rowImported = 1; rowImported < lineDatas.size(); rowImported++) {
				String[] line = lineDatas.get(rowImported);
				if(line[POSITION_INS_INS].trim().equals(INS)) {
					if(line.length != this.getFormatINS().length) {
						error = true;
						break;
					}
					String bankCode = line[POSITION_INS_BANK_CODE];
					Integer.parseInt(bankCode);
					if(bankCode.length() != 7) {
						error = true;
						break;
					}
					String accountNo = line[POSITION_INS_ACC_NO];
					Integer.parseInt(accountNo);
					if(line[POSITION_INS_COLL_AMOUNT] == null || line[POSITION_INS_REFERENCE] == null) {
						error = true;
						break;
					}
				}
				if(error) break;
			}
		}
		if(error) {
			throw new FileImportExportException("Wrong import format");
		}
		return lineDatas;
	}
	
	private void importData(List<String[]> dataLines, E_DIM_GR1_2BlogicInput param) {
		String receiptNo = "";
		G_CDM_P01 cmd_p01 = new G_CDM_P01(this.queryDAO,getUpdateDAO(),param.getUvo().getId_user());
		String valueDate = dataLines.get(0)[POSITION_ENV_VALUE_DATE];
		String remittCurrency = dataLines.get(0)[POSITION_ENV_REMIT_CUR];
		for(int i = 1; i < dataLines.size(); i++) {
			// 15.0 insert to T_CSB_H
			try {
				receiptNo = cmd_p01.getGenerateCode("RCPNO", param.getUvo().getId_user());
			} catch (Exception e) {}
			parameter.put("receiptNo", receiptNo);								
			parameter.put("custID", this.custIDLoaded[i]);									
			parameter.put("idComBank", 0);									
			parameter.put("otherPayer", " ");							
			parameter.put("pmtMethod", "GR");
			parameter.put("dateTrans", valueDate);
			parameter.put("receiptRef", valueDate);
			parameter.put("curCode", remittCurrency);
			parameter.put("amtReceived", dataLines.get(i)[POSITION_INS_COLL_AMOUNT]);
			parameter.put("remark", "");
			parameter.put("pmtStatus", "N");								
			Map<String, Object> bilH = this.bilHLoaded.get(i);
			Integer balanceAmt = Integer.parseInt(dataLines.get(i)[POSITION_INS_COLL_AMOUNT])
				- (((java.math.BigDecimal)bilH.get("BILL_AMOUNT")).intValue() - 
						((java.math.BigDecimal)bilH.get("PAID_AMOUNT")).intValue());
			parameter.put("balanceAmt", balanceAmt);
			parameter.put("reference1", "");
			parameter.put("reference2", "");
			parameter.put("bankCharge", 0);
			parameter.put("isClosed", "0");
			parameter.put("isDeleted", "0");
			this.updateDAO.execute(INSERT_G_GIR_P02_015, parameter);
			// 16.0 insert to T_CSB_D
			parameter.put("idRef", bilH.get("ID_REF"));
			parameter.put("forexLoss", 0);
			parameter.put("forexGain", 0);
			parameter.put("amtDue", ((java.math.BigDecimal)bilH.get("BILL_AMOUNT")).intValue() - 
					((java.math.BigDecimal)bilH.get("PAID_AMOUNT")).intValue());
			parameter.put("amtPaid", dataLines.get(i)[POSITION_INS_COLL_AMOUNT]);
			this.updateDAO.execute(INSERT_G_GIR_P02_016, parameter);
			// 17.0 Update T_BIL_H
			parameter.put("paidAmount", ((java.math.BigDecimal)bilH.get("PAID_AMOUNT")).intValue() + 
					Integer.parseInt(dataLines.get(i)[POSITION_INS_COLL_AMOUNT]));
			if(balanceAmt >= 0) {
				parameter.put("isSettle", "1");
			}
			this.updateDAO.execute(UPDATE_G_GIR_P02_017, parameter);
			// Call G_ADT to insert Audit Trail
			this.insertADT(param.getUvo());
		}
	}
	
	// Call G_ADT to insert Audit Trail
	private void insertADT(BillingSystemUserValueObject uvo) {
		try {
			// TODO not implement
		} catch (Exception e) {
			// 4.0 Record error log
			this.recordLog("errors.ERR1BT016");
		}
	}
}