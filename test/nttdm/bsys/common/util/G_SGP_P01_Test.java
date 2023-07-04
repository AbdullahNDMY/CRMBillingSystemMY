package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitOutput;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

public class G_SGP_P01_Test extends AbstractUtilTest {

    private G_SGP_P01 gSgpP01;
    BillingSystemUserValueObject uvo = null;

    @Override
    protected void setUpData() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
        uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        gSgpP01 = new G_SGP_P01();
        gSgpP01.setQueryDAO(queryDAO);
        gSgpP01.setUpdateDAO(updateDAO);
        gSgpP01.setUpdateDAONuked(updateDAONuked);
    }

    /**
     * Case 1: base
     */
    public void testCase01() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", null, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", null, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                null, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                null, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                null, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                null, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                null } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                null, "1", "0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                null, "1", "0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                null, "1", "0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                null, "1", "0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                null, "1", "0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                null } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                null } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                null } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                null } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", null } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", null } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                null } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                null } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 2: base
     * STATUS = 0
     */
    public void testCase02() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "0", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", null, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", null, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                null, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                null, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                null, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                null, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                null } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                null, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null} };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                null } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                null } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                null } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                null } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", null } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", null } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                null } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                null } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 3: base
     */
    public void testCase03() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "6000", null, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", null, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                null, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                null, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                null, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                null, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                null } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                null, "1", "0", "1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                null, "1", "0", "1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                null, "1", "0", "1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                null, "1", "0", "1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                null, "1", "0", "1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                null, "1", "0", "1"} };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                null } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                null } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                null } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                null } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", null } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", null } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                null } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                null } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        
        param1.put("ID_SGP_EXP_BATCH", "293");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_SGP_EXP_HD", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("errors.ERR1SC059",
            (glPResult.getErrors().get().next()).getKey());
        
    }
    /**
     * Case 4: base
     */
    public void testCase04() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "0", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", null, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "X:\\", null, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                null, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                null, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                null, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                null, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                null } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                null, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                null } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                null } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                null } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                null } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", null } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", null } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                null } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                null } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        
        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("errors.ERR1SC056",
            (glPResult.getErrors().get().next()).getKey());
        
    }
    /**
     * Case 1: base
     */
    public void testCase05() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", null, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", null, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                null, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                null, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                null, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                null, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                null } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", null } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                null, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                null, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", null, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", null} };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                null } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                null } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                null} };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                null } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", null } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                null } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                null } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", null} };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                null } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                null } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        
        File file =new File("C:\\INVOICE_092011.txt");
        file.delete();

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
    }    /**
     * Case 11: base
     * Login ID null
     */
    public void testCase06() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_092011.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", null, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", null, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                null, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE} };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE} };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE} };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        //super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        gSgpP01.setExportPath("X:^&**(&(*");
        gSgpP01.setFilename("*()*(*()");
        Method m = gSgpP01.getClass().getDeclaredMethod("createFile",
            new Class[] { });
        m.setAccessible(true);
        m.invoke(gSgpP01, new Object[] { });
        m.setAccessible(false);
        assertEquals("errors.ERR1SC058",
            (glPResult.getErrors().get().next()).getKey());
    }
    /**
     * Case 4: base
     */
    public void testCase07() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "0", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        //super.insertData("T_BIL_H", T_BIL_H1);
        //super.insertData("T_BIL_H", T_BIL_H2);
        //super.insertData("T_BIL_H", T_BIL_H3);
        //super.insertData("T_BIL_D", T_BIL_D1);
        //super.insertData("T_BIL_D", T_BIL_D2);
        //super.insertData("T_BIL_D", T_BIL_D3);
        //super.insertData("T_BIL_D", T_BIL_D4);
        //super.insertData("T_BIL_D", T_BIL_D5);
        //super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        
        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("errors.ERR1SC052",
            (glPResult.getErrors().get().next()).getKey());
        
    }
    /**
     * Case 11: base
     * Login ID null
     */
    public void testCase08() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_092011.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        //super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        param1.put("idRef", "11000770");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.t_bil_h", param1);
        param1.put("idRef", "11000776");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.t_bil_h", param1);
        param1.put("idRef", "11000779");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.t_bil_h", param1);
        BsysBufferedWriter out = null;
        Method m = gSgpP01.getClass().getDeclaredMethod("exportFile",
            new Class[] { String.class, BsysBufferedWriter.class });
        m.setAccessible(true);
        m.invoke(gSgpP01, new Object[] { "sysadmin" , out });
        m.setAccessible(false);
        assertEquals("errors.ERR1SC065",
            (glPResult.getErrors().get().next()).getKey());
    }
    /**
     * Case 11: base
     * Login ID null
     */
    public void testCase09() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_092011.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        //super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        param1.put("idRef", "11000770");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.t_bil_h", param1);
        param1.put("idRef", "11000776");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.t_bil_h", param1);
        param1.put("idRef", "11000779");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.t_bil_h", param1);
        // declare a object for mock interface
        Mockery mockImpl = new Mockery();

        // declare a object for mock class
        Mockery mockClass = new JUnit4Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
        
        // declare a G_SGP_P02 object by mock
        final UpdateDAO updateDAO1 = mockClass.mock(UpdateDAO.class);
        gSgpP01.setUpdateDAO(updateDAO1);
        // set g_sgp_p02's expect method
        mockClass.checking(new Expectations() {
            {
                oneOf(updateDAO1).execute(with(any(String.class)),
                        with(any(Object.class)));
                will(throwException(new Exception("error")));
                //will(returnValue(1));
            }
        });
        mockClass.checking(new Expectations() {
            {
                oneOf(updateDAO1).execute(with(any(String.class)),
                        with(any(Object.class)));
                //will(throwException(new Exception("error")));
                will(returnValue(1));
            }
        });
        
        BsysBufferedWriter out = new BsysBufferedWriter(new FileWriter(gSgpP01.getExportPath() + gSgpP01.getFilename()));
        Method m = gSgpP01.getClass().getDeclaredMethod("exportFile",
            new Class[] { String.class, BsysBufferedWriter.class });
        m.setAccessible(true);
        m.invoke(gSgpP01, new Object[] { "sysadmin" , out });
        m.setAccessible(false);
        assertEquals("errors.ERR1SC060",
            (glPResult.getErrors().get().next()).getKey());
    }
    /**
     * Case 1: base
     */
    public void testCase10() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS",  "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2",  "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(false);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals(true,
            glPResult.getMessages().isEmpty());
        
        BufferedReader file = null;
        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 11: base
     * FILENAME -> INVOICE_092011.txt
     */
    public void testCase11() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_092011.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
    }
    /**
     * Case 11: base
     * M_CUST no data
     */
    public void testCase12() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_092011.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE} };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        //super.insertData("M_CUST", M_CUST1);
        //super.insertData("M_CUST", M_CUST2);
        //super.insertData("M_CUST", M_CUST3);
        //super.insertData("M_CUST_CTC", M_CUST_CTC1);
        //super.insertData("M_CUST_CTC", M_CUST_CTC2);
        //super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
    }
    /**
     * Case 11: base
     * Login ID null
     */
    public void testCase13() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_092011.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        // declare a object for mock interface
        Mockery mockImpl = new Mockery();

        // declare a object for mock class
        Mockery mockClass = new JUnit4Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
        // declare a G_SGP_P02 object by mock
        final BsysBufferedWriter out1 = mockClass.mock(BsysBufferedWriter.class);
        // set g_sgp_p02's expect method
        mockClass.checking(new Expectations() {
            {
                oneOf(out1).close();
                will(throwException(new IOException("error")));
                //will(returnValue(1));
            }
        });
        Method m = gSgpP01.getClass().getDeclaredMethod("exportFile",
            new Class[] { String.class , BsysBufferedWriter.class });
        m.setAccessible(true);
        m.invoke(gSgpP01, new Object[] { "sysadmin" , out1 });
        m.setAccessible(false);
        
    }
    /**
     * Case 1: base
     */
    public void testCase14() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS",  "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase15() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE} };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase16() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE} };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase17() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC",AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE} };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE} };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE} };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE} };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase18() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase19() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0",AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE} };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase20() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE} };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE} };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE} };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE} };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE} };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase21() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC",AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE } };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase22() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE} };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE} };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE} };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE} };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE} };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        //super.insertData("T_BIL_D", T_BIL_D1);
        //super.insertData("T_BIL_D", T_BIL_D2);
        //super.insertData("T_BIL_D", T_BIL_D3);
        //super.insertData("T_BIL_D", T_BIL_D4);
        //super.insertData("T_BIL_D", T_BIL_D5);
        //super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
