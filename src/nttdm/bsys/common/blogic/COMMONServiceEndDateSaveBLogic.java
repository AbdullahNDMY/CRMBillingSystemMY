/**
 * @(#)COMMONServiceEndDate.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/05/21
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.blogic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts.action.ActionErrors;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CPM_P04;
import nttdm.bsys.common.util.RadUserTUtil;

/**
 * @author gplai
 *
 */
public class COMMONServiceEndDateSaveBLogic implements BLogic<HashMap<String, Object>> {

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
    
    private static final String RADIUS_YES = "1";
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(HashMap<String, Object> params) {
        BLogicResult result = new BLogicResult();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        this.copyParam(params, output);
        String sysdate = CommonUtils.formatDate(new Date(), "dd/MM/yyyy");
        output.put("sysdate", sysdate);
        
        String idCustPlanGrp = CommonUtils.toString(params.get("idCustPlanGrp")).trim();
        String idCustPlan = CommonUtils.toString(params.get("idCustPlan")).trim();
        String svcEndDate = CommonUtils.toString(params.get("svcEndDate")).trim();
        String serviceStatus = CommonUtils.toString(params.get("serviceStatus")).trim();
        String preServiceStatus = CommonUtils.toString(params.get("preServiceStatus")).trim();
        
        String clickRadiusYesFlg = CommonUtils.toString(params.get("clickRadiusYesFlg")).trim();
        //not click yes
        if(CommonUtils.isEmpty(clickRadiusYesFlg)) {
            //Sub 1 check
            if(CommonUtils.isEmpty(svcEndDate) ||
                    (!CommonUtils.isEmpty(svcEndDate) 
                            && CommonUtils.toDate(svcEndDate, "dd/MM/yyyy").compareTo(CommonUtils.toDate(sysdate, "dd/MM/yyyy"))>=0)) {
                List<Map<String, Object>> planBatchMappingList = queryDAO.executeForMapList("COMMON.GET_BATCH_MAPPING_BY_ID_CUST_PLAN_GRP", idCustPlanGrp);
                if (planBatchMappingList!=null && 0<planBatchMappingList.size()) {
                    //from Terminated to Active
                    if("PS7".equals(preServiceStatus) && "PS3".equals(serviceStatus)) {
                        Map<String, Object> sqlParma = new HashMap<String, Object>();
                        sqlParma.put("idCustPlanGrp", idCustPlanGrp);
                        sqlParma.put("idCustPlan", idCustPlan);
                        sqlParma.put("serviceStatus", "PS3");
                        List<Map<String, Object>> otherServicePlanBatchMappingList = queryDAO.executeForMapList("COMMON.GET_BATCH_MAPPING_BY_ID_CUST_PLAN_GRP_AND_SERVICE_STATUS", sqlParma);
                        if (otherServicePlanBatchMappingList==null || otherServicePlanBatchMappingList.size()<=0) {
                            Map<String, Object> subScriptionInfo = queryDAO.executeForMap("COMMON.GET_SUBSCIPTION_BY_ID_CUST_PLAN", idCustPlan);
                            if (subScriptionInfo!=null) {
                                //ACCESS_ACCOUNT
                                String accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                                if (CommonUtils.isEmpty(accessAccount)) {
                                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Access Account and/or Access Password for Subscription Information does not exist. Please create before proceed to reactivation."}));
                                    result.setErrors(errors);
                                    result.setResultObject(output);
                                    result.setResultString("success");
                                    return result;
                                }
                                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                                //select by PK
                                RAD_USERS_T radUserT = radUserTUtil.selectByPK(accessAccount);
                                if (radUserT != null) {
                                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC067", new Object[] {}));
                                    result.setErrors(errors);
                                    result.setResultObject(output);
                                    result.setResultString("success");
                                    return result;
                                } else {
                                    output.put("isRadius", RADIUS_YES);
                                    output.put("radiusConfirmFlg", "1");
                                    result.setResultObject(output);
                                    result.setResultString("success");
                                    return result;
                                }
                            } else {
                                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Access Account and/or Access Password for Subscription Information does not exist. Please create before proceed to reactivation."}));
                                result.setErrors(errors);
                                result.setResultObject(output);
                                result.setResultString("success");
                                return result;
                            }
                        }
                    }
                }
            } else {
                // Sub 2 check
                List<Map<String, Object>> planBatchMappingList = queryDAO.executeForMapList("COMMON.GET_BATCH_MAPPING_BY_ID_CUST_PLAN_GRP", idCustPlanGrp);
                if (planBatchMappingList!=null && 0<planBatchMappingList.size()) {
                    //from Active to Terminated
                    if("PS3".equals(preServiceStatus) && "PS7".equals(serviceStatus)) {
                        Map<String, Object> sqlParma = new HashMap<String, Object>();
                        sqlParma.put("idCustPlanGrp", idCustPlanGrp);
                        sqlParma.put("idCustPlan", idCustPlan);
                        List<Map<String, Object>> otherServicePlanBatchMappingList = queryDAO.executeForMapList("COMMON.GET_BATCH_MAPPING_BY_ID_CUST_PLAN_GRP_AND_SERVICE_STATUS", sqlParma);
                        boolean allServiceIsTerminated = true;
                        if (planBatchMappingList!=null && 0<planBatchMappingList.size()) {
                            for (Map<String, Object> map : otherServicePlanBatchMappingList) {
                                String onceServiceStatus = CommonUtils.toString(map.get("SERVICES_STATUS"));
                                if(!"PS7".equals(onceServiceStatus)) {
                                    allServiceIsTerminated = false;
                                    break;
                                }
                            }
                        }
                        if (allServiceIsTerminated) {
                            Map<String, Object> subScriptionInfo = queryDAO.executeForMap("COMMON.GET_SUBSCIPTION_BY_ID_CUST_PLAN", idCustPlan);
                            if (subScriptionInfo!=null) {
                                //ACCESS_ACCOUNT
                                String accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                                //select by PK
                                RAD_USERS_T radUserT = radUserTUtil.selectByPK(accessAccount);
                                if (radUserT==null) {
                                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC106", new Object[] {}));
                                    result.setErrors(errors);
                                    result.setResultObject(output);
                                    result.setResultString("success");
                                    return result;
                                } else {
                                    output.put("isRadius", RADIUS_YES);
                                    output.put("radiusConfirmFlg", "2");
                                    result.setResultObject(output);
                                    result.setResultString("success");
                                    return result;
                                }
                            }
                        }
                    }
                }
            }
        }
        //click yes
        output.put("radiusConfirmFlg", "");
        
        //get idLogin
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)params.get("uvo");
        
