/*
 * @(#)AbstractE_MIM_US1_2BlogicBLogic.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1_2BlogicInput;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss054
 */
public abstract class AbstractE_MIM_US1_2BlogicBLogic implements
		BLogic<E_MIM_US1_2BlogicInput> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * queryDAO 
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO 
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO 
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO 
	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

}