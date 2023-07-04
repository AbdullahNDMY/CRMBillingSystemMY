/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processing requests from B_SSM_S01
 * FILE NAME      : B_SSM_S01_Request_Process_Action.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s01.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.web.struts.actions.ForwardAction;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;

/**
 * @author NTT Data Vietnam
 * Action processes requests from B_SSM_S01
 */
public class B_SSM_S01_Request_Process_Action extends ForwardAction {

	// Contants 	
	public static String STR_OUTPUT_SUCCESS = "success";
	public static String STR_OUTPUT_FAIL = "fail";
	
	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.web.struts.actions.ActionEx#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, 
								   ActionForm form,
								   HttpServletRequest request,
								   HttpServletResponse response) {
		// Get start index, tabPosition, hasSearchStarted, processMode		
		int tabPosition = 0;
		int hasSearchStarted = -1;
		int processMode = -1;
		DynaValidatorActionFormEx dynaForm = (DynaValidatorActionFormEx)form;
		
		// Tab position
		try {
			tabPosition = Integer.parseInt(request.getParameter(B_SSM_S01_FieldSet.FIELD_TAB_POSITION));			
		} catch (Exception ex) {
			tabPosition = 0;
		}
		if (tabPosition == 0) {
			try {			
				tabPosition = Integer.parseInt((String) dynaForm.get(B_SSM_S01_FieldSet.FIELD_TAB_POSITION));
			} catch (Exception ex) {
				tabPosition = 0;
			}
		}
		
		// Process mode
		try {
			processMode = Integer.parseInt(request.getParameter(B_SSM_S01_FieldSet.FIELD_PROCESSMODE));			
		} catch (Exception ex) {
			processMode = -1;
		}
		
		// Has search started
		try {
			hasSearchStarted = Integer.parseInt(request.getParameter(B_SSM_S01_FieldSet.FIELD_HASSEARCHSTARTED));			
		} catch (Exception ex) {
			hasSearchStarted = -1;
		}
		
		// Set tabPosition	
		if (tabPosition > 0) {
			dynaForm.set(B_SSM_S01_FieldSet.FIELD_TAB_POSITION, String.valueOf(tabPosition));
		}
		
		// Set hasSearchStarted
		if (hasSearchStarted > -1) {
			dynaForm.set(B_SSM_S01_FieldSet.FIELD_HASSEARCHSTARTED, String.valueOf(hasSearchStarted));
		}
		
		// Set processMode
		if (processMode > -1) {
			dynaForm.set(B_SSM_S01_FieldSet.FIELD_PROCESSMODE, String.valueOf(processMode));
		}
		
		return mapping.findForward(STR_OUTPUT_SUCCESS);
	}

}
