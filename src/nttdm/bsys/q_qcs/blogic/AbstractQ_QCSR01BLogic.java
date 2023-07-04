/*
 * @(#)AbstractQ_QCSR01BLogic.java
 *
 *
 */
package nttdm.bsys.q_qcs.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.q_qcs.dto.Q_QCSR01Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss051
 */
public abstract class AbstractQ_QCSR01BLogic implements BLogic<Q_QCSR01Input> {

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