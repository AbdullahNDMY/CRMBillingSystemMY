/**
 * @(#)G_BIL_P01_Test.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-8-1

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Test Class for nttdm.bsys.common.util.G_BIL_P01<br>
 * SetUp data etc. common process
 * 
 * @author bench
 */
public class G_BIL_P01_Test extends AbstractUtilTest {

	protected static final String GST = "6";
	protected static final String SYSTEMBASE = "SG";
    protected static final String CB_AUTO_OFFSET = "BAC";
	protected static final int BATCH_TIME_INTERVAL = 100;
	protected static final String BATCH_TYPE = "GB";
	protected static final String DEF_CURRENCY = "SGD";

	/**
	 * Custom ID.
	 */
	protected String ID_CUST = "9999";

	/**
	 * M_PLAN_H.ID_PLAN
	 */
	protected String ID_PLAN = "1";

	/**
	 * M_PLAN_D.ID_PLAN_GRP for RATE_MODE=1 - Yearly
	 */
	protected String ID_PLAN_GRP_1 = "1";

	/**
	 * M_PLAN_D.ID_PLAN_GRP for RATE_MODE=2 - Bi Annually
	 */
	protected String ID_PLAN_GRP_2 = "2";

	/**
	 * M_PLAN_D.ID_PLAN_GRP for RATE_MODE=3 - Quarterly
	 */
	protected String ID_PLAN_GRP_3 = "3";

	/**
	 * M_PLAN_D.ID_PLAN_GRP for RATE_MODE=4 - Bi-Monthly
	 */
	protected String ID_PLAN_GRP_4 = "4";

	/**
	 * M_PLAN_D.ID_PLAN_GRP for RATE_MODE=5 - Monthly
	 */
	protected String ID_PLAN_GRP_5 = "5";
	
	/**
	 * M_PLAN_D.ID_PLAN_GRP for RATE_MODE=6 - One Time
	 */
	protected String ID_PLAN_GRP_6 = "6";

    /**
     * M_PLAN_D.ID_PLAN_GRP for RATE_MODE=5 - Monthly Fee with GST
     */
    protected String ID_PLAN_GRP_7 = "7";
    
	/**
	 * T_CUST_PLAN_D.ID_CUST_PLAN = 1
	 */
	protected String ID_CUST_PLAN = "1";

    /**
     * T_CUST_PLAN_D.ID_CUST_PLAN = 2
     */
    protected String ID_CUST_PLAN_2 = "2";

	/**
	 * T_BAC_H.BILL_ACCOUNT
	 */
	protected String BILL_ACCOUNT = "BAC000001";

	/**
	 * DateFormat yyyy-MM-dd
	 */
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	protected G_BIL_P01 gBilP01 = null;

	/**
	 * delete exist data and initialize test data.
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		super.deleteAllData("M_ALT_D");
		super.deleteAllData("M_ALT_H");
        super.deleteAllData("T_BIL_S");
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");

		// delete exist Custom Plan
        super.deleteAllData("T_CUST_PLAN_SVC");
		super.deleteAllData("T_CUST_PLAN_LINK");
		super.deleteAllData("T_CUST_PLAN_D");
		super.deleteAllData("T_CUST_PLAN_H");

		// delete exist M_PLAN_BATCH_MAPPING
        super.deleteAllData("M_PLAN_BATCH_MAPPING");
        // delete exist data in T_CLC_IMP_MONTHLY_SUM
        super.deleteAllData("T_CLC_IMP_MONTHLY_SUM");

		// delete exist Standard Master Plan
		super.deleteAllData("M_PLAN_SVC");
		super.deleteAllData("M_PLAN_D");
		super.deleteAllData("M_PLAN_H");

		// delete exist Billing Account Data
		super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");

        // delete exist Cash Book Data
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");

		this.initGlobalSetting();

		this.initBatchSetting();

		// generate a custom for test.
		this.generateCustomMaster();
		// generate a standard plan
		this.generatePlanMaster();

		// initialize G_BIL_P01
		gBilP01 = new G_BIL_P01(queryDAO, updateDAO);
		gBilP01.setAuditIdModule("B");
		gBilP01.setAuditIdSubModule("B-BAC");
		gBilP01.setAuditReference("BILLACCOUNT0000001");
		gBilP01.setAuditStatus("A101010");
	}

	/**
	 * initialize Global Setting of GST/BATCH_TIME_INTERVAL/DEF_CURRENCY
	 */
	private void initGlobalSetting() {
		// clear exist data
		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");

		// initialize GST
		String[][] mGSETH = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{ "GST", "Government Service Tax",
						"Settings for billing/invoice.", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID } };
		super.insertData("M_GSET_H", mGSETH);

