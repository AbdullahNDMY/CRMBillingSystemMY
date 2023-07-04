/**
 * @(#)G_CSB_P04.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/02/16
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.util.Map;

import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * @author gplai
 *
 */
public class G_CSB_P04 {
    
    /**
     * No
     */
    private static final String NO = "No";

    /**
     * Yes
     */
    private static final String YES = "Yes";

    /**
     * IS_DELETED 0
     */
    private static final String IS_DELETED_0 = "0";
    
    /**
     * UPDATE.BSYS.G_CSB_P02.SQL004_1
     */
    private static final String G_CSB_P02_UPDATE_T_CSB_D_1 = "UPDATE.BSYS.G_CSB_P02.SQL004_1";
    
    /**
     * UPDATE.BSYS.G_CSB_P02.SQL004
     */
    private static final String G_CSB_P02_UPDATE_T_CSB_D = "UPDATE.BSYS.G_CSB_P02.SQL004";
    
    /**
     * INSERT.BSYS.G_CSB_P02.SQL005
     */
    private static final String G_CSB_P02_INSERT_T_CSB_D = "INSERT.BSYS.G_CSB_P02.SQL005";
    
    /**
     * UPDATE.BSYS.G_CSB_P02.SQL006
     */
    private static final String G_CSB_P02_UPDATE_T_BIL_D_SETTLED = "UPDATE.BSYS.G_CSB_P02.SQL006";
    
    /**
     * UPDATE.BSYS.G_CSB_P02.SQL007
     */
    private static final String G_CSB_P02_UPDATE_T_BIL_D = "UPDATE.BSYS.G_CSB_P02.SQL007";

    /**
     * UpdateDAO
     */
    private UpdateDAO updateDAO;
    
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
    public G_CSB_P04(UpdateDAO updateDAO) {
        // updateDAO
        this.updateDAO = updateDAO;
    }

    public BigDecimal execute(Map<String, Object> mapTCsbDInfo, Map<String, Object> tCsbDCondition, BigDecimal balanceAmt, BigDecimal amtDue, String idLogin) {
        BigDecimal zero = new BigDecimal("0");
        // amtPaid
        BigDecimal amtPaid = zero;
        // settled
        String settled = NO;
        // T_CSB_D Info not empty
        if (!CommonUtils.isEmpty(mapTCsbDInfo)) {
            if ("1".equals(CommonUtils.toString(mapTCsbDInfo.get("IS_DELETED")))) {
                // BALANCE_AMT>=AMT_DUE
                if (0 <= balanceAmt.compareTo(amtDue)) {
                    amtPaid = amtDue;
                    // settle set Yes
                    settled = YES;
                } else {
                    amtPaid = balanceAmt;
                    // settle set No
                    settled = NO;
                }
                // If cash book detail has been deleted. update to undeleted.
                tCsbDCondition.put("IS_DELETED", IS_DELETED_0);
                // AMT_DUE
                tCsbDCondition.put("amtDue", amtDue);
                // AMT_PAID
                tCsbDCondition.put("amtPaid", amtPaid);
                // ID_LOGIN
                tCsbDCondition.put("idLogin", idLogin);
                // update T_CSB_D
                updateDAO.execute(G_CSB_P02_UPDATE_T_CSB_D_1, tCsbDCondition);
                
            } else {
                // BALANCE_AMT>=AMT_DUE
                if (0 <= balanceAmt.compareTo(amtDue)) {
                    amtPaid = amtDue;
                    // settle set Yes
                    settled = YES;
                } else {
                    amtPaid = balanceAmt;
                    // settle set No
                    settled = NO;
                }
                // AMT_PAID
                tCsbDCondition.put("amtPaid", amtPaid);
                // ID_LOGIN
                tCsbDCondition.put("idLogin", idLogin);
                // update T_CSB_D
                updateDAO.execute(G_CSB_P02_UPDATE_T_CSB_D, tCsbDCondition);
            }
        } else {
            // BALANCE_AMT>=AMT_DUE
            if (0 <= balanceAmt.compareTo(amtDue)) {
                amtPaid = amtDue;
                // settle set Yes
                settled = YES;
            } else {
                amtPaid = balanceAmt;
                // settle set No
                settled = NO;
            }
            // AMT_PAID
            tCsbDCondition.put("amtPaid", amtPaid);
            // AMT_DUE
            tCsbDCondition.put("amtDue", amtDue);
            // FOREX_LOSS
            tCsbDCondition.put("forexLoss", "0");
            // FOREX_GAIN
            tCsbDCondition.put("forexGain", "0");
            // IS_DELETED
            tCsbDCondition.put("isDeleted", IS_DELETED_0);
            // ID_LOGIN
            tCsbDCondition.put("idLogin", idLogin);
            // insert T_CSB_D
            updateDAO.execute(G_CSB_P02_INSERT_T_CSB_D, tCsbDCondition);
        }
        // settle=Yes
        if (YES.equals(settled)) {
            updateDAO.execute(G_CSB_P02_UPDATE_T_BIL_D_SETTLED, tCsbDCondition);
        } else {
            // settle=No
            updateDAO.execute(G_CSB_P02_UPDATE_T_BIL_D, tCsbDCondition);
        }
        // BALANCE_AMT = BALANCE_AMT - AMT_DUE
        balanceAmt = balanceAmt.subtract(amtDue);
        
        return balanceAmt;
    }
}
