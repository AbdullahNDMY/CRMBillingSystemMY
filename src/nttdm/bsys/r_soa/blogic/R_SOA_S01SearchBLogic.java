/**
 * @(#)R_SOA_S01SearchBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/03
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.E_EXP_F02;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.MessageUtil;
import nttdm.bsys.common.util.dto.E_EXP_F02Output;
import nttdm.bsys.r_soa.dto.R_SOA_S01SearchInput;
import nttdm.bsys.r_soa.dto.R_SOA_S01SearchOutput;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * @author gplai
 *
 */
public class R_SOA_S01SearchBLogic extends AbstractR_SOA_S01SearchBLogic {

    private BLogicMessages errors;
    private BLogicMessages msgs;
    
    private List<String> errorMesgInfo;
    
    public BLogicResult execute(R_SOA_S01SearchInput params) {
        BLogicResult result = new BLogicResult();
        // ERROR MESSAGE
        errors = new BLogicMessages();
        msgs = new BLogicMessages();
        errorMesgInfo = new ArrayList<String>();
        
        R_SOA_S01SearchOutput outputDTO = new R_SOA_S01SearchOutput(); 
        String clickBtnTypeFlg = params.getClickBtnTypeFlg();
        BillingSystemUserValueObject uvo = params.getUvo();
        //Click search button
        if ("search".equals(clickBtnTypeFlg) || "pageClick".equals(clickBtnTypeFlg)) {
            if(checkInput(params)) {
                Map<String, Object> m = new HashMap<String, Object>();
                //set value to sql param
                setParamVale(m, params);
                if ("search".equals(clickBtnTypeFlg)) {
                    params.setStartIndex(new Integer(0));
                }
                //Search
                search(params, m, outputDTO);
                //click Search button
                if("search".equals(clickBtnTypeFlg)) {
                    String selectedStatementNo[] = {};
                    outputDTO.setSelectedStatementNo(selectedStatementNo);
                }
                
                outputDTO.setClickBtnTypeFlg("search");
                outputDTO.setLastMsg("");
            }
        } else {
            //Click update statement button
            Map<String, Object> m = new HashMap<String, Object>();
            //set value to sql param
            setPreParamValue(m, params);
            
            if(btnUpdateStatementClickcheckInput(params)){
                String cboStatementMonth = CommonUtils.toString(params.getCboStatementMonth()).trim();
                String tbxStatementYear = CommonUtils.toString(params.getTbxStatementYear()).trim();
                String tbxStatementYearMonth = tbxStatementYear + cboStatementMonth;
                Calendar now=Calendar.getInstance();
                int year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH)+1;
                String currentYearMonth = CommonUtils.toString(year) + CommonUtils.toString(month);
                String preYearMonth = CommonUtils.toString(year) + CommonUtils.toString(month - 1);
                String curStmtMonth = "0";
                if (currentYearMonth.equals(tbxStatementYearMonth)) {
                    curStmtMonth = "1";
                }
                if (preYearMonth.equals(tbxStatementYearMonth)) {
                    curStmtMonth = "0";
                }
                E_EXP_F02Output e_exp_f02Output = callExpF02(curStmtMonth, uvo.getId_user(), params.getSelectedStatementNo());
                
                String batchStatus = e_exp_f02Output.getBatchStatus();
                //Success
                if ("1".equals(batchStatus)) {
                    List<String> listStatementNo = e_exp_f02Output.getListStatementNo();
                    String statementNo = "";
                    for (int i = 0; i < listStatementNo.size(); i++) {
                        if (i != listStatementNo.size() - 1) {
                            statementNo += listStatementNo.get(i) + ", ";
                        } else {
                            statementNo += listStatementNo.get(i);
                        }
                    }
                    BLogicMessage msg = new BLogicMessage("info.ERR2SC051", new Object[] {statementNo});
                    msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
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
            } else {
                String errorMsg = "";
                for (int i = 0; i < errorMesgInfo.size(); i++) {
                    if (i != errorMesgInfo.size() - 1) {
                        errorMsg += errorMesgInfo.get(i) + "<br/>";
                    } else {
                        errorMsg += errorMesgInfo.get(i);
                    }
                }
                outputDTO.setLastMsg(errorMsg);
            }
            //Search
            search(params, m, outputDTO);
            
            outputDTO.setClickBtnTypeFlg("search");
        }
        // payment currency combo box array
        Object[] cboPaymentCurrencyArray = this.queryDAO.executeForObjectArray("R_SOA.getGrandTotal", null, String.class);
        outputDTO.setCboPaymentCurrencyArray((String[]) cboPaymentCurrencyArray);
        
        result.setResultObject(outputDTO); 
        result.setErrors(errors);
        result.setMessages(msgs);
        result.setResultString("success");
        return result;
    }
    
