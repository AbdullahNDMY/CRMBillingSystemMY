/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S04
 * FUNCTION       : Action processing business logic from requests of B_SSM_S04
 * FILE NAME      : B_SSM_S04_BProcess_Action.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s04.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.action.ActionMappingEx;
import jp.terasoluna.fw.web.struts.actions.BLogicAction;

/**
 * @author NTT Data Vietnam	
 * Action processes business from B_SSM_S04 form
 * @param <P>
 */
public class B_SSM_S04_BProcess_Action<P> extends BLogicAction<P> {
	
	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.web.struts.actions.DownloadBLogicAction#processBLogicResult(jp.terasoluna.fw.service.thin.BLogicResult, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, jp.terasoluna.fw.web.struts.action.ActionMappingEx)
	 */
	public void processBLogicResult(BLogicResult result, 
									 HttpServletRequest request,
									 HttpServletResponse response, 
									 ActionMappingEx mappingEx) {		
		super.processBLogicResult(result, request, response, mappingEx);	
	}

	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.web.struts.actions.AbstractBLogicAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward doExecute(ActionMapping mapping, 
									ActionForm form,
								    HttpServletRequest request, 
								    HttpServletResponse response) {		
		
		// Process forward
		ActionForward actforward = null;
		try {
			actforward = super.doExecute(mapping, form, request, response);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return actforward;
	}	
	
}
