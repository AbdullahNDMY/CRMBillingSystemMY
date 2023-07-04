/**
 * @(#)E_SET_S03Common.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/12/13
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
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_set.bean.E_SET_S03Bean;

/**
 * Check for "In Process" status.
 * 
 * @author NTTDM
 *
 */
public class E_SET_S03Common {
    
    /**
     * Still running (In Process)
     */
    private static final String RUN_STATUS_YES = "Y";
    
    /**
     * Batch Status In-Progress
     */
    private static final String BATCH_STATUS_IN_PROGRESS = "1";
    
    /**
     * errors.ERR1BT020
     */
    public static final String ERRORS_ERR1BT020 = "errors.ERR1BT020";
    
    /**
     * errors.ERR1BT022
     */
    public static final String ERRORS_ERR1BT022 = "errors.ERR1BT022";
    
    /**
     * 0: No batch run in process.
     */
    public static final String RET_STATUS_0 = "0";
    
    /**
     * 1: Last batch still in Process but overtime.
     */
    public static final String RET_STATUS_1 = "1";
    
    /**
     * -1: Last batch still in Process.
     */
    public static final String RET_STATUS_NEGATIVE_1 = "-1";
    
    private QueryDAO queryDAO;

    public E_SET_S03Common(QueryDAO queryDAO){
        this.queryDAO = queryDAO;
    }
    
    /**
     * Check In Process Status
     * @param batchType
     * @return
     */
    public E_SET_S03Bean checkInProcessStatus(String batchType) {
        E_SET_S03Bean e_set_s03Bean = new E_SET_S03Bean();
        batchType = CommonUtils.toString(batchType).trim();
        
        if(!CommonUtils.isEmpty(batchType)) {
            // Retrive RUN_STATUS by BATCH_TYPE
            Map<String, Object> m_batch_maintenanceMap = queryDAO.executeForMap("E_SET.getRunStatusByBatchId", batchType);
            String runStatus = CommonUtils.toString(m_batch_maintenanceMap.get("RUN_STATUS"));
            // RUN_STATUS is Y (In Process)
            if(RUN_STATUS_YES.equals(runStatus)) {
                Map<String, Object> t_set_batchMap = queryDAO.executeForMap("E_SET.getLastDataByBatchType", batchType);
                String previousStatus = CommonUtils.toString(t_set_batchMap.get("STATUS"));
                String batchId = CommonUtils.toString(t_set_batchMap.get("ID_BATCH"));
                //previousStatus is 1
                if (BATCH_STATUS_IN_PROGRESS.equals(previousStatus)) {
                    BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
                    long timeInterval = setting.getBatchTimeInterval()* 1000;
                    String lastDataCreateTime = CommonUtils.toString(t_set_batchMap.get("DATE_CREATED_CHAR"));
                    long currentDate = Calendar.getInstance().getTimeInMillis();
                    DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    try {
                        Date updateDate = dfm.parse(lastDataCreateTime);
                        long updateDateLong = updateDate.getTime();
                        // check last batch run time.
                        if (currentDate - updateDateLong < timeInterval) {
                            // Latest Batch still in Process.
                            e_set_s03Bean.setRetStatus(RET_STATUS_NEGATIVE_1);
                            e_set_s03Bean.setRetMsg(ERRORS_ERR1BT020);
                        } else {
                            // Latest Batch Process run overtime.
                            e_set_s03Bean.setRetStatus(RET_STATUS_1);
                            e_set_s03Bean.setRetMsg(ERRORS_ERR1BT022);
                            e_set_s03Bean.setBatchId(batchId);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    e_set_s03Bean.setRetStatus(RET_STATUS_1);
                    e_set_s03Bean.setRetMsg(ERRORS_ERR1BT022);
                    e_set_s03Bean.setBatchId(batchId);
                }
            } else {
                e_set_s03Bean.setRetStatus(RET_STATUS_0);
                e_set_s03Bean.setRetMsg("");
            }
        }
        return e_set_s03Bean;
    }
}
