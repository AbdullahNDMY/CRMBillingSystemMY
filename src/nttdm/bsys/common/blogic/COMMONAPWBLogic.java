/**
 * @(#)COMMONChangeAccessAccountBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/06/04
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.blogic;

import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.util.CommonUtils;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * @author gplai
 *
 */
public class COMMONAPWBLogic implements BLogic<HashMap<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(HashMap<String, Object> params) {
        BLogicResult result = new BLogicResult();
        Map<String, Object> output = new HashMap<String, Object>();
        
        String idSubInfo = CommonUtils.toString(params.get("idSubInfo")).trim();
        Map<String, Object> subscriptInfoMap = queryDAO.executeForMap("COMMON.GET_SUBSCIPTION_BY_ID_SUB_INFO", idSubInfo);
        if (subscriptInfoMap!=null) {
            output.put("accessAccount", CommonUtils.toString(subscriptInfoMap.get("ACCESS_ACCOUNT")));
        }
        output.put("idSubInfo", idSubInfo);
        
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

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
