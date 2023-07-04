/*
 * @(#)AbstractA_USR_S02_InitDataBLogic.java
 *
 *
 */
package nttdm.bsys.a_usr.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.a_usr.dto.A_USR_S02IO;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author hungtm
 */
public abstract class AbstractA_USR_S02_InitDataBLogic implements
		BLogic<A_USR_S02IO> {

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