/**
 * @(#)R_RRR_R02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-9-9
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.blogic;

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
import nttdm.bsys.r_rrr.dto.R_RRR_R02Input;
import nttdm.bsys.r_rrr.dto.R_RRR_R02Output;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_RRR_R02BLogic extends AbstractR_RRR_R02BLogic {
   
    /**
     * errors
     */
    private BLogicMessages errors;
    
    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_RRR_R02Input param) {

        BLogicResult result = new BLogicResult();
        R_RRR_R02Output outputDTO = new R_RRR_R02Output();
        errors = new BLogicMessages();
        // get the number of row for paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
        // mapping parameter
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
        
        List<HashMap<String, Object>> listRefundReport = new ArrayList<HashMap<String, Object>>();
        Integer totalReport = 0;
        
        // count total record
        totalReport = queryDAO.executeForObject("R_RRR.countRefundReport", m, Integer.class);
        if (totalReport == 0) {
            startIndex = 0;
            // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
        } else {
            if (startIndex == null || startIndex < 0 || totalReport < startIndex) {
                startIndex = 0;
            }
            listRefundReport = this.queryDAO.executeForObjectList("R_RRR.getListRefundReport", m, startIndex, row);
        }
        
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        outputDTO.setListRefundReport(listRefundReport);
        outputDTO.setStartIndex(startIndex);
        outputDTO.setTotalRow(totalReport);
        outputDTO.setRow(row);
        result.setResultObject(outputDTO);
        result.setErrors(errors);
        result.setResultString("success");
        return result;
    }
}
