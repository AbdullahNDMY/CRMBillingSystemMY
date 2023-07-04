package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Output;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

public class G_CLC_P01 extends AbstractGlobalProcess<RP_E_MIM_US2_02Input, RP_E_MIM_US2_02Output> {
	
	public static final String STATUS_SUCCESS = "0";
	public static final String STATUS_FAILED = "1";
	public static final String STATUS_INPROCESS = "2";
	
	public static final String INVOICED = "1";
	public static final String NOT_INVOICED = "0";
	
	public static final String ID_CUST_PLAN = "7324";
	public static final String ID_SET_BATCH_G_CLC_P01 = "BATCH_G_CLC_P01";
	public static final String ID_BATCH_TYPE_G_CLC_P01 = "G_CLC_P01";
	public static final String ID_SET_BATCH_TIMEOUT = "BATCH_TIME_INTERVAL";
	public static final Integer SET_SEQ = 1;
	
	public static final String CUSTOMER_CELL = "A";
	public static final String INVOICE_CELL = "B";
	
	public static final int ID_CLC_CUST_LENGTH = 6;
	
	 /** M_MODULES.ID_MODULE **/
    private String auditIdModule = null;
    /** M_SUB_MODULES.ID_SUB_MODULE **/
    private String auditIdSubModule = null;
    /** Reference document/item/file/etc **/
    private String auditReference = null;
    /** Document/Data status **/
    private String auditStatus = null;
    /** Running sequence number **/
    private Integer auditId = null;
    
    //TODO
    private Map<String,Object> parameter=null;
	
	private BLogicMessages errors = new BLogicMessages();
	private BLogicMessages messages = new BLogicMessages();

