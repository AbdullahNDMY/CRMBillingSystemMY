/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S03
 * FUNCTION       : processing business logic from requests of B_SSM_S03
 * FILE NAME      : B_SSM_S03_Request_Process_Blogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s03.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_ssm.s03.b_rpt.common.B_RPT_SB01;
import nttdm.bsys.b_ssm.s03.b_rpt.common.B_RPT_SB03;
import nttdm.bsys.b_ssm.s03.b_rpt.common.B_RPT_SB04;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_SB01_Input;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_SB03_Input;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.exception.ReportException;

/**
 * @author NTT Data Vietnam	
 * Class Blogic processing business logic from requests of B_SSM_S03
 * @param <P>
 */
public class B_SSM_S03_Request_Process_Blogic<P> implements BLogic<P> {
	
	// Contants 	
	public static final String STR_OUTPUT_SUCCESS = "success";
	public static final String STR_OUTPUT_FAIL = "fail";
	public static final String STR_OUTPUT_VIEW = "view";	
	public static final String STR_OUTPUT_EDIT = "edit";
	public static final String STR_OUTPUT_SAVE = "save";
	public static final String STR_OUTPUT_EXIT = "exit";
	public static final String STR_OUTPUT_PRINTREPORT = "printReport";	
	public static final String STR_OUTPUT_EXPORTITCONTACT = "exportITContact";	
	
	// Private properties
	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;	
	private String contextPath;
	
	/**
	 * Initialize
	 */
	public B_SSM_S03_Request_Process_Blogic() {
		
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
			
			Map<String, Object> customerNameInfo = queryDAO.executeForMap("B_SSM_S03.getCustomerName",bLogicInput);
			if (customerNameInfo != null) {
			    bLogicInput.put(B_SSM_S03_FieldSet.FIELD_CUSTOMERNAME, customerNameInfo.get("customerName"));
			    bLogicOutput.put(B_SSM_S03_FieldSet.FIELD_CUSTOMERNAME, customerNameInfo.get("customerName"));
			    bLogicOutput.put("customerType", customerNameInfo.get("customerType"));
			    bLogicOutput.put("accMgrPrim", CommonUtils.toString(customerNameInfo.get("accMgrPrim")));
			}
			// Map data
			B_SSM_S03_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);
			
			// Get process mode
			String processMode = null;
			try {
				processMode = (String) bLogicInput.get(B_SSM_S03_FieldSet.FIELD_PROCESSMODE).toString();		
			} catch (Exception ex) {
				processMode = null;
			}
			if (CommonUtils.isEmpty(CommonUtils.toString(processMode).trim())) {
			    bLogicOutput.put("addressType", "RA");
			}
			
			// Process following modes
			if (processMode != null && !processMode.equals("")) {
			    List<String> selectedServiceIdList = B_SSM_S03_BUtils.getSelectedServiceIDs(bLogicInput);
			    String[] selectedServiceIdArr = new String[selectedServiceIdList.size()];
			    selectedServiceIdList.toArray(selectedServiceIdArr);
			    
			    BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)bLogicInput.get("uvo");
			    String sessionDirectory = uvo.getSESSION_DIRECTORY();
			    
