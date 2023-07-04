package nttdm.bsys.b_cpm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class B_CPM_S07EditScrLogic implements BLogic<Map<String, Object>> {
	/**
	 * queryDAO
	 */
	private QueryDAO queryDAO;
	
	public BLogicResult execute(Map<String, Object> input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		BLogicResult result = new BLogicResult();
		String idCustPlan = CommonUtils.toString(input.get("idCustPlan"));
		output.putAll(input);
		
		List<Map<String, Object>> serverList = queryDAO.executeForMapList("SELECT.B_CPM_S07.GETSERVICE.SQL001", idCustPlan);
        List<Map<String, Object>> removeServerList = new ArrayList<Map<String, Object>>();
        if (serverList!=null && 0<serverList.size()) {
            for (Map<String, Object> service : serverList) {
                String idCustPlanGrp = CommonUtils.toString(service.get("ID_CUST_PLAN_GRP"));
                List<Map<String, Object>> subPlanList = queryDAO.executeForMapList("SELECT.B_CPM_S07.GETSERVICE.SQL002", idCustPlanGrp);
                if(subPlanList!=null && 0<subPlanList.size()) {
                    service.put("subPlanList", subPlanList);
                } else {
                    removeServerList.add(service);
                }
            }
        }
        
        for(Map<String, Object> map : removeServerList) {
            serverList.remove(map);
        }
        output.put("serverList", serverList);
        
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)input.get("uvo");
		output.put("idUser", uvo.getId_user());
		String accessType = queryDAO.executeForObject("SELECT.B_CPM_S07.GETACCESS.SQL001", output, String.class);
		output.put("accessType", accessType);
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
	
	/**
	 * queryDAO を取得すめE
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO を設定すめE
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}
