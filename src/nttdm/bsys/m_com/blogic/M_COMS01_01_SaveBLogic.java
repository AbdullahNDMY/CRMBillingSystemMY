/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Save Data BLogic
 * FILE NAME      : M_COMS01_01_SaveBLogic.java
 *
 * 
 * 
 **********************************************************/
package nttdm.bsys.m_com.blogic;

import jp.terasoluna.fw.service.thin.*;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_com.dto.*;

import nttdm.bsys.m_com.blogic.AbstractM_COMS01_01_SaveBLogic;
import java.util.*;

import nttdm.bsys.m_com.dto.M_COMS01IO;
import org.apache.struts.Globals;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data Vietnam
 * 
 */
public class M_COMS01_01_SaveBLogic extends AbstractM_COMS01_01_SaveBLogic {

    private BLogicMessages msg = new BLogicMessages();
    private BLogicMessages error = new BLogicMessages();

    public BLogicResult execute(M_COMS01IO input) {
        BLogicResult result = new BLogicResult();

        String resultString = "success";
        String idCompany = input.getIdCompany();
        String idLogin = input.getIdLogin();
        List<CompanyBank> listCompanyBank = input.getListCompanyBank();

        String companyName = queryDAO.executeForObject("SELECT.M_COM.GET_COMPANYNAME", idCompany, String.class);
        try {

            if (validateBank(listCompanyBank)) {

                // Audit Trail Start
                Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_M,
                        BillingSystemConstants.SUB_MODULE_M_COM_BI, companyName, null, BillingSystemConstants.AUDIT_TRAIL_EDITED);

                CompanyBank companyBank;
                HashMap<String, Object> param;

                for (int i = 0; i < listCompanyBank.size(); i++) {
                    companyBank = listCompanyBank.get(i);
                    List<CompanyBankAccount> listCompanyBankAccount = companyBank.getListCompanyBankAccount();
                    param = new HashMap<String, Object>();
                    param.put("displayOrder", companyBank.getDisplayOrder());
                    param.put("idbank", companyBank.getIdbank());
                    param.put("accountName", companyBank.getAccountName().trim());
                    param.put("swiftCode", companyBank.getSwiftCode());
                    param.put("idAudit", idAudit);
                    param.put("idCompany", idCompany);
                    param.put("idLogin", idLogin);

                    for (CompanyBankAccount account : listCompanyBankAccount) {

                        param.put("accountNo", account.getAccountNo());
                        param.put("currency", account.getCurrency());
                        param.put("isDefault", account.getIsDefault());
                        param.put("active", account.getActive());
                        param.put("idComBank", account.getIdComBank());

                        String idComBank = account.getIdComBank();
                        if (CommonUtils.isEmpty(idComBank)) {
                            // do insert
                            String idCompanyBank = queryDAO.executeForObject("SELECT.M_COM.GET_SEQ_M_COM_BANKINFO", null, String.class);
                            param.put("idComBank", idCompanyBank);

                            updateDAO.execute("INSERT.M_COM.M_COM_BANKINFO", param);
                        } else {
                            // do update
                            updateDAO.execute("UPDATE.M_COM.M_COM_BANKINFO", param);
                        }
                    }
                }

                msg.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
                // End Audit Trail
                CommonUtils.auditTrailEnd(idAudit);
            } else {
                resultString = "checkError";
            }
        } catch (Exception e) {
            resultString = "checkError";
            e.printStackTrace();
            error.add(Globals.ERROR_KEY, new BLogicMessage("info.ERR2SC004"));
        }

        result.setErrors(error);
        result.setMessages(msg);
        result.setResultString(resultString);
        return result;
    }

    /**
     * check input bank information
     * 
     * @param listCompanyBank
     * @return
     */
    private boolean validateBank(List<CompanyBank> listCompanyBank) {

        List<String> displayOrder = new ArrayList<String>();
        if (CommonUtils.isEmpty(listCompanyBank)) {
            error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC105", "Please register at least one bank."));
        } else {
            for (CompanyBank bank : listCompanyBank) {

                displayOrder.add(bank.getDisplayOrder());

                // id bank
                if (CommonUtils.isEmpty(bank.getIdbank())) {
                    error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC038", "Bank Full Name"));
                }

                // account name
                if (CommonUtils.isEmpty(bank.getAccountName())) {
                    error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Account Name"));
                }

                if (CommonUtils.isEmpty(bank.getListCompanyBankAccount())) {
                    error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC105", "Please register at least one bank account."));
                } else {
                    for (CompanyBankAccount acount : bank.getListCompanyBankAccount()) {
                        if (CommonUtils.isEmpty(acount.getAccountNo())) {
                            error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Account No"));
                        }

                        if (!error.isEmpty()) {
                            break;
                        }
                    }
                }

                if (!error.isEmpty()) {
                    break;
                }
            }
        }

        if (error.isEmpty()) {
            int num1 = 0;
            int num2 = 0;
            int num3 = 0;
            for (String order : displayOrder) {
                int num = Integer.valueOf(order);
                if (num == 1) {
                    num1++;
                } else if (num == 2) {
                    num2++;
                } else if (num == 3) {
                    num3++;
                }
            }
            if (num1 > 1 || num2 > 1 || num3 > 1) {
                error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC006", "Order Value"));
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}