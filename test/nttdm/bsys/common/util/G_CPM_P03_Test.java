/**
 * @(#)G_CPM_P01_Test.java
 * Billing System
 * Version 1.00
 * Created 2011/07/28
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All
 * Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import nttdm.bsys.common.util.dto.G_CPM_P03Input;

/**
 * Test Class for nttdm.bsys.common.util.G_CPM_P03
 * 
 * @author qinjinh
 */
public class G_CPM_P03_Test extends AbstractUtilTest {

    // gCpmP03
    private G_CPM_P03 gCpmP03 = null;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    Date date = new Date();

    private String nowDate = df.format(date) + " 00:00:00.0";

    private String
        getAmt(float param, int p, int mMode, int nDays, int nMonths) {

        float Amt1 = param / mMode;
        float Amt2 = Amt1 * nMonths;
        float Amt3 = (Amt1 / p) * nDays;

        DecimalFormat format = new DecimalFormat("########.##");
        String str = format.format(Amt2 + Amt3);
        return str;
    }

    /*
     * (non-Javadoc)
     * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
     */
    @Override
    protected void setUpData() throws SQLException {

        // initialize queryDAO and updateDAO
        gCpmP03 = new G_CPM_P03(queryDAO, updateDAONuked);
        // Delete related tables
        super.deleteAllData("T_CUST_PLAN_SVC");
        super.deleteAllData("T_CUST_PLAN_LINK");
        super.deleteAllData("T_CUST_PLAN_D");
        super.deleteAllData("T_CUST_PLAN_H");

    }

