/**
 * @(#)G_SET_P02.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import nttdm.bsys.batch.E_EXP_F01_Thread;
import nttdm.bsys.batch.E_EXP_F02_Thread;
import nttdm.bsys.batch.E_MWF_AR1_Thread;
import nttdm.bsys.batch.G_ALT_P04_Thread;
import nttdm.bsys.batch.G_ALT_P05_Thread;
import nttdm.bsys.batch.G_BIL_P01_Thread;
import nttdm.bsys.batch.G_CIT_P01_Thread;
import nttdm.bsys.batch.G_CSB_P01_Thread;
import nttdm.bsys.batch.G_GIR_P01_Thread;
import nttdm.bsys.batch.G_RAD_P01_Thread;
import nttdm.bsys.batch.G_SGP_P01_Thread;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.bean.M_BATCH;
import nttdm.bsys.common.dao.UpdateDAOiBatisNukedImpl;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_set.blogic.E_SET_S03Common;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;

/**
 * Batch Scheduler
 * 
 * @author NTTDM
 */
public class G_SET_P02 {

    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;

    /**
     * UserAccessInfo
     */
    private BillingSystemUserValueObject uvo;

    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO = null;
    
    /**
     * radiusQueryDAO
     */
    protected QueryDAO radiusQueryDAO;

    /**
     * radiusUpdateDAO
     */
    protected UpdateDAO radiusUpdateDAO;
    
    /**
     * <div>id_user</div>
     */
    private String id_user;
    
    /**
     * isScr
     */
    private boolean isScr;
    
    /**
     * dateReq
     */
    private String dateReq;
    
    /**
     * esetRundate
     */
    private Date esetRundate;
    /**
     * <div>SELECT_BATCH_SETTING</div>
     */
    private static final String SELECT_BATCH_SETTING = "SELECT.BSYS.G_SET_P02.SQL002";
    /**
     * <div>SELECT_DAILY_TIME</div>
     */
    private static final String SELECT_DAILY_TIME = "SELECT.BSYS.G_SET_P02.SQL001";
    /**
     * <div>SELECT_BATCH_SETTING_2</div>
     */
    private static final String SELECT_BATCH_SETTING_2 = "SELECT.BSYS.G_SET_P02.SQL003";

    /**
     * <div>Get queryDAO</div>
     * 
     * @return queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * <div>Get UserAccessInfo</div>
     * 
     * @return uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * <div>Set UserAccessInfo</div>
     * 
     * @param uvo
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    /**
     * <div>getId_user</div> id user will be call from application message
     * (global message).
     */
    public String getId_user() {
        return id_user;
    }

    /**
     * <div>setId_user</div>
     */
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    /**
     * <div>setQueryDAO</div>
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * <div>getUpdateDAO</div>
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * <div>setUpdateDAO</div>
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * UpdateDAOiBatisNukedImpl
     */
    private UpdateDAOiBatisNukedImpl updateDAONuked = null;

    /**
     * Get updateDAONuked
     * 
     * @return updateDAONuked
     */
    public UpdateDAOiBatisNukedImpl getUpdateDAONuked() {
        return updateDAONuked;
    }

    /**
     * Set updateDAONuked
     * 
     * @param updateDAONuked
     */
    public void setUpdateDAONuked(UpdateDAOiBatisNukedImpl updateDAONuked) {
        this.updateDAONuked = updateDAONuked;
    }
    /**
     * @param isScr the isScr to set
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
    /**
     * <div>Constructor</div>
     * 
     * @param queryDAO
     *            QueryDAO
     * 
     * @param updateDAO
     *            UpdateDAO
     * 
     * @param updateDAONuked
     *            UpdateDAOiBatisNukedImpl
     */
    public G_SET_P02(QueryDAO queryDAO, UpdateDAO updateDAO,
            UpdateDAOiBatisNukedImpl updateDAONuked, QueryDAO radiusQueryDAO, UpdateDAO radiusUpdateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
        this.updateDAONuked = updateDAONuked;
        this.radiusQueryDAO = radiusQueryDAO;
        this.radiusUpdateDAO = radiusUpdateDAO;
    }

    /**
     * main function
     */
    public void excute_batch() {
        exec_batch_monthly();
        daily_Global_process();
    }

