/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_PBM
 * SERVICE NAME   : M_PBM_S01
 * FUNCTION       : PlanBatchMappingSaveBLogic
 * FILE NAME      : M_PBMS01SaveBLogic.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.m_pbm.blogic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_pbm.dto.M_PBMS01SaveInput;
import nttdm.bsys.m_pbm.dto.M_PBMS01SaveOutput;

/**
 * M_PBMS01SaveBLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
 * 
 * @author NTTData Vietnam
 * @version 1.0
 */
public class M_PBMS01SaveBLogic extends AbstractM_PBMS01SaveBLogic {

    /**
     * 
     * @param param
     * @return ビジネスロジチE��の実行結果、BLogicResultインスタンス、E
     */
    private static final String UPDATE_40 = "UPDATE.40";
    private static final String UPDATE_51_AD_DU = "UPDATE.51_AD_DU";
    private static final String UPDATE_51_IP = "UPDATE.512_IP";
    private static final String UPDATE_51_NH = "UPDATE.51_MH";
    private static final String DELETE_52 = "DELETE.52";
    private static final String SELECT_SQL_Id_Plan_Batch = "SELECT.PLAN_BATCH";
    private static final String DELETE_BATCH_PLAN_GRP = "DELETE.BATCH_PLAN_GRP";
    private static final String SELECT_SQL_Cust_Plan_Group = "SELECT.CUST_PLAN_GROUP";
    private String row_delim = ";";
    private String field_delim = ",";

    public BLogicResult execute(M_PBMS01SaveInput param) {
        BLogicResult result = new BLogicResult();
        M_PBMS01SaveOutput outputDTO = new M_PBMS01SaveOutput();
        String mode = param.getMode();
        String errorMode = "0";
        String message = "ERR2SC003";
        String batchId = param.getId_batch_new();
        String userId = BillingSystemConstants.USER_SYSTEM_DEFAULT;
        userId = param.getUvo().getId_user();
        
        if (mode.equalsIgnoreCase("new")) {
            String checkResult = checkInput(param);
            if (checkResult.equals("")) {
                String usageBase = "0";
                String uom = "MIN";
                String codeValue = "0";
                java.util.HashMap<String, Object> updateParam = new HashMap<String, Object>();
                // push common param
                updateParam.put("ID_PLAN", param.getPlan_id_new());
                updateParam.put("BATCH_ID", param.getId_batch_new());
                updateParam.put("ID_LOGIN", userId);
                // check batch id
                // extract data
                String[] updateRows = param.getNew_data().split(row_delim);
                for (int i = 0; i < updateRows.length; ++i) {
                    String[] newData = updateRows[i].split(field_delim);
                    updateParam.put("ID_PLAN_GRP", newData[0]);
                    if (batchId.equalsIgnoreCase("IP")) {
                        codeValue = (!newData[1].equals("undefined")) ? newData[1] : "0";
                    } else if (batchId.equalsIgnoreCase("AD") || batchId.equalsIgnoreCase("DU")) {
                        // usageBase = newData[1];
                        usageBase = (!newData[1].equals("undefined")) ? newData[1] : "0";
                        uom = (!newData[2].equals("undefined")) ? newData[2] : "MIN";
                    } else if (batchId.equalsIgnoreCase("MH")) {
                        uom = "SPM";
                        codeValue = "1";
                    }
                    updateParam.put("USAGE_BASE", CommonUtils.toInteger(usageBase));
                    updateParam.put("UOM", uom);
                    updateParam.put("CODE_VALUE", CommonUtils.toInteger(codeValue));
                    message = "ERR2SC003";
                    int isSuccess = 0;
                    /**
                     * Audit Trail
                     */
                    Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                            BillingSystemConstants.SUB_MODULE_M_PBM, newData[0] + ":" + batchId, null,
                            BillingSystemConstants.AUDIT_TRAIL_CREATED);
                    updateParam.put("ID_AUDIT", idAudit);
                    try {
                        isSuccess = updateDAO.execute(UPDATE_40, updateParam);
                    } catch (Exception ex) {
                        message = "ERR2SC004";
                        ex.printStackTrace();
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                        break;
                    }
                    CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                }
                
                // Option service mapping 
                if ("MH".equalsIgnoreCase(param.getId_batch_new())) {
                    updateParam.put("USAGE_BASE", 0);
                    
                    // additional Mail account
                    if (!CommonUtils.isEmpty(param.getCboOptSvcAMA())) {
                        String idPlanGrp = param.getCboOptSvcAMA();
                        codeValue = "1".equals(param.getChkCheckAMA()) ? "1" : "0";
                        
                        Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_CREATED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "AMA");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }
    
                    // Mailbox(Qty)
                    if (!CommonUtils.isEmpty(param.getCboOptSvcAMQ())) {
                        String idPlanGrp = param.getCboOptSvcAMQ();
                        codeValue = "1".equals(param.getChkCheckAMQ()) ? "1" : "0";
     
                        Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_CREATED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "AMQ");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }
                    
                    // Virus Scan
                    if (!CommonUtils.isEmpty(param.getCboOptSvcVRS())) {
                        String idPlanGrp = param.getCboOptSvcVRS();
                        codeValue = "1".equals(param.getChkCheckVRS()) ? "1" : "0";
     
                        Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_CREATED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "VRS");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }

