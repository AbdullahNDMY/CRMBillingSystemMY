/*
 * @(#)AbstractB_CSBR02BLogic.java
 *
 *
 */
package nttdm.bsys.m_jnm.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_jnm.dto.M_JNMR02Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss051
 */
public abstract class AbstractM_JNMR02BLogic implements BLogic<M_JNMR02Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;
	
	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * queryDAO 繧貞叙蠕励☆繧�
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO 繧定ｨｭ螳壹☆繧�
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