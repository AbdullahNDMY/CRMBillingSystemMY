/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_PBM
 * SERVICE NAME   : M_PBM_S01
 * FUNCTION       : PlanBatchMappingSearchBLogic
 * FILE NAME      : M_PBMS01SearchBLogic.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.m_pbm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_pbm.dto.M_PBMS01SearchInput;
import nttdm.bsys.m_pbm.dto.M_PBMS01SearchOutput;

import nttdm.bsys.m_pbm.blogic.AbstractM_PBMS01SearchBLogic;
import nttdm.bsys.util.ApplicationContextProvider;

/**
 * M_PBMS01SearchBLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
 * 
 * @author NTTData Vietnam
 * @version 1.0
 */
public class M_PBMS01SearchBLogic extends AbstractM_PBMS01SearchBLogic {
    private static final String SELECT_SQL_Search_11 = "SELECT.M_PBM.SQL11";
    private static final String SELECT_SQL_Search_12 = "SELECT.M_PBM.SQL12";
    private static final String SELECT_SQL_Search_13 = "SELECT.M_PBM.SQL13";
    private static final String SELECT_SQL_Search_14 = "SELECT.M_PBM.SQL14";
    private static final String SELECT_SQL_Search_Get_PlanID = "SELECT.PLANID";
    private static final String SELECT_SQL_Search_Get_Plan = "SELECT.PLANS";
    private static final String SELECT_SQL_Search_Get_SvcGrp = "SELECT.SVC_GRP";
    private static final String SELECT_SQL_32 = "SELECT.32";
    private static final String SELECT_SQL_33 = "SELECT.33";
    private static final String SELECT_SQL_34 = "SELECT.34";
    private static final String SELECT_SQL_Id_Plan_Group = "SELECT.ID_PLAN_GRP";
    private static final String SELECT_SQL_Id_Plan_Batch = "SELECT.PLAN_BATCH";
    private static final String SELECT_SQL_Cust_Plan_Group = "SELECT.CUST_PLAN_GROUP";
    private String row_delim = ";";
    private String field_delim = ",";
    /**
     * 
     * @param param
     * @return ビジネスロジチE��の実行結果、BLogicResultインスタンス、E
     */
    String bill_currency = "";

