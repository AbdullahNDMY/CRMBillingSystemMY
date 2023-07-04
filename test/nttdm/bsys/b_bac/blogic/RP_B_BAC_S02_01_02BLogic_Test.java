/**
 * @(#)RP_B_BAC_S02_01_02BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/08
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bac.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_01_02Input;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_01_02BLogic
 * 
 * @author i-leonzh
 */
public class RP_B_BAC_S02_01_02BLogic_Test extends AbstractUtilTest {

	private RP_B_BAC_S02_01_02BLogic blogic;
	private RP_B_BAC_S02_01_02Input input;
	private BLogicResult result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_B_BAC_S02_01_02BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new RP_B_BAC_S02_01_02Input();

		// delete data
		super.deleteAllData("M_CUST_CTC");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.deleteAllData("T_CUST_PLAN_H");
		super.deleteAllData("M_PLAN_SVC");
		super.deleteAllData("M_PLAN_D");
		super.deleteAllData("M_PLAN_H");
		super.deleteAllData("M_SVG");
		super.deleteAllData("M_SVC");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_BANKINFO");
		super.deleteAllData("M_CUST");
		// insert data to custInfo is not null
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
				{ "21", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		// insert data to listPlanInfo.size() > 0
		String[][] tBacH = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "1100154", "229743", "CA", "MYR", "0", null, "BA", "PC", "0",
						"2011-01-18 12:43:28", "2011-01-18 12:43:28", "DM",
						null, null, "1", "0" },
				{ "1100155", "229743", "CA", "MYR", "0", null, "BA", "PC", "0",
						"2011-01-18 12:43:28", "2011-01-18 12:43:28", "DM",
						null, null, "1", "0" } };
		super.insertData("T_BAC_H", tBacH);

		String[][] tBacDData = {
				{ "ID_CUST_PLAN", "ID_BILL_ACCOUNT", "IS_FIRST_BILL", "ID_QCS",
						"ID_QUO", "CUST_PO", "AC_MANAGER", "TERM", "BILL_FROM",
						"BILL_TO", "IS_RECURRING", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_BIF", "ID_AUDIT",
						"ID_CUST_PLAN_GRP" },
				{ "43", "1100154", "1", null, null, null, null, null,
						"2011-08-01", "2011-08-18", "2", "2011-08-22 16:38:00",
						"2011-09-05", "sysadmin", null, null, "2197" },
				{ "43", "1100155", "1", null, null, null, null, null,
						"2011-08-05", "2011-08-18", "2", "2011-08-22 16:38:00",
						"2011-08-31", "sysadmin", null, null, "2198" } };
		super.insertData("T_BAC_D", tBacDData);

		String[][] tCustPlanHData = {
				{ "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
						"APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
						"ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
						"BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
						"IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "43", "229743", "PS5", "NP", null, "IN", null, "1100155",
						"1", "1", "356", "5", "CA", "KHR", "BIF", "12", "0",
						"0", "2011-08-02 16:14:17", "2011-09-05", "sysadmin",
						null },
				{ "62", "229743", "PS5", "NP", null, "IN", null, "1100155",
						"1", "1", "356", "5", "CA", "KHR", "BIF", "12", "0",
						"0", "2011-08-02 16:14:17", "2011-09-05", "sysadmin",
						null } };
		super.insertData("T_CUST_PLAN_H", tCustPlanHData);

		String[][] tCustPlanDData = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "2197", "43", "PS4", "2011-05-11", "2011-08-06", "1", "1",
						"2011-05-11", "2012-05-11", "M", "23", "0", "25", "0",
						null, "0", "2011-08-02 16:14:21",
						"2011-09-05 16:20:58", "sysadmin", null },
				{ "2198", "43", "PS4", "2011-05-11", "2011-08-06", "1", "1",
						"2011-05-11", "2012-05-11", "M", "23", "0", "25", "0",
						null, "0", "2011-08-02 16:14:21",
						"2011-09-05 16:20:58", "sysadmin", null } };
		super.insertData("T_CUST_PLAN_D", tCustPlanDData);

		// change BILL_DESC to not null
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("BILL_DESC", "bill desc");
		param.put("ID_CUST_PLAN", "43");
		this.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL007", param);

		String[][] tCustPlanLinkData = {
				{ "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_DESC",
						"QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT", "JOB_NO",
						"ID_PLAN", "PLAN_NAME", "PLAN_DESC", "ID_PLAN_GRP",
						"ITEM_GRP_NAME", "SVC_LEVEL1", "SVC_LEVEL2",
						"RATE_TYPE", "RATE_MODE", "RATE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_TYPE" },
				{ "23", "2197", null, "12", "12", "144", "GBIL-001", "1191",
						null, "sd sdf ssdff ds sdf ", null, null, "268", "311",
						"EX", "5", "23", "1", "0", "2011-08-02 16:14:35",
						"2011-08-10 16:31:43", "USERFULL", null, "S" },
				{ "24", "2198", null, "12", "12", "144", "GBIL-001", "1191",
						null, "sd sdf ssdff ds sdf ", null, null, "268", "311",
						"EX", "5", "23", "1", "0", "2011-08-02 16:14:35",
						"2011-08-10 16:31:43", "USERFULL", null, "S" } };
		super.insertData("T_CUST_PLAN_LINK", tCustPlanLinkData);

		String[][] tMPlanH = {
				{ "ID_PLAN", "PLAN_NAME", "PLAN_DESC", "CUSTOMER_TYPE",
						"BILL_CURRENCY", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1191", "Plan for test case",
						"Use this plan for test case captures", "CONS", "MYR",
						"0", "2011-08-02 09:30:49", "2011-08-02 09:30:49",
						"USERFULL", null },
				{ "1192", "Plan for test case",
						"Use this plan for test case captures", "CONS", "MYR",
						"0", "2011-08-02 09:30:49", "2011-08-02 09:30:49",
						"USERFULL", null } };
		super.insertData("M_PLAN_H", tMPlanH);

		String[][] mSvgData = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-06-28 08:17:25",
						"2011-08-08 11:41:51", "USERFULL", null },
				{ "269", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-06-28 08:17:25",
						"2011-08-08 11:41:51", "USERFULL", null } };
		super.insertData("M_SVG", mSvgData);

		String[][] mSvcData = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "311", "121", "TYP", "No GST", null, null, "0", "0",
						"2011-02-25 14:53:37", "2011-02-25 14:53:37",
						"USERFULL", null, },
				{ "312", "121", "TYP", "No GST", null, null, "0", "0",
						"2011-02-25 14:53:37", "2011-02-25 14:53:37",
						"USERFULL", null, } };
		super.insertData("M_SVC", mSvcData);

		// insert data to address != null
		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "21", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "21", "380", "123-123", "GR-Singapore", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", null, null } };
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
	}

	/**
	 * 
	 * Case 1:test normal situation one
	 * 
	 * Condition: <br>
	 * Payment Method onchanged <br>
	 * Billing Address onchanged <br>
	 * Export SingPost checked <br>
	 * Attn Combo box is "Duy Dong" <br>
	 * 
	 * Result: <br>
	 * Payment Method is "CA" <br>
	 * Billing Address is "BA" <br>
	 * Export SingPost is "1" <br>
	 * Attn Combo box is "PC" <br>
	 * 
	 */
	public void test01() {
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("21");
		String[] idKeys = { "43_1100154             ",
				"62_1100155             " };
		T_BAC_H inputHeaderInfo = new T_BAC_H();
		inputHeaderInfo.setBillCurrency("");
		inputHeaderInfo.setContactType("PC");
		inputHeaderInfo.setCustAdrType("BA");
		inputHeaderInfo.setExportSingPost("1");
		inputHeaderInfo.setIdCust("21");
		inputHeaderInfo.setPaymentMethod("CA");

		input.setUvo(uvo);
		input.setIdKeys(idKeys);
		input.setInputHeaderInfo(inputHeaderInfo);

		result = blogic.execute(input);

		// check the header info data had insert
		List<Map<String, Object>> headerInfoList = this.queryDAO
				.executeForMapList("TEST.BSYS.B_BAC_SO2_212.SELECT.SQL001",
						null);
		assertNotNull(headerInfoList);
		Map<String, Object> headerInfo = headerInfoList.get(0);

		assertEquals("CA", headerInfo.get("PAYMENT_METHOD"));
		assertEquals("BA", headerInfo.get("CUST_ADR_TYPE"));
		assertEquals("1", headerInfo.get("IS_SINGPOST"));
		assertEquals("PC", headerInfo.get("CONTACT_TYPE"));

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2:test normal situation one
	 * 
	 * Condition: <br>
	 * Payment Method had no onchanged <br>
	 * Billing Address had no onchanged <br>
	 * Export SingPost had unchecked <br>
	 * Attn Combo box is "Please Select One" <br>
	 * 
	 * Result: <br>
	 * Payment Method is "CQ" <br>
	 * Billing Address is "CA" <br>
	 * Export SingPost is "0" <br>
	 * Attn Combo box is "  " <br>
	 * 
	 */
	public void test02() {
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("21");
		String[] idKeys = { "43_1100154             ",
				"62_1100155             " };
		T_BAC_H inputHeaderInfo = new T_BAC_H();
		inputHeaderInfo.setBillCurrency("");
		// change Attn Combo box to "  "
		inputHeaderInfo.setContactType("  ");
		// change Billing Address to "CA"
		inputHeaderInfo.setCustAdrType("CA");
		// change Export SingPost to "0"
		inputHeaderInfo.setExportSingPost("0");
		inputHeaderInfo.setIdCust("21");
		// change payment to "CQ"
		inputHeaderInfo.setPaymentMethod("CQ");

		input.setUvo(uvo);
		input.setIdKeys(idKeys);
		input.setInputHeaderInfo(inputHeaderInfo);

		result = blogic.execute(input);

		// check the header info data had insert
		List<Map<String, Object>> headerInfoList = this.queryDAO
				.executeForMapList("TEST.BSYS.B_BAC_SO2_212.SELECT.SQL001",
						null);
		assertNotNull(headerInfoList);
		Map<String, Object> headerInfo = headerInfoList.get(0);

		assertEquals("CQ", headerInfo.get("PAYMENT_METHOD"));
		assertEquals("CA", headerInfo.get("CUST_ADR_TYPE"));
		assertEquals("0", headerInfo.get("IS_SINGPOST"));
		assertEquals("  ", headerInfo.get("CONTACT_TYPE"));

		assertEquals("success", result.getResultString());
	}
}
