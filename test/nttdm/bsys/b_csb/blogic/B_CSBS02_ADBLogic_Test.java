/**
 * @(#)B_CSBS02_ADBLogic_Test.java
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
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

import org.apache.struts.util.LabelValueBean;

/**
 * @author gplai
 *
 */
public class B_CSBS02_ADBLogic_Test extends AbstractUtilTest {

    /**
     * B_CSBS02_ADBLogic
     */
    private B_CSBS02_ADBLogic logic;
    /**
     * the B_CSBR01BLogic's paramter
     */
    private Map<String, Object> input ;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CSBS02_ADBLogic();
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
        
        input.put("pattern", "CST");
        input.put("action", "pmtMethodChange");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(null, resultObject.get("payer"));
        assertEquals(null, resultObject.get("other"));
        assertEquals(0, ((List<LabelValueBean>)resultObject.get("cbPayer")).size());
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute2(){
        //
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        input.put("pattern", "CST");
        input.put("action", "pmtMethodChange");
        input.put("pmtMethod", "NT");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(null, resultObject.get("payer"));
        assertEquals(1, ((List<LabelValueBean>)resultObject.get("cbPayer")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute3(){
        //
        insertData3();
        
        input.put("pattern", "CST");
        input.put("action", "pmtMethodChange");
        input.put("pmtMethod", "NT");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(3, ((List<LabelValueBean>)resultObject.get("cbPayer")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute4(){
        //
        insertData4();
        
        input.put("pattern", "CST");
        input.put("action", "pmtMethodChange");
        input.put("pmtMethod", "CQ");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(2, ((List<LabelValueBean>)resultObject.get("cbPayer")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute5(){
        //
        insertData5();
        
        input.put("pattern", "CST");
        input.put("action", "payerChange");
        input.put("pmtMethod", "NT");
        input.put("curCode", "MYR");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute6(){
        
        input.put("pattern", "CST");
        input.put("action", "payerChange");
        input.put("pmtMethod", "CC");
        input.put("curCode", "MYR");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(null, resultObject.get("other"));
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute7(){
        
        insertData7();
        
        input.put("pattern", "CST");
        input.put("action", "payerChange");
        input.put("pmtMethod", "GR");
        input.put("curCode", "MYR");
        input.put("payer", "229743");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(null, resultObject.get("other"));
        assertEquals(2, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute8(){
        //
        insertData5();
        
        input.put("pattern", "CST");
        input.put("action", "currencyChange");
        input.put("pmtMethod", "NT");
        input.put("curCode", "MYR");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute9(){
        
        input.put("pattern", "CST");
        input.put("action", "currencyChange");
        input.put("pmtMethod", "CC");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute10(){
        
        insertData7();
        
        input.put("pattern", "CST");
        input.put("action", "currencyChange");
        input.put("pmtMethod", "GR");
        input.put("curCode", "MYR");
        input.put("payer", "229743");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(2, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute11(){
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        input.put("pattern", "BAC");
        input.put("action", "payerChange");
        input.put("pmtMethod", "GR");
        input.put("curCode", "MYR");
        input.put("payer", "");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(null, resultObject.get("pmtMethod"));
        assertEquals(null, resultObject.get("curCode"));
        assertEquals(0, ((List<LabelValueBean>)resultObject.get("cboBillAccountNo")).size());
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute12(){
        
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        input.put("pattern", "BAC");
        input.put("action", "payerChange");
        input.put("pmtMethod", "GR");
        input.put("curCode", "MYR");
        input.put("payer", "229743");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(null, resultObject.get("pmtMethod"));
        assertEquals(null, resultObject.get("curCode"));
        assertEquals(0, ((List<LabelValueBean>)resultObject.get("cboBillAccountNo")).size());
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute13(){
        
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        input.put("pattern", "BAC");
        input.put("action", "billAccNoChange");
        input.put("pmtMethod", "GR");
        input.put("curCode", "MYR");
        input.put("payer", "229743");
        input.put("idBillAccount", "");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(null, resultObject.get("pmtMethod"));
        assertEquals(null, resultObject.get("curCode"));
        assertEquals(0, ((List<LabelValueBean>)resultObject.get("cboBillAccountNo")).size());
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute14(){
        
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        String[][] dataT_BAC_H ={{
            "ID_BILL_ACCOUNT","ID_CUST","PAYMENT_METHOD","BILL_CURRENCY",
            "IS_DISPLAY_EXP_AMT","EXPORT_CURRENCY","CUST_ADR_TYPE","CONTACT_TYPE",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
            "FIXED_FOREX","ID_AUDIT","IS_SINGPOST","TOTAL_AMT_DUE"},
            { "3                   ","229743","CQ","MYR",
               "0","","BA","PC",
               "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",
               null,null,"1","1000"} };
        
        String[][] dataT_BIL_H ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
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
            { "11000012            ", "IN", "1", "3", "2011-01-05", 
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
        
        super.insertData("T_BAC_H", dataT_BAC_H);
        super.insertData("T_BIL_H", dataT_BIL_H);
        
        input.put("pattern", "BAC");
        input.put("action", "billAccNoChange");
        input.put("pmtMethod", "GR");
        input.put("curCode", "MYR");
        input.put("payer", "229743");
        input.put("idBillAccount", "3");
        
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals("CQ", resultObject.get("pmtMethod"));
        assertEquals("MYR", resultObject.get("curCode"));
        assertEquals(1, ((List<LabelValueBean>)resultObject.get("cboBillAccountNo")).size());
        assertEquals(1, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    @SuppressWarnings("unchecked")
    public void testExecute15(){
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        input.put("pattern", "CST");
        input.put("action", "payerChange");
        input.put("pmtMethod", "NT");
        input.put("curCode", "MYR");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> resultObject = (Map<String,Object>)result.getResultObject();
        assertEquals(0, ((List<Debit_info_bean>)resultObject.get("debitInfos")).size());
    }
    
    private void insertData3(){
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
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
        
        super.insertData("M_CUST", dataM_CUST1);
        super.insertData("M_CUST", dataM_CUST2);
    }
    
    private void insertData4(){
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
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
        
        super.insertData("M_CUST", dataM_CUST1);
        super.insertData("M_CUST", dataM_CUST2);
    }
    
    private void insertData5(){
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
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
        
        super.insertData("M_CUST", dataM_CUST1);
        super.insertData("M_CUST", dataM_CUST2);
    }
    
    private void insertData7(){
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
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
        
        super.insertData("M_CUST", dataM_CUST1);
        super.insertData("M_CUST", dataM_CUST2);
        
        super.insertData("T_BIL_H", dataT_BIL_H1);
        super.insertData("T_BIL_H", dataT_BIL_H2);
    }
}
