/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processing data from Email Tab of B_SSM_S01
 * FILE NAME      : B_SSM_S01_Email_Processor.java
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
 * Class processing data from Email Tab of B_SSM_S01
 */
public class B_SSM_S01_Email_Processor {

    // Private Properties
    private QueryDAO queryDAO = null; 

    /**
     * Initialize processor
     */
    public B_SSM_S01_Email_Processor(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    
    /**
     * Handling input data from email tab
     * @param logicOutput
     * @param logicInput 
     * @param processMode 
     */
    public void handlingEmailTab(HashMap<String, Object> logicOutput, 
                                    HashMap<String, Object> logicInput, 
                                    int processMode) {
        String emailInfoModeStr = BLogicUtils.emptyValue(logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILINFOMODE),null);
        if (emailInfoModeStr == null) {
            return;
        }
        // Build condition field for query
        buildConditionQueryFields(logicInput);
        // Subscribed mode
        if (emailInfoModeStr.trim().equals(B_SSM_S01_FieldSet.EMAIL_SUBSCRIBEDINFOMODE_VALUE)) {
            handlingEmailSubscribedSearch(logicOutput, logicInput, processMode);
        }
        // Domain Name mode
        if (emailInfoModeStr.trim().equals(B_SSM_S01_FieldSet.EMAIL_DOMAINNAMEINFOMODE_VALUE)) {
            handlingTeamworkSearch(logicOutput, logicInput, processMode);
        }
        // IT Contact mode
        if (emailInfoModeStr.trim().equals(B_SSM_S01_FieldSet.EMAIL_ITCONTACTINFOMODE_VALUE)) {
            handlingEmailITContactSearch(logicOutput, logicInput, processMode);
        }
    }
    
    /**
     * Build condition query fields
     * @param logicInput
     */
    private void buildConditionQueryFields(HashMap<String, Object> logicInput) {
        ////////////// Build service status query condition ////////////////////////
        List<String> serviceStatusArray = new ArrayList<String>();        
        String draftServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILDRAFTSERVICESTATUS);        
        String pendingApprovalServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILPENDINGSERVICESTATUS);         
        String activeServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILACTIVESERVICESTATUS);     
        String cancelledServiceStatus    = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILCANCELLEDSERVICESTATUS);      
        String terminatedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILTERMINATEDSERVICESTATUS);     
        String rejectedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILREJECTEDSERVICESTATUS);          
        String suspendedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILSUSPENDEDSERVICESTATUS);
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
        logicInput.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICESTATUSARRAY, serviceStatusArray);
    }

    /**
     * Handling searching on email domain name mode 
     * @param logicOutput
     * @param logicInput
     * @param processMode 
     */
    private void handlingTeamworkSearch(HashMap<String, Object> logicOutput,
                                                HashMap<String, Object> logicInput, 
                                                int processMode) {
        List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String,Object>>();
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(queryDAO);
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX).toString());
        } catch(Exception ex) {
            startIndex = 0;
        }
        Integer totalRow = 0; 
        String isExportFlag = "";
        if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
            logicInput.put("isExportFlag", "0");
            isExportFlag = "0";
        }else {
            logicInput.put("startRowIndex", startIndex);
            logicInput.put("endRowIndex", startIndex+displayRowCount);
        }
        B_SSM_S01_Utils.trimInputMapValue(logicInput);
        totalRow = queryDAO.executeForObject("B_SSM_S01.getEmailDomainNameSearchResultSetCount", logicInput, Integer.class);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        resultSet = queryDAO.executeForObjectList("B_SSM_S01.getEmailDomainNameSearchResultSet",
                                                  logicInput);
        
        // Process query data
        if (resultSet != null && resultSet.size() > 0) {            
            for (int i=0; i < resultSet.size(); i++) {
                HashMap<String, Object> result = resultSet.get(i);
                
                String custID = result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID.toUpperCase().toUpperCase())==null?
                                            null:result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID.toUpperCase().toUpperCase()).toString();
                
                String subID = result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()).toString():null;
                                            
                String service = result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE.toUpperCase()).toString():null;
                
                if (custID != null && !custID.trim().equals("")) {
                    HashMap<String, Object> processedResult = resultSet.get(i);
                    
//                    String[] conditionFieldNames = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                               (new String[] {B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, 
//                                                 B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, 
//                                              B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME}): 
//                                (new String[] {B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, 
//                                                 B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID});          
//                               
//                    String[] conditionFieldValues = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                                       (new String[] {custID, subID, service}): 
//                                       (new String[] {custID, subID});
//                    
//                    if (BLogicUtils.isContainItemWith(processedResultSet, 
//                                                      conditionFieldNames,              
//                                                      conditionFieldValues)) {
//                        
//                            processedResult = BLogicUtils.getItemWith(processedResultSet, 
//                                                                        conditionFieldNames,              
//                                                                        conditionFieldValues);
//                            
//                    }
//                    // Add new item to processed resultSet
//                    else {
                        processedResult = new HashMap<String, Object>();
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, custID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, subID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE, service);
                        processedResultSet.add(processedResult);                        
