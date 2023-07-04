/*
 * @(#)AbstractM_GBSS01_02BLogic.java
 *
 *
 */
package nttdm.bsys.m_gbs.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_gbs.dto.M_GBSS01_02Input;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khaln
 */
public abstract class AbstractM_GBSS01_02BLogic implements
		BLogic<M_GBSS01_02Input> {

	protected QueryDAO queryDAO;
		    
	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * updateDAO を取得する
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO を設定する
	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

}