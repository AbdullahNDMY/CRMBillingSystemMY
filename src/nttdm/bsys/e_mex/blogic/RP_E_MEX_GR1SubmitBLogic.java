package nttdm.bsys.e_mex.blogic;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.util.PropertyUtil;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.G_GIR_P01;
import nttdm.bsys.common.util.G_SET_P03;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitOutput;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionErrors;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

public class RP_E_MEX_GR1SubmitBLogic extends AbstractRP_E_MEX_GR1SubmitBLogic {

    public BLogicResult execute(RP_E_MEX_GR1SubmitInput param) {
        BLogicResult result = new BLogicResult();
        RP_E_MEX_GR1SubmitOutput outputDTO = new RP_E_MEX_GR1SubmitOutput();
        // validation
        BLogicMessages errors = new BLogicMessages();
        BLogicMessages messages = new BLogicMessages();

        // input
        BillingSystemUserValueObject uvo = param.getUvo();
        String scheduleStatus = param.getScheduleStatus();
        String scheduleDay = param.getScheduleDay();
        String scheduleHour = param.getScheduleHour();
        String scheduleMinute = param.getScheduleMinute();
        String giroDay = param.getGiroDay();
        String giroMonth = param.getGiroMonth();
        String giroYear = param.getGiroYear();
        String deductionDate = param.getDeductionDate();
        String bankAcc = param.getBankAcc();

        String forward_update = param.getForward_update();
        String forward_execute = param.getForward_execute();
        String forward_pageLinks = param.getForward_pageLinks();

        if (CommonUtils.anyNotEmpty(forward_update, forward_execute)) {
            errors = this.validate(param, outputDTO);
        }

        // output
        BeanUtils.copyProperties(param, outputDTO);
        if (CommonUtils.isEmpty(scheduleStatus)) {
            scheduleStatus = "0";
            outputDTO.setScheduleStatus(scheduleStatus);
        }
        // business
        if (errors.isEmpty()) {
            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("idLogin", uvo.getId_user());
            parameter.put("idModule", RP_E_MEX_GR1BLogic.ID_MODULE);
            parameter.put("idSubModule", RP_E_MEX_GR1BLogic.ID_SUB_MODULE);
            parameter.put("idBatchType", RP_E_MEX_GR1BLogic.ID_BATCH_TYPE);

            if (CommonUtils.notEmpty(forward_update)) {
                // Update
                parameter.put("isActive", scheduleStatus);
                parameter.put("execDay", CommonUtils.toInteger(scheduleDay));
                parameter.put("execHour", CommonUtils.toInteger(scheduleHour));
                parameter.put("execMinute",
                        CommonUtils.toInteger(scheduleMinute));
                parameter.put("deductionDate", deductionDate);
                parameter.put("bankAcc", bankAcc);
                // parameter.put("giroDay", CommonUtils.toInteger(giroDay));
                // parameter.put("giroMonth", CommonUtils.toInteger(giroMonth));
                // parameter.put("giroYear", CommonUtils.toInteger(giroYear));
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
                                BillingSystemConstants.SUB_MODULE_E_MEX_GR1,
                                //RP_E_MEX_GR1BLogic.ID_BATCH_TYPE,
                                //TODO
                                "update scheduler",
                                
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
                G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
                G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
                gCsbP01CheckInput.setMessageParam1("execute");
                gCsbP01CheckInput.setMessageParam2("Batch Export Giro");
                gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
                G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
                boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
                
                if (!resultFlg) {
                    String errorMsg = gCsbP01CheckOutput.getMessageContext();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
                } else {
                    G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
                    try {
                        G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus(RP_E_MEX_GR1BLogic.ID_BATCH_TYPE);
                        
                        if (returnValue.getRetStatus() != 0) {
                            errors.add(ActionErrors.GLOBAL_MESSAGE,new BLogicMessage(returnValue.getRET_MSG(),false));
                        } else {
                            E_MEX_GR1Thread t = new E_MEX_GR1Thread(param, outputDTO);
                            t.start();
                            messages.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC041"));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        result.setErrors(errors);
        result.setMessages(messages);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }

    public BLogicMessages validate(RP_E_MEX_GR1SubmitInput param,
            RP_E_MEX_GR1SubmitOutput outputDTO) {
        BLogicMessages errors = new BLogicMessages();
        String runtimeType = param.getRuntimeType();
        String scheduleStatus = param.getScheduleStatus();
        String scheduleDay = param.getScheduleDay();
        String scheduleHour = param.getScheduleHour();
        String scheduleMinute = param.getScheduleMinute();
        String closingMonth = param.getClosingMonth();
        String closingYear = param.getClosingYear();
        String giroDay = param.getGiroDay();
        String giroMonth = param.getGiroMonth();
        String giroYear = param.getGiroYear();

        Calendar calendar = Calendar.getInstance();
        // GIRO value date
        // if (CommonUtils.toDate(giroDay, giroMonth, giroYear) == null) {
        // errors.add(ActionErrors.GLOBAL_MESSAGE, new
        // BLogicMessage("errors.date",
        // PropertyUtil.getProperty("screen.e_mex.00036")));
        // }

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
class E_MEX_GR1Thread extends Thread{
    RP_E_MEX_GR1SubmitInput param =null;
    RP_E_MEX_GR1SubmitOutput outputDTO = null;
    public E_MEX_GR1Thread(RP_E_MEX_GR1SubmitInput param,RP_E_MEX_GR1SubmitOutput outputDTO){
        this.param = param;
        this.outputDTO = outputDTO;
    }
    public void run() {
        // Execute
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        G_GIR_P01 girP01 = (G_GIR_P01) context.getBean("G_GIR_P01");
        param.setScr(true);
        //TODO
        GlobalProcessResult result=girP01.execute(param, outputDTO);//.appendErrors(errors).appendMessages(messages);
        AuditTrailHeaderBean auditTrailHeaderBean=new AuditTrailHeaderBean();
        auditTrailHeaderBean.setIdAudit((Integer) result.getParameter().get("ID_AUDIT"));
        auditTrailHeaderBean.setReference(result.getParameter().get("idBatchRefNo")+":"+result.getParameter().get("closeMonthyear"));
        String status=result.getParameter().get("status").toString();
        if(status.equals("0")){
        	auditTrailHeaderBean.setStatus("Successful");
        }else if(status.equals("1")){
        	auditTrailHeaderBean.setStatus("Failed");
        }else if(status.equals("2")){
        	auditTrailHeaderBean.setStatus("in process");
        }
        CommonUtils.auditTrailUpdate(auditTrailHeaderBean);
    }
}