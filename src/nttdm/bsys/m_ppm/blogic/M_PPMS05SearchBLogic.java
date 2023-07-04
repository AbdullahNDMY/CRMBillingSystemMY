package nttdm.bsys.m_ppm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

/**
 * Insert Sub Plan / Option Service Search BLogic<br/>
 * 
 * @author  NTTData 
 * @version 1.1
 */
public class M_PPMS05SearchBLogic implements BLogic<Map<String,Object>>{

	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		
		String noDisplayOTC = CommonUtils.toString(input.get("noDisplayOTC"));
		String idPlan = CommonUtils.toString(input.get("idPlan"));
		Object idPlanGrpsObj = input.get("idPlanGrp");
		
		String idPlanGrpList = CommonUtils.toString(input.get("idPlanGrpList"));
		String[] idPlanGrpNot = new String[]{};
		if(!"".equals(idPlanGrpList.trim())){
			idPlanGrpNot = idPlanGrpList.split(",");
		}
		
		Map<String, Object> output = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String,Object>>();
		
		Integer totalCount = new Integer(0);
		
		BillingSystemSettings systemSetting = new BillingSystemSettings(getQueryDAO());
		
		Integer row = systemSetting.getResultRow();
		Integer startIndex = 0;
		try {
			startIndex = Integer.parseInt(CommonUtils.toString(input.get("startIndex")));
		} catch (Exception e) {
			startIndex = 0;
		}
		
		input.put("idPlanGrpNot", idPlanGrpNot);
		
		if(!CommonUtils.isEmpty(idPlan)){
			
		    totalCount  = getQueryDAO().executeForObject("SELECT.M_PPM.S05.TOTAL", input, Integer.class);
		    
			if(totalCount.compareTo(0)>0){
				searchResult = getQueryDAO().executeForObjectList("SELECT.M_PPM.S05.RESULT", input, startIndex, row);
			}
		}
		if(searchResult.size()>0){
			HashMap<String, Object> t_plan_h = searchResult.get(0);
			output.put("billCurrency", t_plan_h.get("BILLCURRENCY"));
			output.put("lblServiceName", t_plan_h.get("LBLSERVICENAME"));
			output.put("lblServiceDescr", t_plan_h.get("LBLSERVICEDESCR"));
		}
		//re-check Plan is selected
		String[] idPlanGrps;
		List<String> idPlanGrps_tmp = new ArrayList<String>();
		if(idPlanGrpsObj != null) {
		    idPlanGrps = (String[])idPlanGrpsObj;
			for(String idPlanGrp : idPlanGrps) {
				boolean isAdded = true;
				for(HashMap<String, Object> plan  : searchResult) {
					if(plan.get("IDPLANGRP").toString().equals(idPlanGrp)) {
						plan.put("SELECTED", "Y");
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
		
		output.put("noDisplayOTC", noDisplayOTC);
		output.put("idPlanGrpList", idPlanGrpList);
		output.put("idPlan", idPlan);
		output.put("idPlanGrp", idPlanGrps);
		output.put("row", row.toString());
		output.put("totalCount", totalCount.toString());
		output.put("startIndex", startIndex.toString());
		output.put("searchResult", searchResult);
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
	
	private QueryDAO queryDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}
