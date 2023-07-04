/*
 * @(#)AbstractRP_B_BIL_S02_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_02_01Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khungl0ng
 */
public abstract class AbstractRP_B_BIL_S02_02BLogic implements
		BLogic<RP_B_BIL_S02_02_01Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * queryDAO ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

}