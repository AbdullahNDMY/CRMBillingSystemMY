/**
 * @(#)RP_B_BIL_S02_01BLogic_Test.java
 * 
 * Billing System NTTS
 * 
 * Version 1.00
 * 
 * Created 2011/08/24
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_01Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.b_bil.blogic.RP_B_BIL_S02_01BLogic
 * 
 * @author i-jankw
 * 
 */
public class RP_B_BIL_S02_01BLogic_Test extends AbstractUtilTest {

	RP_B_BIL_S02_01BLogic testObject = new RP_B_BIL_S02_01BLogic();
	RP_B_BIL_S02_01Input param = null;
	BillingSystemUserValueObject uvo = null;

	@Override
	protected void setUpData() throws Exception {

	}

	/**
	 * case 1:test the execute method<br>
	 * condition:<br>
	 * delete all data and insert new data <br>
	 * return:BLogicResult <br>
	 * @throws Exception
	 */
	public void testExecute() throws Exception {
		//insert data
		insertData();
		
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		param = new RP_B_BIL_S02_01Input();
		testObject.setQueryDAO(this.queryDAO);
		param.setIdRef("2                   ");
		param.setUvo(uvo);

		BLogicResult result = null;
		try {
			result = testObject.execute(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RP_B_BIL_S02_01Output outputDTO = (RP_B_BIL_S02_01Output) result
				.getResultObject();
		HashMap<String, Object> headerInfo = outputDTO.getHeaderInfo();
		List<HashMap<String, Object>> detailInfo = outputDTO.getDetailInfo();
		List<Map<String, Object>> footerInfo = outputDTO.getFooterInfo();
		String userAccess = outputDTO.getAccessType();

		assertEquals("2000 , Australia", headerInfo.get("ADDRESS4").toString());
		assertEquals("30 Days", detailInfo.get(0).get("TERM").toString());
		assertEquals("2", userAccess);
		assertEquals(
				"Please quote this Invoice / Debit Note No. when making payment",
				footerInfo.get(0).get("SET_VALUE").toString());
		assertEquals("success", result.getResultString());
	}
	
	/**
	 * case 2:test the execute method<br>
	 * condition:<br>
	 * delete all data and insert new data <br>
	 * return:BLogicResult <br>
	 * @throws Exception
	 */
	public void testExecute1() throws Exception {
		//insert data
		insertData1();
		
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		param = new RP_B_BIL_S02_01Input();
		testObject.setQueryDAO(this.queryDAO);
		param.setIdRef("2                   ");
		param.setUvo(uvo);

		BLogicResult result = null;
		try {
			result = testObject.execute(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RP_B_BIL_S02_01Output outputDTO = (RP_B_BIL_S02_01Output) result
				.getResultObject();
		HashMap<String, Object> headerInfo = outputDTO.getHeaderInfo();
		List<HashMap<String, Object>> detailInfo = outputDTO.getDetailInfo();
		List<Map<String, Object>> footerInfo = outputDTO.getFooterInfo();
		String userAccess = outputDTO.getAccessType();

		assertEquals("2000 , Australia", headerInfo.get("ADDRESS4").toString());
		assertEquals("30 Days", detailInfo.get(0).get("TERM").toString());
		assertEquals("2", userAccess);
		assertEquals(
				"Please quote this Invoice / Debit Note No. when making payment",
				footerInfo.get(0).get("SET_VALUE").toString());
		assertEquals("success", result.getResultString());
	}
	
	private void insertData(){
		/**
		 * delete all data
		 */
		super.deleteAllData("M_USER_ACCESS");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");

		/**
		 * references NTTS.M_CUST
		 */
		super.deleteAllData("T_QCS_D");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");
		

		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");

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
				{ "2", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "0",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
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
						TEST_DAY_TODAY, "system", null, null,"0","1" } };
		String[][] dataM_GSET_D = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BILL_DOC_FOOTER",
						"1",
						"",
						"0",
						TEST_DAY_TODAY,
						TEST_DAY_TODAY,
						"USERFULL",
						"Please quote this Invoice / Debit Note No. when making payment",
						null, "1", } };
		String[][] dataM_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "NOPRINTDOC",
                        "1",
                        "",
                        "0",
                        TEST_DAY_TODAY,
                        TEST_DAY_TODAY,
                        "USERFULL",
                        "2",
                        null, "1" } };
		String[][] dataM_GSET_H = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{
						"BILL_DOC_FOOTER",
						"Billing document footer display ",
						"This footer display is applied to Invoice and Debit Note only",
						"0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
		String[][] dataM_GSET_H1 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "NOPRINTDOC",
                        "Billing document footer display ",
                        "This footer display is applied to Invoice and Debit Note only",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };

		String[][] dataM_USER_ACCESS = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "sysadmin", "B", "B-BIL", "2", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "sysadmin", "0", null } };

		/**
		 * insert data
		 */
		super.insertData("T_BIL_H", dataT_BIL_H);
		super.insertData("M_CUST", dataM_CUST);
		super.insertData("T_BIL_D", dataT_BIL_D);

		super.insertData("M_GSET_H", dataM_GSET_H);
		super.insertData("M_GSET_H", dataM_GSET_H1);
		super.insertData("M_GSET_D", dataM_GSET_D);
		super.insertData("M_GSET_D", dataM_GSET_D1);
		super.insertData("M_USER_ACCESS", dataM_USER_ACCESS);
	}
	
	private void insertData1(){
		/**
		 * delete all data
		 */
		super.deleteAllData("M_USER_ACCESS");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");

		/**
		 * references NTTS.M_CUST
		 */
		super.deleteAllData("T_QCS_D");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");
		

		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");

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
				{ "2", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "sysadmin;BIF001", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };
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
						TEST_DAY_TODAY, "system", null, null,"0","1" } };
		String[][] dataM_GSET_D = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BILL_DOC_FOOTER",
						"1",
						"",
						"0",
						TEST_DAY_TODAY,
						TEST_DAY_TODAY,
						"USERFULL",
						"Please quote this Invoice / Debit Note No. when making payment",
						null, "1", } };
		String[][] dataM_GSET_H = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{
						"BILL_DOC_FOOTER",
						"Billing document footer display ",
						"This footer display is applied to Invoice and Debit Note only",
						"0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };

		String[][] dataM_USER_ACCESS = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "sysadmin", "B", "B-BIL", "2", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "sysadmin", "0", null } };

		/**
		 * insert data
		 */
		super.insertData("T_BIL_H", dataT_BIL_H);
		super.insertData("M_CUST", dataM_CUST);
		super.insertData("T_BIL_D", dataT_BIL_D);

		super.insertData("M_GSET_H", dataM_GSET_H);
		super.insertData("M_GSET_D", dataM_GSET_D);
		super.insertData("M_USER_ACCESS", dataM_USER_ACCESS);
	}
}
