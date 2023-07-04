/**
 * @(#)R_RRR_R01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/19
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_rrr.dto.R_RRR_R01Input;
import nttdm.bsys.r_rrr.dto.R_RRR_R01Output;

/**
 * Test Class for nttdm.bsys.R_RRR.blogic.R_RRR_R01BLogic
 * 
 * @author Leonzh
 */
public class R_RRR_R01BLogic_Test extends AbstractUtilTest {

	private R_RRR_R01BLogic blogic;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new R_RRR_R01BLogic();
		blogic.setQueryDAO(queryDAO);

		// delete all exist data
		super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_WF_ACTION");
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("T_WF_ACTION");
        super.deleteAllData("M_ATR_TFR");
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("M_USER");
	}

	/**
	 * 
	 * Case 1:Access normal situation AccessType = "2" <br>
	 * result.getResultString() = "success" <br>
	 */
	public void testExecute01() {
		// insert data to T_BATCH_LOG
		String[][] dataM_USER_ACCESS = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "sysadmin", "R", "R-RRR", "2", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "sysadmin", "0", null } };
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		
        super.insertData("M_USER", dataM_USER);
		super.insertData("M_USER_ACCESS", dataM_USER_ACCESS);

		R_RRR_R01Input input = new R_RRR_R01Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);

		BLogicResult result = blogic.execute(input);
		R_RRR_R01Output output = (R_RRR_R01Output) result.getResultObject();

		// ctmid check the result
		assertEquals("2", output.getAccessType());
	}

	/**
	 * 
	 * Case 2:Access normal situation AccessType = "1" <br>
	 * result.getResultString() = "success" <br>
	 */
	public void testExecute02() {
		// insert data to T_BATCH_LOG
		String[][] dataM_USER_ACCESS = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "sysadmin", "R", "R-RRR", "1", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "sysadmin", "0", null } };
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		
        super.insertData("M_USER", dataM_USER);
		super.insertData("M_USER_ACCESS", dataM_USER_ACCESS);

		R_RRR_R01Input input = new R_RRR_R01Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);

		BLogicResult result = blogic.execute(input);
		R_RRR_R01Output output = (R_RRR_R01Output) result.getResultObject();

		// ctmid check the result
		assertEquals("1", output.getAccessType());
	}

}
