package nttdm.bsys.e_mim_us1.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.e_mim_us1.common.E_MIM_US1_Common;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1BlogicInput;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1BlogicOutput;
import nttdm.bsys.e_mim_us1.dto.TIpassImpHdDto;

public class E_MIM_US1BlogicBLogic extends AbstractE_MIM_US1BlogicBLogic {

	public BLogicResult execute(E_MIM_US1BlogicInput param) {
		BLogicResult result = new BLogicResult();
		E_MIM_US1BlogicOutput outputDTO = new E_MIM_US1BlogicOutput();
		
		// get T_GIR_IMP_HD from DB
		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
		int row = bss.getResultRow();
		// get record list
		List<TIpassImpHdDto> list = null;
		list = queryDAO.executeForObjectList(E_MIM_US1_Common.SELECT_T_IPASS_IMP_HD, null, param.getStartIndex(), row);
		// get total count of list
		Integer totalCount = queryDAO.executeForObject(E_MIM_US1_Common.SELECT_TOTAL_COUNT, null, Integer.class);
		// set to output
		outputDTO.setTotalCount(totalCount);
		outputDTO.setIpassList(list);
		outputDTO.setRow(row);
		
		result.setResultObject(outputDTO);		
		result.setResultString("display");
		return result;
	}
}