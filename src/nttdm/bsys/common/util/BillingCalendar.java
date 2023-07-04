/**
 * @(#)BillingCalendar.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * the class of BillingCalendar
 * 
 * @author p-chengh
 */
public class BillingCalendar extends Calendar {

	public BillingCalendar() {
		super();
	}

	private static final long serialVersionUID = -5978320936328675392L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#add(int, int)
	 */
	@Override
	public void add(int field, int amount) {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#computeFields()
	 */
	@Override
	protected void computeFields() {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#computeTime()
	 */
	@Override
	protected void computeTime() {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#getGreatestMinimum(int)
	 */
	@Override
	public int getGreatestMinimum(int field) {
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#getLeastMaximum(int)
	 */
	@Override
	public int getLeastMaximum(int field) {
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#getMaximum(int)
	 */
	@Override
	public int getMaximum(int field) {
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#getMinimum(int)
	 */
	@Override
	public int getMinimum(int field) {
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Calendar#roll(int, boolean)
	 */
	@Override
	public void roll(int field, boolean up) {

	}

	public static BillingCalendar getInstance() {
		return new BillingCalendar();
	}

	/**
	 * get field value of date
	 * 
	 * @param date
	 * @param field
	 * @return field value
	 */
	public static int getValue4Date(Date date, int field) {
		int returnValue = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		returnValue = calendar.get(field);
		return returnValue;
	}

	/**
	 * add field value of date
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return added value
	 */
	public static Date addValue4Date(Date date, int field, int amount) {
		Date returnValue = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		returnValue = calendar.getTime();
		return returnValue;
	}

	/**
	 * format the data
	 * 
	 * @param date
	 * @param pattern
	 * @return date of string type
	 */
	public static String convertDateToString(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * get the distance of two date
	 * 
	 * @param a
	 * @param b
	 * @param field
	 * @return distance of two date
	 */
	public static long getDistance(Date a, Date b, int field) {
		long returnValue = 0;
		BillingCalendar cal01 = BillingCalendar.getInstance();
		BillingCalendar cal02 = BillingCalendar.getInstance();

		cal01.setTime(a);
		cal02.setTime(b);

		long mili_01 = cal01.getTimeInMillis();
		long mili_02 = cal02.getTimeInMillis();

		long diff = mili_01 - mili_02;

		switch (field) {
		case DATE:
			returnValue = diff / (24 * 60 * 60 * 1000);
			break;

		case HOUR:
			returnValue = diff / (60 * 60 * 1000);
			break;

		case MINUTE:
			returnValue = diff / (60 * 1000);
			break;

		case SECOND:
			returnValue = diff / (1000);
			break;
		default:
			break;
		}
		return returnValue;
	}

	/**
	 * get the maximunDate by the field
	 * 
	 * @param date
	 * @param field
	 * @return
	 */
	public static Date getMaximunDateByField(Date date, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// get max value
		int value = calendar.getActualMaximum(field);
		// set new value
		calendar.set(field, value);
		// return value
		return calendar.getTime();
	}

	/**
	 * compare two date
	 * 
	 * @param date1
	 * @param date2
	 * @return case 1: date1 > date2 case 0: date1 = date2 case -1: date1 <
	 *         date2
	 */
	public static int compare(Date date1, Date date2) {
		int returnValue = 0;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		long value1 = Long.parseLong(fmt.format(date1));
		long value2 = Long.parseLong(fmt.format(date2));
		long diff = value1 - value2;
		// date1 = date2
		if (diff == 0) {
			returnValue = 0;
			// date1 > date2
		} else if (diff > 0) {
			returnValue = 1;
			// date1 < date2
		} else {
			returnValue = -1;
		}
		return returnValue;
	}
}
