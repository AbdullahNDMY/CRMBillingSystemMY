/*
 * @(#)AbstractM_ALTR02BLogic.java
 *
 *
 */
package nttdm.bsys.m_alt.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_alt.dto.M_ALTR02Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss051
 */
public abstract class AbstractM_ALTR02BLogic implements
		BLogic<M_ALTR02Input> {

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	/**
	 * <div>queryDAO</div>
	 */
	protected QueryDAO queryDAO = null;

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}