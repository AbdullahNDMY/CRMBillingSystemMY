package nttdm.bsys.batch;

import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * @author qinjinh
 */
public class G_CSB_P01_Thread implements Runnable {

    /** queryDAO */
    private QueryDAO queryDAO;

    /** updateDAO */
    private UpdateDAO updateDAO;

    /** uvo */
    private BillingSystemUserValueObject uvo;

    /** customer Type */
    private String customerType;

    /**
     * @param query
     * QueryDAO
     * @param update
     * UpdateDAO
     * @param vo
     * BillingSystemUserValueObject
     */
    public G_CSB_P01_Thread(QueryDAO query, UpdateDAO update,
        BillingSystemUserValueObject vo, String custType) {

        queryDAO = query;
        updateDAO = update;
        uvo = vo;
        customerType = custType;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    /** run */
    public void run() {

        G_CSB_P01 gCsbP01 = new G_CSB_P01();
        gCsbP01.setQueryDAO(queryDAO);
        gCsbP01.setUpdateDAO(updateDAO);
        gCsbP01.setUvo(uvo);
        gCsbP01.setBatchId(null);
        gCsbP01.setCustomerType(customerType);
        gCsbP01.execute();
      //recode who run
        Integer idbatch=gCsbP01.getIdBatch();
        recodeWhoRun(uvo.getId_user(),"CB",idbatch);
    }

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {

        return queryDAO;
    }

    /**
     * @param queryDAO
     * the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {

        this.queryDAO = queryDAO;
    }

    /**
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {

        return updateDAO;
    }

    /**
     * @param updateDAO
     * the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {

        this.updateDAO = updateDAO;
    }

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {

        return uvo;
    }

    /**
     * @param uvo
     * the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {

        this.uvo = uvo;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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
