/**
 * @(#)B_CPM_S04InitBLogic.java
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
import java.util.Set;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm_en.dto.InputSearchPlan;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author gplai
 *
 */
public class B_CPM_S04InitBLogic implements BLogic<Map<String, Object>> {

    private static final String B_CPM_S02V_SCREEN = "B_CPM_S02v";
    
    private QueryDAO queryDAO;

    public BLogicResult execute(Map<String, Object> param) {
        BLogicResult result = new BLogicResult();
        
        Map<String, Object> resultObject = new HashMap<String, Object>();
        
        this.copyParam(param, resultObject);
        
        Map<String, Object> cpmTransType = this.queryDAO.executeForMap("B_CPM.GET_CPM_TRANS_TYPE", null);
        resultObject.put("cpmTransType", CommonUtils.toString(cpmTransType.get("SET_VALUE")));
        
        String idScreen = CommonUtils.toString(param.get("idScreen"));
        
        InputSearchPlan inputSearch = (InputSearchPlan)param.get("inputSearchPlan");
        //From B_CPM_S02v
        if (B_CPM_S02V_SCREEN.equals(idScreen)) {
            String customerName = CommonUtils.toString(queryDAO.executeForMap("B_CPM.B_CPM_S04_SELECT_CUST_BY_ID", inputSearch.getCustomerId()).get("CUST_NAME"));
            inputSearch.setCustomerName(customerName);
        } else {
            inputSearch.setCustomerId("");
            inputSearch.setCustomerName("");
        }
        
        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        String row = String.valueOf(systemSetting.getResultRow());
        inputSearch.setRow(Integer.parseInt(row));
        inputSearch.setStartIndex(0);
        
        String nontaxinvoiceFlg=CommonUtils.toString(queryDAO.executeForObject("B_CPM.getNonTaxInvoiceShowFlg",null, String.class));
        resultObject.put("nontaxinvoiceFlg",nontaxinvoiceFlg);
        resultObject.put("inputSearchPlan", inputSearch);
        result.setResultObject(resultObject);
        result.setResultString("success");
        return result;
    }
    
    /**
     * input data to output data
     * 
     * @param input
     *            input data
     * @param output
     *            ouput data
     */
    private void copyParam(Map<String, Object> input, 
            Map<String, Object> output) {
        Set<String> keys = input.keySet();
        for (String key : keys) {
            output.put(key, input.get(key));
        }
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
