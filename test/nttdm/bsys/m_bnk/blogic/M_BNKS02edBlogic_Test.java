/**
 * @(#)M_BNKS02edBlogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 3, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_bnk.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_bnk.dto.M_BNKS02edInput;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_BNKS02edBlogic_Test extends AbstractUtilTest{
	
	private M_BNKS02edBlogic blogic02;
	private M_BNKS02edInput param;
	
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		
		blogic02 = new M_BNKS02edBlogic();
		blogic02.setQueryDAO(queryDAO);
		blogic02.setUpdateDAO(updateDAO);
		
		param = new M_BNKS02edInput();
		result = new BLogicResult(); 
	}
	
	public void testExecute() throws Exception{

		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("joel.chin");
		
		param.setTbxBankCode("1021");
		param.setTbxBankName("Chase Morgan International");
		param.setTbxBranchCode("023");
		param.setTbxBranchName("Island Branch");
		
		param.setTbxAddressLine1RA("RA 1");
		param.setTbxAddressLine2RA("RA 2");
		param.setTbxAddressLine3RA("RA 3");
		param.setTbxZipCodeRA("123456");
		param.setCboAddressCountryRA("SG");
		
		param.setTbxAddressLine1CA("RA 1");
		param.setTbxAddressLine2CA("RA 2");
		param.setTbxAddressLine3CA("RA 3");
		param.setTbxZipCodeCA("123456");
		param.setCboAddressCountryCA("SG");
		
		param.setTbxContactNamePC("Mr. GM");
		param.setTbxDesignationPC("General Manager");
		param.setTbxEmailPC("gm@bank.com");
		param.setTbxTelephoneNoPC("6899-0011");
		param.setTbxFaxNoPC("6499-0011");
		param.setTbxDIDNoPC("6899-0011");
		param.setTbxMobileNoPc("6789-1234");

		param.setTbxContactNameOC("Mr. Officer");
		param.setTbxDesignationOC("Bank Officer");
		param.setTbxEmailOC("officer@bank.com");
		param.setTbxTelephoneNoOC("6899-1111");
		param.setTbxFaxNoOC("6499-1111");
		param.setTbxDIDNoOC("6899-1111");
		param.setTbxMobileNoOC("6789-9876");
		
		result = blogic02.execute(param);
		System.out.println("M_BNK_S02 : Edit Bank : " + result.getResultString());
			
	}

}



