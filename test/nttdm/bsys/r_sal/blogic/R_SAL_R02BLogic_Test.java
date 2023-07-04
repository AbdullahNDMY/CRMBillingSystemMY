/**
 * @(#)R_SAL_R02BLogic_Test.java
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

import java.util.HashMap;
import java.util.List;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_sal.dto.R_SAL_R02Input;
import nttdm.bsys.r_sal.dto.R_SAL_R02Output;

/**
 * Test Class for nttdm.bsys.R_SAL.blogic.R_SAL_R02BLogic
 * 
 * @author Leonzh
 */
public class R_SAL_R02BLogic_Test extends AbstractUtilTest {

	private R_SAL_R02BLogic blogic;
	private R_SAL_R02Input input;
	private BLogicResult result;
	private R_SAL_R02Output output;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new R_SAL_R02BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new R_SAL_R02Input();

		// delete data
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CUST_CTC");
		super.deleteAllData("M_CUST_BANKINFO");
		super.deleteAllData("M_JOB_LIST");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");
		super.deleteAllData("M_SVG");
		super.deleteAllData("M_SVC");

		// insert data to totalReport > 0
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
						"2011-07-07 15:02:51", "USERFULL", "", "", "1986-11-11", "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"ABC", AUDIT_VALUE, "" }, };
		super.insertData("M_CUST", mCustData);

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

		String[][] tBilDData = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT","ITEM_TYPE" },
				{ "1", "1", "1", "0", null, "3", "6", "3500", "0", "0", "1",
						"201", "2216", "114", "0", "311", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", AUDIT_VALUE, null ,"1","1","O"} };
		super.insertData("T_BIL_D", tBilDData);

		String[][] mSvgData = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-06-28 08:17:25",
						"2011-08-08 11:41:51", "USERFULL", AUDIT_VALUE } };
		super.insertData("M_SVG", mSvgData);

		String[][] mSvcData = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "311", "268", "TYP", "No GST", null, null, "0", "0",
						"2011-02-25 14:53:37", "2011-02-25 14:53:37",
						"USERFULL", AUDIT_VALUE, } };
		super.insertData("M_SVC", mSvcData);

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
	}

	/**
	 * 
	 * Case 1: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = null <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute01() {
		input.setStartIndex(null);
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 2: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "1" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute02() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 3: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "2011" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute03() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 4: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "Duy Duong" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute04() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("Duy Duong");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 5: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "ABC" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute05() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("ABC");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 6: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "1                   " <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute06() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("1                   ");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 7: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "201" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute07() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 8: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "0" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute08() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 9: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "0" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute09() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("No GST");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 10: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "MYR" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute10() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("MYR");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 11: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "CQ" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute11() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("CQ");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 12: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "1" <br>
	 * CboBillMonth = "" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "1" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute12() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("1");

		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("1");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(1), output.getTotalRow());

		List<HashMap<String, Object>> listSalesReport = output
				.getListSalesReport();
		HashMap<String, Object> salesReport = listSalesReport.get(0);

		assertEquals("05/01/2011", salesReport.get("DATE_REQ"));
		assertEquals("Duy Duong", salesReport.get("CUST_NAME"));
		assertEquals("ABC", salesReport.get("AFFILIATE_CODE"));
		assertEquals("1", salesReport.get("ID_REF"));
		assertEquals("201", salesReport.get("ID_CUST_PLAN").toString());
		assertEquals("No GST", salesReport.get("SVC_DESC").toString());
		assertEquals("Non-Singpost", salesReport.get("IS_SINGPOST"));
		//assertEquals("CQ", salesReport.get("PAY_METHOD"));
		assertEquals("MYR", salesReport.get("BILL_CURRENCY"));
		assertEquals("3500", salesReport.get("INVOICE_AMT").toString());
		assertEquals("0", salesReport.get("GST_AMOUNT").toString());
		assertEquals("3500", salesReport.get("BILL_AMOUNT").toString());

		assertEquals("success", result.getResultString());
	}

	/**
	 * 
	 * Case 13: test normal situation <br>
	 * 
	 * Condition: <br>
	 * StartIndex = "0" <br>
	 * ChkManual = "" <br>
	 * CboBillMonth = "11" <br>
	 * TbxBillYear = "" <br>
	 * TbxCustomerName = "" <br>
	 * TbxAffiliateCod = "" <br>
	 * TbxInvoiceNo = "" <br>
	 * TbxSubscription = "" <br>
	 * TbxServiceName = "" <br>
	 * CboCurrency = "" <br>
	 * CboPaymentMetho = "" <br>
	 * ChkSingPost = "" <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * totalReport = "0" <br>
	 * 
	 */
	public void testExecute13() {
		input.setStartIndex(new Integer(0));
		//input.setChkManual("");

		input.setTbxBillYearMonth("01/01/2012");
		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxInvoiceNo("");
		input.setTbxSubscription("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setCboPaymentMetho("");
		//input.setChkSingPost("");

		result = blogic.execute(input);
		output = (R_SAL_R02Output) result.getResultObject();

		//assertEquals("1", output.getSearchflag());
		assertEquals(new Integer(30), output.getRow());
		assertEquals(new Integer(0), output.getTotalRow());

		BLogicMessages messages = result.getMessages();
		assertNotNull(messages);
		assertEquals("org.apache.struts.action.GLOBAL_MESSAGE", messages
				.getGroup().next());
		assertEquals("info.ERR2SC002", messages.get().next().getKey());

		assertEquals("success", result.getResultString());
	}

}
