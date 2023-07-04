/***********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processing data from Address Tab of B_SSM_S01
 * FILE NAME      : B_SSM_S01_Address_Processor.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History:
 * 17/10/2011 Hoangdtk update data export
**********************************************************/

package nttdm.bsys.b_ssm.s01.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_ssm.s01.blogic.B_SSM_S01_Utils;
import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;

/**
 * @author NTT Data Vietnam
 * Class processing data from Address Tab of B_SSM_S01
 */
public class B_SSM_S01_Address_Processor {

    // Private Properties
    private QueryDAO queryDAO = null; 

    /**
     * Initialize processor
     */
    public B_SSM_S01_Address_Processor(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    
    /**
     * Handling input data from address tab
     * @param logicOutput
     * @param logicInput 
     * @param processMode 
     */
    public void handlingAddressTab(HashMap<String, Object> logicOutput, 
                                    HashMap<String, Object> logicInput,
                                    int processMode) {
        List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String,Object>>();
        // Add resultSet
        BillingSystemSettings settings = new BillingSystemSettings(queryDAO);
        int displayRowCount = settings.getResultRow();
        int startIndex = 0;
        try {
            startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX).toString());
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
        // #188 Start
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        MappedCodeListLoader countryCodeList = (MappedCodeListLoader) context.getBean("LIST_COUNTRY");
		Map<String, Object> countryMap = countryCodeList.getCodeListMap();
        logicInput.put("countryValue", countryMap.get(logicInput.get("addressCountry")));
        // #188 End
        
        B_SSM_S01_Utils.trimInputMapValue(logicInput);
        totalRow = queryDAO.executeForObject("B_SSM_S01.GetAddressSearchResultSetCount", logicInput, Integer.class);
        List<HashMap<String, Object>> processedResultSet = new ArrayList<HashMap<String,Object>>();
        resultSet = queryDAO.executeForObjectList("B_SSM_S01.GetAddressSearchResultSet",logicInput);
        
        // #188 Start
        if (processMode != B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
	        // Process query data
	        if (resultSet != null && resultSet.size() > 0) {            
	            for (int i=0; i < resultSet.size(); i++) {
	                HashMap<String, Object> result = resultSet.get(i);
	                
	                String custID = result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERID.toUpperCase())==null?
	                                            null:result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERID.toUpperCase()).toString();
	                
	                String subID = result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSUBSCRIPTIONID.toUpperCase())==null?
	                                            null:result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSUBSCRIPTIONID.toUpperCase()).toString();
	                
