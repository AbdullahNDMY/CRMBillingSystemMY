package nttdm.bsys.common.util;

import java.sql.SQLException;
import java.util.Map;

public class G_CPM_P04_Test extends AbstractUtilTest {

    // gCpmP04
    private G_CPM_P04 gCpmP04 = null;

    private static final String PS0 = "PS0";

    private static final String PS1 = "PS1";

    private static final String PS2 = "PS2";

    private static final String PS3 = "PS3";

    private static final String PS4 = "PS4";

    private static final String PS5 = "PS5";

    private static final String PS6 = "PS6";

    private static final String PS7 = "PS7";

    private static final String PS8 = "PS8";

    private static final String ID_CUST_PLAN_1 = "1";

    private static final String ID_CUST_PLAN_2 = "2";

    @Override
    protected void setUpData() throws SQLException {

        gCpmP04 = new G_CPM_P04(queryDAO, updateDAO);
        // Delete related tables
        super.deleteAllData("T_CUST_PLAN_SVC");
        super.deleteAllData("T_CUST_PLAN_LINK");
        super.deleteAllData("T_CUST_PLAN_D");
        super.deleteAllData("T_CUST_PLAN_H");

    }

    private void insertTCustPlanD(String serviceStatus, String idCustPlanGrp) {

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS= $serviceStatus
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {idCustPlanGrp , "1" , serviceStatus , "2011-08-01" ,
                    "2011-09-01" , "0" , "0" , "2011-08-01" , "2011-09-01" ,
                    "M" , "1" , "S" , "0" , "0" , null , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);

    }

    private void insertTCustPlanH() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        String[][] testDataTCustPlanH =
            {
                {"ID_CUST_PLAN" , "ID_CUST" , "PLAN_STATUS" , "PLAN_TYPE" ,
                    "APPROVE_TYPE" , "TRANSACTION_TYPE" , "REFERENCE_PLAN" ,
                    "ID_BILL_ACCOUNT" , "BILL_ACC_ALL" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "BILL_INSTRUCT" , "PAYMENT_METHOD" ,
                    "BILL_CURRENCY" , "EXPORT_CURRENCY" , "FIXED_FOREX" ,
                    "IS_DISPLAY_EXP_AMT" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS0" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
    }

    private String checkStatus(String idCustPlan) {

        Map[] tCustPlanH =
            super.select(
                "T_CUST_PLAN_H", "ID_CUST_PLAN='" + idCustPlan + "'", "");
        return tCustPlanH[0].get("PLAN_STATUS").toString();
    }

    public void testExecute01() {

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

    }

    public void testExecute02() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"
         */
        insertTCustPlanD(PS1, "1");

        gCpmP04.execute(ID_CUST_PLAN_2);
    }

    public void testExecute03() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"
         */
        insertTCustPlanD(PS1, "1");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS0);
    }

    public void testExecute04() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();
        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS2"
         */
        insertTCustPlanD(PS2, "1");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS0);
    }

    public void testExecute05() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS3"
         */
        insertTCustPlanD(PS3, "1");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS3"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS3);
    }

    public void testExecute06() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS4"
         */
        insertTCustPlanD(PS4, "1");
        
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

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS7"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS7);
    }

    public void testExecute07() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS5"
         */
        insertTCustPlanD(PS5, "1");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS0);
    }

    public void testExecute08() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS6"
         */
        insertTCustPlanD(PS6, "1");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS6"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS6);
    }

    public void testExecute09() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS7"
         */
        insertTCustPlanD(PS7, "1");
        
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

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS7"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS7);
    }

    public void testExecute10() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS8"
         */
        insertTCustPlanD(PS8, "1");
        
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

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS7);
    }

    public void testExecute11() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"
         */
        insertTCustPlanD(PS1, "1");
        insertTCustPlanD(PS1, "2");
        insertTCustPlanD(PS1, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);
        gCpmP04.setQueryDAO(queryDAO);
        gCpmP04.getQueryDAO();
        gCpmP04.setUpdateDAO(updateDAO);
        gCpmP04.getUpdateDAO();
        
        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS0);
    }

    public void testExecute12() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS2"
         */
        insertTCustPlanD(PS2, "1");
        insertTCustPlanD(PS2, "2");
        insertTCustPlanD(PS2, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS0);
    }

    public void testExecute13() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS3"
         */
        insertTCustPlanD(PS3, "1");
        insertTCustPlanD(PS3, "2");
        insertTCustPlanD(PS3, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS3"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS3);
    }

    public void testExecute14() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS5"
         */
        insertTCustPlanD(PS5, "1");
        insertTCustPlanD(PS5, "2");
        insertTCustPlanD(PS5, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS0);
    }

    public void testExecute15() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS6"
         */
        insertTCustPlanD(PS6, "1");
        insertTCustPlanD(PS6, "2");
        insertTCustPlanD(PS6, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS0"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS6);
    }

    public void testExecute16() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS7"
         */
        insertTCustPlanD(PS7, "1");
        insertTCustPlanD(PS7, "2");
        insertTCustPlanD(PS7, "3");

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
        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS7"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS7);
    }

    public void testExecute17() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS2"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS2"}
         */
        insertTCustPlanD(PS1, "1");
        insertTCustPlanD(PS2, "2");
        insertTCustPlanD(PS2, "3");
        
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

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS7"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS7);
    }

    public void testExecute18() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS2"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS5"}
         */
        insertTCustPlanD(PS1, "1");
        insertTCustPlanD(PS2, "2");
        insertTCustPlanD(PS5, "3");
        
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

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS7"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS7);
    }

    public void testExecute19() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS2"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS3"}
         */
        insertTCustPlanD(PS1, "1");
        insertTCustPlanD(PS2, "2");
        insertTCustPlanD(PS3, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS3"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS3);
    }

    public void testExecute20() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS6"}
         */
        insertTCustPlanD(PS1, "1");
        insertTCustPlanD(PS1, "2");
        insertTCustPlanD(PS6, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS6"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS6);
    }

    public void testExecute21() {

        /**
         * 【Table "T_CUST_PLAN_H"'s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * PLAN_STATUS="PS0";
         */
        insertTCustPlanH();

        /**
         * 【Table "T_CUST_PLAN_D"'s records】
         * has 3 records in accordance with
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS1"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS3"},
         * {ID_CUST_PLAN=1
         * IS_DELETED="0"
         * SERVICE_STATUS="PS6"}
         */
        insertTCustPlanD(PS1, "1");
        insertTCustPlanD(PS3, "2");
        insertTCustPlanD(PS6, "3");

        /**
         * 【Parameter $ID_CUST_PLAN】
         * value=1
         */
        gCpmP04.execute(ID_CUST_PLAN_1);

        /**
         * 【Update table "T_CUST_PLAN_H"】
         * PLAN_STATUS="PS6"
         * WHERE
         * ID_CUST_PLAN=1
         */
        assertEquals(checkStatus(ID_CUST_PLAN_1), PS3);
    }

}
