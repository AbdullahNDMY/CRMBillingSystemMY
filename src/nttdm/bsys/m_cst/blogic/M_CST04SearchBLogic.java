package nttdm.bsys.m_cst.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

/**
 * Search BLogic<br/>
 * do search action<br/>
 * @author  NTTData 
 * @version 1.1
 */
public class M_CST04SearchBLogic extends AbstractM_CST04SearchBLogic{

	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result  = new BLogicResult();
		
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
		Integer row = systemSetting.getResultRow();
		Integer startIndex = 0;
		try {
			startIndex = Integer.parseInt(CommonUtils.toString(input.get("startIndex")));
		} catch(Exception e) {
			startIndex = 0;
		}
		if(!CommonUtils.toString(input.get("pageSearch")).equals("Y")){
			startIndex = 0;
		}
		HashMap<String,Object> output = new HashMap<String, Object>();
		String doSearch = String.valueOf(input.get("doSearch"));
		if(doSearch!=null&&"Y".equals(doSearch)){
			
			// get total
			Integer totalCount = queryDAO.executeForObject("SELECT.M_CST04.SQL001", input, Integer.class);
		
			List<HashMap<String,Object>> searchResult = queryDAO.executeForObjectList("SELECT.M_CST04.SQL002", input, startIndex, row);
			
			output.put("startIndex",startIndex);
			output.put("row", row);
			output.put("totalCount", totalCount);
			output.put("tbxCustomerName",input.get("tbxCustomerName"));
			output.put("tbxCustomerId",input.get("tbxCustomerId"));
			output.put("customerBeans",searchResult);
		}
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}

}