//        HashMap<String, Object> param1 = new HashMap<String, Object>();
//        param1.put("ID_REF", "11000770");
//        param1.put("ITEM_ID", "1");
//        param1.put("ITEM_DESC", "1");
//        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
//        param1.put("ID_REF", "11000770");
//        param1.put("ITEM_ID", "2");
//        param1.put("ITEM_DESC", "2");
//        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
//        param1.put("ID_REF", "11000770");
//        param1.put("ITEM_ID", "3");
//        param1.put("ITEM_DESC", "3");
//        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
//        param1.put("ID_REF", "11000776");
//        param1.put("ITEM_ID", "1");
//        param1.put("ITEM_DESC", "4");
//        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
//        param1.put("ID_REF", "11000776");
//        param1.put("ITEM_ID", "2");
//        param1.put("ITEM_DESC", "5");
//        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
//        param1.put("ID_REF", "11000779");
//        param1.put("ITEM_ID", "1");
//        param1.put("ITEM_DESC", "6");
//        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                999999999902/09/2011TYPE1                         3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/00                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         3         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    /**
     * Case 1: base
     */
    public void testCase23() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0",AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", null, null,
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE} };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01
            .setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        param.setDeductionDate("33");
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("info.ERR2SC029",
            (glPResult.getMessages().get().next()).getKey());
        
