/**
 * @(#)G_CIT_P01_Thread.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.batch;

import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.dao.UpdateDAOiBatisNukedImpl;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CIT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1SubmitOutput;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Thread to run G_CIT_P01.
 */
public class G_CIT_P01_Thread implements Runnable {

    private QueryDAO queryDAO;
    private UpdateDAO updateDAO;
    private UpdateDAOiBatisNukedImpl updateDAONuked;

    private BillingSystemUserValueObject uvo;
    private String sch_day;
    private String sch_hours;
    private String sch_mim;
    private String sch_month;
    private String sch_year; 

    /**
     * scr
     */
    private boolean scr;
    
    /**
     * Bank Account
     */
    private String bankAcc;

	/**
	 * Constructor.
	 * 
	 * @param query
	 * @param update
	 * @param updateNuked
	 * @param ov
	 * @param day
	 * @param hour
	 * @param minute
	 * @param month
	 * @param year
	 * @param bankAccId
	 * @param sCR
	 */
	public G_CIT_P01_Thread(QueryDAO query, UpdateDAO update,
        UpdateDAOiBatisNukedImpl updateNuked, BillingSystemUserValueObject ov,
        String day, String hour, String minute, String month, String year,
        String bankAccId, boolean isSCR) {

        queryDAO = query;
        updateDAO = update;
        updateDAONuked = updateNuked;
        uvo = ov;
        sch_day = day;
        sch_hours = hour;
        sch_mim = minute;
        sch_month = month;
        sch_year = year;
        bankAcc = bankAccId;
        scr = isSCR;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }
    
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    public UpdateDAOiBatisNukedImpl getUpdateDAONuked() {
        return updateDAONuked;
    }

    public void setUpdateDAONuked(UpdateDAOiBatisNukedImpl updateDAONuked) {
        this.updateDAONuked = updateDAONuked;
    }

    public String getSch_day() {
        return sch_day;
    }

    public void setSch_day(String sch_day) {
        this.sch_day = sch_day;
    }

    public String getSch_hours() {
        return sch_hours;
    }

    public void setSch_hours(String sch_hours) {
        this.sch_hours = sch_hours;
    }

    public String getSch_mim() {
        return sch_mim;
    }

    public void setSch_mim(String sch_mim) {
        this.sch_mim = sch_mim;
    }

    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    /**
     * Set SCR.
     * 
     * @param scr the scr to set
     */
    public void setScr(boolean scr) {

        this.scr = scr;
    }

    /**
     * Get SCR.
     * 
     * @return the scr
     */
    public boolean isScr() {

        return scr;
    }

	/**
	 * Get Bank Account ID.
	 * 
	 * @return the bankAcc
	 */
	public String getBankAcc() {
		return bankAcc;
	}

	/**
	 * Set Bank Account ID.
	 * 
	 * @param bankAcc the bankAcc to set
	 */
	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}

	/**
	 * Run thread
	 */
    public void run() {
        RP_E_MEX_CT1SubmitInput input = new RP_E_MEX_CT1SubmitInput();
        input.setScheduleDay(sch_day);
        input.setScheduleHour(sch_hours);
        input.setScheduleMinute(sch_mim);
        input.setClosingMonth(sch_month);
        input.setClosingYear(sch_year);
        input.setUvo(uvo);
        input.setBankAcc(bankAcc);
        input.setScr(scr);
        RP_E_MEX_CT1SubmitOutput output = new RP_E_MEX_CT1SubmitOutput();
        G_CIT_P01 gcit= new G_CIT_P01();
        gcit.setQueryDAO(queryDAO);
        gcit.setUpdateDAO(updateDAO);
        gcit.setUpdateDAONuked(updateDAONuked);
        GlobalProcessResult result=gcit.execute(input, output);
        
        //TODO
        AuditTrailHeaderBean auditTrailHeaderBean=new AuditTrailHeaderBean();
        auditTrailHeaderBean.setIdAudit((Integer) result.getParameter().get("ID_AUDIT"));
        auditTrailHeaderBean.setReference(result.getParameter().get("idBatchRefNo")+":"+result.getParameter().get("closeMonthyear"));
        String status=result.getParameter().get("status").toString();
        if(status.equals("0")){
        	auditTrailHeaderBean.setStatus("Successful");
        }else if(status.equals("1")){
        	auditTrailHeaderBean.setStatus("Failed");
        }else if(status.equals("2")){
        	auditTrailHeaderBean.setStatus("in process");
        }
        CommonUtils.auditTrailUpdate(auditTrailHeaderBean);
    }
    
}
