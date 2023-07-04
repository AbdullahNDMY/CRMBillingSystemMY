/**
 * @(#)R_BAC_R02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionMessages;

/**
 * R_BAC_R02BLogic class.
 * 
 * @author NTT DATA
 */
public class R_BAC_S02_01BLogic implements BLogic<HashMap<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO
     *            the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(HashMap<String, Object> input) {
        BLogicResult result = new BLogicResult();
        HashMap<String, Object> output = new HashMap<String, Object>();
        String type = CommonUtils.toString(input.get("type"));
        output.put("type", type);
        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        Integer row = systemSetting.getResultRow();
        Integer startIndex = 0;
        try {
            startIndex = Integer.parseInt(CommonUtils.toString(input.get("startIndex")));
        } catch (Exception e) {
            startIndex = 0;
        }
//        List<Map<String, Object>> resultListTotal = new ArrayList<Map<String, Object>>();
//        List<Map<String, Object>> billaccs = queryDAO.executeForMapList("R_BAC.getBillAccs", input);
//
//        Map<String, Object> resultMap;
//
//        for (int i = 0; i < billaccs.size(); i++) {
//
//            resultMap = new HashMap<String, Object>();
//
//            String billacc = billaccs.get(i).get("ID_BILL_ACCOUNT").toString();
//            resultMap.put("billacc", billacc);
//            resultMap.put("totalAmtDue", billaccs.get(i).get("TOTAL_AMT_DUE"));
//
//            // 1
//            List<Map<String, Object>> receiptsNoDetails = queryDAO.executeForMapList("R_BAC.getReceiptsNoDetails", billacc);
//            resultMap.put("receiptsNoDetailsSize", receiptsNoDetails.size());
//
//            // 2
//            List<Map<String, Object>> receiptOverMatch = queryDAO.executeForMapList("R_BAC.getReceiptOverMatch", billacc);
//            resultMap.put("receiptOverMatchSize", receiptOverMatch.size());
//
//            // 3
//            List<Map<String, Object>> receiptNotFullyMatch = queryDAO.executeForMapList("R_BAC.getReceiptNotFullyMatch", billacc);
//            resultMap.put("receiptNotFullyMatchSize", receiptNotFullyMatch.size());
//
//            // 4
//            List<Map<String, Object>> invoiceOverMatch = queryDAO.executeForMapList("R_BAC.getInvoiceOverMatch", billacc);
//            resultMap.put("invoiceOverMatchSize", invoiceOverMatch.size());
//
//            // 5
//            List<Map<String, Object>> invoiceNotFullyMatch = queryDAO.executeForMapList("R_BAC.getInvoiceNotFullyMatch", billacc);
//            resultMap.put("invoiceNotFullyMatchSize", invoiceNotFullyMatch.size());
//
//            // 6
//            List<Map<String, Object>> receiptAmountNegative = queryDAO.executeForMapList("R_BAC.getReceiptAmountNegative", billacc);
//            resultMap.put("receiptAmountNegativeSize", receiptAmountNegative.size());
//
//            int total = receiptsNoDetails.size() + receiptOverMatch.size() + receiptNotFullyMatch.size() + invoiceOverMatch.size()
//                    + invoiceNotFullyMatch.size() + receiptAmountNegative.size();
//            if (total > 0) {
//                resultListTotal.add(resultMap);
//            }
//        }
        List<Map<String, Object>> resultListTotal = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> billaccs = queryDAO.executeForMapList("R_BAC.getBillAccs", input);

        Map<String, Object> resultMap;

        // prepare result map
        // 1
        List<Map<String, Object>> receiptsNoDetails = queryDAO.executeForMapList("R_BAC.getExportSql1", null);

        Map<String, Integer> receiptsNoDetailsMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptsNoDetails.size(); i++) {
            String key = receiptsNoDetails.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptsNoDetailsMap.get(key);
            if (old != null) {
                receiptsNoDetailsMap.put(key, old + 1);
            } else {
                receiptsNoDetailsMap.put(key, 1);
            }
        }

        // 2
        List<Map<String, Object>> receiptOverMatch = queryDAO.executeForMapList("R_BAC.getExportSql2", null);

        Map<String, Integer> receiptOverMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptOverMatch.size(); i++) {
            String key = receiptOverMatch.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptOverMatchMap.get(key);
            if (old != null) {
                receiptOverMatchMap.put(key, old + 1);
            } else {
                receiptOverMatchMap.put(key, 1);
            }
        }

        // 3
        List<Map<String, Object>> receiptNotFullyMatch = queryDAO.executeForMapList("R_BAC.getExportSql3", null);
        Map<String, Integer> receiptNotFullyMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptNotFullyMatch.size(); i++) {

            String key = receiptNotFullyMatch.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptNotFullyMatchMap.get(key);
            if (old != null) {
                receiptNotFullyMatchMap.put(key, old + 1);
            } else {
                receiptNotFullyMatchMap.put(key, 1);
            }
        }

        // 4
        List<Map<String, Object>> invoiceOverMatch = queryDAO.executeForMapList("R_BAC.getExportSql4", null);
        Map<String, Integer> invoiceOverMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < invoiceOverMatch.size(); i++) {

            String key = invoiceOverMatch.get(i).get("BILL_ACC").toString().trim();
            Integer old = invoiceOverMatchMap.get(key);
            if (old != null) {
                invoiceOverMatchMap.put(key, old + 1);
            } else {
                invoiceOverMatchMap.put(key, 1);
            }
        }

        // 5
        List<Map<String, Object>> invoiceNotFullyMatch = queryDAO.executeForMapList("R_BAC.getExportSql5", null);
        Map<String, Integer> invoiceNotFullyMatchMap = new HashMap<String, Integer>();

        for (int i = 0; i < invoiceNotFullyMatch.size(); i++) {

            String key = invoiceNotFullyMatch.get(i).get("BILL_ACC").toString().trim();
            Integer old = invoiceNotFullyMatchMap.get(key);
            if (old != null) {
                invoiceNotFullyMatchMap.put(key, old + 1);
            } else {
                invoiceNotFullyMatchMap.put(key, 1);
            }
        }

        // 6
        List<Map<String, Object>> receiptAmountNegative = queryDAO.executeForMapList("R_BAC.getExportSql6", null);
        Map<String, Integer> receiptAmountNegativeMap = new HashMap<String, Integer>();

        for (int i = 0; i < receiptAmountNegative.size(); i++) {

            String key = receiptAmountNegative.get(i).get("ID_BILL_ACCOUNT").toString().trim();
            Integer old = receiptAmountNegativeMap.get(key);
            if (old != null) {
                receiptAmountNegativeMap.put(key, old + 1);
            } else {
                receiptAmountNegativeMap.put(key, 1);
            }
        }

        // prepare result map

        for (int i = 0; i < billaccs.size(); i++) {

            String billacc = billaccs.get(i).get("ID_BILL_ACCOUNT").toString().trim();

            int total = getNum(receiptsNoDetailsMap, billacc) + getNum(receiptOverMatchMap, billacc) + getNum(receiptNotFullyMatchMap, billacc)
                    + getNum(invoiceOverMatchMap, billacc) + getNum(invoiceNotFullyMatchMap, billacc) + getNum(receiptAmountNegativeMap, billacc);

            if ("Y".equals(type) && total <= 0) {
                continue;
            }

            resultMap = new HashMap<String, Object>();

            resultMap.put("billacc", billacc);
            resultMap.put("totalAmtDue", billaccs.get(i).get("TOTAL_AMT_DUE"));

            // 1
            resultMap.put("receiptsNoDetailsSize", getNum(receiptsNoDetailsMap, billacc));

            // 2
            resultMap.put("receiptOverMatchSize", getNum(receiptOverMatchMap, billacc));

            // 3
            resultMap.put("receiptNotFullyMatchSize", getNum(receiptNotFullyMatchMap, billacc));

            // 4
            resultMap.put("invoiceOverMatchSize", getNum(invoiceOverMatchMap, billacc));

            // 5
            resultMap.put("invoiceNotFullyMatchSize", getNum(invoiceNotFullyMatchMap, billacc));

            // 6
            resultMap.put("receiptAmountNegativeSize", getNum(receiptAmountNegativeMap, billacc));

            resultListTotal.add(resultMap);
        }

        Integer totalRow = resultListTotal.size();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (totalRow == 0) {

            // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
        } else {
            for (int j = startIndex; j < resultListTotal.size(); j++) {
                if (j - startIndex >= row) {
                    break;
                }
                resultListTotal.get(j).put("index", j + 1);

                String billacc = resultListTotal.get(j).get("billacc").toString();
                // latest invoice
                String latestInvoice = queryDAO.executeForObject("R_BAC.getLatestInvoice", paddingRightSpace(billacc,20), String.class);
                if (CommonUtils.isEmpty(latestInvoice)) {
                    latestInvoice = "-";
                }
                resultListTotal.get(j).put("latestInvoice", latestInvoice);

                resultList.add(resultListTotal.get(j));
            }
        }

        output.put("resultList", resultList);
        output.put("totalRow", totalRow);
        output.put("startIndex", startIndex);
        output.put("row", row);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

    private Integer getNum(Map<String, Integer> map, String billacc) {
        if (map.get(billacc) == null) {
            return 0;
        } else {
            return map.get(billacc);
        }
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
