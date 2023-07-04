/**
 * @(#)G_BIL_P02.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.bean.T_SET_BATCH;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;

/**
 * Process To Update Service Status (Terminate)
 * 
 * @author bench
 */
public class G_BIL_P02 extends
        AbstractGlobalProcess<String, HashMap<String, Object>> {

    /**
     * BLogicMessages Errors
     */
    private BLogicMessages errors = new BLogicMessages();

    /**
     * BLogicMessages Messages
     */
    private BLogicMessages messages = new BLogicMessages();

    /**
     * radiusQueryDAO
     */
    protected QueryDAO radiusQueryDAO;

    /**
     * radiusUpdateDAO
     */
    protected UpdateDAO radiusUpdateDAO;
    /**
     * <div>msgResource</div>
     */
    private GlobalMessageResources msgResource;

    /**
     * Constructor.
     */
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
    
    public G_BIL_P02(){
    	// init resource
        this.msgResource = GlobalMessageResources.getInstance();
    }

    /**
     * Update plan status to PS7 when custom plan's PLAN_STATUS = 'PS3' and
     * SVC_END != NULL AND SVC_END <= sysdate.<br>
     * Call G-CPM-04 for ID_CUST_PLAN which contains "PS7" and other service
     * status.
     * 
     * @param idLogin
     *            Login User ID
     * @param outputDTO
     *            HashMap
     * @return GlobalProcessResult
     */
    @Override
    public final GlobalProcessResult execute(String idLogin,
            HashMap<String, Object> outputDTO) {
    	// #150
    	// #646 Start
    	T_SET_BATCH t_batch = new T_SET_BATCH();
        t_batch.setSTATUS("1");
        t_batch.setBATCH_TYPE("PS");
        // Call G_SET_P01
        G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
        int batchId = gsetp01.G_SET_P01_GetIdBatch(t_batch).getBatch_id();
        this.idBatch=batchId;
        if(batchId > 0){
        // #646 End
        	// Update plan status to PS7
            HashMap<String, Object> inputParam = new HashMap<String, Object>();
            String idLogin1 = "Batch Scheduler";
            inputParam.put("idLogin", idLogin1);
            List<String> idCustPlanList = new ArrayList<String>();
            List<Map<String, Object>> activeServiceList = this.queryDAO.executeForMapList("B_BIL.selectActiveService", null);
            if (activeServiceList!=null && 0<activeServiceList.size()) {
                for(Map<String, Object> activeService : activeServiceList) {
                    String idCustPlan = CommonUtils.toString(activeService.get("ID_CUST_PLAN"));
                    String idCustPlanGrp = CommonUtils.toString(activeService.get("ID_CUST_PLAN_GRP"));
                    String planStatus = CommonUtils.toString(activeService.get("PLAN_STATUS"));
                    // #646 Start
                    idCustPlanList.add(idCustPlan);
                    // #646 End
                    
                    Integer idAudit = CommonUtils.auditTrailBegin(idLogin1, BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CPM, idCustPlan, CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", planStatus), BillingSystemConstants.AUDIT_TRAIL_EDITED);
                    
                    inputParam.put("idCustPlanGrp", idCustPlanGrp);
                    inputParam.put("idAudit", idAudit);
            this.updateDAO.execute("B_BIL.updateServiceStatus", inputParam);

                        G_CPM_P04 gCPMP04 = new G_CPM_P04(queryDAO, updateDAO);
                    gCPMP04.setIdAudit(idAudit);
                        gCPMP04.execute(idCustPlan);
                        
                        List<Map<String, Object>> planBatchMappingServiceStatusList = this.queryDAO.executeForMapList("B_BIL.GET_BATCH_MAPPING_BY_ID_CUST_PLAN", idCustPlan);
                        if (planBatchMappingServiceStatusList!=null && 0<planBatchMappingServiceStatusList.size()) {
                            boolean allServiceIsTerminated = true;
                            for (Map<String, Object> map : planBatchMappingServiceStatusList) {
                                String onceServiceStatus = CommonUtils.toString(map.get("SERVICES_STATUS"));
                                if(!"PS7".equals(onceServiceStatus)) {
                                    allServiceIsTerminated = false;
                                    break;
                                }
                            }
                            if(allServiceIsTerminated){
                                Map<String, Object> subScriptionInfo = queryDAO.executeForMap("B_BIL.GET_SUBSCIPTION_BY_ID_CUST_PLAN", idCustPlan);
                                if (subScriptionInfo!=null) {
                                    //ACCESS_ACCOUNT
                                    String accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                                    RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                                    //select by PK
                                    RAD_USERS_T radUserT = radUserTUtil.selectByPK(accessAccount);
                                    if (radUserT!=null) {
                                        radUserTUtil.deleteByUserName(accessAccount);
                                    } else {
                                        continue;
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                // #646 Start
				StringBuilder idCustPlanBui = new StringBuilder();
				// idCustPlan
				if (idCustPlanList != null && idCustPlanList.size() > 0) {
					for (int i = 0; i < idCustPlanList.size() - 1; i++) {
						for (int j = idCustPlanList.size() - 1; j > i; j--) {
							if (idCustPlanList.get(j).equals(
									idCustPlanList.get(i))) {
								idCustPlanList.remove(j);
							}
						}
					}
				}
				for (String idCust : idCustPlanList) {
					idCustPlanBui.append(idCust);
					idCustPlanBui.append(",");
				}
                String idCustPlanMes = idCustPlanBui.substring(0, idCustPlanBui.toString().length()-1).toString();
                t_batch = new T_SET_BATCH();
                t_batch.setSTATUS("2");
                t_batch.setID_BATCH(Integer.toString(batchId));
                t_batch.setBATCH_TYPE("PS");
                t_batch.setMessage(this.msgResource.getMessage("bilp02.ERR2SC056", new Object[] {idCustPlanMes}));
                gsetp01.G_SET_P01_GetIdBatch(t_batch);
                }
            // #646 End
        }
        return (new GlobalProcessResult()).setErrors(errors).setMessages(
                messages);
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
