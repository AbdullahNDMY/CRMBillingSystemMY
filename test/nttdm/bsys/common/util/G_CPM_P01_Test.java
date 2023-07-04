/**
 * @(#)G_CPM_P01_Test.java
 * Billing System
 * Version 1.00
 * Created 2011/07/28
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All
 * Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import nttdm.bsys.common.util.dto.G_CPM_P01Input;

/**
 * Test Class for nttdm.bsys.common.util.G_CPM_P01
 * 
 * @author qinjinh
 */
public class G_CPM_P01_Test extends AbstractUtilTest {

    // gCpmP01
    private G_CPM_P01 gCpmP01 = null;
    /**

	/*
     * (non-Javadoc)
     * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
     */
    @Override
    protected void setUpData() {

        // initialize queryDAO and updateDAO
        gCpmP01 = new G_CPM_P01(queryDAO, updateDAO, "sysadmin");
        // delete all exist data in m_alt_h and m_alt_d
        //super.deleteAllData("T_SUBSCRIPTION_INFO");
        //super.deleteAllData("T_MAIL_ADDRESS");
        //super.deleteAllData("T_RACK_POWER");
        //super.deleteAllData("M_CUST");

        super.deleteAllData("T_BIF_H");
        super.deleteAllData("T_BIF_D");
        super.deleteAllData("T_CUST_PLAN_SVC");
        super.deleteAllData("T_CUST_PLAN_LINK");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("T_IT_CONTACT");
        super.deleteAllData("T_CUST_PLAN_D");
        super.deleteAllData("T_CUST_PLAN_H");

        String[][] testDataMCust =
            {
                {"ID_CUST" , "CUST_ACC_NO" , "CUST_NAME" , "CO_REGNO" ,
                    "CO_WEBSITE" , "CO_EMAIL" , "CO_TEL_NO" , "CO_FAX_NO" ,
                    "INTER_COMP" , "IS_EXPORTED" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" ,
                    "SALUTATION" , "CUST_ID_NO" , "CUST_BIRTH_DATE" ,
                    "HOME_TEL_NO" , "HOME_FAX_NO" , "PRINT_STATEMENT" ,
                    "GBOC_ACC_NO" , "OTHERS_REF_NO" , "CUSTOMER_TYPE" ,
                    "SALES_FORCE_ACC_ID" , "AFFILIATE_CODE" , "ID_AUDIT"} ,
                {"5116" , "" , "jlxtest01" , "" , "" , "" , "" , "" , "0" ,
                    "0" , "0" , "2011-08-02" , "2011-08-02" , "SYSJLX" , "" ,
                    "test01" , "1980-08-06" , "" , "" , "0" , "" , "" , "CONS" , "" ,
                    "" , null}};
        super.insertData("M_CUST", testDataMCust);

    }

