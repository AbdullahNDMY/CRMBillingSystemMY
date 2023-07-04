package nttdm.bsys.b_csb.blogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
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
 * new or update data
 * 
 * @author gplai
 * 
 */
public class B_CSBS02_NEBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * BLogicMessages
     */
    private BLogicMessages messages;

    /**
     * BLogicMessages
     */
    private BLogicMessages errors;

    /**
     * APPLIED CHECK
     */
    private static final String DB_APPLIED = "1";

    /**
     * CB_AUTO_OFFSET CST
     */
    private static final String CB_AUTO_OFFSET_CST = "CST";

    /**
     * CB_AUTO_OFFSET BAC
     */
    private static final String CB_AUTO_OFFSET_BAC = "BAC";

    private BigDecimal refundAmount = BigDecimal.ZERO;
    /**
     * do blogic
     * 
     * @param input
     *            input data
     * @return BLogicResult result value
     */
    public BLogicResult execute(Map<String, Object> input) {
    	
    	input.put("amtReceived", CommonUtils.toString(input.get("amtReceived")).trim());
        BLogicResult result = new BLogicResult();
        messages = new BLogicMessages();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        
        // RECEIPT_NO
        String receiptNo = CommonUtils.toString(input.get("receiptNo")).trim();
        if (CommonUtils.isEmpty(receiptNo)) {
            input.put("actionFlg" , "");
            refundAmount = BigDecimal.ZERO;
        }else{
            input.put("actionFlg" , "edit");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("idRef", paddingRightSpace(receiptNo, 20));
            Map<String, Object> refundInfo = queryDAO.executeForMap("B_CSB.getRefundTotalForReceipt", param);
            String totalNum = CommonUtils.toString(refundInfo.get("TOTAL_NUM"));
            if (!"0".equals(totalNum)) {
                String REFUND_AMOUNT = CommonUtils.toString(refundInfo.get("REFUND_AMOUNT"));
                refundAmount = new BigDecimal(REFUND_AMOUNT);
            }else{
                refundAmount = BigDecimal.ZERO;
            }
        }

        // input data to output data
        this.copyParam(input, output);

        boolean newMode = true;
        if (input.get("actionFlg") != null && input.get("actionFlg").equals("edit")) {
            newMode = false;
        }
        try {
            // check success
            if (validate(input, newMode)) {
                // check BILL_AMOUNT TotalAmtPaid
//                if (checkBillAmountAndTotalPaidAmt(input)) {
                    //check IF cboBillingAccountNo. <> selected debit information billing account No.
                    if (checkDebitInfoBillAcc(input)) {
                        // new model
                        if (newMode) {
                            doNewModel(input);
                            // clear edit actionFlg
                            output.put("actionFlg", null);
                            this.messages.add(ActionMessages.GLOBAL_MESSAGE,
                                    new BLogicMessage("info.ERR2SC003"));
                            result.setResultString("success");
                        } else {
                            // edit model
                            doEditModel(input);
                            // clear edit actionFlg
                            output.put("actionFlg", null);
                            this.messages.add(ActionMessages.GLOBAL_MESSAGE,
                                    new BLogicMessage("info.ERR2SC003"));
                            result.setResultString("success");
                        }
                    } else {
                        result.setResultString("failer");
                    }
//                } else {
//                    result.setResultString("failer");
//                }
            } else {
                result.setResultString("failer");
            }
        } catch (Exception e) {
            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    "info.ERR2SC004"));
            result.setResultString("failer");
        }
        
        output.put("idRef", input.get("receiptNo"));
        result.setResultObject(output);
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
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
        // pattern BAC or CST
        String pattern = CommonUtils.toString(input.get("pattern"));
        // Transaction Date
        Date transDate = CommonUtils.toDate(input.get("transDate"),
                "dd/MM/yyyy");
        // BAC pattern
        if (CB_AUTO_OFFSET_BAC.equals(pattern)) {
            // Update Total Amount Due
            updateDAO.execute("UPDATE.BSYS.BCSB.TOTAL_AMOUNT", parameter);
        }
        // PreviousPayment
        BigDecimal previousPayment = new BigDecimal("0");
        // Debit infomation
        List<Debit_info_bean> listDebitInfos =
            (List<Debit_info_bean>) input.get("debitInfos");
        // Debit infomation not empty
        if (!CommonUtils.isEmpty(listDebitInfos)) {
            for (Debit_info_bean info : listDebitInfos) {
                // APPLIED
                String applied = info.getAPPLIED();
                // APPLIED checked
                if (DB_APPLIED.equals(applied)) {
                    // BigDecimal AMT_PAID
                    BigDecimal amtPaid = new BigDecimal("0");
                    // String AMT_PAID
                    String strAmtPaid = info.getAMT_PAID();
                    if (!CommonUtils.isEmpty(strAmtPaid)
                            && !CommonUtils.isNull(strAmtPaid)) {
                        amtPaid = new BigDecimal(strAmtPaid);
                    }
                    // ID_REF
                    parameter.put("idRef",
                            CommonUtils.toString(info.getID_REF()).trim());
                    // AMT_PAID
                    parameter.put("amtPaid", amtPaid);
                    // AMT_DUE
                    parameter.put("amtDue", info.getAMT_DUE());
                    // FOREX_LOSS
                    parameter.put("forexLoss", info.getFOREX_LOSS());
                    // FOREX_GAIN
                    parameter.put("forexGain", info.getFOREX_GAIN());
                    // insert T_CSB_D
                    updateDAO.execute("INSERT.BSYS.BCSB.SQL_4.2", parameter);
                    // update Debit Transaction
                    updateDebitTransaction(parameter, amtPaid, previousPayment,
                            transDate);
                }
            }
        }

        // ID_AUDIT
        Integer idAudit = Integer.parseInt(CommonUtils.
                toString(parameter.get("idAudit")));
        // End Audit Trail
        CommonUtils.auditTrailEnd(idAudit);
    }

    /**
     * edit model process
     * 
     * @param input
     *            input data
     */
    @SuppressWarnings("unchecked")
    private void doEditModel(Map<String, Object> input) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        Map<String, Object> parameter1 = new HashMap<String, Object>();
        // RECEIPT_NO
        String receiptNo = CommonUtils.toString(input.get("receiptNo"));
        parameter1.put("idRef", receiptNo.trim());
        // get header
        Map<String, Object> beforeUpdateCsbH = queryDAO.executeForMap(
                "SELECT.BSYS.BCSB.SQL008", parameter1);
        // update T_CSB_H
        insertOrUpdataTCsbH(input, parameter, false);
        // pattern BAC or CST
        String pattern = CommonUtils.toString(input.get("pattern"));
        // Transaction Date
        Date transDate = CommonUtils.toDate(input.get("transDate"),
                "dd/MM/yyyy");
        // BAC pattern
        if (CB_AUTO_OFFSET_BAC.equals(pattern)) {
            // String AMT_RECEIVED
            String strAmtReceived = CommonUtils.
                toString(input.get("amtReceived"));
            // Previous BigDecimal AMT_RECEIVED
            BigDecimal preAmtReceived = new BigDecimal(
                    CommonUtils.toString(beforeUpdateCsbH.get("AMT_RECEIVED")));
            // BigDecimal AMT_RECEIVED
            BigDecimal amtReceived = new BigDecimal(strAmtReceived);
            // AMT_RECEIVED Document ProcessFlow-B-CSB-S02e sheet row 178
            parameter.put("amtReceived", amtReceived.subtract(preAmtReceived));
            // Update Total Amount Due
            updateDAO.execute("UPDATE.BSYS.BCSB.TOTAL_AMOUNT", parameter);
        }

        // Debit infomation
        List<Debit_info_bean> listDebitInfos = (List<Debit_info_bean>) 
            input.get("debitInfos");
        // Debit infomation not empty
        if (!CommonUtils.isEmpty(listDebitInfos)) {
            for (Debit_info_bean info : listDebitInfos) {
                // APPLIED
                String applied = info.getAPPLIED();
                // BigDecimal AMT_PAID
                BigDecimal amtPaid = new BigDecimal("0");
                // String AMT_PAID
                String strAmtPaid = info.getAMT_PAID();
                if (!CommonUtils.isEmpty(strAmtPaid)
                        && !CommonUtils.isNull(strAmtPaid)) {
                    amtPaid = new BigDecimal(strAmtPaid);
                }
                // ID_REF
                parameter.put("idRef", CommonUtils.toString(
                        info.getID_REF()).trim());
                // AMT_PAID
                parameter.put("amtPaid", amtPaid);
                // AMT_DUE
                parameter.put("amtDue", info.getAMT_DUE());
                // FOREX_LOSS
                parameter.put("forexLoss", info.getFOREX_LOSS());
                // FOREX_GAIN
                parameter.put("forexGain", info.getFOREX_GAIN());
                // APPLIED checked
                if (DB_APPLIED.equals(applied)) {
                    Map<String, Object> csbDData = queryDAO.executeForMap(
                            "SELECT.BSYS.BCSB.CSB_D_KEY_DEL_IN", parameter);
                    // Previous AMT_PAID
                    BigDecimal previousPayment = new BigDecimal("0");
                    // ID_REF not in T_CSB_D
                    if (CommonUtils.isNull(csbDData)) {
                        // insert T_CSB_D
                        updateDAO.execute(
                                "INSERT.BSYS.BCSB.SQL_4.2", parameter);
                    } else {
                        //delete flag
                        String isDeleted =  CommonUtils.toString(
                                csbDData.get("IS_DELETED"));
                        // data not delete
                        if ("0".equals(isDeleted)) {
                            // Previous AMT_PAID
                            previousPayment = new BigDecimal(
                               CommonUtils.toString(csbDData.get("AMT_PAID")));
                        }
                        // update T_CSB_D
                        updateDAO.execute(
                                "UPDATE.BSYS.BCSB.SQL_4.5", parameter);
                    }
                    // update Debit Transaction
                    updateDebitTransaction(parameter, amtPaid, previousPayment,
                            transDate);
                } else {
                    Map<String, Object> csbDData = queryDAO.executeForMap(
                            "SELECT.BSYS.BCSB.CSB_D_KEY", parameter);
                    // ID_REF in T_CSB_D
                    if (!CommonUtils.isNull(csbDData)) {
                        // Previous AMT_PAID
                        BigDecimal previousPayment = new BigDecimal(
                                CommonUtils.toString(csbDData.get("AMT_PAID")));
                        // Delete T_CSB_D
                        updateDAO.execute(
                                "UPDATE.BSYS.BCSB.SQL_4.7", parameter);
                        // AMT_PAID
                        parameter.put("amtPaid", previousPayment);
                        //Update T_BIL_H
                        updateDAO.execute("UPDATE.BSYS.BCSB.SQL_4.8.2", 
                                parameter);
                        // update is_settled refer to #619
                        updateDAO.execute("UPDATE.BSYS.BCSB.SETTLED", parameter);
                    }
                }
            }
        }

        // ID_AUDIT
        Integer idAudit = Integer.parseInt(CommonUtils.
                toString(parameter.get("idAudit")));
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
    private void insertOrUpdataTCsbH(Map<String, Object> input,
            Map<String, Object> parameter, boolean modelFlag) {
        // BillingSystemUserValueObject
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) 
            input.get("uvo");
        // login user id
        String idUser = uvo.getId_user();
        // RECEIPT_NO
        String receiptNo = "";
        // actionFlg
        String actionFlg = "";
        if (modelFlag) {
            // get G_CDM_P01 object
            G_CDM_P01 gCdmP01 = new G_CDM_P01(queryDAO, updateDAO, idUser);
            // RECEIPT_NO
            receiptNo = gCdmP01.getGenerateCode("RCPNO", idUser);
            input.put("receiptNo", paddingRightSpace(receiptNo, 20));
            // actionFlg
            actionFlg = BillingSystemConstants.AUDIT_TRAIL_CREATED;
        } else {
            // actionFlg
            actionFlg = BillingSystemConstants.AUDIT_TRAIL_EDITED;
            // RECEIPT_NO
            receiptNo = CommonUtils.toString(input.get("receiptNo"));
        }
        // Audit Trail
        Integer idAudit = CommonUtils.auditTrailBegin
            (idUser, BillingSystemConstants.MODULE_B,
                        BillingSystemConstants.SUB_MODULE_B_CSB, receiptNo,
                        "OPEN", actionFlg);

        // String AMT_RECEIVED
        String strAmtReceived = CommonUtils.toString(input.get("amtReceived"));
        // BigDecimal AMT_RECEIVED
        BigDecimal amtReceived = new BigDecimal(strAmtReceived);
        // BALANCE_AMT
        BigDecimal balanceAmt = new BigDecimal("0");

        // Debit infomation
        List<Debit_info_bean> listDebitInfos = (List<Debit_info_bean>) 
            input.get("debitInfos");
        // Debit infomation not empty
        if (!CommonUtils.isEmpty(listDebitInfos)) {
            for (Debit_info_bean info : listDebitInfos) {
                // APPLIED
                String applied = info.getAPPLIED();
                if (DB_APPLIED.equals(applied)) {
                    // BigDecimal AMT_PAID
                    BigDecimal amtPaid = new BigDecimal("0");
                    // BigDecimal FOREX_LOSS
                    BigDecimal forexLoss = new BigDecimal("0");
                    // BigDecimal FOREX_GAIN
                    BigDecimal forexGain = new BigDecimal("0");
                    // String AMT_PAID
                    String strAmtPaid = info.getAMT_PAID();
                    // String FOREX_LOSS
                    String strForexLossStr = info.getFOREX_LOSS();
                    // String FOREX_GAIN
                    String strForexGain = info.getFOREX_GAIN();
                    if (!CommonUtils.isEmpty(strAmtPaid)
                            && !CommonUtils.isNull(strAmtPaid)) {
                        amtPaid = new BigDecimal(strAmtPaid);
                    }
                    if (!CommonUtils.isEmpty(strForexLossStr)
                            && !CommonUtils.isNull(strForexLossStr)) {
                        forexLoss = new BigDecimal(strForexLossStr);
                    }
                    if (!CommonUtils.isEmpty(strForexGain)
                            && !CommonUtils.isNull(strForexGain)) {
                        forexGain = new BigDecimal(strForexGain);
                    }
                    // tbxPayment + tbxForexLoss - tbxForexGain
                    balanceAmt = balanceAmt.add(amtPaid).
                        add(forexLoss).subtract(forexGain);
                }
            }
        }
        balanceAmt = amtReceived.subtract(refundAmount).subtract(balanceAmt);
        // T_CSB_H data
        // RECEIPT_NO
        parameter.put("receiptNo", receiptNo.trim());
        // ID_CUST
        parameter.put(
                "idCust", CommonUtils.toString(input.get("payer")).trim());
        // ID_COM_BANK
        parameter.put("bankAcc", input.get("bankAcc"));
        // OTHER_PAYER
        parameter.put("other", input.get("other"));
        // PMT_METHOD
        parameter.put("pmtMethod", input.get("pmtMethod"));
        // ID_BILL_ACCOUNT
        parameter.put("idBillAccount",
                CommonUtils.toString(input.get("idBillAccount")).trim());
        // DATE_TRANS
        parameter.put("transDate", input.get("transDate"));
        // RECEIPT_REF
        parameter.put("receiptRef", input.get("receiptRef"));
        // CUR_CODE
        parameter.put("curCode", input.get("curCode"));
        // AMT_RECEIVED
        parameter.put("amtReceived", amtReceived);
        // REMARK
        parameter.put("remark", input.get("remark"));
        // PMT_STATUS
        parameter.put("paymentStatus", input.get("paymentStatus"));
        // BALANCE_AMT
        parameter.put("balanceAmt", balanceAmt);
        // REFERENCE1
        parameter.put("paymentRef1", input.get("paymentRef1"));
        // REFERENCE2
        parameter.put("paymentRef2", input.get("paymentRef2"));
        BigDecimal bankCharge = new BigDecimal("0");
        if (CommonUtils.notEmpty(input.get("bankCharge"))) {
            bankCharge = new BigDecimal(CommonUtils.
                    toString(input.get("bankCharge")));
        }
        // BANK_CHARGE
        parameter.put("bankCharge", bankCharge);
        // PAID_PRE_INVOICE
        parameter.put("paidPreInvoice", input.get("paidPreInvoiceText"));
        // OVER_PAID
        parameter.put("overPaid", input.get("overPaidText"));
        // ID_LOGIN
        parameter.put("idLogin", idUser);
        // ID_AUDIT
        parameter.put("idAudit", idAudit);
        // new model
        if (modelFlag) {
            updateDAO.execute("INSERT.BSYS.BCSB.SQL_4.1", parameter);
        } else {
            // edit model
            updateDAO.execute("UPDATE.BSYS.BCSB.SQL_6.1", parameter);
        }
    }

    /**
     * update Debit Transaction
     * 
     * @param parameter
     *            input parameter
     * @param amtPaid
     *            AMT_PAID Data
     * @param previousPayment
     *            PreviousPayment Data
     * @param transDate
     *            Transaction Date
     */
    private void updateDebitTransaction(Map<String, Object> parameter,
            BigDecimal amtPaid, BigDecimal previousPayment, Date transDate) {
        // TotalAmtPaid
        BigDecimal totalAmtPaid = new BigDecimal("0");
        // BILL_AMOUNT
        BigDecimal billAmount = new BigDecimal("0");
        // LAST_PAYMENT_DATE
        Date lastPaymentDate = null;
        // totalAmtPaid = totalAmtPaid+tbxPayment-previousPayment
        totalAmtPaid = totalAmtPaid.add(amtPaid).subtract(previousPayment);
        // select T_BIL_H BY ID_REF
        Map<String, Object> bilHData = queryDAO.executeForMap(
                "SELECT.BSYS.BCSB.BIL_H_BY_KEY", parameter);
        // bilHData is not empty
        if (CommonUtils.notEmpty(bilHData)) {
            // PAID_AMOUNT
            String paidAmount = CommonUtils.
                toString(bilHData.get("PAID_AMOUNT"));
            // BILL_AMOUNT
            String strBillAmount = CommonUtils.
                toString(bilHData.get("BILL_AMOUNT"));
            // LAST_PAYMENT_DATE
            String strLastPaymentDate = CommonUtils.
                toString(bilHData.get("LAST_PAYMENT_DATE"));
            // LAST_PAYMENT_DATE not empty
            if (CommonUtils.notEmpty(strLastPaymentDate)) {
                lastPaymentDate = CommonUtils.toDate(strLastPaymentDate,
                        "dd/MM/yyyy");
            }

            billAmount = new BigDecimal(strBillAmount);
            totalAmtPaid = totalAmtPaid.add(new BigDecimal(paidAmount));
        }
        // IS_SETTLED
        String isSettled = "0";
        // BILL_AMOUNT = TotalAmtPaid
        if (billAmount.compareTo(totalAmtPaid) == 0) {
            isSettled = "1";
        }
        // LAST_PAYMENT_DATE not empty and LAST_PAYMENT_DATE >
        // Transaction Date
        if (CommonUtils.notEmpty(lastPaymentDate)
                && 0 < lastPaymentDate.compareTo(transDate)) {
            // LAST_PAYMENT_DATE
            parameter.put("lastPayment", lastPaymentDate);
        } else {
            // LAST_PAYMENT_DATE
            parameter.put("lastPayment", transDate);
        }
        // IS_SETTLED
        parameter.put("isSettled", isSettled);
        parameter.put("prePayment", previousPayment);
        // update T_BIL_H Debit infomation
        updateDAO.execute("UPDATE.BSYS.BCSB.NEW_UPDATE_BIL_H", parameter);
    }

    /**
     * check BILL_AMOUNT TotalAmtPaid
     * 
     * @param input
     *            input value
     * @return BILL_AMOUNT>TotalAmtPaid:true,BILL_AMOUNT<TotalAmtPaid:false
     */
    @SuppressWarnings("unchecked")
    private boolean checkBillAmountAndTotalPaidAmt(Map<String, Object> input) {
        boolean result = true;
        Map<String, Object> parameter = new HashMap<String, Object>();
        // RECEIPT_NO
        String receiptNo = CommonUtils.toString(input.get("receiptNo"));
        parameter.put("receiptNo", receiptNo.trim());
        // Debit infomation
        List<Debit_info_bean> listDebitInfos = (List<Debit_info_bean>) 
            input.get("debitInfos");
        // PreviousPayment
        BigDecimal previousPayment = new BigDecimal("0");
        int i = 0;
        // Debit infomation not empty
        if (!CommonUtils.isEmpty(listDebitInfos)) {
            for (Debit_info_bean info : listDebitInfos) {
                i = i + 1;
                // APPLIED
                String applied = info.getAPPLIED();
                if (DB_APPLIED.equals(applied)) {
                    // TotalAmtPaid
                    BigDecimal totalAmtPaid = new BigDecimal("0");
                    // BILL_AMOUNT
                    BigDecimal billAmount = new BigDecimal("0");
                    // ID_REF
                    parameter.put("idRef",
                            CommonUtils.toString(info.getID_REF()).trim());
                    // BigDecimal AMT_PAID
                    BigDecimal amtPaid = new BigDecimal("0");
                    // String AMT_PAID
                    String strAmtPaid = info.getAMT_PAID();
                    if (CommonUtils.notEmpty(strAmtPaid)
                            && isDecimal(strAmtPaid)) {
                        amtPaid = new BigDecimal(strAmtPaid);
                    }
                    Map<String, Object> csbDData = queryDAO.executeForMap(
                            "SELECT.BSYS.BCSB.CSB_D_KEY", parameter);
                    if (CommonUtils.notEmpty(csbDData)) {
                        // Previous AMT_PAID
                        previousPayment = new BigDecimal(
                                CommonUtils.toString(csbDData.get("AMT_PAID")));
                    }
                    // totalAmtPaid = totalAmtPaid+tbxPayment-previousPayment
                    totalAmtPaid = totalAmtPaid.add(amtPaid).subtract(
                            previousPayment);
                    // select T_BIL_H BY ID_REF
                    Map<String, Object> bilHData = queryDAO.executeForMap(
                            "SELECT.BSYS.BCSB.BIL_H_BY_KEY", parameter);
                    // bilHData is not empty
                    if (CommonUtils.notEmpty(bilHData)) {
                        // PAID_AMOUNT
                        String paidAmount = CommonUtils.
                            toString(bilHData.get("PAID_AMOUNT"));
                        // BILL_AMOUNT
                        String strBillAmount = CommonUtils.
                            toString(bilHData.get("BILL_AMOUNT"));
                        billAmount = new BigDecimal(strBillAmount);
                        totalAmtPaid = totalAmtPaid.add(new BigDecimal(
                                paidAmount));
                    }
                    // BILL_AMOUNT<TotalAmtPaid
                    if (billAmount.compareTo(totalAmtPaid) == -1) {
                        this.errors.add(
                                ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC041",
                                        new Object[] {
                                                "Debit Information row " + i
                                                        + " Payment",
                                                        amtPaid.subtract(totalAmtPaid.subtract(billAmount))}));
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * check IF cboBillingAccountNo. <> selected debit information billing account No.
     * @param input
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean checkDebitInfoBillAcc(Map<String, Object> input) {
        boolean result = true;
        String pattern = CommonUtils.toString(input.get("pattern"));
        if (CB_AUTO_OFFSET_BAC.equals(pattern)) {
            // idBillAccount
            String idBillAccount = CommonUtils.toString(input.get("idBillAccount")).trim();
            // Debit infomation
            List<Debit_info_bean> listDebitInfos = (List<Debit_info_bean>) input.get("debitInfos");
            // Debit infomation not empty
            if (!CommonUtils.isEmpty(listDebitInfos)) {
                for (Debit_info_bean info : listDebitInfos) {
                    // APPLIED
                    String applied = info.getAPPLIED();
                    if (DB_APPLIED.equals(applied)) {
                        String idRef = CommonUtils.toString(info.getID_REF());
                        Map<String, Object> bilHInfo = queryDAO.executeForMap("SELECT.BSYS.BCSB.T_BIL_H_BILL_ACC", idRef);
                        String billAcc = CommonUtils.toString(bilHInfo.get("BILL_ACC")).trim();
                        if (!idBillAccount.equals(billAcc)) {
                            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC100"));
                            return false;
                        }
                    }
                }
                for (Debit_info_bean info : listDebitInfos) {
                    // APPLIED
                    String applied = info.getAPPLIED();
                    if (DB_APPLIED.equals(applied)) {
                        String idRef = CommonUtils.toString(info.getID_REF());
                        Map<String, Object> bilHInfo = queryDAO.executeForMap("SELECT.BSYS.BCSB.T_BIL_H_BILL_ACC", idRef);
                        BigDecimal payment = new BigDecimal(info.getAMT_PAID());
                        BigDecimal paymentOld = new BigDecimal(info.getAMT_PAID_OLD());
                        BigDecimal outstandingAmt = new BigDecimal(CommonUtils.toString(bilHInfo.get("OUTSTANDING_AMOUNT")).trim());
                        outstandingAmt = outstandingAmt.add(paymentOld);
                        if (outstandingAmt.compareTo(payment)<0) {
                            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC102", new Object[] {idRef.trim()}));
                            return false;
                        }
                    }
                }
            }
        }
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
            // new model
            if (modelFlag) {
                // pattern BAC or CST
                String pattern = CommonUtils.toString(input.get("pattern"));
                // CST pattern
                if (CB_AUTO_OFFSET_CST.equals(pattern)) {
                    // pmtMethod
                    String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));
                    // payer
                    String payer = CommonUtils.toString(input.get("payer"));
                    // other
                    String other = CommonUtils.toString(input.get("other"));
                    // curCode
                    String curCode = CommonUtils.toString(input.get("curCode"));
                    // transDate
                    String transDate = CommonUtils.toString(input.get("transDate"));
                    // amtReceived
                    String amtReceived = CommonUtils.toString(
                            input.get("amtReceived")).trim();
                    // bankCharge
                    String bankCharge = CommonUtils.toString(
                            input.get("bankCharge")).trim();
                    if (CommonUtils.isEmpty(pmtMethod)) {
                        input.put("debitInfos" , new ArrayList<Debit_info_bean>());
                    } else {
                        if (!"NT".equals(pmtMethod ) && 
                                CommonUtils.isEmpty(payer)) {
                            input.put("debitInfos" , 
                                    new ArrayList<Debit_info_bean>());
                        }
                    }
                    // Payment Method
                    if (CommonUtils.isEmpty(pmtMethod)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Payment Method" }));
                    }
                    // Payer
                    if (CommonUtils.isEmpty(payer) || CommonUtils.isNull(payer)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Payer" }));
                    } else {
                        if ("-1".equals(payer) && (CommonUtils.isEmpty(other))) {
                            this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                    new BLogicMessage("errors.ERR1SC005",
                                            new Object[] { "Other Payer" }));
                        }
                    }
                    // Currency
                    if (CommonUtils.isEmpty(curCode) 
                            || CommonUtils.isNull(curCode)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Currency" }));
                    }
                    // Transaction Date
                    if (CommonUtils.isEmpty(transDate)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Transaction Date" }));
                    }
                    // Amount Received
                    if (CommonUtils.isEmpty(amtReceived)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Amount Received" }));
                    } else if (!isDecimal(amtReceived)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC012",
                                        new Object[] { "Amount Received" }));
                    }
                    // Bank Charges
                    if (CommonUtils.notEmpty(bankCharge) 
                            && !isDecimal(bankCharge)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC012",
                                        new Object[] { "Bank Charges" }));
                    }
                } else {
                    // pmtMethod
                    String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));
                    // payer
                    String payer = CommonUtils.toString(input.get("payer"));
                    // idBillAccount
                    String idBillAccount = CommonUtils.toString(
                            input.get("idBillAccount"));
                    // transDate
                    String transDate = CommonUtils.toString(input.get("transDate"));
                    // amtReceived
                    String amtReceived = CommonUtils.toString(
                            input.get("amtReceived")).trim();
                    // bankCharge
                    String bankCharge = CommonUtils.toString(
                            input.get("bankCharge")).trim();

                    if (CommonUtils.isEmpty(payer) || 
                            CommonUtils.isEmpty(idBillAccount)) {
                        input.put("debitInfos" , new ArrayList<Debit_info_bean>());
                    }
                    
                    // Payer
                    if (CommonUtils.isEmpty(payer) || CommonUtils.isNull(payer)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Payer" }));
                    }
                    // Billing Account No.
                    if (CommonUtils.isEmpty(idBillAccount)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Billing Account No." }));
                    }
                    // Payment Method
                    if (CommonUtils.isEmpty(pmtMethod)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Payment Method" }));
                    }
                    // Transaction Date
                    if (CommonUtils.isEmpty(transDate)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Transaction Date" }));
                    }
                    // Amount Received
                    if (CommonUtils.isEmpty(amtReceived)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC005",
                                        new Object[] { "Amount Received" }));
                    } else if (!isDecimal(amtReceived)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC012",
                                        new Object[] { "Amount Received" }));
                    }
                    // Bank Charges
                    if (CommonUtils.notEmpty(bankCharge) 
                            && !isDecimal(bankCharge)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC012",
                                        new Object[] { "Bank Charges" }));
                    }
                }
            } else {
                // edit model
                //edit model check Input data
                checkEditInputData(input);
            }
            //check Debit Input data
            checkDebitInputData(input);
        }
        if (this.errors.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
    
    /**
     * edit model check Input data
     * @param input input data
     */
    private void checkEditInputData(Map<String, Object> input){
        // transDate
        String transDate = CommonUtils.toString(input.get("transDate"));
        // amtReceived
        String amtReceived = CommonUtils.toString(
                input.get("amtReceived")).trim();
        // bankCharge
        String bankCharge = CommonUtils.toString(input.get("bankCharge"));
        
        // pmtMethod
        String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));
        
        // Transaction Date
        if (CommonUtils.isEmpty(transDate) 
                || CommonUtils.isNull(transDate)) {
            this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                    new BLogicMessage("errors.ERR1SC005",
                            new Object[] { "Transaction Date" }));
        }
        // Payment Method
        if (CommonUtils.isEmpty(pmtMethod)) {
            this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                    new BLogicMessage("errors.ERR1SC005",
                            new Object[] { "Payment Method" }));
        }
        // Amount Received
        if (CommonUtils.isEmpty(amtReceived)) {
            this.errors.add(
                    ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                            "errors.ERR1SC005",
                            new Object[] { "Amount Received" }));
        } else if (!isDecimal(amtReceived)) {
            this.errors.add(
                    ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                            "errors.ERR1SC012",
                            new Object[] { "Amount Received" }));
        }
        // Bank Charges
        if (CommonUtils.notEmpty(bankCharge) && !isDecimal(bankCharge)) {
            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    "errors.ERR1SC012", new Object[] { "Bank Charges" }));
        }
    }
    
    /**
     * check Debit Input data
     * @param input input data
     */
    @SuppressWarnings("unchecked")
    private void checkDebitInputData(Map<String, Object> input){
     // amtReceived
        String strAmtReceived = CommonUtils.toString(input.get("amtReceived"));
        BigDecimal amtReceivedNum = new BigDecimal("0");
        // amtReceived not empty
        if (CommonUtils.notEmpty(strAmtReceived)) {
            amtReceivedNum = new BigDecimal(strAmtReceived);
        }
        // Debit infomation
        List<Debit_info_bean> debitInfos = (List<Debit_info_bean>) 
            input.get("debitInfos");
        // Debit infomation not empty
        if (CommonUtils.notEmpty(debitInfos)) {
            BigDecimal total = new BigDecimal("0");
            int i = 0;
            for (Debit_info_bean info : debitInfos) {
                i = i + 1;
                // appied check
                if (DB_APPLIED.equals(info.getAPPLIED())) {
                    // BigDecimal AMT_PAID
                    BigDecimal amtPaid = new BigDecimal("0");
                    // BigDecimal AMT_DUE
                    BigDecimal amtDue = new BigDecimal(
                            CommonUtils.toString(info.getAMT_DUE()));
                    // String AMT_PAID
                    String strAmtPaid = info.getAMT_PAID();
                    // String FOREX_LOSS
                    String strForexLossStr = info.getFOREX_LOSS();
                    // String FOREX_GAIN
                    String strForexGain = info.getFOREX_GAIN();
                    if (CommonUtils.notEmpty(strAmtPaid)
                            && isDecimal(strAmtPaid)) {
                        amtPaid = new BigDecimal(strAmtPaid);
                        if(Double.parseDouble(strAmtPaid)==0.0) {
                            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC014", new Object[] { "Debit Information row " + i + " Payment"}));
                        }
                    }
                    if (isDecimal(strAmtPaid)) {
                        total = total.add(amtPaid);
                    } else {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC012",
                                        new Object[] { "Debit Information row "
                                                + i + " Payment" }));
                    }
                    if (!isDecimal(strForexLossStr)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC012",
                                        new Object[] { "Debit Information row "
                                                + i + " Forex Loss" }));
                    }
                    if (!isDecimal(strForexGain)) {
                        this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC012",
                                        new Object[] { "Debit Information row "
                                                + i + " Forex Gain" }));
                    }
                    if (0 < amtPaid.compareTo(amtDue)) {
                        this.errors.add(
                                ActionErrors.GLOBAL_MESSAGE,
                                new BLogicMessage("errors.ERR1SC041",
                                        new Object[] {
                                                "Debit Information row " + i
                                                        + " Payment",
                                                info.getAMT_DUE() }));
                    }
                }
            }
            
            if (0 < total.compareTo(amtReceivedNum.subtract(refundAmount))) {
            	this.errors.add(
                        ActionErrors.GLOBAL_MESSAGE,
                        new BLogicMessage("errors.ERR1SC041", new Object[] {"Payment", amtReceivedNum.subtract(refundAmount) }));
            }
        }
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
     * input data to output data
     * 
     * @param input
     *            input data
     * @param output
     *            ouput data
     */
    private void copyParam(Map<String, Object> input, 
            Map<String, Object> output) {
        Set<String> keys = input.keySet();
        for (String key : keys) {
            output.put(key, input.get(key));
        }
    }
    
    /**
     * padding right space
     * @param str
     * @param len
     * @return
     */
    private static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for(int i=str.length();i<len;i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
