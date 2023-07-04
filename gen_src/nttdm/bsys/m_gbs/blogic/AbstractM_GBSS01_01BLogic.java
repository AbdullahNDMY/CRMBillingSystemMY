/*
 * @(#)AbstractM_GBSS01_01BLogic.java
 *
 *
 */
package nttdm.bsys.m_gbs.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_gbs.dto.M_GBSS01_01Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khaln
 */
public abstract class AbstractM_GBSS01_01BLogic implements
		BLogic<M_GBSS01_01Input> {

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