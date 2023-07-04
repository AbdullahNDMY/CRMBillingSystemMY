/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S03
 * FUNCTION       : Utilities processing business logic from requests of B_SSM_S03
 * FILE NAME      : B_SSM_S03_BUtils.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s03.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;

/**
 * @author NTT Data Vietnam	
 * Class Blogic Utilities processing business logic from requests of B_SSM_S03
 * @param <P>
 */
public class B_SSM_S03_BUtils {
	
	/**
	 * Map view data
	 */
	public static void mappingViewData(QueryDAO queryDAO,
										 HashMap<String, Object> logicInput,
										 HashMap<String, Object> logicOutput) {
		// Get customer services
		List<HashMap<String, Object>> customerServices = new ArrayList<HashMap<String, Object>>();
		customerServices = queryDAO.executeForObjectList("B_SSM_S03.getCustomerServiceInfo", logicInput);
		// Add output
		if (customerServices.size() > 0) {
			logicOutput.put(B_SSM_S03_FieldSet.FIELD_SERVICENAMELIST, customerServices);
		}
		List<HashMap<String, Object>> addressList = queryDAO.executeForObjectList("B_SSM_S03.getCustomerAddress", logicInput);
		if (addressList!=null && 0<addressList.size()) {
		    logicOutput.put("addressList", addressList);
		} else {
		    logicOutput.put("addressList", new ArrayList<HashMap<String, Object>>());
		}
	}

	/**
	 * Get selected service ids
	 * @param logicInput
	 * @return String[]
	 */
	public static List<String> getSelectedServiceIDs(HashMap<String, Object> logicInput) {
		String[] serviceIDs = logicInput.get(B_SSM_S03_FieldSet.FIELD_SERVICEIDS) != null?
							  (String[]) logicInput.get(B_SSM_S03_FieldSet.FIELD_SERVICEIDS) :
							  null;
	    String[] selectedServices = logicInput.get(B_SSM_S03_FieldSet.FIELD_SELECTEDSERVICES) != null?
									(String[]) logicInput.get(B_SSM_S03_FieldSet.FIELD_SELECTEDSERVICES) :
									null;	
		List<String> selectedIDs = new ArrayList<String>();							  
		
		// Get selected ids
		if (selectedServices != null && selectedServices.length > 0) {
			for (int i = 0; i < selectedServices.length; i++) {
				String isSelectedStr = selectedServices[i];
				if (isSelectedStr != null && isSelectedStr.equals("1")) {
					selectedIDs.add(serviceIDs[i]);
				}
			}
		}
		return selectedIDs;
	}
	
	/**
	 * Get selected customer plan link ids
	 * @param logicInput
	 * @return String[]
	 */
	public static String[] getSelectedCustomerPlanLinkIDs(HashMap<String, Object> logicInput) {
		String[] custPlanLinkIDs = logicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANLINKIDS) != null?
								   (String[]) logicInput.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANLINKIDS) :
								   null;
		String[] selectedServices = logicInput.get(B_SSM_S03_FieldSet.FIELD_SELECTEDSERVICES) != null?
									(String[]) logicInput.get(B_SSM_S03_FieldSet.FIELD_SELECTEDSERVICES) :
									null;	
		List<String> selectedIDs = new ArrayList<String>();							  
		
		// Get selected ids
		if (selectedServices != null && selectedServices.length > 0) {
			for (int i = 0; i < selectedServices.length; i++) {
				String isSelectedStr = selectedServices[i];
				if (isSelectedStr != null && isSelectedStr.equals("1")) {
					selectedIDs.add(custPlanLinkIDs[i]);
				}
			}
		}
		String[] selectedIDArray = new String[selectedIDs.size()];
		selectedIDs.toArray(selectedIDArray);
		return selectedIDArray;
	}

	/**
	 * Get report template path from db
	 * @param queryDAO
	 * @param reportMode
	 * @return String
	 */
	public static String getDBReportTemplatePath(QueryDAO queryDAO,
												   String reportMode) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(B_SSM_S03_FieldSet.FIELD_REPORTCODE, reportMode);
		String rptTemplatePath = queryDAO.executeForObject("B_RPT.getReportTemplate", params, String.class);
		return rptTemplatePath;
	}
}
