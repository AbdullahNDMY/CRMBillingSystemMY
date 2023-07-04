/**
 * @(#)G_SET_P01_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/04/23
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import nttdm.bsys.common.bean.T_SET_BATCH;

/**
 * @author gplai
 *
 */
public class G_SET_P01_Test extends AbstractUtilTest {

    /**
     * gSetP02
     */
    private G_SET_P01 gSetP01 = null;
    
    @Override
    protected void setUpData() throws Exception {
        super.deleteAllData("T_SET_BATCH_LOG");
        super.deleteAllData("T_SET_BATCH");
        super.deleteAllData("M_GSET_D");
    }

    /**
     * case 1:status is 1 and no previous result
     */
    public void testExecute01() {
        gSetP01 = new G_SET_P01(queryDAO, updateDAO);
        T_SET_BATCH tSetBatch = new T_SET_BATCH();
        tSetBatch.setBATCH_TYPE("GB");
        tSetBatch.setFILENAME("test");
        tSetBatch.setMessage("");
        tSetBatch.setID_LOGIN("sysadmin");
        tSetBatch.setSTATUS("1");
        
        int batchID = gSetP01.G_SET_P01_GetIdBatch(tSetBatch).getBatch_id();
        Integer batchId = queryDAO.executeForObject("TEST.SELECT.G_SET_P01.SQL1", null, Integer.class);
        assertEquals(batchId.intValue(), batchID);
    }
    
    /**
     * case 2:status is 1 and has previous result
     */
    public void testExecute02() {
        String[][] testDataTSetBatch =
        {
            {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
            {"1" , "GB" , "1" , "AA" , "2011-08-25 14:00:00" , "2011-08-25 14:00:00" ,
                "sysadmin"}};
        
        String[][] testDataMGsetD =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"BATCH_TIME_INTERVAL" , "1" , "10" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        
        super.insertData("M_GSET_D", testDataMGsetD);
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        
        gSetP01 = new G_SET_P01(queryDAO, updateDAO);
        T_SET_BATCH tSetBatch = new T_SET_BATCH();
        tSetBatch.setBATCH_TYPE("GB");
        tSetBatch.setFILENAME("test");
        tSetBatch.setMessage("");
        tSetBatch.setID_LOGIN("sysadmin");
        tSetBatch.setSTATUS("1");
        
        int batchID = gSetP01.G_SET_P01_GetIdBatch(tSetBatch).getBatch_id();
        Integer batchId = queryDAO.executeForObject("TEST.SELECT.G_SET_P01.SQL1", null, Integer.class);
        assertEquals(batchId.intValue(), batchID);
    }
    
    /**
     * case 3:status is 1 and has previous result but BATCH_TIME_INTERVAL <
     */
    public void testExecute03() {
        String[][] testDataTSetBatch =
        {
            {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
            {"1" , "GB" , "1" , "AA" , TEST_DAY_TODAY , TEST_DAY_TODAY ,
                "sysadmin"}};
        
        String[][] testDataMGsetD =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"BATCH_TIME_INTERVAL" , "1" , "1152890" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        
        super.insertData("M_GSET_D", testDataMGsetD);
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        
        gSetP01 = new G_SET_P01(queryDAO, updateDAO);
        T_SET_BATCH tSetBatch = new T_SET_BATCH();
        tSetBatch.setBATCH_TYPE("GB");
        tSetBatch.setFILENAME("test");
        tSetBatch.setMessage("");
        tSetBatch.setID_LOGIN("sysadmin");
        tSetBatch.setSTATUS("1");
        
        int batchID = gSetP01.G_SET_P01_GetIdBatch(tSetBatch).getBatch_id();
        assertEquals(-1, batchID);
    }
    
    /**
     * case 4:status is 2 and message is null
     */
    public void testExecute04() {
        String[][] testDataTSetBatch =
        {
            {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
            {"1" , "GB" , "1" , "AA" , TEST_DAY_TODAY , TEST_DAY_TODAY ,
                "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        
        gSetP01 = new G_SET_P01(queryDAO, updateDAO);
        T_SET_BATCH tSetBatch = new T_SET_BATCH();
        tSetBatch.setBATCH_TYPE("GB");
        tSetBatch.setFILENAME("test");
        tSetBatch.setMessage("");
        tSetBatch.setID_LOGIN("sysadmin");
        tSetBatch.setSTATUS("2");
        tSetBatch.setID_BATCH("1");
        
        int batchID = gSetP01.G_SET_P01_GetIdBatch(tSetBatch).getBatch_id();
        assertEquals(1, batchID);
    }
    
    /**
     * case 5:status is 2 and message is not null
     */
    public void testExecute05() {
        String[][] testDataTSetBatch =
        {
            {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
            {"1" , "GB" , "1" , "AA" , TEST_DAY_TODAY , TEST_DAY_TODAY ,
                "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        
        gSetP01 = new G_SET_P01(queryDAO, updateDAO);
        T_SET_BATCH tSetBatch = new T_SET_BATCH();
        tSetBatch.setBATCH_TYPE("GB");
        tSetBatch.setFILENAME("test");
        tSetBatch.setMessage("test message info");
        tSetBatch.setID_LOGIN("sysadmin");
        tSetBatch.setSTATUS("2");
        tSetBatch.setID_BATCH("1");
        
        int batchID = gSetP01.G_SET_P01_GetIdBatch(tSetBatch).getBatch_id();
        assertEquals(1, batchID);
    }
    
    /**
     * case 6:status is 3
     */
    public void testExecute06() {
        String[][] testDataTSetBatch =
        {
            {"ID_BATCH" , "BATCH_TYPE" , "STATUS" , "FILENAME" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
            {"1" , "GB" , "1" , "AA" , TEST_DAY_TODAY , TEST_DAY_TODAY ,
                "sysadmin"}};
        super.insertData("T_SET_BATCH", testDataTSetBatch);
        
        gSetP01 = new G_SET_P01(queryDAO, updateDAO);
        T_SET_BATCH tSetBatch = new T_SET_BATCH();
        tSetBatch.setBATCH_TYPE("GB");
        tSetBatch.setFILENAME("test");
        tSetBatch.setMessage("test message info");
        tSetBatch.setID_LOGIN("sysadmin");
        tSetBatch.setSTATUS("3");
        tSetBatch.setID_BATCH("1");
        
        int batchID = gSetP01.G_SET_P01_GetIdBatch(tSetBatch).getBatch_id();
        assertEquals(1, batchID);
    }
}
