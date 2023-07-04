/**
 * @(#)M_CSTR08BLogicBLogic_Test.java
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
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.m_cst.dto.M_CSTR08BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR08BLogicOutput;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.SecurityUtil;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.BankInfoDto;
import nttdm.bsys.m_cst.dto.M_CSTR08BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR08BLogicOutput;
import nttdm.bsys.m_cst.dto.M_CSTR10BLogicOutput;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_CSTR08BLogicBLogic_Test extends AbstractUtilTest {
	
	private M_CSTR08BLogicBLogic blogic08;
	private M_CSTR08BLogicInput param;
	private M_CSTR08BLogicOutput outputDTO;
	
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		
		blogic08 = new M_CSTR08BLogicBLogic();
		blogic08.setQueryDAO(queryDAO);
		blogic08.setUpdateDAO(updateDAO);
		
		param = new M_CSTR08BLogicInput();
		
		result = new BLogicResult(); 
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvoObject(uvo);
		param.getUvoObject().setId_user("joel.chin");
		
		param.setId_cust("33");
		
		param.setCbBankCode("1001");
		param.setCbBranchCode("001");
		param.setSwiftCode("123456");
		param.setAcctNumber("12341234");
		param.setAcctName("Corporate Customer 1");
		param.setRefNum("R1234");
		
		param.setCardType("VISA");
		param.setAcctNumberCredit("A123123");
		param.setCreditCardNumber("1234567812345670");
		param.setSecurityNo("123");
		param.setHolderName("Consumer Customer 1");
		param.setExpiredDate("12/2014");
		param.setExpiredDateMonth("12");
		param.setExpiredDateYear("2014");

		result = blogic08.execute(param);
		outputDTO = (M_CSTR08BLogicOutput) result.getResultObject();
		
		System.out.println("M_CST_S02-01 : Save Bank Info : " + result.getResultString());
		
		assertEquals("33", outputDTO.getId_cust());
		assertEquals("1001", outputDTO.getCbBankCode());
		assertEquals("001", outputDTO.getCbBranchCode());
		assertEquals("12341234", outputDTO.getAcctNumber());
		assertEquals("Corporate Customer 1", outputDTO.getAcctName());
		assertEquals("R1234", outputDTO.getRefNum());
		
		assertEquals("VISA", outputDTO.getCardType());
		assertEquals("A123123", outputDTO.getAcctNumberCredit());
		assertEquals("1234567812345670", outputDTO.getCreditCardNumber());
		assertEquals("123", outputDTO.getSecurityNo());
		assertEquals("Consumer Customer 1", outputDTO.getHolderName());
		
//		try{
//			assertEquals("1234567812345670", outputDTO.getCreditCardNumber());
//		}catch(AssertionError ae){
//			System.out.println("Comparison Failure");
//		}
	}

}
