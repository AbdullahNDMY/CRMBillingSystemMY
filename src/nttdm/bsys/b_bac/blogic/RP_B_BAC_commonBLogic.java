/*
 * @(#)RP_B_BAC_commonBLogic.java
 */
package nttdm.bsys.b_bac.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.util.CommonUtils;

/**
 * commonBLogic class.
 * 
 * @author xycao
 */
public class RP_B_BAC_commonBLogic {
    
    /**
     * common method to update plan info list
     * 
     * @param listPlanInfo
     *            List
     * @return List
     */
    public List<HashMap<String, Object>> getNewListInfo(List<HashMap<String, Object>> listPlanInfo) {
       String idSubInfo = "";
       for(int i=0;i<listPlanInfo.size();i++){
           String subInfo = CommonUtils.toString(listPlanInfo.get(i).get("ID_SUB_INFO"));
           if(subInfo.equals(idSubInfo)){
               listPlanInfo.get(i).put("ID_SUB_INFO", null);
           }
           idSubInfo = subInfo;
       }
        return listPlanInfo;
    }
    /**
     * @param listPlanInfo
     *            List
     * @return List
     */
    public List<HashMap<String, Object>> getListInfo(List<HashMap<String, Object>> listPlanInfo) {
        List<HashMap<String, Object>> newListPlanInfo = new ArrayList<HashMap<String, Object>>();
        Map<String, Object> newSubIDMap = new HashMap<String, Object>();
        // new idCustPlan
        String idCustPlan = "";
        // idCustPlanGrp description set
        String idCustPlanGrp = "";
        if (listPlanInfo != null && listPlanInfo.size() != 0) {
            for (int i = 0; i < listPlanInfo.size(); i++) {
                HashMap<String, Object> subIDMap = (HashMap<String, Object>) listPlanInfo.get(i);
                if (i == 0) {
                    idCustPlan = subIDMap.get("ID_CUST_PLAN").toString();
                    idCustPlanGrp = subIDMap.get("ID_CUST_PLAN_GRP").toString();
                    newSubIDMap.put("ID_CUST_PLAN_GRP", subIDMap.get("ID_CUST_PLAN_GRP").toString());
                    newSubIDMap.put("AUTO_RENEW", subIDMap.get("AUTO_RENEW"));
                    newSubIDMap.put("ID_SUB_INFO", subIDMap.get("ID_SUB_INFO"));
                    newSubIDMap.put("BILL_DESC_DISPLAY", (String) subIDMap.get("BILL_DESC_DISPLAY"));
                    newSubIDMap.put("IS_RECURRING", subIDMap.get("IS_RECURRING"));
                    newSubIDMap.put("SERVICES_STATUS", subIDMap.get("SERVICES_STATUS"));
                    newSubIDMap.put("BILLING_STATUS", subIDMap.get("BILLING_STATUS"));
                    newSubIDMap.put("BILL_FROM", subIDMap.get("BILL_FROM"));
                    newSubIDMap.put("BILL_TO", subIDMap.get("BILL_TO"));
                    newSubIDMap.put("SVC_END", subIDMap.get("SVC_END"));
                    newSubIDMap.put("ID_BILL_ACCOUNT", subIDMap.get("ID_BILL_ACCOUNT"));
                    newSubIDMap.put("BILL_CURRENCY", subIDMap.get("BILL_CURRENCY"));
                    newSubIDMap.put("LINE_DISPLAY", "1");
                    newSubIDMap.put("ID_CUST_PLAN", idCustPlan);
                    newListPlanInfo.add((HashMap<String, Object>) newSubIDMap);
                    newSubIDMap = new HashMap<String, Object>();
                    newSubIDMap.put("SVC_GRP_NAME", subIDMap.get("SVC_GRP_NAME"));
                    newSubIDMap.put("SVC_DESC", subIDMap.get("SVC_DESC"));
                    newSubIDMap.put("PLAN_DESC_DISPLAY", subIDMap.get("PLAN_DESC_DISPLAY"));
                } else {
                    if (subIDMap.get("ID_CUST_PLAN").toString().equals(idCustPlan)) {
                        newSubIDMap.put("LINE_DISPLAY", "0");
                        newListPlanInfo.add((HashMap<String, Object>) newSubIDMap);
                        if (!subIDMap.get("ID_CUST_PLAN_GRP").toString().equals(idCustPlanGrp)) {
                            newSubIDMap = new HashMap<String, Object>();
                            newSubIDMap.put("LINE_DISPLAY", "3");
                            newSubIDMap.put("BILL_DESC_DISPLAY", (String) subIDMap.get("BILL_DESC_DISPLAY"));
                            newSubIDMap.put("IS_RECURRING", subIDMap.get("IS_RECURRING"));
                            newSubIDMap.put("SERVICES_STATUS", subIDMap.get("SERVICES_STATUS"));
                            newSubIDMap.put("BILLING_STATUS", subIDMap.get("BILLING_STATUS"));
                            newSubIDMap.put("BILL_FROM", subIDMap.get("BILL_FROM"));
                            newSubIDMap.put("BILL_TO", subIDMap.get("BILL_TO"));
                            newSubIDMap.put("SVC_END", subIDMap.get("SVC_END"));
                            newSubIDMap.put("AUTO_RENEW", subIDMap.get("AUTO_RENEW"));
                            newListPlanInfo.add((HashMap<String, Object>) newSubIDMap);
                        }
                        newSubIDMap = new HashMap<String, Object>();

                        newSubIDMap.put("SVC_GRP_NAME", subIDMap.get("SVC_GRP_NAME"));
                        newSubIDMap.put("SVC_DESC", subIDMap.get("SVC_DESC"));
                        newSubIDMap.put("PLAN_DESC_DISPLAY", subIDMap.get("PLAN_DESC_DISPLAY"));
                    } else {
                        newSubIDMap.put("LINE_DISPLAY", "2");
                        newListPlanInfo.add((HashMap<String, Object>) newSubIDMap);
                        idCustPlan = subIDMap.get("ID_CUST_PLAN").toString();
                        newSubIDMap = new HashMap<String, Object>();
                        newSubIDMap.put("ID_CUST_PLAN_GRP", subIDMap.get("ID_CUST_PLAN_GRP").toString());
                        newSubIDMap.put("AUTO_RENEW", subIDMap.get("AUTO_RENEW"));
                        newSubIDMap.put("ID_SUB_INFO", subIDMap.get("ID_SUB_INFO"));
                        newSubIDMap.put("BILL_DESC_DISPLAY", (String) subIDMap.get("BILL_DESC_DISPLAY"));
                        newSubIDMap.put("IS_RECURRING", subIDMap.get("IS_RECURRING"));
                        newSubIDMap.put("SERVICES_STATUS", subIDMap.get("SERVICES_STATUS"));
                        newSubIDMap.put("BILLING_STATUS", subIDMap.get("BILLING_STATUS"));
                        newSubIDMap.put("BILL_FROM", subIDMap.get("BILL_FROM"));
                        newSubIDMap.put("BILL_TO", subIDMap.get("BILL_TO"));
                        newSubIDMap.put("SVC_END", subIDMap.get("SVC_END"));
                        newSubIDMap.put("ID_BILL_ACCOUNT", subIDMap.get("ID_BILL_ACCOUNT"));
                        newSubIDMap.put("BILL_CURRENCY", subIDMap.get("BILL_CURRENCY"));
                        newSubIDMap.put("LINE_DISPLAY", "1");
                        newSubIDMap.put("ID_CUST_PLAN", idCustPlan);
                        newListPlanInfo.add((HashMap<String, Object>) newSubIDMap);
                        newSubIDMap = new HashMap<String, Object>();
                        newSubIDMap.put("SVC_GRP_NAME", subIDMap.get("SVC_GRP_NAME"));
                        newSubIDMap.put("SVC_DESC", subIDMap.get("SVC_DESC"));
                        newSubIDMap.put("PLAN_DESC_DISPLAY", subIDMap.get("PLAN_DESC_DISPLAY"));
                    }
                    idCustPlanGrp = subIDMap.get("ID_CUST_PLAN_GRP").toString();
                }
            }
            newSubIDMap.put("LINE_DISPLAY", "2");
            newListPlanInfo.add((HashMap<String, Object>) newSubIDMap);
        }

        return newListPlanInfo;
    }
}
