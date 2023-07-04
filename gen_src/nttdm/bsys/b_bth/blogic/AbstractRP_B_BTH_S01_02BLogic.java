/*
 * @(#)AbstractRP_B_BTH_S01_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bth.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_02Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import org.springframework.transaction.support.ResourceTransactionManager;
import jp.terasoluna.fw.web.thin.AuthenticationController;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khungl0ng
 */
public abstract class AbstractRP_B_BTH_S01_02BLogic implements
		BLogic<RP_B_BTH_P01_02Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * transactionManager
	 */
	protected ResourceTransactionManager transactionManager;

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
	 * transactionManager を取得する
	 * 
	 * @return transactionManager
	 */
	public ResourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * transactionManager を設定する
	 * 
	 * @param transactionManager
	 *            transactionManager
	 */
	public void setTransactionManager(
			ResourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
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