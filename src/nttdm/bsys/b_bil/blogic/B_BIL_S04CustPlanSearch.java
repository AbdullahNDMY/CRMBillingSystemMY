/*
 * @(#)B_BIL_S04CustPlanSearch.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * 
 * @author NTTD
 */
public class B_BIL_S04CustPlanSearch implements BLogic<Map<String, Object>> {

    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        HashMap<String, Object> output = new HashMap<String, Object>();
        
        // customer id
        String idCust = CommonUtils.toString(input.get("idCust"));
        output.put("idCust", idCust);
        output.put("isCpm", CommonUtils.toString(input.get("isCpm")));
        HashMap<String, Object> param = new HashMap<String, Object>();
        
        param.put("idCust", idCust);
        param.put("gdcBillingDescription", CommonUtils.toString(input.get("gdcBillingDescription")).trim());
        param.put("gdcItemDescription", CommonUtils.toString(input.get("gdcItemDescription")).trim());
        param.put("tbxSubscription", CommonUtils.toString(input.get("tbxSubscription")).trim());
        param.put("tbxServiceStatus", input.get("tbxServiceStatus"));
        param.put("jobNo", CommonUtils.toString(input.get("jobNo")).trim());
        
        String offset = queryDAO.executeForObject("B_BIL.getMGSetDInfo", input, String.class);
        if("BAC".equals(offset.trim())){
            param.put("billAcc", CommonUtils.toString(input.get("billAcc")));
            output.put("billAcc", CommonUtils.toString(input.get("billAcc")));
        }
        
        String custName = queryDAO.executeForObject("SELECT.B_BIL.S04.RESULT.IDCUSTNAME", input, String.class);
        output.put("custName", custName);
        
        String isdis = queryDAO.executeForObject("SELECT.B_BIL.S04.GETISDISPLAY", input, String.class);
        output.put("isdis", isdis);
        
        Object serviceStatus = input.get("tbxServiceStatus");
        output.put("tbxServiceStatus", serviceStatus);
        if(serviceStatus==null||((String[])serviceStatus).length<=0||!CommonUtils.toString(input.get("doSearch")).equals("Y")){
            if("0".equals(isdis)){
                output.put("tbxServiceStatus", new String[]{"PS3","PS6","PS7"});
            }else{
                output.put("tbxServiceStatus", new String[]{"PS2","PS3","PS6","PS7","PS8"});
            }      
            param.put("tbxServiceStatus", output.get("tbxServiceStatus"));
        }
        
