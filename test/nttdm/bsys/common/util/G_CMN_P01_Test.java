/**
 * @(#)G_CMN_P01_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/04/25
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import nttdm.bsys.c_cmn002.dto.C_CMN002Input;
import nttdm.bsys.c_cmn002.dto.C_CMN002Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class G_CMN_P01_Test extends AbstractUtilTest{
    
    private G_CMN_P01 gCmnP01;
    
    @Override
    protected void setUpData() throws Exception {
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("M_ALT_H");
    }

    /**
     * case 1:
     */
    public void testExecute01() throws Exception {
        String[][] testDataMGsetD1 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"DASHBD" , "1" , "1" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        String[][] mAltH1 =
        {
            {"ID_MSG", "MSG_TYPE", "SUBJECT", "ID_CREATOR", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"1", "NOTICE", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"2", "NOTICE", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"3", "TASK", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"4", "TASK", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                    "sysadmin" , "0"}};
        String[][] mAltD1 =
        {
            {"ID_MSG", "ID_USER", "DISPLAY_DATE", "IS_NEW", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"1", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"2", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"3", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"4", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                 "sysadmin" , "0"}};
        
        super.insertData("M_ALT_H", mAltH1);
        super.insertData("M_ALT_D", mAltD1);
        super.insertData("M_GSET_D", testDataMGsetD1);
        
        C_CMN002Input input = new C_CMN002Input();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        input.setId_user("sysadmin");
        
        gCmnP01 = new G_CMN_P01(queryDAO);
        
        C_CMN002Output result = gCmnP01.execute(input);
        assertEquals(2, result.getArray_notification().size());
        assertEquals(2, result.getArray_mytasks().size());
        assertEquals(4, result.getArray_sent().size());
        assertEquals("0", result.getNewmsg());
        
        gCmnP01.getQueryDAO();
        gCmnP01.setQueryDAO(queryDAO);
    }
    
    /**
     * case 2:
     */
    public void testExecute02() throws Exception {
        String[][] testDataMGsetD1 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"DASHBD" , "10" , "10" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        String[][] mAltH1 =
        {
            {"ID_MSG", "MSG_TYPE", "SUBJECT", "ID_CREATOR", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"1", "NOTICE", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"2", "NOTICE", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"}};
        String[][] mAltD1 =
        {
            {"ID_MSG", "ID_USER", "DISPLAY_DATE", "IS_NEW", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"1", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"2", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"}};
        
        super.insertData("M_ALT_H", mAltH1);
        super.insertData("M_ALT_D", mAltD1);
        super.insertData("M_GSET_D", testDataMGsetD1);
        
        C_CMN002Input input = new C_CMN002Input();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        input.setId_user("sysadmin");
        
        gCmnP01 = new G_CMN_P01(queryDAO);
        
        C_CMN002Output result = gCmnP01.execute(input);
        assertEquals(2, result.getArray_notification().size());
        assertEquals(0, result.getArray_mytasks().size());
        assertEquals(2, result.getArray_sent().size());
        assertEquals("0", result.getNewmsg());
        
        gCmnP01.getQueryDAO();
        gCmnP01.setQueryDAO(queryDAO);
    }
    
    /**
     * case 3:
     */
    public void testExecute03() throws Exception {
        String[][] testDataMGsetD1 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"DASHBD" , "1" , "10" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        String[][] mAltH1 =
        {
            {"ID_MSG", "MSG_TYPE", "SUBJECT", "ID_CREATOR", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"3", "TASK", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"4", "TASK", "abc", "sysadmin", "2011-07-28" , "2011-07-28" ,
                    "sysadmin" , "0"}};
        String[][] mAltD1 =
        {
            {"ID_MSG", "ID_USER", "DISPLAY_DATE", "IS_NEW", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"3", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"},
            {"4", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                 "sysadmin" , "0"}};
        
        super.insertData("M_ALT_H", mAltH1);
        super.insertData("M_ALT_D", mAltD1);
        super.insertData("M_GSET_D", testDataMGsetD1);
        
        C_CMN002Input input = new C_CMN002Input();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        input.setId_user("sysadmin");
        
        gCmnP01 = new G_CMN_P01(queryDAO);
        
        C_CMN002Output result = gCmnP01.execute(input);
        assertEquals(10, result.getArray_notification().size());
        assertEquals(10, result.getArray_mytasks().size());
        assertEquals(10, result.getArray_sent().size());
        assertEquals("0", result.getNewmsg());
        
        gCmnP01.getQueryDAO();
        gCmnP01.setQueryDAO(queryDAO);
    }
}
