package nttdm.bsys.common.util;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class G_ALT_P04_Test extends AbstractUtilTest{

	private G_ALT_P04  G_ALT_P04 = null; 
    private	BillingSystemUserValueObject uvo = null;
	@Override
	protected void setUpData() throws Exception {
		G_ALT_P04 = new G_ALT_P04(queryDAO, updateDAO);
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		
		super.deleteAllData("M_CUST");
		super.deleteAllData("M_CUST_BANKINFO");
		super.deleteAllData("M_BATCH_USER_ALERT");
		super.deleteAllData("M_BATCH_MAINTENANCE");
	}

	/**
	 * NO_OF_MONTH is null
	 */
	public void testExecute1(){
		// set GB batch type record into M_BATCH_MAINTENANCE
		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
		assertEquals(null, G_ALT_P04.execute(uvo));
	}
	
	/**
	 * NO_OF_MONTH is 1 and planList.size() <= 0
	 */
	public void testExecute2(){
		// set GB batch type record into M_BATCH_MAINTENANCE
		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", "1", null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
		assertEquals(null, G_ALT_P04.execute(uvo));
	}
	
	/**
	 * 
	 */
	public void testExecute3(){
		// set GB batch type record into M_BATCH_MAINTENANCE
		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", "8", null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "229743", "380", "123-123", "GR-Singapore", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2012-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", null, null } };
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		
		String[][] testDataTBatchLog = {
				{ "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
						"CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
						"INTER_COMP", "IS_EXPORTED", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
						"HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
						"GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
						"SALES_FORCE_ACC_ID", "AFFILIATE_CODE",
						"MOBILE_NO" },
				{ "229743", "0123456789", "Duy Duong", "0123456789",
						"http://www.nttdata.com.vn",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0", "0", "0", "2011-07-07 15:01:59",
						"2011-07-07 15:02:51", "USERFULL", "", "", null, "", "",
						"0", "0123456789", "0123456789", "CORP", "0123456789",
						"", "" }, };
		super.insertData("M_CUST", testDataTBatchLog);
		
		assertEquals(null, G_ALT_P04.execute(uvo));
	}
	
	public void testExecute4(){
		// set GB batch type record into M_BATCH_MAINTENANCE
		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CD", "1", "Monthly", "8", null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
			
		assertEquals(null, G_ALT_P04.execute(uvo));
	}
}
