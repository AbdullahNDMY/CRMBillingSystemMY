/**
 * @(#)RP_B_BIL_S03_02_03BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_03Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_03Output;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_02_03BLogic extends AbstractRP_B_BIL_S03_02_03BLogic {

    /**
     * 
     * @param param
     *            RP_B_BIL_S03_02_03Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S03_02_03Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S03_02_03Output outputDTO = new RP_B_BIL_S03_02_03Output();
        // param
        String idRef = param.getIdRef();

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idRef", idRef);
        // get list customer
        List<LabelValueBean> listCust = this.queryDAO.executeForObjectList(
                "B_BIL.getAllCust", m);
        m.put("idCust", param.getInputHeaderInfo().getIdCust());
        m.put("adrType", param.getInputHeaderInfo().getAdrType());
        // generate address info
        HashMap<String, Object> address = (HashMap<String, Object>) queryDAO.
               executeForMap("B_BIL.getCustAdr", m);
        if (address != null) {
            param.getInputHeaderInfo().setAddress1(
                    CommonUtils.toString(address.get("ADR_LINE1")));
            param.getInputHeaderInfo().setAddress2(
                    CommonUtils.toString(address.get("ADR_LINE2")));
            param.getInputHeaderInfo().setAddress3(
                    CommonUtils.toString(address.get("ADR_LINE3")));
            // generate address4
            ApplicationContext context = ApplicationContextProvider.
                                        getApplicationContext();
            MappedCodeListLoader countryCodeList = (MappedCodeListLoader) 
                                         context.getBean("LIST_COUNTRY");
            @SuppressWarnings("unchecked")
            Map<String, Object> countryMap = countryCodeList.getCodeListMap();
            String country = "";
            if (countryMap.containsKey(address.get("COUNTRY"))) {
                country = countryMap.get(address.get("COUNTRY")).toString();
            }
            param.getInputHeaderInfo().setAddress4(
                    CommonUtils.toString(address.get("ZIP_CODE")).
                    concat(", ").concat(country));
        }else{
            param.getInputHeaderInfo().setAddress1("");
            param.getInputHeaderInfo().setAddress2("");
            param.getInputHeaderInfo().setAddress3("");
            param.getInputHeaderInfo().setAddress4("");
        }
        // cust info
        HashMap<String, Object> custInfo = (HashMap<String, Object>) queryDAO.
                                  executeForMap("B_BIL.getSingleCustInfo", m);
        //CUSTOMER_TYPE
        String customerType = "";
        // cust info is not null
        if (custInfo != null) {
            customerType = CommonUtils.toString(custInfo.get("CUSTOMER_TYPE"));
            if ("CORP".equals(customerType)) {
                param.getInputHeaderInfo().setTelNo(
                        CommonUtils.toString(custInfo.get("CO_TEL_NO")));
                param.getInputHeaderInfo().setFaxNo(
                        CommonUtils.toString(custInfo.get("CO_FAX_NO")));
            } else {
                param.getInputHeaderInfo().setTelNo(
                        CommonUtils.toString(custInfo.get("HOME_TEL_NO")));
                param.getInputHeaderInfo().setFaxNo(
                        CommonUtils.toString(custInfo.get("HOME_FAX_NO")));
            }
        } else {
            // cust info is null
            param.getInputHeaderInfo().setTelNo("");
            param.getInputHeaderInfo().setFaxNo("");
        }

        // contact info
        List<LabelValueBean> listContact = null;
        // get list contact
        if ("CORP".equals(customerType)) {
            listContact = this.queryDAO.executeForObjectList(
                    "B_BIL.getAllContact", m);
        } else {
            listContact = this.queryDAO.executeForObjectList(
                    "B_BIL.getAllContact1", m);
        }
        String contactType = "";
        String changeTypeFlag = CommonUtils.toString(param.
                getInputHeaderInfo().getChangeTypeFlag());
        
        if (!"changeCustName".equals(changeTypeFlag)){
            if (0 < listContact.size()) {
                contactType = param.getInputHeaderInfo().getContactType();
            }
        }
        m.put("contactType", contactType);
        param.getInputHeaderInfo().setContactType(contactType);
        
        if ("CORP".equals(customerType)) {
            // get email info
            HashMap<String, Object> contactInfo = (HashMap<String, Object>) 
                queryDAO.executeForMap("B_BIL.getSingleContact", m);
            if (contactInfo != null) {
                param.getInputHeaderInfo().setContactEmail(
                        CommonUtils.toString(contactInfo.get("EMAIL")));
                param.getInputHeaderInfo().setContactName(
                        CommonUtils.toString(contactInfo.get("CONTACT_NAME")));
            } else {
                param.getInputHeaderInfo().setContactEmail("");
                param.getInputHeaderInfo().setContactName("");
            }
        } else {
            Map<String, Object> m1 = new HashMap<String, Object>();
            m1.put("idCust", contactType);
            // get email info
            HashMap<String, Object> contactInfo = (HashMap<String, Object>) 
                queryDAO.executeForMap("B_BIL.getSingleCustInfo", m1);
            if (contactInfo != null) {
                param.getInputHeaderInfo().setContactEmail(
                        CommonUtils.toString(contactInfo.get("CO_EMAIL")));
                param.getInputHeaderInfo().setContactName(
                        CommonUtils.toString(contactInfo.get("CUST_NAME")));
            } else {
                param.getInputHeaderInfo().setContactEmail("");
                param.getInputHeaderInfo().setContactName("");
            }
        }
        if (CommonUtils.isEmpty(contactType)) {
            param.getInputHeaderInfo().setContactName("");
        }
        // get listQcsRef
        // List<LabelValueBean> listQcsRef =
        // this.queryDAO.executeForObjectList("B_BIL.getAllQCSRef", m);
        // get listAllQuoRef
        // List<LabelValueBean> listAllQuoRef =
        // this.queryDAO.executeForObjectList("B_BIL.getAllQuoRef", m);
        // get listAcManager7
        List<LabelValueBean> listAcManager = this.queryDAO.
             executeForObjectList("B_BIL.getAllAcManager", m);
        // get listJobNo
        // List<LabelValueBean> listJobNo =
        // this.queryDAO.executeForObjectList("B_BIL.getAllJobNo", m);
        // Billing Account No.
        List<LabelValueBean> listBillingAccountNo = this.queryDAO.
             executeForObjectList("B_BIL.getBillingAccountNo", m);

        List<Map<String, Object>> footerInfo = this.queryDAO.executeForMapList(
                "B_BIL.getFooterInfo", null);
        /*
         * // generate detail info String[] itemDesc = param.getItemDesc();
         * String[] itemQty = param.getItemQty(); String[] itemUprice =
         * param.getItemUprice(); String[] itemAtm = param.getItemAtm();
         */
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
     // get bankFooterInfo
        List<Map<String, Object>> bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getBankFooterInfo", null);
        String billCurrency = CommonUtils.toString(param.getInputHeaderInfo().getBillCurrency()).trim();
        B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
        bankFooterInfo = bilUtil.getBankFooterInfo(billCurrency, bankFooterInfo);
        
        Map<String, Object> gstAmount = this.queryDAO.executeForMap(
                "B_BIL.getGstAmount", null);
        outputDTO.setGstValue(CommonUtils.toString(gstAmount.get("SET_VALUE")));
        outputDTO.setListCust(listCust);
        outputDTO.setListContact(listContact);
        // outputDTO.setListQcsRef(listQcsRef);
        // outputDTO.setListQuoRef(listAllQuoRef);
        outputDTO.setListAcMan(listAcManager);
        // Billing Account No.
        outputDTO.setListBillingAccountNo(listBillingAccountNo);
        outputDTO.setFooterInfo(footerInfo);
        outputDTO.setBankFooterInfo(bankFooterInfo);
        // outputDTO.setListJobNo(listJobNo);
        // outputDTO.setHeaderInfo(headerInfo);
        // outputDTO.setDetailInfo(detailInfo);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}