/**
 * @(#)M_CURS02_Eblogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 5, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_cur.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_cur.dto.M_CURInput;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_CURS02_Eblogic_Test extends AbstractUtilTest{
	
	private M_CURS02_EBlogic blogic02;
	private M_CURInput arg0;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		
		blogic02 = new M_CURS02_EBlogic();
		blogic02.setUpdateDAO(updateDAO);
		
		arg0 = new M_CURInput();
		result = new BLogicResult(); 
	}
	
	public void testExecute() throws Exception{

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		arg0.setUvo(uvo);
		arg0.getUvo().setId_user("joel.chin");
		
		arg0.setRate_date("02/04/2012");
		arg0.setCur_code("ISK");
				
		result = blogic02.execute(arg0);

		System.out.println("M_CUR_S02 : Edit Currency : " + result.getResultString());
			
	}
}
