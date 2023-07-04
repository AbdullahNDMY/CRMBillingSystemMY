/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S02
 * FUNCTION       : processing business logic from requests of B_SSM_S02
 * FILE NAME      : B_SSM_S01_Request_Process_Blogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s02.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.s03.b_rpt.common.B_RPT_SB02;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.RadUserTUtil;
import nttdm.bsys.g_ssm_s01.G_SSM_S01;
import nttdm.bsys.util.exception.ReportException;

/**
 * @author NTT Data Vietnam	
 * Class Blogic processing business logic from requests of B_SSM_S02
 * @param <P>
 */
public class B_SSM_S02_Request_Process_Blogic<P> implements BLogic<P> {
	
	// Contants 	
	public static final String STR_OUTPUT_SUCCESS = "success";
	public static final String STR_OUTPUT_FAIL = "fail";
	public static final String STR_OUTPUT_VIEW = "view";	
	public static final String STR_OUTPUT_EDIT = "edit";
	public static final String STR_OUTPUT_SAVE = "save";
	public static final String STR_OUTPUT_EXIT = "exit";
	public static final String STR_OUTPUT_PRINTREPORT = "printReport";	
	public static final String STR_OUTPUT_EXPORTITCONTACT = "exportITContact";	
	
	// Privates properties
	private QueryDAO queryDAO;
	private QueryDAO radiusQueryDAO;
	private UpdateDAO radiusUpdateDAO;
	private UpdateDAO updateDAO;	
	private String contextPath;
	/**
     * BLogicMessages
     */
    private BLogicMessages msgs;
	
	/**
	 * Initialize
	 */
	public B_SSM_S02_Request_Process_Blogic() {
		
	}

	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public BLogicResult execute(P input) {		
		BLogicResult result = new BLogicResult();
		msgs = new BLogicMessages();
		HashMap<String, Object> bLogicOutput = new HashMap<String, Object>();
		String resultStr = "";
		if (input instanceof HashMap) {
			HashMap<String, Object> bLogicInput = (HashMap<String, Object>) input;						
			
			// Copy first properties to output
			BLogicUtils.copyProperties(bLogicOutput, bLogicInput);	
			
			// Evaluate infoIDArray
			List<Integer> infoIDArray = B_SSM_S02v_BUtils.evaluateInfoIDArray(queryDAO, bLogicInput); 									
			
			// Copy infoIDs to input
			bLogicInput.put(B_SSM_S02_FieldSet.FIELD_INFOIDARRAY, infoIDArray);	
			
			// Get customer name
			List<HashMap<String, Object>> customerNameInfo = new ArrayList<HashMap<String,Object>>();
			customerNameInfo = queryDAO.executeForObjectList("B_SSM_S02.getCustomerName",
															 bLogicInput);
			if (customerNameInfo.size() > 0) {
				BLogicUtils.copyProperties(bLogicOutput, customerNameInfo.get(0));			
			}
			
			//display memo
			String displayMemoFlg = CommonUtils.toString(queryDAO.executeForObject("B_SSM_S02.getDisplayMemo", null, String.class));
			if(CommonUtils.isEmpty(displayMemoFlg)) {
			    displayMemoFlg = "0";
			}
			bLogicOutput.put("displayMemoFlg", displayMemoFlg);
			
			// Get process mode
			String processMode = null;
			try {
				processMode = (String) bLogicInput.get(B_SSM_S02_FieldSet.FIELD_PROCESSMODE).toString();		
			} catch (Exception ex) {
				processMode = null;
			}
			
			// Get and set access type
			String accessType = queryDAO.executeForObject("B_SSM_S02.getUserAccess",
												  	   	  bLogicInput,
												  	   	  String.class);
			bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ACCESSTYPE, accessType);
			
			// Mode view			
			if (processMode == null || 
				processMode.equals("") ||
				processMode.trim().equals(String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_VIEW))) {
				// Map data
				B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);	
				B_SSM_S02v_BUtils.mappingCarrierCodeData(queryDAO, bLogicInput, bLogicOutput);
				
