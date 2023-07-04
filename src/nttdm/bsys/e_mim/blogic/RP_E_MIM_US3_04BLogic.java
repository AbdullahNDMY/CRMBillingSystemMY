/*
 * @(#)RP_E_MIM_US3_04BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import java.lang.reflect.InvocationTargetException;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.G_RAD_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_04Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_04Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.springframework.context.ApplicationContext;

public class RP_E_MIM_US3_04BLogic extends AbstractRP_E_MIM_US3_04BLogic {

	public BLogicResult execute(RP_E_MIM_US3_04Input param) {
		BLogicResult result = new BLogicResult();
		RP_E_MIM_US3_04Output outputDTO = new RP_E_MIM_US3_04Output();
		//Call G_CLC_P01 process
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		G_RAD_P01 gRadP01 = (G_RAD_P01)context.getBean("G_RAD_P01");
//		try {
			GlobalProcessResult glPResult = gRadP01.execute(param, outputDTO);
			result.setErrors(glPResult.getErrors());
			result.setMessages(glPResult.getMessages());
//		} catch(Exception e) {
//			BLogicMessages errors = new BLogicMessages();
//			BLogicMessage er = new BLogicMessage("errors.batch.execute", e.getMessage());
//			errors.add(ActionErrors.GLOBAL_MESSAGE, er);
//			result.setErrors(errors);
//			result.setMessages(errors);
//		}
		try {
			BeanUtils.copyProperties(outputDTO, param);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		gRadP01.reset();
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}