/*
 * @(#)AbstractM_PBMS01DeleteBLogic.java
 *
 *
 */
package nttdm.bsys.m_pbm.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_pbm.dto.M_PBMS01DeleteInput;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.thin.AuthenticationController;

/**
 * Abstract BusinessLogic class.
 * 
 * @author helloworld
 */
public abstract class AbstractM_PBMS01DeleteBLogic implements
		BLogic<M_PBMS01DeleteInput> {

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * authenticationController
	 */
	protected AuthenticationController authenticationController;

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