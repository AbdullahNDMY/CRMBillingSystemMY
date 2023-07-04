/**
 * @(#)R_AGR_R02BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/19
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_agr.blogic;

import java.util.HashMap;
import java.util.List;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_agr.dto.R_AGR_R02Input;
import nttdm.bsys.r_agr.dto.R_AGR_R02Output;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Output;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Input;

/**
 * Test Class for nttdm.bsys.m_cdm.blogic.R_AGR_R02BLogic
 * 
 * @author xycao
 */
public class R_AGR_R02BLogic_Test extends AbstractUtilTest {

	private R_AGR_R02BLogic blogic;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new R_AGR_R02BLogic();
		blogic.setQueryDAO(queryDAO);
		blogic.setUpdateDAO(updateDAO);

		// delete all exist data
        super.deleteAllData("T_QCS_H");
		super.deleteAllData("m_cust");
	    super.deleteAllData("t_bil_d");
		super.deleteAllData("t_bil_h");
		
        // insert data to
        String[][] dataT_BIL_H = {
            { "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
                    "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
                    "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
                    "TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
                    "BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
                    "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
                    "IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
                    "EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
                    "DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
                    "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
                    "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
                    "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
                    "PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
                    "ID_LOGIN", "ID_AUDIT" },
            { "2", "IN", "0", "1", "2011-01-05", "CQ", "229743", "BA",
                    "PC", null, null, null, null, "bhchan", "30 Days",
                    "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0", "0",
                    "0", "1", "USD", "3.124", "1120.36", "50", "1",
                    TEST_DAY_TODAY, "joeykan", "0",
                    "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                    "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                    "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                    AUDIT_VALUE } };
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
                    TEST_DAY_TODAY, "USERFULL", "", "", "2011-01-02", "", "", "0",
                    "0123456789", "0123456789", "CORP", "0123456789", "ABC",
                    AUDIT_VALUE, "" } };
    String[][] dataT_BIL_D = {
            { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                    "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
                    "APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
                    "ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
                    "SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
                    "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                    "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
            { "2", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                    "201", "2216", "114", "0", "0", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                    TEST_DAY_TODAY, "system", AUDIT_VALUE, null,"0","1" } };
    

         //insert data
        super.insertData("T_BIL_H", dataT_BIL_H);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("T_BIL_D", dataT_BIL_D);

	}

	/**
	 * 
	 * Case : test normal situation <br>
	 * condition: Bill Month = "09" <br>
	 * condition: Year = "2011" <br>
	 * condition: Customer Name = "" <br>
	 * condition: Affiliate Code = "" <br>
	 * condition: Payment Method  = "" <br>
	 * condition: Currency  = "" <br>
	 * result.getResultString() = "success" <br>
	 */
	public void testExecute01() {

        R_AGR_R02Input input = new R_AGR_R02Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);
		input.setCboBillMonth("1");
		input.setTbxBillYear("2011");
		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxBillDocumentNo("");
		
		BLogicResult result = blogic.execute(input);
		R_AGR_R02Output output = (R_AGR_R02Output) result.getResultObject();
		List<HashMap<String, Object>> list = output.getListAgingReport();

