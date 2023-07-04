/*
 * @(#)AbstractRP_E_MEX_SP1BLogic.java
 *
 *
 */
package nttdm.bsys.e_mex.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;

/**
 * Abstract BusinessLogic class.
 * 
 * @author thinhtv
 */
public abstract class AbstractRP_E_MEX_SP1BLogic implements
		BLogic<RP_E_MEX_SP1Input> {

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
	 *          updateDAONuked
	 */
	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

}