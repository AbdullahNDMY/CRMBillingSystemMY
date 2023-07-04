/*
 * @(#)AbstractE_DIM_GR1_201BLogic.java
 *
 *
 */
package nttdm.bsys.e_dim_gr1.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicInput;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss054
 */
public abstract class AbstractE_DIM_GR1_201BLogic implements
		BLogic<E_DIM_GR1_2BlogicInput> {

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