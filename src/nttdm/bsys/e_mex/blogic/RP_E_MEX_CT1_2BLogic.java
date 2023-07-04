/*
 * @(#)RP_E_MEX_CT1_2BLogic.java
 *
 *
 */
package nttdm.bsys.e_mex.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1_2Input;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1_2Output;

import nttdm.bsys.e_mex.blogic.AbstractRP_E_MEX_CT1_2BLogic;

/**
 * BusinessLogic class.
 * 
 * @author thinhtv
 */
public class RP_E_MEX_CT1_2BLogic extends AbstractRP_E_MEX_CT1_2BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_E_MEX_CT1_2Input param) {
		BLogicResult result = new BLogicResult();
		RP_E_MEX_CT1_2Output outputDTO = new RP_E_MEX_CT1_2Output();
		BeanUtils.copyProperties(param, outputDTO);
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("idBatchType", "G_CIT_P01");
		parameter.put("idBatchRefNo", param.getIdBatch());
		List<Map<String, Object>> logsList = this.queryDAO.executeForMapList("E_MEX.getLogReview", parameter);
		
		outputDTO.setLogsList(logsList);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}