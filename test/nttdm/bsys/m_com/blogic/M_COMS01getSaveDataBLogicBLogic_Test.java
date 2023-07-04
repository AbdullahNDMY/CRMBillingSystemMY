/**
 * @(#)M_COMS01getSaveDataBLogicBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 3, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_com.blogic;

import java.io.File;
import java.util.List;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_com.dto.CompanyBank;
import nttdm.bsys.m_com.dto.M_COMS01getSaveDataBLogicInput;
import nttdm.bsys.m_com.dto.M_COMS01getSaveDataBLogicOutput;
import nttdm.bsys.m_cst.blogic.M_CSTR10BLogic;
import nttdm.bsys.m_cst.dto.M_CSTR10BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR10BLogicOutput;

import org.apache.struts.upload.DiskFile;
import org.apache.struts.upload.FormFile;


/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_COMS01getSaveDataBLogicBLogic_Test extends AbstractUtilTest{
	
	private M_COMS01getSaveDataBLogicBLogic blogic01;
	private M_COMS01getSaveDataBLogicInput param;
	private M_COMS01getSaveDataBLogicOutput outputDTO;
	
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		
		blogic01 = new M_COMS01getSaveDataBLogicBLogic();
		blogic01.setQueryDAO(queryDAO);
		blogic01.setUpdateDAO(updateDAO);
		
		param = new M_COMS01getSaveDataBLogicInput();
		outputDTO = new M_COMS01getSaveDataBLogicOutput();
		result = new BLogicResult(); 
	}
	
	public void testExecute() throws Exception{
		
		outputDTO.setId_com(param.getId_com());
		
		DiskFile filo = new DiskFile("../../../../img/logo_report.jpg");
		param.setFile(filo);
		param.setTbxCompanyName("ABC Singapore Pte. Ltd.");
		param.setTbxCompanyRegNo("199712345M");
		param.setTbxCompanyWebsite("http://www.sg.abc.com");
		param.setTbxTelephoneNo("6899-9900");
		param.setTbxFaxNo("6499-9948");
		param.setTbxAffiliateCode("NTTS");

		param.setTbxAddressLine1RA("RA Company Address Line 1");
		param.setTbxAddressLine2RA("RA Company Address Line 2");
		param.setTbxAddressLine3RA("RA Company Address Line 3");
		param.setTbxZipCodeRA("49705");
		param.setCboAddressCountryRA("Singapore");

		param.setTbxAddressLine1CA("CA Company Address Line 1");
		param.setTbxAddressLine2CA("CA Company Address Line 2");
		param.setTbxAddressLine3CA("CA Company Address Line 3");
		param.setTbxZipCodeCA("555858");
		param.setCboAddressCountryCA("Singapore");
	
		param.setTbxEmailSC("sales@abc.com.sg");
		param.setTbxTelephoneNoSC("+65 6499-9901");
		param.setTbxFaxNoSC("+65 6499-9902");

//		param.setTbxEmailTC("");
//		param.setTbxTelephoneNoTC();
//		param.setTbxFaxNoTC();
//
//		param.setTbxEmailOC();
//		param.setTbxTelephoneNoOC();
//		param.setTbxFaxNoOC();
		
		param.setTbxProxyServerName("303.136.163.11");
		param.setTbxPortNumber("8080");
		param.setTbxPrimaryDomainNameServer("303.136.163.11");
		param.setTbxSecondaryDomainNameServer("303.136.163.11");
		
		param.setTbxDefaultDialupTelNo("6499-9936");
		param.setTbxDefaultPassword("Managed by NTTS");
		
		result = blogic01.execute(param);
		
		System.out.println("M_COM_S01 : Save Company Info : " + result.getResultString());
	}

}
