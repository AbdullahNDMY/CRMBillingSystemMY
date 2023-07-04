/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (dto)
 * SERVICE NAME   : B_CPM
 * FUNCTION       : common function for B-CPM
 * FILE NAME      : B_CPM_Common.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/09/15 [Duong Nguyen] Fix B-CPM-S02v #1.0
 * 2011/10/24 [Duong Nguyen] Fix Bug #2827
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

import java.sql.Clob;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_Common.class<br>
 * <ul>
 * <li>load search screen inquiry</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_Common {
	/**
	 * 
	 * @param customerPlan
	 * @return
	 */
	public static CustomerPlan restructureCustomerPlan(CustomerPlan customerPlan, boolean isView) {
		if (customerPlan.getBillAccNo() != null && !customerPlan.getBillAccNo().trim().equals("NEW")) {
			customerPlan.setBillAccNo(customerPlan.getBillAccNo().trim());
			customerPlan.setNewAcc("");
		} else {
			customerPlan.setNewAcc("");
			customerPlan.setBillAccNo("");
		}
		if (isView) {
			customerPlan.setReferencePlan(setEmptyValue(customerPlan.getReferencePlan()));
			customerPlan.setExpGrandTotal(setEmptyValue(customerPlan.getExpGrandTotal()));
			//check empty value
			for (CustomerPlanService service : customerPlan.getServices()) {
				service.setServiceDateStart(setEmptyValue(service.getServiceDateStart()));
				service.setServiceDateEnd(setEmptyValue(service.getServiceDateEnd()));
				service.setMinimumServiceFrom(setEmptyValue(service.getMinimumServiceFrom()));
				service.setMinimumServiceTo(setEmptyValue(service.getMinimumServiceTo()));
				for (CustomerSubPlan subPlan : service.getSubPlans()) {
					subPlan.setJobNo(setEmptyValue(subPlan.getJobNo()));
				}
			}
		}
		
		return customerPlan;
	}
	
	public static String setEmptyValue(String value) {
		String returnValue = "";
		if (value == null || value.equals("")) {
			returnValue = "-";
		} else {
			returnValue = value;
		}
		return returnValue;
	}
	
	/**
	 * 
	 * @param customerPlan
	 * @param uvo
	 * @return
	 */
	public static CustomerPlan restructureCustomerPlan(CustomerPlan customerPlan, BillingSystemUserValueObject uvo, String idCustPlan) {
		String tempIdCustPlan = CommonUtils.toString(customerPlan.getIdCustPlan()).trim();
		String newOrEditFlag = "";
		if (CommonUtils.isEmpty(tempIdCustPlan)){
			//new
			newOrEditFlag = BillingSystemConstants.AUDIT_TRAIL_CREATED;
		} else {
			//edit
			newOrEditFlag = BillingSystemConstants.AUDIT_TRAIL_EDITED;
		}
		Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CPM, 
		        idCustPlan, customerPlan.getPlanStatus(), newOrEditFlag);
		customerPlan.setIdAudit(idAudit.toString());
		customerPlan.setIdLogin(uvo.getId_user());
		if (customerPlan.getBillAccNo() == null || customerPlan.getBillAccNo().equals("")) {
			customerPlan.setBillAccNo(customerPlan.getNewAcc());
		}
		
		for (CustomerPlanService service : customerPlan.getServices()) {
			service.setIdAudit(customerPlan.getIdAudit());
			service.setIdLogin(customerPlan.getIdLogin());
			String minService = service.getMinimumService();
			String contractTerm = service.getContactTerm();
			String autoRenew = service.getAutoRenewal();
			
			if (minService == null || minService.equals("")) {
				service.setMinimumService("0");
			}
			
			if (contractTerm == null || contractTerm.equals("")) {
				service.setContactTerm("Y");
			}
			
			if (autoRenew == null || autoRenew.equals("")) {
				service.setAutoRenewal("0");
			}
			
			for (CustomerSubPlan subPlan : service.getSubPlans()) {
				subPlan.setIdAudit(customerPlan.getIdAudit());
				subPlan.setIdLogin(customerPlan.getIdLogin());
				for (CustomerSubPlanDetail subPlanDetail : subPlan.getSubPlanDetails()) {
					subPlanDetail.setIdAudit(customerPlan.getIdAudit());
					subPlanDetail.setIdLogin(customerPlan.getIdLogin());
				}
			}
		}
		return customerPlan;
	}
	
	/**
	 * 
	 * @param clob
	 * @return
	 */
	public static String convertClobToString(Clob clob) {
		String toRet = "";
		if(clob!=null) {
			try {
				long length=clob.length();
				toRet=clob.getSubString(1, (int)length);          
			} catch(Exception ex) {
				ex.printStackTrace();
			}      
		}      
		return toRet; 
	}
	
	public static String convertObjectToString(Object obj) {
		String value ="";
		if (obj != null) {
			value = obj.toString();
		}
		return value;
	}
}