    // New Test Case
    /**
     * Case 1:
     * Condition: <br>
     * common param:
     * $ID_CUST_PLAN="1"
     * $PAYMENT_METHOD="SP"
     * $IS_DISPLAY_EXP_AMT=0
     * $EXPORT_CURRENCY="SGP"
     * $FIXED_FOREX=2000.0000
     * $ID_REF="001"
     * screenName = B_BIF_S02s
     * T_CUST_PLAN_D 1:
     * Result: true
     */
    public void testExecute01() {

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         * $PAYMENT_METHOD="SP"
         * $IS_DISPLAY_EXP_AMT=0
         * $EXPORT_CURRENCY="SGP"
         * $FIXED_FOREX=2000.0000
         * $ID_REF="001"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_login("sysadmin");
        input.setId_cust_plan("1");
        input.setPayment_method("SP");
        input.setIs_display_exp_amt("0");
        input.setExport_currency("SGP");
        input.setFixed_forex("2000");
        input.setId_bif("001                 ");
        input.setId_cust_plan_grp("1");

        /**
         * 【Table "T_BIF_H" has one record in accordance with
         * ID_REF="001"】Has the record
         */
        String[][] testDataTBifH =
            {
                {"ID_REF" , "ID_CUST" , "ID_CONSLT" , "ID_QCS" , "BIF_TYPE" ,
                    "ADR_TYPE" , "CONTACT_NAME" , "REQ_USER" , "DATE_REQ" ,
                    "CUST_PO" , "JOB_NO" , "CUST_MODE" , "CTTERM" ,
                    "CTTERM3_DAY" , "DELIVERY" , "CN_TYPE" , "CN_TYPE6" ,
                    "REMARKS" , "ID_STATUS" , "IS_CLOSED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "BIL_OPBY" , "DATE_BIFRCV" ,
                    "BIL_REFNO" , "DATE_PRINTED" , "DATE_SIGNED" , "BIL_IDC" ,
                    "BIL_GIN" , "BIL_SI" , "BIL_COR" , "BIL_OTH" ,
                    "IS_DELETED" , "USER_COMMENT" , "EXPORT_CUR" , "BILL_CUR" ,
                    "ID_QUO" , "TEL_NO" , "ADDRESS1" , "ADDRESS2" ,
                    "ADDRESS3" , "ADDRESS4" , "FAX_NO" , "ID_AUDIT"} ,
                {"001                 " , "5116" , "sysadmin" , "idQcsTest" ,
                    "IN" , "BA" , "" , "SYSJLX" , "2011-07-28" , "custPoTest" ,
                    "201107281527" , "" , "2" , "30 Days" , "1" , "" , "" ,
                    "" , "DS3" , "0" , "2011-07-28" , "2011-07-28" ,
                    "vincentlee" , "sysadmin" , "2011-07-28" , "" ,
                    "2011-07-28" , "2011-07-28" , "0" , "0" , "0" , "0" , "0" ,
                    "0" , "" , "USD" , "MYR" , "idQuoTest" , "" , "" , "" ,
                    "" , "" , "" , null}};
        super.insertData("T_BIF_H", testDataTBifH);

        /**
         * Table "T_BIF_D" has one record in accordance with
         * ID_REF="001"
         * ID_CUST_PLAN=1
         */
        String[][] testDataTBifD =
            {
                {"ID_REF" , "ID_CUST_PLAN" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "IS_DELETED" , "ID_AUDIT"} ,
                {"001                 " , "1" , "2011-07-28" , "2011-07-28" ,
                    "SYSJLX" , "0" , null}};
        super.insertData("T_BIF_D", testDataTBifD);
        /**
         * Table "T_CUST_PLAY_H" has one record in accordance with
         * ID_CUST_PLAN=1
         * PAYMENT_METHOD="BT"
         * IS_DISPLAY_EXT_AMT=1
         * EXPORT_CURRENCY="USD"
         * FIXED_FOREX=1000.0000
         * ID_BILL_ACCOUNT="NEW"
         */
        /**
        
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "NEW" , "0" ,
                    "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" , "1" ,
                    "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
        // 【Before jumping to the page】B_BIF_S02s
        boolean result = gCpmP01.execute(input, "B_BIF_S02s").booleanValue();

        assertEquals(result, true);
        /**
         * $PAYMENT_METHOD="BT"
         * $IS_DISPLAY_EXP_AMT=1
         * $EXPORT_CURRENCY="USD"
         * $FIXED_FOREX=1000.0000
         */
        assertEquals(input.getPayment_method(), "BT");
        assertEquals(input.getIs_display_exp_amt(), "1");
        assertEquals(input.getExport_currency(), "USD");
        assertEquals(
            new BigDecimal(input.getFixed_forex()).compareTo(new BigDecimal(
                "1000")) == 0, true);
        
        gCpmP01.setQueryDAO(queryDAO);
        gCpmP01.getQueryDAO();
        gCpmP01.setUpdateDAO(updateDAO);
        gCpmP01.getUpdateDAO();
        gCpmP01.getId_user();
        gCpmP01.setId_user("");
        gCpmP01 = new G_CPM_P01(queryDAO, updateDAO,queryDAO1, updateDAO1, "");
    }

