/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processing data from ISP Tab of B_SSM_S01
 * FILE NAME      : B_SSM_S01_ISP_Processor.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
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
 * Class processing data from ISP Tab of B_SSM_S01
 */
public class B_SSM_S01_ISP_Processor {
    
    // Private Properties
    private QueryDAO queryDAO = null; 

    /**
     * Initialize processor
     */
    public B_SSM_S01_ISP_Processor(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    
    /**
     * Handling input data from isp tab
     * @param logicOutput
     * @param logicInput 
     * @param processMode 
     */
    public void handlingISPTab(HashMap<String, Object> logicOutput, 
                                 HashMap<String, Object> logicInput,
                                 int processMode) {
        List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String,Object>>();        
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(getQueryDAO());
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_STARTINDEX).toString());
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
        totalRow = queryDAO.executeForObject("B_SSM_S01.getISPSearchResultSetCount", logicInput, Integer.class);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        resultSet = getQueryDAO().executeForObjectList("B_SSM_S01.getISPSearchResultSet",
                                                         logicInput);
        
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
                    // Get processed result containing custID
//                    String[] conditionFieldNames = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                               (new String[] {B_SSM_S01_FieldSet.FIELD_CUSTOMERID.toUpperCase(), 
//                                                 B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase(), 
//                                              B_SSM_S01_FieldSet.FIELD_SERVICE.toUpperCase()}): 
//                                (new String[] {B_SSM_S01_FieldSet.FIELD_CUSTOMERID.toUpperCase(), 
//                                                 B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase()});          
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
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_CUSTOMERID.toUpperCase(), custID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase(), subID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_SERVICE.toUpperCase(), service);
                        processedResultSet.add(processedResult);                        
//                    }
                    
                    ////////////// Add value to processed result ///////////////////
