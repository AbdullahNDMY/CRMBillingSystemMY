/*
 * @(#)AbstractM_SVT01_SearchBLogic.java
 *
 *
 */
package nttdm.bsys.m_svt.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchInput;

/**
 * Abstract BusinessLogic class.
 * 
 * @author hungtm
 */
public abstract class AbstractM_SVT01_SearchBLogic implements BLogic<M_SVT01_SearchInput> {

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