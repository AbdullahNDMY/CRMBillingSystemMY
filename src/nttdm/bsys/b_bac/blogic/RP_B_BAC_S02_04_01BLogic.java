/*
 * @(#)RP_B_BAC_S02_04_01BLogic.java
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
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_04_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_04_01Output;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class of B-BAC Merge init.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S02_04_01BLogic extends AbstractRP_B_BAC_S02_04_01BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    @SuppressWarnings("unchecked")
    public BLogicResult execute(RP_B_BAC_S02_04_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S02_04_01Output outputDTO = new RP_B_BAC_S02_04_01Output();
        
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        T_BAC_H inputHeaderInfo = null;
        if (!CommonUtils.isEmpty(param.getInputHeaderInfo())) {
            inputHeaderInfo = param.getInputHeaderInfo();
        } else {
            inputHeaderInfo = new T_BAC_H();
        }
        HashMap<String, Object> m = new HashMap<String, Object>();
        // String idCust = param.getCboCustomerName();
        // setup parameters
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
        m.put("idBillAccount", idBillAccs[0]);
        m.put("idCustPlans", idCustPlans);
        // get header info
        HashMap<String, Object> billAccountInfo = (HashMap<String, Object>) queryDAO.executeForMap(
                "B_BAC.getBillingAccountInfo", m);
        // get exportSingPost info
        if (!"1".equals(param.getNewFlg())) {
            inputHeaderInfo.setExportSingPost(param.getInputHeaderInfo().getExportSingPost());
        } else {
            inputHeaderInfo.setExportSingPost((String) billAccountInfo.get("IS_SINGPOST"));
        }
        // email set checked
        if (!"1".equals(param.getNewFlg())) {
            inputHeaderInfo.setDeliveryEmail(param.getInputHeaderInfo().getDeliveryEmail());
        } else {
            inputHeaderInfo.setDeliveryEmail((String) billAccountInfo.get("DELIVERY_EMAIL"));
        }
        //delivery set checked
        if (!"1".equals(param.getNewFlg())) {
            inputHeaderInfo.setDelivery(param.getInputHeaderInfo().getDelivery());
        } else {
            inputHeaderInfo.setDelivery((String) billAccountInfo.get("DELIVERY"));
        }
        // get cust info
        m.put("idCust", billAccountInfo.get("ID_CUST"));
        HashMap<String, Object> custInfo = (HashMap<String, Object>) queryDAO.executeForMap("B_BAC.getCustInfo", m);
        // get list contact
        List<LabelValueBean> listContact = this.queryDAO.executeForObjectList("B_BAC.getAllContact", m);
        // generate address info
        m.put("adrType", billAccountInfo.get("CUST_ADR_TYPE"));
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
            m.put("contactType", billAccountInfo.get("CONTACT_TYPE"));
            HashMap<String, Object> contactInfo = (HashMap<String, Object>) this.queryDAO.executeForMap(
                    "B_BAC.getSingleContact", m);
            if (contactInfo != null) {
                addressInfo.put("email", CommonUtils.toString(contactInfo.get("EMAIL")));
                addressInfo.put("email_cc", CommonUtils.toString(contactInfo.get("EMAIL_CC")));
            }
        } else {
            addressInfo.put("email", CommonUtils.toString(custInfo.get("CO_EMAIL")).trim());
        }

        // get bill ref info
        List<HashMap<String, Object>> billRefInfo = this.queryDAO.executeForObjectList("B_BAC.getDistBillRef", m);
        // list plan info
        List<HashMap<String, Object>> listPlanInfo = this.queryDAO.executeForObjectList(
                "B_BAC.getPlanInfoByIdCustPlans", m);

        // new newListPlanInfo
        List<HashMap<String, Object>> newListPlanInfo = new ArrayList<HashMap<String, Object>>();
        RP_B_BAC_commonBLogic logic = new RP_B_BAC_commonBLogic();
        newListPlanInfo = logic.getNewListInfo(listPlanInfo);

        // get condition to display bill ref
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> displayBillRefInfo = 
            (HashMap<String, Object>)this.queryDAO.executeForMap("B_BAC.getBillRefDisplayCond", m2);
        if (CommonUtils.isEmpty(displayBillRefInfo)) {
            // billRefInfo.put("displayCond", "1");
            outputDTO.setBillRefDispCond("0");
        } else {
            // billRefInfo.put("displayCond", "0");
            outputDTO.setBillRefDispCond("1");
        }
        // get list bill account
        HashMap<String, Object> m1 = new HashMap<String, Object>();
        m1.put("idCust", billAccountInfo.get("ID_CUST"));
        m1.put("billCurrency", billAccountInfo.get("BILL_CURRENCY"));
        m1.put("isDisplayExpAmt", billAccountInfo.get("IS_DISPLAY_EXP_AMT"));
        m1.put("exportCurrency", billAccountInfo.get("EXPORT_CURRENCY"));
        m1.put("fixedForex", billAccountInfo.get("FIXED_FOREX"));
        m1.put("taxType", billAccountInfo.get("TAX_TYPE"));

        List<LabelValueBean> listBillAccount = this.queryDAO.executeForObjectList("B_BAC.getListComboBillingAccount", m1);
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
        
        outputDTO.setListBillAccount(listBillAccount);
        outputDTO.setBillRefInfo(reConstructBillRefInfo(billRefInfo));
        outputDTO.setHeaderInfo(billAccountInfo);
        outputDTO.setListContact(listContact);
        outputDTO.setAddressInfo(addressInfo);
        outputDTO.setListPlanInfo(newListPlanInfo);
        outputDTO.setCustInfo(custInfo);
        outputDTO.setBss(bss);
        outputDTO.setInputHeaderInfo(inputHeaderInfo);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }

    /**
     * @param listBillRefInfoSrc
     *            List
     * @return list
     */
    private List<HashMap<String, Object>> reConstructBillRefInfo(List<HashMap<String, Object>> listBillRefInfoSrc) {
        // List for return (with none redundant data)
        List<HashMap<String, Object>> listBillRefInfoReturn = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> billRefInfoReturn = null;
        for (int i = 0; i < listBillRefInfoSrc.size(); i++) {
            // get current billRefInfo
            HashMap<String, Object> billRefInfoSrc = listBillRefInfoSrc.get(i);
            // get instance billRefInfoReturn
            billRefInfoReturn = new HashMap<String, Object>();
            if (i == 0) {
                // Map the first element
                billRefInfoReturn = listBillRefInfoSrc.get(i);
            } else { // Begin check the duplicate data and replace these values
                     // with null
                boolean checkIdBillAccount = false;
                boolean checkIdQcs = false;
                boolean checkIdQuo = false;
                boolean checkCustPo = false;
                boolean checkAcManager = false;
                boolean checkJobNo = false;
                boolean checkTerm = false;

                for (int j = 0; j < listBillRefInfoReturn.size(); j++) {
                    HashMap<String, Object> existBillRefInfoReturn = listBillRefInfoReturn.get(j);
                    if (!checkIdBillAccount) {
                        if (CommonUtils.toString(existBillRefInfoReturn.get("ID_BILL_ACCOUNT")).equals(
                                CommonUtils.toString(billRefInfoSrc.get("ID_BILL_ACCOUNT")))) {
                            checkIdBillAccount = true;
                        }
                    }
                    if (!checkIdQcs) {
                        if (CommonUtils.toString(existBillRefInfoReturn.get("ID_QCS")).equals(
                                CommonUtils.toString(billRefInfoSrc.get("ID_QCS")))) {
                            checkIdQcs = true;
                        }
                    }
                    if (!checkIdQuo) {
                        if (CommonUtils.toString(existBillRefInfoReturn.get("ID_QUO")).equals(
                                CommonUtils.toString(billRefInfoSrc.get("ID_QUO")))) {
                            checkIdQuo = true;
                        }
                    }
                    if (!checkCustPo) {
                        if (CommonUtils.toString(existBillRefInfoReturn.get("CUST_PO")).equals(
                                CommonUtils.toString(billRefInfoSrc.get("CUST_PO")))) {
                            checkCustPo = true;
                        }
                    }
                    if (!checkAcManager) {
                        if (CommonUtils.toString(existBillRefInfoReturn.get("AC_MANAGER")).equals(
                                CommonUtils.toString(billRefInfoSrc.get("AC_MANAGER")))) {
                            checkAcManager = true;
                        }
                    }
                    if (!checkJobNo) {
                        if (CommonUtils.toString(existBillRefInfoReturn.get("JOB_NO")).equals(
                                CommonUtils.toString(billRefInfoSrc.get("JOB_NO")))) {
                            checkJobNo = true;
                        }
                    }
                    if (!checkTerm) {
                        if (CommonUtils.toString(existBillRefInfoReturn.get("TERM")).equals(
                                CommonUtils.toString(billRefInfoSrc.get("TERM")))) {
                            checkTerm = true;
                        }
                    }
                }

                billRefInfoReturn.put("ID_BILL_ACCOUNT",
                        checkIdBillAccount ? null : billRefInfoSrc.get("ID_BILL_ACCOUNT"));
                billRefInfoReturn.put("ID_QCS", checkIdQcs ? null : billRefInfoSrc.get("ID_QCS"));
                billRefInfoReturn.put("ID_QUO", checkIdQuo ? null : billRefInfoSrc.get("ID_QUO"));
                billRefInfoReturn.put("CUST_PO", checkCustPo ? null : billRefInfoSrc.get("CUST_PO"));
                billRefInfoReturn.put("AC_MANAGER", checkAcManager ? null : billRefInfoSrc.get("AC_MANAGER"));
                billRefInfoReturn.put("JOB_NO", checkJobNo ? null : billRefInfoSrc.get("JOB_NO"));
                billRefInfoReturn.put("TERM", checkTerm ? null : billRefInfoSrc.get("TERM"));
            }
            listBillRefInfoReturn.add(billRefInfoReturn);
        }
        return listBillRefInfoReturn;
    }
}