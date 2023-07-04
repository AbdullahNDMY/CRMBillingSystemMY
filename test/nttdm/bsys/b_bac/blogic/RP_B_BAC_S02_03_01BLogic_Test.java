/** 
 * @(#)RP_B_BAC_S02_03_01Logic_Test.java
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

import java.util.HashMap;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_03_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_03_01Output;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.m_cdm.blogic.RP_B_BAC_S02_03_01BLogic
 * 
 * @author xycao 
 */
public class RP_B_BAC_S02_03_01BLogic_Test extends AbstractUtilTest {

	private RP_B_BAC_S02_03_01BLogic blogic;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new RP_B_BAC_S02_03_01BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		// delete all exist data
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.deleteAllData("T_CUST_PLAN_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_PLAN_SVC");
		super.deleteAllData("M_PLAN_D");
		super.deleteAllData("M_SVG");
		super.deleteAllData("M_PLAN_H");
		super.deleteAllData("M_SVC");
		super.deleteAllData("M_CUST_CTC");
		
		super.deleteAllData("M_CUST");

		// insert data to
		String[][] testDataTBatchLog1 = {
				{ "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
						"APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
						"ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
						"BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
						"IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "203", "229743", "PS3", "SP", null, "IN", null,
						"1100153             ", "1", "1", "353", "3", "CA",
						"AWG", "BIF", "12", "0", "0", "2011-8-10  15:29:38",
						"2011-8-10  15:29:38", "sysadmin", null }, };
		super.insertData("T_CUST_PLAN_H", testDataTBatchLog1);

		String[][] testDataTBatchLog2 = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "2235", "203", "PS4", "2011-8-2", "2011-8-31", "1", "1",
						"2011-8-1", "2011-8-31", "M", "11", "U", "23", "0",
						null, "0", "2011-8-11  13:58:56", "2011-9-5  16:20:58",
						"sysadmin", null }, };
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);

		String[][] testDataTBatchLog3 = {
				{ "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_DESC",
						"QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT", "JOB_NO",
						"ID_PLAN", "PLAN_NAME", "PLAN_DESC", "ID_PLAN_GRP",
						"ITEM_GRP_NAME", "SVC_LEVEL1", "SVC_LEVEL2",
						"RATE_TYPE", "RATE_MODE", "RATE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_TYPE" },
				{ "250", "2235", null, "12", "2", "24", "0000000003", "1193",
						"P&P1", "Cel", "1523", "Des", "269", "362", "BA", "5",
						"9", "0", "0", "2011-8-11  13:58:56",
						"2011-8-11  13:58:56", "USERFULL", null, "S" }, };
		super.insertData("T_CUST_PLAN_LINK", testDataTBatchLog3);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "1100153             ", "229743", "CA", "MYR", "0", "", "BA",
						"PC", "0", "2011-8-31", "2011-9-5", "sysadmin", "1",
						null, "0", "0" } };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog4 = {
				{ "ID_CUST_PLAN", "ID_BILL_ACCOUNT", "IS_FIRST_BILL", "ID_QCS",
						"ID_QUO", "CUST_PO", "AC_MANAGER", "TERM", "BILL_FROM",
						"BILL_TO", "IS_RECURRING", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_BIF", "ID_AUDIT",
						"ID_CUST_PLAN_GRP" },
				{ "203", "1100153             ", "1", "BIF110025           ",
						"", "", "", "30 days", "2011-8-21", "2011-8-22", "2",
						"2011-8-22  16:38:00", "2011-9-5", "sysadmin",
						"BIF-0000001         ", null, "2235" } };
		super.insertData("T_BAC_D", testDataTBatchLog4);

		String[][] testDataTBatchLog6 = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "269", "ANY CATE1", "000000000000000", "3", "383767    ",
						"hook", "2011-7-14  14:05:25", "2011-8-8  11:41:51",
						"USERFULL", null } };
		super.insertData("M_SVG", testDataTBatchLog6);

		String[][] testDataTBatchLog8 = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "362", "269", "TYP", "S1", "", "1", "0", "0",
						"2011-8-2  9:10:41", "2011-8-4  16:04:06", "USERFULL",
						null } };
		super.insertData("M_SVC", testDataTBatchLog8);

		String[][] testDataTBatchLog7 = {
				{ "ID_PLAN", "PLAN_NAME", "PLAN_DESC", "CUSTOMER_TYPE",
						"BILL_CURRENCY", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1193", "P&P1", "Descriptions... Centre point1", "CORP",
						"BMD", "0", "2011-08-03 09:55:49",
						"2011-08-03 09:55:49", "USERFULL", null } };
		super.insertData("M_PLAN_H", testDataTBatchLog7);

		String[][] mCustData = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "ID_AUDIT",
						"MOBILE_NO" },
				{ "000001", "123456789", "CorpTestBil", "5555544444",
						"www.CorpTestBil.com", "CorpTestBil@CorpTestBil.com",
						"88555544", "88555543", "0", "0", "0",
						"2010-11-23 11:51:43", "2010-11-23 11:51:43",
						"USERFULL", null, null, null, null, null, "0",
						"987654321", "1122", "CORP", "5566995544", null, null,
						null } };
		super.insertData("M_CUST", mCustData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Duy Duong", "0123456789",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0123456789", "0123456789",
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);
	}

	/**
	 * 
	 * Case : T_BAC_H.IS_SINGPOST = 0 <br>
	 * result:Export SingPost = unchecked = 0 <br>
	 */
	public void testExecute01() {
		RP_B_BAC_S02_03_01Input input = new RP_B_BAC_S02_03_01Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);
		// input.setCboCustomerName("229743");
		input.setIdBillAccount("1100153             ");
		input.setIdCustPlan("203");
		
		input.setEditFlg("1");
		
		BLogicResult result = blogic.execute(input);
		RP_B_BAC_S02_03_01Output output = (RP_B_BAC_S02_03_01Output) result
				.getResultObject();
		assertEquals("0", output.getHeaderInfo().get("IS_SINGPOST"));
		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case : T_BAC_H.IS_SINGPOST = 1 <br>
	 * result:Export SingPost = checked = 1 <br>
	 */
	public void testExecute02() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("IS_SINGPOST", "1");
		param.put("ID_BILL_ACCOUNT", "1100153             ");
		super.updateDAO.execute("TEST.B_BAC_S02_221.UPDATE.SQL001", param);
		
		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);
		
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "229743", "380", "123-123", "GR-Singapore", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", null, null } };
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		
		RP_B_BAC_S02_03_01Input input = new RP_B_BAC_S02_03_01Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);
		// input.setCboCustomerName("229743");
		input.setIdBillAccount("1100153             ");
		input.setIdCustPlan("203");
		
		T_BAC_H inputHeaderInfo = new T_BAC_H();
		inputHeaderInfo.setExportSingPost("0");
		input.setInputHeaderInfo(inputHeaderInfo);
		input.setEditFlg("0");
		
		BLogicResult result = blogic.execute(input);
		RP_B_BAC_S02_03_01Output output = (RP_B_BAC_S02_03_01Output) result
				.getResultObject();
		assertEquals("0", output.getHeaderInfo().get("IS_SINGPOST"));
		assertEquals("success", result.getResultString());
	}

}
