package nttdm.bsys.b_cpm.blogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;

public class B_CPM_S07SRCBlogic implements BLogic<Map<String, Object>>{
	/**
	 * queryDAO
	 */
	private QueryDAO queryDAO;
	
	/**
	 * 
	 */
	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		HashMap<String, Object> output = new HashMap<String, Object>();
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)input.get("uvo");
		output.put("idUser", uvo.getId_user());
		String accessType = queryDAO.executeForObject("SELECT.B_CPM_S07.GETACCESS.SQL001", output, String.class);
		output.put("accessType", accessType);
		
		String idSubInfo = CommonUtils.toString(input.get("idSubInfo"));
		String idCustPlan = CommonUtils.toString(input.get("idCustPlan"));
		
		Map<String, Object> mailQty = queryDAO.executeForMap("SELECT.B_CPM_S07.GETQTY.SQL001", idSubInfo);
		// set all data
		if(mailQty!=null){
			output.putAll(mailQty);
		}else{
			output.put("MAILACC", BigDecimal.ZERO);
			output.put("MAILBOX_QTY", BigDecimal.ZERO);
			output.put("VIRUS_SCAN", BigDecimal.ZERO);
			output.put("ANTI_SPAM", BigDecimal.ZERO);
			output.put("JUNK_MGT", BigDecimal.ZERO);
		}
		output.put("OPT_BASE_QTY", CommonUtils.toString(output.get("OPT_BASE_QTY")).equals("")?"0":CommonUtils.toString(output.get("OPT_BASE_QTY")));
		output.put("OPT_ADDT_OPTION", CommonUtils.toString(output.get("OPT_ADDT_OPTION")));
		output.put("OPT_MAILBOX_QTY", CommonUtils.toString(output.get("OPT_MAILBOX_QTY")));
		output.put("OPT_VIRUS_SCAN", CommonUtils.toString(output.get("OPT_VIRUS_SCAN")));
		output.put("OPT_ANTI_SPAM", CommonUtils.toString(output.get("OPT_ANTI_SPAM")));
		output.put("OPT_JUNK_MGT", CommonUtils.toString(output.get("OPT_JUNK_MGT")));
				
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
		
		output.put("idSubInfo", idSubInfo);
		output.put("idCustPlan", idCustPlan);
		output.put("serverList", serverList);
		
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
