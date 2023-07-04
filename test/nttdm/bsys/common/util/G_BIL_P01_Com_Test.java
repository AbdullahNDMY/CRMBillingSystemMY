/**
 * @(#)G_BIL_P01_Com_Test.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-09-19

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.bean.G_BIL_P01_Input;
import nttdm.bsys.common.bean.G_BIL_P01_Output;
import nttdm.bsys.m_alt.bean.Notice;

/**
 * Test Class for nttdm.bsys.common.util.G_BIL_P01<br>
 * NO DATA/Exception/Success and failure log Test
 * 
 * @author bench
 */
public class G_BIL_P01_Com_Test extends G_BIL_P01_Test {

	/**
	 * Case 1: E/NO DATA
	 * 
	 * Condition:<br/>
	 * ModuleId = "E"<br/>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK no record<br/>
	 * GB batch alert user = sysadmin
	 * 
	 * Result:<br/>
	 * No data generated in T_BIL_H/T_BIL_D<br/>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute01() throws Exception {
		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("E");
		input.setBillAccountId(null);

		G_BIL_P01_Output output = gBilP01.execute(input);
		assertEquals(null, output.getBatchStatus());

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist == null || tBilHlist.length == 0);

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist == null || tBilDlist.length == 0);

		// assert batch execution
		Map<String, Object>[] tSetBatch = super.select("T_SET_BATCH",
				"BATCH_TYPE='" + BATCH_TYPE + "' AND STATUS='2'", null);
		assertTrue(tSetBatch != null && tSetBatch.length == 1);
		// assert batch log
		List<String> tSetBatchLog = super.queryDAO.executeForObjectList(
				"TEST.G_BIL_P01.SELECT.SQL002", null);
		if (tSetBatchLog != null && tSetBatchLog.size() == 1) {
			Object errorMsg = tSetBatchLog.get(0);
			String expectMsg = "No billing documents to be generated. Billing Month: "
					+ TEST_DAY_YYYYMM;
			assertEquals(expectMsg, errorMsg);
		} else {
			assertFalse(true);
		}

		// assert alter information
		List<Notice> alertHList = super.queryDAO.executeForObjectList(
				"TEST.G_BIL_P01.SELECT.SQL001", null);
		if (alertHList != null && alertHList.size() == 1) {
			Notice alertH = alertHList.get(0);
			String expectMsg = "No billing documents to be generated. Billing Month: "
					+ TEST_DAY_YYYYMM;
			String expectSubject = "Billing Alert";
			assertEquals(SYS_ADMIN, alertH.getTo_msg());
			assertEquals(expectSubject, alertH.getSubject());
			assertEquals(expectMsg, alertH.getMsg());
		} else {
			assertFalse(true);
		}
		// assert alter user
		Map<String, Object>[] alertD = super.select("M_ALT_D",
				"IS_NEW='1' AND ID_USER='" + SYS_ADMIN + "'", null);
		assertTrue(alertD != null && alertD.length == 1);
	}

	/**
	 * Case 2: E/Batch already Executing
	 * 
	 * Condition:<br/>
	 * ModuleId = "E"<br/>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK has one record<br/>
	 * GB batch is running, G_BIL_P01 will not run.
	 * 
	 * Result:<br/>
	 * No data generated in T_BIL_H/T_BIL_D<br/>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute02() throws Exception {
		this.generateCustPlan();
		this.generateBAC();

		super.updateDAO.execute("TEST.G_BIL_P01.INSERT.SQL001", null);

		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("E");
		input.setBillAccountId(null);

		G_BIL_P01_Output output = gBilP01.execute(input);
		assertEquals(null, output.getBatchStatus());

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist == null || tBilHlist.length == 0);

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist == null || tBilDlist.length == 0);
	}

	/**
	 * Case 3: E/Executing Batch out of Time
	 * 
	 * Condition:<br/>
	 * ModuleId = "E"<br/>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK has one record<br/>
	 * GB batch is running but out oftime, G_BIL_P01 will run.
	 * 
	 * Result:<br/>
	 * One data generated in T_BIL_H/T_BIL_D<br/>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute03() throws Exception {
		this.generateCustPlan();
		this.generateBAC();

		// insert GB batch running record
		// date_created = Sysdate - BATCH_TIME_INTERVAL
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.SECOND, -BATCH_TIME_INTERVAL);
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("date_created", format.format(calendar.getTime()));
		super.updateDAO.execute("TEST.G_BIL_P01.INSERT.SQL001", param);

		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("E");
		input.setBillAccountId(null);

		G_BIL_P01_Output output = gBilP01.execute(input);
		assertEquals(null, output.getBatchStatus());

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist != null);

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist != null);
	}

	/**
	 * Case 4: B/NO DATA
	 * 
	 * Condition:<br/>
	 * ModuleId = "B"<br/>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK no record<br/>
	 * 
	 * Result:<br/>
	 * No data generated in T_BIL_H/T_BIL_D<br/>
	 * Batch Status = "3"<br/>
	 * Message = "No billing need to be generated"<br/>
	 * 
	 * @throws Exception
	 */
	public void testExecute04() throws Exception {
		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("B");
		input.setBillAccountId("");

		G_BIL_P01_Output output = gBilP01.execute(input);

		assertEquals("3", output.getBatchStatus());
		assertEquals("No billing need to be generated",
				output.getErrorMessage());
		assertTrue(null == output.getIdRefList()
				|| output.getIdRefList().size() == 0);
	}

