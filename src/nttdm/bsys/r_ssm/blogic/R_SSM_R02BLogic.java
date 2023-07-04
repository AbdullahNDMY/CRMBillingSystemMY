/**
 * @(#)R_SSM_R02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_ssm.blogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_ssm.dto.R_SSM_R02Input;
import nttdm.bsys.r_ssm.dto.R_SSM_R02Output;

import org.apache.struts.action.ActionMessages;
import org.springframework.util.CollectionUtils;

/**
 * 
 * Check Start click event logic.
 * 
 * @author bench
 */
public class R_SSM_R02BLogic extends AbstractR_SSM_R02BLogic {

    private final static String ERROR_CODE_1 = "1";
    private final static String ERROR_CODE_2 = "2";
    private final static String ERROR_CODE_3 = "3";
    private final static String ERROR_CODE_4 = "4";
    private final static String ERROR_CODE_5 = "5";
    private final static String ERROR_CODE_6 = "6";
    private final static String ERROR_CODE_7 = "7";
    private final static String ERROR_CODE_8 = "8";
    private final static String ERROR_CODE_9 = "9";

    /*
     * (non-Javadoc)
     * 
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(R_SSM_R02Input input) {
        BLogicResult result = new BLogicResult();
        R_SSM_R02Output output = new R_SSM_R02Output();
        List<Map<String, Object>> searchResult = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> displayList = new ArrayList<Map<String, Object>>();

        // R-SSM user access right
        String accessType = CommonUtils.getAccessRight(input.getUvo(), "R-SSM");

        // description maximum length
        Integer descMaxLength = CommonUtils.getSystemLength(queryDAO);

        // record count display in one page
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = 0;

        // All Customer Plan items that need to check.
        List<Map<String, Object>> itemList = this.queryDAO.executeForMapList("R_SSM.SEARCH_CUST_PLAN_ALL_ITEMS", null);

        if (!CollectionUtils.isEmpty(itemList)) {

            // ERROR CHECK
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
                    } else {
                        String serviceStatus = CommonUtils.toString(linkServiceStatuslist.get(0).get("SERVICES_STATUS"));
                        String custPlanGrpIdKey = CommonUtils.toString(linkServiceStatuslist.get(0).get("ID_CUST_PLAN_GRP"));
                        boolean hasOtherActiveService = hasOtherService(itemList, i, custPlanId, custPlanGrpIdKey, uom);

                        if ("PS7".equalsIgnoreCase(serviceStatus)) {
                            // Error 3: Mail option is not assigned to the latest service group.
                            if (hasOtherActiveService) {
                                errorCode = ERROR_CODE_3;
                            } else {
                                continue;
                            }
                        } else {
                            // Error 4: There are multiple services group for mail option.
                            if (hasOtherActiveService) {
                                errorCode = ERROR_CODE_4;
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
                                        // SSM's the actual number of mail account) - (Base Qty) > 0
                                        // CPM's Additional Mail Account's qty should be same as SSM's the actual number of mail account
                                        qtySSM = mailAccCount - optBaseQty;
                                    }

                                    // Error 5: Additional Mail Account option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_5;
                                    }
                                } else if ("AMQ".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("MAILBOX_QTY")));

                                    // Error 6: Additional Mail Box option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_6;
                                    }
                                } else if ("VRS".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("VIRUS_SCAN_COUNT")));

                                    // Error 7: Virus Scan option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_7;
                                    }
                                } else if ("ASP".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("ANTI_SPAM_COUNT")));

                                    // Error 8: Anti Spam option's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_8;
                                    }
                                } else if ("JMG".equalsIgnoreCase(uom)) {
                                    qtySSM = Integer.parseInt(CommonUtils.toString(mailAddress.get("JUNK_MGT_COUNT")));

                                    // Error 9: Junk Management's quantity is not correct.
                                    if (qtyCPM != qtySSM) {
                                        errorCode = ERROR_CODE_9;
                                    }
                                }
                            }
                        }
                    }
                }

                item.put("ERROR_CODE", errorCode);
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

            // Organize data structure for screen display.
            List<Map<String, Object>> subPlanList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> subPlanLinkList = new ArrayList<Map<String, Object>>();
            String custPlanId = "";
            String custPlanGrpId = "";
            for (int i = 0; i < itemList.size(); i++) {
                Map<String, Object> item = itemList.get(i);
                String errorCode = CommonUtils.toString(item.get("ERROR_CODE"));

                // display all items in one service even has no error.
                String custPlanIdCur = CommonUtils.toString(item.get("ID_CUST_PLAN"));
                String custPlanGrpIdCur = CommonUtils.toString(item.get("ID_CUST_PLAN_GRP"));

                if (!(errCustPlanId.contains(custPlanIdCur) && errCustPlanGrpId.contains(custPlanGrpIdCur))) {
                    continue;
                }

                if (custPlanId.equals(custPlanIdCur)) {
                    // current item and previous item are in the same plan.
                    // Get Plan information
                    Map<String, Object> plan = searchResult.get(searchResult.size() - 1);

                    if (custPlanGrpId.equals(custPlanGrpIdCur)) {
                        // current item and previous item are in the same service.
                        // add item error code to service.
                        Map<String, Object> subPlan = subPlanList.get(subPlanList.size() - 1);
                        String existErrCode = CommonUtils.toString(subPlan.get("ERROR_CODE"));
                        if (errorCode.length() > 0) {
                            if (!existErrCode.contains(errorCode)) {
                                if (existErrCode.length() > 0) {
                                    errorCode = existErrCode + "," + errorCode;
                                }
                            } else {
                                // no need to add duplicate error code.
                                errorCode = existErrCode;
                            }

                            subPlan.put("ERROR_CODE", this.sortString(errorCode));
                        }

                        // add item to previous service
                        item.put("ITEM_DESC", CommonUtils.formatString(item.get("ITEM_DESC"), descMaxLength));
                        subPlanLinkList.add(item);
                    } else {

                        custPlanGrpId = custPlanGrpIdCur;
                        subPlanLinkList = new ArrayList<Map<String, Object>>();

                        // set Service information and add to plan.
                        Map<String, Object> subPlan = new HashMap<String, Object>();
                        subPlan.put("ID_CUST_PLAN_GRP", custPlanGrpId);
                        subPlan.put("BILL_DESC", CommonUtils.formatString(item.get("BILL_DESC"), descMaxLength));
                        subPlan.put("SVC_START", item.get("SVC_START"));
                        subPlan.put("SVC_END", item.get("SVC_END"));
                        subPlan.put("SERVICES_STATUS", item.get("SERVICES_STATUS"));
                        subPlan.put("BILLING_STATUS", item.get("BILLING_STATUS"));
                        subPlan.put("ERROR_CODE", errorCode);
                        subPlanList.add(subPlan);
                        plan.put("SUB_PLAN", subPlanList);

                        // set plan link item information and add to service.
                        item.put("ITEM_DESC", CommonUtils.formatString(item.get("ITEM_DESC"), descMaxLength));
                        subPlanLinkList.add(item);
                        subPlan.put("SUB_PLAN_LINK", subPlanLinkList);
                    }
                } else {
                    custPlanId = custPlanIdCur;
                    custPlanGrpId = custPlanGrpIdCur;
                    subPlanList = new ArrayList<Map<String, Object>>();
                    subPlanLinkList = new ArrayList<Map<String, Object>>();

                    // set Plan information and add to display result list.
                    Map<String, Object> plan = new HashMap<String, Object>();
                    plan.put("ID_CUST_PLAN", custPlanId);
                    plan.put("PLAN_STATUS", item.get("PLAN_STATUS"));
                    plan.put("ID_SUB_INFO", item.get("ID_SUB_INFO"));
                    plan.put("ID_CUST", item.get("ID_CUST"));
                    plan.put("CUST_NAME", item.get("CUST_NAME"));
                    plan.put("CUSTOMER_TYPE", item.get("CUSTOMER_TYPE"));
                    searchResult.add(plan);

                    // set Service information and add to plan.
                    Map<String, Object> subPlan = new HashMap<String, Object>();
                    subPlan.put("ID_CUST_PLAN_GRP", custPlanGrpId);
                    subPlan.put("BILL_DESC", CommonUtils.formatString(item.get("BILL_DESC"), descMaxLength));
                    subPlan.put("SVC_START", item.get("SVC_START"));
                    subPlan.put("SVC_END", item.get("SVC_END"));
                    subPlan.put("SERVICES_STATUS", item.get("SERVICES_STATUS"));
                    subPlan.put("BILLING_STATUS", item.get("BILLING_STATUS"));
                    subPlan.put("ERROR_CODE", errorCode);
                    subPlanList.add(subPlan);
                    plan.put("SUB_PLAN", subPlanList);

                    // set plan link item information and add to service.
                    item.put("ITEM_DESC", CommonUtils.formatString(item.get("ITEM_DESC"), descMaxLength));
                    subPlanLinkList.add(item);
                    subPlan.put("SUB_PLAN_LINK", subPlanLinkList);
                }
            }

        }

        // total search result count
        Integer totalCount = searchResult.size();

        if (totalCount <= 0) {
            // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            msgs.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC002", new Object()));
            result.setMessages(msgs);
        } else {
            startIndex = input.getStartIndex();
            if (startIndex == null || startIndex < 0 || totalCount < startIndex) {
                startIndex = 0;
            }

            Integer endIndex = startIndex + row;
            if (endIndex > totalCount) {
                endIndex = totalCount;
            }

            displayList = searchResult.subList(startIndex, endIndex);
        }

        output.setInitFlg("1");
        output.setChkErrorCodeAll(input.getChkErrorCodeAll());
        output.setChkErrorCode1(input.getChkErrorCode1());
        output.setChkErrorCode2(input.getChkErrorCode2());
        output.setChkErrorCode3(input.getChkErrorCode3());
        output.setChkErrorCode4(input.getChkErrorCode4());
        output.setChkErrorCode5(input.getChkErrorCode5());
        output.setChkErrorCode6(input.getChkErrorCode6());
        output.setChkErrorCode7(input.getChkErrorCode7());
        output.setChkErrorCode8(input.getChkErrorCode8());
        output.setChkErrorCode9(input.getChkErrorCode9());
        output.setAccessType(accessType);
        output.setRow(row);
        output.setStartIndex(startIndex);
        output.setTotalRow(totalCount);
        output.setSearchResult(displayList);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
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
     * Sort String.
     * 
     * @param data
     *            : Disorder String
     * @return Sorted string
     */
    private String sortString(String data) {

        String result = data;

        if (!CommonUtils.isEmpty(data)) {
            String[] dataItems = data.split(",");
            Arrays.sort(dataItems);

            StringBuffer sb = new StringBuffer();

            for (String item : dataItems) {
                sb.append(item).append(",");
            }

            result = sb.substring(0, sb.length() - 1);
        }

        return result;
    }

    /**
     * Retrieved user selected Error Code.
     * 
     * @param input
     * @return
     */
    private List<String> getCheckedErrorCode(R_SSM_R02Input input) {

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
