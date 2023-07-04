/**
 * @(#)G_SGP_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.e_mex.blogic.RP_E_MEX_SP1BLogic;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitOutput;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.springframework.context.ApplicationContext;

/**
 * Process to generate and export SingPost & update Collection Data
 */
public class G_SGP_P01
        extends
        AbstractGlobalProcess<RP_E_MEX_SP1SubmitInput, 
        RP_E_MEX_SP1SubmitOutput> {

    /** rec_type **/
    public static final String REC_TYPE_HEADER = "1";
    /** rec_type **/
    public static final String REC_TYPE_DETAIL = "2";
    /** rec_type **/
    public static final String REC_TYPE_FOOTER = "3";

    /** Success **/
    private static final String STATUS_SUCCESS = "0";
    /** Failed **/
    public static final String STATUS_FAILED = "1";
    /** In Process **/
    public static final String STATUS_IN_PROGRESS = "2";

    /** Prompt error message **/
    private BLogicMessages errors = new BLogicMessages();
    /** Prompt message **/
    private BLogicMessages messages = new BLogicMessages();

    /** export path **/
    private String exportPath;
    /** parameter **/
    private Map<String, Object> parameter;

    /** file name **/
    private String filename;
    /** $dateReq month **/
    private String MM;
    /** $dateReq year **/
    private String yyyy;
    /** GIRO Deduction Day **/
    private int deductionDay;
    /** globe setting **/
    private BillingSystemSettings setting;
    /** return **/
    private GlobalProcessResult result = null;
    /** Display message on screen **/
    private boolean SCR = false;

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
    
    /**
     * @return the exportPath
     */
    public String getExportPath() {
        return exportPath;
    }

    /**
     * @param exportPath the exportPath to set
     */
    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
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
     * init G_SGP_P01
     */
    public G_SGP_P01() {
    }

    /**
     * main execute
     * 
     * @param param
     *            RP_E_MEX_SP1SubmitInput
     * @param outputDTO
     *            RP_E_MEX_SP1SubmitOutput
     * @return GlobalProcessResult
     */
    public GlobalProcessResult execute(RP_E_MEX_SP1SubmitInput param,
            RP_E_MEX_SP1SubmitOutput outputDTO) {
        this.errors.clear();
        this.messages.clear();
        this.SCR = param.isScr();

        this.result = new GlobalProcessResult();
        this.parameter = new HashMap<String, Object>();

        int closingMonth = CommonUtils.toInteger(param.getClosingMonth());
        int closingYear = CommonUtils.toInteger(param.getClosingYear());
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MONTH, closingMonth - 1);
        now.set(Calendar.YEAR, closingYear);
        now.set(Calendar.DATE, 1);
        this.yyyy = CommonUtils.formatDate(now.getTime(), "yyyy");
        this.MM = CommonUtils.formatDate(now.getTime(), "MM");
        int maxDay = now.getActualMaximum(Calendar.DAY_OF_MONTH);
        this.deductionDay = CommonUtils.toInteger(param.getDeductionDate());
        if (this.deductionDay > maxDay) {
            this.deductionDay = maxDay;
        }
        this.filename = "INVOICE_" + MM + yyyy + ".txt";

        // 0.0 get M_GSET_D.BATCH_G_SGP_P01
        this.setting = new BillingSystemSettings(queryDAO);
        this.exportPath = CommonUtils.toString(setting.getBatchGSgpP01());
        if (!this.exportPath.endsWith("/")) {
            this.exportPath = this.exportPath + "/";
        }
        parameter.put("filename", filename);
        parameter.put("closeMonthyear", MM + "/" + yyyy);
        parameter.put("dateReq", MM + "/" + yyyy);
        parameter.put("idLogin", param.getUvo().getId_user());
        parameter.put("idBatchType", RP_E_MEX_SP1BLogic.ID_BATCH_TYPE);
        parameter.put("idSet", "BATCH_G_SGP_P01");
        parameter.put("setSeq", 1);
        parameter.put("idBatchRefNo", 0);
        // is in process
        boolean isContinue = true;

        // Audit Trail
        
        //TODO
        if (auditIdModule != null) {
            this.auditId = CommonUtils.auditTrailBegin(param.getUvo().getId_user(),
                this.auditIdModule, this.auditIdSubModule, this.auditReference,
                this.auditStatus, BillingSystemConstants.AUDIT_TRAIL_CREATED);
            if(CommonUtils.isEmpty(this.auditId)){
                parameter.put("filename", "NOT AVAILABLE");
                parameter.put("status", STATUS_FAILED);
                int idSgpExpBatch = this.updateDAONuked.insert("G_SGP_P01_5.0",
                    parameter);
                parameter.put("idSgpExpBatch", idSgpExpBatch);
                parameter.put("idBatchRefNo", idSgpExpBatch);
                
                this.addError("errors.ERR1SC074");
                this.recordLog("errors.ERR1SC074");
                //TODO
                AuditTrailHeaderBean auditHeader=new AuditTrailHeaderBean();
                auditHeader.setIdAudit((Integer) parameter.get("auditId"));
                auditHeader.setReference( parameter.get("idSgpExpBatch")+":"+parameter.get("closeMonthyear"));
                String status=(parameter.get("status").toString().equals("0"))?"Successful":"Failed";
                auditHeader.setStatus(status);
                CommonUtils.auditTrailUpdate(auditHeader);
                
                
                result.setErrors(errors);
                result.setMessages(messages);
                return result;
            }
        }
        parameter.put("auditId", this.auditId);
        parameter.put("RUN_STATUS", "Y");
        updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter);
        
        // 1.0 check latest status SQL: RetrieveProcessStatus
        List<HashMap<String, Object>> latestBatchList = this.queryDAO.executeForObjectList(
            "G_SGP_P01_1.0", parameter);
        HashMap<String, Object> latestBatch = null;
