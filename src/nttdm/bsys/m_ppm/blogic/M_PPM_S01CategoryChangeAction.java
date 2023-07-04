/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S01
 * FUNCTION : M_PPM_S01CategoryChangeAction
 * FILE NAME : M_PPM_S01CategoryChangeAction.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */
package nttdm.bsys.m_ppm.blogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

/**
 * Category Change Action<br/>
 * Use to initial select data<br/>
 * 
 * @author  NTTData
 * @version 1.1
 */
public class M_PPM_S01CategoryChangeAction extends ActionEx{
    private QueryDAO queryDAO;
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.web.struts.actions.ActionEx#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setCharacterEncoding("UTF-8");
        Gson googleSon = new Gson();
        // cboCategory
        String cboCategory = CommonUtils.toString(((DynaValidatorActionFormEx)form).get("cboCategory"));
        
        HashMap<String,List<Map<String,Object>>> resultData = new HashMap<String,List<Map<String,Object>>>();
        
        List<Map<String,Object>> serviceList = queryDAO.executeForMapList("SELECT.M_PPM.S01.cboService", cboCategory);
        List<Map<String,Object>> planList = queryDAO.executeForMapList("SELECT.M_PPM.S01.cboPlan", cboCategory);
        List<Map<String,Object>> planDetailList = queryDAO.executeForMapList("SELECT.M_PPM.S01.cboPlanDetail", cboCategory);
        
        resultData.put("serviceList", serviceList);
        resultData.put("planList", planList);
        resultData.put("planDetailList", planDetailList);
        
        response.setContentType("application/json");
        
        String jsonString = googleSon.toJson(resultData);
        
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
