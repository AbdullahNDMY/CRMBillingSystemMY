/*
 * @(#)RP_B_BAC_S02_05_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_05_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_05_01Output;
import nttdm.bsys.common.bean.G_BIL_P01_Input;
import nttdm.bsys.common.bean.G_BIL_P01_Output;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.E_EXP_F02;
import nttdm.bsys.common.util.G_BAC_P01;
import nttdm.bsys.common.util.G_BIL_P01;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.dto.E_EXP_F02Output;
import nttdm.bsys.common.util.dto.G_BAC_P01_Input;
import nttdm.bsys.common.util.dto.G_BAC_P01_Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_05_01BLogic extends AbstractRP_B_BAC_S02_05_01BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    @SuppressWarnings("unchecked")
    public BLogicResult execute(RP_B_BAC_S02_05_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S02_05_01Output outputDTO = new RP_B_BAC_S02_05_01Output();
        // get the number
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        HashMap<String, Object> m = new HashMap<String, Object>();
        String idCustPlan = param.getIdCustPlan();
        String idBillAccount = param.getIdBillAccount();
        String clickBtnTypeFlg = param.getClickBtnTypeFlg();
        BillingSystemUserValueObject uvo = param.getUvo();
        // String idCust = param.getCboCustomerName();
        // setup parameters
        m.put("idBillAccount", idBillAccount);
        m.put("idCustPlan", idCustPlan);

        //update Total Amt due button
        if("updateTotalAmtDue".equals(clickBtnTypeFlg)) {
            G_BAC_P01 gBacP01 = new G_BAC_P01();
            gBacP01.setQueryDAO(queryDAO);
            gBacP01.setUpdateDAO(updateDAO);
            G_BAC_P01_Input gBacP01Input = new G_BAC_P01_Input();
            gBacP01Input.setType("B");
            gBacP01Input.setId_bill_account(idBillAccount);
            gBacP01Input.setId_login(uvo.getId_user());
            gBacP01Input.setAuditIdModule(BillingSystemConstants.MODULE_B);
            gBacP01Input.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_B_BAC);
            gBacP01Input.setAuditReference(idBillAccount);
            G_BAC_P01_Output gBacP01Output = new G_BAC_P01_Output();
            gBacP01.execute(gBacP01Input, gBacP01Output);
            if("0".equals(gBacP01Output.getStatus())) {
                //success
                outputDTO.setSuccessMsg(gBacP01Output.getMSG());
            } else {
                //fail
                outputDTO.setLastMsg(gBacP01Output.getMSG());
            }
        }
        
        // get header info
        HashMap<String, Object> billAccountInfo =
            (HashMap<String, Object>) queryDAO.executeForMap(
                "B_BAC.getBillingAccountInfo", m);
        // get cust info
        m.put("idCust", billAccountInfo.get("ID_CUST"));
        HashMap<String, Object> custInfo =
            (HashMap<String, Object>) queryDAO.executeForMap(
                "B_BAC.getCustInfo", m);
        // get list contact
        List<LabelValueBean> listContact =
            this.queryDAO.executeForObjectList("B_BAC.getAllContact", m);
        // generate address info
        m.put("adrType", billAccountInfo.get("CUST_ADR_TYPE"));
        HashMap<String, Object> address =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "B_BAC.getCustAdr", m);
        HashMap<String, Object> addressInfo = new HashMap<String, Object>();
        if (address != null) {
            addressInfo.put("address1",
                CommonUtils.toString(address.get("ADR_LINE1")));
            addressInfo.put("address2",
                CommonUtils.toString(address.get("ADR_LINE2")));
            addressInfo.put("address3",
                CommonUtils.toString(address.get("ADR_LINE3")));
            // generate address4
            ApplicationContext context =
                ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader countryCodeList =
                (MappedCodeListLoader) context.getBean("LIST_COUNTRY");
            Map<String, Object> countryMap = countryCodeList.getCodeListMap();
            String country = "";
            if (countryMap.containsKey(address.get("COUNTRY"))) {
                country = countryMap.get(address.get("COUNTRY")).toString();
            }
            addressInfo.put("address4",
                CommonUtils.toString(
                    address.get("ZIP_CODE")) + ", " + country);;
        }
        // get email info
        m.put("contactType", billAccountInfo.get("CONTACT_TYPE"));
        HashMap<String, Object> contactInfo =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "B_BAC.getSingleContact", m);
        if (contactInfo != null) {
            addressInfo.put("email",
                CommonUtils.toString(contactInfo.get("EMAIL")));
        }
        // get bill ref info
        List<HashMap<String, Object>> billRefInfo =
            this.queryDAO.executeForObjectList("B_BAC.getDistBillRef", m);


        // check if any plan in list plan info satisfy the condition to display
        // Generate Terminate CN
        String isDisplayTerCnBut = "0";

        outputDTO.setIsDisplayTerCnBut(isDisplayTerCnBut);
        // get condition to display bill ref
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> displayBillRefInfo =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "B_BAC.getBillRefDisplayCond", m2);
        if (CommonUtils.isEmpty(displayBillRefInfo)) {
            outputDTO.setBillRefDispCond("0");
            outputDTO.setBillRefDispCondSess("0");
        } else {
            outputDTO.setBillRefDispCond("1");
            outputDTO.setBillRefDispCondSess("1");
        }
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // set accessType
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> userAccess =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "B_BAC.getAccessType", m1);
        if (!CommonUtils.isEmpty(userAccess)) {
            outputDTO.setAccessType(
                CommonUtils.toString(userAccess.get("ACCESS_TYPE")));
        }
        billAccountInfo.put("idCustPlan", idCustPlan);
        outputDTO.setBillRefInfo(billRefInfo);
        outputDTO.setContactInfo(contactInfo);
        outputDTO.setHeaderInfo(billAccountInfo);
        outputDTO.setListContact(listContact);
        outputDTO.setAddressInfo(addressInfo);
        outputDTO.setCustInfo(custInfo);
        outputDTO.setBss(bss);
        outputDTO.setBillRefInfoSess(billRefInfo);
        
        // get M_GSET_D info
        List<Object> mGSetDInfoList = (List<Object>) this.queryDAO.executeForObjectList("B_BAC.getMGSetDInfo", null);
        String bacType = "";
        if (!CommonUtils.isEmpty(mGSetDInfoList) && mGSetDInfoList.contains("BAC")) {
            bacType = "BAC";
        }
        outputDTO.setBacType(bacType);
        
        //generateBilling button click 
        if ("generateBilling".equals(clickBtnTypeFlg)) {
            G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
            G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
            gCsbP01CheckInput.setMessageParam1("Generate Billing");
            gCsbP01CheckInput.setMessageParam2("for this billing account");
            gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
            G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
            boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
            
            if (!resultFlg) {
                String errorMsg = gCsbP01CheckOutput.getMessageContext();
                outputDTO.setLastMsg(errorMsg);
            } else {
                // Call G_BIL_P01
                G_BIL_P01 gBilP01 = new G_BIL_P01(this.queryDAO, this.updateDAO);
                G_BIL_P01_Input gBilP01Input = new G_BIL_P01_Input();
                gBilP01Input.setModuleId("B");
                gBilP01Input.setBillAccountId(idBillAccount);
                gBilP01Input.setUserId(param.getUvo().getId_user());
                gBilP01.setAuditIdModule(BillingSystemConstants.MODULE_B);
                gBilP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_B_BAC);
                gBilP01.setAuditReference(idBillAccount);
                // #192 Start
                gBilP01Input.setBillingOption("0");
                // #192 End
                G_BIL_P01_Output gBilP01Output = gBilP01.execute(gBilP01Input);
                // generate and display msg follow the condition from G_BIL_P01
                if (!gBilP01Output.getBatchStatus().equals("1")) {
                    // display msgs from batch
                    outputDTO.setLastMsg(gBilP01Output.getErrorMessage());
                } else {
                    List<String> idRefList = gBilP01Output.getIdRefList();
                    String idRefStr = "";
                    for (int i = 0; i < idRefList.size(); i++) {
                        if (i != idRefList.size() - 1) {
                            idRefStr += idRefList.get(i) + ",";
                        } else {
                            idRefStr += idRefList.get(i);
                        }
                    }
                    BLogicMessages msgs = new BLogicMessages();
                    BLogicMessage msg =
                        new BLogicMessage("info.ERR2SC044", new Object[] {idRefStr});
                    msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                    result.setMessages(msgs);
                }
            }
        } else if ("generateStatement".equals(clickBtnTypeFlg)){
            //Generate Statement button click
            String popupClickYesFlg = CommonUtils.toString(param.getPopupClickYesFlg());
            String idCust = CommonUtils.toString(billAccountInfo.get("ID_CUST")).trim();
            E_EXP_F02Output e_exp_f02Output = null;
            boolean callExpF02Flg = false;
            //not click yes
            if(CommonUtils.isEmpty(popupClickYesFlg)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                Date systemDate = new Date();
                String yyyy = sdf.format(systemDate);
                String mm = sdf1.format(systemDate);
                String yearMonth = yyyy + mm;
                Map<String, Object> sqlParam = new HashMap<String, Object>();
                sqlParam.put("idCust", idCust);
                sqlParam.put("yearMonth", yearMonth);
                sqlParam.put("idBillAccount", idBillAccount.trim());
                //get statement info
                List<Map<String, Object>> listStatementInfo = queryDAO.executeForMapList("B_BAC.getStatementInfo", sqlParam);
                // has statement info
                if (listStatementInfo!=null && 0<listStatementInfo.size()) {
                    String enMM = getMonthStr(Integer.parseInt(mm));
                    StringBuffer popupInfo = new StringBuffer();
                    popupInfo.append("Warning<br/>")
                                .append("Statement was").append(" already generated for month of ").append(enMM).append(" ").append(yyyy).append("<br/>")
                                .append("Do you want to proceed?<br/>")
                                .append("Click Yes to proceed, No to cancel.");
                    
                    outputDTO.setPopupInfo(popupInfo.toString());
                } else {
                    callExpF02Flg = true;
                }
            } else {
                // click yes
                outputDTO.setPopupClickYesFlg("");
                callExpF02Flg = true;
            }
            if(callExpF02Flg) {
                G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
                G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
                gCsbP01CheckInput.setMessageParam1("Generate Statement");
                gCsbP01CheckInput.setMessageParam2("for this billing account");
                gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
                G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
                boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
                
                if (!resultFlg) {
                    String errorMsg = gCsbP01CheckOutput.getMessageContext();
                    outputDTO.setLastMsg(errorMsg);
                } else {
                    e_exp_f02Output = callExpF02(idCust, uvo.getId_user(), idBillAccount);
                    if (e_exp_f02Output!=null) {
                        String batchStatus = e_exp_f02Output.getBatchStatus();
                        //Success
                        if ("1".equals(batchStatus)) {
                            List<String> listStatementNo = e_exp_f02Output.getListStatementNo();
                            String statementNo = "";
                            for (int i = 0; i < listStatementNo.size(); i++) {
                                if (i != listStatementNo.size() - 1) {
                                    statementNo += listStatementNo.get(i) + ",";
                                } else {
                                    statementNo += listStatementNo.get(i);
                                }
                            }
                            BLogicMessages msgs = new BLogicMessages();
                            BLogicMessage msg =
                                new BLogicMessage("info.ERR2SC051", new Object[] {statementNo});
                            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                            result.setMessages(msgs);
                        } else {
                            // display msgs from batch
                            String[] errorMsgArr = e_exp_f02Output.getMsg();
                            if(errorMsgArr!=null && 0<errorMsgArr.length) {
                                String errorMsg = "";
                                for (int i = 0; i < errorMsgArr.length; i++) {
                                    if (i != errorMsgArr.length - 1) {
                                        errorMsg += errorMsgArr[i] + "<br/>";
                                    } else {
                                        errorMsg += errorMsgArr[i];
                                    }
                                }
                                outputDTO.setLastMsg(errorMsg);
                            }
                        }
                    }
                }
            }
        }
        // list plan info
        List<HashMap<String, Object>> listPlanInfo =
            this.queryDAO.executeForObjectList("B_BAC.getPlanInfo", m);
        // new newListPlanInfo
        List<HashMap<String, Object>> newListPlanInfo =
            new ArrayList<HashMap<String, Object>>();
        RP_B_BAC_commonBLogic logic = new RP_B_BAC_commonBLogic();
        newListPlanInfo = logic.getListInfo(listPlanInfo);
        // store in session for editing function
        outputDTO.setListPlanInfoSess(newListPlanInfo);
        
        outputDTO.setListPlanInfo(newListPlanInfo);
        // MessageUtil.get(key, holders)
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
    
    /**
     * call E_EXP_F02
     * @param idCust
     * @param idLogin
     * @return
     */
    private E_EXP_F02Output callExpF02(String idCust, String idLogin, String idBillAccount) {
        E_EXP_F02Output e_exp_f02Output = new E_EXP_F02Output();
        E_EXP_F02 exp_f02 = new E_EXP_F02();
        exp_f02.setQueryDAO(queryDAO);
        exp_f02.setUpdateDAO(updateDAO);
        exp_f02.setId_login(idLogin);
        exp_f02.setIdBillAccount(idBillAccount);
        exp_f02.setId_cust(idCust);
        exp_f02.setEset_rundate(new Date());
        exp_f02.setCurStmtMonth("1");
        exp_f02.setBatch("B");
        exp_f02.excute(e_exp_f02Output);
        return e_exp_f02Output;
    }
    
    /**
     * get English month
     * @param mm
     * @return
     */
    private String getMonthStr(int mm) {
        String enMM = "";
        if(1==mm) {
            enMM = "Jan";
        } else if(2==mm) {
            enMM = "Feb";
        } else if(3==mm) {
            enMM = "Mar";
        } else if(4==mm) {
            enMM = "Apr";
        } else if(5==mm) {
            enMM = "May";
        } else if(6==mm) {
            enMM = "Jun";
        } else if(7==mm) {
            enMM = "Jul";
        } else if(8==mm) {
            enMM = "Aug";
        } else if(9==mm) {
            enMM = "Sep";
        } else if(10==mm) {
            enMM = "Oct";
        } else if(11==mm) {
            enMM = "Nov";
        } else if(12==mm) {
            enMM = "Dec";
        }
        return enMM;
    }
}