	public GlobalProcessResult execute(RP_E_MIM_US2_02Input param, RP_E_MIM_US2_02Output outputDTO) {
		this.errors.clear();
		this.messages.clear();
		String currentBatchId = "";
		// Audit Trail
		int closingMonth = CommonUtils.toInteger(param.getMonth());
		int closingYear = CommonUtils.toInteger(param.getYear());			
		Calendar now = Calendar.getInstance();
		now.set(Calendar.MONTH, closingMonth - 1);
		now.set(Calendar.YEAR, closingYear);
		String yyyy = CommonUtils.formatDate(now.getTime(), "yyyy");
		String MM = CommonUtils.formatDate(now.getTime(), "MM");
		
		String closeMonthYear = MM+"/"+yyyy;
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		this.parameter=parameter;
		parameter.put("filename", "NOT AVAILABLE");
		parameter.put("closeMonthYear", MM+"/"+yyyy);
		parameter.put("status", STATUS_FAILED);
		parameter.put("idLogin", param.getUvo().getId_user());
		parameter.put("idBatchType", ID_BATCH_TYPE_G_CLC_P01);
		
        if (auditIdModule != null) {
            this.auditId = CommonUtils.auditTrailBegin(
                    param.getUvo().getId_user(), this.auditIdModule, 
                    this.auditIdSubModule,
                    this.auditReference, this.auditStatus,
                    BillingSystemConstants.AUDIT_TRAIL_CREATED);
            //TODO
            parameter.put("auditId", auditId);
            if(CommonUtils.isEmpty(this.auditId)){
            	//Get the id for next insert
    			Integer tClcImpHdNewInt = this.queryDAO.executeForObject("E_MIM.getSEQ_T_CLC_IMP_HD", null, Integer.class);
    			parameter.put("idClcImpBatch", tClcImpHdNewInt);
    			this.updateDAO.execute("E_MIM.CreateBatchRecordError", parameter);
    			
    			this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC074",  new Object[]{}));
    			this.recordLog(param, tClcImpHdNewInt, "errors.ERR1SC074",  new Object[]{});
    			
    			// End Audit Trail
    			//TODO
    			AuditTrailHeaderBean auditTrailHeaderBean = new AuditTrailHeaderBean();
    			auditTrailHeaderBean.setIdAudit((Integer) parameter.get("auditId"));
    			auditTrailHeaderBean.setReference(parameter.get("idBatchRefNo") + ":"
    					+ parameter.get("closeMonthYear"));
    			String status = (String) parameter.get("status");
    			if ("0".equals(status)) {
    				auditTrailHeaderBean.setStatus("Successful");
    			} else if ("1".equals(status)) {
    				auditTrailHeaderBean.setStatus("Failed");
    			} else if ("2".equals(status)) {
    				auditTrailHeaderBean.setStatus("In process");
    			}
    			CommonUtils.auditTrailUpdate(auditTrailHeaderBean);
    	        if (this.auditIdModule != null) {
    	            CommonUtils.auditTrailEnd(this.auditId);
    	        }
    			return (new GlobalProcessResult()).setErrors(errors).setMessages(messages);
            }
        }
        
        parameter.put("RUN_STATUS", "Y");
        updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
		// 1.0 Check any in process status
		List<HashMap<String, Object>> listT_CLC_IMP_HDDESC = this.queryDAO.executeForObjectList("E_MIM.getT_CLC_IMP_HDDESC", null);
		HashMap<String, Object> latestTClcImpHdDesc = null;
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
//		if(listT_CLC_IMP_HDDESC.size() > 0){
//			latestTClcImpHdDesc = listT_CLC_IMP_HDDESC.get(0);
//		}
		String idLogin = param.getUvo().getId_user();
		// is in process
		boolean isContinue = true;
		// true: in process
		if(listT_CLC_IMP_HDDESC != null && listT_CLC_IMP_HDDESC.size()>0){
			for(int r = 0;r < listT_CLC_IMP_HDDESC.size(); r++){
				latestTClcImpHdDesc = listT_CLC_IMP_HDDESC.get(r);
			    String idClcImpBatch = CommonUtils.toString(latestTClcImpHdDesc.get("ID_CLC_IMP_BATCH"));
				// BATCH_TIME_INTERVAL
				if(CommonUtils.isTimeInterval(latestTClcImpHdDesc.get("DATE_UPDATED_CHAR").toString(), setting.getBatchTimeInterval() * 1000)){
					// 3.0 update T_CLC_IMP_HD with status=Failed (1)
					Map<String, Object> m3 = new HashMap<String, Object>();
					m3.put("idClcImpBatch", idClcImpBatch);
					m3.put("status", STATUS_FAILED);
					m3.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
					m3.put("idLogin", idLogin);
					this.updateDAO.execute("E_MIM.updateStatusT_CLC_IMP_HD", m3);
					parameter.put("idClcImpBatch", idClcImpBatch);
					parameter.put("status", STATUS_FAILED);
					//TODO
					// Call G_ADT to insert audit
					// not implement
					// 4.0 record error for the previous process
					// get seq T_BATCH_LOG
					this.recordLog(param, idClcImpBatch, "errors.ERR1BT016", new Object[]{});
				}else{ // no
					// 2.1 prompt error message
	            	//Get the id for next insert
	    			Integer tClcImpHdNewInt = this.queryDAO.executeForObject("E_MIM.getSEQ_T_CLC_IMP_HD", null, Integer.class);
	    			parameter.put("idClcImpBatch", tClcImpHdNewInt);
	    			this.updateDAO.execute("E_MIM.CreateBatchRecordError", parameter);
	    			this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC059", new Object[]{}));
	    			this.recordLog(param, tClcImpHdNewInt, "errors.ERR1BT020", new Object[]{setting.getBatchTimeInterval()/60});
	                // generateError("errors.ERR1SC059", "errors.ERR1BT016", 
	    			// new Object[]{}, new Object[]{}, CommonUtils.toInteger(tClcImpHdNewInt), param);
	                
					isContinue = false;
					break;
				}
			}
		}
		if(isContinue){
			FormFile formFile = param.getFormFile();			
			// get file location by ID_SET
			String storageLoc = setting.getBatchGClcP01() + "/";
			//Get the id for next insert
			
			//TODO
			
			Integer tClcImpHdNewInt = this.queryDAO.executeForObject("E_MIM.getSEQ_T_CLC_IMP_HD", null, Integer.class);
			
			String fileName = formFile.getFileName();
			String ext = "";
			if (0 < fileName.lastIndexOf(".")) {
			    ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			}
			String tClcImpHdNew = tClcImpHdNewInt + "";
			currentBatchId = tClcImpHdNewInt + "";
			String newFileName = "";
			if (".xlsx".equals(ext)) {
			    newFileName = tClcImpHdNew + ext;
			} else {
			    newFileName = tClcImpHdNew + ".xls";
			}
//			String newFileName = formFile.getFileName();
			//boolean isStep2Continue = true;
			// 5.0 Create batch header record: insert into T_CLC_IMP_HD		
			Map<String, Object> m5 = new HashMap<String, Object>();
			m5.put("idClcImpBatch", tClcImpHdNewInt);
			m5.put("filename", newFileName);
			m5.put("closeMonthYear", MM+"/"+yyyy);
			m5.put("status", STATUS_INPROCESS);
			m5.put("uploadedDate", new Date(Calendar.getInstance().getTimeInMillis()));
			m5.put("createdDate", new Date(Calendar.getInstance().getTimeInMillis()));
			m5.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
			m5.put("idLogin", param.getUvo().getId_user());
			this.updateDAO.execute("E_MIM.insertT_CLC_IMP_HD", m5);
			//TODO
			parameter.put("idClcImpBatch", tClcImpHdNewInt);
			parameter.put("closeMonthYear", MM+"/"+yyyy);
			parameter.put("status", STATUS_INPROCESS);
			//Path value is exits
			if (!CommonUtils.isEmpty(setting.getBatchGClcP01())) {
			 // 6.0  check folder accessible
	            if(isAccessible(storageLoc)){// yes
	                // 8.0 verify import file format
	                List<String[]> data = new ArrayList<String[]>();    
	                boolean dataFormatOK = collectXLSFlatFile(formFile, param, tClcImpHdNewInt ,data);
	                if(dataFormatOK){// correct
	                    //no data
	                    if (0 < data.size()){
	                        // get the data from file
	                        // 9.0 check id customer ID_CLC_CUST
	                        boolean check = isAllCustomerExist(data, tClcImpHdNewInt, param);

                            // 9.1 Check ID_CLC_CUST are Clear Call Services
                            check = check && isAllClearCallData(data, tClcImpHdNewInt, param);
	                        if(check){// all existed
	                            // 10.0 copy import file (refer to M_GSET_D)
	                            // rename and upload file to storageLocation
	                            renameAndUploadFile(storageLoc, newFileName, formFile);
	                            // 11.0 check if re-upload file
	                            // 12.0 is month/year exist T_CLC_IMP_HD and status= success
	                            Map<String, Object> m6 = new HashMap<String, Object>();
	                            m6.put("closeMonthYear", closeMonthYear);
	                            m6.put("status", STATUS_SUCCESS);
	                            List<Map<String, Object>> listT_CLC_IMP_HDByTime = this.queryDAO.executeForObjectList("E_MIM.getT_CLC_IMP_HDByTimeStatus", m6);
	                            if(listT_CLC_IMP_HDByTime.size() > 0){
	                                // existed
	                                //must be check process #12.1
	                                Map<String, Object> m12 = new HashMap<String, Object>();
	                                m12.put("monthYear", closeMonthYear);
	                                boolean hasDupFlag = false;
	                                for(String[] str : data){
	                                    m12.put("idClcCust", CommonUtils.toString(str[0]).trim());
	                                    List<Map<String, Object>> listT_CLC_IMP_MONTHLY_SUM12 = this.queryDAO.executeForObjectList("E_MIM.getT_CLC_IMP_MONTHLY_SUM12", m12);
	                                    if (0 < listT_CLC_IMP_MONTHLY_SUM12.size()) {
	                                        hasDupFlag = true;
	                                        break;
	                                    }
	                                }
	                                // has Duplicate
	                                if (hasDupFlag) {
	                                    Map<String, Object> m7 = new HashMap<String, Object>();
	                                    m7.put("monthYear", closeMonthYear);
	                                    m7.put("isInvoiced", INVOICED);
	                                    int rowNo = 2;
	                                    boolean isRemoveFlag = true;
	                                    for(String[] str : data){
	                                        m7.put("idClcCust", CommonUtils.toString(str[0]).trim());
	                                        // 13.0 Get invoiced record 
	                                        List<Map<String, Object>> invoicedClcImpMonthly = this.queryDAO.executeForObjectList("E_MIM.getT_CLC_IMP_MONTLY_SUMById", m7);
	                                        if(invoicedClcImpMonthly.size() > 0){// true
	                                            //isStep2Continue = false;
	                                            // generate error
	                                            isRemoveFlag = false;
	                                            generateError("errors.ERR1SC085", "errors.ERR1SC085", 
	                                                    new Object[]{rowNo,closeMonthYear}, new Object[]{rowNo,closeMonthYear}, tClcImpHdNewInt, param);
	                                        }
	                                        rowNo = rowNo + 1;
	                                    }
	                                    if (isRemoveFlag) {
	                                        // 14.0 Remove raw record in T_CLC_IMP_MONTHLY_SUM where MONTH_YEAR=xxx.
                                            Map<String, Object> m8 = new HashMap<String, Object>();
                                            m8.put("monthYear", closeMonthYear);
                                            this.updateDAO.execute("E_MIM.deleteT_CLC_IMP_MONTHLY_SUM", m8); 
                                            // 15.0, 16.0, 17.0 Insert monthly sum table
                                            insertMonthlySum(data, tClcImpHdNew, param, closeMonthYear);
	                                    }
	                                } else {
	                                    //no Duplicate
	                                    // 15.0, 16.0, 17.0 Insert monthly sum table
	                                    insertMonthlySum(data, tClcImpHdNew, param, closeMonthYear);
	                                }
	                            }else{// not existed
	                                // 15.0, 16.0, 17.0 Insert monthly sum table
	                                insertMonthlySum(data, tClcImpHdNew, param, closeMonthYear);
	                            }
	                        }
	                    }else{
	                        // generate error
                            generateError("errors.ERR1SC081", "errors.ERR1SC081", 
                                    new Object[]{2}, new Object[]{2},  tClcImpHdNewInt, param);
	                    }
	                }
	            }else{// no
	                //isStep2Continue = false;
	                // generate error
	                generateError("errors.ERR1SC080", "errors.ERR1SC080", 
	                        new Object[]{}, new Object[]{}, 
	                        tClcImpHdNewInt, param);
	            }
			}else{
			    // generate error
                generateError("errors.ERR1SC079", "errors.ERR1SC079", 
                        new Object[]{}, new Object[]{}, 
                        tClcImpHdNewInt, param);
			}
		}
		// End Audit Trail
		//TODO
		AuditTrailHeaderBean auditTrailHeaderBean = new AuditTrailHeaderBean();
		auditTrailHeaderBean.setIdAudit((Integer) this.parameter.get("auditId"));
		auditTrailHeaderBean.setReference(this.parameter.get("idClcImpBatch") + ":"
				+ this.parameter.get("closeMonthYear"));
		String status = (String) this.parameter.get("status");
		if ("0".equals(status)) {
			auditTrailHeaderBean.setStatus("Successful");
		} else if ("1".equals(status)) {
			auditTrailHeaderBean.setStatus("Failed");
		} else if ("2".equals(status)) {
			auditTrailHeaderBean.setStatus("In process");
		}
		CommonUtils.auditTrailUpdate(auditTrailHeaderBean);

        if (this.auditIdModule != null) {
            CommonUtils.auditTrailEnd(this.auditId);
        }
        
        Map<String, Object> lastBatch = queryDAO.executeForMap("E_MIM.getT_CLC_IMP_HD_lastBatch", null);
        String lastBatchId = lastBatch.get("ID_CLC_IMP_BATCH").toString();
        if(currentBatchId.equals(lastBatchId)||currentBatchId.equals("")){
            parameter.put("RUN_STATUS", "N");
            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
        }
		return (new GlobalProcessResult()).setErrors(errors).setMessages(messages);
	}
	
