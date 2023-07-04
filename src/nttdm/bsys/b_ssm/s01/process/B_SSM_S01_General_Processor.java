/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processing data from General Tab of B_SSM_S01
 * FILE NAME      : B_SSM_S01_ISP_Processor.java
 *
 * Copyright (C) 2012 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s01.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;

import nttdm.bsys.b_ssm.s01.blogic.B_SSM_S01_Utils;
import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author NTT Data Vietnam
 * Class processing data from General Tab of B_SSM_S01
 */
public class B_SSM_S01_General_Processor {
    
    // Private Properties
    private QueryDAO queryDAO = null; 

    /**
     * Initialize processor
     */
    public B_SSM_S01_General_Processor(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    
    /**
     * Handling input data from General tab
     * @param logicOutput
     * @param logicInput 
     * @param processMode 
     */
    public void handlingGeneralTab(HashMap<String, Object> logicOutput, 
                                 HashMap<String, Object> logicInput,
                                 int processMode) {
        List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String,Object>>();        
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(getQueryDAO());
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX).toString());
        } catch(Exception ex) {
            startIndex = 0;
        }
        // Build condition field for query
        buildConditionQueryFields(logicInput);
        // Query
        Integer totalRow = 0;     
        if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
            logicInput.put("isExportFlag", "0");
        }else {
            logicInput.put("startRowIndex", startIndex);
            logicInput.put("endRowIndex", startIndex+displayRowCount);
        }
        B_SSM_S01_Utils.trimInputMapValue(logicInput);
        totalRow = queryDAO.executeForObject("B_SSM_S01.getGeneralSearchResultSetCount", logicInput, Integer.class);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        resultSet = getQueryDAO().executeForObjectList("B_SSM_S01.getGeneralSearchResultSet", logicInput);
        // Process query data
        if (resultSet != null && resultSet.size() > 0) {            
            for (int i=0; i < resultSet.size(); i++) {
                HashMap<String, Object> result = resultSet.get(i);
                
                String custID = result.get(B_SSM_S01_FieldSet.FIELD_CUSTOMERID.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_CUSTOMERID.toUpperCase()).toString():null;
                    
                String subID = result.get(B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase()).toString():null;
                
                String service = result.get(B_SSM_S01_FieldSet.FIELD_SERVICE.toUpperCase()) != null?
                                                    result.get(B_SSM_S01_FieldSet.FIELD_SERVICE.toUpperCase()).toString():null;
                            
                if (custID != null && !custID.trim().equals("")) {
                    HashMap<String, Object> processedResult = null;
                    processedResult = new HashMap<String, Object>();
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_CUSTOMERID.toUpperCase(), custID);
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase(), subID);
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_SERVICE.toUpperCase(), service);
                    //CUSTOMER_TYPE
                    processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
                    // CustomerName
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_CUSTOMERNAME.toUpperCase(), 
                                        BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_CUSTOMERNAME.toUpperCase()), ""));
                    //PlanStatus
                    processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
                    // Plan Name
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_PLAN_NAME.toUpperCase(), 
                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_PLAN_NAME.toUpperCase()), ""));
                    processedResult.put("PLANNAME", BLogicUtils.emptyValue(result.get("PLANNAME"), ""));
                    // Customer plan id
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_CUSTOMERPLANID.toUpperCase(), 
                                        BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_CUSTOMERPLANID.toUpperCase()), ""));
                    
                    processedResultSet.add(processedResult);
                }
            }
        }        
        BLogicUtils.addRowNoToDataSet(startIndex+1, processedResultSet);
        // Add data to output    
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_GENERALRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_GENERALFULLRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_GENERALTOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_GENERALDISPLAYROWCOUNT, displayRowCount);                
    }

    /**
     * Build condition query fields
     * @param logicInput
     */
    private void buildConditionQueryFields(HashMap<String, Object> logicInput) {
        ////////////// Build service status query condition ////////////////////////
        List<String> serviceStatusArray = new ArrayList<String>();        
        String draftServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALDRAFTSERVICESTATUS);        
        String pendingApprovalServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALPENDINGSERVICESTATUS);
        String activeServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALACTIVESERVICESTATUS);     
        String cancelledServiceStatus    = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALCANCELLEDSERVICESTATUS);      
        String terminatedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALTERMINATEDSERVICESTATUS);     
        String rejectedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALREJECTEDSERVICESTATUS);          
        String suspendedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALSUSPENDEDSERVICESTATUS);
        // Add values to service status array
        if (!draftServiceStatus.trim().equals("")) {
            serviceStatusArray.add(draftServiceStatus);
        }
        if (!pendingApprovalServiceStatus.trim().equals("")) {
            serviceStatusArray.add(pendingApprovalServiceStatus);
        }
        if (!activeServiceStatus.trim().equals("")) {
            serviceStatusArray.add(activeServiceStatus);
        }
        if (!cancelledServiceStatus.trim().equals("")) {
            serviceStatusArray.add(cancelledServiceStatus);
        }
        if (!terminatedServiceStatus.trim().equals("")) {
            serviceStatusArray.add(terminatedServiceStatus);
        }
        if (!rejectedServiceStatus.trim().equals("")) {
            serviceStatusArray.add(rejectedServiceStatus);
        }
        if (!suspendedServiceStatus.trim().equals("")) {
            serviceStatusArray.add(suspendedServiceStatus);
        }
        // Add service status array to logicInput
        logicInput.put(B_SSM_S01_FieldSet.FIELD_SERVICESTATUSARRAY, serviceStatusArray);
    }

    /**
     * Set queryDAO
     * @param queryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * get queryDAO
     * @return queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

}
