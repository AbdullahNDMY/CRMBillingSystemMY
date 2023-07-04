package nttdm.bsys.m_bnk.blogic;

import jp.terasoluna.fw.service.thin.BLogic;


import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.m_bnk.dto.M_BNKS01Input;

public abstract class AbstractM_BNKS01 implements BLogic<M_BNKS01Input> {
	
	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

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
