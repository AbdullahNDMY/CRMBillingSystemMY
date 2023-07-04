package nttdm.bsys.b_csb.blogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.BillAccountInfo;
import nttdm.bsys.b_csb.dto.PaymentInformation;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.util.LabelValueBean;

/**
 * 
 * Come from BAC_S02 view screen <br>
 * B_CSB_03 edit logic
 * 
 * @author loamanma
 * 
 */
public class B_CSB_S03EditBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * @param input
     *            input data
     * @return BLogicResult result value
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();

        Map<String, Object> output = new HashMap<String, Object>();

        String receiptNo = CommonUtils.toString(input.get("receiptNo"));

        Map<String, Object> receiptInfo = queryDAO.executeForMap("B_CSB.getReceiptInfo", addSpace(receiptNo, 20));

        BillAccountInfo billAccountInfo = new BillAccountInfo();

        billAccountInfo.setCustName(CommonUtils.toString(receiptInfo.get("CUST_NAME")));
        billAccountInfo.setBillCurrency(CommonUtils.toString(receiptInfo.get("CUR_CODE")));
        billAccountInfo.setIdBillAccount(CommonUtils.toString(receiptInfo.get("ID_BILL_ACCOUNT")));
        billAccountInfo.setIdCust(CommonUtils.toString(receiptInfo.get("ID_CUST")));
        billAccountInfo.setPaymentMethod(CommonUtils.toString(receiptInfo.get("PMT_METHOD")));
        billAccountInfo.setTotalAmtDue(CommonUtils.toString(receiptInfo.get("BALANCE_AMT")));

        output.put("billAccountInfo", billAccountInfo);
        output.put("receiptNo", receiptNo);
        output.put("transDate", CommonUtils.toString(receiptInfo.get("TRANS_DATE")));
        output.put("amtRefunded", CommonUtils.toString(receiptInfo.get("AMT_RECEIVED")));
        output.put("bankAcc", CommonUtils.toString(receiptInfo.get("ID_COM_BANK")));
        output.put("refundRefernece", CommonUtils.toString(receiptInfo.get("REFERENCE2")));
        output.put("remark", CommonUtils.toString(receiptInfo.get("REMARK")));

        // get payment info list refer to receipt no
        List<PaymentInformation> paymentInformationList = queryDAO.executeForObjectList("B_CSB.getReceiptPaymentInfoList", addSpace(receiptNo, 20));

        if (!CommonUtils.isEmpty(paymentInformationList)) {
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

        output.put("paymentInformationList", paymentInformationList);

        // get bank info
        List<LabelValueBean> cbBankAccList = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAccList", cbBankAccList);

        output.put("actionFlg", "edit");
        result.setResultObject(output);
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