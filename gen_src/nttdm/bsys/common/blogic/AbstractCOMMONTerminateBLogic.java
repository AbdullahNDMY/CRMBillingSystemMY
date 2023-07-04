/*
 * @(#)AbstractCOMMONTerminateBLogic.java
 *
 *
 */
package nttdm.bsys.common.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import java.util.HashMap;
import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * ビジネスロジックの抽象クラス。
 * 
 * @author duongnld
 */
public abstract class AbstractCOMMONTerminateBLogic implements BLogic<HashMap<String, Object>> {

	 /**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	
	/**
	 * UpdateDAOiBatisNuked
	 */
	protected UpdateDAOiBatisNuked updateDAOiBatisNuked;

    /**
	 * @return the updateDAOiBatisNuked
	 */
	public UpdateDAOiBatisNuked getUpdateDAOiBatisNuked() {
		return updateDAOiBatisNuked;
	}

	/**
	 * @param updateDAOiBatisNuked the updateDAOiBatisNuked to set
	 */
	public void setUpdateDAOiBatisNuked(UpdateDAOiBatisNuked updateDAOiBatisNuked) {
		this.updateDAOiBatisNuked = updateDAOiBatisNuked;
	}


	/**
     * radiusQueryDAO
     */
    protected QueryDAO radiusQueryDAO;

    /**
     * radiusUpdateDAO
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