        List<HashMap<String, Object>> id_cust_plan_h = new ArrayList<HashMap<String, Object>>();
        Integer row = new Integer(1);
        output.put("row", row);
        Integer startIndex = 0;
        try {
            startIndex = Integer.parseInt(CommonUtils.toString(input.get("startIndex")));
        } catch (Exception e) {
            startIndex = 0;
        }
        output.put("startIndex", startIndex);
        if(!CommonUtils.toString(input.get("doSearch")).equals("Y")){
            output.put("gdcBillingDescription", CommonUtils.toString(input.get("gdcBillingDescription")).trim());
            output.put("gdcItemDescription", CommonUtils.toString(input.get("gdcItemDescription")).trim());
            output.put("tbxSubscription", CommonUtils.toString(input.get("tbxSubscription")).trim());
            output.put("jobNo", CommonUtils.toString(input.get("jobNo")).trim());
            //output.put("totalCount", "&nbsp;&nbsp;&nbsp;&nbsp;");
            result.setResultObject(output);
            result.setResultString("success");
            return result;
        }
        // get total
        Integer totalRow = queryDAO.executeForObject("SELECT.B_BIL.S04.RESULT.TOTALCOUNT", param, Integer.class);
        output.put("totalCount", totalRow);
        if(totalRow.compareTo(new Integer(0))>0){
            // get the current idcustplanh
            List<String> ID_CUST_PLANs = queryDAO.executeForObjectList("SELECT.B_BIL.S04.RESULT.CUSTPLANID", param, startIndex, row);
            param.put("idCustPlan", ID_CUST_PLANs.get(0));
        
            List<Map<String, Object>> planList = queryDAO.executeForMapList("SELECT.B_BIL.S04.RESULT.CUSTPLANList", param);
        
            if(planList.size()>0){
                Map<String, Object> plan = planList.get(0); 
                output.put("subID", plan.get("ID_SUB_INFO"));
                
                input.put("idSet", "CPM_TRANS_TYPE");
                String type = queryDAO.executeForObject("SELECT.B_BIL.S04.G_SET.getValue", input, String.class);
                String TRANSACTION_TYPE = type.equals("1")? plan.get("TRANSACTION_TYPE").toString():"";
                output.put("trans", TRANSACTION_TYPE);
                
                output.put("billinst", plan.get("BILL_INSTRUCT"));
                output.put("cur", plan.get("BILL_CURRENCY"));
                
                input.put("idSet", "DESC_LENGTH");
                input.put("setSeq", "1");
                String length = queryDAO.executeForObject("SELECT.B_BIL.S04.G_SET.getValue", input, String.class);
                
                BigDecimal TAmount = BigDecimal.ZERO;
                BigDecimal SAmount = BigDecimal.ZERO;
                
                String ID_CUST_PLAN_GRP_pre = plan.get("ID_CUST_PLAN_GRP").toString();
                HashMap<String, Object> service = new HashMap<String, Object>();
                
                String bill_desc = plan.get("BILL_DESC").toString();
                if(bill_desc.length()>new Integer(length).intValue()){
                    bill_desc = bill_desc.substring(0, new Integer(length).intValue())+"...";
                }
                service.put("billDesc", bill_desc);
                
                service.put("serviceFrom", plan.get("SVC_START"));
                service.put("serviceTo", plan.get("SVC_END"));
                service.put("status", plan.get("SERVICES_STATUS"));
                
                List<Map<String, Object>> subPlanList = new ArrayList<Map<String,Object>>();
                Map<String, Object> subPlan = new HashMap<String, Object>();
                Boolean isAdd =false;
                
                for(int i =0;i<planList.size();i++){
                    plan  = planList.get(i);
                    String ID_CUST_PLAN_GRP_cur = plan.get("ID_CUST_PLAN_GRP").toString();
                    if(ID_CUST_PLAN_GRP_pre.equals(ID_CUST_PLAN_GRP_cur)){
                        isAdd =true;
                        subPlan.put("ID_CUST_PLAN_GRP", ID_CUST_PLAN_GRP_cur);
                        subPlan.put("ID_CUST_PLAN_LINK", plan.get("ID_CUST_PLAN_LINK"));
                        String item_desc = plan.get("ITEM_DESC").toString();
                        if(item_desc.length()>new Integer(length).intValue()*2){
                            item_desc = item_desc.substring(0, new Integer(length).intValue()*2)+"...";
                        }
                        subPlan.put("ItemDesc", item_desc);
                        subPlan.put("RateType", plan.get("RATE_TYPE"));
                        subPlan.put("RateMode", plan.get("RATE_MODE"));
                        subPlan.put("Quantity", plan.get("QUANTITY"));
                        subPlan.put("UPrice", plan.get("UNIT_PRICE"));
                        subPlan.put("Amount", plan.get("TOTAL_AMOUNT"));
                        subPlan.put("GST", CommonUtils.toString(plan.get("APPLY_GST")).equals("1")?"Yes":"");
                        SAmount = SAmount.add((BigDecimal)plan.get("TOTAL_AMOUNT"));
                        subPlanList.add(subPlan);
                        subPlan = new HashMap<String, Object>();
                    }else{
                        ID_CUST_PLAN_GRP_pre = ID_CUST_PLAN_GRP_cur;
                        isAdd =true;
                        service.put("SAmount", SAmount);
                        TAmount = TAmount.add(SAmount);
                        SAmount = BigDecimal.ZERO;
                        service.put("subPlanList", subPlanList);
                        id_cust_plan_h.add(service);

                        service = new HashMap<String, Object>();
                        subPlanList = new ArrayList<Map<String,Object>>();
                        service.put("billDesc", plan.get("BILL_DESC"));
                        service.put("serviceFrom", plan.get("SVC_START"));
                        service.put("serviceTo", plan.get("SVC_END"));
                        service.put("status", plan.get("SERVICES_STATUS"));
                        
                        subPlan.put("ID_CUST_PLAN_GRP", ID_CUST_PLAN_GRP_cur);
                        subPlan.put("ID_CUST_PLAN_LINK", plan.get("ID_CUST_PLAN_LINK"));
                        subPlan.put("ItemDesc", plan.get("ITEM_DESC"));
                        subPlan.put("RateType", plan.get("RATE_TYPE"));
                        subPlan.put("RateMode", plan.get("RATE_MODE"));
                        subPlan.put("Quantity", plan.get("QUANTITY"));
                        subPlan.put("UPrice", plan.get("UNIT_PRICE"));
                        subPlan.put("Amount", plan.get("TOTAL_AMOUNT"));
                        subPlan.put("GST", CommonUtils.toString(plan.get("APPLY_GST")).equals("1")?"Yes":"");
                        
                        SAmount = SAmount.add((BigDecimal)plan.get("TOTAL_AMOUNT"));
                        subPlanList.add(subPlan);
                        subPlan = new HashMap<String, Object>();
                    }
                }
                if(isAdd){
                    service.put("SAmount", SAmount);
                    TAmount = TAmount.add(SAmount);
                    service.put("subPlanList", subPlanList);
                    id_cust_plan_h.add(service);
                }
                output.put("TAmount", TAmount);
            }
        }
    
        output.put("gdcBillingDescription", CommonUtils.toString(input.get("gdcBillingDescription")).trim());
        output.put("gdcItemDescription", CommonUtils.toString(input.get("gdcItemDescription")).trim());
        output.put("tbxSubscription", CommonUtils.toString(input.get("tbxSubscription")).trim());
        output.put("jobNo", CommonUtils.toString(input.get("jobNo")).trim());
        output.put("custPlanList", id_cust_plan_h);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
    
    /**
     * queryDAO  
     *   to query the database
     */
    private QueryDAO queryDAO= null;
    
    /**
     * for Spring to set instance
     * @param queryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}
