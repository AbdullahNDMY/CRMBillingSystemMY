/**
 * @(#)G_ALT_P04.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.g_alt.AlertUserDto;
import nttdm.bsys.g_alt.G_ALT_P06Input;

/**
 * Generate Credit Card Expiration Alert.
 * 
 * @author NTTDM
 */
public class G_ALT_P04 {
    private static final String SELECT_ALERT = "SELECT.BSYS.SQL036";
    private static final String SELECT_BANK_INFO = "SELECT.BSYS.SQL037_01";
    private static final String SELECT_BANK_INFO_BEFORE_TODAY = "SELECT.BSYS.SQL037_02";
    
    /**
     * Notification folder name
     */
    private static final String STR_NOTIFICATION = "Notification";
    
    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;
    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO;

    /**
     * <div>msgResource</div>
     */
    private GlobalMessageResources msgResource;

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
     * <div>Constructor</div>
     * 
     * @param queryDAO
     *            QueryDAO
     * @param updateDAO
     *            UpdateDAO
     */
    public G_ALT_P04(QueryDAO queryDAO, UpdateDAO updateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;

        // init resource
        this.msgResource = GlobalMessageResources.getInstance();
    }

    /**
     * <div>execute</div>
     * 
     * @param uvo
     *            instance of BillingSystemUserValueObject
     * @return object null
     */
    public Object execute(BillingSystemUserValueObject uvo) {

        T_SET_BATCH t_batch = new T_SET_BATCH();
        t_batch.setSTATUS("1");
        t_batch.setBATCH_TYPE("CC");
        // Call G_SET_P01
        G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
        int batchId = gsetp01.G_SET_P01_GetIdBatch(t_batch).getBatch_id();
        this.idBatch=batchId;
        
        if (batchId > 0) {
            // get alert-user list
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("BATCH_ID", "CC");
            List<Map<String, Object>> alertList = this.queryDAO.executeForMapList(SELECT_ALERT, params);
            // check have any alert user
            if (alertList.size() > 0) {

                String noOfMonth = CommonUtils.toString(alertList.get(0).get("NO_OF_MONTH"));
                if (CommonUtils.isEmpty(noOfMonth)) {
                    noOfMonth = "0";
                }

                List<Map<String, Object>> planList = new ArrayList<Map<String,Object>>();
                if ("0".equals(noOfMonth)) {
                    // check if there is customer whose credit card expiry date < system date (MMYYYY).
                    planList = this.queryDAO.executeForMapList(SELECT_BANK_INFO_BEFORE_TODAY, params);
                } else {
                    //  check if there is customer whose credit card expiry date >= system date and expiry date <= system date + N months
                    params = new HashMap<String, Object>();
                    params.put("NO_OF_MONTH", noOfMonth);
                    planList = this.queryDAO.executeForMapList(SELECT_BANK_INFO, params);
                }

                if (planList.size() <= 0) {
                    t_batch = new T_SET_BATCH();
                    t_batch.setSTATUS("3");
                    t_batch.setID_BATCH(Integer.toString(batchId));
                    t_batch.setBATCH_TYPE("CC");
                    t_batch.setMessage(this.msgResource.getMessage("info.ERR2SC049", null));
                    // Call G_SET_P01
                    gsetp01.G_SET_P01_GetIdBatch(t_batch);

                    return null;
                }

                // generate exported file
                String[] file = this.exportCCExipiredData(planList);
                
                ArrayList<AlertUserDto> arrUserAlert = new ArrayList<AlertUserDto>();

                // process for get user alert
                for (Map<String, Object> user : alertList) {
                    AlertUserDto alertUser = new AlertUserDto();
                    alertUser.setAlertUser(CommonUtils.toString(user.get("ALERT_USER")));
                    arrUserAlert.add(alertUser);
                }

                t_batch = new T_SET_BATCH();
                t_batch.setSTATUS("2");
                t_batch.setID_BATCH(Integer.toString(batchId));
                t_batch.setBATCH_TYPE("CC");
                t_batch.setMessage(this.msgResource.getMessage("info.ERR2SC023", new Object[] { noOfMonth, file[1] }));
                t_batch.setFILENAME(file[1]);
                gsetp01.G_SET_P01_GetIdBatch(t_batch);

                G_ALT_P06 altP06 = new G_ALT_P06(queryDAO, updateDAO);
                G_ALT_P06Input input = new G_ALT_P06Input();
                input.setBatchId("CC");
                String message = this.msgResource.getMessage("info.ERR2SC022", null);
                String subject = message;
                message = this.msgResource.getMessage("info.ERR2SC023", new Object[] { noOfMonth, file[1] });
                String msg = message;
                input.setSubject(subject);
                input.setMsg(msg);
                input.setListAlertUser(arrUserAlert);
                input.setFilelocation(file[0]);
                input.setFilename(file[1]);
                altP06.execute(input, uvo);
            } else {
                t_batch = new T_SET_BATCH();
                t_batch.setSTATUS("3");
                t_batch.setID_BATCH(Integer.toString(batchId));
                t_batch.setBATCH_TYPE("CC");
                t_batch.setMessage(this.msgResource.getMessage("info.ERR2SC049", null));
                // Call G_SET_P01
                gsetp01.G_SET_P01_GetIdBatch(t_batch);
            }
        }
        return null;
    }

