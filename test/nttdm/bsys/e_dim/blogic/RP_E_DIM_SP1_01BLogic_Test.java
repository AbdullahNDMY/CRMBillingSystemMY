/**
 * @(#)RP_E_DIM_SP1_01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/18
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_dim.blogic;

import java.util.HashMap;
import java.util.List;
import org.apache.struts.util.LabelValueBean;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_01Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_01Output;

/**
 * Test Class for nttdm.bsys.e_dim.blogic.RP_E_DIM_SP1_01BLogic
 * 
 * @author leonzh
 */
public class RP_E_DIM_SP1_01BLogic_Test extends AbstractUtilTest {

	private RP_E_DIM_SP1_01BLogic blogic;
	private RP_E_DIM_SP1_01Input input;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic = new RP_E_DIM_SP1_01BLogic();
		blogic.setQueryDAO(queryDAO);
		input = new RP_E_DIM_SP1_01Input();

		// delete all exist data
		super.deleteAllData("M_BANK");
		super.deleteAllData("M_COM_BANKINFO");
		super.deleteAllData("T_SGP_IMP_DT");
		super.deleteAllData("T_SGP_IMP_HD");

		// insert data to M_BANK
		String[][] testDataMBank = {
				{ "ID_BANK", "BANK_FULL_NAME", "BANK_CODE", "BANK_NAME",
						"BRANCH_CODE", "BRANCH_NAME", "BANK_TEL_NO",
						"BANK_FAX_NO", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "398", "Bank Money-XYZ", "0123", "Bank Money", "012", "XYZ",
						null, null, "0", "2011-4-4 14:57:18",
						"2011-4-4 14:57:18", "USERFULL", null } };
		String[][] testDataMComBankinfo = {
				{ "ID_COM_BANK", "ID_COM", "ID_BANK", "COM_SWIFT",
						"COM_ACCT_NO", "COM_ACCT_TYPE", "COM_ACCT_NAME",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT",
						"COM_CUR" },

				{ "0", "11", "398", null, "11111", "1", "8888",
						"2011-02-16 15:17:59", "2011-08-11 17:30:35", "admin",
						null, "DZD" } };
		String[][] testDataTSgpImpHd = {
				{ "ID_SGP_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "456", "456.txt", "/", "2011-08-17 00:00:00", "1",
						"2011-08-17 00:00:00", "2011-08-17 00:00:00",
						"sysadmin" } };