    /**
     * function global_process schedule.
     */
    private void daily_Global_process() {
        List<HashMap<String, Object>> list_global_pro = queryDAO.executeForObjectList(SELECT_BATCH_SETTING_2, null);
        Calendar calen = Calendar.getInstance();
        int current_hours = calen.get(Calendar.HOUR_OF_DAY);
        int current_minute = calen.get(Calendar.MINUTE);
        int current_month = calen.get(Calendar.MONTH) + 1;
        int current_day = calen.get(Calendar.DATE);
        int current_year = calen.get(Calendar.YEAR);
        int max_day_of_curr_month = calen.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (list_global_pro != null) {
            G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
            for (int i = 0; i < list_global_pro.size(); i++) {
                // check time in t_set_batch is not null, if null --> skip
                if (!"0".equals(list_global_pro.get(i).get("EXEC_DAY").toString())) {
                    String module_name = list_global_pro.get(i).get("ID_BATCH_TYPE").toString();
                    int daily_schel = Integer.parseInt(list_global_pro.get(i).get("EXEC_DAY").toString());
                    int hours_schel = Integer.parseInt(list_global_pro.get(i).get("EXEC_HOUR").toString());
                    int min_schel = Integer.parseInt(list_global_pro.get(i).get("EXEC_MINUTE").toString());
                    int deductionDate = 0;
                    if (StringUtils.isNumeric(String.valueOf(list_global_pro.get(i).get("GIRO_DEDUCT_DAY")))) {
                        deductionDate = Integer.parseInt(list_global_pro.get(i).get("GIRO_DEDUCT_DAY").toString());
                        if (deductionDate > max_day_of_curr_month) {
                            deductionDate = max_day_of_curr_month;
                        }
                    }
                    isScr = false;
                    Calendar dateReqCa = Calendar.getInstance();
                    dateReqCa.clear();
                    dateReqCa.set(Calendar.YEAR, current_year);
                    dateReqCa.set(Calendar.MONTH, current_month-1);
                    //dateReqCa.add(Calendar.MONTH, -1);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
                    dateReq = sdf.format(dateReqCa.getTime());
                    
                    String bankAcc = String.valueOf(list_global_pro.get(i).get("ID_COM_BANK"));
                    if (daily_schel > max_day_of_curr_month)
                        daily_schel = max_day_of_curr_month;
                    try {
                        if (module_name.equalsIgnoreCase("G_CIT_P01")
                                && daily_schel == current_day
                                && hours_schel == current_hours
                                && min_schel == current_minute) {
                            G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus("G_CIT_P01");
                            if (returnValue.getRetStatus() == 0) {
                                Thread g_cit_p01_thr = new Thread(
                                        new G_CIT_P01_Thread(queryDAO, updateDAO,
                                                updateDAONuked, uvo, daily_schel + "", hours_schel + "",
                                                min_schel + "", current_month + "", 
                                                current_year + "", bankAcc, isScr));
                                g_cit_p01_thr.run();
                            }
                        } else if (module_name.equalsIgnoreCase("G_GIR_P01")
                                && daily_schel == current_day
                                && hours_schel == current_hours
                                && min_schel == current_minute) {
                            G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus("G_GIR_P01");
                            if (returnValue.getRetStatus() == 0) {
                                Thread g_gir_p01_thr = new Thread(
                                        new G_GIR_P01_Thread(queryDAO, updateDAO,
                                                updateDAONuked, uvo, daily_schel + "", hours_schel + "",
                                                min_schel + "", current_month + "",
                                                current_year + "", deductionDate + "", bankAcc, isScr)); 
                                g_gir_p01_thr.run();
                            }
                        } else if (module_name.equalsIgnoreCase("G_SGP_P01")
                                && daily_schel == current_day
                                && hours_schel == current_hours
                                && min_schel == current_minute) {
                             G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus("G_SGP_P01");
                             if (returnValue.getRetStatus() == 0) {
                                Thread g_sgp = new Thread(new G_SGP_P01_Thread(
                                        queryDAO, updateDAO, updateDAONuked, uvo,
                                        daily_schel + "", hours_schel + "", min_schel + "", current_month + "",
                                        current_year + "",deductionDate + "", dateReq, isScr));
                                g_sgp.run();
                              }
                        } else if (module_name.equalsIgnoreCase("G_RAD_P01")
                                && daily_schel == current_day
                                && hours_schel == current_hours
                                && min_schel == current_minute) {
                            Thread g_rad = new Thread(new G_RAD_P01_Thread(
                                    queryDAO, updateDAO, updateDAONuked, uvo,
                                    daily_schel + "", hours_schel + "",
                                    min_schel + "", current_month + "",
                                    current_year + ""));
                            g_rad.run();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * function call batch_process
     */
    private void exec_batch_monthly() {

        uvo = new BillingSystemUserValueObject();
        GlobalMessageResources message_info = GlobalMessageResources.getInstance();
        String id_login = message_info.getMessage("info.IDBatchLogin");

        uvo.setId_user(id_login);

        /**
         * get daily time for call e_mwf process.
         */
        Calendar calen = Calendar.getInstance();
        Date current = calen.getTime();
        int current_hours = calen.get(Calendar.HOUR_OF_DAY);
        int current_minute = calen.get(Calendar.MINUTE);
        int current_month = calen.get(Calendar.MONTH) + 1;
        int current_day = calen.get(Calendar.DATE);
        int max_day_of_curr_month = calen.getActualMaximum(Calendar.DAY_OF_MONTH);

        /**
         * get info schedule from m_batch_maintenate table.
         */
        List<M_BATCH> batches = new ArrayList<M_BATCH>();
        batches = queryDAO.executeForObjectList(SELECT_BATCH_SETTING, null);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String day = dateFormat.format(date);

        E_SET_S03Common e_SET_S03Common = new E_SET_S03Common(queryDAO);
        for (M_BATCH batch : batches) {
            /*
             * for daily run ; if batch id ="WE or PS" - check daily time run.
             * don't care table batch_maiternane have runtimes or not.
             */
            if ("DAILY".equalsIgnoreCase(batch.getFrequency_mode())) {
                // get daily time in M_SET_D
                String dailyTime = queryDAO.executeForObject(SELECT_DAILY_TIME, null, String.class);
                if (dailyTime != null) {
                    String short_current = current_hours + "" + current_minute;
                    String cur_hours_str = "";
                    String cur_min_str = "";
                    if (current_hours < 10) {
                        cur_hours_str = "0" + current_hours;
                    } else {
                        cur_hours_str = current_hours + "";
                    }
                    if (current_minute < 10) {
                        cur_min_str = "0" + current_minute;
                    } else {
                        cur_min_str = current_minute + "";
                    }
                    StringBuffer sb = new StringBuffer();
                    String long_current = sb.append(cur_hours_str).append(cur_min_str).toString();

                    /*if (dailyTime.equals(short_current)
                            || dailyTime.equals(long_current)) {*/
                        if ("PS".equalsIgnoreCase(batch.getBatch_id())) {
                            try {
                                G_BIL_P02 gbill02 = new G_BIL_P02();
                                gbill02.setQueryDAO(queryDAO);
                                gbill02.setUpdateDAO(updateDAO);
                                gbill02.setRadiusQueryDAO(radiusQueryDAO);
                                gbill02.setRadiusUpdateDAO(radiusUpdateDAO);
                                HashMap<String, Object> outgbill02 = new HashMap<String, Object>();
                                gbill02.execute(uvo.getId_user(), outgbill02);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if ("WE".equalsIgnoreCase(batch.getBatch_id())) {
                            Thread e_mwf_ar1_th = new Thread(new E_MWF_AR1_Thread(queryDAO, updateDAO, uvo));
                            e_mwf_ar1_th.run();
                        }
                        if ("SD".equalsIgnoreCase(batch.getBatch_id())
                                && "1".equalsIgnoreCase(batch.getAlert_mode())) {
                            // check RUN_STATUS = 0
                            if ("0".equals(e_SET_S03Common.checkInProcessStatus("SD").getRetStatus())) {
                                Thread g_alt_p05_th =new Thread(new G_ALT_P05_Thread(queryDAO, updateDAO, uvo)) ;
                                g_alt_p05_th.run();
                            }
                        }
                   /* }*/
                }
            } else {
                // check time in table m_batch_mainternane is not null, if null --> skip
                if (batch.getDay_of_month() != null
                        && batch.getTime_hour() != null
                        && batch.getTime_minute() != null) {
                    int schedule_day = Integer.parseInt(batch.getDay_of_month());
                    int schedule_hours = Integer.parseInt(batch.getTime_hour());
                    int schedule_min = Integer.parseInt(batch.getTime_minute());

                    // reset value of schedule day if > max day of current month.
                    if (schedule_day > max_day_of_curr_month) {
                        schedule_day = max_day_of_curr_month;
                    }

                    /**
                     * check and call process with their time come.
                     */
                    try {
                        esetRundate=current;
                        if (batch.getBatch_id().equalsIgnoreCase("CC")
                                && schedule_day == current_day
                                && schedule_hours == current_hours
                                && schedule_min == current_minute) {
                            if (batch.getAlert_mode().equalsIgnoreCase("1")) {
                                // check RUN_STATUS = 0
                                if ("0".equals(e_SET_S03Common.checkInProcessStatus("CC").getRetStatus())) {
                                    Thread g_alt_p04_th = new Thread(new G_ALT_P04_Thread(queryDAO, updateDAO, uvo));
                                    g_alt_p04_th.run();
                                }
                            }
                        } else if (batch.getBatch_id().equalsIgnoreCase("SD")
                                && schedule_day == current_day
                                && schedule_hours == current_hours
                                && schedule_min == current_minute) {
                            if (batch.getAlert_mode().equalsIgnoreCase("1")) {
                                // check RUN_STATUS = 0
                                if ("0".equals(e_SET_S03Common.checkInProcessStatus("SD").getRetStatus())) {
                                    Thread g_alt_p05_th = new Thread(new G_ALT_P05_Thread(queryDAO, updateDAO, uvo));
                                    g_alt_p05_th.run();
                                }
                            }
                        } else if (batch.getBatch_id().equalsIgnoreCase("GB")
                                && schedule_day == current_day
                                && schedule_hours == current_hours
                                && schedule_min == current_minute) {
                            // check RUN_STATUS = 0
                            if ("0".equals(e_SET_S03Common.checkInProcessStatus("GB").getRetStatus())) {
                                Thread g_bill_p01_th = new Thread(
                                        new G_BIL_P01_Thread(queryDAO, updateDAO, uvo, esetRundate));
                                g_bill_p01_th.run();
                            }
                        } else if (batch.getBatch_id().equalsIgnoreCase("SA")
                                && schedule_day == current_day
                                && schedule_hours == current_hours
                                && schedule_min == current_minute) {
                            // check RUN_STATUS = 0
                            if ("0".equals(e_SET_S03Common.checkInProcessStatus("SA").getRetStatus())) {
                                Thread e_exp_f02_th = new Thread(
                                    new E_EXP_F02_Thread(queryDAO, updateDAO, uvo, esetRundate, batch.getStatement_month()));
                                e_exp_f02_th.run();
                            }
                        } else if (batch.getBatch_id().equalsIgnoreCase("AR")
                                && schedule_day == current_day
                                && schedule_hours == current_hours
                                && schedule_min == current_minute) {
                            // check RUN_STATUS = 0
                            if ("0".equals(e_SET_S03Common.checkInProcessStatus("AR").getRetStatus())) {
                                Thread e_exp_f01_th = new Thread(
                                    new E_EXP_F01_Thread(queryDAO, updateDAO, uvo,esetRundate));
                                e_exp_f01_th.run();
                            }
                        } else if ("CB".equalsIgnoreCase(batch.getBatch_id())
                            && schedule_day == current_day
                            && schedule_hours == current_hours
                            && schedule_min == current_minute) {
                            // check RUN_STATUS = 0
                            if ("0".equals(e_SET_S03Common.checkInProcessStatus("CB").getRetStatus())) {
                                Thread g_csb_p01_th = new Thread(new G_CSB_P01_Thread(queryDAO, updateDAO, uvo, batch.getAlert_mode()));
                                g_csb_p01_th.run();
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @param dateReq the dateReq to set
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
     * @param esetRundate the esetRundate to set
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
     * @return the radiusQueryDAO
     */
    public QueryDAO getRadiusQueryDAO() {
        return radiusQueryDAO;
    }

    /**
     * @param radiusQueryDAO the radiusQueryDAO to set
     */
    public void setRadiusQueryDAO(QueryDAO radiusQueryDAO) {
        this.radiusQueryDAO = radiusQueryDAO;
    }

    /**
     * @return the radiusUpdateDAO
     */
    public UpdateDAO getRadiusUpdateDAO() {
        return radiusUpdateDAO;
    }

    /**
     * @param radiusUpdateDAO the radiusUpdateDAO to set
     */
    public void setRadiusUpdateDAO(UpdateDAO radiusUpdateDAO) {
        this.radiusUpdateDAO = radiusUpdateDAO;
    }


}