	/**
	 * to check whether ID_CLC_CUST's value from import file is duplicate
	 * @param data
	 * @param tClcImpHdNewInt
	 * @param param
	 * @return boolean yes:true , No:false
	 */
	private boolean isMul(List<String[]> data, Object tClcImpHdNewInt,
			RP_E_MIM_US2_02Input param) {
		String cus8 = "";
		boolean isMul = false;
		
		List<String> listIdClcCust = new ArrayList<String>();
		
		for (int j = 0; j < data.size(); j++) {
			String[] str = (String[]) data.get(j);
			cus8 = str[0];
			//for (String[] customers : data) {
			for(int r=j+1; r<data.size() ;r++){
				String[] customers = (String[]) data.get(r);
				if (cus8.equals(customers[0])) {
					if(!listIdClcCust.contains(cus8)){
						listIdClcCust.add(cus8);
					}
					isMul = true;
					break;
				}
			}
		}
		
		if (isMul) {
			String strListIdClcCust = "";
			for(int i=0;i<listIdClcCust.size()-1;i++){
				strListIdClcCust = strListIdClcCust+listIdClcCust.get(i) + ",";
			}
			strListIdClcCust = strListIdClcCust + listIdClcCust.get(listIdClcCust.size()-1);
			this.errors.add(ActionErrors.GLOBAL_MESSAGE,new BLogicMessage("errors.ERR1SC087",
					new Object[] { strListIdClcCust }));
			this.recordLog(param, tClcImpHdNewInt,"errors.ERR1SC087",
					new Object[] { strListIdClcCust });
		}
		return isMul;
	}
	
