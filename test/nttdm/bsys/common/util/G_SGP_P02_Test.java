/**
 * @(#)G_SGP_P02_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/30
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Output;

import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

/**
 * @author gplai
 * 
 */
public class G_SGP_P02_Test extends AbstractUtilTest {

    private G_SGP_P02 gSgpP02;
    BillingSystemUserValueObject uvo = null;

    @Override
    protected void setUpData() throws Exception {
        //Logger.getRootLogger().setLevel(Level.DEBUG);
        uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        gSgpP02 = new G_SGP_P02();
        gSgpP02.setQueryDAO(queryDAO);
        gSgpP02.setUpdateDAO(updateDAO);
    }

    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute1() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");
        super.deleteAllData("T_SGP_IMP_DT");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109021100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109031100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
//        assertEquals(2, list_f_sub.size());
//        HashMap<String, Object> data = list_f_sub.get(1);
//        String[][] dataXX = new String[][]{
//                {"H","HEADER_ID"},
//                {"Filler","HEADER_FILLER"},
//                {"20110916","FILE_PROC_DATE"},
//                {"F","FOOTER_ID"},
//                {"Filler","FOOTER_FILLER"},
//                {"00000003","TOTALREC"},
//                {"19123456.89","TOTALAMT"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_DT",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110901","BUSINESS_DATE"},
//                {"AccountNo1","ACCOUNT_NO"},
//                {"InvoiceNoCase1","INVOICE_NO"},
//                {"001","SC_NO"},
//                {"UGID","UGID"},
//                {"1001","TSN"},
//                {"9123456.89","AMOUNT"},
//                {"9123456.81","AMT1"},
//                {"MO1","MOP1"},
//                {"9123456.82","AMT2"},
//                {"MO2","MOP2"},
//                {"9123456.83","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110902","BUSINESS_DATE"},
//                {"AccountNo2","ACCOUNT_NO"},
//                {"InvoiceNoCase2","INVOICE_NO"},
//                {"002","SC_NO"},
//                {"UGID","UGID"},
//                {"1002","TSN"},
//                {"0.01","AMOUNT"},
//                {"0.02","AMT1"},
//                {"MO1","MOP1"},
//                {"0.03","AMT2"},
//                {"MO2","MOP2"},
//                {"0.04","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110903","BUSINESS_DATE"},
//                {"AccountNo3","ACCOUNT_NO"},
//                {"InvoiceNoCase3","INVOICE_NO"},
//                {"003","SC_NO"},
//                {"UGID","UGID"},
//                {"1003","TSN"},
//                {"9999999.99","AMOUNT"},
//                {"9999999.99","AMT1"},
//                {"MO1","MOP1"},
//                {"9999999.99","AMT2"},
//                {"MO2","MOP2"},
//                {"9999999.99","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_CSB_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"KYD","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase1","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"9999999999999.99","BALANCE_AMT"},
//                {"1100154","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"229743","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"CLP","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase2","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0.01","BALANCE_AMT"},
//                {"1100164","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"ALL","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase3","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"-800","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0","BALANCE_AMT"},
//                {"1100165","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"Y","PAID_PRE_INVOICE"}
//                };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_BAC_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }

    }
    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute11() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("202");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
        assertEquals(2, list_f_sub.size());
