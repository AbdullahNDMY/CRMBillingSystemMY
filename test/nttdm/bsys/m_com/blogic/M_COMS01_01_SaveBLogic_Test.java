/**
 * @(#)M_COMS01_01_SaveBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 2, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_com.blogic;

import java.util.HashMap;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_com.dto.CompanyBank;
import nttdm.bsys.m_com.dto.M_COMS01IO;
import org.apache.struts.Globals;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_COMS01_01_SaveBLogic_Test extends AbstractUtilTest{
	
	private M_COMS01_01_SaveBLogic blogic01;
	private M_COMS01IO input;
	private M_COMS01IO output;

	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic01 = new M_COMS01_01_SaveBLogic();
		input = new M_COMS01IO();
		output = new M_COMS01IO();
		blogic01.setQueryDAO(queryDAO);
		blogic01.setUpdateDAO(updateDAO);

		result = new BLogicResult(); 
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		input.setUvo(uvo);
		input.getUvo().setId_user("joel.chin");
		input.setIdCompany("11");
		
		List<CompanyBank> listCompanyBank = null;
		listCompanyBank = queryDAO.executeForObjectList("SELECT.M_COM.GET_LIST_COMPANYBANK", "11");
		input.setListCompanyBank(listCompanyBank);
		
		result = blogic01.execute(input);
		System.out.println("M-COM-S01 : Edit/New Bank Info : " + result.getResultString());
	}

}
