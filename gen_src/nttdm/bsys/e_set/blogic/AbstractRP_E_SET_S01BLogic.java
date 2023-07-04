/*
 * @(#)AbstractRP_E_SET_S01BLogic.java
 *
 *
 */
package nttdm.bsys.e_set.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.e_set.dto.RP_E_SET_S01Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author thinhtv
 */
public abstract class AbstractRP_E_SET_S01BLogic implements
		BLogic<RP_E_SET_S01Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

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
	 *          queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO を取得する
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO を設定する
	 * 
	 * @param updateDAO
	 *          updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

}