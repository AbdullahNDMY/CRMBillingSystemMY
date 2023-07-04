/**
 * @(#)R_SOA_S01SearchBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/09/10
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_soa.dto.R_SOA_S01SearchInput;
import nttdm.bsys.r_soa.dto.R_SOA_S01SearchOutput;

/**
 * @author gplai
 *
 */
public class R_SOA_S01SearchBLogic_Test extends AbstractUtilTest {

    private R_SOA_S01SearchBLogic blogic;
    private R_SOA_S01SearchInput input;
    private BLogicResult result;
    
    /* (non-Javadoc)
     * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
     */
    @Override
    protected void setUpData() throws Exception {
        blogic = new R_SOA_S01SearchBLogic();
        input = new R_SOA_S01SearchInput();
        
        blogic.setQueryDAO(queryDAO);
        blogic.setUpdateDAO(updateDAO);
        
        /**
         * delete data
         */
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        super.deleteAllData("T_AR_STMT_D");
        super.deleteAllData("T_AR_STMT_H");
        
        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {
                        "RESULTROW",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        String[][] dataM_GSET_D = {
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
                        "2",
                        null, "1" } };
        
        
        String[][] dataM_CUST = {
                { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
                        "CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
                        "INTER_COMP", "IS_EXPORTED", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
                        "HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
                        "GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
                        "SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "ID_AUDIT",
                        "MOBILE_NO" },
                { "229743", "0123456789", "Duy Duong", "0123456789",
                        "http://www.nttdata.com.vn",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "", "", null, "", "", "0",
                        "0123456789", "0123456789", "CORP", "0123456789", "",
                        null, "" } };
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        super.insertData("M_CUST", dataM_CUST);
    }
    
    /**
     * search success
     */
    public void testExecute01(){
        String[][] t_ar_stmt_h = {
                { "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
                        "CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

                { "SAT-12-07-09466", "229743", "2012-07-01",
                        "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
                        "cccc", "ddd", "200281", "china", "13313231313",
                        "22222513", "Jim abc", "aaa@qq.com", "MR" }};
        super.insertData("T_AR_STMT_H", t_ar_stmt_h);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        input.setUvo(uvo);
        input.setCboStatementMonth("7");
        input.setTbxStatementYear("2012");
        input.setClickBtnTypeFlg("search");
        
        result = blogic.execute(input);
        
        R_SOA_S01SearchOutput outputDTO = (R_SOA_S01SearchOutput)result.getResultObject();
        assertEquals(1, outputDTO.getListStatement().size());
    }
    
    /**
     * search success
     */
    public void testExecute02(){
        String[][] t_ar_stmt_h = {
                { "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
                        "CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

                { "SAT-12-07-09466", "229743", "2012-07-01",
                        "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
                        "cccc", "ddd", "200281", "china", "13313231313",
                        "22222513", "Jim abc", "aaa@qq.com", "MR" },
                { "SAT-12-07-09467", "229743", "2012-07-01",
                    "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
                    "cccc", "ddd", "200281", "china", "13313231313",
                    "22222513", "Jim abc", "aaa@qq.com", "MR" } };
        super.insertData("T_AR_STMT_H", t_ar_stmt_h);
        
        String[] selectedStatementNo = {"SAT-12-07-09466","SAT-12-07-09467"};
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        input.setCboStatementMonth("7");
        input.setTbxStatementYear("2012");
        input.setClickBtnTypeFlg("search");
        input.setSelectedStatementNo(selectedStatementNo);
        
        result = blogic.execute(input);
        
        R_SOA_S01SearchOutput outputDTO = (R_SOA_S01SearchOutput)result.getResultObject();
        assertEquals(2, outputDTO.getListStatement().size());
    }
    
    /**
     * update statement button success
     */
    public void testExecute03(){
        String[][] t_ar_stmt_h = {
                { "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
                        "CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

                { "SAT-12-07-09466", "229743", "2012-07-01",
                        "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
                        "cccc", "ddd", "200281", "china", "13313231313",
                        "22222513", "Jim abc", "aaa@qq.com", "MR" },
                { "SAT-12-07-09467", "229743", "2012-07-01",
                    "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
                    "cccc", "ddd", "200281", "china", "13313231313",
                    "22222513", "Jim abc", "aaa@qq.com", "MR" } };
        super.insertData("T_AR_STMT_H", t_ar_stmt_h);
        
        String[] selectedStatementNo = {"SAT-12-07-09466","SAT-12-07-09467"};
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        
        input.setCboStatementMonth("7");
        input.setTbxStatementYear("2012");
        input.setHidStatementMonth("7");
        input.setHidStatementYear("2012");
        input.setClickBtnTypeFlg("updateStatement");
        input.setSelectedStatementNo(selectedStatementNo);
        
        result = blogic.execute(input);
        
        R_SOA_S01SearchOutput outputDTO = (R_SOA_S01SearchOutput)result.getResultObject();
        assertEquals(2, outputDTO.getListStatement().size());
    }
    
    /**
     * search check
     */
    public void testExecute04(){
        String[][] t_ar_stmt_h = {
                { "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
                        "CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

                { "SAT-12-07-09466", "229743", "2012-07-01",
                        "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
                        "cccc", "ddd", "200281", "china", "13313231313",
                        "22222513", "Jim abc", "aaa@qq.com", "MR" }};
        super.insertData("T_AR_STMT_H", t_ar_stmt_h);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        input.setUvo(uvo);
        input.setTbxStatementNoFrom("3");
        input.setTbxStatementNoTo("1");
        input.setClickBtnTypeFlg("search");
        
        result = blogic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * update statement button check
     */
    public void testExecute05(){
        String[][] t_ar_stmt_h = {
                { "ID_STMT", "ID_CUST", "STMT_DATE", "STMT_CURRENCY",
                        "CUST_ACC_NO", "ID_LOGIN", "STMT_TOTAL", "CUST_NAME",
                        "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                        "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                        "CONTACT_NAME", "CONTACT_EMAIL", "SALUTATION" },

                { "SAT-12-07-09466", "229743", "2012-07-01",
                        "SGD", "2", "sysadmin", "5000", "test", "aaaaa", "bbb",
                        "cccc", "ddd", "200281", "china", "13313231313",
                        "22222513", "Jim abc", "aaa@qq.com", "MR" }};
        super.insertData("T_AR_STMT_H", t_ar_stmt_h);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        input.setUvo(uvo);
        input.setCboStatementMonth("7");
        input.setTbxStatementYear("2012");
        input.setHidStatementMonth("7");
        input.setHidStatementYear("2012");
        input.setClickBtnTypeFlg("updateStatement");
        
        result = blogic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    /**
     * search not data
     */
    public void testExecute06(){
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        input.setUvo(uvo);
        input.setCboStatementMonth("7");
        input.setTbxStatementYear("2012");
        input.setClickBtnTypeFlg("search");
        
        result = blogic.execute(input);
        
        R_SOA_S01SearchOutput outputDTO = (R_SOA_S01SearchOutput)result.getResultObject();
        assertEquals(0, outputDTO.getListStatement().size());
    }
}
