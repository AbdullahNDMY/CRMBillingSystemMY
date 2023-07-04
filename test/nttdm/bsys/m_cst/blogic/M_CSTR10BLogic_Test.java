/**
 * @(#)M_CSTR10BLogic_Test.java
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
import nttdm.bsys.m_cst.dto.M_CSTR10BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR10BLogicOutput;
/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_CSTR10BLogic_Test extends AbstractUtilTest{
		
		private M_CSTR10BLogic blogic10;
		private M_CSTR10BLogicInput param;
		private M_CSTR10BLogicOutput outputDTO;
		
		BLogicResult result;
		
		@Override
		protected void setUpData() throws Exception {
			// init test object
			
			blogic10 = new M_CSTR10BLogic();
			blogic10.setQueryDAO(queryDAO);
			blogic10.setUpdateDAO(updateDAO);
			
			param = new M_CSTR10BLogicInput();
			result = new BLogicResult(); 
		}
		
		public void testExecute() throws Exception{
			
			param.setUvo("joel.chin");
			
			// id 33 has all the bank info
			param.setId_cust("33");
			// id 35 does not have any bank info
			//param.setId_cust("35");
			
			result = blogic10.execute(param);
			outputDTO = (M_CSTR10BLogicOutput) result.getResultObject();
			
			System.out.println("M_CST_S02-01 : View Bank Info : " + result.getResultString());
			
			System.out.println("Id_cust : " + outputDTO.getId_cust());
			
			System.out.println("Bank Full Name : " + outputDTO.getBankFullName());
			System.out.println("Bank Code : " + outputDTO.getBankCode());
			System.out.println("Branch Code : " + outputDTO.getBranchCode());
			System.out.println("SwiftCode : " + outputDTO.getSwiftCode());
			System.out.println("Account Number : " + outputDTO.getAcctNumber());
			System.out.println("Account Name : " + outputDTO.getAcctName());
			System.out.println("Reference Number : " + outputDTO.getRefNum());
			
			System.out.println("Card Type : " + outputDTO.getCardType());
			System.out.println("Credit Account Number : " + outputDTO.getAcctNumberCredit());
			System.out.println("Credit Card Number : " + outputDTO.getCreditCardNumber());
			System.out.println("Security No : " + outputDTO.getSecurityNo());
			System.out.println("Holder Name : " + outputDTO.getHolderName());
			System.out.println("Expired Date : " + outputDTO.getExpiredDate());
			System.out.println("Expired Date Month : " + outputDTO.getExpiredDateMonth());
			System.out.println("Expired Date Year : " + outputDTO.getExpiredDateYear());		
		}

}
