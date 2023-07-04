/*
 * @(#)AbstractM_SSMS01_01BLogic.java
 *
 *
 */
package nttdm.bsys.m_ssm.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_ssm.dto.M_SSMS01_01Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.thin.AuthenticationController;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khaln
 */
public abstract class AbstractM_SSMS01_01BLogic implements
		BLogic<M_SSMS01_01Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * authenticationController
	 */
	protected AuthenticationController authenticationController;

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
	 * authenticationController を取得する
	 * 
	 * @return authenticationController
	 */
	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	/**
	 * authenticationController を設定する
	 * 
	 * @param authenticationController
	 *            authenticationController
	 */
	public void setAuthenticationController(
			AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}

}