		super.insertData("M_BANK", testDataMBank);
		super.insertData("M_COM_BANKINFO", testDataMComBankinfo);
		super.insertData("T_SGP_IMP_HD", testDataTSgpImpHd);

	}

	/**
	 * 
	 * Case 1:test normal situation
	 * 
	 * Condition: <br>
	 * startIndex = 0 <br>
	 * BankAcc = "" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void testExecute01() {
		input.setStartIndex(new Integer(0));
		input.setBankAcc("");

		BLogicResult result = blogic.execute(input);
		RP_E_DIM_SP1_01Output output = (RP_E_DIM_SP1_01Output) result
				.getResultObject();

		List<LabelValueBean> cbBankAcc = output.getCbBankAcc();
		LabelValueBean valueBean = cbBankAcc.iterator().next();

		List<HashMap> listHistories = output.getListHistories();
		HashMap oneHistories = listHistories.iterator().next();

		// check the result
		assertEquals("Bank Money-XYZ-11111", valueBean.getLabel());
		assertEquals("", output.getBankAcc());

		assertEquals(new Integer(10), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());
		assertEquals(new Integer(0), output.getStartIndex());

		assertEquals("456", oneHistories.get("ID_SGP_IMP_BATCH").toString()
				.trim());
		assertEquals("456.txt", oneHistories.get("FILENAME"));
		assertEquals("/", oneHistories.get("CLOSE_MONTHYEAR"));
		assertEquals("1", oneHistories.get("STATUS"));
		assertEquals("17/08/2011 00:00:00",
				oneHistories.get("DATE_UPDATED_FMT"));

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2:test one of abnormal situation
	 * 
	 * Condition: <br>
	 * startIndex = null <br>
	 * BankAcc = "bank" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void testExecute02() {
		input.setStartIndex(null);
		input.setBankAcc("bank");

		BLogicResult result = blogic.execute(input);
		RP_E_DIM_SP1_01Output output = (RP_E_DIM_SP1_01Output) result
				.getResultObject();

		List<LabelValueBean> cbBankAcc = output.getCbBankAcc();
		LabelValueBean valueBean = cbBankAcc.iterator().next();

		List<HashMap> listHistories = output.getListHistories();
		HashMap oneHistories = listHistories.iterator().next();

		// check the result
		assertEquals("Bank Money-XYZ-11111", valueBean.getLabel());
		assertEquals("bank", output.getBankAcc());

		assertEquals(new Integer(10), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());
		assertEquals(new Integer(0), output.getStartIndex());

		assertEquals("456", oneHistories.get("ID_SGP_IMP_BATCH").toString()
				.trim());
		assertEquals("456.txt", oneHistories.get("FILENAME"));
		assertEquals("/", oneHistories.get("CLOSE_MONTHYEAR"));
		assertEquals("1", oneHistories.get("STATUS"));
		assertEquals("17/08/2011 00:00:00",
				oneHistories.get("DATE_UPDATED_FMT"));

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 3:test one of abnormal situation, the startIndex is less than 0
	 * 
	 * Condition: <br>
	 * startIndex = -1 <br>
	 * BankAcc = "" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void testExecute03() {
		input.setStartIndex(-1);
		input.setBankAcc("");

		BLogicResult result = blogic.execute(input);
		RP_E_DIM_SP1_01Output output = (RP_E_DIM_SP1_01Output) result
				.getResultObject();

		List<LabelValueBean> cbBankAcc = output.getCbBankAcc();
		LabelValueBean valueBean = cbBankAcc.iterator().next();

		List<HashMap> listHistories = output.getListHistories();
		HashMap oneHistories = listHistories.iterator().next();

		// check the result
		assertEquals("Bank Money-XYZ-11111", valueBean.getLabel());
		assertEquals("", output.getBankAcc());

		assertEquals(new Integer(10), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());
		assertEquals(new Integer(0), output.getStartIndex());

		assertEquals("456", oneHistories.get("ID_SGP_IMP_BATCH").toString()
				.trim());
		assertEquals("456.txt", oneHistories.get("FILENAME"));
		assertEquals("/", oneHistories.get("CLOSE_MONTHYEAR"));
		assertEquals("1", oneHistories.get("STATUS"));
		assertEquals("17/08/2011 00:00:00",
				oneHistories.get("DATE_UPDATED_FMT"));

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 4:test one of abnormal situation, the startIndex is more than
	 * totalHistories
	 * 
	 * Condition: <br>
	 * startIndex = 2 <br>
	 * BankAcc = "" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void testExecute04() {
		input.setStartIndex(2);
		input.setBankAcc("");

		BLogicResult result = blogic.execute(input);
		RP_E_DIM_SP1_01Output output = (RP_E_DIM_SP1_01Output) result
				.getResultObject();

		List<LabelValueBean> cbBankAcc = output.getCbBankAcc();
		LabelValueBean valueBean = cbBankAcc.iterator().next();

		List<HashMap> listHistories = output.getListHistories();
		HashMap oneHistories = listHistories.iterator().next();

		// check the result
		assertEquals("Bank Money-XYZ-11111", valueBean.getLabel());
		assertEquals("", output.getBankAcc());

		assertEquals(new Integer(10), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());
		assertEquals(new Integer(0), output.getStartIndex());

		assertEquals("456", oneHistories.get("ID_SGP_IMP_BATCH").toString()
				.trim());
		assertEquals("456.txt", oneHistories.get("FILENAME"));
		assertEquals("/", oneHistories.get("CLOSE_MONTHYEAR"));
		assertEquals("1", oneHistories.get("STATUS"));
		assertEquals("17/08/2011 00:00:00",
				oneHistories.get("DATE_UPDATED_FMT"));

		assertEquals("success", result.getResultString());
	}

}
