/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S04
 * FUNCTION       : Processing requests from B_SSM_S04
 * FILE NAME      : B_SSM_S04_Request_Process_Action.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 **********************************************************/

package nttdm.bsys.b_ssm.s04.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;
import nttdm.bsys.b_ssm.s04.data.B_SSM_S04_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.web.struts.actions.ForwardAction;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;

/**
 * @author NTT Data Vietnam 
 * Action processes requests from B_SSM_S04
 */
public class B_SSM_S04_Request_Process_Action extends ForwardAction {

	// Contants
	public static String STR_OUTPUT_SUCCESS = "success";
	public static String STR_OUTPUT_FAIL = "fail";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.terasoluna.fw.web.struts.actions.ActionEx#doExecute(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, 
									ActionForm form,
									HttpServletRequest request, 
									HttpServletResponse response) {
		DynaValidatorActionFormEx dynaForm = (DynaValidatorActionFormEx) form;
		
		// Get 'cust plan id'
		String idCustPlan = BLogicUtils.emptyValue(request.getParameter(B_SSM_S04_FieldSet.FIELD_CUSTOMERPLANID), null);
		if (idCustPlan != null) {
			dynaForm.set(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANID,idCustPlan);
		}
		// Get 'subscription id'
		String idSubInfo = BLogicUtils.emptyValue(request.getParameter(B_SSM_S04_FieldSet.FIELD_SUBSCRIPTIONID), null);
		if (idSubInfo != null) {
			dynaForm.set(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID, idSubInfo);
		}
		// Get 'cust id'
		String idCust = BLogicUtils.emptyValue(request.getParameter(B_SSM_S04_FieldSet.FIELD_CUSTOMERID), null);
		if (idCust != null) {
			dynaForm.set(B_SSM_S03_FieldSet.FIELD_CUSTOMERID, idCust);
		}
		
		return mapping.findForward(STR_OUTPUT_SUCCESS);
	}

}
