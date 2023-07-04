/**
 * @(#)B_SSM_S04BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/24
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_ssm.s04.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;

/**
 * @author gplai
 *
 */
public abstract class B_SSM_S04BLogic<T> implements BLogic<T> {
    
    protected QueryDAO queryDAO;
    
    protected UpdateDAO updateDAO;
    
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
}
