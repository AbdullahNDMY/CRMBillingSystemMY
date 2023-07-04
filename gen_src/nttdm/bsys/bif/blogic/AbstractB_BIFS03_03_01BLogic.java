/*
 * @(#)AbstractB_BIFS03_03_01BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.bif.dto.B_BIFS03Input;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * ビジネスロジックの抽象クラス。
 * 
 * @author duongnld
 */
public abstract class AbstractB_BIFS03_03_01BLogic implements
		BLogic<B_BIFS03Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * updateDAONuked
	 */
	protected UpdateDAOiBatisNuked updateDAONuked;
	
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

	/**
	 * updateDAONuked を取得する
	 * 
	 * @return updateDAONuked
	 */
	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	/**
	 * updateDAONuked を設定する
	 * 
	 * @param updateDAONuked
	 * 
	 */
	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

}