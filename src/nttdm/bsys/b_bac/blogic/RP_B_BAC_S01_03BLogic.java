/*
 * @(#)RP_B_BAC_S01_02BLogic.java
 */
package nttdm.bsys.b_bac.blogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Input;
import nttdm.bsys.common.util.CSVWriter;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;
import org.springframework.context.ApplicationContext;

/**
 * Export Logic to export the search result<br>
 * Note:this class come from RP_B_BAC_S01_02BLogic,only to export<br>
 * so input class and super class same as RP_B_BAC_S01_02BLogic
 * 
 * @author nttdm
 */
public class RP_B_BAC_S01_03BLogic implements BLogic<RP_B_BAC_S01_02Input> {

    private QueryDAO queryDAO;

    // message resource tool
    private MessageResources msgResource = MessageResources.getMessageResources("B_BAC-messages");

    /**
     * @param inputDto
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S01_02Input inputDto) {
        BLogicResult result = new BLogicResult();

        String fileName = "BillingAccountInfoList_YYMMDDHHMMSS.csv".replace("YYMMDDHHMMSS", getSysdateStr());

        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }
        String filePathName = tmpFolder + fileName;

        List<String[]> exportData = new ArrayList<String[]>();

        // add header to export list.
        exportData.add(getExportHeaderItems());

        // get input value
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("tbxCustomerName", StringUtils.trim(inputDto.getTbxCustomerName()));
        param.put("tbxCustomerId", StringUtils.trim(inputDto.getTbxCustomerId()));
        /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
        param.put("cboCustomerType", inputDto.getCboCustomerType());
        /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
        param.put("cboPaymentMethod", inputDto.getCboPaymentMethod());
        param.put("cboBillingCurrency", inputDto.getCboBillingCurrency());
        param.put("tbxBillingAccountNo", StringUtils.trim(inputDto.getTbxBillingAccountNo()));
        param.put("cboPaymentCurrency", inputDto.getCboPaymentCurrency());
        param.put("chkBySingPost", inputDto.getChkBySingPost());
        param.put("chkStatus", inputDto.getChkStatus());
        param.put("chkTotalAmountDue", inputDto.getChkTotalAmountDue());
        param.put("chkEmail", inputDto.getChkEmail());
        // #167 Start
        //param.put("chkDeliveries", inputDto.getChkDeliveries());
        if(inputDto.getChkDeliveries().length == 3){
        	param.put("chkDeliveries", new String[] { "1", "2", "3", "4" });
        }else{
        	param.put("chkDeliveries", inputDto.getChkDeliveries());
        }
        // #167 End

        // status array
        String[] chkStatus = inputDto.getChkStatus();

        for (int i = 0; i < chkStatus.length; i++) {

            if ("N".equalsIgnoreCase(chkStatus[i])) {
                // New
                param.put("statusN", "N");

            } else if ("A".equalsIgnoreCase(chkStatus[i])) {
                // Active
                param.put("statusA", "A");

            } else if ("B".equalsIgnoreCase(chkStatus[i])) {
                // Bill Finished
                param.put("statusB", "B");
            }
        }

        // Total Amount Due array
        String[] chkTotalAmountDue = inputDto.getChkTotalAmountDue();

        for (int i = 0; i < chkTotalAmountDue.length; i++) {

            if ("A".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // =0
                param.put("amountDueA", "A");

            } else if ("B".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // <>0
                param.put("amountDueB", "B");

            } else if ("C".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // <0
                param.put("amountDueC", "C");

            } else if ("D".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // >0
                param.put("amountDueD", "D");
            }
        }

        // get result list
        List<Map<String, Object>> searchResultList = queryDAO.executeForMapList("B_BAC.getBillAccSearchResult", param);

        // for generate address4
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        MappedCodeListLoader countryCodeList = (MappedCodeListLoader) context.getBean("LIST_COUNTRY");
        Map<String, Object> countryMap = countryCodeList.getCodeListMap();

        // loop the list to prepare
        for (int i = 0; i < searchResultList.size(); i++) {

            Map<String, Object> item = searchResultList.get(i);

            String idCust = CommonUtils.toString(item.get("ID_CUST"));
            // CONTACT_TYPE
            String contactType = CommonUtils.toString(item.get("CONTACT_TYPE"));
            // CUST_ADR_TYPE
            String custAdrType = CommonUtils.toString(item.get("CUST_ADR_TYPE"));
            // CUSTOMER_TYPE
            String customerType = CommonUtils.toString(item.get("CUSTOMER_TYPE"));

            // address
            item.put("idCust", idCust);
            item.put("adrType", custAdrType);
            Map<String, Object> address = this.queryDAO.executeForMap("B_BAC.getCustAdr", item);

            if (address != null) {
                item.put("address1", CommonUtils.toString(address.get("ADR_LINE1")));
                item.put("address2", CommonUtils.toString(address.get("ADR_LINE2")));
                item.put("address3", CommonUtils.toString(address.get("ADR_LINE3")));

                String country = "";
                if (countryMap.containsKey(address.get("COUNTRY"))) {
                    country = countryMap.get(address.get("COUNTRY")).toString();
                }
                item.put("address4", CommonUtils.toString(address.get("ZIP_CODE")) + ", " + country);

                item.put("ZIP_CODE", CommonUtils.toString(address.get("ZIP_CODE")));

                item.put("COUNTRY", country);
            }

            // email
            item.put("contactType", contactType);
            Map<String, Object> contactInfo = this.queryDAO.executeForMap("B_BAC.getSingleContact", item);

            // attention
            if (contactInfo != null && !CommonUtils.isEmpty(contactInfo.get("CONTACT_NAME"))) {
                item.put("attention", "[" + contactType + "]" + contactInfo.get("CONTACT_NAME"));

                item.put("CONTACT_NAME", contactInfo.get("CONTACT_NAME"));
            }

            if (!"CONS".equals(customerType)) {
                // get email info
                if (contactInfo != null) {
                    // email
                    item.put("email", CommonUtils.toString(contactInfo.get("EMAIL")));
                }
            } else {
                item.put("email", CommonUtils.toString(item.get("CO_EMAIL")).trim());
            }

            exportData.add(getExportContentItems(item));
        }

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
     * Generate exported content.
     * 
     * @param item
     * @return exported item value
     */
    private String[] getExportContentItems(Map<String, Object> item) {
        List<String> items = new ArrayList<String>();

        // No
        items.add(CommonUtils.toString(item.get("ITEM_NO")));

        // Customer ID
        items.add(CommonUtils.toString(item.get("ID_CUST")));

        // Customer Name
        items.add(CommonUtils.toString(item.get("CUST_NAME")));
        //#273 Add customer type at inquiry screen and export result CT 20170710
        // Customer Type
        String customerType = CommonUtils.toString(item.get("CUSTOMER_TYPE"));
		if("CORP".equals(customerType)){
        	customerType = "Corporate";
        }else{
        	customerType = "Consumer";
        }
        items.add(customerType);
        //#273 Add customer type at inquiry screen and export result CT 20170710
        // Billing Account No
        items.add(CommonUtils.toString(item.get("ID_BILL_ACCOUNT")));

        // By SingPost
        if ("1".equals(CommonUtils.toString(item.get("IS_SINGPOST")))) {
            items.add(msgResource.getMessage("screen.b_bac_s01.bySingPost.yes"));
        } else {
            items.add(msgResource.getMessage("screen.b_bac_s01.bySingPost.no"));
        }
        
        //#272 [4-UAT] Billing Account Export Result - Add new column: By Email CT 20170629
        if("1".equals(item.get("DELIVERY_EMAIL"))){
        	items.add(msgResource.getMessage("screen.b_bac_s01.byEmail.yes"));
        }else{
        	items.add(msgResource.getMessage("screen.b_bac_s01.byEmail.no"));
        }
        //#272 [4-UAT] Billing Account Export Result - Add new column: By Email CT 20170629

        // Payment Method
        String paymentMethod = CommonUtils.toString(item.get("PAYMENT_METHOD"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_PAYMENT_METHOD", paymentMethod));

        // Billing Currency
        items.add(CommonUtils.toString(item.get("BILL_CURRENCY")));

        // Payment Currency
        items.add(CommonUtils.toString(item.get("EXPORT_CURRENCY")));

        // Total Amount Due
        items.add(CommonUtils.toString(item.get("TOTAL_AMT_DUE")));

        // Status
        String D_COUNT = CommonUtils.toString(item.get("D_COUNT"));
        String P_COUNT = CommonUtils.toString(item.get("P_COUNT"));
        if ("0".equals(D_COUNT)) {
            items.add(msgResource.getMessage("screen.b_bac_s01.status.new"));
        } else {
            if ("0".equals(P_COUNT)) {
                items.add(msgResource.getMessage("screen.b_bac_s01.status.billFinished"));
            } else {
                items.add(msgResource.getMessage("screen.b_bac_s01.status.active"));
            }
        }

        // Attention ID
        items.add(CommonUtils.toString(item.get("CONTACT_TYPE")));

        // Attention Name
        items.add(CommonUtils.toString(item.get("CONTACT_NAME")).trim());

        // E-mail
        items.add(CommonUtils.toString(item.get("email")).trim());

        // Address1
        items.add(CommonUtils.toString(item.get("address1")));

        // Address2
        items.add(CommonUtils.toString(item.get("address2")));

        // Address3
        items.add(CommonUtils.toString(item.get("address3")));

        // Postcode
        items.add(CommonUtils.toString(item.get("ZIP_CODE")).trim());

        // Country
        items.add(CommonUtils.toString(item.get("COUNTRY")).trim());

        return items.toArray(new String[items.size()]);
    }

    /**
     * Get CSV file header items.
     * 
     */
    private String[] getExportHeaderItems() {

        List<String> items = new ArrayList<String>();

        // No
        items.add(msgResource.getMessage("screen.b_bac_s01.no"));

        // Customer ID
        items.add(msgResource.getMessage("screen.b_bac_s01.customerId"));

        // Customer Name
        items.add(msgResource.getMessage("screen.b_bac_s01.customerName"));
        
        // Customer Type
        items.add(msgResource.getMessage("screen.b_bac_s01.customerType"));
        
        // Billing Account No
        items.add(msgResource.getMessage("screen.b_bac_s01.billingAccNo"));

        // By SingPost
        // #191 Start
        //items.add(msgResource.getMessage("screen.b_bac_s01.bySingPost"));
        String singPostValue = this.queryDAO.executeForObject("B_BAC.getSingPostValue", null, String.class);
        items.add(singPostValue);
        // #191 End
        
        //#272 [4-UAT] Billing Account Export Result - Add new column: By Email CT 20170629
        items.add(msgResource.getMessage("screen.b_bac_s01.byEmail"));
        //#272 [4-UAT] Billing Account Export Result - Add new column: By Email CT 20170629

        // Payment Method
        items.add(msgResource.getMessage("screen.b_bac_s01.paymentMethod"));

        // Billing Currency
        items.add(msgResource.getMessage("screen.b_bac_s01.billingCurrency"));

        // Payment Currency
        items.add(msgResource.getMessage("screen.b_bac_s01.paymentCurrency"));

        // Total Amount Due
        items.add(msgResource.getMessage("screen.b_bac_s01.totalAmountDue"));

        // Status
        items.add(msgResource.getMessage("screen.b_bac_s01.status"));

        // Attention ID
        items.add(msgResource.getMessage("screen.b_bac_s01.export.attentionId"));

        // Attention Name
        items.add(msgResource.getMessage("screen.b_bac_s01.export.attentionName"));

        // E-mail
        items.add(msgResource.getMessage("screen.b_bac_s01.export.email"));

        // address1
        items.add(msgResource.getMessage("screen.b_bac_s01.export.address1"));

        // address2
        items.add(msgResource.getMessage("screen.b_bac_s01.export.address2"));

        // address3
        items.add(msgResource.getMessage("screen.b_bac_s01.export.address3"));

        // postcode
        items.add(msgResource.getMessage("screen.b_bac_s01.export.postcode"));

        // country
        items.add(msgResource.getMessage("screen.b_bac_s01.export.country"));

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