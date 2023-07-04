/**
 * @(#)M_CSTR09BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Mar 22, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_cst.blogic;

import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_cst.bean.M_CSTFormBean;
import nttdm.bsys.m_cst.dto.CustomerDto;
import nttdm.bsys.m_cst.dto.M_CSTR09BLogicInput;

/**
 * @author Joel Chin Yat Leng
 *
 */

public class M_CSTR09BLogic_Test extends AbstractUtilTest{
	
	private M_CSTR09BLogic blogic09;
	private M_CSTR09BLogic output;
	private M_CSTR09BLogicInput param;
	
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		
		blogic09 = new M_CSTR09BLogic();
		blogic09.setQueryDAO(queryDAO);
		blogic09.setUpdateDAO(updateDAO);
		
		param = new M_CSTR09BLogicInput();
		result = new BLogicResult(); 
	    output = (M_CSTR09BLogic) result.getResultObject();
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvoObject(uvo);
		param.getUvoObject().setId_user("joel.chin");
				
		CustomerDto custDto = new CustomerDto();
		
		param.setCust_name("Consumer Customer 1");
		custDto.setCust_name("Consumer Customer 1");
		param.setCust_id_no("121212-34-5656");
		custDto.setCust_id_no("121212-34-5656");
		
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
		
		result = blogic09.execute(param);
		System.out.println("M_CST_S03 : " + result.getResultString());
	}
}
