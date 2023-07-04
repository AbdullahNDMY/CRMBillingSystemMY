package nttdm.bsys.b_csb.blogic;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.BillAccountInfo;
import nttdm.bsys.b_csb.dto.PaymentInformation;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

/**
 * 
 * Come from BAC_S02v screen <br>
 * B_CSB_03 new initial logic
 * 
 * @author loamanma
 * 
 */
public class B_CSB_S03NewBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * BLogicMessages
     */
    private BLogicMessages messages;

    /**
     * BLogicMessages
     */
    private BLogicMessages errors;

    /**
     * @param input
     *            input data
     * @return BLogicResult result value
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();

        messages = new BLogicMessages();
        errors = new BLogicMessages();

        Map<String, Object> output = new HashMap<String, Object>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String transDate = df.format(new Date());
        output.put("transDate", transDate);

        // set action = new
        output.put("actionFlg", "new");

        output.put("fromPage", input.get("fromPage"));
        String idBillAccount = CommonUtils.toString(input.get("idBillAccount"));

        if (CommonUtils.isEmpty(idBillAccount)) {
            // info.ERR2SC002
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setErrors(errors);
            result.setResultObject(output);
            result.setResultString("success");
            return result;
        }
        idBillAccount = addSpace(idBillAccount, 20);

        BillAccountInfo billAccountInfo = queryDAO.executeForObject("B_CSB.getBillAccInfo", idBillAccount, BillAccountInfo.class);

        output.put("billAccountInfo", billAccountInfo);
        BigDecimal totalAmountDue = new BigDecimal(billAccountInfo.getTotalAmtDue().toString());
        billAccountInfo.setTotalAmtDue(totalAmountDue.multiply(new BigDecimal(-1)).toString());
        output.put("amtRefunded", totalAmountDue.multiply(new BigDecimal(-1)).toString());

        // get bank info
        List<LabelValueBean> cbBankAccList = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAccList", cbBankAccList);

        // payment Information
        List<PaymentInformation> paymentInformationList = queryDAO.executeForObjectList("B_CSB.getPaymentInfoList", idBillAccount);
        output.put("paymentInformationList", paymentInformationList);

        if (CommonUtils.isEmpty(paymentInformationList)) {
            // info.ERR2SC002
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
        } else {
            BigDecimal totalAmoutReceive = BigDecimal.ZERO;
            BigDecimal totalBalanceAmount = BigDecimal.ZERO;
            BigDecimal totalRefundAmount = BigDecimal.ZERO;

            for (PaymentInformation payInfo : paymentInformationList) {
                totalAmoutReceive = totalAmoutReceive.add(payInfo.getAmountReceived());
                totalBalanceAmount = totalBalanceAmount.add(payInfo.getBalanceAmount());
                totalRefundAmount = totalRefundAmount.add(new BigDecimal(payInfo.getRefundAmount()));
            }

            output.put("totalAmoutReceive", totalAmoutReceive);
            output.put("totalBalanceAmount", totalBalanceAmount);
            output.put("totalRefundAmount", totalRefundAmount);
        }

        result.setResultObject(output);
        result.setResultString("success");
        result.setMessages(messages);
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