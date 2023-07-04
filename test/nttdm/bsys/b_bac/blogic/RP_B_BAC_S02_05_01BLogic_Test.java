/**
 * @(#)RP_B_BAC_S02_05_01BLogic_Test.java
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

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_05_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_05_01Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

import org.apache.struts.util.LabelValueBean;

/**
 * Test Class for nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_05_01BLogic
 * 
 * @author leonzh
 */
public class RP_B_BAC_S02_05_01BLogic_Test extends AbstractUtilTest {

	private RP_B_BAC_S02_05_01BLogic blogic;
	private RP_B_BAC_S02_05_01Input input;
	private RP_B_BAC_S02_05_01Output output;
	private BLogicResult result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_B_BAC_S02_05_01BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);
		input = new RP_B_BAC_S02_05_01Input();
		
		super.deleteAllData("T_TEAMWORK");
        super.deleteAllData("T_MAIL_ACCOUNT");
        super.deleteAllData("T_MAIL_ADDRESS");
        super.deleteAllData("T_CPE_CONF_D");
        super.deleteAllData("T_CPE_CONF_H");
        super.deleteAllData("T_SERVER_INFO_D");
        super.deleteAllData("T_SERVER_INFO_H");
        super.deleteAllData("T_FIREWALL_POLICY");
        super.deleteAllData("T_FIREWALL_INT_IP");
        super.deleteAllData("T_FIREWALL");
        super.deleteAllData("T_MRTG");
        super.deleteAllData("T_INST_ADR");
        super.deleteAllData("T_IT_CONTACT_D");
        super.deleteAllData("T_IT_CONTACT_H");
        super.deleteAllData("T_DNS_ZONE_REC");
        super.deleteAllData("T_DNS_ZONE");
        super.deleteAllData("T_RACK_POWER_D");
        super.deleteAllData("T_RACK_POWER_H");
        super.deleteAllData("T_FTP_INT");
        super.deleteAllData("T_SUBSCRIPTION_INFO");
	}

	/**
	 * 
	 * Case 1:test normal situation
	 * 
	 * Condition: <br>
	 * subIDMap.get("ID_CUST_PLAN") = subID and
	 * subIDMap.get("BILL_DESC_DISPLAY") = planDescription <br>
	 * displayBillRefInfo.get("SET_VALUE") = "MY" <br>
	 * gBilP01Output.getBatchStatus() != "1" <br>
	 * 
	 * Result: <br>
	 * check the data had in output <br>
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
		super.deleteAllData("M_GSET_D");

		super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_WF_ACTION");
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("T_WF_ACTION");
        super.deleteAllData("M_ATR_TFR");
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("M_USER");
		
		super.deleteAllData("M_SUB_MODULES");
		super.deleteAllData("M_MODULES");
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
				{ "21", "123456789", "CorpTestBil", "5555544444",
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
				{ "1100154", "21", "CA", "MYR", "0", null, "BA", "PC", "0",
						"2011-01-18 12:43:28", "2011-01-18 12:43:28", "DM",
						null, null, "1", "0" },
				{ "1100155", "21", "CQ", "MYR", "0", null, "BA", "PC", "0",
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
						 null, null, "1", "2011-08-22 16:38:00",
						"2011-09-05", "sysadmin", null, null, "2197" },
				{ "43", "1100154", "1", null, null, null, null, null,
						null, null, "1", "2011-08-22 16:38:00",
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
				{ "43", "21", "PS5", "NP", null, "IN", null, "1100155", "1",
						"1", "356", "5", "CA", "KHR", "BIF", "12", "0", "0",
						"2011-08-02 16:14:17", "2011-09-05", "sysadmin",
						null },
				{ "62", "21", "PS5", "NP", null, "IN", null, "1100155", "1",
						"1", "356", "5", "CA", "KHR", "BIF", "12", "0", "0",
						"2011-08-02 16:14:17", "2011-09-05", "sysadmin",
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

		// insert data to displayBillRefInfo.get("SET_VALUE") = "MY"
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"SYSTEMBASE",
						"1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"sysadmin", "MY", null, "1" } };
		super.insertData("M_GSET_D", mGsetDData);

		// insert data to userAccess != null
		String[][] mModulesData = {
				{ "ID_MODULE", "MOD_NAME", "MOD_DESC", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ORDER_SEQ", "IS_DISPLAY" },
				{ "B", "Billing Mgmt System", null, "2010-10-26 15:52:43",
						"2010-10-26 15:52:43", "sysadmin", "2", "1" } };
		super.insertData("M_MODULES", mModulesData);

		String[][] mSubModulesData = {
				{ "ID_MODULE", "ID_SUB_MODULE", "SUB_MOD_NAME", "SUB_MOD_DESC",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ORDER_SEQ", "IS_DISPLAY", "PATH_MODULE", "IS_FIELD" },
				{ "B", "B-BAC", "Billing Accounts", "", "2010-10-26 15:52:43",
						"2010-10-26 15:52:43", "sysadmin", "4", "1",
						"/B_BAC/RP_B_BAC_S01_01BL.do", "0" } };
		super.insertData("M_SUB_MODULES", mSubModulesData);
		String[][] M_USER = {
				{ "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT",
						"ID_ROLE", "PASSWORD","ID_LOGIN"},
				{ "USERFULL", "john", "0001", "0002", "123",
						"123","123"} };
		super.insertData("M_USER", M_USER);
		String[][] MUserAccessData = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "USERFULL", "B", "B-BIF", "2", "2010-11-24 16:50:39",
						"2011-08-10 13:20:25", "USERFULL", "0", null },
				{ "USERFULL", "B", "B-BAC", "2", "2010-11-24 16:50:39",
							"2011-08-10 13:20:25", "USERFULL", "0", null } };
		super.insertData("M_USER_ACCESS", MUserAccessData);
		
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "43", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", AUDIT_VALUE, "1" },
                { "62", "", "", "", "", "", "", "",
                    "", "", "",
                    "", "", "", "sysadmin", "0", AUDIT_VALUE, "2" } };
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);

		input.setCboCustomerName("21");
		input.setIdCust("21");
		input.setIdBillAccount("1100154             ");

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("USERFULL");
		input.setUvo(uvo);

		result = blogic.execute(input);
		output = (RP_B_BAC_S02_05_01Output) result.getResultObject();

		assertEquals("0", output.getIsDisplayTerCnBut());
		assertEquals("1", output.getBillRefDispCond());
		assertEquals("1", output.getBillRefDispCondSess());

		assertEquals("2", output.getAccessType());

		// check contactInfo
		HashMap<String, Object> contactInfo = output.getContactInfo();
		assertEquals("21", contactInfo.get("ID_CUST"));
		assertEquals("PC", contactInfo.get("CONTACT_TYPE"));
		assertEquals("Corp Test GBIL02", contactInfo.get("CONTACT_NAME"));
		assertEquals("Corp Test GBIL02", contactInfo.get("DESIGNATION"));
		assertEquals("test@test.com", contactInfo.get("EMAIL"));
		assertEquals("78787887", contactInfo.get("TEL_NO"));
		assertEquals(null, contactInfo.get("DID_NO"));
		assertEquals(null, contactInfo.get("FAX_NO"));
		assertEquals(null, contactInfo.get("MOBILE_NO"));
		assertEquals("USERFULL", contactInfo.get("ID_LOGIN"));
		assertEquals("0", contactInfo.get("IS_DELETED"));
		assertEquals(null, contactInfo.get("ID_AUDIT"));

		// check billAccountInfo
		HashMap<String, Object> billAccountInfo = output.getHeaderInfo();
		assertEquals("1", billAccountInfo.get("IS_SINGPOST"));
		assertEquals(null, billAccountInfo.get("ID_AUDIT"));
		assertEquals("CA", billAccountInfo.get("PAYMENT_METHOD"));
		assertEquals("0", billAccountInfo.get("TOTAL_AMT_DUE").toString());
		assertEquals("2011-01-18", billAccountInfo.get("DATE_UPDATED")
				.toString());
		assertEquals("1100154             ",
				billAccountInfo.get("ID_BILL_ACCOUNT"));
		assertEquals("PC", billAccountInfo.get("CONTACT_TYPE"));
		assertEquals("DM", billAccountInfo.get("ID_LOGIN"));
		assertEquals("0", billAccountInfo.get("IS_DELETED"));
		assertEquals("0", billAccountInfo.get("IS_DISPLAY_EXP_AMT"));
		assertEquals("BA", billAccountInfo.get("CUST_ADR_TYPE"));
		assertEquals("MYR", billAccountInfo.get("BILL_CURRENCY"));
		assertEquals(null, billAccountInfo.get("FIXED_FOREX"));
		assertEquals(null, billAccountInfo.get("EXPORT_CURRENCY"));
		assertEquals("2011-01-18", billAccountInfo.get("DATE_CREATED")
				.toString());
		assertEquals("21", billAccountInfo.get("ID_CUST"));

		// check listContact
		List<LabelValueBean> listContact = output.getListContact();
		assertEquals("Corp Test GBIL02", listContact.get(0).getLabel());
		assertEquals("PC", listContact.get(0).getValue());

		// check addressInfo
		HashMap<String, Object> addressInfo = output.getAddressInfo();
		assertEquals("BILLING ADDRESS 1", addressInfo.get("address1"));
		assertEquals("BILLING ADDRESS 2", addressInfo.get("address2"));
		assertEquals("BILLING ADDRESS 3", addressInfo.get("address3"));
		assertEquals("57100,  Malaysia    ", addressInfo.get("address4"));
		assertEquals("test@test.com", addressInfo.get("email"));

		// check newListPlanInfo
		List<HashMap<String, Object>> newListPlanInfo = output
				.getListPlanInfo();
		assertEquals(4, newListPlanInfo.size());

		// check custInfo
		HashMap<String, Object> custInfo = output.getCustInfo();
		assertEquals("21", custInfo.get("ID_CUST"));
		assertEquals("2010-11-23", custInfo.get("DATE_UPDATED").toString());
		assertEquals("CORP", custInfo.get("CUSTOMER_TYPE"));
		assertEquals("0", custInfo.get("IS_DELETED"));
		assertEquals("123456789", custInfo.get("CUST_ACC_NO"));
		assertEquals("0", custInfo.get("INTER_COMP"));
		assertEquals("USERFULL", custInfo.get("ID_LOGIN"));
		assertEquals("CorpTestBil", custInfo.get("CUST_NAME"));
		assertEquals("0", custInfo.get("IS_EXPORTED"));
		assertEquals("0", custInfo.get("PRINT_STATEMENT"));
		assertEquals("2010-11-23", custInfo.get("DATE_CREATED").toString());

		// check bss
		assertNotNull(output.getBss());
		
		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2:test normal situation
	 * 
	 * Condition: <br>
	 * subIDMap.get("ID_CUST_PLAN") ！= subID <br>
	 * displayBillRefInfo.get("SET_VALUE") != "MY" <br>
	 * gBilP01Output.getBatchStatus() = "1" <br>
	 * 
	 * Result: <br>
	 * check the data had in output <br>
	 * 
	 */
	public void testExecute02() {
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
		super.deleteAllData("M_GSET_D");
		
		super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_WF_ACTION");
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("T_WF_ACTION");
        super.deleteAllData("M_ATR_TFR");
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("M_USER");
		
		super.deleteAllData("M_SUB_MODULES");
		super.deleteAllData("M_MODULES");
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
				{ "21", "123456789", "CorpTestBil", "5555544444",
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
				{ "1100154", "21", "CA", "MYR", "0", null, "BA", "PC", "0",
						"2011-01-18 12:43:28", "2011-01-18 12:43:28", "DM",
						null, null, "1", "0" },
				{ "1100155", "21", "CQ", "MYR", "0", null, "BA", "PC", "0",
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
						"2011-08-01", "2011-08-18", "1", "2011-08-22 16:38:00",
						"2011-09-05", "sysadmin", null, null, "2197" },
				{ "62", "1100154", "1", null, null, null, null, null,
						"2011-08-05", "2011-08-18", "1", "2011-08-22 16:38:00",
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
				{ "43", "21", "PS3", "NP", null, "IN", null, "1100155", "1",
						"1", "356", "5", "CA", "KHR", "BIF", "12", "0", "0",
						"2011-08-02 16:14:17", "2011-09-05", "sysadmin",
						null },
				{ "62", "21", "PS3", "NP", null, "IN", null, "1100155", "1",
						"1", "356", "5", "CA", "KHR", "BIF", "12", "0", "0",
						"2011-08-02 16:14:17", "2011-09-05", "sysadmin",
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
				{ "2198", "62", "PS4", "2011-05-11", "2011-08-06", "1", "1",
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

		// insert data to displayBillRefInfo.get("SET_VALUE") = "MY"
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"SYSTEMBASE",
						"1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"sysadmin", "44", null, "1" } };
		super.insertData("M_GSET_D", mGsetDData);

		// insert data to userAccess != null
		String[][] mModulesData = {
				{ "ID_MODULE", "MOD_NAME", "MOD_DESC", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ORDER_SEQ", "IS_DISPLAY" },
				{ "B", "Billing Mgmt System", null, "2010-10-26 15:52:43",
						"2010-10-26 15:52:43", "sysadmin", "2", "1" } };
		super.insertData("M_MODULES", mModulesData);

		String[][] mSubModulesData = {
				{ "ID_MODULE", "ID_SUB_MODULE", "SUB_MOD_NAME", "SUB_MOD_DESC",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ORDER_SEQ", "IS_DISPLAY", "PATH_MODULE", "IS_FIELD" },
				{ "B", "B-BAC", "Billing Accounts", "", "2010-10-26 15:52:43",
						"2010-10-26 15:52:43", "sysadmin", "4", "1",
						"/B_BAC/RP_B_BAC_S01_01BL.do", "0" } };
		super.insertData("M_SUB_MODULES", mSubModulesData);
		
		String[][] M_USER = {
				{ "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT",
						"ID_ROLE", "PASSWORD","ID_LOGIN"},
				{ "USERFULL", "john", "0001", "0002", "123",
						"123","123"} };
		super.insertData("M_USER", M_USER);
		
		String[][] MUserAccessData = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "USERFULL", "B", "B-BAC", "2", "2010-11-24 16:50:39",
						"2011-08-10 13:20:25", "USERFULL", "0", null } };
		super.insertData("M_USER_ACCESS", MUserAccessData);
		
		String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "43", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", AUDIT_VALUE, "1" },
                { "62", "", "", "", "", "", "", "",
                    "", "", "",
                    "", "", "", "sysadmin", "0", AUDIT_VALUE, "2" } };
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);

		input.setCboCustomerName("21");
		input.setIdCust("21");
		input.setIdBillAccount("1100154             ");

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("USERFULL");
		input.setUvo(uvo);

		result = blogic.execute(input);
		output = (RP_B_BAC_S02_05_01Output) result.getResultObject();

		assertEquals("0", output.getIsDisplayTerCnBut());
		assertEquals("0", output.getBillRefDispCond());
		assertEquals("0", output.getBillRefDispCondSess());

		assertEquals("2", output.getAccessType());

		// check contactInfo
		HashMap<String, Object> contactInfo = output.getContactInfo();
		assertEquals("21", contactInfo.get("ID_CUST"));
		assertEquals("PC", contactInfo.get("CONTACT_TYPE"));
		assertEquals("Corp Test GBIL02", contactInfo.get("CONTACT_NAME"));
		assertEquals("Corp Test GBIL02", contactInfo.get("DESIGNATION"));
		assertEquals("test@test.com", contactInfo.get("EMAIL"));
		assertEquals("78787887", contactInfo.get("TEL_NO"));
		assertEquals(null, contactInfo.get("DID_NO"));
		assertEquals(null, contactInfo.get("FAX_NO"));
		assertEquals(null, contactInfo.get("MOBILE_NO"));
		assertEquals("USERFULL", contactInfo.get("ID_LOGIN"));
		assertEquals("0", contactInfo.get("IS_DELETED"));
		assertEquals(null, contactInfo.get("ID_AUDIT"));

		// check billAccountInfo
		HashMap<String, Object> billAccountInfo = output.getHeaderInfo();
		assertEquals("1", billAccountInfo.get("IS_SINGPOST"));
		assertEquals(null, billAccountInfo.get("ID_AUDIT"));
		assertEquals("CA", billAccountInfo.get("PAYMENT_METHOD"));
		assertEquals("0", billAccountInfo.get("TOTAL_AMT_DUE").toString());
		assertEquals("2011-01-18", billAccountInfo.get("DATE_UPDATED")
				.toString());
		assertEquals("1100154             ",
				billAccountInfo.get("ID_BILL_ACCOUNT"));
		assertEquals("PC", billAccountInfo.get("CONTACT_TYPE"));
		assertEquals("DM", billAccountInfo.get("ID_LOGIN"));
		assertEquals("0", billAccountInfo.get("IS_DELETED"));
		assertEquals("0", billAccountInfo.get("IS_DISPLAY_EXP_AMT"));
		assertEquals("BA", billAccountInfo.get("CUST_ADR_TYPE"));
		assertEquals("MYR", billAccountInfo.get("BILL_CURRENCY"));
		assertEquals(null, billAccountInfo.get("FIXED_FOREX"));
		assertEquals(null, billAccountInfo.get("EXPORT_CURRENCY"));
		assertEquals("2011-01-18", billAccountInfo.get("DATE_CREATED")
				.toString());
		assertEquals("21", billAccountInfo.get("ID_CUST"));

		// check listContact
		List<LabelValueBean> listContact = output.getListContact();
		assertEquals("Corp Test GBIL02", listContact.get(0).getLabel());
		assertEquals("PC", listContact.get(0).getValue());

		// check addressInfo
		HashMap<String, Object> addressInfo = output.getAddressInfo();
		assertEquals("BILLING ADDRESS 1", addressInfo.get("address1"));
		assertEquals("BILLING ADDRESS 2", addressInfo.get("address2"));
		assertEquals("BILLING ADDRESS 3", addressInfo.get("address3"));
		assertEquals("57100,  Malaysia    ", addressInfo.get("address4"));
		assertEquals("test@test.com", addressInfo.get("email"));

		// check newListPlanInfo
		List<HashMap<String, Object>> newListPlanInfo = output
				.getListPlanInfo();
		assertEquals(4, newListPlanInfo.size());

		// check custInfo
		HashMap<String, Object> custInfo = output.getCustInfo();
		assertEquals("21", custInfo.get("ID_CUST"));
		assertEquals("2010-11-23", custInfo.get("DATE_UPDATED").toString());
		assertEquals("CORP", custInfo.get("CUSTOMER_TYPE"));
		assertEquals("0", custInfo.get("IS_DELETED"));
		assertEquals("123456789", custInfo.get("CUST_ACC_NO"));
		assertEquals("0", custInfo.get("INTER_COMP"));
		assertEquals("USERFULL", custInfo.get("ID_LOGIN"));
		assertEquals("CorpTestBil", custInfo.get("CUST_NAME"));
		assertEquals("0", custInfo.get("IS_EXPORTED"));
		assertEquals("0", custInfo.get("PRINT_STATEMENT"));
		assertEquals("2010-11-23", custInfo.get("DATE_CREATED").toString());

		// check bss
		assertNotNull(output.getBss());

		// check the error message
		//assertEquals("No billing need to be generated", output.getLastMsg());

		//assertEquals("info.ERR2SC044", result.getMessages().get().next().getKey());
		assertEquals("success", result.getResultString());
	}

}
