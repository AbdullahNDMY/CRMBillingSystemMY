/**
 * @(#)G_CSB_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.T_BIL_H;
import nttdm.bsys.common.bean.T_CSB_H;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.g_alt.AlertUserDto;
import nttdm.bsys.g_alt.G_ALT_P06Input;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

/**
 * Auto Offset Cash Book Against Invoice and Debit Note.
 * 
 * @author NTTDM
 */
public class G_CSB_P01 {

    /**
     * @param queryDAO
     *            QueryDAO
     * @param updateDAO
     *            UpdateDAO
     * @param uvo
     *            BillingSystemUserValueObject
     */
    public G_CSB_P01(QueryDAO queryDAO, UpdateDAO updateDAO, BillingSystemUserValueObject uvo) {

        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
        this.uvo = uvo;
    }

    /**
     * 
     */
    public G_CSB_P01() {

    }

    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;

    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO;

    /**
     * <div>BillingSystemUserValueObject</div>
     */
    private BillingSystemUserValueObject uvo;

    /** receiptNos */
    private List<String> receiptNos;

    /** idLogin */
    private String idLogin;

    /** batchId */
    private String batchId;

    /**
     * batchId
     */
    private Integer idBatch=0;

    /**
     * idBatch を取得する
     * @return idBatch
     */
    public Integer getIdBatch() {
        return idBatch;
    }

    /**
     * batchId を設定する
     * @param idBatch
     *            idBatch
     */
    public void setIdBatch(Integer idBatch) {
        this.idBatch = idBatch;
    }
    
    /**
     * Customer Type. 0:Consumer, 1:Corporate
     */
    private String customerType;

    /**
     * 
     * @return Customer Type
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Customer Type
     * @param customerType
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    
    /**
     * @return the batchId
     */
    public String getBatchId() {

        return batchId;
    }

    /**
     * @param batchId
     *            the batchId to set
     */
    public void setBatchId(String batchId) {

        this.batchId = batchId;
    }

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {

        return queryDAO;
    }

    /**
     * @param queryDAO
     *            the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {

        this.queryDAO = queryDAO;
    }

    /**
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {

        return updateDAO;
    }

    /**
     * @param updateDAO
     *            the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {

        this.updateDAO = updateDAO;
    }

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {

        return uvo;
    }

    /**
     * @param uvo
     *            the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {

        this.uvo = uvo;
    }

    /**
     * @return the receiptNos
     */
    public List<String> getReceiptNos() {

        return receiptNos;
    }

    /**
     * @param receiptNos
     *            the receiptNos to set
     */
    public void setReceiptNos(List<String> receiptNos) {

        this.receiptNos = receiptNos;
    }

    /**
     * Generate Billing Document Batch Type
     */
    private static String BATCH_TYPE = "CB";

    /** SELECT_SQL_1 */
    private static final String SELECT_SQL_1 = "SELECT.BSYS.G_CSB_P01.SQL001";

    /** SELECT_SQL_2 */
    private static final String SELECT_SQL_2 = "SELECT.BSYS.G_CSB_P01.SQL002";

    /** SELECT_SQL_3 */
    private static final String SELECT_SQL_3 = "SELECT.BSYS.G_CSB_P01.SQL003";

    /** SELECT_SQL_4 */
    private static final String SELECT_SQL_4 = "SELECT.BSYS.G_CSB_P01.SQL004";

    /** SELECT_SQL_5 */
    private static final String SELECT_SQL_5 = "SELECT.BSYS.G_CSB_P01.SQL005";

    /** SELECT_SQL_6 */
    private static final String SELECT_SQL_6 = "SELECT.BSYS.G_CSB_P01.SQL006";
    
    /** SELECT_SQL_7 */
    private static final String SELECT_SQL_7 = "SELECT.BSYS.G_CSB_P01.SQL007";

    /** UPDATE_SQL_6 */
    private static final String UPDATE_SQL_6 = "UPDATE.BSYS.G_CSB_P01.SQL006";
    
    private static final String EXPORT_ERROR_MSG = "File was not generated.";

