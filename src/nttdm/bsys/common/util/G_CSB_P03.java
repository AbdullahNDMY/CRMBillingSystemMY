/**
 * @(#)G_CSB_P03.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/06/06
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.G_CSB_P03_Input;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class G_CSB_P03 {
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
     * @param uvo BillingSystemUserValueObject
     */
    public G_CSB_P03(QueryDAO queryDAO, UpdateDAO updateDAO,
            BillingSystemUserValueObject uvo) {
        // queryDAO
        this.queryDAO = queryDAO;
        // updateDAO
        this.updateDAO = updateDAO;
        // uvo
        this.uvo = uvo;
    }
    
    /**
     * begin blogic
     * @param input G_CSB_P03_Input
     */
    public void execute(G_CSB_P03_Input input) {
        // idBillAccount
        String idBillAccount = CommonUtils.toString(input.getIdBillAccount()).trim();
        //ID_REF
        String idRef = CommonUtils.toString(input.getIdRef()).trim();
        //ReceiptNo
        String receiptNo = CommonUtils.toString(input.getReceiptNo()).trim();
        //AmtReceived
        String amtReceived = CommonUtils.toString(input.getAmtReceived()).trim();
        // idLogin
        String idLogin = uvo.getId_user();
        
        Map<String, Object> tCsbDCondition = new HashMap<String, Object>();
        tCsbDCondition.put("idBillAccount", idBillAccount);
        tCsbDCondition.put("idRef", idRef);
        tCsbDCondition.put("receiptNo", receiptNo);
        //get AMT_DUE
        Map<String, Object> paidBillDocument = queryDAO.executeForMap("SELECT.BSYS.G_CSB_P03.SQL001", tCsbDCondition);
        
        if (!CommonUtils.isEmpty(paidBillDocument)) {
            BigDecimal amtDue = new BigDecimal(CommonUtils.toString(paidBillDocument.get("AMT_DUE")));
            //Balance Amt
            BigDecimal balanceAmt = new BigDecimal(amtReceived);
            
            // get T_CSB_D Info by Primary
            Map<String, Object> mapTCsbDInfo = this.queryDAO.executeForMap("SELECT.BSYS.G_CSB_P02.SQL003", tCsbDCondition);
            // 0
            BigDecimal zero = new BigDecimal("0");
            G_CSB_P04 g_csb_p04 = new G_CSB_P04(updateDAO);
            //Call G_CSB_P04
            balanceAmt = g_csb_p04.execute(mapTCsbDInfo, tCsbDCondition, balanceAmt, amtDue, idLogin);
            
            if (balanceAmt.compareTo(zero)<0) {
                balanceAmt=new BigDecimal("0");
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
            updateDAO.execute("UPDATE.BSYS.G_CSB_P02.SQL008", tCsbHCondition);
        }
    }
}
