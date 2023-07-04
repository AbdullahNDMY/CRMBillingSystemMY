/**
 * @(#)B_TRMS01BLogic.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_trm.blogic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_RPT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;

/**
 * Transaction Matching File Download Business logic.
 * 
 * @author NTTDM
 */
public class B_TRMS01ExportBLogic extends TRMBLogic<Map<String, Object>> {
    private static final String NO = "NO";
    private static final String CREDIT_REF = "CREDIT_REF";
    private static final String DEBIT_REF = "DEBIT_REF";
    private static final String DATE_UPDATED = "DATE_UPDATED";
    private static final String BILL_AMOUNT = "BILL_AMOUNT";
    private static final String AMT_DUE = "AMT_DUE";
    private static final String CUST_NAME = "CUST_NAME";
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
    private static final String CUSTOMER_TYPE = "CUSTOMER_TYPE";
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
    private static final String ID_CUST = "ID_CUST";
    private static final String BILL_ACC = "BILL_ACC";
    private static final String BILL_CURRENCY = "BILL_CURRENCY";
    private static final String AMT_PAID = "AMT_PAID";
    private static final DecimalFormat formatter = new DecimalFormat("0.00");

    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        Map<String, Object> output = new HashMap<String, Object>();
        HashMap<String, Object> parameter = new HashMap<String, Object>();
        this.copyProperties(input, output);
        this.copyProperties(input, parameter);

        List<Map<String, Object>> searchResults = queryDAO.executeForMapList("SELECT.B_TRM.export.SQL002", parameter);

        List<Map<String, Object>> acc = new ArrayList<Map<String, Object>>();
        int index = 1;
        for (Map<String, Object> m1 : searchResults) {
            m1.put(NO, index); // No
            m1.put(DATE_UPDATED, CommonUtils.toString(m1.get(DATE_UPDATED))); // Transaction Date
            m1.put(ID_CUST, CommonUtils.toString(m1.get(ID_CUST)).trim()); // Customer ID
            m1.put(CUST_NAME, CommonUtils.toString(m1.get(CUST_NAME)).trim()); // Customer Name
            /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
            m1.put(CUSTOMER_TYPE, CommonUtils.toString(m1.get(CUSTOMER_TYPE)).trim()); // Customer Type
            /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
            m1.put(BILL_ACC, CommonUtils.toString(m1.get(BILL_ACC)).trim()); // Billing Account No.
            m1.put(BILL_CURRENCY, CommonUtils.toString(m1.get(BILL_CURRENCY)).trim()); // Billing Currency
            m1.put(CREDIT_REF, CommonUtils.toString(m1.get(CREDIT_REF)).trim()); // Credit Reference
            m1.put(BILL_AMOUNT, formatter.format(m1.get(BILL_AMOUNT))); // Original amount
            m1.put(AMT_DUE, formatter.format(m1.get(AMT_DUE))); // Credit Balance
            m1.put(DEBIT_REF, CommonUtils.toString(m1.get(DEBIT_REF)).trim()); // Debit reference
            m1.put(AMT_PAID, formatter.format(m1.get(AMT_PAID))); // Payment
            acc.add(m1);
            index ++;
        }

        G_RPT_P01_Input param = new G_RPT_P01_Input();
        param.setReportType("CNM");
        List<HashMap<String, Object>> listAgingReport = new ArrayList<HashMap<String, Object>>();
        for (int t = 0; t < searchResults.size(); t++) {
            HashMap<String, Object> report = (HashMap<String, Object>) acc.get(t);
            listAgingReport.add(report);
        }
        param.setListAgingReport(listAgingReport);

        G_RPT_P01 g_rpt_p01 = new G_RPT_P01();
        g_rpt_p01.setQueryDAO(queryDAO);
        g_rpt_p01.setUpdateDAO(updateDAO);
        GlobalProcessResult globalprocessresult = g_rpt_p01.execute(param, null);
        if (globalprocessresult.getFile() == null) {
            result.setErrors(globalprocessresult.getErrors());
            result.setMessages(globalprocessresult.getMessages());
            result.setResultString("fail");
            result.setResultObject(output);
            return result;
        }
        DownloadFile file = new DownloadFile(globalprocessresult.getFile());
        result.setResultObject(file);
        result.setErrors(globalprocessresult.getErrors());
        result.setMessages(globalprocessresult.getMessages());
        return result;
    }
}
