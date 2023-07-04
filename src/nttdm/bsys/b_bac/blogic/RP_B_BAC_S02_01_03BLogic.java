/*
 * @(#)RP_B_BAC_S02_01_03BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_01_03Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_01_03Output;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class of B-BAC NEW (Billing Address Changed).
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_01_03BLogic extends AbstractRP_B_BAC_S02_01_03BLogic {

    /**
     * B-BAC NEW (Billing Address Changed).
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    @SuppressWarnings("unchecked")
    public BLogicResult execute(RP_B_BAC_S02_01_03Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S02_01_03Output outputDTO = new RP_B_BAC_S02_01_03Output();
        
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        T_BAC_H inputHeaderInfo = param.getInputHeaderInfo();
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("idCust", param.getCboCustomerName());
        inputHeaderInfo.setIdCust(param.getCboCustomerName());
        HashMap<String, Object> custInfo = (HashMap<String, Object>) queryDAO.executeForMap("B_BAC.getCustInfo", m);
        // get list contact
        List<LabelValueBean> listContact = this.queryDAO.executeForObjectList("B_BAC.getAllContact", m);
        // String idKeys
        String[] idKeys = param.getIdKeys();
        String[] idBillAccs = new String[idKeys.length];
        String[] idCustPlans = new String[idKeys.length];
        for (int i = 0; i < idKeys.length; i++) {
            String idCustPlan = idKeys[i].split("_")[0];
            String idBillAccount = idKeys[i].split("_")[1];
            idCustPlans[i] = idCustPlan;
            idBillAccs[i] = idBillAccount;
        }
        // get information by idCustGrps
        m.put("idCustPlans", idCustPlans);
        m.put("idBillAccs", idBillAccs);
        m.put("adrType", inputHeaderInfo.getCustAdrType());
        List<HashMap<String, Object>> listPlanInfo = this.queryDAO.executeForObjectList("B_BAC.getPlanInfoByIdCustPlans", m);

        // new newListPlanInfo
        List<HashMap<String, Object>> newListPlanInfo = new ArrayList<HashMap<String, Object>>();
        RP_B_BAC_commonBLogic logic = new RP_B_BAC_commonBLogic();
        newListPlanInfo = logic.getNewListInfo(listPlanInfo);

        inputHeaderInfo.setBillCurrency(CommonUtils.toString(listPlanInfo.get(0).get("BILL_CURRENCY")));
        // generate address info
        HashMap<String, Object> address = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BAC.getCustAdr", m);
        HashMap<String, Object> addressInfo = new HashMap<String, Object>();
        if (address != null) {
            addressInfo.put("address1", CommonUtils.toString(address.get("ADR_LINE1")));
            addressInfo.put("address2", CommonUtils.toString(address.get("ADR_LINE2")));
            addressInfo.put("address3", CommonUtils.toString(address.get("ADR_LINE3")));
            // generate address4
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader countryCodeList = (MappedCodeListLoader) context.getBean("LIST_COUNTRY");
            Map<String, Object> countryMap = countryCodeList.getCodeListMap();
            String country = "";
            if (countryMap.containsKey(address.get("COUNTRY"))) {
                country = countryMap.get(address.get("COUNTRY")).toString();
            }
            addressInfo.put("address4", CommonUtils.toString(address.get("ZIP_CODE")) + ", " + country);
        }
        
        String customerType = CommonUtils.toString(custInfo.get("CUSTOMER_TYPE")).trim();
        if (!"CONS".equals(customerType)) {
            // get email info
            m.put("contactType", inputHeaderInfo.getContactType());
            HashMap<String, Object> contactInfo = (HashMap<String, Object>) this.queryDAO.executeForMap(
                    "B_BAC.getSingleContact", m);
            if (contactInfo != null) {
                addressInfo.put("email", CommonUtils.toString(contactInfo.get("EMAIL")));
                addressInfo.put("email_cc", CommonUtils.toString(contactInfo.get("EMAIL_CC")));
            }
        } else {
            addressInfo.put("email", CommonUtils.toString(custInfo.get("CO_EMAIL")).trim());
        }
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        // get M_GSET_D info: FIXED_FOREX
        String fixedForex = queryDAO.executeForObject("B_BAC.getMGSetFixedForex", null, String.class);
        outputDTO.setFixedForex(fixedForex);
        
        // get M_GSET_D info: CPM_TAX_TYPE_DISPLAY
        String taxTypeDisplay = queryDAO.executeForObject("B_BAC.getMGSetTaxType", null, String.class);
        outputDTO.setTaxTypeDisplay(taxTypeDisplay);
        
        outputDTO.setAddressInfo(addressInfo);
        outputDTO.setListContact(listContact);
        outputDTO.setListPlanInfo(newListPlanInfo);
        outputDTO.setCustInfo(custInfo);
        outputDTO.setBss(bss);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}