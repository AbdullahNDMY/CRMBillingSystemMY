/*
 * @(#)RP_B_BAC_S02_03_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_03_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_03_01Output;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class of B-BAC Edit init.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_03_01BLogic extends AbstractRP_B_BAC_S02_03_01BLogic {

    /**
     * B-BAC Edit init.
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    @SuppressWarnings("unchecked")
    public BLogicResult execute(RP_B_BAC_S02_03_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S02_03_01Output outputDTO = new RP_B_BAC_S02_03_01Output();

        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        T_BAC_H inputHeaderInfo = null;
        if (!CommonUtils.isEmpty(param.getInputHeaderInfo())) {
            inputHeaderInfo = param.getInputHeaderInfo();
        } else {
            inputHeaderInfo = new T_BAC_H();
        }
        // get idBillAccount
        String idBillAccount = param.getIdBillAccount();
        String idCustPlan = param.getIdCustPlan();
        // setup parameters
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("idBillAccount", idBillAccount);
        m.put("idCustPlan", idCustPlan);
        // get header info
        HashMap<String, Object> billAccountInfo = 
            (HashMap<String, Object>)queryDAO.executeForMap("B_BAC.getBillingAccountInfo", m);
        String custAdrType = CommonUtils.toString(billAccountInfo.get("CUST_ADR_TYPE"));
        m.put("adrType", custAdrType);
        // get cust info
        m.put("idCust", billAccountInfo.get("ID_CUST"));
        // get exportSingPost info
        if (!"1".equals(param.getEditFlg())) {
            inputHeaderInfo.setExportSingPost(param.getInputHeaderInfo().getExportSingPost());
            inputHeaderInfo.setMultiBillPeriod(param.getInputHeaderInfo().getMultiBillPeriod());
            inputHeaderInfo.setIsDisplayExpAmt(param.getInputHeaderInfo().getIsDisplayExpAmt());
            inputHeaderInfo.setDelivery(param.getInputHeaderInfo().getDelivery());
            inputHeaderInfo.setDeliveryEmail(param.getInputHeaderInfo().getDeliveryEmail());
        } else {
            inputHeaderInfo.setExportSingPost((String) billAccountInfo.get("IS_SINGPOST"));
            inputHeaderInfo.setMultiBillPeriod((String) billAccountInfo.get("MULTI_BILL_PERIOD"));
            inputHeaderInfo.setIsDisplayExpAmt((String) billAccountInfo.get("IS_DISPLAY_EXP_AMT"));
            inputHeaderInfo.setDelivery((String) billAccountInfo.get("DELIVERY"));
            inputHeaderInfo.setDeliveryEmail((String) billAccountInfo.get("DELIVERY_EMAIL"));
        }
        HashMap<String, Object> custInfo = (HashMap<String, Object>) queryDAO.executeForMap("B_BAC.getCustInfo", m);
        // get list contact
        List<LabelValueBean> listContact = this.queryDAO.executeForObjectList("B_BAC.getAllContact", m);
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
        if (!"1".equals(param.getEditFlg())) {
            String addType = CommonUtils.toString(inputHeaderInfo.getCustAdrType()).trim();
            if (CommonUtils.isEmpty(addType)) {
                addressInfo.put("address1", "");
                addressInfo.put("address2", "");
                addressInfo.put("address3", "");
                addressInfo.put("address4", "");
            }
        }
        String customerType = CommonUtils.toString(custInfo.get("CUSTOMER_TYPE")).trim();
        if (!"CONS".equals(customerType)) {
            // get email info
            m.put("contactType", billAccountInfo.get("CONTACT_TYPE"));
            HashMap<String, Object> contactInfo =
                (HashMap<String, Object>)this.queryDAO.executeForMap("B_BAC.getSingleContact", m);
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
        Integer totalGiro = this.queryDAO.executeForObject("B_BAC.getGIROCustBankInfo", m, Integer.class);
        Integer totalCC = this.queryDAO.executeForObject("B_BAC.getCCCustBankInfo", m, Integer.class);
        if (totalGiro > 0)
            isDisplayGiro = true;
        if (totalCC > 0)
            isDisplayCC = true;

        if (!"1".equals(param.getEditFlg())) {
            billAccountInfo.put("IS_SINGPOST", param.getInputHeaderInfo().getExportSingPost());
            billAccountInfo.put("PAYMENT_METHOD", param.getInputHeaderInfo().getPaymentMethod());
            billAccountInfo.put("CONTACT_TYPE", param.getInputHeaderInfo().getContactType());
            billAccountInfo.put("CUST_ADR_TYPE", param.getInputHeaderInfo().getCustAdrType());
            billAccountInfo.put("BILL_CURRENCY", param.getInputHeaderInfo().getBillCurrency());
            billAccountInfo.put("EXPORT_CURRENCY", param.getInputHeaderInfo().getExportCurrency());
            billAccountInfo.put("FIXED_FOREX", param.getInputHeaderInfo().getFixedForex());
            billAccountInfo.put("idCustPlan", param.getInputHeaderInfo().getIdCustPlan());
        } else {
            billAccountInfo.put("idCustPlan", idCustPlan);
        }

        // get grandTotal cur
        List<LabelValueBean> listGrandTotalCur = this.queryDAO.executeForObjectList("B_BAC.getGrandTotal", null);

        // get M_GSET_D info: FIXED_FOREX
        String fixedForex = queryDAO.executeForObject("B_BAC.getMGSetFixedForex", null, String.class);
        outputDTO.setFixedForex(fixedForex);
        
        // #175 Start
        String disPeriod_Amt = queryDAO.executeForObject("B_BAC.get_IsDisplay_Period_Amt", null, String.class);
        billAccountInfo.put("DIS_PERIOD_AMT", disPeriod_Amt);
        // #175 End
        
        // #191 Start
        String singPostValue = this.queryDAO.executeForObject("B_BAC.getSingPostValue", null, String.class);
        inputHeaderInfo.setSingPostValue(singPostValue);
        // #191 End   
        
        // get M_GSET_D info: CPM_TAX_TYPE_DISPLAY
        String taxTypeDisplay = queryDAO.executeForObject("B_BAC.getMGSetTaxType", null, String.class);
        outputDTO.setTaxTypeDisplay(taxTypeDisplay);
        
        outputDTO.setListGrandTotalCur(listGrandTotalCur);
        outputDTO.setIsDisplayGiro(isDisplayGiro);
        outputDTO.setIsDisplayCC(isDisplayCC);
        outputDTO.setHeaderInfo(billAccountInfo);
        outputDTO.setListContact(listContact);
        outputDTO.setAddressInfo(addressInfo);
        outputDTO.setCustInfo(custInfo);
        outputDTO.setBss(bss);
        outputDTO.setInputHeaderInfo(inputHeaderInfo);
        outputDTO.setFromPage(param.getFromPage());
        outputDTO.setIdRef(param.getIdRef());
        // store in session for editing function
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}