    /**
     * Output expired credit card information.
     * 
     * @param planList
     * @return export file's full path and file name.
     */
    private String[] exportCCExipiredData(List<Map<String, Object>> planList) {
        String[] result = { " ", " " };

        String amountDueNulMsg ="Register account No. not found. Please check this customer account No. at customer bank info.";
        
        // Write data into Excel
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("CreditCardExpiration");

        // Write header
        Row row = sheet1.createRow(0);
        Cell headerCell1 = row.createCell(0);
        headerCell1.setCellValue(MessageUtil.get("reports.GALTP04.no"));

        Cell headerCell2 = row.createCell(1);
        headerCell2.setCellValue(MessageUtil.get("reports.GALTP04.custId"));

        Cell headerCell3 = row.createCell(2);
        headerCell3.setCellValue(MessageUtil.get("reports.GALTP04.custName"));

        Cell headerCell4 = row.createCell(3);
        headerCell4.setCellValue(MessageUtil.get("reports.GALTP04.custType"));

        Cell headerCell5 = row.createCell(4);
        headerCell5.setCellValue(MessageUtil.get("reports.GALTP04.accNo"));

        Cell headerCell6 = row.createCell(5);
        headerCell6.setCellValue(MessageUtil.get("reports.GALTP04.cardType"));

        Cell headerCell7 = row.createCell(6);
        headerCell7.setCellValue(MessageUtil.get("reports.GALTP04.holderName"));

        Cell headerCell8 = row.createCell(7);
        headerCell8.setCellValue(MessageUtil.get("reports.GALTP04.expiryDate"));

        Cell headerCell9 = row.createCell(8);
        headerCell9.setCellValue(MessageUtil.get("reports.GALTP04.ccNumber"));

        Cell headerCell10 = row.createCell(9);
        headerCell10.setCellValue(MessageUtil.get("reports.GALTP04.email"));

        Cell headerCell11 = row.createCell(10);
        headerCell11.setCellValue(MessageUtil.get("reports.GALTP04.phoneNum"));

        Cell headerCell12 = row.createCell(11);
        headerCell12.setCellValue(MessageUtil.get("reports.GALTP04.totalAmountNo"));

        Cell headerCell13 = row.createCell(12);
        headerCell13.setCellValue(MessageUtil.get("reports.GALTP04.lastInvoiceNo"));

        // Write Content
        for (int i = 0; i < planList.size(); i++) {

            row = sheet1.createRow(i + 1);

            Map<String, Object> data = planList.get(i);

            // No.
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(i + 1);

            // Customer ID
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(CommonUtils.toString(data.get("ID_CUST")).trim());

            // Customer Name
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(CommonUtils.toString(data.get("CUST_NAME")).trim());

            // Customer Type
            String custType = CommonUtils.toString(data.get("CUSTOMER_TYPE")).trim();
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", custType));

            // Account No.
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(CommonUtils.toString(data.get("CCARD_ACCT_NO")).trim());

            // Card Type
            String cardType = CommonUtils.toString(data.get("CCARD_TYPE")).trim();
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(CommonUtils.getCodeMapListNameByValue("LIST_CREDIT_CARD_TYPE", cardType));

            // Holder Name
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(CommonUtils.toString(data.get("CCARD_HOLDER_NAME")).trim());

            // Expiry Date
            Cell cell8 = row.createCell(7);
            cell8.setCellValue(CommonUtils.toString(data.get("CCARD_EXPIRED_DATE")).trim());

            // Credit Card Number
            String cardNo = CommonUtils.toString(data.get("CCARD_NO")).trim();
            String decryptCardNo = SecurityUtil.aesDecrypt(cardNo);
            // Credit Card Number only display last 4 digits
            if (decryptCardNo.length() > 4) {
                decryptCardNo = decryptCardNo.substring(decryptCardNo.length() - 4);
            }

            Cell cell9 = row.createCell(8);
            cell9.setCellValue(decryptCardNo);

            // E-mail
            Cell cell10 = row.createCell(9);
            cell10.setCellValue(CommonUtils.toString(data.get("EMAIL")).trim());

            // Phone Number
            Cell cell11 = row.createCell(10);
            cell11.setCellValue(CommonUtils.toString(data.get("TEL_NO")).trim());
            
            //Total Amount Due
            Cell cell12 = row.createCell(11);
            if(CommonUtils.isEmpty(data.get("TOTAL_AMT_DUE"))){
                cell12.setCellValue(amountDueNulMsg);
            }else{
                cell12.setCellValue(CommonUtils.toString(data.get("TOTAL_AMT_DUE")).trim());
            }

            //Last Invoice No.
            Cell cell13 = row.createCell(12);
            cell13.setCellValue(CommonUtils.toString(data.get("ID_REF")).trim());
        }

        for (int i = 0; i < 11; i++) {
            sheet1.autoSizeColumn(i);
        }

        // Ouput excel to physical file
        OutputStream os = null;
        try {
            // Export Path for alert notification directory
            BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
            String exportPath = CommonUtils.toString(setting.getFileLocation()).trim();

            if (!exportPath.endsWith(File.separator)) {
                exportPath = exportPath + File.separator;
            }
            exportPath = exportPath + STR_NOTIFICATION + File.separator;
            
            File pfile = new File(exportPath);
            if (!pfile.exists()) {
                pfile.mkdirs();
            }

            // File Name
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = "CreditCardExpirationList_" + sdf.format(new Date()) + ".xls";
            String fullPath = exportPath + fileName;

            os = new FileOutputStream(fullPath);
            wb.write(os);

            result[0] = fullPath;
            result[1] = fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
