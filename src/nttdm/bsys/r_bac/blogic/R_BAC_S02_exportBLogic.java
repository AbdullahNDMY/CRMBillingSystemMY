/**
 * @(#)R_BAC_S02_exportBLogic.java
 * Billing System
 * Version 1.00
 * Created 2014-05-23
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.blogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * R_BAC_S02_exportBLogic class. to export result
 * 
 * @author NTT DATA
 */
public class R_BAC_S02_exportBLogic implements BLogic<HashMap<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

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
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(HashMap<String, Object> input) {
        BLogicResult result = new BLogicResult();

        String billAcc = CommonUtils.toString(input.get("billAcc"));

        String fileName = billAcc.trim() + "_Analysis_YYMMDDHHMMSS.xls".replace("YYMMDDHHMMSS", getSysdateStr());

        billAcc = paddingRightSpace(billAcc, 20);

        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }
        String filePathName = tmpFolder + fileName;

        List<Map<String, Object>> resultListTotal = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> billaccs = queryDAO.executeForMapList("R_BAC.getBillAccs", input);

        Map<String, Object> resultMap;

        // prepare result map
        // 1
        List<Map<String, Object>> receiptsNoDetails = queryDAO.executeForMapList("R_BAC.getExportSql1", null);

        Map<String, Integer> receiptsNoDetailsMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptsNoDetails.size(); i++) {
            String key = receiptsNoDetails.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptsNoDetailsMap.get(key);
            if (old != null) {
                receiptsNoDetailsMap.put(key, old + 1);
            } else {
                receiptsNoDetailsMap.put(key, 1);
            }
        }

        // 2
        List<Map<String, Object>> receiptOverMatch = queryDAO.executeForMapList("R_BAC.getExportSql2", null);

        Map<String, Integer> receiptOverMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptOverMatch.size(); i++) {
            String key = receiptOverMatch.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptOverMatchMap.get(key);
            if (old != null) {
                receiptOverMatchMap.put(key, old + 1);
            } else {
                receiptOverMatchMap.put(key, 1);
            }
        }

        // 3
        List<Map<String, Object>> receiptNotFullyMatch = queryDAO.executeForMapList("R_BAC.getExportSql3", null);
        Map<String, Integer> receiptNotFullyMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptNotFullyMatch.size(); i++) {

            String key = receiptNotFullyMatch.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptNotFullyMatchMap.get(key);
            if (old != null) {
                receiptNotFullyMatchMap.put(key, old + 1);
            } else {
                receiptNotFullyMatchMap.put(key, 1);
            }
        }

        // 4
        List<Map<String, Object>> invoiceOverMatch = queryDAO.executeForMapList("R_BAC.getExportSql4", null);
        Map<String, Integer> invoiceOverMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < invoiceOverMatch.size(); i++) {

            String key = invoiceOverMatch.get(i).get("BILL_ACC").toString().trim();
            Integer old = invoiceOverMatchMap.get(key);
            if (old != null) {
                invoiceOverMatchMap.put(key, old + 1);
            } else {
                invoiceOverMatchMap.put(key, 1);
            }
        }

        // 5
        List<Map<String, Object>> invoiceNotFullyMatch = queryDAO.executeForMapList("R_BAC.getExportSql5", null);
        Map<String, Integer> invoiceNotFullyMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < invoiceNotFullyMatch.size(); i++) {

            String key = invoiceNotFullyMatch.get(i).get("BILL_ACC").toString().trim();
            Integer old = invoiceNotFullyMatchMap.get(key);
            if (old != null) {
                invoiceNotFullyMatchMap.put(key, old + 1);
            } else {
                invoiceNotFullyMatchMap.put(key, 1);
            }
        }

        // 6
        List<Map<String, Object>> receiptAmountNegative = queryDAO.executeForMapList("R_BAC.getExportSql6", null);
        Map<String, Integer> receiptAmountNegativeMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptAmountNegative.size(); i++) {

            String key = receiptAmountNegative.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptAmountNegativeMap.get(key);
            if (old != null) {
                receiptAmountNegativeMap.put(key, old + 1);
            } else {
                receiptAmountNegativeMap.put(key, 1);
            }
        }

        // prepare result map

        for (int i = 0; i < billaccs.size(); i++) {

            String billacc = billaccs.get(i).get("ID_BILL_ACCOUNT").toString().trim();

            int total = getNum(receiptsNoDetailsMap, billacc) + getNum(receiptOverMatchMap, billacc) + getNum(receiptNotFullyMatchMap, billacc)
                    + getNum(invoiceOverMatchMap, billacc) + getNum(invoiceNotFullyMatchMap, billacc) + getNum(receiptAmountNegativeMap, billacc);

            if (total <= 0) {
                continue;
            }

            resultMap = new HashMap<String, Object>();

            resultMap.put("billacc", billacc);
            resultMap.put("totalAmtDue", billaccs.get(i).get("TOTAL_AMT_DUE"));

            // 1
            resultMap.put("receiptsNoDetailsSize", getNum(receiptsNoDetailsMap, billacc));

            // 2
            resultMap.put("receiptOverMatchSize", getNum(receiptOverMatchMap, billacc));

            // 3
            resultMap.put("receiptNotFullyMatchSize", getNum(receiptNotFullyMatchMap, billacc));

            // 4
            resultMap.put("invoiceOverMatchSize", getNum(invoiceOverMatchMap, billacc));

            // 5
            resultMap.put("invoiceNotFullyMatchSize", getNum(invoiceNotFullyMatchMap, billacc));

            // 6
            resultMap.put("receiptAmountNegativeSize", getNum(receiptAmountNegativeMap, billacc));

            resultListTotal.add(resultMap);
        }

        OutputStream os = null;
        try {

            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet(billAcc.trim());

            Font font = wb.createFont();
            font.setFontName("Calibri");
            font.setFontHeightInPoints((short) 10);

            writeFileHeader(sheet, wb, font);

            writeFileContent(sheet, resultListTotal, wb, font);

            for (int i = 0; i < 15; i++) {
                sheet.autoSizeColumn(i);
            }

            sheet.createFreezePane(0, 1);

            os = new FileOutputStream(new File(filePathName));
            wb.write(os);
            os.flush();
        } catch (IOException e) {
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

        // Set download file
        DownloadFile file = new DownloadFile(new File(filePathName));
        result.setResultObject(file);
        return result;
    }

    // write Csv Content
    private void writeFileContent(Sheet sheet, List<Map<String, Object>> resultListTotal, Workbook wb, Font font) throws IOException {
        Row row = null;

        CellStyle comStyle = wb.createCellStyle();
        comStyle.setFont(font);

        CellStyle numStyle = wb.createCellStyle();
        DataFormat fm = wb.createDataFormat();
        numStyle.setDataFormat(fm.getFormat("#,##0.00"));
        numStyle.setFont(font);
        // numStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

        Map<String, Object> item = null;
        int receiptNum = 0;

        for (int i = 0; i < resultListTotal.size(); i++) {

            item = resultListTotal.get(i);

            row = sheet.createRow(i + 1 + receiptNum);

            Cell cell1 = row.createCell(0);
            cell1.setCellValue(String.valueOf(i + 1));
            // cell1.setCellType(arg0);
            cell1.setCellStyle(comStyle);

            Cell cell2 = row.createCell(1);
            cell2.setCellValue(CommonUtils.toString(item.get("billacc")).trim());
            cell2.setCellStyle(comStyle);

            Cell cell3 = row.createCell(2);
            cell3.setCellValue(Double.valueOf(CommonUtils.toString(item.get("totalAmtDue"))));
            // cell3.setCellType(Cell.CELL_TYPE_NUMERIC);
            cell3.setCellStyle(numStyle);

            String billacc = item.get("billacc").toString();
            // latest invoice
            String latestInvoice = queryDAO.executeForObject("R_BAC.getLatestInvoice", paddingRightSpace(billacc, 20), String.class);
            if (CommonUtils.isEmpty(latestInvoice)) {
                latestInvoice = "-";
            }

            Cell cell4 = row.createCell(3);
            cell4.setCellValue(latestInvoice);
            cell4.setCellStyle(comStyle);

            Cell cell5 = row.createCell(4);
            cell5.setCellValue(CommonUtils.toString(item.get("receiptsNoDetailsSize")));
            cell5.setCellStyle(comStyle);

            Cell cell6 = row.createCell(5);
            cell6.setCellValue(CommonUtils.toString(item.get("receiptOverMatchSize")));
            cell6.setCellStyle(comStyle);

            Cell cell7 = row.createCell(6);
            cell7.setCellValue(CommonUtils.toString(item.get("receiptNotFullyMatchSize")));
            cell7.setCellStyle(comStyle);

            Cell cell8 = row.createCell(7);
            cell8.setCellValue(CommonUtils.toString(item.get("invoiceOverMatchSize")));
            cell8.setCellStyle(comStyle);

            Cell cell9 = row.createCell(8);
            cell9.setCellValue(CommonUtils.toString(item.get("invoiceNotFullyMatchSize")));
            cell9.setCellStyle(comStyle);

            Cell cell10 = row.createCell(9);
            cell10.setCellValue(CommonUtils.toString(item.get("receiptAmountNegativeSize")));
            cell10.setCellStyle(comStyle);

        }
    }

    // write file Header
    private void writeFileHeader(Sheet sheet, Workbook wb, Font font) throws IOException {

        CellStyle gray = wb.createCellStyle();
        gray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        gray.setFillPattern(CellStyle.SOLID_FOREGROUND);
        gray.setBorderLeft(CellStyle.BORDER_THIN);
        gray.setBorderRight(CellStyle.BORDER_THIN);
        gray.setFont(font);
        gray.setWrapText(true);

        Row row = sheet.createRow(0);
        row.setHeightInPoints(45);

        Cell cell1 = row.createCell(0);
        cell1.setCellValue("No");
        cell1.setCellStyle(gray);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Billing\r\nAccount");
        cell2.setCellStyle(gray);

        Cell cell3 = row.createCell(2);
        cell3.setCellValue("Total Amount\r\nDue");
        cell3.setCellStyle(gray);

        Cell cell4 = row.createCell(3);
        cell4.setCellValue("Latest Invoice");
        cell4.setCellStyle(gray);

        Cell cell5 = row.createCell(4);
        cell5.setCellValue("1\r\nReceipt\r\nNo Details");
        cell5.setCellStyle(gray);

        Cell cell6 = row.createCell(5);
        cell6.setCellValue("2\r\nReceipt\r\nOver Match");
        cell6.setCellStyle(gray);

        // receipt
        Cell cell7 = row.createCell(6);
        cell7.setCellValue("3\r\nReceipt Not\r\nFully Match");
        cell7.setCellStyle(gray);

        Cell cell8 = row.createCell(7);
        cell8.setCellValue("4\r\nInvoice\r\nOver Match");
        cell8.setCellStyle(gray);

        Cell cell9 = row.createCell(8);
        cell9.setCellValue("5\r\nInvoice Not\r\nFully Match");
        cell9.setCellStyle(gray);

        Cell cell10 = row.createCell(9);
        cell10.setCellValue("6\r\nReceipt Amount\r\nNegative");
        cell10.setCellStyle(gray);

    }

    private Integer getNum(Map<String, Integer> map, String billacc) {
        if (map.get(billacc) == null) {
            return 0;
        } else {
            return map.get(billacc);
        }
    }

    /**
     * YY MMDDHHMMSS
     * 
     * @return
     */
    private String getSysdateStr() {
        Calendar now = Calendar.getInstance();
        String yy = CommonUtils.formatDate(now.getTime(), "yy");
        String MM = CommonUtils.formatDate(now.getTime(), "MM");
        String dd = CommonUtils.formatDate(now.getTime(), "dd");
        String HH = CommonUtils.formatDate(now.getTime(), "HH");
        String mm = CommonUtils.formatDate(now.getTime(), "mm");
        String ss = CommonUtils.formatDate(now.getTime(), "ss");

        return yy + "" + MM + "" + dd + "" + HH + "" + mm + ss;
    }

    /**
     * padding right space
     * 
     * @param str
     * @param len
     * @return
     */
    private static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for (int i = str.length(); i < len; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