                    // Anti Spam
                    if (!CommonUtils.isEmpty(param.getCboOptSvcASP())) {
                        String idPlanGrp = param.getCboOptSvcASP();
                        codeValue = "1".equals(param.getChkCheckASP()) ? "1" : "0"; 
     
                        Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_CREATED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "ASP");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }

                    // Junk Management
                    if (!CommonUtils.isEmpty(param.getCboOptSvcJMG())) {
                        String idPlanGrp = param.getCboOptSvcJMG();
                        codeValue = "1".equals(param.getChkCheckJMG()) ? "1" : "0"; 
     
                        Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_CREATED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "JMG");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }
                }
                mode = "search";
            } else {
                message = checkResult;
                errorMode = "1";
                mode = "new";
                batchId = param.getId_batch();
            }
            outputDTO.setId_batch_new(param.getId_batch_new());
            outputDTO.setId_batch(batchId);
            outputDTO.setPlan_id_new(param.getPlan_id_new());
            outputDTO.setPlan_id(param.getPlan_id_new());
            outputDTO.setMode(mode);
            outputDTO.setErrorMode(errorMode);
            
        } else if (mode.equalsIgnoreCase("edit")) {
            List<Map<String, Object>> plan_batch = null;
            batchId = param.getId_batch();
            plan_batch = queryDAO.executeForObjectList(SELECT_SQL_Id_Plan_Batch, batchId);
            // get data
            String checkResult = checkInput(param);
            if (checkResult.equals("")) {
                String[] rows = param.getEdit_data().split(row_delim);
                Integer idAudit = null;
                for (int i = 0; i < rows.length; ++i) {
                    String[] editData = rows[i].split(field_delim);
                    String idPlanGrp = "";
                    if (editData[0].equalsIgnoreCase("unchecked")) {
                        // delete
                        idPlanGrp = editData[1];
                        /**
                         * Audit Trail
                         */
                        idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        try {
                            Map<String, Object> sqlParam = new HashMap<String, Object>();
                            sqlParam.put("idPlanGrp", idPlanGrp);
                            sqlParam.put("batchId", batchId);
                            updateDAO.execute(DELETE_52, sqlParam);
                        } catch (Exception e) {
                            e.printStackTrace();
                            message = "ERR2SC004";
                            CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                            break;
                        }
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    } else {
                        // if(batchId.equalsIgnoreCase("CC")) continue;
                        // add
                        idPlanGrp = editData[1];
                        java.util.HashMap<String, Object> updateParam = new HashMap<String, Object>();
                        updateParam.put("ID_LOGIN", userId);
                        updateParam.put("ID_PLAN_GRP", idPlanGrp);
                        /**
                         * Audit Trail
                         */
                        idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        updateParam.put("ID_AUDIT", idAudit);

                        if (this.checkExist(idPlanGrp, plan_batch)) {
                            // update
                            try {
                                if (batchId.equalsIgnoreCase("IP")) {
                                    String code_value = (!editData[2].equals("undefined")) ? editData[2] : "0";
                                    updateParam.put("CODE_VALUE", CommonUtils.toInteger(code_value));
                                    updateParam.put("batchId", batchId);
                                    updateDAO.execute(UPDATE_51_IP, updateParam);
                                } else if (batchId.equalsIgnoreCase("MH")){
                                    String code_value = "1";
                                    String uom = "SPM";
                                    String usageBase = "0";
                                    updateParam.put("CODE_VALUE", CommonUtils.toInteger(code_value));
                                    updateParam.put("UOM", uom);
                                    updateParam.put("USAGE_BASE", CommonUtils.toInteger(usageBase));
                                    updateParam.put("batchId", batchId);
                                    updateDAO.execute(UPDATE_51_NH, updateParam);
                                } else {
                                    String uom = (!editData[3].equals("undefined")) ? editData[3] : "MIN";
                                    String usageBase = (!editData[2].equals("undefined")) ? editData[2] : "0";
                                    updateParam.put("UOM", uom);
                                    updateParam.put("USAGE_BASE", CommonUtils.toInteger(usageBase));
                                    updateParam.put("batchId", batchId);
                                    updateDAO.execute(UPDATE_51_AD_DU, updateParam);
                                }
                            } catch (Exception ex) {
                                message = "ERR2SC004";
                                CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                                break;
                            }
                            CommonUtils.auditTrailEnd(idAudit); // End Audit Trail
                        } else {
                            String usageBase = "0";
                            String uom = "MIN";
                            String codeValue = "0";
                            // param.getUvo().getId_user();
                            updateParam = new HashMap<String, Object>();
                            // push common param
                            updateParam.put("ID_PLAN", param.getPlan_id_new());
                            updateParam.put("BATCH_ID", param.getId_batch());
                            updateParam.put("ID_LOGIN", userId);
                            updateParam.put("ID_PLAN_GRP", editData[1]);
                            if (batchId.equalsIgnoreCase("IP")) {
                                codeValue = (!editData[2].equals("undefined")) ? editData[2] : "0";
                            } else if (batchId.equalsIgnoreCase("AD") || batchId.equalsIgnoreCase("DU")) {
                                uom = (!editData[3].equals("undefined")) ? editData[3] : "MIN";
                                usageBase = (!editData[2].equals("undefined")) ? editData[2] : "0";
                            } else if (batchId.equalsIgnoreCase("MH")) {
                                codeValue = "1";
                                uom = "SPM";
                                usageBase = "0";
                            }
                            updateParam.put("USAGE_BASE", usageBase);
                            updateParam.put("UOM", uom);
                            updateParam.put("CODE_VALUE", codeValue);
                            message = "ERR2SC003";
                            int iSuccess = 0;
                            try {
                                iSuccess = updateDAO.execute(UPDATE_40, updateParam);
                            } catch (Exception ex) {
                                message = "ERR2SC004";
                                break;
                            }
                        }
                    }
                }

//                HashMap<String, Object> selParam = new HashMap<String, Object>();
//                selParam.put("id_plan", param.getPlan_id_new());
//                selParam.put("batchId", batchId);
//                List<Map<String, Object>> custPlanGroup = queryDAO.executeForMapList(SELECT_SQL_Cust_Plan_Group, selParam);

                if (batchId.equalsIgnoreCase("MH")) {
                    // Delete Existing Option service mapping  
                    Map<String, Object> sqlParam = new HashMap<String, Object>();
                    sqlParam.put("id_plan", param.getPlan_id_new());
                    sqlParam.put("batchId", batchId);
                    updateDAO.execute(DELETE_BATCH_PLAN_GRP, sqlParam);

                    HashMap<String, Object> updateParam = new HashMap<String, Object>();
                    String usageBase = "0";
                    String codeValue = "0";
                    updateParam.put("ID_LOGIN", userId);
                    updateParam.put("ID_PLAN", param.getPlan_id_new());
                    updateParam.put("BATCH_ID", param.getId_batch());
                    updateParam.put("USAGE_BASE", CommonUtils.toInteger(usageBase));
                    
                    // additional Mail account
                    if (!CommonUtils.isEmpty(param.getCboOptSvcAMA())) {
                        String idPlanGrp = param.getCboOptSvcAMA();
                        codeValue = "1".equals(param.getChkCheckAMA()) ? "1" : "0";
                        
                        idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "AMA");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }
    
                    // Mailbox(Qty)
                    if (!CommonUtils.isEmpty(param.getCboOptSvcAMQ())) {
                        String idPlanGrp = param.getCboOptSvcAMQ();
                        codeValue = "1".equals(param.getChkCheckAMQ()) ? "1" : "0";
     
                        idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "AMQ");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }
                    
                    // Virus Scan
                    if (!CommonUtils.isEmpty(param.getCboOptSvcVRS())) {
                        String idPlanGrp = param.getCboOptSvcVRS();
                        codeValue = "1".equals(param.getChkCheckVRS()) ? "1" : "0";
     
                        idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "VRS");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }

                    // Anti Spam
                    if (!CommonUtils.isEmpty(param.getCboOptSvcASP())) {
                        String idPlanGrp = param.getCboOptSvcASP();
                        codeValue = "1".equals(param.getChkCheckASP()) ? "1" : "0"; 
     
                        idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "ASP");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }

                    // Junk Management
                    if (!CommonUtils.isEmpty(param.getCboOptSvcJMG())) {
                        String idPlanGrp = param.getCboOptSvcJMG();
                        codeValue = "1".equals(param.getChkCheckJMG()) ? "1" : "0"; 
     
                        idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
                                BillingSystemConstants.SUB_MODULE_M_PBM, idPlanGrp + ":" + batchId, null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        
                        updateParam.put("ID_AUDIT", idAudit);
                        updateParam.put("ID_PLAN_GRP",idPlanGrp);
                        updateParam.put("UOM", "JMG");
                        updateParam.put("CODE_VALUE", codeValue);
                        updateDAO.execute(UPDATE_40, updateParam);
    
                        CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
                    }
                }
                
                mode = "search";
            } else {
                message = checkResult;
                mode = "edit";
                errorMode = "2";
            }
            outputDTO.setMode(mode);
            outputDTO.setErrorMode(errorMode);
            outputDTO.setId_batch(batchId);
            outputDTO.setId_batch_new(param.getId_batch_new());
            outputDTO.setPlan_id_new(param.getPlan_id_new());
            outputDTO.setPlan_id(param.getPlan_id());
        }
        
        outputDTO.setMessage(message);
        outputDTO.setNew_data(param.getNew_data());
        outputDTO.setEdit_data(param.getEdit_data());
        outputDTO.setCboOptSvcAMA(param.getCboOptSvcAMA());
        outputDTO.setChkCheckAMA(param.getChkCheckAMA());
        outputDTO.setCboOptSvcAMQ(param.getCboOptSvcAMQ());
        outputDTO.setChkCheckAMQ(param.getChkCheckAMQ());
        outputDTO.setCboOptSvcVRS(param.getCboOptSvcVRS());
        outputDTO.setChkCheckVRS(param.getChkCheckVRS());
        outputDTO.setCboOptSvcASP(param.getCboOptSvcASP());
        outputDTO.setChkCheckASP(param.getChkCheckASP());
        outputDTO.setCboOptSvcJMG(param.getCboOptSvcJMG());
        outputDTO.setChkCheckJMG(param.getChkCheckJMG());
        
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }

    private boolean checkExist(String idPlanGrp, List<Map<String, Object>> list) {
        Iterator<Map<String, Object>> it = list.iterator();
        while (it.hasNext()) {
            String tmp = it.next().get("ID_PLAN_GRP").toString();
            if (idPlanGrp.equalsIgnoreCase(tmp)) {
                return true;
            }
        }
        return false;
    }
    
    private String checkInput(M_PBMS01SaveInput param) {
        String message = "";

        String mode = CommonUtils.toString(param.getMode());
        String idBatchNew = CommonUtils.toString(param.getId_batch_new());
        String idBatch = CommonUtils.toString(param.getId_batch());
        String data = CommonUtils.toString(param.getNew_data());
        String editData = CommonUtils.toString(param.getEdit_data());
        
        // Sub Plan must be selected.
        if ("new".equalsIgnoreCase(mode)) {
            if (data.equals("")) {
                message = "errors.ERR1SC033";
            }
        } else if ("edit".equalsIgnoreCase(mode)) {
            int count = 0;
            String[] rows = editData.split(row_delim);
            for (int i = 0; i < rows.length; ++i) {
                String[] editItems = rows[i].split(field_delim);
                if (editItems[0].equalsIgnoreCase("unchecked")) {
                    count++;
                }
            }
            
            if (rows.length == 0 || rows.length == count) {
                message = "errors.ERR1SC033";
            }
        }
        
        if ("".equals(message)){
            // Mail Hosting: Option Service Mapping Check.
            if (("new".equalsIgnoreCase(mode) && "MH".equalsIgnoreCase(idBatchNew))
                    || ("edit".equalsIgnoreCase(mode) && "MH".equalsIgnoreCase(idBatch))) {
                // Option service mapping 
                String idPlanGrpAMA = CommonUtils.toString(param.getCboOptSvcAMA());
                String idPlanGrpAMQ = CommonUtils.toString(param.getCboOptSvcAMQ());
                String idPlanGrpVRS = CommonUtils.toString(param.getCboOptSvcVRS());
                String idPlanGrpASP = CommonUtils.toString(param.getCboOptSvcASP());
                String idPlanGrpJMG = CommonUtils.toString(param.getCboOptSvcJMG());
                String[] idPlanGrps = {idPlanGrpAMA, idPlanGrpAMQ, idPlanGrpVRS, idPlanGrpASP, idPlanGrpJMG};
                
                for (int i=0; i<idPlanGrps.length; i++) {
                    for (int j=i+1; j<idPlanGrps.length; j++) {
                        if (!idPlanGrps[i].equals("") && idPlanGrps[i].equals(idPlanGrps[j])) {
                            message = "ERR2SC007";
                            break;
                        }
                    }
                }
                
                String chkAMA = CommonUtils.toString(param.getChkCheckAMA());
                String chkAMQ = CommonUtils.toString(param.getChkCheckAMQ());
                String chkVRS = CommonUtils.toString(param.getChkCheckVRS());
                String chkASP = CommonUtils.toString(param.getChkCheckASP());
                String chkJMG = CommonUtils.toString(param.getChkCheckJMG());
                String[] chks = {chkAMA, chkAMQ, chkVRS, chkASP, chkJMG};
                
                for (int i=0; i<chks.length; i++) {
                    if ("1".equals(chks[i]) && "".equals(idPlanGrps[i])) {
                        message = "ERR2SC008";
                        break;
                    }
                }
            }
        }
        
        return message;
    }
}