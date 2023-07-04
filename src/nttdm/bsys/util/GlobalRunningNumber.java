/**
 * @(#)GlobalRunningNumber.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.util;

import jp.terasoluna.fw.dao.QueryDAO;

/**
 * System Global Running Number
 */
public class GlobalRunningNumber {
	/**
	 * globalNumber
	 */
	private static int globalNumber = 0;

	/**
	 * queryDAO
	 */
	private QueryDAO queryDAO;

	/**
	 * SELECT_Running_No
	 */
	private static final String SELECT_Running_No = "SELECT.BSYS.SQL010";

	/**
	 * queryDAO
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * GlobalRunningNumber
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public GlobalRunningNumber(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
		// check initial
		if (globalNumber == 0) {
			// get current globalNumber from database
			String max = queryDAO.executeForObject(SELECT_Running_No, null,
					String.class);
			if (max != null && !max.trim().equals("")) {
				globalNumber = Integer.parseInt(max);
			} else {
				globalNumber = 0;
			}
		}
	}

	/**
	 * getGlobalRunningNumber
	 * 
	 * @return String
	 */
	public static String getGlobalRunningNumber() {
		// increase 1
		globalNumber++;
		return Integer.toString(globalNumber);
	}
}
