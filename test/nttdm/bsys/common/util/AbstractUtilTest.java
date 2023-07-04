/**
 * @(#)AbstractUtilTest.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-7-27

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.utlib.spring.DaoTestCase;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.util.DateCommon;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Abstract class for all TestCase class in order to get DAO objects
 * 
 * @author bench
 */
public abstract class AbstractUtilTest extends DaoTestCase {

	/**
	 * system default user id named "system"
	 */
	protected static final String USER_ID = "system";

	/**
     * ID_AUDIT
     */
    protected static final String AUDIT_VALUE = null;
	
	/**
	 * System administrator user id named "sysadmin"
	 */
	protected static final String SYS_ADMIN = "sysadmin";

	/**
	 * Precision for double compare
	 */
	protected static final double DOUBLE_DELTA = 0.000000000001;

	/**
	 * The day before yesterday
	 */
	protected static final String TEST_DAY_BEFORE_YESTERDAY = DateCommon
			.getBeforeYesterdayYYYYMMDD();

	/**
	 * Yesterday
	 */
	protected static final String TEST_DAY_YESTERDAY = DateCommon
			.getYesterdayYYYYMMDD();

	/**
	 * Today
	 */
	protected static final String TEST_DAY_TODAY = DateCommon
			.getTodayYYYYMMDD();

	/**
	 * Tomorrow
	 */
	protected static final String TEST_DAY_TOMORROW = DateCommon
			.getTomorrowYYYYMMDD();

	/**
	 * The day after tomorrow
	 */
	protected static final String TEST_DAY_AFTER_TOMORROW = DateCommon
			.getAfterTomorrowYYYYMMDD();

	/**
	 * Today's yyyyMM
	 */
	protected static final String TEST_DAY_YYYYMM = DateCommon.getTodayYYYYMM();

	/**
	 * Next month's first day
	 */
	protected static final String TEST_DAY_NEXTMONTH_FIRSTDAY = DateCommon
			.getNextMonthFirstDayYYYYMMDD();

	/**
	 * Current months's last day.
	 */
	protected static final String TEST_DAY_CURRENTMONTH_LASTDAY = DateCommon
			.getMonthLastDayYYYYMMDD();
	
	/**
     * Current months's last day.
     */
    protected static final String TEST_DAY_CURRENTMONTH_FIRSTDAY = DateCommon
            .getMonthFirstDayYYYYMMDD();

	/**
	 * Last year's yesterday
	 */
	protected static final String TEST_DAY_LASTYEAR_YESTERDAY = DateCommon
			.getLastYearYesterday();

	/**
	 * Last year's today
	 */
	protected static final String TEST_DAY_LASTYEAR_TODAY = DateCommon
			.getLastYearToday();

	/**
	 * Next year's today
	 */
	protected static final String TEST_DAY_NEXTYEAR_TODAY = DateCommon
			.getNextYearToday();

	/**
	 * Next year's yesterday
	 */
	protected static final String TEST_DAY_NEXTYEAR_YESTERDAY = DateCommon
			.getNextYearYesterday();

	/**
	 * Next year's before yesterday
	 */
	protected static final String TEST_DAY_NEXTYEAR_BEFOREYESTODAY = DateCommon
			.getNextYearBeforeYesterday();

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO1;
	
	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO1;

	/**
	 * updateDAONuked
	 */
	protected UpdateDAOiBatisNuked updateDAONuked;

	/**
	 * Getter of QueryDAO
	 * 
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * Setter of QueryDAO
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 */
	public void setQueryDAO(@Qualifier("queryDAO") QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
//	public void setQueryDAO1(@Qualifier("queryDAO1") QueryDAO queryDAO1) {
//		this.queryDAO1 = queryDAO1;
//	}

	/**
	 * Getter of UpdateDAO
	 * 
	 * @return UpdateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * Setter of UpdateDAO
	 * 
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public void setUpdateDAO(@Qualifier("updateDAO") UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	
//	public void setUpdateDAO1(@Qualifier("updateDAO1") UpdateDAO updateDAO1) {
//		this.updateDAO1 = updateDAO1;
//	}

	/**
	 * Getter of UpdateDAOiBatisNuked
	 * 
	 * @return updateDAONuked UpdateDAOiBatisNuked
	 */
	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	/**
	 * Setter of UpdateDAOiBatisNuked
	 * 
	 * @param updateDAONuked
	 *            UpdateDAOiBatisNuked
	 */
	public void setUpdateDAONuked(
			@Qualifier("updateDAONuked") UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

	/**
	 * Get Configuration XML files
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#doGetConfigLocations()
	 */
	@Override
	protected String[] doGetConfigLocations() {
		return new String[] { "dao-test.xml"};
	}

	/**
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#cleanUpData()
	 */
	@Override
	protected void cleanUpData() throws Exception {
	}
}
