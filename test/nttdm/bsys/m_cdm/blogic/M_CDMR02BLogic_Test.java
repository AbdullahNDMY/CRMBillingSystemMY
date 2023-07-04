/**
 * @(#)M_CDMR02BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/19
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_cdm.blogic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_cdm.bean.MCodeBean;
import nttdm.bsys.m_cdm.blogic.M_CDMR01BLogic;
import nttdm.bsys.m_cdm.blogic.M_CDMR02BLogic;
import nttdm.bsys.m_cdm.dto.M_CDMR01Output;
import nttdm.bsys.m_cdm.dto.M_CDMR02Input;

/**
 * Test Class for nttdm.bsys.m_cdm.blogic.M_CDMR02BLogic
 * 
 * @author xycao
 */
public class M_CDMR02BLogic_Test extends AbstractUtilTest {

    private M_CDMR01BLogic blogic1;
    
	private M_CDMR02BLogic blogic2;
	
	private M_CDMR02Input input;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
        blogic1 = new M_CDMR01BLogic();
        blogic1.setQueryDAO(queryDAO);
        
		blogic2 = new M_CDMR02BLogic();
		blogic2.setQueryDAO(queryDAO);
		blogic2.setUpdateDAO(updateDAO);
		
	    input = new M_CDMR02Input();

