/**
 * @(#)COMMONChangeAccessAccountBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/06/04
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts.action.ActionErrors;

import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.RadUserTUtil;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * @author gplai
 *
 */
public class COMMONAPWSaveBLogic implements BLogic<HashMap<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;
    
    /**
     * radiusQueryDAO
     */
    protected QueryDAO radiusQueryDAO;

    /**
     * radiusUpdateDAO
     */
    protected UpdateDAO radiusUpdateDAO;
    
    private BLogicMessages errors;
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(HashMap<String, Object> params) {
        BLogicResult result = new BLogicResult();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        
        copyParam(params, output);
        
        String idSubInfo = CommonUtils.toString(params.get("idSubInfo")).trim();
        String accessAccount = CommonUtils.toString(params.get("accessAccount")).trim();
        String accessPw = CommonUtils.toString(params.get("accessPw")).trim();
        
        RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
        
        List<Map<String, Object>> activePlanBatchMappingList = queryDAO.executeForMapList("COMMON.GET_BATCH_MAPPING_BY_SUB_INFO", idSubInfo);
        if(activePlanBatchMappingList!=null && 0<activePlanBatchMappingList.size()) {
            RAD_USERS_T radUserST = radUserTUtil.selectByPK(accessAccount);
            if (radUserST==null) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC106", new Object[] {}));
                result.setErrors(errors);
                result.setResultObject(output);
                result.setResultString("success");
                return result;
            }
        }
        //get idLogin
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)params.get("uvo");
        
        //Modify Reference and Status
        Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", idSubInfo);
        String planStatus = "";
        if (mapCustomerPlanH!=null) {
            planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
        }
        int idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_SSM, idSubInfo, planStatus, BillingSystemConstants.AUDIT_TRAIL_EDITED);
        
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idLogin", uvo.getId_user());
        sqlParam.put("idAudit", idAudit);
        sqlParam.put("idSubInfo", idSubInfo);
        sqlParam.put("accessPw", accessPw);
        
        //update B_SSM
        updateDAO.execute("COMMON.UPDATE_COMMON_POPAPW_T_SUBSCRIPTION_INFO", sqlParam);
        if(activePlanBatchMappingList!=null && 0<activePlanBatchMappingList.size()) {
            //update radius
            HashMap<String, Object> radiusSqlParam = new HashMap<String, Object>();
            radiusSqlParam.put("accessAccount", accessAccount);
            radiusSqlParam.put("accessPassword", accessPw);
            radUserTUtil.updateRadiusPassword(radiusSqlParam);
        }
        
        output.put("resultType", "success");
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

    private void copyParam(Map<String, Object> input, 
            Map<String, Object> output) {
        Set<String> keys = input.keySet();
        for (String key : keys) {
            output.put(key, input.get(key));
        }
    }
    
    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * @param updateDAO the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * @return the radiusQueryDAO
     */
    public QueryDAO getRadiusQueryDAO() {
        return radiusQueryDAO;
    }

    /**
     * @param radiusQueryDAO the radiusQueryDAO to set
     */
    public void setRadiusQueryDAO(QueryDAO radiusQueryDAO) {
        this.radiusQueryDAO = radiusQueryDAO;
    }

    /**
     * @return the radiusUpdateDAO
     */
    public UpdateDAO getRadiusUpdateDAO() {
        return radiusUpdateDAO;
    }

    /**
     * @param radiusUpdateDAO the radiusUpdateDAO to set
     */
    public void setRadiusUpdateDAO(UpdateDAO radiusUpdateDAO) {
        this.radiusUpdateDAO = radiusUpdateDAO;
    }
}
