/**
 * @(#)RP_B_BIL_S03_02_03BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/29
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_03Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_03Output;
import nttdm.bsys.common.bean.T_BIL_H;
import nttdm.bsys.common.util.AbstractUtilTest;

import org.apache.struts.util.LabelValueBean;

/**
 * Test Class for nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_02_03BLogic
 * 
 * @author i-jankw
 * 
 */
public class RP_B_BIL_S03_02_03BLogic_Test extends AbstractUtilTest {

	RP_B_BIL_S03_02_03BLogic testObject = null;
	RP_B_BIL_S03_02_03Input param = null;
	T_BIL_H inputHeaderInfo = null;
	BLogicResult result = null;

	@Override
	protected void setUpData() throws Exception {

		/**
		 * init object
		 */
		testObject = new RP_B_BIL_S03_02_03BLogic();
		param = new RP_B_BIL_S03_02_03Input();
		inputHeaderInfo = new T_BIL_H();
		result = new BLogicResult();

	}

	/**
	 * case 1:if(contactInfo == null)<br>
	 * condition:<br>
	 * test data=dataM_CUST_CTC<br>
	 * return:BLogicResult<br>
	 * @throws Exception
	 */
	public void testExecute01() throws Exception {

		/**
		 * delete all data
		 */

		/**
		 * references NTTS.M_CUST
		 */
	    super.deleteAllData("T_QCS_D");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");

		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");
		super.deleteAllData("M_CUST_CTC");

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

		super.deleteAllData("T_BAC_D");
		super.deleteAllData("T_BAC_H");
		super.deleteAllData("M_CUST_ADR");

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
		String[][] dataM_CUST_CTC = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT", },
				{ "229743", "PC", "Duy Duong", "0123456789",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0123456789", "0123456789",
						TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "0", null } };
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
		String[][] dataM_CUST_ADR = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "229743", "BA", "R.No 2", "", "", "BG", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "USERFULL", "254", "0", null } };

		/**
		 * insert data
		 */
		super.insertData("M_GSET_H", dataM_GSET_H);
		super.insertData("M_CUST", dataM_CUST);
		super.insertData("M_GSET_D", dataM_GSET_D);
		super.insertData("M_CUST_CTC", dataM_CUST_CTC);
		super.insertData("M_USER", dataM_USER);
		super.insertData("T_BAC_H", dataT_BAC_H);
		super.insertData("M_CUST_ADR", dataM_CUST_ADR);

		testObject.setQueryDAO(queryDAO);

		param.setIdRef("1                   ");

		String idCust = "229743";
		inputHeaderInfo.setIdCust(idCust);
		String adrType = "BA";
		inputHeaderInfo.setAdrType(adrType);
		param.setInputHeaderInfo(inputHeaderInfo);

		result = testObject.execute(param);
		assertEquals("success", result.getResultString());
		RP_B_BIL_S03_02_03Output outputDTO = (RP_B_BIL_S03_02_03Output) result
				.getResultObject();
		List<LabelValueBean> listCust = outputDTO.getListCust();
		assertEquals("229743", listCust.get(0).getValue());
		List<LabelValueBean> listContact = outputDTO.getListContact();
		assertEquals("PC", listContact.get(0).getValue());
		List<LabelValueBean> listAcManager = outputDTO.getListAcMan();
		assertEquals("Yap", listAcManager.get(0).getLabel());
		List<LabelValueBean> listBillingAccountNo = outputDTO
				.getListBillingAccountNo();
		assertEquals("1                   ", listBillingAccountNo.get(0)
				.getLabel());

	}
	
	/**
     * case 1:if(contactInfo == null)<br>
     * condition:<br>
     * test data=dataM_CUST_CTC<br>
     * return:BLogicResult<br>
     * @throws Exception
     */
    public void testExecute011() throws Exception {

        /**
         * delete all data
         */

        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");

        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        super.deleteAllData("M_CUST_CTC");

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

        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("M_CUST_ADR");

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
                        "0123456789", "0123456789", "11", "0123456789", "",
                        null, "" } };
        String[][] dataM_CUST1 = {
                { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
                        "CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
                        "INTER_COMP", "IS_EXPORTED", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
                        "HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
                        "GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
                        "SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "ID_AUDIT",
                        "MOBILE_NO" },
                { "22", "0123456789", "Duy Duong", "0123456789",
                        "http://www.nttdata.com.vn",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "", "", null, "", "", "0",
                        "0123456789", "0123456789", "11", "0123456789", "",
                        null, "" } };

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
        String[][] dataM_CUST_CTC = {
                { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                        "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", },
                { "229743", "PC", "Duy Duong", "0123456789",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0123456789", "0123456789",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "0", null } };
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
        String[][] dataM_CUST_ADR = {
                { "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
                        "COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
                { "229743", "BA", "R.No 2", "", "", "BG", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "254", "0", null } };

        /**
         * insert data
         */
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("M_CUST", dataM_CUST1);
        super.insertData("M_GSET_D", dataM_GSET_D);
        super.insertData("M_CUST_CTC", dataM_CUST_CTC);
        super.insertData("M_USER", dataM_USER);
        super.insertData("T_BAC_H", dataT_BAC_H);
        super.insertData("M_CUST_ADR", dataM_CUST_ADR);

        testObject.setQueryDAO(queryDAO);

        param.setIdRef("1                   ");

        String idCust = "229743";
        inputHeaderInfo.setIdCust(idCust);
        String adrType = "BA";
        inputHeaderInfo.setAdrType(adrType);
        inputHeaderInfo.setContactType("22");
        param.setInputHeaderInfo(inputHeaderInfo);

        result = testObject.execute(param);
        assertEquals("success", result.getResultString());
        RP_B_BIL_S03_02_03Output outputDTO = (RP_B_BIL_S03_02_03Output) result
                .getResultObject();
        List<LabelValueBean> listCust = outputDTO.getListCust();
        assertEquals("22", listCust.get(0).getValue());
        List<LabelValueBean> listContact = outputDTO.getListContact();
        assertEquals("229743", listContact.get(0).getValue());
        List<LabelValueBean> listAcManager = outputDTO.getListAcMan();
        assertEquals("Yap", listAcManager.get(0).getLabel());
        List<LabelValueBean> listBillingAccountNo = outputDTO
                .getListBillingAccountNo();
        assertEquals("1                   ", listBillingAccountNo.get(0)
                .getLabel());

    }

	/**
	 * case 2:if(contactInfo != null)<br>
	 * condition:<br>
	 * test data=dataM_CUST_CTC<br>
	 * return:BLogicResult<br>
	 * 
	 * @throws Exception
	 */
	public void testExecute02() throws Exception {
		/**
		 * delete all data
		 */

		/**
		 * references NTTS.M_CUST
		 */
	    super.deleteAllData("T_QCS_D");
		super.deleteAllData("T_QCS_H");
		super.deleteAllData("M_CUST");

		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");
		super.deleteAllData("M_CUST_CTC");

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
		String[][] dataM_CUST_CTC = {
				{ "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
						"EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT", },
				{ "88543", "PC", "Duy Duong", "0123456789",
						"duong.nguyen@nttdata.com.vn", "0123456789",
						"0123456789", "0123456789", "0123456789",
						TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "0", null } };

		super.insertData("M_GSET_H", dataM_GSET_H);
		/**
		 * insert data
		 */

		super.insertData("M_CUST", dataM_CUST);
		super.insertData("M_GSET_D", dataM_GSET_D);

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
		super.deleteAllData("M_CUST_ADR");
		String[][] dataM_CUST_ADR = {
				{ "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
						"COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
				{ "88543", "BA", "R.No 2", "", "", "BG", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "USERFULL", "254", "0", null } };
		super.insertData("M_CUST_ADR", dataM_CUST_ADR);

		testObject.setQueryDAO(queryDAO);

		param.setIdRef("1                   ");

		String idCust = "88543";
		inputHeaderInfo.setIdCust(idCust);
		inputHeaderInfo.setContactType("PC");
		String adrType = "aa";
		inputHeaderInfo.setAdrType(adrType);
		param.setInputHeaderInfo(inputHeaderInfo);

		result = testObject.execute(param);
		assertEquals("success", result.getResultString());
		RP_B_BIL_S03_02_03Output outputDTO = (RP_B_BIL_S03_02_03Output) result
				.getResultObject();

		List<LabelValueBean> listCust = outputDTO.getListCust();
		assertEquals("229743", listCust.get(0).getValue());

	}
	
	/**
     * case 2:if(contactInfo != null)<br>
     * condition:<br>
     * test data=dataM_CUST_CTC<br>
     * return:BLogicResult<br>
     * 
     * @throws Exception
     */
    public void testExecute22() throws Exception {
        /**
         * delete all data
         */

        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");

        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        super.deleteAllData("M_CUST_CTC");

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

        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        super.deleteAllData("M_CUST_ADR");

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
        String[][] dataM_CUST_CTC = {
                { "ID_CUST", "CONTACT_TYPE", "CONTACT_NAME", "DESIGNATION",
                        "EMAIL", "TEL_NO", "DID_NO", "FAX_NO", "MOBILE_NO",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "IS_DELETED", "ID_AUDIT", },
                { "229743", "PC", "Duy Duong", "0123456789",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0123456789", "0123456789",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, "USERFULL", "0", null } };
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
        String[][] dataM_CUST_ADR = {
                { "ID_CUST", "ADR_TYPE", "ADR_LINE1", "ADR_LINE2", "ADR_LINE3",
                        "COUNTRY", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "ZIP_CODE", "IS_DELETED", "ID_AUDIT" },
                { "229743", "BA", "R.No 2", "", "", "BG", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "254", "0", null } };

        /**
         * insert data
         */
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("M_GSET_D", dataM_GSET_D);
        super.insertData("M_CUST_CTC", dataM_CUST_CTC);
        super.insertData("M_USER", dataM_USER);
        super.insertData("T_BAC_H", dataT_BAC_H);
        super.insertData("M_CUST_ADR", dataM_CUST_ADR);

        testObject.setQueryDAO(queryDAO);

        param.setIdRef("1                   ");

        String idCust = "229743";
        inputHeaderInfo.setIdCust(idCust);
        String adrType = "BA";
        inputHeaderInfo.setAdrType(adrType);
        inputHeaderInfo.setContactType("PC");
        param.setInputHeaderInfo(inputHeaderInfo);

        result = testObject.execute(param);
        assertEquals("success", result.getResultString());

    }

}
