/**
 * @(#)B_CSBS02_SCRBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/14
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_csb.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * @author gplai
 *
 */
public class B_CSBS02_SCRBLogic_Test extends AbstractUtilTest {

    /**
     * B_CSBS02_SCRBLogic
     */
    private B_CSBS02_SCRBLogic logic;
    /**
     * the B_CSBR01BLogic's paramter
     */
    private Map<String, Object> input ;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CSBS02_SCRBLogic();
        input = new HashMap<String,Object>();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("system admin");
        input.put("uvo", uvo);
    }

    @SuppressWarnings("unchecked")
    public void testExecute1(){
        //
        insertData1();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals("CST", resultObject.get("pattern"));
        assertEquals("MYR", resultObject.get("curCode"));
        assertEquals("N", resultObject.get("paymentStatus"));
        //assertEquals("Payment Ref. 1", resultObject.get("labPaymentRef1"));
        //assertEquals("Payment Ref. 2", resultObject.get("labPaymentRef2"));
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute2(){
        //
        insertData2();
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals("BAC", resultObject.get("pattern"));
        assertEquals(null, resultObject.get("curCode"));
        assertEquals("N", resultObject.get("paymentStatus"));
        //assertEquals("Payment Ref. 1", resultObject.get("labPaymentRef1"));
        //assertEquals("Payment Ref. 2", resultObject.get("labPaymentRef2"));
    }
    
    private void insertData1(){
        super.deleteAllData("M_BANK");
        super.deleteAllData("M_COM_BANKINFO");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_BANK1 ={{
            "ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
            "BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
            {
                "398","Bank Money-XYZ","0123","Bank Money",
                "012","XYZ","","",
                "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
        String[][] dataM_BANK2 ={{
            "ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
            "BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
            {
                "357","Duoc-ACB","Duoc","Duoc",
                "ACB","ACB","123","123",
                "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
        String[][] dataM_BANK3 ={{
            "ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
            "BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
            {
                "381","PBB-CYBERJAYA","1121","PBB",
                "112","CYBERJAYA","","",
                "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
        
        String[][] M_COM_BANKINFO1 ={{
            "ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
            "COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
            "DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
                {"0","11","398","",
                "11111","1","8888",
                TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
        String[][] M_COM_BANKINFO2 ={{
            "ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
            "COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
            "DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
                    {"122","11","357","46",
                "adf","3","24",
                TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"ALL"}};
        String[][] M_COM_BANKINFO3 ={{
            "ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
            "COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
            "DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
                    {"141","11","381","",
                "1234-1234","2","NTTDM",
                TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"ARS"}};
        
        String[][] dataM_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "CB_AUTO_OFFSET",
                        "16",
                        "Cash Book auto offset by payment method or customer",
                        "0",
                        TEST_DAY_TODAY,
                        TEST_DAY_TODAY,
                        "sysadmin",
                        "CST",
                        null, "1" } };
        
        String[][] dataM_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "RESULTROW",
                        "1",
                        "Number of rows of result to be display.",
                        "0",
                        TEST_DAY_TODAY,
                        TEST_DAY_TODAY,
                        "sysadmin",
                        "30",
                        null, "1" } };
        String[][] dataM_GSET_D3 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "DEF_CURRENCY",
                        "1",
                        "Default currency to use in the system.",
                        "0",
                        TEST_DAY_TODAY,
                        TEST_DAY_TODAY,
                        "sysadmin",
                        "MYR",
                        null, "1" } };
        
        String[][] dataM_GSET_H1 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "CB_AUTO_OFFSET",
                        "Cash Book auto offset by payment method or customer",
                        "Settings for Cash Book to auto offset by payment method or customer in batch.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        String[][] dataM_GSET_H2 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "RESULTROW",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        String[][] dataM_GSET_H3 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "DEF_CURRENCY",
                        "Default Currency",
                        "System default currency.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        super.insertData("M_BANK", dataM_BANK1);
        super.insertData("M_BANK", dataM_BANK2);
        super.insertData("M_BANK", dataM_BANK3);
        
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO1);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO2);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO3);
        
        super.insertData("M_GSET_H", dataM_GSET_H1);
        super.insertData("M_GSET_H", dataM_GSET_H2);
        super.insertData("M_GSET_H", dataM_GSET_H3);
        super.insertData("M_GSET_D", dataM_GSET_D1);
        super.insertData("M_GSET_D", dataM_GSET_D2);
        super.insertData("M_GSET_D", dataM_GSET_D3);
    }
    
    private void insertData2(){
        super.deleteAllData("M_BANK");
        super.deleteAllData("M_COM_BANKINFO");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_BANK1 ={{
            "ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
            "BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
            {
                "398","Bank Money-XYZ","0123","Bank Money",
                "012","XYZ","","",
                "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
        String[][] dataM_BANK2 ={{
            "ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
            "BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
            {
                "357","Duoc-ACB","Duoc","Duoc",
                "ACB","ACB","123","123",
                "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
        String[][] dataM_BANK3 ={{
            "ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
            "BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
            {
                "381","PBB-CYBERJAYA","1121","PBB",
                "112","CYBERJAYA","","",
                "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
        
        String[][] M_COM_BANKINFO1 ={{
            "ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
            "COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
            "DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
                {"0","11","398","",
                "11111","1","8888",
                TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
        String[][] M_COM_BANKINFO2 ={{
            "ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
            "COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
            "DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
                    {"122","11","357","46",
                "adf","3","24",
                TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"ALL"}};
        String[][] M_COM_BANKINFO3 ={{
            "ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
            "COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
            "DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
                    {"141","11","381","",
                "1234-1234","2","NTTDM",
                TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"ARS"}};
        
        String[][] dataM_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "CB_AUTO_OFFSET",
                        "16",
                        "Cash Book auto offset by payment method or customer",
                        "0",
                        TEST_DAY_TODAY,
                        TEST_DAY_TODAY,
                        "sysadmin",
                        "BAC",
                        null, "1" } };
        
        String[][] dataM_GSET_D2 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "RESULTROW",
                        "1",
                        "Number of rows of result to be display.",
                        "0",
                        TEST_DAY_TODAY,
                        TEST_DAY_TODAY,
                        "sysadmin",
                        "30",
                        null, "1" } };
        String[][] dataM_GSET_D3 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "DEF_CURRENCY",
                        "1",
                        "Default currency to use in the system.",
                        "0",
                        TEST_DAY_TODAY,
                        TEST_DAY_TODAY,
                        "sysadmin",
                        "MYR",
                        null, "1" } };
        
        String[][] dataM_GSET_H1 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "CB_AUTO_OFFSET",
                        "Cash Book auto offset by payment method or customer",
                        "Settings for Cash Book to auto offset by payment method or customer in batch.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        String[][] dataM_GSET_H2 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "RESULTROW",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        String[][] dataM_GSET_H3 = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "DEF_CURRENCY",
                        "Default Currency",
                        "System default currency.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        super.insertData("M_BANK", dataM_BANK1);
        super.insertData("M_BANK", dataM_BANK2);
        super.insertData("M_BANK", dataM_BANK3);
        
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO1);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO2);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO3);
        
        super.insertData("M_GSET_H", dataM_GSET_H1);
        super.insertData("M_GSET_H", dataM_GSET_H2);
        super.insertData("M_GSET_H", dataM_GSET_H3);
        super.insertData("M_GSET_D", dataM_GSET_D1);
        super.insertData("M_GSET_D", dataM_GSET_D2);
        super.insertData("M_GSET_D", dataM_GSET_D3);
    }
}