        String planStatus = "";
        Map<String, Object> mapCustPlanService = queryDAO.executeForMap("COMMON.SELECT_COMMON_SERVICE_END_DATE_GET_T_CUST_PLAN_D", idCustPlanGrp);
        if (mapCustPlanService!=null) {
            planStatus = CommonUtils.toString(mapCustPlanService.get("PLAN_STATUS"));
        }
        Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CPM, idCustPlan, planStatus, BillingSystemConstants.AUDIT_TRAIL_EDITED);
        
        String autoRenew = "0";
        if (CommonUtils.isEmpty(svcEndDate)) {
            autoRenew = "1";
        }
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idCustPlanGrp", idCustPlanGrp);
        sqlParam.put("serviceStatus", serviceStatus);
        sqlParam.put("svcEndDate", svcEndDate);
        sqlParam.put("autoRenew", autoRenew);
        sqlParam.put("idLogin", uvo.getId_user());
        sqlParam.put("idAudit", idAudit);
        
        updateDAO.execute("COMMON.UPDATE_COMMON_SERVICE_END_DATE_T_CUST_PLAN_D", sqlParam);
        //Sub 1 update DB
        if(CommonUtils.isEmpty(svcEndDate) ||
                (!CommonUtils.isEmpty(svcEndDate) 
                        && CommonUtils.toDate(svcEndDate, "dd/MM/yyyy").compareTo(CommonUtils.toDate(sysdate, "dd/MM/yyyy"))>=0)) {
            String isRadius = CommonUtils.toString(params.get("isRadius")).trim();
            if (RADIUS_YES.equals(isRadius)) {
                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                Map<String, Object> subScriptionInfo = queryDAO.executeForMap("COMMON.GET_SUBSCIPTION_BY_ID_CUST_PLAN", idCustPlan);
                String accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                String accessPw = CommonUtils.toString(subScriptionInfo.get("ACCESS_PW"));
                RAD_USERS_T radUserT = new RAD_USERS_T();
                radUserT.setUserName(accessAccount);
                radUserT.setPassword(accessPw);
                radUserT.setExpireDate("1970-01-01 09:00:00");
                radUserT.setCreditTime("-1");
                radUserT.setStatus("0");
                radUserT.setPlanId("0");
                radUserT.setAuthMethod("0");
                radUserT.setAttrbuteRule("0");
                radUserT.setModifyCounter("0");
                radUserTUtil.insert(radUserT);
            }
        } else {
            //Sub 2 update DB
            String isRadius = CommonUtils.toString(params.get("isRadius")).trim();
            if (RADIUS_YES.equals(isRadius)) {
                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                Map<String, Object> subScriptionInfo = queryDAO.executeForMap("COMMON.GET_SUBSCIPTION_BY_ID_CUST_PLAN", idCustPlan);
                String accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                radUserTUtil.deleteByUserName(accessAccount);
            }
        }
        
        G_CPM_P04 gCpmP04 = new G_CPM_P04(this.queryDAO, this.updateDAO);
        gCpmP04.setIdAudit(idAudit);
        gCpmP04.execute(idCustPlan);
        
        output.put("resultType", "success");
        //End Audit Trail
        CommonUtils.auditTrailEnd(idAudit);
        
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
