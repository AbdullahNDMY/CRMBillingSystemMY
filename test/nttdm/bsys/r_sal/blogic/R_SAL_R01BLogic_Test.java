/**
 * @(#)R_SAL_R01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/26
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_sal.dto.R_SAL_R01Input;
import nttdm.bsys.r_sal.dto.R_SAL_R01Output;

/**
 * Test Class for nttdm.bsys.R_SAL.blogic.R_SAL_R01BLogic
 * 
 * @author Leonzh
 */
public class R_SAL_R01BLogic_Test extends AbstractUtilTest {

	private R_SAL_R01BLogic blogic;
	private R_SAL_R01Input input;
	private BLogicResult result;
	private R_SAL_R01Output output;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new R_SAL_R01BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new R_SAL_R01Input();

		// delete data
		super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_WF_ACTION");
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("T_WF_ACTION");
        super.deleteAllData("M_ATR_TFR");
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("M_USER");

		// insert data
        String[][] testDataMUser =
        {
            {"ID_USER" , "ID_DIV" , "ID_DEPT" , "ID_ROLE" , "USER_NAME" ,
                "PASSWORD" , "ID_LOGIN" , "PPLSOFT_ACC_ID"} ,
            {"USERFULL" , "0001" , "0001" , "0000000001" , "testuser1" ,
                "password" , SYS_ADMIN , "ACCID"}};
        super.insertData("M_USER", testDataMUser);
        
		String[][] mUserAccessData = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "USERFULL", "R", "R-SAL", "2", "2010-11-24 16:50:39",
						"2011-08-10 13:20:25", "USERFULL", "0", AUDIT_VALUE } };
		super.insertData("M_USER_ACCESS", mUserAccessData);
	}

	/**
	 * 
	 * Case 1: test normal situation <br>
	 * 
	 * Condition: <br>
	 * userAccess = "2" <br>
	 * ChkManual = "0" <br>
	 * ChkSingPost = "0" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute01() {
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("USERFULL");
		input.setUvo(uvo);
		// input.setSearchflag("true");
		// input.setChkManual("0");
		// input.setChkSingPost("0");

		result = blogic.execute(input);
		output = (R_SAL_R01Output) result.getResultObject();

		assertEquals("2", output.getAccessType());
		assertEquals(null, output.getChkManual());
		assertEquals(null, output.getChkSingPost());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2: test normal situation <br>
	 * 
	 * Condition: <br>
	 * userAccess = "2" <br>
	 * ChkManual = "1" <br>
	 * ChkSingPost = "1" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute02() {
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("USERFULL");
		input.setUvo(uvo);
		// input.setSearchflag(null);
		// input.setChkManual("0");
		// input.setChkSingPost("0");

		result = blogic.execute(input);
		output = (R_SAL_R01Output) result.getResultObject();

		assertEquals("2", output.getAccessType());
		assertEquals(null, output.getChkManual());
		assertEquals(null, output.getChkSingPost());

		assertEquals("success", result.getResultString());
	}

}
