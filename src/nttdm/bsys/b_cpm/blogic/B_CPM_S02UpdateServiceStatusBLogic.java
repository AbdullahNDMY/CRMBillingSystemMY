/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management B-CPM-S02v (Update status)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Update status when suspend and unsuspend in B-CPM-S02v
 * FILE NAME      : B_CPM_S02UpdateServiceStatusBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/10/12 [Duong Nguyen] Update DD change #2761
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CPM_P04;
import nttdm.bsys.common.util.RadUserTUtil;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;

/**
 * B_CPM_S02UpdateServiceStatusBLogic.class<br>
 * <ul>
 * <li>update service status to suspend/unsuspend</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02UpdateServiceStatusBLogic extends
		AbstractB_CPM_S02UpdateServiceStatusBLogic {

    private BLogicMessages errors;
    
	/**
	 * 
	 * @param param
	 * @return ??????????????BLogicResult???????
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();
		
		//get action of to update
		String action = (String)param.get("action");
//		String accessSubmit = (String)param.get("accessSubmit");
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)param.get("uvo");
		CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
		String planStatus = "";
		
		if (action.equals(B_CPM_CONSTANT.ACTION_SUSPEND)) {
            planStatus = "PS6";
        } else if (action.equals(B_CPM_CONSTANT.ACTION_UNSUSPEND)) {
            planStatus = "PS3";
        }
		try {
            Map<String, Object> resultObject = new HashMap<String, Object>();
            String idCustPlan = customerPlan.getIdCustPlan();

           B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
//           Map<String, Object> map= util.getAccessAccount(idCustPlan);
//           if(map.get("ACCESS_ACCOUNT")!=null&&!"".equals(map.get("ACCESS_ACCOUNT"))){
//           String  accessAccount = String.valueOf(map.get("ACCESS_ACCOUNT"));
//           String  accessPw = String.valueOf(map.get("ACCESS_PW"));
//           
//            if (util.isExistPlanGrp(idCustPlan)) {
//
//                if (radUserTUtil.isExistUserName(accessAccount)) {
//                    radUserTUtil.updateRadiusStatus(accessAccount,status);
//                }else{
//                    if(StringUtils.isEmpty(accessSubmit)){
//                    resultObject.put("customerPlan", customerPlan);
//                    resultObject.put("accessSubmit", action);
//                    result.setResultObject(resultObject);
//                    result.setResultString("pause");
//                    return result;
//                    }else{
//                        RAD_USERS_T radUserT = new RAD_USERS_T();
//                        radUserT.setUserName(accessAccount);
//                        radUserT.setPassword(accessPw);
//                        radUserT.setExpireDate("1970-01-01 09:00:00");
//                        radUserT.setCreditTime("-1");
//                        radUserT.setStatus("0");
//                        radUserT.setPlanId("0");
//                        radUserT.setAuthMethod("0");
//                        radUserT.setAttrbuteRule("0");
//                        radUserT.setModifyCounter("0");
//                        radUserTUtil.insert(radUserT);
//                    }
//                }
//               
//            }
//		}
           
           Map<String, Object> mapParam = new HashMap<String, Object>();
           if (radiusCheck(customerPlan.getIdCustPlan(), action, util, mapParam)) {
           Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, 
                   BillingSystemConstants.SUB_MODULE_B_CPM, customerPlan.getIdCustPlan(), 
                       planStatus, BillingSystemConstants.AUDIT_TRAIL_EDITED);
           
           Map<String, Object> input = new HashMap<String, Object>();
           input.put("ID_LOGIN",uvo.getId_user());
           input.put("ID_AUDIT", idAudit);
           if (action.equals(B_CPM_CONSTANT.ACTION_SUSPEND)) {
               input.put("SERVICE_STATUS", "PS6");
               input.put("ID_CUST_PLAN", idCustPlan);
               util.updateServiceStatus(input);
           } else if (action.equals(B_CPM_CONSTANT.ACTION_UNSUSPEND)) {
               List<CustomerPlanService> serviceList = util.getCustomerPlanService(customerPlan);
               if (serviceList!=null && 0 < serviceList.size()) {
                   for(CustomerPlanService service : serviceList) {
                       String svcEnd = CommonUtils.toString(service.getServiceDateEnd()).trim();
                       if (CommonUtils.isEmpty(svcEnd)) {
                           input.put("SERVICE_STATUS", "PS3");
                       } else {
                           Date svcEndDate = CommonUtils.toDate(svcEnd, "dd/MM/yyyy");
                           Date sysdate = CommonUtils.toDate(CommonUtils.formatDate(new Date(), "dd/MM/yyyy"), "dd/MM/yyyy");
                           if(svcEndDate.compareTo(sysdate)>=0) {
                               input.put("SERVICE_STATUS", "PS3");
                           } else {
                               input.put("SERVICE_STATUS", "PS7");
                           }
                       }
                       input.put("ID_CUST_PLAN_GRP", service.getIdCustPlanGrp());
                       util.updateServiceStatusUnsuspend(input);
                   }
               }
           }
           
			//call process G-CPM-P04
			/** Update DD change #2761 start **/

			G_CPM_P04 gCpmP04 = new G_CPM_P04(this.queryDAO, this.updateDAO);
			gCpmP04.setIdAudit(idAudit);
			gCpmP04.execute(idCustPlan);
                
            RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
            if (action.equals(B_CPM_CONSTANT.ACTION_SUSPEND)) {
                boolean isRadius = (Boolean)mapParam.get("isRadius");
                if (isRadius) {
                    String accessAccount = CommonUtils.toString(mapParam.get("accessAccount"));
                    radUserTUtil.updateRadiusStatus(accessAccount, "1");
                }
            } else if (action.equals(B_CPM_CONSTANT.ACTION_UNSUSPEND)) {
                boolean isRadius = (Boolean)mapParam.get("isRadius");
                if (isRadius) {
                    String accessAccount = CommonUtils.toString(mapParam.get("accessAccount"));
                    Map<String, Object> sqlParam = new HashMap<String, Object>();
                    sqlParam.put("idCustPlan", idCustPlan);
                    sqlParam.put("serviceStatus", "PS3");
                    List<Map<String, Object>> mapActiveServicePlanBatchMapping = util.getActiveServicePlanBatchMappingByIdCustPlan(sqlParam);
                    if (mapActiveServicePlanBatchMapping != null && 0 < mapActiveServicePlanBatchMapping.size()){
                        radUserTUtil.updateRadiusStatus(accessAccount, "0");
                    } else {
                        radUserTUtil.deleteByUserName(accessAccount);
                    }
                }
            }
            
            //setting message result
            BLogicMessages paramMessages= new BLogicMessages();
            if (action.equals(B_CPM_CONSTANT.ACTION_SUSPEND)) {
                paramMessages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC007","Suspend"));
            } else if (action.equals(B_CPM_CONSTANT.ACTION_UNSUSPEND)) {
                paramMessages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC007","Unsuspend"));
            }
            result.setMessages(paramMessages);
			/** Update DD change #2761 end **/
			
			CommonUtils.auditTrailEnd(idAudit);
           } else {
               result.setErrors(errors);
           }
			
			resultObject.put("customerPlan", customerPlan);
			resultObject.put("accessSubmit", "");
			
			result.setResultObject(resultObject);
			result.setResultString("success");
			return result;
		} catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(B_CPM_CONSTANT.SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}

	private boolean radiusCheck(String idCustPlan, String action, B_CPM_S02Util util, Map<String, Object> mapParam) {
	    boolean result = true;
	    List<Map<String, Object>> mapPlanBatchMapping = util.getPlanBatchMappingByIdCustPlan(idCustPlan);
        if(mapPlanBatchMapping != null && 0 < mapPlanBatchMapping.size()){
            String serviceStatus = "";
            if (action.equals(B_CPM_CONSTANT.ACTION_SUSPEND)) {
                serviceStatus = "PS3";
            } else if (action.equals(B_CPM_CONSTANT.ACTION_UNSUSPEND)) {
                serviceStatus = "PS6";
            }
            Map<String, Object> sqlParam = new HashMap<String, Object>();
            sqlParam.put("idCustPlan", idCustPlan);
            sqlParam.put("serviceStatus", serviceStatus);
            List<Map<String, Object>> mapActiveServicePlanBatchMapping = util.getActiveServicePlanBatchMappingByIdCustPlan(sqlParam);
            if(mapActiveServicePlanBatchMapping != null && 0 < mapActiveServicePlanBatchMapping.size()){
                Map<String, Object> subScriptionInfo = util.getSubScriptionInfoByIdCustPlan(idCustPlan);
                //ACCESS_ACCOUNT
                String accessAccount = "";
                if (subScriptionInfo != null) {
                    //ACCESS_ACCOUNT
                    accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                    RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                    //select by PK
                    RAD_USERS_T radUserT = radUserTUtil.selectByPK(accessAccount);
                    if (radUserT == null) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC106", new Object[] {}));
                        return false;
                    }
                } else {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC106", new Object[] {}));
                    return false;
                }
                mapParam.put("accessAccount", accessAccount);
                mapParam.put("isRadius", true);
            } else {
                mapParam.put("isRadius", false);
            }
        } else {
            mapParam.put("isRadius", false);
        }
	    
	    return result;
	}
}
