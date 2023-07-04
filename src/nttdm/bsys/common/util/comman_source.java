/**
 * @(#)comman_source.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Offer DAO to operate DB
 * 
 * @author i-jankw
 */
public class comman_source {
	/**
	 * UpdateDAO
	 */
	public static UpdateDAO updateDAO;
	
	/**
	 * QueryDAO
	 */
	public static QueryDAO queryDAO;
	
	/**
	 * BillingSystemUserValueObject
	 */
	public static BillingSystemUserValueObject uvo;
}