//        HashMap<String, Object> data = list_f_sub.get(1);
//        String[][] dataXX = new String[][]{
//                {"H","HEADER_ID"},
//                {"Filler","HEADER_FILLER"},
//                {"20110916","FILE_PROC_DATE"},
//                {"F","FOOTER_ID"},
//                {"Filler","FOOTER_FILLER"},
//                {"00000003","TOTALREC"},
//                {"19123456.89","TOTALAMT"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_DT",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110901","BUSINESS_DATE"},
//                {"AccountNo1","ACCOUNT_NO"},
//                {"InvoiceNoCase1","INVOICE_NO"},
//                {"001","SC_NO"},
//                {"UGID","UGID"},
//                {"1001","TSN"},
//                {"9123456.89","AMOUNT"},
//                {"9123456.81","AMT1"},
//                {"MO1","MOP1"},
//                {"9123456.82","AMT2"},
//                {"MO2","MOP2"},
//                {"9123456.83","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110902","BUSINESS_DATE"},
//                {"AccountNo2","ACCOUNT_NO"},
//                {"InvoiceNoCase2","INVOICE_NO"},
//                {"002","SC_NO"},
//                {"UGID","UGID"},
//                {"1002","TSN"},
//                {"0.01","AMOUNT"},
//                {"0.02","AMT1"},
//                {"MO1","MOP1"},
//                {"0.03","AMT2"},
//                {"MO2","MOP2"},
//                {"0.04","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110903","BUSINESS_DATE"},
//                {"AccountNo3","ACCOUNT_NO"},
//                {"InvoiceNoCase3","INVOICE_NO"},
//                {"003","SC_NO"},
//                {"UGID","UGID"},
//                {"1003","TSN"},
//                {"9999999.99","AMOUNT"},
//                {"9999999.99","AMT1"},
//                {"MO1","MOP1"},
//                {"9999999.99","AMT2"},
//                {"MO2","MOP2"},
//                {"9999999.99","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_CSB_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"202","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"KYD","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase1","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"9999999999999.99","BALANCE_AMT"},
//                {"1100154","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"229743","ID_CUST"},
//                {"202","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"CLP","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase2","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0.01","BALANCE_AMT"},
//                {"1100164","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"202","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"ALL","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase3","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"-800","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0","BALANCE_AMT"},
//                {"1100165","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"Y","PAID_PRE_INVOICE"}
//                };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_BAC_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }

    }
    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute12() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110904UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
        assertEquals(2, list_f_sub.size());
//        HashMap<String, Object> data = list_f_sub.get(1);
//        String[][] dataXX = new String[][]{
//                {"H","HEADER_ID"},
//                {"Filler","HEADER_FILLER"},
//                {"20110916","FILE_PROC_DATE"},
//                {"F","FOOTER_ID"},
//                {"Filler","FOOTER_FILLER"},
//                {"00000003","TOTALREC"},
//                {"19123456.89","TOTALAMT"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_DT",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110902","BUSINESS_DATE"},
//                {"AccountNo2","ACCOUNT_NO"},
//                {"InvoiceNoCase2","INVOICE_NO"},
//                {"002","SC_NO"},
//                {"UGID","UGID"},
//                {"1002","TSN"},
//                {"0.01","AMOUNT"},
//                {"0.02","AMT1"},
//                {"MO1","MOP1"},
//                {"0.03","AMT2"},
//                {"MO2","MOP2"},
//                {"0.04","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110903","BUSINESS_DATE"},
//                {"AccountNo3","ACCOUNT_NO"},
//                {"InvoiceNoCase3","INVOICE_NO"},
//                {"003","SC_NO"},
//                {"UGID","UGID"},
//                {"1003","TSN"},
//                {"9999999.99","AMOUNT"},
//                {"9999999.99","AMT1"},
//                {"MO1","MOP1"},
//                {"9999999.99","AMT2"},
//                {"MO2","MOP2"},
//                {"9999999.99","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110904","BUSINESS_DATE"},
//                {"AccountNo1","ACCOUNT_NO"},
//                {"InvoiceNoCase1","INVOICE_NO"},
//                {"001","SC_NO"},
//                {"UGID","UGID"},
//                {"1001","TSN"},
//                {"9123456.89","AMOUNT"},
//                {"9123456.81","AMT1"},
//                {"MO1","MOP1"},
//                {"9123456.82","AMT2"},
//                {"MO2","MOP2"},
//                {"9123456.83","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_CSB_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"KYD","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase1","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"9999999999999.99","BALANCE_AMT"},
//                {"1100154","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"229743","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"CLP","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase2","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0.01","BALANCE_AMT"},
//                {"1100164","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"ALL","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase3","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"-800","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0","BALANCE_AMT"},
//                {"1100165","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"Y","PAID_PRE_INVOICE"}
//                };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_BAC_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }

    }
    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute13() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110917Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
        assertEquals(2, list_f_sub.size());
