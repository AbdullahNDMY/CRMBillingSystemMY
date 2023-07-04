/**
 * @(#)E_SET_S01_Check.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/02/26
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_set.blogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.MessageUtil;
import nttdm.bsys.e_set.bean.E_SET_S01_CheckInput;
import nttdm.bsys.e_set.bean.E_SET_S01_CheckOutput;

/**
 * @author gplai
 *
 */
public class E_SET_S01_Check {

private QueryDAO queryDAO;
    
    public static final String ID_BATCH_TYPE_G_SGP_P02 = "G_SGP_P02";
    public static final String ID_BATCH_TYPE_G_CIT_P01 = "G_CIT_P01";
    public static final String ID_BATCH_TYPE_G_GIR_P01 = "G_GIR_P01";
    
    public E_SET_S01_Check(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    
    public E_SET_S01_CheckOutput execute(E_SET_S01_CheckInput input) {
        E_SET_S01_CheckOutput output = new E_SET_S01_CheckOutput();
        boolean resultFlg = true;
        String errorMsg = "";
        String idBatchType = input.getIdBatchType();
        Map<String, Object> batchMaintanceMap = queryDAO.executeForMap("SELECT.E_ESET_S01_CHECK.T_BATCH_SCH_INFO", idBatchType);
        
        if (batchMaintanceMap!=null) {
            String last_status_str = "";
            String prev_result_sql = "";
            String messageParam1 = input.getMessageParam1();
            String messageParam2 = input.getMessageParam2();
            String messageParam3 = input.getMessageParam3();
            if (ID_BATCH_TYPE_G_SGP_P02.equals(idBatchType)) {
                prev_result_sql = "SELECT.E_ESET_S01_CHECK.T_SGP_IMP_HD";
            } else if (ID_BATCH_TYPE_G_CIT_P01.equals(idBatchType)) {
                prev_result_sql = "SELECT.E_ESET_S01_CHECK.T_CIT_EXP_HD";
            } else if (ID_BATCH_TYPE_G_GIR_P01.equals(idBatchType)) {
                prev_result_sql = "SELECT.E_ESET_S01_CHECK.T_GIR_EXP_HD";
            }
            Map<String, Object> prev_result = queryDAO.executeForMap(prev_result_sql, null);
            if (prev_result!=null) {
                last_status_str = CommonUtils.toString(prev_result.get("LAST_STATUS"));
            }
            
            //In process
            if ("2".equals(last_status_str)) {
                DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();

                Date today = cal.getTime();
                Date testday = null;
                try {
                    testday = dfm.parse(prev_result.get("HIEUTIME").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diff = today.getTime() - testday.getTime();
                long hourdiff = diff / 1000;
                String interval_Str = queryDAO.executeForObject("SELECT.E_ESET_S01_CHECK.BATCH_TIME_INTERVAL", null, String.class);
                long interval_long = Long.parseLong(interval_Str);
                if (hourdiff < interval_long) {
                    errorMsg = MessageUtil.get("errors.ERR1BT025", messageParam1, messageParam2, messageParam3);
                } else {
                    String batchId = CommonUtils.toString(prev_result.get("ID_BATCH")).trim();
                    errorMsg = "Auto Offset Cash book - " + MessageUtil.get("errors.ERR1BT022", batchId);
                }
                resultFlg = false;
            }
        }
        output.setMessageContext(errorMsg);
        output.setResultFlg(resultFlg);
        
        return output;
    }
}
