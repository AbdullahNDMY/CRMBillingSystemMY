/*
 * @(#)A_USR_S01_InitDataBLogic.java
 *
 *
 */
package nttdm.bsys.a_usr.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.*;
import java.util.*;
import nttdm.bsys.a_usr.blogic.AbstractA_USR_S01_InitDataBLogic;

/**
 * BusinessLogic class.
 * 
 * @author hungtm
 */
public class A_USR_S01_InitDataBLogic extends AbstractA_USR_S01_InitDataBLogic {
	public BLogicResult execute(A_USR_S01IO input) {
		BLogicResult result = new BLogicResult();
		A_USR_S01IO output = new A_USR_S01IO();
		String accessType = input.getAccessType("A", "A-USR");
		List<Department> listDepartment = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_DEPARTMENT",null);
		List<Division> listDivision = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_DIVISION",null);
		List<Role>  listRole =queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_ROLE",null);;
		
		output.setAccessType(accessType);
		output.setListDepartment(listDepartment);
		output.setListDivision(listDivision);
		output.setListRole(listRole);
		
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
}