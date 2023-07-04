/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S04
 * FUNCTION       : processing business logic from requests of B_SSM_S04
 * FILE NAME      : B_SSM_S04_Request_Process_Blogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s04.blogic;

import java.util.HashMap;

import nttdm.bsys.b_ssm.utility.BLogicUtils;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * @author NTT Data Vietnam	
 * Class Blogic processing business logic from requests of B_SSM_S04
 * @param <P>
 */
public class B_SSM_S04_Request_Process_Blogic<P> implements BLogic<P> {
	
	// Contants 	
	public static final String STR_OUTPUT_SUCCESS = "success";
	public static final String STR_OUTPUT_FAIL = "fail";	
	
	// Privates properties
	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;	
	
	/**
	 * Initialize
	 */
	public B_SSM_S04_Request_Process_Blogic() {
		
	}

	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public BLogicResult execute(P input) {		
		BLogicResult result = new BLogicResult();
		HashMap<String, Object> bLogicOutput = new HashMap<String, Object>();
		String resultStr = "";
		if (input instanceof HashMap) {
			HashMap<String, Object> bLogicInput = (HashMap<String, Object>) input;						
			
			// Copy first properties to output
			BLogicUtils.copyProperties(bLogicOutput, bLogicInput);	
			
			// Map data
			B_SSM_S04_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);
								
			resultStr = STR_OUTPUT_SUCCESS;
		} else {
			resultStr = STR_OUTPUT_FAIL;
		}
		/****** Assign values to result *************/
		result.setResultString(resultStr);
		result.setResultObject(bLogicOutput);		
		return result;
	}
	

	/**
	 * Set value queryDao
	 * @param queryDao
	 */
	public void setQueryDAO(QueryDAO queryDao) {
		this.queryDAO = queryDao;
	}

	/**
	 * Get queryDAO
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 *  Set update DAO
	 *	@param updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * Get update DAO
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

}
