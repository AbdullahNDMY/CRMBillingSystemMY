/**
 * @(#)R_RRR_R03BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/26
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_rrr.dto.R_RRR_R03Input;

/**
 * Test Class for nttdm.bsys.r_rrr.blogic.R_RRR_R03BLogic
 * 
 * @author leonzh
 */
public class R_RRR_R03BLogic_Test extends AbstractUtilTest {

	private R_RRR_R03BLogic blogic;
	private R_RRR_R03Input input;
	private BLogicResult result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new R_RRR_R03BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new R_RRR_R03Input();

		// delete data
		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");
		super.deleteAllData("M_CUST_BANKINFO");
		super.deleteAllData("M_JOB_LIST");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("M_COM_BANKINFO");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");

		// insert data to row = 30
		String[][] mGsetHData = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{ "RESULTROW", "Row Result Display",
						"Settings to display result.", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "sysadmin" } };
		super.insertData("M_GSET_H", mGsetHData);

		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{ "RESULTROW", "1", "Number of rows of result to be display.",
						"0", TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "30",
						AUDIT_VALUE, "1", } };
		super.insertData("M_GSET_D", mGsetDData);

		// insert data to Retrieve SystemBase = SG
		String[][] mGsetHData2 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{
						"SYSTEMBASE",
						"System Base",
						"Settings to define base of system to enable certain feature.",
						"0", "2010-11-24 16:50:40", "2010-11-24 16:50:40",
						"sysadmin", } };
		super.insertData("M_GSET_H", mGsetHData2);

		String[][] mGsetDData2 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"SYSTEMBASE",
						"1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"sysadmin", "SG", AUDIT_VALUE, "1", } };
		super.insertData("M_GSET_D", mGsetDData2);

		// insert data to totalReport >0
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
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "USERFULL", "", "", "2001-01-01", "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"ABC", AUDIT_VALUE, "" }, };
		super.insertData("M_CUST", mCustData);

		String[][] mComBankinfoData = {
				{ "ID_COM_BANK", "ID_COM", "ID_BANK", "COM_SWIFT",
						"COM_ACCT_NO", "COM_ACCT_TYPE", "COM_ACCT_NAME",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT",
						"COM_CUR" },
				{ "0", "11", "398", "", "11111", "1", "8888", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "admin", AUDIT_VALUE, "DZD" } };
		super.insertData("M_COM_BANKINFO", mComBankinfoData);

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
				{ "1", "IN", "0", "1", "2011-01-05", "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
						"0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] tCsbHData = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "0", "", "CQ",
						"2011-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "2267", AUDIT_VALUE,
						"0", "0", "0", "2011-08-30", "1111", "0" } };
		super.insertData("T_CSB_H", tCsbHData);

		String[][] tCsbDData = {
				{ "ID_REF", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "RECEIPT_NO", "AMT_DUE", "AMT_PAID",
						"FOREX_LOSS", "FOREX_GAIN", "ID_AUDIT" },
				{ "1                   ", "0", "2011-09-13", "2011-09-13",
						"sysadmin", "111238              ", "606", "51", "1",
						"21", AUDIT_VALUE } };
		super.insertData("T_CSB_D", tCsbDData);
	}

	/**
	 * 
	 * Case 1: test normal situation <br>
	 * 
	 * Condition: <br>
	 * Paid Month From Month = "" <br>
	 * Paid Month From Year = "" <br>
	 * Paid Month To Month = "" <br>
	 * Paid Month To Year = "" <br>
	 * TbxCustomerName = "Duy Duong" <br>
	 * TbxAffiliateCod = "ABC" <br>
	 * CboPaymentMetho = "CQ" <br>
	 * CboCurrency = "MYR" <br>
	 * TbxBankInName = "8888" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute01() {
//		input.setCboPaidMonthFr("");
//		input.setTbxYearFr("");
//		input.setCboPaidMonthTo("");
//		input.setTbxYearTo("");
		input.setTbxCustomerName("Duy Duong");
		input.setTbxAffiliateCod("ABC");
		input.setCboPaymentMetho("CQ");
		input.setCboCurrency("MYR");
		input.setTbxBankInName("8888");
		input.setTbxBillDocument("");
        input.setTbxChequeNo("");
        input.setTbxReceiptNo("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}

	/**
	 * 
	 * Case 2: test normal situation <br>
	 * 
	 * Condition: <br>
	 * Paid Month From Month = "3" <br>
	 * Paid Month From Year = "2011" <br>
	 * Paid Month To Month = "" <br>
	 * Paid Month To Year = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * CboPaymentMetho = "" <br>
	 * CboCurrency = "" <br>
	 * TbxBankInName = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute02() {
//		input.setCboPaidMonthFr("3");
//		input.setTbxYearFr("2011");
//		input.setCboPaidMonthTo("");
//		input.setTbxYearTo("");
		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setCboPaymentMetho("");
		input.setCboCurrency("");
		input.setTbxBankInName("");
		input.setTbxBillDocument("");
        input.setTbxChequeNo("");
        input.setTbxReceiptNo("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}

	/**
	 * 
	 * Case 3: test normal situation <br>
	 * 
	 * Condition: <br>
	 * Paid Month From Month = "" <br>
	 * Paid Month From Year = "" <br>
	 * Paid Month To Month = "5" <br>
	 * Paid Month To Year = "2011" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * CboPaymentMetho = "" <br>
	 * CboCurrency = "" <br>
	 * TbxBankInName = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute03() {
//		input.setCboPaidMonthFr("");
//		input.setTbxYearFr("");
//		input.setCboPaidMonthTo("5");
//		input.setTbxYearTo("2011");
		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setCboPaymentMetho("");
		input.setCboCurrency("");
		input.setTbxBankInName("");
		input.setTbxBillDocument("");
        input.setTbxChequeNo("");
        input.setTbxReceiptNo("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}

	/**
	 * 
	 * Case 4: test normal situation <br>
	 * 
	 * Condition: <br>
	 * Paid Month From Month = "11" <br>
	 * Paid Month From Year = "2010" <br>
	 * Paid Month To Month = "11" <br>
	 * Paid Month To Year = "2011" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * CboPaymentMetho = "" <br>
	 * CboCurrency = "" <br>
	 * TbxBankInName = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute04() {
//		input.setCboPaidMonthFr("11");
//		input.setTbxYearFr("2010");
//		input.setCboPaidMonthTo("11");
//		input.setTbxYearTo("2011");
		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setCboPaymentMetho("");
		input.setCboCurrency("");
		input.setTbxBankInName("");
		input.setTbxBillDocument("");
        input.setTbxChequeNo("");
        input.setTbxReceiptNo("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}

}
