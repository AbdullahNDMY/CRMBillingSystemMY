package nttdm.bsys.m_bnk.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import nttdm.bsys.m_bnk.dto.M_BNKS02deInput;
public abstract class AbstractM_BNKS02de implements BLogic<M_BNKS02deInput> {
	
	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;
	protected UpdateDAO updateDAO;
	
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * queryDAO ã‚’å�–å¾—ã�™ã‚‹
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO ã‚’è¨­å®šã�™ã‚‹
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
