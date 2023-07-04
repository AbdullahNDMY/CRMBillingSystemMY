/**
 * @(#)R_ACR_R02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_acr.blogic;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_acr.dto.R_ACR_R02Input;
import nttdm.bsys.r_acr.dto.R_ACR_R02Output;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_ACR_R02BLogic extends AbstractR_ACR_R02BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_ACR_R02Input param) {

        BLogicResult result = new BLogicResult();
        R_ACR_R02Output outputDTO = new R_ACR_R02Output();
        // get the number of row for paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
        // mapping param
        Map<String, Object> m = new HashMap<String, Object>();
        
        String usageMonth = "";
        if (param.getCboUsageMonth() != null
            && !"".equals(param.getCboUsageMonth())) {
            if (Integer.parseInt(param.getCboUsageMonth()) < 10) {
                usageMonth = "0" + param.getCboUsageMonth();
            } else {
                usageMonth = param.getCboUsageMonth();
            }
        }
        m.put("cboUsageMonth", usageMonth);
        m.put("tbxUsageYear", param.getTbxUsageYear().trim());
        m.put("UsageMonthYear", usageMonth + '/' + param.getTbxUsageYear().trim());
        m.put("tbxCustomerName", CommonUtils.escapeSQL(param.getTbxCustomerName().trim()));
        m.put("tbxServiceName", CommonUtils.escapeSQL(param.getTbxServiceName().trim()));
        m.put("tbxSubID", CommonUtils.escapeSQL(param.getTbxSubID().trim()));
        m.put("tbxServiceStartFrom", param.getTbxServiceStartFrom());
        m.put("tbxServiceStartTo", param.getTbxServiceStartTo());
        m.put("tbxServiceStatus", param.getTbxServiceStatus());
        m.put("cboCurrency", param.getCboCurrency());
        m.put("tbxImportedAmount", param.getTbxImportedAmount());
        m.put("tbxUnbillAmount", param.getTbxUnbillAmount());
        m.put("tbxCustomerId", CommonUtils.escapeSQL(param.getTbxCustomerId().trim()));
        
        // count total record
        Integer totalReport =
            queryDAO.executeForObject("R_ACR.countAccrualReport", m,
                Integer.class);

        List<HashMap<String, Object>> listAccrualReport =
            new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> listAccrualReport_output =
        	new ArrayList<HashMap<String, Object>>();
        if (totalReport == 0) {
            // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg =
                new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
        } else {
            if (startIndex == null || startIndex < 0
                || totalReport < startIndex) {
                startIndex = 0;
            }
            listAccrualReport = this.queryDAO.executeForObjectList("R_ACR.getListAccrualReport", m, startIndex, row);
            HashMap<String, Object> item = new HashMap<String, Object>();
            
            String usageMonthYear = usageMonth + param.getTbxUsageYear().trim();
            
            for(int i=0;i< listAccrualReport.size();i++){
            	item = listAccrualReport.get(i);
            	Double amount = this.getUnbillAmount(item, usageMonthYear);
      
                item.put("TOTAL_AMOUNT",amount);
                
                String ID_PLAN_GRP = CommonUtils.toString(item.get("ID_PLAN_GRP"));             
                Integer count  = queryDAO.executeForObject("SELECT.R_RAC.GETMAPPING", ID_PLAN_GRP, Integer.class);
                if(count.compareTo(new Integer(0))<=0){
                    item.put("INVOICE_TOTAL", null);
                }
            	listAccrualReport_output.add(item);
            }
        }
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        outputDTO.setListAccrualReport(listAccrualReport_output);
        outputDTO.setTotalRow(totalReport);
        outputDTO.setRow(row);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
    
    /***
     * 
     * @param item
     * @param usageMonthYear MMYYYY
     * @return
     */
    private Double getUnbillAmount(HashMap<String, Object> item, String usageMonthYear){
        int no_of_month=1; 
        // Days of usageMonth
        int x = 30;
        // payment days
        int p = 1;
        int UsageMonthMaxDay = 30;
        String BILL_INSTRUCT = "";
        String RATE_MODE = "";
        String SVC_START = "";
        String SVC_START_MMYYYY = "";
        String SVC_END = "";
        String SVC_END_MMYYYY = "";
        String PRO_RATE_BASE = "";
        
        Double amount = Double.valueOf(item.get("TOTAL_AMOUNT").toString());
        BILL_INSTRUCT = CommonUtils.toString(item.get("BILL_INSTRUCT"));
        RATE_MODE = CommonUtils.toString(item.get("RATE_MODE"));
        
        if ("6".equals(BILL_INSTRUCT) || "6".equals(RATE_MODE)) {
            return amount;
        }
        // Calculate one month amount
        no_of_month = get_no_of_month(Integer.valueOf(RATE_MODE));
                
        // For non-Monthly, return one month's amount
        if (!"5".equals(RATE_MODE)){
            return amount / no_of_month;
        }
 
        // number of days based on calendar month(UsageMonth)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Integer.valueOf(usageMonthYear.substring(0,2))-1);    
        UsageMonthMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        PRO_RATE_BASE = CommonUtils.toString(item.get("PRO_RATE_BASE"));
        if ("U".equals(PRO_RATE_BASE)) {
            Integer PRO_RATE_BASE_NO = CommonUtils.toInteger(item.get("PRO_RATE_BASE_NO"), 30);
            x= PRO_RATE_BASE_NO.intValue();
        }
        else{
            x = UsageMonthMaxDay;
        }

        // DD/MM/YYYY
        SVC_END = CommonUtils.toString(item.get("SVC_END"));
        SVC_START = CommonUtils.toString(item.get("SVC_START"));
        if(!"".equals(SVC_END)){
    		SVC_END_MMYYYY = SVC_END.replace("/", "").substring(2);
    	}
        SVC_START_MMYYYY = SVC_START.replace("/", "").substring(2);
        
        if(SVC_START_MMYYYY.equals(usageMonthYear)){
        	if("".equals(SVC_END)||(!usageMonthYear.equals(SVC_END_MMYYYY))){
        		if(!"01".equals(SVC_START.substring(0,2))){
        			p = UsageMonthMaxDay+1-Integer.valueOf(SVC_START.substring(0, 2));
            		amount = (amount*p)/(no_of_month*x);
    			}else{
    				amount = amount/no_of_month;
    			}
        	}else if(SVC_END_MMYYYY.equals(usageMonthYear)){
        		if("01".equals(SVC_START.substring(0,2))&&String.valueOf(UsageMonthMaxDay).equals(SVC_END.substring(0, 2))){
    				amount = amount/no_of_month;
    			}else{
    				p = Integer.valueOf(SVC_END.substring(0, 2))+1-Integer.valueOf(SVC_START.substring(0, 2));
            		amount = (amount*p)/(no_of_month*x);
    			}
        	}
        }else{
        	if(!"".equals(SVC_END)&& SVC_END_MMYYYY.equals(usageMonthYear)){
                if(String.valueOf(UsageMonthMaxDay).equals(SVC_END.substring(0, 2))){
                	amount = amount/no_of_month;
        		}else{
        			// SVC_START = 01
        			p = Integer.valueOf(SVC_END.substring(0, 2));
            		amount = (amount*p)/(no_of_month*x);
        		}
        	}
        }
        return amount;
    }
    
    /**
     * Get Rate Mode or BILL_INSTRUCT's months.
     * 
     * @param xMode Rate Mode or BILL_INSTRUCT
     * @return month number
     */
    private int get_no_of_month(int xMode) {
        int xMonth = -1;

        switch (xMode) {
        case 1:
            // Yearly
            xMonth = 12;
            break;
        case 2:
            // Bi Annually
            xMonth = 6;
            break;
        case 3:
            // Quarterly
            xMonth = 3;
            break;
        case 4:
            // Bi-Monthly
            xMonth = 2;
            break;
        default:
            xMonth = 1;
            break;
        }

        return xMonth;
    }
}