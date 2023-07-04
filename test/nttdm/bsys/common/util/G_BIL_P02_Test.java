/**
 * @(#)G_BIL_P02_Test.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-9-5

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.Map;

/**
 * Test Class for nttdm.bsys.common.util.G_BIL_P02
 * 
 * @author bench
 */
public class G_BIL_P02_Test extends AbstractUtilTest {

    private G_BIL_P02 gBilP02 = null;

    /*
     * (non-Javadoc)
     * 
     * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
     */
    @Override
    protected void setUpData() {
        super.deleteAllData("T_CUST_PLAN_SVC");
        super.deleteAllData("T_CUST_PLAN_LINK");
        super.deleteAllData("T_CUST_PLAN_D");
        super.deleteAllData("T_CUST_PLAN_H");

        String[][] custPlanH = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "229743", "PS3", "SP", null, "IN", null, "NEW", "1",
                        "1", "353", "3", "NT", "SGD", null, null, "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", null },
                { "2", "229743", "PS3", "SP", null, "IN", null, "NEW", "1",
                        "1", "353", "3", "NT", "SGD", null, null, "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", null } };
        super.insertData("T_CUST_PLAN_H", custPlanH);

        // initialize G_BIL_P02 object
        gBilP02 = new G_BIL_P02();
        gBilP02.setQueryDAO(queryDAO);
        gBilP02.setUpdateDAO(updateDAO);
    }

    /**
     * Case 1:
     * 
     * Condition:<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3: Active.<br>
     * SVC_END <> null and SVC_END<current sysdate.<br>
     * 
     * Result:<br>
     * T_CUST_PLAN_H.PLAN_STATUS = PS7<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS7
     */
    @SuppressWarnings("unchecked")
    public void testExecute01() {
        String serviceStatus = "PS3";
        String serviceEnd = TEST_DAY_YESTERDAY;

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", serviceStatus, TEST_DAY_LASTYEAR_TODAY, serviceEnd,
                        "0", "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y",
                        "12", "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "1", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", null,"0" } };
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);

        gBilP02.execute(SYS_ADMIN, null);

        Map<String, Object>[] dataH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS7", dataH[0].get("PLAN_STATUS").toString());

        Map<String, Object>[] dataD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS7", dataD[0].get("SERVICES_STATUS").toString());
    }

    /**
     * Case 2:
     * 
     * Condition:<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3: Active.<br>
     * SVC_END <> null and SVC_END > current sysdate.<br>
     * 
     * Result:<br>
     * T_CUST_PLAN_H.PLAN_STATUS = PS3<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3
     */
    @SuppressWarnings("unchecked")
    public void testExecute02() {
        String serviceStatus = "PS3";
        String serviceEnd = TEST_DAY_TOMORROW;

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", serviceStatus, TEST_DAY_LASTYEAR_TODAY, serviceEnd,
                        "0", "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y",
                        "12", "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);

        gBilP02.execute(SYS_ADMIN, null);

        Map<String, Object>[] dataH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", dataH[0].get("PLAN_STATUS").toString());

        Map<String, Object>[] dataD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", dataD[0].get("SERVICES_STATUS").toString());
    }

    /**
     * Case 3:
     * 
     * Condition:<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3: Active.<br>
     * SVC_END = null.<br>
     * 
     * Result:<br>
     * T_CUST_PLAN_H.PLAN_STATUS = PS3<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3
     */
    @SuppressWarnings("unchecked")
    public void testExecute03() {
        String serviceStatus = "PS3";
        String serviceEnd = null;

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", serviceStatus, TEST_DAY_LASTYEAR_TODAY, serviceEnd,
                        "0", "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y",
                        "12", "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);

        gBilP02.execute(SYS_ADMIN, null);

        Map<String, Object>[] dataH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", dataH[0].get("PLAN_STATUS").toString());

        Map<String, Object>[] dataD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", null);
        assertEquals("PS3", dataD[0].get("SERVICES_STATUS").toString());
    }

    /**
     * Case 4:
     * 
     * Condition:<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3: Active.<br>
     * SVC_END <> null and SVC_END<current sysdate.<br>
     * Another T_CUST_PLAN_D.SERVICES_STATUS = PS7<br>
     * 
     * Result:<br>
     * T_CUST_PLAN_H.PLAN_STATUS = PS7<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS7
     */
    @SuppressWarnings("unchecked")
    public void testExecute04() {
        String serviceStatus = "PS3";
        String serviceEnd = TEST_DAY_YESTERDAY;

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", serviceStatus, TEST_DAY_LASTYEAR_TODAY, serviceEnd,
                        "0", "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y",
                        "12", "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null },
                { "2", "1", "PS7", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "0",
                        "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y", "12",
                        "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);
        
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN", "ACCESS_ACCOUNT",
                        "ACCESS_PW", "ACCESS_TEL", "SSID", "WEP_KEY",
                        "ROUTER_PW", "ROUTER_NO", "ROUTER_TYPE", "MODEM_NO",
                        "MAC_ID", "ADSL_DEL_NO", "CIRCUIT_ID",
                        "CARRIER", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", "ID_SUB_INFO" },
                { "1", "", "", "", "", "", "", "",
                        "", "", "",
                        "", "", "", "sysadmin", "0", null,"0" } };
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);

        gBilP02.execute(SYS_ADMIN, null);

        Map<String, Object>[] dataH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS7", dataH[0].get("PLAN_STATUS").toString());

        Map<String, Object>[] dataD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", "ID_CUST_PLAN_GRP ASC");
        assertEquals("PS7", dataD[0].get("SERVICES_STATUS").toString());
    }

    /**
     * Case 5:
     * 
     * Condition:<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3: Active.<br>
     * SVC_END <> null and SVC_END<current sysdate.<br>
     * Another T_CUST_PLAN_D.SERVICES_STATUS <> PS7<br>
     * 
     * Result:<br>
     * T_CUST_PLAN_H.PLAN_STATUS = PS7<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS7
     */
    @SuppressWarnings("unchecked")
    public void testExecute05() {
        String serviceStatus = "PS3";
        String serviceEnd = TEST_DAY_YESTERDAY;

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", serviceStatus, TEST_DAY_LASTYEAR_TODAY, serviceEnd,
                        "0", "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y",
                        "12", "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null },
                { "2", "1", "PS1", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "0",
                        "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y", "12",
                        "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);

        gBilP02.execute(SYS_ADMIN, null);

        Map<String, Object>[] dataH = super.select("T_CUST_PLAN_H",
                "ID_CUST_PLAN='1'", null);
        assertEquals("PS3", dataH[0].get("PLAN_STATUS").toString());

        Map<String, Object>[] dataD = super.select("T_CUST_PLAN_D",
                "ID_CUST_PLAN_GRP='1'", "ID_CUST_PLAN_GRP ASC");
        assertEquals("PS7", dataD[0].get("SERVICES_STATUS").toString());
    }

    /**
     * Case 6:
     * 
     * Condition:<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS3: Active.<br>
     * SVC_END = null<br>
     * Another T_CUST_PLAN_D.SERVICES_STATUS <> PS7<br>
     * 
     * Result:<br>
     * T_CUST_PLAN_H.PLAN_STATUS = PS7<br>
     * T_CUST_PLAN_D.SERVICES_STATUS = PS7
     */
    @SuppressWarnings("unchecked")
    public void testExecute06() {
        String serviceStatus = "PS3";
        String serviceEnd = null;

        String[][] custPlanD = {
                { "ID_CUST_PLAN_GRP", "ID_CUST_PLAN", "SERVICES_STATUS",
                        "SVC_START", "SVC_END", "AUTO_RENEW", "MIN_SVC_PERIOD",
                        "MIN_SVC_START", "MIN_SVC_END", "CONTRACT_TERM",
                        "CONTRACT_TERM_NO", "PRO_RATE_BASE",
                        "PRO_RATE_BASE_NO", "IS_LUMP_SUM", "BILL_DESC",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { "1", "1", serviceStatus, TEST_DAY_LASTYEAR_TODAY, serviceEnd,
                        "0", "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y",
                        "12", "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null },
                { "2", "2", "PS1", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "0",
                        "0", TEST_DAY_LASTYEAR_TODAY, serviceEnd, "Y", "12",
                        "U", "27", "0", null, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", null } };
        super.insertData("T_CUST_PLAN_D", custPlanD);

        gBilP02.execute(SYS_ADMIN, null);

        Map<String, Object>[] dataH = super.select("T_CUST_PLAN_H", null, null);
        assertEquals("PS3", dataH[0].get("PLAN_STATUS").toString());
        assertEquals("PS3", dataH[1].get("PLAN_STATUS").toString());

        Map<String, Object>[] dataD = super.select("T_CUST_PLAN_D", null, null);
        assertEquals("PS3", dataD[0].get("SERVICES_STATUS").toString());
        assertEquals("PS1", dataD[1].get("SERVICES_STATUS").toString());
    }
}