    private R_SOA_S01SearchOutput search(R_SOA_S01SearchInput params, Map<String, Object> m, R_SOA_S01SearchOutput outputDTO) {
      //get the number of row for paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        
        int row = bss.getResultRow();
        Integer startIndex = params.getStartIndex();
        
        List<HashMap<String, Object>> listStatementAll = this.queryDAO.executeForObjectList("R_SOA.getT_AR_STMT_H", m);
        List<HashMap<String, Object>> listStatement = new ArrayList<HashMap<String, Object>>();
        int totalReport = listStatementAll.size();
        String fullStmtNo = "";
        List<String> listExistselectedStatementNo = new ArrayList<String>();
        if (totalReport == 0) {
            msgs.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC002", new Object()));
        } else {
            if (startIndex == null || startIndex < 0 || startIndex > totalReport) {
                startIndex = 0;
            }
            int endIndex = row;
            if (row > (totalReport-startIndex)){
                endIndex = startIndex + (totalReport-startIndex);
            } else {
                endIndex = startIndex + row;
            }
            for(int i=startIndex;i<endIndex;i++) {
                listStatement.add(listStatementAll.get(i));
            }
            String[] selectedStatementNo = params.getSelectedStatementNo();
            
            if(selectedStatementNo!=null && 0<selectedStatementNo.length) {
                for(int i=0;i<selectedStatementNo.length;i++) {
                    boolean isExist = false;
                    if(i==0) {
                        for(HashMap<String, Object> map : listStatementAll) {
                            String ID_STMT = CommonUtils.toString(map.get("ID_STMT"));
                            fullStmtNo = fullStmtNo + ID_STMT+",";
                            if(ID_STMT.equals(selectedStatementNo[i])) {
                                isExist = true;
                            }
                        }
                    } else {
                        for(HashMap<String, Object> map : listStatementAll) {
                            String ID_STMT = CommonUtils.toString(map.get("ID_STMT"));
                            if(ID_STMT.equals(selectedStatementNo[i])) {
                                isExist = true;
                                break;
                            }
                        }
                    }
                    if (isExist) {
                        listExistselectedStatementNo.add(selectedStatementNo[i]);
                    }
                }
            } else {
                for(HashMap<String, Object> map : listStatementAll) {
                    fullStmtNo = fullStmtNo + CommonUtils.toString(map.get("ID_STMT"))+",";
                }
            }
        }
        try {
            BeanUtils.copyProperties(outputDTO, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String[] newSelectedStatementNo = new String[listExistselectedStatementNo.size()];
        listExistselectedStatementNo.toArray(newSelectedStatementNo);
        outputDTO.setSelectedStatementNo(newSelectedStatementNo);
        
        outputDTO.setListStatement(listStatement);
        outputDTO.setTotalRow(totalReport);
        outputDTO.setRow(row);
        outputDTO.setFullStmtNo(fullStmtNo);
        
        return outputDTO;
    }
    
    /**
     * call E_EXP_F02
     * @param curStmtMonth
     * @param idLogin
     * @param statementNoArr
     * @return
     */
    private E_EXP_F02Output callExpF02(String curStmtMonth, String idLogin, String[] statementNoArr) {
        E_EXP_F02Output e_exp_f02Output = new E_EXP_F02Output();
        E_EXP_F02 exp_f02 = new E_EXP_F02();
        exp_f02.setQueryDAO(queryDAO);
        exp_f02.setUpdateDAO(updateDAO);
        exp_f02.setId_login(idLogin);
        exp_f02.setId_stmts(statementNoArr);
        exp_f02.setEset_rundate(new Date());
        exp_f02.setCurStmtMonth(curStmtMonth);
        exp_f02.setBatch("R");
        exp_f02.excute(e_exp_f02Output);
        return e_exp_f02Output;
    }
    
    /**
     * input check
     * @param params
     * @return true:check ok,false:input error
     */
    private boolean checkInput(R_SOA_S01SearchInput params) {
        boolean checkResult = true;
        String cboStatementMonth = CommonUtils.toString(params.getCboStatementMonth()).trim();
        String tbxStatementYear = CommonUtils.toString(params.getTbxStatementYear()).trim();
        String tbxStatementNoFrom = CommonUtils.toString(params.getTbxStatementNoFrom()).trim();
        String tbxStatementNoTo = CommonUtils.toString(params.getTbxStatementNoTo()).trim();
        if (CommonUtils.isEmpty(cboStatementMonth)) {
            checkResult = false;
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC005", new Object[] {"Statement Month"}));
        }
        if (CommonUtils.isEmpty(tbxStatementYear)) {
            checkResult = false;
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC005", new Object[] {"Statement Year"}));
        }
        if(!CommonUtils.isEmpty(tbxStatementNoFrom) && !CommonUtils.isEmpty(tbxStatementNoTo) && 
                tbxStatementNoFrom.toUpperCase().compareTo(tbxStatementNoTo.toUpperCase())>0) {
            checkResult = false;
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC046", new Object[] {"Statement No.","Statement No. To -","Statement No. From"}));
        }
        return checkResult;
    }
    
    /**
     * Update button click check
     * @param params
     * @return
     */
    private boolean btnUpdateStatementClickcheckInput(R_SOA_S01SearchInput params) {
        //input check
        boolean checkResult = true;
        String[] selectedStatementNo = params.getSelectedStatementNo();
        if(selectedStatementNo==null || selectedStatementNo.length<=0) {
            checkResult = false;
            errorMesgInfo.add(MessageUtil.get("errors.ERR1SC033", new Object[] {"Update Statement","check box"}));
        } else {
            G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
            G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
            gCsbP01CheckInput.setMessageParam1("Update Statement");
            gCsbP01CheckInput.setMessageParam2("for selected Statement No.");
            gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
            G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
            boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
            
            if (!resultFlg) {
                checkResult = false;
                String errorMsg = gCsbP01CheckOutput.getMessageContext();
                errorMesgInfo.add(errorMsg);
            }
        }
        return checkResult;
    }
    
    /**
     * set value to sql param
     * @param m
     * @param params
     */
    private void setParamVale(Map<String, Object> m, R_SOA_S01SearchInput params) {
        String cboStatementMonth = CommonUtils.toString(params.getCboStatementMonth()).trim();
        String tbxStatementYear = CommonUtils.toString(params.getTbxStatementYear()).trim();
        String tbxStatementNoFrom = CommonUtils.toString(params.getTbxStatementNoFrom()).trim();
        String tbxStatementNoTo = CommonUtils.toString(params.getTbxStatementNoTo()).trim();
        String tbxCustomerName = CommonUtils.toString(params.getTbxCustomerName()).trim();
        String tbxCustomerId = CommonUtils.toString(params.getTbxCustomerId()).trim();
        String tbxBillingAccount = CommonUtils.toString(params.getTbxBillingAccount()).trim();
        String cboCustomerType = CommonUtils.toString(params.getCboCustomerType()).trim();
        String cboPrintStatement = CommonUtils.toString(params.getCboPrintStatement()).trim();
        String chkStatementTotal = CommonUtils.toString(params.getChkStatementTotal()).trim();
        

        String cboBillingCurrency = CommonUtils.toString(params.getCboBillingCurrency()).trim();        
        String cboPaymentCurrency = CommonUtils.toString(params.getCboPaymentCurrency()).trim();
        
        if (Integer.parseInt(cboStatementMonth) < 10) {
            cboStatementMonth = "0" + cboStatementMonth;
        }
        m.put("statementYearMonth", tbxStatementYear+cboStatementMonth);
        m.put("tbxCustomerName", CommonUtils.escapeSQL(tbxCustomerName));
        m.put("tbxCustomerId", CommonUtils.escapeSQL(tbxCustomerId));
        m.put("cboCustomerType", cboCustomerType);
        m.put("tbxStatementNoFrom", CommonUtils.escapeSQL(tbxStatementNoFrom));
        m.put("tbxStatementNoTo", CommonUtils.escapeSQL(tbxStatementNoTo));
        m.put("tbxBillingAccount", CommonUtils.escapeSQL(tbxBillingAccount));
        m.put("cboPrintStatement", cboPrintStatement);
        m.put("chkStatementTotal", chkStatementTotal);
        
        m.put("cboBillingCurrency", cboBillingCurrency);
        m.put("cboPaymentCurrency", cboPaymentCurrency);
    }
    
    /**
     * set value to sql param
     * @param m
     * @param params
     */
    private void setPreParamValue(Map<String, Object> m, R_SOA_S01SearchInput params) {
        String hidStatementMonth = CommonUtils.toString(params.getHidStatementMonth()).trim();
        String hidStatementYear = CommonUtils.toString(params.getHidStatementYear()).trim();
        String hidStatementNoFrom = CommonUtils.toString(params.getHidStatementNoFrom()).trim();
        String hidStatementNoTo = CommonUtils.toString(params.getHidStatementNoTo()).trim();
        String hidCustomerName = CommonUtils.toString(params.getHidCustomerName()).trim();
        String hidCustomerId = CommonUtils.toString(params.getHidCustomerId()).trim();
        String hidBillingAccount = CommonUtils.toString(params.getHidBillingAccount()).trim();
        String hidCustomerType = CommonUtils.toString(params.getHidCustomerType()).trim();
        String hidPrintStatement = CommonUtils.toString(params.getHidPrintStatement()).trim();
        String hidStatementTotal = CommonUtils.toString(params.getHidStatementTotal()).trim();
        
        if (Integer.parseInt(hidStatementMonth) < 10) {
            hidStatementMonth = "0" + hidStatementMonth;
        }
        m.put("statementYearMonth", hidStatementYear+hidStatementMonth);
        m.put("tbxCustomerName", CommonUtils.escapeSQL(hidCustomerName));
        m.put("tbxCustomerId", CommonUtils.escapeSQL(hidCustomerId));
        m.put("cboCustomerType", hidCustomerType);
        m.put("tbxStatementNoFrom", CommonUtils.escapeSQL(hidStatementNoFrom));
        m.put("tbxStatementNoTo", CommonUtils.escapeSQL(hidStatementNoTo));
        m.put("tbxBillingAccount", CommonUtils.escapeSQL(hidBillingAccount));
        m.put("cboPrintStatement", hidPrintStatement);
        m.put("chkStatementTotal", hidStatementTotal);
    }
}
