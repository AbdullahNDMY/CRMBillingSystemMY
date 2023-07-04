/**
 * @(#)R_BAC_S02_analysis1BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.blogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;

/**
 * R_BAC_S02_analysis1BLogic class.
 * 
 * @author NTT DATA
 */
public class R_BAC_S02_analysis1BLogic implements BLogic<HashMap<String, Object>> {

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

        String fileName = "BAC_" + CommonUtils.toString(input.get("billAcc")) + "_Analysis1_" + getSysdateStr() + ".sql";

        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }

        String filePathName = tmpFolder + fileName;
        // ----------------------------------

        int insertCnt = 0;
        int updateCnt = 0;

        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new FileWriter(filePathName));

            out.write("--- Case 1");
            out.newLine();
            out.write("--- SQL1");
            out.newLine();

            List<String> csbList = queryDAO.executeForObjectList("R_BAC.checkReference1", null);

            Map<String, Integer> referenceMap = new HashMap<String, Integer>();

            for (int i = 0; i < csbList.size(); i++) {
                String key = csbList.get(i);
                Integer old = referenceMap.get(key);
                if (old != null) {
                    referenceMap.put(key, old + 1);
                } else {
                    referenceMap.put(key, 1);
                }
            }

            String csbInsert = "Insert into T_CSB_D (RECEIPT_NO,ID_REF,AMT_PAID,AMT_DUE,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,FOREX_LOSS,FOREX_GAIN) values ";

            List<Map<String, Object>> receiptsCase1 = queryDAO.executeForMapList("R_BAC.getReceiptNoDetail", input);

            for (int j = 0; j < receiptsCase1.size(); j++) {

                String idRef = receiptsCase1.get(j).get("REFERENCE1").toString();

                // String csbNo =
                // queryDAO.executeForObject("R_BAC.checkReference1", idRef,
                // String.class);
                String csbNo = referenceMap.get(idRef).toString();

                if ("1".equals(csbNo)) {
                    insertCnt++;
                    out.write(csbInsert);
                    out.write("('" + receiptsCase1.get(j).get("RECEIPT_NO").toString().trim() + "',");
                    out.write("'" + idRef + "',");
                    out.write(receiptsCase1.get(j).get("BILL_AMOUNT").toString() + ",");
                    out.write(receiptsCase1.get(j).get("BILL_AMOUNT").toString() + ",");
                    out.write("0,");
                    out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                    out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                    out.write("'DM',");
                    out.write("0,");
                    out.write("0);");
                    out.newLine();
                }
            }

            List<Map<String, Object>> receiptsCase23 = queryDAO.executeForMapList("R_BAC.getReceiptNoDetail23", input);
            out.newLine();
            out.write("--- Case 2 / 3");
            out.newLine();

            Calendar c = Calendar.getInstance();
            c.clear();
            c.set(Calendar.YEAR, 2002);
            c.set(Calendar.MONTH, 2);
            c.set(Calendar.DAY_OF_MONTH, 1);

            for (int i = 0; i < receiptsCase23.size(); i++) {

                String idRef = receiptsCase23.get(i).get("REFERENCE1").toString();

                // String csbNo =
                // queryDAO.executeForObject("R_BAC.checkReference1", idRef,
                // String.class);
                String csbNo = referenceMap.get(idRef).toString();

                if ("1".equals(csbNo)) {
                    out.newLine();
                    out.write("--- Billing account: " + receiptsCase23.get(i).get("ID_BILL_ACCOUNT").toString().trim());
                    out.write(" / " + receiptsCase23.get(i).get("RECEIPT_NO").toString().trim());
                    out.newLine();

                    String BALANCE_AMT_C = receiptsCase23.get(i).get("BALANCE_AMT_C").toString();
                    String AMT_RECEIVED = receiptsCase23.get(i).get("AMT_RECEIVED").toString();
                    String BILL_AMOUNT = receiptsCase23.get(i).get("BILL_AMOUNT").toString();
                    String DATE_REQ = receiptsCase23.get(i).get("DATE_REQ").toString();
                    SimpleDateFormat formt = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateReq = formt.parse(DATE_REQ);

                    BigDecimal balanceAmt = new BigDecimal(BALANCE_AMT_C);
                    BigDecimal amtReceived = new BigDecimal(AMT_RECEIVED);
                    BigDecimal billAmount = new BigDecimal(BILL_AMOUNT);

                    if (balanceAmt.compareTo(billAmount) < 0) {
                        out.write("--- SQL1");
                        out.newLine();

                        insertCnt++;
                        out.write(csbInsert);
                        out.write("('" + receiptsCase23.get(i).get("RECEIPT_NO").toString().trim() + "',");
                        out.write("'" + idRef + "',");
                        out.write(balanceAmt + ",");
                        out.write(billAmount + ",");
                        out.write("0,");
                        out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                        out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                        out.write("'DM',");
                        out.write("0,");
                        out.write("0);");
                        out.newLine();
                        continue;
                    }

                    // no GST
                    if (!dateReq.before(c.getTime())) {
                        out.write("--- SQL1");
                        out.newLine();

                        insertCnt++;
                        out.write(csbInsert);
                        out.write("('" + receiptsCase23.get(i).get("RECEIPT_NO").toString().trim() + "',");
                        out.write("'" + idRef + "',");
                        out.write(billAmount + ",");
                        out.write(billAmount + ",");
                        out.write("0,");
                        out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                        out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                        out.write("'DM',");
                        out.write("0,");
                        out.write("0);");
                        out.newLine();
                        continue;
                    }

                    // GST
                    BigDecimal rejectAmt = BigDecimal.ZERO;
                    BigDecimal finalReceivedAmt = BigDecimal.ZERO;

                    BigDecimal rate = new BigDecimal("1.03");
                    BigDecimal billAmountAndTaxDown = billAmount.multiply(rate).divide(BigDecimal.ONE, 2, BigDecimal.ROUND_DOWN);
                    BigDecimal billAmountAndTaxUp = billAmount.multiply(rate).divide(BigDecimal.ONE, 2, BigDecimal.ROUND_UP);

                    if (balanceAmt.compareTo(billAmountAndTaxDown) == 0 || balanceAmt.compareTo(billAmountAndTaxUp) == 0) {

                        rejectAmt = balanceAmt.subtract(billAmount);
                        finalReceivedAmt = amtReceived.subtract(rejectAmt);

                    } else {
                        continue;
//                        BigDecimal leftAmt = balanceAmt.subtract(billAmount);
//
//                        BigDecimal gstAmt = billAmount.multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_DOWN);
//
//                        if (leftAmt.compareTo(gstAmt) < 0) {
//                            continue;
//                        } else {
//                            rejectAmt = gstAmt;
//                            finalReceivedAmt = amtReceived.subtract(rejectAmt);
//                        }
                    }

                    out.write("--- SQL1");
                    out.newLine();

                    insertCnt++;
                    out.write(csbInsert);
                    out.write("('" + receiptsCase23.get(i).get("RECEIPT_NO").toString().trim() + "',");
                    out.write("'" + idRef + "',");
                    out.write(billAmount + ",");
                    out.write(billAmount + ",");
                    out.write("0,");
                    out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                    out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                    out.write("'DM',");
                    out.write("0,");
                    out.write("0);");
                    out.newLine();

                    out.write("-- SQL4");
                    out.newLine();

                    insertCnt++;
                    out.write("Insert into T_CSB_H (RECEIPT_NO,DATE_TRANS,AMT_RECEIVED,BALANCE_AMT,REFERENCE1,ID_BILL_ACCOUNT,ID_CUST,PMT_METHOD,PMT_STATUS,REJECT_DATE,REJECT_DESC,ID_COM_BANK,CUR_CODE,IS_CLOSED,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,BANK_CHARGE,IS_EXPORT,PAID_PRE_INVOICE,OVER_PAID) VALUES ");
                    out.write("('" + receiptsCase23.get(i).get("RECEIPT_NO").toString().trim() + "-DM',");
                    out.write("TO_DATE('" + receiptsCase23.get(i).get("DATE_TRANS").toString() + "','DD/MM/YYYY'),");
                    out.write(rejectAmt + ",");
                    out.write("0,");
                    out.write("'" + receiptsCase23.get(i).get("REFERENCE1") + "',");
                    out.write("'" + receiptsCase23.get(i).get("ID_BILL_ACCOUNT").toString().trim() + "',");
                    out.write("'" + receiptsCase23.get(i).get("ID_CUST") + "',");
                    out.write("'" + receiptsCase23.get(i).get("PMT_METHOD") + "',");
                    out.write("'R',");
                    out.write("to_date('31/08/2012','DD/MM/YYYY'),");
                    out.write("'Data migration adjustment GST',");
                    out.write("'" + receiptsCase23.get(i).get("ID_COM_BANK") + "',");
                    out.write("'" + receiptsCase23.get(i).get("CUR_CODE") + "',");
                    out.write("0,");
                    out.write("0,");
                    out.write("to_date('31/08/2012','DD/MM/YYYY'),");
                    out.write("to_date('31/08/2012','DD/MM/YYYY'),");
                    out.write("'DM',");
                    out.write("0,");
                    out.write("'1',");
                    out.write("'0',");
                    out.write("'0');");
                    out.newLine();

                    out.write("-- SQL5");
                    out.newLine();

                    updateCnt++;
                    out.write("UPDATE T_CSB_H SET AMT_RECEIVED = " + finalReceivedAmt + " WHERE RECEIPT_NO = '"
                            + receiptsCase23.get(i).get("RECEIPT_NO").toString().trim() + "';");
                    out.newLine();
                }

            }
            out.newLine();
            out.write("-------total insert: " + insertCnt);
            out.newLine();
            out.write("-------total update: " + updateCnt);
            out.flush();
            out.close();

        } catch (IOException e) {

            e.printStackTrace();
            return result;
        } catch (ParseException e) {

            e.printStackTrace();
        }

        // Set download file
        DownloadFile file = new DownloadFile(new File(filePathName));
        result.setResultObject(file);
        return result;
    }

    /**
     * YY MMDDHHMMSS
     * 
     * @return
     */
    private String getSysdateStr() {
        Calendar now = Calendar.getInstance();
        String yy = CommonUtils.formatDate(now.getTime(), "yy");
        String MM = CommonUtils.formatDate(now.getTime(), "MM");
        String dd = CommonUtils.formatDate(now.getTime(), "dd");
        String HH = CommonUtils.formatDate(now.getTime(), "HH");
        String mm = CommonUtils.formatDate(now.getTime(), "mm");
        String ss = CommonUtils.formatDate(now.getTime(), "ss");

        return yy + "" + MM + "" + dd + "" + HH + "" + mm + ss;
    }

    /**
     * padding right space
     * 
     * @param str
     * @param len
     * @return
     */
    @SuppressWarnings("unused")
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
