package nttdm.bsys.r_bac.blogic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_bac.dto.R_BAC_R02Input;
import nttdm.bsys.r_bac.dto.R_BAC_R02Output;

public class R_BAC_R02BLogic_Test extends AbstractUtilTest{
	
	R_BAC_R02BLogic logic = null;
	R_BAC_R02Input input = null;
	R_BAC_R02Output output = null;
	BLogicResult result = null;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected void setUpData() throws Exception {
		logic = new R_BAC_R02BLogic();
		logic.setQueryDAO(queryDAO);
		input = new R_BAC_R02Input();
		input.setAccessType("2");
	}
	/**
	 * 
	 */
	public void testExecute01(){
		input.setCustId("noRecordList");
		input.setDoSearch("Y");
		result = logic.execute(input);
		output = (R_BAC_R02Output)result.getResultObject();
		
		assertEquals( new Integer(0) , output.getTotalRow());
		assertEquals( new Integer(0) , output.getStartIndex());
		assertEquals( "2" , output.getAccessType());
		assertEquals( new Integer(20) , output.getRow());
		assertEquals("info.ERR2SC002", result.getMessages().get().next().getKey());
		assertEquals("success",result.getResultString() );
	}
	
	/**
	 * 
	 */
	public void testExecute02(){
		
		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		super.deleteAllData("T_CSB_D");
		super.deleteAllData("T_CSB_H");
		super.deleteAllData("M_CUST");
		
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
		
		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "CQ", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "1000" }, };
		super.insertData("T_BAC_H", testDataTBatchLog5);
		
		String[][] tBilHDataIN = {
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
						"30 Days", "MYR", "6", "0", "100", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHDataIN);
		String[][] tBilHDataDN = {
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
				{ "2", "DN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "900", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHDataDN);
		String[][] tBilHDataCN = {
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
				{ "3", "CN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "300", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHDataCN);
		
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
						"2011-07-07", "BIF001", "500", "3211", "2267", null,
						"2", "0", "0", "2011-08-30", "1111", "0" } };
		this.insertData("T_CSB_H", dataT_CSB_H);
		
		result = logic.execute(input);
		output = (R_BAC_R02Output)result.getResultObject();
		
		List<Map<String, Object>> resultList = output.getResultList();
		assertEquals( 1 , resultList.size());
		
		Map<String, Object> resultMap = resultList.get(0);
		assertEquals( "800" , resultMap.get("VARIANCE_AMOUNT").toString());
		assertEquals( "200" , resultMap.get("ACTUAL_AMOUNT").toString());
		assertEquals( "1000" , resultMap.get("TOTAL_AMT_DUE").toString());
		
		assertEquals( new Integer(1) , output.getTotalRow());
		assertEquals( new Integer(0) , output.getStartIndex());
		assertEquals( "2" , output.getAccessType());
		assertEquals( new Integer(20) , output.getRow());
		assertEquals("success",result.getResultString() );
	}
}