//        HashMap<String, Object> data = list_f_sub.get(1);
//        String[][] dataXX = new String[][]{
//                {"H","HEADER_ID"},
//                {"Filler","HEADER_FILLER"},
//                {"20110917","FILE_PROC_DATE"},
//                {"F","FOOTER_ID"},
//                {"Filler","FOOTER_FILLER"},
//                {"00000003","TOTALREC"},
//                {"19123456.89","TOTALAMT"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_DT",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110901","BUSINESS_DATE"},
//                {"AccountNo1","ACCOUNT_NO"},
//                {"InvoiceNoCase1","INVOICE_NO"},
//                {"001","SC_NO"},
//                {"UGID","UGID"},
//                {"1001","TSN"},
//                {"9123456.89","AMOUNT"},
//                {"9123456.81","AMT1"},
//                {"MO1","MOP1"},
//                {"9123456.82","AMT2"},
//                {"MO2","MOP2"},
//                {"9123456.83","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110902","BUSINESS_DATE"},
//                {"AccountNo2","ACCOUNT_NO"},
//                {"InvoiceNoCase2","INVOICE_NO"},
//                {"002","SC_NO"},
//                {"UGID","UGID"},
//                {"1002","TSN"},
//                {"0.01","AMOUNT"},
//                {"0.02","AMT1"},
//                {"MO1","MOP1"},
//                {"0.03","AMT2"},
//                {"MO2","MOP2"},
//                {"0.04","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110903","BUSINESS_DATE"},
//                {"AccountNo3","ACCOUNT_NO"},
//                {"InvoiceNoCase3","INVOICE_NO"},
//                {"003","SC_NO"},
//                {"UGID","UGID"},
//                {"1003","TSN"},
//                {"9999999.99","AMOUNT"},
//                {"9999999.99","AMT1"},
//                {"MO1","MOP1"},
//                {"9999999.99","AMT2"},
//                {"MO2","MOP2"},
//                {"9999999.99","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_CSB_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"KYD","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase1","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"9999999999999.99","BALANCE_AMT"},
//                {"1100154","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"229743","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"CLP","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase2","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0.01","BALANCE_AMT"},
//                {"1100164","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"ALL","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase3","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"-800","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0","BALANCE_AMT"},
//                {"1100165","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"Y","PAID_PRE_INVOICE"}
//                };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_BAC_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }

    }
    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute14() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3",AUDIT_VALUE} };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0",AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Other1                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
        assertEquals(2, list_f_sub.size());
//        HashMap<String, Object> data = list_f_sub.get(1);
//        String[][] dataXX = new String[][]{
//                {"H","HEADER_ID"},
//                {"Other1","HEADER_FILLER"},
//                {"20110916","FILE_PROC_DATE"},
//                {"F","FOOTER_ID"},
//                {"Filler","FOOTER_FILLER"},
//                {"00000003","TOTALREC"},
//                {"19123456.89","TOTALAMT"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_DT",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110901","BUSINESS_DATE"},
//                {"AccountNo1","ACCOUNT_NO"},
//                {"InvoiceNoCase1","INVOICE_NO"},
//                {"001","SC_NO"},
//                {"UGID","UGID"},
//                {"1001","TSN"},
//                {"9123456.89","AMOUNT"},
//                {"9123456.81","AMT1"},
//                {"MO1","MOP1"},
//                {"9123456.82","AMT2"},
//                {"MO2","MOP2"},
//                {"9123456.83","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110902","BUSINESS_DATE"},
//                {"AccountNo2","ACCOUNT_NO"},
//                {"InvoiceNoCase2","INVOICE_NO"},
//                {"002","SC_NO"},
//                {"UGID","UGID"},
//                {"1002","TSN"},
//                {"0.01","AMOUNT"},
//                {"0.02","AMT1"},
//                {"MO1","MOP1"},
//                {"0.03","AMT2"},
//                {"MO2","MOP2"},
//                {"0.04","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110903","BUSINESS_DATE"},
//                {"AccountNo3","ACCOUNT_NO"},
//                {"InvoiceNoCase3","INVOICE_NO"},
//                {"003","SC_NO"},
//                {"UGID","UGID"},
//                {"1003","TSN"},
//                {"9999999.99","AMOUNT"},
//                {"9999999.99","AMT1"},
//                {"MO1","MOP1"},
//                {"9999999.99","AMT2"},
//                {"MO2","MOP2"},
//                {"9999999.99","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_CSB_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"KYD","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase1","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"9999999999999.99","BALANCE_AMT"},
//                {"1100154","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"229743","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"CLP","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase2","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0.01","BALANCE_AMT"},
//                {"1100164","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"ALL","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase3","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"-800","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0","BALANCE_AMT"},
//                {"1100165","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"Y","PAID_PRE_INVOICE"}
//                };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_BAC_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }

    }
    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute15() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE} };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    004UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
        assertEquals(2, list_f_sub.size());