//                    }
                    
                    ////////////// Add value to processed result ///////////////////
//                    if (processedResult != null) {        
                        
                        //start modify gplai 
                        Map<String, Object> sameDataListCon = new HashMap<String, Object>();
                        sameDataListCon.put("emailCustomerID", custID);
                        sameDataListCon.put("emailSubscriptionID", CommonUtils.toString(subID));
                        sameDataListCon.put("emailService", CommonUtils.toString(service));
                        sameDataListCon.put("isExportFlag", isExportFlag);
                        
                        //CUSTOMER_TYPE
                        processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
                        // CustomerName
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERNAME, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERNAME.toUpperCase()), ""));
                        // Add subID
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()), ""));
                        // Service
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE.toUpperCase()), ""));                    
                        //PlanStatus
                        processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
                        // Customer Tel
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERTELEPHONE,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERTELEPHONE.toUpperCase()), ""));
                        
                        // Cust plan id
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID.toUpperCase()), ""));
                        // Category
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCATEGORY, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCATEGORY.toUpperCase()), ""));                    
                        // Category name
                        processedResult.put("emailCategoryName", BLogicUtils.emptyValue(result.get("EMAILCATEGORYNAME"), ""));
                        // Service
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME.toUpperCase()), ""));
                        // Email plan
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILPLAN, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILPLAN.toUpperCase()), ""));
                        processedResult.put("planName", BLogicUtils.emptyValue(result.get("PLANNAME"), ""));
                        // Service status
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICESTATUS, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICESTATUS.toUpperCase()), ""));
                        // Email address
                        /*processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS.toUpperCase()),""));*/
                        // IT Contact Email
//                        String emailITContactStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS.toUpperCase()), "");
//                        List<String> emailITContactFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS) == null?
//                                                            new ArrayList<String>() :
//                                                            (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS);
//                        
//                        if (!emailITContactStr.trim().equals("") &&
//                            !BLogicUtils.isContainItem(emailITContactFields, emailITContactStr)) {
//                            emailITContactFields.add(emailITContactStr);
//                        }
                        
                        //Customer email Address
                        List<HashMap<String, Object>> emailCustomerAddrList = queryDAO.executeForObjectList("B_SSM_S01.getEmailDomainNameEmailAddress",sameDataListCon);
                        List<String> emailITContactFields = BLogicUtils.removeDulpicateToListStr(emailCustomerAddrList, 
                                B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS.toUpperCase());
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS, emailITContactFields);
                        
                        // IT Contact No                                                                
//                        String emailITNoStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILITCONTACTNO.toUpperCase()), "");
//                        List<String> emailITContactNoFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_EMAILITCONTACTNO) == null?
//                                                              new ArrayList<String>() :
//                                                              (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_EMAILITCONTACTNO);
//                        
//                        if (!emailITNoStr.trim().equals("") &&
//                            !BLogicUtils.isContainItem(emailITContactNoFields, emailITNoStr)) {
//                            emailITContactNoFields.add(emailITNoStr);
//                        }
                        
                        //T_IT_CONTACT_H and T_IT_CONTACT_D info