	                String service = result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSERVICE.toUpperCase())==null?
	                                            null:result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSERVICE.toUpperCase()).toString();
	                
	                if (custID != null && !custID.trim().equals("")) {
	                	HashMap<String, Object> processedResult = new HashMap<String, Object>();
	                    processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERID, custID);
	                    processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSSUBSCRIPTIONID, subID);
	                    processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSSERVICE, service);
	                    processedResultSet.add(processedResult);                        
	                    ////////////// Add value to processed result ///////////////////
	                    //CUSTOMER_TYPE
	                    processedResult.put("customerType", BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
	                    // CustomerName
	                    processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERNAME, 
	                                        BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERNAME.toUpperCase()), ""));
	                    // Cust plan id
	                    processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERPLANID, 
	                                        BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERPLANID.toUpperCase()), ""));                        
	                    //PlanStatus
	                    processedResult.put("PLAN_STATUS", BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
	                    // Plan name
	                    processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSPLANNAME, BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSPLANNAME.toUpperCase()), ""));
	                    // Customer email
	                    processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTEMAIL, BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTEMAIL.toUpperCase()), ""));
	                    
	                    String customerType = BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), "");
	                    List<String> addressITContactNoTelNoList = new ArrayList<String>();
	                    List<String> addressCustomerFaxNoList = new ArrayList<String>();
	                    List<Map<String, Object>> contactTypeInfoList = new ArrayList<Map<String,Object>>();
	                    //Customer Type = Consumer Customer
	                    if ("CONS".equals(customerType)) {
	                    	//addressITContactNoTelNoList
	                    	addressITContactNoTelNoList.add("(H)"+BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSHOMETELNO.toUpperCase()), ""));
	                    	addressITContactNoTelNoList.add("(O)"+BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCOTELNO.toUpperCase()), ""));
	                    	addressITContactNoTelNoList.add("(M)"+BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSMOBILENO.toUpperCase()), ""));
	                    	//addressCustomerFaxNoList
	                    	addressCustomerFaxNoList.add("(H)"+BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSHOMETELFAX.toUpperCase()), ""));
	                    	addressCustomerFaxNoList.add("(O)"+BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCOFAXNO.toUpperCase()), ""));
	                    	//NRIC / Passport No
	                    	processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSICNO, BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSICNO.toUpperCase()), ""));
	                    	//addressTypeInfoList
	                    	processedResult.put("addressRA", BLogicUtils.emptyValue(result.get("ADDRESSRA".toUpperCase()), ""));
	                    	processedResult.put("addressBA1", BLogicUtils.emptyValue(result.get("ADDRESSBA1".toUpperCase()), ""));
						}else {
							//Customer Type = Corporate Customer
							addressITContactNoTelNoList.add(BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCOTELNO.toUpperCase()), ""));
							addressCustomerFaxNoList.add(BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCOFAXNO.toUpperCase()), ""));
							//NRIC / Passport No
	                    	processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSICNO,"");
	                    	//addressTypeInfo
	                    	processedResult.put("addressRA", BLogicUtils.emptyValue(result.get("ADDRESSRA"), ""));
	                    	processedResult.put("addressBA1", BLogicUtils.emptyValue(result.get("ADDRESSBA1"), ""));
	                    	processedResult.put("addressBA2", BLogicUtils.emptyValue(result.get("ADDRESSBA2"), ""));
	                    	processedResult.put("addressBA3", BLogicUtils.emptyValue(result.get("ADDRESSBA3"), ""));
	                    	processedResult.put("addressBA4", BLogicUtils.emptyValue(result.get("ADDRESSBA4"), ""));
	                    	processedResult.put("addressCA", BLogicUtils.emptyValue(result.get("ADDRESSCA"), ""));
	                    	processedResult.put("addressTA", BLogicUtils.emptyValue(result.get("ADDRESSTA"), ""));
	                    	//addressContactTypeInfo
	                    	getContactTypeInfoList(result, contactTypeInfoList);
						}
	                    processedResult.put("addressITContactNoTelNoList", addressITContactNoTelNoList);
	                    processedResult.put("addressCustomerFaxNoList", addressCustomerFaxNoList);
	                    processedResult.put("contactTypeInfoList", contactTypeInfoList);   
	                }
	            }
	        }
        }
        // Add data to output
        //logicOutput.put("addressTypeArray", logicInput.get("addressTypeArray"));
        logicOutput.put("logicInput", logicInput);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSRESULTSET, processedResultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSFULLRESULTSET, resultSet);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX, startIndex);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSTOTALROW, totalRow);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSDISPLAYROWCOUNT, displayRowCount);
        logicOutput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX, startIndex);
    }
    
    // #188 Start
    private void getContactTypeInfoList(HashMap<String, Object> result,List<Map<String, Object>> contactTypeInfoList){
    	
		for (int i = 1; i < 8; i++) {
			if (i <= 4) {
				setContactTypeInfo(result, contactTypeInfoList, "BC", "Billing Contact " + i, i);
			} else {
				if (i == 5) {
					setContactTypeInfo(result, contactTypeInfoList, "BC", "Primary Contact", i);
				}
				if (i == 6) {
					setContactTypeInfo(result, contactTypeInfoList, "BC", "Technical Contact", i);
				}
				if (i == 7) {
					setContactTypeInfo(result, contactTypeInfoList, "BC", "Others Contact", i);
				}
			}
		}
		for (int i = 1; i < 4; i++) {
			setContactTypeInfo(result, contactTypeInfoList, "ITC", "IT Contact " + i, i);
		}
    }
    
	private void setContactTypeInfo(HashMap<String, Object> result,
			List<Map<String, Object>> contactTypeInfoList, String type, String contactType, int i) {
    	Map<String, Object> contactTypeInfo = new HashMap<String, Object>();
    	String nameKey = type + i + "NAME";
		String nameVal = BLogicUtils.emptyValue(result.get(nameKey), "");
		if (!"".equals(nameVal)) {
			contactTypeInfo.put("contactName", nameVal);
		}
		String telNoKey = type + i + "TELNO";
		String telNoVal = BLogicUtils.emptyValue(result.get(telNoKey), "");
		if (!"".equals(telNoVal)) {
			contactTypeInfo.put("contactTelNo", telNoVal);
		}
		String faxNoKey = type + i + "FAXNO";
		String faxNoVal = BLogicUtils.emptyValue(result.get(faxNoKey), "");
		if (!"".equals(faxNoVal)) {
			contactTypeInfo.put("contactFaxNo", faxNoVal);
		}
		String mobileNoKey = type + i + "MOBILE";
		String mobileNoVal = BLogicUtils.emptyValue(result.get(mobileNoKey), "");
		if (!"".equals(mobileNoVal)) {
			contactTypeInfo.put("contactMobileNo", mobileNoVal);
		}
		String emailKey = type + i + "EMAIL";
		String emailVal = BLogicUtils.emptyValue(result.get(emailKey), "");
		List<String> contactEmailToCcList = new ArrayList<String>();
		if (!"".equals(emailVal)) {
			contactEmailToCcList.add(emailVal);
		}
		if ("BC".equals(type)) {
			String emailCCKey = type + i + "EMAILCC";
			String emailCCVal = BLogicUtils.emptyValue(result.get(emailCCKey), "");
			if (!"".equals(emailCCVal)) {
				contactEmailToCcList.add(emailCCVal);
			}
		}
		
		if (!contactTypeInfo.isEmpty() || contactEmailToCcList.size() > 0) {
			contactTypeInfo.put("contactEmailToCcList",contactEmailToCcList);
			contactTypeInfo.put("contactType", contactType);
			contactTypeInfoList.add(contactTypeInfo);
		}
    }
	// #188 End
    
    /**
     * addressText change
     * @param processedResult
     * @param sameDataList
     * @param sameDataListCon
     * @param billaddlist
     * @param techaddlist
     * @param regisdaddlist
     * @param addcorresslist
     */
    @SuppressWarnings("unchecked")
    private void addressTextChange(HashMap<String, Object> processedResult,List<HashMap<String, Object>> sameDataList,
            Map<String, Object> sameDataListCon,
            List<String> billaddlist, List<String> techaddlist, 
            List<String> regisdaddlist, List<String> addcorresslist) {
        
        String addrRA = "";
        String addrBA = "";
        String addrCA = "";
        String addrTA = "";
        if (sameDataList != null && 0 < sameDataList.size()) {
            for (Map<String, Object> objectAdr : sameDataList) {
                String adrType = CommonUtils.toString(objectAdr.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTYPE.toUpperCase())).trim();
                String adrText = CommonUtils.toString(objectAdr.get("ADDRESSTEXT"));
                if ("RA".equals(adrType)) {
                    addrRA = adrText;
                } else if ("BA".equals(adrType)) {
                    addrBA = adrText;
                } else if ("CA".equals(adrType)) {
                    addrCA = adrText;
                } else {
                    //TA
                    addrTA = adrText;
                }
            }
        }
        
        for (int i=0; i < sameDataList.size(); i++) {
            HashMap<String, Object> result = sameDataList.get(i);
            String addcorress ="";
            String billadd="";
            String techadd="";
            String regisdadd="";
            // Address Type                                                            
            String addressTypeStr = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTYPE.toUpperCase()), "");
            List<String> addressTypeFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTYPE) == null?
                                             new ArrayList<String>() :
                                             (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTYPE);
            if (!addressTypeStr.trim().equals("") && !BLogicUtils.isContainItem(addressTypeFields, addressTypeStr)) {
                addressTypeFields.add(addressTypeStr);                                          
                processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSTYPE, addressTypeFields);
                // Address text
                String addressTextStr = "";
                // RA
                if (addressTypeStr.trim().equals(B_SSM_S01_FieldSet.VALUE_REGISTERED_ADDRESS)) {
                    addressTextStr = addrRA;
                    if (addressTextStr.equalsIgnoreCase("") == false){                          
                        if(regisdaddlist.contains(addressTextStr) == false){
                            regisdaddlist.add(addressTextStr);                          
                            regisdadd += addressTextStr + "\n";
                        }
                    }
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_REGISTEREDADDRESS, regisdadd);
                }
                // BA
                if (addressTypeStr.trim().equals(B_SSM_S01_FieldSet.VALUE_BILLING_ADDRESS)) {
                    addressTextStr = addrBA;
                    if(addressTextStr.equalsIgnoreCase("") == false){
                        if(billaddlist.contains(addressTextStr) == false){
                            billaddlist.add(addressTextStr);
                            billadd += addressTextStr + "\n";
                        }
                    }
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_BILLINGADDRESS, billadd );
                }
                // CA
                if (addressTypeStr.trim().equals(B_SSM_S01_FieldSet.VALUE_CORRESPONDENCE_ADDRESS)) {
                    addressTextStr = addrCA;
                    if(addressTextStr.equalsIgnoreCase("") == false){
                        if(addcorresslist.contains(addressTextStr) == false){
                            addcorresslist.add(addressTextStr);
                            addcorress += addressTextStr + "\n";    
                        }
                    }
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_CORRESPONDENCEADDRESS, addcorress);
                }
                // TA
                if (addressTypeStr.trim().equals(B_SSM_S01_FieldSet.VALUE_TECHNICAL_ADDRESS)) {
                    addressTextStr = addrTA;
                    if(addressTextStr.equalsIgnoreCase("") == false){
                        if(techaddlist.contains(addressTextStr) == false){
                            techaddlist.add(addressTextStr);
                            techadd += addressTextStr + "\n";
                        }
                    }
                    processedResult.put(B_SSM_S01_FieldSet.FIELD_TECHNICALADDRESS, techadd);
                }
                List<String> addressTextFields = processedResult.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTEXT) == null?
                                                new ArrayList<String>() : 
                                                (List<String>)processedResult.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTEXT);                        
                addressTextFields.add(addressTextStr);                          
                processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSTEXT, addressTextFields);
            }
        }
        if (sameDataList==null || sameDataList.size()==0) {
            processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSTYPE, new ArrayList<String>());
            processedResult.put(B_SSM_S01_FieldSet.FIELD_ADDRESSTEXT, new ArrayList<String>());
        }
    }
    
    /**
     * Build condition query fields
     * @param logicInput
     */
    private void buildConditionQueryFields(HashMap<String, Object> logicInput) {
        ////////////// Build service status query condition ////////////////////////
        List<String> serviceStatusArray = new ArrayList<String>();        
        String draftServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSDRAFTSERVICESTATUS);        
        String pendingApprovalServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSPENDINGSERVICESTATUS);         
        String activeServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSACTIVESERVICESTATUS);     
        String cancelledServiceStatus    = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCANCELLEDSERVICESTATUS);      
        String terminatedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTERMINATEDSERVICESTATUS);     
        String rejectedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSREJECTEDSERVICESTATUS);          
        String suspendedServiceStatus = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSUSPENDEDSERVICESTATUS);
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
        logicInput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSSERVICESTATUSARRAY, serviceStatusArray);
        ////////////// Build address type query condition ////////////////////////                     
        // #188 Start
        String addressRegisteredType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSREGISTEREDTYPE);        
        String addressBillingType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSBILLINGTYPE);    
        String addressCorrespondenceType =    (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCORRESPONDENCETYPE);      
        String addressTechnicalType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTECHNICALTYPE);             
        /*List<String> addressTypeArray = new ArrayList<String>();
        // Assign service status values
        if (!addressRegisteredType.trim().equals("")) {
            addressTypeArray.add(addressRegisteredType);
        }
        if (!addressBillingType.trim().equals("")) {
            addressTypeArray.add(addressBillingType);
        }
        if (!addressCorrespondenceType.trim().equals("")) {
            addressTypeArray.add(addressCorrespondenceType);
        }
        if (!addressTechnicalType.trim().equals("")) {
            addressTypeArray.add(addressTechnicalType);
        }        
        // Add service  condition string to logicInput        
        logicInput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSTYPEARRAY, addressTypeArray);*/
        
        if ("".equals(addressRegisteredType) && "".equals(addressBillingType)
                && "".equals(addressCorrespondenceType) && "".equals(addressTechnicalType)) {
        	addressRegisteredType = "RA";
        	addressBillingType = "BA";
        	addressCorrespondenceType = "CA";
        	addressTechnicalType = "TA";
        }
        logicInput.put("addressRAFlag", addressRegisteredType);
        logicInput.put("addressBAFlag", addressBillingType);
        logicInput.put("addressCAFlag", addressCorrespondenceType);
        logicInput.put("addressTAFlag", addressTechnicalType);
        
        String addressContactGeneralType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCONTACTGENERALTYPE);        
        String addressContactBillingType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCONTACTBILLINGTYPE);
        String addressContactPrimaryType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCONTACTPRIMARYTYPE);
        String addressContactTechnicalType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCONTACTTECHNICALTYPE);
        String addressContactOtherType = (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCONTACTOTHERTYPE);
        String addressContactITCType =    (String) logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCONTACTITTYPE);
        // not select Contact Type then contact select GC,BC,PC,TC,OC,ITC
		if ("".equals(addressContactGeneralType)
				&& "".equals(addressContactBillingType)
				&& "".equals(addressContactPrimaryType)
				&& "".equals(addressContactTechnicalType)
				&& "".equals(addressContactOtherType)
				&& "".equals(addressContactITCType)) {
        	addressContactGeneralType = "GC";
        	addressContactBillingType = "BC";
        	addressContactPrimaryType = "PC";
        	addressContactTechnicalType = "TC";
        	addressContactOtherType = "OC";
        	addressContactITCType = "ITC";
        }
        logicInput.put("contactGCFlag", addressContactGeneralType);
        logicInput.put("contactBCFlag", addressContactBillingType);
        logicInput.put("contactPCFlag", addressContactPrimaryType);
        logicInput.put("contactTCFlag", addressContactTechnicalType);
        logicInput.put("contactOCFlag", addressContactOtherType);
        logicInput.put("contactITCFlag", addressContactITCType);
        
        
        // #188 End
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