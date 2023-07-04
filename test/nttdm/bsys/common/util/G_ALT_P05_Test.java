package nttdm.bsys.common.util;

import oracle.sql.BLOB;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class G_ALT_P05_Test extends AbstractUtilTest {

	private G_ALT_P05 G_ALT_P05 = null;
	private	BillingSystemUserValueObject uvo = null;
	
	@Override
	protected void setUpData() throws Exception {
		G_ALT_P05 = new G_ALT_P05(queryDAO, updateDAO);
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.deleteAllData("T_CUST_PLAN_H");
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
				{ "SD", "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
		assertEquals(null, G_ALT_P05.execute(uvo));
	}
	
	/**
	 * NO_OF_MONTH is 1 and planList.size() <= 0
	 */
	public void testExecute2(){
		// set  record into M_BATCH_MAINTENANCE
		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "SD", "1", "Monthly", "1", null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
		String[][] testDataTBatchLog1 = {
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
		super.insertData("T_CUST_PLAN_H", testDataTBatchLog1);

		String[][] testDataTBatchLog2 = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN" },
				{ "2197", "43", "PS3", "2011-5-11", "2012-12-11", "1", "1",
						"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
						null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
						"USERFULL" } };
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		assertEquals(null, G_ALT_P05.execute(uvo));
	}
	
	/**
	 * NO_OF_MONTH is 1 and planList.size() > 0
	 */
	public void testExecute3(){
		// set  record into M_BATCH_MAINTENANCE
		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "SD", "1", "Monthly", "8", null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
		String[][] testDataTBatchLog1 = {
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
		super.insertData("T_CUST_PLAN_H", testDataTBatchLog1);

		String[][] testDataTBatchLog2 = {
				{ "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
						"SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
						"MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
						"CONTRACT_TERM_NO", "PRO_RATE_BASE",
						"PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN" },
				{ "2197", "43", "PS3", "2011-5-11", "2012-12-11", "1", "1",
						"2011-5-11", "2011-5-11", "M", "23", "0", "25", "0",
						null, "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
						"USERFULL" } };
		super.insertData("T_CUST_PLAN_D", testDataTBatchLog2);
		assertEquals(null, G_ALT_P05.execute(uvo));
	}
	
	public void testExecute4(){
		// set cd batch type record into M_BATCH_MAINTENANCE
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
			
		assertEquals(null, G_ALT_P05.execute(uvo));
	}
}
