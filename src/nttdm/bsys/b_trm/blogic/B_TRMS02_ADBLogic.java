package nttdm.bsys.b_trm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_trm.bean.DebitInfoBean;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.Globals;
import org.apache.struts.util.LabelValueBean;

public class B_TRMS02_ADBLogic extends TRMBLogic<Map<String, Object>> {
    private BLogicMessages messages;
    private BLogicMessages errors;

    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        messages = new BLogicMessages();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        Map<String, Object> parameter = new HashMap<String, Object>();
        this.copyProperties(input, output);
        this.copyProperties(input, parameter);

        // check G.SET_VALUE
        String setValue = queryDAO.executeForObject("SELECT.B_TRM.IS_DISPLAY_BIL_ACC", null, String.class);
        output.put("gblSetValue", setValue);
        if (setValue.equals("CST")) {
            output.put("billAccount", null);
        }
        // set default customer
        //List<LabelValueBean> cbCustomer = queryDAO.executeForObjectList("SELECT.B_TRM.SQL013", null);
        //output.put("cbCustomer", cbCustomer);

        // select customer combo box
        if (input.get("action") != null && input.get("action").equals("customerChange")) {
            if ("CST".equals(setValue)) {
                List<LabelValueBean> cbCreditRef = queryDAO.executeForObjectList("SELECT.B_TRM.SQL016", parameter);
                output.put("cbCreditRef", cbCreditRef);
                output.put("cbBillAccount", null);
            } else {
                String currency = queryDAO.executeForObject("SELECT.B_TRM.SQL020.CURRENCY", input, String.class);
                output.put("currency", currency);
                List<LabelValueBean> cbBillAccount = queryDAO.executeForObjectList("SELECT.B_TRM.SQL020", parameter);
                output.put("cbBillAccount", cbBillAccount);
                String billAcc = CommonUtils.toString(input.get("billAccount"));
                if (!CommonUtils.isEmpty(input.get("billAccount"))) {
                    parameter.put("billAcc", billAcc);
                    List<LabelValueBean> cbCreditRef = queryDAO.executeForObjectList("SELECT.B_TRM.SQL016", parameter);
                    output.put("cbCreditRef", cbCreditRef);
                }else{
                    output.put("cbCreditRef", null);
                }
            }
            output.put("creditRef", "");
            //output.put("billAccount", "");
            clearInfo(output);
        } else {
            if (CommonUtils.isEmpty(input.get("customer"))) {
                output.put("cbCreditRef", null);
                output.put("cbBillAccount", null);
                output.put("currency", "");
            }
            clearInfo(output);
        }

        // select billingAccount combo box
        if (input.get("action") != null && input.get("action").equals("billAccChange")) {
            String billAcc = CommonUtils.toString(input.get("billAccount"));
            List<LabelValueBean> cbBillAccount = queryDAO.executeForObjectList("SELECT.B_TRM.SQL020", parameter);
            output.put("cbBillAccount", cbBillAccount);
            if (!CommonUtils.isEmpty(input.get("billAccount"))) {
                parameter.put("billAcc", billAcc);
                List<LabelValueBean> cbCreditRef = queryDAO.executeForObjectList("SELECT.B_TRM.SQL016", parameter);
                output.put("cbCreditRef", cbCreditRef);
                String currency = queryDAO.executeForObject("SELECT.B_TRM.SQL020.CURRENCY", input, String.class);
                output.put("currency", currency);
            } else {
                output.put("cbCreditRef", null);
                output.put("currency", "");
                clearInfo(output);
            }
            output.put("creditRef", "");
        }

        // select credit reference combo box
        if (input.get("action") != null && input.get("action").equals("creditRefChange")) {
            List<LabelValueBean> cbBillAccount = queryDAO.executeForObjectList("SELECT.B_TRM.SQL020", parameter);
            output.put("cbBillAccount", cbBillAccount);

            if (!CommonUtils.isEmpty(input.get("creditRef"))) {
                Map<String, Object> creditInfo = queryDAO.executeForMap("SELECT.B_TRM.SQL018", parameter);

                Object date = creditInfo.get("DATE_REQ");
                Object currency = creditInfo.get("BILL_CURRENCY");
                //Float origAmt = Float.parseFloat(creditInfo.get("BILL_AMOUNT").toString());
                //Float creditBalance = Float.parseFloat(creditInfo.get("AMT_DUE").toString());
                output.put("date", date);
                //output.put("currency", currency);
                output.put("origAmt", creditInfo.get("BILL_AMOUNT"));
                output.put("creditBalance", creditInfo.get("AMT_DUE"));

                String billAcc = CommonUtils.toString(input.get("billAccount"));
                parameter.put("currency", currency);
                if ("CST".equals(setValue)) {
                    List<DebitInfoBean> debitInfos = queryDAO.executeForObjectList("SELECT.B_TRM.SQL017",parameter);
                    output.put("debitInfos", debitInfos);
                } else {
                    if (!"".equals(billAcc)) {
                        parameter.put("billAcc", billAcc);
                        List<DebitInfoBean> debitInfos = queryDAO.executeForObjectList("SELECT.B_TRM.SQL017",parameter);
                        output.put("debitInfos", debitInfos);
                    } else {
                        errors.add(Globals.MESSAGE_KEY, new BLogicMessage(
                                "errors.ERR1SC005",
                                new Object[] { "Billing Account No" }));
                        //output.put("creditRef", "");
                        output.put("debitInfos", null);
                    }
                }
            } else {
                clearInfo(output);
            }
            String billAcc = CommonUtils.toString(input.get("billAccount"));
            if ("CST".equals(setValue)) {
                List<LabelValueBean> cbCreditRef = queryDAO.executeForObjectList("SELECT.B_TRM.SQL016", parameter);
                output.put("cbCreditRef", cbCreditRef);
            } else {
                if (!CommonUtils.isEmpty(input.get("billAccount"))) {
                    parameter.put("billAcc", billAcc);
                    List<LabelValueBean> cbCreditRef = queryDAO.executeForObjectList("SELECT.B_TRM.SQL016", parameter);
                    output.put("cbCreditRef", cbCreditRef);
                }else{
                    output.put("cbCreditRef", null);
                    output.put("creditRef", "");
                }
            }
        }
        
        output.put("currency", CommonUtils.toString(output.get("currency")));
        result.setResultObject(output);
        result.setResultString("success");
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }

    private void clearInfo(Map<String, Object> output) {
        output.put("date", null);
        output.put("origAmt", null);
        output.put("creditBalance", null);
        output.put("debitInfos", null);
    }
}
