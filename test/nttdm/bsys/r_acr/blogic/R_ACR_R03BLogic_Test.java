/**
 * @(#)R_ACR_R03BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/26
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_acr.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_acr.dto.R_ACR_R03Input;

/**
 * Test Class for nttdm.bsys.r_acr.blogic.R_ACR_R03BLogic
 * 
 * @author leonzh
 */
public class R_ACR_R03BLogic_Test extends AbstractUtilTest {

	private R_ACR_R03BLogic blogic;
	private R_ACR_R03Input input;
	private BLogicResult result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new R_ACR_R03BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		input = new R_ACR_R03Input();

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
		super.deleteAllData("M_SVC");
		super.deleteAllData("M_SVG");
		
		//delete T_SUBSCRIPTION_INFO
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
        
        //delete T_CUST_PLAN_H Info
        super.deleteAllData("T_CUST_PLAN_SVC");
        super.deleteAllData("T_CUST_PLAN_LINK");
        super.deleteAllData("T_CUST_PLAN_D");
        super.deleteAllData("T_CUST_PLAN_H");
        
        super.deleteAllData("T_CLC_IMP_MONTHLY_SUM");
        super.deleteAllData("T_CLC_IMP_HD");
	}

	/**
	 * 
	 * Case 1: test normal situation <br>
	 * 
	 * Condition: <br>
	 * Bill Month = "1" <br>
	 * Year = "2011" <br>
	 * Customer Name = "Duy Duong" <br>
	 * Service Name = "No GST" <br>
	 * Currency = "MYR" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute01() {
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
						"2011-07-07 15:02:51", "USERFULL", "", "", "2012-01-01", "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"", AUDIT_VALUE, "" }, };
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
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
                { "1", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "43", "123", "1", "0", "2", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", AUDIT_VALUE, null,"0","1" } };
		super.insertData("T_BIL_D", tBilDData);

		String[][] mSvgData = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-6-28  8:17:25", "2011-8-8  11:41:51",
						"USERFULL", AUDIT_VALUE }, };
		super.insertData("M_SVG", mSvgData);

		String[][] mSvcData = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "2", "268", "ITM", "No GST", "", "2", "0", "0",
						"2011-2-25  14:53:37", "2011-2-25  14:53:37",
						"USERFULL", AUDIT_VALUE }, };
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

		// insert data to Retrieve SystemBase = SG
		String[][] mGsetHData2 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{
						"SYSTEMBASE",
						"System Base",
						"Settings to define base of system to enable certain feature.",
						"0", "2010-11-24 16:50:40", "2010-11-24 16:50:40",
						"sysadmin", },
				{ "BATCH_G_CLC_P01", "Row Result Display",
                    "Settings to display result.", "0", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, "sysadmin" }};
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
						"sysadmin", "SG", AUDIT_VALUE, "1", },
				{ "BATCH_G_CLC_P01", "1", "Number of rows of result to be display.",
                    "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "C:\\",
                    AUDIT_VALUE, "1", }};
		super.insertData("M_GSET_D", mGsetDData2);
		
		String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "0",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] T_CLC_IMP_MONTHLY_SUM = {
                { "ID_CLC_IMP_BATCH", "ID_CLC_CUST", "MONTH_YEAR", "IS_INVOICED",
                        "INVOICE_TOTAL", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "00001", TEST_DAY_TODAY.substring(5,7)+"/"+TEST_DAY_TODAY.substring(0,4), "1",
                   "100", TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] T_CUST_PLAN_H = {
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
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN","DATE_CREATED", "DATE_UPDATED", "ID_LOGIN","IS_DELETED","ID_SUB_INFO"},
                { "43", "2011-8-22  16:38:04", "2011-8-22  16:38:04", "sysadmin", "0",  "00001" } };
        
        String[][] testDataTCustPlanD =
        {
            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"123" , "43" , "PS3" , TEST_DAY_TODAY , null , "0" , "0" ,
                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
                null , "0" , "2011-07-28" , "2011-07-28" , "sysadmin" , null}};
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "123", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};

        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("T_CLC_IMP_MONTHLY_SUM", T_CLC_IMP_MONTHLY_SUM);
        super.insertData("T_CUST_PLAN_H", T_CUST_PLAN_H);
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);

        input.setCboUsageMonth(String.valueOf(Integer.parseInt(TEST_DAY_TODAY.substring(5,7))));
        input.setTbxUsageYear(TEST_DAY_TODAY.substring(0,4));
		input.setTbxCustomerName("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setTbxSubID("");
        input.setTbxCustomerId("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}

	/**
	 * 
	 * Case 2: test normal situation <br>
	 * 
	 * Condition: <br>
	 * Bill Month = "11" <br>
	 * Year = "2011" <br>
	 * Customer Name = "Duy Duong" <br>
	 * Service Name = "No GST" <br>
	 * Currency = "MYR" <br>
	 * 
	 * Result: <br>
	 * result.getResultObject() != null <br>
	 * 
	 */
	public void testExecute02() {
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
						"2011-07-07 15:02:51", "USERFULL", "", "", "2012-01-01", "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"", AUDIT_VALUE, "" }, };
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
				{ "1", "IN", "0", "1", "2011-11-05", "CQ", "229743", "BA",
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
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
                { "1", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "43", "123", "1", "0", "2", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", AUDIT_VALUE, null,"0","1" } };
		super.insertData("T_BIL_D", tBilDData);

		String[][] mSvgData = {
				{ "SVC_GRP", "SVC_GRP_NAME", "ACC_CODE", "ORIGIN_CODE",
						"PRODUCT_CODE", "REMARK", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "268", "TEST SERVICE", "0809070605", "1", "05124852  ",
						"KAKUNIN", "2011-6-28  8:17:25", "2011-8-8  11:41:51",
						"USERFULL", AUDIT_VALUE }, };
		super.insertData("M_SVG", mSvgData);

		String[][] mSvcData = {
				{ "ID_SERVICE", "SVC_GRP", "SVC_CATEGORY", "SVC_DESC",
						"ACC_CODE", "UQTY", "GST", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "2", "268", "ITM", "No GST", "", "2", "0", "0",
						"2011-2-25  14:53:37", "2011-2-25  14:53:37",
						"USERFULL", AUDIT_VALUE }, };
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

		// insert data to Retrieve SystemBase = SG
        String[][] mGsetHData2 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "SYSTEMBASE",
                        "System Base",
                        "Settings to define base of system to enable certain feature.",
                        "0", "2010-11-24 16:50:40", "2010-11-24 16:50:40",
                        "sysadmin", },
                { "BATCH_G_CLC_P01", "Row Result Display",
                    "Settings to display result.", "0", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, "sysadmin" }};
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
                        "sysadmin", "SG", AUDIT_VALUE, "1", },
                { "BATCH_G_CLC_P01", "1", "Number of rows of result to be display.",
                    "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "C:\\",
                    AUDIT_VALUE, "1", }};
        super.insertData("M_GSET_D", mGsetDData2);
        
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "0",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] T_CLC_IMP_MONTHLY_SUM = {
                { "ID_CLC_IMP_BATCH", "ID_CLC_CUST", "MONTH_YEAR", "IS_INVOICED",
                        "INVOICE_TOTAL", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "00001", TEST_DAY_TODAY.substring(5,7)+"/"+TEST_DAY_TODAY.substring(0,4), "1",
                   "100", TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] T_CUST_PLAN_H = {
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
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN","DATE_CREATED", "DATE_UPDATED", "ID_LOGIN","IS_DELETED","ID_SUB_INFO"},
                { "43", "2011-8-22  16:38:04", "2011-8-22  16:38:04", "sysadmin", "0",  "00001" } };
        
        String[][] testDataTCustPlanD =
        {
            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"123" , "43" , "PS3" , TEST_DAY_TODAY , null , "0" , "0" ,
                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
                null , "0" , "2011-07-28" , "2011-07-28" , "sysadmin" , null}};
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "123", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};

        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("T_CLC_IMP_MONTHLY_SUM", T_CLC_IMP_MONTHLY_SUM);
        super.insertData("T_CUST_PLAN_H", T_CUST_PLAN_H);
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);

        input.setCboUsageMonth(String.valueOf(Integer.parseInt(TEST_DAY_TODAY.substring(5,7))));
        input.setTbxUsageYear(TEST_DAY_TODAY.substring(0,4));
		input.setTbxCustomerName("");
		input.setTbxServiceName("");
		input.setCboCurrency("");
		input.setTbxSubID("");
        input.setTbxCustomerId("");

		result = blogic.execute(input);

		assertNotNull(result.getResultObject());
	}
}
