/*
 * @(#)AbstractM_WLMR01BLogic.java
 *
 *
 */
package nttdm.bsys.m_wlm.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_wlm.dto.M_WLMR01Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss052
 */
public abstract class AbstractM_WLMR01BLogic implements
		BLogic<M_WLMR01Input> {

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