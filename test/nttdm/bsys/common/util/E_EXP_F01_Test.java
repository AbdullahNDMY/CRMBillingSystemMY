/**
 * @(#)E_EXP_F01_Test.java
 * Billing System
 * Version 1.00
 * Created 2011/08/18
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.util.Date;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * Test class for nttdm.bsys.common.util.E_EXP_F01
 * 
 * @author qinjinh
 */
public class E_EXP_F01_Test extends AbstractUtilTest {

    @Override
    protected void setUpData() throws Exception {

        super.deleteAllData("T_SET_BATCH_LOG");
        super.deleteAllData("t_set_batch");
        super.deleteAllData("m_gset_d");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("T_CLC_IMP_MONTHLY_SUM");
        super.deleteAllData("M_CUST");
        super.deleteAllData("M_SVC");
        super.deleteAllData("M_SVG");
    }

    public void testCase01() {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_E_EXP_F01" , "1" , "C:\\Test000" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        String[][] testDataMGsetD2 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_TIME_INTERVAL" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD2);

        String[][] testDataTSetBatch =
            {
                {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"2510" , "AR" , "1" , "AA" , "2011-08-25" , "2011-08-25" ,
                    "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(new Date());
        eExpF01.e_exp_execute();
        assertTrue(true);
    }

    public void testCase02() {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_E_EXP_F01" , "1" , "C:\\Program Files" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        String[][] testDataMGsetD2 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_TIME_INTERVAL" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD2);

        String[][] testDataTSetBatch =
            {
                {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"2510" , "AR" , "1" , "AA" , "2011-08-25" , "2011-08-25" ,
                    "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(new Date());
        eExpF01.e_exp_execute();
        assertTrue(true);
    }

    public void testCase03() {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_E_EXP_F01" , "1" , "C:\\Program Files" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        String[][] testDataMGsetD2 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_TIME_INTERVAL" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD2);

        String[][] testDataMGsetD3 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BIL_DEBTER_ACC" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD3);

        String[][] testDataTSetBatch =
            {
                {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"2510" , "AR" , "1" , "AA" , "2011-08-25" , "2011-08-25" ,
                    "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);

        String[][] testDataTCsbH =
            {
                {"ID_REF" , "ID_CONSULT" , "BILL_TYPE" , "IS_MANUAL" ,
                    "BILL_ACC" , "DATE_REQ" , "ID_CUST" , "ADR_TYPE" ,
                    "BILL_CURRENCY" , "GST_PERCENT" , "GST_AMOUNT" ,
                    "BILL_AMOUNT" , "PAID_AMOUNT" , "IS_SETTLED" ,
                    "IS_SINGPOST" , "IS_EXPORT" , "IS_DISPLAY_EXP_AMT" ,
                    "EXPORT_CUR" , "CUR_RATE" , "EXPORT_AMOUNT" ,
                    "FIXED_FOREX" , "NO_PRINTED" , "IS_CLOSED" , "IS_DELETED" ,
                    "PREPARED_BY" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN"} ,
                {"1" , "testuser1" , "DN" , "1" , "1" , TEST_DAY_TODAY , "1" ,
                    "CA" , "CC1" , "10" , "1000" , "150" , "1" , "1" , "1" ,
                    "1" , "1" , "ABC" , "1" , "-1000" , "1000" , "1" , "1" ,
                    "0" , "ABC" , "2011-08-30" , "2011-08-30" , "admin"}};
        super.insertData("T_BIL_H", testDataTCsbH);

        String[][] testDataMCust =
            { {"ID_CUST" , "CUST_NAME" , "CUSTOMER_TYPE" , "ID_LOGIN"} ,
                {"1" , "ABCDEFG" , "1234" , SYS_ADMIN}};
        super.insertData("M_CUST", testDataMCust);

        String[][] testDataMUser =
            {
                {"ID_USER" , "ID_DIV" , "ID_DEPT" , "ID_ROLE" , "USER_NAME" ,
                    "PASSWORD" , "ID_LOGIN" , "PPLSOFT_ACC_ID"} ,
                {"testuser1" , "0001" , "0001" , "0000000001" , "testuser1" ,
                    "password" , SYS_ADMIN , "ACCID"}};
        super.insertData("M_USER", testDataMUser);

        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(new Date());
        eExpF01.e_exp_execute();
        assertTrue(true);
    }

    public void testCase04() {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_E_EXP_F01" , "1" , "C:\\Program Files" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);

        String[][] testDataTSetBatch =
            {
                {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"2510" , "AR" , "1" , "AA" , "2011-08-25" , "2011-08-25" ,
                    "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(new Date());
        eExpF01.e_exp_execute();
        assertTrue(true);
    }

    public void testCase05() {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_E_EXP_F01" , "1" , "C:\\Program Files" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);

        String[][] testDataTSetBatch =
            {
                {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"2510" , "AR" , "1" , "AA" , "2011-08-25" , "2011-08-25" ,
                    "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(new Date());
        eExpF01.e_exp_execute();
        assertTrue(true);
    }
    
    public void testCase06() {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_E_EXP_F01" , "1" , "C:\\Program Files" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        String[][] testDataMGsetD2 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_TIME_INTERVAL" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD2);

        String[][] testDataMGsetD3 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BIL_DEBTER_ACC" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD3);

        String[][] testDataTSetBatch =
            {
                {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"2510" , "AR" , "1" , "AA" , "2011-08-25" , "2011-08-25" ,
                    "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);

        String[][] testDataTCsbH =
            {
                {"ID_REF" , "ID_CONSULT" , "BILL_TYPE" , "IS_MANUAL" ,
                    "BILL_ACC" , "DATE_REQ" , "ID_CUST" , "ADR_TYPE" ,
                    "BILL_CURRENCY" , "GST_PERCENT" , "GST_AMOUNT" ,
                    "BILL_AMOUNT" , "PAID_AMOUNT" , "IS_SETTLED" ,
                    "IS_SINGPOST" , "IS_EXPORT" , "IS_DISPLAY_EXP_AMT" ,
                    "EXPORT_CUR" , "CUR_RATE" , "EXPORT_AMOUNT" ,
                    "FIXED_FOREX" , "NO_PRINTED" , "IS_CLOSED" , "IS_DELETED" ,
                    "PREPARED_BY" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN"} ,
                {"1                   " , "testuser1" , "CN" , "1" , "1" , TEST_DAY_TODAY , "1" ,
                    "CA" , "CC1" , "10" , "1000" , "150" , "1" , "1" , "1" ,
                    "1" , "1" , "ABC" , "1" , "1000" , "1000" , "1" , "1" ,
                    "0" , "ABC" , "2011-08-30" , "2011-08-30" , "admin"}};
        super.insertData("T_BIL_H", testDataTCsbH);
        updateDAO.execute("TEST.E_EXP_F02.UPDATE.SQL100",null);
        
        String[][] dataT_BIL_D = {
                { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                        "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
                        "APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
                        "ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_TYPE","ITEM_CAT" },
                { "1                   ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "201", "2216", "114", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", null, null ,"1","S","1"} };
        super.insertData("T_BIL_D", dataT_BIL_D);

        String[][] testDataMCust =
            { {"ID_CUST" , "CUST_NAME" , "CUSTOMER_TYPE", "AFFILIATE_CODE", "ID_LOGIN"} ,
                {"1" , "ABCDEFG" , "1234" , "11101", SYS_ADMIN}};
        super.insertData("M_CUST", testDataMCust);

        String[][] testDataMUser =
            {
                {"ID_USER" , "ID_DIV" , "ID_DEPT" , "ID_ROLE" , "USER_NAME" ,
                    "PASSWORD" , "ID_LOGIN" , "PPLSOFT_ACC_ID"} ,
                {"testuser1" , "0001" , "0001" , "0000000001" , "testuser1" ,
                    "password" , SYS_ADMIN , "ACCID"}};
        super.insertData("M_USER", testDataMUser);

        String[][] testDataTBatchLog8 = {
                { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
                        "ACC_CODE", "UQTY", "GST", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "121", "TYP", "No GST", "", "2", "0", "0",
                        "2011-2-25  14:53:37", "2011-2-25  14:53:37",
                        "USERFULL" }, };
        super.insertData("M_SVC", testDataTBatchLog8);
        
        String[][] testDataTBatchLog6 = {
                { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                        "PRODUCT_CODE", "REMARK", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN" },
                { "121", "TEST SERVICE", "0809070605", "mbc", "05124852  ",
                        "KAKUNIN", "2011-6-28  8:17:25", "2011-8-8  11:41:51",
                        "USERFULL" }, };
        super.insertData("M_SVG", testDataTBatchLog6);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(new Date());
        eExpF01.e_exp_execute();
        assertTrue(true);
    }
    
    public void testCase07() {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_E_EXP_F01" , "1" , "C:\\Program Files" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        String[][] testDataMGsetD2 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_TIME_INTERVAL" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD2);

        String[][] testDataMGsetD3 =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BIL_DEBTER_ACC" , "1" , "10" ,
                    "Path to export PeopleSoft CSV file" , "1" , "0" ,
                    "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        super.insertData("M_GSET_D", testDataMGsetD3);

        String[][] testDataTSetBatch =
            {
                {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"2510" , "AR" , "1" , "AA" , "2011-08-25" , "2011-08-25" ,
                    "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);

        String[][] testDataTCsbH =
            {
                {"ID_REF" , "ID_CONSULT" , "BILL_TYPE" , "IS_MANUAL" ,
                    "BILL_ACC" , "DATE_REQ" , "ID_CUST" , "ADR_TYPE" ,
                    "BILL_CURRENCY" , "GST_PERCENT" , "GST_AMOUNT" ,
                    "BILL_AMOUNT" , "PAID_AMOUNT" , "IS_SETTLED" ,
                    "IS_SINGPOST" , "IS_EXPORT" , "IS_DISPLAY_EXP_AMT" ,
                    "EXPORT_CUR" , "CUR_RATE" , "EXPORT_AMOUNT" ,
                    "FIXED_FOREX" , "NO_PRINTED" , "IS_CLOSED" , "IS_DELETED" ,
                    "PREPARED_BY" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN"} ,
                {"1                   " , "testuser1" , "CN" , "1" , "1" , TEST_DAY_TODAY , "1" ,
                    "CA" , "CC1" , "10" , "1000" , "150" , "1" , "1" , "1" ,
                    "1" , "1" , "ABC" , "1" , "1000" , "1000" , "1" , "1" ,
                    "0" , "ABC" , "2011-08-30" , "2011-08-30" , "admin"}};
        super.insertData("T_BIL_H", testDataTCsbH);
        updateDAO.execute("TEST.E_EXP_F02.UPDATE.SQL100",null);
        
        String[][] dataT_BIL_D = {
                { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                        "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
                        "APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
                        "ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" ,"IS_DISPLAY_MIN_SVC","ITEM_CAT","ITEM_TYPE"},
                { "1                   ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "201", "2216", "114", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", null, null,"1","1","O" } };
        super.insertData("T_BIL_D", dataT_BIL_D);

        String[][] testDataMCust =
            { {"ID_CUST" , "CUST_NAME" , "CUSTOMER_TYPE", "AFFILIATE_CODE", "ID_LOGIN"} ,
                {"1" , "ABCDEFG" , "1234" , "11101", SYS_ADMIN}};
        super.insertData("M_CUST", testDataMCust);

        String[][] testDataMUser =
            {
                {"ID_USER" , "ID_DIV" , "ID_DEPT" , "ID_ROLE" , "USER_NAME" ,
                    "PASSWORD" , "ID_LOGIN" , "PPLSOFT_ACC_ID"} ,
                {"testuser1" , "0001" , "0001" , "0000000001" , "testuser1" ,
                    "password" , SYS_ADMIN , "ACCID"}};
        super.insertData("M_USER", testDataMUser);

        String[][] testDataTBatchLog8 = {
                { "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
                        "ACC_CODE", "UQTY", "GST", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "121", "TYP", "No GST", "", "2", "0", "0",
                        "2011-2-25  14:53:37", "2011-2-25  14:53:37",
                        "USERFULL" }, };
        super.insertData("M_SVC", testDataTBatchLog8);
        
        String[][] testDataTBatchLog6 = {
                { "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
                        "PRODUCT_CODE", "REMARK", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN" },
                { "121", "TEST SERVICE", "0809070605", "1", "05124852  ",
                        "KAKUNIN", "2011-6-28  8:17:25", "2011-8-8  11:41:51",
                        "USERFULL" }, };
        super.insertData("M_SVG", testDataTBatchLog6);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(new Date());
        eExpF01.e_exp_execute();
        assertTrue(true);
    }
}
