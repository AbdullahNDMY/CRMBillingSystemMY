/*
 * 
 *
 * Copyright 2010 NTT DATA Corporation.
 * Display screen
 *
 */
package nttdm.bsys.e_dim_gr1.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.e_dim_gr1.common.E_DIM_GR1_Common;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1BlogicInput;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1BlogicOutput;
import nttdm.bsys.e_dim_gr1.dto.TGirImpHdDto;

public class E_DIM_GR101BLogic extends AbstractE_DIM_GR101BLogic {

	public BLogicResult execute(E_DIM_GR1BlogicInput param) {
		BLogicResult result = new BLogicResult();
		E_DIM_GR1BlogicOutput outputDTO = new E_DIM_GR1BlogicOutput();
		
		// get T_GIR_IMP_HD from DB
		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
		int row = bss.getResultRow();
		Integer startIndex = param.getStartIndex();
		
		// get total count
		Integer totalCount = queryDAO.executeForObject(E_DIM_GR1_Common.SQL_SELECT_HISTORY_COUNT, null, Integer.class);
		
		// get record list
		List<TGirImpHdDto> list = queryDAO.executeForObjectList(E_DIM_GR1_Common.SQL_SELECT_HISTORY, null, startIndex, row);
		
		// set to output
		outputDTO.setRow(row);
		outputDTO.setStartIndex(startIndex);
		outputDTO.setTotalCount(totalCount);
		
		outputDTO.setGirImpHdDtoList(list);
		outputDTO.setAlertMsg(param.getAlertMsg());

		result.setResultObject(outputDTO);
		
		result.setResultString("success");
		return result;
	}

}