    public void testExecute01() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2010");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        // TEST.G_CPM_P03.SELECT.SQL001
        /**
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
         * PLAN_STATUS=PS1
         * TRANSACTION_TYPE=CN
         * REFERENCE_PLAN=1
         * BILL_INSTRUCT=6
         * IS_DISPLAY_EXP_AMT=0
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanH =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL001", null, String.class);
        Map[] tCustPlanH =
            super.select("T_CUST_PLAN_H", "ID_CUST_PLAN='" + sqsTCustPlanH
                + "'", "");
        assertEquals(tCustPlanH[0].get("ID_CUST"), "1");
        assertEquals(tCustPlanH[0].get("PLAN_TYPE"), "SP");
        assertEquals(
            tCustPlanH[0].get("ID_BILL_ACCOUNT").toString().trim(), "1");
        assertEquals(tCustPlanH[0].get("BILL_ACC_ALL"), "0");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL1"), "1");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL2"), "1");
        assertEquals(tCustPlanH[0].get("PAYMENT_METHOD"), "CQ");
        assertEquals(tCustPlanH[0].get("BILL_CURRENCY"), "USD");
        assertEquals(tCustPlanH[0].get("EXPORT_CURRENCY"), "ABC");
        assertEquals(
            new BigDecimal(tCustPlanH[0].get("FIXED_FOREX").toString()).compareTo(new BigDecimal(
                "1000.0000")), 0);
        assertEquals(tCustPlanH[0].get("PLAN_STATUS"), "PS1");
        assertEquals(tCustPlanH[0].get("TRANSACTION_TYPE"), "CN");
        assertEquals(tCustPlanH[0].get("REFERENCE_PLAN"), "1");
        assertEquals(tCustPlanH[0].get("BILL_INSTRUCT"), "6");
        assertEquals(tCustPlanH[0].get("IS_DISPLAY_EXP_AMT"), "0");
        assertEquals(tCustPlanH[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanH[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanH[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanH[0].get("ID_LOGIN"), SYS_ADMIN);

        /**
         * ID_CUST_PLAN_GRP=Auto generate running no.
         * ID_CUST_PLAN=$ID_CUST_PLAN
         * SERVICES_STATUS=PS1
         * SCV_START=$svc_start
         * SVC_END=$svc_end
         * AUTO_RENEW=0
         * MIN_SVC_PERIOD=0
         * CONTRACT_TERM=M
         * IS_LUMP_SUM=0
         * PRO_RATE_BASE=B.PRO_RATE_BASE
         * PRO_RATE_BASE_NO=B.PRO_RATE_BASE_NO
         * BILL_DESC=B.BILL_DESC
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATE=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanD =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL002", null, String.class);
        Map[] tCustPlanD =
            super.select("T_CUST_PLAN_D", "ID_CUST_PLAN_GRP='" + sqsTCustPlanD
                + "'", "");
        assertEquals(tCustPlanD[0].get("ID_CUST_PLAN"), sqsTCustPlanH);
        assertEquals(tCustPlanD[0].get("SERVICES_STATUS"), "PS1");
        assertEquals(tCustPlanD[0].get("SVC_START"), input.getSvcStart()
            + " 00:00:00.0");
        assertEquals(tCustPlanD[0].get("SVC_END"), input.getSvcEnd()
            + " 00:00:00.0");
        assertEquals(tCustPlanD[0].get("AUTO_RENEW"), "0");
        assertEquals(tCustPlanD[0].get("MIN_SVC_PERIOD"), "0");
        assertEquals(tCustPlanD[0].get("CONTRACT_TERM"), "M");
        assertEquals(tCustPlanD[0].get("IS_LUMP_SUM"), "0");
        assertEquals(tCustPlanD[0].get("PRO_RATE_BASE"), "S");
        assertEquals(tCustPlanD[0].get("PRO_RATE_BASE_NO"), "0");
        assertEquals(tCustPlanD[0].get("BILL_DESC"), null);
        assertEquals(tCustPlanD[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanD[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanD[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanD[0].get("ID_LOGIN"), SYS_ADMIN);

        /**
         * ID_CUST_PLAN_LINK=Auto generate running no.
         * ID_CUST_PLAN_GRP=$ID_CUST_PLAN_GRP
         * UNIT_PRICE=inAmt[0]
         * TOTAL_AMOUNT=inAmt[1]
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         * APPLY_GST=0
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=userID
         */

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);
        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("ID_CUST_PLAN_GRP"), sqsTCustPlanD);
        // assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), "AAAA");
        // assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), "AAAA");
        assertEquals(tCustPlanLink[0].get("ITEM_DESC"), null);
        assertEquals(tCustPlanLink[0].get("QUANTITY"), "1");
        assertEquals(tCustPlanLink[0].get("JOB_NO"), "123456");
        assertEquals(tCustPlanLink[0].get("ID_PLAN"), "1");
        assertEquals(tCustPlanLink[0].get("PLAN_NAME"), "play01");
        assertEquals(tCustPlanLink[0].get("PLAN_DESC"), "1234567890123456789");
        assertEquals(tCustPlanLink[0].get("ID_PLAN_GRP"), "1");
        assertEquals(
            tCustPlanLink[0].get("ITEM_GRP_NAME"), "01234567890123456789");
        assertEquals(tCustPlanLink[0].get("SVC_LEVEL1"), "1");
        assertEquals(tCustPlanLink[0].get("SVC_LEVEL2"), "1");
        assertEquals(tCustPlanLink[0].get("RATE_TYPE"), "BA");
        assertEquals(tCustPlanLink[0].get("RATE_MODE"), "1");
        assertEquals(
            new BigDecimal(tCustPlanLink[0].get("RATE").toString()).compareTo(new BigDecimal(
                "1000.0000")), 0);
        assertEquals(tCustPlanLink[0].get("APPLY_GST"), "0");
        assertEquals(tCustPlanLink[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanLink[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanLink[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanLink[0].get("ID_LOGIN"), SYS_ADMIN);

        /**
         * ID_CUST_PLAN_SVC=Auto generate running no.
         * ID_CUST_PLAN_LINK=$ID_CUST_PLAN_LINK
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanSvc =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL004", null, String.class);
        Map[] tCustPlanSvc =
            super.select("T_CUST_PLAN_SVC", "ID_CUST_PLAN_SVC='"
                + sqsTCustPlanSvc + "'", "");
        assertEquals(
            tCustPlanSvc[0].get("ID_CUST_PLAN_LINK"), sqsTCustPlanLink);
        assertEquals(tCustPlanSvc[0].get("SVC_LEVEL3"), "1");
        assertEquals(tCustPlanSvc[0].get("SVC_LEVEL4"), "1");
        assertEquals(tCustPlanSvc[0].get("ID_SUPPLIER"), "1");
        assertEquals(tCustPlanSvc[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanSvc[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanSvc[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanSvc[0].get("ID_LOGIN"), SYS_ADMIN);

        
        gCpmP03.setQueryDAO(queryDAO);
        gCpmP03.getQueryDAO();
        gCpmP03.setUpdateDAO(updateDAO);
        gCpmP03.getUpdateDAO();
        gCpmP03.getUpdateDAONuked();
        gCpmP03.setUpdateDAONuked(updateDAONuked);
        gCpmP03.setP(0);
        gCpmP03.getnDays();
        gCpmP03.setnDays(0);
        gCpmP03.getnMonths();
        gCpmP03.setnMonths(0);
    }

    public void testExecute02() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("2");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);
        gCpmP03.execute(input);

        String sqsTCustPlanH =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL001", null, String.class);
        String sqsTCustPlanD =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL002", null, String.class);
        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);
        String sqsTCustPlanSvc =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL004", null, String.class);

        Map[] tCustPlanH =
            super.select("T_CUST_PLAN_H", "ID_CUST_PLAN='" + sqsTCustPlanH
                + "'", "");
        Map[] tCustPlanD =
            super.select("T_CUST_PLAN_D", "ID_CUST_PLAN_GRP='" + sqsTCustPlanD
                + "'", "");
        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");
        Map[] tCustPlanSvc =
            super.select("T_CUST_PLAN_SVC", "ID_CUST_PLAN_SVC='"
                + sqsTCustPlanSvc + "'", "");
        assertEquals(tCustPlanH.length, 0);
        assertEquals(tCustPlanD.length, 0);
        assertEquals(tCustPlanLink.length, 0);
        assertEquals(tCustPlanSvc.length, 0);
    }

    public void testExecute03() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");

        gCpmP03.execute(input);

        String sqsTCustPlanH =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL001", null, String.class);
        String sqsTCustPlanD =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL002", null, String.class);
        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);
        String sqsTCustPlanSvc =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL004", null, String.class);

        Map[] tCustPlanH =
            super.select("T_CUST_PLAN_H", "ID_CUST_PLAN='" + sqsTCustPlanH
                + "'", "");
        Map[] tCustPlanD =
            super.select("T_CUST_PLAN_D", "ID_CUST_PLAN_GRP='" + sqsTCustPlanD
                + "'", "");
        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");
        Map[] tCustPlanSvc =
            super.select("T_CUST_PLAN_SVC", "ID_CUST_PLAN_SVC='"
                + sqsTCustPlanSvc + "'", "");
        assertEquals(tCustPlanH.length, 0);
        assertEquals(tCustPlanD.length, 0);
        assertEquals(tCustPlanLink.length, 0);
        assertEquals(tCustPlanSvc.length, 0);
    }

    public void testExecute04() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);
        gCpmP03.execute(input);

        /**
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
         * PLAN_STATUS=PS1
         * TRANSACTION_TYPE=CN
         * REFERENCE_PLAN=1
         * BILL_INSTRUCT=6
         * IS_DISPLAY_EXP_AMT=0
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanH =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL001", null, String.class);
        Map[] tCustPlanH =
            super.select("T_CUST_PLAN_H", "ID_CUST_PLAN='" + sqsTCustPlanH
                + "'", "");
        assertEquals(tCustPlanH[0].get("ID_CUST"), "1");
        assertEquals(tCustPlanH[0].get("PLAN_TYPE"), "SP");
        assertEquals(
            tCustPlanH[0].get("ID_BILL_ACCOUNT").toString().trim(), "1");
        assertEquals(tCustPlanH[0].get("BILL_ACC_ALL"), "0");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL1"), "1");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL2"), "1");
        assertEquals(tCustPlanH[0].get("PAYMENT_METHOD"), "CQ");
        assertEquals(tCustPlanH[0].get("BILL_CURRENCY"), "USD");
        assertEquals(tCustPlanH[0].get("EXPORT_CURRENCY"), "ABC");
        assertEquals(
            new BigDecimal(tCustPlanH[0].get("FIXED_FOREX").toString()).compareTo(new BigDecimal(
                "1000.0000")), 0);
        assertEquals(tCustPlanH[0].get("PLAN_STATUS"), "PS1");
        assertEquals(tCustPlanH[0].get("TRANSACTION_TYPE"), "CN");
        assertEquals(tCustPlanH[0].get("REFERENCE_PLAN"), "1");
        assertEquals(tCustPlanH[0].get("BILL_INSTRUCT"), "6");
        assertEquals(tCustPlanH[0].get("IS_DISPLAY_EXP_AMT"), "0");
        assertEquals(tCustPlanH[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanH[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanH[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanH[0].get("ID_LOGIN"), SYS_ADMIN);

        String sqsTCustPlanD =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL002", null, String.class);
        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);
        String sqsTCustPlanSvc =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL004", null, String.class);

        Map[] tCustPlanD =
            super.select("T_CUST_PLAN_D", "ID_CUST_PLAN_GRP='" + sqsTCustPlanD
                + "'", "");
        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");
        Map[] tCustPlanSvc =
            super.select("T_CUST_PLAN_SVC", "ID_CUST_PLAN_SVC='"
                + sqsTCustPlanSvc + "'", "");
        assertEquals(tCustPlanD.length, 0);
        assertEquals(tCustPlanLink.length, 0);
        assertEquals(tCustPlanSvc.length, 0);

    }

    public void testExecute05() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);

        gCpmP03.execute(input);

        /**
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
         * PLAN_STATUS=PS1
         * TRANSACTION_TYPE=CN
         * REFERENCE_PLAN=1
         * BILL_INSTRUCT=6
         * IS_DISPLAY_EXP_AMT=0
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanH =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL001", null, String.class);
        Map[] tCustPlanH =
            super.select("T_CUST_PLAN_H", "ID_CUST_PLAN='" + sqsTCustPlanH
                + "'", "");
        assertEquals(tCustPlanH[0].get("ID_CUST"), "1");
        assertEquals(tCustPlanH[0].get("PLAN_TYPE"), "SP");
        assertEquals(
            tCustPlanH[0].get("ID_BILL_ACCOUNT").toString().trim(), "1");
        assertEquals(tCustPlanH[0].get("BILL_ACC_ALL"), "0");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL1"), "1");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL2"), "1");
        assertEquals(tCustPlanH[0].get("PAYMENT_METHOD"), "CQ");
        assertEquals(tCustPlanH[0].get("BILL_CURRENCY"), "USD");
        assertEquals(tCustPlanH[0].get("EXPORT_CURRENCY"), "ABC");
        assertEquals(
            new BigDecimal(tCustPlanH[0].get("FIXED_FOREX").toString()).compareTo(new BigDecimal(
                "1000.0000")), 0);
        assertEquals(tCustPlanH[0].get("PLAN_STATUS"), "PS1");
        assertEquals(tCustPlanH[0].get("TRANSACTION_TYPE"), "CN");
        assertEquals(tCustPlanH[0].get("REFERENCE_PLAN"), "1");
        assertEquals(tCustPlanH[0].get("BILL_INSTRUCT"), "6");
        assertEquals(tCustPlanH[0].get("IS_DISPLAY_EXP_AMT"), "0");
        assertEquals(tCustPlanH[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanH[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanH[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanH[0].get("ID_LOGIN"), SYS_ADMIN);

        /**
         * ID_CUST_PLAN_GRP=Auto generate running no.
         * ID_CUST_PLAN=$ID_CUST_PLAN
         * SERVICES_STATUS=PS1
         * SCV_START=$svc_start
         * SVC_END=$svc_end
         * AUTO_RENEW=0
         * MIN_SVC_PERIOD=0
         * CONTRACT_TERM=M
         * IS_LUMP_SUM=0
         * PRO_RATE_BASE=B.PRO_RATE_BASE
         * PRO_RATE_BASE_NO=B.PRO_RATE_BASE_NO
         * BILL_DESC=B.BILL_DESC
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATE=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanD =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL002", null, String.class);
        Map[] tCustPlanD =
            super.select("T_CUST_PLAN_D", "ID_CUST_PLAN_GRP='" + sqsTCustPlanD
                + "'", "");
        assertEquals(tCustPlanD[0].get("ID_CUST_PLAN"), sqsTCustPlanH);
        assertEquals(tCustPlanD[0].get("SERVICES_STATUS"), "PS1");
        assertEquals(tCustPlanD[0].get("SVC_START"), input.getSvcStart()
            + " 00:00:00.0");
        assertEquals(tCustPlanD[0].get("SVC_END"), input.getSvcEnd()
            + " 00:00:00.0");
        assertEquals(tCustPlanD[0].get("AUTO_RENEW"), "0");
        assertEquals(tCustPlanD[0].get("MIN_SVC_PERIOD"), "0");
        assertEquals(tCustPlanD[0].get("CONTRACT_TERM"), "M");
        assertEquals(tCustPlanD[0].get("IS_LUMP_SUM"), "0");
        assertEquals(tCustPlanD[0].get("PRO_RATE_BASE"), "S");
        assertEquals(tCustPlanD[0].get("PRO_RATE_BASE_NO"), "0");
        assertEquals(tCustPlanD[0].get("BILL_DESC"), null);
        assertEquals(tCustPlanD[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanD[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanD[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanD[0].get("ID_LOGIN"), SYS_ADMIN);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);
        String sqsTCustPlanSvc =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL004", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");
        Map[] tCustPlanSvc =
            super.select("T_CUST_PLAN_SVC", "ID_CUST_PLAN_SVC='"
                + sqsTCustPlanSvc + "'", "");

        assertEquals(tCustPlanLink.length, 0);
        assertEquals(tCustPlanSvc.length, 0);

    }

    public void testExecute06() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);

        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        gCpmP03.execute(input);

        /**
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
         * PLAN_STATUS=PS1
         * TRANSACTION_TYPE=CN
         * REFERENCE_PLAN=1
         * BILL_INSTRUCT=6
         * IS_DISPLAY_EXP_AMT=0
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanH =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL001", null, String.class);
        Map[] tCustPlanH =
            super.select("T_CUST_PLAN_H", "ID_CUST_PLAN='" + sqsTCustPlanH
                + "'", "");
        assertEquals(tCustPlanH[0].get("ID_CUST"), "1");
        assertEquals(tCustPlanH[0].get("PLAN_TYPE"), "SP");
        assertEquals(
            tCustPlanH[0].get("ID_BILL_ACCOUNT").toString().trim(), "1");
        assertEquals(tCustPlanH[0].get("BILL_ACC_ALL"), "0");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL1"), "1");
        assertEquals(tCustPlanH[0].get("SVC_LEVEL2"), "1");
        assertEquals(tCustPlanH[0].get("PAYMENT_METHOD"), "CQ");
        assertEquals(tCustPlanH[0].get("BILL_CURRENCY"), "USD");
        assertEquals(tCustPlanH[0].get("EXPORT_CURRENCY"), "ABC");
        assertEquals(
            new BigDecimal(tCustPlanH[0].get("FIXED_FOREX").toString()).compareTo(new BigDecimal(
                "1000.0000")), 0);
        assertEquals(tCustPlanH[0].get("PLAN_STATUS"), "PS1");
        assertEquals(tCustPlanH[0].get("TRANSACTION_TYPE"), "CN");
        assertEquals(tCustPlanH[0].get("REFERENCE_PLAN"), "1");
        assertEquals(tCustPlanH[0].get("BILL_INSTRUCT"), "6");
        assertEquals(tCustPlanH[0].get("IS_DISPLAY_EXP_AMT"), "0");
        assertEquals(tCustPlanH[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanH[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanH[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanH[0].get("ID_LOGIN"), SYS_ADMIN);

        /**
         * ID_CUST_PLAN_GRP=Auto generate running no.
         * ID_CUST_PLAN=$ID_CUST_PLAN
         * SERVICES_STATUS=PS1
         * SCV_START=$svc_start
         * SVC_END=$svc_end
         * AUTO_RENEW=0
         * MIN_SVC_PERIOD=0
         * CONTRACT_TERM=M
         * IS_LUMP_SUM=0
         * PRO_RATE_BASE=B.PRO_RATE_BASE
         * PRO_RATE_BASE_NO=B.PRO_RATE_BASE_NO
         * BILL_DESC=B.BILL_DESC
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATE=sysdate
         * ID_LOGIN=userID
         */
        String sqsTCustPlanD =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL002", null, String.class);
        Map[] tCustPlanD =
            super.select("T_CUST_PLAN_D", "ID_CUST_PLAN_GRP='" + sqsTCustPlanD
                + "'", "");
        assertEquals(tCustPlanD[0].get("ID_CUST_PLAN"), sqsTCustPlanH);
        assertEquals(tCustPlanD[0].get("SERVICES_STATUS"), "PS1");
        assertEquals(tCustPlanD[0].get("SVC_START"), input.getSvcStart()
            + " 00:00:00.0");
        assertEquals(tCustPlanD[0].get("SVC_END"), input.getSvcEnd()
            + " 00:00:00.0");
        assertEquals(tCustPlanD[0].get("AUTO_RENEW"), "0");
        assertEquals(tCustPlanD[0].get("MIN_SVC_PERIOD"), "0");
        assertEquals(tCustPlanD[0].get("CONTRACT_TERM"), "M");
        assertEquals(tCustPlanD[0].get("IS_LUMP_SUM"), "0");
        assertEquals(tCustPlanD[0].get("PRO_RATE_BASE"), "S");
        assertEquals(tCustPlanD[0].get("PRO_RATE_BASE_NO"), "0");
        assertEquals(tCustPlanD[0].get("BILL_DESC"), null);
        assertEquals(tCustPlanD[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanD[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanD[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanD[0].get("ID_LOGIN"), SYS_ADMIN);

        /**
         * ID_CUST_PLAN_LINK=Auto generate running no.
         * ID_CUST_PLAN_GRP=$ID_CUST_PLAN_GRP
         * UNIT_PRICE=inAmt[0]
         * TOTAL_AMOUNT=inAmt[1]
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         * APPLY_GST=0
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=userID
         */

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);
        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("ID_CUST_PLAN_GRP"), sqsTCustPlanD);
        // assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), "AAAA");
        // assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), "AAAA");
        assertEquals(tCustPlanLink[0].get("ITEM_DESC"), null);
        assertEquals(tCustPlanLink[0].get("QUANTITY"), "1");
        assertEquals(tCustPlanLink[0].get("JOB_NO"), "123456");
        assertEquals(tCustPlanLink[0].get("ID_PLAN"), "1");
        assertEquals(tCustPlanLink[0].get("PLAN_NAME"), "play01");
        assertEquals(tCustPlanLink[0].get("PLAN_DESC"), "1234567890123456789");
        assertEquals(tCustPlanLink[0].get("ID_PLAN_GRP"), "1");
        assertEquals(
            tCustPlanLink[0].get("ITEM_GRP_NAME"), "01234567890123456789");
        assertEquals(tCustPlanLink[0].get("SVC_LEVEL1"), "1");
        assertEquals(tCustPlanLink[0].get("SVC_LEVEL2"), "1");
        assertEquals(tCustPlanLink[0].get("RATE_TYPE"), "BA");
        assertEquals(tCustPlanLink[0].get("RATE_MODE"), "1");
        assertEquals(
            new BigDecimal(tCustPlanLink[0].get("RATE").toString()).compareTo(new BigDecimal(
                "1000.0000")), 0);
        assertEquals(tCustPlanLink[0].get("APPLY_GST"), "0");
        assertEquals(tCustPlanLink[0].get("IS_DELETED"), "0");
        assertEquals(tCustPlanLink[0].get("DATE_CREATED"), nowDate);
        assertEquals(tCustPlanLink[0].get("DATE_UPDATED"), nowDate);
        assertEquals(tCustPlanLink[0].get("ID_LOGIN"), SYS_ADMIN);

        String sqsTCustPlanSvc =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL004", null, String.class);

        Map[] tCustPlanSvc =
            super.select("T_CUST_PLAN_SVC", "ID_CUST_PLAN_SVC='"
                + sqsTCustPlanSvc + "'", "");

        assertEquals(tCustPlanSvc.length, 0);

    }

    public void testExecute07() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc1 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc1);
        String[][] testDataTCustPlanSvc2 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"2" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc2);
        String[][] testDataTCustPlanSvc3 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"3" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc3);
        String[][] testDataTCustPlanSvc4 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"4" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc4);

        gCpmP03.execute(input);

        Map[] tCustPlanSvc = super.select("T_CUST_PLAN_SVC", "", "");
        assertEquals(tCustPlanSvc.length, 8);

    }

    public void testExecute08() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink1 =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink1);
        String[][] testDataTCustPlanLink2 =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"2" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink2);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc1 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc1);

        gCpmP03.execute(input);

        Map[] tCustPlanLink = super.select("T_CUST_PLAN_LINK", "", "");
        assertEquals(tCustPlanLink.length, 4);

    }

    public void testExecute09() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink1 =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink1);
        String[][] testDataTCustPlanLink2 =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"2" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink2);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc1 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc1);
        String[][] testDataTCustPlanSvc2 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"2" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc2);
        String[][] testDataTCustPlanSvc3 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"3" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc3);
        String[][] testDataTCustPlanSvc4 =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"4" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc4);

        gCpmP03.execute(input);

        Map[] tCustPlanLink = super.select("T_CUST_PLAN_LINK", "", "");
        Map[] tCustPlanSvc = super.select("T_CUST_PLAN_SVC", "", "");

        assertEquals(tCustPlanLink.length, 4);
        assertEquals(tCustPlanSvc.length, 8);

    }

    public void testExecute10() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "6" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        Map[] tCustPlanLink = super.select("T_CUST_PLAN_LINK", "", "");
        Map[] tCustPlanSvc = super.select("T_CUST_PLAN_SVC", "", "");

        assertEquals(tCustPlanLink.length, 1);
        assertEquals(tCustPlanSvc.length, 1);

    }

    public void testExecute11() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        Date svcEnd = df.parse(input.getSvcEnd());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(svcEnd);
        int end = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        assertEquals(gCpmP03.getP(), end);
    }

    public void testExecute12() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="U"
         * PRO_RATE_BASE_NO="6"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "U" , "6" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "1000.00" , "10000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        assertEquals(gCpmP03.getP(), 6);

    }

    public void testExecute13() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 12, 32, 0));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 12, 32, 0));

    }

    public void testExecute14() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         * UNIT_PRICE=200.00
         * TOTAL_AMOUNT=5000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "200.00" , "5000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            200, 30, 12, 32, 0));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            5000, 30, 12, 32, 0));

    }

    public void testExecute15() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="U"
         * PRO_RATE_BASE_NO="6"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "U" , "6" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 6, 12, 32, 0));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 6, 12, 32, 0));

    }

    public void testExecute16() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-02");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 12, 1, 1));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 12, 1, 1));

    }

    public void testExecute17() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="2"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "2" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 6, 32, 0));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 6, 32, 0));

    }

    public void testExecute18() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="3"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "3" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 3, 32, 0));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 3, 32, 0));
    }

    public void testExecute19() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="4"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "4" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 2, 32, 0));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 2, 32, 0));

    }

    public void testExecute20() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/01'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-01");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="0"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "0" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 1, 32, 0));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 1, 32, 0));

    }

    public void testExecute21() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/02'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-02");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="2"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "2" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 6, 1, 1));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 6, 1, 1));

    }

    public void testExecute22() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/09/02'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-09-02");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="3"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "3" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 30, 3, 1, 1));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 30, 3, 1, 1));

    }

    public void testExecute23() throws ParseException, SQLException {

        /**
         * 【Parameter value Input from B-CPM-S02】
         * $svcStart='2011/08/01'
         * $svcEnd='2011/12/30'
         * $idCustPlan=1
         * $idCustPlanGrp=1
         */
        G_CPM_P03Input input = new G_CPM_P03Input();
        input.setIdLogin(SYS_ADMIN);
        input.setSvcStart("01/08/2011");
        input.setSvcEnd("2011-12-30");
        input.setIdCustPlan("1");
        input.setIdCustPlanGrp("1");
        /**
         * 【Table "T_CUST_PLAN_H"s records】
         * has one record in accordance with
         * ID_CUST_PLAN=1
         * ID_CUST=1
         * PLAN_TYPE=SP
         * ID_BILL_ACCOUNT=1
         * BILL_ALL_ACC="0"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * PAYMENT_METHOD="CQ"
         * BILL_CURRENCY="USD"
         * EXPORT_CURRENCY="ABC"
         * FIXED_FOREX="1000.0000"
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
                {"1" , "1" , "PS1" , "SP" , "B" , "DN" , "1" , "1" , "0" ,
                    "1" , "1" , "1" , "CQ" , "USD" , "ABC" , "1000.0000" ,
                    "0" , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_H", testDataTCustPlanH);

        /**
         * 【Table "T_CUST_PLAN_D"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * PRO_RATE_BASE="S"
         * PRO_RATE_BASE_NO="0"
         * BILL_DESC=null
         */
        String[][] testDataTCustPlanD =
            {
                {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                    "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                    "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                    "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                    "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "PS3" , "2011-08-01" , "2011-09-01" , "0" , "0" ,
                    "2011-08-01" , "2011-09-01" , "M" , "1" , "S" , "0" , "0" ,
                    null , "0" , "2011-08-30" , "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        /**
         * 【Table "T_CUST_PLAN_LINK"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_GRP=1
         * IS_DELETED=0
         * ITEM_DESC=null
         * QUANTITY=1
         * JOB_NO="123456"
         * ID_PLAN=1
         * PLAN_NAME="play01"
         * PLAN_DESC="1234567890123456789"
         * ID_PLAN_GRP=1
         * ITEM_GRP_NAME="01234567890123456789"
         * SVC_LEVEL1=1
         * SVC_LEVEL2=1
         * RATE_TYPE="BA"
         * RATE_MODE="1"
         * RATE="1000.0000"
         * * UNIT_PRICE=100.00
         * TOTAL_AMOUNT=1000.00
         */
        String[][] testDataTCustPlanLink =
            {
                {"ID_CUST_PLAN_LINK" , "ID_CUST_PLAN_GRP" , "ITEM_TYPE" ,
                    "ITEM_DESC" , "QUANTITY" , "UNIT_PRICE" , "TOTAL_AMOUNT" ,
                    "JOB_NO" , "ID_PLAN" , "PLAN_NAME" , "PLAN_DESC" ,
                    "ID_PLAN_GRP" , "ITEM_GRP_NAME" , "SVC_LEVEL1" ,
                    "SVC_LEVEL2" , "RATE_TYPE" , "RATE_MODE" , "RATE" ,
                    "APPLY_GST" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "S" , null , "1" , "100.00" , "1000.00" ,
                    "123456" , "1" , "play01" , "1234567890123456789" , "1" ,
                    "01234567890123456789" , "1" , "1" , "BA" , "1" ,
                    "1000.00" , "0" , "0" , "2011-08-30" , "2011-08-30" ,
                    SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_LINK", testDataTCustPlanLink);

        /**
         * 【Table "T_CUST_PLAN_SVC"s records】
         * has one record in accordance with
         * ID_CUST_PLAN_LINK=1
         * IS_DELETED=0
         * SVC_LEVEL3=1
         * SVC_LEVEL4=1
         * ID_SUPPLIER=1
         */
        String[][] testDataTCustPlanSvc =
            {
                {"ID_CUST_PLAN_SVC" , "ID_CUST_PLAN_LINK" , "SVC_LEVEL3" ,
                    "SVC_LEVEL4" , "ID_SUPPLIER" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"1" , "1" , "1" , "1" , "1" , "0" , "2011-08-30" ,
                    "2011-08-30" , SYS_ADMIN , null}};
        super.insertData("T_CUST_PLAN_SVC", testDataTCustPlanSvc);

        gCpmP03.execute(input);

        String sqsTCustPlanLink =
            super.queryDAO.executeForObject(
                "TEST.G_CPM_P03.SELECT.SQL003", null, String.class);

        Map[] tCustPlanLink =
            super.select("T_CUST_PLAN_LINK", "ID_CUST_PLAN_LINK='"
                + sqsTCustPlanLink + "'", "");

        assertEquals(tCustPlanLink[0].get("UNIT_PRICE"), getAmt(
            100, 31, 12, 26, 4));
        assertEquals(tCustPlanLink[0].get("TOTAL_AMOUNT"), getAmt(
            1000, 31, 12, 26, 4));

    }
}
