/**
 * @(#)R_REV_R03BLogic.java
 * Billing System
 * Version 3.03
 * Created 2016/10/25
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rev.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.r_rev.dto.R_REV_R03Input;

/**
 * Abstract BusinessLogic class.
 * 
 * @author binz
 */
public abstract class AbstractR_REV_R03BLogic implements BLogic<R_REV_R03Input> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;
    
    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;

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

    /**
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * @param updateDAO the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

}
