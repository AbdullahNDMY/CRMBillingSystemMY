/**
 * @(#)R_RRR_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_RPT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
import nttdm.bsys.r_rrr.dto.R_RRR_R03Input;
import nttdm.bsys.util.ApplicationContextProvider;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_RRR_R03BLogic extends AbstractR_RRR_R03BLogic {
    
    /**
     * G_RPT_P01
     */
    private static final String G_RPT_P01 = "G_RPT_P01";
    
    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_RRR_R03Input param) {

        BLogicResult result = new BLogicResult();
        // mapping param
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("tbxPaidDateFrom", param.getTbxPaidDateFrom());
        m.put("tbxPaidDateTo", param.getTbxPaidDateTo());
        m.put("tbxCustomerName", CommonUtils.escapeSQL(param.getTbxCustomerName().trim()));
        m.put("tbxAffiliateCod", CommonUtils.escapeSQL(param.getTbxAffiliateCod().trim()));
        m.put("tbxBillDocument", CommonUtils.escapeSQL(param.getTbxBillDocument().trim()));
        m.put("tbxChequeNo", CommonUtils.escapeSQL(param.getTbxChequeNo().trim()));
        m.put("tbxReceiptNo", CommonUtils.escapeSQL(param.getTbxReceiptNo().trim()));
        m.put("cboPaymentMetho", param.getCboPaymentMetho());
        m.put("cboCurrency", param.getCboCurrency());
        m.put("tbxBankInName", param.getTbxBankInName());

        // count total record
        List<HashMap<String, Object>> listRefundReport = new ArrayList<HashMap<String, Object>>();
        listRefundReport = this.queryDAO.executeForObjectList("R_RRR.getListRefundReport", m);

        // Call G_RPT_P01
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        G_RPT_P01 gRptP01 = (G_RPT_P01) context.getBean(G_RPT_P01);
        G_RPT_P01_Output gRptP01Output = new G_RPT_P01_Output();
        G_RPT_P01_Input gRptP01Input = new G_RPT_P01_Input();
        gRptP01Input.setListAgingReport(listRefundReport);
        gRptP01Input.setReportType("RRR");
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
}