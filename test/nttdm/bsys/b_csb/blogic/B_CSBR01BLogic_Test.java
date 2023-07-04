/**
 * @(#)B_CSBR01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/02
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_csb.blogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * @author gplai
 *
 */
public class B_CSBR01BLogic_Test extends AbstractUtilTest {

	/**
	 * B_CSBR01BLogic
	 */
	private B_CSBR01BLogic logic ;
	
	/**
	 * the B_CSBR01BLogic's paramter
	 */
	private Map<String, Object> input ;
	
	@Override
	protected void setUpData() throws Exception {
		logic = new B_CSBR01BLogic();
		input = new HashMap<String,Object>();
		logic.setQueryDAO(queryDAO);
		logic.setUpdateDAO(updateDAO);
	}

	@SuppressWarnings("unchecked")
	public void testExecute1(){
		//insert data
		insertData1();
		
		input.put("startDate",null);
		input.put("startIndex",null);
		input.put("receiptNo",null);
		input.put("curCode",null);
		input.put("pmtMethod",null);
		input.put("checkpageview","0");
		input.put("endDate",null);
		input.put("paymentStatus",null);
		input.put("row",null);
		input.put("payer",null);
		input.put("filterBybalance",null);
		input.put("refNo",null);
		input.put("bankAcc",null);
		input.put("totalRow",null);
		
		BLogicResult result = logic.execute(input);
		assertEquals("success", result.getResultString());
		Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
		assertEquals("N", resultObject.get("paymentStatus"));
	}
	