    /**
     * execute
     */
    public void execute() {
        T_SET_BATCH t_batch = new T_SET_BATCH();
        t_batch.setSTATUS("1");
        t_batch.setBATCH_TYPE("CB");
        // Call G_SET_P01
        G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
        int batchId = gsetp01.G_SET_P01_GetIdBatch(t_batch).getBatch_id();
        this.idBatch=batchId;
        
        // Exit Process if other process still running.
        if (batchId < 0) {
            return;
        }

        // rollback if insert or update to DB failed
        idLogin = uvo.getId_user();

        // Initialize $RECEIPT_NO[]
        receiptNos = new ArrayList<String>();
        String offset = queryDAO.executeForObject(SELECT_SQL_1, null, String.class);
        String receiptNo = null;
        String idCust;
        String curCode;
        String billAcc;
        BigDecimal balanceAmt = new BigDecimal("0");

        // $Offset_by=BAC?//BAC or CST
        if ("BAC".equals(offset)) {

            // SQL 2.1 Select * from T_CSB_H
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerType", customerType);
            List<T_CSB_H> tCsbHList = queryDAO.executeForObjectList(SELECT_SQL_2, param);

            // Have record?
            if (!CollectionUtils.isEmpty(tCsbHList)) {
                // Loop result set 1
                for (T_CSB_H tCsbH : tCsbHList) {

                    // $receipt_no = A.RECEIPT_NO
                    receiptNo = tCsbH.getReceipt_no();

                    // $balance_amt = A.BALANCE_AMT
                    balanceAmt = new BigDecimal(tCsbH.getBalance_amt());

                    billAcc = tCsbH.getId_bill_account();
                    // SQL3.1 Select * from T_BIL_H
                    T_BIL_H queryTBilH = new T_BIL_H();

                    queryTBilH.setBillAcc(billAcc);

                    List<T_BIL_H> tBilHList = queryDAO.executeForObjectList(SELECT_SQL_4, queryTBilH);
                    // Have record?
                    if (!CollectionUtils.isEmpty(tBilHList)) {
                        balanceAmt = sub(tBilHList, balanceAmt, receiptNo);

                        // SQL 8 update T_CSB_H
                        HashMap<String, Object> hashMapTBilH = new HashMap<String, Object>();
                        hashMapTBilH.put("balance_amt", balanceAmt);
                        hashMapTBilH.put("receipt_no", receiptNo);
                        hashMapTBilH.put("id_login", idLogin);
                        updateDAO.execute(UPDATE_SQL_6, hashMapTBilH);
                    }
                    // Next record in result set 1?
                }
            }
            // End loop result set 1
        } else {

            // SQL2.2 Select * from T_CSB_H
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerType", customerType);
            List<T_CSB_H> tCsbHList = queryDAO.executeForObjectList(SELECT_SQL_3, param);

            // Have record?
            if (!CollectionUtils.isEmpty(tCsbHList)) {
                // Loop result set 1
                for (T_CSB_H tCsbH : tCsbHList) {
                    // $receipt_no = A.RECEIPT_NO
                    receiptNo = tCsbH.getReceipt_no();

                    // $id_cust =A.ID_CUST
                    idCust = tCsbH.getId_cust();

                    // $cur_code = A.CUR_CODE
                    curCode = tCsbH.getCur_code();

                    // $balance_amt = A.BALANCE_AMT
                    balanceAmt = new BigDecimal(tCsbH.getBalance_amt());

                    // SQL3.2 Select * from T_BIL_H
                    T_BIL_H queryTBilH = new T_BIL_H();

                    queryTBilH.setIdCust(idCust);
                    queryTBilH.setBillCurrency(curCode);

                    List<T_BIL_H> tBilHList = queryDAO.executeForObjectList(SELECT_SQL_5, queryTBilH);

                    // Have record?
                    if (!CollectionUtils.isEmpty(tBilHList)) {
                        balanceAmt = sub(tBilHList, balanceAmt, receiptNo);

                        // SQL 8 update T_CSB_H
                        HashMap<String, Object> hashMapTBilH = new HashMap<String, Object>();
                        hashMapTBilH.put("balance_amt", balanceAmt);
                        hashMapTBilH.put("receipt_no", receiptNo);
                        hashMapTBilH.put("id_login", idLogin);
                        updateDAO.execute(UPDATE_SQL_6, hashMapTBilH);
                    }
                    // Next record in result set 1?
                }
            }
            // End loop result set 1
        }
        subCall(batchId + "");
        // End
    }

