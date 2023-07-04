package nttdm.bsys.m_bnk.blogic;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.m_bnk.dto.M_BNKS02vInput;;

public abstract class AbstractM_BNKS02v implements BLogic<M_BNKS02vInput> {
	
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