	public void testExecute2(){
		//insert data
		insertData1();
		
		input.put("startDate","10/01/2011");
		input.put("startIndex",null);
		input.put("receiptNo",null);
		input.put("curCode",null);
		input.put("pmtMethod",null);
		input.put("checkpageview","1");
		input.put("endDate","09/01/2011");
		input.put("paymentStatus",null);
		input.put("row",null);
		input.put("payer",null);
		input.put("filterBybalance",null);
		input.put("refNo",null);
		input.put("bankAcc",null);
		input.put("totalRow",null);
		
		BLogicResult result = logic.execute(input);
		Iterator<BLogicMessage> listMessage = result.getErrors().get();
		while(listMessage.hasNext()){
			assertEquals("errors.ERR1SC013", listMessage.next().getKey());
		}
		
		input.put("startDate","aaaa");
		input.put("startIndex",null);
		input.put("receiptNo",null);
		input.put("curCode",null);
		input.put("pmtMethod",null);
		input.put("checkpageview","1");
		input.put("endDate","09/01/2011");
		input.put("paymentStatus",null);
		input.put("row",null);
		input.put("payer",null);
		input.put("filterBybalance",null);
		input.put("refNo",null);
		input.put("bankAcc",null);
		input.put("totalRow",null);
		
		result = logic.execute(input);
		listMessage = result.getErrors().get();
		while(listMessage.hasNext()){
			assertEquals("errors.ERR1SC013", listMessage.next().getKey());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testExecute3(){
		//insert data
		insertData1();
		insertData2();
		
		input.put("startDate",null);
		input.put("startIndex",null);
		input.put("receiptNo",null);
		input.put("curCode","MYR");
		input.put("pmtMethod",null);
		input.put("checkpageview","1");
		input.put("endDate",null);
		input.put("paymentStatus",null);
		input.put("row",null);
		input.put("payer",null);
		input.put("filterBybalance",null);
		input.put("refNo",null);
		input.put("bankAcc",null);
		input.put("totalRow",null);
		
		BLogicResult result = logic.execute(input);
		assertEquals("success", result.getResultString());
		Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
		assertEquals(new Float("5000"), resultObject.get("totalAmt"));
		List<Map<String, Object>> csbInfos = (List<Map<String, Object>>)resultObject.get("csbInfos");
		for(Map map :csbInfos){
			assertEquals("12/04/2011", map.get("DATE_TRANS"));
			assertEquals("Duy Duong", map.get("CUST_NAME"));
			assertEquals("11RC000038          ", map.get("RECEIPT_NO"));
			assertEquals("Cheque No.", map.get("REFERENCE1"));
			assertEquals("MYR", map.get("CUR_CODE"));
			assertEquals(new BigDecimal("5000"), map.get("AMT_RECEIVED"));
			assertEquals(new BigDecimal("4012"), map.get("BALANCE_AMT"));
			assertEquals("CQ", map.get("PMT_METHOD"));
			assertEquals("Bank Money-XYZ-11111", map.get("BANK_ACC"));
			assertEquals("N", map.get("PMT_STATUS"));
		}
	}
	
	
	private void insertData1(){
		/**
		 * delete data
		 */
		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");
		
		String[][] dataM_GSET_D = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"RESULTROW",
						"1",
						"Number of rows of result to be display.",
						"0",
						TEST_DAY_TODAY,
						TEST_DAY_TODAY,
						"sysadmin",
						"30",
						null, "1" } };
		String[][] dataM_GSET_D1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"DEF_CURRENCY",
						"1",
						"Default currency to use in the system.",
						"0",
						TEST_DAY_TODAY,
						TEST_DAY_TODAY,
						"sysadmin",
						"MYR",
						null, "1" } };
		
		String[][] dataM_GSET_H = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{
						"RESULTROW",
						"Row Result Display",
						"Settings to display result.",
						"0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
		String[][] dataM_GSET_H1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{
						"DEF_CURRENCY",
						"Default Currency",
						"System default currency.",
						"0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
		
		/**
		 * insert data
		 */
		super.insertData("M_GSET_H", dataM_GSET_H);
		super.insertData("M_GSET_H", dataM_GSET_H1);
		super.insertData("M_GSET_D", dataM_GSET_D);
		super.insertData("M_GSET_D", dataM_GSET_D1);
	}
	
	private void insertData2(){
		/**
		 * delete data
		 */
		super.deleteAllData("M_COM_BANKINFO");
		super.deleteAllData("M_BANK");
		/**
		 * references NTTS.M_CUST
		 */
		super.deleteAllData("T_QCS_D");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");
		
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		
		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","11","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"398","Bank Money-XYZ","0123","Bank Money",
						"012","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		
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
				{ "22", "data test", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "USERFULL", "", "", null, "", "", "0",
						"0123456789", "0123456789", "CORP", "0123456789", "",
						null, "" } };
		
		String[][] dataT_CSB_H ={{
			"RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
			"PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
			"REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
			"IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
			"ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
			"ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
			"REJECT_DATE","REJECT_DESC"},
			{
				"11RC000038          ","22","0","aaaa",
				"CQ","2011-04-12","4","MYR",
				"85","N","Cheque No.","Bank-In Slip No.",
				"0","0","2011-07-07","2011-07-07",
				"BIF001","5000.00","1.00","4012.00",
				null,"0","0","0",
				"2011-08-30","1111"}};
		
		/**
		 * insert data
		 */
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		super.insertData("M_BANK", dataM_BANK);
		super.insertData("M_CUST", dataM_CUST);
		super.insertData("T_CSB_H", dataT_CSB_H);
	}
	
	/**
	 * count = 0
	 */
	@SuppressWarnings("unchecked")
	public void testExecute4(){
		super.deleteAllData("M_COM_BANKINFO");
		super.deleteAllData("M_BANK");
		/**
		 * references NTTS.M_CUST
		 */
		super.deleteAllData("T_QCS_D");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");
		
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		
		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");
		
		input.put("startDate","10/01/2011");
		input.put("startIndex",null);
		input.put("receiptNo",null);
		input.put("curCode",null);
		input.put("pmtMethod",null);
		input.put("checkpageview","1");
		input.put("endDate","20/01/2011");
		input.put("paymentStatus",null);
		input.put("row",null);
		input.put("payer",null);
		input.put("filterBybalance",null);
		input.put("refNo",null);
		input.put("bankAcc",null);
		input.put("totalRow",null);
		
		BLogicResult result = logic.execute(input);
		Map<String, Object> map = (Map<String, Object>)result.getResultObject();
		assertEquals("0", map.get("startIndex").toString());
		assertEquals(null, map.get("csbInfos"));
		assertEquals(null, map.get("totalAmt"));
		assertEquals("success", result.getResultString());
		assertEquals(false, result.getMessages().isEmpty());
		assertEquals("info.ERR2SC002", result.getMessages().get().next().getKey());
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void testExecute5(){
		//insert data
		insertData1();
		insertData2();
		
		input.put("startDate",null);
		input.put("startIndex","200");
		input.put("receiptNo",null);
		input.put("curCode","MYR");
		input.put("pmtMethod",null);
		input.put("checkpageview","1");
		input.put("endDate",null);
		input.put("paymentStatus",null);
		input.put("row",null);
		input.put("payer",null);
		input.put("filterBybalance",null);
		input.put("refNo",null);
		input.put("bankAcc",null);
		input.put("totalRow",null);
		
		BLogicResult result = logic.execute(input);
		assertEquals("success", result.getResultString());
		assertEquals("0", ((Map<String, Object>)result.getResultObject()).get("startIndex").toString());
	}
	
}
