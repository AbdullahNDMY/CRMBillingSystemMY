/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processing data from CoLocation Tab of B_SSM_S01
 * FILE NAME      : B_SSM_S01_CoLocation_Processor.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * History:
 *     2011/09/27: Fixed bug not displaying result with size = 1
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s01.process;

import java.math.BigDecimal;
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
 * Class processing data from CoLocation Tab of B_SSM_S01
 */
public class B_SSM_S01_CoLocation_Processor {
    // Constants
    public static final String DIGIT_PATTERN_REGEX_STR = "\\d+";    
    
    // Private Properties
    private QueryDAO queryDAO = null; 
    
    /**
     * Initialize processor
     */
    public B_SSM_S01_CoLocation_Processor(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    
    /**
     * Handling input data from colocation tab
     * @param logicOutput
     * @param logicInput 
     * @param processMode 
     */
    public void handlingCoLocationTab(HashMap<String, Object> logicOutput, 
                                          HashMap<String, Object> logicInput, 
                                          int processMode) {
        String searchByDetailsStr = BLogicUtils.emptyValue(logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSEARCHBYDETAILS), null);
        // Sum search
        if (searchByDetailsStr == null) {
            handlingCoLocationSumSearch(logicOutput, logicInput, processMode);
//            if(processMode == 0 || processMode == 3) { // mode search
//                handlingCoLocationSumSearch(logicOutput, logicInput, processMode);
//            } else if(processMode == 1){ // mode export
//                handlingCoLocationDetailSearch(logicOutput, logicInput, processMode);
//            }
        }
        // Detail search
        else {
            handlingCoLocationDetailSearch(logicOutput, logicInput, processMode);            
        }
    }
    /**
     * Handling detail search of colocation tab
     * @param logicOutput
     * @param logicInput
     * @param processMode 
     */
    @SuppressWarnings("unchecked")
    private void handlingCoLocationDetailSearch(HashMap<String, Object> logicOutput,
                                                  HashMap<String, Object> logicInput, 
                                                  int processMode) {
        List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String,Object>>();
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(queryDAO);
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX).toString());
        } catch(Exception ex) {
            startIndex = 0;
        }
        // Build condition field for query
        buildConditionQueryFields(logicInput);
        // Query
        Integer totalRow = 0; 
        String isExportFlag = "";
        if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
            logicInput.put("isExportFlag", "0");
            isExportFlag = "0";
        }else {
            logicInput.put("startRowIndex", startIndex);
            logicInput.put("endRowIndex", startIndex+displayRowCount);
        }
        String coLocationPowerCommitted = CommonUtils.toString(logicInput.get("coLocationPowerCommitted")).trim();
        if ( 2<coLocationPowerCommitted.length()) {
            //the end is KW,remove KW
            if("KW".equals(coLocationPowerCommitted.substring(coLocationPowerCommitted.length()-2, coLocationPowerCommitted.length()))) {
                logicInput.put("coLocationPowerCommitted",coLocationPowerCommitted.substring(0, coLocationPowerCommitted.length()-2));
            }
        }
        B_SSM_S01_Utils.trimInputMapValue(logicInput);
        totalRow = queryDAO.executeForObject("B_SSM_S01.getCoLocationDetailSearchResultSetCount", logicInput, Integer.class);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        resultSet = queryDAO.executeForObjectList("B_SSM_S01.getCoLocationDetailSearchResultSet",
                                                  logicInput);
        // Process query data
        if (resultSet != null && resultSet.size() > 0) {            
            for (int i=0; i < resultSet.size(); i++) {
                HashMap<String, Object> result = resultSet.get(i);
                String custID = result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID.toUpperCase())==null?
                                            null:result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID.toUpperCase()).toString();
                
                String subID = result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID.toUpperCase()).toString():null;
                                            
                String service = result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE.toUpperCase()).toString():null;
                                            
                if (custID != null && !custID.trim().equals("")) {
                    HashMap<String, Object> processedResult = null;
                    // Get processed result containing custID
//                    String[] conditionFieldNames = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                               (new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID, 
//                                                 B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID, 
//                                              B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE}): 
//                                
//                               (new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID, 
//                                                 B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID});          
//                               
//                    String[] conditionFieldValues = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                                       (new String[] {custID, subID, service}): 
//                                       (new String[] {custID, subID});
//                    
//                    if (BLogicUtils.isContainItemWith(processedResultSet, 
//                                                      conditionFieldNames,              
//                                                      conditionFieldValues)) {
//                            processedResult = BLogicUtils.getItemWith(processedResultSet, 
//                                                                      conditionFieldNames,              
//                                                                      conditionFieldValues);                         
//                    }
//                    // Add new item to processed resultSet
//                    else {
                        processedResult = new HashMap<String, Object>();
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID, custID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID, subID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE, service);
                        processedResultSet.add(processedResult);                        