//        HashMap<String, Object> data = list_f_sub.get(1);
//        String[][] dataXX = new String[][]{
//                {"H","HEADER_ID"},
//                {"Filler","HEADER_FILLER"},
//                {"20110916","FILE_PROC_DATE"},
//                {"F","FOOTER_ID"},
//                {"Filler","FOOTER_FILLER"},
//                {"00000003","TOTALREC"},
//                {"19123456.89","TOTALAMT"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_DT",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110901","BUSINESS_DATE"},
//                {"AccountNo1","ACCOUNT_NO"},
//                {"InvoiceNoCase1","INVOICE_NO"},
//                {"004","SC_NO"},
//                {"UGID","UGID"},
//                {"1001","TSN"},
//                {"9123456.89","AMOUNT"},
//                {"9123456.81","AMT1"},
//                {"MO1","MOP1"},
//                {"9123456.82","AMT2"},
//                {"MO2","MOP2"},
//                {"9123456.83","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110902","BUSINESS_DATE"},
//                {"AccountNo2","ACCOUNT_NO"},
//                {"InvoiceNoCase2","INVOICE_NO"},
//                {"002","SC_NO"},
//                {"UGID","UGID"},
//                {"1002","TSN"},
//                {"0.01","AMOUNT"},
//                {"0.02","AMT1"},
//                {"MO1","MOP1"},
//                {"0.03","AMT2"},
//                {"MO2","MOP2"},
//                {"0.04","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110903","BUSINESS_DATE"},
//                {"AccountNo3","ACCOUNT_NO"},
//                {"InvoiceNoCase3","INVOICE_NO"},
//                {"003","SC_NO"},
//                {"UGID","UGID"},
//                {"1003","TSN"},
//                {"9999999.99","AMOUNT"},
//                {"9999999.99","AMT1"},
//                {"MO1","MOP1"},
//                {"9999999.99","AMT2"},
//                {"MO2","MOP2"},
//                {"9999999.99","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_CSB_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"KYD","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase1","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"9999999999999.99","BALANCE_AMT"},
//                {"1100154","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"229743","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"CLP","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase2","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0.01","BALANCE_AMT"},
//                {"1100164","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"ALL","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase3","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"-800","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0","BALANCE_AMT"},
//                {"1100165","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"Y","PAID_PRE_INVOICE"}
//                };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_BAC_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }

    }
    
    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute16() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE} };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE} };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE} };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.889123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
        assertEquals(2, list_f_sub.size());
