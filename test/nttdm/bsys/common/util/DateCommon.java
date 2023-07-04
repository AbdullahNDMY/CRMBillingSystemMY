/**
 * @(#)DateCommon.java
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

/**
 * Common functions of Date
 * 
 * @author bench
 */
public class DateCommon {

	/**
	 * Get today's formatted string of yyyyMM
	 * 
	 * @return today's yyyyMM string
	 */
	public static String getTodayYYYYMM() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date today = new Date();
		return format.format(today);
	}

	/**
	 * Get today's formatted string
	 * 
	 * @return today's yyyy-MM-dd string
	 */
	public static String getTodayYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		return format.format(today);
	}

	/**
	 * Get tomorrow's formatted string
	 * 
	 * @return tomorrow's yyyy-MM-dd string
	 */
	public static String getTomorrowYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.DAY_OF_MONTH, 1);

		return format.format(today.getTime());
	}

	/**
	 * Get the day after tomorrow's formatted string
	 * 
	 * @return tomorrow's yyyy-MM-dd string
	 */
	public static String getAfterTomorrowYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.DAY_OF_MONTH, 2);

		return format.format(today.getTime());
	}

	/**
	 * Get yesterday's formatted string
	 * 
	 * @return yesterday's yyyy-MM-dd string
	 */
	public static String getYesterdayYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.DAY_OF_MONTH, -1);

		return format.format(today.getTime());
	}

	/**
	 * Get the day before yesterday's formatted string
	 * 
	 * @return yesterday's yyyy-MM-dd string
	 */
	public static String getBeforeYesterdayYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.DAY_OF_MONTH, -2);

		return format.format(today.getTime());
	}

	/**
	 * Get the day of next month's first day's formatted string
	 * 
	 * @return next month first day's yyyy-MM-dd string
	 */
	public static String getNextMonthFirstDayYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.MONTH, 1);
        today.set(Calendar.DAY_OF_MONTH,
                today.getActualMinimum(Calendar.DAY_OF_MONTH));

		return format.format(today.getTime());
	}

	/**
	 * Get current month's last day's formatted string
	 * 
	 * @return current month's last day's yyyy-MM-dd string
	 */
	public static String getMonthLastDayYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(Calendar.DAY_OF_MONTH,
				today.getActualMaximum(Calendar.DAY_OF_MONTH));

		return format.format(today.getTime());
	}
	
	/**
     * Get current month's first day's formatted string
     * 
     * @return current month's first day's yyyy-MM-dd string
     */
    public static String getMonthFirstDayYYYYMMDD() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.set(Calendar.DAY_OF_MONTH,
                today.getActualMinimum(Calendar.DAY_OF_MONTH));

        return format.format(today.getTime());
    }
    
	/**
	 * Get last year today's yesterday's formatted string.
	 * 
	 * @return last year today's yesterday yyyy-MM-dd 
	 */
	public static String getLastYearYesterday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.MONTH, -12);
		today.add(Calendar.DATE, -1);

		return format.format(today.getTime());
	}

	/**
	 * Get last year today's formatted string.
	 * 
	 * @return last year today yyyy-MM-dd
	 */
	public static String getLastYearToday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.MONTH, -12);
		return format.format(today.getTime());
	}

	/**
	 * Get next year today's formatted string.
	 * 
	 * @return next year today yyyy-MM-dd
	 */
	public static String getNextYearToday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.MONTH, 12);

		return format.format(today.getTime());
	}

	/**
	 * Get next year yesterday's formatted string.
	 * 
	 * @return next year yesterday yyyy-MM-dd
	 */
	public static String getNextYearYesterday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.MONTH, 12);
		today.add(Calendar.DATE, -1);

		return format.format(today.getTime());
	}

	/**
	 * Get next year before yesterday's formatted string.
	 * 
	 * @return next year before yesterday yyyy-MM-dd
	 */
	public static String getNextYearBeforeYesterday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.add(Calendar.MONTH, 12);
		today.add(Calendar.DATE, -2);

		return format.format(today.getTime());
	}
}
