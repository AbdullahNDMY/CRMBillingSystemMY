package nttdm.bsys.common.util;

import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_04Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_04Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class G_BTH_P01_2_Test extends AbstractUtilTest {

	private G_BTH_P01_2 G_BTH_P01_2 = null;
	RP_B_BTH_P01_04Input param = null;
	RP_B_BTH_P01_04Output outputDTO = null;
	BillingSystemUserValueObject uvo = null;

	@Override
	protected void setUpData() throws Exception {
		uvo = new BillingSystemUserValueObject();
		G_BTH_P01_2 = new G_BTH_P01_2();
		uvo.setId_user("sysadmin");
		uvo.setSESSION_DIRECTORY("C:\\");
		param = new RP_B_BTH_P01_04Input();
		param.setUvo(uvo);
		G_BTH_P01_2.setQueryDAO(queryDAO);
		G_BTH_P01_2.setUpdateDAO(updateDAO);
		G_BTH_P01_2.setUpdateDAONuked(updateDAONuked);
	}

	public void testExecute1() {
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.deleteAllData("RESOURCESMAINT");
		this.deleteAllData("M_GSET_D");

		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("M_COM_ADR");

		String[][] resourcesmaintS5 = {
				{ "ID", "CATEGORY", "SEQ", "RESOURCE_ID", "VALUE", "RES_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATE", "ID_LOGIN" },

				{ "123", "RPT", "00001", "INVSG", "billDocument.jasper",
						"wicresoft", "0", "2012-03-14", "2012-03-14",
						"sysadmin" },
				{ "124", "RPT", "00001", "DBNSG", "billDocument.jasper",
						"wicresoft", "0", "2012-03-14", "2012-03-14",
						"sysadmin" },
				{ "125", "RPT", "00001", "CRNSG", "billDocument.jasper",
						"wicresoft", "0", "2012-03-14", "2012-03-14",
						"sysadmin" },
				{ "126", "RPT", "00001", "INVMY", "B_BTH_F01_NTTMSC_IN.jasper",
						"wicresoft", "0", "2012-03-14", "2012-03-14",
						"sysadmin" },
				{ "127", "RPT", "00001", "DBNMY", "B_BTH_F01_NTTMSC_DN.jasper",
						"wicresoft", "0", "2012-03-14", "2012-03-14",
						"sysadmin" },
				{ "128", "RPT", "00001", "CRNMY", "B_BTH_F01_NTTMSC_CN.jasper",
						"wicresoft", "0", "2012-03-14", "2012-03-14",
						"sysadmin" } };

		super.insertData("RESOURCESMAINT", resourcesmaintS5);

		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"NOPRINTDOC",
						"1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"sysadmin", "99", null, "1" },
				{
						"GST",
						"1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"sysadmin", "7", null, "1" },
				{
						"BILL_DOC_FOOTER",
						"1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"sysadmin", null, null, "1" },
				{
						"SYSTEMBASE",
						"1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
						"sysadmin", "SG", null, "1" } };
		super.insertData("M_GSET_D", mGsetDData);

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
				{ "PL-211-I0011-I0178  ", "IN", "0", "1", TEST_DAY_TODAY, "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "0",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "0", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
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
						TEST_DAY_TODAY, "USERFULL", "", "", null, "", "", "0",
						"0123456789", "0123456789", "CORP", "0123456789", "",
						null, "" } };
		super.deleteAllData("M_CUST");
		super.insertData("M_CUST", dataM_CUST);

		String[][] mComDataAdr = {
				{ "ID_COM", "COM_ADR_TYPE", "COM_ADR_LINE1", "COM_ADR_LINE2",
						"COM_ADR_LINE3", "ZIP_CODE", "COUNTRY", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1", "CA", "No 1", " ", " ", "12345", "WF",
						"2011-08-08 11:26:06", "2011-08-08 11:26:06",
						"USERFULL", null } };
		super.insertData("M_COM_ADR", mComDataAdr);

		String[][] mComData = {
				{ "ID_COM", "COM_NAME", "COM_TEL_NO", "COM_FAX_NO",
						"COM_WEBSITE", "AFFILIATE_CODE", "LOGO",
						"PROXSERV_NAME", "PRIMDOMAIN_NO", "SECDOMAIN_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "COM_REGNO", "PORT_NO", "ID_AUDIT",
						"DEFAULT_DIALUPTELNO", "DEFAULT_ROUTERPW" },
				{ "1", "NTT DATA", null, "321", "http://www.nttdata.com.vn",
						"11101", null, "proxy.hcm2.vn", "zzzzzz.vn",
						"wwwwwww.com", "0", "2011-08-08 11:26:06",
						"2011-08-08 11:26:06", "USERFULL", "12345", "8080",
						null, "15287", "pass" } };
		super.deleteAllData("M_COM");
		super.insertData("M_COM", mComData);

		String[][] dataM_COM_BANKINFO = {
				{ "ID_COM_BANK", "ID_COM", "ID_BANK", "COM_SWIFT",
						"COM_ACCT_NO", "COM_ACCT_TYPE", "COM_ACCT_NAME",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT",
						"COM_CUR" },
				{ "0", "1", "398", "", "11111", "1", "8888", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "admin", null, "DZD" } };

		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);

		String[][] dataM_BANK = {
				{ "ID_BANK", "BANK_FULL_NAME", "BANK_CODE", "BANK_NAME",
						"BRANCH_CODE", "BRANCH_NAME", "BANK_TEL_NO",
						"BANK_FAX_NO", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "398", "Bank Money-XYZ", "0123", "Bank Money", "012", "XYZ",
						"", "", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						"USERFULL", null } };
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);

		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT", "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC" },
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9",
						"0", "0", "1", "201", "2216", "114", "0", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, "", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, "system", null, null,
						"1", "S", "0" },
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9",
						"0", "0", "1", "201", "2217", "114", "0", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, "", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, "system", null, null,
						"1", "S", "0" } };

		super.deleteAllData("T_BIL_D");
		super.insertData("T_BIL_D", dataT_BIL_D);

		String[][] testDataTBatchLog2 = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN" },
				{ "2216", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
						"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
						null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
						"USERFULL" },
				{ "2217", "43", "PS3", "2011-5-11", "2011-5-11", "1", "1",
						"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
						null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
						"USERFULL" }, };
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		String fullIdRefs = new String(
				"PL-211-I0011-I0178  ,PL-211-I0011-I0174  ");
		param.setFullIdRefs(fullIdRefs);

		GlobalProcessResult result = G_BTH_P01_2.execute(param, outputDTO);
		// logo_report.jpg line 411
		// file load path change to File logoFile = new
		// File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
		assertEquals(true, result.getErrors().isEmpty());
		assertEquals(true, result.getMessages().isEmpty());
		assertEquals(true, result.getFile().isFile());
		assertEquals(
				".zip",
				result.getFile().getName()
						.substring(result.getFile().getName().lastIndexOf(".")));
	}

}
