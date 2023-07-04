/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVT
 * SERVICE NAME   : M_SVT_S01
 * FUNCTION       : Updating BLogic
 * FILE NAME      : M_SVT01_SaveBLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svt.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.*;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_svt.dto.*;
import java.util.*;

import nttdm.bsys.m_svt.blogic.AbstractM_SVT01_SaveBLogic;

/**
 * M_SVT01_SaveBLogic<br>
 * <ul>
 * <li>A BLogic class to process updating services and plans
 * </ul>
 * <br>
* @author  hungtm
* @version 1.0
 */
public class M_SVT01_SaveBLogic extends AbstractM_SVT01_SaveBLogic {


	public BLogicResult execute(M_SVT01IO input) {
		BLogicResult result = new BLogicResult();
		M_SVT01IO output = new M_SVT01IO();
		output.setTabValue(input.getTabValue());
		BLogicMessages msg = new BLogicMessages();
		String idLogin = input.getIdLogin();
		String serviceGroup = input.getChoosed();
		List<PlanService> listPlanService = input.getListPlanService();
		List<PlanService> listPlanServiceCopy = new ArrayList<PlanService>(listPlanService);
		ArrayList<String> errMsgList = new ArrayList<String>();
		
		try{
			// Checking for duplicate values
			for(int i = 0; i < listPlanService.size();i++){
				
				PlanService planService1 = listPlanService.get(i);
				planService1.setIdLogin(idLogin);
				planService1.setServiceGroup(serviceGroup);
				int counter = 0;
				for(int j = 0; j < listPlanService.size(); j++){
						
					PlanService planService2 = listPlanServiceCopy.get(j);
					
					if (planService1.getServiceCategory().equals(planService2.getServiceCategory())){
					
						if(planService1.getServiceDescription().toLowerCase().trim().equals(planService2.getServiceDescription().toLowerCase().trim()) && 
								planService1.getAccCode().trim().equals(planService2.getAccCode().trim()) && 
								planService1.getDescAbbr().trim().equals(planService2.getDescAbbr().trim()) &&
								planService1.getGst().equals(planService2.getGst())){
							
							counter = counter + 1; 
							if(counter > 1){
								String dispTab = null;
								if (planService2.getServiceCategory().equals("TYP"))
									dispTab = "Service Tab";
								else if (planService2.getServiceCategory().equals("ITM"))
									dispTab = "Plan Tab";
								else
									dispTab = "Plan Detail Tab";
											
								errMsgList.add(dispTab + " - " + planService2.getServiceDescription().trim());
							}
						}
					}										
				}				
			}	
			if(!errMsgList.isEmpty()){
				
				HashSet<String> errMsgHashSet = new HashSet<String>(errMsgList);
		     	List<String> errMsgList2 = new ArrayList<String>(errMsgHashSet);
		     	
		     	Comparator<String> comparator = new Comparator<String>(){
		     	 	   public int compare(String ps1, String ps2) {
		     	 		   
		     	 		 String dispMsg1 = ps1.trim();
		     	 		 String dispMsg2 = ps2.trim();
	      	 		   
		     	 		 if (dispMsg1 != dispMsg2) {
	     	 			   return dispMsg1.compareTo(dispMsg2);
		     	 		 } else {
			 			   return 0;
		     	 		 }  	 		   
		     	 	   }
		     	};
		     	Collections.sort(errMsgList2, comparator);
		     	Collections.reverse(errMsgList2);
		     	 
			    for (Object errMsgDisp : errMsgList2){
			    msg.add(Globals.ERROR_KEY,new BLogicMessage("errors.ERR1SC006", errMsgDisp));
				result.setErrors(msg);
			    }
			    output.setListPlanService(listPlanService);
				result.setResultObject(output);
				result.setResultString("failure");
				return result;
				}
			else{
				for(int i = 0; i < listPlanService.size();i++){
					PlanService planService1 = listPlanService.get(i);
					planService1.setIdLogin(idLogin);
					planService1.setServiceGroup(serviceGroup);
					//If it's idServices doesn't exist then insert it
					if(planService1.getIdService().equals("")){
						insertNewPlanService(planService1);
					} else if(planService1.getIsUsed().equals("0")){
						updatePlanService(planService1);
					}
				}
			}
				
			
			//delete plan service
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("idLogin",idLogin);
			map.put("serviceGroup", serviceGroup);
			map.put("listPlanService", listPlanService);
			//Audit Trail
			List<Map<String, Object>> svcDeleteds = queryDAO.executeForMapList("SELECT.M_SVT.GET_SVC_DELETED", map);
			String svcDesc = "";
			if(!svcDeleteds.isEmpty()) {
				for(Map<String, Object> svcDeleted : svcDeleteds) {
					svcDesc += ("," + CommonUtils.toString(svcDeleted.get("SVC_DESC")));
				}
			}
			if(!svcDesc.equals("")) svcDesc = svcDesc.substring(1);
			Integer idAudit = CommonUtils.auditTrailBegin(idLogin, BillingSystemConstants.MODULE_M, 
					BillingSystemConstants.SUB_MODULE_M_SVT, serviceGroup+":"+svcDesc, null, BillingSystemConstants.AUDIT_TRAIL_DELETED);
			map.put("idAudit", idAudit);
			
			updateDAO.execute("UPDATE.DELETE.M_SVT.M_SVC", map);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			
			Comparator<PlanService> comparator = new Comparator<PlanService>(){
	     	 	   public int compare(PlanService ps1, PlanService ps2) {
	     	 		   
	     	 		 String servDesc1 = ps1.getServiceDescription().trim();
	     	 		 String servDesc2 = ps2.getServiceDescription().trim();
      	 		   
	     	 		 if (servDesc1 != servDesc2) {
     	 			   return servDesc1.toLowerCase().compareTo(servDesc2.toLowerCase());
	     	 		 } else {
		 			   return ps1.getAccCode().trim().toLowerCase().compareTo(ps2.getAccCode().trim().toLowerCase());
	     	 		 }  	 		   
	     	 	   }
	     	};
	     	Collections.sort(listPlanService, comparator);
			
			msg.add(Globals.MESSAGE_KEY,new BLogicMessage("info.ERR2SC003"));
			result.setMessages(msg);
		}
		catch (Exception e){
			e.printStackTrace();
			msg.add(Globals.ERROR_KEY,new BLogicMessage("info.ERR2SC004"));
			result.setErrors(msg);
		}
		output.setListPlanService(listPlanService);
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
	public void updatePlanService(PlanService planService){
		Integer idAudit = writeAuditTrail1(planService, BillingSystemConstants.AUDIT_TRAIL_EDITED);
		updateDAO.execute("UPDATE.M_SVT.M_SVC", planService);
		CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
	}
	public void insertNewPlanService(PlanService planService){
		String idService = queryDAO.executeForObject("SELECT.M_SVT.GET_NEW_IDSERVICE",null, String.class);
		planService.setIdService(idService);
		Integer idAudit = writeAuditTrail1(planService, BillingSystemConstants.AUDIT_TRAIL_CREATED);
		updateDAO.execute("INSERT.M_SVT.M_SVC", planService);
		CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
	}
	
	/**
	 * Audit Trail
	 */
	private Integer writeAuditTrail1(PlanService planService, String action) {
		Integer idAudit = CommonUtils.auditTrailBegin(planService.getIdLogin(), BillingSystemConstants.MODULE_M, 
				BillingSystemConstants.SUB_MODULE_M_SVT, planService.getServiceGroup()+":"+planService.getServiceDescription(), null, action);
		planService.setIdAudit(idAudit);
		return idAudit;
	}
}