/**
 * @(#)G_CIT_P01_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Sep 6, 2011
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1SubmitOutput;
/**
 * @author Joel
 *
 */
public class G_CIT_P01_Test extends AbstractUtilTest {
	
	private G_CIT_P01 gCitP01 = null;
	
	private RP_E_MEX_CT1SubmitInput param = null;
	
	private RP_E_MEX_CT1SubmitOutput outputDTO = null;
	
	@Override
    protected void setUpData() throws Exception {  
	    param = new RP_E_MEX_CT1SubmitInput();
        outputDTO = new RP_E_MEX_CT1SubmitOutput();
        //gCitP01 = new G_CIT_P01();
        gCitP01 = new G_CIT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {"BATCH_TIME_INTERVAL",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" },
                {"BATCH_G_CIT_P01",
                    "Row Result Display",
                    "Settings to display result.",
                    "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" }};
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_TIME_INTERVAL", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "2000", null, "1" },
               { "BATCH_G_CIT_P01", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "C:\\", null, "1" }};
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
    }
	
	/**
	 * success
	 * @throws Exception
	 */
	public void testExecute01() throws Exception {
	    File file = new File("C:\\CITIdata_0812.xls");
        file.delete();
        
	    super.deleteAllData("T_CIT_EXP_HD");
        
        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("M_CUST_BANKINFO");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        this.deleteAllData("T_BIL_D");
        this.deleteAllData("T_BIL_H");
        
        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "INVOICE_072011_224.txt", "07/2011",
                        "2011-08-22 00:00:00", "1", "2011-08-22 00:00:00",
                        "2011-08-22 00:00:00", "sysadmin" } };
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
        // insert data to totalGiro > 0
        String[][] mCustBankinfoData = {
                { "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
                        "CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
                        "CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
                        "NO_REFERENCE", "ID_AUDIT" },
                { "229743", "380", "123-123", "GR-Singapore", "VISA", "123-123",
                        "1234-1234-1234-1234", "CC-Singapore", "2012-12-01",
                        "2011-02-25 10:57:49", "2011-02-25 10:57:49",
                        "USERFULL", "12345", "123", "0", null, null } };
        String[][] dataT_BAC_H = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1                   ", "229743", "CC", "MYR", "0", "", "BA",
                        "PC", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM", null,
                        null, "1", "1000" } };
        String[][] tBilHData = {
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
                { "1", "IN", "0", "1                   ", "2012-08-30", "CC", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
                        "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        AUDIT_VALUE } };
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
        super.insertData("T_BAC_H", dataT_BAC_H);
        super.insertData("T_BIL_H", tBilHData);
	    
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		param.setUvo(uvo);
		param.setBankAcc("1818");
		param.setScr(true);
		param.setClosingMonth("08");
		param.setClosingYear("2012");
		
		GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
		assertEquals("info.ERR2SC029", gpr.getMessages().get().next().getKey());
	}	
	
	/**
	 * ClosingMonth input format error
	 */
	public void testExecute02(){
	    BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("ad");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        assertEquals(false, gpr.getMessages().get().hasNext());
	}
	
	/**
     * auditId is null
     */
    public void testExecute03(){
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        //GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        //assertEquals(false, gpr.getMessages().get().hasNext());
    }
    
    /**
     * BATCH_TIME_INTERVAL
     */
    public void testExecute04(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        super.deleteAllData("T_CIT_EXP_HD");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {"BATCH_TIME_INTERVAL",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" },
                {"BATCH_G_CIT_P01",
                    "Row Result Display",
                    "Settings to display result.",
                    "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" }};
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_TIME_INTERVAL", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "327670", null, "1" },
               { "BATCH_G_CIT_P01", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "C:\\", null, "1" }};

        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "INVOICE_072011_224.txt", "07/2011",
                            sdf.format(new Date()), "2", sdf.format(new Date()),
                            sdf.format(new Date()), "sysadmin" } };
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        assertEquals("errors.ERR1SC059", gpr.getErrors().get().next().getKey());
    }
    
    /**
     * BATCH_G_CIT_P01 is null
     */
    public void testExecute05(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        super.deleteAllData("T_CIT_EXP_HD");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {"BATCH_TIME_INTERVAL",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" }};
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_TIME_INTERVAL", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "2", null, "1" }};

        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "INVOICE_072011_224.txt", "07/2011",
                            sdf.format(new Date()), "2", sdf.format(new Date()),
                            sdf.format(new Date()), "sysadmin" } };
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        Iterator<BLogicMessage> errors = gpr.getErrors().get();
        assertEquals("", errors.next().getKey());
        assertEquals("errors.ERR1SC075", errors.next().getKey());
    }
    
    /**
     * folder is not exits
     */
    public void testExecute06(){
        super.deleteAllData("T_CIT_EXP_HD");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {"BATCH_TIME_INTERVAL",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" },
                {"BATCH_G_CIT_P01",
                    "Row Result Display",
                    "Settings to display result.",
                    "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" }};
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_TIME_INTERVAL", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "327670", null, "1" },
               { "BATCH_G_CIT_P01", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "C:\\fdsaaaa\\", null, "1" }};
        
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        Iterator<BLogicMessage> errors = gpr.getErrors().get();
        assertEquals("errors.ERR1SC076", errors.next().getKey());
    }
    
    /**
     * file is exits
     */
    public void testExecute07(){
        super.deleteAllData("T_CIT_EXP_HD");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        Iterator<BLogicMessage> errors = gpr.getErrors().get();
        assertEquals("errors.ERR1SC077", errors.next().getKey());
    }
    
    public void testExecute08(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        super.deleteAllData("T_CIT_EXP_HD");
        
        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("M_CUST_BANKINFO");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        this.deleteAllData("T_BIL_D");
        this.deleteAllData("T_BIL_H");
        
        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "CITIdata_0812.xls", "08/2011",
                            sdf.format(new Date()), "2", sdf.format(new Date()),
                            sdf.format(new Date()), "sysadmin" } };
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        Iterator<BLogicMessage> errors = gpr.getErrors().get();
        assertEquals("", errors.next().getKey());
        assertEquals("errors.ERR1SC064", errors.next().getKey());
    }
    
    /**
     * no detail data
     */
    public void testExecute09(){
        //File file = new File("C:\\CITIdata_0812.xls");
        //file.delete();
        
        super.deleteAllData("T_CIT_EXP_HD");
        
        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("M_CUST_BANKINFO");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        this.deleteAllData("T_BIL_D");
        this.deleteAllData("T_BIL_H");
        
        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "CITIdata_0812.xls", "08/2012",
                        "2011-08-22 00:00:00", "1", "2011-08-22 00:00:00",
                        "2011-08-22 00:00:00", "sysadmin" } };
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
        // insert data to totalGiro > 0
        String[][] mCustBankinfoData = {
                { "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
                        "CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
                        "CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
                        "NO_REFERENCE", "ID_AUDIT" },
                { "229743", "380", "123-123", "GR-Singapore", "VISA", "123-123",
                        "1234-1234-1234-1234", "CC-Singapore", "2012-12-01",
                        "2011-02-25 10:57:49", "2011-02-25 10:57:49",
                        "USERFULL", "12345", "123", "0", null, null } };
        String[][] dataT_BAC_H = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1                   ", "229743", "CC", "MYR", "0", "", "BA",
                        "PC", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM", null,
                        null, "1", "1000" } };
        String[][] tBilHData = {
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
                { "1", "IN", "0", "1                   ", "2012-08-30", "CC", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
                        "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        AUDIT_VALUE },
                { "2", "IN", "0", "1                   ", "2012-08-30", "CC", "229743", "BA",
                    "PC", null, null, null, null, "bhchan", "30 Days",
                    "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
                    "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                    TEST_DAY_TODAY, "joeykan", "0",
                    "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                    "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                    "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                    AUDIT_VALUE },
                { "3", "IN", "1", "1                   ", "2012-08-30", "CC", "229743", "BA",
                    "PC", null, null, null, null, "bhchan", "30 Days",
                    "MYR", "6", "0", "2000", "1000", TEST_DAY_TODAY, "0",
                    "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                    TEST_DAY_TODAY, "joeykan", "0",
                    "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                    "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                    "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                    AUDIT_VALUE }};
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
        super.insertData("T_BAC_H", dataT_BAC_H);
        super.insertData("T_BIL_H", tBilHData);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        Iterator<BLogicMessage> errors = gpr.getErrors().get();
        assertEquals("errors.ERR1SC064", errors.next().getKey());
    }
    
    /**
     * no detail data
     */
    public void testExecute10(){
        //File file = new File("C:\\CITIdata_0812.xls");
        //file.delete();
        
        super.deleteAllData("M_CODE");
        
        super.deleteAllData("T_CIT_EXP_HD");
        
        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("M_CUST_BANKINFO");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        this.deleteAllData("T_BIL_D");
        this.deleteAllData("T_BIL_H");
        
        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "CITIdata_0812.xls", "08/2018",
                        "2011-08-22 00:00:00", "1", "2011-08-22 00:00:00",
                        "2011-08-22 00:00:00", "sysadmin" } };
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
        // insert data to totalGiro > 0
        String[][] mCustBankinfoData = {
                { "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
                        "CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
                        "CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
                        "NO_REFERENCE", "ID_AUDIT" },
                { "229743", "380", "123-123", "GR-Singapore", "VISA", "123-123",
                        "1234-1234-1234-1234", "CC-Singapore", "2012-12-01",
                        "2011-02-25 10:57:49", "2011-02-25 10:57:49",
                        "USERFULL", "12345", "123", "0", null, null } };
        String[][] dataT_BAC_H = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1                   ", "229743", "CC", "MYR", "0", "", "BA",
                        "PC", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM", null,
                        null, "1", "1000" } };
        String[][] tBilHData = {
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
                { "1", "IN", "0", "1                   ", "2012-08-30", "CC", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
                        "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        AUDIT_VALUE }};
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
        super.insertData("T_BAC_H", dataT_BAC_H);
        super.insertData("T_BIL_H", tBilHData);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(true);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        GlobalProcessResult gpr = gCitP01.execute(param, outputDTO);
        Iterator<BLogicMessage> errors = gpr.getErrors().get();
        assertEquals("errors.ERR1SC071", errors.next().getKey());
    }
    
    /**
     * no detail data
     */
    public void testExecute11(){
        //File file = new File("C:\\CITIdata_0812.xls");
        //file.delete();
        
        super.deleteAllData("M_CODE");
        
        super.deleteAllData("T_CIT_EXP_HD");
        
        /**
         * references NTTS.M_CUST
         */
        super.deleteAllData("T_QCS_D");
        super.deleteAllData("T_QCS_H");
        super.deleteAllData("M_CUST");
        
        super.deleteAllData("M_CUST_BANKINFO");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        this.deleteAllData("T_BIL_D");
        this.deleteAllData("T_BIL_H");
        
        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "CITIdata_0812.xls", "08/2018",
                        "2011-08-22 00:00:00", "1", "2011-08-22 00:00:00",
                        "2011-08-22 00:00:00", "sysadmin" } };
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
        // insert data to totalGiro > 0
        String[][] mCustBankinfoData = {
                { "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
                        "CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
                        "CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
                        "NO_REFERENCE", "ID_AUDIT" },
                { "229743", "380", "123-123", "GR-Singapore", "VISA", "123-123",
                        "1234-1234-1234-1234", "CC-Singapore", "2012-12-01",
                        "2011-02-25 10:57:49", "2011-02-25 10:57:49",
                        "USERFULL", "12345", "123", "0", null, null } };
        String[][] dataT_BAC_H = {
                { "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
                        "EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
                        "IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
                        "ID_LOGIN", "FIXED_FOREX", "ID_AUDIT", "IS_SINGPOST",
                        "TOTAL_AMT_DUE" },
                { "1                   ", "229743", "CC", "MYR", "0", "", "BA",
                        "PC", "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "DM", null,
                        null, "1", "1000" } };
        String[][] tBilHData = {
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
                { "1", "IN", "0", "1                   ", "2012-08-30", "CC", "229743", "BA",
                        "PC", null, null, null, null, "bhchan", "30 Days",
                        "MYR", "6", "0", "3500", "1000", TEST_DAY_TODAY, "0",
                        "0", "0", "1", "USD", "3.124", "1120.36", "50", "1",
                        TEST_DAY_TODAY, "joeykan", "0",
                        "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                        "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
                        "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
                        AUDIT_VALUE }};
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        super.insertData("M_CUST", dataM_CUST);
        super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
        super.insertData("T_BAC_H", dataT_BAC_H);
        super.insertData("T_BIL_H", tBilHData);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(false);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        gCitP01.execute(param, outputDTO);
        assertEquals(true, true);
    }
    
    /**
     * BATCH_TIME_INTERVAL
     */
    public void testExecute12(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        super.deleteAllData("T_CIT_EXP_HD");
        
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                {"BATCH_TIME_INTERVAL",
                        "Row Result Display",
                        "Settings to display result.",
                        "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" },
                {"BATCH_G_CIT_P01",
                    "Row Result Display",
                    "Settings to display result.",
                    "0", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" }};
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "BATCH_TIME_INTERVAL", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "327670", null, "1" },
               { "BATCH_G_CIT_P01", "1", "Number of rows of result to be display.", "0",
                   TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "C:\\", null, "1" }};

        String[][] dataCitExpHd = {
                { "ID_CIT_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", 
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "0", "INVOICE_072011_224.txt", "07/2011",
                            sdf.format(new Date()), "2", sdf.format(new Date()),
                            sdf.format(new Date()), "sysadmin" } };
        
        super.insertData("T_CIT_EXP_HD", dataCitExpHd);
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        param.setUvo(uvo);
        param.setBankAcc("1818");
        param.setScr(false);
        param.setClosingMonth("08");
        param.setClosingYear("2012");
        
        gCitP01.setAuditReference("1");
        gCitP01.setAuditStatus("0");
        
        gCitP01.execute(param, outputDTO);
        assertEquals(true, true);
    }
}
