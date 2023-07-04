/*
 * @(#)AbstractQ_QCSR04BLogic.java
 *
 *
 */
package nttdm.bsys.q_qcs.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.q_qcs.dto.Q_QCSR04Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss051
 */
public abstract class AbstractQ_QCSR04BLogic implements BLogic<Q_QCSR04Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

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

}