/**
 * @(#)G_ALT_P05.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.g_alt.G_ALT_P06Input;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;

/**
 * Generate Service Due Date Alert.
 * 
 * @author NTTDM
 */
public class G_ALT_P05 {
    private static final String SELECT_ALERT = "SELECT.BSYS.SQL036";
    private static final String SELECT_CUST_PLAN_INFO = "SELECT.BSYS.SQL037";

    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;

    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO;

    /**
     * <div>msgResource</div>
     */
    private GlobalMessageResources msgResource;

    /**
     * batchId
     */
    private Integer idBatch=0;

    /**
     * idBatch を取得する
     * @return idBatch
     */
    public Integer getIdBatch() {
        return idBatch;
    }

    /**
     * batchId を設定する
     * @param idBatch
     *            idBatch
     */
    public void setIdBatch(Integer idBatch) {
        this.idBatch = idBatch;
    }
    
    /**
     * <div>Constructor</div>
     * 
     * @param queryDAO
     *            QueryDAO
     * @param updateDAO
     *            UpdateDAO
     */
    public G_ALT_P05(QueryDAO queryDAO, UpdateDAO updateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;

        // init resource
        this.msgResource = GlobalMessageResources.getInstance();
    }

    /**
     * <div>execute</div>
     * 
     * @param uvo
     *            instance of BillingSystemUserValueObject
     * @return object null
     */
    public Object execute(BillingSystemUserValueObject uvo) {

        T_SET_BATCH t_batch = new T_SET_BATCH();
        t_batch.setSTATUS("1");
        t_batch.setBATCH_TYPE("SD");
        // Call G_SET_P01
        G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
        int batchId = gsetp01.G_SET_P01_GetIdBatch(t_batch).getBatch_id();
        this.idBatch=batchId;
        
        if (batchId > 0) {
            // get alert-user list
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("BATCH_ID", "SD");
            List<Map<String, Object>> alertList = this.queryDAO.executeForMapList(SELECT_ALERT, params);
            // check have any alert user
            if (alertList.size() > 0) {
//                if (alertList.get(0).get("NO_OF_MONTH") == null) {
//                    return null;
//                }
                String noOfMonth = CommonUtils.toString(alertList.get(0).get("NO_OF_MONTH"));
                if (CommonUtils.isEmpty(noOfMonth)) {
                    noOfMonth = "0";
                }
                
                // get plan information
                params = new HashMap<String, Object>();
                params.put("NO_OF_MONTH", noOfMonth);
                List<Map<String, Object>> planList = this.queryDAO.executeForMapList(SELECT_CUST_PLAN_INFO, params);

                if (planList.size() <= 0) {
                    t_batch = new T_SET_BATCH();
                    t_batch.setSTATUS("3");
                    t_batch.setID_BATCH(Integer.toString(batchId));
                    t_batch.setBATCH_TYPE("SD");
                    t_batch.setMessage(this.msgResource.getMessage("info.ERR2SC050", null));
                    // Call G_SET_P01
                    gsetp01.G_SET_P01_GetIdBatch(t_batch);

                    return null;
                }

                ArrayList<String> arrUserAlert = new ArrayList<String>();

                // process for get user alert
                for (Map<String, Object> planItem : planList) {
                    String value ="[" + planItem.get("TRANSACTION_TYPE") + "]" + " " + "[" + planItem.get("ID_CUST_PLAN") + "]" + " " 
                            + planItem.get("CUST_NAME") + " "
                            + planItem.get("SVC_END") + "<br>";
                    arrUserAlert.add(value);
                }

                t_batch = new T_SET_BATCH();
                t_batch.setSTATUS("2");
                t_batch.setID_BATCH(Integer.toString(batchId));
                t_batch.setBATCH_TYPE("SD");
                t_batch.setMessage(this.msgResource.getMessage("info.ERR2SC025", new Object[] { "[Sub ID]<br>" + arrUserAlert.toString().substring(1, arrUserAlert.toString().length()-1).replaceAll(",", "") }));
                gsetp01.G_SET_P01_GetIdBatch(t_batch);

                G_ALT_P06 altP06 = new G_ALT_P06(queryDAO, updateDAO);
                if (arrUserAlert.size() > 0) {
                    G_ALT_P06Input input = new G_ALT_P06Input();
                    input.setBatchId("SD");
                    String message = this.msgResource.getMessage("info.ERR2SC024", null);
                    String subject = message;
                    message = this.msgResource.getMessage("info.ERR2SC025", new Object[] { "[Sub ID]<br>" + " " + arrUserAlert.toString().toString().substring(1, arrUserAlert.toString().length()-1).replaceAll(",", "") });
                    String msg = message;
                    input.setSubject(subject);
                    input.setMsg(msg);
                    input.setListAlertUser(alertList);
                    altP06.execute(input, uvo);
                }
            } else {
                t_batch = new T_SET_BATCH();
                t_batch.setSTATUS("3");
                t_batch.setID_BATCH(Integer.toString(batchId));
                t_batch.setBATCH_TYPE("SD");
                t_batch.setMessage(this.msgResource.getMessage("info.ERR2SC050", null));
                // Call G_SET_P01
                gsetp01.G_SET_P01_GetIdBatch(t_batch);
            }
        }
        return null;
    }
}
