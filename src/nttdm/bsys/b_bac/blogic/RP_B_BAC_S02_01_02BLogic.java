/*
 * @(#)RP_B_BAC_S02_01_02BLogic.java
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
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_01_02Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_01_02Output;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;

import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class of B-BAC NEW Save.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_01_02BLogic extends AbstractRP_B_BAC_S02_01_02BLogic {

    /**
     * B-BAC NEW Save.
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S02_01_02Input param) {
        BLogicResult result = new BLogicResult();
        String idCode = "BACNO";
        String idUser = param.getUvo().getId_user();
        String[] idKeys = param.getIdKeys();
        String[] idCustPlans = new String[idKeys.length];
        String idBillAcc = "";
        for (int i = 0; i < idKeys.length; i++) {
            String idCustPlan = idKeys[i].split("_")[0];
            idCustPlans[i] = idCustPlan;
            idBillAcc = idKeys[i].split("_")[1];

        }
        G_CDM_P01 gCdmP01 = new G_CDM_P01(this.queryDAO, this.updateDAO, idUser);
        // generate by Generated Running No. [G_CDM_P01]
        String idBillAccount = "";
        try {
            idBillAccount = gCdmP01.getGenerateCode(idCode, idUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // get old info Bill_curr, ...
        HashMap<String, Object> m1 = new HashMap<String, Object>();
        m1.put("idBillAccount", idBillAcc);
        Map<String, Object> map = queryDAO.executeForMap("B_BAC.getBillingAccountInfo", m1);

        Integer idAudit = CommonUtils.auditTrailBegin(idUser, BillingSystemConstants.MODULE_B,
                BillingSystemConstants.SUB_MODULE_B_BAC, idBillAccount, null,
                BillingSystemConstants.AUDIT_TRAIL_CREATED);

        HashMap<String, Object> inputData = new HashMap<String, Object>();
        inputData.put("idBillAccount", idBillAccount);
        inputData.put("idCust", param.getInputHeaderInfo().getIdCust());
        inputData.put("paymentMethod", param.getInputHeaderInfo().getPaymentMethod());
        inputData.put("billCurrency", map.get("BILL_CURRENCY"));
        inputData.put("isDisplayExpAmt", map.get("IS_DISPLAY_EXP_AMT"));
        inputData.put("exportCurrency", map.get("EXPORT_CURRENCY"));
        inputData.put("fixedForex", map.get("FIXED_FOREX"));
        inputData.put("custAdrType", param.getInputHeaderInfo().getCustAdrType());
        inputData.put("contactType", "".equals(param.getInputHeaderInfo().getContactType()) ?
                " " : param.getInputHeaderInfo().getContactType());
        inputData.put("isDeleted", "0");
        inputData.put("dateCreated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        inputData.put("dateUpdated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        inputData.put("idLogin", idUser);
        inputData.put("idCustPlans", idCustPlans);
        inputData.put("idAudit", idAudit);
        inputData.put("isSingPost", param.getInputHeaderInfo().getExportSingPost() == null ? 
                "0" : param.getInputHeaderInfo().getExportSingPost());
        inputData.put("totalAmtDue", "0");
        inputData.put("delivery", param.getInputHeaderInfo().getDelivery());
        inputData.put("deliveryEmail", param.getInputHeaderInfo().getDeliveryEmail());
        // insert header info SQL 2
        updateDAO.execute("B_BAC.insertHeaderInfo", inputData);
        if (0 < idCustPlans.length) {
            // SQL 3 T_BAC_D
            updateDAO.execute("B_BAC.createBillAccNoDetailBlock", inputData);
            // update T_CUST_PLAN_H BIll_ACC
            updateDAO.execute("B_BAC.updateT_CUST_PLAN_H_BILL_ACC", inputData);
        }
        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
        RP_B_BAC_S02_01_02Output output = new RP_B_BAC_S02_01_02Output();
        output.setIdBillAccount(paddingRightSpace(idBillAccount, 20));
        output.setIdCustPlan(idCustPlans[0]);
        // success
        BLogicMessages msgs = new BLogicMessages();
        BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[] {});
        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
        result.setMessages(msgs);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

    /**
     * padding right space
     * 
     * @param str
     * @param len
     * @return
     */
    private static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for (int i = str.length(); i < len; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}