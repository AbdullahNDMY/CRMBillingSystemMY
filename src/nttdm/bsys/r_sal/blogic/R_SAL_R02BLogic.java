/**
 * @(#)R_SAL_R02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_sal.dto.R_SAL_R02Input;
import nttdm.bsys.r_sal.dto.R_SAL_R02Output;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_SAL_R02BLogic extends AbstractR_SAL_R02BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_SAL_R02Input param) {

        BLogicResult result = new BLogicResult();
        R_SAL_R02Output outputDTO = new R_SAL_R02Output();
        // get the number of row for paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
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

        // count total record
        Integer totalReport =
            queryDAO.executeForObject("R_SAL.countSalesReport", m,
                Integer.class);

        List<HashMap<String, Object>> listSalesReport =
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
            listSalesReport =
                this.queryDAO.executeForObjectList("R_SAL.getListSalesReport",
                    m, startIndex, row);
        }
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        outputDTO.setListSalesReport(listSalesReport);
        outputDTO.setTotalRow(totalReport);
        outputDTO.setRow(row);
        outputDTO.setInitFlag("0");
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}