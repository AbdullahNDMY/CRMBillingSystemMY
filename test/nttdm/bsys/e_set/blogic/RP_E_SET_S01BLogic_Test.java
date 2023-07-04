/**
 * @(#)RP_E_SET_S01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/10/29
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_set.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_set.dto.RP_E_SET_S01Input;

/**
 * @author gplai
 *
 */
public class RP_E_SET_S01BLogic_Test extends AbstractUtilTest {

    private RP_E_SET_S01BLogic logic;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new RP_E_SET_S01BLogic();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
        
        super.deleteAllData("M_BATCH_USER_ALERT");
        super.deleteAllData("M_BATCH_MAINTENANCE");
    }

    /**
     * test BATCH_ID is CC
     */
    public void testExecute01() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null } };
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is CC
     */
    public void testExecute02() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", null, "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is SD
     */
    public void testExecute03() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is SD
     */
    public void testExecute04() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "SD", "1", null, "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is GB
     */
    public void testExecute05() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "GB", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is GB
     */
    public void testExecute06() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "GB", "1", null, "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is SA
     */
    public void testExecute07() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "SA", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is SA
     */
    public void testExecute08() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "SA", "1", null, "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is SS
     */
    public void testExecute09() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "SS", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is SS
     */
    public void testExecute10() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "SS", "1", null, "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is CB
     */
    public void testExecute11() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CB", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is CB
     */
    public void testExecute12() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CB", "1", null, "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is AR
     */
    public void testExecute13() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "AR", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * test BATCH_ID is AR
     */
    public void testExecute14() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "AR", "1", null, "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
}
