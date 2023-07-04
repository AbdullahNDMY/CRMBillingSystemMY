/*
 * @(#)AbstractQ_QCSR02BLogicBLogic.java
 *
 *
 */
package nttdm.bsys.q_qcs.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.q_qcs.dto.Q_QCSR02Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss051
 */
public abstract class AbstractQ_QCSR02BLogic implements
		BLogic<Q_QCSR02Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

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

}