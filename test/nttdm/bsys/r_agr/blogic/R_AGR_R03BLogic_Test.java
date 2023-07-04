/**
 * @(#)R_AGR_R03BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/19
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_agr.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_agr.dto.R_AGR_R03Input;

/**
 * Test Class for nttdm.bsys.m_cdm.blogic.R_AGR_R03BLogic
 * 
 * @author leonzh
 */
public class R_AGR_R03BLogic_Test extends AbstractUtilTest {

	private R_AGR_R03BLogic blogic;
	private R_AGR_R03Input input;
	private BLogicResult result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new R_AGR_R03BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new R_AGR_R03Input();
	}

	/**
	 * 
	 * Case 1: test normal situation <br>
	 * 
	 * Condition: <br>
	 * CboBillMonth = "1" <br>
	 * TbxBillYear = "2011" <br>
	 * TbxCustomerName = "Duy Duong" <br>
	 * TbxAffiliateCod = "ABC" <br>
	 * CboPaymentMetho = "CQ" <br>
	 * CboCurrency = "MYR" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute01() {
		// delete all exist data
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

		// insert data
		String[][] dataT_BIL_H = {
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
				{ "2", "IN", "0", "1", "2011-01-05", "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
						"0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", dataT_BIL_H);

		String[][] dataM_CUST = {
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
						"0123456789", "0", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "USERFULL", "", "", "2008-02-02", "", "", "0",
						"0123456789", "0123456789", "CORP", "0123456789",
						"ABC", AUDIT_VALUE, "" } };
		super.insertData("M_CUST", dataM_CUST);

		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
				{ "2", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", AUDIT_VALUE, null,"0","1" } };
		super.insertData("T_BIL_D", dataT_BIL_D);

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

		input.setCboBillMonth("1");
		input.setTbxBillYear("2011");
		input.setTbxCustomerName("Duy Duong");
		input.setTbxAffiliateCod("ABC");
		input.setCboPaymentMetho("CQ");
		input.setCboCurrency("MYR");
		input.setTbxBillDocumentNo("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}

	/**
	 * 
	 * Case 2: test normal situation <br>
	 * 
	 * Condition: <br>
	 * CboBillMonth = "11" <br>
	 * TbxBillYear = "2011" <br>
	 * TbxCustomerName = "Duy Duong" <br>
	 * TbxAffiliateCod = "ABC" <br>
	 * CboPaymentMetho = "CQ" <br>
	 * CboCurrency = "MYR" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute02() {
		// delete all exist data
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

		// insert data
		String[][] dataT_BIL_H = {
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
				{ "2", "IN", "0", "1", "2011-11-05", "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
						"0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", dataT_BIL_H);

		String[][] dataM_CUST = {
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
						"0123456789", "0", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "USERFULL", "", "", "2003-02-02", "", "", "0",
						"0123456789", "0123456789", "CORP", "0123456789",
						"ABC", AUDIT_VALUE, "" } };
		super.insertData("M_CUST", dataM_CUST);

		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
				{ "2", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", AUDIT_VALUE, null,"0","1" } };
		super.insertData("T_BIL_D", dataT_BIL_D);

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

		input.setCboBillMonth("11");
		input.setTbxBillYear("2011");
		input.setTbxCustomerName("Duy Duong");
		input.setTbxAffiliateCod("ABC");
		input.setCboPaymentMetho("CQ");
		input.setCboCurrency("MYR");
		input.setTbxBillDocumentNo("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}

    public void testExecute03() {
        // delete all exist data
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

        // insert data
        String[][] dataT_BIL_H = {
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
                { "2", "IN", "0", "1", "2011-11-05", "CQ", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
                        "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        AUDIT_VALUE } };
        super.insertData("T_BIL_H", dataT_BIL_H);

        String[][] dataM_CUST = {
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
                        "0123456789", "0", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "", "", "2003-02-02", "", "", "0",
                        "0123456789", "0123456789", "CORP", "0123456789",
                        "ABC", AUDIT_VALUE, "" } };
        super.insertData("M_CUST", dataM_CUST);

        String[][] dataT_BIL_D = {
                { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                        "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
                        "APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
                        "ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
                { "2", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "201", "2216", "114", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", AUDIT_VALUE, null,"0","1" } };
        super.insertData("T_BIL_D", dataT_BIL_D);

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

        input.setCboBillMonth("11");
        input.setTbxBillYear("");
        input.setTbxCustomerName("Duy Duong");
        input.setTbxAffiliateCod("ABC");
        input.setCboPaymentMetho("CQ");
        input.setCboCurrency("MYR");
        input.setTbxBillDocumentNo("");
        input.setCboBillMonthTo("12");
        input.setTbxBillYearTo("2015");

        result = blogic.execute(input);

        assertNotNull(result.getResultObject());
    }
    
    public void testExecute04() {
        // delete all exist data
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

        // insert data
        String[][] dataT_BIL_H = {
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
                { "2", "IN", "0", "1", "2011-11-05", "CQ", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
                        "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        AUDIT_VALUE } };
        super.insertData("T_BIL_H", dataT_BIL_H);

        String[][] dataM_CUST = {
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
                        "0123456789", "0", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "", "", "2003-02-02", "", "", "0",
                        "0123456789", "0123456789", "CORP", "0123456789",
                        "ABC", AUDIT_VALUE, "" } };
        super.insertData("M_CUST", dataM_CUST);

        String[][] dataT_BIL_D = {
                { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                        "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
                        "APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
                        "ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
                { "2", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "201", "2216", "114", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", AUDIT_VALUE, null,"0","1" } };
        super.insertData("T_BIL_D", dataT_BIL_D);

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

        input.setCboBillMonth("11");
        input.setTbxBillYear("2011");
        input.setTbxCustomerName("Duy Duong");
        input.setTbxAffiliateCod("ABC");
        input.setCboPaymentMetho("CQ");
        input.setCboCurrency("MYR");
        input.setTbxBillDocumentNo("");
        input.setCboBillMonthTo("9");
        input.setTbxBillYearTo("2015");

        result = blogic.execute(input);

        assertNotNull(result.getResultObject());
    }
}
