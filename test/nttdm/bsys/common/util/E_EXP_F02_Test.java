package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;

import nttdm.bsys.common.util.dto.E_EXP_F02Output;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class E_EXP_F02_Test extends AbstractUtilTest {

	private E_EXP_F02 e_exp_f02;

	// 【正常系】General Settings for OfficeSystem Detail
	private String[][] m_gset_dS1 = {
			{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
					"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
			{
					"CB_AUTO_OFFSET",
					"1",
					"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
					"0", "sysadmin", "BAC", "50929", "0" },
			{
					"BATCH_G_RPT_AR01",
					"1",
					"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
					"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\E_EXP_F02\\",
					"50929", "0" },
			{
					"BATCH_TIME_INTERVAL",
					"1",
					"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
					"0", "sysadmin", "2", "50929", "0" } };
	// 【正常系】General Settings for OfficeSystem Header
	private String[][] m_gset_hS1 = {
			{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

			{
					"CB_AUTO_OFFSET",
					"Cash Book auto offset by payment method or customer",
					"Settings for Cash Book to auto offset by payment method or customer in batch.",
					"0", "sysadmin" },
			{
					"BATCH_G_RPT_AR01",
					"Cash Book auto offset by payment method or customer",
					"Settings for Cash Book to auto offset by payment method or customer in batch.",
					"0", "sysadmin" },
			{
					"SYSTEMBASE",
					"Cash Book auto offset by payment method or customer",
					"Settings for Cash Book to auto offset by payment method or customer in batch.",
					"0", "sysadmin" },
			{
					"BATCH_TIME_INTERVAL",
					"Cash Book auto offset by payment method or customer",
					"Settings for Cash Book to auto offset by payment method or customer in batch.",
					"0", "sysadmin" } };

	@Override
	protected void setUpData() throws Exception {
		// Logger.getRootLogger().setLevel(Level.DEBUG);
		e_exp_f02 = new E_EXP_F02();
		e_exp_f02.setQueryDAO(queryDAO);
		e_exp_f02.setUpdateDAO(updateDAO);
	}

	/**
	 * batch module path is not exist
	 * 
	 */
	public void testCase01() throws Exception {
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"CB_AUTO_OFFSET",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "BAC", "50929", "0" },
				{
						"BATCH_TIME_INTERVAL",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "2", "50929", "0" } };
		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("E");
		e_exp_f02.setCurStmtMonth("0");
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		// this.deleteAllData("t_set_Batch");
		this.insertData("m_gset_H", m_gset_hS1);
		this.insertData("m_gset_d", m_gset_dS1);
		e_exp_f02.excute(output);

		Map<String, Object> result = queryDAO.executeForMap(
				"select.e_exp_f02.getbatch", "");
		String status = result.get("STATUS").toString();
		assertEquals("3", status);
	}

	/**
	 * batch module CB_AUTO_OFFSET = BAC
	 * 
	 */
	public void testCase02() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "2267", null,
						"0", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("E");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("2500", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals("0123456789", stmt_h.get("TEL_NO").toString());
		assertEquals("0123456789", stmt_h.get("FAX_NO").toString());
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("1", stmt_d.get("ID_REF").toString().trim());
        // Avoid DB LOCAL Difference
        String[] date = format1.format(c1.getTime()).split("-");
        assertTrue(stmt_d.get("ENTRY_TYPE").toString().startsWith(date[0]));
        assertTrue(stmt_d.get("ENTRY_TYPE").toString().endsWith(date[2]));
		assertEquals("2500", stmt_d.get("AMOUNT").toString());
		assertEquals(" ", stmt_d.get("CUST_PO").toString());
		assertEquals(null, stmt_d.get("PAYMENT_INFO"));
		assertEquals(format.format(new Date()), stmt_d.get("DOC_DATE")
				.toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("3499", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("IN", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * 
	 * batch module CB_AUTO_OFFSET != BAC
	 * 
	 */
	public void testCase03() throws Exception {
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"CB_AUTO_OFFSET",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "BIL", "50929", "0" },
				{
						"BATCH_G_RPT_AR01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin",
						"C:\\OfficeSystem\\BATCH\\E_EXP_F02\\", "50929", "0" },
				{
						"BATCH_TIME_INTERVAL",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "2", "50929", "0" } };
		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "CN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);
		updateDAO.execute("TEST.E_EXP_F02.UPDATE.SQL100", null);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "2267", null,
						"0", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567890", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567891", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567892", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567893", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567894", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567895", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567896", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567897", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567898", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "1234567890123456789", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("E");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("-2500", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals("0123456789", stmt_h.get("TEL_NO").toString());
		assertEquals("0123456789", stmt_h.get("FAX_NO").toString());
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		assertEquals("1", stmt_d.get("ID_REF").toString().trim());
		assertEquals(" ", stmt_d.get("ENTRY_TYPE").toString());
		assertEquals("-2500", stmt_d.get("AMOUNT").toString());
		assertEquals("aaaaa", stmt_d.get("CUST_PO").toString());
		assertEquals(null, stmt_d.get("PAYMENT_INFO"));
		assertEquals(format.format(new Date()), stmt_d.get("DOC_DATE")
				.toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("-100", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("CN", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * called by B_BAC process C
	 */
	public void testCase04() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", "2015-07-02", "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "1",
						"0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						format.format(new Date()), "4", "MYR", "REMARK", "N",
						"Cheque No.", "Bank-In Slip No.", "0", "0",
						"2011-07-07", "2011-07-07", "BIF001", "2331", "3211",
						"2267", null, "0", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("B");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_cust("229743");
		e_exp_f02.setIdBillAccount("2");
		e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("2500", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals("0123456789", stmt_h.get("TEL_NO").toString());
		assertEquals("0123456789", stmt_h.get("FAX_NO").toString());
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("1", stmt_d.get("ID_REF").toString().trim());
		// assertEquals(format1.format(c1.getTime()), format1.format(format1
		// .parse(stmt_d.get("ENTRY_TYPE").toString())));
		assertEquals("2500", stmt_d.get("AMOUNT").toString());
		assertEquals(" ", stmt_d.get("CUST_PO").toString());
		assertEquals(null, stmt_d.get("PAYMENT_INFO"));
		assertEquals("2015-07-02", stmt_d.get("DOC_DATE").toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("3500", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("IN", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * called by B_BAC process C file path =null
	 */
	public void testCase05() throws Exception {
		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("B");
		e_exp_f02.setCurStmtMonth("0");
		this.deleteAllData("m_gset_d");

		e_exp_f02.excute(output);

		String status = output.getBatchStatus();
		assertEquals("3", status);

		String msg[] = output.getMsg();
		GlobalMessageResources message_info = GlobalMessageResources
				.getInstance();
		String message = message_info.getMessage("errors.ERR1SC070");
		assertEquals(message, msg[0]);
	}

	/**
	 * called by R_SOA process D
	 */
	public void testCase06() throws Exception {
		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("R");
		e_exp_f02.setCurStmtMonth("0");
		this.deleteAllData("m_gset_d");

		e_exp_f02.excute(output);

		String status = output.getBatchStatus();
		assertEquals("3", status);

		String msg[] = output.getMsg();
		GlobalMessageResources message_info = GlobalMessageResources
				.getInstance();
		String message = message_info.getMessage("errors.ERR1SC070");
		assertEquals(message, msg[0]);
	}

	/**
	 * called by B_BAC process C loop result 2
	 */
	public void testCase07() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						format.format(new Date()), "4", "MYR", "REMARK", "N",
						"Cheque No.", "Bank-In Slip No.", "0", "0",
						"2011-07-07", "2011-07-07", "BIF001", "2331", "3211",
						"0", null, "2", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("B");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_cust("229743");
		e_exp_f02.setIdBillAccount("2");
		e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("0", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals("0123456789", stmt_h.get("TEL_NO").toString());
		assertEquals("0123456789", stmt_h.get("FAX_NO").toString());
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("111238", stmt_d.get("ID_REF").toString().trim());
		assertEquals(" ", stmt_d.get("ENTRY_TYPE").toString());
		assertEquals("0", stmt_d.get("AMOUNT").toString());
		assertEquals(null, stmt_d.get("CUST_PO"));
		assertEquals("Bank-In Slip No.;1", stmt_d.get("PAYMENT_INFO"));
		assertEquals(format.format(new Date()), stmt_d.get("DOC_DATE")
				.toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("-1", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("CB", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * called by B_BAC process C loop result 2
	 */
	public void testCase08() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						format.format(new Date()), "4", "MYR", "REMARK", "N",
						"Cheque No.", "Bank-In Slip No.", "0", "0",
						"2011-07-07", "2011-07-07", "BIF001", "2331", "3211",
						"500", null, "2", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("B");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_cust("229743");
		e_exp_f02.setIdBillAccount("2");
		e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("-500", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals("0123456789", stmt_h.get("TEL_NO").toString());
		assertEquals("0123456789", stmt_h.get("FAX_NO").toString());
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("111238", stmt_d.get("ID_REF").toString().trim());
		assertEquals(" ", stmt_d.get("ENTRY_TYPE").toString());
		assertEquals("-500", stmt_d.get("AMOUNT").toString());
		assertEquals(null, stmt_d.get("CUST_PO"));
		assertEquals("Bank-In Slip No.;1", stmt_d.get("PAYMENT_INFO"));
		assertEquals(format.format(new Date()), stmt_d.get("DOC_DATE")
				.toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("-1", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("CB", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * called by R_SOA process D loop result 3
	 * 
	 * @throws ParseException
	 */
	public void testCase09() throws ParseException {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("R");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_stmts(new String[] { "SAT-12-07-09466" });
		e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("0", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals("0123456789", stmt_h.get("TEL_NO").toString());
		assertEquals("0123456789", stmt_h.get("FAX_NO").toString());
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("111238", stmt_d.get("ID_REF").toString().trim());
		assertEquals(" ", stmt_d.get("ENTRY_TYPE").toString());
		assertEquals("0", stmt_d.get("AMOUNT").toString());
		assertEquals(null, stmt_d.get("CUST_PO"));
		assertEquals("Bank-In Slip No.;1", stmt_d.get("PAYMENT_INFO"));
		assertEquals("2012-04-12", stmt_d.get("DOC_DATE").toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("-1", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("CB", stmt_d.get("DOC_TYPE").toString());
	}

	/**
     *
	 */
	public void testCase10() {
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"CB_AUTO_OFFSET",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "BIL", "50929", "0" },
				{
						"BATCH_G_RPT_AR01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin",
						"C:\\OfficeSystem\\BATCH\\E_EXP_F02\\", "50929", "0" },
				{
						"BATCH_TIME_INTERVAL",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "2", "50929", "0" } };
		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("R");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_stmts(new String[] { "SAT-12-07-09466" });
		e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("0", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals("0123456789", stmt_h.get("TEL_NO").toString());
		assertEquals("0123456789", stmt_h.get("FAX_NO").toString());
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("111238", stmt_d.get("ID_REF").toString().trim());
		assertEquals(" ", stmt_d.get("ENTRY_TYPE").toString());
		assertEquals("0", stmt_d.get("AMOUNT").toString());
		assertEquals(null, stmt_d.get("CUST_PO"));
		assertEquals("Bank-In Slip No.;1", stmt_d.get("PAYMENT_INFO"));
		assertEquals("2012-04-12", stmt_d.get("DOC_DATE").toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("-1", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("CB", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * batch module CB_AUTO_OFFSET = BAC custType = cust
	 */
	public void testCase11() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CONS",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "2267", null,
						"0", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("E");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("2500", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals(null, stmt_h.get("TEL_NO"));
		assertEquals(null, stmt_h.get("FAX_NO"));
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("1", stmt_d.get("ID_REF").toString().trim());
        // Avoid DB LOCAL Difference
        String[] date = format1.format(c1.getTime()).split("-");
        assertTrue(stmt_d.get("ENTRY_TYPE").toString().startsWith(date[0]));
        assertTrue(stmt_d.get("ENTRY_TYPE").toString().endsWith(date[2]));
        assertEquals("2500", stmt_d.get("AMOUNT").toString());
		assertEquals(" ", stmt_d.get("CUST_PO").toString());
		assertEquals(null, stmt_d.get("PAYMENT_INFO"));
		assertEquals(format.format(new Date()), stmt_d.get("DOC_DATE")
				.toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("3499", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("IN", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * batch module CB_AUTO_OFFSET = BAC custType = cust
	 */
	public void testCase12() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CONS",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// // 【正常系】
		// String[][] t_ar_stmt_hS1 = {
		// { "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
		// "CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
		// "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
		// "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
		// "CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },
		//
		// { "SAT-12-07-09466", "229743", format.format(new Date()),
		// "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
		// "cccc", "ddd", "200281", "china", "13313231313",
		// "22222513", "Jim abc", "aaa@qq.com", "MR" } };
		// super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "2267", null,
						"0", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("E");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.insert.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02..insert.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("MYR", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2                   ", stmt_h.get("CUST_ACC_NO")
				.toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("2500", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("Duy Duong", stmt_h.get("CUST_NAME").toString());
		assertEquals("BILLING ADDRESS 1", stmt_h.get("ADDRESS1").toString());
		assertEquals("BILLING ADDRESS 2", stmt_h.get("ADDRESS2").toString());
		assertEquals("BILLING ADDRESS 3", stmt_h.get("ADDRESS3").toString());
		assertEquals("57100 Malaysia", stmt_h.get("ADDRESS4"));
		assertEquals("57100", stmt_h.get("ZIP_CODE").toString());
		assertEquals("Malaysia", stmt_h.get("COUNTRY").toString());
		assertEquals(null, stmt_h.get("TEL_NO"));
		assertEquals(null, stmt_h.get("FAX_NO"));
		assertEquals("Corp Test GBIL02", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("test@test.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("Mr", stmt_h.get("SALUTATION").toString());

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy",Locale.US);
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DATE, -1);

		assertEquals("1", stmt_d.get("ID_REF").toString().trim());
		// Avoid DB LOCAL Difference
        String[] date = format1.format(c1.getTime()).split("-");
		assertTrue(stmt_d.get("ENTRY_TYPE").toString().startsWith(date[0]));
        assertTrue(stmt_d.get("ENTRY_TYPE").toString().endsWith(date[2]));
		assertEquals("2500", stmt_d.get("AMOUNT").toString());
		assertEquals(" ", stmt_d.get("CUST_PO").toString());
		assertEquals(null, stmt_d.get("PAYMENT_INFO"));
		assertEquals(format.format(new Date()), stmt_d.get("DOC_DATE")
				.toString());
		assertEquals("sysadmin", stmt_d.get("ID_LOGIN").toString());
		assertEquals("3499", stmt_d.get("ITEM_ACTIVITY").toString());
		assertEquals("IN", stmt_d.get("DOC_TYPE").toString());
	}

	/**
	 * called by B_BAC process C no result
	 */
	public void testCase13() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09467", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "3", "2011-07-02", "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "1",
						"0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						format.format(new Date()), "4", "MYR", "REMARK", "N",
						"Cheque No.", "Bank-In Slip No.", "0", "0",
						"2011-07-07", "2011-07-07", "BIF001", "2331", "3211",
						"2267", null, "0", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "a                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "2", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "2", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("B");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_cust("229743");
		e_exp_f02.setIdBillAccount("2");
		e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		assertEquals("No Statement Generated.", output.getMsg()[0]);
		assertEquals("3", output.getBatchStatus());
		assertEquals(0, output.getListStatementNo().size());
	}

	/**
	 * called by R_SOA process D loop result 3 no statement
	 * 
	 * @throws ParseException
	 */
	public void testCase14() throws ParseException {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229741", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "2                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("R");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_stmts(new String[] { "SAT-12-07-09466" });
		e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		assertEquals("3", output.getBatchStatus());
		assertEquals(0, output.getListStatementNo().size());
		assertEquals("No Statement Generated.", output.getMsg()[0]);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void testCase15() throws Exception {
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"CB_AUTO_OFFSET",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "BAC", "50929", "0" },
				{
						"BATCH_G_RPT_AR01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin",
						"C:\\OfficeSystem\\BATCH\\E_EXP_F02\\", "50929", "0" },
				{
						"BATCH_TIME_INTERVAL",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "2", "50929", "0" } };
		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		// super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "CN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "1", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);
		updateDAO.execute("TEST.E_EXP_F02.UPDATE.SQL100", null);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "2267", null,
						"0", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567890", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567891", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567892", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567893", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567894", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567895", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567896", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567897", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "12345678901234567898", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null },
				{ "1234567890123456789", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("E");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals(null, stmt_h);
		assertEquals(null, stmt_d);

	}

	/**
	 * called by R_SOA process D loop result 3
	 * 
	 * @throws ParseException
	 */
	public void testCase16() throws ParseException {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("R");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_stmts(new String[] { "SAT-12-07-09466" });
		// e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("SGD", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("0", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("test", stmt_h.get("CUST_NAME").toString());
		assertEquals("aaaaa", stmt_h.get("ADDRESS1").toString());
		assertEquals("bbb", stmt_h.get("ADDRESS2").toString());
		assertEquals("cccc", stmt_h.get("ADDRESS3").toString());
		assertEquals("ddd", stmt_h.get("ADDRESS4"));
		assertEquals("200281", stmt_h.get("ZIP_CODE").toString());
		assertEquals("china", stmt_h.get("COUNTRY").toString());
		assertEquals("13313231313", stmt_h.get("TEL_NO").toString());
		assertEquals("22222513", stmt_h.get("FAX_NO").toString());
		assertEquals("Jim abc", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("aaa@qq.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("MR", stmt_h.get("SALUTATION").toString());

		assertEquals(null, stmt_d);

	}

	/**
	 * called by R_SOA process D loop result 3
	 * 
	 * @throws ParseException
	 */
	public void testCase17() throws ParseException {
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"CB_AUTO_OFFSET",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "BIL", "50929", "0" },
				{
						"BATCH_G_RPT_AR01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin",
						"C:\\OfficeSystem\\BATCH\\E_EXP_F02\\", "50929", "0" },
				{
						"BATCH_TIME_INTERVAL",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "2", "50929", "0" } };
		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("R");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_stmts(new String[] { "SAT-12-07-09466" });
		// e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("SGD", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("0", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("test", stmt_h.get("CUST_NAME").toString());
		assertEquals("aaaaa", stmt_h.get("ADDRESS1").toString());
		assertEquals("bbb", stmt_h.get("ADDRESS2").toString());
		assertEquals("cccc", stmt_h.get("ADDRESS3").toString());
		assertEquals("ddd", stmt_h.get("ADDRESS4"));
		assertEquals("200281", stmt_h.get("ZIP_CODE").toString());
		assertEquals("china", stmt_h.get("COUNTRY").toString());
		assertEquals("13313231313", stmt_h.get("TEL_NO").toString());
		assertEquals("22222513", stmt_h.get("FAX_NO").toString());
		assertEquals("Jim abc", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("aaa@qq.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("MR", stmt_h.get("SALUTATION").toString());

		assertEquals(null, stmt_d);
	}

	/**
	 * called by B_BAC process C loop result 2
	 */
	public void testCase18() throws Exception {

		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						format.format(new Date()), "4", "MYR", "REMARK", "N",
						"Cheque No.", "Bank-In Slip No.", "0", "0",
						"2011-07-07", "2011-07-07", "BIF001", "2331", "3211",
						"0", null, "2", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("B");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_cust("229743");
		e_exp_f02.setIdBillAccount("2");
		// e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("SGD", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("0", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("test", stmt_h.get("CUST_NAME").toString());
		assertEquals("aaaaa", stmt_h.get("ADDRESS1").toString());
		assertEquals("bbb", stmt_h.get("ADDRESS2").toString());
		assertEquals("cccc", stmt_h.get("ADDRESS3").toString());
		assertEquals("ddd", stmt_h.get("ADDRESS4"));
		assertEquals("200281", stmt_h.get("ZIP_CODE").toString());
		assertEquals("china", stmt_h.get("COUNTRY").toString());
		assertEquals("13313231313", stmt_h.get("TEL_NO").toString());
		assertEquals("22222513", stmt_h.get("FAX_NO").toString());
		assertEquals("Jim abc", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("aaa@qq.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("MR", stmt_h.get("SALUTATION").toString());

		assertEquals(null, stmt_d);
	}

	/**
	 * called by B_BAC process C loop result 2
	 */
	public void testCase19() throws Exception {
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"CB_AUTO_OFFSET",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "BIL", "50929", "0" },
				{
						"BATCH_G_RPT_AR01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin",
						"C:\\OfficeSystem\\BATCH\\E_EXP_F02\\", "50929", "0" },
				{
						"BATCH_TIME_INTERVAL",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "2", "50929", "0" } };
		// delete data
		this.deleteAllData("m_gset_d");
		this.deleteAllData("m_gset_h");
		super.deleteAllData("T_AR_STMT_D");
		super.deleteAllData("T_AR_STMT_H");
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_TRM_D");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");

		super.insertData("m_gset_h", m_gset_hS1);
		super.insertData("m_gset_d", m_gset_dS1);

		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "0" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);

		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "sysadmin", "Mr", "", null, "",
						"", "0", "0123456789", "0123456789", "CORP",
						"0123456789", "", "" }, };

		super.insertData("M_CUST", testDataTBatchLog);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 【正常系】
		String[][] t_ar_stmt_hS1 = {
				{ "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
						"CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

				{ "SAT-12-07-09466", "229743", format.format(new Date()),
						"SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
						"cccc", "ddd", "200281", "china", "13313231313",
						"22222513", "Jim abc", "aaa@qq.com", "MR" } };
		super.insertData("T_AR_STMT_H", t_ar_stmt_hS1);

		String[][] m_gset_d = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },

				{
						"SYSTEMBASE",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "2011-07-11", "2012-08-01 15:41:17", "sysadmin",
						"SG", "50929", "0" } };
		super.insertData("M_GSET_D", m_gset_d);

		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						format.format(new Date()), "4", "MYR", "REMARK", "N",
						"Cheque No.", "Bank-In Slip No.", "0", "0",
						"2011-07-07", "2011-07-07", "BIF001", "2331", "3211",
						"0", null, "2", "0", "0", "2011-08-30", "1111", "0" } };
		String[][] dataT_CSB_D1 = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "1", "1",
						"21", null } };

		this.insertData("T_CSB_H", dataT_CSB_H);
		this.insertData("T_CSB_D", dataT_CSB_D1);

		// 【正常系】
		String[][] t_trm_dS1 = {
				{ "MATCH_ID", "CREDIT_REF", "DEBIT_REF", "AMT_PAID", "AMT_DUE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ID_BILL_ACCOUNT" },

				{ "1", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" },
				{ "2", "1", "PL-212-I0003-I0139  ", "50.00", "2000.00", "0",
						"2011-07-11", "2011-07-11", "sysadmin", "0", "2" } };
		this.insertData("T_TRM_D", t_trm_dS1);

		String[][] mCustAdrData = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "BILLING ADDRESS 1", "BILLING ADDRESS 2",
						"BILLING ADDRESS 3", "MY", "2010-11-19 11:55:05",
						"2010-11-19 13:11:26", "USERFULL", "57100", "0", null } };
		super.insertData("M_CUST_ADR", mCustAdrData);

		// insert data to listContact is not null
		String[][] mCustCtcData = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "229743", "PC", "Corp Test GBIL02", "Corp Test GBIL02",
						"test@test.com", "78787887", null, null, null,
						"2010-12-02 13:51:14", "2010-12-02 13:51:14",
						"USERFULL", "0", null } };
		super.insertData("M_CUST_CTC", mCustCtcData);

		E_EXP_F02Output output = new E_EXP_F02Output();

		e_exp_f02.setEset_rundate(new Date());
		e_exp_f02.setBatch("B");
		e_exp_f02.setCurStmtMonth("1");
		e_exp_f02.setId_cust("229743");
		e_exp_f02.setIdBillAccount("2");
		// e_exp_f02.setId_login("sysadmin");
		e_exp_f02.excute(output);
		String id_stmt = "SAT-12-07-09466";
		Map<String, Object> stmt_h = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_H", id_stmt);
		Map<String, Object> stmt_d = queryDAO.executeForMap(
				"select.e_exp_f02.getT_ARM_STMT_D", id_stmt);
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("229743", stmt_h.get("ID_CUST").toString());
		assertEquals(format.format(new Date()), stmt_h.get("STMT_DATE")
				.toString());
		assertEquals("SGD", stmt_h.get("STMT_CURRENCY").toString());
		assertEquals("2", stmt_h.get("CUST_ACC_NO").toString());
		assertEquals("sysadmin", stmt_h.get("ID_LOGIN").toString());
		assertEquals("0", stmt_h.get("STMT_TOTAL").toString());
		assertEquals("test", stmt_h.get("CUST_NAME").toString());
		assertEquals("aaaaa", stmt_h.get("ADDRESS1").toString());
		assertEquals("bbb", stmt_h.get("ADDRESS2").toString());
		assertEquals("cccc", stmt_h.get("ADDRESS3").toString());
		assertEquals("ddd", stmt_h.get("ADDRESS4"));
		assertEquals("200281", stmt_h.get("ZIP_CODE").toString());
		assertEquals("china", stmt_h.get("COUNTRY").toString());
		assertEquals("13313231313", stmt_h.get("TEL_NO").toString());
		assertEquals("22222513", stmt_h.get("FAX_NO").toString());
		assertEquals("Jim abc", stmt_h.get("CONTACT_NAME").toString());
		assertEquals("aaa@qq.com", stmt_h.get("CONTACT_EMAIL").toString());
		assertEquals("MR", stmt_h.get("SALUTATION").toString());

		assertEquals(null, stmt_d);
	}
}
