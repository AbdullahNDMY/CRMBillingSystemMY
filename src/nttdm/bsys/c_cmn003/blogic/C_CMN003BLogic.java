/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : C_CMN
 * SERVICE NAME   : C_CMN003
 * FUNCTION       : Get List module and sub module
 * FILE NAME      : C_CMN003BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.c_cmn003.blogic;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.Globals;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult; 
import nttdm.bsys.c_cmn003.bean.C_CMN003FormBean;
import nttdm.bsys.c_cmn003.bean.SubModule;
import nttdm.bsys.c_cmn003.blogic.AbstractC_CMN003BLogic;
import nttdm.bsys.c_cmn003.dto.C_CMN003Input;
import nttdm.bsys.c_cmn003.dto.C_CMN003Output;
import nttdm.bsys.common.util.BillingSystemConstants; 
/**
 * @author NTT Data Vietnam	
 * Action processes business from C_CMN003 form
 * @param <P>
 */
public class C_CMN003BLogic extends AbstractC_CMN003BLogic{
	
	private static final String SELECT_SQL_Module = "SELECT.C_CMN003.SQL001";
	private static final String SELECT_SQL_SubModule = "SELECT.C_CMN003.SQL002";
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	private QueryDAO queryDAO = null; 
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	} 


	public BLogicResult execute(C_CMN003Input arg0) {
		// TODO Auto-generated method stub
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		try{
			// TODO Auto-generated method stub 
			//Declare output array object
			C_CMN003Output outputDTO = new C_CMN003Output();
			//Set user id for object 
			arg0.setId_user(arg0.getUvo().getId_user()); 
			List<C_CMN003FormBean> arrayModule = new ArrayList<C_CMN003FormBean>();
			arrayModule = queryDAO.executeForObjectList(SELECT_SQL_Module, arg0);
			//List<C_CMN003FormBean> arr = new ArrayList<C_CMN003FormBean>();
			/*for (C_CMN003FormBean formBean : arrayModule) {
				arg0.setId_module(formBean.getId_module());
				List<SubModule> arraySubModule = new ArrayList<SubModule>();
				arraySubModule = queryDAO.executeForObjectList(SELECT_SQL_SubModule, arg0);
				formBean.setArr_subModule(arraySubModule);
				//arr.add(formBean);
			}*/
			List<SubModule> arraySubModule = new ArrayList<SubModule>();
			arraySubModule = queryDAO.executeForObjectList(SELECT_SQL_SubModule, arg0);
			
			outputDTO.setArr_Module(arrayModule);
			outputDTO.setArr_subModule(arraySubModule);
			//return object
			result.setResultObject(outputDTO);
			//return message
			result.setResultString("success");
			
			return result; 
		}catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		} 
	}
	
}
