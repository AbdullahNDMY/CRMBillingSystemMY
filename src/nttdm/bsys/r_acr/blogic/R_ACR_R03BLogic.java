/**
 * @(#)R_ACR_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_acr.blogic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_RPT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
import nttdm.bsys.r_acr.dto.R_ACR_R03Input;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_ACR_R03BLogic extends AbstractR_ACR_R03BLogic {

    /**
     * G_RPT_P01
     */
    private static final String G_RPT_P01 = "G_RPT_P01";

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_ACR_R03Input param) {

        BLogicResult result = new BLogicResult();
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
        m.put("tbxCustomerId", param.getTbxCustomerId().trim());
        
        List<HashMap<String, Object>> listAccrualReport =
            new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> listAccrualReport_output =
        	new ArrayList<HashMap<String, Object>>();
        
        listAccrualReport =
            this.queryDAO.executeForObjectList("R_ACR.getListAccrualReport", m);
       
        HashMap<String, Object> item = new HashMap<String, Object>();
        String usageMonthYear = usageMonth + param.getTbxUsageYear().trim();
        for(int i=0;i< listAccrualReport.size();i++){
        	item = listAccrualReport.get(i);
        	Double amount = this.getUnbillAmount(item, usageMonthYear);
  
        	DecimalFormat formater = new DecimalFormat("0.00");       
            item.put("TOTAL_AMOUNT",formater.format(amount));
            
            String ID_PLAN_GRP = CommonUtils.toString(item.get("ID_PLAN_GRP"));             
            Integer count  = queryDAO.executeForObject("SELECT.R_RAC.GETMAPPING", ID_PLAN_GRP, Integer.class);
            if(count.compareTo(new Integer(0))<=0){
                item.put("INVOICE_TOTAL", null);
            }
        	listAccrualReport_output.add(item);
        }
        
        // Call G_RPT_P01
        ApplicationContext context =
            ApplicationContextProvider.getApplicationContext();
        // get G_RPT_P01 object
        G_RPT_P01 gRptP01 = (G_RPT_P01) context.getBean(G_RPT_P01);
        G_RPT_P01_Output gRptP01Output = new G_RPT_P01_Output();
        G_RPT_P01_Input gRptP01Input = new G_RPT_P01_Input();
        gRptP01Input.setListAgingReport(listAccrualReport_output);
        gRptP01Input.setReportType("ACR");
        // The method execute is called G_RPT_P01
        GlobalProcessResult gRptResult =
            gRptP01.execute(gRptP01Input, gRptP01Output);
        // error info
        result.setErrors(gRptResult.getErrors());
        // message info
        result.setMessages(gRptResult.getMessages());
        // Set download file
        DownloadFile file = new DownloadFile(gRptResult.getFile());
        result.setResultObject(file);
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