/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management B-CPM-S05
 * SERVICE NAME   : B_CPM_S05
 * FUNCTION       : Display B-CPM-S05
 * FILE NAME      : B_CPM_S05BLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.b_cpm.blogic.AbstractB_CPM_S05BLogic;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;

/**
 * B_CPM_S05BLogic.class<br>
 * <ul>
 * <li>process to determinate display B-CPM-S05</li> 
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S05BLogic extends AbstractB_CPM_S05BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		
		CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
		
		Map<String, Object> resultObject = new HashMap<String, Object>();
		resultObject.put("customerPlan", customerPlan);
		
		customerPlan.setScreen(B_CPM_CONSTANT.SCR_B_CPM_05);
		
		result.setResultObject(resultObject);
		result.setResultString("success");
		return result;
	}
}
