/**
 * @(#)G_SET_P03.java
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.bean.G_SET_ReturnValue;

/**
 * Check for "In Process" Status T_BATCH_SCH.RUN_STATUS.
 * 
 * @author NTTDM
 * 
 */
public class G_SET_P03 {
    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;

    public G_SET_P03(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * Check for "In progress" Status
     * 
     * @param batch_type
     * @return
     * @throws ParseException 
     */
    public G_SET_ReturnValue G_SET_P03_CheckStatus(String batch_type) throws ParseException {
        G_SET_ReturnValue returnValue = new G_SET_ReturnValue();
        int retStatus = 0;
        String RET_MSG = "";
        String runStatus = queryDAO.executeForObject("SELECT.BSYS.G_SETP03.getRunStatus", batch_type, String.class);
        if("Y".equals(runStatus)){
            String create_date = "";
            Map<String, Object> resultMap = new HashMap<String, Object>();
            Map<String, Object> param = new HashMap<String, Object>();

            if ("G_CIT_P01".equals(batch_type)) {
                param.put("batchIdName", "ID_CIT_EXP_BATCH"); 
                param.put("tableName", "T_CIT_EXP_HD"); 
            } else if ("G_GIR_P01".equals(batch_type)) {
                param.put("batchIdName", "ID_GIR_EXP_BATCH"); 
                param.put("tableName", "T_GIR_EXP_HD"); 
            } else if ("G_SGP_P01".equals(batch_type)) {
                param.put("batchIdName", "ID_SGP_EXP_BATCH"); 
                param.put("tableName", "T_SGP_EXP_HD");
            } else if ("G_SGP_P02".equals(batch_type)) {
                param.put("batchIdName", "ID_SGP_IMP_BATCH"); 
                param.put("tableName", "T_SGP_IMP_HD");
            } else if ("G_CLC_P01".equals(batch_type)){
                param.put("batchIdName", "ID_CLC_IMP_BATCH"); 
                param.put("tableName", "T_CLC_IMP_HD");
            }
            // retrive latest in-Process Status record for batch history table
            resultMap = queryDAO.executeForMap("SELECT.BSYS.G_SETP03.getStatusAndDate", param);
            
            if(resultMap != null && !resultMap.isEmpty()){
                create_date = resultMap.get("DATE_CREATED").toString();
                DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();

                Date today = cal.getTime();
                Date testday = dfm.parse(create_date);
                long diff = today.getTime() - testday.getTime();
                long hourdiff = diff / 1000;
                String interval_Str = queryDAO.executeForObject("SELECT.BSYS.G_SETP03.BATCH_TIME_INTERVAL", null,String.class);
                long interval_long = Long.parseLong(interval_Str);
                if (hourdiff < interval_long) {
                    // Batch still running.
                    retStatus = -1;
                    RET_MSG = MessageUtil.get("errors.ERR1BT020", new Object[] { interval_long / 60 });
                } else {
                    // Batch run overtime.
                    retStatus = 1;
                    RET_MSG = MessageUtil.get("errors.ERR1BT022", new Object[]{resultMap.get("BATCH_ID")});
                }
                
            }else{
                // No in-Process record in BATCH History table.
                retStatus = 0;
                RET_MSG = "";
            }
        }else{
             retStatus = 0;
             RET_MSG = "";
        }
        
        returnValue.setRET_MSG(RET_MSG);
        returnValue.setRetStatus(retStatus);
        return returnValue;
    }
}
