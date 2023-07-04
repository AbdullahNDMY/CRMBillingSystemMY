/**
 * @(#)A_ADTR01BLogic.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.a_adt.blogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_adt.bean.AuditHeader;
import nttdm.bsys.a_adt.dto.A_ADTR01Input;
import nttdm.bsys.a_adt.dto.A_ADTR01Output;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionMessages;

/**
 * ビジネスロジッククラス。
 * 
 * @author NTTDM
 */
public class A_ADTR01BLogic extends AbstractA_ADTR01BLogic {

    // Count from audit header SQL
    private static final String SQL_COUNT_AUDIT_HEADER = "SELECT.A_ADT.SQL001";

    // Select audit header SQL
    private static final String SQL_SELECT_AUDIT_HEADER = "SELECT.A_ADT.SQL002";

    // Date format
    private static final String DATE_INPUT_FORMAT = "dd/MM/yyyy";

    private static final String DATE_OUTPUT_FORMAT = "yyyyMMdd";

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(A_ADTR01Input param) {
        A_ADTR01Output output = new A_ADTR01Output();

        // Initialize startIndex and row
        int startIndex = param.getStartIndex();
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int rowsPerPage = bss.getResultRow();

        // Repairs params
        try {
            Date tmpDate;
            DateFormat inFormat = new SimpleDateFormat(DATE_INPUT_FORMAT);
            DateFormat outFormat = new SimpleDateFormat(DATE_OUTPUT_FORMAT);
            if (!CommonUtils.isEmpty(param.getDateFrom())) {
                tmpDate = inFormat.parse(param.getDateFrom());
                param.setDateFrom(outFormat.format(tmpDate));
            }
            if (!CommonUtils.isEmpty(param.getDateTo())) {
                tmpDate = inFormat.parse(param.getDateTo());
                param.setDateTo(outFormat.format(tmpDate));
            }
        } catch (Exception ex) {

        }
        if (!CommonUtils.isEmpty(param.getReference())) {
            param.setReference("%" + param.getReference().trim() + "%");
        }
        if (!CommonUtils.isEmpty(param.getUserName())) {
            param.setUserName("%" + param.getUserName().trim() + "%");
        }
        String subModuleID = CommonUtils.toString(param.getSubModuleID()).trim();
        param.setSubModuleIDLen(CommonUtils.toString(subModuleID.length()));
        // Get total audit
        Integer totalAuditHeader = queryDAO.executeForObject(SQL_COUNT_AUDIT_HEADER, param, Integer.class);
        // Get list of audit header
        List<AuditHeader> auditHeaderList = queryDAO.executeForObjectList(SQL_SELECT_AUDIT_HEADER, param, startIndex,
                rowsPerPage);
        // Set output
        output.setRowsPerPage(rowsPerPage);
        output.setAuditHeaderList(auditHeaderList);
        output.setTotalAuditHeader(totalAuditHeader);

        // Set result
        BLogicResult result = new BLogicResult();
        if(totalAuditHeader==0){
            // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
        }
        result.setResultObject(output);
        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
        return result;
    }
}