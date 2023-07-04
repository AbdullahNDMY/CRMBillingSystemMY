package nttdm.bsys.m_svt.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.dto.AutoScaleList;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchInput;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchOutput;
import nttdm.bsys.m_svt.dto.PlanService;

public class M_SVTS02_NewInitBLogic extends AbstractM_SVT02_NewInitBLogic {

	public BLogicResult execute(M_SVT01_SearchInput input) {

		BLogicResult result = new BLogicResult();
		M_SVT01_SearchOutput output = new M_SVT01_SearchOutput();
		output.setTitle(input.getTitle());
		output.setSvcGrpName(input.getSvcGrpName());
		output.setCategory(input.getCategory());
		output.setEditStatus(input.getEditStatus());
		output.setParameters(input.getParameters());
		
		//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
		String productCodeInput = queryDAO.executeForObject("SELECT.M_SVT.M_GSET_D.PROD", null, String.class);
		output.setProductCodeInput(productCodeInput);
		//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
		
		if (input.getEditStatus().equals("edit")) {
			PlanService planService = new PlanService();
			List<PlanService> planServiceList = new AutoScaleList<PlanService>(new PlanService());
			planService = queryDAO.executeForObject("SELECT.M_SVT.GET_EDIT_IDSERVICE", input.getIdService(),PlanService.class);
			planServiceList.add(planService);
			input.setPlanServiceList(planServiceList);
			output.setPlanServiceList(input.getPlanServiceList());
		} 
		if (input.getTitle().equals("Service")) {
			result.setResultString("service");
		} else {
			result.setResultString("plan");
		}
		
		result.setResultObject(output);
		return result;
	}
}