//                    if (processedResult != null) {    
                        
                        Map<String, Object> paramCondition = new HashMap<String, Object>();
                        paramCondition.put("subscriptionID", CommonUtils.toString(subID).trim());
                        paramCondition.put("custID", CommonUtils.toString(custID).trim());
                        paramCondition.put("idSupplier", CommonUtils.toString(result.get("CARRIER1")).trim());
                        
                        //CUSTOMER_TYPE
                        processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
                        // CustomerName
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_CUSTOMERNAME.toUpperCase(), 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_CUSTOMERNAME.toUpperCase()), ""));
                        // SubID
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase(), 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_SUBSCRIPTIONID.toUpperCase()), ""));
                        // Service
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_SERVICE.toUpperCase(), 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_SERVICE.toUpperCase()), ""));
                        //PlanStatus
                        processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
                        // Plan Name
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_PLAN_NAME.toUpperCase(), 
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_PLAN_NAME.toUpperCase()), ""));
                        processedResult.put("PLANNAME", BLogicUtils.emptyValue(result.get("PLANNAME"), ""));
                        // Customer plan id
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_CUSTOMERPLANID.toUpperCase(), 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_CUSTOMERPLANID.toUpperCase()), ""));
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_ACCESS_ACCOUNT.toUpperCase(), 
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ACCESS_ACCOUNT.toUpperCase()), ""));
                        //T_INST_ADR Installation Address 1
                        List<Map<String, Object>> mapT_INST_ADR1List = queryDAO.executeForMapList("B_SSM_S01.getISPSearchResultSetT_INST_ADR1", paramCondition);
                        
                        String a1Addr1 = "";
                        String a1Addr2 = "";
                        String a1Addr3 = "";
                        String a1ZipCode = "";
                        String a1CountryName = "";
                        String a2Addr1 = "";
                        String a2Addr2 = "";
                        String a2Addr3 = "";
                        String a2ZipCode = "";
                        String a2CountryName = "";
                        
                        if(mapT_INST_ADR1List!=null && 0<mapT_INST_ADR1List.size()) {
                            for(Map<String, Object> mapT_INST_ADR: mapT_INST_ADR1List) {
                                String addrType = CommonUtils.toString(mapT_INST_ADR.get("ADR_TYPE"));
                                String instAddr1 = CommonUtils.toString(mapT_INST_ADR.get("ADR_LINE1"));
                                String instAddr2 = CommonUtils.toString(mapT_INST_ADR.get("ADR_LINE2"));
                                String instAddr3 = CommonUtils.toString(mapT_INST_ADR.get("ADR_LINE3"));
                                String instZipCode = CommonUtils.toString(mapT_INST_ADR.get("ZIP_CODE"));
                                String instCountryName = CommonUtils.toString(mapT_INST_ADR.get("COUNTRYNAME"));
                                if("A1".equals(addrType)) {
                                    a1Addr1 = instAddr1;
                                    a1Addr2 = instAddr2;
                                    a1Addr3 = instAddr3;
                                    a1ZipCode = instZipCode;
                                    a1CountryName = instCountryName;
                                } else if("A2".equals(addrType)) {
                                    a2Addr1 = instAddr1;
                                    a2Addr2 = instAddr2;
                                    a2Addr3 = instAddr3;
                                    a2ZipCode = instZipCode;
                                    a2CountryName = instCountryName;
                                }
                            }
                        }
                        
                        StringBuffer installationAddress1 = new StringBuffer();
                        StringBuffer installationAddress2 = new StringBuffer();
                        
                        if(!CommonUtils.isEmpty(a1Addr1)) {
                            installationAddress1.append(a1Addr1);
                        }
                        if(!CommonUtils.isEmpty(a1Addr2)) {
                            installationAddress1.append(", ").append(a1Addr2);
                        }
                        if(!CommonUtils.isEmpty(a1Addr3)) {
                            installationAddress1.append(", ").append(a1Addr3);
                        }
                        if(!CommonUtils.isEmpty(a1CountryName)) {
                            installationAddress1.append(", ").append(a1CountryName);
                        }
                        if(!CommonUtils.isEmpty(a1ZipCode)) {
                            installationAddress1.append(", ").append(a1ZipCode);
                        }
                        
                        if(!CommonUtils.isEmpty(a2Addr1)) {
                            installationAddress2.append(a2Addr1);
                        }
                        if(!CommonUtils.isEmpty(a2Addr2)) {
                            installationAddress2.append(", ").append(a2Addr2);
                        }
                        if(!CommonUtils.isEmpty(a2Addr3)) {
                            installationAddress2.append(", ").append(a2Addr3);
                        }
                        if(!CommonUtils.isEmpty(a2CountryName)) {
                            installationAddress2.append(", ").append(a2CountryName);
                        }
                        if(!CommonUtils.isEmpty(a2ZipCode)) {
                            installationAddress2.append(", ").append(a2ZipCode);
                        }
                        
                        // Installation Address  1
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_INSTALLATIONADDRESS1.toUpperCase(), installationAddress1.toString());
                        // Installation Address  2
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_INSTALLATIONADDRESS2.toUpperCase(), installationAddress2.toString());
                        
                        // ADSL_DEL No  
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_ADSL_DELNO.toUpperCase(), 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADSL_DELNO.toUpperCase()), ""));
                        // Circuit ID
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_CIRCUITID.toUpperCase(),
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_CIRCUITID.toUpperCase()), "") );
                        //M_SUPPLIER_H Carrier
                        Map<String, Object> mapM_SUPPLIER_H = queryDAO.executeForMap("B_SSM_S01.getISPSearchResultSetM_SUPPLIER_H", paramCondition);
                        if (mapM_SUPPLIER_H != null) {
                            // Carrier
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_CARRIER.toUpperCase(), 
                                                BLogicUtils.emptyValue(mapM_SUPPLIER_H.get(B_SSM_S01_FieldSet.FIELD_CARRIER.toUpperCase()), ""));
                        } else {
                            // Carrier
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_CARRIER.toUpperCase(), "");
                        }
                        
                        //Modem No.
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_MODEMNO.toUpperCase(), 
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_MODEMNO.toUpperCase()), ""));
                        
                        // Router No. 
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_ROUTERNO.toUpperCase(),
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ROUTERNO.toUpperCase()), "") );
                        // Contract Start Date
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_SERVICE_START.toUpperCase(),
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_SERVICE_START.toUpperCase()), "") );
                        //Contract End Date
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_SERVICE_END.toUpperCase(),
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_SERVICE_END.toUpperCase()), "") );
                        // Billing Instruction
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_BILLING_INSTRUCTION.toUpperCase(),
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_BILLING_INSTRUCTION.toUpperCase()), "") );
                        // Terms
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_CONTRACT_TERMS.toUpperCase(),
                                BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_CONTRACT_TERMS.toUpperCase()), "") );
                        
                        //get Contact info
                        List<HashMap<String, Object>> listContact = queryDAO.executeForObjectList("B_SSM_S01.getISPSearchResultSetContactInfo",
                                paramCondition);
                        if (listContact != null && 0 < listContact.size()) {
                            for (Map<String, Object> objectContact : listContact) {
                                String contactType = CommonUtils.toString(objectContact.get("CONTACT_TYPE")).trim();
                                if ("C1".equals(contactType)) {
                                    // IT Contact Name 1
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_NAME1, CommonUtils.toString(objectContact.get("NAME")));
                                    // Designation 1
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATION1, CommonUtils.toString(objectContact.get("DESIGNATION")));
                                    // Tel.No 1
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, CommonUtils.toString(objectContact.get("TEL_NO")));
                                    // Fax No 1
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNO1, CommonUtils.toString(objectContact.get("FAX_NO")));
                                } else if ("C2".equals(contactType)) {
                                    // IT Contact Name 2
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_NAME2, CommonUtils.toString(objectContact.get("NAME")));
                                    // Designation 2
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATION2, CommonUtils.toString(objectContact.get("DESIGNATION")));
                                    // Tel.No 2
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, CommonUtils.toString(objectContact.get("TEL_NO")));
                                    // Fax No 2
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNO2, CommonUtils.toString(objectContact.get("FAX_NO")));
                                } else {
                                    //C3
                                    // IT Contact Name 3
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_NAME3, CommonUtils.toString(objectContact.get("NAME")));
                                    // Designation 3
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATION3, CommonUtils.toString(objectContact.get("DESIGNATION")));
                                    // Tel.No 3
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, CommonUtils.toString(objectContact.get("TEL_NO")));
                                    // Fax No 3
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNO3, CommonUtils.toString(objectContact.get("FAX_NO")));
                                }
                            }
                        } else {
                            // IT Contact Name 1
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_NAME1, "");
                            // Designation 1
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATION1, "");
                            // Tel.No 1
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, "");
                            // Fax No 1
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNO1, "");
                            
                            // IT Contact Name 2
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_NAME2, "");
                            // Designation 2
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATION2, "");
                            // Tel.No 2
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, "");
                            // Fax No 2
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNO2, "");
                            
                            // IT Contact Name 3
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_NAME3, "");
                            // Designation 3
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATION3, "");
                            // Tel.No 3
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, "");
                            // Fax No 3
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNO3, "");
                        }
                        
                        //M_CUST_CTC info
                        Map<String, Object> mapCustCtc = queryDAO.executeForMap("B_SSM_S01.getISPSearchResultSetCustCtcInfo", paramCondition);
                        if (mapCustCtc != null) {
                            // Contact Name
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_NAMETC,
                                    BLogicUtils.emptyValue(mapCustCtc.get(B_SSM_S01_FieldSet.FIELD_NAMETC.toUpperCase()), "") );
                            // Designation
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATIONTC,
                                    BLogicUtils.emptyValue(mapCustCtc.get(B_SSM_S01_FieldSet.FIELD_DESIGNATIONTC.toUpperCase()), "") );
                            // Tel.No
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNOTC,
                                    BLogicUtils.emptyValue(mapCustCtc.get(B_SSM_S01_FieldSet.FIELD_TELNOTC.toUpperCase()), "") );
                            // Fax No.
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNOTC,
                                    BLogicUtils.emptyValue(mapCustCtc.get(B_SSM_S01_FieldSet.FIELD_FAXNOTC.toUpperCase()), "") );
                            // Email
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAIL.toUpperCase(),
                                    BLogicUtils.emptyValue(mapCustCtc.get(B_SSM_S01_FieldSet.FIELD_EMAIL.toUpperCase()), "") );
                            // Mobile No.
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MOBILENO.toUpperCase(),
                                    BLogicUtils.emptyValue(mapCustCtc.get(B_SSM_S01_FieldSet.FIELD_MOBILENO.toUpperCase()), "") );
                        } else {
                            // Contact Name
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_NAMETC, "");
                            // Designation
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_DESIGNATIONTC, "");
                            // Tel.No
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNOTC, "");
                            // Fax No.
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_FAXNOTC, "");
                            // Email
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAIL.toUpperCase(), "");
                            // Mobile No.
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MOBILENO.toUpperCase(), "");
                        }

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
        BLogicUtils.addRowNoToDataSet(startIndex+1, processedResultSet);
        // Add data to output    
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_RESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_FULLRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_STARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_TOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_DISPLAYROWCOUNT, displayRowCount);                
    }

    /**
     * Build condition query fields
     * @param logicInput
     */
    private void buildConditionQueryFields(HashMap<String, Object> logicInput) {
        ////////////// Build service status query condition ////////////////////////
        List<String> serviceStatusArray = new ArrayList<String>();        
        String draftServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_DRAFTSERVICESTATUS);        
        String pendingApprovalServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_PENDINGAPPROVALSERVICESTATUS);         
        String activeServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ACTIVESERVICESTATUS);     
        String cancelledServiceStatus    = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_CANCELLEDSERVICESTATUS);      
        String terminatedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_TERMINATEDSERVICESTATUS);     
        String rejectedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_REJECTEDSERVICESTATUS);          
        String suspendedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_SUSPENDEDSERVICESTATUS);
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
