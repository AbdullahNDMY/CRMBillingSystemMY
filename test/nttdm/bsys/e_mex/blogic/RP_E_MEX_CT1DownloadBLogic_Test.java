/**
 * @(#)RP_E_MEX_CT1DownloadBLogic_Test.java
 * 
 * Billing System NTTS
 * 
 * Version 1.00
 * 
 * Created 2011/08/23
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mex.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1DownloadInput;

/**
 * Test Class for nttdm.bsys.e_mex.blogic.RP_E_MEX_CT1DownloadBLogic
 * 
 * @author leonzh 
 */
public class RP_E_MEX_CT1DownloadBLogic_Test extends AbstractUtilTest {
	
	private RP_E_MEX_CT1DownloadBLogic blogic;
	private RP_E_MEX_CT1DownloadInput input;
	private BLogicResult result;
	private DownloadFile downloadFile;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_E_MEX_CT1DownloadBLogic();
		blogic.setQueryDAO(queryDAO);
		input = new RP_E_MEX_CT1DownloadInput();
	}
	
	/**
	 * 
	 * Case 1:test normal situation one
	 * 
	 * Condition: <br>
	 * BATCH_G_SGP_P01 = "C:\\" <br>
	 * filename = "myFileName.txt" <br>
	 * 
	 * Result: <br>
	 * downloadFile.getName() = "myFileName.txt" <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute01() {
		// delete data
		super.deleteAllData("M_GSET_D");

		// insert data for BATCH_G_SGP_P01's SET_VALUE is "C:\"
		String[][] dataGsetD = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY", },
				{
						"BATCH_G_SGP_P01",
						"1",
						"Process to generate and export SingPost and update Collection Data",
						"0", "2010-11-24 16:50:44", "2011-07-13 11:00:34",
						"USERFULL", "C:\\", AUDIT_VALUE, "1" } };
		super.insertData("M_GSET_D", dataGsetD);

		input.setFilename("myFileName.txt");
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("21");
		input.setUvo(uvo);
		result = blogic.execute(input);

		downloadFile = (DownloadFile) result.getResultObject();

		assertEquals("myFileName.txt", downloadFile.getName());
		assertEquals("success", result.getResultString());
	}
	
}
