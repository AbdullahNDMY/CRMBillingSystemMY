/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S03
 * FUNCTION : SearchBLogic
 * FILE NAME : M_PPM_S03_02BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.m_ppm.dto.M_PPM_S03_02Input;
import nttdm.bsys.m_ppm.dto.M_PPM_S03_02Output;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.StandardPlan;

/**
 * Search BLogic<br/>
 * Do search action<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S03_02BLogic implements BLogic<M_PPM_S03_02Input> {
	private QueryDAO queryDAO;
	
	public BLogicResult execute(M_PPM_S03_02Input input) {
		BLogicResult result = new BLogicResult();

		M_PPM_S03_02Output output = new M_PPM_S03_02Output();
		List<Service> cboCategoryList = queryDAO.executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
		output.setCboCategoryList(cboCategoryList);
		
		List<Map<String, Object>> cboServiceListFinal = new ArrayList<Map<String, Object>>(); 
		List<Map<String, Object>> cboServiceList = queryDAO.executeForMapList("SELECT.M_PPM.S01.GET_SERVICE", null);
		List<String> svcGroup = new ArrayList<String>();
		for(Map<String, Object> serviceMap : cboServiceList) {
			if(!svcGroup.contains(serviceMap.get("SVC_GRP").toString())) {
				svcGroup.add(serviceMap.get("SVC_GRP").toString());
			}
		}
		for(int i=0;i<svcGroup.size();i++) {
			Map<String, Object> temp = new HashMap<String, Object>(); 
			temp.put("ID_SERVICE", "");
			temp.put("SVC_NAME", "-Please Select One-");
			temp.put("GST", null);
			temp.put("SVC_GRP", svcGroup.get(i));
			cboServiceListFinal.add(temp);
		}
		cboServiceListFinal.addAll(cboServiceList);
        output.setCboServiceList(cboServiceListFinal);
		
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
		Integer row = systemSetting.getResultRow();
		Integer startIndex = 0;
		try {
			startIndex = Integer.parseInt(input.getStartIndex());
		} catch(Exception e) {
			startIndex = 0;
		}
		List<StandardPlan> searchResult = new ArrayList<StandardPlan>();
		Integer totalCount = new Integer(0);
		if("Y".equals(input.getDoSearch())){
			searchResult = queryDAO.executeForObjectList("SELECT.M_PPM.S03.SEARCH", input, startIndex, row);
			totalCount = queryDAO.executeForObject("SELECT.M_PPM.S03.SEARCH_COUNT", input, Integer.class);
		}
//		List<StandardPlan> 	searchResult = queryDAO.executeForObjectList("SELECT.M_PPM.S03.SEARCH", input, startIndex, row);
//		Integer totalCount = queryDAO.executeForObject("SELECT.M_PPM.S03.SEARCH_COUNT", input, Integer.class);
		//filter description limit
		int limitLen = systemSetting.getDescLength();
		for(StandardPlan plan : searchResult) {
			if(plan.getDescription() != null && plan.getDescription().length() > limitLen) {
				plan.setDescriptionLimit(plan.getDescription().substring(0, limitLen) + "...");
			}
		}
		
		//re-check Plan is selected
		String[] idPlanGrps = input.getIdPlanGrp();
		List<String> idPlanGrps_tmp = new ArrayList<String>();
		if(idPlanGrps != null) {
			for(String idPlanGrp : idPlanGrps) {
				boolean isAdded = true;
				for(StandardPlan plan : searchResult) {
					if(plan.getIdPlanGrp().equals(idPlanGrp)) {
						plan.setSelected(true);
						isAdded = false;
						break;
					}
				}
				if(isAdded) {
					idPlanGrps_tmp.add(idPlanGrp);
				}
			}
		}
		if(idPlanGrps_tmp.isEmpty()) {
			idPlanGrps = new String[] {};
		}
		else {
			idPlanGrps = idPlanGrps_tmp.toArray(new String[0]);
		}
		
		output.setStartIndex(String.valueOf(startIndex));
		output.setRow(String.valueOf(row));
		output.setTotalCount(String.valueOf(totalCount));
		output.setSearchResult(searchResult);
		output.setIdPlanGrp(idPlanGrps);
		
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