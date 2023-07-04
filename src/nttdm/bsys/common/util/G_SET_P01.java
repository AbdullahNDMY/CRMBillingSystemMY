/**
 * @(#)G_SET_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.bean.T_SET_BATCH;

/**
 * Process to update batch status and log
 * 
 * @author NTTDM
 */
public class G_SET_P01 {
    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;
    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO;

    /**
     * <div>Get queryDAO</div>
     * 
     * @return queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * <div>Set queryDAO</div>
     * 
     * @param queryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * <div>Get updateDAO</div>
     * 
     * @return updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * <div>Set updateDAO</div>
     * 
     * @param updateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * <div>Constructor</div>
     * 
     * @param queryDAO
     *            QueryDAO
     * 
     * @param updateDAO
     *            UpdateDAO
     */
    public G_SET_P01(QueryDAO queryDAO, UpdateDAO updateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
    }

    /**
     * Process to update batch status and log
     * 
     * @param t_batch
     *            T_SET_BATCH
     * 
     * @return ID_BATCH: batch_id for batch process, if fail return -1.
     */
    public G_SET_ReturnValue G_SET_P01_GetIdBatch(T_SET_BATCH t_batch) {

        G_SET_ReturnValue returnValue = new G_SET_ReturnValue();
        int batch_re = -1;
        String last_status_str = "";
        Map<String, Object>[] prev_result;

        // the status of the batch is In-Progress / NEW
        if (t_batch.getSTATUS().equals("1")) {
            try {
                prev_result = queryDAO.executeForMapArray("SELECT.BSYS.G_SETP01.SQL001", t_batch);
                if (prev_result.length > 0) {
                    last_status_str = prev_result[0].get("LAST_STATUS").toString();
                }
                if (last_status_str.equals("1")) {
                    // Last process still "in Process".
                    DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar cal = Calendar.getInstance();

                    Date today = cal.getTime();
                    Date testday = dfm.parse(prev_result[0].get("HIEUTIME").toString());
                    long diff = today.getTime() - testday.getTime();
                    long hourdiff = diff / 1000;
                    String interval_Str = queryDAO.executeForObject("SELECT.BSYS.G_SETP01.SQL002", t_batch, String.class);
                    long interval_long = Long.parseLong(interval_Str);
                    if (interval_long > hourdiff) {
                        batch_re = -1;
                        returnValue.setRET_MSG(MessageUtil.get("errors.ERR1BT020", new Object[]{interval_long / 60}));
                    } else {
                        // Last batch run overtime. NOT UPDATE PREVIOUS BATCH. start new process.
                        updateDAO.execute("INSERT.BSYS.G.SETP01.SQL001", t_batch);
                        String id_batch_ger_str = queryDAO.executeForObject("SELECT.BSYS.G_SETP01.SQL003", t_batch, String.class);
                        batch_re = Integer.parseInt(id_batch_ger_str);
                        
                        // Update M_BATCH_MAINTENANCE.RUN_STATUS
                        t_batch.setSTATUS("Y");
                        updateDAO.execute("UPDATE.BSYS.G.SETP01.SQL0045", t_batch);
                    }
                } else {
                    // No process running, then start new process.
                    updateDAO.execute("INSERT.BSYS.G.SETP01.SQL001", t_batch);

                    String id_batch_ger_str = queryDAO.executeForObject("SELECT.BSYS.G_SETP01.SQL003", t_batch, String.class);
                    batch_re = Integer.parseInt(id_batch_ger_str);
                    
                    // Update M_BATCH_MAINTENANCE.RUN_STATUS
                    t_batch.setSTATUS("Y");
                    updateDAO.execute("UPDATE.BSYS.G.SETP01.SQL0045", t_batch);
                }
            } catch (Exception e) {
                e.printStackTrace();
                returnValue.setBatch_id(batch_re);
                return returnValue;
            }
        }

        // the status of the batch is success
        if (t_batch.getSTATUS().equals("2")) {
            if (t_batch.getMessage() == null
                    || t_batch.getMessage().equals(" ")
                    || t_batch.getMessage().equals("")) {
                updateDAO.execute("UPDATE.BSYS.G.SETP01.SQL001", t_batch);
                batch_re = Integer.parseInt(t_batch.getID_BATCH());
            } else if (t_batch.getMessage() != null
                    && !t_batch.getMessage().equals(" ")
                    && !t_batch.getMessage().equals("")) {
                try {
                    Map<String, Object> sqlParam = new HashMap<String, Object>();
                    sqlParam.put("ID_BATCH", t_batch.getID_BATCH());
                    sqlParam.put("ID_LOGIN", t_batch.getID_LOGIN());
                    String mesg = CommonUtils.toString(t_batch.getMessage());
                    Clob mesgClob = CommonUtils.stringToClob(mesg);
                    sqlParam.put("message", mesgClob.getCharacterStream());
                    updateDAO.execute("INSERT.BSYS.G.SETP01.SQL002", sqlParam);
                    updateDAO.execute("UPDATE.BSYS.G.SETP01.SQL001", t_batch);
                    batch_re = Integer.parseInt(t_batch.getID_BATCH());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            // Update M_BATCH_MAINTENANCE.RUN_STATUS
            Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.BSYS.G_SETP01.lastBatch", t_batch);
            String lastBatchid = lastBatch.get("ID_BATCH").toString();
            if(lastBatchid.equals(t_batch.getID_BATCH())){
                t_batch.setSTATUS("N");
                updateDAO.execute("UPDATE.BSYS.G.SETP01.SQL0045", t_batch); 
            }
        }

        // the status of the batch is fail.
        if (t_batch.getSTATUS().equals("3")) {
            Map<String, Object> sqlParam = new HashMap<String, Object>();
            try {
                sqlParam.put("ID_BATCH", t_batch.getID_BATCH());
                sqlParam.put("ID_LOGIN", t_batch.getID_LOGIN());
                String mesg = CommonUtils.toString(t_batch.getMessage());
                Clob mesgClob = CommonUtils.stringToClob(mesg);
                sqlParam.put("message", mesgClob.getCharacterStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateDAO.execute("INSERT.BSYS.G.SETP01.SQL002", sqlParam);
            updateDAO.execute("UPDATE.BSYS.G.SETP01.SQL001", t_batch);
            batch_re = Integer.parseInt(t_batch.getID_BATCH());
            
            // Update M_BATCH_MAINTENANCE.RUN_STATUS
            Map<String, Object> lastBatch = queryDAO.executeForMap("SELECT.BSYS.G_SETP01.lastBatch", t_batch);
            String lastBatchid = lastBatch.get("ID_BATCH").toString();
            if(lastBatchid.equals(t_batch.getID_BATCH())){
                t_batch.setSTATUS("N");
                updateDAO.execute("UPDATE.BSYS.G.SETP01.SQL0045", t_batch); 
            }
        }

        returnValue.setBatch_id(batch_re);
        return returnValue;
    }
}
