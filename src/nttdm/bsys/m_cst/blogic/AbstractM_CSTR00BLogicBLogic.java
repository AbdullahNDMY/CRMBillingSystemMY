
 /**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST00
 * FUNCTION       : Abstract BLogic 
 * FILE NAME      : AbstractM_CSTR00BLogicBLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
 
package nttdm.bsys.m_cst.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_cst.dto.M_CSTR00BLogicInput;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * * @author NTT Data Vietnam	
 */
public abstract class AbstractM_CSTR00BLogicBLogic implements
		BLogic<M_CSTR00BLogicInput> {

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