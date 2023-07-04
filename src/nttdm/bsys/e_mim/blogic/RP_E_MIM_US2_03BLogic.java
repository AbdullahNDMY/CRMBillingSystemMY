/*
 * @(#)RP_E_MIM_US2_03BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_03Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_03Output;
import nttdm.bsys.e_mim.blogic.AbstractRP_E_MIM_US2_03BLogic;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US2_03BLogic extends AbstractRP_E_MIM_US2_03BLogic {
	
	public static final String ID_BATCH_TYPE_G_CLC_P01 = "G_CLC_P01";
	public static final String ID_SET_BATCH_TIMEOUT = "BATCH_TIME_INTERVAL";
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	@SuppressWarnings("unchecked")
	public BLogicResult execute(RP_E_MIM_US2_03Input param) {
		BLogicResult result = new BLogicResult();
		RP_E_MIM_US2_03Output outputDTO = new RP_E_MIM_US2_03Output();
		// Set parameter
		Map map = new HashMap();
		map.put("idBatchType", ID_BATCH_TYPE_G_CLC_P01);
		map.put("idBatchRefNo", param.getIdClcImpBatch());
		// Get list log
		List<HashMap> listLogs = queryDAO.executeForObjectList("E_MIM.getLogByHistory", map);
		// Set output parameter
		outputDTO.setListLog(listLogs);
		
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}