				int accessAccountFlag = 0;
				int countWithStatus = 0;
                Integer countWithStatusInteger = queryDAO.executeForObject("B_SSM_S02.getCountByCustomerPlanIdWithStatus", bLogicInput, Integer.class);
                if (countWithStatusInteger!=null) {
                    countWithStatus = countWithStatusInteger.intValue();
                }
                if(countWithStatus <= 0) {
                    accessAccountFlag = 1;
                }
                bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ACCESS_ACCOUNT_FLAG, accessAccountFlag);
                
				// Output string
				resultStr = STR_OUTPUT_VIEW; 
			}
			
			// Mode edit				
			if (processMode != null && 
				!processMode.equals("") && 
				processMode.trim().equals(String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_EDIT))) {
				
				// decide whether the Access Account and Access Password's input fields can be used or not
			    int count = 0;
			    Integer countInteger = queryDAO.executeForObject("B_SSM_S02.getCountByCustomerPlanId", bLogicInput, Integer.class);
				if (countInteger!=null) {
				    count = countInteger.intValue();
				}
				int countWithStatus = 0;
				Integer countWithStatusInteger = queryDAO.executeForObject("B_SSM_S02.getCountByCustomerPlanIdWithStatus", bLogicInput, Integer.class);
				if (countWithStatusInteger!=null) {
				    countWithStatus = countWithStatusInteger.intValue();
				}
				int accessAccountFlag = 0;
				int accessPasswordFlag = 0;
				if(count > 0) {
					accessPasswordFlag = 1;
				}
				if(countWithStatus <= 0) {
					accessAccountFlag = 1;
				}
				//from B_CPM_S02e, check ID_PLAN_GRP from page is Radius
				if(accessPasswordFlag==0) {
				    String idPlanGrpListStr = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_ID_PLAN_GRP_LIST));
				    if(!CommonUtils.isEmpty(idPlanGrpListStr)) {
				        String[] idPlanGrpArr = idPlanGrpListStr.split(",");
				        if (idPlanGrpArr!=null && 0<idPlanGrpArr.length) {
				            List<Map<String, Object>> mapPlanBatchMapping = B_SSM_S02v_BUtils.getPlanBatchMappingByIdPlanGrp(queryDAO, idPlanGrpArr);
				            if(mapPlanBatchMapping != null && 0 < mapPlanBatchMapping.size()){
				                accessPasswordFlag = 1;
				            }
				        }
				    }
				}
				bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ACCESS_ACCOUNT_FLAG, accessAccountFlag);
				bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ACCESS_PASSWORD_FLAG, accessPasswordFlag);
				
				// Map data
				B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);
				B_SSM_S02v_BUtils.mappingCountryCodeData(queryDAO, bLogicInput, bLogicOutput);
				B_SSM_S02v_BUtils.mappingAutoUpdateQuantityPlanData(queryDAO, bLogicInput, bLogicOutput);
				//B_SSM_S02v_BUtils.mappingMailServerInformationWithCodeData(queryDAO, bLogicInput, bLogicOutput);
				//B_SSM_S02v_BUtils.mappingModemNoData(queryDAO, bLogicInput, bLogicOutput);
				// Output string
				resultStr = STR_OUTPUT_EDIT; 
			}
			
			// Mode save				
			if (processMode != null && 
				!processMode.equals("") && 
				processMode.trim().equals(String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_SAVE))) {
				
				// Check result
				boolean chkRst = true;
				
				// Check the mandatory items
				String accessAccount = (String)bLogicInput.get(B_SSM_S02_FieldSet.FIELD_ACCESS_ACCOUNT);
				String accessPassword = (String)bLogicInput.get(B_SSM_S02_FieldSet.FIELD_ACCESS_PASSWORD);
				
				if( bLogicInput.get("accessPasswordFlag").equals(1) && (infoIDArray.contains(1)) && "0".equals(CommonUtils.toString(bLogicInput.get("accessPasswordFlag")))){
					if(accessAccount == null || accessAccount.equals("")) {
						String message = GlobalMessageResources.getInstance().getInstance().getMessage("errors.ERR1SC005",new Object[] { "Access Account" });
						bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ERROR_MESSAGE, message);
						chkRst = false;
					}
				}
				if(infoIDArray.contains(2) && bLogicInput.get("accessPasswordFlag").equals(1) && "0".equals(CommonUtils.toString(bLogicInput.get("accessPasswordFlag")))){
					if(accessPassword == null || accessPassword.equals("")) {
						String message = GlobalMessageResources.getInstance().getInstance().getMessage("errors.ERR1SC005",new Object[] { "Access Password" });
						bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ERROR_MESSAGE, message);
						chkRst = false;
					}
				}
				// Check whether the Access Account already exists in DB or not
