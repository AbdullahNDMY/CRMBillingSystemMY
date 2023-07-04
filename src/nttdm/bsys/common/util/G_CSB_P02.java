/**
 * @(#)G_CSB_P02.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/30
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.G_CSB_P02_Input;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 * 
 */
public class G_CSB_P02 {

    /**
     * SELECT.BSYS.G_CSB_P02.SQL001
     */
    private static final String SELECT_BSYS_G_CSB_P02_GET_T_CSB_H = "SELECT.BSYS.G_CSB_P02.SQL001";

    /**
     * SELECT.BSYS.G_CSB_P02.SQL002
     */
    private static final String SELECT_BSYS_G_CSB_P02_GET_T_BIL_H = "SELECT.BSYS.G_CSB_P02.SQL002";

    /**
     * SELECT.BSYS.G_CSB_P02.SQL003
     */
    private static final String SELECT_BSYS_G_CSB_P02_GET_T_CSB_D = "SELECT.BSYS.G_CSB_P02.SQL003";

    /**
     * UPDATE.BSYS.G_CSB_P02.SQL008
     */
    private static final String G_CSB_P02_UPDATE_T_CSB_H = "UPDATE.BSYS.G_CSB_P02.SQL008";

    /**
     * QueryDAO
     */
    private QueryDAO queryDAO;

    /**
     * UpdateDAO
     */
    private UpdateDAO updateDAO;

    /**
     * BillingSystemUserValueObject
     */
    private BillingSystemUserValueObject uvo;

    /**
     * construct method
     * 
     * @param queryDAO
     *            QueryDAO
     * @param updateDAO
     *            UpdateDAO
     * @param uvo
     *            BillingSystemUserValueObject
     */
    public G_CSB_P02(QueryDAO queryDAO, UpdateDAO updateDAO, BillingSystemUserValueObject uvo) {
        // queryDAO
        this.queryDAO = queryDAO;
        // updateDAO
        this.updateDAO = updateDAO;
        // uvo
        this.uvo = uvo;
    }

    /**
     * <div> begin blogic<div>
     * 
     * @param input
     *            G_CSB_P02_Input data
     */
    public void execute(G_CSB_P02_Input input) {
        // receiptNo
        String receiptNo = CommonUtils.toString(input.getReceiptNo()).trim();
        // idLogin
        String idLogin = uvo.getId_user();
        // select active receipt with balance amount > 0.
        List<Map<String, Object>> listReceiptAndBAmount = this.queryDAO.executeForMapList(
                SELECT_BSYS_G_CSB_P02_GET_T_CSB_H, receiptNo);

        // receipt with balance amount not empty
        if (!CommonUtils.isEmpty(listReceiptAndBAmount)) {
            G_CSB_P04 g_csb_p04 = new G_CSB_P04(updateDAO);
            // 0
            BigDecimal zero = new BigDecimal("0");
            // receipt with balance amount loop
            for (int i = 0; i < listReceiptAndBAmount.size(); i++) {
                Map<String, Object> mapReceiptAndBAmount = listReceiptAndBAmount.get(i);
                // ID_BILL_ACCOUNT
                String idBillAccount = CommonUtils.toString(mapReceiptAndBAmount.get("ID_BILL_ACCOUNT")).trim();
                // BALANCE_AMT
                BigDecimal balanceAmt = new BigDecimal(CommonUtils.toString(mapReceiptAndBAmount.get("BALANCE_AMT")));
                // select record with outstanding amount, order by oldest bill.
                List<Map<String, Object>> listOutSAmount = this.queryDAO.executeForMapList(
                        SELECT_BSYS_G_CSB_P02_GET_T_BIL_H, idBillAccount);
                // outstanding amount not empty
                if (!CommonUtils.isEmpty(listOutSAmount)) {
                    for (int j = 0; j < listOutSAmount.size() && 0 < balanceAmt.compareTo(zero); j++) {
                        Map<String, Object> mapOutSAmount = listOutSAmount.get(j);
                        // AMT_DUE
                        BigDecimal amtDue = new BigDecimal(CommonUtils.toString(mapOutSAmount.get("AMT_DUE")));
                        // ID_REF
                        String idRef = CommonUtils.toString(mapOutSAmount.get("ID_REF")).trim();
                        // T_CSB_D condition
                        Map<String, Object> tCsbDCondition = new HashMap<String, Object>();
                        // ID_REF
                        tCsbDCondition.put("idRef", idRef);
                        // RECEIPT_NO
                        tCsbDCondition.put("receiptNo", receiptNo);
                        // get T_CSB_D Info by Primary
                        Map<String, Object> mapTCsbDInfo = this.queryDAO.executeForMap(
                                SELECT_BSYS_G_CSB_P02_GET_T_CSB_D, tCsbDCondition);
                        //Call G_CSB_P04
                        balanceAmt = g_csb_p04.execute(mapTCsbDInfo, tCsbDCondition, balanceAmt, amtDue, idLogin);
                    }
                    if (balanceAmt.compareTo(zero) < 0) {
                        balanceAmt = new BigDecimal("0");
                    }
                }
                // T_CSB_H condition
                Map<String, Object> tCsbHCondition = new HashMap<String, Object>();
                // RECEIPT_NO
                tCsbHCondition.put("receiptNo", receiptNo);
                // BALANCE_AMT
                tCsbHCondition.put("balanceAmt", balanceAmt);
                // BALANCE_AMT
                tCsbHCondition.put("idLogin", idLogin);
                // update T_CSB_H
                updateDAO.execute(G_CSB_P02_UPDATE_T_CSB_H, tCsbHCondition);
            }
        }
    }
}
