/*
 * @(#)RP_B_BAC_S02_03_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_03_02Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_03_02Output;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class of B-BAC Edit->Save.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_03_02BLogic extends AbstractRP_B_BAC_S02_03_02BLogic {

    private BLogicMessages msgs;
    private BLogicMessages errors;

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S02_03_02Input param) {
        BLogicResult result = new BLogicResult();
        msgs = new BLogicMessages();
        errors = new BLogicMessages();
        RP_B_BAC_S02_03_02Output output = new RP_B_BAC_S02_03_02Output();

        if (check(param)) {
            Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_B,
                    BillingSystemConstants.SUB_MODULE_B_BAC, param.getInputHeaderInfo().getIdBillAccount(), null,
                    BillingSystemConstants.AUDIT_TRAIL_EDITED);

            HashMap<String, Object> inputData = new HashMap<String, Object>();
            String idBillAcount = param.getInputHeaderInfo().getIdBillAccount();
            inputData.put("idBillAccount", idBillAcount);
            inputData.put("paymentMethod", param.getInputHeaderInfo().getPaymentMethod());
            inputData.put("custAdrType", param.getInputHeaderInfo().getCustAdrType());
            inputData.put("contactType", "".equals(param.getInputHeaderInfo().getContactType()) ? 
                    " " : param.getInputHeaderInfo().getContactType());
            inputData.put("exportSingPost", param.getInputHeaderInfo().getExportSingPost() == null ? 
                    "0" : param.getInputHeaderInfo().getExportSingPost());
            inputData.put("dateUpdated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            inputData.put("idLogin", param.getUvo().getId_user());
            inputData.put("idAudit", idAudit);
            inputData.put("multiBillPeriod", param.getInputHeaderInfo().getMultiBillPeriod() == null ? 
                    "0" : param.getInputHeaderInfo().getMultiBillPeriod());
            inputData.put("isDisplayExpAmt", param.getInputHeaderInfo().getIsDisplayExpAmt() == null ? 
                    "0" : param.getInputHeaderInfo().getIsDisplayExpAmt());
            inputData.put("delivery", param.getInputHeaderInfo().getDelivery());
            inputData.put("deliveryEmail", param.getInputHeaderInfo().getDeliveryEmail());
            inputData.put("taxType", param.getInputHeaderInfo().getTaxType());

            String count = queryDAO.executeForObject("B_BAC.SELECT_BAC_D_COUNT", idBillAcount, String.class);
            if (Integer.parseInt(count) != 0) {
                // update header info SQL 8
                updateDAO.execute("B_BAC.updateBillAccount", inputData);
                // update T_CUST_PLAN_H
                updateDAO.execute("B_BAC.updateT_CUST_PLAN_H", inputData);
            } else {
                inputData.put("billCurrency", param.getInputHeaderInfo().getBillCurrency());
                inputData.put("exportCurrency", param.getInputHeaderInfo().getExportCurrency());
                inputData.put("fixedForex", param.getInputHeaderInfo().getFixedForex());
                updateDAO.execute("B_BAC.updateBillAccount1", inputData);
                // update T_CUST_PLAN_H
                updateDAO.execute("B_BAC.updateT_CUST_PLAN_H1", inputData);
            }
            // End Audit Trail
            CommonUtils.auditTrailEnd(idAudit);
            output.setIdBillAccount(param.getInputHeaderInfo().getIdBillAccount());
            output.setIdCustPlan(param.getInputHeaderInfo().getIdCustPlan());

            BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[] {});
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);

            result.setResultString("success");
        } else {
            try {
                BeanUtils.copyProperties(output, param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            result.setResultString("fail");
        }

        result.setErrors(errors);
        result.setMessages(msgs);
        result.setResultObject(output);
        return result;
    }

    private boolean check(RP_B_BAC_S02_03_02Input param) {
        boolean checkResutl = true;
        String fixedForex = CommonUtils.toString(param.getInputHeaderInfo().getFixedForex());
        if (!CommonUtils.isEmpty(fixedForex) && Double.parseDouble(fixedForex) == 0.0) {
            checkResutl = false;
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new BLogicMessage("errors.ERR1SC018", new Object[] { "Fixed Forex", "a non zero value." }));
        }
        return checkResutl;
    }
}