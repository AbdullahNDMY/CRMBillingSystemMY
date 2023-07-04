/**
 * @(#)SC_E_MEX_SP1SCRBLogic_Test.java
 * 
 * Billing System NTTS
 * 
 * Version 1.00
 * 
 * Created 2011/08/22
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mex.blogic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.e_mex.blogic.SC_E_MEX_SP1SCRBLogic
 * 
 * @author leonzh 
 */
public class SC_E_MEX_SP1SCRBLogic_Test extends AbstractUtilTest {

	private static String SYSTEMDATE;
	private SC_E_MEX_SP1SCRBLogic blogic;
	private Map<String, Object> input;
	private Map<String, Object> output;
	private BLogicResult result;

	static {
		// get system date
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		month++;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		SYSTEMDATE = year + "-" + month + "-" + day;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new SC_E_MEX_SP1SCRBLogic();
		blogic.setQueryDAO(queryDAO);
		input = new HashMap<String, Object>();
	}

	/**
	 * 
	 * Case 1:test by scheduler setting is null <br>
	 * 
	 * Condition: <br>
	 * setting = null <br>
	 * countHistory = new Integer(0) <br>
	 * startIndex = null <br>
	 * 
	 * Result: <br>
	 * errors had no messages <br>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void testExecute01() throws Exception {
		// delete data
		super.deleteAllData("T_BATCH_SCH");
		super.deleteAllData("T_SGP_EXP_HD");
		super.deleteAllData("M_GSET_D");

		// insert data for result row is 30
		String[][] dataGsetD = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{ "RESULTROW", "1", "Number of rows of result to be display.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"USERFULL", "30", AUDIT_VALUE, "1" } };
		super.insertData("M_GSET_D", dataGsetD);

		// update system date to 2011-08-29
		Process processStart = Runtime.getRuntime().exec(
				"cmd /c date 2011-08-29");
		processStart.waitFor();

		result = blogic.execute(input);

		// back up system date
		Process processEnd = Runtime.getRuntime().exec(
				"cmd /c date " + SYSTEMDATE);
		processEnd.waitFor();

		output = (Map<String, Object>) result.getResultObject();

		assertEquals(queryDAO, blogic.getQueryDAO());

		assertEquals("0", output.get("scheduleStatus"));
		assertEquals("0", output.get("scheduleDay"));
		assertEquals("0", output.get("scheduleHour"));
		assertEquals("0", output.get("scheduleMinute"));
		assertEquals("", output.get("deductionDate"));

		assertEquals("8" + "", output.get("closingMonth"));
		assertEquals("2011" + "", output.get("closingYear"));

		assertEquals(30, output.get("row"));
		assertEquals(null, output.get("countHistory"));
		assertEquals(0, output.get("startIndex"));
		assertEquals(0,
				((List<Map<String, Object>>) output.get("historyList")).size());
		assertEquals("2", output.get("runtimeType"));

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2:test by scheduler setting isn't null and scheduleStatus is 1 and <br>
	 * update system time to month = 1<br>
	 * 
	 * Condition: <br>
	 * setting != null <br>
	 * countHistory = new Integer(1) <br>
	 * startIndex = 30 <br>
	 * scheduleStatus = "1" <br>
	 * month = 1 <br>
	 * 
	 * Result: <br>
	 * errors had no messages <br>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void testExecute02() throws Exception {
		// delete data
		super.deleteAllData("T_BATCH_SCH");
		super.deleteAllData("T_SGP_EXP_HD");
		super.deleteAllData("M_GSET_D");

		// insert data
		String[][] dataBatchSch = {
				{ "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
						"EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
						"DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
						"DATE_CREATED", "ID_AUDIT", },
				{ "G_SGP_P01", "1", "11", "22", "3", "4", null,
						"2011-08-22 15:31:00", "USERFULL", "0",
						"2011-08-22 14:03:00", null, } };
		super.insertData("T_BATCH_SCH", dataBatchSch);

		String[][] dataSgpExpHd = {
				{ "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", },
				{ "223", "INVOICE_072011_224.txt", "07/2011",
						"2011-08-22 00:00:00", "1",  "2011-08-22 00:00:00",
						"2011-08-22 00:00:00", "sysadmin", } };
		super.insertData("T_SGP_EXP_HD", dataSgpExpHd);
		// insert data for result row is 30
		String[][] dataGsetD = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY", },
				{ "RESULTROW", "1", "Number of rows of result to be display.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"USERFULL", "30", AUDIT_VALUE, "1", } };
		super.insertData("M_GSET_D", dataGsetD);

		input.put("startIndex", new Integer(0));

		// update system date to 2011-01-01
		Process processStart = Runtime.getRuntime().exec(
				"cmd /c date 2011-01-01");
		processStart.waitFor();

		result = blogic.execute(input);

		// back up system date
		Process processEnd = Runtime.getRuntime().exec(
				"cmd /c date " + SYSTEMDATE);
		processEnd.waitFor();

		output = (Map<String, Object>) result.getResultObject();

		// check output for T_BATCH_SCH's table
		assertEquals("G_SGP_P01", output.get("ID_BATCH_TYPE"));
		assertEquals("1", output.get("IS_ACTIVE"));
		assertEquals("11", output.get("EXEC_DAY").toString().trim());
		assertEquals("22", output.get("EXEC_HOUR").toString().trim());
		assertEquals("3", output.get("EXEC_MINUTE").toString().trim());
		assertEquals(null, output.get("GIRO_VALUE_DAY"));
		assertEquals(null, output.get("GIRO_VALUE_MONTH"));
		assertEquals(null, output.get("GIRO_VALUE_YEAR"));
		assertEquals("2011-08-22", output.get("DATE_UPDATED").toString().trim());
		assertEquals("USERFULL", output.get("ID_LOGIN"));
		assertEquals("0", output.get("IS_DELETED"));
		assertEquals("2011-08-22", output.get("DATE_CREATED").toString().trim());

		assertEquals("1", output.get("scheduleStatus"));
		assertEquals("11", output.get("scheduleDay"));
		assertEquals("22", output.get("scheduleHour"));
		assertEquals("3", output.get("scheduleMinute"));
		assertEquals("4", output.get("deductionDate"));

		assertEquals(1 + "", output.get("closingMonth"));
		assertEquals(2011 + "", output.get("closingYear"));

		assertEquals(30, output.get("row"));
		assertEquals(1, output.get("totalRow"));
		assertEquals(0, output.get("startIndex"));

		List<Map<String, Object>> historyList = (List<Map<String, Object>>) output
				.get("historyList");
		assertNotNull(historyList);
		assertEquals(1, historyList.size());

		Map<String, Object> oneHistory = historyList.iterator().next();
		assertEquals("223", oneHistory.get("ID_SGP_EXP_BATCH").toString()
				.trim());
		assertEquals("INVOICE_072011_224.txt", oneHistory.get("FILENAME"));
		assertEquals("07/2011", oneHistory.get("CLOSE_MONTHYEAR"));
		assertEquals("2011-08-22", oneHistory.get("DATE_UPLOADED").toString()
				.trim());
		assertEquals("1", oneHistory.get("STATUS"));
		assertEquals("2011-08-22", oneHistory.get("DATE_CREATED").toString()
				.trim());
		assertEquals("2011-08-22", oneHistory.get("DATE_UPDATED").toString()
				.trim());
		assertEquals("sysadmin", oneHistory.get("ID_LOGIN"));
		assertEquals("22/08/2011 00:00:00", oneHistory.get("DATE_UPDATED_FMT")
				.toString().trim());

		assertEquals("1", output.get("runtimeType"));

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 3:test by scheduler setting isn't null and scheduleStatus is 0 <br>
	 * 
	 * Condition: <br>
	 * setting != null <br>
	 * countHistory = new Integer(1) <br>
	 * startIndex = 30 <br>
	 * scheduleStatus = "0" <br>
	 * 
	 * Result: <br>
	 * errors had no messages <br>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void testExecute03() {
		// delete data
		super.deleteAllData("T_BATCH_SCH");
		super.deleteAllData("T_SGP_EXP_HD");
		super.deleteAllData("M_GSET_D");

		// insert data
		String[][] dataBatchSch = {
				{ "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
						"EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
						"DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
						"DATE_CREATED", "ID_AUDIT", },
				{ "G_SGP_P01", "0", "11", "22", "3", "4", null,
						"2011-08-22 15:31:00", "USERFULL", "0",
						"2011-08-22 14:03:00", null, } };
		super.insertData("T_BATCH_SCH", dataBatchSch);

		String[][] dataSgpExpHd = {
				{ "ID_SGP_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", 
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", },
				{ "223", "INVOICE_072011_224.txt", "07/2011",
						"2011-08-22 00:00:00", "1", "2011-08-22 00:00:00",
						"2011-08-22 00:00:00", "sysadmin", } };
		super.insertData("T_SGP_EXP_HD", dataSgpExpHd);
		// insert data for result row is 30
		String[][] dataGsetD = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY", },
				{ "RESULTROW", "1", "Number of rows of result to be display.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"USERFULL", "30", AUDIT_VALUE, "1", } };
		super.insertData("M_GSET_D", dataGsetD);

		input.put("startIndex", new Integer(0));

		// current date
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		result = blogic.execute(input);
		output = (Map<String, Object>) result.getResultObject();

		// check output for T_BATCH_SCH's table
		assertEquals("G_SGP_P01", output.get("ID_BATCH_TYPE"));
		assertEquals("0", output.get("IS_ACTIVE"));
		assertEquals("11", output.get("EXEC_DAY").toString().trim());
		assertEquals("22", output.get("EXEC_HOUR").toString().trim());
		assertEquals("3", output.get("EXEC_MINUTE").toString().trim());
		assertEquals(null, output.get("GIRO_VALUE_DAY"));
		assertEquals(null, output.get("GIRO_VALUE_MONTH"));
		assertEquals(null, output.get("GIRO_VALUE_YEAR"));
		assertEquals("2011-08-22", output.get("DATE_UPDATED").toString().trim());
		assertEquals("USERFULL", output.get("ID_LOGIN"));
		assertEquals("0", output.get("IS_DELETED"));
		assertEquals("2011-08-22", output.get("DATE_CREATED").toString().trim());

		assertEquals("0", output.get("scheduleStatus"));
		assertEquals("11", output.get("scheduleDay"));
		assertEquals("22", output.get("scheduleHour"));
		assertEquals("3", output.get("scheduleMinute"));
		assertEquals("4", output.get("deductionDate"));

		assertEquals(month + "", output.get("closingMonth"));
		assertEquals(year + "", output.get("closingYear"));

		assertEquals(30, output.get("row"));
		assertEquals(1, output.get("totalRow"));
		assertEquals(0, output.get("startIndex"));

		List<Map<String, Object>> historyList = (List<Map<String, Object>>) output
				.get("historyList");
		assertNotNull(historyList);
		assertEquals(1, historyList.size());

		Map<String, Object> oneHistory = historyList.iterator().next();
		assertEquals("223", oneHistory.get("ID_SGP_EXP_BATCH").toString()
				.trim());
		assertEquals("INVOICE_072011_224.txt", oneHistory.get("FILENAME"));
		assertEquals("07/2011", oneHistory.get("CLOSE_MONTHYEAR"));
		assertEquals("2011-08-22", oneHistory.get("DATE_UPLOADED").toString()
				.trim());
		assertEquals("1", oneHistory.get("STATUS"));
		assertEquals("2011-08-22", oneHistory.get("DATE_CREATED").toString()
				.trim());
		assertEquals("2011-08-22", oneHistory.get("DATE_UPDATED").toString()
				.trim());
		assertEquals("sysadmin", oneHistory.get("ID_LOGIN"));
		assertEquals("22/08/2011 00:00:00", oneHistory.get("DATE_UPDATED_FMT")
				.toString().trim());

		assertEquals("2", output.get("runtimeType"));

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 4:test throw Exception and catch the Excpetion <br>
	 * 
	 * Condition: <br>
	 * queryDAO = null <br>
	 * 
	 * Result: <br>
	 * output = null <br>
	 * result.getResultString = "failure" <br>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void testExecute04() {
		blogic.setQueryDAO(null);

		result = blogic.execute(input);
		output = (Map<String, Object>) result.getResultObject();

		assertNull(output);
		assertEquals("failure", result.getResultString());
	}

}
