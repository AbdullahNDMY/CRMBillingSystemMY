/*
 * @(#)AbstractG_SSM_S01ActionBLogic.java
 *
 *
 */
package nttdm.bsys.g_ssm_s01.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.g_ssm_s01.dto.G_SSM_S01ActionInput;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khaln
 */
public abstract class AbstractG_SSM_S01ActionBLogic implements
		BLogic<G_SSM_S01ActionInput> {

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