    /**
     * Case 2:
     * Condition: <br>
     * common param: //TODO;
     * screenName = B_BIF_S02s
     * Result: true
     */
    public void testExecute02() {

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         * $PAYMENT_METHOD="SP"
         * $IS_DISPLAY_EXP_AMT=0
         * $EXPORT_CURRENCY="SGP"
         * $FIXED_FOREX=2000.0000
         * $ID_REF="001"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_cust_plan("1");
        input.setPayment_method("SP");
        input.setIs_display_exp_amt("0");
        input.setExport_currency("SGP");
        input.setFixed_forex("2000.0000");
        input.setId_bif("001                 ");
        /**
         * 【Table "T_BIF_H" has one record in accordance with
         * ID_REF="001"】Has the record
         */
        String[][] testDataTBifH =
            {
                {"ID_REF" , "ID_CUST" , "ID_CONSLT" , "ID_QCS" , "BIF_TYPE" ,
                    "ADR_TYPE" , "CONTACT_NAME" , "REQ_USER" , "DATE_REQ" ,
                    "CUST_PO" , "JOB_NO" , "CUST_MODE" , "CTTERM" ,
                    "CTTERM3_DAY" , "DELIVERY" , "CN_TYPE" , "CN_TYPE6" ,
                    "REMARKS" , "ID_STATUS" , "IS_CLOSED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "BIL_OPBY" , "DATE_BIFRCV" ,
                    "BIL_REFNO" , "DATE_PRINTED" , "DATE_SIGNED" , "BIL_IDC" ,
                    "BIL_GIN" , "BIL_SI" , "BIL_COR" , "BIL_OTH" ,
                    "IS_DELETED" , "USER_COMMENT" , "EXPORT_CUR" , "BILL_CUR" ,
                    "ID_QUO" , "TEL_NO" , "ADDRESS1" , "ADDRESS2" ,
                    "ADDRESS3" , "ADDRESS4" , "FAX_NO" , "ID_AUDIT"} ,
                {"001                 " , "5116" , "sysadmin" , "idQcsTest" ,
                    "IN" , "BA" , "" , "SYSJLX" , "2011-07-28" , "custPoTest" ,
                    "201107281527" , "" , "2" , "30 Days" , "1" , "" , "" ,
                    "" , "DS3" , "0" , "2011-07-28" , "2011-07-28" ,
                    "vincentlee" , "sysadmin" , "2011-07-28" , "" ,
                    "2011-07-28" , "2011-07-28" , "0" , "0" , "0" , "0" , "0" ,
                    "0" , "" , "USD" , "MYR" , "idQuoTest" , "" , "" , "" ,
                    "" , "" , "" , null}};
        super.insertData("T_BIF_H", testDataTBifH);

        /**
         * Table "T_BIF_D" has one record in accordance with
         * ID_REF="001"
         * ID_CUST_PLAN=1
         */
        String[][] testDataTBifD =
            {
                {"ID_REF" , "ID_CUST_PLAN" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "IS_DELETED" , "ID_AUDIT"} ,
                {"001                 " , "1" , "2011-07-28" , "2011-07-28" ,
                    "SYSJLX" , "0" , null}};
        super.insertData("T_BIF_D", testDataTBifD);

        // 【Before jumping to the page】B_BIF_S02s
        boolean result = gCpmP01.execute(input, "B_BIF_S02s").booleanValue();

        assertEquals(result, false);
        /**
         * 【Variable】
         * $PAYMENT_METHOD="SP"
         * $IS_DISPLAY_EXP_AMT=0
         * $EXPORT_CURRENCY="SGP"
         * $FIXED_FOREX=2000.0000
         */
        assertEquals(input.getPayment_method(), "SP");
        assertEquals(input.getIs_display_exp_amt(), "0");
        assertEquals(input.getExport_currency(), "SGP");
        assertEquals(
            new BigDecimal(input.getFixed_forex()).compareTo(new BigDecimal(
                "2000.0000")) == 0, true);
    }

