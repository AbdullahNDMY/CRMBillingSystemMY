/**
 * @(#)RP_E_SET_S01SubmitBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/08
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_set.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.G_ALT_P04;
import nttdm.bsys.e_set.dto.RP_E_SET_S01Input;
import nttdm.bsys.util.ApplicationContextProvider;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.springframework.context.ApplicationContext;

/**
 * @author gplai
 *
 */
public class RP_E_SET_S01SubmitBLogic_Test extends AbstractUtilTest {

    private RP_E_SET_S01SubmitBLogic logic;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new RP_E_SET_S01SubmitBLogic();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
        logic.setUpdateDAONuked(updateDAONuked);
        
        super.deleteAllData("M_BATCH_USER_ALERT");
        super.deleteAllData("M_BATCH_MAINTENANCE");
    }
    
    /**
     * case 1
     */
    public void testExecute01() {
        // set GB batch type record into M_BATCH_MAINTENANCE
//        String[][] mBatch = {
//                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
//                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
//                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
//                        "IS_DELETED", "ID_AUDIT" },
//                { "CC", "1", "Monthly", "1", "1", "1", "1",
//                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
//        // set batch alter user into M_BATCH_USER_ALERT
//        String[][] mAlterUser = {
//                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
//                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
//                        "IS_DELETED", "ID_AUDIT" },
//                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
//                        TEST_DAY_TODAY, USER_ID, "0", null } };
//        
//        super.insertData("M_BATCH_MAINTENANCE", mBatch);
//        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_save("1");
        input.setCCAlertMode("2");
        input.setCCMonths("2");
        input.setCCDay("2");
        input.setCCHour("2");
        input.setCCMinute("2");
        String[] ccUsers = {"Monthly_sysadmin"};
        input.setCCUsers(ccUsers);
        
        String[] sdUsers = {""};
        input.setSDUsers(sdUsers);
        
        String[] gbUsers = {""};
        input.setGBUsers(gbUsers);
        
        String[] saUsers = {""};
        input.setSAUsers(saUsers);
        
        String[] cbUsers = {""};
        input.setCBUsers(cbUsers);
        
        String[] arUsers = {""};
        input.setARUsers(arUsers);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case 2
     */
    public void testExecute02() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_save("1");
        input.setCCAlertMode("2");
        input.setCCMonths("2");
        input.setCCDay("2");
        input.setCCHour("2");
        input.setCCMinute("2");
        String[] ccUsers = {"Monthly_gplai"};
        input.setCCUsers(ccUsers);
        
        String[] sdUsers = {""};
        input.setSDUsers(sdUsers);
        
        String[] gbUsers = {""};
        input.setGBUsers(gbUsers);
        
        String[] saUsers = {""};
        input.setSAUsers(saUsers);
        
        String[] cbUsers = {""};
        input.setCBUsers(cbUsers);
        
        String[] arUsers = {""};
        input.setARUsers(arUsers);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case exception 
     */
    public void testExecute03() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_save("1");
        input.setCCAlertMode("2");
        input.setCCMonths("2");
        input.setCCDay("2");
        input.setCCHour("2");
        input.setCCMinute("2");
        String[] ccUsers = {"Monthly_gplai"};
        input.setCCUsers(ccUsers);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("info.ERR2SC004", result.getErrors().get().next().getKey());
    }
    
    /**
     * case runing G_ALT_P04
     */
    public void testExecute04() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_ccRun("1");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case runing G_ALT_P05
     */
    public void testExecute05() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_sdRun("1");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case runing G_BIL_P01
     */
    public void testExecute06() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_gbRun("1");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case runing E_EXP_F02
     */
    public void testExecute07() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_saRun("1");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case runing 
     */
    public void testExecute08() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_ssRun("1");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case runing G_CSB_P01
     */
    public void testExecute09() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_cbRun("1");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * case runing E_EXP_F01
     */
    public void testExecute10() {
        // set GB batch type record into M_BATCH_MAINTENANCE
        String[][] mBatch = {
                { "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
                        "DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "CC", "1", "Monthly", "1", "1", "1", "1",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null },
                { "SD", "1", "Monthly", "1", "1", "1", "1",
                    TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null }};
        // set batch alter user into M_BATCH_USER_ALERT
        String[][] mAlterUser = {
                { "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "1", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, USER_ID, "0", null },
                { "2", "SD", SYS_ADMIN, "USER", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, USER_ID, "0", null }};
        
        super.insertData("M_BATCH_MAINTENANCE", mBatch);
        super.insertData("M_BATCH_USER_ALERT", mAlterUser);
        
        RP_E_SET_S01Input input = new RP_E_SET_S01Input();
        input.setForward_arRun("1");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
}
