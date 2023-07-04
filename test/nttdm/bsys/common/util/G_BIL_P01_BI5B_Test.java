/**
 * @(#)G_BIL_P01_BI5B_Test.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-9-29

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.bean.G_BIL_P01_Input;
import nttdm.bsys.common.bean.G_BIL_P01_Output;

/**
 * Test Class for nttdm.bsys.common.util.G_BIL_P01.<br>
 * T_CUST_PLAN_H.BILL_INSTRUCT = 5(Monthly), Bill in Advance.
 * 
 * @author bench
 */
public class G_BIL_P01_BI5B_Test extends G_BIL_P01_Test{

    private static String BILL_INSTRUCT = "5";

    /**
     * Case BI5_01: E/1 Monthly Custom Plan/PRORATE_SVCSTART(Start Service)
     * 
     * Condition:<br>
     * BILL_FROM = null<br>
     * BILL_TO = null<br>
     * 
     * SVC_START = before current month last day <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly)<br>
     * 
     * nDay = 2<br>
     * nMonth = 2<br>
     * 
     * T_BIL_H: No Record<br>
     * T_CSB_H: No Record<br>
     * T_BIL_S: No Record<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM = SVC_START<br>
     * BILL_TO = Last Day of the month(BILL_FROM)<br>
     * 
     * One record in T_BIL_S.<br>
     * 1. No payment case<br>
     * 2. No C Statement<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI501() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = getBeforeLastMonthDay(TEST_DAY_TODAY);
        String service_end = null;
        String last_bill_from = null;
        String last_bill_to = null;
        String bill_from = service_start;
        String bill_to = getMonthlyLastDay(bill_from);
        
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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        
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
        assertEquals("13.34", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("13.34", tBilHlist[0].get("EXPORT_AMOUNT"));
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
        assertEquals("13.34", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("13.34", tBilDlist[0].get("ITEM_AMT"));
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
        assertEquals("6.67", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("6.67", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("6.67", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("6.67", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("6.67", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("6.67", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
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
        assertEquals("13.34", tBacH[0].get("TOTAL_AMT_DUE"));
        
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
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("0", tBilS[0].get("PREVIOUS_AMT"));
        //assertEquals("Last Payment", tBilS[0].get("LAST_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("LAST_PAY_DATE"));
        assertEquals("0", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals(null, tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("REJECT_DESC"));
        assertEquals(null, tBilS[0].get("REJECT_DATE"));
        assertEquals("0", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("0", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("13.34", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));        
    }
    
    /**
     * Case BI5_02: E/1 Monthly Custom Plan/FULLPERIOD_MODE(In Service)
     * 
     * Condition:<br>
     * BILL_FROM = 1 months ago<br>
     * BILL_TO = The last day of the last month<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * T_CUST_PLAN_D.PRO_RATE_BASE = "S"<br>
     * T_CUST_PLAN_D.PRO_RATE_BASE_NO = null<br>
     * T_BAC_H.IS_DISPLAY_EXP_AMT = null<br>
     * 
     * T_BIL_H/T_BIL_S contains one record<br>
     * T_CSB_H contains 2 records<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM = the first day of the month<br>
     * BILL_TO = the last day of the month<br>
     * 
     * T_BIL_H.IS_DISPLAY_EXP_AMT = 0
     * 1. Last payment case
     * 2. No C Statement
     * CBUpdate = 1. T_CSB_H.IS_EXPORT 0 -> 1
     * BILUpdate = 0
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI502() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = getLastMonthLastDay(TEST_DAY_TODAY);
        String bill_from = getMonthFirstDay(TEST_DAY_TODAY);
        String bill_to = getMonthlyLastDay(bill_from);
        
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
                        "0", null, null, "M", null, "S", null, "0", null, "0",
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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", null, null, "BA",
                        "PC", "2000", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
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

        // Generate invoice data in T_BIL_S/T_CSB_H.
        this.generatePreInvoce();
        this.generateTCSBH("N", "0", "0", "0");

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", "ID_REF <> '11IV000158'", null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        
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
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
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
        assertEquals("100", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("100", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("100", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("100", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
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
        assertEquals("2200", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", "ID_REF <> '11IV000158'", null);
        assertEquals(1, tBilS.length);
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("2000", tBilS[0].get("PREVIOUS_AMT"));

        //assertEquals("Last Payment By Cheque", tBilS[0].get("LAST_PAY_TYPE"));
        Date lastPayDate = format.parse(tBilS[0].get("LAST_PAY_DATE").toString());
        assertEquals("2011-08-08", format.format(lastPayDate));
        assertEquals("26000", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals(null, tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("REJECT_DESC"));
        assertEquals(null, tBilS[0].get("REJECT_DATE"));
        assertEquals("0", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("2000", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("2200", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));
        
        // assert T_CSB_H table updated IS_EXPORT = 1
        Map<String, Object>[] tCsbH = super.select("T_CSB_H", null, null);
        assertEquals("1", tCsbH[0].get("IS_EXPORT"));
        assertEquals("1", tCsbH[1].get("IS_EXPORT"));
    }

    /**
     * Case BI5_03: E/1 Monthly Custom Plan/PRORATE_SVCSTART(In Service)
     * 
     * Condition:<br>
     * BILL_FROM = "2011-08-08"<br>
     * BILL_TO = "2011-08-29"<br>
     * 
     * SVC_START = "2011-08-08" <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly)<br>
     * 
     * run_date = 2011/9/30<br>
     * 
     * Result:<br>
     * 2 data generated in T_BIL_H/T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = 2011-08-30<br>
     * BILL_TO = 2011-08-31<br>
     * BILL_FROM2 = 2011-09-01<br>
     * BILL_TO2 = 2011-09-30<br>
     *
     * 1. No payment case
     * 2. Reject Case
     * CBUpdate = 1
     * BILUpdate = 0
     *
     * 1. No payment case
     * 2. No C Statement
     * CBUpdate = 0
     * BILUpdate = 0
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI503() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = "2011-08-08";
        String service_end = null;
        String last_bill_from = "2011-08-08";
        String last_bill_to = "2011-08-29";
        String bill_from = "2011-08-30";
        String bill_to = "2011-08-31";
        String bill_from2 = "2011-09-01";
        String bill_to2 = "2011-09-30";
        String run_date = "2011-09-29";
        
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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
                        "PC", "2000", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
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
        input.setRunning_date(format.parse(run_date));

        // Generate invoice data in T_BIL_S/T_CSB_H.
        this.generatePreInvoce();
        this.generateTCSBH("R", "0", "0", "0");
        
        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", "ID_REF <> '11IV000158'", null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        //String idRef2 = CommonUtils.toString(tBilHlist[1].get("ID_REF"));
        
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
        // tBilHlist[0]+tBilHlist[1] for one bill
        assertEquals("213.34", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        // tBilHlist[0]+tBilHlist[1]
        assertEquals("213.34", tBilHlist[0].get("EXPORT_AMOUNT"));
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

        // just one bill
        //assertEquals("200", tBilHlist[1].get("BILL_AMOUNT"));
        //assertEquals("200", tBilHlist[1].get("EXPORT_AMOUNT"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", "ID_REF <> '11IV000158'", "ID_REF ASC, ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 6);
        
        // T_BIL_D[0] is Header Information
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("13.34", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("13.34", tBilDlist[0].get("ITEM_AMT"));
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
        assertEquals("6.67", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("6.67", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("6.67", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("6.67", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("6.67", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("6.67", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
        assertEquals("1", tBilDlist[2].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
        assertEquals("2", tBilDlist[2].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[2].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[2].get("SVC_LEVEL2"));
        assertEquals("JOBNO5", tBilDlist[2].get("JOB_NO"));
        assertEquals("0", tBilDlist[2].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
        
        // Second Billing
        assertEquals("200", tBilDlist[3].get("ITEM_UPRICE"));
        assertEquals("200", tBilDlist[3].get("ITEM_AMT"));
        assertEquals("100", tBilDlist[4].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[4].get("ITEM_AMT"));
        assertEquals("100", tBilDlist[4].get("ITEM_EXPORT_AMT"));
        assertEquals("100", tBilDlist[5].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[5].get("ITEM_AMT"));
        assertEquals("100", tBilDlist[5].get("ITEM_EXPORT_AMT"));
        for (int i = 3; i < 6; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from2, format.format(billfrom1));
            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to2, format.format(billTo1));
        }
        
        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from2, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to2, format.format(billTo));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("2213.34", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", "ID_REF <> '11IV000158'", null);
        assertEquals(1, tBilS.length);
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("2000", tBilS[0].get("PREVIOUS_AMT"));
        //assertEquals("Last Payment", tBilS[0].get("LAST_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("LAST_PAY_DATE"));
        assertEquals("0", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals("Reject (Cheque) - ", tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals("reject desc2", tBilS[0].get("REJECT_DESC"));
        Date rejectDate = format.parse(tBilS[0].get("REJECT_DATE").toString());
        assertEquals("2011-08-20", format.format(rejectDate));
        assertEquals("-26000", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("2000", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("2213.34", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));    
        
        //assertEquals(idRef2.trim(), tBilS[1].get("ID_REF").toString().trim());
        //assertEquals("2013.34", tBilS[1].get("PREVIOUS_AMT"));
		// assertEquals("Last Payment", tBilS[1].get("LAST_PAY_TYPE"));
		// assertEquals(null, tBilS[1].get("LAST_PAY_DATE"));
		// assertEquals("0", tBilS[1].get("LAST_PAY_AMT"));
		// assertEquals(null, tBilS[1].get("REJECT_PAY_TYPE"));
		// assertEquals(null, tBilS[1].get("REJECT_DESC"));
		// assertEquals(null, tBilS[1].get("REJECT_DATE"));
		// assertEquals("0", tBilS[1].get("REJECT_PAY_AMT"));
		// assertEquals("2013.34", tBilS[1].get("TOTAL_OUTSTANDING"));
		// assertEquals("2213.34", tBilS[1].get("TOTAL_AMT_DUE"));
		// assertEquals("0", tBilS[1].get("IS_DELETED"));
		// assertEquals(USER_ID, tBilS[1].get("ID_LOGIN"));

        // assert T_CSB_H table updated IS_EXPORT = 1
        Map<String, Object>[] tCsbH = super.select("T_CSB_H", null, null);
        assertEquals("1", tCsbH[0].get("IS_EXPORT"));
        assertEquals("1", tCsbH[1].get("IS_EXPORT"));
    }
    
    /**
     * Case BI5_04: E/1 Monthly Custom Plan/PRORATE_SVCEND(SVCEnd=BillTo)
     * 
     * Condition:<br>
     * BILL_FROM = 2 months ago<br>
     * BILL_TO = 2011-7-31<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = 2011-8-31<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = "2011-08-01"<br>
     * BILL_TO = 2011-8-31<br>
     * IS_RECURRING = 0
     * 
     * 1. Last Payment By ADVANCE RECEIPT
     * 2. No C Statement
     * CBUpdate = 1
     * BILUpdate = 0
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI504() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = "2001-08-31";
        String service_end = "2011-08-31";
        String last_bill_from = "2011-08-11";
        String last_bill_to = "2011-07-31";
        String bill_from = this.getNextDay(last_bill_to);
        String bill_to = "2011-08-31";
        String run_date = "2011-08-25";
        
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
                        "0", null, null, "M", null, "S", null, "0", null, "0",
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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", "1", null, "BA",
                        "PC", "2000", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
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

        // Generate invoice data in T_BIL_S/T_CSB_H.
        this.generatePreInvoce();
        this.generateTCSBH("N", "0", "1", "1");

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setBillAccountId(null);
        input.setRunning_date(format.parse(run_date));
        
        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", "ID_REF <> '11IV000158'", null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        
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
        assertEquals("200", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("1", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
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
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
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
        assertEquals("100", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("100", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("100", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("100", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
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
        assertEquals("0", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("2200", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", "ID_REF <> '11IV000158'", null);
        assertEquals(1, tBilS.length);
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("2000", tBilS[0].get("PREVIOUS_AMT"));
        //assertEquals("Last Payment By ADVANCE RECEIPT", tBilS[0].get("LAST_PAY_TYPE"));
        Date lastPayDate = format.parse(tBilS[0].get("LAST_PAY_DATE").toString());
        assertEquals("2011-08-08", format.format(lastPayDate));
        assertEquals("0", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals(null, tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("REJECT_DESC"));
        assertEquals(null, tBilS[0].get("REJECT_DATE"));
        assertEquals("0", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("2000", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("2200", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));

        // assert T_CSB_H table updated IS_EXPORT = 1
        Map<String, Object>[] tCsbH = super.select("T_CSB_H", null, null);
        assertEquals("1", tCsbH[0].get("IS_EXPORT"));
        assertEquals("1", tCsbH[1].get("IS_EXPORT"));
    }
    
    /**
     * Case BI5_05: E/1 Monthly Custom Plan/FULLPERIOD_MODE(In Service)
     * 
     * Condition:<br>
     * BILL_FROM = 1 months ago<br>
     * BILL_TO = The last day of the last month<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * T_CUST_PLAN_D.PRO_RATE_BASE = "U"<br>
     * T_CUST_PLAN_D.PRO_RATE_BASE_NO = 30<br>
     * T_BAC_H.IS_DISPLAY_EXP_AMT = 0<br>
     * 
     * T_BIL_H/T_BIL_S contains one record<br>
     * T_CSB_H contains 2 records<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM = the first day of the month<br>
     * BILL_TO = the last day of the month<br>
     * 
     * T_BIL_H.IS_DISPLAY_EXP_AMT = 0
     * 1. ADVANCE RECEIPT
     * 2. Paid Previous Invoice
     * CBUpdate = 1. T_CSB_H.IS_EXPORT 0 -> 1
     * BILUpdate = 0
     * 
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public void testExecuteBI505() throws Exception {
		String plan_status = "PS3";
		String service_status = "PS3";
		String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
		String service_end = null;

		String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
		String last_bill_to = getLastMonthLastDay(TEST_DAY_TODAY);
		String bill_from = getNextDay(last_bill_to);
		String bill_to = getMonthlyLastDay(bill_from);

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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
                        "PC", "2000", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
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

        // Generate invoice data in T_BIL_S/T_CSB_H.
        this.generatePreInvoce();
		String[][] tCsbH = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "ID_BILL_ACCOUNT", "DATE_TRANS",
						"RECEIPT_REF", "CUR_CODE", "AMT_RECEIVED", "REMARK",
						"PMT_STATUS", "BALANCE_AMT", "REFERENCE1",
						"REFERENCE2", "BANK_CHARGE", "REJECT_DATE",
						"REJECT_DESC", "PAID_PRE_INVOICE", "OVER_PAID",
						"IS_EXPORT", "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "999900", ID_CUST, null, null, "CQ", "1199999", "2011-07-08",
						null, "SGD", "12000", null, "N", "-900", "R1000001",
						"R2000001", "0", "2011-07-20", "reject desc1", "1",
						"0", "0", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						SYS_ADMIN, null },
				{ "999901", ID_CUST, null, null, "CQ", "1199999", "2011-08-08",
						null, "SGD", "14000", null, "N", "900", "R1100001",
						"R2200001", "0", "2011-08-20", "reject desc2", "0",
						"1", "1", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						SYS_ADMIN, null } };
		super.insertData("T_CSB_H", tCsbH);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", "ID_REF <> '11IV000158'", null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        
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
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
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
        assertEquals("100", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("100", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("100", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("100", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
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
        assertEquals("2200", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", "ID_REF <> '11IV000158'", null);
        assertEquals(1, tBilS.length);
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("2000", tBilS[0].get("PREVIOUS_AMT"));
        //assertEquals("Last Payment By ADVANCE RECEIPT", tBilS[0].get("LAST_PAY_TYPE"));
        Date lastPayDate = format.parse(tBilS[0].get("LAST_PAY_DATE").toString());
        assertEquals("2011-08-08", format.format(lastPayDate));
        assertEquals("0", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals("Paid Pre Invoice - ", tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals("R1000001", tBilS[0].get("REJECT_DESC"));
        Date rejectDate = format.parse(tBilS[0].get("REJECT_DATE").toString());
        assertEquals("2011-07-08", format.format(rejectDate));
        assertEquals("12000", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("2000", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("2200", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));
        
        // assert T_CSB_H table updated IS_EXPORT = 1
        Map<String, Object>[] tCsbH1 = super.select("T_CSB_H", null, null);
        assertEquals("1", tCsbH1[0].get("IS_EXPORT"));
        assertEquals("1", tCsbH1[1].get("IS_EXPORT"));
	}

    /**
     * Case BI5_06: E/1 Monthly Custom Plan/FULLPERIOD_MODE(In Service)
     * 
     * Condition:<br>
     * BILL_FROM = 1 months ago<br>
     * BILL_TO = The last day of the last month<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * T_BIL_H contains four records<br>
     * T_BIL_S contains no record<br>
     * T_CSB_H contains no record<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM = the first day of the month<br>
     * BILL_TO = the last day of the month<br>
     * 
     * 1. No payment case
     * 2. Revision Previous Invoice - 
     * CBUpdate = 0
     * BILUpdate = 1. T_BIL_H.IS_EXPORT 0 -> 1
     * 
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public void testExecuteBI506() throws Exception {
		String plan_status = "PS3";
		String service_status = "PS3";
		String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
		String service_end = null;
		String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
		String last_bill_to = getLastMonthLastDay(TEST_DAY_TODAY);
		String bill_from = getNextDay(last_bill_to);
		String bill_to = getMonthlyLastDay(bill_from);

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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
                        "PC", "2000", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
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

		// Generate invoice data in T_BIL_H
		String[][] tBilH = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
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
				{ "11IV000126", "DN", "0", "1199999", TEST_DAY_BEFORE_YESTERDAY, "CQ",
						ID_CUST, "BA", "PC", "SGD", "0", "0", "1200", "0",
						TEST_DAY_TODAY, "0", "1", "0", "0", "SGD", "1", "200",
						null, "0", null, null, "0",
						"PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
						"", "", "24300, Singapore", "24300", "Singapore",
						"(09) 8283327", "(09) 8283230", "Hiroshi Shiraga",
						"test@test.com", "0", SYS_ADMIN, TEST_DAY_TODAY,
						TEST_DAY_TODAY, SYS_ADMIN, null },
				{ "11IV000127", "DN", "0", "1199999", TEST_DAY_BEFORE_YESTERDAY, "CQ",
						ID_CUST, "BA", "PC", "SGD", "0", "0", "4200", "0",
						TEST_DAY_TODAY, "0", "1", "0", "0", "SGD", "1", "200",
						null, "0", null, null, "0",
						"PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
						"", "", "24300, Singapore", "24300", "Singapore",
						"(09) 8283327", "(09) 8283230", "Hiroshi Shiraga",
						"test@test.com", "0", SYS_ADMIN, TEST_DAY_TODAY,
						TEST_DAY_TODAY, SYS_ADMIN, null },
				{ "11IV000128", "CN", "0", "1199999", TEST_DAY_BEFORE_YESTERDAY, "CQ",
						ID_CUST, "BA", "PC", "SGD", "0", "0", "2200", "0",
						TEST_DAY_TODAY, "0", "1", "0", "0", "SGD", "1", "200",
						null, "0", null, null, "0",
						"PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
						"", "", "24300, Singapore", "24300", "Singapore",
						"(09) 8283327", "(09) 8283230", "Hiroshi Shiraga",
						"test@test.com", "0", SYS_ADMIN, TEST_DAY_TODAY,
						TEST_DAY_TODAY, SYS_ADMIN, null },
				{ "11IV000129", "CN", "0", "1199999", TEST_DAY_YESTERDAY, "CQ",
						ID_CUST, "BA", "PC", "SGD", "0", "0", "200", "0",
						TEST_DAY_TODAY, "0", "1", "0", "0", "SGD", "1", "200",
						null, "0", null, null, "0",
						"PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
						"", "", "24300, Singapore", "24300", "Singapore",
						"(09) 8283327", "(09) 8283230", "Hiroshi Shiraga",
						"test@test.com", "0", SYS_ADMIN, TEST_DAY_TODAY,
						TEST_DAY_TODAY, SYS_ADMIN, null } };
		super.insertData("T_BIL_H", tBilH);

        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", "ID_REF NOT IN ('11IV000126','11IV000127','11IV000128','11IV000129') ", null);
        assertTrue(tBilHlist != null && tBilHlist.length == 1);
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        
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
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
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
        assertEquals("100", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("100", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("100", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("100", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
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
        assertEquals("2200", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", "ID_REF <> '11IV000158'", null);
        assertEquals(1, tBilS.length);
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("0", tBilS[0].get("PREVIOUS_AMT"));
        //assertEquals("Last Payment", tBilS[0].get("LAST_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("LAST_PAY_DATE"));
        assertEquals("0", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals("Revision Previous Invoice - ", tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals("11IV000129", tBilS[0].get("REJECT_DESC").toString().trim());
        Date rejectDate = format.parse(tBilS[0].get("REJECT_DATE").toString());
        assertEquals(TEST_DAY_YESTERDAY, format.format(rejectDate));
        assertEquals("3000", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("2000", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("2200", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));
        
		// assert T_BIL_H table updated IS_EXPORT = 1
		Map<String, Object>[] tBilH1 = super.select("T_BIL_H",
						"ID_REF IN ('11IV000126','11IV000127','11IV000128','11IV000129') ",
						null);
		for (int i = 0; i < tBilH1.length; i++) {
			assertEquals("1", tBilH1[i].get("IS_EXPORT"));
		}
	}
	 /**
     * Case BI5_07: E/1 Monthly Custom Plan/FULLPERIOD_MODE(2 BillAcc)
     * 
     * Condition:<br>
     * 
     * 2 Billing Account/2 Custom Plan/2 Service/4 sub Service
     * 
     * BILL_FROM = 1 months ago<br>
     * BILL_TO = The last day of the last month<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     *  
     * One Sub Service contains RATE_MODE=6<br>
     *  
     * Result:<br>
     * Two data generated in T_BIL_H/Five data in T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM = the first day of the month<br>
     * BILL_TO = the last day of the month<br>
     * 
     * Two data in T_BIL_S<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI507() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = getLastMonthLastDay(TEST_DAY_TODAY);
        String bill_from = getMonthFirstDay(TEST_DAY_TODAY);
        String bill_to = getMonthlyLastDay(bill_from);

        String[][] dataCustPlanH = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { ID_CUST_PLAN, ID_CUST, plan_status, "SP", "S", "IN", ID_PLAN,
                        "1199998", "0", "1", "1", BILL_INSTRUCT, "CA",
                        DEF_CURRENCY, null, null, "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null },
                { ID_CUST_PLAN_2, ID_CUST, plan_status, "SP", "S", "IN", ID_PLAN,
                        "1199999", "0", "1", "1", BILL_INSTRUCT, "CA",
                        DEF_CURRENCY, null, null, "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_H", dataCustPlanH);

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null },
                { "2", ID_CUST_PLAN_2, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");
        this.updateBILL_DESC("2");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "O", null, "1", "200", "200", "JOBNO4", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "4",
                        "Bi-Monthly Fee", "1", "2", "BA", "4", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "2", "1", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "3", "2", "O", null, "1", "200", "200", "JOBNO4", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "4",
                        "Bi-Monthly Fee", "1", "2", "BA", "4", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "4", "2", "S", null, "1", "1000", "1000", "JOBNO6", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "6",
                        "One Time", "1", "2", "BA", "6", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
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
        HashMap<String, Object> param3 = new HashMap<String, Object>();
        param3.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param3.put("ID_CUST_PLAN_LINK", "3");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param3);
        HashMap<String, Object> param4 = new HashMap<String, Object>();
        param4.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param4.put("ID_CUST_PLAN_LINK", "4");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param4);

        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199998", ID_CUST, "CQ", DEF_CURRENCY, "1", null, null,
                        "BA", "PC", "2000", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null, null },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", null, null,
                        "BA", "PC", "2000", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199998", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        null, null, null, null, "ACM", "30Days",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "1199999", "2", "2", "1", "1", last_bill_from, last_bill_to,
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
        assertTrue(tBilHlist != null && tBilHlist.length == 2);
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        String idRef2 = CommonUtils.toString(tBilHlist[1].get("ID_REF"));
        
        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        assertEquals("1199998", tBilHlist[0].get("BILL_ACC").toString().trim());
        String dateReq = format.format(format.parse(tBilHlist[0].get("DATE_REQ").toString()));
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
        assertEquals("IN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("0", tBilHlist[0].get("IS_MANUAL"));
        
        assertEquals("1199999", tBilHlist[1].get("BILL_ACC").toString().trim());
        assertEquals(TEST_DAY_TODAY, dateReq);
        assertEquals("CQ", tBilHlist[1].get("PAY_METHOD").toString().trim());
        assertEquals(ID_CUST, tBilHlist[1].get("ID_CUST"));
        assertEquals("BA", tBilHlist[1].get("ADR_TYPE"));
        assertEquals("PC", tBilHlist[1].get("CONTACT_TYPE"));
        assertEquals("ACM", tBilHlist[1].get("ID_CONSULT"));
        assertEquals("30Days", tBilHlist[1].get("TERM"));
        assertEquals(DEF_CURRENCY, tBilHlist[1].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[1].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[1].get("GST_AMOUNT"));
        assertEquals("100", tBilHlist[1].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[1].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[1].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[1].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[1].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[1].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[1].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[1].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[1].get("CUR_RATE"));
        assertEquals("100", tBilHlist[1].get("EXPORT_AMOUNT"));
        assertEquals(null, tBilHlist[1].get("FIXED_FOREX"));
        assertEquals("0", tBilHlist[1].get("NO_PRINTED"));
        assertEquals(null, tBilHlist[1].get("DATE_PRINTED"));
        assertEquals(null, tBilHlist[1].get("USER_PRINTED"));
        assertEquals("0", tBilHlist[1].get("IS_CLOSED"));
        assertEquals("PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                tBilHlist[1].get("ADDRESS1"));
        assertEquals(null, tBilHlist[1].get("ADDRESS2"));
        assertEquals(null, tBilHlist[1].get("ADDRESS3"));
        assertEquals("24300, Singapore", tBilHlist[1].get("ADDRESS4").toString().trim());
        assertEquals("24300", tBilHlist[1].get("ZIP_CODE"));
        assertEquals("Singapore", tBilHlist[1].get("COUNTRY").toString().trim());
        assertEquals("(09) 8283327", tBilHlist[1].get("TEL_NO"));
        assertEquals("(09) 8283230", tBilHlist[1].get("FAX_NO"));
        assertEquals("Hiroshi Shiraga", tBilHlist[1].get("CONTACT_NAME"));
        assertEquals("test@test.com", tBilHlist[1].get("CONTACT_EMAIL"));
        assertEquals("0", tBilHlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilHlist[1].get("PREPARED_BY"));
        assertEquals(USER_ID, tBilHlist[1].get("ID_LOGIN"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ID_REF ASC, ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 5);
        
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
        assertEquals("0", tBilDlist[0].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[0].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[0].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[0].get("SVC_LEVEL2"));
        assertEquals(null, tBilDlist[0].get("JOB_NO"));
        assertEquals("0", tBilDlist[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[0].get("ID_LOGIN"));

        for (int i = 0; i < 5; i++) {
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
        assertEquals("100", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("100", tBilDlist[1].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
        assertEquals("1", tBilDlist[1].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
        assertEquals("2", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
        assertEquals("JOBNO5", tBilDlist[1].get("JOB_NO"));
        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
        assertEquals("3", tBilDlist[2].get("ITEM_ID"));
        assertEquals("1", tBilDlist[2].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[2].get("ITEM_QTY"));
        assertEquals("100", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("100", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
        assertEquals("1", tBilDlist[2].get("IS_DISPLAY"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[2].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[2].get("SVC_LEVEL2"));
        assertEquals("JOBNO4", tBilDlist[2].get("JOB_NO"));
        assertEquals("0", tBilDlist[2].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
     
        // T_BIL_D[3] is Header Information
        assertEquals("1", tBilDlist[3].get("ITEM_ID"));
        assertEquals("0", tBilDlist[3].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[3].get("ITEM_NO"));
        assertEquals("1", tBilDlist[3].get("ITEM_QTY"));
        assertEquals("100", tBilDlist[3].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[3].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[3].get("ITEM_GST"));
        assertEquals("0", tBilDlist[3].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[3].get("APPLY_GST"));
        assertEquals("0", tBilDlist[3].get("IS_DISPLAY"));
        assertEquals("2", tBilDlist[3].get("ID_CUST_PLAN"));
        assertEquals("2", tBilDlist[3].get("ID_CUST_PLAN_GRP"));
        assertEquals(null, tBilDlist[3].get("ID_CUST_PLAN_LINK"));
        assertEquals(null, tBilDlist[3].get("SVC_LEVEL1"));
        assertEquals(null, tBilDlist[3].get("SVC_LEVEL2"));
        assertEquals(null, tBilDlist[3].get("JOB_NO"));
        assertEquals("0", tBilDlist[3].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[3].get("ID_LOGIN"));

        // T_BIL_D[4] is generated from T_CUST_PLAN_LINK
        assertEquals("2", tBilDlist[4].get("ITEM_ID"));
        assertEquals("1", tBilDlist[4].get("ITEM_LEVEL"));
        assertEquals("1", tBilDlist[4].get("ITEM_QTY"));
        assertEquals("100", tBilDlist[4].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[4].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[4].get("ITEM_GST"));
        assertEquals("100", tBilDlist[4].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[4].get("APPLY_GST"));
        assertEquals("1", tBilDlist[4].get("IS_DISPLAY"));
        assertEquals("2", tBilDlist[4].get("ID_CUST_PLAN"));
        assertEquals("2", tBilDlist[4].get("ID_CUST_PLAN_GRP"));
        assertEquals("3", tBilDlist[4].get("ID_CUST_PLAN_LINK"));
        assertEquals("1", tBilDlist[4].get("SVC_LEVEL1"));
        assertEquals("2", tBilDlist[4].get("SVC_LEVEL2"));
        assertEquals("JOBNO4", tBilDlist[4].get("JOB_NO"));
        assertEquals("0", tBilDlist[4].get("IS_DELETED"));
        assertEquals(USER_ID, tBilDlist[4].get("ID_LOGIN"));
        
        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D", null, null);
        assertEquals(2, tBacD.length);
        for (int i = 0; i < tBacD.length; i++) {
            Date billfrom = format.parse(tBacD[i].get("BILL_FROM").toString());
            assertEquals(bill_from, format.format(billfrom));
            Date billTo = format.parse(tBacD[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo));
            assertEquals("1", tBacD[i].get("IS_RECURRING"));
        }

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H", null,
                "ID_BILL_ACCOUNT ASC");
        assertEquals("2200", tBacH[0].get("TOTAL_AMT_DUE"));
        assertEquals("2100", tBacH[1].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",null, null);
        assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));
        assertEquals("PS3", tCustPlanD[1].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H", null, null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));
        assertEquals("PS3", tCustPlanH[1].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(2, tBilS.length);
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals(idRef2.trim(), tBilS[1].get("ID_REF").toString().trim());
    }
    
    /**
     * Case BI5_08: E/7 Monthly Custom Plan/FULLPERIOD_MODE(AC_MANAGER/TERM)
     * 
     * Condition:<br>
     * BILL_FROM = 1 months ago<br>
     * BILL_TO = The last day of the last month<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/ 14 in T_BIL_D<br>
     * ID_BIF, ID_QCS, QUO_REF, CUST_PO, ID_CONSULT, JOB_NO, TERM is correct<br>
     * BILL_FROM = the first day of the month<br>
     * BILL_TO = the last day of the month<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI508() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = getLastMonthLastDay(TEST_DAY_TODAY);
        String bill_from = getMonthFirstDay(TEST_DAY_TODAY);
        String bill_to = getMonthlyLastDay(bill_from);

        String AC_MANAGER = "sysadministrat";
        String TERM = "ABCDEFGHIJKLMN";
        String ID_QCS = "IDQCS";
        String ID_QUO = "ID_QUO";
        String CUST_PO = "CUST_PO";
        String ID_BIF = "ID_BIF";

        String expectAC_MANAGER = "sysadministrat1;sysadministrat2;sysadministrat3;sysadministrat4;sysadministrat5;sysadministrat6;sysa";
        String expectTERM = "ABCDEFGHIJKLMN1;ABCDEFGHIJKLMN2;ABCDEFGHIJKLMN3;ABCDEFGHIJKLMN4;ABCDEFGHIJKLMN5;ABCDEFGHIJKLMN6;ABCD";
        
        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status, "DN");

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null },
                { "2", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null },
                { "3", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null },
                { "4", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null },
                { "5", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null },
                { "6", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null },
                { "7", ID_CUST_PLAN, service_status, service_start,
                        service_end, "1", "0", null, null, "M", null, "S",
                        null, "0", null, "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        this.updateBILL_DESC("1");
        this.updateBILL_DESC("2");
        this.updateBILL_DESC("3");
        this.updateBILL_DESC("4");
        this.updateBILL_DESC("5");
        this.updateBILL_DESC("6");
        this.updateBILL_DESC("7");

        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "2", "2", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "3", "3", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "4", "4", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "5", "5", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "6", "6", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null },
                { "7", "7", "S", null, "1", "100", "100", "JOBNO5", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
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
        param2.put("ID_CUST_PLAN_LINK", "3");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);
        param2.put("ID_CUST_PLAN_LINK", "4");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);
        param2.put("ID_CUST_PLAN_LINK", "5");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);
        param2.put("ID_CUST_PLAN_LINK", "6");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);
        param2.put("ID_CUST_PLAN_LINK", "7");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param2);

        String[][] bacH = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_SINGPOST", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT" },
                { "1199999", ID_CUST, "CQ", DEF_CURRENCY, "1", null, null, "BA",
                        "PC", "2000", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null, null } };
        super.insertData("T_BAC_H", bacH);

        String[][] bacD = {
                { "ID_BILL_ACCOUNT", "ID_CUST_PLAN_GRP", "ID_CUST_PLAN",
                        "IS_RECURRING", "IS_FIRST_BILL", "BILL_FROM",
                        "BILL_TO", "ID_BIF", "ID_QCS", "ID_QUO", "CUST_PO",
                        "AC_MANAGER", "TERM", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1199999", "1", "1", "1", "1", last_bill_from, last_bill_to,
                        ID_BIF + 1, ID_QCS + 1, ID_QUO + 1, CUST_PO + 1,
                        AC_MANAGER + 1, TERM + 1, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null },
                { "1199999", "2", "1", "1", "1", last_bill_from, last_bill_to,
                        ID_BIF + 2, ID_QCS + 2, ID_QUO + 2, CUST_PO + 2,
                        AC_MANAGER + 2, TERM + 2, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null },
                { "1199999", "3", "1", "1", "1", last_bill_from, last_bill_to,
                        ID_BIF + 3, ID_QCS + 3, ID_QUO + 3, CUST_PO + 3,
                        AC_MANAGER + 3, TERM + 3, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null },
                { "1199999", "4", "1", "1", "1", last_bill_from, last_bill_to,
                        ID_BIF + 4, ID_QCS + 4, ID_QUO + 4, CUST_PO + 4,
                        AC_MANAGER + 4, TERM + 4, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null },
                { "1199999", "5", "1", "1", "1", last_bill_from, last_bill_to,
                        ID_BIF + 5, ID_QCS + 5, ID_QUO + 5, CUST_PO + 5,
                        AC_MANAGER + 5, TERM + 5, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null },
                { "1199999", "6", "1", "1", "1", last_bill_from, last_bill_to,
                        ID_BIF + 6, ID_QCS + 6, ID_QUO + 6, CUST_PO + 6,
                        AC_MANAGER + 6, TERM + 6, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null },
                { "1199999", "7", "1", "1", "1", last_bill_from, last_bill_to,
                        ID_BIF + 7, ID_QCS + 7, ID_QUO + 7, CUST_PO + 7,
                        AC_MANAGER + 7, TERM + 7, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null } };
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
        assertEquals("DN", tBilHlist[0].get("BILL_TYPE"));
        assertEquals("700", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("700", tBilHlist[0].get("EXPORT_AMOUNT"));
        assertEquals(expectTERM, tBilHlist[0].get("TERM"));
        assertEquals(expectAC_MANAGER, tBilHlist[0].get("ID_CONSULT"));

        // Assert Clob value in T_BIL_H
        Map<String, Object> paramH = new HashMap<String, Object>();
        paramH.put("ID_REF", tBilHlist[0].get("ID_REF"));
        Map<String, Object> result = super.queryDAO.executeForMap(
                "TEST.G_BIL_P01.SELECT.SQL007", paramH);
        assertEquals("ID_BIF1;ID_BIF2;ID_BIF3;ID_BIF4;ID_BIF5;ID_BIF6;ID_BIF7",
                result.get("ID_BIF").toString());
        assertEquals(
                "CUST_PO1;CUST_PO2;CUST_PO3;CUST_PO4;CUST_PO5;CUST_PO6;CUST_PO7",
                result.get("CUST_PO").toString());
        assertEquals("IDQCS1;IDQCS2;IDQCS3;IDQCS4;IDQCS5;IDQCS6;IDQCS7", result
                .get("ID_QCS").toString());
        assertEquals("ID_QUO1;ID_QUO2;ID_QUO3;ID_QUO4;ID_QUO5;ID_QUO6;ID_QUO7",
                result.get("QUO_REF").toString());
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 14);
        
        for (int i = 0; i < 7; i++) {
            // T_BIL_D[0] is Header Information
            assertEquals(i + 1 + "", tBilDlist[0+i].get("ITEM_ID"));
            assertEquals("0", tBilDlist[0+i * 2].get("ITEM_LEVEL"));
            assertEquals(null, tBilDlist[0+i * 2].get("ITEM_NO"));
            assertEquals("1", tBilDlist[0+i * 2].get("ITEM_QTY"));
            assertEquals("100", tBilDlist[0+i * 2].get("ITEM_UPRICE"));
            assertEquals("100", tBilDlist[0+i * 2].get("ITEM_AMT"));
            assertEquals("0", tBilDlist[0+i * 2].get("ITEM_GST"));
            assertEquals("0", tBilDlist[0+i * 2].get("ITEM_EXPORT_AMT"));
            assertEquals("0", tBilDlist[0+i * 2].get("APPLY_GST"));
            assertEquals("0", tBilDlist[0+i * 2].get("IS_DISPLAY"));
            assertEquals("1", tBilDlist[0+i * 2].get("ID_CUST_PLAN"));
            assertEquals(i + 1 + "", tBilDlist[0+i * 2].get("ID_CUST_PLAN_GRP"));
            assertEquals(null, tBilDlist[0+i * 2].get("ID_CUST_PLAN_LINK"));
            assertEquals(null, tBilDlist[0+i * 2].get("SVC_LEVEL1"));
            assertEquals(null, tBilDlist[0+i * 2].get("SVC_LEVEL2"));
            assertEquals(null, tBilDlist[0+i * 2].get("JOB_NO"));
            assertEquals("0", tBilDlist[0+i * 2].get("IS_DELETED"));
            assertEquals(USER_ID, tBilDlist[0+i * 2].get("ID_LOGIN"));

            // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
            assertEquals(i*2 + 2 + "", tBilDlist[1+i * 2].get("ITEM_ID"));
            assertEquals("1", tBilDlist[1+i * 2].get("ITEM_LEVEL"));
            assertEquals("1", tBilDlist[1+i * 2].get("ITEM_QTY"));
            assertEquals("100", tBilDlist[1+i * 2].get("ITEM_UPRICE"));
            assertEquals("100", tBilDlist[1+i * 2].get("ITEM_AMT"));
            assertEquals("0", tBilDlist[1+i * 2].get("ITEM_GST"));
            assertEquals("100", tBilDlist[1+i * 2].get("ITEM_EXPORT_AMT"));
            assertEquals("0", tBilDlist[1+i * 2].get("APPLY_GST"));
            assertEquals("1", tBilDlist[1+i * 2].get("IS_DISPLAY"));
            assertEquals("1", tBilDlist[1+i * 2].get("ID_CUST_PLAN"));
            assertEquals(i + 1 + "", tBilDlist[1+i * 2].get("ID_CUST_PLAN_GRP"));
            assertEquals(i + 1 + "", tBilDlist[1+i * 2].get("ID_CUST_PLAN_LINK"));
            assertEquals("1", tBilDlist[1+i * 2].get("SVC_LEVEL1"));
            assertEquals("2", tBilDlist[1+i * 2].get("SVC_LEVEL2"));
            assertEquals("JOBNO5", tBilDlist[1+i * 2].get("JOB_NO"));
            assertEquals("0", tBilDlist[1+i * 2].get("IS_DELETED"));
            assertEquals(USER_ID, tBilDlist[1+i * 2].get("ID_LOGIN"));
        }

        for (int i = 0; i < 14; i++) {
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
                    .toString());
            assertEquals(bill_from, format.format(billfrom1));
            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo1));
        }

        // assert T_BAC_D.BILL_FROM/BILL_TO updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D", null, null);
        for (int i = 0; i < 7; i++) {
            Date billfrom = format.parse(tBacD[i].get("BILL_FROM").toString());
            assertEquals(bill_from, format.format(billfrom));
            Date billTo = format.parse(tBacD[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo));
            assertEquals("1", tBacD[i].get("IS_RECURRING"));
        }
        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H", null, null);
        assertEquals("2700", tBacH[0].get("TOTAL_AMT_DUE"));

        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D", null,
                null);
        for (int i = 0; i < 7; i++) {
            assertEquals("PS3", tCustPlanD[0].get("SERVICES_STATUS"));
        }
        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", tCustPlanH[0].get("PLAN_STATUS"));

        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(0, tBilS.length);
    }
    
    /**
     * Case BI5_09: E/1 Monthly Custom Plan/PRORATE_SVCSTART(ServiceEnd=BillTo)
     * 
     * Condition:<br>
     * BILL_FROM = null<br>
     * BILL_TO = null<br>
     * 
     * SVC_START = 2011-09-16<br>
     * SVC_END = 2011-09-30<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly)<br>
     * 
     * nDay = 15<br>
     * nMonth = 0<br>
     *  
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM = SVC_START<br>
     * BILL_TO = SVC_END<br>
     * 
     * One record in T_BIL_S.<br>
     * 1. No payment case<br>
     * 2. No C Statement<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI509() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = "2011-09-16";
        String service_end = "2011-09-30";
        String last_bill_from = null;
        String last_bill_to = null;
        String bill_from = service_start;
        String bill_to = service_end;
        
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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        
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
        assertEquals("100", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("100", tBilHlist[0].get("EXPORT_AMOUNT"));
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
        assertEquals("100", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[0].get("ITEM_AMT"));
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
        assertEquals("50", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("50", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("50", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("50", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("50", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("50", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
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
        assertEquals("0", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("100", tBacH[0].get("TOTAL_AMT_DUE"));
        
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
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("0", tBilS[0].get("PREVIOUS_AMT"));
        //assertEquals("Last Payment", tBilS[0].get("LAST_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("LAST_PAY_DATE"));
        assertEquals("0", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals(null, tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("REJECT_DESC"));
        assertEquals(null, tBilS[0].get("REJECT_DATE"));
        assertEquals("0", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("0", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("100", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));        
    }
    

    /**
     * Case BI5_10: E/1 Monthly Custom Plan/FULLPERIOD_MODE(ServiceEnd=BillTo)
     * 
     * Condition:<br>
     * BILL_FROM = null<br>
     * BILL_TO = null<br>
     * 
     * SVC_START = 2011-09-01<br>
     * SVC_END = 2011-09-30<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly)<br>
     * 
     * BILL_FROM=First Day of Month AND ServiceEnd=BillTo<br>
     * FULLPERIOD_MODE<br>
     *  
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM = SVC_START<br>
     * BILL_TO = SVC_END<br>
     * 
     * One record in T_BIL_S.<br>
     * 1. No payment case<br>
     * 2. No C Statement<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI510() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = "2011-09-01";
        String service_end = "2011-09-30";
        String last_bill_from = null;
        String last_bill_to = null;
        String bill_from = service_start;
        String bill_to = service_end;
        
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
                        "0", null, null, "M", null, "U", "31", "0", null, "0",
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
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "5",
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0",
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
        
        String idRef = CommonUtils.toString(tBilHlist[0].get("ID_REF"));
        
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
        assertTrue(tBilDlist != null && tBilDlist.length == 3);
        
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
        assertEquals("100", tBilDlist[1].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[1].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
        assertEquals("100", tBilDlist[1].get("ITEM_EXPORT_AMT"));
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
        assertEquals("100", tBilDlist[2].get("ITEM_UPRICE"));
        assertEquals("100", tBilDlist[2].get("ITEM_AMT"));
        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
        assertEquals("100", tBilDlist[2].get("ITEM_EXPORT_AMT"));
        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
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
        assertEquals("0", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE updated
        Map<String, Object>[] tBacH = super.select("T_BAC_H",
                "ID_BILL_ACCOUNT='1199999'", null);
        assertEquals("200", tBacH[0].get("TOTAL_AMT_DUE"));
        
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
        assertEquals(idRef.trim(), tBilS[0].get("ID_REF").toString().trim());
        assertEquals("0", tBilS[0].get("PREVIOUS_AMT"));
        //assertEquals("Last Payment", tBilS[0].get("LAST_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("LAST_PAY_DATE"));
        assertEquals("0", tBilS[0].get("LAST_PAY_AMT"));
        assertEquals(null, tBilS[0].get("REJECT_PAY_TYPE"));
        assertEquals(null, tBilS[0].get("REJECT_DESC"));
        assertEquals(null, tBilS[0].get("REJECT_DATE"));
        assertEquals("0", tBilS[0].get("REJECT_PAY_AMT"));
        assertEquals("0", tBilS[0].get("TOTAL_OUTSTANDING"));
        assertEquals("200", tBilS[0].get("TOTAL_AMT_DUE"));
        assertEquals("0", tBilS[0].get("IS_DELETED"));
        assertEquals(USER_ID, tBilS[0].get("ID_LOGIN"));        
    }
    
    /**
     * Generate old invoice
     */
    private void generatePreInvoce() {
        String[][] tBilH = {
                { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                        "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                        "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
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
                { "11IV000158", "IN", "0", "1199999", TEST_DAY_TODAY, "CQ", ID_CUST, "BA",
                        "PC", "SGD", "0", "0", "200", "0", TEST_DAY_TODAY, "0", "1", "0",
                        "0", "SGD", "1", "200", null, "0", null, null, "0",
                        "PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
                        "", "", "24300, Singapore", "24300", "Singapore",
                        "(09) 8283327", "(09) 8283230", "Hiroshi Shiraga",
                        "test@test.com", "0", SYS_ADMIN, TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BIL_H", tBilH);

        String[][] tBilS = {
                { "ID_REF", "PREVIOUS_AMT", "LAST_PAY_TYPE", "LAST_PAY_DATE",
                        "LAST_PAY_AMT", "REJECT_PAY_TYPE", "REJECT_DESC",
                        "REJECT_DATE", "REJECT_PAY_AMT", "TOTAL_OUTSTANDING",
                        "TOTAL_AMT_DUE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "11IV000158", "0", "Last Payment", null, "0", null, null,
                        null, "0", "2000", "2000", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_BIL_S", tBilS);
	}

	/**
	 * Generate T_CSB_H records.
	 * 
	 * @param pmtStatus
	 *            PMT_STATUS
	 * @param paidPreInvoice
	 *            PAID_PRE_INVOICE
	 * @param overPaid
	 *            OVER_PAID
	 * @param isExport
	 *            IS_EXPORT
	 */
    private void generateTCSBH(String pmtStatus, String paidPreInvoice,
            String overPaid, String isExport) {
        String[][] tCsbH = {
                { "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
                        "PMT_METHOD", "ID_BILL_ACCOUNT", "DATE_TRANS",
                        "RECEIPT_REF", "CUR_CODE", "AMT_RECEIVED", "REMARK",
                        "PMT_STATUS", "BALANCE_AMT", "REFERENCE1",
                        "REFERENCE2", "BANK_CHARGE", "REJECT_DATE",
                        "REJECT_DESC", "PAID_PRE_INVOICE", "OVER_PAID",
                        "IS_EXPORT", "IS_CLOSED", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "999900", ID_CUST, null, null, "CQ", "1199999", "2011-07-08",
                        null, "SGD", "12000", null, pmtStatus, "-900",
                        "R1000001", "R2000001", "0", "2011-07-20",
                        "reject desc1", paidPreInvoice, overPaid, isExport,
                        "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN,
                        null },
                { "999901", ID_CUST, null, null, "CQ", "1199999", "2011-08-08",
                        null, "SGD", "14000", null, pmtStatus, "900",
                        "R1100001", "R2200001", "0", "2011-08-20",
                        "reject desc2", paidPreInvoice, overPaid, isExport,
                        "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN,
                        null } };
        super.insertData("T_CSB_H", tCsbH);
    }
}
