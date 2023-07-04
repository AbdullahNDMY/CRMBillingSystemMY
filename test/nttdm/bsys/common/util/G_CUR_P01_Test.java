/**
 * @(#)G_CUR_P01.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/04/24
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.Map;

/**
 * @author gplai
 * 
 */
public class G_CUR_P01_Test extends AbstractUtilTest {

    private G_CUR_P01 gCurP01;

    @Override
    protected void setUpData() throws Exception {
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_CURRENCY");
    }

    /**
     * case1:fixed_forex is not null and billing currency is the default
     * currency
     * 
     * @throws Exception
     */
    public void testExecute1() throws Exception {
        String[][] testDataMGsetD = {
                { "ID_SET", "SET_SEQ", "SET_VALUE", "SET_DESC", "SET_APPLY", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "DEF_CURRENCY", "1", "SGD", "", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null },
                { "CURRENCY_STD", "1", "0", "CURRENCY_STD", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null } };
        super.insertData("M_GSET_D", testDataMGsetD);

        gCurP01 = new G_CUR_P01(queryDAO);
        Map<String, Object> result = gCurP01.convertCurrency("SGD", 100, "SGD", "20");
        assertEquals(5.0, result.get("EXPORT_AMOUNT"));
        assertEquals(20.0, result.get("CUR_RATE"));
    }

    /**
     * case2:fixed_forex is not null and billing currency not is the default
     * currency
     * 
     * @throws Exception
     */
    public void testExecute2() throws Exception {
        String[][] testDataMGsetD = {
                { "ID_SET", "SET_SEQ", "SET_VALUE", "SET_DESC", "SET_APPLY", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "DEF_CURRENCY", "1", "SGD", "", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null },
                { "CURRENCY_STD", "1", "0", "CURRENCY_STD", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null } };
        super.insertData("M_GSET_D", testDataMGsetD);

        gCurP01 = new G_CUR_P01(queryDAO);
        Map<String, Object> result = gCurP01.convertCurrency("SGG", 100, "SGD", "20");
        assertEquals(2000.0, result.get("EXPORT_AMOUNT"));
        assertEquals(20.0, result.get("CUR_RATE"));
    }

    /**
     * case3:fixed_forex is null and billing currency is the default currency
     * 
     * @throws Exception
     */
    public void testExecute3() throws Exception {
        String[][] testDataMGsetD = {
                { "ID_SET", "SET_SEQ", "SET_VALUE", "SET_DESC", "SET_APPLY", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "DEF_CURRENCY", "1", "SGD", "", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null },
                { "CURRENCY_STD", "1", "0", "CURRENCY_STD", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null } };
        String[][] testMCurrency = {
                { "RATE_DATE", "CUR_CODE", "RATE_TO", "RATE_FROM", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { TEST_DAY_TODAY, "NZD", "2", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null } };
        super.insertData("M_GSET_D", testDataMGsetD);
        super.insertData("M_CURRENCY", testMCurrency);

        gCurP01 = new G_CUR_P01(queryDAO);
        Map<String, Object> result = gCurP01.convertCurrency("SGD", 100, "NZD", null);
        assertEquals(50.0, result.get("EXPORT_AMOUNT"));
        assertEquals(2.0, result.get("CUR_RATE"));
    }

    /**
     * case4:fixed_forex is null and billing currency not is the default
     * currency
     * 
     * @throws Exception
     */
    public void testExecute4() throws Exception {
        String[][] testDataMGsetD = {
                { "ID_SET", "SET_SEQ", "SET_VALUE", "SET_DESC", "SET_APPLY", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "DEF_CURRENCY", "1", "SGD", "", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null },
                { "CURRENCY_STD", "1", "0", "CURRENCY_STD", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null } };
        String[][] testMCurrency = {
                { "RATE_DATE", "CUR_CODE", "RATE_TO", "RATE_FROM", "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT" },
                { TEST_DAY_TODAY, "SGP", "2", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null } };
        super.insertData("M_GSET_D", testDataMGsetD);
        super.insertData("M_CURRENCY", testMCurrency);

        gCurP01 = new G_CUR_P01(queryDAO);
        Map<String, Object> result = gCurP01.convertCurrency("SGP", 100, "NZD", null);
        assertEquals(100.0, result.get("EXPORT_AMOUNT"));
        assertEquals(1.0, result.get("CUR_RATE"));
    }

    /**
     * case5:fixed_forex is not null and billing currency is the default
     * currency
     * 
     * @throws Exception
     */
    public void testExecute5() throws Exception {
        String[][] testDataMGsetD = {
                { "ID_SET", "SET_SEQ", "SET_VALUE", "SET_DESC", "SET_APPLY", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "DEF_CURRENCY", "1", "SGD", "", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null },
                { "CURRENCY_STD", "1", "1", "CURRENCY_STD", "1", "0", "2011-11-24", "2011-07-13", "USERFULL", null } };
        super.insertData("M_GSET_D", testDataMGsetD);

        gCurP01 = new G_CUR_P01(queryDAO);
        Map<String, Object> result = gCurP01.convertCurrency("USD", 100, "SGD", "1.2457");
        assertEquals(100 * 1.2457d, Double.parseDouble(result.get("EXPORT_AMOUNT").toString()));
        assertEquals(1.2457, result.get("CUR_RATE"));
    }

}
