/*
 * @(#)RP_E_MIM_US2_02BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.util.BillingSystemConstants;

import nttdm.bsys.common.util.G_CLC_P01;
import nttdm.bsys.common.util.G_SET_P03;

import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.springframework.context.ApplicationContext;

public class RP_E_MIM_US2_02BLogic extends AbstractRP_E_MIM_US2_02BLogic {

	public BLogicResult execute(RP_E_MIM_US2_02Input param) {
		BLogicResult result = new BLogicResult();		
		RP_E_MIM_US2_02Output outputDTO = new RP_E_MIM_US2_02Output();
        BLogicMessages errors = new BLogicMessages();
        BLogicMessages messages = new BLogicMessages();
        
//		try {
			
			//result.setErrors(glPResult.getErrors());
			//result.setMessages(glPResult.getMessages());
//		} catch(Exception e) {
//			BLogicMessages errors = new BLogicMessages();
//			BLogicMessage er = new BLogicMessage("errors.batch.execute", e.getMessage());
//			errors.add(ActionErrors.GLOBAL_MESSAGE, er);
//			result.setErrors(errors);
//			result.setMessages(errors);
//		}
        
        G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
        try {
            G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus("G_CLC_P01");
            if(returnValue.getRetStatus()!=0){
                errors.add(ActionErrors.GLOBAL_MESSAGE,new BLogicMessage(returnValue.getRET_MSG(),false));
            }else{
                E_MIM_US2Thread t = new E_MIM_US2Thread(param, outputDTO);
                t.start();
                messages.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC041"));
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        
		try {
			BeanUtils.copyProperties(outputDTO, param);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
        result.setErrors(errors);
        result.setMessages(messages);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}
class E_MIM_US2Thread extends Thread{
    RP_E_MIM_US2_02Input param = null;
    RP_E_MIM_US2_02Output outputDTO = null;
    public E_MIM_US2Thread(RP_E_MIM_US2_02Input param,RP_E_MIM_US2_02Output outputDTO){
        this.param = param;
        this.outputDTO = outputDTO;
    }
    @Override
    public void run() {
        //Call G_CLC_P01 process
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        G_CLC_P01 gClcP01 = (G_CLC_P01)context.getBean("G_CLC_P01");
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US2);
        //GlobalProcessResult glPResult = 
       gClcP01.execute(param, outputDTO);
       gClcP01.reset();
    }
}