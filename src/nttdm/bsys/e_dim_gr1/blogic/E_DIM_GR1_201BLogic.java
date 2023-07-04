package nttdm.bsys.e_dim_gr1.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.e_dim_gr1.common.E_DIM_GR1_Common;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicInput;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicOutput;
import nttdm.bsys.e_dim_gr1.dto.TBatchLogDto;

public class E_DIM_GR1_201BLogic extends AbstractE_DIM_GR1_201BLogic {


	public BLogicResult execute(E_DIM_GR1_2BlogicInput param) {
		BLogicResult result = new BLogicResult();

		// get row for each page in result list
//		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
//		int row = bss.getResultRow();
		// get records from T_BATCH_LOG table
		List<TBatchLogDto> list = queryDAO.executeForObjectList(E_DIM_GR1_Common.SQL_SELECT_LOG, param.getIdGirImpBatch());
		// get total count
//		Integer totalCount = queryDAO.executeForObject(E_DIM_GR1_Common.SQL_SELECT_LOG_COUNT, null, Integer.class);
		// set to output 
		E_DIM_GR1_2BlogicOutput outputDTO = new E_DIM_GR1_2BlogicOutput();

//		outputDTO.setTotalCount(totalCount);
		outputDTO.setLogList(list);
//		outputDTO.setRow(row);
		result.setResultObject(outputDTO);
		
		result.setResultString("success");
		return result;
	}
}