    /**
     * subCall
     */
    private void subCall(String id_batch) {
        // GlobalMessageResources
        GlobalMessageResources msgResource = GlobalMessageResources.getInstance();

        // Pass parameter
        G_ALT_P06Input param = new G_ALT_P06Input();

        // $BATCH_ID = "CB"
        param.setBatchId(BATCH_TYPE);

        // $SUBJECT = ERR2SC045
        String subject = msgResource.getMessage("info.ERR2SC045", null);
        param.setSubject(subject);

        // get notification user list
        List<AlertUserDto> alertUsers = queryDAO.executeForObjectList("SELECT.BSYS.SQL053", BATCH_TYPE);
        param.setListAlertUser(alertUsers);

        G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
        T_SET_BATCH t_batch = new T_SET_BATCH();
        t_batch.setBATCH_TYPE("CB");

        // $RECEIPT_NO[] = null or empty ?
        if (CollectionUtils.isEmpty(receiptNos)) {
            // $MSG=ERR2SC047
            String message = msgResource.getMessage("info.ERR2SC047", null);
            param.setMsg(message);

            t_batch.setSTATUS("3");
            t_batch.setID_BATCH(id_batch);
            t_batch.setMessage(message);
        } else {
            List<List<String>> createInConditionList = CommonUtils.createInCondition(receiptNos);
            List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
            if(createInConditionList!=null && 0<createInConditionList.size()) {
                for(List<String> receiptNoList : createInConditionList) {
                    Map<String, Object> csbSqlParam = new HashMap<String, Object>();
                    csbSqlParam.put("receiptNos", receiptNoList);
                    List<Map<String, Object>> list = queryDAO.executeForMapList(SELECT_SQL_7, csbSqlParam);
                    if(list!=null && 0<list.size()) {
                        for (Map<String, Object> map : list) {
                            detailList.add(map);
                        }
                    }
                }
            }
            
            if (CommonUtils.MAX_COUNT < detailList.size()) {
                Comparator<Map<String,Object>> comparator = new Comparator<Map<String,Object>>(){
                    public int compare(Map<String,Object> param1, Map<String,Object> param2) {
                        String idBillAccount1 = CommonUtils.toString(param1.get("ID_BILL_ACCOUNT")).trim();
                        String idBillAccount2 = CommonUtils.toString(param2.get("ID_BILL_ACCOUNT")).trim();
                        
                        String dateTrans1 = CommonUtils.toString(param1.get("DATE_TRANS_ORDER")).trim();
                        String dateTrans2 = CommonUtils.toString(param2.get("DATE_TRANS_ORDER")).trim();
                        
                        String receiptNo1 = CommonUtils.toString(param1.get("RECEIPT_NO")).trim();
                        String receiptNo2 = CommonUtils.toString(param2.get("RECEIPT_NO")).trim();
                        
                        String receiptRef1 = CommonUtils.toString(param1.get("ID_REF")).trim();
                        String receiptRef2 = CommonUtils.toString(param2.get("ID_REF")).trim();
                        
                        if (!idBillAccount1.equals(idBillAccount2)) {
                            return idBillAccount1.compareTo(idBillAccount2);
                        } else if (!dateTrans1.equals(dateTrans2)) {
                            return dateTrans1.compareTo(dateTrans2);
                        } else if (!receiptNo1.equals(receiptNo2)) {
                            return receiptNo1.compareTo(receiptNo2);
                        } else {
                            return receiptRef1.compareTo(receiptRef2);
                        }
                    }
               };
               Collections.sort(detailList,comparator);
            }
            
            BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
            String exportPath = CommonUtils.toString(setting.getBatchGCsbP01()).trim();
            String saveMsg = "";
            if (CommonUtils.isEmpty(exportPath)) {
                saveMsg = EXPORT_ERROR_MSG;
            } else {
                File folder = new File(exportPath);
                if (!folder.exists()) {
                    saveMsg = EXPORT_ERROR_MSG;
                } else {
                    if (folder.isDirectory() && folder.canRead() && folder.canWrite()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String fileName = "AutoOffsetCB_" + sdf.format(new Date());
                        OutputStream os = null;
                        try {
                            Workbook wb = new HSSFWorkbook();
                            Sheet sheet1 = wb.createSheet("AutoOffsetCB_export");
                            writeCsvHeader(sheet1);
                            
                            writeCsvContent(detailList, sheet1);
                            
                            for(int i = 0;i<8;i++){
                                sheet1.autoSizeColumn(i);
                            }
                            os = new FileOutputStream(new File(exportPath + fileName + ".xls"));  
                            wb.write(os); 
                            
                            saveMsg = msgResource.getMessage("info.ERR2SC030", receiptNos);
                            t_batch.setFILENAME(fileName + ".xls");
                        } catch (Exception e) {
                            saveMsg = EXPORT_ERROR_MSG;
                        } finally{
                            if(os!=null){
                                try {
                                    os.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }  
                            }
                        }
                    } else {
                        saveMsg = EXPORT_ERROR_MSG;
                    }
                }
            }
            
            if (receiptNos!=null && 0<receiptNos.size()) {
                for (int i = 0; i < receiptNos.size(); i++){
                    String receiptNo = receiptNos.get(i);
                    receiptNos.set(i, CommonUtils.toString(receiptNo).trim());
                }
            }
            // $RECEIPT_NO=$RECEIPT_NO[]
            // $MSG=ERR2SC046
            String message = msgResource.getMessage("info.ERR2SC046", receiptNos);
            param.setMsg(saveMsg + " " + message);

            t_batch.setSTATUS("2");
            t_batch.setID_BATCH(id_batch);
            t_batch.setMessage(message);
        }
        // Call G_SET_P01
        gsetp01.G_SET_P01_GetIdBatch(t_batch);
        // Call G_ALT_P06
        G_ALT_P06 gAltP06 = new G_ALT_P06(queryDAO, updateDAO);

        gAltP06.execute(param, uvo);

    }

