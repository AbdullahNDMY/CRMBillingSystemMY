/**
 * @(#)Functions.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.sql.Date;
import java.util.List;
/**
 * A collection of some of the function
 * 
 * @author leonzh
 */
public class Functions {
	/**
	 * <div>convert number to word</div>
	 * 
	 * @param number
	 *            Object
	 * 
	 * @return String the number by word converted
	 */
	public static String numberToWord(Object number){
		return EnglishNumberToWords.convert(CommonUtils.toString(number));
	}
	
	/**
	 * <div>format the date</div>
	 * 
	 * @param date
	 *            Object
	 * @param ptt
	 *            String
	 * 
	 * @return String the string of the date
	 */
	public static String formatDate(Object date, String ptt){
		return CommonUtils.parseToString((Date)date, ptt);
	}
	
	/**
	 * <div>Determine whether the list includes obj</div>
	 * 
	 * @param list
	 *            List
	 * @param obj
	 *            Object
	 * 
	 * @return boolean whether the list includes obj
	 */
	@SuppressWarnings("rawtypes")
	public static boolean contains(List list, Object obj) {
		return list.contains(obj);
	}
}
