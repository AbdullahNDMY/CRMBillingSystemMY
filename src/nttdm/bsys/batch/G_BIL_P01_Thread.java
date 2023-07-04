/**
 * @(#)G_BIL_P01_Thread.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.bean.G_BIL_P01_Input;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_BIL_P01;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Run G_BIL_P01 process thread class.
 */
public class G_BIL_P01_Thread implements Runnable {

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
     * running date
     */
    private Date esetRundate;

    /**
     * Get QueryDAO.
     * 
     * @return QueryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * Set QueryDAO.
     * 
     * @param queryDAO
     *            QueryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * Get UpdateDAO.
     * 
     * @return UpdateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * Set UpdateDAO.
     * 
     * @param updateDAO
     *            UpdateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * Get BillingSystemUserValueObject
     * 
     * @return BillingSystemUserValueObject
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * Set BillingSystemUserValueObject
     * 
     * @param uvo
     *            BillingSystemUserValueObject
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    /**
     * @param esetRundate
     *            the esetRundate to set
     */
    public void setEsetRundate(Date esetRundate) {

        this.esetRundate = esetRundate;
    }

    /**
     * @return the esetRundate
     */
    public Date getEsetRundate() {

        return esetRundate;
    }

    /**
     * Constructor.
     * 
     * @param query
     *            QueryDAO
     * @param update
     *            UpdateDAO
     * @param ov
     *            BillingSystemUserValueObject
     * @param rundate
     *            running date
     */
    public G_BIL_P01_Thread(QueryDAO query, UpdateDAO update,
            BillingSystemUserValueObject ov, Date rundate) {
        queryDAO = query;
        updateDAO = update;
        uvo = ov;
        esetRundate = rundate;
    }

    /**
     * Call G_BIL_P01 to run.
     */
    public void run() {
        G_BIL_P01 gbilp01 = new G_BIL_P01(queryDAO, updateDAO);
        G_BIL_P01_Input gbill_in = new G_BIL_P01_Input();
        gbill_in.setModuleId("E");
        gbill_in.setRunning_date(esetRundate);
        gbill_in.setUserId(uvo.getId_user());
        //set who run the action
        gbill_in.setRunFrom("BatchRun");
        
        gbilp01.execute(gbill_in);
      //recode who run
        Integer idbatch=gbilp01.getIdBatch();
        recodeWhoRun(uvo.getId_user(),"GB",idbatch);
    }
    
    /**
     * Audit Trail
     *     @param userid 
     *     @param batchtype 
     *     @param idbatch 
     */
    private void recodeWhoRun(String userid,String batchtype,Integer idbatch) {
        
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("batchId", batchtype);
        Map<String, Object> batchNo = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
        parameter.put("idBatch", idbatch);
        Map<String, Object> batchStatus = queryDAO.executeForMap("E_SET.getBatchStatus", parameter);
        //Reference
        String reference="";
        String status="";
        if(batchNo != null) {
            reference=batchtype+":"+batchNo.get("BATCH_DESC").toString();
        }
        //Status
        if(batchStatus!=null){
            String statusid=batchStatus.get("STATUS").toString();
            if(statusid.equals("1")){
                status="In-Progress";
            }
            if(statusid.equals("2")){
                status="Success";
            }
            if(statusid.equals("3")){
                status="Fail";
            }
        }
      //write log
        Integer idAudit = CommonUtils.auditTrailBegin(userid, 
                BillingSystemConstants.MODULE_E, 
                BillingSystemConstants.SUB_MODULE_E_SET,
                reference, status, BillingSystemConstants.AUDIT_TRAIL_CREATED);
    }

}
