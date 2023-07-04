package nttdm.bsys.e_mex.blogic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_GIR_P01_E;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1_3Input;
import nttdm.bsys.util.ApplicationContextProvider;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class RP_E_MEX_GR1_3BLogic implements BLogic<RP_E_MEX_GR1_3Input> {
	
	private QueryDAO queryDAO;
	
	private UpdateDAO updateDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public BLogicResult execute(RP_E_MEX_GR1_3Input input) {
		BLogicResult result = new BLogicResult();
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		Map map = new HashMap();
		map.put("idBatch", input.getIdBatch());
//		map.put("giroDay", param.get("giroDay"));
//		map.put("giroMonth", param.get("giroMonth"));
//		map.put("giroYear", param.get("giroYear"));
		map.put("giroDay", day+"");
		map.put("giroMonth", month+"");
		map.put("giroYear", year+"");
		
		String forward_execute = input.getForward_execute();
		if (CommonUtils.notEmpty(forward_execute)) {
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			G_GIR_P01_E girP01_e = (G_GIR_P01_E)context.getBean("G_GIR_P01_E");
			girP01_e.execute(input, null);
		}
		else {
		}
		
		result.setResultObject(map);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
}
