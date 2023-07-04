/**
 * @(#)G_SET_P02_Task.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.batch;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.dao.UpdateDAOiBatisNukedImpl;
import nttdm.bsys.common.util.G_SET_P02;

/**
 * Batch Scheduler
 * 
 * @author lixinj
 */
public class G_SET_P02_Task {

	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO;

	/**
	 * <div>updateDAONuked</div>
	 */
	private UpdateDAOiBatisNukedImpl updateDAONuked;

	/**
     * radiusQueryDAO
     */
    protected QueryDAO radiusQueryDAO;

    /**
     * radiusUpdateDAO
     */
    protected UpdateDAO radiusUpdateDAO;

	/**
	 * Get QueryDAO.
	 * 
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * Set queryDAO
	 * @param queryDAO QueryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * Get UpdateDAO
	 * 
	 * @return UpdateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * Set UpdateDAO
	 * 
	 * @param updateDAO UpdateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * Get UpdateDAONuked
	 * 
	 * @return UpdateDAONuked
	 */
	public UpdateDAOiBatisNukedImpl getUpdateDAONuked() {
		return updateDAONuked;
	}

	/**
	 * Set UpdateDAONuked.
	 * 
	 * @param updateDAONuked UpdateDAOiBatisNukedImpl
	 */
	public void setUpdateDAONuked(UpdateDAOiBatisNukedImpl updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
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

    /**
	 * call batch execution.
	 */
	public void printMessage() {
		G_SET_P02 GSETBATCH = new G_SET_P02(this.queryDAO, this.updateDAO,
				this.updateDAONuked, radiusQueryDAO, radiusUpdateDAO);
		GSETBATCH.excute_batch();
	}
}