		// ctmid check the result
		assertEquals("Duy Duong", list.get(0).get("CUST_NAME"));
		assertEquals("01/2011", list.get(0).get("DATE_REQ"));
		assertEquals("ABC", list.get(0).get("AFFILIATE_CODE"));
		assertEquals("2", list.get(0).get("ID_REF"));
		assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
		assertEquals("3500", list.get(0).get("BILL_AMOUNT").toString());
		assertEquals("2500", list.get(0).get("OUTSTANDING_AMOUNT").toString());
		assertEquals("CQ",list.get(0).get("PAY_METHOD"));
		assertEquals("success", result.getResultString());
	}
	
    /**
     * 
     * Case : test normal situation <br>
     * condition: Bill Month = "" <br>
     * condition: Year = "" <br>
     * condition: Customer Name = "Duy Duong" <br>
     * condition: Affiliate Code = "" <br>
     * condition: Payment Method  = "" <br>
     * condition: Currency  = "" <br>
     * result.getResultString() = "success" <br>
     */
    public void testExecute02() {

        R_AGR_R02Input input = new R_AGR_R02Input();
        BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
        uvoObject.setId_user("sysadmin");
        uvoObject.setIs_deleted("0");
        uvoObject.setIs_need_change_password("0");
        uvoObject.setLast_pwd_change("15/08/2011");
        uvoObject.setPassword("sysadmin");
        uvoObject.setUser_name("System Admin");
        uvoObject.setUser_status("1");
        input.setUvo(uvoObject);
        input.setTbxBillYear("");
        input.setTbxCustomerName("Duy Duong");
        input.setTbxAffiliateCod("");
        input.setTbxBillDocumentNo("");
        
        BLogicResult result = blogic.execute(input);
        R_AGR_R02Output output = (R_AGR_R02Output) result.getResultObject();
        List<HashMap<String, Object>> list = output.getListAgingReport();

        // ctmid check the result
        assertEquals("Duy Duong", list.get(0).get("CUST_NAME"));
        assertEquals("01/2011", list.get(0).get("DATE_REQ"));
        assertEquals("ABC", list.get(0).get("AFFILIATE_CODE"));
        assertEquals("2", list.get(0).get("ID_REF"));
        assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
        assertEquals("3500", list.get(0).get("BILL_AMOUNT").toString());
        assertEquals("2500", list.get(0).get("OUTSTANDING_AMOUNT").toString());
        assertEquals("CQ",list.get(0).get("PAY_METHOD"));
        assertEquals("success", result.getResultString());
    }
    
    /**
     * 
     * Case : test normal situation <br>
     * condition: Bill Month = "" <br>
     * condition: Year = "" <br>
     * condition: Customer Name = "" <br>
     * condition: Affiliate Code = "ABC" <br>
     * condition: Payment Method  = "" <br>
     * condition: Currency  = "" <br>
     * result.getResultString() = "success" <br>
     */
    public void testExecute03() {

        R_AGR_R02Input input = new R_AGR_R02Input();
        BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
        uvoObject.setId_user("sysadmin");
        uvoObject.setIs_deleted("0");
        uvoObject.setIs_need_change_password("0");
        uvoObject.setLast_pwd_change("15/08/2011");
        uvoObject.setPassword("sysadmin");
        uvoObject.setUser_name("System Admin");
        uvoObject.setUser_status("1");
        input.setUvo(uvoObject);
        input.setTbxAffiliateCod("ABC");
        input.setTbxCustomerName("");
        input.setTbxBillYear("");
        input.setTbxBillDocumentNo("");

        BLogicResult result = blogic.execute(input);
        R_AGR_R02Output output = (R_AGR_R02Output) result.getResultObject();
        List<HashMap<String, Object>> list = output.getListAgingReport();

        // ctmid check the result
        assertEquals("Duy Duong", list.get(0).get("CUST_NAME"));
        assertEquals("01/2011", list.get(0).get("DATE_REQ"));
        assertEquals("ABC", list.get(0).get("AFFILIATE_CODE"));
        assertEquals("2", list.get(0).get("ID_REF"));
        assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
        assertEquals("3500", list.get(0).get("BILL_AMOUNT").toString());
        assertEquals("2500", list.get(0).get("OUTSTANDING_AMOUNT").toString());
        assertEquals("CQ",list.get(0).get("PAY_METHOD"));
        assertEquals("success", result.getResultString());
    }
    
    /**
     * 
     * Case : test normal situation <br>
     * condition: Bill Month = "" <br>
     * condition: Year = "" <br>
     * condition: Customer Name = "" <br>
     * condition: Affiliate Code = "" <br>
     * condition: Payment Method  = "CQ" <br>
     * condition: Currency  = "" <br>
     * result.getResultString() = "success" <br>
     */
    public void testExecute04() {

        R_AGR_R02Input input = new R_AGR_R02Input();
        BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
        uvoObject.setId_user("sysadmin");
        uvoObject.setIs_deleted("0");
        uvoObject.setIs_need_change_password("0");
        uvoObject.setLast_pwd_change("15/08/2011");
        uvoObject.setPassword("sysadmin");
        uvoObject.setUser_name("System Admin");
        uvoObject.setUser_status("1");
        input.setUvo(uvoObject);
        input.setCboPaymentMetho("CQ");
        input.setTbxAffiliateCod("");
        input.setTbxCustomerName("");
        input.setTbxBillYear("");
        input.setTbxBillDocumentNo("");

        BLogicResult result = blogic.execute(input);
        R_AGR_R02Output output = (R_AGR_R02Output) result.getResultObject();
        List<HashMap<String, Object>> list = output.getListAgingReport();

        // ctmid check the result
        assertEquals("Duy Duong", list.get(0).get("CUST_NAME"));
        assertEquals("01/2011", list.get(0).get("DATE_REQ"));
        assertEquals("ABC", list.get(0).get("AFFILIATE_CODE"));
        assertEquals("2", list.get(0).get("ID_REF"));
        assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
        assertEquals("3500", list.get(0).get("BILL_AMOUNT").toString());
        assertEquals("2500", list.get(0).get("OUTSTANDING_AMOUNT").toString());
        assertEquals("CQ",list.get(0).get("PAY_METHOD"));
        assertEquals("success", result.getResultString());
    }
    
    /**
     * 
     * Case : test normal situation <br>
     * condition: Bill Month = "" <br>
     * condition: Year = "" <br>
     * condition: Customer Name = "" <br>
     * condition: Affiliate Code = "" <br>
     * condition: Payment Method  = "" <br>
     * condition: Currency  = "MYR" <br>
     * result.getResultString() = "success" <br>
     */
    public void testExecute05() {

        R_AGR_R02Input input = new R_AGR_R02Input();
        BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
        uvoObject.setId_user("sysadmin");
        uvoObject.setIs_deleted("0");
        uvoObject.setIs_need_change_password("0");
        uvoObject.setLast_pwd_change("15/08/2011");
        uvoObject.setPassword("sysadmin");
        uvoObject.setUser_name("System Admin");
        uvoObject.setUser_status("1");
        input.setUvo(uvoObject);
        input.setCboCurrency("MYR");
        input.setTbxAffiliateCod("");
        input.setTbxCustomerName("");
        input.setTbxBillYear("");
        input.setTbxBillDocumentNo("");
        
        BLogicResult result = blogic.execute(input);
        R_AGR_R02Output output = (R_AGR_R02Output) result.getResultObject();
        List<HashMap<String, Object>> list = output.getListAgingReport();

        // ctmid check the result
        assertEquals("Duy Duong", list.get(0).get("CUST_NAME"));
        assertEquals("01/2011", list.get(0).get("DATE_REQ"));
        assertEquals("ABC", list.get(0).get("AFFILIATE_CODE"));
        assertEquals("2", list.get(0).get("ID_REF"));
        assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
        assertEquals("3500", list.get(0).get("BILL_AMOUNT").toString());
        assertEquals("2500", list.get(0).get("OUTSTANDING_AMOUNT").toString());
        assertEquals("CQ",list.get(0).get("PAY_METHOD"));
        assertEquals("success", result.getResultString());
    }
    
    /**
     * 
     */
    public void testExecute06add() {
    	R_AGR_R02Input input = new R_AGR_R02Input();
		BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
		uvoObject.setId_user("sysadmin");
		uvoObject.setIs_deleted("0");
		uvoObject.setIs_need_change_password("0");
		uvoObject.setLast_pwd_change("15/08/2011");
		uvoObject.setPassword("sysadmin");
		uvoObject.setUser_name("System Admin");
		uvoObject.setUser_status("1");
		input.setUvo(uvoObject);
		input.setCboBillMonth("12");
		input.setTbxBillYear("2011");
		input.setTbxCustomerName("");
		input.setTbxAffiliateCod("");
		input.setTbxBillDocumentNo("");
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("dateReq","2011-11-05" );
		super.updateDAO.execute("TEST.R_AGR_R02.UPDATE.SQL001", map);
		
		BLogicResult result = blogic.execute(input);
		assertEquals("info.ERR2SC002", result.getMessages().get().next().getKey());
		assertEquals("success", result.getResultString());
    }
    
    public void testExecute07() {

        R_AGR_R02Input input = new R_AGR_R02Input();
        BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
        uvoObject.setId_user("sysadmin");
        uvoObject.setIs_deleted("0");
        uvoObject.setIs_need_change_password("0");
        uvoObject.setLast_pwd_change("15/08/2011");
        uvoObject.setPassword("sysadmin");
        uvoObject.setUser_name("System Admin");
        uvoObject.setUser_status("1");
        input.setUvo(uvoObject);
        input.setCboBillMonth("1");
        input.setTbxBillYear("2011");
        input.setTbxCustomerName("");
        input.setTbxAffiliateCod("");
        input.setTbxBillDocumentNo("");
        input.setCboBillMonthTo("12");
        input.setTbxBillYearTo("2015");
        
        BLogicResult result = blogic.execute(input);
        R_AGR_R02Output output = (R_AGR_R02Output) result.getResultObject();
        List<HashMap<String, Object>> list = output.getListAgingReport();

        // ctmid check the result
        assertEquals("Duy Duong", list.get(0).get("CUST_NAME"));
        assertEquals("01/2011", list.get(0).get("DATE_REQ"));
        assertEquals("ABC", list.get(0).get("AFFILIATE_CODE"));
        assertEquals("2", list.get(0).get("ID_REF"));
        assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
        assertEquals("3500", list.get(0).get("BILL_AMOUNT").toString());
        assertEquals("2500", list.get(0).get("OUTSTANDING_AMOUNT").toString());
        assertEquals("CQ",list.get(0).get("PAY_METHOD"));
        assertEquals("success", result.getResultString());
    }
    

    public void testExecute08() {

        R_AGR_R02Input input = new R_AGR_R02Input();
        BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
        uvoObject.setId_user("sysadmin");
        uvoObject.setIs_deleted("0");
        uvoObject.setIs_need_change_password("0");
        uvoObject.setLast_pwd_change("15/08/2011");
        uvoObject.setPassword("sysadmin");
        uvoObject.setUser_name("System Admin");
        uvoObject.setUser_status("1");
        input.setUvo(uvoObject);
        input.setCboBillMonth("1");
        input.setTbxBillYear("2011");
        input.setTbxCustomerName("");
        input.setTbxAffiliateCod("");
        input.setTbxBillDocumentNo("");
        input.setCboBillMonthTo("9");
        input.setTbxBillYearTo("2015");
        input.setStartIndex(100);
        
        BLogicResult result = blogic.execute(input);
        R_AGR_R02Output output = (R_AGR_R02Output) result.getResultObject();
        List<HashMap<String, Object>> list = output.getListAgingReport();

        // ctmid check the result
        assertEquals("Duy Duong", list.get(0).get("CUST_NAME"));
        assertEquals("01/2011", list.get(0).get("DATE_REQ"));
        assertEquals("ABC", list.get(0).get("AFFILIATE_CODE"));
        assertEquals("2", list.get(0).get("ID_REF"));
        assertEquals("MYR", list.get(0).get("BILL_CURRENCY"));
        assertEquals("3500", list.get(0).get("BILL_AMOUNT").toString());
        assertEquals("2500", list.get(0).get("OUTSTANDING_AMOUNT").toString());
        assertEquals("CQ",list.get(0).get("PAY_METHOD"));
        assertEquals("success", result.getResultString());
    }
}
