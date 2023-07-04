/*
 * @(#)AbstractM_ALTR01BLogic.java
 *
 *
 */
package nttdm.bsys.m_alt.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_alt.dto.M_ALTR01Input;

/**
 * <div>
 * Abstract BusinessLogic class.
 * </div>
 * @author Phu.Nguyen
 */
public abstract class AbstractM_ALTR01BLogic implements
		BLogic<M_ALTR01Input> {
	/**
	 * <div>queryDAO</div>
	 */
	protected QueryDAO queryDAO = null;
	protected UpdateDAO updateDAO=null;

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
}