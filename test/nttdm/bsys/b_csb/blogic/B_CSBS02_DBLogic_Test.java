/**
 * @(#)B_CSBS02_DBLogic_Test.java
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

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author gplai
 *
 */
public class B_CSBS02_DBLogic_Test extends AbstractUtilTest {

    /**
     * B_CSBS02_DBLogic
     */
    private B_CSBS02_DBLogic logic;
    /**
     * the B_CSBR01BLogic's paramter
     */
    private Map<String, Object> input ;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CSBS02_DBLogic();
        input = new HashMap<String,Object>();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("system admin");
        input.put("uvo", uvo);
    }

    public void testExecute1(){
        //
        insertData1();
        
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        input.put("isClosed", "0");
        input.put("model", "Delete");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        BLogicMessages resultObject = result.getMessages();
        Map<String,Object> csbHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL001", receiptNo);
        assertEquals("1", csbHData.get("IS_DELETED"));
        assertEquals("info.ERR2SC005", resultObject.get().next().getKey());
        
        String idRef = "1                   ";
        Map<String,Object> bilHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", idRef);
        assertEquals("0", bilHData.get("IS_SETTLED"));
        assertEquals("0", CommonUtils.toString(bilHData.get("PAID_AMOUNT")));
        
        idRef = "2                   ";
        bilHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", idRef);
        assertEquals("0", bilHData.get("IS_SETTLED"));
        assertEquals("-1", CommonUtils.toString(bilHData.get("PAID_AMOUNT")));
    }
    
    public void testExecute2(){
        //
        insertData2();
        
        String receiptNo = "111250              ";
        input.put("idRef", receiptNo);
        input.put("isClosed", "0");
        input.put("model", "Reject");
        input.put("remark", "desc info");
        input.put("rejectionDate", "01/09/2011");
        input.put("amtReceived", "321");
        input.put("idBillAccount", "3");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        BLogicMessages resultObject = result.getMessages();
        Map<String,Object> csbHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL001", receiptNo);
        assertEquals("2011-09-01", CommonUtils.toString(csbHData.get("REJECT_DATE")));
        assertEquals("desc info", csbHData.get("REJECT_DESC"));
        assertEquals("info.ERR2SC003", resultObject.get().next().getKey());
        
        String idRef = "1                   ";
        Map<String,Object> bilHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", idRef);
        assertEquals("1", bilHData.get("IS_SETTLED"));
        assertEquals("51", CommonUtils.toString(bilHData.get("PAID_AMOUNT")));
        
        idRef = "2                   ";
        bilHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", idRef);
        assertEquals("1", bilHData.get("IS_SETTLED"));
        assertEquals("0", CommonUtils.toString(bilHData.get("PAID_AMOUNT")));
        
        String idBillAccount ="3";
        Map<String,Object> bacHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL003", idBillAccount);
        assertEquals("1321", CommonUtils.toString(bacHData.get("TOTAL_AMT_DUE")));
    }
    
    public void testExecute3(){
        //
        insertData2();
        
        String receiptNo = "111250              ";
        input.put("idRef", receiptNo);
        input.put("isClosed", "0");
        input.put("model", "Delete");
        input.put("remark", "desc info");
        input.put("rejectionDate", "01/09/2011");
        input.put("amtReceived", "");
        input.put("idBillAccount", "3");
        
        BLogicResult result = logic.execute(input);
        BLogicMessages resultObject = result.getErrors();
        assertEquals("info.ERR2SC006", resultObject.get().next().getKey());
        
    }
    
    public void testExecute4(){
        //
        insertData2();
        
        String receiptNo = "111250              ";
        input.put("idRef", receiptNo);
        input.put("isClosed", "0");
        input.put("model", "Reject");
        input.put("remark", "desc info");
        input.put("rejectionDate", "01/09/2011");
        input.put("amtReceived", "");
        input.put("idBillAccount", "3");
        
        BLogicResult result = logic.execute(input);
        BLogicMessages resultObject = result.getErrors();
        assertEquals("info.ERR2SC004", resultObject.get().next().getKey());
        
    }
    
    private void insertData1(){
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
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
        
        
        
        String[][] dataT_BIL_H1 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "1                   ", "IN", "1", "", "2011-01-05", 
                "CQ", "229743", "BA","PC", 
                null, null, null, null, 
                "BBBBBBBBBB", "30 Days","MYR", "5", 
                "1", "606", "51", TEST_DAY_TODAY, 
                "1", "0","0", "1", 
                "MYR", "3.124", "606", null, 
                "1","2011-01-05", "joeykan", "0",
                "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                "2000", "ddd", "11", "", 
                "Mr.Timothy Yap", "", "0","w.h.wong", 
                TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        String[][] dataT_BIL_H2 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "2                   ", "IN", "0", "1", "2011-01-27", 
              "CQ", "229743", "BA","PC", 
              null, null, null, null, 
              "sysadmin;BIF001", "30 Days", "MYR", "6", 
              "0", "3500", "0", TEST_DAY_TODAY, 
              "1", "0","0", "1", 
              "USD", "3.124", "1120.36", "50", 
              "1","2011-01-05", "joeykan", "0",
              "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
              "2000", "ddd", "11", "", 
              "Mr.Timothy Yap", "", "0","w.h.wong", 
              TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        super.insertData("T_BIL_H", dataT_BIL_H1);
        super.insertData("T_BIL_H", dataT_BIL_H2);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }
    
    private void insertData2(){
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111250              ";
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        String[][] dataT_CSB_H ={{
            "RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
            "PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
            "REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
            "IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
            "ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
            "REJECT_DATE","REJECT_DESC","OVER_PAID"},
            {
                receiptNo,"229743","122","",
                "CQ","2011-04-12","Receipt Reference ","MYR",
                "Remark 1111","N","Cheque No.","Bank-In Slip No.",
                "0","0","2011-07-07","2011-07-07",
                "BIF001","321","111","321",
                null,"3","0","1",
                null,"","1"}};
        
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
        
        
        
        String[][] dataT_BIL_H1 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "1                   ", "IN", "1", "", "2011-01-05", 
                "CQ", "229743", "BA","PC", 
                null, null, null, null, 
                "BBBBBBBBBB", "30 Days","MYR", "5", 
                "1", "606", "51", TEST_DAY_TODAY, 
                "1", "0","0", "1", 
                "MYR", "3.124", "606", null, 
                "1","2011-01-05", "joeykan", "0",
                "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                "2000", "ddd", "11", "", 
                "Mr.Timothy Yap", "", "0","w.h.wong", 
                TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        String[][] dataT_BIL_H2 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "2                   ", "IN", "0", "1", "2011-01-27", 
              "CQ", "229743", "BA","PC", 
              null, null, null, null, 
              "sysadmin;BIF001", "30 Days", "MYR", "6", 
              "0", "3500", "0", TEST_DAY_TODAY, 
              "1", "0","0", "1", 
              "USD", "3.124", "1120.36", "50", 
              "1","2011-01-05", "joeykan", "0",
              "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
              "2000", "ddd", "11", "", 
              "Mr.Timothy Yap", "", "0","w.h.wong", 
              TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        
        String[][] dataT_BAC_H ={{
            "ID_BILL_ACCOUNT","ID_CUST","PAYMENT_METHOD","BILL_CURRENCY",
            "IS_DISPLAY_EXP_AMT","EXPORT_CURRENCY","CUST_ADR_TYPE","CONTACT_TYPE",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
            "FIXED_FOREX","ID_AUDIT","IS_SINGPOST","TOTAL_AMT_DUE"},
            { "3                   ","229743","CQ","MYR",
               "0","","BA","PC",
               "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",
               null,null,"1","1000"} };
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        super.insertData("T_BIL_H", dataT_BIL_H1);
        super.insertData("T_BIL_H", dataT_BIL_H2);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        
        super.insertData("T_BAC_H", dataT_BAC_H);
    }
}
