/**
 * @(#)AbstractR_SSM_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_ssm.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.r_ssm.dto.R_SSM_R03Input;

/**
 * Export Result.
 * 
 * @author bench
 */
public abstract class AbstractR_SSM_R03BLogic implements BLogic<R_SSM_R03Input> {
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
     * @param queryDAO
     *            the queryDAO to set
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
     * @param updateDAO
     *            the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
}
