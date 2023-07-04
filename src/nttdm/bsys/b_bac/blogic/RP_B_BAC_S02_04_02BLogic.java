/*
 * @(#)RP_B_BAC_S02_04_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_04_02Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_04_02Output;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class of B-BAC Merge Save.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_04_02BLogic extends AbstractRP_B_BAC_S02_04_02BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S02_04_02Input param) {
        BLogicResult result = new BLogicResult();
        // setup parameters
        // String idKeys
        String[] idKeys = param.getIdKeys();
        String[] idBillAccs = new String[idKeys.length];
        String[] idCustPlans = new String[idKeys.length];
        for (int i = 0; i < idKeys.length; i++) {
            String idCustPlan = idKeys[i].split("_")[0];
            String idBillAccount = idKeys[i].split("_")[1];
            idBillAccs[i] = idBillAccount;
            idCustPlans[i] = idCustPlan;
        }
        /**
         * Audit Trail
         */
        Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_B,
                BillingSystemConstants.SUB_MODULE_B_BAC, param.getInputHeaderInfo().getIdBillAccount(), null,
                BillingSystemConstants.AUDIT_TRAIL_EDITED);

        HashMap<String, Object> inputData = new HashMap<String, Object>();
        inputData.put("idBillAccount", param.getInputHeaderInfo().getIdBillAccount());
        // get header info
        inputData.put("idCustPlans", idCustPlans);
        inputData.put("exportSingPost", param.getInputHeaderInfo().getExportSingPost() == null ? 
                "0" : param.getInputHeaderInfo().getExportSingPost());
        inputData.put("dateUpdated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        inputData.put("idLogin", param.getUvo().getId_user());
        inputData.put("idAudit", idAudit);

        String idBillAcc = CommonUtils.toString(param.getInputHeaderInfo().getIdBillAccount()).trim();
        Map<String, Object> billAccMap = queryDAO.executeForMap("B_BAC.getBillAccInfo", idBillAcc);
        // update header info SQL 11a
        updateDAO.execute("B_BAC.updateBillAccountNo", inputData);
        // update paymentMethod SQL 11c
        updateDAO.execute("B_BAC.updateIsSingPost", inputData);
        if (0 < idCustPlans.length) {
            inputData.put("paymentMethod", billAccMap.get("PAYMENT_METHOD"));
            // update T_CUST_PLAN_H BIll_ACC
            updateDAO.execute("B_BAC.updateT_CUST_PLAN_H_BILL_ACC", inputData);
        }
        // End Audit Trail
        CommonUtils.auditTrailEnd(idAudit);
        
        RP_B_BAC_S02_04_02Output output = new RP_B_BAC_S02_04_02Output();
        output.setIdBillAccount(param.getInputHeaderInfo().getIdBillAccount());
        output.setIdCustPlan(idCustPlans[0]);
        BLogicMessages msgs = new BLogicMessages();
        BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[] {});
        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
        result.setMessages(msgs);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
}