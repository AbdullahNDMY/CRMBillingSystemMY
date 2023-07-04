/*
 * @(#)AbstractB_BIFS02BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * ビジネスロジックの抽象クラス。
 * 
 * @author duongnld
 */
public abstract class AbstractB_BIFS02BLogic implements BLogic<B_BIFS02Input> {

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