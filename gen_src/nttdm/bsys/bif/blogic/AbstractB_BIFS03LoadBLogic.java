/*
 * @(#)AbstractB_BIFS03LoadBLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.bif.dto.B_BIFS03LoadInput;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * ビジネスロジックの抽象クラス。
 * 
 * @author duongnld
 */

public abstract class AbstractB_BIFS03LoadBLogic implements
		BLogic<B_BIFS03LoadInput> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;
	private UpdateDAO updateDAO;


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

	/**
	 * @param updateDAO the updateDAO to set
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * @return the updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

}