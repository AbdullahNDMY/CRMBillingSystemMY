/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S01
 * FUNCTION : SearchBLogic
 * FILE NAME : M_PPM_S01_02BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.ServiceGroup;
import nttdm.bsys.m_ppm.bean.StandardPlan;
import nttdm.bsys.m_ppm.dto.M_PPM_S01_02Input;
import nttdm.bsys.m_ppm.dto.M_PPM_S01_02Output;

/**
 * Search BLogic<br/>
 * Do search action<br/>
 * 
 * @author NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S01_02BLogic implements BLogic<M_PPM_S01_02Input> {
    private QueryDAO queryDAO;

    public BLogicResult execute(M_PPM_S01_02Input input) {
        BLogicResult result = new BLogicResult();
        M_PPM_S01_02Output output = new M_PPM_S01_02Output();

        BillingSystemSettings systemSetting = new BillingSystemSettings( queryDAO);
        Integer row = systemSetting.getResultRow();
        Integer startIndex = 0;
        try {
            startIndex = Integer.parseInt(input.getStartIndex());
        } catch (Exception e) {
            startIndex = 0;
        }

        List<StandardPlan> searchResult = queryDAO.executeForObjectList( "SELECT.M_PPM.S01.SEARCH", input, startIndex, row);
        Integer totalCount = queryDAO.executeForObject( "SELECT.M_PPM.S01.SEARCH_COUNT", input, Integer.class);
        int limitLen = systemSetting.getDescLength();
        for (StandardPlan plan : searchResult) {
            if (plan.getDescription() != null && plan.getDescription().length() > limitLen) {
                plan.setDescriptionLimit(plan.getDescription().substring(0, limitLen) + "...");
            }
        }

        // get Category Group
        List<ServiceGroup> cboCategoryList = queryDAO.executeForObjectList( "SELECT.M_PPM.S01.CATEGORY", null);
        output.setCboCategoryList(cboCategoryList);

        // Category
        String cboCategory = input.getCboCategory();
        // service
        List<Map<String, Object>> serviceList;
        // plan
        List<Map<String, Object>> planList;
        // planDetail
        List<Map<String, Object>> planDetailList;

        if (CommonUtils.isEmpty(cboCategory)) {
            serviceList = new ArrayList<Map<String, Object>>();
            planList = new ArrayList<Map<String, Object>>();
            planDetailList = new ArrayList<Map<String, Object>>();
        } else {
            serviceList = queryDAO.executeForMapList( "SELECT.M_PPM.S01.cboService", cboCategory);
            planList = queryDAO.executeForMapList("SELECT.M_PPM.S01.cboPlan", cboCategory);
            planDetailList = queryDAO.executeForMapList( "SELECT.M_PPM.S01.cboPlanDetail", cboCategory);
        }

        output.setCboServiceList(serviceList);
        output.setCboPlanList(planList);
        output.setCboPlanDetailList(planDetailList);

        output.setStartIndex(String.valueOf(startIndex));
        output.setRow(String.valueOf(row));
        output.setTotalCount(String.valueOf(totalCount));
        output.setSearchResult(searchResult);

        if(totalCount==0){
            // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
        }
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}