	private boolean isAllCustomerExist(List<String[]> lines, Object idBatchRefNo, RP_E_MIM_US2_02Input param){
		boolean check = true;
		int i = 2;
		for(String[] str : lines){
			Map<String, Object> m1 = new HashMap<String, Object>();
			m1.put("idSubInfo", CommonUtils.toString(str[0]).trim());
			List<Map<String, Object>> custPlanId = this.queryDAO.executeForObjectList("E_MIM.getCustPlanId", m1);
			if(custPlanId.size() == 0){
				check = false;
				// 7.0 prompt error msg ERR1SC048 for each fail row
				this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC083", new Object[]{i}));
				this.recordLog(param, idBatchRefNo, "errors.ERR1SC083", new Object[]{i});
			}
			i ++;
		}
		if(!check) {
			Map<String, Object> m9 = new HashMap<String, Object>();
			m9.put("idClcImpBatch", idBatchRefNo);
			m9.put("status", STATUS_FAILED);
			m9.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
			m9.put("idLogin", param.getUvo().getId_user());
			this.updateDAO.execute("E_MIM.updateStatusT_CLC_IMP_HD", m9);
			  //TODO
			this.parameter.put("idClcImpBatch", idBatchRefNo);
            this.parameter.put("status", STATUS_FAILED);
			
		}
		return check;
	}
	