    /**
     * Case 3:
     * Condition: <br>
     * common param: //TODO;
     * screenName = B_BIF_S02s
     * Result: true
     */
    public void testExecute03() {

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         * $PAYMENT_METHOD="SP"
         * $IS_DISPLAY_EXP_AMT=0
         * $EXPORT_CURRENCY="SGP"
         * $FIXED_FOREX=2000.0000
         * $ID_REF="001"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_cust_plan("2");
        input.setPayment_method("SP");
        input.setIs_display_exp_amt("0");
        input.setExport_currency("SGP");
        input.setFixed_forex("2000");
        input.setId_bif("001                 ");
        /**
         * 【Table "T_BIF_H" has one record in accordance with
         * ID_REF="001"】Has the record
         */
        String[][] testDataTBifH =
            {
                {"ID_REF" , "ID_CUST" , "ID_CONSLT" , "ID_QCS" , "BIF_TYPE" ,
                    "ADR_TYPE" , "CONTACT_NAME" , "REQ_USER" , "DATE_REQ" ,
                    "CUST_PO" , "JOB_NO" , "CUST_MODE" , "CTTERM" ,
                    "CTTERM3_DAY" , "DELIVERY" , "CN_TYPE" , "CN_TYPE6" ,
                    "REMARKS" , "ID_STATUS" , "IS_CLOSED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "BIL_OPBY" , "DATE_BIFRCV" ,
                    "BIL_REFNO" , "DATE_PRINTED" , "DATE_SIGNED" , "BIL_IDC" ,
                    "BIL_GIN" , "BIL_SI" , "BIL_COR" , "BIL_OTH" ,
                    "IS_DELETED" , "USER_COMMENT" , "EXPORT_CUR" , "BILL_CUR" ,
                    "ID_QUO" , "TEL_NO" , "ADDRESS1" , "ADDRESS2" ,
                    "ADDRESS3" , "ADDRESS4" , "FAX_NO" , "ID_AUDIT"} ,
                {"001                 " , "5116" , "sysadmin" , "idQcsTest" ,
                    "IN" , "BA" , "" , "SYSJLX" , "2011-07-28" , "custPoTest" ,
                    "201107281527" , "" , "2" , "30 Days" , "1" , "" , "" ,
                    "" , "DS3" , "0" , "2011-07-28" , "2011-07-28" ,
                    "vincentlee" , "sysadmin" , "2011-07-28" , "" ,
                    "2011-07-28" , "2011-07-28" , "0" , "0" , "0" , "0" , "0" ,
                    "0" , "" , "USD" , "MYR" , "idQuoTest" , "" , "" , "" ,
                    "" , "" , "" , null}};
        super.insertData("T_BIF_H", testDataTBifH);

        /**
         * Table "T_BIF_D" has one record in accordance with
         * ID_REF="001"
         * ID_CUST_PLAN=1
         */
        String[][] testDataTBifD =
            {
                {"ID_REF" , "ID_CUST_PLAN" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "IS_DELETED" , "ID_AUDIT"} ,
                {"001                 " , "1" , "2011-07-28" , "2011-07-28" ,
                    "SYSJLX" , "0" , null}};
        super.insertData("T_BIF_D", testDataTBifD);
        /**
         * Table "T_CUST_PLAY_H" has one record in accordance with
         * ID_CUST_PLAN=1
         * PAYMENT_METHOD="BT"
         * IS_DISPLAY_EXT_AMT=1
         * EXPORT_CURRENCY="USD"
         * FIXED_FOREX=1000.0000
         * ID_BILL_ACCOUNT="NEW"
         */
        /**
        
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "NEW" , "0" ,
                    "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" , "1" ,
                    "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
        // 【Before jumping to the page】B_CPM_S02v
        boolean result = gCpmP01.execute(input, "B_CPM_S02v").booleanValue();

        assertEquals(result, false);
        /**
         * $PAYMENT_METHOD="SP"
         * $IS_DISPLAY_EXP_AMT=0
         * $EXPORT_CURRENCY="SGP"
         * $FIXED_FOREX=2000.0000
         */
        assertEquals(input.getPayment_method(), "SP");
        assertEquals(input.getIs_display_exp_amt(), "0");
        assertEquals(input.getExport_currency(), "SGP");
        assertEquals(
            new BigDecimal(input.getFixed_forex()).compareTo(new BigDecimal(
                "2000.0000")) == 0, true);
    }

    /**
     * Case 4:
     * Condition: <br>
     * common param:
     * screenName = B_BIF_S02s
     * Result: true
     */
    public void testExecute04() {

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_cust_plan("1");
        input.setId_login("sysadmin");
        input.setId_cust("5116");
        input.setBill_currency("USD");
        input.setId_cust_plan_grp("1");
        /**
         * Table "T_CUST_PLAY_H" has one record in accordance with
         * ID_CUST_PLAN=1
         * PAYMENT_METHOD="BT"
         * IS_DISPLAY_EXT_AMT=1
         * EXPORT_CURRENCY="USD"
         * FIXED_FOREX=1000.0000
         * ID_BILL_ACCOUNT="NEW"
         */
        /**
        
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "NEW" , "0" ,
                    "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" , "1" ,
                    "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Member Variable】
         * NewBAC=Null
         */
        gCpmP01.setNewBAC(null);
        // 【Before jumping to the page】B_CPM_S02v
        boolean result = gCpmP01.execute(input, "B_CPM_S02v").booleanValue();

        assertEquals(result, true);
        /**
         * 【Insert table "T_BAC_D"】
         * $ID_BILL_ACCOUNT=returned code from G_CDM_P01
         */

        assertEquals(input.getId_bill_account(), gCpmP01.getNewBAC());

    }

