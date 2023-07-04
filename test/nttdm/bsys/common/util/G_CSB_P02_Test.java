/**
 * @(#)G_CSB_P02_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/30
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.bean.G_CSB_P02_Input;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class G_CSB_P02_Test extends AbstractUtilTest {
	
	private G_CSB_P02 gCsbP02;
	BillingSystemUserValueObject uvo = null;
	
	@Override
	protected void setUpData() throws Exception {
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		uvo.setUser_name("sysadmin");
		gCsbP02 = new G_CSB_P02(this.queryDAO,this.updateDAO,uvo);
	}
	
	/**
	 * case 1:test the execute method<br>
	 * condition:<br>
	 * delete all data and insert new data <br>
	 * return:BLogicResult <br>
	 * @throws Exception
	 */
	public void testExecute1() throws Exception{
		
		String receiptNo = "11RC000040          ";
		String idRef1 = "1                   ";
		String idRef2 = "2                   ";
		
		/**
		 * delete all data
		 */
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		String[][] dataT_CSB_H ={{
			"RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
			"PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
			"REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
			"IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
			"ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
			"ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
			"REJECT_DATE","REJECT_DESC"},
			{
				receiptNo,"229743","0","aaaa",
				"CQ","2011-07-01","100000","VND",
				"aaa","N","0123456789.","0123456789.",
				"0","0","2011-07-07","2011-07-07",
				"USERFULL","650","1","650",
				null,"0","0","0",
				"2011-08-30","1111"}};
		
		String[][] dataT_CSB_D ={{
			"ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
			"ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
			"FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
				{
				idRef1,"0",TEST_DAY_TODAY,TEST_DAY_TODAY,
				"USERFULL",receiptNo,"0","200",
				"356","5",null}};
		
		String[][] dataT_CSB_D1 ={{
			"ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
			"ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
			"FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
				{
				idRef2,"0",TEST_DAY_TODAY,TEST_DAY_TODAY,
				"USERFULL",receiptNo,"0","200",
				"356","5",null}};
		
		String[][] dataT_BIL_H ={{
			"ID_REF","BILL_TYPE","IS_MANUAL","BILL_ACC",
			"DATE_REQ","PAY_METHOD","ID_CUST","ADR_TYPE",
			"CONTACT_TYPE","ID_BIF","ID_QCS","QUO_REF",
			"CUST_PO","ID_CONSULT","TERM","BILL_CURRENCY",
			"GST_PERCENT","GST_AMOUNT","BILL_AMOUNT","PAID_AMOUNT",
			"LAST_PAYMENT_DATE","IS_SETTLED","IS_SINGPOST","IS_EXPORT",
			"IS_DISPLAY_EXP_AMT","EXPORT_CUR","CUR_RATE","EXPORT_AMOUNT",
			"FIXED_FOREX","NO_PRINTED","DATE_PRINTED","USER_PRINTED",
			"IS_CLOSED","ADDRESS1","ADDRESS2","ADDRESS3",
			"ADDRESS4","ZIP_CODE","COUNTRY","TEL_NO",
			"FAX_NO","CONTACT_NAME","CONTACT_EMAIL","IS_DELETED",
			"PREPARED_BY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{idRef1,"IN","1","0",
				"2011-01-05","CQ                  ","229743","BA",
				"PC",null,null,null,
				null,"BBBBBBBBBB","30 Days","MYR",
				"5","1","606","0",
				TEST_DAY_TODAY,"0","0","0",
				"1","MYR","3.124","606",
				"0","1","2011-01-05","joeykan",
				"0","123 Nguyen Thi Minh Khai","District 1","Ho Chi Minh City",
				"+84, VN","+84","Viet Nam","0123456789",
				"0123456789","Duy Duong","duong.nguyen@nttdata.com.vn","0",
				"w.h.wong",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		
		String[][] dataT_BIL_H1 ={{
			"ID_REF","BILL_TYPE","IS_MANUAL","BILL_ACC",
			"DATE_REQ","PAY_METHOD","ID_CUST","ADR_TYPE",
			"CONTACT_TYPE","ID_BIF","ID_QCS","QUO_REF",
			"CUST_PO","ID_CONSULT","TERM","BILL_CURRENCY",
			"GST_PERCENT","GST_AMOUNT","BILL_AMOUNT","PAID_AMOUNT",
			"LAST_PAYMENT_DATE","IS_SETTLED","IS_SINGPOST","IS_EXPORT",
			"IS_DISPLAY_EXP_AMT","EXPORT_CUR","CUR_RATE","EXPORT_AMOUNT",
			"FIXED_FOREX","NO_PRINTED","DATE_PRINTED","USER_PRINTED",
			"IS_CLOSED","ADDRESS1","ADDRESS2","ADDRESS3",
			"ADDRESS4","ZIP_CODE","COUNTRY","TEL_NO",
			"FAX_NO","CONTACT_NAME","CONTACT_EMAIL","IS_DELETED",
			"PREPARED_BY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{idRef2,"IN","1","0",
				"2011-01-26","CQ                  ","229743","BA",
				"PC",null,null,null,
				null,"BBBBBBBBBB","30 Days","MYR",
				"5","1","100","0",
				TEST_DAY_TODAY,"0","0","0",
				"1","MYR","3.124","606",
				"0","1","2011-01-05","joeykan",
				"0","123 Nguyen Thi Minh Khai","District 1","Ho Chi Minh City",
				"+84, VN","+84","Viet Nam","0123456789",
				"0123456789","Duy Duong","duong.nguyen@nttdata.com.vn","0",
				"w.h.wong",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		
		/**
		 * insert data
		 */
		super.insertData("T_CSB_H", dataT_CSB_H);
		super.insertData("T_BIL_H", dataT_BIL_H);
		super.insertData("T_BIL_H", dataT_BIL_H1);
		super.insertData("T_CSB_D", dataT_CSB_D);
		super.insertData("T_CSB_D", dataT_CSB_D1);
		
		//paramter
		G_CSB_P02_Input input = new G_CSB_P02_Input();
		input.setIdBillAccount("0");
		
		gCsbP02.execute(input);
		
		// get T_CSB_H Info by Primary
		Map<String, Object> mapTCsbHInfo = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL009",receiptNo.trim());
		assertEquals("0",mapTCsbHInfo.get("BALANCE_AMT").toString());
		
		// get T_BIL_H Info by Primary
		String idRef = idRef1.trim();
		Map<String, Object> mapTBilHInfo = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL010",idRef);
		assertEquals("606",mapTBilHInfo.get("PAID_AMOUNT").toString());
		assertEquals("1",mapTBilHInfo.get("IS_SETTLED").toString());
		
		// get T_BIL_H Info by Primary
		idRef = idRef2.trim();
		Map<String, Object> mapTBilHInfo1 = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL010",idRef);
		assertEquals("44",mapTBilHInfo1.get("PAID_AMOUNT").toString());
		assertEquals("0",mapTBilHInfo1.get("IS_SETTLED").toString());
		
		// T_CSB_D condition
		Map<String, Object> tCsbDCondition = new HashMap<String, Object>();
		idRef = idRef1.trim();
		// ID_REF
		tCsbDCondition.put("idRef", idRef);
		// RECEIPT_NO
		tCsbDCondition.put("receiptNo", receiptNo.trim());
		// get T_CSB_D Info by Primary
		Map<String, Object> mapTCsbDInfo = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL011",
						tCsbDCondition);
		assertEquals("806",mapTCsbDInfo.get("AMT_PAID").toString());
		
		tCsbDCondition = new HashMap<String, Object>();
		idRef = idRef2.trim();
		// ID_REF
		tCsbDCondition.put("idRef", idRef);
		// RECEIPT_NO
		tCsbDCondition.put("receiptNo", receiptNo.trim());
		// get T_CSB_D Info by Primary
		Map<String, Object> mapTCsbDInfo1 = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL011",
						tCsbDCondition);
		assertEquals("244",mapTCsbDInfo1.get("AMT_PAID").toString());
	}
	
	/**
	 * case 2:test the execute method<br>
	 * condition:<br>
	 * delete all data and insert new data <br>
	 * return:BLogicResult <br>
	 * @throws Exception
	 */
	public void testExecute2() throws Exception{
		String receiptNo = "11RC000040          ";
		String idRef1 = "1                   ";
		String idRef2 = "2                   ";
		
		
		/**
		 * delete all data
		 */
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		String[][] dataT_CSB_H ={{
			"RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
			"PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
			"REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
			"IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
			"ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
			"ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
			"REJECT_DATE","REJECT_DESC"},
			{
				receiptNo,"229743","0","aaaa",
				"CQ","2011-07-01","100000","VND",
				"aaa","N","0123456789.","0123456789.",
				"0","0","2011-07-07","2011-07-07",
				"USERFULL","650","1","650",
				null,"0","0","0",
				"2011-08-30","1111"}};
		
		String[][] dataT_BIL_H ={{
			"ID_REF","BILL_TYPE","IS_MANUAL","BILL_ACC",
			"DATE_REQ","PAY_METHOD","ID_CUST","ADR_TYPE",
			"CONTACT_TYPE","ID_BIF","ID_QCS","QUO_REF",
			"CUST_PO","ID_CONSULT","TERM","BILL_CURRENCY",
			"GST_PERCENT","GST_AMOUNT","BILL_AMOUNT","PAID_AMOUNT",
			"LAST_PAYMENT_DATE","IS_SETTLED","IS_SINGPOST","IS_EXPORT",
			"IS_DISPLAY_EXP_AMT","EXPORT_CUR","CUR_RATE","EXPORT_AMOUNT",
			"FIXED_FOREX","NO_PRINTED","DATE_PRINTED","USER_PRINTED",
			"IS_CLOSED","ADDRESS1","ADDRESS2","ADDRESS3",
			"ADDRESS4","ZIP_CODE","COUNTRY","TEL_NO",
			"FAX_NO","CONTACT_NAME","CONTACT_EMAIL","IS_DELETED",
			"PREPARED_BY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{idRef1,"IN","1","0",
				"2011-01-05","CQ                  ","229743","BA",
				"PC",null,null,null,
				null,"BBBBBBBBBB","30 Days","MYR",
				"5","1","606","0",
				TEST_DAY_TODAY,"0","0","0",
				"1","MYR","3.124","606",
				"0","1","2011-01-05","joeykan",
				"0","123 Nguyen Thi Minh Khai","District 1","Ho Chi Minh City",
				"+84, VN","+84","Viet Nam","0123456789",
				"0123456789","Duy Duong","duong.nguyen@nttdata.com.vn","0",
				"w.h.wong",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		
		String[][] dataT_BIL_H1 ={{
			"ID_REF","BILL_TYPE","IS_MANUAL","BILL_ACC",
			"DATE_REQ","PAY_METHOD","ID_CUST","ADR_TYPE",
			"CONTACT_TYPE","ID_BIF","ID_QCS","QUO_REF",
			"CUST_PO","ID_CONSULT","TERM","BILL_CURRENCY",
			"GST_PERCENT","GST_AMOUNT","BILL_AMOUNT","PAID_AMOUNT",
			"LAST_PAYMENT_DATE","IS_SETTLED","IS_SINGPOST","IS_EXPORT",
			"IS_DISPLAY_EXP_AMT","EXPORT_CUR","CUR_RATE","EXPORT_AMOUNT",
			"FIXED_FOREX","NO_PRINTED","DATE_PRINTED","USER_PRINTED",
			"IS_CLOSED","ADDRESS1","ADDRESS2","ADDRESS3",
			"ADDRESS4","ZIP_CODE","COUNTRY","TEL_NO",
			"FAX_NO","CONTACT_NAME","CONTACT_EMAIL","IS_DELETED",
			"PREPARED_BY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{idRef2,"IN","1","0",
				"2011-01-26","CQ                  ","229743","BA",
				"PC",null,null,null,
				null,"BBBBBBBBBB","30 Days","MYR",
				"5","1","100","0",
				TEST_DAY_TODAY,"0","0","0",
				"1","MYR","3.124","606",
				"0","1","2011-01-05","joeykan",
				"0","123 Nguyen Thi Minh Khai","District 1","Ho Chi Minh City",
				"+84, VN","+84","Viet Nam","0123456789",
				"0123456789","Duy Duong","duong.nguyen@nttdata.com.vn","0",
				"w.h.wong",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		
		/**
		 * insert data
		 */
		super.insertData("T_CSB_H", dataT_CSB_H);
		super.insertData("T_BIL_H", dataT_BIL_H);
		super.insertData("T_BIL_H", dataT_BIL_H1);
		
		//paramter
		G_CSB_P02_Input input = new G_CSB_P02_Input();
		input.setIdBillAccount("0");
		
		gCsbP02.execute(input);
		
		// get T_CSB_H Info by Primary
		Map<String, Object> mapTCsbHInfo = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL009",receiptNo.trim());
		assertEquals("0",mapTCsbHInfo.get("BALANCE_AMT").toString());
		
		// get T_BIL_H Info by Primary
		String idRef = idRef1.trim();
		Map<String, Object> mapTBilHInfo = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL010",idRef);
		assertEquals("606",mapTBilHInfo.get("PAID_AMOUNT").toString());
		assertEquals("1",mapTBilHInfo.get("IS_SETTLED").toString());
		
		// get T_BIL_H Info by Primary
		idRef = idRef2.trim();
		Map<String, Object> mapTBilHInfo1 = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL010",idRef);
		assertEquals("44",mapTBilHInfo1.get("PAID_AMOUNT").toString());
		assertEquals("0",mapTBilHInfo1.get("IS_SETTLED").toString());
		
		// T_CSB_D condition
		Map<String, Object> tCsbDCondition = new HashMap<String, Object>();
		idRef = idRef1.trim();
		// ID_REF
		tCsbDCondition.put("idRef", idRef);
		// RECEIPT_NOF
		tCsbDCondition.put("receiptNo", receiptNo.trim());
		// get T_CSB_D Info by Primary
		Map<String, Object> mapTCsbDInfo = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL011",
						tCsbDCondition);
		assertEquals("606",mapTCsbDInfo.get("AMT_PAID").toString());
		assertEquals("606",mapTCsbDInfo.get("AMT_DUE").toString());
		assertEquals("0",mapTCsbDInfo.get("IS_DELETED").toString());
		
		tCsbDCondition = new HashMap<String, Object>();
		idRef = idRef2.trim();
		// ID_REF
		tCsbDCondition.put("idRef", idRef);
		// RECEIPT_NO
		tCsbDCondition.put("receiptNo", receiptNo.trim());
		// get T_CSB_D Info by Primary
		Map<String, Object> mapTCsbDInfo1 = this.queryDAO
				.executeForMap("SELECT.BSYS.G_CSB_P02.SQL011",
						tCsbDCondition);
		assertEquals("44",mapTCsbDInfo1.get("AMT_PAID").toString());
		assertEquals("100",mapTCsbDInfo1.get("AMT_DUE").toString());
		assertEquals("0",mapTCsbDInfo1.get("IS_DELETED").toString());
	}
	
	/**
     * case 2:test the execute method<br>
     * condition:<br>
     * delete all data and insert new data <br>
     * return:BLogicResult <br>
     * @throws Exception
     */
    public void testExecute3() throws Exception{
        String receiptNo = "11RC000040          ";
        String idRef1 = "1                   ";
        String idRef2 = "2                   ";
        
        
        /**
         * delete all data
         */
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        String[][] dataT_BIL_H ={{
            "ID_REF","BILL_TYPE","IS_MANUAL","BILL_ACC",
            "DATE_REQ","PAY_METHOD","ID_CUST","ADR_TYPE",
            "CONTACT_TYPE","ID_BIF","ID_QCS","QUO_REF",
            "CUST_PO","ID_CONSULT","TERM","BILL_CURRENCY",
            "GST_PERCENT","GST_AMOUNT","BILL_AMOUNT","PAID_AMOUNT",
            "LAST_PAYMENT_DATE","IS_SETTLED","IS_SINGPOST","IS_EXPORT",
            "IS_DISPLAY_EXP_AMT","EXPORT_CUR","CUR_RATE","EXPORT_AMOUNT",
            "FIXED_FOREX","NO_PRINTED","DATE_PRINTED","USER_PRINTED",
            "IS_CLOSED","ADDRESS1","ADDRESS2","ADDRESS3",
            "ADDRESS4","ZIP_CODE","COUNTRY","TEL_NO",
            "FAX_NO","CONTACT_NAME","CONTACT_EMAIL","IS_DELETED",
            "PREPARED_BY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {idRef1,"IN","1","0",
                "2011-01-05","CQ                  ","229743","BA",
                "PC",null,null,null,
                null,"BBBBBBBBBB","30 Days","MYR",
                "5","1","606","0",
                TEST_DAY_TODAY,"0","0","0",
                "1","MYR","3.124","606",
                "0","1","2011-01-05","joeykan",
                "0","123 Nguyen Thi Minh Khai","District 1","Ho Chi Minh City",
                "+84, VN","+84","Viet Nam","0123456789",
                "0123456789","Duy Duong","duong.nguyen@nttdata.com.vn","0",
                "w.h.wong",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        
        String[][] dataT_BIL_H1 ={{
            "ID_REF","BILL_TYPE","IS_MANUAL","BILL_ACC",
            "DATE_REQ","PAY_METHOD","ID_CUST","ADR_TYPE",
            "CONTACT_TYPE","ID_BIF","ID_QCS","QUO_REF",
            "CUST_PO","ID_CONSULT","TERM","BILL_CURRENCY",
            "GST_PERCENT","GST_AMOUNT","BILL_AMOUNT","PAID_AMOUNT",
            "LAST_PAYMENT_DATE","IS_SETTLED","IS_SINGPOST","IS_EXPORT",
            "IS_DISPLAY_EXP_AMT","EXPORT_CUR","CUR_RATE","EXPORT_AMOUNT",
            "FIXED_FOREX","NO_PRINTED","DATE_PRINTED","USER_PRINTED",
            "IS_CLOSED","ADDRESS1","ADDRESS2","ADDRESS3",
            "ADDRESS4","ZIP_CODE","COUNTRY","TEL_NO",
            "FAX_NO","CONTACT_NAME","CONTACT_EMAIL","IS_DELETED",
            "PREPARED_BY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {idRef2,"IN","1","0",
                "2011-01-26","CQ                  ","229743","BA",
                "PC",null,null,null,
                null,"BBBBBBBBBB","30 Days","MYR",
                "5","1","100","0",
                TEST_DAY_TODAY,"0","0","0",
                "1","MYR","3.124","606",
                "0","1","2011-01-05","joeykan",
                "0","123 Nguyen Thi Minh Khai","District 1","Ho Chi Minh City",
                "+84, VN","+84","Viet Nam","0123456789",
                "0123456789","Duy Duong","duong.nguyen@nttdata.com.vn","0",
                "w.h.wong",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        
        super.insertData("T_BIL_H", dataT_BIL_H);
        super.insertData("T_BIL_H", dataT_BIL_H1);
        
        //paramter
        G_CSB_P02_Input input = new G_CSB_P02_Input();
        input.setIdBillAccount("0");
        
        gCsbP02.execute(input);
        
        // get T_BIL_H Info by Primary
        String idRef = idRef1.trim();
        Map<String, Object> mapTBilHInfo = this.queryDAO
                .executeForMap("SELECT.BSYS.G_CSB_P02.SQL010",idRef);
        assertEquals("0",mapTBilHInfo.get("PAID_AMOUNT").toString());
        assertEquals("0",mapTBilHInfo.get("IS_SETTLED").toString());
    }
}
