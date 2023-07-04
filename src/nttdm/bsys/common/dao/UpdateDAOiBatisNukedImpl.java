/**
 * @(#)UpdateDAOiBatisNukedImpl.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.dao;

import java.sql.SQLException;

import nttdm.bsys.common.util.CommonUtils;

import jp.terasoluna.fw.dao.ibatis.UpdateDAOiBatisImpl;

/**
 * Common methods for UpdateDAOiBatisNukedImpl
 * 
 * @author p-minzh
 */
public class UpdateDAOiBatisNukedImpl extends UpdateDAOiBatisImpl implements
		UpdateDAOiBatisNuked {

	/**
	 * Execute insert operation with SQLID and parameter.
	 * 
	 * @param query
	 *            SQLID
	 * @param obj
	 *            parameter
	 * @return <T> with generated primary key.
	 */
	public Integer insert(String query, Object obj) {
		return this.insert(query, obj, null);
	}

	/**
	 * Execute insert operation with SQLID and parameter.<br>
	 * If operation fails, return default value.
	 * 
	 * @param query
	 *            SQLID
	 * @param obj
	 *            parameter
	 * @param def
	 *            default value
	 * @return <T> with generated primary key or def.
	 */
	public Integer insert(String query, Object obj, Integer def) {
		try {
			return CommonUtils.toInteger(
					this.getSqlMapClient().insert(query, obj), def);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return def;
	}
}
