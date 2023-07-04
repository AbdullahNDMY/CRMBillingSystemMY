package nttdm.bsys.b_csb.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.PaymentInformation;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Come from BAC_S03 view screen <br>
 * B_CSB_03 delete logic
 * 
 * @author loamanma
 * 
 */
public class B_CSB_S03DelBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * @param input
     *            input data
     * @return BLogicResult result value
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        BLogicMessages messages = new BLogicMessages();

        // check if batch is running
        G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("Delete");
        gCsbP01CheckInput.setMessageParam2("Cash book");
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();

        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            messages.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
            result.setErrors(messages);
            result.setResultString("faild");
            return result;
        }
        // BillingSystemUserValueObject
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) input.get("uvo");

        String receiptNo = CommonUtils.toString(input.get("receiptNo"));

        Map<String, Object> receiptInfo = queryDAO.executeForMap("B_CSB.getReceiptInfo", addSpace(receiptNo, 20));

        if (receiptInfo == null || receiptInfo.isEmpty()) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", "Receipt: " + receiptNo.trim()
                    + " has already been Deleted!"));
            result.setErrors(messages);
            // result.setResultObject(output);
            result.setResultString("faild");
            return result;
        }

        /**
         * Audit Trail
         */
        //Modify Status
        String status="";
        if(CommonUtils.toString(receiptInfo.get("IS_CLOSED")).equals("0")){
            status="OPEN";
        }else{
            status="CLOSED";
        }
        Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CSB,
                receiptNo, status, BillingSystemConstants.AUDIT_TRAIL_DELETED);

        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("receiptNo", addSpace(receiptNo, 20));
        parameter.put("idLogin", uvo.getId_user());
        parameter.put("idAudit", idAudit);
        // delete csb_h
        updateDAO.execute("B_CSB.updateCsbHeaderDelete", parameter);

        // AMT_RECEIVED
        parameter.put("amtReceived", CommonUtils.toString(receiptInfo.get("AMT_RECEIVED")));
        // ID_BILL_ACCOUNT
        parameter.put("idBillAccount", CommonUtils.toString(receiptInfo.get("ID_BILL_ACCOUNT")));
        // Update Total Amount Due
        updateDAO.execute("B_CSB.updateTotalAmountDelete", parameter);

        List<PaymentInformation> paymentInformationList = queryDAO.executeForObjectList("B_CSB.getReceiptPaymentInfoList", addSpace(receiptNo, 20));

        if (!CommonUtils.isEmpty(paymentInformationList)) {

            // delete csb_d
            updateDAO.execute("B_CSB.updateCsbDetailDelete", parameter);

            // roll back refund csb_h
            for (PaymentInformation payInfo : paymentInformationList) {
                parameter.put("idRef", payInfo.getReceiptNo());
                parameter.put("amtPaid", payInfo.getRefundAmount());
                updateDAO.execute("B_CSB.updateCSBBalanceDelete", parameter);
            }
        }
        // End Audit Trail
        CommonUtils.auditTrailEnd(idAudit);
        messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC005"));
        result.setMessages(messages);
        // result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

    private String addSpace(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < len) {
            sb.append(" ");
        }
        return sb.toString();
    }
}