/**
 * @(#)RP_E_MEX_GR1_2BLogic_Test.java
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

import java.util.List;
import java.util.Map;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1_2Input;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1_2Output;

/**
 * Test Class for nttdm.bsys.e_mex.blogic.RP_E_MEX_GR1_2BLogic
 * 
 * @author leonzh
 */
public class RP_E_MEX_GR1_2BLogic_Test extends AbstractUtilTest {

	private RP_E_MEX_GR1_2BLogic blogic;
	private BLogicResult result;
	private RP_E_MEX_GR1_2Input input;
	private RP_E_MEX_GR1_2Output output;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_E_MEX_GR1_2BLogic();
		blogic.setQueryDAO(queryDAO);

		result = new BLogicResult();
		input = new RP_E_MEX_GR1_2Input();
		output = new RP_E_MEX_GR1_2Output();
	}

	/**
	 * 
	 * Case 1:test normal situation one
	 * 
	 * Condition: <br>
	 * ID_BATCH_REF_NO = "138" <br>
	 * ID_BATCH_TYPE = "G_GIR_P01" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute01() {
		// delete data
		super.deleteAllData("T_BATCH_LOG");

		// insert data
		String[][] data = {
				{ "ID_BATCH_LOG", "ID_BATCH_REF_NO", "ERROR_MSG",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ID_BATCH_TYPE", },
				{ "2403", "138", "No data match 02/2011",
						"2011-03-01 00:00:00", "2011-03-01 00:00:00",
						"USERFULL", "G_GIR_P01", } };
		super.insertData("T_BATCH_LOG", data);

		input.setIdBatch(new Integer(138));

		result = blogic.execute(input);
		output = (RP_E_MEX_GR1_2Output) result.getResultObject();
		List<Map<String, Object>> logRevievList = output.getLogsList();

		assertEquals(1, logRevievList.size());

		Map<String, Object> logReview = logRevievList.iterator().next();

		assertEquals("2403", logReview.get("ID_BATCH_LOG").toString());
		assertEquals("138", logReview.get("ID_BATCH_REF_NO").toString());
		assertEquals("No data match 02/2011", logReview.get("ERROR_MSG")
				.toString());
		assertEquals("2011-03-01", logReview.get("DATE_CREATED").toString());
		assertEquals("2011-03-01", logReview.get("DATE_UPDATED").toString());
		assertEquals("USERFULL", logReview.get("ID_LOGIN").toString());
		assertEquals("G_GIR_P01", logReview.get("ID_BATCH_TYPE").toString());
		assertEquals("01/03/2011 00:00:00", logReview.get("DATE_UPDATED_FMT")
				.toString());

		assertEquals("success", result.getResultString());
	}

}
