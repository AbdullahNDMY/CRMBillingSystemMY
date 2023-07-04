/*
 * @(#)AbstractB_BIFS03_05BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.bif.dto.B_BIFS03Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * ビジネスロジックの抽象クラス。
 * 
 * @author duongnld
 */
public abstract class AbstractB_BIFS03_05BLogic implements
		BLogic<B_BIFS03Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	
	/**
     * queryDAO
     */
    protected QueryDAO radiusQueryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO radiusUpdateDAO;

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
     * @return the radiusQueryDAO
     */
    public QueryDAO getRadiusQueryDAO() {
        return radiusQueryDAO;
    }

    /**
     * @param radiusQueryDAO the radiusQueryDAO to set
     */
    public void setRadiusQueryDAO(QueryDAO radiusQueryDAO) {
        this.radiusQueryDAO = radiusQueryDAO;
    }

    /**
     * @return the radiusUpdateDAO
     */
    public UpdateDAO getRadiusUpdateDAO() {
        return radiusUpdateDAO;
    }

    /**
     * @param radiusUpdateDAO the radiusUpdateDAO to set
     */
    public void setRadiusUpdateDAO(UpdateDAO radiusUpdateDAO) {
        this.radiusUpdateDAO = radiusUpdateDAO;
    }
}