/**
 * @(#)R_AGR_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_agr.blogic;

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
import nttdm.bsys.r_agr.dto.R_AGR_R03Input;
import nttdm.bsys.util.ApplicationContextProvider;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_AGR_R03BLogic extends AbstractR_AGR_R03BLogic {
    
    /**
     * G_RPT_P01
     */
    private static final String G_RPT_P01 = "G_RPT_P01";
    
    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_AGR_R03Input param) {

        BLogicResult result = new BLogicResult();

        // mapping param
        Map<String, Object> m = new HashMap<String, Object>();

        String billMonth = "";
        if (param.getCboBillMonth() != null
            && !"".equals(param.getCboBillMonth())) {
            if (Integer.parseInt(param.getCboBillMonth()) < 10) {
                billMonth = "0" + param.getCboBillMonth();
            } else {
                billMonth = param.getCboBillMonth();
            }
        }
        String billMonthTo = "";
        if (param.getCboBillMonthTo() != null
            && !"".equals(param.getCboBillMonthTo())) {
            if (Integer.parseInt(param.getCboBillMonthTo()) < 10) {
                billMonthTo = "0" + param.getCboBillMonthTo();
            } else {
                billMonthTo = param.getCboBillMonthTo();
            }
        }
        
        String billYear = CommonUtils.toString(param.getTbxBillYear()).trim();
        String billYearTo = CommonUtils.toString(param.getTbxBillYearTo()).trim();
        m.put("cboBillMonth", billMonth);
        m.put("tbxBillYear", billYear);
        m.put("cboBillMonthTo", billMonthTo);
        m.put("tbxBillYearTo", billYearTo);
        if (!CommonUtils.isEmpty(billMonth) && !CommonUtils.isEmpty(billYear)) {
            m.put("tbxBillFromYearMonth", billYear+billMonth);
        } else {
            m.put("tbxBillFromYearMonth", "");
        }
        if (!CommonUtils.isEmpty(billMonthTo) && !CommonUtils.isEmpty(billYearTo)) {
            m.put("tbxBillFromYearMonthTo", billYearTo+billMonthTo);
        } else {
            m.put("tbxBillFromYearMonthTo", "");
        }
        m.put("tbxCustomerName", CommonUtils.escapeSQL(param.getTbxCustomerName().trim()));
        m.put("tbxAffiliateCod", CommonUtils.escapeSQL(param.getTbxAffiliateCod().trim()));
        m.put("cboPaymentMetho", param.getCboPaymentMetho());
        m.put("cboCurrency", param.getCboCurrency());
        m.put("tbxBillingAccout", CommonUtils.escapeSQL(param.getTbxBillingAccout().trim()));
        m.put("tbxBillDocumentNo", CommonUtils.escapeSQL(param.getTbxBillDocumentNo().trim()));

        List<HashMap<String, Object>> listAgingReport =
            new ArrayList<HashMap<String, Object>>();

        listAgingReport =
            this.queryDAO.executeForObjectList("R_AGR.getListAgingReport", m);

        // Call G_RPT_P01
        ApplicationContext context = ApplicationContextProvider.
        getApplicationContext();
        // get G_RPT_P01 object
        G_RPT_P01 gRptP01 = (G_RPT_P01) context.getBean(G_RPT_P01);
        G_RPT_P01_Output gRptP01Output = new G_RPT_P01_Output();
        G_RPT_P01_Input gRptP01Input = new G_RPT_P01_Input();
        gRptP01Input.setListAgingReport(listAgingReport);
        gRptP01Input.setReportType("AGR");
        // The method execute is called G_RPT_P01
        GlobalProcessResult gRptResult = gRptP01.execute(gRptP01Input,
            gRptP01Output);
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