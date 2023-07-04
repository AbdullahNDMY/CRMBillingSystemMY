/**
 * @(#)M_PPM_S02NewAjaxGetService.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/02/19
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_ppm.blogic;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.Service;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

/**
 * @author gplai
 *
 */
public class M_PPM_S02NewAjaxGetServiceAction extends ActionEx {

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
            HttpServletRequest req, HttpServletResponse res)
            throws Exception {
    	res.setCharacterEncoding("UTF-8");
        Gson googleSon = new Gson();
        String jsonString = "";
        String idService = CommonUtils.toString(req.getParameter("idService")).trim();
        List<Service> cboSvcLevel2List = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL2", idService);
        
        StringBuffer customers = new StringBuffer();
        
        customers.append("<option value=''")
                 .append("'>")
                 .append("- Please Select One -")
                 .append("</option>");
        if (cboSvcLevel2List!=null && 0<cboSvcLevel2List.size() ){
            for (Service serivce : cboSvcLevel2List) {
                customers.append("<option value='")
                         .append(serivce.getIdService())
                         .append("'>")
                         .append(CommonUtils.toString(serivce.getSvcDesc()))
                         .append(" - ")
                         .append(CommonUtils.toString(serivce.getAccCode()))
                         .append("</option>");
            }
        }
        
        //convert the period to JSON string
        jsonString = googleSon.toJson(customers.toString());
        
        // set type for the response
        res.setContentType(JSON);
        try {
            // write JSON string into the response
            res.getWriter().print(jsonString);
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
