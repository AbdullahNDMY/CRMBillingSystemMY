/**
 * @(#)AbstractRP_B_BIL_S03_04BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/18
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.web.thin.AuthenticationController;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_04Input;

import org.springframework.transaction.support.ResourceTransactionManager;

/**
 * Abstract BusinessLogic class.
 * 
 * @author gplai
 * 
 */
public abstract class AbstractRP_B_BIL_S03_04BLogic implements
        BLogic<RP_B_BIL_S03_04Input> {
    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;

    /**
     * transactionManager
     */
    protected ResourceTransactionManager transactionManager;

    /**
     * authenticationController
     */
    protected AuthenticationController authenticationController;

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

    /**
     * transactionManager を取得する
     * 
     * @return transactionManager
     */
    public ResourceTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * transactionManager を設定する
     * 
     * @param transactionManager
     *            transactionManager
     */
    public void setTransactionManager(
            ResourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * authenticationController を取得する
     * 
     * @return authenticationController
     */
    public AuthenticationController getAuthenticationController() {
        return authenticationController;
    }

    /**
     * authenticationController を設定する
     * 
     * @param authenticationController
     *            authenticationController
     */
    public void setAuthenticationController(
            AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
    }
}
