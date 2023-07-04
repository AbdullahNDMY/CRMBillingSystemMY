/*
 * @(#)AbstractM_ALTR04BLogic.java
 *
 *
 */
package nttdm.bsys.m_alt.blogic;


import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_alt.dto.M_ALTR04Input;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss051
 */
public abstract class AbstractM_ALTR04BLogic implements BLogic<M_ALTR04Input> {
	
	protected QueryDAO queryDAO = null;
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	
}