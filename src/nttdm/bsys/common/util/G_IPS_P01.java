package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1_2BlogicInput;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1_2BlogicOutput;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

public class G_IPS_P01 extends AbstractGlobalProcess<E_MIM_US1_2BlogicInput, E_MIM_US1_2BlogicOutput> {
	private static final String ID_BATCH_TYPE = "G_IPS_P01";
	private static final String STATUS_SUCCESS = "0";
	private static final String STATUS_FAILED = "1";
	private static final String STATUS_INPROCESS = "2";
	private static final String FILE_EXTENSION = ".csv";
	private static final String INVOICED = "1";
	private static final String INVOICED_NOT = "0";
	
	private BLogicMessages errors = null;
	private BLogicMessages messages = null;
	private GlobalProcessResult result = null;
	private Map<String, Object> parameter = null;
	private Map<String, Object> latestProcess = null;
	private BatchUtil util = null;
	private BillingSystemSettings setting = null;
	
	public G_IPS_P01() {}

	public GlobalProcessResult execute(E_MIM_US1_2BlogicInput param, E_MIM_US1_2BlogicOutput outputDTO) {
		this.errors = new BLogicMessages();
		this.messages = new BLogicMessages();
		this.result = new GlobalProcessResult();
		this.parameter = new HashMap<String, Object>();
		this.util = new BatchUtil(this.queryDAO, this.updateDAO, param.getUvo());
		setting = new BillingSystemSettings(queryDAO);
		
		parameter.put("idLogin", param.getUvo().getId_user());
		parameter.put("idBatchType", ID_BATCH_TYPE);
		
		// 1.0 Check latest process status
		latestProcess = this.queryDAO.executeForMap("G_IPS_P01_1.0", null);
		if(latestProcess != null && latestProcess.get("STATUS").equals(STATUS_INPROCESS)) {
			// 2.0 Check if In Process has been exceed BATCH_TIME_INTERVAL
			//BATCH_TIME_INTERVAL
			if(CommonUtils.isTimeInterval(latestProcess.get("DATE_UPDATED_CHAR").toString(), setting.getBatchTimeInterval() * 1000)) {
				// 3.0 Update the previous process STATUS=Failed
				parameter.put("status", STATUS_FAILED);
				parameter.put("idBatch", latestProcess.get("ID_IPASS_IMP_BATCH"));
				parameter.put("idBatchRefNo", latestProcess.get("ID_IPASS_IMP_BATCH"));
				this.updateDAO.execute("G_IPS_P01_3.0", parameter);
				// Call G_ADT to insert Audit Trail
				// TODO not implement
				// 4.0 Record error log
				this.recordLog("errors.ERR1BT016");
				// 5.0 create batch header
				this.createBatchHeader(param);
			}
			else {
				// 2.1 Prompt error message, end
				this.addError("errors.ERR1SC059");
				result.setErrors(errors);
				return result;
			}
		}
		else {
			// 5.0 create batch header
			this.createBatchHeader(param);
		}
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	
	private void createBatchHeader(E_MIM_US1_2BlogicInput param) {
		int closingMonth = Integer.parseInt(param.getClosingMonth());
		int closingYear = Integer.parseInt(param.getClosingYear());		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.MONTH, closingMonth - 1);
		now.set(Calendar.YEAR, closingYear);		
		String yyyy = CommonUtils.formatDate(now.getTime(), "yyyy");
		String MM = CommonUtils.formatDate(now.getTime(), "MM");
		
		// 5.0 Create batch header
		Integer idIpassImpBatch = this.queryDAO.executeForObject("G_IPS_P01_SEQ", null, Integer.class);
		parameter.put("idBatch", idIpassImpBatch);
		parameter.put("idBatchRefNo", idIpassImpBatch);
		parameter.put("status", STATUS_INPROCESS);
		String filename = idIpassImpBatch + FILE_EXTENSION;
		parameter.put("filename", filename);
		parameter.put("closeMonthYear", MM+"/"+yyyy);
		this.updateDAO.execute("G_IPS_P01_5.0", parameter);
		
		// 6.0 Check path is accessible
		String importPath = setting.getBatchGIpsP01() + "/";
		if(isAccessible(importPath)) {
			// 8.0 Verify import file format
			List<String[]> lineDatas = verifyImportFormat(param.getFileName());
			if(! lineDatas.isEmpty()) {
				// 9.0 Check ACCESS_ACCOUNT
				if(checkAccessAccount(lineDatas)) {
					// 10.0 Copy the import file into Storage folder. Rename the file name as <batchID>.csv
					try {
						File fileToCreate = new File(importPath, filename);
						if (!fileToCreate.exists()) {
							FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
							fileOutStream.write(param.getFileName().getFileData());
							fileOutStream.flush();
							fileOutStream.close();
						}
					} catch(Exception e) {}
					// 11.0 Check if user has already upload IPASS import file for the selected month and year
					parameter.put("closeMonthYear", MM+"/"+yyyy);
					parameter.put("status", STATUS_SUCCESS);
					List<Map<String, Object>> uploadedList = this.queryDAO.executeForMapList("G_IPS_P01_11.0", parameter);
					// 12.0 Is data already existed in the table, and STATUS=Success then this process is consider re-upload.
					if(! uploadedList.isEmpty()) {
						// 12.1 Check if the import file contain any duplicate record.
						boolean duplicate = false;
						for(String[] str : lineDatas) {
							parameter.put("closeMonthYear", MM+"/"+yyyy);
							parameter.put("status", STATUS_SUCCESS);
							parameter.put("timeLocal", str[6]);
							parameter.put("accessAcc", str[2]+"@"+str[3]);
							String accessAcc = this.queryDAO.executeForObject("G_IPS_P01_12.1", parameter, String.class);
							if(accessAcc != null) {
								duplicate = true;
							}
						}
						if(!duplicate) {
							// 16.0 Inser all new import data to system.
							insertData(param, lineDatas, idIpassImpBatch, MM, yyyy);
							return;
						}
						// 13.0 check whether the record has been proceed to invoiced
						parameter.put("monthYear", MM+"/"+yyyy);
						parameter.put("isInvoiced", INVOICED);
						List<Map<String, Object>> invoicedList = this.queryDAO.executeForMapList("G_IPS_P01_13.0", parameter);
						if(! invoicedList.isEmpty()) {
							// 7.0 Prompt error message
							this.addError("errors.ERR1SC050", new Object[] {0});
							// 7.1 Update T_IPASS_IMP_HD and STATUS=Failed.
							parameter.put("status", STATUS_FAILED);
							this.updateDAO.execute("G_IPS_P01_3.0", parameter);
							// 7.2 Record error log
							this.recordLog("errors.ERR1BT010", new Object[] {0});
							return ;
						}
						// 14.0 Remove raw record in T_IPASS_IMP_RAW where BatchID=xxx.
						for(Map<String, Object> m : uploadedList) {
							parameter.put("idIpassImpBatch", m.get("ID_IPASS_IMP_BATCH"));
							this.updateDAO.execute("G_IPS_P01_14.0", parameter);
						}
						// 15.0 Remove all monthly usage summary in T_IPASS_IMP_MONTLY_SUM where BatchID=xxx.
						parameter.put("monthYear", MM+"/"+yyyy);
						this.updateDAO.execute("G_IPS_P01_15.0", parameter);
					}
					// 16.0 Inser all new import data to system.
					insertData(param, lineDatas, idIpassImpBatch, MM, yyyy);
					return;
				}
				else {
					return;
				}
			}
			else {
				return;
			}
		}
		else {
			// 7.0 Prompt error message
			this.addError("errors.ERR1SC056", new Object[] {importPath});
			// 7.1 Update T_IPASS_IMP_HD and STATUS=Failed.
			parameter.put("status", STATUS_FAILED);
			this.updateDAO.execute("G_IPS_P01_3.0", parameter);
			// 7.2 Record error log
			this.recordLog("errors.ERR1BT012", new Object[] {importPath});
			return ;
		}
	}
	
