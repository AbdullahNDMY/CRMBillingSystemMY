/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Abstract class blogic
 * FILE NAME      : AbstractB_CPM_S02AddCobOptionBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/08/05 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.m_svt.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import java.util.HashMap;

import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * AbstractB_CPM_S02AddPlanBLogic.class<br>
 * <ul>
 * <li>Add Plan BLogic</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public abstract class AbstractM_SVT02_SavePlanDetailOptionBLogic implements BLogic<HashMap<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;
    
    /**
     * updateDAONuked
     */
    private UpdateDAOiBatisNuked updateDAONuked;
    
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
     * updateDAONuked を取得する
     * 
     * @return updateDAONuked
     */
    public UpdateDAOiBatisNuked getUpdateDAONuked() {
        return updateDAONuked;
    }

    /**
     * updateDAONuked を設定する
     * 
     * @param updateDAONuked
     *            updateDAONuked
     */
    public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
        this.updateDAONuked = updateDAONuked;
    }
}