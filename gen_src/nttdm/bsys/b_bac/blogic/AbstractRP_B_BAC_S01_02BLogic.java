/*
 * @(#)AbstractRP_B_BAC_S01_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author dunglq
 */
public abstract class AbstractRP_B_BAC_S01_02BLogic implements
		BLogic<RP_B_BAC_S01_02Input> {

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
	 *            queryDAO
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
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

}