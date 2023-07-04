/**
 * @(#)R_BAC_S02_analysis3BLogic.java
 * Billing System
 * Version 1.00
 * Created 2014-07-09
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;

/**
 * R_BAC_S02_analysis1BLogic class.
 * 
 * @author NTT DATA
 */
public class R_BAC_S02_analysis3BLogic implements BLogic<HashMap<String, Object>> {

    /**
     * updateDao
     */
    protected UpdateDAO updateDAO;

    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

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

    SimpleDateFormat formt = new SimpleDateFormat("dd/MM/yyyy");
    BigDecimal rate = new BigDecimal("0.03");

    Calendar c = Calendar.getInstance();

    Integer insertCnt = 0;
    Integer updateCnt = 0;
    RoundingMode rm = RoundingMode.HALF_DOWN;
    String csbInsert = "Insert into T_CSB_D (RECEIPT_NO,ID_REF,AMT_PAID,AMT_DUE,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,FOREX_LOSS,FOREX_GAIN) values ";

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(HashMap<String, Object> input) {

        BLogicResult result = new BLogicResult();

        String fileName = "BAC_" + CommonUtils.toString(input.get("billAcc")) + "_Analysis3_" + getSysdateStr() + ".sql";

        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }

        String filePathName = tmpFolder + fileName;
        // ----------------------------------

