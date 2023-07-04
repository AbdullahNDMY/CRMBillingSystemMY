package nttdm.bsys.e_eml.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.thin.AuthenticationController;
import nttdm.bsys.common.util.G_EML_P02;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_eml.dto.E_EML03Input;

import org.springframework.transaction.support.ResourceTransactionManager;

public class E_EMLS04EmailBLogic implements BLogic<E_EML03Input>{
	
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

	public BLogicResult execute(E_EML03Input arg0) {
		BLogicResult result = new BLogicResult();
		arg0.setModuleId("email");
		G_EML_P02 gemlp02 = new G_EML_P02(queryDAO, updateDAO);
		GlobalProcessResult gResult = gemlp02.contextInitialized(arg0,null);
		result.setResultObject(arg0);
        result.setErrors(gResult.getErrors());
        result.setMessages(gResult.getMessages());
		result.setResultString("success");
		return result;
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * @return the updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * @param updateDAO the updateDAO to set
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * @return the transactionManager
	 */
	public ResourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * @param transactionManager the transactionManager to set
	 */
	public void setTransactionManager(ResourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * @return the authenticationController
	 */
	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	/**
	 * @param authenticationController the authenticationController to set
	 */
	public void setAuthenticationController(
			AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}

}
