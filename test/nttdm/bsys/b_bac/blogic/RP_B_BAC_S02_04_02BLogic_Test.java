/**
 * @(#)RP_B_BAC_S02_04_02BLogic_Test.java
 * 
 * Billing System NTTS
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
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_04_02Input;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_04_02BLogic
 * 
 * @author leonzh
 */
public class RP_B_BAC_S02_04_02BLogic_Test extends AbstractUtilTest {

	private RP_B_BAC_S02_04_02BLogic blogic;
	private RP_B_BAC_S02_04_02Input input;
	private BLogicResult result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_B_BAC_S02_04_02BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new RP_B_BAC_S02_04_02Input();
	}

	/**
	 * 
	 * Case 1:test normal situation one
	 * 
	 * Condition: <br>
	 * idBillAccount = "1100154" <br>
	 * exportSingPost = "0" <br>
	 * id_user = "21" <br>
	 * 
	 * Result: <br>
	 * check the data had update <br>
	 * 
	 */
	public void testExecute01() {
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
						null, null, "1", "0" } };
		super.insertData("T_BAC_H", tBacH);

		String[][] tBacDData = {
				{ "ID_CUST_PLAN", "ID_BILL_ACCOUNT", "IS_FIRST_BILL", "ID_QCS",
						"ID_QUO", "CUST_PO", "AC_MANAGER", "TERM", "BILL_FROM",
						"BILL_TO", "IS_RECURRING", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_BIF", "ID_AUDIT",
						"ID_CUST_PLAN_GRP" },
				{ "203", "1100154", "1", null, null, null, null, null,
						"2011-08-01", "2011-08-18", "2", "2011-08-22 16:38:00",
						"2011-09-05", "sysadmin", null, null, "2197" } };
		super.insertData("T_BAC_D", tBacDData);

		String[][] tCustPlanHData = {
				{ "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
						"APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
						"ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
						"BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
						"IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "203", "229743", "PS5", "NP", null, "IN", null, "1100154",
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
				{ "2197", "203", "PS1", "2011-05-11", "2011-08-06", "1", "1",
						"2011-05-11", "2012-05-11", "M", "23", "0", "25", "0",
						null, "0", "2011-08-02 16:14:21",
						"2011-09-05 16:20:58", "sysadmin", null } };
		super.insertData("T_CUST_PLAN_D", tCustPlanDData);

		// change BILL_DESC to not null
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("BILL_DESC", "cao");
		param.put("ID_CUST_PLAN", "203");
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
						null, "Cel", null, "Des", "268", "311", "EX", "5",
						"23", "1", "0", "2011-08-02 16:14:35",
						"2011-08-10 16:31:43", "USERFULL", null, "S" } };
		super.insertData("T_CUST_PLAN_LINK", tCustPlanLinkData);

		String[][] tMPlanH = {
				{ "ID_PLAN", "PLAN_NAME", "PLAN_DESC", "CUSTOMER_TYPE",
						"BILL_CURRENCY", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1191", "Plan for test case",
						"Use this plan for test case captures", "CONS", "MYR",
						"0", "2011-08-02 09:30:49", "2011-08-02 09:30:49",
						"USERFULL", null } };
		super.insertData("M_PLAN_H", tMPlanH);

		String[][] mSvgData = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "268", "ANY CATE1", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-06-28 08:17:25",
						"2011-08-08 11:41:51", "USERFULL", null } };
		super.insertData("M_SVG", mSvgData);

		String[][] mSvcData = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "311", "121", "TYP", "S1", null, null, "0", "0",
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

		T_BAC_H inputHeaderInfo = new T_BAC_H();
		inputHeaderInfo.setIdBillAccount("1100154             ");
		inputHeaderInfo.setExportSingPost("0");
		input.setInputHeaderInfo(inputHeaderInfo);

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("21");
		input.setUvo(uvo);

		String[] idKeys = { "2197_1100154             " };
		input.setIdKeys(idKeys);

		result = blogic.execute(input);

		// check billAccountNo had update
		HashMap<String, Object> headInfoParam = new HashMap<String, Object>();
		String[] idCustPlans = { "203" };
		headInfoParam.put("idCustPlans", idCustPlans);
		List<Map<String, Object>> headerInfos = this.queryDAO
				.executeForMapList("TEST.BSYS.B_BAC_SO2_242.SELECT.SQL002",
						headInfoParam);
		Map<String, Object> headerInfo = headerInfos.get(0);
		assertEquals("1100154             ", headerInfo.get("ID_BILL_ACCOUNT"));
		assertEquals("sysadmin", headerInfo.get("ID_LOGIN")); 

		// check PaymentMethodDetail had update
		HashMap<String, Object> custPlanParam = new HashMap<String, Object>();
		custPlanParam.put("idBillAccount", "1100154             ");
		List<Map<String, Object>> custPlans = this.queryDAO.executeForMapList(
				"TEST.BSYS.B_BAC_SO2_231.SELECT.SQL002", custPlanParam);
		Map<String, Object> custPlan = custPlans.get(0);
		assertEquals("1100154             ", custPlan.get("ID_BILL_ACCOUNT"));
		assertEquals("sysadmin", custPlan.get("ID_LOGIN"));

		// check IsSingPost had update
		HashMap<String, Object> paramForIsSingPost = new HashMap<String, Object>();
		paramForIsSingPost.put("idBillAccount", "1100154             ");
		List<Map<String, Object>> tBacHDatas = this.queryDAO.executeForMapList(
				"TEST.BSYS.B_BAC_SO2_231.SELECT.SQL001", paramForIsSingPost);
		Map<String, Object> tBacHData = tBacHDatas.get(0);
		assertEquals("0", tBacHData.get("IS_SINGPOST"));
		assertEquals("21", tBacHData.get("ID_LOGIN"));

		assertEquals("success", result.getResultString());
	}
}
