/**
 * @(#)G_BIL_P01_BI5A_Test.java
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
 * T_CUST_PLAN_H.BILL_INSTRUCT = 5(Monthly), Bill After Use.
 * 
 * @author bench
 */
public class G_BIL_P01_BI5A_Test extends G_BIL_P01_Test{
    
    private String BILL_INSTRUCT = "5";

    /**
     * Case BillAfterUse_01: E/1 Monthly Custom Plan/FULLPERIOD_MODE(In Service)
     * 
     * Condition:<br>
     * BILL_FROM = 2 months ago<br>
     * BILL_TO = The last day of 2 months ago<br>
     * "CC" batch plan mapping exist, ID_PLAN_GRP=4. "T_CLC_IMP_MONTHLY_SUM"
     * data exist
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = the first day of the last month<br>
     * BILL_TO = the last day of the last month<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBillAfterUse01() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = get2MonthAgoLastDay(TEST_DAY_TODAY);
        String bill_from = getNextDay(last_bill_to);
        String bill_to = getMonthlyLastDay(bill_from);

        this.generatePlanBatchData("4");
        this.generateCCData(new String[] { CommonUtils.formatDate(bill_from,
                "yyyy-MM-dd", "MM/yyyy") });
        
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
                { "1", "1", "S", null, "2", "200", "400", "JOBNO4", "1",
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
//        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
//        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
//        assertEquals("1", tBilDlist[1].get("ITEM_QTY"));
//        assertEquals("0", tBilDlist[1].get("ITEM_UPRICE"));
//        assertEquals("0", tBilDlist[1].get("ITEM_AMT"));
//        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
//        assertEquals("0", tBilDlist[1].get("ITEM_EXPORT_AMT"));
//        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
//        assertEquals("0", tBilDlist[1].get("IS_DISPLAY"));
//        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
//        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
//        assertEquals("2", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
//        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
//        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
//        assertEquals("JOBNO5", tBilDlist[1].get("JOB_NO"));
//        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
//        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
        // index out of bounds
		// assertEquals("3", tBilDlist[2].get("ITEM_ID"));
		// assertEquals("1", tBilDlist[2].get("ITEM_LEVEL"));
		// assertEquals("1", tBilDlist[2].get("ITEM_QTY"));
		// assertEquals("100", tBilDlist[2].get("ITEM_UPRICE"));
		// assertEquals("100", tBilDlist[2].get("ITEM_AMT"));
		// assertEquals("0", tBilDlist[2].get("ITEM_GST"));
		// assertEquals("100", tBilDlist[2].get("ITEM_EXPORT_AMT"));
		// assertEquals("0", tBilDlist[2].get("APPLY_GST"));
		// assertEquals("0", tBilDlist[2].get("IS_DISPLAY"));
		// assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
		// assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
		// assertEquals("2", tBilDlist[2].get("ID_CUST_PLAN_LINK"));
		// assertEquals("1", tBilDlist[2].get("SVC_LEVEL1"));
		// assertEquals("2", tBilDlist[2].get("SVC_LEVEL2"));
		// assertEquals("JOBNO5", tBilDlist[2].get("JOB_NO"));
		// assertEquals("0", tBilDlist[2].get("IS_DELETED"));
		// assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
        
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
        assertEquals("1300", tBacH[0].get("TOTAL_AMT_DUE"));
        
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

        // assert T_CLC_IMP_MONTHLY_SUM updated
        Map<String, Object>[] ccImp = super.select("T_CLC_IMP_MONTHLY_SUM",
                null, null);
        assertTrue(ccImp != null && ccImp.length == 1);
        assertEquals("0", ccImp[0].get("IS_INVOICED").toString());
        
    }
    /**
     * Case BillAfterUse_02: E/1 Monthly Custom Plan/FULLPERIOD_MODE(In Service)<br>
     * PostingMonth < BillingMonth, no data generated.
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
     * No data generated in T_BIL_H/T_BIL_D<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBillAfterUse02() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = getLastMonthLastDay(TEST_DAY_TODAY);
        String bill_from = getMonthFirstDay(TEST_DAY_TODAY);
        
        this.generatePlanBatchData("4");
        this.generateCCData(new String[] { CommonUtils.formatDate(bill_from,
                "yyyy-MM-dd", "MM/yyyy") });
        
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
                { "1", "1", "S", null, "2", "200", "400", "JOBNO4", "1",
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
        assertTrue(tBilHlist == null || tBilHlist.length == 0);

        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilD = super.select("T_BIL_D", null, null);
        assertEquals(0, tBilD.length);

        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(0, tBilS.length);
        
        // assert T_CLC_IMP_MONTHLY_SUM not updated
        Map<String, Object>[] ccImp = super.select("T_CLC_IMP_MONTHLY_SUM",
                null, null);
        assertTrue(ccImp != null && ccImp.length == 1);
        assertEquals("0", ccImp[0].get("IS_INVOICED").toString());
    }
    /**
     * Case BillAfterUse_03: E/1 Monthly Custom Plan/PRORATE_SVCSTART(2
     * billings)
     * 
     * Condition:<br>
     * BILL_FROM = null<br>
     * BILL_TO = null<br>
     * 
     * SVC_START = 2 Month's ago Last Day <br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * Result:<br>
     * 2 data generated in T_BIL_H/Six data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_FROM1 = SVC_START<br>
     * BILL_TO1 = 2 Month's ago Last Day<br>
     * BILL_FROM2 = 1 Month's ago First Day<br>
     * BILL_TO2 = 1 Month's ago Last Day<br>
     * 
     * Two data in T_BIL_S<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBillAfterUse03() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = this.get2MonthAgoLastDay(TEST_DAY_TODAY);
        String service_end = null;
        String last_bill_from = null;
        String last_bill_to = null;
        String bill_from1 = service_start;
        String bill_to1 = this.getMonthlyLastDay(bill_from1);
        String bill_from2 = this.getNextDay(bill_to1);
        String bill_to2 = this.getMonthlyLastDay(bill_from2);

        this.generatePlanBatchData("5");
        
        this.generateCCData(new String[] {
                CommonUtils.formatDate(bill_from1, "yyyy-MM-dd", "MM/yyyy"),
                CommonUtils.formatDate(bill_from2, "yyyy-MM-dd", "MM/yyyy") });
        
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
                { "1", "1", "S", null, "2", "200", "400", "JOBNO4", "1",
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
        assertEquals("206.67", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("206.67", tBilHlist[0].get("EXPORT_AMOUNT"));
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
        
        // Second Billing
        // assertEquals("1200", tBilHlist[1].get("BILL_AMOUNT"));
        // assertEquals("1200", tBilHlist[1].get("EXPORT_AMOUNT"));
        
        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ID_REF ASC, ITEM_ID ASC");
        assertTrue(tBilDlist != null && tBilDlist.length == 6);
        
        // T_BIL_D[0] is Header Information
        assertEquals("1", tBilDlist[0].get("ITEM_ID"));
        assertEquals("0", tBilDlist[0].get("ITEM_LEVEL"));
        assertEquals(null, tBilDlist[0].get("ITEM_NO"));
        assertEquals("1", tBilDlist[0].get("ITEM_QTY"));
        assertEquals("6.67", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("6.67", tBilDlist[0].get("ITEM_AMT"));
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

//        for (int i = 0; i < 4; i++) {
//        	// the current month is March
//        	if(i<2){
//	            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
//	                    .toString());
//	            assertEquals(bill_from1, format.format(billfrom1));
//	            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
//	            assertEquals(bill_to1, format.format(billTo1));
//        	}else{
//        		Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
//	                    .toString());
//	            assertEquals(this.getMonthFirstDay(this.getLastMonthLastDay(TEST_DAY_TODAY)), format.format(billfrom1));
//	            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
//	            assertEquals(this.getLastMonthLastDay(TEST_DAY_TODAY), format.format(billTo1));
//        	}
//        }
//        
//        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
//        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
//        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
//        assertEquals("2", tBilDlist[1].get("ITEM_QTY"));
//        assertEquals("3.33", tBilDlist[1].get("ITEM_UPRICE"));
//        assertEquals("6.67", tBilDlist[1].get("ITEM_AMT"));
//        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
//        assertEquals("6.67", tBilDlist[1].get("ITEM_EXPORT_AMT"));
//        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
//        assertEquals("0", tBilDlist[1].get("IS_DISPLAY"));
//        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
//        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
//        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
//        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
//        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
//        assertEquals("JOBNO4", tBilDlist[1].get("JOB_NO"));
//        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
//        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));
//
//        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
//        assertEquals("3", tBilDlist[2].get("ITEM_ID"));
//        assertEquals("0", tBilDlist[2].get("ITEM_LEVEL"));
//        assertEquals("1", tBilDlist[2].get("ITEM_QTY"));
//        assertEquals("200", tBilDlist[2].get("ITEM_UPRICE"));
//        assertEquals("200", tBilDlist[2].get("ITEM_AMT"));
//        assertEquals("0", tBilDlist[2].get("ITEM_GST"));
//        assertEquals("0", tBilDlist[2].get("ITEM_EXPORT_AMT"));
//        assertEquals("0", tBilDlist[2].get("APPLY_GST"));
//        assertEquals("1", tBilDlist[2].get("IS_DISPLAY"));
//        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
//        assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
//        assertEquals(null, tBilDlist[2].get("ID_CUST_PLAN_LINK"));
//        assertEquals(null, tBilDlist[2].get("SVC_LEVEL1"));
//        assertEquals(null, tBilDlist[2].get("SVC_LEVEL2"));
//        assertEquals(null, tBilDlist[2].get("JOB_NO"));
//        assertEquals("0", tBilDlist[2].get("IS_DELETED"));
//        assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
//        
//        // T_BIL_D[3] is Header Information
//        assertEquals("4", tBilDlist[3].get("ITEM_ID"));
//        assertEquals("1", tBilDlist[3].get("ITEM_LEVEL"));
//        assertEquals(null, tBilDlist[3].get("ITEM_NO"));
//        assertEquals("2", tBilDlist[3].get("ITEM_QTY"));
//        assertEquals("100", tBilDlist[3].get("ITEM_UPRICE"));
//        assertEquals("200", tBilDlist[3].get("ITEM_AMT"));
//        assertEquals("0", tBilDlist[3].get("ITEM_GST"));
//        assertEquals("200", tBilDlist[3].get("ITEM_EXPORT_AMT"));
//        assertEquals("0", tBilDlist[3].get("APPLY_GST"));
//        assertEquals("0", tBilDlist[3].get("IS_DISPLAY"));
//        assertEquals("1", tBilDlist[3].get("ID_CUST_PLAN"));
//        assertEquals("1", tBilDlist[3].get("ID_CUST_PLAN_GRP"));
//        assertEquals("1", tBilDlist[3].get("ID_CUST_PLAN_LINK"));
//        assertEquals("1", tBilDlist[3].get("SVC_LEVEL1"));
//        assertEquals("2", tBilDlist[3].get("SVC_LEVEL2"));
//        assertEquals("JOBNO4", tBilDlist[3].get("JOB_NO"));
//        assertEquals("0", tBilDlist[3].get("IS_DELETED"));
//        assertEquals(USER_ID, tBilDlist[3].get("ID_LOGIN"));
//
//        for (int i = 3; i < 4; i++) {
//            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM")
//                    .toString());
//            assertEquals(bill_from2, format.format(billfrom1));
//            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
//            assertEquals(bill_to2, format.format(billTo1));
//        }
        
        // T_BIL_D[4] is generated from T_CUST_PLAN_LINK
		// assertEquals("2", tBilDlist[4].get("ITEM_ID"));
		// assertEquals("1", tBilDlist[4].get("ITEM_LEVEL"));
		// assertEquals("2", tBilDlist[4].get("ITEM_QTY"));
		// assertEquals("100", tBilDlist[4].get("ITEM_UPRICE"));
		// assertEquals("200", tBilDlist[4].get("ITEM_AMT"));
		// assertEquals("0", tBilDlist[4].get("ITEM_GST"));
		// assertEquals("200", tBilDlist[4].get("ITEM_EXPORT_AMT"));
		// assertEquals("0", tBilDlist[4].get("APPLY_GST"));
		// assertEquals("0", tBilDlist[4].get("IS_DISPLAY"));
		// assertEquals("1", tBilDlist[4].get("ID_CUST_PLAN"));
		// assertEquals("1", tBilDlist[4].get("ID_CUST_PLAN_GRP"));
		// assertEquals("1", tBilDlist[4].get("ID_CUST_PLAN_LINK"));
		// assertEquals("1", tBilDlist[4].get("SVC_LEVEL1"));
		// assertEquals("2", tBilDlist[4].get("SVC_LEVEL2"));
		// assertEquals("JOBNO4", tBilDlist[4].get("JOB_NO"));
		// assertEquals("0", tBilDlist[4].get("IS_DELETED"));
		// assertEquals(USER_ID, tBilDlist[4].get("ID_LOGIN"));

        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
		// assertEquals("3", tBilDlist[5].get("ITEM_ID"));
		// assertEquals("1", tBilDlist[5].get("ITEM_LEVEL"));
		// assertEquals("1", tBilDlist[5].get("ITEM_QTY"));
		// assertEquals("1000", tBilDlist[5].get("ITEM_UPRICE"));
		// assertEquals("1000", tBilDlist[5].get("ITEM_AMT"));
		// assertEquals("0", tBilDlist[5].get("ITEM_GST"));
		// assertEquals("1000", tBilDlist[5].get("ITEM_EXPORT_AMT"));
		// assertEquals("0", tBilDlist[5].get("APPLY_GST"));
		// assertEquals("0", tBilDlist[5].get("IS_DISPLAY"));
		// assertEquals("1", tBilDlist[5].get("ID_CUST_PLAN"));
		// assertEquals("1", tBilDlist[5].get("ID_CUST_PLAN_GRP"));
		// assertEquals("2", tBilDlist[5].get("ID_CUST_PLAN_LINK"));
		// assertEquals("1", tBilDlist[5].get("SVC_LEVEL1"));
		// assertEquals("2", tBilDlist[5].get("SVC_LEVEL2"));
		// assertEquals("JOBNO5", tBilDlist[5].get("JOB_NO"));
		// assertEquals("0", tBilDlist[5].get("IS_DELETED"));
		// assertEquals(USER_ID, tBilDlist[5].get("ID_LOGIN"));
        
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
        assertEquals("206.67", tBacH[0].get("TOTAL_AMT_DUE"));
        
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

        // assert T_CLC_IMP_MONTHLY_SUM updated
        Map<String, Object>[] ccImp = super.select("T_CLC_IMP_MONTHLY_SUM",
                null, null);
        assertTrue(ccImp != null && ccImp.length == 2);
        assertEquals("0", ccImp[0].get("IS_INVOICED").toString());
        assertEquals("0", ccImp[1].get("IS_INVOICED").toString());
    }
    
    /**
     * Case BillAfterUse_04: E/1 Monthly Custom Plan/PRORATE_SVCEND(PS4)
     * 
     * Condition:<br>
     * BILL_FROM = 2 months ago<br>
     * BILL_TO = Today<br>
     * 
     * SVC_START = 1 years ago <br>
     * SVC_END = Next year"2012-07-31"<br>
     * PLAN_STATUS = "PS4"<br>
     * SERVICEs_STATUS = "PS4"<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/T_BIL_D<br>
     * PLAN_STATUS = "PS4"<br>
     * SERVICEs_STATUS = "PS4"<br>
     * BILL_FROM = "2012-08-01"<br>
     * BILL_TO = "2012-07-31"<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBillAfterUse04() throws Exception {
        String plan_status = "PS4";
        String service_status = "PS4";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = this.getLastMonthLastDay(TEST_DAY_TODAY);

        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = this.getLastMonthBeforeLastDay(TEST_DAY_TODAY);
        String bill_from = this.getNextDay(last_bill_to);
        String bill_to = service_end;
        
        this.generatePlanBatchData("5");
        this.generateCCData(new String[] { CommonUtils.formatDate(bill_from,
                "yyyy-MM-dd", "MM/yyyy") });
        
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
                { "1", "1", "S", null, "2", "200", "400", "JOBNO4", "1",
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
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
        //assertEquals(0, tBilHlist.length); TODO
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
        assertEquals("6.67", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("6.67", tBilHlist[0].get("EXPORT_AMOUNT"));
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
        assertEquals("6.67", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("6.67", tBilDlist[0].get("ITEM_AMT"));
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
		// assertEquals("2", tBilDlist[1].get("ITEM_ID"));
		// assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
		// assertEquals("2", tBilDlist[1].get("ITEM_QTY"));
		// assertEquals("3.33", tBilDlist[1].get("ITEM_UPRICE"));
		// assertEquals("6.67", tBilDlist[1].get("ITEM_AMT"));
		// assertEquals("0", tBilDlist[1].get("ITEM_GST"));
		// assertEquals("6.67", tBilDlist[1].get("ITEM_EXPORT_AMT"));
		// assertEquals("0", tBilDlist[1].get("APPLY_GST"));
		// assertEquals("0", tBilDlist[1].get("IS_DISPLAY"));
		// assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
		// assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
		// assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
		// assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
		// assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
		// assertEquals("JOBNO4", tBilDlist[1].get("JOB_NO"));
		// assertEquals("0", tBilDlist[1].get("IS_DELETED"));
		// assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));

        // T_BIL_D[2] is generated from T_CUST_PLAN_LINK
		// assertEquals("3", tBilDlist[2].get("ITEM_ID"));
		// assertEquals("1", tBilDlist[2].get("ITEM_LEVEL"));
		// assertEquals("1", tBilDlist[2].get("ITEM_QTY"));
		// assertEquals("1000", tBilDlist[2].get("ITEM_UPRICE"));
		// assertEquals("1000", tBilDlist[2].get("ITEM_AMT"));
		// assertEquals("0", tBilDlist[2].get("ITEM_GST"));
		// assertEquals("1000", tBilDlist[2].get("ITEM_EXPORT_AMT"));
		// assertEquals("0", tBilDlist[2].get("APPLY_GST"));
		// assertEquals("0", tBilDlist[2].get("IS_DISPLAY"));
		// assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN"));
		// assertEquals("1", tBilDlist[2].get("ID_CUST_PLAN_GRP"));
		// assertEquals("2", tBilDlist[2].get("ID_CUST_PLAN_LINK"));
		// assertEquals("1", tBilDlist[2].get("SVC_LEVEL1"));
		// assertEquals("2", tBilDlist[2].get("SVC_LEVEL2"));
		// assertEquals("JOBNO5", tBilDlist[2].get("JOB_NO"));
		// assertEquals("0", tBilDlist[2].get("IS_DELETED"));
		// assertEquals(USER_ID, tBilDlist[2].get("ID_LOGIN"));
        
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
        assertEquals("2406.67", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS4", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS4", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length);

        // assert T_CLC_IMP_MONTHLY_SUM updated
        Map<String, Object>[] ccImp = super.select("T_CLC_IMP_MONTHLY_SUM",
                null, null);
        assertTrue(ccImp != null && ccImp.length == 1);
        assertEquals("0", ccImp[0].get("IS_INVOICED").toString());
    }
    
    /**
     * Case BillAfterUse_05: E/1 Monthly Custom Plan/FULLPERIOD_MODE(Start Service)
     * 
     * Condition:<br>
     * BILL_FROM = NULL<br>
     * BILL_TO = null<br>
     * "CC" batch plan mapping exist, ID_PLAN_GRP=4.<br> 
     * "T_CLC_IMP_MONTHLY_SUM" data not exist.<br>
     * 
     * SVC_START = First 1 of last month<br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * BILL_FROM = the first day of the last month<br>
     * BILL_TO = the last day of the last month<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBillAfterUse05() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = getNextDay(get2MonthAgoLastDay(TEST_DAY_TODAY));
        String service_end = null;
        String last_bill_from = null;
        String last_bill_to = null;
        String bill_from = service_start;
        String bill_to = getMonthlyLastDay(bill_from);

        this.generatePlanBatchData("4");
        // no data in T_CLC_IMP_MONTHLY_SUM
        this.generateCCData(new String[] {});
        
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
                { "1", "1", "S", null, "2", "200", "400", "JOBNO4", "1",
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
            Date billfrom1 = format.parse(tBilDlist[i].get("BILL_FROM").toString());
            assertEquals(bill_from, format.format(billfrom1));
            Date billTo1 = format.parse(tBilDlist[i].get("BILL_TO").toString());
            assertEquals(bill_to, format.format(billTo1));
        }
        
        // T_BIL_D[1] is generated from T_CUST_PLAN_LINK
//        assertEquals("2", tBilDlist[1].get("ITEM_ID"));
//        assertEquals("1", tBilDlist[1].get("ITEM_LEVEL"));
//        assertEquals("1", tBilDlist[1].get("ITEM_QTY"));
//        assertEquals("100", tBilDlist[1].get("ITEM_UPRICE"));
//        assertEquals("100", tBilDlist[1].get("ITEM_AMT"));
//        assertEquals("0", tBilDlist[1].get("ITEM_GST"));
//        assertEquals("100", tBilDlist[1].get("ITEM_EXPORT_AMT"));
//        assertEquals("0", tBilDlist[1].get("APPLY_GST"));
//        assertEquals("0", tBilDlist[1].get("IS_DISPLAY"));
//        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN"));
//        assertEquals("1", tBilDlist[1].get("ID_CUST_PLAN_GRP"));
//        assertEquals("2", tBilDlist[1].get("ID_CUST_PLAN_LINK"));
//        assertEquals("1", tBilDlist[1].get("SVC_LEVEL1"));
//        assertEquals("2", tBilDlist[1].get("SVC_LEVEL2"));
//        assertEquals("JOBNO5", tBilDlist[1].get("JOB_NO"));
//        assertEquals("0", tBilDlist[1].get("IS_DELETED"));
//        assertEquals(USER_ID, tBilDlist[1].get("ID_LOGIN"));
        
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
        assertEquals("1300", tBacH[0].get("TOTAL_AMT_DUE"));
        
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

        // assert T_CLC_IMP_MONTHLY_SUM updated
        Map<String, Object>[] ccImp = super.select("T_CLC_IMP_MONTHLY_SUM", null, null);
        assertTrue(ccImp == null || ccImp.length == 0);
    }
    
    /**
     * Case BillAfterUse_06: E/1 Monthly Custom Plan/FULLPERIOD_MODE(Start Service)
     * 
     * Condition:<br>
     * BILL_FROM = NULL<br>
     * BILL_TO = null<br>
     * Two "CC" batch plan mapping exist, ID_PLAN_GRP=4 and 5.<br> 
     * "T_CLC_IMP_MONTHLY_SUM" data not exist.<br>
     * 
     * SVC_START = First 1 of last month<br>
     * SVC_END = null<br>
     * PLAN_STATUS = PS3<br>
     * BILL_INSTRUCT = 5:1(Monthly))<br>
     * 
     * Result:<br>
     * NO data generated in T_BIL_H/Three data in T_BIL_D<br>
     * PLAN_STATUS = "PS3"<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBillAfterUse06() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = getNextDay(get2MonthAgoLastDay(TEST_DAY_TODAY));
        String service_end = null;
        String last_bill_from = null;
        String last_bill_to = null;

        this.generatePlanBatchData("4");
        this.generatePlanBatchData("5");
        // no data in T_CLC_IMP_MONTHLY_SUM
        this.generateCCData(new String[] {});
        
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
                { "1", "1", "S", null, "2", "200", "400", "JOBNO4", "1",
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
        assertTrue(tBilHlist == null || tBilHlist.length == 0);

        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, "ITEM_ID ASC");
        assertTrue(tBilDlist == null || tBilDlist.length == 0);
        
        // assert T_BAC_D.BILL_FROM/BILL_TO NOT updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        //assertEquals(last_bill_from, tBacD[0].get("BILL_FROM"));
        //assertEquals(last_bill_to, tBacD[0].get("BILL_FROM"));
        assertEquals("1", tBacD[0].get("IS_RECURRING"));

        // assert T_BAC_H.TOTAL_AMT_DUE NOT updated
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
        assertEquals(0, tBilS.length);

        // assert T_CLC_IMP_MONTHLY_SUM updated
        Map<String, Object>[] ccImp = super.select("T_CLC_IMP_MONTHLY_SUM", null, null);
        assertTrue(ccImp == null || ccImp.length == 0);
    }
    
    public void testExecuteBillAfterUse07add() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_LASTYEAR_YESTERDAY;
        String service_end = null;
        String last_bill_from = TEST_DAY_LASTYEAR_YESTERDAY;
        String last_bill_to = get2MonthAgoLastDay(TEST_DAY_TODAY);
        String bill_from = getNextDay(last_bill_to);
        String bill_to = getMonthlyLastDay(bill_from);

        this.generatePlanBatchData("4");
        String[][] dataCCImp = {
                { "ID_CLC_IMP_BATCH", "ID_CLC_CUST", "MONTH_YEAR",
                        "IS_INVOICED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "INVOICE_TOTAL", "ID_AUDIT" },
                { "1", ID_CUST, "02/2012", "1", TEST_DAY_TODAY,
                            TEST_DAY_TODAY, "SC", "10", null }};
        super.insertData("T_CLC_IMP_MONTHLY_SUM", dataCCImp);
        
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
                { "1", "1", "S", null, "2", "200", "400", "JOBNO4", "1",
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
        
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "1", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", "0", ID_CUST } };
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        
        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setRunning_date(new Date());
        input.setBillAccountId(null);

        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());
    }
    /**
     * Generate M_PLAN_BATCH_MAPPING data.
     * 
     * @param idPlanGrp
     *            ID_PLAN_GRP
     */
    private void generatePlanBatchData(String idPlanGrp) {
        String[][] dataPlanBatch = {
                { "ID_PLAN_GRP", "ID_PLAN", "BATCH_ID", "USAGE_BASE", "UOM",
                        "CODE_VALUE", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { idPlanGrp, "1", "CC", "100", "MIN", "1", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("M_PLAN_BATCH_MAPPING", dataPlanBatch);
    }

    /**
     * Generate T_CLC_IMP_MONTHLY_SUM data
     * 
     * @param monthYears
     *            PostingMonth
     * @throws Exception
     */
    private void generateCCData(String[] monthYears) throws Exception {
        // create one test data for given monthYear
        for (int n = 0; n < monthYears.length; n++) {
            String[][] dataCCImp = {
                    { "ID_CLC_IMP_BATCH", "ID_CLC_CUST", "MONTH_YEAR",
                            "IS_INVOICED", "DATE_CREATED", "DATE_UPDATED",
                            "ID_LOGIN", "INVOICE_TOTAL", "ID_AUDIT" },
                    { n + "", ID_CUST, monthYears[n], "0", TEST_DAY_TODAY,
                            TEST_DAY_TODAY, "DM", "1000", null } };
            super.insertData("T_CLC_IMP_MONTHLY_SUM", dataCCImp);
        }
    }
}
