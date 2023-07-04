package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_02Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_02Output;
import nttdm.bsys.common.util.AbstractUtilTest;

public class RP_B_BIL_S01_02BLogic_Test extends AbstractUtilTest {
	RP_B_BIL_S01_02BLogic logic = null;
	RP_B_BIL_S01_02Input param = null;
	RP_B_BIL_S01_02Output outputDTO = null;
	
	@Override
	protected void setUpData() throws Exception {
		logic = new RP_B_BIL_S01_02BLogic();
		logic.setQueryDAO(queryDAO);
		logic.setUpdateDAO(updateDAO);
		param = new RP_B_BIL_S01_02Input();
		param.setStartIndex(0);
		param.setTbxBillingReference("1");
		//param.setTbxCustomerName("Duy Duong");
		//param.setTbxReference("00");
		param.setCboTransactionType("IN");
		param.setTbxBillingAccountNo("1");
		param.setTbxBillingDateFrom("30/04/2012");
		param.setTbxBillingDateTo("02/05/2012");
		param.setCboDocumentStatus("0");
		//param.setCboSettlementStatus("Outstanding");
	}
	
	public void testExecute1(){
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
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
				{ "1", "IN", "0", "1", "2012-05-01", "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
						"0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);
		
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
						"2011-07-07 15:02:51", "USERFULL", "", "", "2008-01-01", "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"ABC", AUDIT_VALUE, "" }, };
		super.insertData("M_CUST", mCustData);
		RP_B_BIL_S01_02Output outputDTO;
		BLogicResult result = logic.execute(param);
		outputDTO = (RP_B_BIL_S01_02Output)result.getResultObject();
		assertEquals("success", result.getResultString());
		assertEquals("1", outputDTO.getTotalRow().toString());
		assertEquals(1, outputDTO.getListReport().size());
	}
	
	public void testExecute2(){
		param.setStartIndex(2);
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
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
				{ "1", "IN", "0", "1", "2012-05-01", "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
						"0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);
		
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
						"2011-07-07 15:02:51", "USERFULL", "", "", "2008-01-01", "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"ABC", AUDIT_VALUE, "" }, };
		super.insertData("M_CUST", mCustData);
		RP_B_BIL_S01_02Output outputDTO;
		BLogicResult result = logic.execute(param);
		outputDTO = (RP_B_BIL_S01_02Output)result.getResultObject();
		assertEquals("success", result.getResultString());
		assertEquals("1", outputDTO.getTotalRow().toString());
		assertEquals(1, outputDTO.getListReport().size());
	}
}