        c.clear();
        c.set(Calendar.YEAR, 2002);
        c.set(Calendar.MONTH, 2);
        c.set(Calendar.DAY_OF_MONTH, 1);
        insertCnt = 0;
        updateCnt = 0;

        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new FileWriter(filePathName));

            // truncate temp_bil
            updateDAO.execute("R_BAC.deleteTempBil", input);
            // truncate temp_csb
            updateDAO.execute("R_BAC.deleteTempCsb", input);

            // insert invoice not full paid has csb_d
            updateDAO.execute("R_BAC.insertBilHaveDetail", input);
            // insert invoice do not have csb_d
            updateDAO.execute("R_BAC.insertBilNoDetail", input);

            // insert csb have detail not full matched
            updateDAO.execute("R_BAC.insertCsbHaveDetail", input);
            // insert csb no detail
            updateDAO.execute("R_BAC.insertCsbNoDetail", input);

            List<String> billAccList = queryDAO.executeForObjectList("R_BAC.getBillaccFromTempCsb", input);

            for (int i = 0; i < billAccList.size(); i++) {

                String billAcc = billAccList.get(i);

                out.newLine();
                out.write("--- Billing account: " + billAcc);
                out.newLine();

                BigDecimal tempBalance = queryDAO.executeForObject("R_BAC.getTempBalance", billAcc, BigDecimal.class);

                BigDecimal tempOutstanding = queryDAO.executeForObject("R_BAC.getTempOutstanding", billAcc, BigDecimal.class);

                if (tempBalance.compareTo(tempOutstanding) == 0) {
                    out.write("--- Error: Balance = Outstanding");
                    out.newLine();
                }

                // cash book balance = bill amount + GST
                List<Map<String, Object>> billForGst = queryDAO.executeForMapList("R_BAC.getBillForGst", billAcc);

                BigDecimal GSTAmount = BigDecimal.ZERO;
                if (!CommonUtils.isEmpty(billForGst)) {
                    for (Map<String, Object> invoice : billForGst) {
                        BigDecimal billAmount = new BigDecimal(invoice.get("BILL_AMOUNT").toString());
                        GSTAmount = GSTAmount.add(billAmount.multiply(rate).setScale(2, rm));
                    }
                }

                BigDecimal AmtDiff = tempBalance.subtract(GSTAmount).subtract(tempOutstanding);

                List<Map<String, Object>> storeSpecialCsb = new ArrayList<Map<String, Object>>();

                if (AmtDiff.compareTo(BigDecimal.ZERO) > 0) {
                    // cashbook payment Previous invoice
                    out.write("--- cashbook payment Previous invoice. AmtDiff=" + AmtDiff);
                    out.newLine();
                    out.write("--- tempBalance=" + tempBalance + "  tempOutstanding=" + tempOutstanding + " GSTAmount=" + GSTAmount);
                    out.newLine();

                    List<Map<String, Object>> csbBooks = queryDAO.executeForMapList("R_BAC.getTempCsb", billAcc);

                    for (int j = 0; j < csbBooks.size(); j++) {
                        out.write("---" + csbBooks.get(j).get("RECEIPT_NO").toString().trim());
                        out.newLine();

                        BigDecimal balanceAmtC = new BigDecimal(csbBooks.get(j).get("BALANCE_AMT_C").toString());
                        BigDecimal amtReceived = new BigDecimal(csbBooks.get(j).get("AMT_RECEIVED").toString());

                        if (AmtDiff.compareTo(BigDecimal.ZERO) == 0) {
                            // means other Receipt has reject for amt diff
                            sub1(out, csbBooks.get(j), billAcc, storeSpecialCsb);

                        } else if (balanceAmtC.compareTo(AmtDiff) == 0) {

                            Integer detail = queryDAO.executeForObject("R_BAC.checkCashBookDetail", csbBooks.get(j).get("RECEIPT_NO").toString(),
                                    Integer.class);

                            if (detail <= 0) {
                                updateCnt++;
                                out.write("UPDATE T_CSB_H SET PMT_STATUS = 'R',REJECT_DESC='Data Migration adjustment: Pay previous invoice',"
                                        + "REJECT_DATE = TO_DATE('2012-08-31','YYYY-MM-DD'),DATE_UPDATED = TO_DATE('2012-08-31','YYYY-MM-DD') WHERE RECEIPT_NO = '"
                                        + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "';");
                                out.newLine();
                            } else {
                                insertCnt++;
                                out.write("Insert into T_CSB_H (RECEIPT_NO,DATE_TRANS,AMT_RECEIVED,BALANCE_AMT,REFERENCE1,ID_BILL_ACCOUNT,ID_CUST,PMT_METHOD,PMT_STATUS,REJECT_DATE,REJECT_DESC,ID_COM_BANK,CUR_CODE,IS_CLOSED,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,BANK_CHARGE,IS_EXPORT,PAID_PRE_INVOICE,OVER_PAID) VALUES ");
                                out.write("('" + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "-DM2',");
                                out.write("TO_DATE('" + csbBooks.get(j).get("DATE_TRANS_STR").toString() + "','DD/MM/YYYY'),");
                                out.write(AmtDiff + ",");
                                out.write("0,");
                                out.write("'" + csbBooks.get(j).get("REFERENCE1") + "',");
                                out.write("'" + csbBooks.get(j).get("ID_BILL_ACCOUNT").toString().trim() + "',");
                                out.write("'" + csbBooks.get(j).get("ID_CUST") + "',");
                                out.write("'" + csbBooks.get(j).get("PMT_METHOD") + "',");
                                out.write("'R',");
                                out.write("to_date('31/08/2012','DD/MM/YYYY'),");
                                out.write("'Data Migration adjustment: Pay previous invoice',");
                                out.write("'" + csbBooks.get(j).get("ID_COM_BANK") + "',");
                                out.write("'" + csbBooks.get(j).get("CUR_CODE") + "',");
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

                                updateCnt++;
                                out.write("UPDATE T_CSB_H SET AMT_RECEIVED = AMT_RECEIVED-" + AmtDiff + " WHERE RECEIPT_NO = '"
                                        + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "';");
                                out.newLine();
                            }

                            AmtDiff = BigDecimal.ZERO;

                        } else if (balanceAmtC.compareTo(AmtDiff) > 0) {

                            insertCnt++;
                            out.write("Insert into T_CSB_H (RECEIPT_NO,DATE_TRANS,AMT_RECEIVED,BALANCE_AMT,REFERENCE1,ID_BILL_ACCOUNT,ID_CUST,PMT_METHOD,PMT_STATUS,REJECT_DATE,REJECT_DESC,ID_COM_BANK,CUR_CODE,IS_CLOSED,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,BANK_CHARGE,IS_EXPORT,PAID_PRE_INVOICE,OVER_PAID) VALUES ");
                            out.write("('" + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "-DM2',");
                            out.write("TO_DATE('" + csbBooks.get(j).get("DATE_TRANS_STR").toString() + "','DD/MM/YYYY'),");
                            out.write(AmtDiff + ",");
                            out.write("0,");
                            out.write("'" + csbBooks.get(j).get("REFERENCE1") + "',");
                            out.write("'" + csbBooks.get(j).get("ID_BILL_ACCOUNT").toString().trim() + "',");
                            out.write("'" + csbBooks.get(j).get("ID_CUST") + "',");
                            out.write("'" + csbBooks.get(j).get("PMT_METHOD") + "',");
                            out.write("'R',");
                            out.write("to_date('31/08/2012','DD/MM/YYYY'),");
                            out.write("'Data Migration adjustment: Pay previous invoice',");
                            out.write("'" + csbBooks.get(j).get("ID_COM_BANK") + "',");
                            out.write("'" + csbBooks.get(j).get("CUR_CODE") + "',");
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

                            updateCnt++;
                            out.write("UPDATE T_CSB_H SET AMT_RECEIVED = AMT_RECEIVED-" + AmtDiff + " WHERE RECEIPT_NO = '"
                                    + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "';");
                            out.newLine();

                            csbBooks.get(j).put("AMT_RECEIVED", amtReceived.subtract(AmtDiff));

                            balanceAmtC = balanceAmtC.subtract(AmtDiff);
                            AmtDiff = BigDecimal.ZERO;

                            csbBooks.get(j).put("BALANCE_AMT_C", balanceAmtC);

                            sub1(out, csbBooks.get(j), billAcc, storeSpecialCsb);

                        } else {

                            Integer detail = queryDAO.executeForObject("R_BAC.checkCashBookDetail", csbBooks.get(j).get("RECEIPT_NO").toString(),
                                    Integer.class);

                            if (detail <= 0) {
                                updateCnt++;

                                out.write("UPDATE T_CSB_H SET PMT_STATUS = 'R',REJECT_DESC='Data Migration adjustment: Pay previous invoice',"
                                        + "REJECT_DATE = TO_DATE('2012-08-31','YYYY-MM-DD'),DATE_UPDATED = TO_DATE('2012-08-31','YYYY-MM-DD') WHERE RECEIPT_NO = '"
                                        + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "';");
                                out.newLine();
                            } else {
                                insertCnt++;
                                out.write("Insert into T_CSB_H (RECEIPT_NO,DATE_TRANS,AMT_RECEIVED,BALANCE_AMT,REFERENCE1,ID_BILL_ACCOUNT,ID_CUST,PMT_METHOD,PMT_STATUS,REJECT_DATE,REJECT_DESC,ID_COM_BANK,CUR_CODE,IS_CLOSED,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,BANK_CHARGE,IS_EXPORT,PAID_PRE_INVOICE,OVER_PAID) VALUES ");
                                out.write("('" + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "-DM2',");
                                out.write("TO_DATE('" + csbBooks.get(j).get("DATE_TRANS_STR").toString() + "','DD/MM/YYYY'),");
                                out.write(balanceAmtC + ",");
                                out.write("0,");
                                out.write("'" + csbBooks.get(j).get("REFERENCE1") + "',");
                                out.write("'" + csbBooks.get(j).get("ID_BILL_ACCOUNT").toString().trim() + "',");
                                out.write("'" + csbBooks.get(j).get("ID_CUST") + "',");
                                out.write("'" + csbBooks.get(j).get("PMT_METHOD") + "',");
                                out.write("'R',");
                                out.write("to_date('31/08/2012','DD/MM/YYYY'),");
                                out.write("'Data Migration adjustment: Pay previous invoice',");
                                out.write("'" + csbBooks.get(j).get("ID_COM_BANK") + "',");
                                out.write("'" + csbBooks.get(j).get("CUR_CODE") + "',");
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

                                updateCnt++;
                                out.write("UPDATE T_CSB_H SET AMT_RECEIVED = AMT_RECEIVED-" + balanceAmtC + " WHERE RECEIPT_NO = '"
                                        + csbBooks.get(j).get("RECEIPT_NO").toString().trim() + "';");
                                out.newLine();
                            }

                            AmtDiff = AmtDiff.subtract(balanceAmtC);
                        }
                    }

                    if (!CommonUtils.isEmpty(storeSpecialCsb)) {
                        out.write("-------ERROR: In Analysis3 , process 6 ,has Receipt Skip all invoices and can not match");
                        out.newLine();
                        out.write("-------Bill Account: " + billAcc);
                        out.newLine();
                        out.write("-------Not fullly matched Cash Book No start-------- ");
                        out.newLine();
                        for (int k = 0; k < storeSpecialCsb.size(); k++) {
                            out.write("------- " + storeSpecialCsb.get(k).get("RECEIPT_NO"));
                            out.newLine();
                        }
                        out.write("-------Not fullly matched Cash Book No end--------- ");
                        out.newLine();
                    }

                } else if (AmtDiff.compareTo(BigDecimal.ZERO) < 0) {
                    // Invoice no payment
                    out.write("--- Invoice no payment. AmtDiff=" + AmtDiff);
                    out.newLine();
                    out.write("--- tempBalance=" + tempBalance + "  tempOutstanding=" + tempOutstanding + " GSTAmount=" + GSTAmount);
                    out.newLine();

                    List<Map<String, Object>> csbBooks = queryDAO.executeForMapList("R_BAC.getTempCsb", billAcc);
                    for (Map<String, Object> cashBook : csbBooks) {
                        sub1(out, cashBook, billAcc, storeSpecialCsb);
                    }

                    if (CommonUtils.isEmpty(storeSpecialCsb)) {

                        List<Map<String, Object>> invalidCashbooks = queryDAO.executeForMapList("R_BAC.getInvalidCashBooks", billAcc);

                        if (CommonUtils.isEmpty(invalidCashbooks)) {

                            BigDecimal totalOutstanding = queryDAO.executeForObject("R_BAC.getTotalOutstanding", billAcc, BigDecimal.class);

                            List<Map<String, Object>> invoices = queryDAO.executeForMapList("R_BAC.getInvoiceNoPayment", billAcc);

                            Map<String, Object> receipt = queryDAO.executeForMap("R_BAC.getLastCsb", billAcc);

                            insertCnt++;
                            out.write("Insert into T_CSB_H (RECEIPT_NO,DATE_TRANS,AMT_RECEIVED,BALANCE_AMT,REFERENCE1,ID_BILL_ACCOUNT,ID_CUST,PMT_METHOD,PMT_STATUS,REJECT_DATE,REJECT_DESC,ID_COM_BANK,CUR_CODE,IS_CLOSED,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,BANK_CHARGE,IS_EXPORT,PAID_PRE_INVOICE,OVER_PAID) VALUES ");
                            out.write("('" + receipt.get("RECEIPT_NO").toString().trim() + "-DM2',");
                            out.write("TO_DATE('" + receipt.get("DATE_TRANS_STR").toString() + "','DD/MM/YYYY'),");
                            out.write(totalOutstanding + ",");
                            out.write("0,");
                            out.write("'" + receipt.get("REFERENCE1") + "',");
                            out.write("'" + receipt.get("ID_BILL_ACCOUNT").toString().trim() + "',");
                            out.write("'" + receipt.get("ID_CUST") + "',");
                            out.write("'" + receipt.get("PMT_METHOD") + "',");
                            out.write("'N',");
                            out.write("'',");
                            out.write("'',");
                            out.write("'" + receipt.get("ID_COM_BANK") + "',");
                            out.write("'" + receipt.get("CUR_CODE") + "',");
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

                            for (Map<String, Object> invoice : invoices) {

                                // all the no pay invoice will be full matched
                                // here
                                // no branch

                                insertCnt++;
                                out.write(csbInsert);
                                out.write("('" + receipt.get("RECEIPT_NO").toString().trim() + "-DM2',");
                                out.write("'" + invoice.get("ID_REF").toString().trim() + "',");
                                out.write(invoice.get("OUTSTANDING") + ",");
                                out.write(invoice.get("OUTSTANDING") + ",");
                                out.write("0,");
                                out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                                out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                                out.write("'DM',");
                                out.write("0,");
                                out.write("0);");
                                out.newLine();

                                // no need to update TEMP_BIL
                            }
                        } else {
                            out.write("-------ERROR: bill account=" + billAcc + " 7.2b 2010 thereafrer - have cash book not fully match");
                            out.newLine();
                            out.write("------- Receipt NO.:");
                            for (Map<String, Object> cashbook : invalidCashbooks) {
                                out.write(cashbook.get("RECEIPT_NO").toString() + ", ");
                            }
                            out.newLine();

                            List<Map<String, Object>> invoices = queryDAO.executeForMapList("R_BAC.getInvoiceNoPayment", billAcc);
                            out.write("------- No payment invoice:");
                            for (Map<String, Object> invoice : invoices) {
                                out.write(invoice.get("ID_REF").toString() + ", ");
                            }
                            out.newLine();
                        }

                    } else {
                        out.write("-------ERROR: In Analysis3 , process 7.1 ,has Receipt Skip all invoices and can not match");
                        out.newLine();
                        out.write("-------and do not procee 7.2 ");
                        out.newLine();
                        out.write("-------Bill Account: " + billAcc);
                        out.newLine();
                        out.write("-------Not fullly matched Cash Book No start-------- ");
                        out.newLine();
                        for (int k = 0; k < storeSpecialCsb.size(); k++) {
                            out.write("------- " + storeSpecialCsb.get(k).get("RECEIPT_NO"));
                            out.newLine();
                        }
                        out.write("-------Not fullly matched Cash Book No end--------- ");
                        out.newLine();
                    }

                } else {
                    if (tempBalance.compareTo(tempOutstanding) == 0) {
                        out.write("--- Error: Balance = Outstanding+GSTAmount");
                        out.newLine();
                    }
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

    private void sub1(BufferedWriter out, Map<String, Object> csbBook, String billAcc, List<Map<String, Object>> storeSpecialCsb)
            throws ParseException, IOException {

        BigDecimal balanceAmtC = new BigDecimal(csbBook.get("BALANCE_AMT_C").toString());
        BigDecimal totalGSTAmt = BigDecimal.ZERO;
        BigDecimal amtReceived = new BigDecimal(csbBook.get("AMT_RECEIVED").toString());
        BigDecimal totalAmtPaid = amtReceived.subtract(balanceAmtC);

        List<Map<String, Object>> invoices = queryDAO.executeForMapList("R_BAC.getTempBils", billAcc);
        Iterator<Map<String, Object>> invoiceIterator = invoices.iterator();

        while (invoiceIterator.hasNext()) {

            Map<String, Object> invoice = invoiceIterator.next();

            BigDecimal outstanding = new BigDecimal(invoice.get("OUTSTANDING").toString());
            String dateReq = invoice.get("DATE_REQ_STR").toString();
            Date dateReqD = formt.parse(dateReq);
            BigDecimal BILL_AMOUNT = new BigDecimal(invoice.get("BILL_AMOUNT").toString());

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("idRef", invoice.get("ID_REF"));

            BigDecimal amtPaid = BigDecimal.ZERO;
            BigDecimal gstAmount = BigDecimal.ZERO;

            boolean isSQL1 = false;

            if (dateReqD.before(c.getTime())) {

                // need GST
                if (balanceAmtC.compareTo(outstanding) < 0) {

                    amtPaid = balanceAmtC;
                    gstAmount = BigDecimal.ZERO;

                    isSQL1 = true;

                } else if (balanceAmtC.compareTo(outstanding) == 0) {
                    // go to next invoice
                    isSQL1 = false;

                } else if (balanceAmtC.compareTo(outstanding) > 0) {

                    amtPaid = outstanding;

                    gstAmount = BILL_AMOUNT.multiply(rate).setScale(2, rm);

                    if (balanceAmtC.subtract(amtPaid).subtract(gstAmount).compareTo(BigDecimal.ZERO) < 0) {
                        // go to next invoice
                        isSQL1 = false;
                    } else {
                        isSQL1 = true;
                    }

                }

            } else {
                // no need GST

                if (balanceAmtC.compareTo(outstanding) > 0) {
                    amtPaid = outstanding;
                } else {
                    amtPaid = balanceAmtC;
                }
                gstAmount = BigDecimal.ZERO;
                isSQL1 = true;
            }

            if (isSQL1) {

                insertCnt++;
                out.write(csbInsert);
                out.write("('" + csbBook.get("RECEIPT_NO").toString().trim() + "',");
                out.write("'" + invoice.get("ID_REF").toString().trim() + "',");
                out.write(amtPaid + ",");
                out.write(outstanding.toString() + ",");
                out.write("0,");
                out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                out.write("TO_DATE('2012-08-31','YYYY-MM-DD'),");
                out.write("'DM',");
                out.write("0,");
                out.write("0);");
                out.newLine();

                param.put("outstanding", outstanding.subtract(amtPaid));
                param.put("paidAmt", amtPaid.add(new BigDecimal(invoice.get("PAID_AMOUNT_C").toString())));
                updateDAO.execute("R_BAC.updateTempBil", param);

                totalAmtPaid = totalAmtPaid.add(amtPaid);
                balanceAmtC = balanceAmtC.subtract(amtPaid).subtract(gstAmount);
                totalGSTAmt = totalGSTAmt.add(gstAmount);

            }

            if (balanceAmtC.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }

            if (!invoiceIterator.hasNext() && balanceAmtC.compareTo(BigDecimal.ZERO) > 0) {
                storeSpecialCsb.add(csbBook);
            }
        }

        // has GST
        if (totalGSTAmt.compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal amtReceivedGST = BigDecimal.ZERO;
            BigDecimal amtReceivedCSB = BigDecimal.ZERO;

            if (balanceAmtC.compareTo(BigDecimal.ZERO) > 0) {
                amtReceivedGST = totalGSTAmt;
                amtReceivedCSB = amtReceived.subtract(totalGSTAmt);
            } else {
                amtReceivedGST = amtReceived.subtract(totalAmtPaid);
                amtReceivedCSB = totalAmtPaid;
            }

            int dmCnt = queryDAO.executeForObject("R_BAC.getCsbDmCount", csbBook.get("RECEIPT_NO").toString().trim() + "-DM", Integer.class);
            if (dmCnt > 0) {

                updateCnt++;
                out.write("UPDATE T_CSB_H SET AMT_RECEIVED = AMT_RECEIVED+" + amtReceivedGST + " WHERE RECEIPT_NO = '"
                        + csbBook.get("RECEIPT_NO").toString().trim() + "-DM" + "';");
                out.newLine();

            } else {
                insertCnt++;
                out.write("Insert into T_CSB_H (RECEIPT_NO,DATE_TRANS,AMT_RECEIVED,BALANCE_AMT,REFERENCE1,ID_BILL_ACCOUNT,ID_CUST,PMT_METHOD,PMT_STATUS,REJECT_DATE,REJECT_DESC,ID_COM_BANK,CUR_CODE,IS_CLOSED,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN,BANK_CHARGE,IS_EXPORT,PAID_PRE_INVOICE,OVER_PAID) VALUES ");
                out.write("('" + csbBook.get("RECEIPT_NO").toString().trim() + "-DM',");
                out.write("TO_DATE('" + csbBook.get("DATE_TRANS_STR").toString() + "','DD/MM/YYYY'),");
                out.write(amtReceivedGST + ",");
                out.write("0,");
                out.write("'" + csbBook.get("REFERENCE1") + "',");
                out.write("'" + csbBook.get("ID_BILL_ACCOUNT").toString().trim() + "',");
                out.write("'" + csbBook.get("ID_CUST") + "',");
                out.write("'" + csbBook.get("PMT_METHOD") + "',");
                out.write("'R',");
                out.write("to_date('31/08/2012','DD/MM/YYYY'),");
                out.write("'Data migration adjustment GST',");
                out.write("'" + csbBook.get("ID_COM_BANK") + "',");
                out.write("'" + csbBook.get("CUR_CODE") + "',");
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
            }

            updateCnt++;
            out.write("UPDATE T_CSB_H SET AMT_RECEIVED = " + amtReceivedCSB + " WHERE RECEIPT_NO = '" + csbBook.get("RECEIPT_NO").toString().trim()
                    + "';");
            out.newLine();
        }

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
