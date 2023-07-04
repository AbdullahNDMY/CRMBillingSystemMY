package nttdm.bsys.batch;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.dao.UpdateDAOiBatisNukedImpl;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.G_SGP_P01;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_SP1SubmitOutput;

import org.apache.commons.lang.StringUtils;

public class G_SGP_P01_Thread implements Runnable {

    private QueryDAO queryDAO;

    private UpdateDAO updateDAO;

    private UpdateDAOiBatisNukedImpl updateDAONuked;

    private BillingSystemUserValueObject uvo;

    private String sch_day;

    private String sch_hours;

    private String sch_mim;

    /**
     * dateReq
     */
    private String dateReq;

    /**
     * scr
     */
    private boolean isScr;

    public String getSch_month() {

        return sch_month;
    }

    public void setSch_month(String sch_month) {

        this.sch_month = sch_month;
    }

    public String getSch_year() {

        return sch_year;
    }

    public void setSch_year(String sch_year) {

        this.sch_year = sch_year;
    }

    private String sch_month;

    private String sch_year;

    /**
     * Deduction Date (Day)
     */
    private String deductionDate;

    /**
     * @return the deductionDate
     */
    public String getDeductionDate() {

        return deductionDate;
    }

    /**
     * @param deductionDate
     * the deductionDate to set
     */
    public void setDeductionDate(String deductionDate) {

        this.deductionDate = deductionDate;
    }

    public QueryDAO getQueryDAO() {

        return queryDAO;
    }

    /**
     * @param dateReq
     * the dateReq to set
     */
    public void setDateReq(String dateReq) {

        this.dateReq = dateReq;
    }

    /**
     * @return the dateReq
     */
    public String getDateReq() {

        return dateReq;
    }

    /**
     * @param isScr
     * the isScr to set
     */
    public void setScr(boolean isScr) {

        this.isScr = isScr;
    }

    /**
     * @return the isScr
     */
    public boolean isScr() {

        return isScr;
    }

    public G_SGP_P01_Thread(QueryDAO query, UpdateDAO update,
        UpdateDAOiBatisNukedImpl updateNuked, BillingSystemUserValueObject ov,
        String day, String hour, String minute, String month, String year,
        String deductionDate, String dateReq, boolean isScr) {

        queryDAO = query;
        updateDAO = update;
        updateDAONuked = updateNuked;
        uvo = ov;
        sch_day = day;
        sch_hours = hour;
        sch_mim = minute;
        sch_month = month;
        sch_year = year;
        this.deductionDate = deductionDate;
        this.dateReq = dateReq;
        this.isScr = isScr;
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

    public void run() {

        RP_E_MEX_SP1SubmitInput input = new RP_E_MEX_SP1SubmitInput();
        // 2. $dateReq = sysdate - 1 month (in MM/YYYY format)
        sch_month = StringUtils.left(dateReq, 2);
        sch_year = StringUtils.right(dateReq, 4);
        input.setScheduleDay(sch_day);
        input.setScheduleHour(sch_hours);
        input.setScheduleMinute(sch_mim);
        input.setClosingMonth(sch_month);
        input.setClosingYear(sch_year);
        input.setUvo(uvo);
        input.setScr(isScr);
        input.setDeductionDate(deductionDate);
        RP_E_MEX_SP1SubmitOutput output = new RP_E_MEX_SP1SubmitOutput();

        G_SGP_P01 gsgp = new G_SGP_P01();
        gsgp.setQueryDAO(queryDAO);
        gsgp.setUpdateDAO(updateDAO);
        gsgp.setUpdateDAONuked(updateDAONuked);
        gsgp.execute(input, output);

    }

}
