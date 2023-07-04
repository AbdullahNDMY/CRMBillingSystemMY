/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Get Data BLogic
 * FILE NAME      : M_COMS01_01_InitDataBLogic.java
 *
 * 
 * 
 **********************************************************/
package nttdm.bsys.m_com.blogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_com.dto.CompanyBank;
import nttdm.bsys.m_com.dto.CompanyBankAccount;
import nttdm.bsys.m_com.dto.M_COMS01IO;

/**
 * BusinessLogic class.
 * 
 * @author loamanma
 */
public class M_COMS01_01_InitDataBLogic extends AbstractM_COMS01_01_InitDataBLogic {

    public BLogicResult execute(M_COMS01IO input) {
        BLogicResult result = new BLogicResult();
        M_COMS01IO output = new M_COMS01IO();

        // Get idCompnay
        String idCompany = input.getIdCompany();

        List<CompanyBank> listCompanyBank = new ArrayList<CompanyBank>();

        // Get listCompanyBank
        List<Map<String, Object>> companyBankList = queryDAO.executeForMapList("SELECT.M_COM.GET_LIST_COMPANYBANK", idCompany);

        String idBank = "";

        if (!CommonUtils.isEmpty(companyBankList)) {

            CompanyBank companyBank = null;

            for (int i = 0; i < companyBankList.size(); i++) {

                Map<String, Object> bank = companyBankList.get(i);

                String bankId = CommonUtils.toString(bank.get("ID_BANK"));

                if (idBank.equals(bankId)) {

                    CompanyBankAccount account = new CompanyBankAccount();
                    account.setAccountNo(CommonUtils.toString(bank.get("COM_ACCT_NO")));
                    account.setActive(CommonUtils.toString(bank.get("STATUS")));
                    account.setCurrency(CommonUtils.toString(bank.get("COM_CUR")));
                    account.setIdComBank(CommonUtils.toString(bank.get("ID_COM_BANK")));
                    account.setIsDefault(CommonUtils.toString(bank.get("DEFAULT_ACC")));

                    companyBank.getListCompanyBankAccount().add(account);

                } else {
                    if (companyBank != null) {
                        listCompanyBank.add(companyBank);
                    }
                    idBank = bankId;
                    companyBank = new CompanyBank();
                    companyBank.setDisplayOrder(CommonUtils.toString(bank.get("DISPLAY_ORDER")));
                    companyBank.setBankFullName(CommonUtils.toString(bank.get("BANK_FULL_NAME")));
                    companyBank.setAccountName(CommonUtils.toString(bank.get("COM_ACCT_NAME")));
                    companyBank.setSwiftCode(CommonUtils.toString(bank.get("COM_SWIFT")));
                    companyBank.setBankCode(CommonUtils.toString(bank.get("BANK_CODE")));
                    companyBank.setBranchCode(CommonUtils.toString(bank.get("BRANCH_CODE")));
                    companyBank.setIdComBank(CommonUtils.toString(bank.get("ID_COM_BANK")));
                    companyBank.setIdCom(CommonUtils.toString(bank.get("ID_COM")));
                    companyBank.setIdbank(CommonUtils.toString(bank.get("ID_BANK")));

                    CompanyBankAccount account = new CompanyBankAccount();

                    account.setAccountNo(CommonUtils.toString(bank.get("COM_ACCT_NO")));
                    account.setActive(CommonUtils.toString(bank.get("STATUS")));
                    account.setCurrency(CommonUtils.toString(bank.get("COM_CUR")));
                    account.setIdComBank(CommonUtils.toString(bank.get("ID_COM_BANK")));
                    account.setIsDefault(CommonUtils.toString(bank.get("DEFAULT_ACC")));

                    companyBank.getListCompanyBankAccount().add(account);
                }
            }

            if (companyBank != null) {
                listCompanyBank.add(companyBank);
            }
        }

        output.setIdCompany(idCompany);
        output.setListCompanyBank(listCompanyBank);

        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
}