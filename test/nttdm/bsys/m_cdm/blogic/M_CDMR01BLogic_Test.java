/**
 * @(#)M_CDMR01BLogic_Test.java
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
import nttdm.bsys.m_cdm.dto.M_CDMR01Output;

/**
 * Test Class for nttdm.bsys.m_cdm.blogic.M_CDMR01BLogic
 * 
 * @author xycao
 */
public class M_CDMR01BLogic_Test extends AbstractUtilTest {

    private M_CDMR01BLogic blogic;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() {
		// init test object
		blogic = new M_CDMR01BLogic();
		blogic.setQueryDAO(queryDAO);

		// delete all exist data
		super.deleteAllData("M_CODE");
	}

	/**
	 * 
	 * Case :test normal situation
	 * ID_CODE = "CTMID" <br>
	 * TYPE_VAL = "09" <br>
	 * TYPE_VAL = "06" CUR_VAL="wer3" <br>
	 * TYPE_VAL = "05" CUR_VAL="1111" <br>
	 * CtmCodeValue = "08081111" <br>
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
				{ "QCSNO", "1", "01", "11", "aa", "0", "2010-11-26  9:44:55", 
				    "2011-8-18  17:39:29", "sysadmin", null },
                { "QCSNO", "2", "01", "11", "2011", "0", "2010-11-26  9:44:55", 
                    "2011-8-18  17:39:29", "sysadmin", null },
                { "QCSNO",  "3", "01",   "11",   "aa", "0",
                    "2010-12-22  17:37:36", "2011-8-18  17:39:29", "sysadmin", null },
                {"QUONO",   "1",    "06",   "aa", "111S", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"QUONO",   "2",    "05",   "1234", "1234", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"BIFNO",   "1",    "06",   "aa", "2222", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"BIFNO",   "2",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"BIFNO",   "3",    "05",   "212",  "212",  "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", null },
                {"INVNO",   "1",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"INVNO",   "2",    "06",   "aa", "1234", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"INVNO",   "3",    "05",   "000763",   "000763",   "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", null },
                {"DBTNO",   "1",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"DBTNO",   "2",    "06",   "aa", "ew34", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"DBTNO",   "3",    "05",   "000008",   "000008",   "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", null },
                {"CDTNO",   "1",    "03",   "20110818", "aa", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"CDTNO",   "2",    "06",   "aa", "77",   "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"CDTNO",   "3",    "05",   "000054",   "000054",   "0",
                    "2010-12-22  17:36:22",  "2011-8-18  17:39:29", "sysadmin", null },
                {"RCPNO",   "1",    "01",   "11",   "2011", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"RCPNO",   "2",    "06",   "aa", "123456789012", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"RCPNO",   "3",    "05",   "1234", "1234", "0",
                    "2010-11-26  9:44:55", "2011-8-18  17:39:29", "sysadmin", null },
                {"SCPID",   "1",    "02",   "2011", "aa", "0",
                    "2011-8-16  17:24:30", "2011-8-18  17:39:29", "sysadmin", null },
                {"SCPID",   "2",    "06",   "aa", "cc223",    "0",
                    "2011-8-16  17:24:30", "2011-8-18  17:39:29", "sysadmin", null },
                {"CTMID",   "1",    "09",   "08",   "aa", "0",
                    "2011-8-16  17:24:30", "2011-8-18  17:39:29", "sysadmin", null },
                {"CTMID",   "2",    "06",   "aa", "wer3", "0",
                    "2011-8-18  11:36:52", "2011-8-18  17:39:29", "sysadmin", null },
                {"QCSNO",   "4",    "05",   "11111111", "11111111", "0",
                    "2011-8-17  10:55:17", "2011-8-18  17:39:29", "sysadmin", null },
                {"SCPID",   "3",    "05",   "5678", "5678", "0",
                    "2011-8-18  10:59:53", "2011-8-18  10:59:53", "sysadmin", null },
                {"CTMID",   "3",    "05",   "1111", "1111", "0",
                    "2011-8-18  11:37:00", "2011-8-18  17:39:29", "sysadmin", null },           
		};
		super.insertData("M_CODE", testDataTBatchLog);


        BLogicResult result = blogic.execute(param);
        M_CDMR01Output output = (M_CDMR01Output) result.getResultObject();

        // ctmid check the result
        assertEquals("CSTID", output.getCtmid()[0].getId_code());
//        assertEquals(null, output.getCtmid()[0].getType_val());
//        assertEquals(null, output.getCtmid()[0].getType_val());
//        assertEquals("06", output.getCtmid()[1].getType_val());
//        assertEquals("wer3", output.getCtmid()[1].getValue());
//        assertEquals("05", output.getCtmid()[2].getType_val());
//        assertEquals("1111", output.getCtmid()[2].getValue());
//        assertEquals("10wer31111", output.getCtmCodeValue());
        assertEquals("success", result.getResultString());
	}
}
