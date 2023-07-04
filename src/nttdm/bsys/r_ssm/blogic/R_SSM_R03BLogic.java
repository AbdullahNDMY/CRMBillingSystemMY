/**
 * @(#)R_SSM_R03BLogic.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_ssm.blogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CSVWriter;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_ssm.dto.R_SSM_R03Input;

import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.springframework.util.CollectionUtils;

/**
 * Export Result.
 * 
 * @author bench
 */
public class R_SSM_R03BLogic extends AbstractR_SSM_R03BLogic {

    private final static String ERROR_CODE_1 = "1";
    private final static String ERROR_CODE_2 = "2";
    private final static String ERROR_CODE_3 = "3";
    private final static String ERROR_CODE_4 = "4";
    private final static String ERROR_CODE_5 = "5";
    private final static String ERROR_CODE_6 = "6";
    private final static String ERROR_CODE_7 = "7";
    private final static String ERROR_CODE_8 = "8";
    private final static String ERROR_CODE_9 = "9";
    private final static String FILE_NAME = "MailAccountConfigChecking_yyyyMMddHHmmss.csv";
    private MessageResources msgResource = MessageResources.getMessageResources("R_SSM-messages");;

    /*
     * (non-Javadoc)
     * 
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(R_SSM_R03Input input) {

        BLogicResult result = new BLogicResult();

        List<String[]> exportData = new ArrayList<String[]>();

        // add header to export list.
        exportData.add(getExportHeaderItems());

        // Loop all Mail Hosting Customer Plan items that need to check.
        List<Map<String, Object>> itemList = this.queryDAO.executeForMapList("R_SSM.GET_EXPORT_CUSTOMER_PLAN", null);
        if (!CollectionUtils.isEmpty(itemList)) {

            // index
            int no = 1;

            for (int i = 0; i < itemList.size(); i++) {
                Map<String, Object> item = itemList.get(i);

                String custPlanId = CommonUtils.toString(item.get("ID_CUST_PLAN"));
                String uom = CommonUtils.toString(item.get("UOM"));

                // only check Mail Hosting option service set in PBM
                if (!("AMA".equalsIgnoreCase(uom) || "AMQ".equalsIgnoreCase(uom) || "VRS".equalsIgnoreCase(uom)
                        || "ASP".equalsIgnoreCase(uom) || "JMG".equalsIgnoreCase(uom))) {
                    item.put("ERROR_CODE", "");

                    continue;
                }

                String autoUpdate = "";
                String optionId = "";
                String errorCode = "";
                String errorCodeMsg = "";

                // Retrieve mail address and mail account information from SSM.
                Map<String, Object> mailAddress = this.queryDAO.executeForMap("R_SSM.SEARCH_MAIL_ADDR_ACCOUNT", item);
                if (!CollectionUtils.isEmpty(mailAddress)) {
                    if ("AMA".equalsIgnoreCase(uom)) {
                        autoUpdate = CommonUtils.toString(mailAddress.get("AUTO_MAIL_ACC"));
                        optionId = CommonUtils.toString(mailAddress.get("OPT_ADDT_OPTION"));
                    } else if ("AMQ".equalsIgnoreCase(uom)) {
                        autoUpdate = CommonUtils.toString(mailAddress.get("AUTO_MAILBOX_QTY"));
                        optionId = CommonUtils.toString(mailAddress.get("OPT_MAILBOX_QTY"));
                    } else if ("VRS".equalsIgnoreCase(uom)) {
                        autoUpdate = CommonUtils.toString(mailAddress.get("AUTO_VIRUS_SCAN"));
                        optionId = CommonUtils.toString(mailAddress.get("OPT_VIRUS_SCAN"));
                    } else if ("ASP".equalsIgnoreCase(uom)) {
                        autoUpdate = CommonUtils.toString(mailAddress.get("AUTO_ANTI_SPAM"));
                        optionId = CommonUtils.toString(mailAddress.get("OPT_ANTI_SPAM"));
                    } else if ("JMG".equalsIgnoreCase(uom)) {
                        autoUpdate = CommonUtils.toString(mailAddress.get("AUTO_JUNK_MGT"));
                        optionId = CommonUtils.toString(mailAddress.get("OPT_JUNK_MGT"));
                    }
                }

                // Error 1: Auto-update count is not assigned.
                if (!"1".equals(autoUpdate)) {
                    errorCode = ERROR_CODE_1;
                    errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_1");
                } else {

                    // retrive all item's customer plan link id in the same customer plan with same UOM.
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("ID_CUST_PLAN", custPlanId);
                    param.put("ID_CUST_PLAN_LINK", optionId);
                    param.put("UOM", uom);
                    List<Map<String, Object>> linkServiceStatuslist = 
                        this.queryDAO.executeForMapList("R_SSM.SEARCH_CUST_PLAN_LINK_SERVICE_STATUS", param);

                    // Error 2: Mail option is not assigned to correct service group.
                    if (CollectionUtils.isEmpty(linkServiceStatuslist)) {
                        errorCode = ERROR_CODE_2;
                        errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_2");
                    } else {
                        String serviceStatus = CommonUtils.toString(linkServiceStatuslist.get(0).get("SERVICES_STATUS"));
                        String custPlanGrpIdKey = CommonUtils.toString(linkServiceStatuslist.get(0).get("ID_CUST_PLAN_GRP"));
                        boolean hasOtherActiveService = hasOtherService(itemList, i, custPlanId, custPlanGrpIdKey, uom);

                        if ("PS7".equalsIgnoreCase(serviceStatus)) {
                            // Error 3: Mail option is not assigned to the latest service group.
                            if (hasOtherActiveService) {
                                errorCode = ERROR_CODE_3;
                                errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_3");
                            } else {
                                continue;
                            }
                        } else {
                            // Error 4: There are multiple services group for mail option.
                            if (hasOtherActiveService) {
                                errorCode = ERROR_CODE_4;
                                errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_4");
                            } else {
                                int qtyCPM = Integer.parseInt(CommonUtils.toString(item.get("QUANTITY")));
                                int qtySSM = 0;

                                if ("AMA".equalsIgnoreCase(uom)) {
                                    int optBaseQty = Integer.parseInt(CommonUtils.toString(mailAddress.get("OPT_BASE_QTY")));
                                    int mailAccCount = Integer.parseInt(CommonUtils.toString(mailAddress.get("MAILACC_COUNT")));
                                    // (SSM's the actual number of mail account) - (Base Qty) <= 0
                                    // CPM's Additional Mail Account's qty should be 0
                                    if (mailAccCount - optBaseQty <= 0) {
                                        qtySSM = 0;
                                    } else {
                                        // (SSM's the actual number of mail account) - (Base Qty) > 0
                                        // CPM's Additional Mail Account's qty should be same as SSM's the actual number of mail account
                                        qtySSM = mailAccCount - optBaseQty;
                                    }

                                    // Error 5: Additional Mail Account option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_5;
                                        errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_5");
                                    }
                                } else if ("AMQ".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("MAILBOX_QTY")));

                                    // Error 6: Additional Mail Box option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_6;
                                        errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_6");
                                    }
                                } else if ("VRS".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("VIRUS_SCAN_COUNT")));

                                    // Error 7: Virus Scan option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_7;
                                        errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_7");
                                    }
                                } else if ("ASP".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("ANTI_SPAM_COUNT")));

                                    // Error 8: Anti Spam option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_8;
                                        errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_8");
                                    }
                                } else if ("JMG".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("JUNK_MGT_COUNT")));

                                    // Error 9: Junk Management's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_9;
                                        errorCodeMsg = msgResource.getMessage("screen.r_ssm.errorCode_9");
                                    }
                                }
                            }
                        }
                    }
                }

                item.put("ERROR_CODE", errorCode);
                item.put("ERROR_CODE_MSG", errorCodeMsg);
            }

            List<String> errorCodeList = this.getCheckedErrorCode(input);

            // Get all customer plan and service that contains error.
            List<String> errCustPlanId = new ArrayList<String>();
            List<String> errCustPlanGrpId = new ArrayList<String>();
            for (Map<String, Object> item : itemList) {
                if (!CommonUtils.isEmpty(item.get("ERROR_CODE"))
                        && errorCodeList.contains(CommonUtils.toString(item.get("ERROR_CODE")))) {
                    String custPlanId = CommonUtils.toString(item.get("ID_CUST_PLAN"));
                    String custPlanGrpId = CommonUtils.toString(item.get("ID_CUST_PLAN_GRP"));

                    if (!errCustPlanId.contains(custPlanId)) {
                        errCustPlanId.add(custPlanId);
                    }

                    if (!errCustPlanGrpId.contains(custPlanGrpId)) {
                        errCustPlanGrpId.add(custPlanGrpId);
                    }
                }
            }

            for (Map<String, Object> item : itemList) {
                String custPlanId = CommonUtils.toString(item.get("ID_CUST_PLAN"));
                String custPlanGrpId = CommonUtils.toString(item.get("ID_CUST_PLAN_GRP"));

                if (errCustPlanId.contains(custPlanId) && errCustPlanGrpId.contains(custPlanGrpId)) {
                    item.put("ITEM_NO", no++);
                    exportData.add(this.getExportContentItems(item));
                }
            }
        }

        String timeStamp = CommonUtils.formatDate(new Date(), "yyyyMMddHHmmss");
        String fileName = FILE_NAME.replace("yyyyMMddHHmmss", timeStamp);
        String tmpFolder = System.getProperty("java.io.tmpdir");
        if (!tmpFolder.endsWith(File.separator)) {
            tmpFolder = tmpFolder + File.separator;
        }

        String fullFile = tmpFolder + fileName;

        try {
            FileWriter fw = new FileWriter(fullFile);
            CSVWriter writer = new CSVWriter(fw, ',', '\"');
            writer.writeAll(exportData);
            writer.close();
        } catch (IOException e) {
            BLogicMessages errors = new BLogicMessages();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC106"));
            result.setErrors(errors);
        }

        // Set download file
        DownloadFile file = new DownloadFile(new File(fullFile));
        result.setResultObject(file);

        return result;
    }

    /**
     * Get CSV file header items.
     * 
     * @throws IOException
     */
    private String[] getExportHeaderItems() {

        List<String> items = new ArrayList<String>();

        // No
        items.add(msgResource.getMessage("screen.r_ssm.export.header.no", null));

        // Cust ID
        items.add(msgResource.getMessage("screen.r_ssm.export.header.custID"));

        // Customer Name
        items.add(msgResource.getMessage("screen.r_ssm.export.header.customerName"));

        // Sub. ID
        items.add(msgResource.getMessage("screen.r_ssm.export.header.subID"));

        // Service From
        items.add(msgResource.getMessage("screen.r_ssm.export.header.serviceFrom"));

        // Service To
        items.add(msgResource.getMessage("screen.r_ssm.export.header.serviceTo"));

        // Service Status
        items.add(msgResource.getMessage("screen.r_ssm.export.header.serviceStatus"));

        // Billing Description
        items.add(msgResource.getMessage("screen.r_ssm.export.header.billingDescription"));

        // Item Description
        items.add(msgResource.getMessage("screen.r_ssm.export.header.itemDescription"));

        // Category
        items.add(msgResource.getMessage("screen.r_ssm.export.header.category"));

        // Service
        items.add(msgResource.getMessage("screen.r_ssm.export.header.service"));

        // Plan
        items.add(msgResource.getMessage("screen.r_ssm.export.header.plan"));

        // Rate Mode
        items.add(msgResource.getMessage("screen.r_ssm.export.header.rateMode"));

        // Quantity
        items.add(msgResource.getMessage("screen.r_ssm.export.header.quantity"));

        // Unit Price
        items.add(msgResource.getMessage("screen.r_ssm.export.header.unitPrice"));

        // Amount
        items.add(msgResource.getMessage("screen.r_ssm.export.header.amount"));

        // Error Message
        items.add(msgResource.getMessage("screen.r_ssm.export.header.errorMessage"));

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
        items.add(CommonUtils.toString(item.get("ITEM_NO")));

        // Cust ID
        items.add(CommonUtils.toString(item.get("ID_CUST")));

        // Customer Name
        items.add(CommonUtils.toString(item.get("CUST_NAME")));

        // Sub. ID
        items.add(CommonUtils.toString(item.get("ID_SUB_INFO")));

        // Service From
        items.add(CommonUtils.toString(item.get("SVC_START")));

        // Service To
        items.add(CommonUtils.toString(item.get("SVC_END")));

        // Service Status
        String serviceStatus = CommonUtils.toString(item.get("SERVICES_STATUS"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", serviceStatus));

        // Billing Description
        items.add(CommonUtils.toString(item.get("BILL_DESC")));

        // Item Description
        items.add(CommonUtils.toString(item.get("ITEM_DESC")));

        // Category
        items.add(CommonUtils.toString(item.get("SVC_GRP_NAME")));

        // Service
        items.add(CommonUtils.toString(item.get("SVC_DESC")));

        // Plan
        items.add(CommonUtils.toString(item.get("PLAN")));

        // Rate Mode
        String rateMode = CommonUtils.toString(item.get("RATE_MODE"));
        items.add(CommonUtils.getCodeMapListNameByValue("LIST_RATEMODE", rateMode));

        // Quantity
        items.add(CommonUtils.toString(item.get("QUANTITY")));

        // Unit Price
        items.add(CommonUtils.toString(item.get("UNIT_PRICE")));

        // Amount
        items.add(CommonUtils.toString(item.get("TOTAL_AMOUNT")));

        // Error Message
        items.add(CommonUtils.toString(item.get("ERROR_CODE_MSG")));

        return items.toArray(new String[items.size()]);
    }

    /**
     * Check whether there is other active service exist in the same customer plan with same UOM.
     * 
     * @param list
     *            : all customer plan link items
     * @param currentIndex
     *            : current item's index in list
     * @param custPlanIdKey
     *            : current item's customer plan id.
     * @param custPlanGrpIdKey
     *            : current item's customer plan group id.
     * @param uomKey
     *            : current item's uom.
     * @return true if other active service exist.
     */
    private boolean hasOtherService(List<Map<String, Object>> list, int currentIndex, String custPlanIdKey,
            String custPlanGrpIdKey, String uomKey) {

        boolean result = false;

        // Loop from current item to previous items
        for (int i = currentIndex; i > 0; i--) {
            Map<String, Object> item = list.get(i);
            String custPlanId = CommonUtils.toString(item.get("ID_CUST_PLAN"));
            String custPlanGrpId = CommonUtils.toString(item.get("ID_CUST_PLAN_GRP"));

            if (custPlanId.equals(custPlanIdKey)) {
                if (custPlanGrpId.equals(custPlanGrpIdKey)) {
                    // no need to check the same service.
                    continue;
                } else {
                    String serviceStatus = CommonUtils.toString(item.get("SERVICES_STATUS"));
                    String crrentUom = CommonUtils.toString(item.get("UOM"));

                    if (crrentUom.equalsIgnoreCase(uomKey)
                            && ("PS0".equalsIgnoreCase(serviceStatus) || "PS1".equalsIgnoreCase(serviceStatus)
                                    || "PS2".equalsIgnoreCase(serviceStatus) || "PS3".equalsIgnoreCase(serviceStatus))) {

                        // active service exists.
                        result = true;
                    }
                }
            } else {
                // no need to check different customer plan.
                break;
            }
        }

        // Loop from current item to following items.
        for (int i = currentIndex + 1; i < list.size(); i++) {
            Map<String, Object> item = list.get(i);
            String custPlanId = CommonUtils.toString(item.get("ID_CUST_PLAN"));
            String custPlanGrpId = CommonUtils.toString(item.get("ID_CUST_PLAN_GRP"));

            if (custPlanId.equals(custPlanIdKey)) {
                if (custPlanGrpId.equals(custPlanGrpIdKey)) {
                    // no need to check the same service.
                    continue;
                } else {
                    String serviceStatus = CommonUtils.toString(item.get("SERVICES_STATUS"));
                    String crrentUom = CommonUtils.toString(item.get("UOM"));

                    if (crrentUom.equalsIgnoreCase(uomKey)
                            && ("PS0".equalsIgnoreCase(serviceStatus) || "PS1".equalsIgnoreCase(serviceStatus)
                                    || "PS2".equalsIgnoreCase(serviceStatus) || "PS3".equalsIgnoreCase(serviceStatus))) {

                        // active service exists.
                        result = true;
                    }
                }
            } else {
                // no need to check different customer plan.
                break;
            }
        }

        return result;
    }

    /**
     * Retrieved user selected Error Code.
     * 
     * @param input
     * @return
     */
    private List<String> getCheckedErrorCode(R_SSM_R03Input input) {

        List<String> checkErrorCodeLst = new ArrayList<String>();
        if (!CommonUtils.isEmpty(input.getChkErrorCode1())) {
            checkErrorCodeLst.add(input.getChkErrorCode1());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode2())) {
            checkErrorCodeLst.add(input.getChkErrorCode2());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode3())) {
            checkErrorCodeLst.add(input.getChkErrorCode3());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode4())) {
            checkErrorCodeLst.add(input.getChkErrorCode4());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode5())) {
            checkErrorCodeLst.add(input.getChkErrorCode5());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode6())) {
            checkErrorCodeLst.add(input.getChkErrorCode6());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode7())) {
            checkErrorCodeLst.add(input.getChkErrorCode7());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode8())) {
            checkErrorCodeLst.add(input.getChkErrorCode8());
        }
        if (!CommonUtils.isEmpty(input.getChkErrorCode9())) {
            checkErrorCodeLst.add(input.getChkErrorCode9());
        }

        if (checkErrorCodeLst.size() == 0) {
            checkErrorCodeLst.add(input.getChkErrorCode1());
            checkErrorCodeLst.add(input.getChkErrorCode2());
            checkErrorCodeLst.add(input.getChkErrorCode3());
            checkErrorCodeLst.add(input.getChkErrorCode4());
            checkErrorCodeLst.add(input.getChkErrorCode5());
            checkErrorCodeLst.add(input.getChkErrorCode6());
            checkErrorCodeLst.add(input.getChkErrorCode7());
            checkErrorCodeLst.add(input.getChkErrorCode8());
            checkErrorCodeLst.add(input.getChkErrorCode9());
        }

        return checkErrorCodeLst;
    }
}
