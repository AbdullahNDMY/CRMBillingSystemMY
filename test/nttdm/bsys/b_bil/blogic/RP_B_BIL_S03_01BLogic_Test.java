package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_01Input;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

public class RP_B_BIL_S03_01BLogic_Test extends AbstractUtilTest {
	RP_B_BIL_S03_01BLogic logic = null;
	RP_B_BIL_S03_01Input param = null;
	BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();

	@Override
	protected void setUpData() throws Exception {
		logic = new RP_B_BIL_S03_01BLogic();
		logic.setQueryDAO(queryDAO);
		logic.setUpdateDAO(updateDAO);
		param = new RP_B_BIL_S03_01Input();
		param.setIdRef("01234567890123456789");
		uvo.setId_user("sysadmin");
		param.setUvo(uvo);
	}

	public void testExecute1() {
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
				{ "01234567890123456789", "IN", "0", "1", "2012-05-11", "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "9", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "1111",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] T_BIL_D1 = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
				{ "01234567890123456789", "1", "0", "333", null, "3", "3.6", "10.8", "5",
						"1", "1", "801", "999999999", "999999999", "359",
						"130", "2011-01-31 00:00:00", "2011-04-01 00:00:00",
						"1", "0", "2011-08-31 13:44:33", "2011-08-31 13:44:33",
						"sysadmin", null, "1","0","1" } };

		super.insertData("T_BIL_D", T_BIL_D1);
		
		super.deleteAllData("M_CUST");
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
						"2011-07-07 15:02:51", "USERFULL", "", "",
						"2008-01-01", "", "", "0", "0123456789", "0123456789",
						"CORP", "0123456789", "ABC", AUDIT_VALUE, "" }, };
		super.insertData("M_CUST", mCustData);

		BLogicResult result = logic.execute(param);
		assertEquals("success", result.getResultString());
	}
	public void testExecute2() {
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
				{ "01234567890123456789", "IN", "0", "1", "2012-05-11", "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "1000",
						TEST_DAY_TODAY, "0", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "0", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "1111",
						AUDIT_VALUE } };
		super.insertData("T_BIL_H", tBilHData);

		String[][] T_BIL_D1 = {
				{ "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
						"ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
						"APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
						"ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
						"SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
				{ "01234567890123456789", "1", "0", "333", null, "3", "3.6", "10.8", "5",
						"1", "1", "801", "999999999", "999999999", "359",
						"130", "2011-01-31 00:00:00", "2011-04-01 00:00:00",
						"1", "0", "2011-08-31 13:44:33", "2011-08-31 13:44:33",
						"sysadmin", null, "1","0","1" } };

		super.insertData("T_BIL_D", T_BIL_D1);
		
		super.deleteAllData("M_CUST");
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
						"2011-07-07 15:02:51", "USERFULL", "", "",
						"2008-01-01", "", "", "0", "0123456789", "0123456789",
						"CORP", "0123456789", "ABC", AUDIT_VALUE, "" }, };
		super.insertData("M_CUST", mCustData);

		BLogicResult result = logic.execute(param);
		assertEquals("success", result.getResultString());
	}
}
