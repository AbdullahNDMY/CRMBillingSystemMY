/*
 * @(#)RP_B_BAC_S01_02BLogic.java
 */
package nttdm.bsys.b_bac.blogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
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
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Input;
import nttdm.bsys.common.util.CSVWriter;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;

/**
 * Export Logic to export the search result<br>
 * Note:this class come from RP_B_BAC_S01_02BLogic,only to export<br>
 * so input class and super class same as RP_B_BAC_S01_02BLogic
 * 
 * @author nttdm
 */
public class RP_B_BAC_S01_04BLogic implements BLogic<RP_B_BAC_S01_02Input> {

    private QueryDAO queryDAO;

    // message resource tool
    private MessageResources msgResource = MessageResources.getMessageResources("B_BAC-messages");

    /**
     * @param inputDto
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S01_02Input inputDto) {
        BLogicResult result = new BLogicResult();

        String fileName = "OutstandingReport_YYMMDDHHMMSS.csv".replace("YYMMDDHHMMSS", getSysdateStr());

        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }
        String filePathName = tmpFolder + fileName;

        List<String[]> exportData = new ArrayList<String[]>();

        // add header to export list.
        exportData.add(getExportHeaderItems());

        // get result list
        List<Map<String, Object>> accountInfoList = queryDAO.executeForMapList("B_BAC.getBillAccInfo14a", null);

        // loop the list to prepare export
        for (int i = 0; i < accountInfoList.size(); i++) {

            Map<String, Object> item = accountInfoList.get(i);

            String billAcc = CommonUtils.toString(item.get("ID_BILL_ACCOUNT"));

            // get LAST INVOICE NO
            String idRef = queryDAO.executeForObject("B_BAC.getOtherInfo14b", billAcc, String.class);
            item.put("ID_REF", idRef);

            // outstanding amount (Invoice / Debit Note)
            Map<String, Object> indnAmount = queryDAO.executeForMap("B_BAC.getOutstandingAmount14c", billAcc);

            // outstanding amount (CreditNote)
            Map<String, Object> cnAmount = queryDAO.executeForMap("B_BAC.getOutstandingAmount14d", billAcc);

            // get cash book balance
            Map<String, Object> csbBalance = queryDAO.executeForMap("B_BAC.getCsbBalance14e", billAcc);

            // IN/DN TOTAL_OUTSTANDING
            BigDecimal indnOut = new BigDecimal(CommonUtils.toString(indnAmount.get("TOTAL_OUTSTANDING")));

            // CN
            BigDecimal cnOut = new BigDecimal(CommonUtils.toString(cnAmount.get("TOTAL_OUTSTANDING")));

            // TOTAL_OUTSTANDING
            BigDecimal TOTAL_OUTSTANDING = indnOut.subtract(cnOut);
            item.put("TOTAL_OUTSTANDING", TOTAL_OUTSTANDING);

            // TOTAL_AMT_DUE
            BigDecimal TOTAL_AMT_DUE = new BigDecimal(CommonUtils.toString(item.get("TOTAL_AMT_DUE")));

            // TOTAL_BALANCE
            BigDecimal totalBalance = new BigDecimal(CommonUtils.toString(csbBalance.get("TOTAL_BALANCE")));
            item.put("TOTAL_BALANCE", totalBalance);

            BigDecimal totalAmountDue = TOTAL_OUTSTANDING.subtract(totalBalance);

            if (totalAmountDue.compareTo(TOTAL_AMT_DUE) == 0) {
                item.put("STATUS", "OK");
            } else {
                item.put("STATUS", "NG-Please contact your system administrator");
            }

            exportData.add(getExportContentItems(item));
        }

        try {
            FileWriter fw = new FileWriter(filePathName);
            CSVWriter writer = new CSVWriter(fw, ',', '\"');
            writer.writeAllFullNumber(exportData);
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
     * Generate exported content.
     * 
     * @param item
     * @return exported item value
     */
    private String[] getExportContentItems(Map<String, Object> item) {
        List<String> items = new ArrayList<String>();

        // Customer ID
        items.add(CommonUtils.toString(item.get("ID_CUST")));

        // Customer Name
        items.add(CommonUtils.toString(item.get("CUST_NAME")));

        // Billing Account No
        items.add(CommonUtils.toString(item.get("ID_BILL_ACCOUNT")));

        // CUSTOMER_TYPE
        String customerType = CommonUtils.toString(item.get("CUSTOMER_TYPE"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", customerType));

        // Payment Method
        String paymentMethod = CommonUtils.toString(item.get("PAYMENT_METHOD"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_PAYMENT_METHOD", paymentMethod));

        // SingPost Export
        if ("1".equals(CommonUtils.toString(item.get("IS_SINGPOST")))) {
            items.add(msgResource.getMessage("screen.b_bac_s01.bySingPost.yes"));
        } else {
            items.add(msgResource.getMessage("screen.b_bac_s01.bySingPost.no"));
        }

        // Billing Currency
        items.add(CommonUtils.toString(item.get("BILL_CURRENCY")));

        // Export Currency
        items.add(CommonUtils.toString(item.get("EXPORT_CURRENCY")));

        // Fixed Forex
        items.add(CommonUtils.toString(item.get("FIXED_FOREX")));

        // Total Outstanding
        items.add(CommonUtils.toString(item.get("TOTAL_OUTSTANDING")));

        // Total Balance
        items.add(CommonUtils.toString(item.get("TOTAL_BALANCE")));

        // Total Amount Due
        items.add(CommonUtils.toString(item.get("TOTAL_AMT_DUE")));

        // Last Invoice
        items.add(CommonUtils.toString(item.get("ID_REF")));

        // Status
        items.add(CommonUtils.toString(item.get("STATUS")));

        return items.toArray(new String[items.size()]);
    }

    /**
     * Get CSV file header items.
     * 
     */
    private String[] getExportHeaderItems() {

        List<String> items = new ArrayList<String>();

        // Customer ID
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.custmerId"));

        // Customer Name
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.customerName"));

        // Billing Account No.
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.billAccNo"));

        // Customer Type
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.customerType"));
        
        // Payment Method
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.pamentMethod"));

        // SingPost Export
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.singpost"));

        // Billing Currency
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.billingCurency"));

        // Export Currency
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.exportCurrency"));

        // Fixed Forex
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.fixedForex"));

        // Total Outstanding
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.totalOutstanding"));

        // Total Balance
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.totalBalance"));

        // Total Amount Due
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.totalAmountDue"));

        // Last Invoice
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.lastInvoice"));

        // Status
        items.add(msgResource.getMessage("screen.b_bac_s01.outstanding.status"));

        return items.toArray(new String[items.size()]);
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
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
}