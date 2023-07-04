/**
 * @(#)B_CSBR01BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-8-29

 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_csb.blogic;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

/**
 * Case Book Search business logic.
 * 
 * @author bench
 *
 */
public class B_CSBR01BLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * Normal
     */
    private static final String PAYMENT_STATE_NORMAL = "N";

    /**
     * BLogicMessages
     */
    private BLogicMessages messages;
    
    /**
     * BLogicMessages
     */
    private BLogicMessages errors;

    /**
     * @param input Map<String, Object>
     * @return BLogicResult
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        messages = new BLogicMessages();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        Map<String, Object> parameter = new HashMap<String, Object>();
        this.copyParam(input, output);
        this.copyParam(input, parameter);
        Object checkpageview = input.get("checkpageview");
        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        if (checkpageview != null && "1".equals(checkpageview)) {
            if (this.validate(input)) {
                Integer startIndex = 0;
                startIndex = CommonUtils.toInteger(input.get("startIndex"));
                Integer row = systemSetting.getResultRow();
                output.put("row", row);
                parameter.put("payer", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("payer")).trim()));
                /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
                parameter.put("payerType", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("payerType")).trim()));
                /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
                parameter.put("refNo", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("refNo")).trim()));
                parameter.put("receiptNo", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("receiptNo")).trim()));
                parameter.put("billaccNo", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("billaccNo")).trim()));

                // retrieve search result count
                Integer csbInfos_count = queryDAO.executeForObject("SELECT.BSYS.BCSB.SQL_1.2", parameter, Integer.class);
                output.put("totalRow", csbInfos_count);
                
                if(csbInfos_count.compareTo(0) ==0 ){
                    output.put("startIndex", startIndex);
                    output.put("csbInfos", null);
                    output.put("totalAmt", null);
                    
                    // info.ERR2SC002
                    BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                    messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
                }else{
                	// rest start index if need
                    if (startIndex == null 
                            || startIndex < 0 
                            || 0 < startIndex.compareTo(csbInfos_count)){
                        startIndex = 0;
                    }
                    output.put("startIndex", startIndex);

                    // retrieve search result data
                    List<Map<String, Object>> csbInfos = queryDAO.executeForMapList("SELECT.BSYS.BCSB.SQL_1.1", parameter, startIndex, row);
                    output.put("csbInfos", csbInfos);

                    // retrieve all records' balance amount and Received amount
                    List<Map<String, Object>> csbInfos1 = queryDAO.executeForMapList("SELECT.BSYS.BCSB.SQL_1.3", parameter);
                    BigDecimal totalAmt = new BigDecimal("0");
                    BigDecimal totalBalanceAmt = new BigDecimal("0");
                    
                    boolean isAllSameflag = true;
                    if(csbInfos1 != null && 1 < csbInfos1.size()){
                        isAllSameflag = false;
                    }
                    
                    if (isAllSameflag) {
                        totalAmt = totalAmt.add(new BigDecimal(CommonUtils.toString(csbInfos1.get(0).get("TOTAL_AMT_RECEIVED"))));
                        totalBalanceAmt = totalBalanceAmt.add(new BigDecimal(CommonUtils.toString(csbInfos1.get(0).get("TOTAL_BALANCE_AMT"))));
                        output.put("totalAmt", totalAmt);
                        output.put("totalBalanceAmt", totalBalanceAmt);
                    }else{
                        output.put("totalAmt", null);
                        output.put("totalBalanceAmt", null);
                    } 
                }
            } else {
                // reset all value
                Set<String> keys = input.keySet();
                for (String key : keys) {
                    output.put(key, null);
                }
                output.put("totalAmt", null);
                output.put("totalBalanceAmt", null);
                output.put("csbInfos", null);
                output.put("totalRow", null);
            }
        } else {
            // reset all value
            Set<String> keys = input.keySet();
            for (String key : keys) {
                output.put(key, null);
            }
            output.put("totalAmt", null);
            output.put("totalBalanceAmt", null);
            output.put("csbInfos", null);
            output.put("totalRow", null);

            // Payment Currency Selected "Normal" by default
            output.put("paymentStatus", PAYMENT_STATE_NORMAL);

            // set default currency
            output.put("curCode", systemSetting.getDefCurrency());
        }

        output.put("defCurCode", systemSetting.getDefCurrency());
        // set default bank account
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAcc", cbBankAcc);

        result.setResultObject(output);
        result.setResultString("success");
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }

    /**
     * 
     * @param input Map<String, Object>
     * @return result
     */
    private boolean validate(Map<String, Object> input) {
        Object startDate = input.get("startDate");
        Object endDate = input.get("endDate");
        if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (format.parse(startDate.toString()).after(
                        format.parse(endDate.toString()))) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE,
                            new BLogicMessage("errors.ERR1SC013", new Object[] {
                                    "Transaction Date", "From", "To" }));
                    return false;
                }
            } catch (ParseException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param src Map<String, Object>
     * @param des Map<String, Object>
     */
    private void copyParam(Map<String, Object> src, Map<String, Object> des) {
        Set<String> keys = src.keySet();
        for (String key : keys) {
            des.put(key, src.get(key));
        }  
    }
}