    /**
     * @param tBilHList
     *            List<T_BIL_H>
     * @param balanceAmt
     *            float
     * @param receiptNo
     *            String
     * @return balanceAmt float
     */
    private BigDecimal sub(List<T_BIL_H> tBilHList, BigDecimal balanceAmt, String receiptNo) {
        G_CSB_P04 g_csb_p04 = new G_CSB_P04(updateDAO);
        BigDecimal zero = new BigDecimal("0");
        // Loop result set 2
        for (T_BIL_H tBilH : tBilHList) {

            // $amt_due = B.BILL_AMOUNT -B.PAID_AMOUNT
            BigDecimal amtDue = new BigDecimal(tBilH.getBillAmount()).subtract(new BigDecimal(tBilH.getPaidAmount()));
            // ID_REF
            String idRef = CommonUtils.toString(tBilH.getIdRef());
            // T_CSB_D condition
            Map<String, Object> tCsbDCondition = new HashMap<String, Object>();
            // ID_REF
            tCsbDCondition.put("idRef", idRef);
            // RECEIPT_NO
            tCsbDCondition.put("receiptNo", receiptNo);
            // get T_CSB_D Info by Primary
            Map<String, Object> mapTCsbDInfo = this.queryDAO.executeForMap(
                    SELECT_SQL_6, tCsbDCondition);
            // ID_REF
            tCsbDCondition.put("idRef", idRef.trim());
            // RECEIPT_NO
            tCsbDCondition.put("receiptNo", receiptNo.trim());
            //Call G_CSB_P04
            balanceAmt = g_csb_p04.execute(mapTCsbDInfo, tCsbDCondition, balanceAmt, amtDue, idLogin);
            
            // Add $receipt_no to RECEIPT_NO[]
            if (!receiptNos.contains(receiptNo)) {
                receiptNos.add(receiptNo);
            }
            
            // $balance_amt>0 #QA37
            if (balanceAmt.compareTo(zero) <= 0) {
                balanceAmt = zero;
                return balanceAmt;
            }
            // End loop result set 2
        }
        return balanceAmt;
    }
    
