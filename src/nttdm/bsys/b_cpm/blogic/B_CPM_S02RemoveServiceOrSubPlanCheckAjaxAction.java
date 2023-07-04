/**
 * @(#)B_CPM_S02RemoveServiceOrSubPlanCheckAjaxAction.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/06/07
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_cpm.blogic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

/**
 * @author gplai
 *
 */
public class B_CPM_S02RemoveServiceOrSubPlanCheckAjaxAction extends ActionEx {

    /**
     * queryDAO object
     */
    protected QueryDAO queryDAO = null;
    
    /**
     * type of reponse
     */
    private static String JSON = "application/json";
    
    private static final String TYPE_SERVICE = "service";
    
    private static final String TYPE_SUBPLAN = "subplan";

    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Gson googleSon = new Gson();
        String jsonString = "";
        
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String idSubInfo = request.getParameter("idSubInfo");
        String result = "success";
        
        Map<String, Object> mapSubInfo = queryDAO.executeForMap("B_CPM.GET_SUBSCIPTION_BY_ID_SUB_INFO", idSubInfo);
        if (mapSubInfo!=null) {
            String accessAccountTest = CommonUtils.toString(mapSubInfo.get("ACCESS_ACCOUNT_TEST"));
            if("1".equals(accessAccountTest)) {
                if (TYPE_SERVICE.equals(type)) {
                    List<Map<String, Object>> listRadiusServiceByIdCustPlanGrp = queryDAO.executeForMapList("B_CPM.GET_SERVICE_BATCH_MAPPING_BY_ID_CUST_PLAN_GRP", id);
                    if(listRadiusServiceByIdCustPlanGrp!=null && 0<listRadiusServiceByIdCustPlanGrp.size()) {
                        result = "failure";
                    }
                } else if (TYPE_SUBPLAN.equals(type)) {
                    List<Map<String, Object>> listRadiusServiceByIdCustPlanLink = queryDAO.executeForMapList("B_CPM.GET_SERVICE_BATCH_MAPPING_BY_ID_CUST_PLAN_LINK", id);
                    if(listRadiusServiceByIdCustPlanLink!=null && 0<listRadiusServiceByIdCustPlanLink.size()) {
                        result = "failure";
                    }
                }
            }
        }
        
        //convert the period to JSON string
        jsonString = googleSon.toJson(result);
        
        // set type for the response
        response.setContentType(JSON);
        try {
            // write JSON string into the response
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}
