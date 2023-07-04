/**
 * @(#)G_CSB_P01_Check.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/02/26
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;

/**
 * @author gplai
 *
 */
public class G_CSB_P01_Check {

    private QueryDAO queryDAO;
    
    public G_CSB_P01_Check(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
    
    public G_CSB_P01_CheckOutput execute(G_CSB_P01_CheckInput input) {
        G_CSB_P01_CheckOutput output = new G_CSB_P01_CheckOutput();
        boolean resultFlg = true;
        String errorMsg = "";
        Map<String, Object> batchMaintanceMap = queryDAO.executeForMap("SELECT.G_CSB_P01_CHECK.M_BATCH_MAINTENANCE_INFO", null);
        
        if (batchMaintanceMap!=null) {
            String last_status_str = "";
            String messageParam1 = input.getMessageParam1();
            String messageParam2 = input.getMessageParam2();
            String messageParam3 = input.getMessageParam3();
            Map<String, Object> prev_result = queryDAO.executeForMap("SELECT.G_CSB_P01_CHECK.T_SET_BATCH_INFO", null);
            if (prev_result!=null) {
                last_status_str = CommonUtils.toString(prev_result.get("LAST_STATUS"));
            }
            
            //In process
            if ("1".equals(last_status_str)) {
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
                String interval_Str = queryDAO.executeForObject("SELECT.G_CSB_P01_CHECK.BATCH_TIME_INTERVAL", null, String.class);
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
