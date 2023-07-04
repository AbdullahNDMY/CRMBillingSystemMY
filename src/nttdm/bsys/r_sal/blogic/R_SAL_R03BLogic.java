/**
 * @(#)R_SAL_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.blogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.common.fw.BillingSystemPageLinksTag;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_RPT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
import nttdm.bsys.r_sal.dto.R_SAL_R03Input;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_SAL_R03BLogic extends AbstractR_SAL_R03BLogic {
    
    /**
     * G_RPT_P01
     */
    private static final String G_RPT_P01 = "G_RPT_P01";
    
    private static Log log =
            LogFactory.getLog(R_SAL_R03BLogic.class);
    
    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_SAL_R03Input param) {

        BLogicResult result = new BLogicResult();
        // mapping param
        Map<String, Object> m = new HashMap<String, Object>();
        
        m.put("tbxBillFromYearMonth", CommonUtils.toString(param.getTbxBillYearMonth()).trim());
        m.put("tbxBillFromYearMonthTo", CommonUtils.toString(param.getTbxBillYearMonthTo()).trim());
        m.put("tbxCustomerName", CommonUtils.escapeSQL(param.getTbxCustomerName().trim()));
        m.put("tbxAffiliateCod", CommonUtils.escapeSQL(param.getTbxAffiliateCod().trim()));
        m.put("tbxInvoiceNo", CommonUtils.escapeSQL(param.getTbxInvoiceNo().trim()));
        m.put("tbxSubscription", CommonUtils.escapeSQL(param.getTbxSubscription().trim()));
        m.put("tbxServiceName", CommonUtils.escapeSQL(param.getTbxServiceName().trim()));
        m.put("tbxRevenueCode", CommonUtils.escapeSQL(param.getTbxRevenueCode().trim()));
        m.put("cboCurrency", param.getCboCurrency());
        m.put("cboPaymentMetho", param.getCboPaymentMetho());
        m.put("cboCategory", param.getCboCategory());
        m.put("tbxCustomerType", param.getTbxCustomerType());
        m.put("cboRateMode", param.getCboRateMode());
        m.put("serviceDateStartFrom", param.getServiceDateStartFrom());
        m.put("serviceDateStartTo", param.getServiceDateStartTo());
        m.put("serviceDateEndFrom", param.getServiceDateEndFrom());
        m.put("serviceDateEndTo", param.getServiceDateEndTo());
        
        String issueTypeAllNotChecked = "";
        String issueTypeSingpost = CommonUtils.toString(param.getIssueTypeSingpost());
        String issueTypeAuto = CommonUtils.toString(param.getIssueTypeAuto());
        String issueTypeManual = CommonUtils.toString(param.getIssueTypeManual());
        
        if (CommonUtils.isEmpty(issueTypeSingpost)
                &&CommonUtils.isEmpty(issueTypeAuto)
                &&CommonUtils.isEmpty(issueTypeManual)) {
            issueTypeAllNotChecked = "0";
        }
        m.put("issueTypeSingpost", issueTypeSingpost);
        m.put("issueTypeAuto", issueTypeAuto);
        m.put("issueTypeManual", issueTypeManual);
        m.put("issueTypeAllNotChecked", issueTypeAllNotChecked);
        
        List<HashMap<String, Object>> listSalesReport =
            new ArrayList<HashMap<String, Object>>();

        listSalesReport =
            this.queryDAO.executeForObjectList("R_SAL.getListSalesReport", m);
        
        /*#267 [T15][R-SAL-S01] Sales Reports: Export result - Add new column: Plan CT 13062017*/
        for (Map<String, Object> svc : listSalesReport) {
			Map<String, Object> m2 = new HashMap<String, Object>();
	        m2.put("idCustPlanLink", svc.get("ID_CUST_PLAN_LINK"));
	        List<Map<String,Object>> plan = this.queryDAO.executeForMapList("R_SAL.GET_SVC_NAME",m2);
	        svc.put("Plan", getPlanDesc(plan,svc.get("ID_CUST_PLAN_LINK")));
    	}
        /*#267 [T15][R-SAL-S01] Sales Reports: Export result - Add new column: Plan CT 13062017*/

        // Call G_RPT_P01
        ApplicationContext context = ApplicationContextProvider.
        getApplicationContext();
        // get G_RPT_P01 object
        G_RPT_P01 gRptP01 = (G_RPT_P01) context.getBean(G_RPT_P01);
        G_RPT_P01_Output gRptP01Output = new G_RPT_P01_Output();
        G_RPT_P01_Input gRptP01Input = new G_RPT_P01_Input();
        gRptP01Input.setListAgingReport(listSalesReport);
        gRptP01Input.setReportType("SAL");
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
    
    public String getPlanDesc(List<Map<String,Object>> list, Object idCustPlanLink){
    	String planDesc = "";
    	if(list.size() > 1){
    		log.info("Plan Description more than one idCustPlanLink: "+idCustPlanLink);
    	}
    	for (Map<String, Object> svc : list) {
    		planDesc = (String) svc.get("SVC_DESC");
    	}
    	return planDesc;
    }
}