/*
 * @(#)AbstractM_BNKS01Blogic.java
 *
 *
 */
package nttdm.bsys.m_bnk.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_bnk.dto.M_BNKS01BlogicInput;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author hieuvt
 */
public abstract class AbstractM_BNKS01Blogic implements
		BLogic<M_BNKS01BlogicInput> {

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