		String[][] mGSETD = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT" },
				{ "GST", "1", "Tax value in %", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, GST, null } };
		super.insertData("M_GSET_D", mGSETD);

		// initialize BATCH_TIME_INTERVAL
		String[][] mGSETH2 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{ "BATCH_TIME_INTERVAL", "Batch Time Interval",
						"Setting for the batch time interval", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID } };
		super.insertData("M_GSET_H", mGSETH2);

		String[][] mGSETD2 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT" },
				{
						"BATCH_TIME_INTERVAL",
						"1",
						"The number of hours before the subsequent batch can be executed",
						"0", TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID,
						"" + BATCH_TIME_INTERVAL, null } };
		super.insertData("M_GSET_D", mGSETD2);

		// initialize Default Currency
		String[][] mGSETH3 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
				{ "DEF_CURRENCY", "Default Currency",
						"System default currency.", "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID } };
		super.insertData("M_GSET_H", mGSETH3);

		String[][] mGSETD3 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT" },
				{ "DEF_CURRENCY", "1",
						"Default currency to use in the system.", "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, DEF_CURRENCY,
						null } };
		super.insertData("M_GSET_D", mGSETD3);
        
        // initialize SYSTEMBASE
        String[][] mGSETH4 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "SYSTEMBASE", "", "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID } };
        super.insertData("M_GSET_H", mGSETH4);

        String[][] mGSETD4 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT" },
                { "SYSTEMBASE", "1", "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, SYSTEMBASE, null } };
        super.insertData("M_GSET_D", mGSETD4);
        
        // initialize CB_AUTO_OFFSET
        String[][] mGSETH5 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "CB_AUTO_OFFSET", "", "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID } };
        super.insertData("M_GSET_H", mGSETH5);

        String[][] mGSETD5 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT" },
                { "CB_AUTO_OFFSET", "1", "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, CB_AUTO_OFFSET, null } };
        super.insertData("M_GSET_D", mGSETD5);
	}

	/**
	 * initialize Batch Setting
	 */
	private void initBatchSetting() {
		// clear exist data
		super.deleteAllData("M_BATCH_USER_ALERT");
		super.deleteAllData("M_BATCH_MAINTENANCE");
		super.deleteAllData("T_SET_BATCH_LOG");
		super.deleteAllData("T_SET_BATCH");

		// set GB batch type record into M_BATCH_MAINTENANCE
		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ BATCH_TYPE, "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", BATCH_TYPE, SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
	}

	/**
	 * Create M_PLAN_H/M_PLAN_D record. Assume that M_SVG/M_SVC record exist.
	 */
	private void generatePlanMaster() {
		String[][] dataPlanH = {
				{ "ID_PLAN", "PLAN_NAME", "PLAN_DESC", 
						"CUSTOMER_TYPE", "BILL_CURRENCY",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ ID_PLAN, "IDC-DRCenter-001", "Disaster Recovery",
						"BOTH", DEF_CURRENCY, "0", TEST_DAY_TODAY,
						TEST_DAY_TODAY, SYS_ADMIN, null } };
		super.insertData("M_PLAN_H", dataPlanH);

		String[][] dataPlanD = {
				{ "ID_PLAN_GRP", "ID_PLAN", "ITEM_TYPE", "SVC_LEVEL1", "SVC_LEVEL2",
				        "RATE", "ITEM_GRP_NAME", "RATE_MODE", "RATE_TYPE", "APPLY_GST",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ ID_PLAN_GRP_1, ID_PLAN, "S", "1", "1", "1", "Yearly Fee", "1",
						"BA", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						SYS_ADMIN, null },
				{ ID_PLAN_GRP_2, ID_PLAN, "S", "1", "1", "1", "Bi-Annually Fee",
						"2", "BA", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						SYS_ADMIN, null },
				{ ID_PLAN_GRP_3, ID_PLAN, "S", "1", "1", "1", "Quarterly Fee", "3",
						"BA", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						SYS_ADMIN, null },
				{ ID_PLAN_GRP_4, ID_PLAN, "S", "1", "1", "1", "Bi-Monthly Fee", "4",
						"BA", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						SYS_ADMIN, null },
				{ ID_PLAN_GRP_5, ID_PLAN, "S", "1", "1", "1", "Monthly Fee", "5",
						"BA", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY,
						SYS_ADMIN, null },
				{ ID_PLAN_GRP_6, ID_PLAN, "S", "1", "1", "1", "One Time", "6", "BA",
						"0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN,
						null },
				{ ID_PLAN_GRP_7, ID_PLAN, "S", "1", "1", "1", "Monthly Fee with GST", "5", "BA",
						"1", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN,
						null } };
		super.insertData("M_PLAN_D", dataPlanD);
	}

	/**
	 * Generate custom master information M_CUST/M_CUST_ADR/M_CUST_CTC record.
	 * 
	 * @return ID_CUST
	 */
	private String generateCustomMaster() {
		// set exist M_CUST.IS_DELETED = 1
		super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL001", null);

		// create new Custom
		ID_CUST = super.queryDAO.executeForObject(
				"TEST.G_BIL_P01.SELECT.SQL005", null, String.class);

        String[][] dataCustM = {
                { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CUSTOMER_TYPE",
                        "CO_REGNO", "SALUTATION", "CUST_ID_NO",
                        "CUST_BIRTH_DATE", "CO_WEBSITE", "CO_EMAIL",
                        "CO_TEL_NO", "CO_FAX_NO", "HOME_TEL_NO", "HOME_FAX_NO",
                        "MOBILE_NO", "INTER_COMP", "PRINT_STATEMENT",
                        "GBOC_ACC_NO", "OTHERS_REF_NO", "SALES_FORCE_ACC_ID",
                        "AFFILIATE_CODE", "IS_EXPORTED", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { ID_CUST, "2BA0303-2917", "AMSB", "CORP", "2BA0303-2917",
                        null, null, null, null, "abc@abc.net.my",
                        "(09) 8283327", "(09) 8283230", "(86) 216699",
                        "(86) 216688", "13564000000", "0", "0", "", "RODOPI",
                        "", "", "0", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM",
                        null } };
        super.insertData("M_CUST", dataCustM);

		String[][] dataCustAddress = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ ID_CUST, "BA",
						"PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
						null, null, "SG", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM",
						"24300", "0", null },
				{ ID_CUST, "RA",
						"PPTSB,24300 Kerteh Kemaman Terengganu Darul Iman, ",
						null, null, "SG", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM",
						null, "0", null } };
		super.insertData("M_CUST_ADR", dataCustAddress);

		String[][] dataCustCTC = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ ID_CUST, "PC", "Hiroshi Shiraga", null, "test@test.com",
						null, null, null, null, TEST_DAY_TODAY, TEST_DAY_TODAY,
						"DM", "0", null } };
		super.insertData("M_CUST_CTC", dataCustCTC);

		return ID_CUST;
	}

    /**
     * generate T_CUST_PLAN_H for the custID
     * 
     * @param custID
     *            ID_CUST
     * @param bi
     *            Billing Instruction
     * @param planStatus
     *            Plan Status
     */
    protected void generateCustPlanH(String custID, String bi, String planStatus) {
        // Create record of CUST_ID in T_CUST_PLAN_H
        this.generateCustPlanH(custID, bi, planStatus, "IN");
    }

    /**
     * generate T_CUST_PLAN_H for the custID
     * 
     * @param custID
     *            ID_CUST
     * @param bi
     *            Billing Instruction
     * @param planStatus
     *            Plan Status
     * @param transType
     *            TRANSACTION_TYPE
     */
    protected void generateCustPlanH(String custID, String bi, String planStatus, String transType) {
        // Create record of CUST_ID in T_CUST_PLAN_H
        String[][] dataCustPlanH = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { ID_CUST_PLAN, custID, planStatus, "SP", "S", transType, ID_PLAN,
                        "1199999", "0", "1", "1", bi, "CA",
                        DEF_CURRENCY, null, null, "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, SYS_ADMIN, null } };

        super.insertData("T_CUST_PLAN_H", dataCustPlanH);
    }
    
	/**
	 * Update T_CUST_PLAN_D.BILL_DESC to not null.
	 * 
	 * @param custPlanGrpID
	 *            ID_CUST_PLAN_GRP
	 */
	protected void updateBILL_DESC(String custPlanGrpID) {
		// update BILL_DESC not null in order to avoid exception
		HashMap<String, Object> param1 = new HashMap<String, Object>();
		param1.put("BILL_DESC", custPlanGrpID + " : BILL_DESC");
		param1.put("ID_CUST_PLAN_GRP", custPlanGrpID);
		super.updateDAO.execute("TEST.G_BIL_P01.UPDATE.SQL007", param1);
	}

	/**
	 * Get the next day of given day.<br>
	 * 
	 * @return next day
	 * @throws Exception
	 */
	protected String getNextDay(String currentDay) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = format.parse(currentDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DATE, 1);
		Date newDay = calendar.getTime();

		return format.format(newDay);
	}
	
	/**
	 * Get last day of given day's month.<br>
	 * 
	 * @param currentDay
	 * @return
	 * @throws Exception
	 */
	protected String getMonthlyLastDay(String currentDay) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = format.parse(currentDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date newDay = calendar.getTime();

		return format.format(newDay);
	}

	/**
	 * Get before last day of given day's month.<br>
	 * 
	 * @param currentDay
	 * @return
	 * @throws Exception
	 */
	protected String getBeforeLastMonthDay(String currentDay) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = format.parse(currentDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date newDay = calendar.getTime();

		return format.format(newDay);
	}

	/**
	 * Get last day of given day's last month.<br>
	 * 
	 * @param currentDay
	 * @return
	 * @throws Exception
	 */
	protected String getLastMonthLastDay(String currentDay) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = format.parse(currentDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date newDay = calendar.getTime();

		return format.format(newDay);
	}
	
	/**
	 * Get before last day of given day's last month.<br>
	 * 
	 * @param currentDay
	 * @return
	 * @throws Exception
	 */
	protected String getLastMonthBeforeLastDay(String currentDay) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = format.parse(currentDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date newDay = calendar.getTime();

		return format.format(newDay);
	}
	/**
	 * Get first day of given day's month.<br>
	 * 
	 * @param currentDay
	 * @return
	 * @throws Exception
	 */
	protected String getMonthFirstDay(String currentDay) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = format.parse(currentDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getMinimum(Calendar.DAY_OF_MONTH));
		Date newDay = calendar.getTime();

		return format.format(newDay);
	}
	
	/**
	 * Get last day of given day's last month.<br>
	 * 
	 * @param currentDay
	 * @return
	 * @throws Exception
	 */
	protected String get2MonthAgoLastDay(String currentDay) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = format.parse(currentDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		calendar.add(Calendar.MONTH, -2);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(calendar.getTime());
		calendar2.set(Calendar.DAY_OF_MONTH,
				calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date newDay = calendar2.getTime();

		return format.format(newDay);
	}
	

    public void testExecuteTEMP() throws Exception {
        assertEquals(1, 1);
    }
}