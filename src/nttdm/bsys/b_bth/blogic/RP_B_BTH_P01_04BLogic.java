/**
 * Billing System
 * 
 * SUBSYSTEM NAME : RP_B_BTH_P01_04 
 * SERVICE NAME :  RP_B_BTH
 * FUNCTION : Print Billing Document
 * FILE NAME : RP_B_BTH_P01_04BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */

package nttdm.bsys.b_bth.blogic;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_04Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_04Output;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_BTH_P01_2;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data Vietnam Action export file billing document
 * 
 */

public class RP_B_BTH_P01_04BLogic extends AbstractRP_B_BTH_P01_04BLogic {

    public static final String ID_SET_NOPRINT = "NOPRINTDOC";

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */

    public BLogicResult execute(RP_B_BTH_P01_04Input param) {
        BLogicResult result = new BLogicResult();
        BLogicMessages errors = new BLogicMessages();
        RP_B_BTH_P01_04Output outputDTO = new RP_B_BTH_P01_04Output();
        String customerName = param.getCustomerName();
        String documentNoFrom = param.getDocumentNoFrom();
        String documentNoTo = param.getDocumentNoTo();
        String documentNo = param.getDocumentNo();
        String billingType = param.getBillingType();
        String billingAccount = param.getBillingAccount();
        Date fromDate = CommonUtils.parseToDate(param.getFromDate(), "dd/MM/yyyy");
        Date toDate = CommonUtils.parseToDate(param.getToDate(), "dd/MM/yyyy");

        // Prepare data for the map
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("customerName", CommonUtils.escapeSQL(customerName == null ? customerName : customerName.trim()));
        m.put("documentNoFrom", CommonUtils.escapeSQL(documentNoFrom == null ? documentNoFrom : documentNoFrom.trim()));
        m.put("documentNoTo", CommonUtils.escapeSQL(documentNoTo == null ? documentNoTo : documentNoTo.trim()));
        m.put("billingType", billingType);
        m.put("billingAccount", CommonUtils.escapeSQL(billingAccount == null ? billingAccount : billingAccount.trim()));
        m.put("fromDate", fromDate);
        m.put("toDate", toDate);
        m.put("idSet", ID_SET_NOPRINT);
        m.put("cboDeletedStatus", param.getCboDeletedStatus());
        m.put("cboBillingCurrency", param.getCboBillingCurrency());
        m.put("documentNo", param.getDocumentNo());
        m.put("deliveryEmail", param.getDeliveryEmail());
		if (param.getDelivery().length == 3) {
			m.put("delivery", new String[] { "1", "2", "3", "4" });
		} else {
			m.put("delivery", param.getDelivery());
		}
        String issueTypeAllNotChecked = "";
        String issueTypeSingpost = CommonUtils.toString(param.getIssueTypeSingpost());
        String issueTypeAuto = CommonUtils.toString(param.getIssueTypeAuto());
        String issueTypeManual = CommonUtils.toString(param.getIssueTypeManual());

        if (CommonUtils.isEmpty(issueTypeSingpost) && CommonUtils.isEmpty(issueTypeAuto) && CommonUtils.isEmpty(issueTypeManual)) {
            issueTypeAllNotChecked = "0";
        }
        m.put("issueTypeSingpost", issueTypeSingpost);
        m.put("issueTypeAuto", issueTypeAuto);
        m.put("issueTypeManual", issueTypeManual);
        m.put("issueTypeAllNotChecked", issueTypeAllNotChecked);

        // validate data
        boolean allowSearch = true;
        if ((CommonUtils.isEmpty(documentNoFrom) && CommonUtils.isEmpty(documentNoTo))
                || (!CommonUtils.isEmpty(documentNoFrom) && !CommonUtils.isEmpty(documentNoTo))) {
            allowSearch = true;
        } else {
            allowSearch = false;
        }

        if (CommonUtils.isEmpty(param.getFromDate()) && !CommonUtils.isEmpty(param.getToDate())) {
            allowSearch = false;
        }
        if (!CommonUtils.isEmpty(param.getFromDate()) && CommonUtils.isEmpty(param.getToDate())) {
            allowSearch = false;
        }

        if (fromDate != null && toDate != null) {
            if (toDate.before(fromDate)) {
                allowSearch = false;
            }
        }

        if (!allowSearch) {
            try {
                BeanUtils.copyProperties(outputDTO, param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            result.setResultObject(outputDTO);
            result.setResultString("search");
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(" ",false));
            result.setErrors(errors);
            return result;
        }

        List<String> listFullPrinted = new ArrayList<String>();
        listFullPrinted = this.queryDAO.executeForObjectList("B_BTH.getFullPrinted", m);
        String fullIdRefs = "";
        for (String s : listFullPrinted) {
            fullIdRefs += ("," + s);
        }
        if (!fullIdRefs.equals(""))
            fullIdRefs = fullIdRefs.substring(1);
        param.setFullIdRefs(fullIdRefs);

        // Call G_BTH_P01 process
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        G_BTH_P01_2 gBthP01 = (G_BTH_P01_2) context.getBean("G_BTH_P01_2");
        GlobalProcessResult glPResult = gBthP01.execute(param, outputDTO);
        result.setErrors(glPResult.getErrors());
        result.setMessages(glPResult.getMessages());
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // Set download file
        result.setResultObject(gBthP01.getDownloadFile(glPResult.getFile()));
        result.setErrors(errors);
        return result;
    }

}