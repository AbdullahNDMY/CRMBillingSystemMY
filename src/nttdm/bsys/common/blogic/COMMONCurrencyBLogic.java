/**
 * @(#)COMMONCurrencyBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/01/22
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.blogic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author gplai
 *
 */
public class COMMONCurrencyBLogic implements BLogic<Map<String, Object>> {

    /**
     * queryDAO
     */
    private QueryDAO queryDAO;

    /**
     * updateDAO
     */
    private UpdateDAO updateDAO;
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(Map<String, Object> params) {
        BLogicResult result = new BLogicResult();
        Map<String, Object> output = new HashMap<String, Object>();
        String runDateStr = CommonUtils.toString(params.get("runDate")).trim();
        Date runDate = null;
        if (!CommonUtils.isEmpty(runDateStr)) {
            runDate = CommonUtils.toDate(runDateStr, "dd/MM/yyyy");
        } else {
            runDate = new Date();
        }
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("date", CommonUtils.formatDate(runDate, "yyyy/MM"));
        List<Map<String, Object>> currencyList = queryDAO.executeForMapList("SELECT.COMMON.POPCUR.CURRENCY", sqlParam);
        output.put("monthYear", CommonUtils.formatDate(runDate, "MM/yyyy"));
        output.put("currencyList", currencyList);
        
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