	private void insertData(E_MIM_US1_2BlogicInput param, List<String[]> lineDatas, Integer idIpassImpBatch, String MM, String yyyy) {
		// 16.0 Inser all new import data to system.
		parameter.put("idBatch", idIpassImpBatch);
		for(String[] str : lineDatas) {
			parameter.put("transId", str[0]);//Transaction ID
			parameter.put("billAcc", str[1]);//Billing Account
			parameter.put("accessAcc", str[2]+"@"+str[3]);//[User ID] + [Authentication Domain]
			
			String accessType = str[10];//Access Type
			String description = str[4];//Description
			parameter.put("codeNo", this.getCodeNo(accessType.trim(), description.trim()));
			
			parameter.put("description", description);
			parameter.put("timeGmt", str[5]);//GMT Time[Date]
			parameter.put("timeLocal", str[6]);//Local Time[Date]
			parameter.put("sessionLen", str[7]);//Length of Session (seconds)
			parameter.put("billRate", str[8]);//Billing Rate
			parameter.put("netBillAmt", str[9]);//Net Billing Amount
			parameter.put("accessType", accessType);//Access Type
			parameter.put("servType", str[11]);//Service Type
			this.updateDAO.execute("G_IPS_P01_16.0", parameter);
		}
		// 17.0 Summarize monthly usage based on ACCESS_ACC  and CODE_NO.
		// 18.0 SUM session usage GROUP BY ACCESS_ACC  and CODE_NO.
		parameter.put("idBatch", idIpassImpBatch);
		List<Map<String, Object>> sessionUsages = this.queryDAO.executeForMapList("G_IPS_P01_18.0", parameter);
		// 19.0 Convert session to minutes format and insert into monthly summary table.
		for(Map<String, Object> session : sessionUsages) {
			String accessAcc = CommonUtils.toString(session.get("ACCESS_ACC"));
			String codeNo = CommonUtils.toString(session.get("CODE_NO"));
			parameter.put("accessAcc", accessAcc);
			parameter.put("codeNo", codeNo);
			parameter.put("monthYear", MM+"/"+yyyy);
			parameter.put("sessionTotal", Math.round(((java.math.BigDecimal) session.get("SESSION_TOTAL")).doubleValue() / 60));
			parameter.put("isInvoiced", INVOICED_NOT);
			this.updateDAO.execute("G_IPS_P01_19.0", parameter);
			// Call G_ADT to insert Audit Trail
			// TODO not implement
		}
		// 20.0 Prompt success Message 
		this.addMsg("info.ERR2SC028");
		// 21.0 Update batch header table T_IPASS_IMP_HD.STATUS=0
		parameter.put("idBatch", idIpassImpBatch);
		parameter.put("status", STATUS_SUCCESS);
		this.updateDAO.execute("G_IPS_P01_3.0", parameter);
	}
	
