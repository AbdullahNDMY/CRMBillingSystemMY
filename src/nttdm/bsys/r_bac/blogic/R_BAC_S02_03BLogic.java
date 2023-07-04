/**
 * @(#)R_BAC_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.blogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.util.CollectionUtils;

/**
 * R_BAC_R03BLogic class.
 * 
 * @author NTT DATA
 */
public class R_BAC_S02_03BLogic implements BLogic<HashMap<String, Object>> {

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

        String fileName = billAcc.trim() + "_YYMMDDHHMMSS.xls".replace("YYMMDDHHMMSS", getSysdateStr());

        billAcc = paddingRightSpace(billAcc, 20);

        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }
        String filePathName = tmpFolder + fileName;
        OutputStream os = null;
        try {

            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet(billAcc.trim());

            Font font = wb.createFont();
            font.setFontName("Calibri");
            font.setFontHeightInPoints((short) 10);

            writeFileHeader(sheet, wb, font);

            writeFileContent(sheet, billAcc, wb, font);

//            for (int i = 0; i < 15; i++) {
//                sheet.autoSizeColumn(i);
//            }
//            sheet.setColumnWidth((short) 0, (short) (35.7*85));
//            sheet.setColumnWidth((short) 1, (short) (35.7*120));
//            sheet.setColumnWidth((short) 6, (short) (35.7*85));
//            sheet.setColumnWidth((short) 7, (short) (35.7*75));
//            sheet.setColumnWidth((short) 10, (short) (35.7*120));
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(6);
            sheet.setColumnWidth((short) 7, (short) (35.7*75));
            sheet.autoSizeColumn(10);

            sheet.createFreezePane(6, 1);

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
    private void writeFileContent(Sheet sheet, String billAcc, Workbook wb, Font font) throws IOException {
        Row row = null;

        CellStyle comStyle = wb.createCellStyle();
        comStyle.setFont(font);

        CellStyle numStyle = wb.createCellStyle();
        DataFormat fm = wb.createDataFormat();
        numStyle.setDataFormat(fm.getFormat("#,##0.00"));
        numStyle.setFont(font);
        //numStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

        List<Map<String, Object>> invoiceList = queryDAO.executeForMapList("R_BAC.getInvoices", billAcc);

        Map<String, Object> invoice = null;
        int receiptNum = 0;

        for (int i = 0; i < invoiceList.size(); i++) {

            invoice = invoiceList.get(i);
            List<Map<String, Object>> receiptsList = queryDAO.executeForMapList("R_BAC.getReceipts", invoice.get("ID_REF"));

            row = sheet.createRow(i + 1 + receiptNum);

            Cell cell1 = row.createCell(0);
            cell1.setCellValue(CommonUtils.toString(invoice.get("BILL_ACC")).trim());
            //cell1.setCellType(arg0);
            cell1.setCellStyle(comStyle);

            Cell cell2 = row.createCell(1);
            cell2.setCellValue(CommonUtils.toString(invoice.get("ID_REF")).trim());
            cell2.setCellStyle(comStyle);

            Cell cell3 = row.createCell(2);
            cell3.setCellValue(Double.valueOf(CommonUtils.toString(invoice.get("BILL_AMOUNT"))));
            //cell3.setCellType(Cell.CELL_TYPE_NUMERIC);
            cell3.setCellStyle(numStyle);

            Cell cell4 = row.createCell(3);
            cell4.setCellValue(Double.valueOf(CommonUtils.toString(invoice.get("PAID_AMOUNT"))));
            cell4.setCellStyle(numStyle);

            Cell cell5 = row.createCell(4);
            cell5.setCellValue(Double.valueOf(CommonUtils.toString(invoice.get("OUTSTANDING"))));
            cell5.setCellStyle(numStyle);

            Cell cell6 = row.createCell(5);
            cell6.setCellValue(CommonUtils.toString(invoice.get("IS_SETTLED")));
            cell6.setCellStyle(comStyle);

            if (!CollectionUtils.isEmpty(receiptsList)) {
                for (int j = 0; j < receiptsList.size(); j++) {
                    if (j > 0) {
                        receiptNum++;
                        row = sheet.createRow(i + 1 + receiptNum);
                    }

                    // receipt
                    Cell cell7 = row.createCell(6);
                    cell7.setCellValue(CommonUtils.toString(receiptsList.get(j).get("RECEIPT_NO")).trim());
                    cell7.setCellStyle(comStyle);

                    Cell cell8 = row.createCell(7);
                    cell8.setCellValue(CommonUtils.toString(receiptsList.get(j).get("DATE_TRANS")));
                    cell8.setCellStyle(comStyle);

                    Cell cell9 = row.createCell(8);
                    cell9.setCellValue(Double.valueOf(CommonUtils.toString(receiptsList.get(j).get("AMT_RECEIVED"))));
                    cell9.setCellStyle(numStyle);

                    Cell cell10 = row.createCell(9);
                    cell10.setCellValue(Double.valueOf(CommonUtils.toString(receiptsList.get(j).get("BALANCE_AMT"))));
                    cell10.setCellStyle(numStyle);

                    Cell cell11 = row.createCell(10);
                    cell11.setCellValue(CommonUtils.toString(receiptsList.get(j).get("REFERENCE1")).trim());
                    cell11.setCellStyle(comStyle);

                    Cell cell12 = row.createCell(11);
                    cell12.setCellValue(CommonUtils.toString(receiptsList.get(j).get("ID_REF")));
                    cell12.setCellStyle(comStyle);

                    Cell cell13 = row.createCell(12);
                    cell13.setCellValue(Double.valueOf(CommonUtils.toString(receiptsList.get(j).get("AMT_PAID"))));
                    cell13.setCellStyle(numStyle);

                    Cell cell14 = row.createCell(13);
                    cell14.setCellValue(Double.valueOf(CommonUtils.toString(receiptsList.get(j).get("AMT_DUE"))));
                    cell14.setCellStyle(numStyle);

                    Cell cell15 = row.createCell(14);
                    cell15.setCellValue(CommonUtils.toString(receiptsList.get(j).get("PMT_STATUS")));
                    cell15.setCellStyle(comStyle);
                }
            }
        }

        List<Map<String, Object>> receiptNoInvoiceList = queryDAO.executeForMapList("R_BAC.getReceiptNoInvoice", billAcc);

        for (int i = 0; i < receiptNoInvoiceList.size(); i++) {

            row = sheet.createRow(i + 1 + receiptNum + invoiceList.size());
            // receipt
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(CommonUtils.toString(receiptNoInvoiceList.get(i).get("RECEIPT_NO")).trim());
            cell7.setCellStyle(comStyle);

            Cell cell8 = row.createCell(7);
            cell8.setCellValue(CommonUtils.toString(receiptNoInvoiceList.get(i).get("DATE_TRANS")));
            cell8.setCellStyle(comStyle);

            Cell cell9 = row.createCell(8);
            cell9.setCellValue(Double.valueOf(CommonUtils.toString(receiptNoInvoiceList.get(i).get("AMT_RECEIVED"))));
            cell9.setCellStyle(numStyle);

            Cell cell10 = row.createCell(9);
            cell10.setCellValue(Double.valueOf(CommonUtils.toString(receiptNoInvoiceList.get(i).get("BALANCE_AMT"))));
            cell10.setCellStyle(numStyle);

            Cell cell11 = row.createCell(10);
            cell11.setCellValue(CommonUtils.toString(receiptNoInvoiceList.get(i).get("REFERENCE1")).trim());
            cell11.setCellStyle(comStyle);

            Cell cell12 = row.createCell(11);
            cell12.setCellValue(CommonUtils.toString(receiptNoInvoiceList.get(i).get("REJECT_DESC")));
            cell12.setCellStyle(comStyle);

            Cell cell15 = row.createCell(14);
            cell15.setCellValue(CommonUtils.toString(receiptNoInvoiceList.get(i).get("PMT_STATUS")));
            cell15.setCellStyle(comStyle);
        }
    }

    // write file Header
    private void writeFileHeader(Sheet sheet, Workbook wb, Font font) throws IOException {

        CellStyle orange = wb.createCellStyle();
        orange.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        orange.setFillPattern(CellStyle.SOLID_FOREGROUND);
        orange.setBorderLeft(CellStyle.BORDER_THIN);
        orange.setBorderRight(CellStyle.BORDER_THIN);
        orange.setFont(font);

        CellStyle aqua = wb.createCellStyle();
        aqua.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        aqua.setFillPattern(CellStyle.SOLID_FOREGROUND);
        aqua.setBorderLeft(CellStyle.BORDER_THIN);
        aqua.setBorderRight(CellStyle.BORDER_THIN);
        aqua.setFont(font);

        Row row = sheet.createRow(0);
        row.setHeightInPoints(20);

        Cell cell1 = row.createCell(0);
        cell1.setCellValue("BILL_ACC");
        cell1.setCellStyle(orange);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("ID_REF");
        cell2.setCellStyle(orange);

        Cell cell3 = row.createCell(2);
        cell3.setCellValue("BILL_AMOUNT");
        cell3.setCellStyle(orange);

        Cell cell4 = row.createCell(3);
        cell4.setCellValue("PAID_AMOUNT");
        cell4.setCellStyle(orange);

        Cell cell5 = row.createCell(4);
        cell5.setCellValue("OUTSTANDING");
        cell5.setCellStyle(orange);

        Cell cell6 = row.createCell(5);
        cell6.setCellValue("IS_SETTLED");
        cell6.setCellStyle(orange);

        // receipt
        Cell cell7 = row.createCell(6);
        cell7.setCellValue("RECEIPT_NO");
        cell7.setCellStyle(aqua);

        Cell cell8 = row.createCell(7);
        cell8.setCellValue("DATE_TRANS");
        cell8.setCellStyle(aqua);

        Cell cell9 = row.createCell(8);
        cell9.setCellValue("AMT_RECEIVED");
        cell9.setCellStyle(aqua);

        Cell cell10 = row.createCell(9);
        cell10.setCellValue("BALANCE_AMT");
        cell10.setCellStyle(aqua);

        Cell cell11 = row.createCell(10);
        cell11.setCellValue("REFERENCE1");
        cell11.setCellStyle(aqua);

        Cell cell12 = row.createCell(11);
        cell12.setCellValue("ID_REF");
        cell12.setCellStyle(aqua);

        Cell cell13 = row.createCell(12);
        cell13.setCellValue("AMT_PAID");
        cell13.setCellStyle(aqua);

        Cell cell14 = row.createCell(13);
        cell14.setCellValue("AMT_DUE");
        cell14.setCellStyle(aqua);

        Cell cell15 = row.createCell(14);
        cell15.setCellValue("PMT_STATUS");
        cell15.setCellStyle(aqua);
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
