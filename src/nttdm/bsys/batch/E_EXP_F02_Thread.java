/**
 * @(#)E_EXP_F02_Thread.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.E_EXP_F02;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 *  Statement of Accounts Export Called from G_SET_P02 by scheduler
 */
public class E_EXP_F02_Thread implements Runnable{
	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;
	private BillingSystemUserValueObject uvo;
	private Date esetRundate;
    private String statementMonth;

    /**
     * @return the esetRundate
     */
    public Date getEsetRundate() {
        return esetRundate;
    }

    /**
     * @param esetRundate the esetRundate to set
     */
    public void setEsetRundate(Date esetRundate) {
    
    }

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	
    /**
     * @param queryDAO the queryDAO to set
     */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	/**
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	
	/**
	 * @param updateDAO the updateDAO to set
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

    /**
     * @return the statementMonth
     */
    public String getStatementMonth() {
        return statementMonth;
    }
    /**
     * @param statementMonth the statementMonth to set
     */
    public void setStatementMonth(String statementMonth) {
        this.statementMonth = statementMonth;
    }

	/**
	 * Initialize E_EXP_F02_Thread.
	 * 
	 * @param query QueryDAO
	 * @param update UpdateDAO
	 * @param ov BillingSystemUserValueObject
	 * @param esetRundate esetRundate
	 * @param stmtMonth Statement Month
	 */
    public E_EXP_F02_Thread(QueryDAO query, UpdateDAO update,
            BillingSystemUserValueObject ov, Date esetRundate, String stmtMonth) {
        queryDAO = query;
        updateDAO = update;
        uvo = ov;
        this.esetRundate = esetRundate;
        this.statementMonth = stmtMonth;
    }
    
    /**
     *  Run E_EXP_F02
     */
	public void run() {
		E_EXP_F02 expf02= new E_EXP_F02();
		expf02.setQueryDAO(queryDAO);
		expf02.setUpdateDAO(updateDAO);
		expf02.setEset_rundate(esetRundate);
		expf02.setCurStmtMonth(statementMonth);
		expf02.setBatch("E");
		expf02.excute(null);
		//recode who run
        Integer idbatch=expf02.getIdBatch();
        recodeWhoRun(uvo.getId_user(),"SA",idbatch);
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
