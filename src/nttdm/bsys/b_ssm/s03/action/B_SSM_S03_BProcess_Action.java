/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S03
 * FUNCTION       : Action processing business logic from requests of B_SSM_S03
 * FILE NAME      : B_SSM_S03_BProcess_Action.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s03.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.s03.blogic.B_SSM_S03_Request_Process_Blogic;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
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
 * Action processes business from B_SSM_S03 form
 * @param <P>
 */
public class B_SSM_S03_BProcess_Action<P> extends BLogicAction<P> {
	
	private boolean isReportEmpty = false;
	
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
			processMode = Integer.parseInt(resultObj.get(B_SSM_S03_FieldSet.FIELD_PROCESSMODE).toString());			
		} catch(Exception ex) {
			processMode = -1;			
		}
		
		// Get report result
		B_RPT_Output reportResult = (B_RPT_Output) (resultObj.get(B_SSM_S03_FieldSet.FIELD_REPORTRESULT) != null?
									resultObj.get(B_SSM_S03_FieldSet.FIELD_REPORTRESULT):
									null);
		if (reportResult == null) {
			processMode = -1;
			isReportEmpty = true;
		}
		
		if (!isReportEmpty) {
			// Mode notice
			if (processMode == B_SSM_S03_FieldSet.PROCESSMODE_NOTICE) {
				FileUtils.exportFile(response, 
									 reportResult.getInputStream(), 
									 reportResult.getFileName(),
									 reportResult.getContentType().getValue());
			}
			
			// Mode completion report
			if (processMode == B_SSM_S03_FieldSet.PROCESSMODE_COMPLETIONREPORT) {
				FileUtils.exportFile(response, 
									 reportResult.getInputStream(), 
									 reportResult.getFileName(),
									 reportResult.getContentType().getValue());
				
			}
			
			// Mode free format
			if (processMode == B_SSM_S03_FieldSet.PROCESSMODE_FREEFORMAT) {				
				FileUtils.exportZipFile(response, 
										reportResult.getPackageName(),
										reportResult.getInputStreams(), 
										reportResult.getFileNames());
			}
			
			// Set finishing of downloading status
			request.getSession().setAttribute(B_SSM_S03_FieldSet.FIELD_DOWNLOADSTATUS, "1");
			
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
		int processMode = 0;
		
		// Get 'process mode'
		try {			
			processMode = Integer.parseInt((String) dynaForm.get(B_SSM_S03_FieldSet.FIELD_PROCESSMODE));
		} catch (Exception ex) {
			processMode = 0;
		}
		
		// Set report template stream
		setReportTemplatePath(request, dynaForm, processMode);
		
		// Set context path 
		((B_SSM_S03_Request_Process_Blogic)getBusinessLogic()).setContextPath(getServlet().getServletContext().getRealPath(""));
		
		// Get logon userId
		HttpSession session = request.getSession();
		BillingSystemUserValueObject userObj = (BillingSystemUserValueObject) session.getAttribute(UserValueObject.USER_VALUE_OBJECT_KEY);
		if(userObj != null) {			
			dynaForm.set(B_SSM_S03_FieldSet.FIELD_LOGONUSERID, userObj.getId_user());
		}
		
		// Process forward
		ActionForward actforward = null;
		try {
			actforward = super.doExecute(mapping, form, request, response);		
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		// Mode polling
		if (processMode == B_SSM_S03_FieldSet.PROCESSMODE_POLLING) {
			String downloadStatus = BLogicUtils.emptyValue(request.getSession().getAttribute(B_SSM_S03_FieldSet.FIELD_DOWNLOADSTATUS), "0");
			if (downloadStatus.trim().equals("1")) {
				// Set finishing of downloading status
				request.getSession().setAttribute(B_SSM_S03_FieldSet.FIELD_DOWNLOADSTATUS, "0");
			}
			try {
				FileUtils.exportText(response, downloadStatus);
			} catch (IOException e) {				
				e.printStackTrace();
			}
			return null;
		}
		
		// Mode empty report
		if (isReportEmpty) {
			return actforward;
		}
		
		// Mode view or print report
		if (processMode == B_SSM_S03_FieldSet.PROCESSMODE_VIEW ||
			processMode == B_SSM_S03_FieldSet.PROCESSMODE_PRINTREPORT) {
			return actforward;
		}
		
		// Mode export
		if (processMode == B_SSM_S03_FieldSet.PROCESSMODE_NOTICE ||
			processMode == B_SSM_S03_FieldSet.PROCESSMODE_COMPLETIONREPORT ||
			processMode == B_SSM_S03_FieldSet.PROCESSMODE_FREEFORMAT) {
			return null;
		}
		
		return null;
	}

	/**
	 * Set report template path in form
	 * @param request
	 * @param dynaForm
	 * @param processMode
	 */
	private void setReportTemplatePath(HttpServletRequest request,
										 DynaValidatorActionFormEx dynaForm,
										 int processMode) {
		// Mode 'free format'
		if (processMode == B_SSM_S03_FieldSet.PROCESSMODE_FREEFORMAT) {
			String basePath = getServlet().getServletContext().getRealPath("");
			String templatePath = BLogicUtils.emptyValue(getResources(request).getMessage(B_SSM_S03_FieldSet.KEY_FILEPATH_BRPTS04), "");
			String subReportPath = BLogicUtils.emptyValue(getResources(request).getMessage(B_SSM_S03_FieldSet.KEY_FILEPATH_SUBREPORT), "");
			String logoPath = BLogicUtils.emptyValue(getResources(request).getMessage(B_SSM_S03_FieldSet.KEY_FILEPATH_REPORTLOGO), "");
			String fullLogoPath = basePath + logoPath;
			String fullSubReportPath = basePath + subReportPath;
			String fullTemplatePath = basePath + templatePath;
			// Set path
			dynaForm.set(B_SSM_S03_FieldSet.FIELD_REPORTTEMPLATEPATH, fullTemplatePath);
			dynaForm.set(B_SSM_S03_FieldSet.FIELD_REPORTLOGOPATH, fullLogoPath);
			dynaForm.set(B_SSM_S03_FieldSet.FIELD_SUBREPORTPATH, fullSubReportPath);
		}
	}	
	
}
