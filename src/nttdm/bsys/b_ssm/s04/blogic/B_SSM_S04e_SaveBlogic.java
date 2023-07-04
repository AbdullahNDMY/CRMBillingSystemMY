/**
 * @(#)B_SSM_S04e_InitBlogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/23
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_ssm.s04.blogic;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author gplai
 *
 */
public class B_SSM_S04e_SaveBlogic extends B_SSM_S04BLogic<HashMap<String, Object>> {
    
    /**
     * @param input Map<String, Object>
     * @return BLogicResult
     */
    public BLogicResult execute(HashMap<String, Object> input) {
        BLogicResult result = new BLogicResult();
        BLogicMessages messages = new BLogicMessages();
        BLogicMessages errors = new BLogicMessages();
        HashMap<String, Object> output = new HashMap<String, Object>();
        
        // BillingSystemUserValueObject
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) 
            input.get("uvo");
        // login user id
        String idUser = uvo.getId_user();
        
        // Copy first properties to output
        BLogicUtils.copyProperties(output, input);
     
        String serviceIds = CommonUtils.toString(input.get("serviceIds"));
        String completionDates = CommonUtils.toString(input.get("completionDates"));
        if(!CommonUtils.isEmpty(serviceIds)) {
            // Audit Trail
            Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", CommonUtils.toString(input.get("subscriptionID")));
            String planStatus = "";
            if (mapCustomerPlanH!=null) {
                planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
            }
            Integer idAudit = CommonUtils.auditTrailBegin
                (idUser, BillingSystemConstants.MODULE_B,
                            BillingSystemConstants.SUB_MODULE_B_SSM, CommonUtils.toString(input.get("subscriptionID")),
                            planStatus, BillingSystemConstants.AUDIT_TRAIL_EDITED);
            
            String[] serviceIdArr = serviceIds.split(",");
            String[] completionDateArr = completionDates.split(",");
            Map<String, Object> mapSqlParam = new HashMap<String, Object>();
            mapSqlParam.put("idUser", idUser);
            mapSqlParam.put("idAudit", idAudit);
            for (int i=0;i<serviceIdArr.length;i++) {
                serviceIdArr[i] = serviceIdArr[i].trim();
                completionDateArr[i] = completionDateArr[i].trim();
            }
            for (int i=0;i<serviceIdArr.length;i++) {
                if(!CommonUtils.isEmpty(serviceIdArr[i])) {
                    mapSqlParam.put("idCustPlanGrp", serviceIdArr[i]);
                    Date completionDate = null;
                    if(!CommonUtils.isEmpty(completionDateArr[i])) {
                        completionDate = CommonUtils.toDate(completionDateArr[i],"dd/MM/yyyy");
                    }
                    mapSqlParam.put("completionDate", completionDate);
                    //update CompletionDate
                    updateDAO.execute("B_SSM_S04.updateCompletionDate", mapSqlParam);
                }
            }
            CommonUtils.auditTrailEnd(idAudit);
        }
        
        messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC003"));
        result.setResultObject(output);
        result.setResultString("success");
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }
}
