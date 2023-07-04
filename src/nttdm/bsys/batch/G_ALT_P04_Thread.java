package nttdm.bsys.batch;


import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_ALT_P04;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

public class G_ALT_P04_Thread implements Runnable{

	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;
	private BillingSystemUserValueObject uvo;
		public QueryDAO getQueryDAO() {
		return queryDAO;
	}
		
	public G_ALT_P04_Thread(QueryDAO query, UpdateDAO update,BillingSystemUserValueObject ov)
	{
		queryDAO=query;
		updateDAO=update;
		uvo=ov;	
		
	}
		
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public void run() {
		G_ALT_P04 gp04 = new G_ALT_P04(queryDAO,updateDAO);
		gp04.execute(uvo);
		//recode who run
        Integer idbatch=gp04.getIdBatch();
        recodeWhoRun(uvo.getId_user(),"CC",idbatch);
		// TODO Auto-generated method stub
		
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
