/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processing business from B_SSM_S01 form
 * FILE NAME      : B_SSM_S01_BProcess_Action.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s01.action;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.UserValueObject;
import jp.terasoluna.fw.web.struts.action.ActionMappingEx;
import jp.terasoluna.fw.web.struts.actions.BLogicAction;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;
import nttdm.bsys.b_ssm.bean.blogic.B_SSM_E01_ADR;
import nttdm.bsys.b_ssm.bean.blogic.B_SSM_E01_COL;
import nttdm.bsys.b_ssm.bean.blogic.B_SSM_E01_EMAIL;
import nttdm.bsys.b_ssm.bean.blogic.B_SSM_E01_GENERAL;
import nttdm.bsys.b_ssm.bean.blogic.B_SSM_E01_ISP;
import nttdm.bsys.b_ssm.bean.data.BeanDataCache;
import nttdm.bsys.b_ssm.s01.blogic.B_SSM_S01_Request_Process_Blogic;
import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author NTT Data Vietnam	
 * Action processes business from B_SSM_S01 form
 * @param <P>
 */
public class B_SSM_S01_BProcess_Action<P> extends BLogicAction<P> {
	
	private static final String CLASSTYPE_INT = "Integer";
	private static final String CLASSTYPE_STRING = "String";
	private static final String CLASSTYPE_OBJECT = "Object";
	
	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.web.struts.actions.DownloadBLogicAction#processBLogicResult(jp.terasoluna.fw.service.thin.BLogicResult, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, jp.terasoluna.fw.web.struts.action.ActionMappingEx)
	 */
	public QueryDAO queryDAO;
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	@SuppressWarnings("unchecked")
	public void processBLogicResult(BLogicResult result, 
									 HttpServletRequest request,
									 HttpServletResponse response, 
									 ActionMappingEx mappingEx) {
		
		// Get sessionID 
		String sessionID = request.getSession().getId();
		
		// Get process mode		
		int processMode = 0;
		int tabPosition = 0;
		int typesearch=0;
		HashMap<String, Object> resultObj = (HashMap<String, Object>) result.getResultObject();
		try {			
			processMode = Integer.parseInt(resultObj.get(B_SSM_S01_FieldSet.FIELD_PROCESSMODE).toString());			
		} catch(Exception ex) {
			processMode = 0;			
		}
		try {						
			tabPosition = Integer.parseInt(resultObj.get(B_SSM_S01_FieldSet.FIELD_TAB_POSITION).toString());
		} catch(Exception ex) {			
			tabPosition = 0;
		}
		
		// Mode initial
		if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_INITIAL) {
			// Super processing
			super.processBLogicResult(result, request, response, mappingEx);			
		}
		
