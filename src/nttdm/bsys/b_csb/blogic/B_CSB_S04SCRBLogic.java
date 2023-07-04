package nttdm.bsys.b_csb.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;

/**
 * 
 * Come from BAC_S02 view or edit screen <br>
 * B_CSB_04 initial logic
 * 
 * @author loamanma
 * 
 */
public class B_CSB_S04SCRBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * @param input
     *            input data
     * @return BLogicResult result value
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();

        Map<String, Object> output = new HashMap<String, Object>();

        String receiptNo = CommonUtils.toString(input.get("receiptNo"));
        output.put("receiptNo", addSpace(receiptNo, 20));

        // get bank info
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAcc", cbBankAcc);

        List<Map<String, Object>> refundInfoList = queryDAO.executeForMapList("B_CSB.getRefundInfoList", addSpace(receiptNo, 20));
        output.put("refundInfoList", refundInfoList);

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