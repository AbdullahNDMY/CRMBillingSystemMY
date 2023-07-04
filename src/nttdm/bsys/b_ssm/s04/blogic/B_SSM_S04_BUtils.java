/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S04
 * FUNCTION       : Utilities processing business logic from requests of B_SSM_S04
 * FILE NAME      : B_SSM_S04_BUtils.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s04.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.b_ssm.s04.data.B_SSM_S04_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author NTT Data Vietnam	
 * Class Blogic Utilities processing business logic from requests of B_SSM_S04
 * @param <P>
 */
public class B_SSM_S04_BUtils {
	
	/**
	 * Map view data
	 */
	public static void mappingViewData(QueryDAO queryDAO,
										 HashMap<String, Object> logicInput,
										 HashMap<String, Object> logicOutput) {
		// Billing instruction
	    String idCust = CommonUtils.toString(logicInput.get("customerID"));
		Map<String, Object> custInfo = queryDAO.executeForMap("B_SSM_S04.getCustomerInfo", idCust);		
		if (custInfo!=null) {
		    logicOutput.put("customerName", custInfo.get("CUST_NAME"));
		} else {
		    logicOutput.put("customerName", "");
		}
		// Services
		List<HashMap<String, Object>> services = new ArrayList<HashMap<String, Object>>();
		services = queryDAO.executeForObjectList("B_SSM_S04.getCustomerServiceInfo", logicInput);		
		
		// Sub Services
		List<HashMap<String, Object>> subServices = new ArrayList<HashMap<String, Object>>();
		subServices = queryDAO.executeForObjectList("B_SSM_S04.getCustomerSubServiceInfo", logicInput);		
		
		// Des length 
		int desLength = -1;
		try {
			Integer desLengthI = queryDAO.executeForObject("B_SSM_S04.getDescLength", logicInput, Integer.class)	;
			if (desLengthI != null) {
			    desLength = desLengthI.intValue();
			}
		} catch (Exception ex) {
			desLength = -1;
		}
		
		// Filter services
		List<HashMap<String, Object>> filterServices = new ArrayList<HashMap<String,Object>>();
		if (services.size() > 0) {
			for (int i=0; i < services.size(); i++) {
				HashMap<String, Object> filterService = new HashMap<String, Object>();
				HashMap<String, Object> service = services.get(i);
				String serviceGrpID = BLogicUtils.emptyValue(service.get(B_SSM_S04_FieldSet.FIELD_SERVICEGROUPID), "");
				if (serviceGrpID == null || serviceGrpID.equals("")) {
					continue;
				}
				// Plan description
				String planDesc = BLogicUtils.emptyValue(service.get(B_SSM_S04_FieldSet.FIELD_PLANDESCRIPTION), "");
				
				// Contract period
				String startContractPeriod = BLogicUtils.emptyValue(service.get(B_SSM_S04_FieldSet.FIELD_STARTCONTRACTPERIOD), "");
				String endContractPeriod = BLogicUtils.emptyValue(service.get(B_SSM_S04_FieldSet.FIELD_ENDCONTRACTPERIOD), "");
				String contractPeriod = !startContractPeriod.equals("") && !endContractPeriod.equals("") ?
										startContractPeriod + "-" + endContractPeriod:
										(!startContractPeriod.equals("") ?
										startContractPeriod:endContractPeriod);
				filterService.put(B_SSM_S04_FieldSet.FIELD_CONTRACTPERIOD, contractPeriod);
				
				// Contract terms
				filterService.put(B_SSM_S04_FieldSet.FIELD_CONTRACTTERMS, 
						  		  BLogicUtils.emptyValue(service.get(B_SSM_S04_FieldSet.FIELD_CONTRACTTERMS), ""));
		
				// Status
				filterService.put(B_SSM_S04_FieldSet.FIELD_SERVICESTATUS, 
				  		  		  BLogicUtils.emptyValue(service.get(B_SSM_S04_FieldSet.FIELD_SERVICESTATUS), ""));
				//Service Completion Date
				filterService.put("completionDate", BLogicUtils.emptyValue(service.get("completionDate"), ""));
				//
                filterService.put("serviceGroupID", BLogicUtils.emptyValue(service.get("serviceGroupID"), ""));
                //Service name
                filterService.put("serviceName", "");
                //Service Description 
                filterService.put("serviceDesc", CommonUtils.toString(CommonUtils.formatString(planDesc, desLength)));
                filterService.put("dataType", "header");
                filterService.put("serviceNo", i+1);
                filterServices.add(filterService);
                
				// Get sub service name and plan description
				if (subServices.size() > 0) {
					for (int j=0; j < subServices.size(); j++) {
						HashMap<String, Object> subService = subServices.get(j);
						String subServiceGrpID = BLogicUtils.emptyValue(subService.get(B_SSM_S04_FieldSet.FIELD_SERVICEGROUPID), "");
						if (subServiceGrpID!=null && serviceGrpID.trim().equals(subServiceGrpID.trim())) {
							String subServicename =  BLogicUtils.emptyValue(subService.get(B_SSM_S04_FieldSet.FIELD_SERVICENAME), "");
							String subPlanDes =  BLogicUtils.emptyValue(subService.get(B_SSM_S04_FieldSet.FIELD_PLANDESCRIPTION), "");
							subPlanDes = desLength == -1? 
										 subPlanDes :
										 CommonUtils.toString(CommonUtils.formatString(subPlanDes, desLength));
							filterService = new HashMap<String, Object>();
							filterService.put("serviceName", subServicename);
							filterService.put("serviceDesc", subPlanDes);
							filterService.put("dataType", "detail");
							filterServices.add(filterService);
						}
					}
				}
			}
			logicOutput.put(B_SSM_S04_FieldSet.FIELD_SERVICENAMELIST, filterServices);
		}
	}
}
