/**
 * @(#)G_CPM_P04.java
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Update Plan Status From Service Status.
 * 
 * @author NTTDM
 * @version 1.0
 */
public class G_CPM_P04 {

    private Integer idAudit;
    
    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;

    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO;

    /**
     * radiusUpdateDAO
     */
    protected UpdateDAO radiusUpdateDAO;

    /**
     * radiusQueryDAO
     */
    protected QueryDAO radiusQueryDAO;

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

    public Integer getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(Integer idAudit) {
        this.idAudit = idAudit;
    }
    
    /**
     * @param radiusUpdateDAO the radiusUpdateDAO to set
     */
    public void setRadiusUpdateDAO(UpdateDAO radiusUpdateDAO) {
    
        this.radiusUpdateDAO = radiusUpdateDAO;
    }
    
    /** SELECT_SQL_1_0 */
    private static final String SELECT_SQL_1_0 = "SELECT.BSYS.G_CPM_P04.SQL001";

    /** UPDATE_SQL_1_0 */
    private static final String UPDATE_SQL_1_0 = "UPDATE.BSYS.G_CPM_P04.SQL001";

    /** Status PS0: Cancelled */
    private static final String PS0 = "PS0";
    
    /** Status PS1: Draft */
    private static final String PS1 = "PS1";

    /** Status PS2: In-Approval */
    private static final String PS2 = "PS2";

    /** Status PS3: Active */
    private static final String PS3 = "PS3";

    /** Status PS5: One Time */
    private static final String PS5 = "PS5";

    /** Status PS6: Suspended */
    private static final String PS6 = "PS6";

    /** Status PS7: Terminated */
    private static final String PS7 = "PS7";
    
    /** Status PS8: Rejected */
    private static final String PS8 = "PS8";

    /**
     * @param queryDAO QueryDAO
     * @param updateDAO UpdateDAO
     */
    public G_CPM_P04(QueryDAO queryDAO, UpdateDAO updateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
    }

    /**
     * Update Plan Status From Service Status.
     * 
     * @param idCustPlan ID_CUST_PLAN
     */
    public void execute(String idCustPlan) {
        // Select status from t_cust_plan_d where id_cust_plan =$ID_CUST_PLAN
        List<String> listStatus = queryDAO.executeForObjectList(SELECT_SQL_1_0, idCustPlan);
        if (!CollectionUtils.isEmpty(listStatus)) {
            // total of records
            int nCount = listStatus.size();
            // Count of each status
            int nPs0 = 0;
            int nPs1 = 0;
            int nPs2 = 0;
            int nPs3 = 0;
            int nPs5 = 0;
            int nPs6 = 0;
            int nPs8 = 0;
            
            // Count each status
            for (String status : listStatus) {
                if (PS0.equals(status)) {
                    nPs0++;
                } else if (PS1.equals(status)) {
                    nPs1++;
                } else if (PS2.equals(status)) {
                    nPs2++;
                } else if (PS3.equals(status)) {
                    nPs3++;
                } else if (PS5.equals(status)) {
                    nPs5++;
                } else if (PS6.equals(status)) {
                    nPs6++;
                } else if (PS8.equals(status)) {
                    nPs8++;
                }
            }
            
            // All service_status = "PS8" or All service_status = "PS0" or All service_status = "PS1" or 
            // All service_status = "PS2" or All service_status = "PS5"(PS5 no longer used as a service status)
            if (nPs8 == nCount || nPs0 == nCount || nPs1 == nCount || nPs2 == nCount || nPs5 == nCount) {
                return;
            } else if (0 < nPs3) {
                // Any of Service status = PS3, update t_cust_plan_h.plan_status ="PS3"
                updateStatus(PS3, idCustPlan);
            } else if (0 < nPs6) {
                // Any of Service status = PS6, update t_cust_plan_h.plan_status ="PS6"
                updateStatus(PS6, idCustPlan);
            } else {
                // update t_cust_plan_h.plan_status ="PS7"
                updateStatus(PS7, idCustPlan);
                
                // DELETE RADUIS RECORD IF EXIST
                B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
                String userName = CommonUtils.toString(util.getAccessAccount(idCustPlan).get("ACCESS_ACCOUNT"));
                if (!userName.equals("") && util.isExistPlanGrp(idCustPlan)) {
                    ApplicationContext context = ApplicationContextProvider.getApplicationContext();
                    this.radiusQueryDAO = (QueryDAO)context.getBean("radiusQueryDAO");
                    this.radiusUpdateDAO = (UpdateDAO)context.getBean("radiusUpdateDAO");
                    RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                    if (radUserTUtil.isExistUserName(userName)) {
                        radUserTUtil.deleteByUserName(userName);
                    }
                }
            }
        }
    }

    /**
     * @param status
     * String
     * @param idCustPlan
     * String
     */
    private void updateStatus(String status, String idCustPlan) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("idCustPlan", idCustPlan);
        hashMap.put("planStatus", status);
        hashMap.put("idAudit", this.idAudit);
        
        updateDAO.execute(UPDATE_SQL_1_0, hashMap);
        
        if(idAudit!=null) {
            Map<String, Object> tCustPlanHMap = queryDAO.executeForMap("SELECT.BSYS.G_CPM_P04.SQL002", idCustPlan);
            String afterUpdatePlanStatus = CommonUtils.toString(tCustPlanHMap.get("PLAN_STATUS"));
            Map<String, Object> sqlParam = new HashMap<String, Object>();
            sqlParam.put("idAudit", idAudit);
            sqlParam.put("status", afterUpdatePlanStatus);
            updateDAO.execute("UPDATE.BSYS.G_CPM_P04.SQL002", sqlParam);
            
            sqlParam = new HashMap<String, Object>();
            sqlParam.put("idAudit", idAudit);
            sqlParam.put("tableName", "T_CUST_PLAN_H");
            sqlParam.put("atField", "PLAN_STATUS");
            sqlParam.put("newData", afterUpdatePlanStatus);
            updateDAO.execute("UPDATE.BSYS.G_CPM_P04.SQL003", sqlParam);
        }
    }
}
