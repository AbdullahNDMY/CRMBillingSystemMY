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
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;

/**
 * R_BAC_R02BLogic class.
 * 
 * @author NTT DATA
 */
public class R_BAC_S02_02BLogic implements BLogic<HashMap<String, Object>> {

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

        String billAcc = CommonUtils.toString(input.get("billAcc"));
        billAcc = paddingRightSpace(billAcc, 20);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        if ("1".equals(type)) {

            resultList = queryDAO.executeForMapList("R_BAC.getReceiptsNoDetails", billAcc);

        } else if ("2".equals(type)) {

            resultList = queryDAO.executeForMapList("R_BAC.getReceiptOverMatch", billAcc);

        } else if ("3".equals(type)) {

            resultList = queryDAO.executeForMapList("R_BAC.getReceiptNotFullyMatch", billAcc);

        } else if ("4".equals(type)) {

            resultList = queryDAO.executeForMapList("R_BAC.getInvoiceOverMatch", billAcc);

        } else if ("5".equals(type)) {

            resultList = queryDAO.executeForMapList("R_BAC.getInvoiceNotFullyMatch", billAcc);

        } else if ("6".equals(type)) {

            resultList = queryDAO.executeForMapList("R_BAC.getReceiptAmountNegative", billAcc);

        }

        output.put("resultList", resultList);

        result.setResultObject(output);
        result.setResultString("success");
        return result;
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
