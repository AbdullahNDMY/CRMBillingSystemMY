/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S03
 * FUNCTION : Export BLogic
 * FILE NAME : M_PPM_S01_03BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */
package nttdm.bsys.m_ppm.blogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CSVWriter;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.dto.M_PPM_S01_02Input;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;

/**
 * Export BLogic<br/>
 * Use to export data<br/>
 * 
 * @author NTTData
 * @version 1.1
 */
public class M_PPM_S01_03BLogic implements BLogic<M_PPM_S01_02Input> {

    private QueryDAO queryDAO;
    private MessageResources msgResource = MessageResources.getMessageResources("M_PPM-messages");

    public BLogicResult execute(M_PPM_S01_02Input input) {

        BLogicResult result = new BLogicResult();

        List<String[]> exportData = new ArrayList<String[]>();

        // add header to export list.
        exportData.add(getExportHeaderItems());

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        dataList = queryDAO.executeForMapList("SELECT.M_PPM.S03.SEARCH_EXPORT", input);

        for (int i = 0; i < dataList.size(); i++) {
            exportData.add(getExportContentItems(dataList.get(i)));
        }

        String fileName = "StandardPlanInfo_YYMMDDHHMMSS.csv".replace("YYMMDDHHMMSS", getSysdateStr());

        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }
        String filePathName = tmpFolder + fileName;

        try {
            FileWriter fw = new FileWriter(filePathName);
            CSVWriter writer = new CSVWriter(fw, ',', '\"');
            writer.writeAll(exportData);
            writer.close();
        } catch (IOException e) {
            BLogicMessages errors = new BLogicMessages();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1BT014", fileName));
            result.setErrors(errors);
        }

        // Set download file
        DownloadFile file = new DownloadFile(new File(filePathName));
        result.setResultObject(file);
        return result;
    }

    /**
     * YY MMDDHHMMSS
     * 
     * @return
     */
    private String getSysdateStr() {
        Calendar now = Calendar.getInstance();
        String yy = CommonUtils.formatDate(now.getTime(), "yy");
        String MM = CommonUtils.formatDate(now.getTime(), "MM");
        String dd = CommonUtils.formatDate(now.getTime(), "dd");
        String HH = CommonUtils.formatDate(now.getTime(), "HH");
        String mm = CommonUtils.formatDate(now.getTime(), "mm");
        String ss = CommonUtils.formatDate(now.getTime(), "ss");

        return yy + "" + MM + "" + dd + "" + HH + "" + mm + ss;
    }

    /**
     * Get CSV file header items.
     * 
     */
    private String[] getExportHeaderItems() {

        List<String> items = new ArrayList<String>();

        // No
        items.add(msgResource.getMessage("screen.m_ppms01.export.no"));

        // Service Name
        items.add(msgResource.getMessage("screen.m_ppms01.export.serviceName"));

        // Service Description
        items.add(msgResource.getMessage("screen.m_ppms01.export.serviceDesc"));

        // Customer Type
        items.add(msgResource.getMessage("screen.m_ppms01.export.customerType"));

        // Billing Currency
        items.add(msgResource.getMessage("screen.m_ppms01.export.billingCurrency"));

        // Item Type
        items.add(msgResource.getMessage("screen.m_ppms01.export.itemType"));

        // Sub Plan / Option Name
        items.add(msgResource.getMessage("screen.m_ppms01.export.subPlanOptionName"));

        // Category
        items.add(msgResource.getMessage("screen.m_ppms01.export.category"));

        // Service
        items.add(msgResource.getMessage("screen.m_ppms01.export.service"));

        // Revenue Code
        items.add(msgResource.getMessage("screen.m_ppms01.export.revenueCd"));

        // Rate Type
        items.add(msgResource.getMessage("screen.m_ppms01.export.rateType"));

        // Rate Mode
        items.add(msgResource.getMessage("screen.m_ppms01.export.rateMode"));

        // Rate
        items.add(msgResource.getMessage("screen.m_ppms01.export.rate"));

        // GST
        items.add(msgResource.getMessage("screen.m_ppms01.export.gst"));
        
        // Plan
        items.add(msgResource.getMessage("screen.m_ppms01.export.plan"));
        
        // PlanDetail
        items.add(msgResource.getMessage("screen.m_ppms01.export.planDetail"));

        return items.toArray(new String[items.size()]);
    }

    /**
     * Generate exported content.
     * 
     * @param item
     * @return exported item value
     */
    private String[] getExportContentItems(Map<String, Object> item) {
        List<String> items = new ArrayList<String>();

        // No
        items.add(CommonUtils.toString(item.get("NO")));

        // SERVICE_NAME
        items.add(CommonUtils.toString(item.get("SERVICE_NAME")));

        // SERVICE_DESCRIPTION
        items.add(CommonUtils.toString(item.get("SERVICE_DESCRIPTION")));

        // CUSTOMER_TYPE
        String customerType = CommonUtils.toString(item.get("CUSTOMER_TYPE"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", customerType));

        // BILLING_CURRENCY
        items.add(CommonUtils.toString(item.get("BILLING_CURRENCY")));

        // ITEM_TYPE
        String itemType = CommonUtils.toString(item.get("ITEM_TYPE"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_PLANTYPE", itemType));

        // PLAN_OPTION_NAME
        items.add(CommonUtils.toString(item.get("PLAN_OPTION_NAME")));

        // CATEGORY
        items.add(CommonUtils.toString(item.get("CATEGORY_NAME")));

        // SERVICE
        items.add(CommonUtils.toString(item.get("SERVICE")));

        // REVENUE_CODE
        items.add(CommonUtils.toString(item.get("REVENUE_CODE")));

        // RATE_TYPE
        String rateType = CommonUtils.toString(item.get("RATE_TYPE"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_RATETYPE", rateType));

        // RATE_MODE
        String rateMode = CommonUtils.toString(item.get("RATE_MODE"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_RATEMODE", rateMode));

        // RATE
        items.add(CommonUtils.toString(item.get("RATE")));

        // GST
        items.add(CommonUtils.toString(item.get("TAXRATEDESCRIPTION")));

        // PLAN
        items.add(CommonUtils.toString(item.get("PLAN")));
        
        // PLAN_DETAIL
        items.add(CommonUtils.toString(item.get("PLAN_DETAIL")));
        
        return items.toArray(new String[items.size()]);
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}