/**
 * @(#)G_CUR_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.m_cur.bean.M_CURBean;

/**
 * Process to convert currency amount
 * 
 * @author bench
 */
public class G_CUR_P01 {
    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO = null;
    /**
     * <div>SQL_SELECT_CURRENCY</div>
     */
    private static final String SQL_SELECT_CURRENCY = "SELECT.BSYS.SQL018";
    /**
     * <div>SQL_SELECT_DEFINED_CURRENCY</div>
     */
    private static final String SQL_SELECT_DEFINED_CURRENCY = "SELECT.BSYS.SQL040";

    /**
     * <div>EXPORT_AMOUNT</div>
     */
    public static final String EXPORT_AMOUNT = "EXPORT_AMOUNT";

    /**
     * <div>CUR_RATE</div>
     */
    public static final String CUR_RATE = "CUR_RATE";

    /**
     * Constructor
     */
    public G_CUR_P01(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * Convert export amount by currency rate.
     * 
     * @param billing_currency
     *            Billing Currency
     * @param bill_amount
     *            Billing Amount
     * @param export_cur
     *            Export currency
     * @param fixed_forex
     *            Fixed forex
     * 
     * @return a map contains currency rate and export amount.
     */
    public Map<String, Object> convertCurrency(String billing_currency, double bill_amountD, String export_cur,
            Object fixed_forex) {
        BigDecimal cur_rate = new BigDecimal("0");
        BigDecimal export_amount = new BigDecimal("0");
        BigDecimal bill_amount = new BigDecimal(CommonUtils.toString(bill_amountD));

        // get default currency
        String default_currency = queryDAO.executeForObject(SQL_SELECT_DEFINED_CURRENCY, "DEF_CURRENCY", String.class);

        // set currency rate
        if (fixed_forex == null || fixed_forex.toString().trim().equals("")) {
            // When fixed_forex is not set, get rate by currency.
            M_CURBean m_cur = new M_CURBean();
            if (default_currency != null && default_currency.equals(billing_currency)) {
                // When billing currency is the default currency, get rate of
                // export currency.
                m_cur = queryDAO.executeForObject(SQL_SELECT_CURRENCY, export_cur, M_CURBean.class);
                if (m_cur != null) {
                    cur_rate = new BigDecimal(m_cur.getRate_to());
                }
            } else {
                // When billing currency isn't the default currency, get rate of
                // billing currency.
                m_cur = queryDAO.executeForObject(SQL_SELECT_CURRENCY, billing_currency, M_CURBean.class);
                if (m_cur != null) {
                    cur_rate = new BigDecimal(m_cur.getRate_from());
                }
            }
        } else {
            // When fixed_forex is set, use it as rate.
            cur_rate = new BigDecimal(fixed_forex.toString());
        }
        // get CURRENCY_STD
        String currencySTD = queryDAO.executeForObject(SQL_SELECT_DEFINED_CURRENCY, "CURRENCY_STD", String.class);
     
        // calculate export amount
        if ("1".equals(currencySTD)) {
            export_amount = bill_amount.multiply(cur_rate);
        } else {
            if (default_currency != null && default_currency.equals(billing_currency)) {
                if (cur_rate.compareTo(new BigDecimal("0")) != 0) {
                	try{
                        export_amount = bill_amount.divide(cur_rate);
                	}
                	catch(Exception e){
                		export_amount = bill_amount.divide(cur_rate,8,BigDecimal.ROUND_HALF_UP);
                	}
                }
            } else {
                export_amount = bill_amount.multiply(cur_rate);
            }
        }
        if("JPY".equals(export_cur)){
        	export_amount = export_amount.setScale(0, BigDecimal.ROUND_HALF_UP);
        }
        // set return value
        Map<String, Object> returnValue = new HashMap<String, Object>();
        returnValue.put(EXPORT_AMOUNT, export_amount.doubleValue());
        returnValue.put(CUR_RATE, cur_rate.doubleValue());

        return returnValue;
    }
}