    @SuppressWarnings("unchecked")
    public BLogicResult execute(M_PBMS01SearchInput param) {
        M_PBMS01SearchOutput outputDTO = new M_PBMS01SearchOutput();
        BLogicResult result = new BLogicResult();
        
        String batchID = param.getId_batch();

        // get list of plan id
        String[] planIdList = queryDAO.executeForObjectArray(SELECT_SQL_Search_Get_PlanID, batchID, java.lang.String.class);
        String totalPlan = Integer.toString(planIdList.length);

        // maping between page number and plan id
        HashMap<String, String> idPageMap = new HashMap<String, String>(); // define a map

        String ip = "0"; // index property, current index
        String rp = "1"; // row property, row per page

        // map page number and plan_id
        for (int i = 0; i < planIdList.length; ++i) {
            idPageMap.put(String.valueOf(i), planIdList[i]);
        }
        if (param.getIp().length() != 0) {
            ip = String.valueOf(param.getIp());
        }

        String mode = param.getMode();
        List<Map<String, Object>> dbResult = null;
        List<Map<String, Object>> optSvcResult = null;
        List<Map<String, Object>> custPlanGroup = null;
        // get plan id
        String planid = idPageMap.get(ip);

        String errorMode = param.getErrorMode();
        
        
        // get search data
        if (mode.equalsIgnoreCase("search") || mode.equalsIgnoreCase("edit")) {
            HashMap<String, String> dbParams = new HashMap<String, String>();
            dbParams.put("id_batch", batchID);
            // forward from New screen
            if (param.getPlan_id_new() != null && !param.getPlan_id_new().trim().equals("")) {
                java.util.Set<String> keys = idPageMap.keySet();
                for (String key : keys) {
                    if (idPageMap.get(key).equals(param.getPlan_id_new())) {
                        ip = key;
                        planid = idPageMap.get(ip);
                        break;
                    }
                }
                // planid = param.getPlan_id_new();
            }
            // forward from Delete screen
            if (!idPageMap.containsKey(ip)) {
                int previous = idPageMap.size() - 1;
                ip = (previous >= 0 ? previous : 0) + "";// get last element
                planid = idPageMap.get(ip);
            }
            //
            dbParams.put("id_plan", planid);

            if (batchID.equalsIgnoreCase("DU") || batchID.equalsIgnoreCase("AD")) {
                dbResult = queryDAO.executeForMapList(SELECT_SQL_Search_11, dbParams);
            } else if (batchID.equalsIgnoreCase("CC") || batchID.equalsIgnoreCase("RD")) {
                dbResult = queryDAO.executeForMapList(SELECT_SQL_Search_12, dbParams);
                if (dbResult != null && dbResult.size() > 0) {
                    bill_currency = dbResult.get(0).get("BILL_CURRENCY").toString();
                }
            } else if (batchID.equalsIgnoreCase("MH")) {
                dbResult = queryDAO.executeForMapList(SELECT_SQL_Search_12, dbParams);
                if (dbResult != null && dbResult.size() > 0) {
                    bill_currency = dbResult.get(0).get("BILL_CURRENCY").toString();
                }
                optSvcResult = queryDAO.executeForMapList(SELECT_SQL_Search_14, dbParams);
                if (errorMode.equals("0") || errorMode.equals("")) {
                    for(Map<String, Object> map : optSvcResult) {
                        if("AMA".equalsIgnoreCase(CommonUtils.toString(map.get("UOM")))) {
                            outputDTO.setCboOptSvcAMA(CommonUtils.toString(map.get("ID_PLAN_GRP")));
                            outputDTO.setChkCheckAMA(CommonUtils.toString(map.get("CODE_VALUE")));
                        }
                        if("AMQ".equalsIgnoreCase(CommonUtils.toString(map.get("UOM")))) {
                            outputDTO.setCboOptSvcAMQ(CommonUtils.toString(map.get("ID_PLAN_GRP")));
                            outputDTO.setChkCheckAMQ(CommonUtils.toString(map.get("CODE_VALUE")));
                        }
                        if("VRS".equalsIgnoreCase(CommonUtils.toString(map.get("UOM")))) {
                            outputDTO.setCboOptSvcVRS(CommonUtils.toString(map.get("ID_PLAN_GRP")));
                            outputDTO.setChkCheckVRS(CommonUtils.toString(map.get("CODE_VALUE")));
                        }
                        if("ASP".equalsIgnoreCase(CommonUtils.toString(map.get("UOM")))) {
                            outputDTO.setCboOptSvcASP(CommonUtils.toString(map.get("ID_PLAN_GRP")));
                            outputDTO.setChkCheckASP(CommonUtils.toString(map.get("CODE_VALUE")));
                        }
                        if("JMG".equalsIgnoreCase(CommonUtils.toString(map.get("UOM")))) {
                            outputDTO.setCboOptSvcJMG(CommonUtils.toString(map.get("ID_PLAN_GRP")));
                            outputDTO.setChkCheckJMG(CommonUtils.toString(map.get("CODE_VALUE")));
                        }
                    }       
                } else {
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
                }
            } else {
                dbResult = queryDAO.executeForMapList(SELECT_SQL_Search_13, dbParams);
            }
            custPlanGroup = queryDAO.executeForMapList(SELECT_SQL_Cust_Plan_Group, null);

            outputDTO.setSearch_info(dbResult);
            outputDTO.setSearch_OptSvcinfo(optSvcResult);
            outputDTO.setBill_currency(bill_currency);
            outputDTO.setCust_plan_group(custPlanGroup);
        }

        // get plan name and plan description
        String planName = "";
        String planDesc = "";
        String batchName = "";

        if (dbResult != null && dbResult.size() > 0) {
            planName = dbResult.get(0).get("PLAN_NAME").toString();
            planDesc = dbResult.get(0).get("PLAN_DESC").toString();
        }
        
        // get plan data for new mode
        List<Map<String, Object>> cboPlan = null;
        cboPlan = queryDAO.executeForMapList(SELECT_SQL_Search_Get_Plan, null);
        // get data for new mode
        List<Map<String, Object>> newModeResult = null;
        List<Map<String, Object>> newModeOptSvcResult = null;
        
        String message = "";
        if (mode.equalsIgnoreCase("new")) {
            String idBatchNew = param.getId_batch_new();
            String idPlanNew = param.getPlan_id_new();
            
            HashMap<String, String> sqlParam = new HashMap<String, String>();
            sqlParam.put("plan_id_new", idPlanNew);
            sqlParam.put("id_batch", idBatchNew);
            List<Map<String, Object>> existIdPlanList = queryDAO.executeForMapList(SELECT_SQL_34, sqlParam);
            if(existIdPlanList!=null && 0<existIdPlanList.size()) {
                message = "errors.ERR1SC105";
            } else {
                if (param.getMode().equalsIgnoreCase("new") && idBatchNew.length() > 0 && !idBatchNew.equals("0")
                       && idPlanNew.length() > 0 && !idPlanNew.equalsIgnoreCase("0")) {
                    HashMap<String, String> newModeParams = new HashMap<String, String>();
                    newModeParams.put("plan_id_new", idPlanNew);
                    newModeParams.put("id_batch", idBatchNew);
                    newModeResult = queryDAO.executeForMapList(SELECT_SQL_32, newModeParams);
                    if ("MH".equalsIgnoreCase(idBatchNew)) {
                        newModeOptSvcResult = queryDAO.executeForMapList(SELECT_SQL_33, newModeParams);
    
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
                    }
                }
            }

            for (Map<String, Object> plan : cboPlan) {
                if (plan.get("ID_PLAN").toString().equals(param.getPlan_id_new())) {
                    planDesc = CommonUtils.toString(plan.get("PLAN_DESC"));
                    planName = CommonUtils.toString(plan.get("PLAN_NAME"));
                    bill_currency = CommonUtils.toString(plan.get("BILL_CURRENCY"));
                    break;
                }
            }
            // put in to output bean
            outputDTO.setNew_info(newModeResult);
            outputDTO.setNew_OptSvcinfo(newModeOptSvcResult);
            outputDTO.setBill_currency(bill_currency);
        }

        // get batch name
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        MappedCodeListLoader batchList = (MappedCodeListLoader) context.getBean("LIST_BATCH");
        Map batchMap = batchList.getCodeListMap();
        batchName = (String) batchMap.get(batchID);

        // get delete condition
        String delCondition2 = "1";
        if (planid != null) {
            Map<String, Object> planGroupInput = new HashMap<String, Object>();
            planGroupInput.put("id_plan", planid);
            planGroupInput.put("batchId", batchID);
            List<Map<String, Object>> planGroupOutput = queryDAO.executeForObjectList(SELECT_SQL_Cust_Plan_Group, planGroupInput);
            delCondition2 = Integer.toString(planGroupOutput.size());
        }

        // build UOM data
        ArrayList cboUOM = new ArrayList();
        Menu m = new Menu("HR", "Hours");
        cboUOM.add(m);
        m = new Menu("MIN", "Minutes");
        cboUOM.add(m);
        outputDTO.setCbo_uom(cboUOM);

        // get Service Group
        List<LabelValueBean> cboSvcGrp = queryDAO.executeForObjectList(SELECT_SQL_Search_Get_SvcGrp, null);

        // get id plan group for edit mode
        List<Map<String, Object>> idPlanGrpList = null;
        idPlanGrpList = queryDAO.executeForObjectList(SELECT_SQL_Id_Plan_Group, null);
        // build string of id plan group seperated by a comma
        Iterator<Map<String, Object>> it = idPlanGrpList.iterator();
        String grpID = "";
        while (it.hasNext()) {
            grpID += it.next().get("ID_PLAN_GRP").toString();
        }
        outputDTO.setId_plan_grp(grpID);

        // get plan batch map
        List<Map<String, Object>> planBatch = new ArrayList<Map<String,Object>>();
        
        if (mode.equalsIgnoreCase("edit")) {
            if (errorMode.equals("0") || errorMode.equals("")) {
                planBatch = queryDAO.executeForObjectList(SELECT_SQL_Id_Plan_Batch, batchID);
            } else {
                String[] rows = CommonUtils.toString(param.getEdit_data()).split(row_delim);
                for (int i = 0; i < rows.length; ++i) {
                    String[] editItems = rows[i].split(field_delim);
                    if (!editItems[0].equalsIgnoreCase("unchecked")) {
                        Map<String, Object> data = new HashMap<String, Object>();
                        data.put("ID_PLAN_GRP", editItems[1]);
                        planBatch.add(data);
                    }
                }
            }
            param.setNew_data("");
        } else if (mode.equalsIgnoreCase("new")) {
            String[] rows = CommonUtils.toString(param.getNew_data()).split(row_delim);
            for (int i = 0; i < rows.length; ++i) {
                String[] newData = rows[i].split(field_delim);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("ID_PLAN_GRP", newData[0]);
                planBatch.add(data);
            }
            param.setEdit_data("");
        } else if (mode.equalsIgnoreCase("search")) {
            param.setNew_data("");
            param.setEdit_data("");
            planBatch = queryDAO.executeForObjectList(SELECT_SQL_Id_Plan_Batch, batchID);
        }

        // populate to output bean
        outputDTO.setNew_data(param.getNew_data());
        outputDTO.setEdit_data(param.getEdit_data());
        outputDTO.setPlan_batch(planBatch);
        if(!CommonUtils.isEmpty(message)) {
            outputDTO.setMessage(message);
        } else {
            outputDTO.setMessage(param.getMessage());
        }
        outputDTO.setMode(param.getMode());
        outputDTO.setId_batch_new(param.getId_batch_new());
        outputDTO.setPlan_id_new(param.getPlan_id_new());
        outputDTO.setPlan_list(cboPlan);
        outputDTO.setEnable_delete(delCondition2);
        outputDTO.setPlan_name(planName);
        outputDTO.setBatch_name(batchName);
        outputDTO.setPlan_desc(planDesc);
        outputDTO.setPlan_id(planid);
        outputDTO.setTotal_plan(totalPlan);
        outputDTO.setId_batch(param.getId_batch());
        outputDTO.setCboSvcGrp(cboSvcGrp);
        outputDTO.setIp(ip);
        outputDTO.setRp(rp);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}

class Menu {
    /** Creates a new instance of Menu */
    public Menu() {
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String value;

    public Menu(String value, String name) {
        this.value = value;
        this.name = name;
    }

}