/**
 * @(#)A_USR_S02_SaveBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 5, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.a_usr.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S02IO;
import nttdm.bsys.a_usr.dto.User;
import nttdm.bsys.a_usr.dto.UserAccess;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class A_USR_S02_SaveBLogic_Test extends AbstractUtilTest{
	
	private A_USR_S02_SaveBLogic blogic02;
	private A_USR_S02IO input;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
	    
		// init test object
		blogic02 = new A_USR_S02_SaveBLogic();
		blogic02.setQueryDAO(queryDAO);
		blogic02.setUpdateDAO(updateDAO);
		
		input = new A_USR_S02IO();
		result = new BLogicResult();
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		input.setUvo(uvo);
		input.getUvo().setId_user("joel.chin");
		
		User user = new User();
		user.setDidNo("6899-1011");
		user.setEmail("sales@email.com");
		user.setIdDepartment("Dep2");
		user.setIdDivision("DivA");
		// value too large for PPLSOFT_ACC_ID [actual:9, maximum:5]
		// user.setIdPeopleSoftAcc("1234-5678");
		user.setIdPeopleSoftAcc("12345");
		user.setIdRole("0000000003");
		user.setIdUser("new.user");
		user.setOfficeMobileNo("6789-6789");
		user.setPassword("password123");
		user.setReEnterPassword("password123");
		user.setTelExtNo("123");
		user.setTelNo("6899-1010");
		user.setUserName("New User");
		user.setUserStatus("1");
		user.setIdUser("joel.chin");
		input.setUser(user);

		// how to input user access right for each MODULE & SUB-MODULE in the LIST
		List<UserAccess> listUserAccess = null;
		listUserAccess = queryDAO.executeForObjectList("SELECT.A_USER.GET_LIST_USERACCESS", "joel.chin");
		input.setListUserAccess(listUserAccess);

		for(int i = 0; i < listUserAccess.size();i++){
			UserAccess access = listUserAccess.get(i);
			access.setIdLogin("joel.chin");
			access.setIdUser("joel.chin");
			//updateDAO.execute("INSERT.A_USR.USERACCESS", access);
		}
		
		result = blogic02.execute(input);
		System.out.println("A_USR_S02 : New User : " + result.getResultString());		
	}

}
