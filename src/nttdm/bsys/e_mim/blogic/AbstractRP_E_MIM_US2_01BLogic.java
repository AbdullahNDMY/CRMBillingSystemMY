/*
 * @(#)AbstractRP_E_MIM_US2_01BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_01Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.support.ResourceTransactionManager;
import jp.terasoluna.fw.dao.StoredProcedureDAO;
import jp.terasoluna.fw.web.codelist.CodeListLoader;
import jp.terasoluna.fw.web.thin.AuthenticationController;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khungl0ng
 */
public abstract class AbstractRP_E_MIM_US2_01BLogic implements
		BLogic<RP_E_MIM_US2_01Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * contextApplicationContextProvider
	 */
	protected ApplicationContextAware contextApplicationContextProvider;

	/**
	 * transactionManager
	 */
	protected ResourceTransactionManager transactionManager;

	/**
	 * spDAO
	 */
	protected StoredProcedureDAO spDAO;

	/**
	 * beanCodeList
	 */
	protected CodeListLoader beanCodeList;

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
	 * contextApplicationContextProvider を取得する
	 * 
	 * @return contextApplicationContextProvider
	 */
	public ApplicationContextAware getContextApplicationContextProvider() {
		return contextApplicationContextProvider;
	}

	/**
	 * contextApplicationContextProvider を設定する
	 * 
	 * @param contextApplicationContextProvider
	 *            contextApplicationContextProvider
	 */
	public void setContextApplicationContextProvider(
			ApplicationContextAware contextApplicationContextProvider) {
		this.contextApplicationContextProvider = contextApplicationContextProvider;
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
	 * spDAO を取得する
	 * 
	 * @return spDAO
	 */
	public StoredProcedureDAO getSpDAO() {
		return spDAO;
	}

	/**
	 * spDAO を設定する
	 * 
	 * @param spDAO
	 *            spDAO
	 */
	public void setSpDAO(StoredProcedureDAO spDAO) {
		this.spDAO = spDAO;
	}

	/**
	 * beanCodeList を取得する
	 * 
	 * @return beanCodeList
	 */
	public CodeListLoader getBeanCodeList() {
		return beanCodeList;
	}

	/**
	 * beanCodeList を設定する
	 * 
	 * @param beanCodeList
	 *            beanCodeList
	 */
	public void setBeanCodeList(CodeListLoader beanCodeList) {
		this.beanCodeList = beanCodeList;
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