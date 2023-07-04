/**
 * @(#)G_GIR_P01_Thread.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.batch;

import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.dao.UpdateDAOiBatisNukedImpl;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_GIR_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitOutput;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Thread to run G_GIR_P01.
 */
public class G_GIR_P01_Thread implements Runnable {

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
     * Deduction Date (Day)
     */
    private String deductionDate;
   
	/**
     * Bank Account
     */
    private String bankAcc;

    /**
     * scr
     */
    private boolean scr;
    
    
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
     * @param ductionDate
     * @param bankAccId
     */
    public G_GIR_P01_Thread(QueryDAO query, UpdateDAO update,
            UpdateDAOiBatisNukedImpl updateNuked,
            BillingSystemUserValueObject ov, String day, String hour,
            String minute, String month, String year, String ductionDate,
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
        deductionDate = ductionDate;
        bankAcc = bankAccId;
        scr = isSCR;
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
	 * Get Deduction Date.
	 * 
	 * @return the deductionDate
	 */
	public String getDeductionDate() {
		return deductionDate;
	}

	/**
	 * Set Deduction Date.
	 * 
	 * @param deductionDate the deductionDate to set
	 */
	public void setDeductionDate(String deductionDate) {
		this.deductionDate = deductionDate;
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
     * Run thread.
     */
    public void run() {
        RP_E_MEX_GR1SubmitInput input = new RP_E_MEX_GR1SubmitInput();
        input.setScheduleDay(sch_day);
        input.setScheduleHour(sch_hours);
        input.setScheduleMinute(sch_mim);
        input.setClosingMonth(sch_month);
        input.setClosingYear(sch_year);
        input.setUvo(uvo);
        input.setBankAcc(bankAcc);
        input.setScr(scr);
        input.setDeductionDate(deductionDate);
        RP_E_MEX_GR1SubmitOutput output = new RP_E_MEX_GR1SubmitOutput();
        G_GIR_P01 ggir = new G_GIR_P01();
        ggir.setQueryDAO(queryDAO);
        ggir.setUpdateDAO(updateDAO);
        ggir.setUpdateDAONuked(getUpdateDAONuked());
        GlobalProcessResult result=ggir.execute(input, output);
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