//        if (latestBatch != null) {
//            // 4.0 record log for the previous process
//            parameter.put("idBatchRefNo", latestBatch.get("ID_SGP_EXP_BATCH"));
//        }
        if (latestBatchList != null && latestBatchList.size() > 0) {
            for(int r = 0; r < latestBatchList.size(); r++){
                latestBatch = latestBatchList.get(r);
                parameter.put("idBatchRefNo", latestBatch.get("ID_SGP_EXP_BATCH"));
                // 2.0 check if "In Progress" has been exceed BATCH_TIME_INTERVAL
                if (CommonUtils.isTimeInterval(
                    latestBatch.get("DATE_UPDATED_CHAR").toString(), 
                    setting.getBatchTimeInterval() * 1000)) {
                    // 3.0 update the previous process STATUS = failed
                    parameter.put("status", STATUS_FAILED);
                    parameter.put("idSgpExpBatch",
                        latestBatch.get("ID_SGP_EXP_BATCH"));
                    // G_SGP_P01_3.0 SQL: UpdatePrevStatus
                    this.updateDAO.execute("G_SGP_P01_3.0", parameter);
                    this.recordLog("errors.ERR1BT016");
                } else {
                    parameter.put("filename", "NOT AVAILABLE");
                    parameter.put("status", STATUS_FAILED);
                    int idSgpExpBatch = this.updateDAONuked.insert("G_SGP_P01_5.0",
                        parameter);
                    parameter.put("idSgpExpBatch", idSgpExpBatch);
                    parameter.put("idBatchRefNo", idSgpExpBatch);
                    // 2.1 prompt error message
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                        "errors.ERR1SC059"));
                    isContinue = false;
                    this.recordLog("errors.ERR1BT020",setting.getBatchTimeInterval()/60);
                    break;
                }
            }
        }

        if (isContinue) {
            // 5.0 create batch header record
            this.createBatchHeader(param, outputDTO);
        }
        Map<String, Object> lastBatch = queryDAO.executeForMap("G_SGP_P01_lastBatch", parameter);
        String lastBatchId = lastBatch.get("ID_SGP_EXP_BATCH").toString();
        String currentId = CommonUtils.toString(parameter.get("idSgpExpBatch"));
        if(lastBatchId.equals(currentId)||currentId.equals("")){
            parameter.put("RUN_STATUS", "N");
            updateDAO.execute("UPDATE.G.T_BATCH_SCH.RUN_STATUS", parameter); 
        }
        
        //TODO
        AuditTrailHeaderBean auditHeader=new AuditTrailHeaderBean();
        auditHeader.setIdAudit((Integer) parameter.get("auditId"));
        auditHeader.setReference( parameter.get("idSgpExpBatch")+":"+parameter.get("closeMonthyear"));
        String status=(parameter.get("status").toString().equals("0"))?"Successful":"Failed";
        auditHeader.setStatus(status);
        CommonUtils.auditTrailUpdate(auditHeader);
       
        
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }

    /**
     * fix length of fields as design document
     * 
     * @param param
     *            RP_E_MEX_SP1SubmitInput
     * @param outputDTO
     *            RP_E_MEX_SP1SubmitOutput
     */
    private void createBatchHeader(RP_E_MEX_SP1SubmitInput param,
            RP_E_MEX_SP1SubmitOutput outputDTO) {

        boolean isStep2Continue = false;

        // 6.0 check export path is accessible
        File folder = new File(exportPath);
        if (!exportPath.equals("/") && folder.isDirectory() && folder.canRead() && folder.canWrite()) {
            // 7.0 check the file name INVOICE_mmyyyy.txt is existing
            String exitsIdSgpExpBatch = queryDAO.executeForObject(
                    "G_SGP_P01_7.0", filename, String.class);
            exitsIdSgpExpBatch = CommonUtils.toString(exitsIdSgpExpBatch);
            File file = new File(exportPath + filename);
            
            boolean insertFlag = false ;
            if(CommonUtils.isEmpty(exitsIdSgpExpBatch)){
                insertFlag = true ;
            }else{
                if (file.exists()) {
                    parameter.put("idSgpExpBatch", exitsIdSgpExpBatch);
                    String newFilename = "INVOICE_" + MM + yyyy + 
                            "_" + exitsIdSgpExpBatch + ".txt";
                    parameter.put("filename", newFilename);
                    // SQL: UpdateFilename
                    this.updateDAO.execute("G_SGP_P01_10.0", parameter);
                    // 9.0 Rename the existing file name as
                    // INVOICE_mmyyyy_<ID_SGP_EXP_BATCH>.txt
                    file.renameTo(new File(exportPath + newFilename));
                    parameter.put("filename", filename);
                    parameter.put("idBatchRefNo", exitsIdSgpExpBatch);
                    insertFlag = true ;
                }
            }
            if (insertFlag) {
                // 5.0 create batch header record
                parameter.put("status", STATUS_IN_PROGRESS);
                int idSgpExpBatch = this.updateDAONuked.insert("G_SGP_P01_5.0",
                    parameter);
                parameter.put("idSgpExpBatch", idSgpExpBatch);
                parameter.put("idBatchRefNo", idSgpExpBatch);
            }
            BsysBufferedWriter out = createFile();
            if (out != null) {
                isStep2Continue = this.exportFile(
                    param.getUvo().getId_user(), out);
            }
        } else {
            parameter.put("filename", "NOT AVAILABLE");
            parameter.put("status", STATUS_FAILED);
            int idSgpExpBatch = this.updateDAONuked.insert("G_SGP_P01_5.0",
                parameter);
            parameter.put("idSgpExpBatch", idSgpExpBatch);
            parameter.put("idBatchRefNo", idSgpExpBatch);
            
            // 7.0 prompt error message ERR1SC056
            this.addError("errors.ERR1SC056", new Object[] { exportPath });
            // 7.2 record error log ERR1BT012
            this.recordLog("errors.ERR1BT012", new Object[] { exportPath });
            isStep2Continue = false;
        }
        if (!isStep2Continue) {
            // 7.1 update T_SGP_EXP_HD status = FAILED
            parameter.put("status", STATUS_FAILED);
            this.updateDAO.execute("G_SGP_P01_3.0", parameter);
        }
    }

    /**
     * create new export file as INVOICE_mmyyyy.txt in storage
     * @return BsysBufferedWriter
     */
    private BsysBufferedWriter createFile() {
        // 11.0 create new export file as INVOICE_mmyyyy.txt in storage
        BsysBufferedWriter out = null;
        try {
            out = new BsysBufferedWriter(new FileWriter(exportPath + filename));
        } catch (Exception ioe) {
            // 7.0 prompt error message ERR1SC058
            this.addError("errors.ERR1SC058", new Object[] { filename });
            // 7.2 record error log ERR1BT014
            this.recordLog("errors.ERR1BT014", new Object[] { filename });
        }
        return out;
    }
    
    /**
     * export File
     * 
     * @param userId
     *            user id
     * @param out BsysBufferedWriter
     * @return boolean
     */
    private boolean exportFile(String userId, BsysBufferedWriter out) {
        boolean isStep2Continue = true;

        try {
            int totalAccRow = 0;
            int totalRow = 0;
            // 12.0 search invoice for the selected month from T_BIL_H where
            // IS_EXPORT = false SQL : RetrieveInvoices
            List<Map<String, Object>> invoices = 
                this.queryDAO.executeForMapList("G_SGP_P01_13.0", parameter);
            if (CommonUtils.isEmpty(invoices)) {
                // 7.0 prompt error message ERR1SC052
                this.addError("errors.ERR1SC052");
                // 7.2 record error log ERR1BT011
                this.recordLog("errors.ERR1BT011", new Object[] { MM + "/"
                    + yyyy });
                isStep2Continue = false;
                return isStep2Continue;
            } else {
                // 13.0 Retrieve invoice header
                ApplicationContext context = ApplicationContextProvider.getApplicationContext();
                MappedCodeListLoader rateModeCodeList = (MappedCodeListLoader) 
                context.getBean("LIST_RATEMODE");
                @SuppressWarnings("unchecked")
                Map<String, Object> rateModeMap = rateModeCodeList.getCodeListMap();
                for (int j = 0; j < invoices.size(); j++) {
                    Map<String, Object> invoice = invoices.get(j);
                    parameter.put("idCust",
                        String.valueOf(invoice.get("ID_CUST")));
                    parameter.put("idRefS",
                        String.valueOf(invoice.get("ID_REF")).trim());
                    parameter.put("idRef",
                            String.valueOf(invoice.get("ID_REF")));
                    // 14.1 retrieve customer detail from M_CUST
                    // SQL : RetrieveCustDtl
                    Map<String, Object> cust = this.queryDAO.executeForMap(
                        "G_SGP_P01_14.1", parameter);
                    if (cust == null) {
                        continue;
                    }
                    totalAccRow++;
                    totalRow++;
                    if (1 < totalAccRow) {
                        out.newLine();
                    }

                    // 13.0 Retrieve invoice header
                    this.retrieveInvoiceHeader(out, invoice, cust);

                    int detailrow = this.detail(out, invoice,rateModeMap);
                    totalRow = totalRow + detailrow;

                    // 17.0 update invoice table
                    isStep2Continue = updateInvoice();
                    if (!isStep2Continue) {
                        return isStep2Continue;
                    }
                }
                // 18.0 write footer into export file
                out.newLine();
                // rec_type
                out.print(REC_TYPE_FOOTER, 1);
                // total_no_of_acc_row
                out.print(totalAccRow, 10);
                // total_no_row
                out.print(totalRow, 10);
                out.flush();

                if (this.SCR) {
                    // 19.0 prompt success message
                    this.messages.add(ActionMessages.GLOBAL_MESSAGE,
                        new BLogicMessage("info.ERR2SC029"));
                }
                // 20.0 update successful status
                parameter.put("status", STATUS_SUCCESS);
                this.updateDAO.execute("G_SGP_P01_3.0", parameter);
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
            // 7.0 prompt error message ERR1SC065
            this.addError("errors.ERR1SC065", new Object[] { filename });
            // 7.2 record error log ERR1BT014
            this.recordLog("errors.ERR1BT014", new Object[] { filename });
            isStep2Continue = false;
        } finally {
            // End Audit Trail
            if (this.auditIdModule != null) {
                CommonUtils.auditTrailEnd(this.auditId);
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // 7.0 prompt error message ERR1SC058
                    this.addError("errors.ERR1SC058", 
                        new Object[] { filename });
                    // 7.2 record error log ERR1BT014
                    this.recordLog("errors.ERR1BT014",
                        new Object[] { filename });
                    isStep2Continue = false;
                }
            }
        }
        return isStep2Continue;
    }

    /**
     * update invoice table
     * @return successfully
     */
    private boolean updateInvoice() {
        boolean isStep2Continue = true;
        // 17.0 update invoice table
        try {
            // SQL : updateIsExport
            this.updateDAO.execute("G_SGP_P01_17.0", parameter);
        } catch (Exception ioe) {
            // 7.0 prompt error message ERR1SC060
            this.addError("errors.ERR1SC060",
                new Object[] { "T_BIL_H" });
            // 7.2 record error log ERR1BT017
            this.recordLog("errors.ERR1BT017",
                new Object[] { "T_BIL_H" });
            isStep2Continue = false;
        }
        return isStep2Continue;
    }
    /**
     * detail write 
     * @param out BsysBufferedWriter 
     * @param invoice Map<String, Object>
     * @return row number
     * @throws IOException error
     */
    private int detail(BsysBufferedWriter out, Map<String, Object> invoice,Map<String, Object> rateModeMap)
            throws IOException {
        // 14.1 retrieve invoice detail from T_BILL_D
        // SQL : RetrieveInvDtl
        List<Map<String, Object>> invoiceDetails = 
            this.queryDAO.executeForMapList("G_SGP_P01_15.0", parameter);
        int totalRow = 0;
        String serviceName = "";
        for (int i = 0; i < invoiceDetails.size(); i++) {
            Map<String, Object> invoiceDetail = invoiceDetails.get(i);
            if ("0".equalsIgnoreCase(CommonUtils.toString(
                    invoiceDetail.get("ITEM_LEVEL")))) {
                serviceName = CommonUtils.toString(invoiceDetail.get("ITEM_DESC2")).trim();
                serviceName = reomveEnter(serviceName);
                continue;
            }
            // T_BIL_D.ITEM_TYPE
            String itemType = CommonUtils.toString(invoiceDetail.get("ITEM_TYPE"));
            
            totalRow++;
            // 16.0 write invoice detail into export file
            out.newLine();
            // rec_type
            out.print(REC_TYPE_DETAIL, 1);
            // detail_rec_no
            out.print(totalRow, 3);
            // invoice_no
            out.print(invoice.get("ID_REF"), 18);
            // category_name
            out.print(invoiceDetail.get("SVC_GRP_NAME"), 50);

            // service_name
            if ("S".equalsIgnoreCase(itemType)) {
                out.print(serviceName, 128);
            } else {
                out.print(" ", 128);
            }

            // plan_name
            if ("S".equalsIgnoreCase(itemType)) {
                out.print(reomveEnter(CommonUtils.toString(invoiceDetail.get("ITEM_DESC2"))), 128);
            } else {
                out.print(" ", 128);                
            }

            // service_subscribe
            String idCustPlan = CommonUtils.toString(invoiceDetail.get("ID_CUST_PLAN"));
            out.print(CommonUtils.toString(invoiceDetail.get("ID_SUB_INFO")), 7);
            
            StringBuffer cost_desc = new StringBuffer();
            // Rate Mode
            String xratemode = CommonUtils.toString(invoiceDetail.get("RATE_MODE"));
            if ("6".equalsIgnoreCase(xratemode)) {
                cost_desc.append("One Time Charge");
            } else {
                //BILL_INSTRUCT
                String billInstruct = this.queryDAO.executeForObject(
                        "G_SGP_P01_11.0", idCustPlan.trim(), String.class);
                String billInstructName = "";
                billInstruct = CommonUtils.toString(billInstruct).trim();
                if (!CommonUtils.isEmpty(billInstruct)) {
                    if (rateModeMap.containsKey(billInstruct)) {
                        billInstructName = rateModeMap.get(billInstruct).toString();
                    }
                }
                // Charge Period
                cost_desc.append(billInstructName);
                cost_desc.append(" Charge From ");
                cost_desc.append(invoiceDetail.get("BILL_FROM"));
                cost_desc.append(" To ");
                cost_desc.append(invoiceDetail.get("BILL_TO"));
            }
            // cost_desc
            if ("S".equalsIgnoreCase(itemType)) {
                out.print(cost_desc.toString(), 50);
            } else {
                out.print(reomveEnter(CommonUtils.toString(invoiceDetail.get("ITEM_DESC2"))), 50);
            }
            // option_cost_desc
            if ("S".equalsIgnoreCase(itemType)) {
                out.print(" ", 128);
            } else {
                out.print(cost_desc.toString(), 128);
            }
            // #155 Start
            /*// unitprice
            out.print(invoiceDetail.get("ITEM_UPRICE"), 11);
            // qty
            out.print(invoiceDetail.get("ITEM_QTY"), 7);
            // amount
            out.print(invoiceDetail.get("ITEM_AMT"), 10);*/
            
            // unitprice
            if("0".equalsIgnoreCase(CommonUtils.toString(invoiceDetail.get("ITEM_DISC_AMT")))){
            	out.print(invoiceDetail.get("ITEM_UPRICE"), 11);
            }else{
            	out.print(invoiceDetail.get("ITEM_SUBTOTAL"), 11);
            }
            // qty
            if("0".equalsIgnoreCase(CommonUtils.toString(invoiceDetail.get("ITEM_DISC_AMT")))){
            	out.print(invoiceDetail.get("ITEM_QTY"), 7);
            }else{
            	out.print("1", 7);
            }
            // amount
            out.print(invoiceDetail.get("ITEM_SUBTOTAL"), 10);
            /*if("0".equalsIgnoreCase(CommonUtils.toString(invoiceDetail.get("ITEM_DISC_AMT")))){
            	out.print(invoiceDetail.get("ITEM_AMT"), 10);
            }else{
            	out.print(invoiceDetail.get("ITEM_SUBTOTAL"), 10);
            }*/
            // #155 End
        }
        return totalRow;
    }

    /**
     * Retrieve Invoice Header
     * 
     * @param out
     *            BsysBufferedWriter
     * @param invoice
     *            Map<String, Object>
     * @param cust
     *            Map<String, Object>
     * @throws IOException error
     */
    private void retrieveInvoiceHeader(BsysBufferedWriter out,
            Map<String, Object> invoice, Map<String, Object> cust)
            throws IOException {
        // 14.0 generate invoice header
        // rec_type
        out.print(REC_TYPE_HEADER, 1);
        // account-no
        out.print(invoice.get("BILL_ACC"), 10);
        // Other
        String payMethod = "0";
        if ("GR".equals(StringUtils.trim(
            String.valueOf(invoice.get("PAY_METHOD"))))) {
            payMethod = "1";
        } else if ("CC".equals(StringUtils.trim(
            String.valueOf(invoice.get("PAY_METHOD"))))) {
            // Card
            payMethod = "2";
        }
        // pay_method
        out.print(payMethod, 1);
        // customer_id
        out.print(invoice.get("ID_CUST"), 10);
        // identity_code
        String customer_id = cust.get("CUSTOMER_TYPE").toString();
        if ("CORP".equals(customer_id)) {
            out.print(cust.get("CO_REGNO"), 15);
        } else {
            out.print(cust.get("CUST_ID_NO"), 15);
        }
        // bill_salutation
        out.print(CommonUtils.getCodeMapListNameByValue("LIST_SALUTATION",CommonUtils.toString(invoice.get("SALUTATION"))), 5);
        // bill_Name
        out.print(invoice.get("CUST_NAME"), 60);
        // contact_salutation
        out.print(" ", 5);
        // contact_person
        if ("CORP".equals(customer_id)) {
            out.print(invoice.get("CONTACT_NAME"), 30);
        } else {
            out.print(" ", 30);
        }
        // bill_address1
        out.print(invoice.get("ADDRESS1"), 50);
        // bill_address2
        out.print(invoice.get("ADDRESS2"), 50);
        // bill_address3
        out.print(invoice.get("ADDRESS3"), 50);
        // country_name
        out.print(invoice.get("COUNTRY"), 30);
        // bill_postal
        out.print(invoice.get("ZIP_CODE"), 15);
        // invoice_no
        out.print(invoice.get("ID_REF"), 18);
        // issue_date
        out.print(invoice.get("DATE_REQ_DDMMYYYY"), 10);
        // 150
        // due_date
        /*Calendar dueDate = Calendar.getInstance();
        Date dateReq = CommonUtils.toDate(invoice.get("DATE_REQ"), "yy-MM-dd");
        dueDate.setTime(dateReq);
        dueDate.add(Calendar.DAY_OF_MONTH, setting.getInvoiceDuePeriod());*/
        out.print(CommonUtils.formatDate(invoice.get("DUE_DATE"), "dd/MM/yyyy"), 10);
        // regrading
        out.print(" ", 64);
        // customer_type
        if ("CORP".equals(customer_id)) {
            out.print("C", 1);
        } else {
            out.print("H", 1);
        }
        // 14.3 retrieve previous invoice detail from T_BILL_H
        Map<String, Object> lastInvoice = this.queryDAO.executeForMap(
            "retrieveFromTBILS", parameter);
        if (lastInvoice != null) {
            // 14.4 select retrieved data as previous invoice data
            // 14.5 write invoice header in export file
            // previous_balance
            out.print(lastInvoice.get("PREVIOUS_AMT"), 10);
            String lastPayDate = CommonUtils.toString(
                    lastInvoice.get("LAST_PAY_DATE"));
            String lastPayType = CommonUtils.toString(
                    lastInvoice.get("LAST_PAY_TYPE"));
            String lastPayAmount = CommonUtils.toString(
                    lastInvoice.get("LAST_PAY_AMT"));
            if(CommonUtils.isEmpty(lastPayDate)){
                lastPayDate = "-";
            }
            if(CommonUtils.isEmpty(lastPayType)){
                lastPayType = "-";
            }
            if(CommonUtils.isEmpty(lastPayAmount)){
                lastPayAmount = "-";
            }
            // last_pay_date
            out.print(lastPayDate, 10);
            // last_pay_type
            out.print(lastPayType, 30);
            // last_pay_amount
            out.print(lastPayAmount, 10);
            // reject_date
            out.print(lastInvoice.get("REJECT_DATE"), 10);
            // reject_pay_type
            out.print(lastInvoice.get("REJECT_PAY_TYPE"), 30);
            // reject_desc
            out.print(lastInvoice.get("REJECT_DESC"), 50);
            // reject_amount
            out.print(lastInvoice.get("REJECT_PAY_AMT"), 10);
            // outstanding_balance
            out.print(lastInvoice.get("TOTAL_OUTSTANDING"), 10);
        } else {
            out.print(" ", 170);
        }
        // sub-total
        BigDecimal bill = new BigDecimal(
            String.valueOf(invoice.get("BILL_AMOUNT")));   
        BigDecimal gst = new BigDecimal(
            String.valueOf(invoice.get("GST_AMOUNT")));   
        out.print(bill.subtract(gst).toString(), 10);
        // GST
        out.print(invoice.get("GST_AMOUNT"), 10);
        // total_current_amount
        out.print(invoice.get("BILL_AMOUNT"), 10);
        if (lastInvoice != null) {
            // total_amount_due
            out.print(lastInvoice.get("TOTAL_AMT_DUE"), 10);
        } else {
            out.print(" ", 10);
        }
        // giro_msg
        if ("CC".equals(StringUtils.trim(
            String.valueOf(invoice.get("PAY_METHOD"))))) {
            out.print(setting.getBatchMsg3(), 150);
        } else if ("GR".equals(StringUtils.trim(
            String.valueOf(invoice.get("PAY_METHOD"))))) {
            String giroDeductionDay = "";
            if (this.deductionDay < 10) {
                giroDeductionDay = "0" + this.deductionDay;
            }
            else {
                giroDeductionDay = "" + this.deductionDay;
            }            
            StringBuffer str = new StringBuffer();
            str.append(setting.getBatchMsg2());
            str.append(" ");
            str.append(this.yyyy);
            str.append("/");
            str.append(this.MM);
            str.append("/");
            str.append(giroDeductionDay);
            str.append(".");
            out.print(str.toString(), 150);
        } else {
            out.print(setting.getBatchMsg1(), 150);
        }
        // other_msg1
        out.print(" ", 150);
        // other_msg2
        out.print(" ", 150);
        // other_msg3
        out.print(" ", 150);
    }

    /**
     * add Error
     * 
     * @param msgID
     *            message id
     * @param args
     *            Parameter
     */
    private void addError(String msgID, Object... args) {
        if (this.SCR) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID,
                args));
        }
    }
    
    /**
     * remove /r/n in string
     * @param str
     * @return 
     */
    private String reomveEnter(String str){
        if(CommonUtils.isEmpty(str)){
            return "";
        }
        String retStr = str.replaceAll("(\r\n|\n\r|\r|\n)", " ");
        return retStr;
    }
    
    /**
     * Record Log
     * 
     * @param msgID
     *            message id
     * @param args
     *            Parameter
     */
    private void recordLog(String msgID, Object... args) {
        parameter.put("errorMsg", MessageUtil.get(msgID, args));
        // G_SGP_P01_4.0 SQL: InsertErrorLog1
        this.updateDAO.execute("G_SGP_P01_4.0", parameter);
    }
}