	/**
	 * Check all imported SUB ID is Clear Call Service or not.
	 * 
	 * @param lines Sub ID list 
	 * @param idBatchRefNo Batch type
	 * @param param input parameter
	 * @return true if all sub IDs are Clear Call service
	 */
    private boolean isAllClearCallData(List<String[]> lines, Object idBatchRefNo, RP_E_MIM_US2_02Input param) {
        boolean check = true;
        int i = 2;
        for (String[] str : lines) {
            Map<String, Object> m1 = new HashMap<String, Object>();
            m1.put("idSubInfo", CommonUtils.toString(str[0]).trim());
            List<Map<String, Object>> custPlanId = this.queryDAO.executeForObjectList("E_MIM.getClearCallSubId", m1);
            if (custPlanId.size() == 0) {
                check = false;
                // 7.0 prompt error message ERR1BT021 for each fail row
                this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1BT021", new Object[] { i, CommonUtils.toString(str[0]).trim()}));
                this.recordLog(param, idBatchRefNo, "errors.ERR1BT021", new Object[] { i, CommonUtils.toString(str[0]).trim()});
            }
            i++;
        }
        if (!check) {
            Map<String, Object> m9 = new HashMap<String, Object>();
            m9.put("idClcImpBatch", idBatchRefNo);
            m9.put("status", STATUS_FAILED);
            m9.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
            m9.put("idLogin", param.getUvo().getId_user());
            this.updateDAO.execute("E_MIM.updateStatusT_CLC_IMP_HD", m9);
            //TODO
            this.parameter.put("idClcImpBatch", idBatchRefNo);
            this.parameter.put("status", STATUS_FAILED);
        }
        return check;
    }
	   
