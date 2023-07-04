/*
 * @(#)E_MIM_US2BlogicBLogic.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.e_dim_gr1.common.E_DIM_GR1_Common;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicOutput;
import nttdm.bsys.e_dim_gr1.dto.TBatchLogDto;
import nttdm.bsys.e_mim_us1.common.E_MIM_US1_Common;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US2BlogicInput;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US2BlogicOutput;

import nttdm.bsys.e_mim_us1.blogic.AbstractE_MIM_US2BlogicBLogic;

/**
 * BusinessLogic class.
 * 
 * @author ss054
 */
public class E_MIM_US2BlogicBLogic extends AbstractE_MIM_US2BlogicBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(E_MIM_US2BlogicInput param) {
		BLogicResult result = new BLogicResult();
		// get records from T_BATCH_LOG table
		List<TBatchLogDto> list = queryDAO.executeForObjectList(E_MIM_US1_Common.SELECT_T_BATCH_LOG, param.getIdIpassImpBatch());
		// set to output 
		E_MIM_US2BlogicOutput outputDTO = new E_MIM_US2BlogicOutput();
		outputDTO.setLogList(list);

		result.setResultObject(outputDTO);
		result.setResultString("display");
		return result;
	}
}