    /**
     * Case 5:
     * Condition: <br>
     * common param:
     * screenName = B_BIF_S02s
     * Result: true
     */
    public void testExecute05() {

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_cust_plan("1");
        input.setId_login("sysadmin");
        input.setId_cust_plan_grp("1");

        String[][] testDataBacH =
            {
                {"ID_BILL_ACCOUNT" , "ID_CUST" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "IS_SINGPOST" , "IS_DISPLAY_EXP_AMT" ,
                    "EXPORT_CURRENCY" , "FIXED_FOREX" , "CUST_ADR_TYPE" ,
                    "CONTACT_TYPE" , "TOTAL_AMT_DUE" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"SS" , "A" , "CQ" , "USD" , "0" , "0" , "ABC" , "1000.0000" ,
                    "RA" , "PC" , "1000.00" , "0" , "2011-08-20" ,
                    "2011-08-20" , "sysadmin" , null}};
        super.insertData("T_BAC_H", testDataBacH);
        /**
         * 【Member Variable】
         * NewBAC="SS"
         */
        gCpmP01.setNewBAC("SS");

        /**
         * Table "T_CUST_PLAY_H" has one record in accordance with
         * ID_CUST_PLAN=1
         * PAYMENT_METHOD="BT"
         * IS_DISPLAY_EXT_AMT=1
         * EXPORT_CURRENCY="USD"
         * FIXED_FOREX=1000.0000
         * ID_BILL_ACCOUNT="NEW"
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "NEW" , "0" ,
                    "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" , "1" ,
                    "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        @SuppressWarnings({"rawtypes" , "unused"})
        Map[] tBacH = super.select("T_CUST_PLAN_H", "", "");
        // 【Before jumping to the page】B_CPM_S02v
        boolean result = gCpmP01.execute(input, "B_CPM_S02v").booleanValue();
        assertEquals(result, true);
        assertEquals(input.getId_bill_account(), "SS");
    }

    /**
     * Case 6:
     * Condition: <br>
     * common param:
     * screenName = B_BIF_S02s
     * Result: true
     */
    public void testExecute06() {

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_cust_plan("1");
        input.setId_login("sysadmin");
        input.setId_cust_plan_grp("1");

        String[][] testDataBacH =
            {
                {"ID_BILL_ACCOUNT" , "ID_CUST" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "IS_SINGPOST" , "IS_DISPLAY_EXP_AMT" ,
                    "EXPORT_CURRENCY" , "FIXED_FOREX" , "CUST_ADR_TYPE" ,
                    "CONTACT_TYPE" , "TOTAL_AMT_DUE" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"IBA001" , "A" , "CQ" , "USD" , "0" , "0" , "ABC" ,
                    "1000.0000" , "RA" , "PC" , "1000.00" , "0" ,
                    "2011-08-20" , "2011-08-20" , "sysadmin" , null}};
        super.insertData("T_BAC_H", testDataBacH);
        /**
         * Table "T_CUST_PLAY_H" has one record in accordance with
         * ID_CUST_PLAN=1
         * PAYMENT_METHOD="BT"
         * IS_DISPLAY_EXT_AMT=1
         * EXPORT_CURRENCY="USD"
         * FIXED_FOREX=1000.0000
         * ID_BILL_ACCOUNT="IBA001"
         */
        /**
        
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "IBA001" ,
                    "0" , "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" ,
                    "1" , "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
        @SuppressWarnings({"rawtypes" , "unused"})
        Map[] tBacH = super.select("T_CUST_PLAN_H", "", "");
        // 【Before jumping to the page】B_CPM_S02v
        boolean result = gCpmP01.execute(input, "B_CPM_S02v").booleanValue();
        assertEquals(result, true);
        assertEquals(input.getId_bill_account(), "IBA001");
    }

    /**
     * Case 7:
     * Condition: <br>
     * common param:
     * screenName = B_BIF_S02s
     * Result: true
     */
    public void testExecute07() {

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_cust_plan("1");
        input.setId_login("sysadmin");
        input.setId_cust_plan_grp("1");

        String[][] testDataBacH =
            {
                {"ID_BILL_ACCOUNT" , "ID_CUST" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "IS_SINGPOST" , "IS_DISPLAY_EXP_AMT" ,
                    "EXPORT_CURRENCY" , "FIXED_FOREX" , "CUST_ADR_TYPE" ,
                    "CONTACT_TYPE" , "TOTAL_AMT_DUE" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"IBA001" , "A" , "CQ" , "USD" , "0" , "0" , "ABC" ,
                    "1000.0000" , "RA" , "PC" , "1000.00" , "0" ,
                    "2011-08-20" , "2011-08-20" , "sysadmin" , null}};
        super.insertData("T_BAC_H", testDataBacH);
        /**
         * Table "T_CUST_PLAY_H" has one record in accordance with
         * ID_CUST_PLAN=1
         * PAYMENT_METHOD="BT"
         * IS_DISPLAY_EXT_AMT=1
         * EXPORT_CURRENCY="USD"
         * FIXED_FOREX=1000.0000
         * ID_BILL_ACCOUNT="IBA001"
         */
        /**
        
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "IBA001" ,
                    "0" , "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" ,
                    "1" , "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
                    "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
                    null , "0" , "2011-07-28" , "2011-07-28" , "sysadmin" , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);

        // 【Before jumping to the page】B_CPM_S02v
        boolean result = gCpmP01.execute(input, "B_CPM_S02v").booleanValue();
        Date date = new Date();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        assertEquals(result, true);
        // T_CUST_PLAN_H
        @SuppressWarnings("rawtypes")
        Map[] tCustPlanH =
            super.select("T_CUST_PLAN_H", "ID_CUST_PLAN = 1", "");
        // T_CUST_PLAN_D
        @SuppressWarnings("rawtypes")
        Map[] tCustPlanD =
            super.select("T_CUST_PLAN_D", "ID_CUST_PLAN = 1", "");
        assertEquals(tCustPlanH[0].get("PLAN_STATUS"), "PS3");
        assertEquals(tCustPlanH[0].get("APPROVE_TYPE").toString().trim(), "S");
        assertEquals(
            tCustPlanH[0].get("ID_BILL_ACCOUNT").toString().trim(), "IBA001");
        assertEquals(tCustPlanH[0].get("DATE_UPDATED"), format1.format(date)
            + " 00:00:00.0");
        assertEquals(tCustPlanH[0].get("ID_LOGIN"), "sysadmin");
        assertEquals(tCustPlanD[0].get("SERVICES_STATUS"), "PS3");
        assertEquals(tCustPlanD[0].get("DATE_UPDATED"), "2011-07-28 00:00:00.0");
        assertEquals(tCustPlanD[0].get("ID_LOGIN"), "sysadmin");
    }
    
    /**
     * T_SUBSCRIPTION_INFO no data
     */
    public void testExecute8(){
        
    	G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_login("sysadmin");
        input.setId_cust_plan("1");
        input.setPayment_method("SP");
        input.setIs_display_exp_amt("0");
        input.setExport_currency("SGP");
        input.setFixed_forex("2000");
        input.setId_bif("001                 ");
        input.setId_cust_plan_grp("1");
        
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);

        String[][] testDataTCustPlanH =
        {
            {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "IBA001" ,
                "0" , "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" ,
                "1" , "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
	    super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
	    
	    String[][] testDataTCustPlanD =
	        {
	            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
	                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
	                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
	                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
	                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
	                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
	            {"1" , "1" , "PS0" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
	                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
	                null , "0" , "2011-07-28" , "2011-07-28" , "system" , null}};
	    super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        
        boolean result = gCpmP01.execute(input, "B_CPM_S02v");
        assertEquals("errors.ERR1SC069", input.getErrorMessages().get().next().getKey());
        assertEquals(false, result);
    }
    
    /**
     * T_SUBSCRIPTION_INFO have data  ACCESS_ACCOUNT is empty
     */
    public void testExecute9(){
        
    	G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_login("sysadmin");
        input.setId_cust_plan("1");
        input.setPayment_method("SP");
        input.setIs_display_exp_amt("0");
        input.setExport_currency("SGP");
        input.setFixed_forex("2000");
        input.setId_bif("001                 ");
        input.setId_cust_plan_grp("1");
        
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);

        String[][] testDataTCustPlanH =
        {
            {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "IBA001" ,
                "0" , "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" ,
                "1" , "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
	    super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
	    
	    String[][] testDataTCustPlanD =
	        {
	            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
	                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
	                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
	                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
	                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
	                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
	            {"1" , "1" , "PS0" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
	                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
	                null , "0" , "2011-07-28" , "2011-07-28" , "system" , null}};
	    super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "1", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", null,"0" } };
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        
        boolean result = gCpmP01.execute(input, "B_CPM_S02v");
        assertEquals("errors.ERR1SC069", input.getErrorMessages().get().next().getKey());
        assertEquals(false, result);
    }
    /**
     * 0 < ctc.size()
     */
    public void testExecut10(){

        /**
         * 【Pass parameter】
         * $ID_CUST_PLAN="1"
         * $PAYMENT_METHOD="SP"
         * $IS_DISPLAY_EXP_AMT=0
         * $EXPORT_CURRENCY="SGP"
         * $FIXED_FOREX=2000.0000
         * $ID_REF="001"
         */
        G_CPM_P01Input input = new G_CPM_P01Input();
        input.setId_login("sysadmin");
        input.setId_cust_plan("1");
        input.setPayment_method("SP");
        input.setIs_display_exp_amt("0");
        input.setExport_currency("SGP");
        input.setFixed_forex("2000");
        input.setId_bif("001                 ");
        input.setId_cust_plan_grp("1");

        /**
         * 【Table "T_BIF_H" has one record in accordance with
         * ID_REF="001"】Has the record
         */
        String[][] testDataTBifH =
            {
                {"ID_REF" , "ID_CUST" , "ID_CONSLT" , "ID_QCS" , "BIF_TYPE" ,
                    "ADR_TYPE" , "CONTACT_NAME" , "REQ_USER" , "DATE_REQ" ,
                    "CUST_PO" , "JOB_NO" , "CUST_MODE" , "CTTERM" ,
                    "CTTERM3_DAY" , "DELIVERY" , "CN_TYPE" , "CN_TYPE6" ,
                    "REMARKS" , "ID_STATUS" , "IS_CLOSED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "BIL_OPBY" , "DATE_BIFRCV" ,
                    "BIL_REFNO" , "DATE_PRINTED" , "DATE_SIGNED" , "BIL_IDC" ,
                    "BIL_GIN" , "BIL_SI" , "BIL_COR" , "BIL_OTH" ,
                    "IS_DELETED" , "USER_COMMENT" , "EXPORT_CUR" , "BILL_CUR" ,
                    "ID_QUO" , "TEL_NO" , "ADDRESS1" , "ADDRESS2" ,
                    "ADDRESS3" , "ADDRESS4" , "FAX_NO" , "ID_AUDIT"} ,
                {"001                 " , "5116" , "sysadmin" , "idQcsTest" ,
                    "IN" , "BA" , "" , "SYSJLX" , "2011-07-28" , "custPoTest" ,
                    "201107281527" , "" , "2" , "30 Days" , "1" , "" , "" ,
                    "" , "DS3" , "0" , "2011-07-28" , "2011-07-28" ,
                    "vincentlee" , "sysadmin" , "2011-07-28" , "" ,
                    "2011-07-28" , "2011-07-28" , "0" , "0" , "0" , "0" , "0" ,
                    "0" , "" , "USD" , "MYR" , "idQuoTest" , "" , "" , "" ,
                    "" , "" , "" , null}};
        super.insertData("T_BIF_H", testDataTBifH);

        /**
         * Table "T_BIF_D" has one record in accordance with
         * ID_REF="001"
         * ID_CUST_PLAN=1
         */
        String[][] testDataTBifD =
            {
                {"ID_REF" , "ID_CUST_PLAN" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "IS_DELETED" , "ID_AUDIT"} ,
                {"001                 " , "1" , "2011-07-28" , "2011-07-28" ,
                    "SYSJLX" , "0" , null}};
        super.insertData("T_BIF_D", testDataTBifD);
        
        /**
         * Table "m_cust_ctc" has one record in accordance with
         * id_cust="5116"
         * 
         */
        String[][] testM_CUST_CTC =
        {
            {"ID_CUST" , "CONTACT_NAME","CONTACT_TYPE" ,"ID_LOGIN" , "IS_DELETED" , "ID_AUDIT"} ,
            {"5116" , "123" ,"BC" , "sysadmin" , "0" ,"0"}};
        super.insertData("M_CUST_CTC", testM_CUST_CTC);
        
        /**
         * Table "T_CUST_PLAY_H" has one record in accordance with
         * ID_CUST_PLAN=1
         * PAYMENT_METHOD="BT"
         * IS_DISPLAY_EXT_AMT=1
         * EXPORT_CURRENCY="USD"
         * FIXED_FOREX=1000.0000
         * ID_BILL_ACCOUNT="NEW"
         */
        /**
        
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "NEW" , "0" ,
                    "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" , "1" ,
                    "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
        // 【Before jumping to the page】B_BIF_S02s
        boolean result = gCpmP01.execute(input, "B_BIF_S02s").booleanValue();

        assertEquals(result, true);
        assertEquals("BC", input.getContact_type());
        assertEquals("BA", input.getCust_adr_type());
    }
	
    /**
	 * radUserT != null
	 */
//    public void testExecut11(){
//    	gCpmP01 = new G_CPM_P01(queryDAO, updateDAO,queryDAO1,updateDAO1, "sysadmin");
//    	G_CPM_P01Input input = new G_CPM_P01Input();
//        input.setId_login("sysadmin");
//        input.setId_cust_plan("1");
//        input.setPayment_method("SP");
//        input.setIs_display_exp_amt("0");
//        input.setExport_currency("SGP");
//        input.setFixed_forex("2000");
//        input.setId_bif("001                 ");
//        input.setId_cust_plan_grp("1");
//        
//        String[][] testM_PLAN_BATCH_MAPPING =
//        {
//            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
//                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
//            {"123" , "123" , "RD" , "0" ,"","0",
//                "sysadmin" , "0" }};
//        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);
//
//        String[][] testDataTCustPlanH =
//        {
//            {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
//                "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
//                "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
//                "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
//                "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
//                "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
//                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
//            {"1" , "AAA" , "PS0" , "SP" , "1" , "DN" , "1" , "IBA001" ,
//                "0" , "1" , "1" , "1" , "BT" , "1" , "USD" , "1000.0000" ,
//                "1" , "1" , "2011-07-28" , "2011-07-28" , "system" , null}};
//	    super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
//	    
//	    String[][] testDataTCustPlanD =
//	        {
//	            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
//	                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
//	                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
//	                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
//	                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
//	                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
//	            {"1" , "1" , "PS0" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
//	                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
//	                null , "0" , "2011-07-28" , "2011-07-28" , "system" , null}};
//	    super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
//        
//        String[][] custPlanLink = {
//                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
//                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
//                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
//                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
//                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
//                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
//                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
//                { "1", "1", "S", null, "1", "1200", "1200", "JOBNO1", "1",
//                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
//                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
//                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
//        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
//        
//        String[][] T_SUBSCRIPTION_INFO = {
//                { "ID_CUST_PLAN", "MEMO", "ACCESS_ACCOUNT",
//                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
//                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
//                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
//                        "CARRIER", "ID_LOGIN",
//                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
//                { "1", "", "000000123", "", "", "", "", "", "",
//                        "", "", "",
//                        "", "", "", "sysadmin", "0", "0","0" } };
//        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
//        
//        String[][] RAD_USERS_T = {
//        		{ "USERNAME", "PASSWORD", "EXPIRE_DATE",
//        			"CREDIT_TIME", "STATUS", "PLAN_ID", "AUTH_METHOD",
//        			"ATTRIBUTE_RULE", "MODIFY_COUNTER"},
//        			{ "000000123", "123", "2011-01-01", "10", "0", "1", "0", "0", "0" } };
//        super.insertData("RAD_USERS_T", RAD_USERS_T);
//        
//        boolean result = gCpmP01.execute(input, "B_CPM_S02v");
//        assertEquals("errors.ERR1SC067", input.getErrorMessages().get().next().getKey());
//        assertEquals(false, result);
//    }
}
