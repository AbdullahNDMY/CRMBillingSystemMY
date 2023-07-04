/**
 * @(#)RP_E_MEX_GR1BLogic_Test.java
 * 
 * Billing System NTTS
 * 
 * Version 1.00
 * 
 * Created 2011/08/24
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mex.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * Test Class for nttdm.bsys.e_mex.blogic.RP_E_MEX_GR1BLogic
 * 
 * @author leonzh
 */
public class RP_E_MEX_GR1BLogic_Test extends AbstractUtilTest {

	private RP_E_MEX_GR1BLogic blogic;
	private BLogicResult result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		blogic = new RP_E_MEX_GR1BLogic();
		result = new BLogicResult();
	}

	/**
	 * 
	 * Case 1:test normal situation one
	 * 
	 * Condition: <br>
	 * 
	 * Result: <br>
	 * result.getResultString() = "success" <br>
	 * 
	 */
	public void testExecute01() {
		result = blogic.execute(null);

		assertEquals("success", result.getResultString());
	}

}