	private void addError(String msgID, Object... args) {
		errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID, args));
	}
	
	private void addMsg(String msgID) {
		messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(msgID));
	}
	
	private void recordLog(String msgID, Object... args) {
		parameter.put("errorMsg", MessageUtil.get(msgID, args));
		this.updateDAO.execute("G_IPS_P01_4.0", parameter);
	}
	
	private boolean isAccessible(String path) {
		File folder = new File(path);
		if (folder.isDirectory() && folder.canRead() && folder.canWrite()) {
			return true;
		}
		return false;
	}
	
	private List<String[]> verifyImportFormat(FormFile uploadFile) {
		List<String[]> lineDatas = new ArrayList<String[]>();
		String filename = uploadFile.getFileName();
		String ext = filename.substring(filename.lastIndexOf("."), filename.length());
		boolean error = false;
		boolean isFormat = true;
		// dd/mm/yyyy hh:mm
		String regexDate = "(([0-2]?[1-9]|[1-3][0-1])/(0?[1-9]|1[0-2])/\\d{4})\\s+([0-1]?[0-9]|2[0-4]):([0-5]?[0-9])";
		String regexNumber15_2 = "\\d{1,15}([\\.]\\d{0,2})?$";
		if(ext.toLowerCase().equals(FILE_EXTENSION.toLowerCase())) {
			lineDatas = util.readFile(uploadFile);
			for(int i = 0; i < lineDatas.size(); i++) {
				String[] str = lineDatas.get(i);
				error = false;
				if(str.length != 12) //12 fields
					error = true;
				else {
					if(CommonUtils.isEmpty(str[0]) || str[0].length() > 50) {//Transaction ID
						error = true;
					}
					if(CommonUtils.isEmpty(str[1]) || str[1].length() > 20) {//Billing Account
						error = true;
					}
					String accessAcc = str[2]+"@"+str[3];
					if(CommonUtils.isEmpty(accessAcc) || accessAcc.length() > 50) {//[User ID] + [Authentication Domain]
						error = true;
					}
					if(CommonUtils.isEmpty(str[4]) || str[4].length() > 100) {//Description
						error = true;
					}
					if(CommonUtils.isEmpty(str[5]) || !str[5].matches(regexDate)) {//GMT Time
						error = true;
					}
					if(CommonUtils.isEmpty(str[6]) || !str[6].matches(regexDate)) {//Local Time
						error = true;
					}
					try {
						Integer.parseInt(str[7]);//Length of Session
					} catch(Exception e) {
						error = true;
					}
					if(CommonUtils.isEmpty(str[8]) || !str[8].matches(regexNumber15_2)) {//Billing Rate
						error = true;
					}
					if(CommonUtils.isEmpty(str[9]) || !str[9].matches(regexNumber15_2)) {//Net Billing Amount
						error = true;
					}
					if(CommonUtils.isEmpty(str[10]) || str[10].length() > 10) {//Access Type
						error = true;
					}
					if(CommonUtils.isEmpty(str[11]) || str[11].length() > 15) {//Service Type
						error = true;
					}
				}
				if(error) {
					isFormat = false;
					// 7.0 Prompt error message
					this.addError("errors.ERR1SC049", new Object[] {i});
					// 7.2 Record error log
					this.recordLog("errors.ERR1BT006", new Object[] {i, filename});
				}
			}
			if(! isFormat) {
				// 7.1 Update T_IPASS_IMP_HD and STATUS=Failed.
				parameter.put("status", STATUS_FAILED);
				this.updateDAO.execute("G_IPS_P01_3.0", parameter);
				return new ArrayList<String[]>();
			}
		}
		else {
			// 7.0 Prompt error message
			this.addError("errors.ERR1SC049", new Object[] {0});
			// 7.1 Update T_IPASS_IMP_HD and STATUS=Failed.
			parameter.put("status", STATUS_FAILED);
			this.updateDAO.execute("G_IPS_P01_3.0", parameter);
			// 7.2 Record error log
			this.recordLog("errors.ERR1BT006", new Object[] {0, filename});
		}
		return lineDatas;
	}
	
	private boolean checkAccessAccount(List<String[]> lineDatas) {
		String[] ipassDatas;
		String accessAccount, accessAccountRs;
		boolean isExists = true;
		for(int i = 0; i < lineDatas.size(); i++) {
			ipassDatas = lineDatas.get(i);
			accessAccount = ipassDatas[2]+"@"+ipassDatas[3];//User ID + Authentication Domain
			parameter.put("accessAccount", accessAccount);
			accessAccountRs = this.queryDAO.executeForObject("G_IPS_P01_9.0", parameter, String.class);
			if(accessAccountRs == null) {
				isExists = false;
				// 7.0 Prompt error message
				this.addError("errors.ERR1SC048", new Object[] {i});
				// 7.2 Record error log
				this.recordLog("errors.ERR1BT009", new Object[] {i});
			}
		}
		if(!isExists) {
			// 7.1 Update T_IPASS_IMP_HD and STATUS=Failed.
			parameter.put("status", STATUS_FAILED);
			this.updateDAO.execute("G_IPS_P01_3.0", parameter);
		}
		return isExists;
	}
	
	private int getCodeNo(String accessType, String description) {
		if(accessType.equals("WIFI")) {
			return 3;
		}
		if(accessType.equals("ENET")) {
			return 3;
		}
		if(accessType.equals("DIAL")) {
			if(description.contains("TOLLFREE")) {
				return 2;
			}
			else {
				return 1;
			}
		}
		return 0;
	}
}