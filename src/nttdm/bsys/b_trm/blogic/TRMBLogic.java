package nttdm.bsys.b_trm.blogic;

import java.util.Map;
import java.util.Set;

import org.springframework.transaction.support.ResourceTransactionManager;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.web.thin.AuthenticationController;

public abstract class TRMBLogic<T> implements BLogic<T> {
	protected QueryDAO queryDAO;

	protected UpdateDAO updateDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	/**
	 * transactionManager
	 */
	protected ResourceTransactionManager transactionManager;

	/**
	 * authenticationController
	 */
	protected AuthenticationController authenticationController;
	
	public ResourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(ResourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	public void setAuthenticationController(
			AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}

	protected void copyProperties(Map<String, Object> src, Map<String, Object> des) {
		Set<String> keys = src.keySet();
		for(String key : keys)
			des.put(key, src.get(key));
	}
}