		// delete all exist data
		super.deleteAllData("M_CODE");
	}

	/**
	 * 
	 * Case :test normal situation
	 * CTMID ID_SUB_CODE value is "3" and CUR_VAL value from "1111" to "009988" <br>
	 * SCPID ID_SUB_CODE value is "1" and TYPE_VAL value from "02" to "04" <br>
     * result.getResultString() = "success" <br>
	 */
	public void testExecute01() {
	    BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<UserAccess> userAccessList = new ArrayList<UserAccess>();
        UserAccess userAccess = new UserAccess();
        userAccess.setId_module("M");
        userAccess.setId_sub_module("M-CDM");
        userAccess.setAccess_type("2");
        userAccessList.add(userAccess);
        uvo.setUser_access(userAccessList);
        
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("uvoObject", uvo);
        
		// insert data to T_BATCH_LOG
		String[][] testDataTBatchLog = {
		        { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",  "IS_DELETED",   
		            "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
				{ "QCSNO", "1", "01", "11", "PL-", "0", "2010-11-26  9:44:55", 
				    "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                { "QCSNO", "2", "01", "11", "2011", "0", "2010-11-26  9:44:55", 
                    "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                { "QCSNO",  "3", "01",   "11",   "PL-", "0",
                    "2010-12-22  17:37:36", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"QUONO",   "1",    "06",   "", "111S", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"QUONO",   "2",    "05",   "1234", "1234", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"BIFNO",   "1",    "06",   "", "2222", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"BIFNO",   "2",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"BIFNO",   "3",    "05",   "212",  "212",  "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"INVNO",   "1",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"INVNO",   "2",    "06",   "", "1234", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"INVNO",   "3",    "05",   "000763",   "000763",   "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"DBTNO",   "1",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"DBTNO",   "2",    "06",   "", "ew34", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"DBTNO",   "3",    "05",   "000008",   "000008",   "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"CDTNO",   "1",    "03",   "20110818", "PL-", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"CDTNO",   "2",    "06",   "", "77",   "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"CDTNO",   "3",    "05",   "000054",   "000054",   "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"RCPNO",   "1",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"RCPNO",   "2",    "06",   "", "123456789012", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"RCPNO",   "3",    "05",   "1234", "1234", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"SCPID",   "1",    "02",   "2011", "PL-", "0",
                    "2011-8-16  17:24:30", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"SCPID",   "2",    "06",   "", "cc223",    "0",
                    "2011-8-16  17:24:30", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"CTMID",   "1",    "09",   "08",   "PL-", "0",
                    "2011-8-16  17:24:30", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"CTMID",   "2",    "06",   "", "wer3", "0",
                    "2011-8-18  11:36:52", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"QCSNO",   "4",    "05",   "11111111", "11111111", "0",
                    "2011-8-17  10:55:17", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },
                {"SCPID",   "3",    "05",   "5678", "5678", "0",
                    "2011-8-18  10:59:53", "2011-8-18  10:59:53", "sysadmin", AUDIT_VALUE },
                {"CTMID",   "3",    "05",   "1111", "1111", "0",
                    "2011-8-18  11:37:00", "2011-8-18  17:39:29", "sysadmin", AUDIT_VALUE },           
		};
		super.insertData("M_CODE", testDataTBatchLog);
		
        BLogicResult result = blogic1.execute(param);
        M_CDMR01Output output = (M_CDMR01Output) result.getResultObject();
        
        MCodeBean[] ctmid = output.getCtmid();
        ctmid[0].setInit_val("Customer ID.;MM");
        ctmid[0].setValue("");
        ctmid[1].setInit_val("Customer ID.;Prefix");
        ctmid[2].setInit_val("Customer ID.;Running No.");
        ctmid[3].setInit_val("Customer ID.;- Please Select One -");
        ctmid[3].setType_val("");
        ctmid[3].setValue("");
        
        MCodeBean[] qcsno = output.getQcsno();
        qcsno[0].setInit_val("QCS No.;YY");
        qcsno[0].setValue("");
        qcsno[1].setInit_val("QCS No.;YY");
        qcsno[2].setInit_val("QCS No.;YY");
        qcsno[2].setValue("");
        qcsno[3].setInit_val("QCS No.;Running No.");
        
        MCodeBean[] quono = output.getQuono();
        quono[0].setInit_val("Quotation No.;Prefix");
        quono[1].setInit_val("Quotation No.;Running No.");
        quono[2].setInit_val("Quotation No.;- Please Select One -");
        quono[2].setType_val("");
        quono[2].setValue("");
        quono[3].setInit_val("Quotation No.;- Please Select One -");
        quono[3].setType_val("");
        quono[3].setValue("");
        
        MCodeBean[] bifno = output.getBifno();
        bifno[0].setInit_val("BIF No.;Prefix");
        bifno[1].setInit_val("BIF No.;YY");
        bifno[2].setInit_val("BIF No.;Running No.");
        bifno[3].setInit_val("BIF No.;- Please Select One -");
        bifno[3].setType_val("");
        bifno[3].setValue("");
        
        MCodeBean[] invno = output.getInvno();
        invno[0].setInit_val("Invoice No.;YY");
        invno[1].setInit_val("Invoice No.;Prefix");
        invno[2].setInit_val("Invoice No.;Running No.");
        invno[3].setInit_val("Invoice No.;- Please Select One -");
        invno[3].setType_val("");
        invno[3].setValue("");
        
        MCodeBean[] dbtno = output.getDbtno();
        dbtno[0].setInit_val("Debit Note No.;YY");
        dbtno[1].setInit_val("Debit Note No.;Prefix");
        dbtno[2].setInit_val("Debit Note No.;Running No.");
        dbtno[3].setInit_val("Debit Note No.;- Please Select One -");
        dbtno[3].setType_val("");
        dbtno[3].setValue("");
        
        MCodeBean[] cdtno = output.getCdtno();
        cdtno[0].setInit_val("Credit Note No.;YYYYMMDD");
        cdtno[1].setInit_val("Credit Note No.;Prefix");
        cdtno[2].setInit_val("Credit Note No.;Running No.");
        cdtno[3].setInit_val("Credit Note No.;- Please Select One -");
        cdtno[3].setType_val("");
        cdtno[3].setValue("");
        
        MCodeBean[] rcpno = output.getRcpno();
        rcpno[0].setInit_val("Receipt No.;YY");
        rcpno[1].setInit_val("Credit Note No.;Prefix");
        rcpno[2].setInit_val("Receipt No.;Running No.");
        rcpno[3].setInit_val("Receipt No.;- Please Select One -");
        rcpno[3].setType_val("");
        rcpno[3].setValue("");
        
        MCodeBean[] scpid = output.getScpid();
        scpid[0].setInit_val("Subscription ID.;YYYY");
        scpid[0].setValue("");
        scpid[1].setInit_val("Subscription ID.;Prefix");
        scpid[2].setInit_val("Subscription ID.;Running No.");
        scpid[3].setInit_val("Subscription ID.;- Please Select One -");
        scpid[3].setType_val("");
        scpid[3].setValue("");
        
        BillingSystemUserValueObject uvoObject = new BillingSystemUserValueObject();
        uvoObject.setId_user("sysadmin");
        uvoObject.setIs_deleted("0");
        uvoObject.setIs_need_change_password("0");
        uvoObject.setLast_pwd_change("15/08/2011");
        uvoObject.setPassword("sysadmin");
        uvoObject.setUser_name("System Admin");
        uvoObject.setUser_status("1");
        input.setUvoObject(uvoObject);
        
        input.setCtmid(ctmid);
        input.setQcsno(qcsno);
        input.setQuono(quono);
        input.setBifno(bifno);
        input.setInvno(invno);
        input.setDbtno(dbtno);
        input.setCdtno(cdtno);
        input.setRcpno(rcpno);
        input.setScpid(scpid);
        
		// CTMID ID_SUB_CODE value is "3" and CUR_VAL value from "1111" to "009988" 
        output.getCtmid()[2].setValue("009988");
        input.setCtmid(ctmid);
		
		// SCPID ID_SUB_CODE value is "1" and TYPE_VAL value from "02" to "04"
        output.getScpid()[0].setType_val("09");
        input.setScpid(scpid);
        
	    // input set value
        BLogicResult result2 = blogic2.execute(input);
        assertEquals("success", result2.getResultString());
        
        BLogicResult result3 = blogic1.execute(param);
        M_CDMR01Output outputChange = (M_CDMR01Output) result3.getResultObject();
        // ctmid check the result
        assertEquals("CSTID", outputChange.getCtmid()[2].getId_code());
        assertEquals(null, outputChange.getCtmid()[2].getValue());
        assertEquals("SCPID", outputChange.getScpid()[0].getId_code());
        assertEquals("02", outputChange.getScpid()[0].getType_val());
        assertEquals("success", result.getResultString());
	}
}