//                        List<HashMap<String, Object>> listEmailAndTelNo = queryDAO.executeForObjectList("B_SSM_S01.getEmailT_IT_CONTACT_H_D",sameDataListCon);
//                        if (listEmailAndTelNo != null && 0 < listEmailAndTelNo.size()) {
//                            for (Map<String, Object> emailAndTelNo : listEmailAndTelNo) {
//                                String contactType = CommonUtils.toString(emailAndTelNo.get("CONTACT_TYPE")).trim();
//                                if ("C1".equals(contactType)) {
//                                    // Email1
//                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL1, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
//                                    // Tell no1
//                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
//                                } else if ("C2".equals(contactType)) {
//                                    // Email2
//                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL2, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
//                                    // Tell no2
//                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
//                                } else {
//                                    //C3
//                                    // Email3
//                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL3, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
//                                    // Tell no3
//                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
//                                }
//                            }
//                        } else {
//                            // Email1
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL1, "");
//                            // Tell no1
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, "");  
//                            // Email2
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL2, "");
//                            // Tell no2
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, "");   
//                            // Email3
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL3, "");
//                            // Tell no3
//                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, "");  
//                        }

                        
                        //FTP URL
                        List<HashMap<String, Object>> ftpUrlList = queryDAO.executeForObjectList("B_SSM_S01.getEmailDomainNameFtpUrl",sameDataListCon);
                        List<String> emailFTP_URLFields = BLogicUtils.removeDulpicateToListStr(ftpUrlList, 
                                B_SSM_S01_FieldSet.FIELD_EMAILFTP_URL.toUpperCase());
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILFTP_URL, emailFTP_URLFields);
                        
                        
                        //FTP URL
                        List<HashMap<String, Object>> domainNameList = queryDAO.executeForObjectList("B_SSM_S01.getEmailDomainNameDomainName",sameDataListCon);
                        List<String> emailDNSDomainNameFields = BLogicUtils.removeDulpicateToListStr(domainNameList, 
                                B_SSM_S01_FieldSet.FIELD_EMAILDNSDOMAINNAME.toUpperCase());
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILDNSDOMAINNAME, emailDNSDomainNameFields);
                        
                        // Mail Address
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILDOMAINNAME,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILDOMAINNAME.toUpperCase()), 
                                                                   ""));
                        
                        // Email Teamwork Domain Address                                                
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKDOMAINNAMEADDRESS,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKDOMAINNAMEADDRESS.toUpperCase()), ""));
                        
                        // Email Teamwork URL
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKURL,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKURL.toUpperCase()),  ""));
                        // Email Teamwork Admin ID
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKADMINID,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKADMINID.toUpperCase()),  ""));
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
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILFULLRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILDISPLAYROWCOUNT, displayRowCount);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX, startIndex);
    }

    /**
     * Handling searching on email subscribed mode 
     * @param logicOutput
     * @param logicInput
     * @param processMode 
     */
    private void handlingEmailSubscribedSearch(HashMap<String, Object> logicOutput,
                                                HashMap<String, Object> logicInput, 
                                                int processMode) {
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(queryDAO);
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX).toString());
        } catch(Exception ex) {
            startIndex = 0;
        }
        Integer totalRow = 0; 
        String isExportFlag = "";
        if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
            logicInput.put("isExportFlag", "0");
            isExportFlag = "0";
        }else {
            logicInput.put("startRowIndex", startIndex);
            logicInput.put("endRowIndex", startIndex+displayRowCount);
        }
        
        String detetedEmail = CommonUtils.toString(logicInput.get("detetedEmail")).trim();
        
        B_SSM_S01_Utils.trimInputMapValue(logicInput);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        totalRow = queryDAO.executeForObject("B_SSM_S01.getEmailSubscribedSearchResultSetCount", logicInput, Integer.class);
        List<String> subIDList = null;
        if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
            subIDList = queryDAO.executeForObjectList("B_SSM_S01.getEmailSubscribedIDList", logicInput);
        } else {
            subIDList = queryDAO.executeForObjectList("B_SSM_S01.getEmailSubscribedIDList", logicInput, startIndex, displayRowCount);
        }
        
        if(subIDList!=null && 0 < subIDList.size()) {
            List<List<String>> createInConditionList = CommonUtils.createInCondition(subIDList);
            if(createInConditionList!=null && 0<createInConditionList.size()) {
                for(List<String> emailSubscriptionIDList : createInConditionList) {
                    if(emailSubscriptionIDList!=null && 0<emailSubscriptionIDList.size()) {
                        Map<String, Object> sqlParam = new HashMap<String, Object>();
                        sqlParam.put("emailSubscriptionIDList", emailSubscriptionIDList);
                        List<HashMap<String, Object>> resultSet = queryDAO.executeForObjectList("B_SSM_S01.getEmailSubscribedSearchResultSet", sqlParam);
                        // Process query data
                        if (resultSet != null && resultSet.size() > 0) {            
                            for (int i=0; i < resultSet.size(); i++) {
                            HashMap<String, Object> result = resultSet.get(i);
                            
                            String custID = result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID.toUpperCase())==null?
                                          null:result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID.toUpperCase()).toString();
                            
                            String subID = result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()) != null?
                                          result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()).toString():null;
                                          
                            String service = result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME.toUpperCase()) != null?
                                           result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME.toUpperCase()).toString():null;
                              
                            if (custID != null && !custID.trim().equals("")) {
                                HashMap<String, Object> processedResult = resultSet.get(i);
                                // Get processed result containing custID
                                //String[] conditionFieldNames = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
                                //                   (new String[] {B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, 
                                //                                  B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, 
                                //                                  B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME}): 
                                //                    (new String[] {B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, 
                                //                                  B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID});         
                                //                   
                                //String[] conditionFieldValues = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
                                //                           (new String[] {custID, subID, service}): 
                                //                           (new String[] {custID, subID});        
                                //
                                //if (BLogicUtils.isContainItemWith(processedResultSet, 
                                //                      conditionFieldNames,            
                                //                      conditionFieldValues)) {
                                //
                                //processedResult = BLogicUtils.getItemWith(processedResultSet, 
                                //                                    conditionFieldNames,              
                                //                                    conditionFieldValues);
                                //}
                                //// Add new item to processed resultSet
                                //else {
                                processedResult = new HashMap<String, Object>();
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, custID);
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, subID);
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME, service);
                                processedResultSet.add(processedResult);                        
                                //}
                                
                                ////////////// Add value to processed result ///////////////////
                                //if (processedResult != null) {  
                                
                                //start modify gplai 
                                Map<String, Object> sameDataListCon = new HashMap<String, Object>();
                                sameDataListCon.put("emailCustomerID", custID);
                                sameDataListCon.put("emailSubscriptionID", CommonUtils.toString(subID));
                                sameDataListCon.put("emailService", CommonUtils.toString(service));
                                sameDataListCon.put("isExportFlag", isExportFlag);
                                sameDataListCon.put("detetedEmail", detetedEmail);
                                //get the data by cust_id,subscription_id, if is export type then add service
                                List<HashMap<String, Object>> emailAddressList = queryDAO.executeForObjectList("B_SSM_S01.getEmailSubscribedSubEmailAddress",sameDataListCon);
                                
                                //CUSTOMER_TYPE
                                processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
                                // CustomerName
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERNAME, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERNAME.toUpperCase()), ""));
                                // Add subID
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()), ""));
                                //PlanStatus
                                processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
                                // Cust plan id
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID.toUpperCase()), ""));
                                // Service
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME.toUpperCase()),""));                    
                                // Customer Tel
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERTELEPHONE,
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERTELEPHONE.toUpperCase()), ""));
                                // Address text
                                // processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIBEDADDRESSTEXT, 
                                //                     BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIBEDADDRESSTEXT.toUpperCase()), ""));
                                // Cust plan id
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID.toUpperCase()), ""));
                                processedResult.put("planName", BLogicUtils.emptyValue(result.get("PLANNAME"), ""));
                                // Category
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCATEGORY, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCATEGORY.toUpperCase()), ""));  
                                // Category name
                                processedResult.put("emailCategoryName", BLogicUtils.emptyValue(result.get("EMAILCATEGORYNAME"), ""));
                                // Service
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME.toUpperCase()), ""));
                                // Email plan
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILPLAN, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILPLAN.toUpperCase()), ""));
                                // Service status
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICESTATUS, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICESTATUS.toUpperCase()), ""));
                                // Email address
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS, 
                                              BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS.toUpperCase()),""));
                                //Domain Name
                                if (emailAddressList != null && 0 < emailAddressList.size()) {
                                    processedResult.put("emailDomainName", CommonUtils.toString(emailAddressList.get(0).get("DOMAIN_NAME")).trim());
                                } else {
                                    processedResult.put("emailDomainName", "");
                                }
                                ////T_IT_CONTACT_H and T_IT_CONTACT_D info
                                //List<HashMap<String, Object>> listEmailAndTelNo = queryDAO.executeForObjectList("B_SSM_S01.getEmailT_IT_CONTACT_H_D",sameDataListCon);
                                //if (listEmailAndTelNo != null && 0 < listEmailAndTelNo.size()) {
                                //for (Map<String, Object> emailAndTelNo : listEmailAndTelNo) {
                                //String contactType = CommonUtils.toString(emailAndTelNo.get("CONTACT_TYPE")).trim();
                                //if ("C1".equals(contactType)) {
                                //    // Email1
                                //    processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL1, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
                                //    // Tell no1
                                //    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
                                //} else if ("C2".equals(contactType)) {
                                //    // Email2
                                //      processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL2, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
                                //      // Tell no2
                                //      processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
                                //} else {
                                //    //C3
                                //    // Email3
                                //      processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL3, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
                                //      // Tell no3
                                //      processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
                                //}
                                //}
                                //} else {
                                //// Email1
                                //processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL1, "");
                                //// Tell no1
                                //processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, "");  
                                //// Email2
                                //processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL2, "");
                                //// Tell no2
                                //processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, "");   
                                //// Email3
                                //processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL3, "");
                                //// Tell no3
                                //processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, "");  
                                //}
                                List<String> subscribedEmailAddressFields = BLogicUtils.listObjectByNameTolistStr(emailAddressList, "MAIL_ACCOUNT");
                                processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIBEDADDRESSTEXT, subscribedEmailAddressFields);
                                processedResult.put("mailAccountInfoList", emailAddressList);
                                //}
                                }
                            }
                        }
                    }
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
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILFULLRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILDISPLAYROWCOUNT, displayRowCount);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX, startIndex);
    }
    
    /**
     * Handling searching on email it contact mode 
     * @param logicOutput
     * @param logicInput
     * @param processMode 
     */
    private void handlingEmailITContactSearch(HashMap<String, Object> logicOutput,
                                               HashMap<String, Object> logicInput, 
                                               int processMode) {
        List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String,Object>>();
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(queryDAO);
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX).toString());
        } catch(Exception ex) {
            startIndex = 0;
        }
        Integer totalRow = 0; 
        String isExportFlag = "";
        if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
            logicInput.put("isExportFlag", "0");
            isExportFlag = "0";
        }else {
            logicInput.put("startRowIndex", startIndex);
            logicInput.put("endRowIndex", startIndex+displayRowCount);
        }
        B_SSM_S01_Utils.trimInputMapValue(logicInput);
        totalRow = queryDAO.executeForObject("B_SSM_S01.getEmailITContactSearchResultSetCount", logicInput, Integer.class);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        resultSet = queryDAO.executeForObjectList("B_SSM_S01.getEmailITContactSearchResultSet",
                                                  logicInput);
        
        // Process query data
        if (resultSet != null && resultSet.size() > 0) {            
            for (int i=0; i < resultSet.size(); i++) {
                HashMap<String, Object> result = resultSet.get(i);
                
                String custID = result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID.toUpperCase())==null?
                                            null:result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID.toUpperCase()).toString();
                
                String subID = result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()).toString():null;
                                            
                String service = result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE.toUpperCase()) != null?
                                            result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE.toUpperCase()).toString():null;
                                            
                if (custID != null && !custID.trim().equals("")) {
                    HashMap<String, Object> processedResult = resultSet.get(i);
                    // Get processed result containing custID
//                  String[] conditionFieldNames = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                             (new String[] {B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, 
//                                            B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, 
//                                            B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME}): 
//                              (new String[] {B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, 
//                                            B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID});         
//                             
//                  String[] conditionFieldValues = (processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT)? 
//                                     (new String[] {custID, subID, service}): 
//                                     (new String[] {custID, subID});
//                  
//                  if (BLogicUtils.isContainItemWith(processedResultSet, 
//                                                    conditionFieldNames,            
//                                                    conditionFieldValues)) {
//                          processedResult = BLogicUtils.getItemWith(processedResultSet, 
//                                                                    conditionFieldNames,            
//                                                                    conditionFieldValues);                        
//                  }
//                  // Add new item to processed resultSet
//                  else {
                        processedResult = new HashMap<String, Object>();
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERID, custID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, subID);
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE, service);
                        processedResultSet.add(processedResult);                        
