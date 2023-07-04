/**
 * @(#)M_CSTR04BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Mar 20, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_cst.blogic;

import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_cst.bean.M_CSTFormBean;
import nttdm.bsys.m_cst.blogic.M_CSTR04BLogic;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.CustomerDto;
import nttdm.bsys.m_cst.dto.M_CSTR04Input;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_CSTR04BLogic_Test extends AbstractUtilTest {

	private M_CSTR04BLogic blogic04;
	private M_CSTR04BLogic output;
	private M_CSTR04Input param;
		
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic04 = new M_CSTR04BLogic();
		blogic04.setQueryDAO(queryDAO);
		blogic04.setUpdateDAO(updateDAO);
				
	    param = new M_CSTR04Input();
	    result = new BLogicResult();
	    output = (M_CSTR04BLogic) result.getResultObject();
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvoObject(uvo);
		param.getUvoObject().setId_user("joel.chin");
				
		CustomerDto custDto = new CustomerDto();
		
		param.setMode(M_CSTFormBean.NEWMODE);
		custDto.setMode(M_CSTFormBean.NEWMODE);
		param.setCust_name("Corporate Customer 1");
		custDto.setCust_name("Corporate Customer 1");
		
		//insert m_cust_adr ra
		param.setRa_adr_line1("RA Line 1");
		custDto.setRa_adr_line1("RA Line 1");
		param.setRa_adr_line2("RA Line 2");
		custDto.setRa_adr_line1("RA Line 2");
		param.setRa_adr_line3("RA Line 3");
		custDto.setRa_adr_line1("RA Line 3");
		param.setRa_zip_code("12345");
		custDto.setRa_zip_code("12345");
		param.setRa_country_id("SG");
		custDto.setRa_country_id("SG");
		
		//insert m_cust_adr ba
		param.setBa_adr_line1("BA Line 1");
		custDto.setBa_adr_line1("BA Line 1");
		param.setBa_adr_line2("BA Line 2");
		custDto.setBa_adr_line2("BA Line 2");
		param.setBa_adr_line3("BA Line 3");
		custDto.setBa_adr_line3("BA Line 3");
		param.setBa_zip_code("12345");
		custDto.setBa_zip_code("12345");
		param.setBa_country_id("SG");
		custDto.setBa_country_id("SG");

		//insert m_cust_ctc pc
		param.setContact_type(Util.CONTACT_TYPE_PC);
		custDto.setContact_type(Util.CONTACT_TYPE_PC);
		param.setPc_contact_name("Mr. Primary Corporate");
		custDto.setPc_contact_name("Mr. Primary Corporate");
		
		result = blogic04.execute(param); 
		System.out.println("M_CST_S02 : " + result.getResultString());		
	}
}
