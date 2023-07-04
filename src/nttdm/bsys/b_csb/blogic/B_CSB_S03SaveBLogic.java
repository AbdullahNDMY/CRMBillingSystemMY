package nttdm.bsys.b_csb.blogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.BillAccountInfo;
import nttdm.bsys.b_csb.dto.PaymentInformation;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;
import nttdm.bsys.common.util.G_CSB_P01_Check;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Come from BAC_S02 edit screen <br>
 * B_CSB_03 save logic
 * 
 * @author loamanma
 * 
 */
public class B_CSB_S03SaveBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * BLogicMessages
     */
    private BLogicMessages messages;

    /**
     * BLogicMessages
     */
    private BLogicMessages errors;

    private String receiptNo = "";

    /**
     * @param input
     *            input data
     * @return BLogicResult result value
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();

        messages = new BLogicMessages();
        errors = new BLogicMessages();

        String actionFlg = CommonUtils.toString(input.get("actionFlg")).trim();

        Map<String, Object> output = new HashMap<String, Object>();

        boolean newMode;
        if ("new".equalsIgnoreCase(actionFlg)) {
            newMode = true;
        } else {
            newMode = false;
        }

        // check success
        if (validate(input, newMode) && checkDebitInfoBillAcc(input, newMode)) {
            // new model
            if (newMode) {
                doNewModel(input);

                this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC003"));
                result.setResultString("success");
            } else {
                // edit model
                doEditModel(input);

                this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC003"));
                result.setResultString("success");
            }
        } else {
            result.setResultString("faild");
        }

        output.put("receiptNo", receiptNo);
        result.setErrors(errors);
        result.setMessages(messages);
        result.setResultObject(output);
        return result;
    }

    /**
     * check input data format
     * 
     * @param input
     *            input data
     * @param modelFlag
     *            true:new model,false:edit model
     * @return true:check success,false:check fail
     */
    private boolean validate(Map<String, Object> input, boolean modelFlag) {

        // check batch if is running
        G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("save");
        gCsbP01CheckInput.setMessageParam2("Cash book");
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();

        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
        } else {

            // bill account info
            BillAccountInfo billAccountInfo = (BillAccountInfo) input.get("billAccountInfo");

            // pmtMethod
            String pmtMethod = CommonUtils.toString(billAccountInfo.getPaymentMethod());

            // transDate
            String transDate = CommonUtils.toString(input.get("transDate"));

            // amtRefunded
            String amtRefunded = CommonUtils.toString(input.get("amtRefunded")).trim();

            // Payment Method
            if (CommonUtils.isEmpty(pmtMethod)) {
                this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC005", new Object[] { "Payment Method" }));
            }

            // Transaction Date
            if (CommonUtils.isEmpty(transDate)) {
                this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC005", new Object[] { "Transaction Date" }));
            }

            // Amount Refunded
            if (modelFlag) {
                if (CommonUtils.isEmpty(amtRefunded)) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC005", new Object[] { "Amount Refunded" }));
                } else if (!isDecimal(amtRefunded)) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC012", new Object[] { "Amount Refunded" }));
                    return false;
                } else if (new BigDecimal(amtRefunded).compareTo(BigDecimal.ZERO) <= 0) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC014", new Object[] { "Amount Refunded" }));
                    return false;
                }
                // check payment Input data
                checkPaymentInputData(input);
            }
        }

        if (this.errors.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check payment Input data
     * 
     * @param input
     *            input data
     */
    @SuppressWarnings("unchecked")
    private void checkPaymentInputData(Map<String, Object> input) {

        // amtRefunded
        String strAmtReceived = CommonUtils.toString(input.get("amtRefunded")).trim();
        BigDecimal amtRefundedNum = new BigDecimal(strAmtReceived);

        // payment infomation
        List<PaymentInformation> paymentInformationList = (List<PaymentInformation>) input.get("paymentInformationList");

        // Payment infomation not empty
        if (CommonUtils.notEmpty(paymentInformationList)) {

            BigDecimal total = BigDecimal.ZERO;

            int i = 0;
            for (PaymentInformation info : paymentInformationList) {

                i = i + 1;

                // appied check
                if ("Y".equals(info.getAppliedTo())) {

                    // BigDecimal balance amount
                    BigDecimal amtDue = info.getBalanceAmount();

                    // String refund amount
                    String strAmtPaid = info.getRefundAmount();

                    if (CommonUtils.notEmpty(strAmtPaid) && isDecimal(strAmtPaid)) {

                        if (Double.parseDouble(strAmtPaid) == 0.0) {
                            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC014",
                                    new Object[] { "Payment Information row " + i + " Refund" }));
                        } else {
                            total = total.add(new BigDecimal(strAmtPaid));
                        }

                    } else {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC012", new Object[] { "Payment Information row "
                                + i + " Refund" }));
                    }
                    if (new BigDecimal(strAmtPaid).compareTo(amtDue) > 0) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC041", new Object[] {
                                "Payment Information row " + i + " Refund", amtDue }));
                    }
                }
            }
            // to check Amount refunded be fully settled with receipt.
            // means balance shuold be ZERO
            if (total.compareTo(amtRefundedNum) != 0) {
                this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105",
                        new Object[] { "Refund balance must be equal to zero" }));
            }
        }
    }

    /**
     * new model process
     * 
     * @param input
     *            input data
     */
    @SuppressWarnings("unchecked")
    private void doNewModel(Map<String, Object> input) {

        Map<String, Object> parameter = new HashMap<String, Object>();

        // insert T_CSB_H
        insertOrUpdataTCsbH(input, parameter, true);

        // Update Total Amount Due
        updateDAO.execute("B_CSB.updateTotalAmount", parameter);

        // Payment Info list
        List<PaymentInformation> paymentInformationList = (List<PaymentInformation>) input.get("paymentInformationList");

        if (!CommonUtils.isEmpty(paymentInformationList)) {
            for (PaymentInformation info : paymentInformationList) {
                // APPLIED
                String applied = info.getAppliedTo();
                if ("Y".equalsIgnoreCase(applied)) {

                    // ID_REF
                    parameter.put("idRef", info.getReceiptNo());
                    // AMT_PAID
                    parameter.put("amtPaid", info.getRefundAmount());
                    // AMT_DUE
                    parameter.put("amtDue", info.getBalanceAmount());
                    // FOREX_LOSS
                    parameter.put("forexLoss", null);
                    // FOREX_GAIN
                    parameter.put("forexGain", null);
                    // insert T_CSB_D
                    updateDAO.execute("INSERT.BSYS.BCSB.SQL_4.2", parameter);

                    // update csbBalance amount
                    updateDAO.execute("B_CSB.updateCSBBalance", parameter);
                }

            }
        }

        // ID_AUDIT
        Integer idAudit = Integer.parseInt(CommonUtils.toString(parameter.get("idAudit")));
        // End Audit Trail
        CommonUtils.auditTrailEnd(idAudit);
    }

    /**
     * edit model process
     * 
     * @param input
     *            input data
     */
    private void doEditModel(Map<String, Object> input) {
        Map<String, Object> parameter = new HashMap<String, Object>();

        // update T_CSB_H
        insertOrUpdataTCsbH(input, parameter, false);

        // ID_AUDIT
        Integer idAudit = Integer.parseInt(CommonUtils.toString(parameter.get("idAudit")));
        // End Audit Trail
        CommonUtils.auditTrailEnd(idAudit);
    }

    /**
     * insert or update T_CSB_H
     * 
     * @param input
     *            input data
     * @param parameter
     *            insert or update data parameter
     * @param modelFlag
     *            true:new model,false:edit model
     */
    @SuppressWarnings("unchecked")
    private void insertOrUpdataTCsbH(Map<String, Object> input, Map<String, Object> parameter, boolean modelFlag) {
        // BillingSystemUserValueObject
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) input.get("uvo");
        // login user id
        String idUser = uvo.getId_user();
        // actionFlg
        String actionFlg = "";

        if (modelFlag) {
            // get G_CDM_P01 object
            G_CDM_P01 gCdmP01 = new G_CDM_P01(queryDAO, updateDAO, idUser);
            // RECEIPT_NO
            receiptNo = gCdmP01.getGenerateCode("RCPNO", idUser);

            // actionFlg
            actionFlg = BillingSystemConstants.AUDIT_TRAIL_CREATED;
        } else {
            // actionFlg
            actionFlg = BillingSystemConstants.AUDIT_TRAIL_EDITED;
            // RECEIPT_NO
            receiptNo = CommonUtils.toString(input.get("receiptNo"));
        }
        // Audit Trail
        Integer idAudit = CommonUtils.auditTrailBegin(idUser, BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CSB, receiptNo,
                "OPEN", actionFlg);

        // bill account info
        BillAccountInfo billAccountInfo = (BillAccountInfo) input.get("billAccountInfo");
        // transdate
        String transDate = CommonUtils.toString(input.get("transDate"));
        // amtRefund
        String amtRefunded = CommonUtils.toString(input.get("amtRefunded"));
        // bankAcc
        String bankAcc = CommonUtils.toString(input.get("bankAcc"));
        // refundRefernece
        String refundRefernece = CommonUtils.toString(input.get("refundRefernece")).trim();
        // remark
        String remark = CommonUtils.toString(input.get("remark")).trim();

        // T_CSB_H data
        // RECEIPT_NO
        parameter.put("receiptNo", paddingRightSpace(receiptNo, 20));
        // ID_CUST
        parameter.put("idCust", billAccountInfo.getIdCust());
        // ID_COM_BANK
        parameter.put("bankAcc", bankAcc);
        // OTHER_PAYER
        parameter.put("other", "");
        // PMT_METHOD
        parameter.put("pmtMethod", billAccountInfo.getPaymentMethod());
        // DATE_TRANS
        parameter.put("transDate", transDate);
        // RECEIPT_REF
        parameter.put("receiptRef", "");
        // CUR_CODE
        parameter.put("curCode", billAccountInfo.getBillCurrency());
        // REMARK
        parameter.put("remark", remark);
        // PMT_STATUS
        parameter.put("paymentStatus", "F");
        // REFERENCE1
        parameter.put("paymentRef1", "");
        // REFERENCE2
        parameter.put("paymentRef2", refundRefernece);

        // IS_CLOSED = 0
        // IS_DELETED = 0
        // ID_LOGIN
        parameter.put("idLogin", idUser);
        // AMT_RECEIVED
        parameter.put("amtReceived", amtRefunded);
        // BANK_CHARGE
        parameter.put("bankCharge", BigDecimal.ZERO);

        // payment infomation
        List<PaymentInformation> paymentInformationList = (List<PaymentInformation>) input.get("paymentInformationList");

        BigDecimal total = BigDecimal.ZERO;

        for (PaymentInformation info : paymentInformationList) {

            // appied check
            if ("Y".equals(info.getAppliedTo())) {

                // String refund amount
                String strAmtPaid = info.getRefundAmount();

                total = total.add(new BigDecimal(strAmtPaid));

            }
        }

        // BALANCE_AMT
        parameter.put("balanceAmt", new BigDecimal(amtRefunded).subtract(total));
        // ID_AUDIT
        parameter.put("idAudit", idAudit);
        // ID_BILL_ACCOUNT
        parameter.put("idBillAccount", billAccountInfo.getIdBillAccount());
        // IS_EXPORT = 0
        // PAID_PRE_INVOICE default value
        parameter.put("paidPreInvoice", "0");
        // OVER_PAID default value
        parameter.put("overPaid", "0");

        // new model
        if (modelFlag) {
            updateDAO.execute("INSERT.BSYS.BCSB.SQL_4.1", parameter);
        } else {
            // edit model
            updateDAO.execute("B_CSB.updateCSBHeaderEdit", parameter);
        }
    }

    /**
     * this method is copy from csb_02<br>
     * previous purpose: <br>
     * 1. check if payment Info bill account is same <br>
     * 2. check if amount refund is lower than balance <br>
     * 
     * @param input
     * @return check result
     */
    @SuppressWarnings("unchecked")
    private boolean checkDebitInfoBillAcc(Map<String, Object> input, boolean modelFlag) {
        boolean result = true;

        // Payment Info list
        List<PaymentInformation> paymentInformationList = (List<PaymentInformation>) input.get("paymentInformationList");

        if (!CommonUtils.isEmpty(paymentInformationList) && modelFlag) {
            int i = 0;
            for (PaymentInformation info : paymentInformationList) {
                i = i + 1;

                Map<String, Object> receiptInfo = queryDAO.executeForMap("B_CSB.getReceiptInfo", info.getReceiptNo());

                BigDecimal balancAmt = new BigDecimal(CommonUtils.toString(receiptInfo.get("BALANCE_AMT")));
                BigDecimal refundAmt = new BigDecimal(info.getRefundAmount());
                if (refundAmt.compareTo(balancAmt) > 0) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC041", new Object[] {
                            "Payment Information row " + i + " Refund", balancAmt }));
                    result = false;
                }

            }
        }

        return result;
    }

    /**
     * check number
     * 
     * @param obj
     *            check object
     * @return true:check success,false:check fail
     */
    private static boolean isDecimal(String obj) {
        String regexNumber = "\\d{1,15}([\\.]\\d{0,2})?$";
        return obj.matches(regexNumber);
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