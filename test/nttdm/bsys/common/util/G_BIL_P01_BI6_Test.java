/**
 * @(#)G_BIL_P01_BI6_Test.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-9-28

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
 * T_CUST_PLAN_H.BILL_INSTRUCT = 6(One Time)
 * 
 * @author bench
 */
public class G_BIL_P01_BI6_Test extends G_BIL_P01_Test {

	private static final String BILL_INSTRUCT = "6";

	/**
	 * Case BI6_01: E/1 One Time Custom Plan/FULLPERIOD_FULLAMT(SVC_END NULL)
	 * 
	 * Condition:<br>
	 * BILL_FROM = null<br>
	 * BILL_TO = null<br>
	 * 
	 * SVC_START = SYSDATE <br>
	 * SVC_END = null<br>
	 * PLAN_STATUS = PS3<br>
	 * SERVICES_STATUS = PS3<br>
	 * BILL_INSTRUCT = 6:0(One Time)<br>
	 * 
	 * Result:<br>
	 * One data generated in T_BIL_H/T_BIL_D<br>
	 * PLAN_STATUS = "PS5"<br>
	 * SERVICEs_STATUS = "PS5"<br>
	 * BILL_FROM = SVC_START<br>
	 * BILL_TO = BILL_FROM<br>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecuteBI601() throws Exception {
		String plan_status = "PS3";
		String service_status = "PS3";
		String service_start = TEST_DAY_TODAY;
		String service_end = null;
		String last_bill_from = null;
		String last_bill_to = null;
		String bill_from = TEST_DAY_TODAY;
		String bill_to = bill_from;
		
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
						"Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-1");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-2");
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
                        "ID_BIF001", "ID_QCS002", "ID_QUO003", "CUST_PO004",
                        "AC_MANAGER005", "TERM006", TEST_DAY_TODAY, TEST_DAY_TODAY,
                        SYS_ADMIN, null } };
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
        assertEquals("AC_MANAGER005", tBilHlist[0].get("ID_CONSULT"));
        assertEquals("TERM006", tBilHlist[0].get("TERM"));

        assertEquals(DEF_CURRENCY, tBilHlist[0].get("BILL_CURRENCY"));
        assertEquals("0", tBilHlist[0].get("GST_PERCENT"));
        assertEquals("0", tBilHlist[0].get("GST_AMOUNT"));
        assertEquals("300", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("300", tBilHlist[0].get("EXPORT_AMOUNT"));
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
        assertEquals("300", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("300", tBilDlist[0].get("ITEM_AMT"));
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
        
        Map<String, Object>[] tBilHClob = this.queryDAO.executeForMapArray(
                "TEST.G_BIL_P01.SELECT.SQL007", null);
        assertEquals("ID_BIF001", tBilHClob[0].get("ID_BIF"));
        assertEquals("ID_QCS002", tBilHClob[0].get("ID_QCS"));
        assertEquals("ID_QUO003", tBilHClob[0].get("QUO_REF"));
        assertEquals("CUST_PO004", tBilHClob[0].get("CUST_PO"));
        assertEquals("AC_MANAGER005", tBilHClob[0].get("ID_CONSULT"));
        assertEquals("TERM006", tBilHClob[0].get("TERM"));
        
		Map<String, Object>[] itemDesc = this.queryDAO.executeForMapArray(
				"TEST.G_BIL_P01.SELECT.SQL006", null);
		assertEquals("1 : BILL_DESC", itemDesc[0].get("ITEM_DESC"));
		assertEquals("1 : ITEM_DESC-1", itemDesc[1].get("ITEM_DESC"));
		assertEquals("1 : ITEM_DESC-2", itemDesc[2].get("ITEM_DESC"));
        
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
        assertEquals("300", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS5", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS5", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length);
	}
	
	/**
	 * Case BI6_02: E/1 One Time Custom Plan/FULLPERIOD_FULLAMT(SVC_END Not NULL)
	 * 
	 * Condition:<br>
	 * BILL_FROM = null<br>
	 * BILL_TO = null<br>
	 * 
	 * SVC_START = SYSDATE <br>
	 * SVC_END = One year later<br>
	 * PLAN_STATUS = PS3<br>
	 * SERVICES_STATUS = PS3<br>
	 * BILL_INSTRUCT = 6:0(One Time)<br>
	 * 
	 * Result:<br>
	 * One data generated in T_BIL_H/T_BIL_D<br>
	 * PLAN_STATUS = "PS5"<br>
	 * SERVICES_STATUS = PS5<br>
	 * BILL_FROM = SVC_START<br>
	 * BILL_TO = SVC_END<br>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecuteBI602() throws Exception {
		String plan_status = "PS3";
		String service_status = "PS3";
		String service_start = TEST_DAY_TODAY;
		String service_end = TEST_DAY_NEXTYEAR_TODAY;
		String last_bill_from = null;
		String last_bill_to = null;
		String bill_from = TEST_DAY_TODAY;
		String bill_to = TEST_DAY_NEXTYEAR_TODAY;

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
						"Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-1");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-2");
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
        assertEquals("300", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("300", tBilHlist[0].get("EXPORT_AMOUNT"));
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
        assertEquals("300", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("300", tBilDlist[0].get("ITEM_AMT"));
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

		Map<String, Object>[] itemDesc = this.queryDAO.executeForMapArray(
				"TEST.G_BIL_P01.SELECT.SQL006", null);
		assertEquals("1 : BILL_DESC", itemDesc[0].get("ITEM_DESC"));
		assertEquals("1 : ITEM_DESC-1", itemDesc[1].get("ITEM_DESC"));
		assertEquals("1 : ITEM_DESC-2", itemDesc[2].get("ITEM_DESC"));
        
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
        assertEquals("300", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS5", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS5", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(1, tBilS.length);
	}	
	
	/**
	 * Case BI6_03: E/1 One Time Custom Plan/FULLPERIOD_FULLAMT(PS5)
	 * 
	 * Condition:<br>
	 * BILL_FROM = TEST_DAY_YESTERDAY<br>
	 * BILL_TO = TEST_DAY_YESTERDAY<br>
	 * 
	 * SVC_START = SYSDATE <br>
	 * SVC_END = NULL<br>
	 * PLAN_STATUS = PS5<br>
	 * SERVICES_STATUS = PS5<br>
	 * BILL_INSTRUCT = 6:0(One Time)<br>
	 * 
	 * Result:<br>
	 * No data generated in T_BIL_H/T_BIL_D<br>
	 * CUST_PLAN_STATUS = "PS5"<br>
	 * SERVICES_STATUS = PS5<br>
	 * BILL_FROM = TEST_DAY_TODAY<br>
	 * BILL_TO = TEST_DAY_TODAY<br>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecuteBI603() throws Exception {
		String plan_status = "PS5";
		String service_status = "PS5";
		String service_start = TEST_DAY_YESTERDAY;
		String service_end = null;
		String last_bill_from = TEST_DAY_YESTERDAY;
		String last_bill_to = TEST_DAY_YESTERDAY;
		String bill_from = last_bill_from;
		String bill_to = last_bill_to;
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
						"Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-1");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-2");
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
        assertTrue(tBilHlist == null || tBilHlist.length == 0);
        
		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist == null || tBilDlist.length == 0);

		// assert T_BAC_D.BILL_FROM/BILL_TO not updated
        Map<String, Object>[] tBacD = super.select("T_BAC_D",
                "ID_CUST_PLAN_GRP='1' AND ID_BILL_ACCOUNT='1199999'", null);
        Date billfrom = format.parse(tBacD[0].get("BILL_FROM").toString());
        assertEquals(bill_from, format.format(billfrom));
        Date billTo = format.parse(tBacD[0].get("BILL_TO").toString());
        assertEquals(bill_to, format.format(billTo));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS5", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS5", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S data generated.
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(0, tBilS.length); 
	}

    /**
     * Case BI6_04: E/1 One Time Custom Plan/FULLPERIOD_FULLAMT(SVC_START > BillingMonth)
     * 
     * Condition:<br>
     * BILL_FROM = null<br>
     * BILL_TO = null<br>
     * 
     * SVC_START = TEST_DAY_NEXTYEAR_YESTERDAY <br>
     * SVC_END = TEST_DAY_NEXTYEAR_TODAY<br>
     * PLAN_STATUS = PS3<br>
     * SERVICES_STATUS = PS3<br>
     * BILL_INSTRUCT = 6:0(One Time)<br>
     * BILL_TYPE = CN<br>
     * 
     * Result:<br>
     * One data generated in T_BIL_H/T_BIL_D<br>
     * PLAN_STATUS = "PS5"<br>
     * SERVICES_STATUS = PS5<br>
     * BILL_FROM = SVC_START<br>
     * BILL_TO = SVC_END<br>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecuteBI604() throws Exception {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_NEXTYEAR_YESTERDAY;
        String service_end = TEST_DAY_NEXTYEAR_TODAY;
        String last_bill_from = null;
        String last_bill_to = null;
        String bill_from = service_start;
        String bill_to = service_end;

        this.generateCustPlanH(ID_CUST, BILL_INSTRUCT, plan_status, "CN");

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
                        "Monthly Fee", "1", "2", "BA", "5", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-1");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
        HashMap<String, Object> param2 = new HashMap<String, Object>();
        param2.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC-2");
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

        assertEquals("CN", tBilHlist[0].get("BILL_TYPE"));
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
        assertEquals("300", tBilHlist[0].get("BILL_AMOUNT"));
        assertEquals("0", tBilHlist[0].get("PAID_AMOUNT"));
        assertEquals(null, tBilHlist[0].get("LAST_PAYMENT_DATE"));
        assertEquals("0", tBilHlist[0].get("IS_SETTLED"));
        assertEquals("1", tBilHlist[0].get("IS_SINGPOST"));
        assertEquals("0", tBilHlist[0].get("IS_EXPORT"));
        assertEquals("0", tBilHlist[0].get("IS_DISPLAY_EXP_AMT"));
        assertEquals(DEF_CURRENCY, tBilHlist[0].get("EXPORT_CUR"));
        assertEquals("1", tBilHlist[0].get("CUR_RATE"));
        assertEquals("300", tBilHlist[0].get("EXPORT_AMOUNT"));
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
        assertEquals("300", tBilDlist[0].get("ITEM_UPRICE"));
        assertEquals("300", tBilDlist[0].get("ITEM_AMT"));
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

        Map<String, Object>[] itemDesc = this.queryDAO.executeForMapArray(
                "TEST.G_BIL_P01.SELECT.SQL006", null);
        assertEquals("1 : BILL_DESC", itemDesc[0].get("ITEM_DESC"));
        assertEquals("1 : ITEM_DESC-1", itemDesc[1].get("ITEM_DESC"));
        assertEquals("1 : ITEM_DESC-2", itemDesc[2].get("ITEM_DESC"));
        
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
        assertEquals("-300", tBacH[0].get("TOTAL_AMT_DUE"));
        
        // assert T_CUST_PLAN_D.SERVICES_STATUS not changed
        Map<String, Object>[] tCustPlanD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS5", tCustPlanD[0].get("SERVICES_STATUS"));

        // assert T_CUST_PLAN_H.PLAN_STATUS not changed
        Map<String, Object>[] tCustPlanH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS5", tCustPlanH[0].get("PLAN_STATUS"));
        
        // assert T_BIL_S NO data generated.(BILLTYPE=CN)
        Map<String, Object>[] tBilS = super.select("T_BIL_S", null, null);
        assertEquals(0, tBilS.length);
    }
}
