/**
 * @(#)RadUserTUtil.java
 * Billing System
 * Version 1.00
 * Created 2011/10/21
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.HashMap;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.RAD_USERS_T;

/**
 * @author gplai
 * RAD_USERS_T table DAO
 */
public class RadUserTUtil {

    /**
     * queryDAO
     */
    private QueryDAO queryDAO;

    /**
     * updateDAO
     */
    private UpdateDAO updateDAO;

    /**
     * construct method
     * 
     * @param queryDAO
     * QueryDAO Object
     * @param updateDAO
     * UpdateDAO Object
     */
    public RadUserTUtil(QueryDAO queryDAO, UpdateDAO updateDAO) {

        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
    }

    /**
     * select by PK
     * 
     * @param userName
     * PK
     * @return RAD_USERS_T table Bean
     */
    public RAD_USERS_T selectByPK(String userName) {

        RAD_USERS_T radUserT =
            queryDAO.executeForObject(
                "SELECT.RAD_USERS_T.BYPK", userName, RAD_USERS_T.class);
        return radUserT;
    }

    /**
     * insert RAD_USERS_T
     * 
     * @param redUserTBean
     * RAD_USERS_T table Bean
     */
    public void insert(RAD_USERS_T redUserTBean) {

        updateDAO.execute("INSERT.RAD_USERS_T.SQL001", redUserTBean);
    }

    public void updateRadiusStatus(String accessAcount, String status) {

        RAD_USERS_T radUserT = new RAD_USERS_T();
        radUserT.setUserName(accessAcount);
        radUserT.setStatus(status);
        updateDAO.execute("UPDATE_STATUS", radUserT);
    }

    public boolean isExistUserName(String accessAccount) {

        String strCount =
            queryDAO.executeForObject(
                "SELECT_COUNT_OF_USERNAME", accessAccount, String.class);
        long count = Long.parseLong(strCount);
        if (count > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void deleteByUserName(String userName) {

        updateDAO.execute("DELETE_BY_USERNAME", userName);
        
    }
    
    public void updateRadiusPassword(HashMap<String, Object> paramsMap) {
    	updateDAO.execute("UPDATE.RAD_USERS_T.BYUSERNAME", paramsMap);
    }
}
