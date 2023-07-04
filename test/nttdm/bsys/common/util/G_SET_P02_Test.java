/**
 * @(#)G_SET_P02_Test.java
 * Billing System
 * Version 1.00
 * Created 2011/08/11
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.Calendar;
import java.util.Date;

import nttdm.bsys.common.dao.UpdateDAOiBatisNukedImpl;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * Test class for nttdm.bsys.common.util.G_SET_P02
 * 
 * @author qinjinh
 */
public class G_SET_P02_Test extends AbstractUtilTest {

    UpdateDAOiBatisNukedImpl updateDAOiBatisNukedImpl;

    BillingSystemUserValueObject uvo;

    /**
     * gSetP02
     */
    private G_SET_P02 gSetP02 = null;

    /**
     * TIME
     */
    private static String TIME = null;

    /**
     * DATE
     */
    private static String DATE = null;

    /**
     * MONTH
     */
    private static String MONTH = null;

    /**
     * HOUR
     */
    private static String HOUR = null;

    /**
     * MINUTE
     */
    private static String MINUTE = null;

    /**
     * MAXDATE
     */
    private static String MAXDATE = null;

    /**
     * get daily time
     * 
     * @return
     * daily_time
     */
    private String getHourMinutee(int addValue) {

        Calendar calen = Calendar.getInstance();
        HOUR = String.valueOf(calen.get(Calendar.HOUR_OF_DAY));
        MINUTE = String.valueOf(calen.get(Calendar.MINUTE) + addValue);

        return HOUR + MINUTE;

    }

