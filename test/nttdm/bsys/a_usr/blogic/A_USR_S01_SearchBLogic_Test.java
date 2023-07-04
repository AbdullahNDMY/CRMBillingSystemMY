/**
 * @(#)A_USR_S01_SearchBLogic_Test.java
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

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S01IO;
import nttdm.bsys.a_usr.dto.InputSearch;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.dto.SearchBy;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class A_USR_S01_SearchBLogic_Test  extends AbstractUtilTest{
	
	private A_USR_S01_SearchBLogic blogic01;
	private A_USR_S01IO input;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic01 = new A_USR_S01_SearchBLogic();
		blogic01.setQueryDAO(queryDAO);
		
		input = new A_USR_S01IO();
		result = new BLogicResult();
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		input.setUvo(uvo);
		input.getUvo().setId_user("joel.chin");
		
		InputSearch inputSearch = new InputSearch();
		inputSearch.setIdDepartment("0001");
		inputSearch.setIdDivision("0001");
		//inputSearch.setIdUser("");
		//inputSearch.setUserName("");
		//---get start index
		input.setStartIndex("0");
		// Test for both kind of user input
		//input.setSearchBy(SearchBy.BUTTON);
		input.setSearchBy(SearchBy.PAGELINK);
		
		//---Get inputSearch
		//if search by button then input search will be submit data
		String searchBy = input.getSearchBy();
		if(searchBy.equals(SearchBy.BUTTON)){
			input.setInputSearch(inputSearch);
		//if search by page link then input search will be session's data
		} else if(searchBy.equals(SearchBy.PAGELINK)){
			input.setFirstInputSearch(inputSearch);
		}
			
		result = blogic01.execute(input);
		System.out.println("A_USR_S01 : Search User : " + result.getResultString());
	}

}
