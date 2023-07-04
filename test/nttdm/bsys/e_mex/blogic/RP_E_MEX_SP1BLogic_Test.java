/**
 * @(#)RP_E_MEX_SP1BLogic_Test.java
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
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1Input;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1Output;

/**
 * Test Class for nttdm.bsys.e_mex.blogic.RP_E_MEX_SP1BLogic
 * 
 * @author leonzh
 */
public class RP_E_MEX_SP1BLogic_Test extends AbstractUtilTest {

	private RP_E_MEX_SP1BLogic blogic;
	private RP_E_MEX_SP1Input input;
	private RP_E_MEX_SP1Output output;
	private BLogicResult result;
	private BillingSystemUserValueObject uvo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_E_MEX_SP1BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new RP_E_MEX_SP1Input();

		uvo = new BillingSystemUserValueObject();
	}

	/**
	 * 
	 * Case 1:forward is empty and had no scheduler setting <br>
	 * 
	 * Condition: <br>
	 * forward_update = "" <br>
	 * forwar_execute = "" <br>
	 * setting = null <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute01() {
		// delete data
		super.deleteAllData("T_BATCH_SCH");

		input.setClosingMonth("08");
		input.setClosingYear("2011");
		input.setForward_execute("");
		input.setForward_pageLinks("4");
		input.setForward_update("");
		input.setRow(new Integer(10));
		input.setRuntimeType("0");
		input.setScheduleDay("22");
		input.setScheduleHour("12");
		input.setScheduleMinute("30");
		input.setScheduleStatus("1");
		input.setStartIndex(new Integer(0));
		input.setUvo(uvo);

		result = blogic.execute(input);
		output = (RP_E_MEX_SP1Output) result.getResultObject();

		// init closing month
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		assertEquals(CommonUtils.toString(cal.get(Calendar.MONTH) + 1),
				output.getClosingMonth());
		assertEquals(CommonUtils.toString(cal.get(Calendar.YEAR)),
				output.getClosingYear());
		assertEquals("", output.getForward_execute());
		assertEquals("4", output.getForward_pageLinks());
		assertEquals("", output.getForward_update());
		assertEquals(new Integer(10), output.getRow());
		assertEquals("0", output.getRuntimeType());
		assertEquals("22", output.getScheduleDay());
		assertEquals("12", output.getScheduleHour());
		assertEquals("30", output.getScheduleMinute());
		assertEquals("0", output.getScheduleStatus());
		assertEquals(new Integer(0), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2:forward is empty and had scheduler setting <br>
	 * 
	 * Condition: <br>
	 * forward_update = "" <br>
	 * forwar_execute = "" <br>
	 * setting != null <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute02() {
		// delete data
		super.deleteAllData("T_BATCH_SCH");

		// insert data
		String[][] data = {
				{ "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
						"EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
						"DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
						"DATE_CREATED", "ID_AUDIT", },
				{ "G_SGP_P01", "0", "4", "12", "2", null, null,
						"2011-06-28 09:00:56", "USERFULL", "0",
						"2011-03-30 14:01:55", AUDIT_VALUE, } };
		super.insertData("T_BATCH_SCH", data);

		input.setClosingMonth("08");
		input.setClosingYear("2011");
		input.setForward_execute("");
		input.setForward_pageLinks("4");
		input.setForward_update("");
		input.setRow(new Integer(10));
		input.setRuntimeType("0");
		input.setScheduleDay("22");
		input.setScheduleHour("12");
		input.setScheduleMinute("30");
		input.setScheduleStatus("1");
		input.setStartIndex(new Integer(0));
		input.setUvo(uvo);

		result = blogic.execute(input);
		output = (RP_E_MEX_SP1Output) result.getResultObject();

		// init closing month
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		assertEquals(CommonUtils.toString(cal.get(Calendar.MONTH) + 1),
				output.getClosingMonth());
		assertEquals(CommonUtils.toString(cal.get(Calendar.YEAR)),
				output.getClosingYear());
		assertEquals("", output.getForward_execute());
		assertEquals("4", output.getForward_pageLinks());
		assertEquals("", output.getForward_update());
		assertEquals(new Integer(10), output.getRow());
		assertEquals("0", output.getRuntimeType());
		assertEquals("4", output.getScheduleDay());
		assertEquals("12", output.getScheduleHour());
		assertEquals("2", output.getScheduleMinute());
		assertEquals("0", output.getScheduleStatus());
		assertEquals(new Integer(0), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 3:forward update is "Update" and had scheduler setting and runtime
	 * type is 1 and schedule status is 0 <br>
	 * 
	 * Condition: <br>
	 * forward_update = "Update" <br>
	 * forwar_execute = "" <br>
	 * runtimeType = 1 <br>
	 * scheduleStatus = 0 <br>
	 * setting != null <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute03() {
		// delete data
		super.deleteAllData("T_BATCH_SCH");

		// insert data
		String[][] data = {
				{ "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
						"EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
						"DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
						"DATE_CREATED", "ID_AUDIT", },
				{ "G_SGP_P01", "0", "4", "12", "2", null, null,
						"2011-06-28 09:00:56", "USERFULL", "0",
						"2011-03-30 14:01:55", AUDIT_VALUE, } };
		super.insertData("T_BATCH_SCH", data);

		input.setClosingMonth("08");
		input.setClosingYear("2011");
		input.setForward_execute("");
		input.setForward_pageLinks("4");
		input.setForward_update("Update");
		input.setRow(new Integer(10));
		input.setRuntimeType("1");
		input.setScheduleDay("22");
		input.setScheduleHour("12");
		input.setScheduleMinute("30");
		input.setScheduleStatus("0");
		input.setStartIndex(new Integer(0));
		input.setUvo(uvo);

		result = blogic.execute(input);
		output = (RP_E_MEX_SP1Output) result.getResultObject();

		assertEquals("08", output.getClosingMonth());
		assertEquals("2011", output.getClosingYear());
		assertEquals("", output.getForward_execute());
		assertEquals("4", output.getForward_pageLinks());
		assertEquals("Update", output.getForward_update());
		assertEquals(new Integer(10), output.getRow());
		assertEquals("1", output.getRuntimeType());
		assertEquals("4", output.getScheduleDay());
		assertEquals("12", output.getScheduleHour());
		assertEquals("2", output.getScheduleMinute());
		assertEquals("0", output.getScheduleStatus());
		assertEquals(new Integer(0), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

}
