/**
 * @(#)BsysValidator.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.validator;

import nttdm.bsys.common.util.CommonUtils;

/**
 * Common methods for BsysValidator
 * 
 * @author p-minzh
 */
public class BsysValidator extends CommonUtils {

	/**
	 * <div>is Date</div>
	 * 
	 * @param date
	 *            Object
	 * 
	 * @return boolean
	 * 
	 */
	public static boolean isDate(Object date) {
		return toDate(date) != null;
	}

	/**
	 * <div>is Date</div>
	 * 
	 * @param date
	 *            Object
	 * @param pattern
	 *            String
	 * 
	 * @return boolean
	 * 
	 */
	public static boolean isDate(Object date, String pattern) {
		return toDate(date, pattern) != null;
	}

}
