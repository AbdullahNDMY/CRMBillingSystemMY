/**
 * @(#)RP_E_MEX_GR1SubmitBLogic_Test.java
 * 
 * Billing System NTTS
 * 
 * Version 1.00
 * 
 * Created 2011/08/24
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mex.blogic;

import java.util.Iterator;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitOutput;

/**
 * Test Class for nttdm.bsys.e_mex.blogic.RP_E_MEX_GR1SubmitBLogic
 * 
 * @author leonzh
 */
public class RP_E_MEX_GR1SubmitBLogic_Test extends AbstractUtilTest {

	private RP_E_MEX_GR1SubmitBLogic blogic;
	private RP_E_MEX_GR1SubmitInput input;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_E_MEX_GR1SubmitBLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);
		input = new RP_E_MEX_GR1SubmitInput();
	}

	/**
	 * 
	 * Case 1:test normal situation one
	 * 
	 * Condition: <br>
	 * runtimeType = 1 <br>
	 * scheduleStatus = 1 <br>
	 * scheduleDay = 1 <br>
	 * scheduleHour = 0 <br>
	 * scheduleMinute=0 <br>
	 * 
	 * Result: <br>
	 * errors had no messages <br>
	 * 
	 */
	public void testValidate01() {
		input.setRuntimeType("1");
		input.setScheduleStatus("1");
		input.setScheduleDay("1");
		input.setScheduleHour("0");
		input.setScheduleMinute("0");

		BLogicMessages errors = blogic.validate(input, null);
		Iterator<String> itGroup = errors.getGroup();
		Iterator<BLogicMessage> it = errors.get();

		assertFalse(itGroup.hasNext());
		assertFalse(it.hasNext());
	}

	/**
	 * 
	 * Case 2:test normal situation two
	 * 
	 * Condition: <br>
	 * runtimeType = 2 <br>
	 * closingMonth = 1 <br>
	 * closingYear = 2011 <br>
	 * 
	 * Result: <br>
	 * errors had no messages <br>
	 * 
	 */
	public void testValidate02() {
		input.setRuntimeType("2");
		input.setClosingMonth("1");
		input.setClosingYear("2011");

		BLogicMessages errors = blogic.validate(input, null);
		Iterator<String> itGroup = errors.getGroup();
		Iterator<BLogicMessage> it = errors.get();

		assertFalse(itGroup.hasNext());
		assertFalse(it.hasNext());
	}

	/**
	 * 
	 * Case 3:test abnormal situation by scheduleHour = 24
	 * 
	 * Condition: <br>
	 * runtimeType = 1 <br>
	 * scheduleStatus = 1 <br>
	 * scheduleDay = 1 <br>
	 * scheduleHour = 24 <br>
	 * scheduleMinute= 0 <br>
	 * 
	 * Result: <br>
	 * errors.hour is Execute Hour <br>
	 * 
	 */
	public void testValidate03() {
		input.setRuntimeType("1");
		input.setScheduleStatus("1");
		input.setScheduleDay("1");
		input.setScheduleHour("24");
		input.setScheduleMinute("0");

		BLogicMessages errors = blogic.validate(input, null);
		Iterator<String> itGroup = errors.getGroup();
		Iterator<BLogicMessage> it = errors.get();

		assertTrue(itGroup.hasNext());
		assertTrue(it.hasNext());

		String group = itGroup.next();
		BLogicMessage message = it.next();

		assertEquals("org.apache.struts.action.GLOBAL_MESSAGE", group);
		assertEquals("errors.hour", message.getKey());
		assertEquals("Execute Hour", message.getValues()[0]);
	}

	/**
	 * 
	 * Case 4:test abnormal situation by scheduMinute = 60
	 * 
	 * Condition: <br>
	 * runtimeType = 1 <br>
	 * scheduleStatus = 1 <br>
	 * scheduleDay = 1 <br>
	 * scheduleHour = 0 <br>
	 * scheduleMinute= 60 <br>
	 * 
	 * Result: <br>
	 * errors.minute is Execute Minute <br>
	 * 
	 */
	public void testValidate04() {
		input.setRuntimeType("1");
		input.setScheduleStatus("1");
		input.setScheduleDay("1");
		input.setScheduleHour("0");
		input.setScheduleMinute("60");

		BLogicMessages errors = blogic.validate(input, null);
		Iterator<String> itGroup = errors.getGroup();
		Iterator<BLogicMessage> it = errors.get();

		assertTrue(itGroup.hasNext());
		assertTrue(it.hasNext());

		String group = itGroup.next();
		BLogicMessage message = it.next();

		assertEquals("org.apache.struts.action.GLOBAL_MESSAGE", group);
		assertEquals("errors.minute", message.getKey());
		assertEquals("Execute Minute", message.getValues()[0]);
	}

	/**
	 * 
	 * Case 5:test abnormal situation by closingMonth = 0
	 * 
	 * Condition: <br>
	 * runtimeType = 2 <br>
	 * closingMonth = 0 <br>
	 * closingYear = 2011 <br>
	 * 
	 * Result: <br>
	 * errors.date is Invoice Period <br>
	 * 
	 */
	public void testValidate05() {
		input.setRuntimeType("2");
		input.setClosingMonth("0");
		input.setClosingYear("2011");

		BLogicMessages errors = blogic.validate(input, null);
		Iterator<String> itGroup = errors.getGroup();
		Iterator<BLogicMessage> it = errors.get();

		assertTrue(itGroup.hasNext());
		assertTrue(it.hasNext());

		String group = itGroup.next();
		BLogicMessage message = it.next();

		assertEquals("org.apache.struts.action.GLOBAL_MESSAGE", group);
		assertEquals("errors.date", message.getKey());
		assertEquals("Invoice Period", message.getValues()[0]);
	}

	/**
	 * 
	 * Case 6:test abnormal situation by closingYear = 999
	 * 
	 * Condition: <br>
	 * runtimeType = 2 <br>
	 * closingMonth = 1 <br>
	 * closingYear = 999 <br>
	 * 
	 * Result: <br>
	 * errors.date is Invoice Period <br>
	 * 
	 */
	public void testValidate06() {
		input.setRuntimeType("2");
		input.setClosingMonth("1");
		input.setClosingYear("999");

		BLogicMessages errors = blogic.validate(input, null);
		Iterator<String> itGroup = errors.getGroup();
		Iterator<BLogicMessage> it = errors.get();

		assertTrue(itGroup.hasNext());
		assertTrue(it.hasNext());

		String group = itGroup.next();
		BLogicMessage message = it.next();

		assertEquals("org.apache.struts.action.GLOBAL_MESSAGE", group);
		assertEquals("errors.date", message.getKey());
		assertEquals("Invoice Period", message.getValues()[0]);
	}

	/**
	 * 
	 * Case 1:test normal situation,forward_update isn't empty and setting is
	 * empty
	 * 
	 * Condition: <br>
	 * forward_update = "Update" <br>
	 * forward_execute = "" <br>
	 * setting = null <br>
	 * scheduleStatus = "" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute01() {
		BLogicResult result = null;
		RP_E_MEX_GR1SubmitOutput output = null;

		BLogicMessages errors = null;
		BLogicMessages messages = null;

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("myIdUser");
		input.setUvo(uvo);

		input.setRuntimeType("1");
		input.setScheduleStatus("");
		input.setScheduleDay("1");
		input.setScheduleHour("0");
		input.setScheduleMinute("0");

		input.setForward_update("Update");
		input.setForward_execute("");
		input.setForward_pageLinks("1");

		// clear table and setting = null
		super.deleteAllData("T_BATCH_SCH");

		result = blogic.execute(input);
		errors = result.getErrors();
		messages = result.getMessages();
		output = (RP_E_MEX_GR1SubmitOutput) result.getResultObject();

		assertTrue(errors.isEmpty());
		assertTrue(messages.isEmpty());

		assertEquals(input.getClosingMonth(), output.getClosingMonth());
		assertEquals(input.getClosingYear(), output.getClosingYear());
		assertEquals(input.getEvent(), output.getEvent());
		assertEquals(input.getForward_execute(), output.getForward_execute());
		assertEquals(input.getForward_pageLinks(),
				output.getForward_pageLinks());
		assertEquals(input.getForward_update(), output.getForward_update());
		assertEquals(input.getRuntimeType(), output.getRuntimeType());
		assertEquals(input.getScheduleDay(), output.getScheduleDay());
		assertEquals(input.getScheduleHour(), output.getScheduleHour());
		assertEquals(input.getScheduleMinute(), output.getScheduleMinute());
		assertEquals("0", output.getScheduleStatus());
		assertEquals(input.getStartIndex(), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2:test normal situation,forward_update isn't empty and setting isn't
	 * empty
	 * 
	 * Condition: <br>
	 * forward_update = "Update" <br>
	 * forward_execute = "" <br>
	 * setting != null <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute02() {
		BLogicResult result = null;
		RP_E_MEX_GR1SubmitOutput output = null;

		BLogicMessages errors = null;
		BLogicMessages messages = null;

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("myIdUser");
		input.setUvo(uvo);

		input.setRuntimeType("1");
		input.setScheduleStatus("1");
		input.setScheduleDay("1");
		input.setScheduleHour("0");
		input.setScheduleMinute("0");

		input.setForward_update("Update");
		input.setForward_execute("");
		input.setForward_pageLinks("1");

		// clear table for setting = null
		super.deleteAllData("T_BATCH_SCH");

		// insert data for setting != null
		String[][] data = {
				{ "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
						"EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
						"DATE_UPDATED", "ID_LOGIN", "IS_DELETED",
						"DATE_CREATED", "ID_AUDIT", },
				{ "G_GIR_P01", "0", "4", "12", "2", null, null,
						"2011-06-28 09:00:56", "USERFULL", "0",
						"2011-03-30 14:01:55", AUDIT_VALUE, } };
		super.insertData("T_BATCH_SCH", data);

		result = blogic.execute(input);
		errors = result.getErrors();
		messages = result.getMessages();
		output = (RP_E_MEX_GR1SubmitOutput) result.getResultObject();

		assertTrue(errors.isEmpty());
		assertTrue(messages.isEmpty());

		assertEquals(input.getClosingMonth(), output.getClosingMonth());
		assertEquals(input.getClosingYear(), output.getClosingYear());
		assertEquals(input.getEvent(), output.getEvent());
		assertEquals(input.getForward_execute(), output.getForward_execute());
		assertEquals(input.getForward_pageLinks(),
				output.getForward_pageLinks());
		assertEquals(input.getForward_update(), output.getForward_update());
		assertEquals(input.getRuntimeType(), output.getRuntimeType());
		assertEquals(input.getScheduleDay(), output.getScheduleDay());
		assertEquals(input.getScheduleHour(), output.getScheduleHour());
		assertEquals(input.getScheduleMinute(), output.getScheduleMinute());
		assertEquals(input.getScheduleStatus(), output.getScheduleStatus());
		assertEquals(input.getStartIndex(), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 3:test normal situation,forward_execute isn't empty
	 * 
	 * Condition: <br>
	 * forward_update = "" <br>
	 * forward_execute = "Execute" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute03() {
		input.setClosingYear("2011");
		input.setClosingMonth("8");

		BLogicResult result = null;
		RP_E_MEX_GR1SubmitOutput output = null;

		BLogicMessages errors = null;
		BLogicMessages messages = null;

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("myIdUser");
		input.setUvo(uvo);

		input.setRuntimeType("1");
		input.setScheduleStatus("1");
		input.setScheduleDay("1");
		input.setScheduleHour("0");
		input.setScheduleMinute("0");
		input.setDeductionDate("2011/01/01");

		input.setForward_update("");
		input.setForward_execute("Execute");
		input.setForward_pageLinks("1");

		result = blogic.execute(input);
		errors = result.getErrors();
		messages = result.getMessages();
		output = (RP_E_MEX_GR1SubmitOutput) result.getResultObject();

		assertFalse(errors.isEmpty());
		assertTrue(messages.isEmpty());

		assertEquals(input.getClosingMonth(), output.getClosingMonth());
		assertEquals(input.getClosingYear(), output.getClosingYear());
		assertEquals(input.getEvent(), output.getEvent());
		assertEquals(input.getForward_execute(), output.getForward_execute());
		assertEquals(input.getForward_pageLinks(),
				output.getForward_pageLinks());
		assertEquals(input.getForward_update(), output.getForward_update());
		assertEquals(input.getRuntimeType(), output.getRuntimeType());
		assertEquals(input.getScheduleDay(), output.getScheduleDay());
		assertEquals(input.getScheduleHour(), output.getScheduleHour());
		assertEquals(input.getScheduleMinute(), output.getScheduleMinute());
		assertEquals(input.getScheduleStatus(), output.getScheduleStatus());
		assertEquals(input.getStartIndex(), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 4:test abnormal situation,forward_upadte and forward_execute bath
	 * are empty
	 * 
	 * Condition: <br>
	 * forward_update = "" <br>
	 * forward_execute = "" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute04() {
		BLogicResult result = null;
		RP_E_MEX_GR1SubmitOutput output = null;

		BLogicMessages errors = null;
		BLogicMessages messages = null;

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("myIdUser");
		input.setUvo(uvo);

		input.setRuntimeType("1");
		input.setScheduleStatus("1");
		input.setScheduleDay("1");
		input.setScheduleHour("0");
		input.setScheduleMinute("0");

		input.setForward_update("");
		input.setForward_execute("");
		input.setForward_pageLinks("1");

		result = blogic.execute(input);
		errors = result.getErrors();
		messages = result.getMessages();
		output = (RP_E_MEX_GR1SubmitOutput) result.getResultObject();

		assertTrue(errors.isEmpty());
		assertTrue(messages.isEmpty());

		assertEquals(input.getClosingMonth(), output.getClosingMonth());
		assertEquals(input.getClosingYear(), output.getClosingYear());
		assertEquals(input.getEvent(), output.getEvent());
		assertEquals(input.getForward_execute(), output.getForward_execute());
		assertEquals(input.getForward_pageLinks(),
				output.getForward_pageLinks());
		assertEquals(input.getForward_update(), output.getForward_update());
		assertEquals(input.getRuntimeType(), output.getRuntimeType());
		assertEquals(input.getScheduleDay(), output.getScheduleDay());
		assertEquals(input.getScheduleHour(), output.getScheduleHour());
		assertEquals(input.getScheduleMinute(), output.getScheduleMinute());
		assertEquals(input.getScheduleStatus(), output.getScheduleStatus());
		assertEquals(input.getStartIndex(), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 5:test abnormal situation by errors.isEmpty = false
	 * 
	 * Condition: <br>
	 * forward_update = "Update" <br>
	 * forward_execute = "" <br>
	 * scheduleHour = 24 <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute05() {
		BLogicResult result = null;
		RP_E_MEX_GR1SubmitOutput output = null;

		BLogicMessages errors = null;
		BLogicMessages messages = null;

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("myIdUser");
		input.setUvo(uvo);

		input.setRuntimeType("1");
		input.setScheduleStatus("1");
		input.setScheduleDay("29");
		input.setScheduleHour("24");
		input.setScheduleMinute("0");

		input.setForward_update("Update");
		input.setForward_execute("");
		input.setForward_pageLinks("1");

		result = blogic.execute(input);
		errors = result.getErrors();
		messages = result.getMessages();
		output = (RP_E_MEX_GR1SubmitOutput) result.getResultObject();

		// check errors
		assertFalse(errors.isEmpty());

		Iterator<String> itGroup = errors.getGroup();
		Iterator<BLogicMessage> it = errors.get();

		assertTrue(itGroup.hasNext());
		assertTrue(it.hasNext());

		String group = itGroup.next();
		BLogicMessage message = it.next();

		assertEquals("org.apache.struts.action.GLOBAL_MESSAGE", group);
		assertEquals("errors.hour", message.getKey());
		assertEquals("Execute Hour", message.getValues()[0]);

		assertTrue(messages.isEmpty());

		// check output
		assertEquals(input.getClosingMonth(), output.getClosingMonth());
		assertEquals(input.getClosingYear(), output.getClosingYear());
		assertEquals(input.getEvent(), output.getEvent());
		assertEquals(input.getForward_execute(), output.getForward_execute());
		assertEquals(input.getForward_pageLinks(),
				output.getForward_pageLinks());
		assertEquals(input.getForward_update(), output.getForward_update());
		assertEquals(input.getRuntimeType(), output.getRuntimeType());
		assertEquals(input.getScheduleDay(), output.getScheduleDay());
		assertEquals(input.getScheduleHour(), output.getScheduleHour());
		assertEquals(input.getScheduleMinute(), output.getScheduleMinute());
		assertEquals(input.getScheduleStatus(), output.getScheduleStatus());
		assertEquals(input.getStartIndex(), output.getStartIndex());

		assertEquals("success", result.getResultString());
	}

}
