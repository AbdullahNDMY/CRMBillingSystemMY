package nttdm.bsys.e_mim_us1.blogic;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.G_IPS_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1_2BlogicInput;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1_2BlogicOutput;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.Globals;
import org.springframework.context.ApplicationContext;

public class E_MIM_US1_2BlogicBLogic extends AbstractE_MIM_US1_2BlogicBLogic {

	public BLogicResult execute(E_MIM_US1_2BlogicInput param) {
		E_MIM_US1_2BlogicOutput outputDTO = new E_MIM_US1_2BlogicOutput();
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		if (param.getFileName().getFileName() == null || param.getFileName().getFileName().equals("")){
			errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC053"));
			result.setErrors(errors);
		}else{
			// call to G_IPS_P01 process
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			G_IPS_P01 p01 = (G_IPS_P01) context.getBean("G_IPS_P01");
			GlobalProcessResult glPResult = p01.execute(param, outputDTO);
			result.setErrors(glPResult.getErrors());
			result.setMessages(glPResult.getMessages());
		}
		
		result.setResultObject(outputDTO);		
		result.setResultString("display");
		return result;
	}
}