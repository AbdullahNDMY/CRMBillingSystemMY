/**
 * @(#)B_CSBS02_ESCRBLogic_Test.java
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
public class B_CSBS02_ESCRBLogic_Test extends AbstractUtilTest {

    /**
     * B_CSBS02_ESCRBLogic
     */
    private B_CSBS02_ESCRBLogic logic;
    /**
     * the B_CSBR01BLogic's paramter
     */
    private Map<String, Object> input ;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CSBS02_ESCRBLogic();
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
        input.put("curCode", "MYR");
        input.put("idCust", "229743");
        input.put("pattern", "CST");
        input.put("pmtMethod", "NT");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    public void testExecute2(){
        //
        insertData1();
        
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        input.put("curCode", "MYR");
        input.put("idCust", "229743");
        input.put("pattern", "CST");
        input.put("pmtMethod", "CQ");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    public void testExecute3(){
        //
        insertData2();
        
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        input.put("curCode", "MYR");
        input.put("idCust", "229743");
        input.put("pattern", "CST");
        input.put("pmtMethod", "NT");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    public void testExecute4(){
        //
        insertData2();
        
        String receiptNo = "111238              ";
        input.put("idRef", receiptNo);
        input.put("curCode", "MYR");
        input.put("idCust", "229743");
        input.put("pattern", "BAC");
        input.put("pmtMethod", "NT");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    private void insertData1(){
        
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        String[][] dataM_CUST1 ={{
            "ID_CUST","CUST_ACC_NO","CUST_NAME","CO_REGNO",
            "CO_WEBSITE","CO_EMAIL","CO_TEL_NO","CO_FAX_NO",
            "INTER_COMP","IS_EXPORTED","IS_DELETED","DATE_CREATED",
            "DATE_UPDATED","ID_LOGIN","SALUTATION","CUST_ID_NO",
            "CUST_BIRTH_DATE","HOME_TEL_NO","HOME_FAX_NO","PRINT_STATEMENT",
            "GBOC_ACC_NO","OTHERS_REF_NO","CUSTOMER_TYPE","SALES_FORCE_ACC_ID",
            "AFFILIATE_CODE","ID_AUDIT","MOBILE_NO"},
            {
                "88543","data","Corporate A","",
                "","","","",
                "1","0","0","2010-12-01",
                "2010-12-01","USERFULL","","",
                null,"","","0",
                "","","CORP","",
                "21101",null,""}};
        String[][] dataM_CUST2 ={{
            "ID_CUST","CUST_ACC_NO","CUST_NAME","CO_REGNO",
            "CO_WEBSITE","CO_EMAIL","CO_TEL_NO","CO_FAX_NO",
            "INTER_COMP","IS_EXPORTED","IS_DELETED","DATE_CREATED",
            "DATE_UPDATED","ID_LOGIN","SALUTATION","CUST_ID_NO",
            "CUST_BIRTH_DATE","HOME_TEL_NO","HOME_FAX_NO","PRINT_STATEMENT",
            "GBOC_ACC_NO","OTHERS_REF_NO","CUSTOMER_TYPE","SALES_FORCE_ACC_ID",
            "AFFILIATE_CODE","ID_AUDIT","MOBILE_NO"},
            {
                "226943","","NTTDATA Vietnam","",
                "","","","",
                "1","0","0","2011-04-06",
                "2011-05-25","USERFULL","","",
                null,"","","0",
                "","","CORP","",
                "21101",null,""}};
        
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
        
        super.insertData("M_CUST", dataM_CUST1);
        super.insertData("M_CUST", dataM_CUST2);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }
    
    private void insertData2(){
        
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
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
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }
}
