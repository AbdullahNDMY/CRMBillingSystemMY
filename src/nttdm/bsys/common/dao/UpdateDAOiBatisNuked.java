/**
 * @(#)UpdateDAOiBatisNuked.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Common methods for UpdateDAOiBatisNuked
 * 
 * @author p-minzh
 */
public interface UpdateDAOiBatisNuked extends UpdateDAO {

	SqlMapClient getSqlMapClient();

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @param obj
	 * @return <T> with generated primary key.
	 * 
	 */
	public Integer insert(String query, Object obj);
}
