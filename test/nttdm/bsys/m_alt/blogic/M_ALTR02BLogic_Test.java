/**
 * @(#)M_ALTR02BLogic_Test.java
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

import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_alt.dto.M_ALTR02Input;
import org.apache.struts.upload.FormFile;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_ALTR02BLogic_Test extends AbstractUtilTest{
	
	private M_ALTR02BLogic blogic02;
	private M_ALTR02Input param;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic02 = new M_ALTR02BLogic();
		blogic02.setQueryDAO(queryDAO);
		blogic02.setUpdateDAO(updateDAO);
		
		param = new M_ALTR02Input();
		result = new BLogicResult();
	}

	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("joel.chin");
		
		param.setClick_event("5");
		param.setId_login("");
		param.setId_msg("");
		param.setImportance_chk("");
		param.setReminder_chk("");
		param.setTo_msg("");
		param.setCc_msg("");
		param.setMsg_type("TASK");
		FormFile[] files = param.getListFile();
		String[] listFileIdOld = param.getListFileIdOld();
		
		
		
		result = blogic02.execute(param);
		System.out.println("M_ALT_R02 : Edit Task : " + result.getResultString());
	}
}