//                    }
                    ////////////// Add value to processed result ///////////////////
//                    if (processedResult != null) {
//                        String rackNoStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO.toUpperCase()), "");
//                        List<String> rackNoFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO) == null?
//                                                    new ArrayList<String>() :
//                                                    (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO);    
                        //CUSTOMER_TYPE
                        processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
                        // CustomerName
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERNAME, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERNAME.toUpperCase()), ""));
                        // SubID
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID.toUpperCase()), ""));
                        // Service
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE.toUpperCase()), ""));
                        // Cust plan id
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERPLANID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERPLANID.toUpperCase()), ""));
                        //PlanStatus
                        processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
                        // Plan
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONPLAN, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONPLAN.toUpperCase()), ""));
                        processedResult.put("planName", BLogicUtils.emptyValue(result.get("PLANNAME"), ""));
                        // From service date
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONFROMSERVICEDATE,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONFROMSERVICEDATE.toUpperCase()), "") );
                        // To service date
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOSERVICEDATE, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOSERVICEDATE.toUpperCase()), ""));
                        // Bill instruction
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONBILLINGINSTRUCTION, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONBILLINGINSTRUCTION.toUpperCase()), ""));
                        // Term
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCONTRACTTERM, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCONTRACTTERM.toUpperCase()), ""));                            
                        
                        //start modify gplai 
                        Map<String, Object> sameDataListCon = new HashMap<String, Object>();
                        sameDataListCon.put("coLocationCustomerID", custID);
                        sameDataListCon.put("coLocationSubscriptionID", CommonUtils.toString(subID));
                        sameDataListCon.put("coLocationService", CommonUtils.toString(service));
                        sameDataListCon.put("isExportFlag", isExportFlag);
                        //get the data by cust_id,subscription_id, if is export type then add service
                        List<HashMap<String, Object>> tRackList = queryDAO.executeForObjectList("B_SSM_S01.getCoLocationT_RAC",sameDataListCon);
                        
                        // Equipment Location
//                        String equipmentLocationStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTLOCATION.toUpperCase()), "");
//                        List<String> equipmentLocationFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTLOCATION) == null?
//                                                               new ArrayList<String>() :
//                                                               (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTLOCATION);
//                        
//                        if (!rackNoStr.trim().equals("") && !BLogicUtils.isContainItem(rackNoFields, rackNoStr)) {
//                            equipmentLocationFields.add(equipmentLocationStr);
//                        }
                        
                        List<String> equipmentLocationFields = BLogicUtils.listObjectByNameTolistStr(tRackList, "EQUIP_LOCATION");
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTLOCATION, equipmentLocationFields);                                        
                        // Equipment Suite
//                        String equipmentSuiteStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTSUITE.toUpperCase()), "");                        
//                        List<String> equipmentSuiteFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTSUITE) == null?
//                                                            new ArrayList<String>() :
//                                                            (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTSUITE);                        
//                        if (!rackNoStr.trim().equals("") && !BLogicUtils.isContainItem(rackNoFields, rackNoStr)) {
//                            equipmentSuiteFields.add(equipmentSuiteStr);
//                        }    
                        
                        List<String> equipmentSuiteFields = BLogicUtils.listObjectByNameTolistStr(tRackList, "EQUIP_SUITE");
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTSUITE, equipmentSuiteFields);                                            
                        
                        // Total No Rack                                        
                        String totalRackNoStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALRACKNO.toUpperCase()), "");                            
                        if (!totalRackNoStr.trim().equals("")) {
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALRACKNO, totalRackNoStr);    
                        }    
                        // Power Committed
//                        String powerCommittedStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONPOWERCOMMITTED.toUpperCase()), "");
//                        List<String> powerCommittedFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONPOWERCOMMITTED) == null?
//                                                            new ArrayList<String>() :
//                                                            (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONPOWERCOMMITTED);
//                        
//                        if (!rackNoStr.trim().equals("") && !BLogicUtils.isContainItem(rackNoFields, rackNoStr)) {
//                            powerCommittedFields.add(powerCommittedStr);
//                        }    
                        
                        List<String> powerCommittedFields = BLogicUtils.listObjectByNameTolistStr(tRackList, "MAX_POWER");
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONPOWERCOMMITTED, powerCommittedFields);    
                        // Rack No                                                                
