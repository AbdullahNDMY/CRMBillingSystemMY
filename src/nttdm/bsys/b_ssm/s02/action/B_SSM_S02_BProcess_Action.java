/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S02
 * FUNCTION       : Processing requests from B_SSM_S02
 * FILE NAME      : B_SSM_S02_BProcess_Action.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 * 	2011/09/15: Add parameters into path leading to 'export IT contact'
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s02.action;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nttdm.bsys.b_ssm.s02.blogic.B_SSM_S02_Request_Process_Blogic;
import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.utility.FileUtils;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.UserValueObject;
import jp.terasoluna.fw.web.struts.action.ActionMappingEx;
import jp.terasoluna.fw.web.struts.actions.BLogicAction;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;

/**
 * @author NTT Data Vietnam	
 * Action processes business from B_SSM_S02 form
 * @param <P>
 */
public class B_SSM_S02_BProcess_Action<P> extends BLogicAction<P> {
	
	private boolean isReportEmpty = false;
	
	public static final String STR_OUTPUT_SUCCESS = "success";
	public static final String STR_OUTPUT_FAIL = "fail";
	public static final String STR_OUTPUT_VIEW = "view";	
	public static final String STR_OUTPUT_EDIT = "edit";
	public static final String STR_OUTPUT_SAVE = "save";
	public static final String STR_OUTPUT_EXIT = "exit";
	public static final String STR_OUTPUT_PRINTREPORT = "printReport";	
	public static final String STR_OUTPUT_EXPORTITCONTACT = "exportITContact";	
	
	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.web.struts.actions.DownloadBLogicAction#processBLogicResult(jp.terasoluna.fw.service.thin.BLogicResult, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, jp.terasoluna.fw.web.struts.action.ActionMappingEx)
	 */
	@SuppressWarnings("unchecked")
	public void processBLogicResult(BLogicResult result, 
									 HttpServletRequest request,
									 HttpServletResponse response, 
									 ActionMappingEx mappingEx) {		
		// Get process mode		
		int processMode = -1;
		HashMap<String, Object> resultObj = (HashMap<String, Object>) result.getResultObject();
		try {			
			processMode = Integer.parseInt(resultObj.get(B_SSM_S02_FieldSet.FIELD_PROCESSMODE).toString());			
		} catch(Exception ex) {
			processMode = -1;			
		}
		
		// Get report result
		B_RPT_Output reportResult = (B_RPT_Output) (resultObj.get(B_SSM_S02_FieldSet.FIELD_REPORTRESULT) != null?
									resultObj.get(B_SSM_S02_FieldSet.FIELD_REPORTRESULT):
									null);
		if (reportResult == null) {
			processMode = -1;	
			isReportEmpty = true;
		}
		
		if (!isReportEmpty) {
			// Mode "print email addition/deletion letter"
			if (processMode == B_SSM_S02_FieldSet.PROCESSMODE_PRINTEMAILADDITIONDELETIONLETTER) {
				FileUtils.exportFile(response, 
									 reportResult.getInputStream(), 
									 reportResult.getFileName(),
									 reportResult.getContentType().getValue());
			}
			
			// Mode "export it contact"
			if (processMode == B_SSM_S02_FieldSet.PROCESSMODE_EXPORTITCONTACT) {
				FileUtils.exportFile(response, 
									 reportResult.getInputStream(), 
									 reportResult.getFileName(),
									 reportResult.getContentType().getValue());
			}
					
		}			
		
		// Mode default
		if (processMode == -1) {
			super.processBLogicResult(result, request, response, mappingEx);	
		}
	}

	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.web.struts.actions.AbstractBLogicAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	public ActionForward doExecute(ActionMapping mapping, 
									ActionForm form,
								    HttpServletRequest request, 
								    HttpServletResponse response) {		
		
		DynaValidatorActionFormEx dynaForm = (DynaValidatorActionFormEx)form;
		
		// Get logon userId
		HttpSession session = request.getSession();
		BillingSystemUserValueObject userObj = (BillingSystemUserValueObject) session.getAttribute(UserValueObject.USER_VALUE_OBJECT_KEY);
		if(userObj != null) {			
			dynaForm.set(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, userObj.getId_user());
		}
		
		// Get 'process mode'
		int processMode = 0;
		try {			
			processMode = Integer.parseInt((String) dynaForm.get(B_SSM_S02_FieldSet.FIELD_PROCESSMODE));
		} catch (Exception ex) {
			processMode = 0;
		}
		
		// Set report template stream
		setReportTemplatePath(request, dynaForm, processMode);
		
		// Set context path 
		((B_SSM_S02_Request_Process_Blogic)getBusinessLogic()).setContextPath(getServlet().getServletContext().getRealPath(""));
		
		// Process forward
		ActionForward actforward = null;
		try {
			actforward = super.doExecute(mapping, form, request, response);			
		} catch (Exception e) {			
			e.printStackTrace();
		}			
		
		// Mode empty report
		if (isReportEmpty) {
			return actforward;
		}
		
		// Mode export
		if (processMode == B_SSM_S02_FieldSet.PROCESSMODE_PRINTEMAILADDITIONDELETIONLETTER) {
			return null;
		}
		
		// Mode "export it contact"
		if (processMode == B_SSM_S02_FieldSet.PROCESSMODE_EXPORTITCONTACT) {
			return null;
		}
		
		return null;
	}

	/**
	 * Create export it contact forward
	 * @param actforward
	 * @param dynaForm 
	 * @return ActionForward
	 */
	public ActionForward createExportITContactForward(ActionForward actforward, DynaValidatorActionFormEx dynaForm) {
		StringBuffer pathBuffer = new StringBuffer(actforward.getPath());
		// Customer ID
		pathBuffer.append("?" + B_SSM_S02_FieldSet.FIELD_CUSTOMERID + "=" + dynaForm.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERID));
		// Customer Plan ID
		pathBuffer.append("&" + B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID + "=" + dynaForm.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID));
		// Sub ID
		pathBuffer.append("&" + B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID + "=" + dynaForm.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
		
		// Process forward
		ActionForward processedforward = new ActionForward();
		processedforward.setName(STR_OUTPUT_EXPORTITCONTACT);
		processedforward.setPath(pathBuffer.toString());
		return processedforward;
	}

	/**
	 * Set report template path from config
	 * @param request
	 * @param dynaForm
	 * @param processMode
	 */
	private void setReportTemplatePath(HttpServletRequest request,
				   						 DynaValidatorActionFormEx dynaForm, 
				   						 int processMode) {
		// Mode 'print email addition/deletion letter'
		if (processMode == B_SSM_S02_FieldSet.PROCESSMODE_PRINTEMAILADDITIONDELETIONLETTER) {
			String templatePath = getResources(request).getMessage(B_SSM_S02_FieldSet.KEY_FILEPATH_BRPTS02);
			String basePath = getServlet().getServletContext().getRealPath("");
			String logoPath = basePath + getResources(request).getMessage(B_SSM_S02_FieldSet.KEY_FILEPATH_REPORTLOGO);
			String fullTemplatePath = basePath + templatePath;
			dynaForm.set(B_SSM_S02_FieldSet.FIELD_REPORTTEMPLATEPATH, fullTemplatePath);
			dynaForm.set(B_SSM_S02_FieldSet.FIELD_REPORTLOGOPATH, logoPath);
		}
	}	
	
}