//                  }
                    
                    ////////////// Add value to processed result ///////////////////
//                  if (processedResult != null) {  
                        
                        //start modify gplai 
                        Map<String, Object> sameDataListCon = new HashMap<String, Object>();
                        sameDataListCon.put("emailCustomerID", custID);
                        sameDataListCon.put("emailSubscriptionID", CommonUtils.toString(subID));
                        sameDataListCon.put("emailService", CommonUtils.toString(service));
                        sameDataListCon.put("isExportFlag", isExportFlag);
                        //get the data by cust_id,subscription_id, if is export type then add service
                        List<HashMap<String, Object>> tContactList = queryDAO.executeForObjectList("B_SSM_S01.getEmailITContactT_CONTACT",sameDataListCon);
                        
                        //CUSTOMER_TYPE
                        processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
                        // CustomerName
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERNAME, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERNAME.toUpperCase()), ""));
                        // Add subID
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSUBSCRIPTIONID.toUpperCase()), ""));
                        // Service
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICE.toUpperCase()),""));                    
                        //PlanStatus
                        processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
                        // Customer Tel
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERTELEPHONE,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERTELEPHONE.toUpperCase()), ""));
                        
                        // Cust plan id
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERPLANID.toUpperCase()), ""));
                        processedResult.put("planName", BLogicUtils.emptyValue(result.get("PLANNAME"), ""));
                        // Category
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCATEGORY, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCATEGORY.toUpperCase()), ""));                  
                        // Category name
                        processedResult.put("emailCategoryName", BLogicUtils.emptyValue(result.get("EMAILCATEGORYNAME"), ""));
                        // Service
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME,
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICENAME.toUpperCase()), ""));
                        // Email plan
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILPLAN, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILPLAN.toUpperCase()), ""));
                        // Service status
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILSERVICESTATUS, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILSERVICESTATUS.toUpperCase()), ""));
                        // Email address
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS, 
                                            BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_EMAILCUSTOMERADDRESS.toUpperCase()),""));
                        
                        //T_IT_CONTACT_H and T_IT_CONTACT_D info
                        List<HashMap<String, Object>> listEmailAndTelNo = queryDAO.executeForObjectList("B_SSM_S01.getEmailT_IT_CONTACT_H_D",sameDataListCon);
                        if (listEmailAndTelNo != null && 0 < listEmailAndTelNo.size()) {
                            for (Map<String, Object> emailAndTelNo : listEmailAndTelNo) {
                                String contactType = CommonUtils.toString(emailAndTelNo.get("CONTACT_TYPE")).trim();
                                if ("C1".equals(contactType)) {
                                    // Email1
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL1, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
                                    // Tell no1
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
                                } else if ("C2".equals(contactType)) {
                                    // Email2
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL2, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
                                    // Tell no2
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
                                } else {
                                    //C3
                                    // Email3
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL3, CommonUtils.toString(emailAndTelNo.get("EMAIL")));
                                    // Tell no3
                                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, CommonUtils.toString(emailAndTelNo.get("TEL_NO")));   
                                }
                            }
                        } else {
                            // Email1
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL1, "");
                            // Tell no1
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO1, "");  
                            // Email2
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL2, "");
                            // Tell no2
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO2, "");   
                            // Email3
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_MAIL3, "");
                            // Tell no3
                            processedResult.put(B_SSM_S01_FieldSet.FIELD_TELNO3, "");  
                        }
                        // IT Contact Email
                        List<String> emailITContactFields = BLogicUtils.removeDulpicateToListStr(tContactList, 
                                B_SSM_S01_FieldSet.FIELD_EMAILITCONTACT.toUpperCase());
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILITCONTACT, emailITContactFields);
                        
                        // IT Contact No                                                                
                        List<String> emailITContactNoFields = BLogicUtils.removeDulpicateToListStr(tContactList, 
                                B_SSM_S01_FieldSet.FIELD_EMAILITCONTACTNO.toUpperCase());
                        processedResult.put(B_SSM_S01_FieldSet.FIELD_EMAILITCONTACTNO, emailITContactNoFields);
                        
                        List<HashMap<String, Object>> listMailAccountObject = queryDAO.executeForObjectList("B_SSM_S01.getEmailT_MAIL_ACCOUNT",sameDataListCon);
                        List<String> listMailAccount = BLogicUtils.removeDulpicateToListStr(listMailAccountObject, "MAIL_ACCOUNT");
                        processedResult.put("mailAccountList", listMailAccount);
//                  }
                }
            }
        }
        
        // Get "displayRowCount" quantity of record at start index postion 
//      List<HashMap<String, Object>> filterProcessedResultSet = new ArrayList<HashMap<String,Object>>();
//      if (processedResultSet != null && 
//          processedResultSet.size() > 0 &&
//          startIndex < processedResultSet.size()) {
//          filterProcessedResultSet = BLogicUtils.get(processedResultSet, startIndex, displayRowCount);
//          totalRow = processedResultSet.size(); 
//      }
        
        // Add data to output   
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILFULLRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILDISPLAYROWCOUNT, displayRowCount);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX, startIndex);
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
