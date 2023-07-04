/**
 * @(#)RP_B_BIL_S03_02_01BLogic_Test.java
 * 
 * Billing System NTTS
 * 
 * Version 1.00
 * 
 * Created 2011/08/26
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_01Output;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_02_01BLogic
 * 
 * @author i-jankw
 * 
 */
public class RP_B_BIL_S03_02_01BLogic_Test extends AbstractUtilTest {

	RP_B_BIL_S03_02_01BLogic testObject = null;
	RP_B_BIL_S03_02_01Input param = null;

	@Override
	protected void setUpData() throws Exception {

		param = new RP_B_BIL_S03_02_01Input();
	}

	/**
	 * case 1:test the execute method <br>
	 * condition:<br>
	 * delete all data and insert new data<br>
	 * return:BLogicResult<br>
	 * 
	 * @throws Exception
	 */
	public void testExecute() throws Exception {
	    /**
         * delete all data
         */
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");

        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
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
                { "1", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
                        "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        null } };
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
        String[][] dataT_BIL_D = {
                { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                        "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
                        "APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
                        "ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
                { "1", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "201", "2216", "114", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", null, null,"0","1" } };
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "GST", "1", "Tax value in %", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "5", null, "1" } };
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "GST", "Government Service Tax",
                        "Settings for billing/invoice.", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin" } };

        String[][] dataM_USER_ACCESS = {
                { "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "sysadmin", "B", "B-BIL", "2", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "0", null } };

        /**
         * insert data
         */
        super.insertData("T_BIL_H", dataT_BIL_H);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("T_BIL_D", dataT_BIL_D);

        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);

        super.insertData("M_USER_ACCESS", dataM_USER_ACCESS);

        super.deleteAllData("M_CUST_CTC");
        String[][] dataM_CUST_CTC = {
                { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                        "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", },
                { "229743", "PC", "Duy Duong", "0123456789",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0123456789", "0123456789",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "0", null } };

        super.insertData("M_CUST_CTC", dataM_CUST_CTC);

        /**
         * references NTTS.M_USE
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_WF_ACTION");
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("T_WF_ACTION");
        super.deleteAllData("M_ATR_TFR");
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("M_USER");
        String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "styap", "Yap", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
        super.insertData("M_USER", dataM_USER);

        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        String[][] dataT_BAC_H = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1                   ", "229743", "CQ", "MYR", "0", "", "BA",
                        "PC", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM", null,
                        null, "1", "0" } };
        super.insertData("T_BAC_H", dataT_BAC_H);
        
		param.setIdRef("1                   ");
		testObject = new RP_B_BIL_S03_02_01BLogic();
		testObject.setQueryDAO(queryDAO);
		BLogicResult result = testObject.execute(param);
		RP_B_BIL_S03_02_01Output outputDTO = (RP_B_BIL_S03_02_01Output) result
				.getResultObject();
		List<LabelValueBean> listBillingAccountNo = outputDTO
				.getListBillingAccountNo();
		assertEquals("1                   ", listBillingAccountNo.get(0)
				.getValue());
		HashMap<String, Object> headerInfo = outputDTO.getHeaderInfo();
		assertEquals("30 Days", headerInfo.get("TERM").toString());
		assertEquals("success", result.getResultString());

	}
	
	/**
     * case 1:test the execute method <br>
     * condition:<br>
     * delete all data and insert new data<br>
     * return:BLogicResult<br>
     * 
     * @throws Exception
     */
    public void testExecute1() throws Exception {
        /**
         * delete all data
         */
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");

        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
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
                { "1", "IN", "0", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
                        "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        null } };
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
                        "0123456789", "0123456789", "1", "0123456789", "",
                        null, "" } };
        String[][] dataT_BIL_D = {
                { "ID_REF", "ITEM_ID", "ITEM_LEVEL", "ITEM_NO", "ITEM_DESC",
                        "ITEM_QTY", "ITEM_UPRICE", "ITEM_AMT", "ITEM_GST",
                        "APPLY_GST", "IS_DISPLAY", "ID_CUST_PLAN",
                        "ID_CUST_PLAN_GRP", "ID_CUST_PLAN_LINK", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_FROM", "BILL_TO", "JOB_NO",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "ID_AUDIT", "ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT" },
                { "1", "1", "1", "0", null, "3", "6", "9", "0", "0", "1",
                        "201", "2216", "114", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "system", null, null,"0","1" } };
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "GST", "1", "Tax value in %", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "5", null, "1" } };
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "GST", "Government Service Tax",
                        "Settings for billing/invoice.", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin" } };

        String[][] dataM_USER_ACCESS = {
                { "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT" },
                { "sysadmin", "B", "B-BIL", "2", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "0", null } };

        /**
         * insert data
         */
        super.insertData("T_BIL_H", dataT_BIL_H);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("T_BIL_D", dataT_BIL_D);

        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);

        super.insertData("M_USER_ACCESS", dataM_USER_ACCESS);

        super.deleteAllData("M_CUST_CTC");
        String[][] dataM_CUST_CTC = {
                { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                        "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", },
                { "229743", "PC", "Duy Duong", "0123456789",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0123456789", "0123456789",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "0", null } };

        super.insertData("M_CUST_CTC", dataM_CUST_CTC);

        /**
         * references NTTS.M_USE
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_WF_ACTION");
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("T_WF_ACTION");
        super.deleteAllData("M_ATR_TFR");
        super.deleteAllData("M_USER_ACCESS");
        super.deleteAllData("M_USER");
        String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "styap", "Yap", "0001", "0001", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
        super.insertData("M_USER", dataM_USER);

        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        String[][] dataT_BAC_H = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1                   ", "229743", "CQ", "MYR", "0", "", "BA",
                        "PC", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM", null,
                        null, "1", "0" } };
        super.insertData("T_BAC_H", dataT_BAC_H);
        
        param.setIdRef("1                   ");
        testObject = new RP_B_BIL_S03_02_01BLogic();
        testObject.setQueryDAO(queryDAO);
        BLogicResult result = testObject.execute(param);
        RP_B_BIL_S03_02_01Output outputDTO = (RP_B_BIL_S03_02_01Output) result
                .getResultObject();
        List<LabelValueBean> listBillingAccountNo = outputDTO
                .getListBillingAccountNo();
        assertEquals("1                   ", listBillingAccountNo.get(0)
                .getValue());
        HashMap<String, Object> headerInfo = outputDTO.getHeaderInfo();
        assertEquals("30 Days", headerInfo.get("TERM").toString());
        assertEquals("success", result.getResultString());

    }
}
