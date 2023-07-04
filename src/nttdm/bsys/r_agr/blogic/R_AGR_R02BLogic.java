/**
 * @(#)R_AGR_R02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_agr.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_agr.dto.R_AGR_R02Input;
import nttdm.bsys.r_agr.dto.R_AGR_R02Output;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_AGR_R02BLogic extends AbstractR_AGR_R02BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_AGR_R02Input param) {

        BLogicResult result = new BLogicResult();
        R_AGR_R02Output outputDTO = new R_AGR_R02Output();
        // get the number of row for paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
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

        // count total record
        Integer totalReport =
            queryDAO.executeForObject("R_AGR.countAgingReport", m,
                Integer.class);

        List<HashMap<String, Object>> listAgingReport =
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
            listAgingReport =
                this.queryDAO.executeForObjectList("R_AGR.getListAgingReport",
                    m, startIndex, row);
        }
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        outputDTO.setListAgingReport(listAgingReport);
        outputDTO.setTotalRow(totalReport);
        outputDTO.setRow(row);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}