//				int countRst = queryDAO.executeForObject("B_SSM_S02.getCountByAccessAccount", bLogicInput, Integer.class);
//				if(countRst > 0) {
//					String message = GlobalMessageResources.getInstance().getInstance().getMessage("errors.ERR1SC006",new Object[] { "Access Account" });
//					bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ERROR_MESSAGE, message);
//					chkRst = false;
//					} 
				
				if(chkRst) {
					// Save data
					B_SSM_S02e_BUtils.saveEditedData(queryDAO, updateDAO, bLogicInput, bLogicOutput);
					// Map data
					B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);
					//B_SSM_S02v_BUtils.mappingCountryCodeData(queryDAO, bLogicInput, bLogicOutput);
					B_SSM_S02v_BUtils.mappingAutoUpdateQuantityPlanData(queryDAO, bLogicInput, bLogicOutput);
					//B_SSM_S02v_BUtils.mappingMailServerInformationWithCodeData(queryDAO, bLogicInput, bLogicOutput);
					B_SSM_S02v_BUtils.mappingCarrierCodeData(queryDAO, bLogicInput, bLogicOutput);
					
					// update radius system's data
					int accessFlag = (Integer) bLogicInput.get(B_SSM_S02_FieldSet.FIELD_ACCESS_PASSWORD_FLAG);
					if(accessFlag > 0) {
						RadUserTUtil rut = new RadUserTUtil(null,radiusUpdateDAO);
						rut.updateRadiusPassword(bLogicInput);
					}
					// Output string
					resultStr = STR_OUTPUT_SAVE; 
				} else {
					// Map data
					B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);
					B_SSM_S02v_BUtils.mappingCountryCodeData(queryDAO, bLogicInput, bLogicOutput);
					B_SSM_S02v_BUtils.mappingAutoUpdateQuantityPlanData(queryDAO, bLogicInput, bLogicOutput);
					B_SSM_S02v_BUtils.mappingMailServerInformationWithCodeData(queryDAO, bLogicInput, bLogicOutput);
					//B_SSM_S02v_BUtils.mappingModemNoData(queryDAO, bLogicInput, bLogicOutput);
					BLogicUtils.copyProperties(bLogicOutput, bLogicInput);
					bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_ACCESSTYPE, accessType);
					resultStr = STR_OUTPUT_EDIT;
				}
				
			}
			
			// Mode exit
			if (processMode != null && 
				!processMode.equals("") && 
				processMode.trim().equals(String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_EXIT))) {	
				// Output string
				resultStr = STR_OUTPUT_EXIT; 
			}
			
			// Mode print report
			if (processMode != null && 
				!processMode.equals("") && 
				processMode.trim().equals(String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_PRINTREPORT))) {	
				// Output string
				resultStr = STR_OUTPUT_PRINTREPORT; 
			}
			
			// Mode 'print email addition/deletion letter'
			if (processMode != null && 
				!processMode.equals("") && 
				processMode.trim().equals(String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_PRINTEMAILADDITIONDELETIONLETTER))) {
				HashMap<String, Object> inputParams = new HashMap<String, Object>();				
				// Logon user id
				inputParams.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, 
								BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), ""));
				// CustomerID
				inputParams.put(B_SSM_S02_FieldSet.FIELD_CUSTOMERID, 
								BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERID), ""));
				// CustomerPlanID
				inputParams.put(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID, 
								BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID), ""));
				// Selected mail account ids
				inputParams.put(B_SSM_S02_FieldSet.FIELD_SELECTEDMAILACCOUNTIDS, 
							    B_SSM_S02v_BUtils.getSelectedMailAccountIDs(bLogicInput));
				// Selected deleted mail account ids
				inputParams.put(B_SSM_S02_FieldSet.FIELD_SELECTEDDELETEDMAILACCOUNTIDS, 
							    B_SSM_S02v_BUtils.getSelectedDeletedMailAccountIDs(bLogicInput));
				// SubID
				inputParams.put(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID, 
								BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID), ""));
				// Template path
				String dBRptTemplatePath = B_SSM_S02v_BUtils.getDBReportTemplatePath(queryDAO, B_SSM_S02_FieldSet.REPORTMODE_PRINTDELETIONADDITIONEMAIL);
				String fullDBRptTemplatePath = contextPath + BLogicUtils.emptyValue(dBRptTemplatePath, "");
				inputParams.put(B_SSM_S02_FieldSet.FIELD_REPORTTEMPLATEPATH, 
							    fullDBRptTemplatePath != null && !fullDBRptTemplatePath.trim().equals("")?
								fullDBRptTemplatePath :		
							    BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_REPORTTEMPLATEPATH), ""));
				// Report logo path
				inputParams.put(B_SSM_S02_FieldSet.FIELD_REPORTLOGOPATH, 
						        bLogicInput.get(B_SSM_S02_FieldSet.FIELD_REPORTLOGOPATH));
				// Create report
				B_RPT_Output rptOut = null;
				String rptErrorStatus = null;
				try {
					rptOut = new B_RPT_SB02(queryDAO).export(inputParams);
				} catch (ReportException e) {
					rptOut = null;
					rptErrorStatus = e.getMessage();
				}
				// Add rptOutput to blogic output
				bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_REPORTRESULT, rptOut);
				if (rptErrorStatus != null) {
					bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_REPORTERRORSTATUS, rptErrorStatus);									
				} else {
					// Update printed date
					B_SSM_S02e_BUtils.updateMailAccountPrintedDate(queryDAO, updateDAO, B_SSM_S02v_BUtils.getSelectedMailAccountIDs(bLogicInput));					
					B_SSM_S02e_BUtils.updateMailAccountPrintedDate(queryDAO, updateDAO, B_SSM_S02v_BUtils.getSelectedDeletedMailAccountIDs(bLogicInput));
				}				
				
				// Map data
				B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);	
				B_SSM_S02v_BUtils.mappingCarrierCodeData(queryDAO, bLogicInput, bLogicOutput);
				
				// Output string
				resultStr = STR_OUTPUT_VIEW; 
			}
			
			// Mode 'export it contact'			
			if (processMode != null && 
				!processMode.equals("") &&
				processMode.trim().equals(String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_EXPORTITCONTACT))) {
				String rptErrorStatus = null;
				B_RPT_Output rptOut = null;
				try {
					// Params
					HashMap<String, Object> params = new HashMap<String, Object>();
					// Customer ID
					params.put(B_SSM_S02_FieldSet.FIELD_CUSTOMERID, 
							   BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERID), ""));
					// Customer Plan ID
					params.put(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID, 
							   BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID), ""));
					// Sub ID
					List<String> subIDs = new ArrayList<String>();
					subIDs.add(BLogicUtils.emptyValue(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID), ""));
					params.put(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID, subIDs);					
					// Create rpt
					G_SSM_S01 itContactExport = new G_SSM_S01(queryDAO);
					rptOut = itContactExport.export(params);
				} catch (Exception e) {
					rptOut = null;
					rptErrorStatus = e.getMessage();
				}
				// Add rpt
				bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_REPORTRESULT, rptOut);
				if (rptErrorStatus != null) {
					bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_REPORTERRORSTATUS, rptErrorStatus);									
				}
				// Map data
				B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput);	
				B_SSM_S02v_BUtils.mappingCarrierCodeData(queryDAO, bLogicInput, bLogicOutput);
				// Output string
				resultStr = STR_OUTPUT_EXPORTITCONTACT; 
			}
			
			String isCheckErrorFlag = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_CHECK_ERROR_FLAG)).trim();
			String idSubInfo = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
			// Mode Test Start         
            if (processMode != null && 
                !"".equals(processMode) &&
                String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_TEST_START).equals(CommonUtils.toString(processMode).trim())) {
                
                String accessAccount = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_ACCESS_ACCOUNT)).trim();
                String accessPw = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_ACCESS_PASSWORD)).trim();
                
                if (B_SSM_S02_FieldSet.FIELD_CHECK_ERROR_FLAG_NO.equals(isCheckErrorFlag)) {
                    String idLogin = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
                    Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", idSubInfo);
                    String planStatus = "";
                    if (mapCustomerPlanH!=null) {
                        planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
                    }
                    int auditID = CommonUtils.auditTrailBegin(idLogin, BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_SSM, idSubInfo, planStatus, BillingSystemConstants.AUDIT_TRAIL_EDITED);
                    
                    B_SSM_S02v_BUtils.updateAccessAccountTest(updateDAO, idSubInfo, "1", idLogin, auditID);
                    B_SSM_S02v_BUtils.testStartBtnClick(radiusQueryDAO, radiusUpdateDAO, accessAccount, accessPw);
                    
                    BLogicMessage msg = new BLogicMessage("info.ERR2SC003", 
                            new Object[] {});
                     msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                }
                // Map data
                B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput); 
                B_SSM_S02v_BUtils.mappingCarrierCodeData(queryDAO, bLogicInput, bLogicOutput);
                // Output string
                resultStr = STR_OUTPUT_VIEW;
            }
            
            // Mode Test Complete         
            if (processMode != null && 
                    !"".equals(processMode) &&
                    String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_TEST_COMPLETE).equals(CommonUtils.toString(processMode).trim())) {
                
                String accessAccount = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_ACCESS_ACCOUNT)).trim();
                if (B_SSM_S02_FieldSet.FIELD_CHECK_ERROR_FLAG_NO.equals(isCheckErrorFlag)) {
                    String idLogin = CommonUtils.toString(bLogicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
                    Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", idSubInfo);
                    String planStatus = "";
                    if (mapCustomerPlanH!=null) {
                        planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
                    }
                    int auditID = CommonUtils.auditTrailBegin(idLogin, BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_SSM, idSubInfo, planStatus, BillingSystemConstants.AUDIT_TRAIL_EDITED);
                    
                    B_SSM_S02v_BUtils.updateAccessAccountTest(updateDAO, idSubInfo, "0", idLogin, auditID);
                    B_SSM_S02v_BUtils.testCompleteBtnClick(queryDAO, radiusQueryDAO, radiusUpdateDAO, accessAccount);
                    BLogicMessage msg = new BLogicMessage("info.ERR2SC003", 
                            new Object[] {});
                     msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                }
                
                // Map data
                B_SSM_S02v_BUtils.mappingViewData(queryDAO, bLogicInput, bLogicOutput); 
                B_SSM_S02v_BUtils.mappingCarrierCodeData(queryDAO, bLogicInput, bLogicOutput);
                // Output string
                resultStr = STR_OUTPUT_VIEW;
            }
			
			// Copy infoIDs to output
			bLogicOutput.put(B_SSM_S02_FieldSet.FIELD_INFOIDARRAY, infoIDArray);						
			
		} else {
			resultStr = STR_OUTPUT_FAIL;
		}
		/****** Assign values to result *************/
		result.setResultString(resultStr);
		result.setResultObject(bLogicOutput);
		result.setMessages(msgs);
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

	public UpdateDAO getRadiusUpdateDAO() {
		return radiusUpdateDAO;
	}

	public void setRadiusUpdateDAO(UpdateDAO radiusUpdateDAO) {
		this.radiusUpdateDAO = radiusUpdateDAO;
	}

    /**
     * @return the radiusQueryDAO
     */
    public QueryDAO getRadiusQueryDAO() {
        return radiusQueryDAO;
    }

    /**
     * @param radiusQueryDAO the radiusQueryDAO to set
     */
    public void setRadiusQueryDAO(QueryDAO radiusQueryDAO) {
        this.radiusQueryDAO = radiusQueryDAO;
    }

}