	public List<String[]> readFile(FormFile formFile, char seperator) {
		List<String[]> lineList = null;
		CSVReader csvReader = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(formFile.getInputStream()));
			csvReader = new CSVReader(br, seperator);
			lineList = csvReader.readAll();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new FileImportExportException("Cannot import from file");
		} finally {
			try {
				csvReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lineList;
	}
	
	private void renameAndUploadFile(String location, String newFileName, FormFile fileSrc){
		String exportFormFilePath = location + newFileName;
		File exportFormFile = new File(exportFormFilePath);
	    // Upload export form file into Server's file system  
		try {
		    OutputStream ouputStream = new FileOutputStream(exportFormFile);
		    ouputStream.write(fileSrc.getFileData());
		    ouputStream.flush();
	        ouputStream.close();
		    // rename--  f.renameTo(new File("junk.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void reset(){
		this.errors = new BLogicMessages();
		this.messages = new BLogicMessages();
	}
	
	// collect XLS Flat info
	public boolean collectXLSFlatFile(FormFile formFile, RP_E_MIM_US2_02Input param, Object idBatchRefNo,List<String[]> details) {
		boolean isOK = true;
	    String fileName = formFile.getFileName();
	    String ext = "";
	    if (0 < fileName.lastIndexOf(".")) {
	        ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
	    }
		if(!".xls".equals(ext)&&!".xlsx".equals(ext)){
		    isOK = false;
		    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC082", new Object[]{}));
            this.recordLog(param, idBatchRefNo, "errors.ERR1SC082", new Object[]{});
		} else {						
			Workbook wb = null;
			int i = 0;
			try {
			    if (".xls".equals(ext)) {
			        wb = new HSSFWorkbook(formFile.getInputStream());    
			    } else {
			        wb = new XSSFWorkbook(formFile.getInputStream());    
			    }
				Sheet sheet = wb.getSheetAt(0);
				for(i = 1; i <= sheet.getLastRowNum(); i++) {
				    boolean formatTrust = true;
					Row row = sheet.getRow(i);
					int rowNo = i+1;
					if(row==null){
						continue;
					}
					try{
						Cell cusCell = row.getCell(CommonUtils.getColumnIndex(CUSTOMER_CELL));;
						Cell invoiceCell = row.getCell(CommonUtils.getColumnIndex(INVOICE_CELL));
						String[] str = new String[]{CommonUtils.toString(getCellContentFromExcelFile(row, cusCell)).trim(), 
						        CommonUtils.toString(getCellContentFromExcelFile(row, invoiceCell)).trim()};
						
						String idClcCust = CommonUtils.toString(str[0]).trim();
						String invoiceTotal = CommonUtils.toString(str[1]).trim();
						
						if(!CommonUtils.isEmpty(idClcCust)&&CommonUtils.isEmpty(invoiceTotal)){
						    formatTrust = false;
						    isOK = false;
						    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC081", new Object[]{rowNo}));
			                this.recordLog(param, idBatchRefNo, "errors.ERR1SC081", new Object[]{rowNo});
						} else if(CommonUtils.isEmpty(idClcCust)&&!CommonUtils.isEmpty(invoiceTotal)){
						    formatTrust = false;
						    isOK = false;
						    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC081", new Object[]{rowNo}));
                            this.recordLog(param, idBatchRefNo, "errors.ERR1SC081", new Object[]{rowNo});
                        } else if(CommonUtils.isEmpty(idClcCust)&&CommonUtils.isEmpty(invoiceTotal)){
                        	  continue;
//                            formatTrust = false;
//                            isOK = false;
//                            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC081", new Object[]{rowNo}));
//                            this.recordLog(param, idBatchRefNo, "errors.ERR1SC081", new Object[]{rowNo});
                        }else if(!CommonUtils.isEmpty(idClcCust)&&!CommonUtils.isEmpty(invoiceTotal)){
						    try{
						        Double.parseDouble(invoiceTotal);
						    } catch(Exception e3) {
						        formatTrust = false;
						        isOK = false;
						        this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC084", new Object[]{rowNo}));
	                            this.recordLog(param, idBatchRefNo, "errors.ERR1SC084", new Object[]{rowNo});
	                            e3.printStackTrace();
						    }
						}
						
						if(formatTrust){
						    details.add(str);
						}
					}catch(Exception e2){
					    isOK = false;
					    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC081", new Object[]{rowNo}));
                        this.recordLog(param, idBatchRefNo, "errors.ERR1SC081", new Object[]{rowNo});
                        e2.printStackTrace();
					}
				}
			}catch (Exception e1) {
			    isOK = false;
			    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC082", new Object[]{}));
                this.recordLog(param, idBatchRefNo, "errors.ERR1SC082", new Object[]{});
                e1.printStackTrace();
			}
		}
		if (details!=null && 0<details.size()) {
		    for(String[] strArr : details) {
		        String idClcCust = CommonUtils.toString(strArr[0]).trim();
		        strArr[0] = paddingLeftZero(idClcCust, ID_CLC_CUST_LENGTH);
		    }
		}
		if(isOK && 0<details.size()&&isMul(details,idBatchRefNo,param)){
			isOK = false;
		}
		if(!isOK) {
            Map<String, Object> m9 = new HashMap<String, Object>();
            m9.put("idClcImpBatch", idBatchRefNo);
            m9.put("status", STATUS_FAILED);
            m9.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
            m9.put("idLogin", param.getUvo().getId_user());
            this.updateDAO.execute("E_MIM.updateStatusT_CLC_IMP_HD", m9);
            //TODO
            this.parameter.put("idClcImpBatch", idBatchRefNo);
            this.parameter.put("status",STATUS_FAILED );
        }
		return isOK;
	}

	private void insertMonthlySum(List<String[]> data, Object tClcImpHdNew, RP_E_MIM_US2_02Input param, String monthYear){
		// 15.0 insert T_CLC_IMP_MONTHLY_SUM
		for(String[] str : data){
			Map<String, Object> m1 = new HashMap<String, Object>();
			m1.put("idClcImpBatch", tClcImpHdNew);
			m1.put("idClcCust", str[0]);
			m1.put("monthYear", monthYear);
			m1.put("invoiceTotal", str[1]);
			m1.put("isInvoiced", NOT_INVOICED);
			m1.put("createdDate", new Date(Calendar.getInstance().getTimeInMillis()));
			m1.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
			m1.put("idLogin", param.getUvo().getId_user());
			m1.put("auditId", auditId);
			this.updateDAO.execute("E_MIM.insertT_CLC_IMP_MONTHLY_SUM", m1);
		}
		
		// Call G_ADT to insert audit
		// not implement
		// 16.0 prompt success msg info.ERR2SC048
		this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC048", new Object[]{data.size()}));
		// 17.0 update T_CLC_IMP_HD with status=SUCCESS (0)
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("idClcImpBatch", tClcImpHdNew);
		m2.put("status", STATUS_SUCCESS);
		m2.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
		m2.put("idLogin", param.getUvo().getId_user());
		this.updateDAO.execute("E_MIM.updateStatusT_CLC_IMP_HD", m2);
		//TODO
		this.parameter.put("idClcImpBatch", tClcImpHdNew);
		this.parameter.put("status", STATUS_SUCCESS);
	}
	
	//process from 7.0 - 7.3
	public void generateError(String displayMsg, String errorMsg, 
			Object[] displayMsgObject, Object[] errorMsgObject, Integer newIdTClcImpHd, RP_E_MIM_US2_02Input param){
		// 7.0 prompt errors.ERR1SC024
		if(displayMsg != null)
			this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(displayMsg, displayMsgObject));
		// 7.1 update T_CLC_IMP_HD with status=Failed (1)
		Map<String, Object> m9 = new HashMap<String, Object>();
		m9.put("idClcImpBatch", newIdTClcImpHd);
		m9.put("status", STATUS_FAILED);
		m9.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
		m9.put("idLogin", param.getUvo().getId_user());
		this.updateDAO.execute("E_MIM.updateStatusT_CLC_IMP_HD", m9);
		//TODO
		this.parameter.put("idClcImpBatch", newIdTClcImpHd);
		this.parameter.put("status", STATUS_FAILED);
		// 7.2 record error log
		//create error log
		if(errorMsg != null)
			this.recordLog(param, newIdTClcImpHd, errorMsg, errorMsgObject);
	}
	
	private boolean isAccessible(String path) {
		File folder = new File(path);
		if (folder.isDirectory() && folder.canRead() && folder.canWrite()) {
			return true;
		}
		return false;
	}
	
	private void recordLog(RP_E_MIM_US2_02Input param, Object idBatchRefNo, String errID, Object... args) {
		Integer idBatchLog = queryDAO.executeForObject("E_MIM.getSEQ_T_BATCH_LOG", null, Integer.class);
		Map<String, Object> m4 = new HashMap<String, Object>();
		m4.put("idBatchLog", idBatchLog);
		m4.put("idBatchType", ID_BATCH_TYPE_G_CLC_P01);
		m4.put("idBatchRefNo", idBatchRefNo);
		m4.put("errorMsg", MessageUtil.get(errID, args));
		m4.put("createdDate", new Date(Calendar.getInstance().getTimeInMillis()));
		m4.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
		m4.put("idLogin", param.getUvo().getId_user());
		this.updateDAO.execute("E_MIM.insertT_BATCH_LOG", m4);
	}
	
	/**
     * Set auditIdModule
     * 
     * @param auditIdModule
     *            M_MODULES.ID_MODULE
     */
    public void setAuditIdModule(String auditIdModule) {
        this.auditIdModule = auditIdModule;
    }

    /**
     * Set auditIdSubModule
     * 
     * @param auditIdSubModule
     *            M_SUB_MODULES.ID_SUB_MODULE
     */
    public void setAuditIdSubModule(String auditIdSubModule) {
        this.auditIdSubModule = auditIdSubModule;
    }

    /**
     * Set auditReference
     * 
     * @param auditReference
     *            Reference document/item/file/etc
     */
    public void setAuditReference(String auditReference) {
        this.auditReference = auditReference;
    }

    /**
     * Set auditStatus
     * 
     * @param auditStatus
     *            Document/Data status
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    
    /**
     * get CellContentFromExcelFile
     * 
     * @param currentRow
     * @param cell
     * @return
     */
    private String getCellContentFromExcelFile(Row currentRow,
            Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return CommonUtils.parseToString(cell.getDateCellValue(), "dd/MM/yyyy");
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    return cell.getRichStringCellValue().getString() + "";
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return "";
            }
        } else {
            return "";
        }
    }
    
    /**
     * padding left zero
     * @param str
     * @param len
     * @return
     */
    private static String paddingLeftZero(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        for(int i=str.length();i<len;i++) {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();
    }
}