    /**
     * write Csv Header.
     * 
     * @param sheet Excel
     */
    private void writeCsvHeader(Sheet sheet) {
        String value = "";
        Row row = sheet.createRow(0); 
       
        Cell cell1 = row.createCell(0); 
        cell1.setCellValue(reportHeader("reports.GCSBP01.billAcc", value));

        Cell cell2 = row.createCell(1);
        cell2.setCellValue(reportHeader("reports.GCSBP01.custId", value));

        Cell cell3 = row.createCell(2);
        cell3.setCellValue(reportHeader("reports.GCSBP01.custName", value));

        Cell cell4 = row.createCell(3);
        cell4.setCellValue(reportHeader("reports.GCSBP01.custType", value));
        
        Cell cell5 = row.createCell(4);
        cell5.setCellValue(reportHeader("reports.GCSBP01.receiptNo", value));
        
        Cell cell6 = row.createCell(5);
        cell6.setCellValue(reportHeader("reports.GCSBP01.dateTransaction", value));

        Cell cell7 = row.createCell(6);
        cell7.setCellValue(reportHeader("reports.GCSBP01.AmountReceived", value));

        Cell cell8 = row.createCell(7);
        cell8.setCellValue(reportHeader("reports.GCSBP01.reference1", value));
        
        Cell cell9 = row.createCell(8);
        cell9.setCellValue(reportHeader("reports.GCSBP01.debitInformation", value));

        Cell cell10 = row.createCell(9);
        cell10.setCellValue(reportHeader("reports.GCSBP01.currency", value));
        
        Cell cell11 = row.createCell(10);
        cell11.setCellValue(reportHeader("reports.GCSBP01.origAmt", value));
        
        Cell cell12 = row.createCell(11);
        cell12.setCellValue(reportHeader("reports.GCSBP01.amountDue", value));
        
        Cell cell13 = row.createCell(12);
        cell13.setCellValue(reportHeader("reports.GCSBP01.amountPaid", value));
    }
    
    /**
     * Write Cash Book detail list data into Excel file.
     * 
     * @param detailList cash book detail list
     * @param sheet excel
     * @throws IOException
     */
    private void writeCsvContent(List<Map<String, Object>> detailList, Sheet sheet) throws IOException {
        Row row = null; 
        for (int i = 0; i < detailList.size(); i++){
            row = sheet.createRow(i+1); 
            Map<String, Object> data = detailList.get(i);
            
            Cell cell1 = row.createCell(0); 
            cell1.setCellValue(CommonUtils.toString(data.get("ID_BILL_ACCOUNT")).trim());

            Cell cell2 = row.createCell(1); 
            cell2.setCellValue(CommonUtils.toString(data.get("ID_CUST")).trim());
            
            Cell cell3 = row.createCell(2); 
            cell3.setCellValue(CommonUtils.toString(data.get("CUST_NAME")).trim());
            
            String custType = CommonUtils.toString(data.get("CUSTOMER_TYPE")).trim();
            Cell cell4 = row.createCell(3); 
            cell4.setCellValue(CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE",custType));
            
            Cell cell5 = row.createCell(4); 
            cell5.setCellValue(CommonUtils.toString(data.get("RECEIPT_NO")).trim());
            
            Cell cell6 = row.createCell(5); 
            cell6.setCellValue(CommonUtils.toString(data.get("DATE_TRANS")).trim());
            
            Cell cell7 = row.createCell(6); 
            cell7.setCellValue(CommonUtils.toString(data.get("AMT_RECEIVED")).trim());
            
            Cell cell8 = row.createCell(7); 
            cell8.setCellValue(CommonUtils.toString(data.get("REFERENCE1")).trim());
            
            Cell cell9 = row.createCell(8); 
            cell9.setCellValue(CommonUtils.toString(data.get("ID_REF")).trim());
            
            Cell cell10 = row.createCell(9); 
            cell10.setCellValue(CommonUtils.toString(data.get("BILL_CURRENCY")).trim());
            
            Cell cell11 = row.createCell(10); 
            cell11.setCellValue(CommonUtils.toString(data.get("BILL_AMOUNT")).trim());
            
            Cell cell12 = row.createCell(11); 
            cell12.setCellValue(CommonUtils.toString(data.get("AMT_DUE")).trim());
            
            Cell cell13 = row.createCell(12); 
            cell13.setCellValue(CommonUtils.toString(data.get("AMT_PAID")).trim());
        }
    }
    
    /**
     * Read message from properties file by Key.
     * 
     * @param key message key
     * @param args message parameter
     * @return
     */
    private String reportHeader(String key, Object args) {
        return MessageUtil.get(key, args);
    }
}
