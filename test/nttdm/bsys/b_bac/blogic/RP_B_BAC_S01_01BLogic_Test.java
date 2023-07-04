/**
 * @(#)RP_B_BAC_S01_01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/19
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bac.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_01Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.m_cdm.blogic.RP_B_BAC_S01_01BLogic
 * 
 * @author xycao
 */
public class RP_B_BAC_S01_01BLogic_Test extends AbstractUtilTest {

	private RP_B_BAC_S01_01BLogic blogic;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new RP_B_BAC_S01_01BLogic();
		blogic.setQueryDAO(queryDAO);

		// delete all exist data
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("M_USER_ACCESS");
		super.deleteAllData("M_USER");
	}

	/**
	 * 
	 * Case 1:combox normal situation CUST_NAME = "Duy Duong" <br>
	 * ID_CUST = "229743" <br>
	 * result.getResultString() = "success" <br>
	 */
	public void testExecute01() {
		// insert data to T_BATCH_LOG
		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE",
						"MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "USERFULL", "", "", null, "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"", "" }, };
		super.insertData("M_CUST", testDataTBatchLog);
		
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
		String[][] m_user_accessS1 = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },

				{ "sysadmin", "123", "B-BAC", "1", "2012-03-12", "2012-03-12",
						"sysadmin", "0", "0" } };
		super.insertData("m_user_access", m_user_accessS1);
        
		String[][] testDataTBatchLog2 = {
				{ "ID_REF", "ID_CUST", "ID_CONSLT", "ID_QUO", "REQ_USER",
						"DATE_REQ", "CUST_MODE", "CTTERM", "CTTERM3_DAY",
						"DEPOSIT", "REMARKS", "ID_STATUS", "IS_CLOSED",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN" },
				{ "QCS110001           ", "229743", "sysadmin",
						"0                   ", "duoc.nguyen", "2011-03-06",
						"0", "1", "1", "3", "", "DS0", "0", "0",
						"2011-03-23 15:52:55", "2011-03-23 16:01:28",
						"USERFULL" } };
		super.insertData("T_QCS_H", testDataTBatchLog2);

		RP_B_BAC_S01_01Input input = new RP_B_BAC_S01_01Input();
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
		RP_B_BAC_S01_01Output output = (RP_B_BAC_S01_01Output) result
				.getResultObject();
//		String[] strLabel = new String[5];
//		String[] strValue = new String[5];
//		List<LabelValueBean> list = (List<LabelValueBean>) output.getListCust();
//		for (int i = 0; i < list.size(); i++) {
//			LabelValueBean label = (LabelValueBean) list.get(i);
//			strLabel[i] = label.getLabel();
//			strValue[i] = label.getValue();
//		}
		// ctmid check the result
//		assertEquals("2", output.getAccessType());
//		assertEquals("Duy Duong-229743", strLabel[0]);
//		assertEquals("229743", strValue[0]);
		assertEquals("success", result.getResultString());
		assertEquals("1", output.getAccessType());
	}

	/**
	 * 
	 * Case 2:Access normal situation AccessType = "2" <br>
	 * result.getResultString() = "success" <br>
	 */
	@SuppressWarnings("rawtypes")
	public void testExecute02() {
		//Map[] tBacH = super.select("M_USER_ACCESS", "ID_USER = 'sysadmin'", "");
		// ctmid check the result
		//assertEquals("2", tBacH[0].get("ACCESS_TYPE"));
	}
}
