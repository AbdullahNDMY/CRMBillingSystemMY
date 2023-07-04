package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1_3Input;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1_3Output;

public class G_GIR_P01_E extends
		AbstractGlobalProcess<RP_E_MEX_GR1_3Input, RP_E_MEX_GR1_3Output> {
			
	private BLogicMessages errors = null;
	private BLogicMessages messages = null;
	private GlobalProcessResult result = null;
	private Map<String, Object> parameter = null;

	/**
	 * revision 3.0
	 */
	@Override
	public GlobalProcessResult execute(RP_E_MEX_GR1_3Input input,
			RP_E_MEX_GR1_3Output outputDTO) {
		this.errors = new BLogicMessages();
		this.messages = new BLogicMessages();
		this.result = new GlobalProcessResult();
		this.parameter = new HashMap<String, Object>();
		//get parameter
		int idBatch = input.getIdBatch();
		String idLogin = input.getUvo().getId_user();
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
		
		//generate new parameter
		parameter.put("idBatch", idBatch);
		parameter.put("idLogin", idLogin);
		parameter.put("idBatchType", "G_GIR_P01");
		parameter.put("idBatchRefNo", idBatch);
		
		//1.0 get M_GSET_D.BATCH_G_GIR_P01
		String exportPath = setting.getBatchGGirP01() + "/";
		
		//2.0 Check exportPath is accessible.
		File folder = new File(exportPath);
		Date giroDate = CommonUtils.toDate(input.getGiroDay(), input.getGiroMonth(), input.getGiroYear());
		String fileNameTxt = "GIROdata_" + CommonUtils.formatDate(giroDate, "MMyy") + ".txt";
		String fileNameCsv = "GIROdata_" + CommonUtils.formatDate(giroDate, "MMyy") + ".csv";
		String newFileTxt = exportPath+fileNameTxt;
		String newFileCsv = exportPath+fileNameCsv;
		if(folder.canWrite()) {
			//3.0 get T_GIR_EXP_HD.FILENAME
			Map<String, Object> fileNames = this.queryDAO.executeForMap("G_GIR_P01_E_3.0", parameter);
			String fileName = (String) fileNames.get("FILENAME");
			
			// TXT
			//4.0 check the file is existing
			File file = new File(exportPath+fileName);
			String reFileTxt = "GIROdata_" + CommonUtils.formatDate(giroDate, "MMyy")+"_"+idBatch + ".txt";
			if(file.exists()) {				
				//6.0 receive ID_GIR_EXP_BATCH as idBatch
				//7.0 Update FILENAME as GIROdata_MMYY_<ID_GIR_EXP_BATCH>.txt
				parameter.put("fileName", reFileTxt);
				this.updateDAO.execute("G_GIR_P01_E_7.0", parameter);
				//8.0 rename file
				file.renameTo(new File(exportPath+reFileTxt));
			}
			else {
				//9.0 create new export file as GIROdata_MMYY_<ID_GIR_EXP_BATCH>.txt
				try {
					new File(newFileTxt).createNewFile();
				} catch (IOException e) {
					//5.0 Prompt error message, record error log to user
					this.addError("errors.ERR1SC058", newFileTxt);
					this.recordLog("errors.ERR1BT014", newFileTxt);
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}
			}
			
			// CSV
			//4.0 check the file is existing
			File fileCsv = new File(exportPath+fileName.replaceAll(".txt", ".csv"));
			String reFileCsv = "GIROdata_" + CommonUtils.formatDate(giroDate, "MMyy")+"_"+idBatch + ".csv";
			if(fileCsv.exists()) {
				//8.0 rename file
				fileCsv.renameTo(new File(exportPath+reFileCsv));
			}
			else {
				//9.0 create new export file as GIROdata_MMYY_<ID_GIR_EXP_BATCH>.txt
				try {
					new File(newFileCsv).createNewFile();
				} catch (IOException e) {
					//5.0 Prompt error message, record error log to user
					this.addError("errors.ERR1SC058", newFileCsv);
					this.recordLog("errors.ERR1BT014", newFileCsv);
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				}
			}
			
			//10.0 Retrieve GIRO data from T_GIR_EXP_TEMP
			List<Map<String, Object>> gir_tmps = this.queryDAO.executeForMapList("G_GIR_P01_E_10.0", parameter);
			try {
				writeTxt(gir_tmps, newFileTxt, giroDate);
				writeCsv(gir_tmps, newFileCsv, giroDate);
			} catch (IOException e) {}
			
			// 15.0 Update FILENAME as GIROdata_MMYY.txt
			parameter.put("fileName", fileNameTxt);
			this.updateDAO.execute("G_GIR_P01_E_7.0", parameter);
			
			// 17.0 Prompt success message
			this.addMsg("info.ERR2SC030");
		}
		else {
			//5.0 Prompt error message, record error log to user
			this.addError("errors.ERR1SC056", exportPath);
			this.recordLog("errors.ERR1BT012", exportPath);
			result.setErrors(errors);
			result.setMessages(messages);
			return result;
		}		
		
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	
	// 13.0 Write TXT
	private void writeTxt(List<Map<String, Object>> gir_tmps, String newFileTxt, Date giroDate) throws IOException {
		FileOutputStream out = new FileOutputStream(new File(newFileTxt));
		//11.0 Check and write
		if(gir_tmps != null && out != null) {
			String outNTTAC = "200880";
			String outValueDate = "30" + CommonUtils.formatDate(giroDate, "yyMMdd");
			String outDebit = "D";
			String outRef = "NTTSingapore";
			for(Map<String, Object> map : gir_tmps) {
				write(out, outNTTAC, 6);//outNTTAC
				write(out, map.get("GIRO_BANK_CODE"), 4);//outBankCode
				write(out, map.get("GIRO_BANK_CODE"), 4, 3);//outBranchCode
				write(out, map.get("GIRO_ACCT_NO"), 11);//outBankAccountNo
				write(out, map.get("GIRO_NAME"), 20);//outBankAccountName
				write(out, outValueDate, 8);//outValueDate
				
				int outAmount = ((java.math.BigDecimal)map.get("BILL_AMOUNT")).intValue() - 
					((java.math.BigDecimal)map.get("PAID_AMOUNT")).intValue();				
				write(out, outAmount, 12);//outAmount
				
				write(out, outDebit, 1);//outDebit
				write(out, outRef, 12);//outRef
				write(out, map.get("ID_REF"), 12);//outID
			}
			out.close();
		}
	}
	
	// 12.0 and 14.0 Write CSV
	private void writeCsv(List<Map<String, Object>> gir_tmps, String newFileCsv, Date giroDate) throws IOException {
		FileWriter fw = new FileWriter(newFileCsv);
		//write header
		String[] csvHeader = {"No", "CustomerID", "ReferenceNo", "AccountName", "BankName", "BranchName", 
				"BankAccountNo", "TotalAMT", "PayAMT"};
		for(String str : csvHeader) {
			fw.append(str);
			fw.append(',');
		}
		//write content
		for(int i = 0; i < gir_tmps.size(); i++) {
			fw.append('\n');
			Map<String, Object> gir_tmp = gir_tmps.get(i);
			fw.append((i+1)+"");
			fw.append(',');
			writeCsv(fw, gir_tmp.get("CUST_ID"));
			fw.append(',');
			writeCsv(fw, gir_tmp.get("ID_REF"));
			fw.append(',');
			writeCsv(fw, gir_tmp.get("GIRO_ACCT_NAME"));
			fw.append(',');
			writeCsv(fw, gir_tmp.get("GIRO_BANK_NAME"));
			fw.append(',');
			writeCsv(fw, gir_tmp.get("GIRO_BRANCH_NAME"));
			fw.append(',');
			writeCsv(fw, gir_tmp.get("GIRO_ACCT_NO"));
			fw.append(',');
			writeCsv(fw, gir_tmp.get("BILL_AMOUNT"));
			fw.append(',');
			int billAmount = ((java.math.BigDecimal)gir_tmp.get("BILL_AMOUNT")).intValue();
			int paidAmount = ((java.math.BigDecimal)gir_tmp.get("PAID_AMOUNT")).intValue();
			fw.append((billAmount - paidAmount)+"");
		}
		fw.flush();
		fw.close();
	}
	
	private static void writeCsv(FileWriter fw, Object obj) throws IOException {
		if(obj.toString().contains(",")) {
			fw.append("\""+obj.toString()+"\"");
		}
		else {
			fw.append(obj.toString());			
		}
	}
	
	private static void write(FileOutputStream out, Object obj, int len) throws IOException {
		write(out, obj, 0, len);
	}
	
	private static void write(FileOutputStream out, Object obj, int off, int len) throws IOException {
		String str = obj.toString();
		int newLen = Math.min(str.length(), off + len);
		str = str.substring(off, newLen);
		out.write(Arrays.copyOf(str.getBytes(), len));
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
		parameter.put("errorMsg", MessageUtil.get(msgID, args));
		this.updateDAO.execute("G_GIR_P01_E_5.1", parameter);
	}
}