//        HashMap<String, Object> data = list_f_sub.get(1);
//        String[][] dataXX = new String[][]{
//                {"H","HEADER_ID"},
//                {"Filler","HEADER_FILLER"},
//                {"20110916","FILE_PROC_DATE"},
//                {"F","FOOTER_ID"},
//                {"Filler","FOOTER_FILLER"},
//                {"00000003","TOTALREC"},
//                {"19123456.89","TOTALAMT"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_DT",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110901","BUSINESS_DATE"},
//                {"AccountNo1","ACCOUNT_NO"},
//                {"InvoiceNoCase1","INVOICE_NO"},
//                {"001","SC_NO"},
//                {"UGID","UGID"},
//                {"1001","TSN"},
//                {"9123456.88","AMOUNT"},
//                {"9123456.81","AMT1"},
//                {"MO1","MOP1"},
//                {"9123456.82","AMT2"},
//                {"MO2","MOP2"},
//                {"9123456.83","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110902","BUSINESS_DATE"},
//                {"AccountNo2","ACCOUNT_NO"},
//                {"InvoiceNoCase2","INVOICE_NO"},
//                {"002","SC_NO"},
//                {"UGID","UGID"},
//                {"1002","TSN"},
//                {"0.01","AMOUNT"},
//                {"0.02","AMT1"},
//                {"MO1","MOP1"},
//                {"0.03","AMT2"},
//                {"MO2","MOP2"},
//                {"0.04","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"D","DETAIL_ID"},
//                {"20110903","BUSINESS_DATE"},
//                {"AccountNo3","ACCOUNT_NO"},
//                {"InvoiceNoCase3","INVOICE_NO"},
//                {"003","SC_NO"},
//                {"UGID","UGID"},
//                {"1003","TSN"},
//                {"9999999.99","AMOUNT"},
//                {"9999999.99","AMT1"},
//                {"MO1","MOP1"},
//                {"9999999.99","AMT2"},
//                {"MO2","MOP2"},
//                {"9999999.99","AMT3"},
//                {"MO3","MOP3"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_CSB_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"KYD","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase1","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"9999999999999.99","BALANCE_AMT"},
//                {"1100154","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"229743","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"CLP","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase2","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"0","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0.01","BALANCE_AMT"},
//                {"1100164","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"N","PAID_PRE_INVOICE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"13","ID_CUST"},
//                {"101","ID_COM_BANK"},
//                {"SP","PMT_METHOD"},
//                {"ALL","CUR_CODE"},
//                {"N","PMT_STATUS"},
//                {"InvoiceNoCase3","REFERENCE1"},
//                {"0","IS_CLOSED"},
//                {"0","IS_DELETED"},
//                {"-800","AMT_RECEIVED"},
//                {"0","BANK_CHARGE"},
//                {"0","BALANCE_AMT"},
//                {"1100165","ID_BILL_ACCOUNT"},
//                {"0","IS_EXPORT"},
//                {"Y","PAID_PRE_INVOICE"}
//                };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//
//        
//        list_f_sub = queryDAO
//        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_BAC_H",
//                null);
//        assertEquals(3, list_f_sub.size());
//        data = list_f_sub.get(0);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(1);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }
//        data = list_f_sub.get(2);
//        dataXX = new String[][]{
//                {"0","TOTAL_AMT_DUE"}
//        };
//        for (String[] dataX : dataXX) {
//            assertEquals(dataX[0],StringUtils.trim(data.get(dataX[1]).toString()));
//        }

    }

    /**
     * case errors.ERR1SC059
     * @throws Exception
     */
    public void testExecute17() throws Exception{
        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        TEST_DAY_TOMORROW, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        
        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        
        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("errors.ERR1SC059",
                (glPResult.getErrors().get().next()).getKey());
    }


    /**
     * case 1:true: in process<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute18() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "0", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE} };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE} };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE} };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.889123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("info.ERR2SC048",
                (glPResult.getMessages().get().next()).getKey());
        
        List<HashMap<String, Object>> list_f_sub = queryDAO
        .executeForObjectList("TEST.G_SGP_P02.SELECT.T_SGP_IMP_HD",
                null);
        assertEquals(2, list_f_sub.size());
    }

    /**
     * case upload file path is NULL
     * @throws Exception
     */
    public void testExecute19() throws Exception{
        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        
        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        
        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals("errors.ERR1SC056",
                (glPResult.getErrors().get().next()).getKey());
    }

    /**
     * case upload file path is NULL
     * @throws Exception
     */
    public void testExecute20() throws Exception{
        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        
        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        
        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
            .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "A2011B916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D20d109011100154   InvoiceNoCase1    001UGID1001912345a.889123456.81MO19123456.82MO29123456.83M3\r\n";
            os.write(detai.getBytes());
            detai = "C20d109011100154   InvoiceNoCase1    001UGID1001912345a.889123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D20d109011100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID1003999999d.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "C0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals(false,glPResult.getErrors().isEmpty());
    }
    
    /**
     * case input format error
     * @throws Exception
     */
    public void testExecute21() throws Exception{
        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        
        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        
        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
            .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20d1B916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D20d109011100154   InvoiceNoCase1    001UGID1001912345a.889123456.81MO19123456.82MO29123456.83M3\r\n";
            os.write(detai.getBytes());
            detai = "D20d109011100154   InvoiceNoCase1    001UGID1001912345a.889123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10010000000.000123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "C0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals(false,glPResult.getErrors().isEmpty());
    }
    
    /**
     * case upload file not txt file
     * @throws Exception
     */
    public void testExecute22() throws Exception{
        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        
        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        
        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
            .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt1");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20d1B916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals(false,glPResult.getErrors().isEmpty());
    }


    /**
     * case Duplicate
     * @throws Exception
     */
    public void testExecute23() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "InvoiceNoCase1", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("T_CSB_H", T_CSB_H1);
        super.insertData("T_CSB_H", T_CSB_H2);
        super.insertData("T_CSB_H", T_CSB_H3);
        super.insertData("T_CSB_D", T_CSB_D1);
        super.insertData("T_CSB_D", T_CSB_D2);
        super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100114   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109011100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals(false,glPResult.getErrors().isEmpty());
    }

    /**
     * case file is empty
     * @throws Exception
     */
    public void testExecute24() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                 \r\n";
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();

        assertEquals(false,glPResult.getErrors().isEmpty());
    }
    
    /**
     * case 1:test the execute method<br>
     * condition:<br>
     * get Audit Trail is null <br>
     * return:BLogicResult <br>
     * 
     * @throws Exception
     */
    public void testExecute25() throws Exception {

        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_SGP_IMP_DT");
        super.deleteAllData("T_SGP_IMP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_BATCH_LOG");
        super.deleteAllData("T_SGP_IMP_DT");

        String[][] T_SGP_IMP_HD = {
                { "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "HEADER_ID",
                        "HEADER_FILLER", "FILE_PROC_DATE", "FOOTER_ID",
                        "FOOTER_FILLER", "TOTALREC", "TOTALAMT",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "469", "469.txt", "2011/08", "2011-08-17 15:03:55", "2", "",
                        "", "", "", "", "", "", "2011-08-17 15:03:55",
                        "2011-08-17 15:03:55", "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_G_SGP_P02", "1",
                        "EOD - Data Import (SingPost Collection Data)", "0",
                        "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                        "USERFULL", "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" } };
        String[][] T_CSB_H1 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111237              ", "229743", "122", "", "GR",
                        "2011-09-09", "20110901UGID1001", "MYR", "Remark ",
                        "R", "Payment Ref 1 ", "Payment Ref 12", "0", "0",
                        "2011-09-09 18:11:25", "2011-09-13 11:13:51",
                        "sysadmin", "3332", "33", "2900", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "ddddddd", "1" } };
        String[][] T_CSB_H2 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111248              ", "229743", "0", "dsds", "CQ",
                        "2011-09-13 00:00:00", "20110902UGID1002", "MYR", "",
                        "N", "", "", "0", "0", "2011-09-13 14:46:01",
                        "2011-09-13 14:46:01", "sysadmin", "3223", "0", "3223",
                        AUDIT_VALUE, "3", "0", "0", "2011-09-13 00:00:00", "", "0" } };
        String[][] T_CSB_H3 = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
                        "REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
                        "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
                        "BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
                        "ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
                        "REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
                { "111252              ", "12", "122", "", "CC",
                        "2011-09-07 00:00:00", "20110903UGID1003", "MXN",
                        "Remark ", "N", "Credit Card No. ",
                        "Credit Card Exp.Date", "0", "0",
                        "2011-09-13 17:22:55", "2011-09-13 17:37:01",
                        "sysadmin", "3211", "111", "3211", AUDIT_VALUE, "", "0",
                        "1", "2011-09-13 00:00:00", "", "1" } };
        String[][] T_CSB_D1 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "0", "2011-09-09 18:12:20",
                        "2011-09-09 18:12:20", "sysadmin",
                        "111237              ", "606", "432", "3", "3", AUDIT_VALUE } };
        String[][] T_CSB_D2 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "0", "2011-09-13 11:41:03",
                        "2011-09-13 17:17:40", "sysadmin",
                        "111248              ", "606", "51", "1", "21", AUDIT_VALUE } };
        String[][] T_CSB_D3 = {
                { "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
                        "FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "0", "2011-09-13 12:14:41",
                        "2011-09-13 12:14:41", "sysadmin",
                        "111252              ", "475", "1", "2", "3", AUDIT_VALUE } };
        String[][] T_BIL_H1 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase1    ", "IN", "1", "1100154             ",
                        "2011-08-25 00:00:00", "", "13", "BA", "", null, null,
                        null, null, "BIF001", "", "KYD", "5", "0", "2000", "0",
                        "2011-09-13 00:00:00", "0", "0", "0", "0", "KYD", "1",
                        "0", "0", "0", "2011-09-13 00:00:00", "", "0",
                        "Testing 0001", "Test GBIL", "Test billing",
                        "45678 AU", "45678", "  Australia ", "0219999999",
                        "0219999999", "ws", "ws@admin.com", "0", "sysadmin",
                        "2011-08-25 17:29:53", "2011-09-02 16:42:50",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase2    ", "IN", "1", "1100164             ",
                        "2012-08-31 00:00:00", "", "229743", "BA", "PC", null,
                        null, null, null, "BBBBBBBBBB", "dsdaf", "CLP", "5",
                        "5", "15.82", "0", "2011-09-13 00:00:00", "0", "0",
                        "0", "0", "CLP", "1", "15.8", "0", "0",
                        "2011-09-13 00:00:00", "", "0",
                        "123 Nguyen Thi Minh Khai", "District 1",
                        "Ho Chi Minh City", "+84 VN", "+84", "  Viet Nam",
                        "0123456789", "0123456789", "Duy Duong",
                        "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                        "2011-08-31 13:44:33", "2011-08-31 13:44:52",
                        "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase3    ", "IN", "1", "1100165             ",
                        "2011-08-24 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ws1@admin.com", "0",
                        "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BIL_H4 = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                        "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                        "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                        "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                        "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                        "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                        "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                        "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "InvoiceNoCase4  ", "DN", "1", "1100165             ",
                        "2011-08-25 00:00:00", "", "13", "CA", "PC", null,
                        null, null, null, "sysadmin", "Term ", "ALL", "5", "2",
                        "0", "-800", "2011-09-13 00:00:00", "0", "0", "0", "0",
                        "ALL", "1", "8.4", "0", "0", "2011-09-13 00:00:00", "",
                        "0", "Testing 0001", "Test GBIL", "Test billing",
                        "45678   Australia ", "45678", "  Australia ",
                        "0219999999", "0219999999", "ws", "ricky@test.com",
                        "0", "sysadmin", "2011-08-24 15:48:36",
                        "2011-09-07 13:00:07", "sysadmin", AUDIT_VALUE } };
        String[][] T_BAC_H1 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100154             ", "229743", "CA", "MYR", "0", "", "BA",
                        "PC", "0", "2011-08-31 00:00:00",
                        "2011-08-31 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "9999999999999.99" } };
        String[][] T_BAC_H2 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100164             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0.01" } };
        String[][] T_BAC_H3 = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1100165             ", "229743", "BT", "MYR", "0", "", "CA",
                        "PC", "0", "2011-09-08 00:00:00",
                        "2011-09-08 00:00:00", "sysadmin", "0", AUDIT_VALUE, "1",
                        "0" } };

        super.insertData("T_SGP_IMP_HD", T_SGP_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        // super.insertData("T_CSB_H", T_CSB_H1);
        // super.insertData("T_CSB_H", T_CSB_H2);
        // super.insertData("T_CSB_H", T_CSB_H3);
        // super.insertData("T_CSB_D", T_CSB_D1);
        // super.insertData("T_CSB_D", T_CSB_D2);
        // super.insertData("T_CSB_D", T_CSB_D3);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_H", T_BIL_H4);
        super.insertData("T_BAC_H", T_BAC_H1);
        super.insertData("T_BAC_H", T_BAC_H2);
        super.insertData("T_BAC_H", T_BAC_H3);

        // paramter
        RP_E_DIM_SP1_02Input param = new RP_E_DIM_SP1_02Input();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        param.setUvo(this.uvo);
        try {
            Class parentClass = Class
                    .forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain",
                    true, "20110915.txt");
            OutputStream os = item.getOutputStream();
            String detai;
            detai = "H20110916Filler                                                                                  \r\n";
            os.write(detai.getBytes());
            detai = "D201109011100154   InvoiceNoCase1    001UGID10019123456.899123456.81MO19123456.82MO29123456.83MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109021100164   InvoiceNoCase2    002UGID10020.01      0.02      MO10.03      MO20.04      MO3\r\n";
            os.write(detai.getBytes());
            detai = "D201109031100165   InvoiceNoCase3    003UGID10039999999.999999999.99MO19999999.99MO29999999.99MO3\r\n";
            os.write(detai.getBytes());
            detai = "F0000000319123456.89 Filler                                                                      \r\n";
            os.write(detai.getBytes());
            os.close();
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            param.setFormFile(formFile);
            param.setBankAcc("101");
            param.setMonth("08");
            param.setYear("2011");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        //gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        gSgpP02.setAuditReference("");
        gSgpP02.setAuditStatus("");
        GlobalProcessResult glPResult = gSgpP02.execute(param, outputDTO);
        gSgpP02.reset();
        
        assertEquals("errors.ERR1SC074",
                (glPResult.getErrors().get().next()).getKey());
    }
}
