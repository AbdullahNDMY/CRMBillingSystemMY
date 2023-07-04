/*
 * @(#)RP_E_DIM_SP1_03BLogic.java
 *
 *
 */
package nttdm.bsys.e_dim.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_03Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_03Output;
import nttdm.bsys.e_dim.blogic.AbstractRP_E_DIM_SP1_03BLogic;

public class RP_E_DIM_SP1_03BLogic extends AbstractRP_E_DIM_SP1_03BLogic {

	public BLogicResult execute(RP_E_DIM_SP1_03Input param) {
		BLogicResult result = new BLogicResult();

		RP_E_DIM_SP1_03Output outputDTO = new RP_E_DIM_SP1_03Output();
		// Set parameter
		Map map = new HashMap();
		map.put("idBatchType", "G_SGP_P02");
		map.put("idClcImpBatch", param.getIdSgpImpBatch());
		// Get list log
		List<Map<String, Object>> listLogs = queryDAO.executeForMapList("E_DIM.getLogByHistory", map);
		// Set output parameter
		outputDTO.setListLog(listLogs);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}