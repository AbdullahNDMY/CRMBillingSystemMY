/**
 * @(#)B_CPM_S04Common.java
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

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.CodeListLoader;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.InputSearchPlan;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

/**
 * @author gplai
 *
 */
public class B_CPM_S04Common implements B_CPM_CONSTANT {
    private QueryDAO queryDAO;
    
    public B_CPM_S04Common(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * do Search
     * @param param
     * @param resultObject
     */
    public void doSearch(Map<String, Object> param, Map<String, Object> resultObject) {
        InputSearchPlan inputSearch = (InputSearchPlan)param.get("inputSearchPlan");
        inputSearch = this.processInputSeach(inputSearch);
        //get search result
        List<Map<String, Object>> searchResult = null;
        
        searchResult = queryDAO.executeForMapList(NAMESPACE + "B_CPM_S04_SEARCH_CUST_PLAN_H", inputSearch, inputSearch.getStartIndex(), inputSearch.getRow());
        
        String totalCount = queryDAO.executeForObject(NAMESPACE + "B_CPM_S04_SEARCH_CUST_PLAN_H_COUNT", inputSearch, String.class);
        inputSearch.setTotalCount(totalCount);
        
        // Process Bill Inst
        CodeListLoader codeList= (CodeListLoader) ApplicationContextProvider.getApplicationContext().getBean("BILL_INSTRUCTION_LIST");
        CodeBean[] results = codeList != null ? codeList.getCodeBeans() : null;
        
        for (Map<String, Object> record : searchResult) {
            for(CodeBean cBean : results){
                if(record.get("BILL_INSTRUCT").equals(cBean.getId())){
                    String fm = cBean.getName();
                    fm = fm.substring(fm.indexOf('(')+1, fm.indexOf(')'));
                    record.put("BILL_INSTRUCT",fm);
                    break;
                }   
            }
        }

        //get detail customer plan
        for (Map<String, Object> plan : searchResult) {
            List<Map<String, Object>> subPlanList = queryDAO.executeForMapList(NAMESPACE + "B_CPM_S04_SEARCH_CUST_PLAN_D", plan);
            plan = this.processCustomerPlanH(plan);
            
            plan.put("SUB_PLAN", subPlanList);
            //get link customer plan
            double total = 0.00;
            for (Map<String, Object> subPlan : subPlanList) {
                Integer length = CommonUtils.getSystemLength(queryDAO);
                subPlan.put("BILL_DESC", CommonUtils.formatString(subPlan.get("BILL_DESC"), length));
                List<Map<String, Object>> subPlanLinkList = queryDAO.executeForMapList(NAMESPACE + "B_CPM_S04_SEARCH_CUST_PLAN_LINK", subPlan);
                subPlan.put("SUB_PLAN_LINK", subPlanLinkList);
                double sub_total = 0.00;    
                for (Map<String, Object> subPlanLink : subPlanLinkList) {
                    subPlanLink.put("ITEM_DESC", CommonUtils.formatString(subPlanLink.get("ITEM_DESC"), length));
                    sub_total += Double.parseDouble(subPlanLink.get("TOTAL_AMOUNT").toString());
                }
                subPlan.put("TOTAL_AMOUNT", sub_total);
                total += sub_total;
            }
            plan.put("TOTAL_AMOUNT", total);
        }
        
        
        
        Map<String, Object> cpmTransType = this.queryDAO.executeForMap(NAMESPACE + "GET_CPM_TRANS_TYPE", null);
        resultObject.put("cpmTransType", CommonUtils.toString(cpmTransType.get("SET_VALUE")));
        
        String nontaxinvoiceFlg=CommonUtils.toString(queryDAO.executeForObject("B_CPM.getNonTaxInvoiceShowFlg",null, String.class));
        resultObject.put("nontaxinvoiceFlg",nontaxinvoiceFlg);
        
        String customerName = CommonUtils.toString(queryDAO.executeForMap("B_CPM.B_CPM_S04_SELECT_CUST_BY_ID", inputSearch.getCustomerId()).get("CUST_NAME"));
        inputSearch.setCustomerName(customerName);
        
        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        String row = String.valueOf(systemSetting.getResultRow());
        inputSearch.setRow(Integer.parseInt(row));
        
        resultObject.put("inputSearchPlan", inputSearch);
        resultObject.put("searchResult", searchResult);
    }
    
    /**
     * 
     * @param inputSearch
     * @return
     */
    private InputSearchPlan processInputSeach(InputSearchPlan inputSearch) {
        //Customer Information
        if (inputSearch != null) {
            inputSearch.setCustomerId(Trim(inputSearch.getCustomerId()));
            inputSearch.setCustomerName(Trim(inputSearch.getCustomerName()));
            
            //Customer Plan Information
            inputSearch.setSubscriptionId(Trim(inputSearch.getSubscriptionId()));
            inputSearch.setBillingAccount(Trim(inputSearch.getBillingAccount()));
        }
        return inputSearch;
    }
    
    private String Trim(String value) {
        if (value != null) {
            value = value.trim();
        }
        return value;
    }
    
    /**
     * 
     * @param plan
     * @return
     */
    private Map<String, Object> processCustomerPlanH(Map<String, Object> plan) {
        Object bacNo = plan.get("ID_BILL_ACCOUNT");
        if (bacNo == null || bacNo.equals("") || bacNo.toString().trim().equals("NEW")) {
            plan.put("ID_BILL_ACCOUNT", "-");
        }
        return plan;
    }
}