	/**
	 * Case 5: E/Execution Exception
	 * 
	 * Condition:<br>
	 * ModuleId = "E"<br>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK has one record<br>
	 * T_BAC_H/T_BAC_D has one record.<br>
	 * T_CUST_PLAN_D.BILL_DESC = null which will cause exception.<br>
	 * 
	 * Result:<br>
	 * No data generated in T_BIL_H/T_BIL_D<br>
	 * Batch Status = "3"<br>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute05() throws Exception {
		this.generateCustPlan();
		this.generateBAC();

		// update BILL_INSTRUCT to null in order to generate exception
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("ID_CUST_PLAN", ID_CUST_PLAN);
		super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL006", param);

		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("E");
		input.setBillAccountId(null);

		G_BIL_P01_Output output = gBilP01.execute(input);
		assertEquals(null, output.getBatchStatus());

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist == null || tBilHlist.length == 0);

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist == null || tBilDlist.length == 0);

		// assert batch execution
		Map<String, Object>[] tSetBatch = super.select("T_SET_BATCH",
				"BATCH_TYPE='" + BATCH_TYPE + "' AND STATUS='3'", null);
		assertTrue(tSetBatch != null && tSetBatch.length == 1);
		// assert batch log
		//List<String> tSetBatchLog = super.queryDAO.executeForObjectList(
		//		"TEST.G_BIL_P01.SELECT.SQL002", null);
		//assertTrue(tSetBatchLog != null && tSetBatchLog.size() == 1);

		// assert alter information
		List<Notice> alertHList = super.queryDAO.executeForObjectList(
				"TEST.G_BIL_P01.SELECT.SQL001", null);
		if (alertHList != null && alertHList.size() == 1) {
			Notice alertH = alertHList.get(0);
			String expectMsg = "Billing documents failed to be generated. Billing Month: "
					+ TEST_DAY_YYYYMM
					+ "Please refer to Log File for further information.";
			String expectSubject = "Billing Alert";
			assertEquals(SYS_ADMIN, alertH.getTo_msg());
			assertEquals(expectSubject, alertH.getSubject());
			assertEquals(expectMsg, alertH.getMsg());
		} else {
			assertFalse(true);
		}
		// assert alter user
		Map<String, Object>[] alertD = super.select("M_ALT_D",
				"IS_NEW='1' AND ID_USER='" + SYS_ADMIN + "'", null);
		assertTrue(alertD != null && alertD.length == 1);
	}

	/**
	 * Case 6: B/Execution Exception
	 * 
	 * Condition:<br>
	 * ModuleId = "B"<br>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK has one record<br>
	 * T_BAC_H/T_BAC_D has one record.<br>
	 * T_CUST_PLAN_D.BILL_DESC = null which will cause exception.<br>
	 * 
	 * Result:<br>
	 * No data generated in T_BIL_H/T_BIL_D<br>
	 * Batch Status = "3"<br>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute06() throws Exception {
		this.generateCustPlan();
		this.generateBAC();

		// update BILL_INSTRUCT to null in order to generate exception
		HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ID_CUST_PLAN", ID_CUST_PLAN);
		super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL006", param);

		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("B");
		input.setBillAccountId("");

		G_BIL_P01_Output output = gBilP01.execute(input);

		assertEquals("3", output.getBatchStatus());
		//assertEquals("Error while generating billing", output.getErrorMessage());
		assertTrue(null == output.getIdRefList()
				|| output.getIdRefList().size() == 0);

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist == null || tBilHlist.length == 0);

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist == null || tBilDlist.length == 0);
	}

	/**
	 * Case 7: E/Execution Success
	 * 
	 * Condition:<br>
	 * ModuleId = "E"<br>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK has one record<br>
	 * T_BAC_H/T_BAC_D has one record.<br>
	 * T_CUST_PLAN_D.BILL_DESC != null<br>
	 * 
	 * Result:<br>
	 * One data generated in T_BIL_H/T_BIL_D<br>
	 * Batch Status = "2"<br>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute07() throws Exception {
		this.generateCustPlan();
		this.generateBAC();

		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("E");
		input.setBillAccountId(null);
        input.setRunning_date(null);

		G_BIL_P01_Output output = gBilP01.execute(input);
		assertEquals(null, output.getBatchStatus());

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist != null && tBilHlist.length == 1);

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist != null && tBilDlist.length == 2);

		// assert batch execution
		Map<String, Object>[] tSetBatch = super.select("T_SET_BATCH",
				"BATCH_TYPE='" + BATCH_TYPE + "' AND STATUS='2'", null);
		assertTrue(tSetBatch != null && tSetBatch.length == 1);
		// assert batch log
		List<String> tSetBatchLog = super.queryDAO.executeForObjectList(
				"TEST.G_BIL_P01.SELECT.SQL002", null);
		if (tSetBatchLog != null && tSetBatchLog.size() == 1) {
			Object errorMsg = tSetBatchLog.get(0);
			String expectMsg = "Billing documents have been generated successfully. Billing Month: "
					+ TEST_DAY_YYYYMM
					+ " No. of Generated Billing Documents: 1";
			assertEquals(expectMsg, errorMsg);
		} else {
			assertFalse(true);
		}

		// assert alter information
		List<Notice> alertHList = super.queryDAO.executeForObjectList(
				"TEST.G_BIL_P01.SELECT.SQL001", null);
		if (alertHList != null && alertHList.size() == 1) {
			Notice alertH = alertHList.get(0);
			String expectMsg = "Billing documents have been generated successfully. Billing Month: "
					+ TEST_DAY_YYYYMM
					+ " No. of Generated Billing Documents: 1";
			String expectSubject = "Billing Alert";
			assertEquals(SYS_ADMIN, alertH.getTo_msg());
			assertEquals(expectSubject, alertH.getSubject());
			assertEquals(expectMsg, alertH.getMsg());
		} else {
			assertFalse(true);
		}
		// assert alter user
		Map<String, Object>[] alertD = super.select("M_ALT_D",
				"IS_NEW='1' AND ID_USER='" + SYS_ADMIN + "'", null);
		assertTrue(alertD != null && alertD.length == 1);
	}

	/**
	 * Case 8: B/Execution Success
	 * 
	 * Condition:<br>
	 * ModuleId = "B"<br>
	 * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK has one record<br>
	 * T_BAC_H/T_BAC_D has one record.<br>
	 * 
	 * Result:<br>
	 * One data generated in T_BIL_H/T_BIL_D<br>
	 * Batch Status = "1"<br>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute08() throws Exception {
		this.generateCustPlan();
		this.generateBAC();

		G_BIL_P01_Input input = new G_BIL_P01_Input();
		input.setUserId(SYS_ADMIN);
		input.setModuleId("B");
		input.setBillAccountId("");
        input.setRunning_date(new Date());

		G_BIL_P01_Output output = gBilP01.execute(input);

		assertEquals("1", output.getBatchStatus());
		assertEquals("", output.getErrorMessage());
		assertTrue(null != output.getIdRefList()
				|| output.getIdRefList().size() == 1);

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist != null && tBilHlist.length == 1);
		assertEquals(output.getIdRefList().get(0).trim(),
				tBilHlist[0].get("ID_REF").toString().trim());

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist != null);
	}

	/**
	 * Case 9: E/Has Data but Month(SVC_START) > Current Month
	 * 
	 * Condition:<br>
	 * BILL_FROM = null<br>
	 * BILL_TO	= null<br>
	 * 
	 * SVC_START = (SYSDATE - 1 Day) < SYSDATE <br>
	 * SVC_END = null<br>
	 * CUST_PLAN_STATUS = PS3<br>
	 * BILL_INSTRUCT = 1: 12 (Yearly)<br>
	 * 
	 * Result:<br>
	 * No data generated in T_BIL_H/T_BIL_D<br>
	 *  
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testExecute09() throws Exception {
		String plan_status = "PS3";
		String service_status = "PS3";
		String service_start = TEST_DAY_NEXTMONTH_FIRSTDAY;
		String service_end = null;
		String bill_instruct = "1";
        String last_bill_from = null;
        String last_bill_to = null;
		
		// Create record of T_CUST_PLAN_H
        this.generateCustPlanH(ID_CUST, bill_instruct, plan_status);
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
                { "1", "1", "S", null, "2", "1200", "2400", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "1",
                        "Yearly Fee", "1", "2", "BA", "1", "1", "0", "0",
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
		input.setRunning_date(null);
		
		G_BIL_P01_Output output = gBilP01.execute(input);
		assertEquals(null, output.getBatchStatus());

		// assert generated data in T_BIL_H
		Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
		assertTrue(tBilHlist == null || tBilHlist.length == 0);

		// assert generated data in T_BIL_D
		Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
		assertTrue(tBilDlist == null || tBilDlist.length == 0);
		
		// assert batch execution
		Map<String, Object>[] tSetBatch = super.select("T_SET_BATCH",
				"BATCH_TYPE='" + BATCH_TYPE + "' AND STATUS='2'", null);
		assertTrue(tSetBatch != null && tSetBatch.length == 1);
		// assert batch log
		List<String> tSetBatchLog = super.queryDAO.executeForObjectList(
				"TEST.G_BIL_P01.SELECT.SQL002", null);
		if (tSetBatchLog != null && tSetBatchLog.size() == 1) {
			Object errorMsg = tSetBatchLog.get(0);
			String expectMsg = "No billing documents to be generated. Billing Month: "
					+ TEST_DAY_YYYYMM;
			assertEquals(expectMsg, errorMsg);
		} else {
			assertFalse(true);
		}

		// assert alter information
		List<Notice> alertHList = super.queryDAO.executeForObjectList(
				"TEST.G_BIL_P01.SELECT.SQL001", null);
		if (alertHList != null && alertHList.size() == 1) {
			Notice alertH = alertHList.get(0);
			String expectMsg = "No billing documents to be generated. Billing Month: "
					+ TEST_DAY_YYYYMM;
			String expectSubject = "Billing Alert";
			assertEquals(SYS_ADMIN, alertH.getTo_msg());
			assertEquals(expectSubject, alertH.getSubject());
			assertEquals(expectMsg, alertH.getMsg());
		} else {
			assertFalse(true);
		}
		// assert alter user
		Map<String, Object>[] alertD = super.select("M_ALT_D",
				"IS_NEW='1' AND ID_USER='" + SYS_ADMIN + "'", null);
		assertTrue(alertD != null && alertD.length == 1);
	}

    /**
     * Case 1: E/NO DATA(Running_date < T_BAC_D.BILL_TO)
     * 
     * Condition:<br/>
     * ModuleId = "E"<br/>
     * T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK contain record<br/>
     * GB batch alert user = sysadmin
     * T_BAC_D.BILL_TO = TEST_DAY_YESTERDAY
     * Running_date = Last Year Today < T_BAC_D.BILL_TO
     * 
     * Result:<br/>
     * No data generated in T_BIL_H/T_BIL_D<br/>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testExecute10() throws Exception {
        this.generateCustPlan();
        this.generateBAC();
        
        G_BIL_P01_Input input = new G_BIL_P01_Input();
        input.setUserId(SYS_ADMIN);
        input.setModuleId("E");
        input.setBillAccountId(null);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMM");
        Date runningDate = format.parse(TEST_DAY_LASTYEAR_TODAY);
        input.setRunning_date(runningDate);
        String billingMonth = format2.format(runningDate);
        
        G_BIL_P01_Output output = gBilP01.execute(input);
        assertEquals(null, output.getBatchStatus());

        // assert generated data in T_BIL_H
        Map<String, Object>[] tBilHlist = super.select("T_BIL_H", null, null);
        assertTrue(tBilHlist == null || tBilHlist.length == 0);

        // assert generated data in T_BIL_D
        Map<String, Object>[] tBilDlist = super.select("T_BIL_D", null, null);
        assertTrue(tBilDlist == null || tBilDlist.length == 0);

        // assert batch execution
        Map<String, Object>[] tSetBatch = super.select("T_SET_BATCH",
                "BATCH_TYPE='" + BATCH_TYPE + "' AND STATUS='2'", null);
        assertTrue(tSetBatch != null && tSetBatch.length == 1);
        // assert batch log
        List<String> tSetBatchLog = super.queryDAO.executeForObjectList(
                "TEST.G_BIL_P01.SELECT.SQL002", null);
        if (tSetBatchLog != null && tSetBatchLog.size() == 1) {
            Object errorMsg = tSetBatchLog.get(0);
            String expectMsg = "No billing documents to be generated. Billing Month: "
                    + billingMonth;
            assertEquals(expectMsg, errorMsg);
        } else {
            assertFalse(true);
        }

        // assert alter information
        List<Notice> alertHList = super.queryDAO.executeForObjectList(
                "TEST.G_BIL_P01.SELECT.SQL001", null);
        if (alertHList != null && alertHList.size() == 1) {
            Notice alertH = alertHList.get(0);
            String expectMsg = "No billing documents to be generated. Billing Month: "
                    + billingMonth;
            String expectSubject = "Billing Alert";
            assertEquals(SYS_ADMIN, alertH.getTo_msg());
            assertEquals(expectSubject, alertH.getSubject());
            assertEquals(expectMsg, alertH.getMsg());
        } else {
            assertFalse(true);
        }
        // assert alter user
        Map<String, Object>[] alertD = super.select("M_ALT_D",
                "IS_NEW='1' AND ID_USER='" + SYS_ADMIN + "'", null);
        assertTrue(alertD != null && alertD.length == 1);
    }
    
	/**
	 * Generate custom plan T_CUST_PLAN_H/T_CUST_PLAN_D/T_CUST_PLAN_LINK record.
	 */
    private void generateCustPlan() {
        String plan_status = "PS3";
        String service_status = "PS3";
        String service_start = TEST_DAY_YESTERDAY;
        String service_end = null;

        // Create record of T_CUST_PLAN_H
        this.generateCustPlanH(ID_CUST, "1", plan_status);
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
                { "1", "1", "S", null, "2", "1200", "2400", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "1",
                        "Yearly Fee", "1", "2", "BA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null }};
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        // update T_CUST_PLAN_LINK.ITEM_DESC not null in order to avoid exception
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ITEM_DESC", ID_CUST_PLAN + " : ITEM_DESC");
        param.put("ID_CUST_PLAN_LINK", "1");
        super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL008", param);
	}

	/**
	 * Generate custom plan T_BAC_H/T_BSC_D record.
	 */
	private void generateBAC() {
        String last_bill_from = TEST_DAY_LASTYEAR_TODAY;
        String last_bill_to = TEST_DAY_YESTERDAY;
        
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
	}
}
