package nttdm.bsys.e_dim.blogic;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.G_SET_P03;
import nttdm.bsys.common.util.G_SGP_P02;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.springframework.context.ApplicationContext;

public class RP_E_DIM_SP1_02BLogic extends AbstractRP_E_DIM_SP1_02BLogic {

    public BLogicResult execute(RP_E_DIM_SP1_02Input param) {
        BLogicResult result = new BLogicResult();
        RP_E_DIM_SP1_02Output outputDTO = new RP_E_DIM_SP1_02Output();
        BLogicMessages errors = new BLogicMessages();
        BLogicMessages messages = new BLogicMessages();
        //result.setErrors(glPResult.getErrors());
        //result.setMessages(glPResult.getMessages());
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("upload");
        gCsbP01CheckInput.setMessageParam2("Batch Import Singpost");
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
        
        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
        } else {
            G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
            try {
                G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus("G_SGP_P02");
                if(returnValue.getRetStatus()!=0){
                    errors.add(ActionErrors.GLOBAL_MESSAGE,new BLogicMessage(returnValue.getRET_MSG(),false));
                }else{
                    E_DIM_SP1Thread t = new E_DIM_SP1Thread(param, outputDTO);
                    t.start();
                    messages.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC041"));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        result.setErrors(errors);
        result.setMessages(messages);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}
class E_DIM_SP1Thread extends Thread{
    RP_E_DIM_SP1_02Input param = null;
    RP_E_DIM_SP1_02Output outputDTO = null;
    public E_DIM_SP1Thread(RP_E_DIM_SP1_02Input param,RP_E_DIM_SP1_02Output outputDTO){
        this.param = param;
        this.outputDTO = outputDTO;
    }
    @Override
    public void run() {
        // Call G_SGP_P02 process
        ApplicationContext context = ApplicationContextProvider
                .getApplicationContext();
        G_SGP_P02 gSgpP02 = (G_SGP_P02) context.getBean("G_SGP_P02");
        gSgpP02.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gSgpP02.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
        //GlobalProcessResult glPResult = 
        GlobalProcessResult result=gSgpP02.execute(param, outputDTO);
        //TODO
        AuditTrailHeaderBean auditTrailHeaderBean=new AuditTrailHeaderBean();
        auditTrailHeaderBean.setIdAudit((Integer) result.getParameter().get("ID_AUDIT"));
        auditTrailHeaderBean.setReference(result.getParameter().get("idBatchRefNo")+":"+result.getParameter().get("closeMonthYear"));
        String status=(String) result.getParameter().get("status");
        if("0".equals(status)){
        	auditTrailHeaderBean.setStatus("Successful");
        }else if("1".equals(status)){
        	auditTrailHeaderBean.setStatus("Failed");
        }else if("2".equals(status)){
        	auditTrailHeaderBean.setStatus("In process");
        }
        CommonUtils.auditTrailUpdate(auditTrailHeaderBean);
        gSgpP02.reset();
    }
}