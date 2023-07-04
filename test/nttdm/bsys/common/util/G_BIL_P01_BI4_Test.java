/**
 * @(#)G_BIL_P01_BI4_Test.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-9-28

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.bean.G_BIL_P01_Input;
import nttdm.bsys.common.bean.G_BIL_P01_Output;

/**
 * Test Class for nttdm.bsys.common.util.G_BIL_P01.<br>
 * T_CUST_PLAN_H.BILL_INSTRUCT = 4(Bi-Monthly)
 * 
 * @author bench
 */
public class G_BIL_P01_BI4_Test extends G_BIL_P01_Test{

    private static String BILL_INSTRUCT = "4";
    
    /**
     * Case BI4_01: E/1 Bi-Monthly Custom Plan/FULLPERIOD_MODE(Start Service)
     * 
     * Condition:<br>
     * BILL_FROM = null<br>
     * BILL_TO = null<br>
     * 
     * SVC_START = SYSDATE <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 4:2(Bi-Monthly)<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = SVC_START<br>
     * BILL_TO = BILL_FROM + 2Months -1DAY<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI401() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_TODAY;
        String service_end = null;
        String last_bill_from = null;
        String last_bill_to = null;
        String bill_from = TEST_DAY_TODAY;
        String bill_to = getBiMonthlyDay(bill_from);
        
        // Update GST to 0 in order to test.
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("SET_VALUE", "0");
        params.put("ID_SET", "GST");
        params.put("SET_SEQ", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL002", params);
        
        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status);

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start, service_end, "1",
                        "0", null, null, "M", null, "U", "30", "0", null, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "1", "200", "200", "JOBNO4", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "4",
                        "Bi-Monthly Fee", "1", "2", "BA", "4", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "2", "1", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "7",
                        "Monthly Fee with GST", "1", "2", "BA", "5", "1", "1",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param2.put("ID_CUST_PLAN_LINK", "2");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);

        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "0", null, "BA",
                        "PC", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BAC_D", bacD);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);

        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199999", tBilHlist[0].get("BILL_ACC").toString().trim());
        
        String dateReq = format.format(format.parse(tBilHlist[0]
                .get("DATE_REQ").toString()));
        assertEquals(TEST_DAY_TODAY, dateReq);
        assertEquals("CQ", tBilHlist[0].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[0].get("ID_CUST"));
        assertEquals("BA", tBilHlist[0].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[0].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("400", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("400", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[0].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[0].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[0].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[0].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[0].get("ADDRESS1"));
        assertEquals(null, tBilHlist[0].get("ADDRESS2"));
        assertEquals(null, tBilHlist[0].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[0].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[0].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[0].get("COUNTRY").toString().trim());
        assertEquals("(09) 8283327", tBilHlist[0].get("TEL_NO"));
        assertEquals("(09) 8283230", tBilHlist[0].get("FAX_NO"));
        assertEquals("Hiroshi Shiraga", tBilHlist[0].get("CONTACT_NAME"));
        assertEquals("test@test.com", tBilHlist[0].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[0].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[0].get("ID_LOGIN"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
        // T_BIL_D[0] is Header Information
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("400", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("400", tBilDlist[0].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[0].get("ITEM_GST"));
        assertEquals("0", tBilDlist[0].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[0].get("APPLY_GST"));
        assertEquals("0", tBilDlist[0].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[0].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL2"));
        assertEquals(null, tBilDlist[0].get("JOB_NO"));
        assertEquals("0", tBilDlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[0].get("ID_LOGIN"));

        for (int i = 0; i < 3; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from, format.format(billfrom1));
            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo1));
        }
        
        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[1].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("200", tBilDlist[1].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
        assertEquals("1", tBilDlist[1].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
        assertEquals("JOBNO4", tBilDlist[1].get("JOB_NO"));
        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
        assertEquals("3", tBilDlist[2].get("ITEM_ID"));
        assertEquals("1", tBilDlist[2].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[2].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("200", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("1", tBilDlist[2].get("APPLY_GST"));
        assertEquals("1", tBilDlist[2].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
        assertEquals("2", tBilDlist[2].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[2].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[2].get("SVC_LEVEL2"));
        assertEquals("JOBNO5", tBilDlist[2].get("JOB_NO"));
        assertEquals("0", tBilDlist[2].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
        
        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("400", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length);
    }

    /**
     * Case BI4_02: E/1 Bi-Monthly Custom Plan/FULLPERIOD_MODE(In Service)
     * 
     * Condition:<br>
     * BILL_FROM = 2 months ago<br>
     * BILL_TO = The day before yesterday<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 4:2(Bi-Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = yesterday<br>
     * BILL_TO = BILL_FROM + 2Months -1DAY<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI402() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = TEST_DAY_BEFORE_YESTERDAY;
        String bill_from = TEST_DAY_YESTERDAY;
        String bill_to = getBiMonthlyDay(bill_from);
        
        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status);

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start, service_end, "1",
                        "0", null, null, "M", null, "U", "30", "1", null, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "1", "200", "200", "JOBNO4", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "4",
                        "Bi-Monthly Fee", "1", "2", "BA", "4", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "2", "1", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "7",
                        "Monthly Fee with GST", "1", "2", "BA", "5", "1", "0",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param2.put("ID_CUST_PLAN_LINK", "2");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);

        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "0", null, "BA",
                        "PC", "1200", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BAC_D", bacD);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);

        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199999", tBilHlist[0].get("BILL_ACC").toString().trim());
        
        String dateReq = format.format(format.parse(tBilHlist[0]
                .get("DATE_REQ").toString()));
        assertEquals(TEST_DAY_TODAY, dateReq);
        assertEquals("CQ", tBilHlist[0].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[0].get("ID_CUST"));
        assertEquals("BA", tBilHlist[0].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[0].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("400", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("400", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[0].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[0].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[0].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[0].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[0].get("ADDRESS1"));
        assertEquals(null, tBilHlist[0].get("ADDRESS2"));
        assertEquals(null, tBilHlist[0].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[0].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[0].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[0].get("COUNTRY").toString().trim());
        assertEquals("(09) 8283327", tBilHlist[0].get("TEL_NO"));
        assertEquals("(09) 8283230", tBilHlist[0].get("FAX_NO"));
        assertEquals("Hiroshi Shiraga", tBilHlist[0].get("CONTACT_NAME"));
        assertEquals("test@test.com", tBilHlist[0].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[0].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[0].get("ID_LOGIN"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
        // T_BIL_D[0] is Header Information
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("400", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("400", tBilDlist[0].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[0].get("ITEM_GST"));
        assertEquals("0", tBilDlist[0].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[0].get("APPLY_GST"));
        assertEquals("1", tBilDlist[0].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[0].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL2"));
        assertEquals(null, tBilDlist[0].get("JOB_NO"));
        assertEquals("0", tBilDlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[0].get("ID_LOGIN"));

        for (int i = 0; i < 3; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from, format.format(billfrom1));
            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo1));
        }
        
        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[1].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("200", tBilDlist[1].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
        assertEquals("0", tBilDlist[1].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
        assertEquals("JOBNO4", tBilDlist[1].get("JOB_NO"));
        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
        assertEquals("3", tBilDlist[2].get("ITEM_ID"));
        assertEquals("1", tBilDlist[2].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[2].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("200", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
        assertEquals("0", tBilDlist[2].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
        assertEquals("2", tBilDlist[2].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[2].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[2].get("SVC_LEVEL2"));
        assertEquals("JOBNO5", tBilDlist[2].get("JOB_NO"));
        assertEquals("0", tBilDlist[2].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
        
        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("1600", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length); 
    }
    
    /**
     * Case BI4_03: E/1 Bi-Monthly Custom Plan/FULLPERIOD_MODE(2 billings)
     * 
     * Condition:<br>
     * BILL_FROM = 5 months ago<br>
     * BILL_TO = 3 months ago<br>
     * 
     * SVC_START = 10 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 4:2(Bi-Monthly))<br>
     * 
     * Result:<br>
     * 2 data generated in T_BIL_H<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = 1 months ago + 1DAY<br>
     * BILL_TO = BILL_FROM + 2 Months -1DAY<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI403() throws Exception {
        String plan_status = "PS3";
        String service_status= "PS3";
        String service_start = "2001-02-02";
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_TODAY;
        String last_bill_to = this.get3MonthsAgoDay();
        String bill_from1 = this.getNextDay(last_bill_to);
        String bill_to1 = this.getBiMonthlyDay(bill_from1);
        String bill_from2 = this.getNextDay(bill_to1);
        String bill_to2 = this.getBiMonthlyDay(bill_from2);
        
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("CUSTOMER_TYPE", "CONS");
        params.put("ID_CUST", ID_CUST);
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL003", params);
        
        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status);

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start, service_end, "1",
                        "0", null, null, "M", null, "U", "30", "0", null, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "2", "300", "600", "JOBNO3", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "3",
                        "Quarterly Fee", "1", "2", "BA", "3", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null }};
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        
        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "0", null, "BA",
                        "PC", "2400", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BAC_D", bacD);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setBillAccountId(null);
        input.setRunning_date(new Date());

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null,
                "ID_REF ASC");
        // one bill
        assertTrue(tBilHlist != null && tBilHlist.length == 1);

        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199999", tBilHlist[0].get("BILL_ACC").toString().trim());
        
        String dateReq = format.format(format.parse(tBilHlist[0]
                .get("DATE_REQ").toString()));
        assertEquals(TEST_DAY_TODAY, dateReq);
        assertEquals("CQ", tBilHlist[0].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[0].get("ID_CUST"));
        assertEquals("BA", tBilHlist[0].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[0].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("800", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("800", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[0].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[0].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[0].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[0].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[0].get("ADDRESS1"));
        assertEquals(null, tBilHlist[0].get("ADDRESS2"));
        assertEquals(null, tBilHlist[0].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[0].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[0].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[0].get("COUNTRY").toString().trim());
        assertEquals("(86) 216699", tBilHlist[0].get("TEL_NO"));
        assertEquals("(86) 216688", tBilHlist[0].get("FAX_NO"));
        assertEquals("AMSB", tBilHlist[0].get("CONTACT_NAME"));
        assertEquals("abc@abc.net.my", tBilHlist[0].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[0].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[0].get("ID_LOGIN"));
        // now is one bill
        //assertEquals("400", tBilHlist[1].get("BILL_AMOUNT"));
        //assertEquals("400", tBilHlist[1].get("EXPORT_AMOUNT"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null,
                "ID_REF ASC, ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 4);

        for (int i = 0; i < 4; i = i + 2) {
            // T_BIL_D[0] is header information
        	// auto increased by one
        	if(i==0){
        		assertEquals("1", tBilDlist[i + 0].get("ITEM_ID"));
        	}else{
        		assertEquals("3", tBilDlist[i + 0].get("ITEM_ID"));
        	}
            assertEquals("0", tBilDlist[i + 0].get("ITEM_LEVEL"));
            assertEquals(null, tBilDlist[i + 0].get("ITEM_NO"));
            assertEquals("1", tBilDlist[i + 0].get("ITEM_QTY"));
            assertEquals("400", tBilDlist[i + 0].get("ITEM_UPRICE"));
            assertEquals("400", tBilDlist[i + 0].get("ITEM_AMT"));
            assertEquals("0", tBilDlist[i + 0].get("ITEM_GST"));
            assertEquals("0", tBilDlist[i + 0].get("ITEM_EXPORT_AMT"));
            assertEquals("0", tBilDlist[i + 0].get("APPLY_GST"));
            assertEquals("0", tBilDlist[i + 0].get("IS_DISPLAY"));
            assertEquals("1", tBilDlist[i + 0].get("ID_CUST_PLAN"));
            assertEquals("1", tBilDlist[i + 0].get("ID_CUST_PLAN_GRP"));
            assertEquals(null, tBilDlist[i + 0].get("ID_CUST_PLAN_LINK"));
            assertEquals(null, tBilDlist[i + 0].get("SVC_LEVEL1"));
            assertEquals(null, tBilDlist[i + 0].get("SVC_LEVEL2"));
            assertEquals(null, tBilDlist[i + 0].get("JOB_NO"));
            assertEquals("0", tBilDlist[i + 0].get("IS_DELETED"));
            assertEquals(USER_ID, tBilDlist[i + 0].get("ID_LOGIN"));

            // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
        	// auto increased by one
        	if(i==0){
        		assertEquals("2", tBilDlist[i + 1].get("ITEM_ID"));
        	}else{
        		assertEquals("4", tBilDlist[i + 1].get("ITEM_ID"));
        	}
            assertEquals("1", tBilDlist[i + 1].get("ITEM_LEVEL"));
            assertEquals("2", tBilDlist[i + 1].get("ITEM_QTY"));
            assertEquals("200", tBilDlist[i + 1].get("ITEM_UPRICE"));
            assertEquals("400", tBilDlist[i + 1].get("ITEM_AMT"));
            assertEquals("0", tBilDlist[i + 1].get("ITEM_GST"));
            assertEquals("400", tBilDlist[i + 1].get("ITEM_EXPORT_AMT"));
            assertEquals("0", tBilDlist[i + 1].get("APPLY_GST"));
            assertEquals("1", tBilDlist[i + 1].get("IS_DISPLAY"));
            assertEquals("1", tBilDlist[i + 1].get("ID_CUST_PLAN"));
            assertEquals("1", tBilDlist[i + 1].get("ID_CUST_PLAN_GRP"));
            assertEquals("1", tBilDlist[i + 1].get("ID_CUST_PLAN_LINK"));
            assertEquals("1", tBilDlist[i + 1].get("SVC_LEVEL1"));
            assertEquals("2", tBilDlist[i + 1].get("SVC_LEVEL2"));
            assertEquals("JOBNO3", tBilDlist[i + 1].get("JOB_NO"));
            assertEquals("0", tBilDlist[i + 1].get("IS_DELETED"));
            assertEquals(USER_ID, tBilDlist[i + 1].get("ID_LOGIN"));
        }

        for (int i = 0; i < 2; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from1, format.format(billfrom1));

            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to1, format.format(billTo1));
        }
        
        for (int i = 2; i < 4; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from2, format.format(billfrom1));

            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to2, format.format(billTo1));
        }
        
        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN='1'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from2, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to2, format.format(billTo));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("3200", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert two T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length);
    }
    
    /**
     * Case BI4_04: E/1 Bi-Monthly Custom Plan/PRORATE_SVCEND(PS4)
     * 
     * Condition:<br>
     * BILL_FROM = 2 months ago<br>
     * BILL_TO = Today<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = The day after tomorrow<br>
     * PLAN_STATUS = PS4<br>
     * BILL_INSTRUCT = 4:2(Bi-Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/T_BIL_D<br>
     * PLAN_STATUS = "PS4"<br>
     * BILL_FROM = tomorrow<br>
     * BILL_TO = SVC_END<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI404() throws Exception {
        String plan_status = "PS4";
        String service_status = "PS4";
        String service_start = "2010-01-29";
        String service_end = "2011-09-01";
        String last_bill_from = "2011-07-01";
        String last_bill_to = "2011-08-30";
        String bill_from = "2011-08-31";
        String bill_to = "2011-09-01";
        String run_date = "2011-08-30";
        
        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status);

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start, service_end, "1",
                        "0", null, null, "M", null, "U", "30", "0", null, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };

        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "2", "600", "1200", "JOBNO2", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "2",
                        "Bi-Annually Fee", "1", "2", "BA", "2", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null }};
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        
        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "0", null, "BA",
                        "PC", "1200", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BAC_D", bacD);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setBillAccountId(null);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date runningDate = format.parse(run_date);
        input.setRunning_date(runningDate);
        
        
        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);

        if (last_bill_to.equals(this.getMonthlyLastDay(last_bill_to))) {
            assertTrue("No Data Generated.", tBilHlist == null || tBilHlist.length == 0);
            return;
        } else {        
            assertTrue(tBilHlist != null && tBilHlist.length == 1);
        }

        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199999", tBilHlist[0].get("BILL_ACC").toString().trim());
        
        String dateReq = format.format(format.parse(tBilHlist[0]
                .get("DATE_REQ").toString()));
        assertEquals(run_date, dateReq);
        assertEquals("CQ", tBilHlist[0].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[0].get("ID_CUST"));
        assertEquals("BA", tBilHlist[0].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[0].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("13.33", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("13.33", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[0].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[0].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[0].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[0].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[0].get("ADDRESS1"));
        assertEquals(null, tBilHlist[0].get("ADDRESS2"));
        assertEquals(null, tBilHlist[0].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[0].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[0].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[0].get("COUNTRY").toString().trim());
        assertEquals("(09) 8283327", tBilHlist[0].get("TEL_NO"));
        assertEquals("(09) 8283230", tBilHlist[0].get("FAX_NO"));
        assertEquals("Hiroshi Shiraga", tBilHlist[0].get("CONTACT_NAME"));
        assertEquals("test@test.com", tBilHlist[0].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[0].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[0].get("ID_LOGIN"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 2);
        
        // T_BIL_D[0] is blank when T_CUST_PLAN_D.IS_DISPLAY_DESC=0
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("13.33", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("13.33", tBilDlist[0].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[0].get("ITEM_GST"));
        assertEquals("0", tBilDlist[0].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[0].get("APPLY_GST"));
        assertEquals("0", tBilDlist[0].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[0].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL2"));
        
        Date billfrom1 = format.parse(tBilDlist[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom1));
        
        Date billTo1 = format.parse(tBilDlist[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo1));
        
        assertEquals(null, tBilDlist[0].get("JOB_NO"));
        assertEquals("0", tBilDlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[0].get("ID_LOGIN"));
        
        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
        assertEquals("2", tBilDlist[1].get("ITEM_QTY"));
        assertEquals("6.67", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("13.33", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("13.33", tBilDlist[1].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
        assertEquals("1", tBilDlist[1].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
        
        Date billfrom2 = format.parse(tBilDlist[1].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom2));
        
        Date billTo2 = format.parse(tBilDlist[1].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo2));

        assertEquals("JOBNO2", tBilDlist[1].get("JOB_NO"));
        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo));
        assertEquals("0", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE not updated because of CB_AUTO_OFFSET != BAC
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("1213.33", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS4", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS4", tCustPlanH[0].get("PLAN_STATUS"));

        // assert no T_BIL_S data generated because of BillType != IN.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length);
    }
    
    /**
     * Case BI4_05: E/1 Bi-Monthly Custom Plan/FULLPERIOD_MODE(USD)
     * 
     * Condition:<br>
     * BILL_FROM = 2 months ago<br>
     * BILL_TO = The day before yesterday<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 4:2(Bi-Monthly))<br>
     * 
     * EXPORT_CURRENCY=USD
     * FIXED_FOREX=3.1
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Two data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = yesterday<br>
     * BILL_TO = BILL_FROM + 2Months -1DAY<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI405() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = TEST_DAY_BEFORE_YESTERDAY;
        String bill_from = TEST_DAY_YESTERDAY;
        String bill_to = getBiMonthlyDay(bill_from);
        String exportCurrency = "USD";
        String fixedForex = "3.1";
        String curRate = "4.1";

        // Not used when fixedForex != null
        String[][] mCurData = {
                { "RATE_DATE", "CUR_CODE", "RATE_TO", "RATE_FROM",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { TEST_DAY_YESTERDAY, "USD", curRate, curRate, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { TEST_DAY_BEFORE_YESTERDAY, "USD", "3.00", "3.00", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("M_CURRENCY", mCurData);
        
        String[][] dataCustPlanH = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { ID_CUST_PLAN, ID_CUST, plan_status, "SP", "S", "IN", ID_PLAN,
                        "1199999", "0", "1", "1", BILL_INSTRUCT, "CA",
                        DEF_CURRENCY, exportCurrency, fixedForex, "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_H", dataCustPlanH);
        
        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start, service_end, "1",
                        "0", null, null, "M", null, "U", "30", "0", null, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };

        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "2", "600", "1200", "JOBNO2", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "2",
                        "Bi-Annually Fee", "1", "2", "BA", "2", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null }};
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);

        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "0",
                        exportCurrency, "BA", "PC", "1200", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, fixedForex,
                        null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BAC_D", bacD);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);

        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199999", tBilHlist[0].get("BILL_ACC").toString().trim());
        
        String dateReq = format.format(format.parse(tBilHlist[0]
                .get("DATE_REQ").toString()));
        assertEquals(TEST_DAY_TODAY, dateReq);
        assertEquals("CQ", tBilHlist[0].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[0].get("ID_CUST"));
        assertEquals("BA", tBilHlist[0].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[0].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("400", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(exportCurrency, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals(fixedForex, tBilHlist[0].get("CUR_RATE"));
        assertEquals("129.03", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(fixedForex, tBilHlist[0].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[0].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[0].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[0].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[0].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[0].get("ADDRESS1"));
        assertEquals(null, tBilHlist[0].get("ADDRESS2"));
        assertEquals(null, tBilHlist[0].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[0].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[0].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[0].get("COUNTRY").toString().trim());
        assertEquals("(09) 8283327", tBilHlist[0].get("TEL_NO"));
        assertEquals("(09) 8283230", tBilHlist[0].get("FAX_NO"));
        assertEquals("Hiroshi Shiraga", tBilHlist[0].get("CONTACT_NAME"));
        assertEquals("test@test.com", tBilHlist[0].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[0].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[0].get("ID_LOGIN"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 2);
        
        // T_BIL_D[0] is blank when T_CUST_PLAN_D.IS_DISPLAY_DESC=0
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("400", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("400", tBilDlist[0].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[0].get("ITEM_GST"));
        assertEquals("0", tBilDlist[0].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[0].get("APPLY_GST"));
        assertEquals("0", tBilDlist[0].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[0].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL2"));
        
        Date billfrom1 = format.parse(tBilDlist[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom1));
        
        Date billTo1 = format.parse(tBilDlist[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo1));
        
        assertEquals(null, tBilDlist[0].get("JOB_NO"));
        assertEquals("0", tBilDlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[0].get("ID_LOGIN"));
        
        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
        assertEquals("2", tBilDlist[1].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("400", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("129.03", tBilDlist[1].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
        assertEquals("1", tBilDlist[1].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
        
        Date billfrom2 = format.parse(tBilDlist[1].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom2));
        
        Date billTo2 = format.parse(tBilDlist[1].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo2));

        assertEquals("JOBNO2", tBilDlist[1].get("JOB_NO"));
        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE not updated because of CB_AUTO_OFFSET != BAC
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("1600", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));

        // assert no T_BIL_S data generated because of BillType != IN.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length);
    }
   
    /**
     * Case BI4_06: E/1 Bi-Monthly Custom Plan/FULLPERIOD_MODE(RateMode=6)
     * 
     * Condition:<br>
     * BILL_FROM = 2 months ago<br>
     * BILL_TO = The day before yesterday<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 4:2(Bi-Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Two data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = yesterday<br>
     * BILL_TO = BILL_FROM + 2Months -1DAY<br>
     * 
     * RateMode=6:data not generated
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI406() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = TEST_DAY_BEFORE_YESTERDAY;
        String bill_from = TEST_DAY_YESTERDAY;
        String bill_to = getBiMonthlyDay(bill_from);
        
        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status);

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start, service_end, "1",
                        "0", null, null, "M", null, "U", "30", "1", null, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "1", "200", "200", "JOBNO4", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "4",
                        "Bi-Monthly Fee", "1", "2", "BA", "4", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "2", "1", "S", null, "1", "1000", "1000", "JOBNO6", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "6",
                        "One Time", "1", "2", "BA", "6", "1", "0",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param2.put("ID_CUST_PLAN_LINK", "2");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);

        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "0", null, "BA",
                        "PC", "1200", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BAC_D", bacD);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);

        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199999", tBilHlist[0].get("BILL_ACC").toString().trim());
        
        String dateReq = format.format(format.parse(tBilHlist[0]
                .get("DATE_REQ").toString()));
        assertEquals(TEST_DAY_TODAY, dateReq);
        assertEquals("CQ", tBilHlist[0].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[0].get("ID_CUST"));
        assertEquals("BA", tBilHlist[0].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[0].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("200", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("200", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[0].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[0].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[0].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[0].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[0].get("ADDRESS1"));
        assertEquals(null, tBilHlist[0].get("ADDRESS2"));
        assertEquals(null, tBilHlist[0].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[0].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[0].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[0].get("COUNTRY").toString().trim());
        assertEquals("(09) 8283327", tBilHlist[0].get("TEL_NO"));
        assertEquals("(09) 8283230", tBilHlist[0].get("FAX_NO"));
        assertEquals("Hiroshi Shiraga", tBilHlist[0].get("CONTACT_NAME"));
        assertEquals("test@test.com", tBilHlist[0].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[0].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[0].get("ID_LOGIN"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 2);
        
        // T_BIL_D[0] is Header Information
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[0].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[0].get("ITEM_GST"));
        assertEquals("0", tBilDlist[0].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[0].get("APPLY_GST"));
        assertEquals("1", tBilDlist[0].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[0].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL2"));
        assertEquals(null, tBilDlist[0].get("JOB_NO"));
        assertEquals("0", tBilDlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[0].get("ID_LOGIN"));

        for (int i = 0; i < 2; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from, format.format(billfrom1));
            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo1));
        }
        
        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[1].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("200", tBilDlist[1].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
        assertEquals("0", tBilDlist[1].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
        assertEquals("JOBNO4", tBilDlist[1].get("JOB_NO"));
        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));
        
        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("1400", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length); 
    }
    

	/**
	 * Case BI4_07: E/1 Bi-Monthly Custom Plan/FULLPERIOD_MODE(RateMode=6)
	 * 
	 * Condition:<br>
	 * BILL_FROM = null<br>
	 * BILL_TO = null<br>
	 * 
	 * SVC_START = SYSDATE <br>
	 * SVC_END = null<br>
	 * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
	 * BILL_INSTRUCT = 4:2(Bi-Monthly)<br>
	 * 
	 * Result:<br>
	 * One data generated in T_BIL_H/Three data in T_BIL_D<br>
	 * PLAN_STATUS = "PS3"<br>
     * SERVICES_STATUS = PS3<br>
	 * BILL_FROM = SVC_START<br>
	 * BILL_TO = BILL_FROM + 2Months -1DAY<br>
	 * 
	 * RateMode=6: Data generated
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecuteBI407() throws Exception {
		String plan_status = "PS3";
		String service_status= "PS3";
		String service_start = TEST_DAY_TODAY;
		String service_end = null;
		String last_bill_from = null;
		String last_bill_to = null;
		String bill_from = TEST_DAY_TODAY;
		String bill_to = getBiMonthlyDay(bill_from);
		
        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status);

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start, service_end, "1",
                        "0", null, null, "M", null, "U", "30", "1", null, "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "1", "200", "200", "JOBNO4", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "4",
                        "Bi-Monthly Fee", "1", "2", "BA", "4", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "2", "1", "S", null, "1", "1000", "1000", "JOBNO6", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "6",
                        "One Time", "1", "2", "BA", "6", "1", "0",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param2.put("ID_CUST_PLAN_LINK", "2");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);

        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "0", null, "BA",
                        "PC", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BAC_D", bacD);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setBillAccountId(null);
        input.setRunning_date(new Date());

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);

        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199999", tBilHlist[0].get("BILL_ACC").toString().trim());
        
        String dateReq = format.format(format.parse(tBilHlist[0]
                .get("DATE_REQ").toString()));
        assertEquals(TEST_DAY_TODAY, dateReq);
        assertEquals("CQ", tBilHlist[0].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[0].get("ID_CUST"));
        assertEquals("BA", tBilHlist[0].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[0].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("1200", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("1200", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[0].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[0].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[0].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[0].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[0].get("ADDRESS1"));
        assertEquals(null, tBilHlist[0].get("ADDRESS2"));
        assertEquals(null, tBilHlist[0].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[0].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[0].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[0].get("COUNTRY").toString().trim());
        assertEquals("(09) 8283327", tBilHlist[0].get("TEL_NO"));
        assertEquals("(09) 8283230", tBilHlist[0].get("FAX_NO"));
        assertEquals("Hiroshi Shiraga", tBilHlist[0].get("CONTACT_NAME"));
        assertEquals("test@test.com", tBilHlist[0].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[0].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[0].get("ID_LOGIN"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
        // T_BIL_D[0] is Header Information
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("1200", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("1200", tBilDlist[0].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[0].get("ITEM_GST"));
        assertEquals("0", tBilDlist[0].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[0].get("APPLY_GST"));
        assertEquals("1", tBilDlist[0].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[0].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL2"));
        assertEquals(null, tBilDlist[0].get("JOB_NO"));
        assertEquals("0", tBilDlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[0].get("ID_LOGIN"));

        for (int i = 0; i < 3; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from, format.format(billfrom1));
            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo1));
        }
        
        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[1].get("ITEM_QTY"));
        assertEquals("200", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("200", tBilDlist[1].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
        assertEquals("0", tBilDlist[1].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
        assertEquals("JOBNO4", tBilDlist[1].get("JOB_NO"));
        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
        assertEquals("3", tBilDlist[2].get("ITEM_ID"));
        assertEquals("1", tBilDlist[2].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[2].get("ITEM_QTY"));
        assertEquals("1000", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("1000", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("1000", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
        assertEquals("0", tBilDlist[2].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
        assertEquals("2", tBilDlist[2].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[2].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[2].get("SVC_LEVEL2"));
        assertEquals("JOBNO6", tBilDlist[2].get("JOB_NO"));
        assertEquals("0", tBilDlist[2].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
        
        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("1200", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length); 
	}
    /**
     * Get 2 months later day of given day.<br>
     * Given Day + 2Months -1Day
     * 
     * @param currentDay
     * @return
     * @throws Exception
     */
    private String getBiMonthlyDay(String currentDay) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = format.parse(currentDay);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, 2);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date newDay = calendar.getTime();

        return format.format(newDay);
    }

    /**
     * Get 3 months ago day of given day.<br>
     * 
     * @return
     * @throws Exception
     */
    private String get3MonthsAgoDay() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, -3);
        Date newDay = calendar.getTime();

        return format.format(newDay);
    }
}
