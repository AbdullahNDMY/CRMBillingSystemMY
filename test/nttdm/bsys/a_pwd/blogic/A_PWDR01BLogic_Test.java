/**
 * @(#)A_PWDR01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 5, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.a_pwd.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_pwd.dto.A_PWDS01R01Input;
import nttdm.bsys.a_pwd.dto.A_PWDS01R01Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class A_PWDR01BLogic_Test extends AbstractUtilTest{
	
	private A_PWDR01BLogic blogic01;
	private A_PWDS01R01Input param;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic01 = new A_PWDR01BLogic();
		blogic01.setQueryDAO(queryDAO);
		blogic01.setUpdateDAO(updateDAO);
		
		param = new A_PWDS01R01Input();
		result = new BLogicResult();
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("joel.chin");
		param.setUserID("joel.chin");
		
		// IF condition : current password == old password ?
		// IF condition : re-enter password == new password ?
		// IF condition : current password == new password ? 
		// IF condition : new password length < 6 ?
	
		param.getUvo().setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
		param.setOldPassword("password");
		param.setReEnterPassword("default");
		param.setNewPassword("default");
		
		result = blogic01.execute(param);
		A_PWDS01R01Output output = (A_PWDS01R01Output) result.getResultObject();
		System.out.println("User Status : " + output.getUvo().getUser_status());
		System.out.println("A_PWD_R01 : Change Password : " + result.getResultString());
		
	}

}
