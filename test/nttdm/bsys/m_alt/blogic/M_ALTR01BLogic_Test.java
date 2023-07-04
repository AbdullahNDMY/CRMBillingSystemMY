/**
 * @(#)M_ALTR01BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 10, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_alt.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_alt.dto.M_ALTR01Input;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_ALTR01BLogic_Test extends AbstractUtilTest{
	
	private M_ALTR01BLogic blogic01;
	private M_ALTR01Input param;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic01 = new M_ALTR01BLogic();
		blogic01.setQueryDAO(queryDAO);
		blogic01.setUpdateDAO(updateDAO);
		
		param = new M_ALTR01Input();
		result = new BLogicResult();
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("joel.chin");
		
		List<UserAccess> userAccessList = new ArrayList<UserAccess>();
		UserAccess userAccess = new UserAccess();
		userAccess.setId_sub_module("M-ALT");
		userAccess.setAccess_type("2");
		userAccessList.add(userAccess);
		
		param.getClick_event();
		param.getId_msg();
		param.getUvo().setUser_access(userAccessList);
		param.getMsg_type();
		param.getMsg_type_label();
		
		
		result = blogic01.execute(param);
		System.out.println("M_ALT_R02 : New Notification / Task : " + result.getResultString());
	}

}
