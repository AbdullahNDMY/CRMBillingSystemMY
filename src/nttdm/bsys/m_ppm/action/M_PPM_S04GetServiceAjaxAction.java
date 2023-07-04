/**
 * @(#)M_PPM_S04GetServiceAjaxAction.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/06/07
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_ppm.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import nttdm.bsys.common.util.MessageUtil;
import nttdm.bsys.m_ppm.bean.Service;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

/**
 * @author gplai
 *
 */
public class M_PPM_S04GetServiceAjaxAction extends ActionEx {

    /**
     * queryDAO object
     */
    protected QueryDAO queryDAO = null;
    
    /**
     * type of reponse
     */
    private static String JSON = "application/json";
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.web.struts.actions.ActionEx#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setCharacterEncoding("UTF-8");
        Gson googleSon = new Gson();
        String jsonString = "";
        
        String cboCategory = request.getParameter("cboCategory");
        // get all customer job
        List<Service> cboServiceList = queryDAO.executeForObjectList("SELECT.M_PPM.S04.SERVICE", cboCategory);
        
        StringBuffer services = new StringBuffer();
        
        if (cboServiceList!=null && 0<cboServiceList.size() ){
            services.append("<option value=''>- Please Select One -</option>");
            for (Service service : cboServiceList) {
                services.append("<option value='")
                         .append(service.getIdService())
                         .append("'>")
                         .append(service.getSvcDesc())
                         .append("</option>");
            }
        } else {
            services.append("<option value=''>- Please Select One -</option>");
        }
        
        
        //convert the period to JSON string
        jsonString = googleSon.toJson(services.toString());
        
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
