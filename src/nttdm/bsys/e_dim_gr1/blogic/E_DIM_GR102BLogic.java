package nttdm.bsys.e_dim_gr1.blogic;

import java.util.List;

import org.apache.struts.action.ActionErrors;
import org.springframework.context.ApplicationContext;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.G_GIR_P02;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_dim_gr1.common.E_DIM_GR1_Common;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1BlogicInput;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1BlogicOutput;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicInput;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR1_2BlogicOutput;
import nttdm.bsys.e_dim_gr1.dto.TGirImpHdDto;
import nttdm.bsys.util.ApplicationContextProvider;

public class E_DIM_GR102BLogic extends AbstractE_DIM_GR1_201BLogic {

	public BLogicResult execute(E_DIM_GR1_2BlogicInput param) {
		E_DIM_GR1_2BlogicOutput outputDTO = new E_DIM_GR1_2BlogicOutput();
		BLogicResult result = new BLogicResult();
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		G_GIR_P02 p02 = (G_GIR_P02) context.getBean("G_GIR_P02");
		GlobalProcessResult glPResult = p02.execute(param, outputDTO);
		result.setErrors(glPResult.getErrors());
		result.setMessages(glPResult.getMessages());
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
}