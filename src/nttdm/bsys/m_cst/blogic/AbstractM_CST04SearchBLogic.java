
 /**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST04
 * FUNCTION       : Abstract BLogic 
 * FILE NAME      : AbstractM_CST04SearchBLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
 
package nttdm.bsys.m_cst.blogic;

import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;

/**
 * Abstract BusinessLogic class.
 * 
 * * @author NTT Data 	
 */
public abstract class AbstractM_CST04SearchBLogic implements BLogic<Map<String,Object>>{
	
	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * queryDAO を取得する
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO を設定する
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}