//        BufferedReader file = null;
//        String currentRecord = "";
//        try {
//            file = new BufferedReader(new FileReader("C:\\INVOICE_092011.txt"));
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11001      0229741    0123456789          CORP Duy Duong                                                   Duy Duong                     Testing 0001                                      Test GBIL                                         Test billing                                      Australia                     45678          11000770          01/09/201115/09/2011                                                                9999999999-         -                             3456789.0104/09/2011Paid Pre Invoice1             PL-206-I0001-I0024                                963852.12 741852.96 -111110.16852963.12 741852.96 456369.85 SP                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            //assertEquals(currentRecord, "22  11000770                                                            1                                                                                                                               2                                                                                                                               802    Charge From 01/02/2011 To 02/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      2229742    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000776          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 CC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "21  11000776                                                                                                                                                                                            4                                                                                                                               804    Charge From 03/02/2011 To 04/04/2011                                                                                                                                              22         22     484       ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "11002      1229743    1002                CONS Duy Duong                                                   Duy Duong                     123 Nguyen Thi Minh Khai                          District 1                                        Ho Chi Minh City                                  Viet Nam                      +84            11000779          30/09/201114/10/2011                                                                0.01      03/09/2011TYPE2                         3456789.0205/09/2011Paid Pre Invoice2             PL-206-I0001-I0025                                963852.13 852963.41 222728.16 741123.96 963852.12 741456.89 GR2011/09/30                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
//            currentRecord = file.readLine();
//            assertEquals(currentRecord, "33         5         ");
//        } catch (IOException e) {
//            fail("file read error!!!");
//        } finally {
//            if (file != null) {
//                file.close();
//            }
//        }
    }
    
    /**
     * Case 1: Audit Trail is null
     */
    public void testCase24() throws Exception {
        // delete data
        super.deleteAllData("T_SGP_EXP_HD");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST_CTC");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BIL_S");
        super.deleteAllData("T_BATCH_SCH");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
        super.deleteAllData("T_BATCH_LOG");

        String[][] T_SGP_EXP_HD = {
            { "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                "DATE_UPLOADED", "STATUS", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN" },
            { "293","INVOICE_082011_298.txt", "08/2011", "2011-08-23 18:12:00",
                "2", "2011-08-23 18:12:00", "2011-08-24 14:13:23",
                "sysadmin" } };
        String[][] M_GSET_D1 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_TIME_INTERVAL",
                "1",
                "The number of hours before the subsequent batch can be executed",
                "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44", "sysadmin",
                "2", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D2 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            {
                "BATCH_G_SGP_P01",
                "1",
                "Process to generate and export SingPost and update Collection Data",
                "0", "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL",
                "C:\\", AUDIT_VALUE, "1" } };
        String[][] M_GSET_D3 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "1", "This message will use in ", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "SP",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D4 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "2", "This message will use in (GIRO)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "GR",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D5 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "BATCH_MSG", "3", "This message will use in (Citibank)", "0",
                "2010-11-24 00:00:00", "2011-07-13 11:00:34", "USERFULL", "CC",
                AUDIT_VALUE, "1" } };
        String[][] M_GSET_D6 = {
            { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "SET_VALUE", "ID_AUDIT",
                "SET_APPLY" },
            { "INVOICE_DUE_PERIOD", "1", "By default 14 days", "0",
                "2010-11-24 16:50:44", "2011-07-13 11:00:34", "USERFULL", "14",
                AUDIT_VALUE, "1" } };
        String[][] T_BIL_H1 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "IN", "1", "1001", "2011-09-01 00:00:00", "SP",
                "229741", "BA", "PC", null, null, null, null, "BIF001", "",
                "KYD", "5", "852963.12", "741852.96", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "KYD", "1", "0",
                "0", "0", null, "", "0", "Testing 0001", "Test GBIL",
                "Test billing", "45678 AU", "45678", "Australia", "0123456789",
                "0123456789", "Duy Duong", "duong.nguyen@nttdata.com.vn", "0",
                "sysadmin", "2011-08-25 17:29:53", "2011-09-16 14:05:23", "1",
                AUDIT_VALUE } };
        String[][] T_BIL_H2 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "IN", "1", "1002", "2011-09-30 00:00:00", "CC",
                "229742", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_H3 = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE", "ID_BIF",
                "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT", "TERM",
                "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT", "BILL_AMOUNT",
                "PAID_AMOUNT", "LAST_PAYMENT_DATE", "IS_SETTLED",
                "IS_SINGPOST", "IS_EXPORT", "IS_DISPLAY_EXP_AMT", "EXPORT_CUR",
                "CUR_RATE", "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED", "ADDRESS1",
                "ADDRESS2", "ADDRESS3", "ADDRESS4", "ZIP_CODE", "COUNTRY",
                "TEL_NO", "FAX_NO", "CONTACT_NAME", "CONTACT_EMAIL",
                "IS_DELETED", "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "IN", "1", "1002", "2011-09-30 00:00:00", "GR",
                "229743", "BA", "PC", null, null, null, null, "BBBBBBBBBB",
                "dsdaf", "CLP", "5", "741123.96", "963852.12", "0",
                "2011-09-16 14:05:23", "0", "1", "0", "0", "CLP", "1", "15.8",
                "0", "0", null, "", "0", "123 Nguyen Thi Minh Khai",
                "District 1", "Ho Chi Minh City", "+84 VN", "+84", "Viet Nam",
                "0123456789", "0123456789", "Duy Duong",
                "duong.nguyen@nttdata.com.vn", "0", "sysadmin",
                "2011-08-31 13:44:33", "2011-09-16 14:05:23", "1", AUDIT_VALUE } };
        String[][] T_BIL_D1 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "1", "0", "333", null, "3", "3.6", "10.8", "5", "1",
                "1", "801", "999999999", "999999999", "359", "130",
                "2011-01-31 00:00:00", "2011-04-01 00:00:00", "1", "0",
                "2011-08-31 13:44:33", "2011-08-31 13:44:33", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D2 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "2", "1", "2", null, "22", "22", "484", "1", "0", "1",
                "802", "999999999", "999999999", "360", "131",
                "2011-02-01 00:00:00", "2011-04-02 00:00:00", "1", "0",
                "2011-01-04 16:17:22", "2011-08-29 13:34:00", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D3 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000770", "3", "0", "1", null, "1", "1", "1", "3", "1", "1",
                "803", "999999999", "999999999", "335", "132",
                "2011-02-02 00:00:00", "2011-04-03 00:00:00", "1", "0",
                "2011-08-24 16:30:14", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D4 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "1", "1", "2", null, "22", "22", "484", "7", "1", "1",
                "804", "999999999", "999999999", "338", "133",
                "2011-02-03 00:00:00", "2011-04-04 00:00:00", "1", "0",
                "2011-08-24 16:30:31", "2011-08-25 17:03:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D5 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000776", "2", "0", "1", null, "2", "2", "3500", "2", "1", "1",
                "805", "999999999", "999999999", "339", "134",
                "2011-02-04 00:00:00", "2011-04-05 00:00:00", "1", "0",
                "2011-08-25 09:34:11", "2011-08-25 09:34:11", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] T_BIL_D6 = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST", "APPLY_GST",
                "IS_DISPLAY", "ID_CUST_PLAN", "ID_CUST_PLAN_GRP",
                "ID_CUST_PLAN_LINK", "SVC_LEVEL1", "SVC_LEVEL2", "BILL_FROM",
                "BILL_TO", "JOB_NO", "IS_DELETED", "DATE_CREATED",
                "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "11000779", "1", "1", "", null, "0", "0", "0", "0", "0", "1",
                "806", "999999999", "999999999", "340", "135",
                "2011-02-05 00:00:00", "2011-04-06 00:00:00", "1", "0",
                "2011-08-25 14:32:01", "2011-08-25 14:32:01", "sysadmin",
                AUDIT_VALUE, "1","0","1" } };
        String[][] M_CUST1 = {
            { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229741","0123456789", "CORP Duy Duong ", "0123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 15:01:59", "2011-09-13 15:27:53", "sysadmin", "1",
                "1001", null, "1", "1", "0", "0123456789", "0123456789", "CORP",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST2 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229742", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST3 = {
            { "ID_CUST","CUST_ACC_NO", "CUST_NAME", "CO_REGNO", "CO_WEBSITE", "CO_EMAIL",
                "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", "IS_EXPORTED",
                "IS_DELETED", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO",
                "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO",
                "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID",
                "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
            { "229743", "0123456789", "CONS Duy Duong ", "1123456789",
                "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn",
                "0123456789", "0123456789", "0", "0", "0",
                "2011-07-07 00:00:00", "2011-07-07 00:00:00", "USERFULL", "1",
                "1002", null, "1", "1", "0", "0123456789", "0123456789", "CONS",
                "0123456789", "ABC", AUDIT_VALUE, "" } };
        String[][] M_CUST_CTC1 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229741", "PC", "A Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC2 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229742", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0", AUDIT_VALUE } };
        String[][] M_CUST_CTC3 = {
            { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
                "ID_AUDIT" },
            { "229743", "PC", "B Duy Duong", "0123456789",
                "duong.nguyen@nttdata.com.vn", "0123456789", "0123456789",
                "0123456789", "0123456789", "2011-07-07 15:01:59",
                "2011-09-13 15:27:53", "sysadmin", "0",AUDIT_VALUE } };
        String[][] T_BIL_S1 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000770", "9999999999999.99", "TYPE1", "2011-09-02 00:00:00",
                "3456789.01", "Paid Pre Invoice1", "PL-206-I0001-I0024",
                "2011-09-04 00:00:00", "963852.12", "741852.96", "456369.85",
                "0", "2011-09-22 13:47:31", "2011-09-01 00:00:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S2 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000776", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:46", "2011-08-26 16:34:22", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BIL_S3 = {
            { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "11000779", "0.01", "TYPE2", "2011-09-03 00:00:00", "3456789.02",
                "Paid Pre Invoice2", "PL-206-I0001-I0025",
                "2011-09-05 00:00:00", "963852.13", "852963.41", "741456.89",
                "0", "2011-09-13 10:55:54", "2011-09-06 10:52:34", "sysadmin",
                AUDIT_VALUE } };
        String[][] T_BATCH_SCH = {
            { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                "ID_AUDIT" },
            { "G_GIR_P01", "1", "0", "0", "0", "19", "0",
                "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                AUDIT_VALUE } };
        String[][] M_SVG1 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "266", "NTTi-net", "234N", "3", "5555      ", "X1",
                "2011-04-18 10:20:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG2 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
                "KAKUNIN", "2011-06-28 08:17:25", "2011-09-08 09:17:00",
                "sysadmin", AUDIT_VALUE} };
        String[][] M_SVG3 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "121", "ISP", "11220012", "2", "123456    ", "X2",
                "2010-11-19 11:39:14", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVG4 = {
            { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                "PRODUCT_CODE", "REMARK", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "267", "xyz", "12345", "2", "1212123   ", "X3",
                "2011-04-18 13:47:15", "2011-09-08 09:17:00", "sysadmin",
                AUDIT_VALUE } };
        String[][] M_SVC1 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "359", "266", "ITM", "kk", "123FG", "0", "0", "0",
                "2011-07-14 11:49:22", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC2 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "360", "268", "DTL", "Ale", "0984NN", "0", "1", "0",
                "2011-07-14 13:09:14", "2011-07-15 10:22:02", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC3 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "335", "121", "TYP", "ddddd", "42", "3", "1", "1",
                "2011-03-29 16:14:37", "2011-03-29 16:22:54", "USERFULL", AUDIT_VALUE } };
        String[][] M_SVC4 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "338", "267", "TYP", "aaaaaaaaaa", "bbbbbbbbb", "4", "1", "1",
                "2011-04-11 14:18:00", "2011-04-11 14:18:05", "USERFULL",
                AUDIT_VALUE } };
        String[][] M_SVC5 = {
            { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC", "ACC_CODE",
                "UQTY", "GST", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                "ID_LOGIN", "ID_AUDIT" },
            { "339", "121", "TYP", "Rack Rental 1", "408010001", "0", "0", "0",
                "2011-04-11 14:18:05", "2011-04-21 13:21:49", "ling.lee",
                AUDIT_VALUE } };

        super.insertData("T_SGP_EXP_HD", T_SGP_EXP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("M_GSET_D", M_GSET_D2);
        super.insertData("M_GSET_D", M_GSET_D3);
        super.insertData("M_GSET_D", M_GSET_D4);
        super.insertData("M_GSET_D", M_GSET_D5);
        super.insertData("M_GSET_D", M_GSET_D6);
        super.insertData("T_BIL_H", T_BIL_H1);
        super.insertData("T_BIL_H", T_BIL_H2);
        super.insertData("T_BIL_H", T_BIL_H3);
        super.insertData("T_BIL_D", T_BIL_D1);
        super.insertData("T_BIL_D", T_BIL_D2);
        super.insertData("T_BIL_D", T_BIL_D3);
        super.insertData("T_BIL_D", T_BIL_D4);
        super.insertData("T_BIL_D", T_BIL_D5);
        super.insertData("T_BIL_D", T_BIL_D6);
        super.insertData("M_CUST", M_CUST1);
        super.insertData("M_CUST", M_CUST2);
        super.insertData("M_CUST", M_CUST3);
        super.insertData("M_CUST_CTC", M_CUST_CTC1);
        super.insertData("M_CUST_CTC", M_CUST_CTC2);
        super.insertData("M_CUST_CTC", M_CUST_CTC3);
        super.insertData("T_BIL_S", T_BIL_S1);
        super.insertData("T_BIL_S", T_BIL_S2);
        super.insertData("T_BIL_S", T_BIL_S3);
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        super.insertData("M_SVG", M_SVG1);
        super.insertData("M_SVG", M_SVG2);
        super.insertData("M_SVG", M_SVG3);
        super.insertData("M_SVG", M_SVG4);
        super.insertData("M_SVC", M_SVC1);
        super.insertData("M_SVC", M_SVC2);
        super.insertData("M_SVC", M_SVC3);
        super.insertData("M_SVC", M_SVC4);
        super.insertData("M_SVC", M_SVC5);
        
        HashMap<String, Object> param1 = new HashMap<String, Object>();
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "1");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "2");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000770");
        param1.put("ITEM_ID", "3");
        param1.put("ITEM_DESC", "3");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "4");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000776");
        param1.put("ITEM_ID", "2");
        param1.put("ITEM_DESC", "5");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);
        param1.put("ID_REF", "11000779");
        param1.put("ITEM_ID", "1");
        param1.put("ITEM_DESC", "6");
        super.updateDAO.execute("TEST.G_SGP_P01.UPDATE.T_BIL_D", param1);

        RP_E_MEX_SP1SubmitInput param = new RP_E_MEX_SP1SubmitInput();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();
        gSgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP01.setAuditReference("");
        gSgpP01.setAuditStatus("");
        param.setClosingMonth("09");
        param.setClosingYear("2011");
        param.setScr(true);
        param.setUvo(this.uvo);
        GlobalProcessResult glPResult = gSgpP01.execute(param, outputDTO);
        
        assertEquals("errors.ERR1SC074",
            (glPResult.getErrors().get().next()).getKey());
    }

}
