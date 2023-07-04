/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S03_01
 * FUNCTION       : processing business to redirect to B_SSM_S03_01
 * FILE NAME      : B_SSM_S03_01SCRAction.java
 *
 * Copyright (C) 2014 NTTDATA Corporation
 * 
 **********************************************************/
package nttdm.bsys.b_ssm.s03.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ForwardAction;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author NTT Data SCR of B_SSM_S03_01
 */
public class B_SSM_S03_01SCRAction extends ForwardAction {

    private QueryDAO queryDAO;

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
        DynaValidatorActionFormEx formex = (DynaValidatorActionFormEx) form;
        //String subscriptionID = CommonUtils.toString(formex.get("subscriptionID"));
        String[] selectedServices = (String[]) formex.get("selectedServices");
        String[] serviceIDs = (String[]) formex.get("serviceIDs");

        List<String> selectedIDs = new ArrayList<String>();

        List<Map<String, Object>> serviceList = new ArrayList<Map<String, Object>>();
        Map<String, Object> service;

        // Get selected ids
        if (selectedServices != null && selectedServices.length > 0) {
            for (int i = 0; i < selectedServices.length; i++) {
                String isSelectedStr = selectedServices[i];
                if (isSelectedStr != null && isSelectedStr.equals("1")) {
                    selectedIDs.add(serviceIDs[i]);
                }
            }
        }

        for (int i = 0; i < selectedIDs.size(); i++) {
            service = new HashMap<String, Object>();
            service.put("serviceId", selectedIDs.get(i));

            // get Bill desc
            String billDesc = queryDAO.executeForObject("B_SSM_S03.getBillDescbyID", selectedIDs.get(i), String.class);

            // get sub plan
            List<String> subPlan = queryDAO.executeForObjectList("B_SSM_S03.getSubPlans", selectedIDs.get(i));

            service.put("serviceDesc", billDesc + "(" + subPlan.toString().substring(1, subPlan.toString().length()-1) + ")");
            serviceList.add(service);
        }

        formex.set("serviceList", serviceList);
        return super.doExecute(mapping, form, req, res);
    }

}
