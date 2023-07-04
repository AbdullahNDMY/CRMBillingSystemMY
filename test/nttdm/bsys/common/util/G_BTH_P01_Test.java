/**
 * It need to do following change in G_BTH_P01 line 411
 * File logoFile = new File(getClass().getClassLoader().getResource("../../image/logo_report.jpg").toURI());
 * to
 * File logoFile = new File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
 */

package nttdm.bsys.common.util;

import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class G_BTH_P01_Test extends AbstractUtilTest{
	
	G_BTH_P01 G_BTH_P01 = null;
	RP_B_BTH_P01_03Input param = null;
	BillingSystemUserValueObject uvo = null;
	String [] idRefs = null;
	RP_B_BTH_P01_03Output outputDTO = null;
	
	@Override
	protected void setUpData() throws Exception {
		G_BTH_P01 = new G_BTH_P01();
		param = new RP_B_BTH_P01_03Input();
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		uvo.setSESSION_DIRECTORY("C:\\");
		param.setUvo(uvo);
		outputDTO = new RP_B_BTH_P01_03Output();
		G_BTH_P01.setQueryDAO(super.queryDAO);
		G_BTH_P01.setUpdateDAO(super.updateDAO);
		G_BTH_P01.setUpdateDAONuked(super.updateDAONuked);
		
		super.deleteAllData("RESOURCESMAINT");
		String[][] resourcesmaintS5 = {
				{ "ID", "CATEGORY", "SEQ", "RESOURCE_ID", "VALUE", "RES_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATE", "ID_LOGIN" },

				{ "123", "RPT", "00001", "INVSG", "billDocument.jasper", "wicresoft",
						"0", "2012-03-14", "2012-03-14", "sysadmin" },
				{ "124", "RPT", "00001", "DBNSG", "billDocument.jasper", "wicresoft",
							"0", "2012-03-14", "2012-03-14", "sysadmin" },
				{ "125", "RPT", "00001", "CRNSG", "billDocument.jasper", "wicresoft",
								"0", "2012-03-14", "2012-03-14", "sysadmin" },
				{ "126", "RPT", "00001", "INVMY", "B_BTH_F01_NTTMSC_IN.jasper", "wicresoft",
						"0", "2012-03-14", "2012-03-14", "sysadmin" },
				{ "127", "RPT", "00001", "DBNMY", "B_BTH_F01_NTTMSC_DN.jasper", "wicresoft",
							"0", "2012-03-14", "2012-03-14", "sysadmin" },
				{ "128", "RPT", "00001", "CRNMY", "B_BTH_F01_NTTMSC_CN.jasper", "wicresoft",
								"0", "2012-03-14", "2012-03-14", "sysadmin" }};
		
		super.insertData("RESOURCESMAINT", resourcesmaintS5);
	}
	/**
	 *  in order to UT test, the source file G_BTH_P01.java  Line 795 and 393 need to change the file path to absolute path
	 *  file load path change to File logoFile = new File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
	 *  
	 *  BILL_TYPE = IN
	 */
	public void testExecute1(){
	    
		this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "SG", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
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
						"COM_ADR_LINE3", "ZIP_CODE", "COUNTRY", 
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1", "CA", "No 1", " ", " ", "12345", "WF",
						"2011-08-08 11:26:06", "2011-08-08 11:26:06",
						"USERFULL", null } };
		super.deleteAllData("M_COM_ADR");
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
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","1","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"398","Bank Money-XYZ","0123","Bank Money",
						"012","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
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
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","1"},
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "1",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","S","1"}};
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
	}
	
	/**
	 *  in order to UT test, the source file G_BTH_P01.java  Line 795 and 393 need to change the file path to absolute path
	 *  file load path change to File logoFile = new File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
	 *  
	 *  BILL_TYPE = CN
	 */
	public void testExecute2(){
		this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "SG", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "CN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"SG", "6", "12", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "1", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
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
						"COM_ADR_LINE3", "ZIP_CODE", "COUNTRY", 
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1", "CA", "No 1", " ", " ", "12345", "WF",
						"2011-08-08 11:26:06", "2011-08-08 11:26:06",
						"USERFULL", null } };
		super.deleteAllData("M_COM_ADR");
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
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","1","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"398","Bank Money-XYZ","0123","Bank Money",
						"012","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
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
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null,"1","S","1" },
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "1",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","S","1"}};
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
	}
	
	/**
	 *  in order to UT test, the source file G_BTH_P01.java  Line 795 and 393 need to change the file path to absolute path
	 *  file load path change to File logoFile = new File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
	 *  
	 *  BILL_TYPE = DN
	 */
	public void testExecute3(){
		this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "SG", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "DN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
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
						"COM_ADR_LINE3", "ZIP_CODE", "COUNTRY", 
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1", "CA", "No 1", " ", " ", "12345", "WF",
						"2011-08-08 11:26:06", "2011-08-08 11:26:06",
						"USERFULL", null } };
		super.deleteAllData("M_COM_ADR");
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
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","1","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"398","Bank Money-XYZ","0123","Bank Money",
						"012","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "0", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","0"},
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "1",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null,"1","S","0" }};
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
	}
	
	/**
	 *  in order to UT test, the source file G_BTH_P01.java  Line 795 and 393 need to change the file path to absolute path
	 *  file load path change to File logoFile = new File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
	 *  
	 *  BILL_TYPE = others
	 */
	public void testExecute4(){
		this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "SG", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "OT", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
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
						"COM_ADR_LINE3", "ZIP_CODE", "COUNTRY", 
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1", "CA", "No 1", " ", " ", "12345", "WF",
						"2011-08-08 11:26:06", "2011-08-08 11:26:06",
						"USERFULL", null } };
		super.deleteAllData("M_COM_ADR");
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
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","1","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"398","Bank Money-XYZ","0123","Bank Money",
						"012","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null,"1","S","1" },
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "1",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","O","0"}};
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = null;
		try{
		    result = G_BTH_P01.execute(param, outputDTO);
		}catch (Exception e) {
			
		}
		assertNull(result);
	}
	
	/**
	 *  in order to UT test, the source file G_BTH_P01.java  Line 795 and 393 need to change the file path to absolute path
	 *  file load path change to File logoFile = new File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
	 *  
	 *  BILL_TYPE = DN  and foot is not empty
	 */
	public void testExecute5(){
		this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "SG", null, "1" },
				{"BILL_DOC_FOOTER","2",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "Note:", null, "1" },
				{"BILL_DOC_FOOTER","3",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "1.Please quote the Invoice / Debit Note No., Billing Acc. No. when making payment.", null, "1" },
				{"BILL_DOC_FOOTER","4",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "2.Due date: within 30 days from the date of issue.", null, "1" },
				{"BILL_DOC_FOOTER","5",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "3.This is a computer generated invoice. No signature is required.", null, "1" },
				{"BILL_DOC_FOOTER","6",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "4.Payment by remittance:", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "DN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
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
						"COM_ADR_LINE3", "ZIP_CODE", "COUNTRY", 
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1", "CA", "No 1", " ", " ", "12345", "WF",
						"2011-08-08 11:26:06", "2011-08-08 11:26:06",
						"USERFULL", null } };
		super.deleteAllData("M_COM_ADR");
		super.insertData("M_COM_ADR", mComDataAdr);
		
		String[][] mComData = {
				{ "ID_COM", "COM_NAME", "COM_TEL_NO", "COM_FAX_NO",
						"COM_WEBSITE", "AFFILIATE_CODE", "LOGO",
						"PROXSERV_NAME", "PRIMDOMAIN_NO", "SECDOMAIN_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "COM_REGNO", "PORT_NO", "ID_AUDIT",
						"DEFAULT_DIALUPTELNO", "DEFAULT_ROUTERPW" },
				{ "1", "NTT DATA", "5553331", "321", "http://www.nttdata.com.vn",
						"11101", null, "proxy.hcm2.vn", "zzzzzz.vn",
						"wwwwwww.com", "0", "2011-08-08 11:26:06",
						"2011-08-08 11:26:06", "USERFULL", "12345", "8080",
						null, "15287", "pass" } };
		super.deleteAllData("M_COM");
		super.insertData("M_COM", mComData);
		
		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","1","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"398","Bank Money-XYZ","0123","Bank Money",
						"012","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","1"},
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "1",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null,"1","S","0" }};
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
	}
	
	/**
	 *  in order to UT test, the source file G_BTH_P01.java  Line 795 and 393 need to change the file path to absolute path
	 *  file load path change to File logoFile = new File(getClass().getClassLoader().getResource("logo_report.jpg").toURI());
	 *  
	 *  BILL_TYPE = DN  and foot is not empty
	 *  bankInf[k].get("COM_CUR") == null
	 *  ADDRESS3 ADDRESS4 not null
	 */
	public void testExecute6(){
		this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "SG", null, "1" },
				{"BILL_DOC_FOOTER","2",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "Note:", null, "1" },
				{"BILL_DOC_FOOTER","3",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "1.Please quote the Invoice / Debit Note No., Billing Acc. No. when making payment.", null, "1" },
				{"BILL_DOC_FOOTER","4",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "2.Due date: within 30 days from the date of issue.", null, "1" },
				{"BILL_DOC_FOOTER","5",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "3.This is a computer generated invoice. No signature is required.", null, "1" },
				{"BILL_DOC_FOOTER","6",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "4.Payment by remittance:", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "DN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"SG", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "1", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "address3", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
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
						"COM_ADR_LINE3", "ZIP_CODE", "COUNTRY", 
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "1", "CA", "No 1", " ", " ", "12345", "WF",
						"2011-08-08 11:26:06", "2011-08-08 11:26:06",
						"USERFULL", null } };
		super.deleteAllData("M_COM_ADR");
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
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","1","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,null}};
		
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"398","Bank Money-XYZ","0123","Bank Money",
						"012","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","1"},
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "1",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","S","0"}};
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
	}

	/**
	 * SYSTEMBASE = MY 
	 * billingType = IN
	 */
    public void testExecute7(){
    	this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "MY", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "sysadmin", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null,"1","S","1" },
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "0",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","S","1"}};
		
		super.deleteAllData("T_BIL_D");
		super.insertData("T_BIL_D", dataT_BIL_D);
		
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
        super.insertData("M_USER", dataM_USER);
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
    }
    
    /**
	 * SYSTEMBASE = MY 
	 * billingType = IN
	 * IS_MANUAL = 1
	 */
    public void testExecute8(){
    	this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "MY", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "IN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "sysadmin", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "address", "",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT", "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC" },
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "0",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null,"1","S","0" },
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "0",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null,"1","S","0" }};
		
		super.deleteAllData("T_BIL_D");
		super.insertData("T_BIL_D", dataT_BIL_D);
		
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
        super.insertData("M_USER", dataM_USER);
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
    }
    
    /**
	 * SYSTEMBASE = MY 
	 * billingType = IN
	 * IS_MANUAL = 0  and IS_DISPLAY=0 and ITEM_LEVEL = 0
	 * FooterInfo is not null
	 */
    public void testExecute9(){
    	this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "MY", null, "1" },
				{"BILL_DOC_FOOTER","2",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "Note:", null, "1" },
				{"BILL_DOC_FOOTER","3",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "1.Please quote the Invoice / Debit Note No., Billing Acc. No. when making payment.", null, "1" },
				{"BILL_DOC_FOOTER","4",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "2.Due date: within 30 days from the date of issue.", null, "1" },
				{"BILL_DOC_FOOTER","5",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "3.This is a computer generated invoice. No signature is required.", null, "1" },
				{"BILL_DOC_FOOTER","6",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "4.Payment by remittance:", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "sysadmin", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "address", "",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "0", "0", null, "3", "6", "9", "0", "0", "0",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","1"},
				{ "PL-211-I0011-I0178  ", "2", "0", "0", null, "3", "6", "9", "0", "0", "0",
							"201", "2217", "114", "0", "0", TEST_DAY_YESTERDAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","S","1"}};
		
		super.deleteAllData("T_BIL_D");
		super.insertData("T_BIL_D", dataT_BIL_D);
		
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
        super.insertData("M_USER", dataM_USER);
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
    }
    
    /**
	 * SYSTEMBASE = MY 
	 * billingType = others
	 * IS_MANUAL = 1
	 */
    public void testExecute10(){
    	this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "MY", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "OT", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "sysadmin", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "address", "",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "0",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","1"},
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "0",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","S","0"}};
		
		super.deleteAllData("T_BIL_D");
		super.insertData("T_BIL_D", dataT_BIL_D);
		
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
        super.insertData("M_USER", dataM_USER);
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result  = null;
		try{
			result = G_BTH_P01.execute(param, outputDTO);
		}catch (Exception e) {
		}
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
    }
    
    /**
	 * SYSTEMBASE = MY 
	 * billingType = IN
	 * IS_MANUAL = 1
	 * conditions.get("NO_PRINTED") > totalAllowedPrintDoc  printed = 0
	 */
    public void testExecute11(){
    	this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "0", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "MY", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "IN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "sysadmin", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "address", "",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "1", "0", null, "3", "6", "9", "0", "0", "0",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","0"},
				{ "PL-211-I0011-I0178  ", "2", "1", "0", null, "3", "6", "9", "0", "0", "0",
							"201", "2217", "114", "0", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","O","1"}};
		
		super.deleteAllData("T_BIL_D");
		super.insertData("T_BIL_D", dataT_BIL_D);
		
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
        super.insertData("M_USER", dataM_USER);
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result  = null;
		try{
			result = G_BTH_P01.execute(param, outputDTO);
		}catch (Exception e) {
		}
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
    }
    
    /**
	 * SYSTEMBASE = MY 
	 * billingType = IN
	 * IS_MANUAL = 0  and IS_DISPLAY=0 and ITEM_LEVEL = 0
	 * BILL_CURRENCY = others
	 * FooterInfo is not null
	 */
    public void testExecute12(){
    	this.deleteAllData("M_GSET_D");
		String[][] mGsetDData = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{"NOPRINTDOC","1",
				"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
				"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "99", null, "1" },
				{"GST","1",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "7", null, "1" },
				{"BILL_DOC_FOOTER","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", null, null, "1" },
				{"SYSTEMBASE","1",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "MY", null, "1" },
				{"BILL_DOC_FOOTER","2",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "Note:", null, "1" },
				{"BILL_DOC_FOOTER","3",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "1.Please quote the Invoice / Debit Note No., Billing Acc. No. when making payment.", null, "1" },
				{"BILL_DOC_FOOTER","4",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "2.Due date: within 30 days from the date of issue.", null, "1" },
				{"BILL_DOC_FOOTER","5",
					"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
					"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "3.This is a computer generated invoice. No signature is required.", null, "1" },
				{"BILL_DOC_FOOTER","6",
						"Value can be MY or SG. Certain feature will be enable/disable based on this setting.",
						"0", "2010-11-24 16:50:44", "2010-11-24 16:50:44","sysadmin", "4.Payment by remittance:", null, "1" }};
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
				{ "PL-211-I0011-I0178  ", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "sysadmin", "30 Days",
						"OTH", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "address", "",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
		
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		
		String[][] dataT_BIL_D = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT" , "ITEM_CAT",
						"ITEM_TYPE", "IS_DISPLAY_MIN_SVC"},
				{ "PL-211-I0011-I0178  ", "1", "0", "0", null, "3", "6", "9", "0", "0", "0",
						"201", "2216", "114", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "system", null, null ,"1","S","1"},
				{ "PL-211-I0011-I0178  ", "2", "0", "0", null, "3", "6", "9", "0", "0", "0",
							"201", "2217", "114", "0", "0", TEST_DAY_YESTERDAY,
							TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
							TEST_DAY_TODAY, "system", null, null ,"1","S","0"}};
		
		super.deleteAllData("T_BIL_D");
		super.insertData("T_BIL_D", dataT_BIL_D);
		
		String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
        super.insertData("M_USER", dataM_USER);
		
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
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		
		idRefs = new String[]{"PL-211-I0011-I0178  ","PL-211-I0011-I0174  "};
		param.setIdRefs(idRefs);
		GlobalProcessResult result = G_BTH_P01.execute(param, outputDTO);
		assertEquals(true,result.getErrors().isEmpty());
		assertEquals(true,result.getMessages().isEmpty());
		assertEquals(true,result.getFile().isFile());
		assertEquals(".zip",result.getFile().getName().substring(result.getFile().getName().lastIndexOf(".")));
    }
}
