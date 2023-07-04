/**
 * @(#)B_CSBS02_VBLogic_Test.java
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
public class B_CSBS02_VBLogic_Test extends AbstractUtilTest {

    /**
     * B_CSBS02_VBLogic
     */
    private B_CSBS02_VBLogic logic;
    /**
     * the B_CSBR01BLogic's paramter
     */
    private Map<String, Object> input ;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CSBS02_VBLogic();
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
        //insert data
        insertData1();
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals("No", resultObject.get("paidPreInvoiceText"));
        assertEquals("No", resultObject.get("overPaidText"));
        assertEquals("CQ", resultObject.get("pmtMethod"));
        //assertEquals("Cheque No.", resultObject.get("labPaymentRef1"));
        //assertEquals("Bank-In Slip No.", resultObject.get("labPaymentRef2"));
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute2(){
        //insert data
        insertData2();
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals("Yes", resultObject.get("paidPreInvoiceText"));
        assertEquals("Yes", resultObject.get("overPaidText"));
        assertEquals("NT", resultObject.get("pmtMethod"));
        //assertEquals("Payment Ref. 1", resultObject.get("labPaymentRef1"));
        //assertEquals("Payment Ref. 2", resultObject.get("labPaymentRef2"));
    }
    @SuppressWarnings("unchecked")
    public void testExecute3(){
        //insert data
        insertData3();
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals("Yes", resultObject.get("paidPreInvoiceText"));
        assertEquals("Yes", resultObject.get("overPaidText"));
        assertEquals("CC", resultObject.get("pmtMethod"));
        //assertEquals("Credit Card No.", resultObject.get("labPaymentRef1"));
        //assertEquals("Credit Card Exp.Date", resultObject.get("labPaymentRef2"));
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute4(){
        //insert data
        insertData4();
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals("Yes", resultObject.get("paidPreInvoiceText"));
        assertEquals("Yes", resultObject.get("overPaidText"));
        assertEquals("GR", resultObject.get("pmtMethod"));
        //assertEquals("GIRO Account No.", resultObject.get("labPaymentRef1"));
        //assertEquals("Other GIRO Ref.", resultObject.get("labPaymentRef2"));
    }
    
    private void insertData1(){
        
        super.deleteAllData("M_BANK");
        super.deleteAllData("M_COM_BANKINFO");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        String[][] dataT_CSB_H ={{
            "RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
            "PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
            "REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
            "IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
            "ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
            "REJECT_DATE","REJECT_DESC","OVER_PAID"},
            {
                receiptNo,"229743","141","",
                "CQ","2011-04-12","4","MYR",
                "REMARK","N","Cheque No.","Bank-In Slip No.",
                "0","0","2011-07-07","2011-07-07",
                "BIF001","2331","3211","2267",
                null,"0","0","0",
                "2011-08-30","1111","0"}};
        String[][] dataT_CSB_D1 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"1                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"606","51",
                "1","21",null}};
        String[][] dataT_CSB_D2 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"2                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"3500","1",
                "2","1",null}};
        
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
        
        String[][] dataM_GSET_D = {
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
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "CB_AUTO_OFFSET",
                        "Cash Book auto offset by payment method or customer",
                        "Settings for Cash Book to auto offset by payment method or customer in batch.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        super.insertData("M_BANK", dataM_BANK1);
        super.insertData("M_BANK", dataM_BANK2);
        super.insertData("M_BANK", dataM_BANK3);
        
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO1);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO2);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO3);
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }
    
    private void insertData2(){
        
        super.deleteAllData("M_BANK");
        super.deleteAllData("M_COM_BANKINFO");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        String[][] dataT_CSB_H ={{
            "RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
            "PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
            "REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
            "IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
            "ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
            "REJECT_DATE","REJECT_DESC","OVER_PAID"},
            {
                receiptNo,"229743","141","",
                "NT","2011-04-12","4","MYR",
                "REMARK","N","Cheque No.","Bank-In Slip No.",
                "0","0","2011-07-07","2011-07-07",
                "BIF001","2331","3211","2267",
                null,"0","0","1",
                "2011-08-30","1111","1"}};
        String[][] dataT_CSB_D1 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"1                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"606","51",
                "1","21",null}};
        String[][] dataT_CSB_D2 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"2                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"3500","1",
                "2","1",null}};
        
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
        
        String[][] dataM_GSET_D = {
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
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "CB_AUTO_OFFSET",
                        "Cash Book auto offset by payment method or customer",
                        "Settings for Cash Book to auto offset by payment method or customer in batch.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        super.insertData("M_BANK", dataM_BANK1);
        super.insertData("M_BANK", dataM_BANK2);
        super.insertData("M_BANK", dataM_BANK3);
        
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO1);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO2);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO3);
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }
    
    private void insertData3(){
        
        super.deleteAllData("M_BANK");
        super.deleteAllData("M_COM_BANKINFO");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        String[][] dataT_CSB_H ={{
            "RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
            "PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
            "REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
            "IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
            "ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
            "REJECT_DATE","REJECT_DESC","OVER_PAID"},
            {
                receiptNo,"229743","141","",
                "CC","2011-04-12","4","MYR",
                "REMARK","N","Cheque No.","Bank-In Slip No.",
                "0","0","2011-07-07","2011-07-07",
                "BIF001","2331","3211","2267",
                null,"0","0","1",
                "2011-08-30","1111","1"}};
        String[][] dataT_CSB_D1 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"1                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"606","51",
                "1","21",null}};
        String[][] dataT_CSB_D2 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"2                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"3500","1",
                "2","1",null}};
        
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
        
        String[][] dataM_GSET_D = {
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
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "CB_AUTO_OFFSET",
                        "Cash Book auto offset by payment method or customer",
                        "Settings for Cash Book to auto offset by payment method or customer in batch.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        super.insertData("M_BANK", dataM_BANK1);
        super.insertData("M_BANK", dataM_BANK2);
        super.insertData("M_BANK", dataM_BANK3);
        
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO1);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO2);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO3);
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }

    private void insertData4(){
        
        super.deleteAllData("M_BANK");
        super.deleteAllData("M_COM_BANKINFO");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        String[][] dataT_CSB_H ={{
            "RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
            "PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
            "REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
            "IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
            "ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
            "REJECT_DATE","REJECT_DESC","OVER_PAID"},
            {
                receiptNo,"229743","141","",
                "GR","2011-04-12","4","MYR",
                "REMARK","N","Cheque No.","Bank-In Slip No.",
                "0","0","2011-07-07","2011-07-07",
                "BIF001","2331","3211","2267",
                null,"0","0","1",
                "2011-08-30","1111","1"}};
        String[][] dataT_CSB_D1 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"1                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"606","51",
                "1","21",null}};
        String[][] dataT_CSB_D2 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"2                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"3500","1",
                "2","1",null}};
        
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
        
        String[][] dataM_GSET_D = {
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
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "CB_AUTO_OFFSET",
                        "Cash Book auto offset by payment method or customer",
                        "Settings for Cash Book to auto offset by payment method or customer in batch.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        super.insertData("M_BANK", dataM_BANK1);
        super.insertData("M_BANK", dataM_BANK2);
        super.insertData("M_BANK", dataM_BANK3);
        
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO1);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO2);
        super.insertData("M_COM_BANKINFO", M_COM_BANKINFO3);
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }
}
