/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S02
 * FUNCTION       : Processing requests from B_SSM_S02
 * FILE NAME      : B_SSM_S02_Request_Process_Action.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 **********************************************************/

package nttdm.bsys.b_ssm.s02.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.web.UserValueObject;
import jp.terasoluna.fw.web.struts.actions.ForwardAction;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;

/**
 * @author NTT Data Vietnam 
 * Action processes requests from B_SSM_S02
 */
public class B_SSM_S02_Request_Process_Action extends ForwardAction {

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

		// Get 'process mode'
		String processMode = null;
		try {
			processMode = (String) dynaForm.get(B_SSM_S02_FieldSet.FIELD_PROCESSMODE).toString();
		} catch (Exception ex) {
			processMode = null;
		}
		if (processMode == null) {
			processMode = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_PROCESSMODE), null);
		}

		// Get 'logon userId'
		HttpSession session = request.getSession();
		BillingSystemUserValueObject userObj = (BillingSystemUserValueObject) session.getAttribute(UserValueObject.USER_VALUE_OBJECT_KEY);
		if (userObj != null) {
			dynaForm.set(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, userObj.getId_user());
		}

		// Mode view
		if (processMode == null ||
			processMode.equals("")|| 
			processMode.trim().equals(B_SSM_S02_FieldSet.PROCESSMODE_VIEW)) {
			// Get 'cust plan id'
			String idCustPlan = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID), null);
			if (idCustPlan != null) {
				dynaForm.set(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID,idCustPlan);
			}
			// Get 'subscription id'
			String idSubInfo = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID), null);
			if (idSubInfo != null) {
				dynaForm.set(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID, idCustPlan);
			}
			// Get 'cust id'
			String idCust = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_CUSTOMERID), null);
			if (idCust != null) {
				dynaForm.set(B_SSM_S02_FieldSet.FIELD_CUSTOMERID, idCust);
			}
			// Get 'cust name'
			String custName = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_CUSTOMERNAME), null);
			if (custName != null) {
				dynaForm.set(B_SSM_S02_FieldSet.FIELD_CUSTOMERNAME, custName);
			}
			// Get 'is popup'
			String isPopUp = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_ISPOPUP), null);
			if (isPopUp != null) {
				dynaForm.set(B_SSM_S02_FieldSet.FIELD_ISPOPUP, isPopUp);
			}
			// Get 'fromPopup'
			String fromPopUp = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_FROMPOPUP), null);
			if(fromPopUp != null){
				dynaForm.set(B_SSM_S02_FieldSet.FIELD_FROMPOPUP, fromPopUp);
			}
			String idPlanGrpList = BLogicUtils.emptyValue(request.getParameter(B_SSM_S02_FieldSet.FIELD_ID_PLAN_GRP_LIST), null);
            if(idPlanGrpList != null){
                dynaForm.set(B_SSM_S02_FieldSet.FIELD_ID_PLAN_GRP_LIST, idPlanGrpList);
            }
		}

		// Mode edit
		if (processMode != null && 
			!processMode.equals("") &&
			processMode.trim().equals(B_SSM_S02_FieldSet.PROCESSMODE_EDIT)) {
			
		}

		// Mode save
		if (processMode != null &&
		    !processMode.equals("") && 
		    processMode.trim().equals(B_SSM_S02_FieldSet.PROCESSMODE_SAVE)) {
			
		}

		return mapping.findForward(STR_OUTPUT_SUCCESS);
	}

}
