/**
 * @(#)B_TRMS01BLogic.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_trm.blogic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * Transaction Matching Search Logic.
 * 
 * @author NTTDM
 */
public class B_TRMS01BLogic extends TRMBLogic<Map<String, Object>> {
    private static final String CREDIT_REF = "CREDIT_REF";
    private static final String DEBIT_REF = "DEBIT_REF";
    private static final String DATE_UPDATED = "DATE_UPDATED";
    private static final String BILL_AMOUNT = "BILL_AMOUNT";
    private static final String AMT_DUE = "AMT_DUE";
    private static final String CUST_NAME = "CUST_NAME";
    private static final String ID_CUST = "ID_CUST";
    private static final String BILL_ACC = "BILL_ACC";
    private static final String BILL_CURRENCY = "BILL_CURRENCY";
    private static final String AMT_PAID = "AMT_PAID";
    private static final String INDEX = "INDEX";
    private BLogicMessages messages;
    private BLogicMessages errors;
    private static DecimalFormat formatter = new DecimalFormat("###,##0.00");

    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        messages = new BLogicMessages();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        Map<String, Object> parameter = new HashMap<String, Object>();
        this.copyProperties(input, output);
        this.copyProperties(input, parameter);
        
        parameter.put("cusName", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("cusName")).trim()));
        /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
        parameter.put("customerType", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("customerType")).trim()));
        /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
        parameter.put("creditReference", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("creditReference")).trim()));
        parameter.put("debitReference", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("debitReference")).trim()));
        parameter.put("cusID", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("cusID")).trim()));
        parameter.put("billAccountNo", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("billAccountNo")).trim()));
        parameter.put("billCurrency", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("billCurrency")).trim()));

        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        Integer row = systemSetting.getResultRow();
        output.put("row", row);

        if (this.validate(input)) {
            // Calculate Total Row
            Integer totalRow = queryDAO.executeForObject("SELECT.B_TRM.total.SQL001", parameter, Integer.class);
            if (totalRow == 0) {
                // info.ERR2SC002
                messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC002", new Object()));
            }

            // Idenfify Start Index
            int startIndex = 0;
            if (input.get("startIndex") == null) {
                startIndex = 0;
            } else {
                startIndex = Integer.parseInt(input.get("startIndex").toString());
            }
            if (totalRow.intValue() < startIndex) {
                startIndex = 0;
            }
            output.put("startIndex", startIndex);

            // Retrieve Credit REF list that will display in current screen.
            List<String> creditRefs = new ArrayList<String>();
            List<Map<String, Object>> creditRefResult = queryDAO.executeForObjectList("SELECT.B_TRM.export.SQL001",
                    parameter, startIndex, row);
            for (Map<String, Object> creditRef : creditRefResult) {
                creditRefs.add(CommonUtils.toString(creditRef.get(CREDIT_REF)));
            }
            parameter.put("creditRefs", creditRefs);

            // Retrieve Transaction Matching details
            List<Map<String, Object>> searchResults = queryDAO.executeForMapList("SELECT.B_TRM.export.SQL002",
                    parameter);

            // filter to comparable design layout
            String chCode = "<br/>";
            List<Map<String, Object>> acc = new ArrayList<Map<String, Object>>();
            int i = 0;
            for (Map<String, Object> m1 : searchResults) {
                boolean contain = false;
                m1.put(BILL_AMOUNT, formatter.format(new BigDecimal(m1.get(BILL_AMOUNT).toString()).negate()));
                m1.put(AMT_DUE, formatter.format(new BigDecimal(m1.get(AMT_DUE).toString()).negate()));
                m1.put(AMT_PAID, formatter.format(m1.get(AMT_PAID)));
                m1.put(DEBIT_REF, CommonUtils.toString(m1.get(DEBIT_REF)).trim());
                m1.put(BILL_ACC, CommonUtils.toString(m1.get(BILL_ACC)).trim());
                for (Map<String, Object> m2 : acc) {
                    if (m2.get(CREDIT_REF).toString().contains(m1.get(CREDIT_REF).toString())) {
                        contain = true;
                        updateNear(m2, m1, DATE_UPDATED, chCode, false);
                        updateNear(m2, m1, ID_CUST, chCode, false);
                        updateNear(m2, m1, CUST_NAME, chCode, false);
                        updateNear(m2, m1, BILL_ACC, chCode, false);
                        updateNear(m2, m1, BILL_CURRENCY, chCode, false);
                        updateNear(m2, m1, BILL_AMOUNT, chCode, false);
                        updateNear(m2, m1, AMT_DUE, chCode, false);
                        updateNear(m2, m1, DEBIT_REF, chCode, true);
                        updateNear(m2, m1, AMT_PAID, chCode, true);
                    }
                }
                if (!contain) {
                    i++;
                    m1.put(INDEX, i);
                    acc.add(m1);
                }
            }

            output.put("searchResult", acc);
            output.put("totalRow", totalRow);
        } else {
            output.put("searchResult", null);
            output.put("startIndex", null);
            output.put("totalRow", null);
        }

        result.setResultObject(output);
        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }

    /**
     * Input Check.
     */
    private boolean validate(Map<String, Object> input) {
        Object startDate = input.get("startDate");
        Object endDate = input.get("endDate");
        if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (format.parse(startDate.toString()).after(format.parse(endDate.toString()))) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC013", new Object[] {
                            "Transaction Date", "From", "To" }));
                    return false;
                }
            } catch (ParseException e) {
            }
        }
        return true;
    }

    /**
     * Format display by add <br/>. 
     * 
     * @param m1 previous value
     * @param m2 current value
     * @param key format key
     * @param chCode <br/>
     * @param flag 
     */
    private void updateNear(Map<String, Object> m1, Map<String, Object> m2, String key, String chCode, boolean flag) {
        String s1 = m1.get(key).toString();
        String s2 = m2.get(key).toString();
        if (!flag) {
            m1.put(key, s1 + chCode);
        } else {
            m1.put(key, s1 + chCode + s2);
        }
    }

//    private boolean hasNear(String str, String s, String chCode) {
//        if (str.isEmpty())
//            return false;
//        String acc = str;
//        while (acc.endsWith(chCode)) {
//            acc = acc.substring(0, acc.length() - chCode.length());
//        }
//        if (acc.endsWith(s))
//            return true;
//        else
//            return false;
//    }
}
