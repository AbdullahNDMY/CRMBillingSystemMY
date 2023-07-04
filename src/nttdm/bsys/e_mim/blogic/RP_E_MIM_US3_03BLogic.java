/*
 * @(#)RP_E_MIM_US3_03BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import java.util.HashMap;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_03Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_03Output;
import nttdm.bsys.e_mim.blogic.AbstractRP_E_MIM_US3_03BLogic;

public class RP_E_MIM_US3_03BLogic extends AbstractRP_E_MIM_US3_03BLogic {
	
	public static final String ID_BATCH_TYPE_G_RAD_P01 = "G_RAD_P01";
	
	public static final String ID_SET_BATCH_TIMEOUT = "BATCH_TIME_INTERVAL";

	public BLogicResult execute(RP_E_MIM_US3_03Input param) {
		BLogicResult result = new BLogicResult();
		RP_E_MIM_US3_03Output outputDTO = new RP_E_MIM_US3_03Output();
		// Set parameter
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idBatchType", ID_BATCH_TYPE_G_RAD_P01);
		map.put("idBatchRefNo", param.getIdRadImpBatch());
		// Get list log
		List<HashMap<String, Object>> listLogs = queryDAO.executeForObjectList("E_MIM.getLogByHistory", map);
		// Set output parameter
		outputDTO.setListLog(listLogs);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}