		// Mode search
		if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_SEARCH) {
			// Assign and save results to other tabs
			assignAndSaveDataTabsToCache(sessionID, resultObj);
			// Super processing
			super.processBLogicResult(result, request, response, mappingEx);			
		}
		
		// Mode page list
		if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_PAGELIST) {
			// Assign and save results to other tabs
			assignAndSaveDataTabsToCache(sessionID, resultObj);
			// Super processing
			super.processBLogicResult(result, request, response, mappingEx);			
		}
		
		// Mode export
		if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {						
		    
		    // General Tab
            if (tabPosition == B_SSM_S01_FieldSet.TABPOS_GENERAL) {
                B_SSM_E01_GENERAL FileExport = new B_SSM_E01_GENERAL();
                FileExport.setQueryDAO(queryDAO);
                List<HashMap<String, Object>> searchResultSet = (List<HashMap<String, Object>>) resultObj.get(B_SSM_S01_FieldSet.FIELD_GENERALFULLRESULTSET);          
                FileExport.exportCSVFile(searchResultSet,queryDAO,response);
            }
			// ISP Tab
			if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ISP) {
				B_SSM_E01_ISP FileExport = new B_SSM_E01_ISP();
				FileExport.setQueryDAO(queryDAO);
				List<HashMap<String, Object>> searchResultSet = (List<HashMap<String, Object>>) resultObj.get(B_SSM_S01_FieldSet.FIELD_FULLRESULTSET);			
				FileExport.exportCSVFile(searchResultSet,queryDAO,response);
			}
			// Colocation Tab
			if (tabPosition == B_SSM_S01_FieldSet.TABPOS_COLOCATION) {
				if(BLogicUtils.emptyValue(resultObj.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSEARCHBYDETAILS),null) != null )
					typesearch=1;
				B_SSM_E01_COL FileExportCOL = new B_SSM_E01_COL();
				FileExportCOL.setQueryDAO(queryDAO);
				List<HashMap<String, Object>> searchResultSet = (List<HashMap<String, Object>>) resultObj.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONFULLRESULTSET);			
				FileExportCOL.exportCSVFile(searchResultSet, typesearch, queryDAO, response);
			}
			// Email Tab
			if (tabPosition == B_SSM_S01_FieldSet.TABPOS_EMAIL) {
				String emailInfoModeStr = BLogicUtils.emptyValue(resultObj.get(B_SSM_S01_FieldSet.FIELD_EMAILINFOMODE),"");				
				if (emailInfoModeStr.trim().equals(B_SSM_S01_FieldSet.EMAIL_SUBSCRIBEDINFOMODE_VALUE)) {
					typesearch=1;
				}
				if (emailInfoModeStr.trim().equals(B_SSM_S01_FieldSet.EMAIL_DOMAINNAMEINFOMODE_VALUE)) {
					typesearch=2;
				}
				// IT Contact mode
				if (emailInfoModeStr.trim().equals(B_SSM_S01_FieldSet.EMAIL_ITCONTACTINFOMODE_VALUE)) {
					typesearch=3;
				}
				B_SSM_E01_EMAIL FileExportEMAIL = new B_SSM_E01_EMAIL();
				FileExportEMAIL.setQueryDAO(queryDAO);
				List<HashMap<String, Object>> searchResultSet = (List<HashMap<String, Object>>) resultObj.get(B_SSM_S01_FieldSet.FIELD_EMAILFULLRESULTSET);			
				FileExportEMAIL.exportCSVFile(searchResultSet,typesearch, queryDAO, response);
			}
			// Address Tab
			if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ADDRESS) {
			    // #188 Start
			    //List<String> addressTypeArray = (List<String>)resultObj.get("addressTypeArray");
				HashMap<String, Object> logicInput = (HashMap<String, Object>)resultObj.get("logicInput");
				B_SSM_E01_ADR FileExportADR = new B_SSM_E01_ADR();
				FileExportADR.setQueryDAO(queryDAO);
				List<HashMap<String, Object>> searchResultSet = (List<HashMap<String, Object>>) resultObj.get(B_SSM_S01_FieldSet.FIELD_ADDRESSFULLRESULTSET);			
				FileExportADR.exportCSVFile(searchResultSet, queryDAO, response, logicInput);
				// #188 End
			}			
		}
		
		// Mode view with previous state
		if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_VIEWPREVIOUSSTATE) {			
			// Get tab position
			int savedTabPos = request.getSession().getAttribute(B_SSM_S01_FieldSet.FIELD_TAB_POSITION) == null?
					          -1:
					          Integer.valueOf(request.getSession().getAttribute(B_SSM_S01_FieldSet.FIELD_TAB_POSITION).toString()); 
			if (savedTabPos != -1) {				
				tabPosition = savedTabPos;
			}
			resultObj = (HashMap<String, Object>) BeanDataCache.get(sessionID, String.valueOf(tabPosition))!=null?
						(HashMap<String, Object>) BeanDataCache.get(sessionID, String.valueOf(tabPosition)):
						resultObj;
		    // Assign and save results to other tabs
		    assignAndSaveDataTabsToCache(sessionID, resultObj);
		    // Set tab position
		    resultObj.put(B_SSM_S01_FieldSet.FIELD_TAB_POSITION, String.valueOf(savedTabPos));
			// Super processing			
			result.setResultObject(resultObj);
			super.processBLogicResult(result, request, response, mappingEx);
		}
		
		// Save tab position in session
		request.getSession().setAttribute(B_SSM_S01_FieldSet.FIELD_TAB_POSITION, tabPosition);
		
		// Delete full resultSets
		resultObj.remove(B_SSM_S01_FieldSet.FIELD_COLOCATIONFULLRESULTSET);
		resultObj.remove(B_SSM_S01_FieldSet.FIELD_FULLRESULTSET);
		resultObj.remove(B_SSM_S01_FieldSet.FIELD_EMAILFULLRESULTSET);
		resultObj.remove(B_SSM_S01_FieldSet.FIELD_ADDRESSFULLRESULTSET);
		resultObj.remove(B_SSM_S01_FieldSet.FIELD_GENERALFULLRESULTSET);
	}
	
	/**
	 * Assign and save results to other tabs
	 * @param sessionID
	 * @param resultObj
	 */
	private void assignAndSaveDataTabsToCache(String sessionID,
												HashMap<String, Object> resultObj) {		
		HashMap<String, Object> cloneResultObj = (HashMap<String, Object>) resultObj;
		//BLogicUtils.copyProperties(cloneResultObj, resultObj);
		int tabPosition = 0;		
		try {			
			tabPosition = Integer.parseInt(resultObj.get(B_SSM_S01_FieldSet.FIELD_TAB_POSITION).toString());
		} catch (Exception ex) {
			tabPosition = 0;
		}
		
		// Set status 'has search started'
		resultObj.put(B_SSM_S01_FieldSet.FIELD_HASSEARCHSTARTED, "1");
		
		// General Tab
        if (tabPosition == B_SSM_S01_FieldSet.TABPOS_GENERAL) {
            // Save result object           
            BeanDataCache.add(sessionID, String.valueOf(tabPosition), cloneResultObj);
            // Assign resultSet to other tabs
            // ISP tab                              
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_ISP,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_RESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_STARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_TOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_CURRENTROW,
                                                      B_SSM_S01_FieldSet.FIELD_DISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
            // Colocation tab                               
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_COLOCATION,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONRESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_COLOCATIONCURRENTROW,                                                
                                                      B_SSM_S01_FieldSet.FIELD_COLOCATIONDISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
            // Email tab                
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_EMAIL,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_EMAILRESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_EMAILCURRENTROW,
                                                      B_SSM_S01_FieldSet.FIELD_EMAILDISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
            
            // Address tab
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_ADDRESS,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_ADDRESSRESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_ADDRESSTOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_ADDRESSCURRENTROW,
                                                      B_SSM_S01_FieldSet.FIELD_ADDRESSDISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
        }
		
		// ISP Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ISP) {
			// Save result object			
			BeanDataCache.add(sessionID, String.valueOf(tabPosition), cloneResultObj);
			// Assign resultSet to other tabs
			// General tab
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_GENERAL,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_GENERALRESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALTOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALCURRENTROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALDISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
			// Colocation tab								
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_COLOCATION,
										new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONCURRENTROW,												
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			// Email tab				
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_EMAIL,
										new String[] {B_SSM_S01_FieldSet.FIELD_EMAILRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_EMAILCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_EMAILDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			
			// Address tab
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_ADDRESS,
										new String[] {B_SSM_S01_FieldSet.FIELD_ADDRESSRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
		}
		
		// Colocation Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_COLOCATION) {
			// Save result object			
			BeanDataCache.add(sessionID, String.valueOf(tabPosition), cloneResultObj);
			// Assign resultSet to other tabs
			// General tab
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_GENERAL,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_GENERALRESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALTOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALCURRENTROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALDISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
			// ISP tab								
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_ISP,
										new String[] {B_SSM_S01_FieldSet.FIELD_RESULTSET,
													  B_SSM_S01_FieldSet.FIELD_STARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_TOTALROW,
													  B_SSM_S01_FieldSet.FIELD_CURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_DISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			// Email tab				
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_EMAIL,
										new String[] {B_SSM_S01_FieldSet.FIELD_EMAILRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_EMAILCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_EMAILDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			
			// Address tab
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_ADDRESS,
										new String[] {B_SSM_S01_FieldSet.FIELD_ADDRESSRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
		}
		
		// Email Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_EMAIL) {
			// Save result object			
			BeanDataCache.add(sessionID, String.valueOf(tabPosition), cloneResultObj);
			// Assign resultSet to other tabs
			// General tab
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_GENERAL,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_GENERALRESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALTOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALCURRENTROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALDISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
			// ISP tab								
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_ISP,
										new String[] {B_SSM_S01_FieldSet.FIELD_RESULTSET,
													  B_SSM_S01_FieldSet.FIELD_STARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_TOTALROW,
													  B_SSM_S01_FieldSet.FIELD_CURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_CURRENTROW},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			// Colocation tab								
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_COLOCATION,
										new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			
			// Address tab
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_ADDRESS,
										new String[] {B_SSM_S01_FieldSet.FIELD_ADDRESSRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_ADDRESSDISPLAYROWCOUNT},
									    new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
		}
		
		// Address Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ADDRESS) {
			// Save result object			
			BeanDataCache.add(sessionID, String.valueOf(tabPosition), cloneResultObj);
			// Assign resultSet to other tabs
			// General tab
            assignAndSaveDataTabToCache(sessionID, 
                                        cloneResultObj, 
                                        tabPosition, 
                                        B_SSM_S01_FieldSet.TABPOS_GENERAL,
                                        new String[] {B_SSM_S01_FieldSet.FIELD_GENERALRESULTSET,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALTOTALROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALCURRENTROW,
                                                      B_SSM_S01_FieldSet.FIELD_GENERALDISPLAYROWCOUNT},
                                        new String[] {CLASSTYPE_OBJECT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT,
                                                      CLASSTYPE_INT});
			// ISP tab								
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_ISP,
										new String[] {B_SSM_S01_FieldSet.FIELD_RESULTSET,
													  B_SSM_S01_FieldSet.FIELD_STARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_TOTALROW,
													  B_SSM_S01_FieldSet.FIELD_CURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_DISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			// Colocation tab								
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_COLOCATION,
										new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_COLOCATIONDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
			
			// Email tab				
			assignAndSaveDataTabToCache(sessionID, 
										cloneResultObj, 
										tabPosition, 
										B_SSM_S01_FieldSet.TABPOS_EMAIL,
										new String[] {B_SSM_S01_FieldSet.FIELD_EMAILRESULTSET,
													  B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX,
													  B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW,
													  B_SSM_S01_FieldSet.FIELD_EMAILCURRENTROW,
													  B_SSM_S01_FieldSet.FIELD_EMAILDISPLAYROWCOUNT},
										new String[] {CLASSTYPE_OBJECT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT,
													  CLASSTYPE_INT});
		}		
	}
	
	/**
	 * Assign and save data of specific tab to cache
	 * @param sessionID
	 * @param resultObj
	 * @param tabPosition
	 * @param relTabPosition
	 * @param assignedFieldNames
	 */
	@SuppressWarnings("unchecked")
	private void assignAndSaveDataTabToCache(String sessionID,
											   HashMap<String, Object> resultObj,
											   int tabPosition,
											   int relTabPosition,
											   String[] assignedFieldNames,
											   String[] assignedFieldClasses) {
		// Save result
		BeanDataCache.add(sessionID, String.valueOf(tabPosition), resultObj);
		
		// Get previous result
		HashMap<String, Object> previousResultObj = null;
		if (BeanDataCache.get(sessionID, String.valueOf(relTabPosition)) != null) {
			previousResultObj = (HashMap<String, Object>) BeanDataCache.get(sessionID, String.valueOf(relTabPosition));
		}
		
		// Assign field value to resultObj
		if (previousResultObj != null) {
			if (assignedFieldNames != null && assignedFieldNames.length > 0) {
				for (int i = 0; i < assignedFieldNames.length; i++) {
					String assignedFieldName = assignedFieldNames[i];
					String assignedFieldClass = assignedFieldClasses[i];
					if (assignedFieldName != null && !assignedFieldName.trim().equals("")) {
						Object previousFieldValue = previousResultObj.get(assignedFieldName); 
						if (previousFieldValue != null) {
							// Type integer
							if (assignedFieldClass != null && assignedFieldClass.equals(CLASSTYPE_INT)) {
								resultObj.put(assignedFieldName, !BLogicUtils.emptyValue(previousFieldValue, "").trim().equals("")?
																 Integer.parseInt(previousFieldValue.toString()):
																 0);
							}	
							// Type string
							if (assignedFieldClass != null && assignedFieldClass.equals(CLASSTYPE_STRING)) {
								resultObj.put(assignedFieldName, BLogicUtils.emptyValue(previousFieldValue, ""));
							}
							// Type object
							if (assignedFieldClass != null && assignedFieldClass.equals(CLASSTYPE_OBJECT)) {
								resultObj.put(assignedFieldName, previousFieldValue);
							}
						}
					}
				}
			}
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
		int hasSearchStarted = 0;
		
		// Get 'process mode'
		try {			
			processMode = Integer.parseInt((String) dynaForm.get(B_SSM_S01_FieldSet.FIELD_PROCESSMODE));
		} catch (Exception ex) {
			processMode = 0;
		}
		
		// Get 'has search started'
		try {			
			hasSearchStarted = Integer.parseInt((String) dynaForm.get(B_SSM_S01_FieldSet.FIELD_HASSEARCHSTARTED));
		} catch (Exception ex) {
			hasSearchStarted = 0;
		}
		
		// Get sessionID 
		String sessionID = request.getSession().getId();
		
		// Clear cache
		if (hasSearchStarted == 0 && 
			processMode != B_SSM_S01_FieldSet.PROCESSMODE_VIEWPREVIOUSSTATE) {
			BeanDataCache.remove(sessionID);
		}
		
		// Set 'logon userId'
		HttpSession session = request.getSession();
		BillingSystemUserValueObject userObj = (BillingSystemUserValueObject) session.getAttribute(UserValueObject.USER_VALUE_OBJECT_KEY);
		if (userObj != null) {
			dynaForm.set(B_SSM_S01_FieldSet.FIELD_LOGONUSERID, userObj.getId_user());
		}
		
		// Set blogic
		((B_SSM_S01_Request_Process_Blogic)this.getBusinessLogic()).setSessionID(sessionID);
		
		if(processMode == B_SSM_S01_FieldSet.PROCESSMODE_INITIAL){
			dynaForm.set("coLocationSearchByDetails", "true");
			dynaForm.set("addressRegisteredType", "RA");
			dynaForm.set("addressBillingType", "BA");
			// #188 Start
			dynaForm.set("addressContactGeneralType", "GC");
			dynaForm.set("addressContactBillingType", "BC");
			// #188 End
			dynaForm.set("generalActiveServiceStatus", "PS3");
			dynaForm.set("activeServiceStatus", "PS3");
			dynaForm.set("coLocationActiveServiceStatus", "PS3");
			dynaForm.set("emailActiveServiceStatus", "PS3");
			dynaForm.set("addressActiveServiceStatus", "PS3");
		}
		
		// Process forward
		ActionForward actforward = null;
		try {
			actforward = super.doExecute(mapping, form, request, response);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		// Mode search or view
		if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_INITIAL ||
			processMode == B_SSM_S01_FieldSet.PROCESSMODE_SEARCH ||
			processMode == B_SSM_S01_FieldSet.PROCESSMODE_VIEWPREVIOUSSTATE ||
			processMode == B_SSM_S01_FieldSet.PROCESSMODE_PAGELIST) {
			return actforward;
		}
		
		// Mode export
		if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
			return null;
		}
		
		return null;
	}	
	
}
