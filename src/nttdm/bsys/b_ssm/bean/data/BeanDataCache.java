/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM
 * FUNCTION       : Temporarily cache data
 * FILE NAME      : BeanDataCache.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.bean.data;

import java.util.HashMap;

/**
 * @author NTT Data Vietnam
 * Temporarily cache data
 */
public class BeanDataCache {
	
	private static HashMap<String, HashMap<String, Object>> beanCache = new HashMap<String, HashMap<String,Object>>();
	
	/**
	 * Get data from cache
	 * @param sessionID
	 * @param objID
	 * @return Object
	 */
	public static Object get(String sessionID, String objID) {
		return beanCache.containsKey(sessionID) ?
				beanCache.get(sessionID).get(objID):
				null;
	}
	
	/**
	 * Add data to cache
	 * @param sessionID
	 * @param objID
	 * @param obj
	 */
	public static void add(String sessionID, String objID, Object obj) {
		if (beanCache.containsKey(sessionID)) {
			HashMap<String, Object> values = beanCache.get(sessionID);
			if (values == null) {
				values = new HashMap<String, Object>();
			}
			values.put(objID, obj);
		} else {
			HashMap<String, Object> values = new HashMap<String, Object>();
			values.put(objID, obj);
			beanCache.put(sessionID, values);
		}		
	}

	/**
	 * Remove cache with specific sessionID
	 * @param sessionID
	 */
	public static void remove(String sessionID) {
		if (beanCache.containsKey(sessionID)) {
			beanCache.remove(sessionID);
		} 
	}
	
}
