/**
 * @(#)B_CPM_S04CheckErrorBLogic.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created 2013/03/19
 * 
 * Copyright (c) 2013 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.b_cpm.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * @author gplai
 *
 */
public class B_CPM_S04CheckErrorBLogic implements BLogic<Map<String, Object>> {

    private QueryDAO queryDAO;
    
    public BLogicResult execute(Map<String, Object> param) {
        BLogicResult result = new BLogicResult();

        Map<String, Object> resultObject = new HashMap<String, Object>();
        B_CPM_S04Common b_cpm_s04Common = new B_CPM_S04Common(queryDAO);
        //do search
        b_cpm_s04Common.doSearch(param, resultObject);
        
        result.setResultObject(resultObject);
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
}
