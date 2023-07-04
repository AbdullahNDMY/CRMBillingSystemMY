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

import java.util.HashMap;
import java.util.List;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Output;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Input;

/**
 * Test Class for nttdm.bsys.m_cdm.blogic.RP_B_BAC_S01_02BLogic
 * 
 * @author xycao
 */
public class RP_B_BAC_S01_02BLogic_Test extends AbstractUtilTest {

	private RP_B_BAC_S01_02BLogic blogic;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new RP_B_BAC_S01_02BLogic();
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
	}
	
	/**
	 * listBillingAccount.size()==0
	 */
	public void testExecute01() {
		// insert data to
		String[][] testDataTBatchLog1 = {
				{ "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
						"APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
						"ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
						"BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
						"IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "43", "229743", "PS1", "NP", null, "IN", null, "2", "1",
						"268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
						"2011-08-02 14:13:46", "2011-08-02 14:13:46",
						"USERFULL"}, };
		super.insertData("T_CUST_PLAN_H", testDataTBatchLog1);

		String[][] testDataTBatchLog2 = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN" },
				{ "2197", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
						"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
						null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
						"USERFULL" }, 
				{ "2198", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
							"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
							null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
							"USERFULL" }, };
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);

		String[][] testDataTBatchLog3 = {
				{ "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "QUANTITY",
						"UNIT_PRICE", "TOTAL_AMOUNT", "JOB_NO", "ID_PLAN",
						"PLAN_NAME", "PLAN_DESC", "ID_PLAN_GRP",
						"ITEM_GRP_NAME", "SVC_LEVEL1", "SVC_LEVEL2",
						"RATE_TYPE", "RATE_MODE", "RATE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN",  "ITEM_TYPE" },
				{ "23", "2197", "12", "12", "144", "GBIL-001", "1191", "1",
						"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
						"5", "23", "1", "0", "2011-08-02 16:14:35",
						"2011-08-10 16:31:43", "USERFULL",  "S" },
				{ "24", "2198", "12", "12", "144", "GBIL-001", "1191", "1",
							"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
							"5", "23", "1", "0", "2011-08-02 16:14:35",
							"2011-08-10 16:31:43", "USERFULL",  "S" },
				{ "25", "2198", "12", "12", "144", "GBIL-001", "1191", "1",
								"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
								"5", "23", "1", "0", "2011-08-02 16:14:35",
								"2011-08-10 16:31:43", "USERFULL",  "S" } 
						};
		super.insertData("T_CUST_PLAN_LINK", testDataTBatchLog3);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX",  "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2",  "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog4 = {
				{ "ID_CUST_PLAN", "ID_BILL_ACCOUNT", "IS_FIRST_BILL", "ID_QCS",
						"ID_QUO", "CUST_PO", "AC_MANAGER", "TERM", "BILL_FROM",
						"BILL_TO", "IS_RECURRING", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_BIF", 
						"ID_CUST_PLAN_GRP" },
				{ "43", "2", "1", "", "", "", "", "", "2011-8-22", "2011-8-22",
						"2", "2011-8-22  16:38:04", "2011-8-22  16:38:04",
						"sysadmin", "",  "2197" }, 
				{ "43", "2", "1", "", "", "", "", "", "2011-8-22", "2011-8-22",
							"2", "2011-8-22  16:38:04", "2011-8-22  16:38:04",
							"sysadmin", "",  "2198" }, };
		super.insertData("T_BAC_D", testDataTBatchLog4);

		String[][] testDataTBatchLog6 = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-6-28  8:17:25", "2011-8-8  11:41:51",
						"USERFULL" }, };
		super.insertData("M_SVG", testDataTBatchLog6);

		String[][] testDataTBatchLog8 = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{ "311", "121", "TYP", "No GST", "", "2", "0", "0",
						"2011-2-25  14:53:37", "2011-2-25  14:53:37",
						"USERFULL" }, };
		super.insertData("M_SVC", testDataTBatchLog8);

		String[][] testDataTBatchLog7 = {
				{ "ID_PLAN", "PLAN_NAME", "PLAN_DESC", "CUSTOMER_TYPE",
						"BILL_CURRENCY", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "1191", "Plan for test case",
						"Use this plan for test case captures", "CONS", "MYR",
						"0", "2011-08-02 09:30:49", "2011-08-02 09:30:49",
						"USERFULL" }, };
		super.insertData("M_PLAN_H", testDataTBatchLog7);

		String[][] testDataTBatchLog9 = {
				{ "ID_PLAN_GRP", "ID_PLAN", "ITEM_TYPE", "SVC_LEVEL1", "RATE",
						"ITEM_GRP_NAME", "RATE_MODE", "RATE_TYPE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "SVC_LEVEL2" },
				{ "1525", "1191", "S", "268", "4", "Flower", "5", "BA", "0",
						"0", "2011-8-3  11:06:52", "2011-8-3  11:06:52",
						"USERFULL",  "311" }, };
		super.insertData("M_PLAN_D", testDataTBatchLog9);

		String[][] testDataTBatchLog10 = {
				{ "ID_PLAN_SVC", "ID_PLAN_GRP", "SVC_LEVEL3", "SVC_LEVEL4",
						"ID_SUPPLIER", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN"},
				{ "1588", "1525", "311", "311", "13", "0",
						"2011-8-3  11:06:52", "011-8-3  11:06:52", "USERFULL" }

		};
		super.insertData("M_PLAN_SVC", testDataTBatchLog10);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("BILL_DESC", "billinformation");
		param.put("ID_CUST_PLAN_GRP", "2197");
		super.updateDAO.execute("TEST.B_BAC_S01.UPDATE.SQL001", param);

		RP_B_BAC_S01_02Input input = new RP_B_BAC_S01_02Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);
		input.setCboCustomerName("229743");

		BLogicResult result = blogic.execute(input);
		RP_B_BAC_S01_02Output output = (RP_B_BAC_S01_02Output) result
				.getResultObject();
		List<HashMap<String, Object>> list = output.getListBillingAccount();
		assertEquals(0, list.size());
		assertEquals("1", output.getNewFlg());
		assertEquals("success", result.getResultString());
	}
	
	/**
	 * 
	 * Case : test normal situation ID_BILL_ACCOUNT = "2" <br>
	 * BILL_CURRENCY = "MYR" <br>
	 * ID_CUST_PLAN = "43" <br>
	 * PAYMENT_METHOD = "CQ" <br>
	 * SERVICES_STATUS = "PS3" <br>
	 * SVC_GRP_NAME = "TEST SERVICE" <br>
	 * SVC_DESC = "No GST" <br>
	 * BILL_DESC = "billinformation" <br>
	 * F.PLAN_DESC + F.ITEM_GRP_NAME ="sd sdf ssdff ds sdf  - 1" <br>
	 * result.getResultString() = "success" <br>
	 */
	public void testExecute02() {
		
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
		
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "43", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", AUDIT_VALUE, "9999" } };
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        
		// insert data to
		String[][] testDataTBatchLog1 = {
				{ "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
						"APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
						"ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
						"BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
						"IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "43", "229743", "PS3", "NP", null, "IN", null, "2", "1",
						"268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
						"2011-08-02 14:13:46", "2011-08-02 14:13:46",
						"USERFULL"}, };
		super.insertData("T_CUST_PLAN_H", testDataTBatchLog1);

		String[][] testDataTBatchLog2 = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN" },
				{ "2197", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
						"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
						null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
						"USERFULL" }, 
				{ "2198", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
							"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
							null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
							"USERFULL" }, };
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);

		String[][] testDataTBatchLog3 = {
				{ "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "QUANTITY",
						"UNIT_PRICE", "TOTAL_AMOUNT", "JOB_NO", "ID_PLAN",
						"PLAN_NAME", "PLAN_DESC", "ID_PLAN_GRP",
						"ITEM_GRP_NAME", "SVC_LEVEL1", "SVC_LEVEL2",
						"RATE_TYPE", "RATE_MODE", "RATE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN",  "ITEM_TYPE" },
				{ "23", "2197", "12", "12", "144", "GBIL-001", "1191", "1",
						"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
						"5", "23", "1", "0", "2011-08-02 16:14:35",
						"2011-08-10 16:31:43", "USERFULL",  "S" },
				{ "24", "2198", "12", "12", "144", "GBIL-001", "1191", "1",
							"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
							"5", "23", "1", "0", "2011-08-02 16:14:35",
							"2011-08-10 16:31:43", "USERFULL",  "S" },
				{ "25", "2198", "12", "12", "144", "GBIL-001", "1191", "1",
								"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
								"5", "23", "1", "0", "2011-08-02 16:14:35",
								"2011-08-10 16:31:43", "USERFULL",  "S" } 
						};
		super.insertData("T_CUST_PLAN_LINK", testDataTBatchLog3);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX",  "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2",  "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog4 = {
				{ "ID_CUST_PLAN", "ID_BILL_ACCOUNT", "IS_FIRST_BILL", "ID_QCS",
						"ID_QUO", "CUST_PO", "AC_MANAGER", "TERM", "BILL_FROM",
						"BILL_TO", "IS_RECURRING", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_BIF", 
						"ID_CUST_PLAN_GRP" },
				{ "43", "2", "1", "", "", "", "", "", "2011-8-22", "2011-8-22",
						"2", "2011-8-22  16:38:04", "2011-8-22  16:38:04",
						"sysadmin", "",  "2197" }, 
				{ "43", "2", "1", "", "", "", "", "", "2011-8-22", "2011-8-22",
							"2", "2011-8-22  16:38:04", "2011-8-22  16:38:04",
							"sysadmin", "",  "2198" }, };
		super.insertData("T_BAC_D", testDataTBatchLog4);

		String[][] testDataTBatchLog6 = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-6-28  8:17:25", "2011-8-8  11:41:51",
						"USERFULL" }, };
		super.insertData("M_SVG", testDataTBatchLog6);

		String[][] testDataTBatchLog8 = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{ "311", "121", "TYP", "No GST", "", "2", "0", "0",
						"2011-2-25  14:53:37", "2011-2-25  14:53:37",
						"USERFULL" }, };
		super.insertData("M_SVC", testDataTBatchLog8);

		String[][] testDataTBatchLog7 = {
				{ "ID_PLAN", "PLAN_NAME", "PLAN_DESC", "CUSTOMER_TYPE",
						"BILL_CURRENCY", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "1191", "Plan for test case",
						"Use this plan for test case captures", "CONS", "MYR",
						"0", "2011-08-02 09:30:49", "2011-08-02 09:30:49",
						"USERFULL" }, };
		super.insertData("M_PLAN_H", testDataTBatchLog7);

		String[][] testDataTBatchLog9 = {
				{ "ID_PLAN_GRP", "ID_PLAN", "ITEM_TYPE", "SVC_LEVEL1", "RATE",
						"ITEM_GRP_NAME", "RATE_MODE", "RATE_TYPE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "SVC_LEVEL2" },
				{ "1525", "1191", "S", "268", "4", "Flower", "5", "BA", "0",
						"0", "2011-8-3  11:06:52", "2011-8-3  11:06:52",
						"USERFULL",  "311" }, };
		super.insertData("M_PLAN_D", testDataTBatchLog9);

		String[][] testDataTBatchLog10 = {
				{ "ID_PLAN_SVC", "ID_PLAN_GRP", "SVC_LEVEL3", "SVC_LEVEL4",
						"ID_SUPPLIER", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN"},
				{ "1588", "1525", "311", "311", "13", "0",
						"2011-8-3  11:06:52", "011-8-3  11:06:52", "USERFULL" }

		};
		super.insertData("M_PLAN_SVC", testDataTBatchLog10);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("BILL_DESC", "billinformation");
		param.put("ID_CUST_PLAN_GRP", "2197");
		super.updateDAO.execute("TEST.B_BAC_S01.UPDATE.SQL001", param);

		RP_B_BAC_S01_02Input input = new RP_B_BAC_S01_02Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);
		input.setCboCustomerName("229743");

		BLogicResult result = blogic.execute(input);
		RP_B_BAC_S01_02Output output = (RP_B_BAC_S01_02Output) result
				.getResultObject();
		List<HashMap<String, Object>> list = output.getListBillingAccount();


		// ctmid check the result
		assertEquals("2                   ", list.get(0).get("ID_BILL_ACCOUNT"));
		assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
		assertEquals("43", list.get(0).get("ID_CUST_PLAN").toString());
		assertEquals("CQ", list.get(0).get("PAYMENT_METHOD"));
		assertEquals("PS3", list.get(0).get("SERVICES_STATUS"));
		assertEquals("billinformation", list.get(0).get("BILL_DESC_DISPLAY"));
		assertEquals("TEST SERVICE", list.get(1).get("SVC_GRP_NAME"));
		assertEquals("sd sdf ssdff ds sdf -1",
				list.get(1).get("PLAN_DESC_DISPLAY").toString().trim());
		assertEquals("No GST", list.get(1).get("SVC_DESC"));
		assertEquals("success", result.getResultString());
	}
	
	public void testExecute03() {
		
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
		
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "43", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", "0", "9999" },
                { "44", "", "", "", "", "", "", "",
                            "", "", "",
                            "", "", "", "sysadmin", "0", AUDIT_VALUE, "9998" }};
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        
		// insert data to
		String[][] testDataTBatchLog1 = {
				{ "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
						"APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
						"ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
						"BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
						"IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "43", "229743", "PS3", "NP", null, "IN", null, "2", "1",
						"268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
						"2011-08-02 14:13:46", "2011-08-02 14:13:46",
						"USERFULL"}, 
				{ "44", "229743", "PS3", "NP", null, "IN", null, "2", "1",
							"268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
							"2011-08-02 14:13:46", "2011-08-02 14:13:46",
							"USERFULL"}, };
		super.insertData("T_CUST_PLAN_H", testDataTBatchLog1);

		String[][] testDataTBatchLog2 = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN" },
				{ "2197", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
						"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
						null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
						"USERFULL" }, 
				{ "2198", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
							"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
							null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
							"USERFULL" },
				{ "2199", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
								"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
								null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
								"USERFULL" },};
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);

		String[][] testDataTBatchLog3 = {
				{ "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "QUANTITY",
						"UNIT_PRICE", "TOTAL_AMOUNT", "JOB_NO", "ID_PLAN",
						"PLAN_NAME", "PLAN_DESC", "ID_PLAN_GRP",
						"ITEM_GRP_NAME", "SVC_LEVEL1", "SVC_LEVEL2",
						"RATE_TYPE", "RATE_MODE", "RATE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN",  "ITEM_TYPE" },
				{ "23", "2197", "12", "12", "144", "GBIL-001", "1191", "1",
						"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
						"5", "23", "1", "0", "2011-08-02 16:14:35",
						"2011-08-10 16:31:43", "USERFULL",  "S" },
				{ "24", "2198", "12", "12", "144", "GBIL-001", "1191", "1",
							"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
							"5", "23", "1", "0", "2011-08-02 16:14:35",
							"2011-08-10 16:31:43", "USERFULL",  "S" },
				{ "25", "2199", "12", "12", "144", "GBIL-001", "1191", "1",
								"sd sdf ssdff ds sdf ", "1", "1", "268", "311", "EX",
								"5", "23", "1", "0", "2011-08-02 16:14:35",
								"2011-08-10 16:31:43", "USERFULL",  "S" } 
						};
		super.insertData("T_CUST_PLAN_LINK", testDataTBatchLog3);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX",  "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2",  "1", "0" },
				{ "3", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
							"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
							"2",  "1", "0" },};
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog4 = {
				{ "ID_CUST_PLAN", "ID_BILL_ACCOUNT", "IS_FIRST_BILL", "ID_QCS",
						"ID_QUO", "CUST_PO", "AC_MANAGER", "TERM", "BILL_FROM",
						"BILL_TO", "IS_RECURRING", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_BIF", 
						"ID_CUST_PLAN_GRP" },
				{ "43", "2", "1", "", "", "", "", "", "2011-8-22", "2011-8-22",
						"2", "2011-8-22  16:38:04", "2011-8-22  16:38:04",
						"sysadmin", "",  "2197" }, 
				{ "43", "2", "1", "", "", "", "", "", "2011-8-22", "2011-8-22",
							"2", "2011-8-22  16:38:04", "2011-8-22  16:38:04",
							"sysadmin", "",  "2198" },
				{ "44", "3", "1", "", "", "", "", "", "2011-8-22", "2011-8-22",
								"2", "2011-8-22  16:38:04", "2011-8-22  16:38:04",
								"sysadmin", "",  "2199" },};
		super.insertData("T_BAC_D", testDataTBatchLog4);

		String[][] testDataTBatchLog6 = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-6-28  8:17:25", "2011-8-8  11:41:51",
						"USERFULL" }, };
		super.insertData("M_SVG", testDataTBatchLog6);

		String[][] testDataTBatchLog8 = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{ "311", "121", "TYP", "No GST", "", "2", "0", "0",
						"2011-2-25  14:53:37", "2011-2-25  14:53:37",
						"USERFULL" }, };
		super.insertData("M_SVC", testDataTBatchLog8);

		String[][] testDataTBatchLog7 = {
				{ "ID_PLAN", "PLAN_NAME", "PLAN_DESC", "CUSTOMER_TYPE",
						"BILL_CURRENCY", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN" },
				{ "1191", "Plan for test case",
						"Use this plan for test case captures", "CONS", "MYR",
						"0", "2011-08-02 09:30:49", "2011-08-02 09:30:49",
						"USERFULL" }, };
		super.insertData("M_PLAN_H", testDataTBatchLog7);

		String[][] testDataTBatchLog9 = {
				{ "ID_PLAN_GRP", "ID_PLAN", "ITEM_TYPE", "SVC_LEVEL1", "RATE",
						"ITEM_GRP_NAME", "RATE_MODE", "RATE_TYPE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "SVC_LEVEL2" },
				{ "1525", "1191", "S", "268", "4", "Flower", "5", "BA", "0",
						"0", "2011-8-3  11:06:52", "2011-8-3  11:06:52",
						"USERFULL",  "311" }, };
		super.insertData("M_PLAN_D", testDataTBatchLog9);

		String[][] testDataTBatchLog10 = {
				{ "ID_PLAN_SVC", "ID_PLAN_GRP", "SVC_LEVEL3", "SVC_LEVEL4",
						"ID_SUPPLIER", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN"},
				{ "1588", "1525", "311", "311", "13", "0",
						"2011-8-3  11:06:52", "011-8-3  11:06:52", "USERFULL" }

		};
		super.insertData("M_PLAN_SVC", testDataTBatchLog10);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("BILL_DESC", "billinformation");
		param.put("ID_CUST_PLAN_GRP", "2197");
		super.updateDAO.execute("TEST.B_BAC_S01.UPDATE.SQL001", param);

		RP_B_BAC_S01_02Input input = new RP_B_BAC_S01_02Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);
		input.setCboCustomerName("229743");

		BLogicResult result = blogic.execute(input);
		RP_B_BAC_S01_02Output output = (RP_B_BAC_S01_02Output) result
				.getResultObject();
		List<HashMap<String, Object>> list = output.getListBillingAccount();

		// ctmid check the result
		assertEquals("2                   ", list.get(0).get("ID_BILL_ACCOUNT"));
		assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
		assertEquals("43", list.get(0).get("ID_CUST_PLAN").toString());
		assertEquals("CQ", list.get(0).get("PAYMENT_METHOD"));
		assertEquals("PS3", list.get(0).get("SERVICES_STATUS"));
		assertEquals("billinformation", list.get(0).get("BILL_DESC_DISPLAY"));
		assertEquals("TEST SERVICE", list.get(1).get("SVC_GRP_NAME"));
		assertEquals("sd sdf ssdff ds sdf -1",
				list.get(1).get("PLAN_DESC_DISPLAY").toString().trim());
		assertEquals("No GST", list.get(1).get("SVC_DESC"));
		assertEquals("success", result.getResultString());
	}
}