    private void setDateData() {

        Calendar calen = Calendar.getInstance();
        HOUR = String.valueOf(calen.get(Calendar.HOUR_OF_DAY));
        MINUTE = String.valueOf(calen.get(Calendar.MINUTE));
        MONTH = String.valueOf(calen.get(Calendar.MONTH) + 1);
        DATE = String.valueOf(calen.get(Calendar.DATE));
        MAXDATE =
            String.valueOf(calen.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected void setUpData() {

        // M_BATCH_USER_ALERT
        super.deleteAllData("M_BATCH_USER_ALERT");

        // M_BATCH_MAINTENANCE
        super.deleteAllData("M_BATCH_MAINTENANCE");

        // M_GSET_D
        super.deleteAllData("M_GSET_D");

        // M_GSET_H
        super.deleteAllData("M_GSET_H");
        /*
         * 【Table "M_GSET_H"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */
        String[][] testDataMGsetH =
            {
                {"ID_SET" , "SET_NAME" , "SET_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {"BATCH_RUNTIME" , "NameAAA" , "DDDD" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN},
                {"BATCH_TIME_INTERVAL" , "NameAAA" , "DDDD" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN}};
        super.insertData("M_GSET_H", testDataMGsetH);

    }

    /***/
    public void testExecute01() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="DAILY"
         * BATCH_ID="SD"
         * ALERT_MODE="1"
         */
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"SD" , "1" , "DAILY" , "1" , "1" , "1" , "1" , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
                {"BATCH_TIME_INTERVAL" , "1" , "2" , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);

        gSetP02.excute_batch();
    }
    
    public void testExecute08() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="DAILY"
         * BATCH_ID="SD"
         * ALERT_MODE="1"
         */
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"SD" , "1" , "DAILY" , "1" , "1" , "1" , "1" , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
                {"BATCH_TIME_INTERVAL" , "1" , "2" , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        
        super.deleteAllData("T_BATCH_SCH");
        String[][] T_BATCH_SCH = {
                { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                    "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                    "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                    "ID_AUDIT" },
                { "G_GIR_P01", "1", "32", "0", "0", "32", "0",
                    "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                    null } };
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);

        gSetP02.excute_batch();
    }
    
    public void testExecute09() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="DAILY"
         * BATCH_ID="SD"
         * ALERT_MODE="1"
         */
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"SD" , "1" , "DAILY" , "1" , "1" , "1" , "1" , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
                {"BATCH_TIME_INTERVAL" , "1" , "2" , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        
        Calendar calen = Calendar.getInstance();
        int current_hours = calen.get(Calendar.HOUR_OF_DAY);
        int current_minute = calen.get(Calendar.MINUTE);
        int current_day = calen.get(Calendar.DATE);
        
        super.deleteAllData("T_BATCH_SCH");
        String[][] T_BATCH_SCH = {
                { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                    "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                    "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                    "ID_AUDIT" },
                { "G_GIR_P01", "1", String.valueOf(current_day), 
                        String.valueOf(current_hours), String.valueOf(current_minute), "32", "0",
                    "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                    null } };
        super.insertData("T_BATCH_SCH", T_BATCH_SCH);
        
        String[][] T_BATCH_SCH1 = {
                { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                    "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                    "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                    "ID_AUDIT" },
                { "G_CIT_P01", "1", String.valueOf(current_day), 
                        String.valueOf(current_hours), String.valueOf(current_minute), "32", "0",
                    "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                    null } };
        super.insertData("T_BATCH_SCH", T_BATCH_SCH1);
        
        String[][] T_BATCH_SCH2 = {
                { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                    "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                    "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                    "ID_AUDIT" },
                { "G_SGP_P01", "1", String.valueOf(current_day), 
                        String.valueOf(current_hours), String.valueOf(current_minute), "32", "0",
                    "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                    null } };
        super.insertData("T_BATCH_SCH", T_BATCH_SCH2);
        
        String[][] T_BATCH_SCH3 = {
                { "ID_BATCH_TYPE", "IS_ACTIVE", "EXEC_DAY", "EXEC_HOUR",
                    "EXEC_MINUTE", "GIRO_DEDUCT_DAY", "ID_COM_BANK",
                    "DATE_UPDATED", "ID_LOGIN", "IS_DELETED", "DATE_CREATED",
                    "ID_AUDIT" },
                { "G_RAD_P01", "1", String.valueOf(current_day), 
                        String.valueOf(current_hours), String.valueOf(current_minute), "32", "0",
                    "2011-09-13 10:55:54", "sysadmin", "0", "2011-09-06 10:52:34",
                    null } };
        super.insertData("T_BATCH_SCH", T_BATCH_SCH3);

        gSetP02.excute_batch();
    }

    public void testExecute02() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="DAILY"
         * BATCH_ID="SD"
         * ALERT_MODE="0"
         */
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"SD" , "0" , "DAILY" , "1" , "1" , "1" , "1" , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
                {"BATCH_TIME_INTERVAL" , "1" , "2" , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        gSetP02.excute_batch();

    }

    public void testExecute03() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="DAILY"
         * BATCH_ID="WE"
         * ALERT_MODE="1"
         */
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"WE" , "1" , "DAILY" , "1" , "1" , "1" , "1" , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);
        
        String[][] testDataMBatchMaintenance1 =
        {
            {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "ID_AUDIT"} ,
            {"PS" , "1" , "DAILY" , "1" , "1" , "1" , "1" , "0" ,
                "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance1);
        
        String[][] testDataMBatchMaintenance2 =
        {
            {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "ID_AUDIT"} ,
            {"SD" , "1" , "DAILY" , "1" , "1" , "1" , "1" , "0" ,
                "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance2);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null},
                {"BATCH_TIME_INTERVAL" , "1" , "2" , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        gSetP02.excute_batch();

    }

    public void testExecute04() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        setDateData();
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="MONTHLY"
         * DAY_OF_MONTH=last day of the month of current system date
         * TIME_HOUR=current system datetime hour
         * TIME_MINUTE=current system datetime minute
         * BATCH_ID="CB"
         */

        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"CB" , "1" , "MONTHLY" , "1" , MAXDATE , HOUR , MINUTE , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        gSetP02.excute_batch();

    }

    public void testExecute05() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="MONTHLY"
         * DAY_OF_MONTH=last day of the month of current system date
         * TIME_HOUR=current system datetime hour
         * TIME_MINUTE=current system datetime minute
         * BATCH_ID="AR"
         */
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"AR" , "1" , "MONTHLY" , "1" , MAXDATE , HOUR , MINUTE , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        gSetP02.excute_batch();

    }
    
    public void testExecute06() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="MONTHLY"
         * DAY_OF_MONTH=last day of the month of current system date
         * TIME_HOUR=current system datetime hour
         * TIME_MINUTE=current system datetime minute
         * BATCH_ID="AR"
         */
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"AR" , "1" , "MONTHLY" , "1" , "32" , HOUR , MINUTE , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        gSetP02.excute_batch();
    }
    
    public void testExecute07() {

        gSetP02 = new G_SET_P02(queryDAO, updateDAO, updateDAOiBatisNukedImpl);
        /*
         * 【Table "M_BATCH_MAINTENANCE"'s records】
         * has one record in accordance with
         * FREQUENCY_MODE="MONTHLY"
         * DAY_OF_MONTH=last day of the month of current system date
         * TIME_HOUR=current system datetime hour
         * TIME_MINUTE=current system datetime minute
         * BATCH_ID="AR"
         */
        Calendar calen = Calendar.getInstance();
        int current_hours = calen.get(Calendar.HOUR_OF_DAY);
        int current_minute = calen.get(Calendar.MINUTE);
        int current_day = calen.get(Calendar.DATE);
        
        String[][] testDataMBatchMaintenance =
            {
                {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                    "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                    "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                    "ID_LOGIN" , "ID_AUDIT"} ,
                {"AR" , "1" , "MONTHLY" , "1" , String.valueOf(current_day) , 
                        String.valueOf(current_hours) , String.valueOf(current_minute) , "0" ,
                    "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance);
        
        String[][] testDataMBatchMaintenance1 =
        {
            {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "ID_AUDIT"} ,
            {"CC" , "1" , "MONTHLY" , "1" , String.valueOf(current_day) , 
                    String.valueOf(current_hours) , String.valueOf(current_minute) , "0" ,
                "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance1);
        
        String[][] testDataMBatchMaintenance2 =
        {
            {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "ID_AUDIT"} ,
            {"SD" , "1" , "MONTHLY" , "1" , String.valueOf(current_day) , 
                    String.valueOf(current_hours) , String.valueOf(current_minute) , "0" ,
                "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance2);
        
        String[][] testDataMBatchMaintenance3 =
        {
            {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "ID_AUDIT"} ,
            {"GB" , "1" , "MONTHLY" , "1" , String.valueOf(current_day) , 
                    String.valueOf(current_hours) , String.valueOf(current_minute) , "0" ,
                "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance3);
        
        String[][] testDataMBatchMaintenance4 =
        {
            {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "ID_AUDIT"} ,
            {"SA" , "1" , "MONTHLY" , "1" , String.valueOf(current_day) , 
                    String.valueOf(current_hours) , String.valueOf(current_minute) , "0" ,
                "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance4);
        
        String[][] testDataMBatchMaintenance5 =
        {
            {"BATCH_ID" , "ALERT_MODE" , "FREQUENCY_MODE" , "NO_OF_MONTH" ,
                "DAY_OF_MONTH" , "TIME_HOUR" , "TIME_MINUTE" ,
                "IS_DELETED" , "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "ID_AUDIT"} ,
            {"CB" , "1" , "MONTHLY" , "1" , String.valueOf(current_day) , 
                    String.valueOf(current_hours) , String.valueOf(current_minute) , "0" ,
                "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_BATCH_MAINTENANCE", testDataMBatchMaintenance5);

        /*
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * ID_SET="BATCH_RUNTIME"
         * SET_VALUE=current system time
         */

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"BATCH_RUNTIME" , "1" , getHourMinutee(0) , "AAA" , "1" ,
                    "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
        gSetP02.excute_batch();
    }
}
