/**
 * @(#)B_CPM_S01DownloadBLogic_Test.java
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

import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * @author gplai
 *
 */
public class B_CPM_S01DownloadBLogic_Test extends AbstractUtilTest {

    private B_CPM_S01DownloadBLogic logic;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CPM_S01DownloadBLogic();
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("RESOURCESMAINT");
    }

    public void testExecute01(){
        String[][] M_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "SYSTEMBASE", "1", "", "0",
                  TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL",
                  "SG", null, "1", } };
        String[][] M_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "SYSTEMBASE", "System Base", "Settings to define bas","0", 
                  TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        String[][] RESOURCESMAINT = {
                { "ID", "CATEGORY", "SEQ", "RESOURCE_ID", "VALUE", "RES_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATE", "ID_LOGIN" },

                { "1", "EXP", "1", "PLNSG", "PlanInfo_yymmddhhmmss.csv", "B_CPM_E01 - Export Result for Plan info header",
                        "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "2", "HDR", "1", "PLNSG", "No", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "3", "HDR", "2", "PLNSG", "Cust ID", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "4", "HDR", "3", "PLNSG", "Customer Name", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "5", "HDR", "4", "PLNSG", "Sub. ID", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "6", "HDR", "5", "PLNSG", "BAC No", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "7", "HDR", "6", "PLNSG", "Bill Inst.", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "8", "HDR", "7", "PLNSG", "Cur", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "9", "HDR", "8", "PLNSG", "Service From", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "10", "HDR", "9", "PLNSG", "Service To", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "11", "HDR", "10", "PLNSG", "Service Status", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "12", "HDR", "11", "PLNSG", "Billing Description", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "13", "HDR", "12", "PLNSG", "Item Description", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "14", "HDR", "13", "PLNSG", "Category", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "15", "HDR", "14", "PLNSG", "Service", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "16", "HDR", "15", "PLNSG", "Plan", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "17", "HDR", "16", "PLNSG", "Quantity", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "18", "HDR", "17", "PLNSG", "Unit Price", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" },
                { "19", "HDR", "18", "PLNSG", "Amount", "",
                    "0", "2012-03-14", "2012-03-14", "sysadmin" }};
        
        super.insertData("M_GSET_H", M_GSET_H);
        super.insertData("M_GSET_D", M_GSET_D);
        super.insertData("RESOURCESMAINT", RESOURCESMAINT);
        
        //HttpServletRequest aa = ;
    }
}