				// Mode notice
				if (processMode.trim().equals(String.valueOf(B_SSM_S03_FieldSet.PROCESSMODE_NOTICE))) {
					String dBRptTemplatePath = B_SSM_S03_BUtils.getDBReportTemplatePath(queryDAO, B_SSM_S03_FieldSet.REPORTMODE_NOTE);
					String fullDBRptTemplatePath = contextPath + BLogicUtils.emptyValue(dBRptTemplatePath, "");
					B_RPT_SB01_Input rptIn = new B_RPT_SB01_Input();				
					rptIn.setIdCust(BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERID), ""));
					rptIn.setIdCustPlan(BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANID), ""));
					rptIn.setIdCustPlanGrp(selectedServiceIdArr);
					rptIn.setServiceDescs((String[])bLogicInput.get("serviceDescs"));
					rptIn.setSelectedServiceIds((String[])bLogicInput.get("selectedServiceIds"));
					rptIn.setIdSubInfo(BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID), ""));
					rptIn.setModuleId(BillingSystemConstants.SUB_MODULE_B_SSM);
					rptIn.setReportPath(fullDBRptTemplatePath != null && !fullDBRptTemplatePath.trim().equals("")?
									    fullDBRptTemplatePath :		
									    BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_REPORTTEMPLATEPATH), ""));
					rptIn.setAddType(CommonUtils.toString(bLogicInput.get("addressType")));
					rptIn.setSessionDirectory(sessionDirectory);
                    rptIn.setNoticeMode(CommonUtils.toString(bLogicInput.get("noticeMode")));
					B_RPT_Output rptOut = null;
					String rptErrorStatus = null;
					try {
						rptOut = new B_RPT_SB01(queryDAO).export(rptIn);
					} catch (ReportException e) {
						rptOut = null;
						rptErrorStatus = e.getMessage();
					}
					// Add rptOutput to blogic output
					bLogicOutput.put(B_SSM_S03_FieldSet.FIELD_REPORTRESULT, rptOut);
					if (rptErrorStatus != null) {
						bLogicOutput.put(B_SSM_S03_FieldSet.FIELD_REPORTERRORSTATUS, rptErrorStatus);
					}
				}
				
				// Mode completion report
				if (processMode.trim().equals(String.valueOf(B_SSM_S03_FieldSet.PROCESSMODE_COMPLETIONREPORT))) {
					String dBRptTemplatePath = B_SSM_S03_BUtils.getDBReportTemplatePath(queryDAO, B_SSM_S03_FieldSet.REPORTMODE_COMPLETION);
					String fullDBRptTemplatePath = contextPath + BLogicUtils.emptyValue(dBRptTemplatePath, "");
					B_RPT_SB03_Input rptIn = new B_RPT_SB03_Input();				
					rptIn.setCustomerID(BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERID), ""));
					rptIn.setCustomerPlanID(BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANID), ""));
					rptIn.setCustomerPlanGrps(selectedServiceIdArr);
					rptIn.setSubscriptionID(BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID), ""));
					rptIn.setModuleID(BillingSystemConstants.SUB_MODULE_B_SSM);
					rptIn.setReportPath(fullDBRptTemplatePath != null && !fullDBRptTemplatePath.trim().equals("")?
						    fullDBRptTemplatePath :		
						    BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_REPORTTEMPLATEPATH), ""));
					rptIn.setAddType(CommonUtils.toString(bLogicInput.get("addressType")));
					rptIn.setSessionDirectory(sessionDirectory);
					rptIn.setLoginId(uvo.getId_user());
					B_RPT_Output rptOut = null;
					String rptErrorStatus = null;
					try {
						rptOut = new B_RPT_SB03(queryDAO).export(rptIn);
					} catch (ReportException e) {
						rptOut = null;
						rptErrorStatus = e.getMessage();
					}
					// Add rptOutput to blogic output
					bLogicOutput.put(B_SSM_S03_FieldSet.FIELD_REPORTRESULT, rptOut);
					if (rptErrorStatus != null) {
						bLogicOutput.put(B_SSM_S03_FieldSet.FIELD_REPORTERRORSTATUS, rptErrorStatus);
					}
				}
				
				// Mode free format
				if (processMode.trim().equals(String.valueOf(B_SSM_S03_FieldSet.PROCESSMODE_FREEFORMAT))) {					
					HashMap<String, Object> inputParams = new HashMap<String, Object>();				
					// Logon user id
					inputParams.put(B_SSM_S03_FieldSet.FIELD_LOGONUSERID, 
									BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_LOGONUSERID), ""));
					// CustomerID
					inputParams.put(B_SSM_S03_FieldSet.FIELD_CUSTOMERID, 
									BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERID), ""));
					// CustomerPlanID
					inputParams.put(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANID, 
									BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANID), ""));
					// Selected service ids
					//inputParams.put(B_SSM_S03_FieldSet.FIELD_SELECTEDSERVICEIDS, svcLevel2Array);
					// Selected plan link ids
					//inputParams.put(B_SSM_S03_FieldSet.FIELD_SELECTEDCUSTOMERPLANLINKIDS, idCustPlanLinkArray);
					inputParams.put("selectedServiceIdArr", selectedServiceIdArr);
					// SubID
					inputParams.put(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID, 
									BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID), ""));
					// Template path
					String dBRptTemplatePath = B_SSM_S03_BUtils.getDBReportTemplatePath(queryDAO, B_SSM_S03_FieldSet.REPORTMODE_FREEFORMAT);					
					String fullDBRptTemplatePath = contextPath + BLogicUtils.emptyValue(dBRptTemplatePath, "");
					inputParams.put(B_SSM_S03_FieldSet.FIELD_REPORTTEMPLATEPATH, 
								    fullDBRptTemplatePath != null && !fullDBRptTemplatePath.trim().equals("")?
								    fullDBRptTemplatePath :		
									BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S03_FieldSet.FIELD_REPORTTEMPLATEPATH), ""));
					
					// Report logo path
					inputParams.put(B_SSM_S03_FieldSet.FIELD_REPORTLOGOPATH, 
							        bLogicInput.get(B_SSM_S03_FieldSet.FIELD_REPORTLOGOPATH));
					// Sub report path
					inputParams.put(B_SSM_S03_FieldSet.FIELD_SUBREPORTPATH, 
				    			    bLogicInput.get(B_SSM_S03_FieldSet.FIELD_SUBREPORTPATH));	
					inputParams.put("addType", CommonUtils.toString(bLogicInput.get("addressType")));
					// Create report
					B_RPT_Output rptOut = null;
					String rptErrorStatus = null;
					try {
						rptOut = new B_RPT_SB04(queryDAO).export(inputParams);
					} catch (ReportException e) {
						rptOut = null;
						rptErrorStatus = e.getMessage();
					}
					// Add rptOutput to blogic output
					bLogicOutput.put(B_SSM_S03_FieldSet.FIELD_REPORTRESULT, rptOut);
					if (rptErrorStatus != null) {
						bLogicOutput.put(B_SSM_S03_FieldSet.FIELD_REPORTERRORSTATUS, rptErrorStatus);
					}
				}
			}
								
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

	/**
	 * Set context path
	 * @param contextPath
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

}
