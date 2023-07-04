/**
 * @(#)M_SSMS01_01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created May 14, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_ssm.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_ssm.dto.M_SSMS01_01Input;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_SSMS01_01BLogic_Test extends AbstractUtilTest {
	
	private M_SSMS01_01BLogic blogic01;
	private M_SSMS01_01Input param;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		
		blogic01 = new M_SSMS01_01BLogic();
		param = new M_SSMS01_01Input();
		result = new BLogicResult(); 
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("joel.chin");
	
//		param.setActionType("");
//		param.setSvc_grp("");
//		param.setIdService("");
//		param.setDeletedIdService("");
//		param.setServiceInfoQuantity("");
//		param.setEntry("");
//		param.setReport("");
//		param.setInfoId("");
		
		//result = blogic01.execute(param);
		System.out.println("M_SSM_S01 : Edit / New Service Subs Info : " + result.getResultString());
	}

}
