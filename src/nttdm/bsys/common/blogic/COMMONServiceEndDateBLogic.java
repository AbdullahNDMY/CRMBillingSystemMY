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
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author gplai
 *
 */
public class COMMONServiceEndDateBLogic implements BLogic<HashMap<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(HashMap<String, Object> params) {
        BLogicResult result = new BLogicResult();
        Map<String, Object> output = new HashMap<String, Object>();
        
        String serviceNo = CommonUtils.toString(params.get("serviceNo")).trim();
        String idCustPlanGrp = CommonUtils.toString(params.get("idCustPlanGrp")).trim();
        
        Map<String, Object> mapCustPlanService = queryDAO.executeForMap("COMMON.SELECT_COMMON_SERVICE_END_DATE_GET_T_CUST_PLAN_D", idCustPlanGrp);
        if (mapCustPlanService!=null) {
            output.put("idCustPlan", CommonUtils.toString(mapCustPlanService.get("ID_CUST_PLAN")));
            output.put("svcStartDate", CommonUtils.toString(mapCustPlanService.get("SVC_START")));
            output.put("svcEndDate", CommonUtils.toString(mapCustPlanService.get("SVC_END")));
            output.put("preServiceStatus", CommonUtils.toString(mapCustPlanService.get("SERVICES_STATUS")));
            output.put("serviceStatus", CommonUtils.toString(mapCustPlanService.get("SERVICES_STATUS")));
            output.put("billingStatus", CommonUtils.toString(mapCustPlanService.get("BILLING_STATUS")));
            output.put("billFrom", CommonUtils.toString(mapCustPlanService.get("BILL_FROM")));
            output.put("billTo", CommonUtils.toString(mapCustPlanService.get("BILL_TO")));
            output.put("contractPeriodType", CommonUtils.toString(mapCustPlanService.get("MIN_SVC_PERIOD")));
            output.put("contractPeriodFrom", CommonUtils.toString(mapCustPlanService.get("MIN_SVC_END")));
            output.put("contractPeriodTo", CommonUtils.toString(mapCustPlanService.get("MIN_SVC_END")));
            output.put("billInstruct", CommonUtils.toString(mapCustPlanService.get("BILL_INSTRUCT")));
        }
        
        output.put("sysdate", CommonUtils.formatDate(new Date(), "dd/MM/yyyy"));
        output.put("serviceNo", serviceNo);
        output.put("idCustPlanGrp", idCustPlanGrp);
        
        result.setResultObject(output);
        result.setResultString("success");
        return result;
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

}