//                        if (!rackNoStr.trim().equals("") &&
//                            !BLogicUtils.isContainItem(rackNoFields, rackNoStr)) {
//                            rackNoFields.add(rackNoStr);
//                        }    
                        List<String> rackNoFields = BLogicUtils.listObjectByNameTolistStr(tRackList, "RACK_NO");
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO, rackNoFields);
//                    }
                }
            }
            
        }
        // Get "displayRowCount" quantity of record at start index postion 
//        List<HashMap<String, Object>> filterProcessedResultSet = new ArrayList<HashMap<String,Object>>();
//        if (processedResultSet != null && 
//            processedResultSet.size() > 0 &&
//            startIndex < processedResultSet.size()) {
//            filterProcessedResultSet = BLogicUtils.get(processedResultSet, startIndex, displayRowCount);
//            totalRow = processedResultSet.size(); 
//        }
        // Add data to output    
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONFULLRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONDISPLAYROWCOUNT, displayRowCount);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX, startIndex);
    }
    
    /**
     * Handling sum search of colocation tab
     * @param logicOutput
     * @param logicInput
     */
    private void handlingCoLocationSumSearch(HashMap<String, Object> logicOutput,
                                              HashMap<String, Object> logicInput,
                                               int processMode) {
        List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String,Object>>();
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(queryDAO);
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX).toString());
        } catch(Exception ex) {
            startIndex = 0;
        }
        // Build condition field for query
        buildConditionQueryFields(logicInput);
        // Query
        Integer totalRow = 0; 
        //is not means export
        String isExportFlag = "";
        if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
            isExportFlag = "0";
            logicInput.put("isExportFlag", "0");
        }else {
            logicInput.put("startRowIndex", startIndex);
            logicInput.put("endRowIndex", startIndex+displayRowCount);
        }
        String coLocationPowerCommitted = CommonUtils.toString(logicInput.get("coLocationPowerCommitted")).trim();
        if ( 2<coLocationPowerCommitted.length()) {
            //the end is KW,remove KW
            if("KW".equals(coLocationPowerCommitted.substring(coLocationPowerCommitted.length()-2, coLocationPowerCommitted.length()))) {
                logicInput.put("coLocationPowerCommitted",coLocationPowerCommitted.substring(0, coLocationPowerCommitted.length()-2));
            }
        }
        B_SSM_S01_Utils.trimInputMapValue(logicInput);
        totalRow = queryDAO.executeForObject("B_SSM_S01.getCoLocationSumSearchResultSetCount", logicInput, Integer.class);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        resultSet = queryDAO.executeForObjectList("B_SSM_S01.getCoLocationSumSearchResultSet",
                                                  logicInput);
        
        // Process query data
        if (resultSet != null && resultSet.size() > 0) {            
            for (int i=0; i < resultSet.size(); i++) {
                HashMap<String, Object> result = resultSet.get(i);
                String custID = result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID.toUpperCase()).toString():null;
                        
                String subID = result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID.toUpperCase()).toString():null;
                        
                String service = result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE.toUpperCase()).toString():null;
                                
                if (custID != null && !custID.trim().equals("")) {
                    HashMap<String, Object> processedResult = null;
                    // Get processed result containing custID
//                    String[] conditionFieldNames = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                               (new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID, 
//                                                 B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID, 
//                                              B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE}): 
//                                
//                               (new String[] {B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID, 
//                                                 B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID});          
//                               
//                    String[] conditionFieldValues = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                                       (new String[] {custID, subID, service}): 
//                                       (new String[] {custID, subID});
//                    
//                    if (BLogicUtils.isContainItemWith(processedResultSet, 
//                                                      conditionFieldNames,              
//                                                      conditionFieldValues)) {
//                            processedResult = BLogicUtils.getItemWith(processedResultSet, 
//                                                                      conditionFieldNames,              
//                                                                      conditionFieldValues);                         
//                    }
//                    // Add new item to processed resultSet
//                    else {
                        processedResult = new HashMap<String, Object>();
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERID, custID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID, subID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE, service);
                        processedResultSet.add(processedResult);                        
//                    }
                    
                    ////////////// Add value to processed result ///////////////////
