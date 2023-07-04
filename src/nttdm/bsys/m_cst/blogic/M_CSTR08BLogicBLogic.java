/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS02_01
 * FUNCTION       : Save data BLogic
 * FILE NAME      : M_CSTR08BLogicBLogic.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.MessageUtil;
import nttdm.bsys.common.util.SecurityUtil;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.BankInfoDto;
import nttdm.bsys.m_cst.dto.M_CSTR08BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR08BLogicOutput;

import org.apache.struts.Globals;

/******
 * Class M_CSTR08BLogicBLogic<br>
 * <ul>
 * <li>Class save BankInformation
 * <li>
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1
 * ******/

public class M_CSTR08BLogicBLogic extends AbstractM_CSTR08BLogicBLogic {

    public BLogicResult execute(M_CSTR08BLogicInput param) {
        BLogicResult result = new BLogicResult();
        // declare error message
        BLogicMessages errors = new BLogicMessages();
        // declare message
        BLogicMessages messages = new BLogicMessages();
        M_CSTR08BLogicOutput outputDTO = new M_CSTR08BLogicOutput();

        try {
            // set param to output
            outputDTO.setOutput(param);
            
            // Validate GIRO Information - Bank, existing when Bank Code and Branch Code entered.
            String bankId = CommonUtils.toString(param.getBankInfo().getBank());
            String bankCode = CommonUtils.toString(param.getBankInfo().getCbBankCode());
            String bankBranchCode = CommonUtils.toString(param.getBankInfo().getCbBranchCode());
            if (!bankCode.equals("") || !bankBranchCode.equals("")) {
                if (bankId.equals("")){
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC018", "Bank/Branch Code", "valid Bank/Branch Code"));
                    result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);    
                }
            }

            // Validate Security No. is  exactly 4 digits
            if (!isValidSecurityNo(param.getSecurityNo())){
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC055", "Security No"));
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);             
            }
            // Validate creditCardNumber
            if (!isValidCreditCardNumber(param.getCreditCardNumber())){
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC088", "Credit Card Number"));
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            }
            // Validate Faild: Month has value while the other has no value
            if ((param.getExpiredDateMonth() == null || param.getExpiredDateMonth().equals("")) && (param.getExpiredDateYear() != null && !param.getExpiredDateYear().equals(""))){
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Month of Expiry Date"));
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);

            }
            if ((param.getExpiredDateMonth() != null && !param.getExpiredDateMonth().equals("")) && (param.getExpiredDateYear() == null || param.getExpiredDateYear().equals(""))){
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Year of Expiry Date"));
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            }
            
            if(isExpiredDate(param.getExpiredDateMonth(),param.getExpiredDateYear()) == false){
                
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC063", "Credit Card Expiry Date"));
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            }
            
            if (errors.get().hasNext()) {
                result.setResultObject(outputDTO);      
                result.setErrors(errors);
                result.setMessages(messages);  
                return result;
            } else {
                String popupClickYesFlg = CommonUtils.toString(param.getPopupClickYesFlg()).trim();
                //not click yes
                if(CommonUtils.isEmpty(popupClickYesFlg)) {
                    //Bank Account Number 
                    String acctNumber = CommonUtils.toString(param.getBankInfo().getAcctNumber()).trim();
                    //Credit Card Number 
                    String creditCardNumber = CommonUtils.toString(param.getCreditCardNumber()).trim();
                    String idCust = param.getId_cust();
                    Map<String, Object> sqlParam = new HashMap<String, Object>();
                    sqlParam.put("idCust", idCust);
                    boolean hasWarning = false;
                    String acctNumberMsg = "";
                    String creditCardNumberMsg = "";
                    if(!CommonUtils.isEmpty(acctNumber)) {
                        sqlParam.put("acctNumber", acctNumber);
                        List<Map<String, Object>> mapList = queryDAO.executeForMapList("SELECT.M_CST.GET_M_CUST_BANKINFO_BY_CCARD_ACCT_NO", sqlParam);
                        if(mapList!=null && 0<mapList.size()) {
                            acctNumberMsg = MessageUtil.get("info.ERR4SC018", "The bank account number", "customer ID =" + listObjectByNameToStr(mapList, "ID_CUST"), "save this information?");
                            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                            hasWarning = true;
                        }
                    }
                    if(!CommonUtils.isEmpty(creditCardNumber)) {
                        creditCardNumber = SecurityUtil.aesEncrypt(creditCardNumber);
                        sqlParam.put("creditCardNumber", creditCardNumber);
                        List<Map<String, Object>> mapList = queryDAO.executeForMapList("SELECT.M_CST.GET_M_CUST_BANKINFO_BY_CCARD_NO", sqlParam);
                        if(mapList!=null && 0<mapList.size()) {
                            creditCardNumberMsg = MessageUtil.get("info.ERR4SC018", "The credit card number", "customer ID =" + listObjectByNameToStr(mapList, "ID_CUST"), "save this information?");
                            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                            hasWarning = true;
                        }
                    }
                    outputDTO.setAcctNumberMsg(acctNumberMsg);
                    outputDTO.setCreditCardNumberMsg(creditCardNumberMsg);
                    if (hasWarning) {
                        result.setResultObject(outputDTO);      
                        result.setErrors(errors);
                        result.setMessages(messages);  
                        return result;
                    }
                } else {
                    // click yes
                    outputDTO.setPopupClickYesFlg("");
                }
            }
            
            // validate Expiry Date Pass:
            // both Month & Year have to have values or both have no values & validate securityNo ok
            if (isValidSecurityNo(param.getSecurityNo()) && ((param.getExpiredDateMonth() != null && !param.getExpiredDateMonth().equals("") && param.getExpiredDateYear() != null && !param.getExpiredDateYear().equals("")) && (isExpiredDate(param.getExpiredDateMonth(),param.getExpiredDateYear())))
                    ||((param.getExpiredDateMonth() == null || param.getExpiredDateMonth().equals("")) && (param.getExpiredDateYear() == null || param.getExpiredDateYear().equals("")))){
                if ((param.getMode()== null || param.getMode().equals("")) || (param.getMode() != null && (param.getMode().equals("NEWMODE") || param.getMode().equals("EDITMODE")))) {
                    String count = queryDAO.executeForObject(Util.SELECT_ID_CUST_COUNT, param.getId_cust(), String.class);
                    String custName = queryDAO.executeForObject("SELECT.M_CST.SQL025_1", param.getId_cust(), String.class);
                    
                    String creditCardNumber = param.getCreditCardNumber();
                    String securityNo = param.getSecurityNo();
                    if(CommonUtils.notEmpty(creditCardNumber)){
                        param.setCreditCardNumber(SecurityUtil.aesEncrypt(creditCardNumber));
                    }
                    if(CommonUtils.notEmpty(securityNo)){
                        param.setSecurityNo(SecurityUtil.aesEncrypt(securityNo));
                    }
                    
                    BankInfoDto bankInfo = param.getBankInfo(); 
                    bankInfo.setExpiredDateDay(expiredDay(param.getExpiredDateMonth(), param.getExpiredDateYear()));
                    if(bankInfo.getExpiredDateDay() == null || bankInfo.getExpiredDateDay().equals("")){
                        bankInfo.setExpiredDate("");
                    }else{
                        bankInfo.setExpiredDate(bankInfo.getExpiredDateDay() + "/" + bankInfo.getExpiredDateMonth() + "/" + bankInfo.getExpiredDateYear());
                    }
                    
                    if (count == null || count.equals("0")){
                        /**
                         * Audit Trail
                         */
                        Integer idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), BillingSystemConstants.MODULE_M, 
                                BillingSystemConstants.SUB_MODULE_M_CST_BI, CommonUtils.toString(param.getId_cust()) + ":"
                                + CommonUtils.toString(custName), null,
                                BillingSystemConstants.AUDIT_TRAIL_CREATED);
                        bankInfo.setIdAudit(idAudit);
                        updateDAO.execute(Util.INSERT_M_CUST_BANKINFO, bankInfo);
                        CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
                    }else{
                        /**
                         * Audit Trail
                         */
                        Integer idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), "M", 
                                "M-CST-BI", CommonUtils.toString(param.getId_cust()) + ":"
                                + CommonUtils.toString(custName), null,
                                BillingSystemConstants.AUDIT_TRAIL_EDITED);
                        bankInfo.setIdAudit(idAudit);
                        updateDAO.execute(Util.UPDATE_M_CUST_BANKINFO, bankInfo);
                        CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
                    }
                    outputDTO.setMode("READONLY");
                    result.setResultString("success");
                    //set return message
                    messages.add(Globals.MESSAGE_KEY, new BLogicMessage(Util.ERRORS_ERR2SC003));
                }
            }
            result.setResultObject(outputDTO);      
            result.setErrors(errors);
            result.setMessages(messages);   
        } catch (Exception ex) {
            // add error inserting/updating failure
            ex.printStackTrace();
            errors.add(Globals.ERROR_KEY, new BLogicMessage(
                    Util.ERRORS_ERR2SC004));
            // set return error
            result.setErrors(errors);
            result.setMessages(messages);   
            // set return string
            result
                    .setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
        }
        return result;
    }
    
    private boolean isValidSecurityNo(String securityNo){
        if (securityNo == null || securityNo.equals("")) return true;
        if (securityNo != null && (securityNo.length() < 3 || securityNo.length() > 4)) {
        	return false;
        }
        if (securityNo != null && securityNo.length() >= 3 && securityNo.length() <= 4){
            try {
                Integer.parseInt(securityNo);
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * check creditCardNumber
     * @param creditCardNumber
     * @return
     */
    private boolean isValidCreditCardNumber(String creditCardNumber){
        creditCardNumber = CommonUtils.toString(creditCardNumber).trim();
        if (!CommonUtils.isEmpty(creditCardNumber)) {
            return creditCardNumber.matches("^[0-9]*$");
        }
        return true;
    }
    
    private boolean isExpiredDate(String expireMonth, String expireYear){
    if(!expireMonth.equals("") && !expireYear.equals("")){
        Calendar calendar = Calendar.getInstance();     
        int expMonth = Integer.parseInt(expireMonth);
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        int expYear = Integer.parseInt(expireYear);
        int curYear = calendar.get(Calendar.YEAR);
        
        if(expYear == curYear){
            if(expMonth == curMonth || expMonth > curMonth)
                return true;    
            else
                return false;
        }
        
        if(expYear > curYear){
            if(expMonth < curMonth || expMonth == curMonth || expMonth > curMonth)
                return true;
        }       
        return false;   
    }else{
        return true;
    }
    }
    
    private String expiredDay(String expireMonth, String expireYear) {  
        if(!expireMonth.equals("") && !expireYear.equals("")){
        int expMonth = Integer.parseInt(expireMonth);
        int expYear = Integer.parseInt(expireYear);
        int expDay = 0;
        
        if(expYear%4==0){
            if(expMonth%2==1){
                expDay = 31;
            if(expMonth==9 || expMonth==11)
                expDay = 30;
            }
        else if(expMonth%2==0){
            expDay = 30;
            if(expMonth==2)
                expDay = 29;
            if(expMonth==8 || expMonth==10 || expMonth==12)
                expDay = 31;
        }}
        else if(expYear%4!=0){
            if(expMonth%2==1){
                expDay = 31;
            if(expMonth==9 || expMonth==11)
                expDay = 30;
            }
            else if(expMonth%2==0){
                expDay = 30;
                if(expMonth==2)
                    expDay = 28;
                if(expMonth==8 || expMonth==10 || expMonth==12)
                    expDay = 31;
            }
        }
        return Integer.toString(expDay);
    } else{
        return null;
    }
    }
    
    /**
     * List Object to List String by name
     * @param list
     * @param name
     * @return
     */
    public String listObjectByNameToStr(List<Map<String, Object>> list, String name) {
        String resultStr = "";
        StringBuffer sb = new StringBuffer();
        if (list != null && 0 < list.size()) {
            sb.append("[");
            for(Map<String, Object> map : list) {
                String value = CommonUtils.toString(map.get(name)).trim();
                sb.append(value).append(",");
            }
            resultStr = sb.toString();
            sb.delete(resultStr.length()-1, resultStr.length());
            sb.append("]");
            resultStr = sb.toString();
        }
        return resultStr;
    }
}