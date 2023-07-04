/*
 * @(#)AbstractM_COMS01_1BLogic.java
 *
 *
 */
package nttdm.bsys.m_com.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_com.dto.M_COMS01_1Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author helloworld
 */
public abstract class AbstractM_COMS01_1BLogic implements
		BLogic<M_COMS01_1Input> {

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