//                    if (processedResult != null) {    
                        //CUSTOMER_TYPE
                        processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
                        // CustomerName
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERNAME, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERNAME.toUpperCase()), ""));
                        // SubID
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUBSCRIPTIONID.toUpperCase()), ""));
                        // Service
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICE.toUpperCase()), ""));
                        // Cust plan id
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERPLANID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCUSTOMERPLANID.toUpperCase()), ""));
                        //PlanStatus
                        processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
                        // Plan
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONPLAN, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONPLAN.toUpperCase()), ""));
                        processedResult.put("planName", BLogicUtils.emptyValue(result.get("PLANNAME"), ""));
                        // From service date
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONFROMSERVICEDATE,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONFROMSERVICEDATE.toUpperCase()), "") );
                        // To service date
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOSERVICEDATE, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOSERVICEDATE.toUpperCase()), ""));
                        // Bill instruction
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONBILLINGINSTRUCTION, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONBILLINGINSTRUCTION.toUpperCase()), ""));
                        // Term
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONCONTRACTTERM, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCONTRACTTERM.toUpperCase()), ""));                                                
                        
                        //start add gplai 
                        Map<String, Object> sameDataListCon = new HashMap<String, Object>();
                        sameDataListCon.put("coLocationCustomerID", custID);
                        sameDataListCon.put("coLocationSubscriptionID", CommonUtils.toString(subID));
                        sameDataListCon.put("coLocationService", CommonUtils.toString(service));
                        sameDataListCon.put("isExportFlag", isExportFlag);
                        //get the data by cust_id,subscription_id, if is export type then add service
                        List<HashMap<String, Object>> sameDataList = queryDAO.executeForObjectList("B_SSM_S01.getCoLocationT_RAC",sameDataListCon);
                        //list RackNo to line String
                        String rackNo = BLogicUtils.listFirstAndLastProToStr(sameDataList, "RACK_NO");
                        String location = BLogicUtils.listFirstAndLastProToStr(sameDataList, "EQUIP_LOCATION");
                        String suite = BLogicUtils.listFirstAndLastProToStr(sameDataList, "EQUIP_SUITE");
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO, rackNo);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTLOCATION, location);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONEQUIPMENTSUITE, suite);
                        //end add gplai
                        
                        // Rack No    
//                        String rackNo = BLogicUtils.emptyValue(processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTRACKNO), "");
//                        if (rackNo.equals("")) {
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTRACKNO, 
//                                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO.toUpperCase()), ""));
//                            
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO, 
//                                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO.toUpperCase()), ""));
//                        } else {
//                            String endRackNo = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO.toUpperCase()), "");
//                            String sumRackNo = BLogicUtils.emptyValue(processedResult.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO), "");
//                            if (!endRackNo.equals("") && !endRackNo.equals(rackNo)) {
//                                sumRackNo = rackNo +  "-"  + endRackNo;
//                            }
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONRACKNO, sumRackNo);                            
//                        }                
                        // Total No Rack                                            
                        String totalRackNoStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALRACKNO.toUpperCase()), "");                            
                        if (!totalRackNoStr.trim().equals("")) {
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALRACKNO, totalRackNoStr);    
                        }                
                        BigDecimal powerCommitted = new BigDecimal("0");
                        if (sameDataList!=null && 0 < sameDataList.size()) {
                            for(HashMap<String, Object> map : sameDataList) {
                                BigDecimal onePowerCommitted = new BigDecimal("0");
                                try{
                                    onePowerCommitted = new BigDecimal(CommonUtils.toString(map.get("MAX_POWER")));
                                } catch (Exception e) {
                                    onePowerCommitted = new BigDecimal("0");
                                }
                                powerCommitted = powerCommitted.add(onePowerCommitted);
                            }
                        }
                        // Power Committed
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONPOWERCOMMITTED, powerCommitted.toString());
//                    }
                }
            }
        }
        
        // Get "displayRowCount" quantity of record at start index postion 
//        List<HashMap<String, Object>> filterProcessedResultSet = new ArrayList<HashMap<String,Object>>();
//        if (processedResultSet != null && 
//            processedResultSet.size() > 0 &&
//            startIndex <= processedResultSet.size() - 1) {
//            filterProcessedResultSet = BLogicUtils.get(processedResultSet, startIndex, displayRowCount);
//            totalRow = processedResultSet.size(); 
//        }
        
        // Add data to output    
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONFULLRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONDISPLAYROWCOUNT, displayRowCount);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX, startIndex);
    }
    
    /**
     * Build condition query fields
     * @param logicInput
     */
    private void buildConditionQueryFields(HashMap<String, Object> logicInput) {
        //////////////Build service status query condition ////////////////////////
        List<String> serviceStatusArray = new ArrayList<String>();        
        String draftServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONDRAFTSERVICESTATUS);        
        String pendingApprovalServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONPENDINGSERVICESTATUS);         
        String activeServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONACTIVESERVICESTATUS);     
        String cancelledServiceStatus    = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONCANCELLEDSERVICESTATUS);      
        String terminatedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONTERMINATEDSERVICESTATUS);     
        String rejectedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONREJECTEDSERVICESTATUS);          
        String suspendedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSUSPENDEDSERVICESTATUS);
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
        logicInput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSERVICESTATUSARRAY, serviceStatusArray);
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
