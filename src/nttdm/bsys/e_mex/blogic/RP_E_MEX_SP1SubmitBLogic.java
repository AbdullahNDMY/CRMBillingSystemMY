/*
 * @(#)RP_E_MEX_SP1SubmitBLogic.java
 *
 *
 */
package nttdm.bsys.e_mex.blogic;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.util.PropertyUtil;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_SET_P03;
import nttdm.bsys.common.util.G_SGP_P01;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitOutput;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionErrors;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

public class RP_E_MEX_SP1SubmitBLogic extends AbstractRP_E_MEX_SP1SubmitBLogic {

    public BLogicResult execute(RP_E_MEX_SP1SubmitInput param) {
        BLogicResult result = new BLogicResult();
        RP_E_MEX_SP1SubmitOutput outputDTO = new RP_E_MEX_SP1SubmitOutput();

        // validation
        BLogicMessages errors = new BLogicMessages();
        BLogicMessages messages = new BLogicMessages();

        // input
        BillingSystemUserValueObject uvo = param.getUvo();
        String scheduleStatus = param.getScheduleStatus();
        String scheduleDay = param.getScheduleDay();
        String scheduleHour = param.getScheduleHour();
        String scheduleMinute = param.getScheduleMinute();
        String deductionDate = param.getDeductionDate();

        String forward_update = param.getForward_update();
        String forward_execute = param.getForward_execute();
        String forward_pageLinks = param.getForward_pageLinks();

        if (CommonUtils.anyNotEmpty(forward_update, forward_execute)) {
            errors = this.validate(param, outputDTO);
        }
        // output
        BeanUtils.copyProperties(param, outputDTO);
        // business
        if (errors.isEmpty()) {
            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("idLogin", uvo.getId_user());
            parameter.put("idModule", RP_E_MEX_SP1BLogic.ID_MODULE);
            parameter.put("idSubModule", RP_E_MEX_SP1BLogic.ID_SUB_MODULE);
            parameter.put("idBatchType", RP_E_MEX_SP1BLogic.ID_BATCH_TYPE);

            if (CommonUtils.notEmpty(forward_update)) {
                // Update
                parameter.put("isActive",
                        CommonUtils.isEmpty(scheduleStatus) ? 0
                                : scheduleStatus);
                parameter.put("execDay", scheduleDay);
                parameter.put("execHour", scheduleHour);
                parameter.put("execMinute", scheduleMinute);
                parameter.put("deductionDate", deductionDate);
                // update setting
                Map<String, Object> setting = queryDAO.executeForMap(
                        "E_MEX.getSchedulerSetting", parameter);
                /**
                 * Audit Trail
                 */
                Integer idAudit = CommonUtils
                        .auditTrailBegin(
                                uvo.getId_user(),
                                BillingSystemConstants.MODULE_E,
                                BillingSystemConstants.SUB_MODULE_E_MEX_SP1,
                                //RP_E_MEX_SP1BLogic.ID_BATCH_TYPE,
                                "update scheduler",
                                //TODO
                                null,
                                setting == null ? BillingSystemConstants.AUDIT_TRAIL_CREATED
                                        : BillingSystemConstants.AUDIT_TRAIL_EDITED);
                parameter.put("idAudit", idAudit);

                if (setting == null) {
                    updateDAO.execute("E_MEX.addSchedulerSetting", parameter);
                } else {
                    updateDAO
                            .execute("E_MEX.updateSchedulerSetting", parameter);
                }
                CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
            } else if (CommonUtils.notEmpty(forward_execute)) {
                G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
                try {
                    G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus(RP_E_MEX_SP1BLogic.ID_BATCH_TYPE);
                    if (returnValue.getRetStatus() != 0) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE,new BLogicMessage(returnValue.getRET_MSG(),false));
                    } else {
                        E_MEX_SP1Thread t = new E_MEX_SP1Thread(param, outputDTO);
                        t.start();
                        messages.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC041"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        result.setErrors(errors);
        result.setMessages(messages);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }

    public BLogicMessages validate(RP_E_MEX_SP1SubmitInput param,
            RP_E_MEX_SP1SubmitOutput outputDTO) {
        BLogicMessages errors = new BLogicMessages();
        String runtimeType = param.getRuntimeType();
        String scheduleStatus = param.getScheduleStatus();
        String scheduleHour = param.getScheduleHour();
        String scheduleMinute = param.getScheduleMinute();
        String closingMonth = param.getClosingMonth();
        String closingYear = param.getClosingYear();

        Calendar calendar = Calendar.getInstance();

        if ("1".equalsIgnoreCase(runtimeType)) { // scheduler
            if ("1".equalsIgnoreCase(scheduleStatus)) { // active
                if (!CommonUtils.between(scheduleHour, 0, 23)) {
                    errors.add(
                            ActionErrors.GLOBAL_MESSAGE,
                            new BLogicMessage("errors.hour", PropertyUtil
                                    .getProperty("screen.e_mex.00008")));
                }
                if (!CommonUtils.between(scheduleMinute, 0, 59)) {
                    errors.add(
                            ActionErrors.GLOBAL_MESSAGE,
                            new BLogicMessage("errors.minute", PropertyUtil
                                    .getProperty("screen.e_mex.00009")));
                }
            }
        } else if ("2".equals(runtimeType)) { // manual execute
            if (!CommonUtils.between(closingMonth, 1, 12)
                    || !CommonUtils.between(closingYear, 1000,
                            calendar.get(Calendar.YEAR))) {
                errors.add(
                        ActionErrors.GLOBAL_MESSAGE,
                        new BLogicMessage("errors.date", PropertyUtil
                                .getProperty("screen.e_mex.00016")));
            }
        }
        return errors;
        }
}
class E_MEX_SP1Thread extends Thread{
    RP_E_MEX_SP1SubmitInput param=null;
    RP_E_MEX_SP1SubmitOutput outputDTO = null;
    public E_MEX_SP1Thread(RP_E_MEX_SP1SubmitInput param,RP_E_MEX_SP1SubmitOutput outputDTO){
        this.param = param;
        this.outputDTO = outputDTO;
    }
    public void run() {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        G_SGP_P01 sgpP01 = (G_SGP_P01) context.getBean("G_SGP_P01");
        param.setScr(true);
        sgpP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        sgpP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MEX_SP1);
        sgpP01.execute(param, outputDTO);//.appendErrors(errors).appendMessages(messages);
    }
}