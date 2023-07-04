/**
 * @(#)B_CPM_S01CheckExportBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/08
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_cpm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm_en.dto.InputSearchPlan;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.BillingSystemConstants;

/**
 * @author gplai
 *
 */
public class B_CPM_S01CheckExportBLogic_Test extends AbstractUtilTest {

    private B_CPM_S01CheckExportBLogic logic;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CPM_S01CheckExportBLogic();
        logic.setQueryDAO(queryDAO);
        
        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        // delete all exist data
        super.deleteAllData("T_CUST_PLAN_SVC");
        super.deleteAllData("T_CUST_PLAN_LINK");
        super.deleteAllData("T_CUST_PLAN_D");
        super.deleteAllData("T_CUST_PLAN_H");
        
        //used by delete T_SUBSCRIPTION_INFO
        super.deleteAllData("T_TEAMWORK");
        super.deleteAllData("T_MAIL_ACCOUNT");
        super.deleteAllData("T_MAIL_ADDRESS");
        super.deleteAllData("T_CPE_CONF_D");
        super.deleteAllData("T_CPE_CONF_H");
        super.deleteAllData("T_SERVER_INFO_D");
        super.deleteAllData("T_SERVER_INFO_H");
        super.deleteAllData("T_FIREWALL_POLICY");
        super.deleteAllData("T_FIREWALL_INT_IP");
        super.deleteAllData("T_FIREWALL");
        super.deleteAllData("T_MRTG");
        super.deleteAllData("T_INST_ADR");
        super.deleteAllData("T_IT_CONTACT_D");
        super.deleteAllData("T_IT_CONTACT_H");
        super.deleteAllData("T_DNS_ZONE_REC");
        super.deleteAllData("T_DNS_ZONE");
        super.deleteAllData("T_RACK_POWER_D");
        super.deleteAllData("T_RACK_POWER_H");
        super.deleteAllData("T_FTP_INT");
        super.deleteAllData("T_SUBSCRIPTION_INFO");
    }

    /**
     * search result is not null
     */
    @SuppressWarnings("unchecked")
    public void testExecute01(){
        String[][] M_CUST = {
                { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", 
                    "CO_REGNO", "CO_WEBSITE", "CO_EMAIL", 
                    "CO_TEL_NO", "CO_FAX_NO", "INTER_COMP", 
                    "IS_EXPORTED", "IS_DELETED", "DATE_CREATED", 
                    "DATE_UPDATED", "ID_LOGIN", "SALUTATION", 
                    "CUST_ID_NO", "CUST_BIRTH_DATE", "HOME_TEL_NO", 
                    "HOME_FAX_NO", "PRINT_STATEMENT", "GBOC_ACC_NO", 
                    "OTHERS_REF_NO", "CUSTOMER_TYPE", "SALES_FORCE_ACC_ID", 
                    "AFFILIATE_CODE", "ID_AUDIT", "MOBILE_NO" },
                { "1", "0123456789", "Duy Duong", 
                   "0123456789", "http://www.nttdata.com.vn", "duong.nguyen@nttdata.com.vn", 
                   "0123456789", "0123456789", "0", 
                   "0", "0", TEST_DAY_TODAY,
                   TEST_DAY_TODAY, "USERFULL", "", 
                   "", null, "", 
                   "", "0", "0123456789", 
                   "0123456789", "CORP", "0123456789", 
                   "", null, "" } };
        String[][] T_CUST_PLAN_H = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN" },
                { "43", "1", "PS1", "NP", 
                   null, "IN", null, 
                   "2", "1", "268", 
                   "357", "4", "CQ", 
                   "MYR", "BAM", "12", 
                   "0", "0","2011-08-02 14:13:46", 
                   "2011-08-02 14:13:46","USERFULL"} };
        String[][] T_CUST_PLAN_D = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", 
                        "MIN_SVC_PERIOD","MIN_SVC_START", "MIN_SVC_END", 
                        "CONTRACT_TERM","CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN" },
                { "2197", "43", "PS3", 
                  "2011-5-11", "2011-5-11", "1", 
                  "1","2011-5-11", "2011-5-11", 
                  "M", "23", "0", 
                  "25", "0",null, 
                  "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
                  "USERFULL" }, 
                { "2198", "43", "PS3", 
                  "2011-5-11", "2011-5-11", "1", 
                  "1","2011-5-11", "2011-5-11", 
                  "M", "23", "0", 
                  "25", "0",null, 
                  "0", "2011-8-2  16:14:21", "2011-8-10  16:31:43",
                  "USERFULL" }, };
        String[][] T_CUST_PLAN_LINK = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "QUANTITY",
                        "UNIT_PRICE", "TOTAL_AMOUNT", "JOB_NO", 
                        "ID_PLAN", "PLAN_NAME", "PLAN_DESC", 
                        "ID_PLAN_GRP","ITEM_GRP_NAME", "SVC_LEVEL1", 
                        "SVC_LEVEL2","RATE_TYPE", "RATE_MODE", 
                        "RATE", "APPLY_GST","IS_DELETED", 
                        "DATE_CREATED", "DATE_UPDATED","ID_LOGIN",  
                        "ITEM_TYPE" },
                { "23", "2197", "12", 
                  "12", "144", "GBIL-001", 
                  "1191", "1", "sd sdf ssdff ds sdf ", 
                  "1", "1", "268", 
                  "311", "EX", "5", 
                  "23", "1", "0", 
                  "2011-08-02 16:14:35", "2011-08-10 16:31:43", "USERFULL",  
                  "S" },
                { "24", "2198", "12", 
                  "12", "144", "GBIL-001", 
                  "1191", "1", "sd sdf ssdff ds sdf ", 
                  "1", "1", "268", 
                  "311", "EX", "5", 
                  "23", "1", "0", 
                  "2011-08-02 16:14:35", "2011-08-10 16:31:43", "USERFULL",  
                  "S" },
                { "25", "2198", "12",
                  "12", "144", "GBIL-001", 
                  "1191", "1", "sd sdf ssdff ds sdf ", 
                  "1", "1", "268",
                  "311", "EX", "5", 
                  "23", "1", "0", 
                  "2011-08-02 16:14:35", "2011-08-10 16:31:43", "USERFULL",  
                  "S" } 
                        };
        String[][] T_CUST_PLAN_SVC =
        {
            {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"1" , "23" , "1" , 
              "1" , "1" , "0" , 
              "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
            {"2" , "23" , "1" , 
             "1" , "1" , "0" , 
             "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
            {"3" , "24" , "1" , 
             "1" , "1" , "0" , 
             "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
            {"4" , "24" , "1" ,
             "1" , "1" , "0" , 
             "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
            {"5" , "25" , "1" , 
             "1" , "1" , "0" , 
             "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
            {"6" , "25" , "1" , 
             "1" , "1" , "0" , 
             "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "43", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", AUDIT_VALUE, "9999" } };
        
        super.insertData("M_CUST", M_CUST);
        super.insertData("T_CUST_PLAN_H", T_CUST_PLAN_H);
        super.insertData("T_CUST_PLAN_D", T_CUST_PLAN_D);
        super.insertData("T_CUST_PLAN_LINK", T_CUST_PLAN_LINK);
        super.insertData("T_CUST_PLAN_SVC", T_CUST_PLAN_SVC);
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        
        InputSearchPlan inputSearchPlan = new InputSearchPlan();
        HashMap<String, Object> inputMap = new HashMap<String, Object>();
        inputMap.put("inputSearchPlan", inputSearchPlan);
        
        BLogicResult result = logic.execute(inputMap);
        assertEquals(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS, result.getResultString());
        assertEquals(3, ((List<Map<String, Object>>)((Map<String, Object>)result.getResultObject()).get("searchResult")).size());
    }
    
    /**
     * search result is null
     */
    @SuppressWarnings("unchecked")
    public void testExecute02(){
        
        InputSearchPlan inputSearchPlan = new InputSearchPlan();
        HashMap<String, Object> inputMap = new HashMap<String, Object>();
        inputMap.put("inputSearchPlan", inputSearchPlan);
        
        BLogicResult result = logic.execute(inputMap);
        assertEquals(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE, result.getResultString());
        assertEquals(0, ((List<Map<String, Object>>)((Map<String, Object>)result.getResultObject()).get("searchResult")).size());
    }
}
