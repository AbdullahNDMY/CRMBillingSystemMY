/**
 * @(#)AbstractR_SAL_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011/08/29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.blogic;

import nttdm.bsys.r_sal.dto.R_SAL_R03Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;

/**
 * Abstract BusinessLogic class.
 * 
 * @author xycao
 */
public abstract class AbstractR_SAL_R03BLogic implements BLogic<R_SAL_R03Input> {

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
