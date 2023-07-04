/**
 * @(#)B_CSBR01BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-8-29

 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_csb.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_RPT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;

/**
 * Case Book Search business logic.
 * 
 * @author bench
 *
 */
public class B_CSBR01ExportBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * @param input Map<String, Object>
     * @return BLogicResult
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        Map<String, Object> output = new HashMap<String, Object>();
        Map<String, Object> parameter = new HashMap<String, Object>();
        this.copyParam(input, output);
        this.copyParam(input, parameter);
        
        parameter.put("payer", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("payer")).trim()));
        //#263 [T11] Add customer type at inquiry screen and export result CT 06062017
        parameter.put("payerType", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("payerType")).trim()));
        //#263 [T11] Add customer type at inquiry screen and export result CT 06062017
        parameter.put("refNo", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("refNo")).trim()));
        parameter.put("receiptNo", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("receiptNo")).trim()));
        parameter.put("billaccNo", CommonUtils.escapeSQL(CommonUtils.toString(parameter.get("billaccNo")).trim()));

        // retrieve search result data
        List<HashMap<String, Object>> csbInfos = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_1.1", parameter);
        
        // Call G_RPT_P01
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        G_RPT_P01 gRptP01 = (G_RPT_P01) context.getBean("G_RPT_P01");
        G_RPT_P01_Output gRptP01Output = new G_RPT_P01_Output();
        G_RPT_P01_Input gRptP01Input = new G_RPT_P01_Input();
        gRptP01Input.setListAgingReport(csbInfos);
        gRptP01Input.setReportType("CSB");
        // The method execute is called G_RPT_P01
        GlobalProcessResult gRptResult = gRptP01.execute(gRptP01Input, gRptP01Output);
        // error info
        result.setErrors(gRptResult.getErrors());
        // message info
        result.setMessages(gRptResult.getMessages());
        // Set download file
        DownloadFile file = new DownloadFile(gRptResult.getFile());
        result.setResultObject(file);
        return result;
    }
    
    /**
     * 
     * @param src Map<String, Object>
     * @param des Map<String, Object>
     */
    private void copyParam(Map<String, Object> src, Map<String, Object> des) {
        Set<String> keys = src.keySet();
        for (String key : keys) {
            des.put(key, src.get(key));
        }  
    }
}