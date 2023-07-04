/*
 * @(#)AbstractRP_E_MIM_US2_03BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_03Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.thin.AuthenticationController;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khungl0ng
 */
public abstract class AbstractRP_E_MIM_US2_03BLogic implements
		BLogic<RP_E_MIM_US2_03Input> {

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