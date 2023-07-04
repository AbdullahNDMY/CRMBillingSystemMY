/*
 * @(#)AbstractE_MEX_SP1BLogic.java
 *
 * Copyright 2010 NTT DATA Corporation.
 *
 */
package nttdm.bsys.e_dim_gr1.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1BlogicInput;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicInput;

/**
 * ビジ�?スロジック�?�抽象クラス。
 * 
 * @author handn
 */
public abstract class AbstractE_DIM_GR101BLogic implements
		BLogic<E_DIM_GR1BlogicInput> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * queryDAO を�?�得�?�る
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO を設定�?�る
	 * 
	 * @param queryDAO
	 *          queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO を�?�得�?�る
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO を設定�?�る
	 * 
	 * @param updateDAO
	 *          updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

}