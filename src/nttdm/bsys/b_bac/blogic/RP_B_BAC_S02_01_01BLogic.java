/*
 * @(#)RP_B_BAC_S02_01_01BLogic.java
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
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_01_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_01_01Output;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class of B-BAC NEW Init.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_01_01BLogic extends AbstractRP_B_BAC_S02_01_01BLogic {

    /**
     * B-BAC NEW Init.
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    @SuppressWarnings("unchecked")
    public BLogicResult execute(RP_B_BAC_S02_01_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S02_01_01Output outputDTO = new RP_B_BAC_S02_01_01Output();
        
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        T_BAC_H inputHeaderInfo = null;
        if (!CommonUtils.isEmpty(param.getInputHeaderInfo())) {
            inputHeaderInfo = param.getInputHeaderInfo();
        } else {
            inputHeaderInfo = new T_BAC_H();
        }
        
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("idCust", param.getCboCustomerName());
        inputHeaderInfo.setIdCust(param.getCboCustomerName());
        
        // exportSingPost set checked
        if (!"1".equals(param.getNewFlg())) {
            inputHeaderInfo.setExportSingPost(param.getInputHeaderInfo().getExportSingPost());
        } else {
            inputHeaderInfo.setExportSingPost("1");
        }
        // email set checked
        if (!"1".equals(param.getNewFlg())) {
            inputHeaderInfo.setDeliveryEmail(param.getInputHeaderInfo().getDeliveryEmail());
        } else {
            inputHeaderInfo.setDeliveryEmail("1");
        }
        //delivery set checked
        if (!"1".equals(param.getNewFlg())) {
            inputHeaderInfo.setDelivery(param.getInputHeaderInfo().getDelivery());
        } else {
            inputHeaderInfo.setDelivery("3");
        }
        
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
        // paymentMethod pass
        List<HashMap<String, Object>> listPaymentMethod = this.queryDAO.executeForObjectList("B_BAC.getPaymentByIdCustGrps", m);

        boolean paymentF = false;
        String paymentMethod1 = "";
        if (listPaymentMethod != null && listPaymentMethod.size() != 0) {
            paymentMethod1 = CommonUtils.toString(listPaymentMethod.get(0).get("PAYMENT_METHOD"));
            for (int i = 0; i < listPaymentMethod.size(); i++) {
                String paymentMethod = CommonUtils.toString(listPaymentMethod.get(i).get("PAYMENT_METHOD"));
                if (!paymentMethod1.equals(paymentMethod)) {
                    paymentF = true;
                    break;
                }
            }
        } else {
            paymentF = true;
        }
        if (!paymentF) {
            if (!"1".equals(param.getNewFlg())) {
                inputHeaderInfo.setPaymentMethod(param.getInputHeaderInfo().getPaymentMethod());
            } else {
                inputHeaderInfo.setPaymentMethod(paymentMethod1);
            }
        }

        List<HashMap<String, Object>> listPlanInfo = this.queryDAO.executeForObjectList("B_BAC.getPlanInfoByIdCustPlans", m);
        // new newListPlanInfo
        List<HashMap<String, Object>> newListPlanInfo = new ArrayList<HashMap<String, Object>>();
        RP_B_BAC_commonBLogic logic = new RP_B_BAC_commonBLogic();
        newListPlanInfo = logic.getNewListInfo(listPlanInfo);

        inputHeaderInfo.setBillCurrency(CommonUtils.toString(listPlanInfo.get(0).get("BILL_CURRENCY")));
        inputHeaderInfo.setExportCurrency(CommonUtils.toString(listPlanInfo.get(0).get("EXPORT_CURRENCY")));
        inputHeaderInfo.setFixedForex(CommonUtils.toString(listPlanInfo.get(0).get("FIXED_FOREX")));
        inputHeaderInfo.setTaxType(CommonUtils.toString(listPlanInfo.get(0).get("TAX_TYPE")));
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        // get custAdrType
        HashMap<String, Object> m2 = new HashMap<String, Object>();
        m2.put("idCust", param.getCboCustomerName());
        String custAdrType = CommonUtils.toString(inputHeaderInfo.getCustAdrType());
        
        // generate address info
        m2.put("adrType", custAdrType);
        HashMap<String, Object> address = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BAC.getCustAdr", m2);
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
            String idCust = CommonUtils.toString(custInfo.get("ID_CUST")).trim();
            List<Map<String, Object>> custAtcInfoList = queryDAO.executeForMapList("B_BAC.SELECT_CUST_ATC_INFO", idCust);
            String contactType = "";
            if (0 < custAtcInfoList.size()) {
                contactType = "BC";
            } else {
                contactType = "PC";
            }
            inputHeaderInfo.setContactType(contactType);

            m2.put("contactType", inputHeaderInfo.getContactType());
            HashMap<String, Object> contactInfo = (HashMap<String, Object>)this.queryDAO.executeForMap("B_BAC.getSingleContact", m2);
            if (contactInfo != null) {
                addressInfo.put("email", CommonUtils.toString(contactInfo.get("EMAIL")));
                addressInfo.put("email_cc", CommonUtils.toString(contactInfo.get("EMAIL_CC")));
            }
        } else {
            addressInfo.put("email", CommonUtils.toString(custInfo.get("CO_EMAIL")).trim());
        }
        
        // checking existence of GIRO and CC
        Boolean isDisplayGiro = false;
        Boolean isDisplayCC = false;
        Integer totalGiro = this.queryDAO.executeForObject("B_BAC.getGIROCustBankInfo", m2, Integer.class);
        Integer totalCC = this.queryDAO.executeForObject("B_BAC.getCCCustBankInfo", m2, Integer.class);
        if (totalGiro > 0) {
            isDisplayGiro = true;
        }
        if (totalCC > 0) {
            isDisplayCC = true;
        }
        
        // get M_GSET_D info: FIXED_FOREX
        String fixedForex = queryDAO.executeForObject("B_BAC.getMGSetFixedForex", null, String.class);
        outputDTO.setFixedForex(fixedForex);
        
        // #191 Start
        String singPostValue = this.queryDAO.executeForObject("B_BAC.getSingPostValue", null, String.class);
        inputHeaderInfo.setSingPostValue(singPostValue);
        // #191 End
        
        // get M_GSET_D info: CPM_TAX_TYPE_DISPLAY
        String taxTypeDisplay = queryDAO.executeForObject("B_BAC.getMGSetTaxType", null, String.class);
        outputDTO.setTaxTypeDisplay(taxTypeDisplay);
        
        outputDTO.setIsDisplayGiro(isDisplayGiro);
        outputDTO.setIsDisplayCC(isDisplayCC);
        outputDTO.setAddressInfo(addressInfo);
        outputDTO.setListContact(listContact);
        outputDTO.setListPlanInfo(newListPlanInfo);
        outputDTO.setCustInfo(custInfo);
        outputDTO.setInputHeaderInfo(inputHeaderInfo);
        outputDTO.setBss(bss);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}