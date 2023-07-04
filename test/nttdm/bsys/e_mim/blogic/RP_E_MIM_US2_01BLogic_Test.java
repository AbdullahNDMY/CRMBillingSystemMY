/**
 * @(#)RP_E_MIM_US2_01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/03
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mim.blogic;

import java.io.IOException;
import java.util.Calendar;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_01Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_01Output;

/**
 * @author gplai
 *
 */
public class RP_E_MIM_US2_01BLogic_Test extends AbstractUtilTest {

    private RP_E_MIM_US2_01BLogic logic;
    private static String SYSTEMDATE;
    
    static {
        // get system date
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month++;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        SYSTEMDATE = year + "-" + month + "-" + day;
    }
    @Override
    protected void setUpData() throws Exception {
       logic = new RP_E_MIM_US2_01BLogic();
       logic.setQueryDAO(queryDAO);
       logic.setUpdateDAO(updateDAO);
       
       super.deleteAllData("T_CLC_IMP_MONTHLY_SUM");
       super.deleteAllData("T_CLC_IMP_HD");
    }
    
    public void testExecute01() {
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TOMORROW, "sysadmin" } };
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        
        RP_E_MIM_US2_01Input input = new RP_E_MIM_US2_01Input();
        input.setMonth("01");
        input.setYear("2012");
        
        BLogicResult result = logic.execute(input);
        RP_E_MIM_US2_01Output output = (RP_E_MIM_US2_01Output)result.getResultObject();
        
        assertEquals(1, output.getListHistories().size());
    }
    
    public void testExecute02() throws IOException, InterruptedException {
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TOMORROW, "sysadmin" } };
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        
        // update system date to 2011-08-29
        Process processStart = Runtime.getRuntime().exec(
                "cmd /c date 2011-01-29");
        processStart.waitFor();
        
        RP_E_MIM_US2_01Input input = new RP_E_MIM_US2_01Input();
        
        BLogicResult result = logic.execute(input);

        // back up system date
        Process processEnd = Runtime.getRuntime().exec(
                "cmd /c date " + SYSTEMDATE);
        processEnd.waitFor();
        
        RP_E_MIM_US2_01Output output = (RP_E_MIM_US2_01Output)result.getResultObject();
        
        assertEquals(1, output.getListHistories().size());
    }
    
    public void testExecute03() throws IOException, InterruptedException {
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TOMORROW, "sysadmin" } };
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        
        // update system date to 2011-08-29
        Process processStart = Runtime.getRuntime().exec(
                "cmd /c date 2011-08-29");
        processStart.waitFor();
        
        RP_E_MIM_US2_01Input input = new RP_E_MIM_US2_01Input();
        
        BLogicResult result = logic.execute(input);

        // back up system date
        Process processEnd = Runtime.getRuntime().exec(
                "cmd /c date " + SYSTEMDATE);
        processEnd.waitFor();
        
        RP_E_MIM_US2_01Output output = (RP_E_MIM_US2_01Output)result.getResultObject();
        
        assertEquals(1, output.getListHistories().size());
    }
    
    public void testExecute04() throws IOException, InterruptedException {
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TOMORROW, "sysadmin" } };
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        
        // update system date to 2011-08-29
        Process processStart = Runtime.getRuntime().exec(
                "cmd /c date 2011-10-29");
        processStart.waitFor();
        
        RP_E_MIM_US2_01Input input = new RP_E_MIM_US2_01Input();
        
        BLogicResult result = logic.execute(input);

        // back up system date
        Process processEnd = Runtime.getRuntime().exec(
                "cmd /c date " + SYSTEMDATE);
        processEnd.waitFor();
        
        
        RP_E_MIM_US2_01Output output = (RP_E_MIM_US2_01Output)result.getResultObject();
        
        assertEquals(1, output.getListHistories().size());
    }
}
