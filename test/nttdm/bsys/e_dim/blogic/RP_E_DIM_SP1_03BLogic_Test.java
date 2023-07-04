/**
 * @(#)RP_E_DIM_SP1_03BLogic_Test.java
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_03Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_03Output;

/**
 * Test Class for nttdm.bsys.e_dim.blogic.RP_E_DIM_SP1_03BLogic
 * 
 * @author leonzh
 */
public class RP_E_DIM_SP1_03BLogic_Test extends AbstractUtilTest {

	private RP_E_DIM_SP1_03BLogic blogic;
	private RP_E_DIM_SP1_03Input input;
	private List<Map<String, Object>> listLogs;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic = new RP_E_DIM_SP1_03BLogic();
		blogic.setQueryDAO(queryDAO);
		input = new RP_E_DIM_SP1_03Input();

		// delete all exist data
		super.deleteAllData("T_BATCH_LOG");
	}

	/**
	 * 
	 * Case 1:test normal situation
	 * 
	 * Condition:<br>
	 * ID_BATCH_LOG = 12 <br>
	 * ERROR_MSG = "my errer messages" <br>
	 * DATE_UPDATED = "2011-08-17 00:00:00" <br>
	 * ID_BATCH_REF_NO = 44 <br>
	 * ID_BATCH_TYPE = "G_SGP_P02" <br>
	 * 
	 * Result: <br>
	 * mapResult.get("ID_BATCH_LOG") = 12 <br>
	 * mapResult.get("ERROR_MSG") = "my errer messages" <br>
	 * mapResult.get("DATE_UPDATED_FMT") = "2011/08/17 00:00:00" <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute01() {
		// insert data to T_BATCH_LOG
		String[][] testDataTBatchLog = {
				{ "ID_BATCH_LOG", "ID_BATCH_REF_NO", "ERROR_MSG",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ID_BATCH_TYPE" },
				{ "12", "44", "my errer messages", "2011-08-17 00:00:00",
						"2011-08-17 00:00:00", "sysadmin", "G_SGP_P02" } };
		super.insertData("T_BATCH_LOG", testDataTBatchLog);

		input.setIdSgpImpBatch(new Integer(44));

		BLogicResult result = blogic.execute(input);
		listLogs = ((RP_E_DIM_SP1_03Output) result.getResultObject())
				.getListLog();

		// check the result
		Iterator<Map<String, Object>> it = listLogs.iterator();
		Map<String, Object> mapResult = it.next();

		assertEquals("12", mapResult.get("ID_BATCH_LOG").toString().trim());
		assertEquals("my errer messages", mapResult.get("ERROR_MSG"));
		assertEquals("17/08/2011 00:00:00", mapResult.get("DATE_UPDATED_FMT"));

		assertEquals("success", result.getResultString());
	}
}
