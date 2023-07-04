/**
 * @(#)G_SGP_P02.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.common.bean.G_CSB_P03_Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Output;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

/**
 * Process to import SingPost & update collection Data
 */
public class G_SGP_P02 extends
        AbstractGlobalProcess<RP_E_DIM_SP1_02Input, RP_E_DIM_SP1_02Output> {

    /** Success **/
    private static final String STATUS_SUCCESS = "0";
    /** Failed **/
    private static final String STATUS_FAILED = "1";
    /** In Process **/
    private static final String STATUS_INPROCESS = "2";

    /** Close **/
    private static final String STATUS_NOT_CLOSED = "0";
    /** Delete **/
    private static final String STATUS_NOT_DELETED = "0";

    /** G_SGP_P02 **/
    private static final String ID_BATCH_TYPE_G_SGP_P02 = "G_SGP_P02";

    /** First line of the file. **/
    private static final int HEADER_RECORD_ID = 0;
    /** First line of the file. **/
    private static final int HEADER_FILE_PROCESSING_DATE = 1;
    /** First line of the file. **/
    private static final int HEADER_FILLER = 2;

    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_RECORD_ID = 0;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_BUSINESS_DATE = 1;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_ACCOUNT_NO = 2;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_INVOICE_NO = 3;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_SERCNTRSERVICE_CENTRE_NO = 4;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_UGID = 5;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_TSN = 6;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_AMOUNT = 7;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_AMT1 = 8;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_MOP1 = 9;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_AMT2 = 10;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_MOP2 = 11;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_AMT3 = 12;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_MOP3 = 13;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_BILL_ACC = 14;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_ID_CUST = 15;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_BILL_CURRENCY = 16;
    /** Second line of the file and onwards for every payment collection **/
    private static final int TRANSACTION_PAID_AMOUNT = 17;

    /** Last line of the file **/
    private static final int TRAILER_RECORD_ID = 0;
    /** Last line of the file **/
    private static final int TRAILER_TOTAL_NO_OF_DETAIL_RECORDS = 1;
    /** Last line of the file **/
    private static final int TRAILER_TOTAL_AMOUNT = 2;
    /** Last line of the file **/
    private static final int TRAILER_FILLER = 3;
    
    /** import data name **/
    private static final String IMPORT_DATA_NAME = "importData";
    /** DuplicatedFlag **/
    private static final String DUPLICATED_FLAG = "DuplicatedFlag";
    /** idRefFlag **/
    private static final String ID_REF_FLAG = "idRefFlag";
    /** file content type **/
    private static final String TXT_FILE_CONTENT_TYPE = "text/plain";

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

    /** errors **/
    private BLogicMessages errors = null;
    /** messages **/
    private BLogicMessages messages = null;
    /** result **/
    private GlobalProcessResult result = null;
    /** parameter **/
    private Map<String, Object> parameter = null;

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
     * init G_SGP_P02
     */
    public G_SGP_P02() {
    }

    /**
     * main execute
     * 
     * @param param
     *            RP_E_DIM_SP1_02Input
     * @param outputDTO
     *            RP_E_DIM_SP1_02Output
     * @return result
     */
    public GlobalProcessResult execute(RP_E_DIM_SP1_02Input param,
            RP_E_DIM_SP1_02Output outputDTO) {

        this.errors = new BLogicMessages();
        this.messages = new BLogicMessages();
        this.result = new GlobalProcessResult();
        this.parameter = new HashMap<String, Object>();
        BillingSystemSettings setting = new BillingSystemSettings(queryDAO);

        parameter.put("idLogin", param.getUvo().getId_user());
        parameter.put("idBatchType", ID_BATCH_TYPE_G_SGP_P02);
        parameter.put("closeMonthYear", param.getMonth() + "/" + param.getYear());
		// Audit Trail
        //TODO
        result.setParameter(this.parameter);
		if (auditIdModule != null) {
			this.auditId = CommonUtils.auditTrailBegin(param.getUvo()
					.getId_user(), this.auditIdModule, this.auditIdSubModule,
					this.auditReference, this.auditStatus,
					BillingSystemConstants.AUDIT_TRAIL_CREATED);
			//TODO
			parameter.put("ID_AUDIT", auditId);
			if (CommonUtils.isEmpty(this.auditId)) {
				parameter.put("filename", "NOT AVAILABLE");
	        	parameter.put("status", STATUS_FAILED);
	            // Get the id for next insert
	            Integer tSgpImpHdNewInt = this.queryDAO.executeForObject(
	                    "E_DIM.getKeySEQ_T_SGP_IMP_HD", null, Integer.class);
	            parameter.put("idBatchRefNo", tSgpImpHdNewInt);
	            parameter.put("idSgpImpHd", tSgpImpHdNewInt);
	            this.updateDAO.execute("E_DIM.CreateBatchRecordError", parameter);
	            
	        	this.addError("errors.ERR1SC074");
	        	this.recordLog("errors.ERR1SC074");
	        	
	            // End Audit Trail
	            if (this.auditIdModule != null) {
	                CommonUtils.auditTrailEnd(this.auditId);
	            }
				result.setErrors(errors);
				result.setMessages(messages);
				return result;
			}
		}
		
        parameter.put("RUN_STATUS", "Y");
        updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
        // 1.0 Check latest process
        List<HashMap<String, Object>> listT_CLC_IMP_HDDESC = 
            this.queryDAO.executeForObjectList(
                    "E_DIM.retrieveProcessStatus", null);
        HashMap<String, Object> latestTClcImpHdDesc = null;
//        if (0 < listT_CLC_IMP_HDDESC.size()) {
//            latestTClcImpHdDesc = listT_CLC_IMP_HDDESC.get(0);
//        }
        // is in process
        boolean isContinue = true;
        // true: in process
        if (listT_CLC_IMP_HDDESC != null&& listT_CLC_IMP_HDDESC.size() > 0) {
            for(int r = 0; r < listT_CLC_IMP_HDDESC.size(); r++){
            	latestTClcImpHdDesc = listT_CLC_IMP_HDDESC.get(r);
	        	// BATCH_TIME_INTERVAL
	            if (CommonUtils.isTimeInterval(
	                    latestTClcImpHdDesc.get("DATE_UPDATED_CHAR").toString(),
	                    setting.getBatchTimeInterval() * 1000)) {
	                // 3.0 update T_CLC_IMP_HD with status=Failed (1)
	                Map<String, Object> m3 = new HashMap<String, Object>();
	                m3.put("idSgpImpBatch",
	                        latestTClcImpHdDesc.get("ID_SGP_IMP_BATCH"));
	                m3.put("status", STATUS_FAILED);
	                m3.put("idLogin", param.getUvo().getId_user());
	                this.updateDAO.execute("E_DIM.updatePrevStatus", m3);
	                // 4.0 record error for the previous process
	                //TODO
	                parameter.put("status", STATUS_FAILED);
	                parameter.put("idBatchRefNo",
	                        latestTClcImpHdDesc.get("ID_SGP_IMP_BATCH"));
	                this.recordLog("errors.ERR1BT016", new Object[] {});
	            } else {
	            	parameter.put("filename", "NOT AVAILABLE");
		        	parameter.put("status", STATUS_FAILED);
		            // Get the id for next insert
		            Integer tSgpImpHdNewInt = this.queryDAO.executeForObject(
		                    "E_DIM.getKeySEQ_T_SGP_IMP_HD", null, Integer.class);
		            parameter.put("idBatchRefNo", tSgpImpHdNewInt);
		            parameter.put("idSgpImpHd", tSgpImpHdNewInt);
		            this.updateDAO.execute("E_DIM.CreateBatchRecordError", parameter);
	                // 2.1 prompt error message
	                this.addError("errors.ERR1SC059");
	                isContinue = false;
	                this.recordLog("errors.ERR1BT020", new Object[] {setting.getBatchTimeInterval()/60});
	                break;
	            }
            }
        }

        if (isContinue) {
            // 5.0 Create batch header record
            this.createBatchHeader(param, setting);
        }

        result.setErrors(errors);
        result.setMessages(messages);
        Map<String, Object> lastBatch = queryDAO.executeForMap("E_DIM.retrieveProcessLastBatch", null);
        String lastBatchID = lastBatch.get("ID_SGP_IMP_BATCH").toString();
        String currentBatchId = CommonUtils.toString(parameter.get("idBatchRefNo"));
        
        if(lastBatchID.equals(currentBatchId)||currentBatchId.equals("")){
            parameter.put("RUN_STATUS", "N");
            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
        }
        
        return result;
    }

    /**
     * Create batch header record
     * 
     * @param param
     *            RP_E_DIM_SP1_02Input
     * @param setting
     *            BillingSystemSettings
     */
    private void createBatchHeader(RP_E_DIM_SP1_02Input param,
            BillingSystemSettings setting) {
        // Get the id for next insert
        Integer tSgpImpHdNewInt = this.queryDAO.executeForObject(
                "E_DIM.getKeySEQ_T_SGP_IMP_HD", null, Integer.class);
        parameter.put("idBatchRefNo", tSgpImpHdNewInt);
        FormFile formFile = param.getFormFile();
        // get file location by ID_SET
        String storageLoc = setting.getBatchGSgpP02() + "/";
        String newFileName = this.generateNewFileName(formFile, tSgpImpHdNewInt);
        // 5.0 Create batch header record: insert into T_SGP_EXP_HD
        Map<String, Object> m6 = new HashMap<String, Object>();
        m6.put("idSgpImpHd", tSgpImpHdNewInt);
        m6.put("filename", newFileName);
        m6.put("closeMonthYear", param.getMonth() + "/" + param.getYear());
        m6.put("status", STATUS_INPROCESS);
        m6.put("idLogin", param.getUvo().getId_user());
        
        this.updateDAO.execute("E_DIM.createBatchHeader", m6);
        //TODO
        this.parameter.put("idSgpImpHd", tSgpImpHdNewInt);
        this.parameter.put("closeMonthYear", param.getMonth() + "/" + param.getYear());
        this.parameter.put("status", STATUS_INPROCESS);
        // 6.0 check folder accessible
       
        boolean isAccessible = false;
        File folder = new File(storageLoc);
        if (folder.canWrite()) {
            isAccessible = true;
        }
        if (CommonUtils.isEmpty(setting.getBatchGSgpP02())) {
            isAccessible = false;
        }
        boolean isStep2Continue = true;
        if (isAccessible) {
            // 8.0 Verify import file format
            isStep2Continue = importFile(param, formFile, storageLoc,
                    newFileName, tSgpImpHdNewInt);
        } else {
            // not accessible
            isStep2Continue = false;
            this.addError("errors.ERR1SC056", storageLoc);
            this.recordLog("errors.ERR1BT012", new Object[] { storageLoc });
        }
        // Notify error 7.0, 7.1, 7.2
        if (!isStep2Continue) {
            // 7.1 update T_CLC_IMP_HD with status=Failed (1)
            Map<String, Object> m11 = new HashMap<String, Object>();
            m11.put("idSgpImpBatch", tSgpImpHdNewInt);
            m11.put("status", STATUS_FAILED);
            m11.put("idLogin", param.getUvo().getId_user());
            this.updateDAO.execute("E_DIM.updatePrevStatus", m11);
            //TODO
            this.parameter.put("idSgpImpBatch", tSgpImpHdNewInt);
            this.parameter.put("status",STATUS_FAILED);
            
        }
    }

    /**
     * Verify import file format
     * 
     * @param param RP_E_DIM_SP1_02Input
     * @param formFile FormFile
     * @param storageLoc String
     * @param newFileName String
     * @param tSgpImpHdNewInt Integer
     * @return result
     */
    private boolean importFile(RP_E_DIM_SP1_02Input param, FormFile formFile,
            String storageLoc, String newFileName, Integer tSgpImpHdNewInt) {
        boolean isStep2Continue = true;

        try {
            // 8.0 verify import file format
            if (isTxtFile(param.getFormFile())) {
                // 8.0 
                List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
                boolean isFormatOk = getDataFromTxtFile(param.getFormFile(), listData);
                if (!isFormatOk) {
                    isStep2Continue = false;
                }
                if (!listData.isEmpty()) {
                    //head and footer data format should be correct
                    if (listData.get(0) != null && listData.get(listData.size()-1) != null){
                        // 9.0 check has duplicate entry in cash book
                        boolean isDuplicateFlag = isDuplicateEntry(listData);
                        if (isDuplicateFlag) {
                            isStep2Continue = false;
                        } 
                        // hasn't duplicate record
                        // 10.0 Update T_SGP_IMP_HD UpdatePrevStatus
                        // 10.1 Insert into T_SGP_IMP_DT SQL: UpdateHD
                        updateHDAndDT(param, listData, tSgpImpHdNewInt);
                        // 11.0 is all data match T_BIL_H
                        boolean isIDRefFlag = isAllInvoiceMatch(listData);
                        if (!isIDRefFlag) {
                            isStep2Continue = false;
                        }
                        // all invoice data matched
                        // 12.0 copy and rename file upload
                        renameAndUploadFile(storageLoc, newFileName, formFile);
                        // loop import data
                        int insertRowCount = 0;
                        for (int i = 0; i < listData.size(); i++) {
                            if (i != 0 && i != listData.size() - 1) {
                                Map<String, Object>  map = listData.get(i);
                                if (map != null) {
                                    Boolean isDuplicatedFlag = (Boolean)map.get(DUPLICATED_FLAG);
                                    Boolean isIdRefFlag = (Boolean)map.get(ID_REF_FLAG);
                                    if (isDuplicatedFlag.booleanValue() && isIdRefFlag.booleanValue()) {
                                        String[] str = (String[])map.get(IMPORT_DATA_NAME);
                                        setFile(str, param);
                                        insertRowCount = insertRowCount + 1;
                                    }
                                }
                            }
                        }
                        if (0 < insertRowCount || listData.size() <= 2) {
                            isStep2Continue = true;
                            // 18.0 Prompt success message ERR2SC048
                            this.addMsg("info.ERR2SC048", insertRowCount);
                            // 17.0 Update A.STATUS = 0 (success)
                            Map<String, Object> m10 = new HashMap<String, Object>();
                            m10.put("idSgpImpBatch", tSgpImpHdNewInt);
                            m10.put("status", STATUS_SUCCESS);
                            m10.put("updatedDate", new java.sql.Date(
                                    Calendar.getInstance().getTimeInMillis()));
                            m10.put("idLogin", param.getUvo().getId_user());
                            this.updateDAO.execute("E_DIM.updatePrevStatus", m10);
                            //TODO
                            this.parameter.put("idSgpImpBatch", tSgpImpHdNewInt);
                            this.parameter.put("status", STATUS_SUCCESS);
                        }
                    } else {
                        isStep2Continue = false;
                    }
                } else {
                    this.addError("errors.ERR1SC049", 1);
                    this.recordLog("errors.ERR1BT006", new Object[] { 1,
                            formFile.getFileName() });
                    isStep2Continue = false;
                }
            } else { // incorrect format
                isStep2Continue = false;
            }
        } catch (Exception e) {
            // Write to all db succesfully?
            isStep2Continue = false;
        }
        // End Audit Trail
        if (this.auditIdModule != null) {
            CommonUtils.auditTrailEnd(this.auditId);
        }
        return isStep2Continue;
    }

    /**
     * setFile
     * @param listData List<String[]>
     * @param i integer
     * @param param RP_E_DIM_SP1_02Input
     * @throws IOException error
     */
    private void setFile(String[] str, RP_E_DIM_SP1_02Input param) throws IOException {
        //String lastIdRef = "";
        Map<String, Object> cond = 
            new HashMap<String, Object>();
        Map<String, Object> result = null;
        List<HashMap<String, Object>> listResult = null;
        // 13.0 Get latest invoice or debit note for the
        // Billing Account
        cond.put("billAcc", str[TRANSACTION_BILL_ACC]);
//        listResult = this.queryDAO.executeForObjectList(
//                        "E_DIM.retrieveIDRef", cond);
//        if (!listResult.isEmpty()) {
//            result = listResult.get(0);
//            lastIdRef = String.valueOf(result.get("ID_REF"));
//        }
        //String totalAmtDue = "0";
        // 13.1 Get TOTAL_AMT_DUE
        listResult = this.queryDAO.executeForObjectList(
                        "E_DIM.getTotalAmtDue", cond);
        if (!listResult.isEmpty()) {
            result = listResult.get(0);
            //totalAmtDue = String.valueOf(result.get("TOTAL_AMT_DUE"));
        }
        // Import ID_REF same as result from process
        // 13.0?
        String paidPreInvoice = "0";
//        if (StringUtils.equals(StringUtils.trim(lastIdRef), 
//                StringUtils.trim(str[TRANSACTION_INVOICE_NO]))) {
//            paidPreInvoice = "0";
//        } else {
//            paidPreInvoice = "1";
//        }
        // 14.0 Update Collection Data
        String receiptNo = InsertCashBook(param, str, paidPreInvoice);
        // 14.3 Update TOTAL_AMT_DUE
        UpdateTotal_Amt_Due(param, str);
        
        // call G_CSB_P03
        G_CSB_P03_Input g_csb_p03Input = new G_CSB_P03_Input();
        g_csb_p03Input.setIdBillAccount(str[TRANSACTION_BILL_ACC]);
        g_csb_p03Input.setIdRef(str[TRANSACTION_INVOICE_NO]);
        g_csb_p03Input.setReceiptNo(receiptNo);
        g_csb_p03Input.setAmtReceived(str[TRANSACTION_AMOUNT]);
        G_CSB_P03 gCsbP03 = new G_CSB_P03(this.queryDAO, getUpdateDAO(), param.getUvo());
        gCsbP03.execute(g_csb_p03Input);
        
        // 15.0 G_CSB_P02
//        G_CSB_P02_Input input = new G_CSB_P02_Input();
//        // Pass parameter $ID_BILL_ACCOUNT
//        input.setIdBillAccount(str[TRANSACTION_BILL_ACC]);
//        G_CSB_P02 gCsbP02 = new G_CSB_P02(
//                this.queryDAO, getUpdateDAO(),
//                param.getUvo());
//        gCsbP02.execute(input);
    }
    
    /**
     * Format correct?
     * 
     * @param formFile FormFile
     * @return file date
     */
    private boolean isTxtFile(FormFile formFile) {
        boolean check = true;
        String fileName = formFile.getFileName();
        String fileType = formFile.getContentType();
        if (!TXT_FILE_CONTENT_TYPE.equalsIgnoreCase(fileType)) {
            this.addError("errors.ERR1SC049", 1);
            this.recordLog("errors.ERR1BT006", new Object[] { 1, fileName });
            check = false;
        }
        return check;
    }

    /**
     * Insert into T_CSB_H
     * 
     * @param param
     *            RP_E_DIM_SP1_02Input
     * @param str
     *            String[]
     * @param paidPreInvoice
     *            String
     * @return
     * @throws IOException
     *             error
     */
    private String InsertCashBook(RP_E_DIM_SP1_02Input param, String[] str,
            String paidPreInvoice) throws IOException {

        try {
            // 12.0 insert into T_CSB_H
            Map<String, Object> m7 = new HashMap<String, Object>();
            // get $ReceiptNo by passing "RCPNO" to
            // program G_CDM_P01.
            String receiptNo = "";
            G_CDM_P01 cmd_p01 = new G_CDM_P01(this.queryDAO, getUpdateDAO(),
                    param.getUvo().getId_user());
            receiptNo = cmd_p01.getGenerateCode("RCPNO", 
                    param.getUvo().getId_user());

            // $Business Date + $UGID + $TSN
            String receiptRef = str[TRANSACTION_BUSINESS_DATE]
                    + str[TRANSACTION_UGID] + str[TRANSACTION_TSN];
            m7.put("receiptNo", receiptNo);
            m7.put("idCust", str[TRANSACTION_ID_CUST]);
            m7.put("idComBank", param.getBankAcc());
            m7.put("idBillAccount", str[TRANSACTION_BILL_ACC]);
            // "SP" is means payment by "SINGPOST".
            m7.put("pmtMethod", "SP");
            // $Business Date
            m7.put("dateTrans", str[TRANSACTION_BUSINESS_DATE]);
            m7.put("receiptRef", receiptRef.trim());
            // M_GSET_D.DEF_CURRENCY
            m7.put("curCode", str[TRANSACTION_BILL_CURRENCY]);
            // value from global setting.
            // $Amount
            m7.put("amtReceived", str[TRANSACTION_AMOUNT]);
            m7.put("remark", "");
            // refer to resource file - PAYMENT STATUS
            m7.put("pmtStatus", "N");
            m7.put("balanceAmt", str[TRANSACTION_AMOUNT]);
            m7.put("reference1", str[TRANSACTION_INVOICE_NO]);
            m7.put("reference2", "");
            m7.put("bankCharge", 0);
            m7.put("paidPreInvoice", paidPreInvoice);
            m7.put("overPaid", 0);
            m7.put("isClosed", STATUS_NOT_CLOSED);
            m7.put("isDeleted", STATUS_NOT_DELETED);
            m7.put("idLogin", param.getUvo().getId_user());
            m7.put("auditId", this.auditId);

            this.updateDAO.execute("E_DIM.insertCashBook", m7);
            
            return receiptNo;
        } catch (Exception e) {
            // Write to all db succesfully?
            this.addError("errors.ERR1SC060", "T_CSB_H");
            this.recordLog("errors.ERR1BT017", "T_CSB_H");
            throw new IOException(e.toString());
        }

    }

    /**
     * Update Total_Amt_Due
     * 
     * @param param
     *            RP_E_DIM_SP1_02Input
     * @param str
     *            String[]
     * @throws IOException
     *             error
     */
    private void UpdateTotal_Amt_Due(RP_E_DIM_SP1_02Input param, String[] str) throws IOException {

        try {
            // 14.0 Update T_BIL_H
            Map<String, Object> m9 = new HashMap<String, Object>();
            m9.put("idBillAccount", str[TRANSACTION_BILL_ACC]);
            m9.put("totalAmtDue", str[TRANSACTION_AMOUNT]);
            m9.put("idLogin", param.getUvo().getId_user());
            m9.put("auditId", this.auditId);
            this.updateDAO.execute("E_DIM.updateTotal_Amt_Due", m9);
        } catch (Exception e) {
            // Write to all db succesfully?
            this.addError("errors.ERR1SC060", "T_BAC_H table");
            this.recordLog("errors.ERR1BT017", "T_BAC_H table");
            throw new IOException(e.toString());
        }
    }

    /**
     * get file data
     * 
     * @param formFile FormFile
     * @return data
     */
    private boolean getDataFromTxtFile(FormFile formFile, List<Map<String,Object>> lineList) {
        boolean isFormatOk = true;
        int i = 0;
        
        String recordID = "";
        String businessDate = "";
        String fileProcessDate = "";
        //String accountNo = "";
        //String inviceNo = "";
        //String ugId = "";
        //String tsn = "";
        String amount = "";
        try {
            InputStreamReader reader = new InputStreamReader(
                    formFile.getInputStream());
            BufferedReader br = new BufferedReader(reader);
            int totalRow = getTotalRowInTxtFile(formFile);
            String singleLine = null;
            int rowNo = 0;
            while ((singleLine = br.readLine()) != null) {
                // read each row from file
                rowNo = rowNo + 1;
                if (singleLine.length() != 97) {
                    isFormatOk = false;
                    lineList.add(null);
                    this.addError("errors.ERR1SC049", rowNo);
                    this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                            formFile.getFileName() });
                    i++;
                    continue;
                }
                if (i == 0) { // first line : File Header Record
                    if(!"H".equals(singleLine.substring(0, 1))){
                        isFormatOk = false;
                        lineList.add(null);
                        this.addError("errors.ERR1SC049", rowNo);
                        this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                                formFile.getFileName() });
                        i++;
                        continue;
                    }
                    fileProcessDate = singleLine.substring(1, 9);
                    fileProcessDate = fileProcessDate.substring(0, 4) + "-" + 
                                        fileProcessDate.substring(4, 6) + "-" +
                                        fileProcessDate.substring(6, 8);
                    if(!checkDate(fileProcessDate)){
                        isFormatOk = false;
                        lineList.add(null);
                        this.addError("errors.ERR1SC049", rowNo);
                        this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                                formFile.getFileName() });
                        i++;
                        continue;
                    }
                    Map<String,Object> map = new HashMap<String,Object>();
                    String[] str = new String[] { singleLine.substring(0, 1),
                            singleLine.substring(1, 9),
                            singleLine.substring(9, 97) };
                    map.put(IMPORT_DATA_NAME, str);
                    lineList.add(map);
                } else if (i == (totalRow - 1)) { // last row : File
                                                  // Trailer Record
                    if(!"F".equals(singleLine.substring(0, 1))){
                        isFormatOk = false;
                        lineList.add(null);
                        this.addError("errors.ERR1SC049", rowNo);
                        this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                                formFile.getFileName() });
                        i++;
                        continue;
                    }
                    Map<String,Object> map = new HashMap<String,Object>();
                    String[] str = new String[] { singleLine.substring(0, 1),
                            singleLine.substring(1, 9),
                            singleLine.substring(9, 21),
                            singleLine.substring(21, 97) };
                    map.put(IMPORT_DATA_NAME, str);
                    lineList.add(map);
                } else {// middle : Transaction Record
                    // last row : File
                    recordID = CommonUtils.toString(singleLine.substring(0, 1)).trim();
                    businessDate = CommonUtils.toString(singleLine.substring(1, 9)).trim();
                    //accountNo = CommonUtils.toString(singleLine.substring(9, 19)).trim();
                    //inviceNo = CommonUtils.toString(singleLine.substring(19, 37)).trim();
                    //ugId = CommonUtils.toString(singleLine.substring(40, 44)).trim();
                    //tsn = CommonUtils.toString(singleLine.substring(44, 48)).trim();
                    amount = CommonUtils.toString(singleLine.substring(48, 58)).trim();
                    if (!"D".equals(recordID)) {
                        isFormatOk = false;
                        lineList.add(null);
                        this.addError("errors.ERR1SC049", rowNo);
                        this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                                formFile.getFileName() });
                        i++;
                        continue;
                    }
                    businessDate = businessDate.substring(0, 4) + "-" + 
                                    businessDate.substring(4, 6) + "-" +
                                    businessDate.substring(6, 8);
                    if(!checkDate(businessDate)){
                        isFormatOk = false;
                        lineList.add(null);
                        this.addError("errors.ERR1SC049", rowNo);
                        this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                                formFile.getFileName() });
                        i++;
                        continue;
                    }
                    try {
                        if(Double.parseDouble(amount) == 0.0) {
                            isFormatOk = false;
                            lineList.add(null);
                            this.addError("errors.ERR1SC049", rowNo);
                            this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                                    formFile.getFileName() });
                            i++;
                            continue;
                        }
                    } catch (Exception ex) {
                        isFormatOk = false;
                        lineList.add(null);
                        this.addError("errors.ERR1SC049", rowNo);
                        this.recordLog("errors.ERR1BT006", new Object[] { rowNo,
                                formFile.getFileName() });
                        i++;
                        continue;
                    }
                    Map<String,Object> map = new HashMap<String,Object>();
                    String[] str = new String[] { singleLine.substring(0, 1),
                            singleLine.substring(1, 9),
                            singleLine.substring(9, 19),
                            singleLine.substring(19, 37),
                            singleLine.substring(37, 40),
                            singleLine.substring(40, 44),
                            singleLine.substring(44, 48),
                            singleLine.substring(48, 58),
                            singleLine.substring(58, 68),
                            singleLine.substring(68, 71),
                            singleLine.substring(71, 81),
                            singleLine.substring(81, 84),
                            singleLine.substring(84, 94),
                            singleLine.substring(94, 97), "", "", "", "" };
                    map.put(IMPORT_DATA_NAME, str);
                    lineList.add(map);
                }
                i++;
            }
            br.close();
            reader.close();
        } catch (Exception ex) {
            lineList = new ArrayList<Map<String,Object>>();
        }
        return isFormatOk;
    }

    /**
     * get file data
     * 
     * @param txtFile FormFile
     * @return
     * @throws IOException error
     * @throws FileNotFoundException error
     * @return date
     */
    private int getTotalRowInTxtFile(FormFile txtFile)
            throws FileNotFoundException, IOException {
        int count = 0;
        InputStreamReader read = new InputStreamReader(
                txtFile.getInputStream());
        LineNumberReader ln = new LineNumberReader(read);
        while (ln.readLine() != null) {
            count++;
        }
        ln.close();
        read.close();
        return count;
    }

    /**
     * Check has duplicate entry in cash book
     * 
     * @param data List<String[]>
     * @return resultList<String[]>
     */
    private boolean isDuplicateEntry(List<Map<String,Object>> data) {
        boolean isDuplicated = false;
        int rowNo = 0;
        for (int i = 0; i < data.size(); i++) {
            rowNo = rowNo + 1;
            if (i != 0 && i != data.size() - 1) {
                Map<String, Object>  map = data.get(i);
                if (map != null){
                    String[] str = (String[])map.get(IMPORT_DATA_NAME);
                    // $Business Date + $UGID + $TSN
                    String receiptRef = str[TRANSACTION_BUSINESS_DATE]
                            + str[TRANSACTION_UGID] + str[TRANSACTION_TSN];
                    String invoiceNo = str[TRANSACTION_INVOICE_NO];
                    Map<String, Object> m = new HashMap<String, Object>();
                    m.put("receiptRef", receiptRef.trim());
                    m.put("invoiceNo", invoiceNo.trim());
                    List<HashMap<String, Object>> listDuplicate = 
                        this.queryDAO.executeForObjectList(
                                "E_DIM.checkDuplicateEntry", m);
                    if ( 0 < listDuplicate.size() ) {
                        this.addError("errors.ERR1SC050", rowNo);
                        this.recordLog("errors.ERR1BT010", rowNo);
                        isDuplicated = true;
                        map.put(DUPLICATED_FLAG, false);
                    } else {
                        map.put(DUPLICATED_FLAG, true);
                    }
                }
            }
        }
        return isDuplicated;
    }
    

    /**
     * Check import data
     * 
     * @param data
     *            file data
     * @return boolean
     */
    private boolean isAllInvoiceMatch(List<Map<String,Object>> data) {
        boolean check = true;
        int rowNo = 0;
        // 11.0 Check import data
        for (int i = 0; i < data.size(); i++) {
            rowNo = rowNo + 1;
            if (i != 0 && i != data.size() - 1) {
                Map<String, Object>  map = data.get(i);
                if (map != null) {
                    Boolean isDuplicatedFlag = (Boolean)map.get(DUPLICATED_FLAG);
                    if (isDuplicatedFlag.booleanValue()) {
                        String[] str = (String[])map.get(IMPORT_DATA_NAME);
                        String invoiceNo = str[TRANSACTION_INVOICE_NO];
                        Map<String, Object> m = new HashMap<String, Object>();
                        m.put("invoiceNo", invoiceNo.trim());
                        m.put("billAcc", str[TRANSACTION_ACCOUNT_NO].trim());
                        List<HashMap<String, Object>> listMatchInvoice = 
                            this.queryDAO.executeForObjectList(
                                    "E_DIM.validateIDRef", m);
                        // All ID_REF exist?
                        if (listMatchInvoice.size() == 0) {
                            this.addError("errors.ERR1SC086", rowNo);
                            this.recordLog("errors.ERR1SC086", rowNo);
                            check = false;
                            map.put(ID_REF_FLAG, false);
                        } else {
                            HashMap<String, Object> matchedInvoice = 
                                listMatchInvoice.get(0);
                            str[TRANSACTION_BILL_ACC] = 
                                String.valueOf(matchedInvoice.get("BILL_ACC"));
                            str[TRANSACTION_ID_CUST] = 
                                String.valueOf(matchedInvoice.get("ID_CUST"));
                            str[TRANSACTION_BILL_CURRENCY] = 
                                String.valueOf(matchedInvoice.get("BILL_CURRENCY"));
                            str[TRANSACTION_PAID_AMOUNT] = 
                                String.valueOf(matchedInvoice.get("PAID_AMOUNT"));
                            map.put(ID_REF_FLAG, true);
                        }
                    }
                }
            }
        }
        return check;
    }

    /**
     * Update T_SGP_IMP_HD Insert into T_SGP_IMP_DT
     * 
     * @param param
     *            RP_E_DIM_SP1_02Input
     * @param data
     *            file data
     * @param tSgpImpHdNewInt
     *            id
     */
    private void updateHDAndDT(RP_E_DIM_SP1_02Input param, List<Map<String,Object>> data,
            Integer tSgpImpHdNewInt) {
        String headerId = "";
        String headerFiller = "";
        String fileProcDate = "";
        String footerId = "";
        String footerFiller = "";
        String totalrec = "";
        String totalamt = "";
        boolean hasNoDuplicatedFlag = false;
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object>  map = data.get(i);
            if (map != null){
                String[] str = (String[])map.get(IMPORT_DATA_NAME);
                if (i != 0 && i != data.size() - 1) {
                    // 10.1 Insert into T_SGP_IMP_DT SQL: InsertDT
                    Boolean isDuplicatedFlag = (Boolean)map.get(DUPLICATED_FLAG);
                    if (isDuplicatedFlag.booleanValue()) {
                        hasNoDuplicatedFlag = true;
                        Map<String, Object> insertDT = new HashMap<String, Object>();
                        insertDT.put("idSgpImpBatch", tSgpImpHdNewInt);
                        insertDT.put("seqNo", i);
                        insertDT.put("detailId", str[TRANSACTION_RECORD_ID]);
                        insertDT.put("businessDate", str[TRANSACTION_BUSINESS_DATE]);
                        insertDT.put("accountNo", str[TRANSACTION_ACCOUNT_NO]);
                        insertDT.put("invoiceNo", str[TRANSACTION_INVOICE_NO]);
                        insertDT.put("scNo", str[TRANSACTION_SERCNTRSERVICE_CENTRE_NO]);
                        insertDT.put("ugid", str[TRANSACTION_UGID]);
                        insertDT.put("tsn", str[TRANSACTION_TSN]);
                        insertDT.put("amount", str[TRANSACTION_AMOUNT]);
                        insertDT.put("amt1", str[TRANSACTION_AMT1]);
                        insertDT.put("mop1", str[TRANSACTION_MOP1]);
                        insertDT.put("amt2", str[TRANSACTION_AMT2]);
                        insertDT.put("mop2", str[TRANSACTION_MOP2]);
                        insertDT.put("amt3", str[TRANSACTION_AMT3]);
                        insertDT.put("mop3", str[TRANSACTION_MOP3]);
                        insertDT.put("idLogin", param.getUvo().getId_user());
                        this.updateDAO.execute("E_DIM.insertDT", insertDT);
                    }
                } else if (i == 0) {
                    headerId = str[HEADER_RECORD_ID];
                    headerFiller = str[HEADER_FILLER];
                    fileProcDate = str[HEADER_FILE_PROCESSING_DATE];
                }
                if (i == data.size() - 1) {
                    footerId = str[TRAILER_RECORD_ID];
                    footerFiller = str[TRAILER_FILLER];
                    totalrec = str[TRAILER_TOTAL_NO_OF_DETAIL_RECORDS];
                    totalamt = str[TRAILER_TOTAL_AMOUNT];
                }
            }
        }
        if (hasNoDuplicatedFlag) {
            // 10.0 Update T_SGP_IMP_HD SQL: UpdateHD
            Map<String, Object> updateHD = new HashMap<String, Object>();
            updateHD.put("idSgpImpBatch", tSgpImpHdNewInt);
            updateHD.put("headerId", headerId);
            updateHD.put("headerFiller", headerFiller);
            updateHD.put("fileProcDate", fileProcDate);
            updateHD.put("footerId", footerId);
            updateHD.put("footerFiller", footerFiller);
            updateHD.put("totalrec", totalrec);
            updateHD.put("totalamt", totalamt);
            updateHD.put("idLogin", param.getUvo().getId_user());
            this.updateDAO.execute("E_DIM.updateHD", updateHD);
        }
    }

    /**
     * Copy the import file into Storage. Rename the file name as <batchID>.txt
     * 
     * @param location
     *            String
     * @param newFileName
     *            String
     * @param fileSrc
     *            FormFile
     * @throws IOException
     *             error
     */
    private void renameAndUploadFile(String location, String newFileName,
            FormFile fileSrc) throws IOException {
        // Upload export form file into Server's file system
        String exportFormFilePath = location + newFileName;
        File exportFormFile = new File(exportFormFilePath);
        OutputStream ouputStream = new FileOutputStream(exportFormFile);
        ouputStream.write(fileSrc.getFileData());
        // rename-- f.renameTo(new File("junk.dat"));
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * reset
     */
    public void reset() {
        this.errors = new BLogicMessages();
        this.messages = new BLogicMessages();
    }

    /**
     * Display message on screen
     * 
     * @param msgID
     *            String
     * @param args
     *            Object
     */
    private void addError(String msgID, Object... args) {
        this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID,
                args));
    }

    /**
     * Display message on screen
     * 
     * @param msgID String
     */
    private void addMsg(String msgID, Object... args) {
        this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(msgID, 
                args));
    }

    /**
     * Record error log
     * 
     * @param msgID String
     * @param args Object
     */
    private void recordLog(String msgID, Object... args) {
        // get seq T_BATCH_LOG
        Integer idBatchLog = queryDAO.executeForObject(
                "E_DIM.getSEQ_T_BATCH_LOG", null, Integer.class);
        parameter.put("idBatchLog", idBatchLog);
        parameter.put("errorMsg", MessageUtil.get(msgID, args));
        this.updateDAO.execute("E_DIM.insertErrorLog1", parameter);
    }
    
    private boolean checkDate(String checkValue) {
        String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(checkValue);
        boolean b = m.matches();
        if (b) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Generate New File Name
     * 
     * @param formFile FormFile
     * @param tSgpImpHdNewInt ProcessID
     * @return new file name
     */
    private String generateNewFileName(FormFile formFile, Integer tSgpImpHdNewInt) {
        String fileName = formFile.getFileName();
        String originalFileName = fileName;
        String ext = "";
        if (fileName.lastIndexOf(".") > 0) {
            ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            if (ext.equals(".txt")) {
                originalFileName = fileName.substring(0, fileName.lastIndexOf("."));
            }
        }
        String currentDate = BillingCalendar.convertDateToString(new Date(), "MMddyyyy");

        return originalFileName + "-" + currentDate + "-" + tSgpImpHdNewInt + ".txt";
    }
}
