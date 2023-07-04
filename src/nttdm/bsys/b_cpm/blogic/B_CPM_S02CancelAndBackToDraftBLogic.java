/**
 * @(#)B_CPM_S02CancelAndBackToDraftBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/05/22
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_cpm.blogic;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionErrors;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CPM_P04;

/**
 * @author gplai
 *
 */
public class B_CPM_S02CancelAndBackToDraftBLogic extends AbstractB_CPM_S02UpdateServiceStatusBLogic {

    private static final String CANCEL = "cancel";
    private static final String DRAFT = "draft";
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(HashMap<String, Object> param) {
        BLogicResult result = new BLogicResult();
        BLogicMessages errors = new BLogicMessages();
        Map<String, Object> resultObject = new HashMap<String, Object>();
        
        String action = CommonUtils.toString(param.get("action"));
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)param.get("uvo");
        CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
        String idCustPlan = customerPlan.getIdCustPlan();
        
        B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
        
        if (CANCEL.equals(action)) {
            Map<String, Object> subscriptionInfoMap = util.getSubScriptionInfoByIdCustPlan(idCustPlan);
            if (subscriptionInfoMap!=null) {
                String accessAccountTest = CommonUtils.toString(subscriptionInfoMap.get("ACCESS_ACCOUNT_TEST"));
                if("1".equals(accessAccountTest)) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Connection test in progress. Please click Test Complete button before Cancellation at subscription information maintenance."}));
                    resultObject.put("customerPlan", customerPlan);
                    result.setErrors(errors);
                    result.setResultObject(resultObject);
                    result.setResultString("failure");
                    return result;
                }
            }
        }
        
        String serviceStatus = "";
        
        if (CANCEL.equals(action)) {
            serviceStatus = "PS0";
        } else if (DRAFT.equals(action)) {
            serviceStatus = "PS1";
        }
        
        Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CPM, idCustPlan, serviceStatus, BillingSystemConstants.AUDIT_TRAIL_EDITED);
        
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idCustPlan", idCustPlan);
        sqlParam.put("idLogin", uvo.getId_user());
        sqlParam.put("idAudit", idAudit);
        sqlParam.put("serviceStatus", serviceStatus);
        sqlParam.put("planStatus", serviceStatus);
        
        util.updateT_CUST_PLAN_D_ServiceStatus(sqlParam);
        util.updateT_CUST_PLAN_H_PlanStatus(sqlParam);
        
        G_CPM_P04 gCpmP04 = new G_CPM_P04(this.queryDAO, this.updateDAO);
        gCpmP04.setIdAudit(idAudit);
        gCpmP04.execute(idCustPlan);
        
        //End Audit Trail
        CommonUtils.auditTrailEnd(idAudit);
        
        resultObject.put("customerPlan", customerPlan);
        
        result.setErrors(errors);
        result.setResultObject(resultObject);
        result.setResultString("success");
        return result;
    }

}
