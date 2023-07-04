/**
 * @(#)MessageUtil.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import jp.terasoluna.fw.util.PropertyUtil;

/**
 * Common methods for MessageUtil
 * 
 * @author p-minzh
 */
public class MessageUtil extends PropertyUtil {

	/**
	 * <div>get placeHolders</div>
	 * 
	 * @param key
	 *            String
	 * @param holders
	 *            Object
	 * 
	 * @return String placeHolders
	 * 
	 */
	public static String get(String key, Object... holders) {
		String value = getProperty(key);
		return CommonUtils.placeHolders(value, holders);
	}

	/**
	 * <div>get Int</div>
	 * 
	 * @param key
	 *            String
	 * 
	 * @return int
	 * 
	 */
	public static int getInt(String key) {
		return CommonUtils.toInteger(getProperty(key));
	}

	/**
	 * <div>get Int</div>
	 * 
	 * @param key
	 *            String
	 * @param def
	 *            int
	 * 
	 * @return int
	 * 
	 */
	public static int getInt(String key, int def) {
		return CommonUtils.toInteger(getProperty(key), def);
	}

}
